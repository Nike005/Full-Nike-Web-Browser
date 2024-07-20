package androidx.media2.session;

import android.content.Context;
import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.p064v4.media.session.MediaSessionCompat;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseArray;
import android.view.Surface;
import androidx.core.util.ObjectsCompat;
import androidx.media.MediaSessionManager;
import androidx.media2.common.MediaItem;
import androidx.media2.common.MediaMetadata;
import androidx.media2.common.MediaParcelUtils;
import androidx.media2.common.Rating;
import androidx.media2.common.SessionPlayer;
import androidx.media2.common.SubtitleData;
import androidx.media2.common.VideoSize;
import androidx.media2.session.IMediaSession;
import androidx.media2.session.MediaController;
import androidx.media2.session.MediaLibraryService;
import androidx.media2.session.MediaSession;
import androidx.media2.session.SessionCommandGroup;
import androidx.versionedparcelable.ParcelImpl;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

class MediaSessionStub extends IMediaSession.Stub {
    static final boolean DEBUG = Log.isLoggable(TAG, 3);
    private static final boolean RETHROW_EXCEPTION = true;
    private static final String TAG = "MediaSessionStub";
    static final SparseArray<SessionCommand> sCommandsForOnCommandRequest = new SparseArray<>();
    final ConnectedControllersManager<IBinder> mConnectedControllersManager;
    final Context mContext;
    final Object mLock = new Object();
    final MediaSession.MediaSessionImpl mSessionImpl;
    final MediaSessionManager mSessionManager;

    private interface LibrarySessionCallbackTask<T> extends SessionTask {
        T run(MediaSession.ControllerInfo controllerInfo) throws RemoteException;
    }

    private interface SessionCallbackTask<T> extends SessionTask {
        T run(MediaSession.ControllerInfo controllerInfo) throws RemoteException;
    }

    private interface SessionPlayerTask extends SessionTask {
        ListenableFuture<SessionPlayer.PlayerResult> run(MediaSession.ControllerInfo controllerInfo) throws RemoteException;
    }

    private interface SessionTask<T> {
    }

    static {
        for (SessionCommand next : new SessionCommandGroup.Builder().addAllPlayerCommands(1, false).addAllVolumeCommands(1).build().getCommands()) {
            sCommandsForOnCommandRequest.append(next.getCommandCode(), next);
        }
    }

    MediaSessionStub(MediaSession.MediaSessionImpl mediaSessionImpl) {
        this.mSessionImpl = mediaSessionImpl;
        Context context = mediaSessionImpl.getContext();
        this.mContext = context;
        this.mSessionManager = MediaSessionManager.getSessionManager(context);
        this.mConnectedControllersManager = new ConnectedControllersManager<>(mediaSessionImpl);
    }

    /* access modifiers changed from: package-private */
    public ConnectedControllersManager<IBinder> getConnectedControllersManager() {
        return this.mConnectedControllersManager;
    }

    static void sendSessionResult(MediaSession.ControllerInfo controllerInfo, int i, int i2) {
        sendSessionResult(controllerInfo, i, new SessionResult(i2));
    }

    static void sendSessionResult(MediaSession.ControllerInfo controllerInfo, int i, SessionResult sessionResult) {
        try {
            controllerInfo.getControllerCb().onSessionResult(i, sessionResult);
        } catch (RemoteException e) {
            Log.w(TAG, "Exception in " + controllerInfo.toString(), e);
        }
    }

    static void sendPlayerResult(MediaSession.ControllerInfo controllerInfo, int i, SessionPlayer.PlayerResult playerResult) {
        try {
            controllerInfo.getControllerCb().onPlayerResult(i, playerResult);
        } catch (RemoteException e) {
            Log.w(TAG, "Exception in " + controllerInfo.toString(), e);
        }
    }

    static void sendLibraryResult(MediaSession.ControllerInfo controllerInfo, int i, int i2) {
        sendLibraryResult(controllerInfo, i, new LibraryResult(i2));
    }

    static void sendLibraryResult(MediaSession.ControllerInfo controllerInfo, int i, LibraryResult libraryResult) {
        try {
            controllerInfo.getControllerCb().onLibraryResult(i, libraryResult);
        } catch (RemoteException e) {
            Log.w(TAG, "Exception in " + controllerInfo.toString(), e);
        }
    }

    private void dispatchSessionTask(IMediaController iMediaController, int i, int i2, SessionTask sessionTask) {
        dispatchSessionTaskInternal(iMediaController, i, (SessionCommand) null, i2, sessionTask);
    }

    private void dispatchSessionTask(IMediaController iMediaController, int i, SessionCommand sessionCommand, SessionTask sessionTask) {
        dispatchSessionTaskInternal(iMediaController, i, sessionCommand, 0, sessionTask);
    }

    private void dispatchSessionTaskInternal(IMediaController iMediaController, int i, SessionCommand sessionCommand, int i2, SessionTask sessionTask) {
        long clearCallingIdentity = Binder.clearCallingIdentity();
        try {
            final MediaSession.ControllerInfo controller = this.mConnectedControllersManager.getController(iMediaController.asBinder());
            if (!this.mSessionImpl.isClosed()) {
                if (controller != null) {
                    final SessionCommand sessionCommand2 = sessionCommand;
                    final int i3 = i2;
                    final int i4 = i;
                    final SessionTask sessionTask2 = sessionTask;
                    this.mSessionImpl.getCallbackExecutor().execute(new Runnable() {
                        public void run() {
                            SessionCommand sessionCommand;
                            if (MediaSessionStub.this.mConnectedControllersManager.isConnected(controller)) {
                                if (sessionCommand2 != null) {
                                    if (MediaSessionStub.this.mConnectedControllersManager.isAllowedCommand(controller, sessionCommand2)) {
                                        sessionCommand = MediaSessionStub.sCommandsForOnCommandRequest.get(sessionCommand2.getCommandCode());
                                    } else if (MediaSessionStub.DEBUG) {
                                        Log.d(MediaSessionStub.TAG, "Command (" + sessionCommand2 + ") from " + controller + " isn't allowed.");
                                        return;
                                    } else {
                                        return;
                                    }
                                } else if (MediaSessionStub.this.mConnectedControllersManager.isAllowedCommand(controller, i3)) {
                                    sessionCommand = MediaSessionStub.sCommandsForOnCommandRequest.get(i3);
                                } else if (MediaSessionStub.DEBUG) {
                                    Log.d(MediaSessionStub.TAG, "Command (" + i3 + ") from " + controller + " isn't allowed.");
                                    return;
                                } else {
                                    return;
                                }
                                if (sessionCommand != null) {
                                    try {
                                        int onCommandRequest = MediaSessionStub.this.mSessionImpl.getCallback().onCommandRequest(MediaSessionStub.this.mSessionImpl.getInstance(), controller, sessionCommand);
                                        if (onCommandRequest != 0) {
                                            if (MediaSessionStub.DEBUG) {
                                                Log.d(MediaSessionStub.TAG, "Command (" + sessionCommand + ") from " + controller + " was rejected by " + MediaSessionStub.this.mSessionImpl + ", code=" + onCommandRequest);
                                            }
                                            MediaSessionStub.sendSessionResult(controller, i4, onCommandRequest);
                                            return;
                                        }
                                    } catch (RemoteException e) {
                                        Log.w(MediaSessionStub.TAG, "Exception in " + controller.toString(), e);
                                        return;
                                    } catch (Exception e2) {
                                        throw e2;
                                    }
                                }
                                if (sessionTask2 instanceof SessionPlayerTask) {
                                    final ListenableFuture<SessionPlayer.PlayerResult> run = ((SessionPlayerTask) sessionTask2).run(controller);
                                    if (run != null) {
                                        run.addListener(new Runnable() {
                                            public void run() {
                                                try {
                                                    MediaSessionStub.sendPlayerResult(controller, i4, (SessionPlayer.PlayerResult) run.get(0, TimeUnit.MILLISECONDS));
                                                } catch (Exception e) {
                                                    Log.w(MediaSessionStub.TAG, "Cannot obtain PlayerResult after the command is finished", e);
                                                    MediaSessionStub.sendSessionResult(controller, i4, -2);
                                                }
                                            }
                                        }, MediaUtils.DIRECT_EXECUTOR);
                                        return;
                                    }
                                    throw new RuntimeException("SessionPlayer has returned null, commandCode=" + i3);
                                } else if (sessionTask2 instanceof SessionCallbackTask) {
                                    Object run2 = ((SessionCallbackTask) sessionTask2).run(controller);
                                    if (run2 == null) {
                                        throw new RuntimeException("SessionCallback has returned null, commandCode=" + i3);
                                    } else if (run2 instanceof Integer) {
                                        MediaSessionStub.sendSessionResult(controller, i4, ((Integer) run2).intValue());
                                    } else if (run2 instanceof SessionResult) {
                                        MediaSessionStub.sendSessionResult(controller, i4, (SessionResult) run2);
                                    } else if (MediaSessionStub.DEBUG) {
                                        throw new RuntimeException("Unexpected return type " + run2 + ". Fix bug");
                                    }
                                } else if (sessionTask2 instanceof LibrarySessionCallbackTask) {
                                    Object run3 = ((LibrarySessionCallbackTask) sessionTask2).run(controller);
                                    if (run3 == null) {
                                        throw new RuntimeException("LibrarySessionCallback has returned null, commandCode=" + i3);
                                    } else if (run3 instanceof Integer) {
                                        MediaSessionStub.sendLibraryResult(controller, i4, ((Integer) run3).intValue());
                                    } else if (run3 instanceof LibraryResult) {
                                        MediaSessionStub.sendLibraryResult(controller, i4, (LibraryResult) run3);
                                    } else if (MediaSessionStub.DEBUG) {
                                        throw new RuntimeException("Unexpected return type " + run3 + ". Fix bug");
                                    }
                                } else if (MediaSessionStub.DEBUG) {
                                    throw new RuntimeException("Unknown task " + sessionTask2 + ". Fix bug");
                                }
                            }
                        }
                    });
                    Binder.restoreCallingIdentity(clearCallingIdentity);
                }
            }
        } finally {
            Binder.restoreCallingIdentity(clearCallingIdentity);
        }
    }

    private void dispatchLibrarySessionTask(IMediaController iMediaController, int i, int i2, LibrarySessionCallbackTask librarySessionCallbackTask) {
        if (this.mSessionImpl instanceof MediaLibraryService.MediaLibrarySession.MediaLibrarySessionImpl) {
            dispatchSessionTaskInternal(iMediaController, i, (SessionCommand) null, i2, librarySessionCallbackTask);
            return;
        }
        throw new RuntimeException("MediaSession cannot handle MediaLibrarySession command");
    }

    /* access modifiers changed from: package-private */
    public void connect(final IMediaController iMediaController, String str, int i, int i2, Bundle bundle) {
        MediaSessionManager.RemoteUserInfo remoteUserInfo = new MediaSessionManager.RemoteUserInfo(str, i, i2);
        final MediaSession.ControllerInfo controllerInfo = new MediaSession.ControllerInfo(remoteUserInfo, this.mSessionManager.isTrustedForMediaControl(remoteUserInfo), new Controller2Cb(iMediaController), bundle);
        this.mSessionImpl.getCallbackExecutor().execute(new Runnable() {
            public void run() {
                SequencedFutureManager sequencedFutureManager;
                if (!MediaSessionStub.this.mSessionImpl.isClosed()) {
                    IBinder callbackBinder = ((Controller2Cb) controllerInfo.getControllerCb()).getCallbackBinder();
                    SessionCommandGroup onConnect = MediaSessionStub.this.mSessionImpl.getCallback().onConnect(MediaSessionStub.this.mSessionImpl.getInstance(), controllerInfo);
                    if (onConnect != null || controllerInfo.isTrusted()) {
                        if (MediaSessionStub.DEBUG) {
                            Log.d(MediaSessionStub.TAG, "Accepting connection, controllerInfo=" + controllerInfo + " allowedCommands=" + onConnect);
                        }
                        if (onConnect == null) {
                            onConnect = new SessionCommandGroup();
                        }
                        synchronized (MediaSessionStub.this.mLock) {
                            if (MediaSessionStub.this.mConnectedControllersManager.isConnected(controllerInfo)) {
                                Log.w(MediaSessionStub.TAG, "Controller " + controllerInfo + " has sent connection request multiple times");
                            }
                            MediaSessionStub.this.mConnectedControllersManager.addController(callbackBinder, controllerInfo, onConnect);
                            sequencedFutureManager = MediaSessionStub.this.mConnectedControllersManager.getSequencedFutureManager(controllerInfo);
                        }
                        MediaSessionStub mediaSessionStub = MediaSessionStub.this;
                        ConnectionResult connectionResult = new ConnectionResult(mediaSessionStub, mediaSessionStub.mSessionImpl, onConnect);
                        if (!MediaSessionStub.this.mSessionImpl.isClosed()) {
                            try {
                                iMediaController.onConnected(sequencedFutureManager.obtainNextSequenceNumber(), MediaParcelUtils.toParcelable(connectionResult));
                            } catch (RemoteException unused) {
                            }
                            MediaSessionStub.this.mSessionImpl.getCallback().onPostConnect(MediaSessionStub.this.mSessionImpl.getInstance(), controllerInfo);
                            return;
                        }
                        return;
                    }
                    if (MediaSessionStub.DEBUG) {
                        Log.d(MediaSessionStub.TAG, "Rejecting connection, controllerInfo=" + controllerInfo);
                    }
                    try {
                        iMediaController.onDisconnected(0);
                    } catch (RemoteException unused2) {
                    }
                }
            }
        });
    }

    /* access modifiers changed from: package-private */
    public MediaItem convertMediaItemOnExecutor(MediaSession.ControllerInfo controllerInfo, String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        MediaItem onCreateMediaItem = this.mSessionImpl.getCallback().onCreateMediaItem(this.mSessionImpl.getInstance(), controllerInfo, str);
        if (onCreateMediaItem == null) {
            Log.w(TAG, "onCreateMediaItem(mediaId=" + str + ") returned null. Ignoring");
        } else if (onCreateMediaItem.getMetadata() == null || !TextUtils.equals(str, onCreateMediaItem.getMetadata().getString("android.media.metadata.MEDIA_ID"))) {
            throw new RuntimeException("onCreateMediaItem(mediaId=" + str + "): media ID in the returned media item should match");
        }
        return onCreateMediaItem;
    }

    public void connect(IMediaController iMediaController, int i, ParcelImpl parcelImpl) throws RuntimeException {
        if (iMediaController != null && parcelImpl != null) {
            int callingUid = Binder.getCallingUid();
            int callingPid = Binder.getCallingPid();
            long clearCallingIdentity = Binder.clearCallingIdentity();
            ConnectionRequest connectionRequest = (ConnectionRequest) MediaParcelUtils.fromParcelable(parcelImpl);
            if (callingPid == 0) {
                callingPid = connectionRequest.getPid();
            }
            try {
                connect(iMediaController, connectionRequest.getPackageName(), callingPid, callingUid, connectionRequest.getConnectionHints());
            } finally {
                Binder.restoreCallingIdentity(clearCallingIdentity);
            }
        }
    }

    public void release(IMediaController iMediaController, int i) throws RemoteException {
        if (iMediaController != null) {
            long clearCallingIdentity = Binder.clearCallingIdentity();
            try {
                this.mConnectedControllersManager.removeController(iMediaController.asBinder());
            } finally {
                Binder.restoreCallingIdentity(clearCallingIdentity);
            }
        }
    }

    public void onControllerResult(IMediaController iMediaController, int i, ParcelImpl parcelImpl) {
        if (iMediaController != null && parcelImpl != null) {
            long clearCallingIdentity = Binder.clearCallingIdentity();
            try {
                SequencedFutureManager sequencedFutureManager = this.mConnectedControllersManager.getSequencedFutureManager(iMediaController.asBinder());
                if (sequencedFutureManager != null) {
                    sequencedFutureManager.setFutureResult(i, (SessionResult) MediaParcelUtils.fromParcelable(parcelImpl));
                    Binder.restoreCallingIdentity(clearCallingIdentity);
                }
            } finally {
                Binder.restoreCallingIdentity(clearCallingIdentity);
            }
        }
    }

    public void setVolumeTo(IMediaController iMediaController, int i, final int i2, final int i3) throws RuntimeException {
        if (iMediaController != null) {
            dispatchSessionTask(iMediaController, i, 30000, (SessionTask) new SessionCallbackTask<Integer>() {
                public Integer run(MediaSession.ControllerInfo controllerInfo) {
                    MediaSessionCompat sessionCompat = MediaSessionStub.this.mSessionImpl.getSessionCompat();
                    if (sessionCompat != null) {
                        sessionCompat.getController().setVolumeTo(i2, i3);
                    }
                    return 0;
                }
            });
        }
    }

    public void adjustVolume(IMediaController iMediaController, int i, final int i2, final int i3) throws RuntimeException {
        if (iMediaController != null) {
            dispatchSessionTask(iMediaController, i, (int) SessionCommand.COMMAND_CODE_VOLUME_ADJUST_VOLUME, (SessionTask) new SessionCallbackTask<Integer>() {
                public Integer run(MediaSession.ControllerInfo controllerInfo) {
                    MediaSessionCompat sessionCompat = MediaSessionStub.this.mSessionImpl.getSessionCompat();
                    if (sessionCompat != null) {
                        sessionCompat.getController().adjustVolume(i2, i3);
                    }
                    return 0;
                }
            });
        }
    }

    public void play(IMediaController iMediaController, int i) throws RuntimeException {
        if (iMediaController != null) {
            dispatchSessionTask(iMediaController, i, 10000, (SessionTask) new SessionPlayerTask() {
                public ListenableFuture<SessionPlayer.PlayerResult> run(MediaSession.ControllerInfo controllerInfo) {
                    return MediaSessionStub.this.mSessionImpl.play();
                }
            });
        }
    }

    public void pause(IMediaController iMediaController, int i) throws RuntimeException {
        if (iMediaController != null) {
            dispatchSessionTask(iMediaController, i, (int) SessionCommand.COMMAND_CODE_PLAYER_PAUSE, (SessionTask) new SessionPlayerTask() {
                public ListenableFuture<SessionPlayer.PlayerResult> run(MediaSession.ControllerInfo controllerInfo) {
                    return MediaSessionStub.this.mSessionImpl.pause();
                }
            });
        }
    }

    public void prepare(IMediaController iMediaController, int i) throws RuntimeException {
        if (iMediaController != null) {
            dispatchSessionTask(iMediaController, i, (int) SessionCommand.COMMAND_CODE_PLAYER_PREPARE, (SessionTask) new SessionPlayerTask() {
                public ListenableFuture<SessionPlayer.PlayerResult> run(MediaSession.ControllerInfo controllerInfo) {
                    return MediaSessionStub.this.mSessionImpl.prepare();
                }
            });
        }
    }

    public void fastForward(IMediaController iMediaController, int i) {
        if (iMediaController != null) {
            dispatchSessionTask(iMediaController, i, (int) SessionCommand.COMMAND_CODE_SESSION_FAST_FORWARD, (SessionTask) new SessionCallbackTask<Integer>() {
                public Integer run(MediaSession.ControllerInfo controllerInfo) {
                    return Integer.valueOf(MediaSessionStub.this.mSessionImpl.getCallback().onFastForward(MediaSessionStub.this.mSessionImpl.getInstance(), controllerInfo));
                }
            });
        }
    }

    public void rewind(IMediaController iMediaController, int i) {
        if (iMediaController != null) {
            dispatchSessionTask(iMediaController, i, (int) SessionCommand.COMMAND_CODE_SESSION_REWIND, (SessionTask) new SessionCallbackTask<Integer>() {
                public Integer run(MediaSession.ControllerInfo controllerInfo) {
                    return Integer.valueOf(MediaSessionStub.this.mSessionImpl.getCallback().onRewind(MediaSessionStub.this.mSessionImpl.getInstance(), controllerInfo));
                }
            });
        }
    }

    public void skipForward(IMediaController iMediaController, int i) {
        if (iMediaController != null) {
            dispatchSessionTask(iMediaController, i, (int) SessionCommand.COMMAND_CODE_SESSION_SKIP_FORWARD, (SessionTask) new SessionCallbackTask<Integer>() {
                public Integer run(MediaSession.ControllerInfo controllerInfo) {
                    return Integer.valueOf(MediaSessionStub.this.mSessionImpl.getCallback().onSkipForward(MediaSessionStub.this.mSessionImpl.getInstance(), controllerInfo));
                }
            });
        }
    }

    public void skipBackward(IMediaController iMediaController, int i) {
        if (iMediaController != null) {
            dispatchSessionTask(iMediaController, i, (int) SessionCommand.COMMAND_CODE_SESSION_SKIP_BACKWARD, (SessionTask) new SessionCallbackTask<Integer>() {
                public Integer run(MediaSession.ControllerInfo controllerInfo) {
                    return Integer.valueOf(MediaSessionStub.this.mSessionImpl.getCallback().onSkipBackward(MediaSessionStub.this.mSessionImpl.getInstance(), controllerInfo));
                }
            });
        }
    }

    public void seekTo(IMediaController iMediaController, int i, final long j) throws RuntimeException {
        if (iMediaController != null) {
            dispatchSessionTask(iMediaController, i, (int) SessionCommand.COMMAND_CODE_PLAYER_SEEK_TO, (SessionTask) new SessionPlayerTask() {
                public ListenableFuture<SessionPlayer.PlayerResult> run(MediaSession.ControllerInfo controllerInfo) {
                    return MediaSessionStub.this.mSessionImpl.seekTo(j);
                }
            });
        }
    }

    public void onCustomCommand(IMediaController iMediaController, int i, ParcelImpl parcelImpl, final Bundle bundle) {
        if (iMediaController != null && parcelImpl != null) {
            final SessionCommand sessionCommand = (SessionCommand) MediaParcelUtils.fromParcelable(parcelImpl);
            dispatchSessionTask(iMediaController, i, sessionCommand, (SessionTask) new SessionCallbackTask<SessionResult>() {
                public SessionResult run(MediaSession.ControllerInfo controllerInfo) {
                    SessionResult onCustomCommand = MediaSessionStub.this.mSessionImpl.getCallback().onCustomCommand(MediaSessionStub.this.mSessionImpl.getInstance(), controllerInfo, sessionCommand, bundle);
                    if (onCustomCommand != null) {
                        return onCustomCommand;
                    }
                    throw new RuntimeException("SessionCallback#onCustomCommand has returned null, command=" + sessionCommand);
                }
            });
        }
    }

    public void prepareFromUri(IMediaController iMediaController, int i, final Uri uri, final Bundle bundle) {
        if (iMediaController != null) {
            dispatchSessionTask(iMediaController, i, (int) SessionCommand.COMMAND_CODE_SESSION_PREPARE_FROM_URI, (SessionTask) new SessionCallbackTask<Integer>() {
                public Integer run(MediaSession.ControllerInfo controllerInfo) {
                    if (uri != null) {
                        return Integer.valueOf(MediaSessionStub.this.mSessionImpl.getCallback().onPrepareFromUri(MediaSessionStub.this.mSessionImpl.getInstance(), controllerInfo, uri, bundle));
                    }
                    Log.w(MediaSessionStub.TAG, "prepareFromUri(): Ignoring null uri from " + controllerInfo);
                    return -3;
                }
            });
        }
    }

    public void prepareFromSearch(IMediaController iMediaController, int i, final String str, final Bundle bundle) {
        if (iMediaController != null) {
            dispatchSessionTask(iMediaController, i, (int) SessionCommand.COMMAND_CODE_SESSION_PREPARE_FROM_SEARCH, (SessionTask) new SessionCallbackTask<Integer>() {
                public Integer run(MediaSession.ControllerInfo controllerInfo) {
                    if (!TextUtils.isEmpty(str)) {
                        return Integer.valueOf(MediaSessionStub.this.mSessionImpl.getCallback().onPrepareFromSearch(MediaSessionStub.this.mSessionImpl.getInstance(), controllerInfo, str, bundle));
                    }
                    Log.w(MediaSessionStub.TAG, "prepareFromSearch(): Ignoring empty query from " + controllerInfo);
                    return -3;
                }
            });
        }
    }

    public void prepareFromMediaId(IMediaController iMediaController, int i, final String str, final Bundle bundle) {
        if (iMediaController != null) {
            dispatchSessionTask(iMediaController, i, (int) SessionCommand.COMMAND_CODE_SESSION_PREPARE_FROM_MEDIA_ID, (SessionTask) new SessionCallbackTask<Integer>() {
                public Integer run(MediaSession.ControllerInfo controllerInfo) {
                    if (!TextUtils.isEmpty(str)) {
                        return Integer.valueOf(MediaSessionStub.this.mSessionImpl.getCallback().onPrepareFromMediaId(MediaSessionStub.this.mSessionImpl.getInstance(), controllerInfo, str, bundle));
                    }
                    Log.w(MediaSessionStub.TAG, "prepareFromMediaId(): Ignoring empty mediaId from " + controllerInfo);
                    return -3;
                }
            });
        }
    }

    public void playFromUri(IMediaController iMediaController, int i, final Uri uri, final Bundle bundle) {
        if (iMediaController != null) {
            dispatchSessionTask(iMediaController, i, (int) SessionCommand.COMMAND_CODE_SESSION_PLAY_FROM_URI, (SessionTask) new SessionCallbackTask<Integer>() {
                public Integer run(MediaSession.ControllerInfo controllerInfo) {
                    if (uri != null) {
                        return Integer.valueOf(MediaSessionStub.this.mSessionImpl.getCallback().onPlayFromUri(MediaSessionStub.this.mSessionImpl.getInstance(), controllerInfo, uri, bundle));
                    }
                    Log.w(MediaSessionStub.TAG, "playFromUri(): Ignoring null uri from " + controllerInfo);
                    return -3;
                }
            });
        }
    }

    public void playFromSearch(IMediaController iMediaController, int i, final String str, final Bundle bundle) {
        if (iMediaController != null) {
            dispatchSessionTask(iMediaController, i, (int) SessionCommand.COMMAND_CODE_SESSION_PLAY_FROM_SEARCH, (SessionTask) new SessionCallbackTask<Integer>() {
                public Integer run(MediaSession.ControllerInfo controllerInfo) {
                    if (!TextUtils.isEmpty(str)) {
                        return Integer.valueOf(MediaSessionStub.this.mSessionImpl.getCallback().onPlayFromSearch(MediaSessionStub.this.mSessionImpl.getInstance(), controllerInfo, str, bundle));
                    }
                    Log.w(MediaSessionStub.TAG, "playFromSearch(): Ignoring empty query from " + controllerInfo);
                    return -3;
                }
            });
        }
    }

    public void playFromMediaId(IMediaController iMediaController, int i, final String str, final Bundle bundle) {
        if (iMediaController != null) {
            dispatchSessionTask(iMediaController, i, (int) SessionCommand.COMMAND_CODE_SESSION_PLAY_FROM_MEDIA_ID, (SessionTask) new SessionCallbackTask<Integer>() {
                public Integer run(MediaSession.ControllerInfo controllerInfo) {
                    if (!TextUtils.isEmpty(str)) {
                        return Integer.valueOf(MediaSessionStub.this.mSessionImpl.getCallback().onPlayFromMediaId(MediaSessionStub.this.mSessionImpl.getInstance(), controllerInfo, str, bundle));
                    }
                    Log.w(MediaSessionStub.TAG, "playFromMediaId(): Ignoring empty mediaId from " + controllerInfo);
                    return -3;
                }
            });
        }
    }

    public void setRating(IMediaController iMediaController, int i, final String str, ParcelImpl parcelImpl) {
        if (iMediaController != null && parcelImpl != null) {
            final Rating rating = (Rating) MediaParcelUtils.fromParcelable(parcelImpl);
            dispatchSessionTask(iMediaController, i, (int) SessionCommand.COMMAND_CODE_SESSION_SET_RATING, (SessionTask) new SessionCallbackTask<Integer>() {
                public Integer run(MediaSession.ControllerInfo controllerInfo) {
                    if (TextUtils.isEmpty(str)) {
                        Log.w(MediaSessionStub.TAG, "setRating(): Ignoring empty mediaId from " + controllerInfo);
                        return -3;
                    } else if (rating != null) {
                        return Integer.valueOf(MediaSessionStub.this.mSessionImpl.getCallback().onSetRating(MediaSessionStub.this.mSessionImpl.getInstance(), controllerInfo, str, rating));
                    } else {
                        Log.w(MediaSessionStub.TAG, "setRating(): Ignoring null rating from " + controllerInfo);
                        return -3;
                    }
                }
            });
        }
    }

    public void setPlaybackSpeed(IMediaController iMediaController, int i, final float f) {
        if (iMediaController != null) {
            dispatchSessionTask(iMediaController, i, (int) SessionCommand.COMMAND_CODE_PLAYER_SET_SPEED, (SessionTask) new SessionPlayerTask() {
                public ListenableFuture<SessionPlayer.PlayerResult> run(MediaSession.ControllerInfo controllerInfo) {
                    return MediaSessionStub.this.mSessionImpl.setPlaybackSpeed(f);
                }
            });
        }
    }

    public void setPlaylist(IMediaController iMediaController, int i, final List<String> list, final ParcelImpl parcelImpl) {
        if (iMediaController != null && parcelImpl != null) {
            dispatchSessionTask(iMediaController, i, (int) SessionCommand.COMMAND_CODE_PLAYER_SET_PLAYLIST, (SessionTask) new SessionPlayerTask() {
                public ListenableFuture<SessionPlayer.PlayerResult> run(MediaSession.ControllerInfo controllerInfo) {
                    if (list == null) {
                        Log.w(MediaSessionStub.TAG, "setPlaylist(): Ignoring null playlist from " + controllerInfo);
                        return SessionPlayer.PlayerResult.createFuture(-3);
                    }
                    ArrayList arrayList = new ArrayList();
                    for (int i = 0; i < list.size(); i++) {
                        MediaItem convertMediaItemOnExecutor = MediaSessionStub.this.convertMediaItemOnExecutor(controllerInfo, (String) list.get(i));
                        if (convertMediaItemOnExecutor != null) {
                            arrayList.add(convertMediaItemOnExecutor);
                        }
                    }
                    return MediaSessionStub.this.mSessionImpl.setPlaylist(arrayList, (MediaMetadata) MediaParcelUtils.fromParcelable(parcelImpl));
                }
            });
        }
    }

    public void setMediaItem(IMediaController iMediaController, int i, final String str) {
        if (iMediaController != null) {
            dispatchSessionTask(iMediaController, i, (int) SessionCommand.COMMAND_CODE_PLAYER_SET_MEDIA_ITEM, (SessionTask) new SessionPlayerTask() {
                public ListenableFuture<SessionPlayer.PlayerResult> run(MediaSession.ControllerInfo controllerInfo) {
                    if (TextUtils.isEmpty(str)) {
                        Log.w(MediaSessionStub.TAG, "setMediaItem(): Ignoring empty mediaId from " + controllerInfo);
                        return SessionPlayer.PlayerResult.createFuture(-3);
                    }
                    MediaItem convertMediaItemOnExecutor = MediaSessionStub.this.convertMediaItemOnExecutor(controllerInfo, str);
                    if (convertMediaItemOnExecutor == null) {
                        return SessionPlayer.PlayerResult.createFuture(-3);
                    }
                    return MediaSessionStub.this.mSessionImpl.setMediaItem(convertMediaItemOnExecutor);
                }
            });
        }
    }

    public void updatePlaylistMetadata(IMediaController iMediaController, int i, final ParcelImpl parcelImpl) {
        if (iMediaController != null) {
            dispatchSessionTask(iMediaController, i, (int) SessionCommand.COMMAND_CODE_PLAYER_UPDATE_LIST_METADATA, (SessionTask) new SessionPlayerTask() {
                public ListenableFuture<SessionPlayer.PlayerResult> run(MediaSession.ControllerInfo controllerInfo) {
                    return MediaSessionStub.this.mSessionImpl.updatePlaylistMetadata((MediaMetadata) MediaParcelUtils.fromParcelable(parcelImpl));
                }
            });
        }
    }

    public void addPlaylistItem(IMediaController iMediaController, int i, final int i2, final String str) {
        if (iMediaController != null) {
            dispatchSessionTask(iMediaController, i, (int) SessionCommand.COMMAND_CODE_PLAYER_ADD_PLAYLIST_ITEM, (SessionTask) new SessionPlayerTask() {
                public ListenableFuture<SessionPlayer.PlayerResult> run(MediaSession.ControllerInfo controllerInfo) {
                    if (TextUtils.isEmpty(str)) {
                        Log.w(MediaSessionStub.TAG, "addPlaylistItem(): Ignoring empty mediaId from " + controllerInfo);
                        return SessionPlayer.PlayerResult.createFuture(-3);
                    }
                    MediaItem convertMediaItemOnExecutor = MediaSessionStub.this.convertMediaItemOnExecutor(controllerInfo, str);
                    if (convertMediaItemOnExecutor == null) {
                        return SessionPlayer.PlayerResult.createFuture(-3);
                    }
                    return MediaSessionStub.this.mSessionImpl.addPlaylistItem(i2, convertMediaItemOnExecutor);
                }
            });
        }
    }

    public void removePlaylistItem(IMediaController iMediaController, int i, final int i2) {
        if (iMediaController != null) {
            dispatchSessionTask(iMediaController, i, (int) SessionCommand.COMMAND_CODE_PLAYER_REMOVE_PLAYLIST_ITEM, (SessionTask) new SessionPlayerTask() {
                public ListenableFuture<SessionPlayer.PlayerResult> run(MediaSession.ControllerInfo controllerInfo) {
                    return MediaSessionStub.this.mSessionImpl.removePlaylistItem(i2);
                }
            });
        }
    }

    public void replacePlaylistItem(IMediaController iMediaController, int i, final int i2, final String str) {
        if (iMediaController != null) {
            dispatchSessionTask(iMediaController, i, (int) SessionCommand.COMMAND_CODE_PLAYER_REPLACE_PLAYLIST_ITEM, (SessionTask) new SessionPlayerTask() {
                public ListenableFuture<SessionPlayer.PlayerResult> run(MediaSession.ControllerInfo controllerInfo) {
                    if (TextUtils.isEmpty(str)) {
                        Log.w(MediaSessionStub.TAG, "replacePlaylistItem(): Ignoring empty mediaId from " + controllerInfo);
                        return SessionPlayer.PlayerResult.createFuture(-3);
                    }
                    MediaItem convertMediaItemOnExecutor = MediaSessionStub.this.convertMediaItemOnExecutor(controllerInfo, str);
                    if (convertMediaItemOnExecutor == null) {
                        return SessionPlayer.PlayerResult.createFuture(-3);
                    }
                    return MediaSessionStub.this.mSessionImpl.replacePlaylistItem(i2, convertMediaItemOnExecutor);
                }
            });
        }
    }

    public void skipToPlaylistItem(IMediaController iMediaController, int i, final int i2) {
        if (iMediaController != null) {
            dispatchSessionTask(iMediaController, i, (int) SessionCommand.COMMAND_CODE_PLAYER_SKIP_TO_PLAYLIST_ITEM, (SessionTask) new SessionPlayerTask() {
                public ListenableFuture<SessionPlayer.PlayerResult> run(MediaSession.ControllerInfo controllerInfo) {
                    if (i2 >= 0) {
                        return MediaSessionStub.this.mSessionImpl.skipToPlaylistItem(i2);
                    }
                    Log.w(MediaSessionStub.TAG, "skipToPlaylistItem(): Ignoring negative index from " + controllerInfo);
                    return SessionPlayer.PlayerResult.createFuture(-3);
                }
            });
        }
    }

    public void skipToPreviousItem(IMediaController iMediaController, int i) {
        if (iMediaController != null) {
            dispatchSessionTask(iMediaController, i, (int) SessionCommand.COMMAND_CODE_PLAYER_SKIP_TO_PREVIOUS_PLAYLIST_ITEM, (SessionTask) new SessionPlayerTask() {
                public ListenableFuture<SessionPlayer.PlayerResult> run(MediaSession.ControllerInfo controllerInfo) {
                    return MediaSessionStub.this.mSessionImpl.skipToPreviousItem();
                }
            });
        }
    }

    public void skipToNextItem(IMediaController iMediaController, int i) {
        if (iMediaController != null) {
            dispatchSessionTask(iMediaController, i, (int) SessionCommand.COMMAND_CODE_PLAYER_SKIP_TO_NEXT_PLAYLIST_ITEM, (SessionTask) new SessionPlayerTask() {
                public ListenableFuture<SessionPlayer.PlayerResult> run(MediaSession.ControllerInfo controllerInfo) {
                    return MediaSessionStub.this.mSessionImpl.skipToNextItem();
                }
            });
        }
    }

    public void setRepeatMode(IMediaController iMediaController, int i, final int i2) {
        if (iMediaController != null) {
            dispatchSessionTask(iMediaController, i, (int) SessionCommand.COMMAND_CODE_PLAYER_SET_REPEAT_MODE, (SessionTask) new SessionPlayerTask() {
                public ListenableFuture<SessionPlayer.PlayerResult> run(MediaSession.ControllerInfo controllerInfo) {
                    return MediaSessionStub.this.mSessionImpl.setRepeatMode(i2);
                }
            });
        }
    }

    public void setShuffleMode(IMediaController iMediaController, int i, final int i2) {
        if (iMediaController != null) {
            dispatchSessionTask(iMediaController, i, (int) SessionCommand.COMMAND_CODE_PLAYER_SET_SHUFFLE_MODE, (SessionTask) new SessionPlayerTask() {
                public ListenableFuture<SessionPlayer.PlayerResult> run(MediaSession.ControllerInfo controllerInfo) {
                    return MediaSessionStub.this.mSessionImpl.setShuffleMode(i2);
                }
            });
        }
    }

    public void setSurface(IMediaController iMediaController, int i, final Surface surface) {
        if (iMediaController != null) {
            dispatchSessionTask(iMediaController, i, (int) SessionCommand.COMMAND_CODE_PLAYER_SET_SURFACE, (SessionTask) new SessionPlayerTask() {
                public ListenableFuture<SessionPlayer.PlayerResult> run(MediaSession.ControllerInfo controllerInfo) {
                    return MediaSessionStub.this.mSessionImpl.setSurface(surface);
                }
            });
        }
    }

    public void selectTrack(IMediaController iMediaController, int i, final ParcelImpl parcelImpl) {
        if (iMediaController != null && parcelImpl != null) {
            dispatchSessionTask(iMediaController, i, (int) SessionCommand.COMMAND_CODE_PLAYER_SELECT_TRACK, (SessionTask) new SessionPlayerTask() {
                public ListenableFuture<SessionPlayer.PlayerResult> run(MediaSession.ControllerInfo controllerInfo) {
                    SessionPlayer.TrackInfo trackInfo = (SessionPlayer.TrackInfo) MediaParcelUtils.fromParcelable(parcelImpl);
                    if (trackInfo == null) {
                        return SessionPlayer.PlayerResult.createFuture(-3);
                    }
                    return MediaSessionStub.this.mSessionImpl.selectTrack(trackInfo);
                }
            });
        }
    }

    public void deselectTrack(IMediaController iMediaController, int i, final ParcelImpl parcelImpl) {
        if (iMediaController != null && parcelImpl != null) {
            dispatchSessionTask(iMediaController, i, (int) SessionCommand.COMMAND_CODE_PLAYER_DESELECT_TRACK, (SessionTask) new SessionPlayerTask() {
                public ListenableFuture<SessionPlayer.PlayerResult> run(MediaSession.ControllerInfo controllerInfo) {
                    SessionPlayer.TrackInfo trackInfo = (SessionPlayer.TrackInfo) MediaParcelUtils.fromParcelable(parcelImpl);
                    if (trackInfo == null) {
                        return SessionPlayer.PlayerResult.createFuture(-3);
                    }
                    return MediaSessionStub.this.mSessionImpl.deselectTrack(trackInfo);
                }
            });
        }
    }

    /* access modifiers changed from: package-private */
    public MediaLibraryService.MediaLibrarySession.MediaLibrarySessionImpl getLibrarySession() {
        MediaSession.MediaSessionImpl mediaSessionImpl = this.mSessionImpl;
        if (mediaSessionImpl instanceof MediaLibraryService.MediaLibrarySession.MediaLibrarySessionImpl) {
            return (MediaLibraryService.MediaLibrarySession.MediaLibrarySessionImpl) mediaSessionImpl;
        }
        throw new RuntimeException("Session cannot be casted to library session");
    }

    public void getLibraryRoot(IMediaController iMediaController, int i, final ParcelImpl parcelImpl) throws RuntimeException {
        if (iMediaController != null && parcelImpl != null) {
            dispatchLibrarySessionTask(iMediaController, i, 50000, new LibrarySessionCallbackTask<LibraryResult>() {
                public LibraryResult run(MediaSession.ControllerInfo controllerInfo) {
                    return MediaSessionStub.this.getLibrarySession().onGetLibraryRootOnExecutor(controllerInfo, (MediaLibraryService.LibraryParams) MediaParcelUtils.fromParcelable(parcelImpl));
                }
            });
        }
    }

    public void getItem(IMediaController iMediaController, int i, final String str) throws RuntimeException {
        dispatchLibrarySessionTask(iMediaController, i, SessionCommand.COMMAND_CODE_LIBRARY_GET_ITEM, new LibrarySessionCallbackTask<LibraryResult>() {
            public LibraryResult run(MediaSession.ControllerInfo controllerInfo) {
                if (!TextUtils.isEmpty(str)) {
                    return MediaSessionStub.this.getLibrarySession().onGetItemOnExecutor(controllerInfo, str);
                }
                Log.w(MediaSessionStub.TAG, "getItem(): Ignoring empty mediaId from " + controllerInfo);
                return new LibraryResult(-3);
            }
        });
    }

    public void getChildren(IMediaController iMediaController, int i, String str, int i2, int i3, ParcelImpl parcelImpl) throws RuntimeException {
        if (iMediaController != null && parcelImpl != null) {
            final String str2 = str;
            final int i4 = i2;
            final int i5 = i3;
            final ParcelImpl parcelImpl2 = parcelImpl;
            dispatchLibrarySessionTask(iMediaController, i, SessionCommand.COMMAND_CODE_LIBRARY_GET_CHILDREN, new LibrarySessionCallbackTask<LibraryResult>() {
                public LibraryResult run(MediaSession.ControllerInfo controllerInfo) {
                    if (TextUtils.isEmpty(str2)) {
                        Log.w(MediaSessionStub.TAG, "getChildren(): Ignoring empty parentId from " + controllerInfo);
                        return new LibraryResult(-3);
                    } else if (i4 < 0) {
                        Log.w(MediaSessionStub.TAG, "getChildren(): Ignoring negative page from " + controllerInfo);
                        return new LibraryResult(-3);
                    } else if (i5 < 1) {
                        Log.w(MediaSessionStub.TAG, "getChildren(): Ignoring pageSize less than 1 from " + controllerInfo);
                        return new LibraryResult(-3);
                    } else {
                        return MediaSessionStub.this.getLibrarySession().onGetChildrenOnExecutor(controllerInfo, str2, i4, i5, (MediaLibraryService.LibraryParams) MediaParcelUtils.fromParcelable(parcelImpl2));
                    }
                }
            });
        }
    }

    public void search(IMediaController iMediaController, int i, final String str, final ParcelImpl parcelImpl) {
        if (iMediaController != null && parcelImpl != null) {
            dispatchLibrarySessionTask(iMediaController, i, SessionCommand.COMMAND_CODE_LIBRARY_SEARCH, new LibrarySessionCallbackTask<Integer>() {
                public Integer run(MediaSession.ControllerInfo controllerInfo) {
                    if (!TextUtils.isEmpty(str)) {
                        return Integer.valueOf(MediaSessionStub.this.getLibrarySession().onSearchOnExecutor(controllerInfo, str, (MediaLibraryService.LibraryParams) MediaParcelUtils.fromParcelable(parcelImpl)));
                    }
                    Log.w(MediaSessionStub.TAG, "search(): Ignoring empty query from " + controllerInfo);
                    return -3;
                }
            });
        }
    }

    public void getSearchResult(IMediaController iMediaController, int i, String str, int i2, int i3, ParcelImpl parcelImpl) {
        if (iMediaController != null && parcelImpl != null) {
            final String str2 = str;
            final int i4 = i2;
            final int i5 = i3;
            final ParcelImpl parcelImpl2 = parcelImpl;
            dispatchLibrarySessionTask(iMediaController, i, SessionCommand.COMMAND_CODE_LIBRARY_GET_SEARCH_RESULT, new LibrarySessionCallbackTask<LibraryResult>() {
                public LibraryResult run(MediaSession.ControllerInfo controllerInfo) {
                    if (TextUtils.isEmpty(str2)) {
                        Log.w(MediaSessionStub.TAG, "getSearchResult(): Ignoring empty query from " + controllerInfo);
                        return new LibraryResult(-3);
                    } else if (i4 < 0) {
                        Log.w(MediaSessionStub.TAG, "getSearchResult(): Ignoring negative page from " + controllerInfo);
                        return new LibraryResult(-3);
                    } else if (i5 < 1) {
                        Log.w(MediaSessionStub.TAG, "getSearchResult(): Ignoring pageSize less than 1 from " + controllerInfo);
                        return new LibraryResult(-3);
                    } else {
                        return MediaSessionStub.this.getLibrarySession().onGetSearchResultOnExecutor(controllerInfo, str2, i4, i5, (MediaLibraryService.LibraryParams) MediaParcelUtils.fromParcelable(parcelImpl2));
                    }
                }
            });
        }
    }

    public void subscribe(IMediaController iMediaController, int i, final String str, final ParcelImpl parcelImpl) {
        if (iMediaController != null && parcelImpl != null) {
            dispatchLibrarySessionTask(iMediaController, i, SessionCommand.COMMAND_CODE_LIBRARY_SUBSCRIBE, new LibrarySessionCallbackTask<Integer>() {
                public Integer run(MediaSession.ControllerInfo controllerInfo) {
                    if (!TextUtils.isEmpty(str)) {
                        return Integer.valueOf(MediaSessionStub.this.getLibrarySession().onSubscribeOnExecutor(controllerInfo, str, (MediaLibraryService.LibraryParams) MediaParcelUtils.fromParcelable(parcelImpl)));
                    }
                    Log.w(MediaSessionStub.TAG, "subscribe(): Ignoring empty parentId from " + controllerInfo);
                    return -3;
                }
            });
        }
    }

    public void unsubscribe(IMediaController iMediaController, int i, final String str) {
        if (iMediaController != null) {
            dispatchLibrarySessionTask(iMediaController, i, SessionCommand.COMMAND_CODE_LIBRARY_UNSUBSCRIBE, new LibrarySessionCallbackTask<Integer>() {
                public Integer run(MediaSession.ControllerInfo controllerInfo) {
                    if (!TextUtils.isEmpty(str)) {
                        return Integer.valueOf(MediaSessionStub.this.getLibrarySession().onUnsubscribeOnExecutor(controllerInfo, str));
                    }
                    Log.w(MediaSessionStub.TAG, "unsubscribe(): Ignoring empty parentId from " + controllerInfo);
                    return -3;
                }
            });
        }
    }

    final class Controller2Cb extends MediaSession.ControllerCb {
        private final IMediaController mIControllerCallback;

        Controller2Cb(IMediaController iMediaController) {
            this.mIControllerCallback = iMediaController;
        }

        /* access modifiers changed from: package-private */
        public IBinder getCallbackBinder() {
            return this.mIControllerCallback.asBinder();
        }

        /* access modifiers changed from: package-private */
        public void onPlayerResult(int i, SessionPlayer.PlayerResult playerResult) throws RemoteException {
            onSessionResult(i, SessionResult.from(playerResult));
        }

        /* access modifiers changed from: package-private */
        public void onSessionResult(int i, SessionResult sessionResult) throws RemoteException {
            if (sessionResult == null) {
                sessionResult = new SessionResult(-1, (Bundle) null);
            }
            this.mIControllerCallback.onSessionResult(i, MediaParcelUtils.toParcelable(sessionResult));
        }

        /* access modifiers changed from: package-private */
        public void onLibraryResult(int i, LibraryResult libraryResult) throws RemoteException {
            if (libraryResult == null) {
                libraryResult = new LibraryResult(-1);
            }
            this.mIControllerCallback.onLibraryResult(i, MediaParcelUtils.toParcelable(libraryResult));
        }

        /* access modifiers changed from: package-private */
        public void setCustomLayout(int i, List<MediaSession.CommandButton> list) throws RemoteException {
            this.mIControllerCallback.onSetCustomLayout(i, MediaUtils.convertCommandButtonListToParcelImplList(list));
        }

        /* access modifiers changed from: package-private */
        public void onPlaybackInfoChanged(int i, MediaController.PlaybackInfo playbackInfo) throws RemoteException {
            this.mIControllerCallback.onPlaybackInfoChanged(i, MediaParcelUtils.toParcelable(playbackInfo));
        }

        /* access modifiers changed from: package-private */
        public void onAllowedCommandsChanged(int i, SessionCommandGroup sessionCommandGroup) throws RemoteException {
            this.mIControllerCallback.onAllowedCommandsChanged(i, MediaParcelUtils.toParcelable(sessionCommandGroup));
        }

        /* access modifiers changed from: package-private */
        public void sendCustomCommand(int i, SessionCommand sessionCommand, Bundle bundle) throws RemoteException {
            this.mIControllerCallback.onCustomCommand(i, MediaParcelUtils.toParcelable(sessionCommand), bundle);
        }

        /* access modifiers changed from: package-private */
        public void onPlayerStateChanged(int i, long j, long j2, int i2) throws RemoteException {
            this.mIControllerCallback.onPlayerStateChanged(i, j, j2, i2);
        }

        /* access modifiers changed from: package-private */
        public void onPlaybackSpeedChanged(int i, long j, long j2, float f) throws RemoteException {
            this.mIControllerCallback.onPlaybackSpeedChanged(i, j, j2, f);
        }

        /* access modifiers changed from: package-private */
        public void onBufferingStateChanged(int i, MediaItem mediaItem, int i2, long j, long j2, long j3) throws RemoteException {
            this.mIControllerCallback.onBufferingStateChanged(i, MediaParcelUtils.toParcelable(mediaItem), i2, j, j2, j3);
        }

        /* access modifiers changed from: package-private */
        public void onSeekCompleted(int i, long j, long j2, long j3) throws RemoteException {
            this.mIControllerCallback.onSeekCompleted(i, j, j2, j3);
        }

        /* access modifiers changed from: package-private */
        public void onCurrentMediaItemChanged(int i, MediaItem mediaItem, int i2, int i3, int i4) throws RemoteException {
            this.mIControllerCallback.onCurrentMediaItemChanged(i, MediaParcelUtils.toParcelable(mediaItem), i2, i3, i4);
        }

        /* access modifiers changed from: package-private */
        public void onPlaylistChanged(int i, List<MediaItem> list, MediaMetadata mediaMetadata, int i2, int i3, int i4) throws RemoteException {
            MediaSession.ControllerInfo controller = MediaSessionStub.this.mConnectedControllersManager.getController(getCallbackBinder());
            if (MediaSessionStub.this.mConnectedControllersManager.isAllowedCommand(controller, (int) SessionCommand.COMMAND_CODE_PLAYER_GET_PLAYLIST)) {
                this.mIControllerCallback.onPlaylistChanged(i, MediaUtils.convertMediaItemListToParcelImplListSlice(list), MediaParcelUtils.toParcelable(mediaMetadata), i2, i3, i4);
            } else if (MediaSessionStub.this.mConnectedControllersManager.isAllowedCommand(controller, (int) SessionCommand.COMMAND_CODE_PLAYER_GET_PLAYLIST_METADATA)) {
                this.mIControllerCallback.onPlaylistMetadataChanged(i, MediaParcelUtils.toParcelable(mediaMetadata));
            }
        }

        /* access modifiers changed from: package-private */
        public void onPlaylistMetadataChanged(int i, MediaMetadata mediaMetadata) throws RemoteException {
            if (MediaSessionStub.this.mConnectedControllersManager.isAllowedCommand(MediaSessionStub.this.mConnectedControllersManager.getController(getCallbackBinder()), (int) SessionCommand.COMMAND_CODE_PLAYER_GET_PLAYLIST_METADATA)) {
                this.mIControllerCallback.onPlaylistMetadataChanged(i, MediaParcelUtils.toParcelable(mediaMetadata));
            }
        }

        /* access modifiers changed from: package-private */
        public void onShuffleModeChanged(int i, int i2, int i3, int i4, int i5) throws RemoteException {
            this.mIControllerCallback.onShuffleModeChanged(i, i2, i3, i4, i5);
        }

        /* access modifiers changed from: package-private */
        public void onRepeatModeChanged(int i, int i2, int i3, int i4, int i5) throws RemoteException {
            this.mIControllerCallback.onRepeatModeChanged(i, i2, i3, i4, i5);
        }

        /* access modifiers changed from: package-private */
        public void onPlaybackCompleted(int i) throws RemoteException {
            this.mIControllerCallback.onPlaybackCompleted(i);
        }

        /* access modifiers changed from: package-private */
        public void onChildrenChanged(int i, String str, int i2, MediaLibraryService.LibraryParams libraryParams) throws RemoteException {
            this.mIControllerCallback.onChildrenChanged(i, str, i2, MediaParcelUtils.toParcelable(libraryParams));
        }

        /* access modifiers changed from: package-private */
        public void onSearchResultChanged(int i, String str, int i2, MediaLibraryService.LibraryParams libraryParams) throws RemoteException {
            this.mIControllerCallback.onSearchResultChanged(i, str, i2, MediaParcelUtils.toParcelable(libraryParams));
        }

        /* access modifiers changed from: package-private */
        public void onDisconnected(int i) throws RemoteException {
            this.mIControllerCallback.onDisconnected(i);
        }

        /* access modifiers changed from: package-private */
        public void onVideoSizeChanged(int i, MediaItem mediaItem, VideoSize videoSize) throws RemoteException {
            this.mIControllerCallback.onVideoSizeChanged(i, MediaParcelUtils.toParcelable(mediaItem), MediaParcelUtils.toParcelable(videoSize));
        }

        /* access modifiers changed from: package-private */
        public void onTrackInfoChanged(int i, List<SessionPlayer.TrackInfo> list, SessionPlayer.TrackInfo trackInfo, SessionPlayer.TrackInfo trackInfo2, SessionPlayer.TrackInfo trackInfo3, SessionPlayer.TrackInfo trackInfo4) throws RemoteException {
            int i2 = i;
            this.mIControllerCallback.onTrackInfoChanged(i2, MediaParcelUtils.toParcelableList(list), MediaParcelUtils.toParcelable(trackInfo), MediaParcelUtils.toParcelable(trackInfo2), MediaParcelUtils.toParcelable(trackInfo3), MediaParcelUtils.toParcelable(trackInfo4));
        }

        /* access modifiers changed from: package-private */
        public void onTrackSelected(int i, SessionPlayer.TrackInfo trackInfo) throws RemoteException {
            this.mIControllerCallback.onTrackSelected(i, MediaParcelUtils.toParcelable(trackInfo));
        }

        /* access modifiers changed from: package-private */
        public void onTrackDeselected(int i, SessionPlayer.TrackInfo trackInfo) throws RemoteException {
            this.mIControllerCallback.onTrackDeselected(i, MediaParcelUtils.toParcelable(trackInfo));
        }

        /* access modifiers changed from: package-private */
        public void onSubtitleData(int i, MediaItem mediaItem, SessionPlayer.TrackInfo trackInfo, SubtitleData subtitleData) throws RemoteException {
            this.mIControllerCallback.onSubtitleData(i, MediaParcelUtils.toParcelable(mediaItem), MediaParcelUtils.toParcelable(trackInfo), MediaParcelUtils.toParcelable(subtitleData));
        }

        public int hashCode() {
            return ObjectsCompat.hash(getCallbackBinder());
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || obj.getClass() != Controller2Cb.class) {
                return false;
            }
            return ObjectsCompat.equals(getCallbackBinder(), ((Controller2Cb) obj).getCallbackBinder());
        }
    }
}

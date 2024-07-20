package androidx.media2;

import android.app.PendingIntent;
import android.content.Context;
import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.ParcelUuid;
import android.os.RemoteException;
import android.os.ResultReceiver;
import android.os.SystemClock;
import android.support.p064v4.media.session.MediaSessionCompat;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseArray;
import androidx.media.MediaSessionManager;
import androidx.media2.IMediaSession2;
import androidx.media2.MediaController2;
import androidx.media2.MediaLibraryService2;
import androidx.media2.MediaSession2;
import androidx.media2.SessionCommandGroup2;
import androidx.versionedparcelable.ParcelImpl;
import androidx.versionedparcelable.ParcelUtils;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

class MediaSession2Stub extends IMediaSession2.Stub {
    private static final boolean DEBUG = true;
    private static final String TAG = "MediaSession2Stub";
    static final SparseArray<SessionCommand2> sCommandsForOnCommandRequest = new SparseArray<>();
    final ConnectedControllersManager<IBinder> mConnectedControllersManager;
    final Set<IBinder> mConnectingControllers = new HashSet();
    final Context mContext;
    final Object mLock = new Object();
    final MediaSession2.MediaSession2Impl mSessionImpl;
    final MediaSessionManager mSessionManager;

    @FunctionalInterface
    private interface SessionRunnable {
        void run(MediaSession2.ControllerInfo controllerInfo) throws RemoteException;
    }

    static {
        for (SessionCommand2 next : new SessionCommandGroup2.Builder().addAllPlaybackCommands().addAllPlaylistCommands().addAllVolumeCommands().build().getCommands()) {
            sCommandsForOnCommandRequest.append(next.getCommandCode(), next);
        }
    }

    MediaSession2Stub(MediaSession2.MediaSession2Impl mediaSession2Impl) {
        this.mSessionImpl = mediaSession2Impl;
        Context context = mediaSession2Impl.getContext();
        this.mContext = context;
        this.mSessionManager = MediaSessionManager.getSessionManager(context);
        this.mConnectedControllersManager = new ConnectedControllersManager<>(mediaSession2Impl);
    }

    /* access modifiers changed from: package-private */
    public ConnectedControllersManager<IBinder> getConnectedControllersManager() {
        return this.mConnectedControllersManager;
    }

    private void onSessionCommand(IMediaController2 iMediaController2, int i, SessionRunnable sessionRunnable) {
        onSessionCommandInternal(iMediaController2, (SessionCommand2) null, i, sessionRunnable);
    }

    private void onSessionCommand(IMediaController2 iMediaController2, SessionCommand2 sessionCommand2, SessionRunnable sessionRunnable) {
        onSessionCommandInternal(iMediaController2, sessionCommand2, 0, sessionRunnable);
    }

    private void onSessionCommandInternal(IMediaController2 iMediaController2, SessionCommand2 sessionCommand2, int i, SessionRunnable sessionRunnable) {
        IBinder iBinder;
        ConnectedControllersManager<IBinder> connectedControllersManager = this.mConnectedControllersManager;
        if (iMediaController2 == null) {
            iBinder = null;
        } else {
            iBinder = iMediaController2.asBinder();
        }
        final MediaSession2.ControllerInfo controller = connectedControllersManager.getController(iBinder);
        if (!this.mSessionImpl.isClosed() && controller != null) {
            final SessionCommand2 sessionCommand22 = sessionCommand2;
            final int i2 = i;
            final SessionRunnable sessionRunnable2 = sessionRunnable;
            this.mSessionImpl.getCallbackExecutor().execute(new Runnable() {
                public void run() {
                    SessionCommand2 sessionCommand2;
                    if (MediaSession2Stub.this.mConnectedControllersManager.isConnected(controller)) {
                        if (sessionCommand22 != null) {
                            if (MediaSession2Stub.this.mConnectedControllersManager.isAllowedCommand(controller, sessionCommand22)) {
                                sessionCommand2 = MediaSession2Stub.sCommandsForOnCommandRequest.get(sessionCommand22.getCommandCode());
                            } else {
                                return;
                            }
                        } else if (MediaSession2Stub.this.mConnectedControllersManager.isAllowedCommand(controller, i2)) {
                            sessionCommand2 = MediaSession2Stub.sCommandsForOnCommandRequest.get(i2);
                        } else {
                            return;
                        }
                        if (sessionCommand2 == null || MediaSession2Stub.this.mSessionImpl.getCallback().onCommandRequest(MediaSession2Stub.this.mSessionImpl.getInstance(), controller, sessionCommand2)) {
                            try {
                                sessionRunnable2.run(controller);
                            } catch (RemoteException e) {
                                Log.w(MediaSession2Stub.TAG, "Exception in " + controller.toString(), e);
                            }
                        } else {
                            Log.d(MediaSession2Stub.TAG, "Command (" + sessionCommand2 + ") from " + controller + " was rejected by " + MediaSession2Stub.this.mSessionImpl);
                        }
                    }
                }
            });
        }
    }

    private void onBrowserCommand(IMediaController2 iMediaController2, int i, SessionRunnable sessionRunnable) {
        if (this.mSessionImpl instanceof MediaLibraryService2.MediaLibrarySession.MediaLibrarySessionImpl) {
            onSessionCommandInternal(iMediaController2, (SessionCommand2) null, i, sessionRunnable);
            return;
        }
        throw new RuntimeException("MediaSession2 cannot handle MediaLibrarySession command");
    }

    public void connect(final IMediaController2 iMediaController2, String str) throws RuntimeException {
        MediaSessionManager.RemoteUserInfo remoteUserInfo = new MediaSessionManager.RemoteUserInfo(str, Binder.getCallingPid(), Binder.getCallingUid());
        final MediaSession2.ControllerInfo controllerInfo = new MediaSession2.ControllerInfo(remoteUserInfo, this.mSessionManager.isTrustedForMediaControl(remoteUserInfo), new Controller2Cb(iMediaController2));
        this.mSessionImpl.getCallbackExecutor().execute(new Runnable() {
            public void run() {
                if (!MediaSession2Stub.this.mSessionImpl.isClosed()) {
                    IBinder callbackBinder = ((Controller2Cb) controllerInfo.getControllerCb()).getCallbackBinder();
                    synchronized (MediaSession2Stub.this.mLock) {
                        MediaSession2Stub.this.mConnectingControllers.add(callbackBinder);
                    }
                    SessionCommandGroup2 onConnect = MediaSession2Stub.this.mSessionImpl.getCallback().onConnect(MediaSession2Stub.this.mSessionImpl.getInstance(), controllerInfo);
                    if (onConnect != null || controllerInfo.isTrusted()) {
                        Log.d(MediaSession2Stub.TAG, "Accepting connection, controllerInfo=" + controllerInfo + " allowedCommands=" + onConnect);
                        if (onConnect == null) {
                            onConnect = new SessionCommandGroup2();
                        }
                        synchronized (MediaSession2Stub.this.mLock) {
                            MediaSession2Stub.this.mConnectingControllers.remove(callbackBinder);
                            MediaSession2Stub.this.mConnectedControllersManager.addController(callbackBinder, controllerInfo, onConnect);
                        }
                        int playerState = MediaSession2Stub.this.mSessionImpl.getPlayerState();
                        ParcelImpl parcelImpl = (ParcelImpl) ParcelUtils.toParcelable(MediaSession2Stub.this.mSessionImpl.getCurrentMediaItem());
                        long elapsedRealtime = SystemClock.elapsedRealtime();
                        long currentPosition = MediaSession2Stub.this.mSessionImpl.getCurrentPosition();
                        float playbackSpeed = MediaSession2Stub.this.mSessionImpl.getPlaybackSpeed();
                        long bufferedPosition = MediaSession2Stub.this.mSessionImpl.getBufferedPosition();
                        ParcelImpl parcelImpl2 = (ParcelImpl) ParcelUtils.toParcelable(MediaSession2Stub.this.mSessionImpl.getPlaybackInfo());
                        int repeatMode = MediaSession2Stub.this.mSessionImpl.getRepeatMode();
                        int shuffleMode = MediaSession2Stub.this.mSessionImpl.getShuffleMode();
                        PendingIntent sessionActivity = MediaSession2Stub.this.mSessionImpl.getSessionActivity();
                        List<ParcelImpl> convertMediaItem2ListToParcelImplList = MediaUtils2.convertMediaItem2ListToParcelImplList(onConnect.hasCommand(18) ? MediaSession2Stub.this.mSessionImpl.getPlaylist() : null);
                        if (!MediaSession2Stub.this.mSessionImpl.isClosed()) {
                            try {
                                iMediaController2.onConnected(MediaSession2Stub.this, (ParcelImpl) ParcelUtils.toParcelable(onConnect), playerState, parcelImpl, elapsedRealtime, currentPosition, playbackSpeed, bufferedPosition, parcelImpl2, repeatMode, shuffleMode, convertMediaItem2ListToParcelImplList, sessionActivity);
                            } catch (RemoteException unused) {
                            }
                        }
                    } else {
                        synchronized (MediaSession2Stub.this.mLock) {
                            MediaSession2Stub.this.mConnectingControllers.remove(callbackBinder);
                        }
                        Log.d(MediaSession2Stub.TAG, "Rejecting connection, controllerInfo=" + controllerInfo);
                        iMediaController2.onDisconnected();
                    }
                }
            }
        });
    }

    public void release(IMediaController2 iMediaController2) throws RemoteException {
        this.mConnectedControllersManager.removeController(iMediaController2 == null ? null : iMediaController2.asBinder());
    }

    public void setVolumeTo(IMediaController2 iMediaController2, final int i, final int i2) throws RuntimeException {
        onSessionCommand(iMediaController2, 10, (SessionRunnable) new SessionRunnable() {
            public void run(MediaSession2.ControllerInfo controllerInfo) throws RemoteException {
                MediaSessionCompat sessionCompat = MediaSession2Stub.this.mSessionImpl.getSessionCompat();
                if (sessionCompat != null) {
                    sessionCompat.getController().setVolumeTo(i, i2);
                }
            }
        });
    }

    public void adjustVolume(IMediaController2 iMediaController2, final int i, final int i2) throws RuntimeException {
        onSessionCommand(iMediaController2, 11, (SessionRunnable) new SessionRunnable() {
            public void run(MediaSession2.ControllerInfo controllerInfo) throws RemoteException {
                MediaSessionCompat sessionCompat = MediaSession2Stub.this.mSessionImpl.getSessionCompat();
                if (sessionCompat != null) {
                    sessionCompat.getController().adjustVolume(i, i2);
                }
            }
        });
    }

    public void play(IMediaController2 iMediaController2) throws RuntimeException {
        onSessionCommand(iMediaController2, 1, (SessionRunnable) new SessionRunnable() {
            public void run(MediaSession2.ControllerInfo controllerInfo) throws RemoteException {
                MediaSession2Stub.this.mSessionImpl.play();
            }
        });
    }

    public void pause(IMediaController2 iMediaController2) throws RuntimeException {
        onSessionCommand(iMediaController2, 2, (SessionRunnable) new SessionRunnable() {
            public void run(MediaSession2.ControllerInfo controllerInfo) throws RemoteException {
                MediaSession2Stub.this.mSessionImpl.pause();
            }
        });
    }

    public void reset(IMediaController2 iMediaController2) throws RuntimeException {
        onSessionCommand(iMediaController2, 3, (SessionRunnable) new SessionRunnable() {
            public void run(MediaSession2.ControllerInfo controllerInfo) throws RemoteException {
                MediaSession2Stub.this.mSessionImpl.reset();
            }
        });
    }

    public void prepare(IMediaController2 iMediaController2) throws RuntimeException {
        onSessionCommand(iMediaController2, 6, (SessionRunnable) new SessionRunnable() {
            public void run(MediaSession2.ControllerInfo controllerInfo) throws RemoteException {
                MediaSession2Stub.this.mSessionImpl.prepare();
            }
        });
    }

    public void fastForward(IMediaController2 iMediaController2) {
        onSessionCommand(iMediaController2, 7, (SessionRunnable) new SessionRunnable() {
            public void run(MediaSession2.ControllerInfo controllerInfo) throws RemoteException {
                MediaSession2Stub.this.mSessionImpl.getCallback().onFastForward(MediaSession2Stub.this.mSessionImpl.getInstance(), controllerInfo);
            }
        });
    }

    public void rewind(IMediaController2 iMediaController2) {
        onSessionCommand(iMediaController2, 8, (SessionRunnable) new SessionRunnable() {
            public void run(MediaSession2.ControllerInfo controllerInfo) throws RemoteException {
                MediaSession2Stub.this.mSessionImpl.getCallback().onRewind(MediaSession2Stub.this.mSessionImpl.getInstance(), controllerInfo);
            }
        });
    }

    public void seekTo(IMediaController2 iMediaController2, final long j) throws RuntimeException {
        onSessionCommand(iMediaController2, 9, (SessionRunnable) new SessionRunnable() {
            public void run(MediaSession2.ControllerInfo controllerInfo) throws RemoteException {
                MediaSession2Stub.this.mSessionImpl.seekTo(j);
            }
        });
    }

    public void sendCustomCommand(IMediaController2 iMediaController2, ParcelImpl parcelImpl, final Bundle bundle, final ResultReceiver resultReceiver) {
        final SessionCommand2 sessionCommand2 = (SessionCommand2) ParcelUtils.fromParcelable(parcelImpl);
        onSessionCommand(iMediaController2, sessionCommand2, (SessionRunnable) new SessionRunnable() {
            public void run(MediaSession2.ControllerInfo controllerInfo) throws RemoteException {
                MediaSession2Stub.this.mSessionImpl.getCallback().onCustomCommand(MediaSession2Stub.this.mSessionImpl.getInstance(), controllerInfo, sessionCommand2, bundle, resultReceiver);
            }
        });
    }

    public void prepareFromUri(IMediaController2 iMediaController2, final Uri uri, final Bundle bundle) {
        onSessionCommand(iMediaController2, 26, (SessionRunnable) new SessionRunnable() {
            public void run(MediaSession2.ControllerInfo controllerInfo) throws RemoteException {
                if (uri == null) {
                    Log.w(MediaSession2Stub.TAG, "prepareFromUri(): Ignoring null uri from " + controllerInfo);
                    return;
                }
                MediaSession2Stub.this.mSessionImpl.getCallback().onPrepareFromUri(MediaSession2Stub.this.mSessionImpl.getInstance(), controllerInfo, uri, bundle);
            }
        });
    }

    public void prepareFromSearch(IMediaController2 iMediaController2, final String str, final Bundle bundle) {
        onSessionCommand(iMediaController2, 27, (SessionRunnable) new SessionRunnable() {
            public void run(MediaSession2.ControllerInfo controllerInfo) throws RemoteException {
                if (TextUtils.isEmpty(str)) {
                    Log.w(MediaSession2Stub.TAG, "prepareFromSearch(): Ignoring empty query from " + controllerInfo);
                    return;
                }
                MediaSession2Stub.this.mSessionImpl.getCallback().onPrepareFromSearch(MediaSession2Stub.this.mSessionImpl.getInstance(), controllerInfo, str, bundle);
            }
        });
    }

    public void prepareFromMediaId(IMediaController2 iMediaController2, final String str, final Bundle bundle) {
        onSessionCommand(iMediaController2, 25, (SessionRunnable) new SessionRunnable() {
            public void run(MediaSession2.ControllerInfo controllerInfo) throws RemoteException {
                if (str == null) {
                    Log.w(MediaSession2Stub.TAG, "prepareFromMediaId(): Ignoring null mediaId from " + controllerInfo);
                    return;
                }
                MediaSession2Stub.this.mSessionImpl.getCallback().onPrepareFromMediaId(MediaSession2Stub.this.mSessionImpl.getInstance(), controllerInfo, str, bundle);
            }
        });
    }

    public void playFromUri(IMediaController2 iMediaController2, final Uri uri, final Bundle bundle) {
        onSessionCommand(iMediaController2, 23, (SessionRunnable) new SessionRunnable() {
            public void run(MediaSession2.ControllerInfo controllerInfo) throws RemoteException {
                if (uri == null) {
                    Log.w(MediaSession2Stub.TAG, "playFromUri(): Ignoring null uri from " + controllerInfo);
                    return;
                }
                MediaSession2Stub.this.mSessionImpl.getCallback().onPlayFromUri(MediaSession2Stub.this.mSessionImpl.getInstance(), controllerInfo, uri, bundle);
            }
        });
    }

    public void playFromSearch(IMediaController2 iMediaController2, final String str, final Bundle bundle) {
        onSessionCommand(iMediaController2, 24, (SessionRunnable) new SessionRunnable() {
            public void run(MediaSession2.ControllerInfo controllerInfo) throws RemoteException {
                if (TextUtils.isEmpty(str)) {
                    Log.w(MediaSession2Stub.TAG, "playFromSearch(): Ignoring empty query from " + controllerInfo);
                    return;
                }
                MediaSession2Stub.this.mSessionImpl.getCallback().onPlayFromSearch(MediaSession2Stub.this.mSessionImpl.getInstance(), controllerInfo, str, bundle);
            }
        });
    }

    public void playFromMediaId(IMediaController2 iMediaController2, final String str, final Bundle bundle) {
        onSessionCommand(iMediaController2, 22, (SessionRunnable) new SessionRunnable() {
            public void run(MediaSession2.ControllerInfo controllerInfo) throws RemoteException {
                if (str == null) {
                    Log.w(MediaSession2Stub.TAG, "playFromMediaId(): Ignoring null mediaId from " + controllerInfo);
                    return;
                }
                MediaSession2Stub.this.mSessionImpl.getCallback().onPlayFromMediaId(MediaSession2Stub.this.mSessionImpl.getInstance(), controllerInfo, str, bundle);
            }
        });
    }

    public void setRating(IMediaController2 iMediaController2, final String str, ParcelImpl parcelImpl) {
        final Rating2 rating2 = (Rating2) ParcelUtils.fromParcelable(parcelImpl);
        onSessionCommand(iMediaController2, 28, (SessionRunnable) new SessionRunnable() {
            public void run(MediaSession2.ControllerInfo controllerInfo) throws RemoteException {
                if (str == null) {
                    Log.w(MediaSession2Stub.TAG, "setRating(): Ignoring null mediaId from " + controllerInfo);
                } else if (rating2 == null) {
                    Log.w(MediaSession2Stub.TAG, "setRating(): Ignoring null ratingBundle from " + controllerInfo);
                } else {
                    MediaSession2Stub.this.mSessionImpl.getCallback().onSetRating(MediaSession2Stub.this.mSessionImpl.getInstance(), controllerInfo, str, rating2);
                }
            }
        });
    }

    public void setPlaybackSpeed(IMediaController2 iMediaController2, final float f) {
        onSessionCommand(iMediaController2, 39, (SessionRunnable) new SessionRunnable() {
            public void run(MediaSession2.ControllerInfo controllerInfo) throws RemoteException {
                MediaSession2Stub.this.mSessionImpl.getInstance().setPlaybackSpeed(f);
            }
        });
    }

    public void setPlaylist(IMediaController2 iMediaController2, final List<ParcelImpl> list, final Bundle bundle) {
        onSessionCommand(iMediaController2, 19, (SessionRunnable) new SessionRunnable() {
            public void run(MediaSession2.ControllerInfo controllerInfo) throws RemoteException {
                if (list == null) {
                    Log.w(MediaSession2Stub.TAG, "setPlaylist(): Ignoring null playlist from " + controllerInfo);
                    return;
                }
                MediaSession2Stub.this.mSessionImpl.getInstance().setPlaylist(MediaUtils2.convertParcelImplListToMediaItem2List(list), MediaMetadata2.fromBundle(bundle));
            }
        });
    }

    public void updatePlaylistMetadata(IMediaController2 iMediaController2, final Bundle bundle) {
        onSessionCommand(iMediaController2, 21, (SessionRunnable) new SessionRunnable() {
            public void run(MediaSession2.ControllerInfo controllerInfo) throws RemoteException {
                MediaSession2Stub.this.mSessionImpl.getInstance().updatePlaylistMetadata(MediaMetadata2.fromBundle(bundle));
            }
        });
    }

    public void addPlaylistItem(IMediaController2 iMediaController2, final int i, final ParcelImpl parcelImpl) {
        onSessionCommand(iMediaController2, 15, (SessionRunnable) new SessionRunnable() {
            public void run(MediaSession2.ControllerInfo controllerInfo) throws RemoteException {
                MediaItem2 mediaItem2 = (MediaItem2) ParcelUtils.fromParcelable(parcelImpl);
                mediaItem2.mParcelUuid = new ParcelUuid(UUID.randomUUID());
                MediaSession2Stub.this.mSessionImpl.getInstance().addPlaylistItem(i, mediaItem2);
            }
        });
    }

    public void removePlaylistItem(IMediaController2 iMediaController2, final ParcelImpl parcelImpl) {
        onSessionCommand(iMediaController2, 16, (SessionRunnable) new SessionRunnable() {
            public void run(MediaSession2.ControllerInfo controllerInfo) throws RemoteException {
                MediaSession2Stub.this.mSessionImpl.getInstance().removePlaylistItem((MediaItem2) ParcelUtils.fromParcelable(parcelImpl));
            }
        });
    }

    public void replacePlaylistItem(IMediaController2 iMediaController2, final int i, final ParcelImpl parcelImpl) {
        onSessionCommand(iMediaController2, 17, (SessionRunnable) new SessionRunnable() {
            public void run(MediaSession2.ControllerInfo controllerInfo) throws RemoteException {
                MediaItem2 mediaItem2 = (MediaItem2) ParcelUtils.fromParcelable(parcelImpl);
                mediaItem2.mParcelUuid = new ParcelUuid(UUID.randomUUID());
                MediaSession2Stub.this.mSessionImpl.getInstance().replacePlaylistItem(i, mediaItem2);
            }
        });
    }

    public void skipToPlaylistItem(IMediaController2 iMediaController2, final ParcelImpl parcelImpl) {
        onSessionCommand(iMediaController2, 12, (SessionRunnable) new SessionRunnable() {
            public void run(MediaSession2.ControllerInfo controllerInfo) throws RemoteException {
                if (parcelImpl == null) {
                    Log.w(MediaSession2Stub.TAG, "skipToPlaylistItem(): Ignoring null mediaItem from " + controllerInfo);
                }
                MediaSession2Stub.this.mSessionImpl.getInstance().skipToPlaylistItem((MediaItem2) ParcelUtils.fromParcelable(parcelImpl));
            }
        });
    }

    public void skipToPreviousItem(IMediaController2 iMediaController2) {
        onSessionCommand(iMediaController2, 5, (SessionRunnable) new SessionRunnable() {
            public void run(MediaSession2.ControllerInfo controllerInfo) throws RemoteException {
                MediaSession2Stub.this.mSessionImpl.getInstance().skipToPreviousItem();
            }
        });
    }

    public void skipToNextItem(IMediaController2 iMediaController2) {
        onSessionCommand(iMediaController2, 4, (SessionRunnable) new SessionRunnable() {
            public void run(MediaSession2.ControllerInfo controllerInfo) throws RemoteException {
                MediaSession2Stub.this.mSessionImpl.getInstance().skipToNextItem();
            }
        });
    }

    public void setRepeatMode(IMediaController2 iMediaController2, final int i) {
        onSessionCommand(iMediaController2, 14, (SessionRunnable) new SessionRunnable() {
            public void run(MediaSession2.ControllerInfo controllerInfo) throws RemoteException {
                MediaSession2Stub.this.mSessionImpl.getInstance().setRepeatMode(i);
            }
        });
    }

    public void setShuffleMode(IMediaController2 iMediaController2, final int i) {
        onSessionCommand(iMediaController2, 13, (SessionRunnable) new SessionRunnable() {
            public void run(MediaSession2.ControllerInfo controllerInfo) throws RemoteException {
                MediaSession2Stub.this.mSessionImpl.getInstance().setShuffleMode(i);
            }
        });
    }

    public void subscribeRoutesInfo(IMediaController2 iMediaController2) {
        onSessionCommand(iMediaController2, 36, (SessionRunnable) new SessionRunnable() {
            public void run(MediaSession2.ControllerInfo controllerInfo) throws RemoteException {
                MediaSession2Stub.this.mSessionImpl.getCallback().onSubscribeRoutesInfo(MediaSession2Stub.this.mSessionImpl.getInstance(), controllerInfo);
            }
        });
    }

    public void unsubscribeRoutesInfo(IMediaController2 iMediaController2) {
        onSessionCommand(iMediaController2, 37, (SessionRunnable) new SessionRunnable() {
            public void run(MediaSession2.ControllerInfo controllerInfo) throws RemoteException {
                MediaSession2Stub.this.mSessionImpl.getCallback().onUnsubscribeRoutesInfo(MediaSession2Stub.this.mSessionImpl.getInstance(), controllerInfo);
            }
        });
    }

    public void selectRoute(IMediaController2 iMediaController2, final Bundle bundle) {
        if (!MediaUtils2.isUnparcelableBundle(bundle)) {
            onSessionCommand(iMediaController2, 37, (SessionRunnable) new SessionRunnable() {
                public void run(MediaSession2.ControllerInfo controllerInfo) throws RemoteException {
                    MediaSession2Stub.this.mSessionImpl.getCallback().onSelectRoute(MediaSession2Stub.this.mSessionImpl.getInstance(), controllerInfo, bundle);
                }
            });
            return;
        }
        throw new RuntimeException("Unexpected route bundle: " + bundle);
    }

    /* access modifiers changed from: package-private */
    public MediaLibraryService2.MediaLibrarySession.MediaLibrarySessionImpl getLibrarySession() {
        MediaSession2.MediaSession2Impl mediaSession2Impl = this.mSessionImpl;
        if (mediaSession2Impl instanceof MediaLibraryService2.MediaLibrarySession.MediaLibrarySessionImpl) {
            return (MediaLibraryService2.MediaLibrarySession.MediaLibrarySessionImpl) mediaSession2Impl;
        }
        throw new RuntimeException("Session cannot be casted to library session");
    }

    public void getLibraryRoot(IMediaController2 iMediaController2, final Bundle bundle) throws RuntimeException {
        onBrowserCommand(iMediaController2, 31, new SessionRunnable() {
            public void run(MediaSession2.ControllerInfo controllerInfo) throws RemoteException {
                MediaSession2Stub.this.getLibrarySession().onGetLibraryRootOnExecutor(controllerInfo, bundle);
            }
        });
    }

    public void getItem(IMediaController2 iMediaController2, final String str) throws RuntimeException {
        onBrowserCommand(iMediaController2, 30, new SessionRunnable() {
            public void run(MediaSession2.ControllerInfo controllerInfo) throws RemoteException {
                if (str == null) {
                    Log.w(MediaSession2Stub.TAG, "getItem(): Ignoring null mediaId from " + controllerInfo);
                    return;
                }
                MediaSession2Stub.this.getLibrarySession().onGetItemOnExecutor(controllerInfo, str);
            }
        });
    }

    public void getChildren(IMediaController2 iMediaController2, String str, int i, int i2, Bundle bundle) throws RuntimeException {
        final String str2 = str;
        final int i3 = i;
        final int i4 = i2;
        final Bundle bundle2 = bundle;
        onBrowserCommand(iMediaController2, 29, new SessionRunnable() {
            public void run(MediaSession2.ControllerInfo controllerInfo) throws RemoteException {
                if (str2 == null) {
                    Log.w(MediaSession2Stub.TAG, "getChildren(): Ignoring null parentId from " + controllerInfo);
                } else if (i3 < 0) {
                    Log.w(MediaSession2Stub.TAG, "getChildren(): Ignoring negative page from " + controllerInfo);
                } else if (i4 < 1) {
                    Log.w(MediaSession2Stub.TAG, "getChildren(): Ignoring pageSize less than 1 from " + controllerInfo);
                } else {
                    MediaSession2Stub.this.getLibrarySession().onGetChildrenOnExecutor(controllerInfo, str2, i3, i4, bundle2);
                }
            }
        });
    }

    public void search(IMediaController2 iMediaController2, final String str, final Bundle bundle) {
        onBrowserCommand(iMediaController2, 33, new SessionRunnable() {
            public void run(MediaSession2.ControllerInfo controllerInfo) throws RemoteException {
                if (TextUtils.isEmpty(str)) {
                    Log.w(MediaSession2Stub.TAG, "search(): Ignoring empty query from " + controllerInfo);
                    return;
                }
                MediaSession2Stub.this.getLibrarySession().onSearchOnExecutor(controllerInfo, str, bundle);
            }
        });
    }

    public void getSearchResult(IMediaController2 iMediaController2, String str, int i, int i2, Bundle bundle) {
        final String str2 = str;
        final int i3 = i;
        final int i4 = i2;
        final Bundle bundle2 = bundle;
        onBrowserCommand(iMediaController2, 32, new SessionRunnable() {
            public void run(MediaSession2.ControllerInfo controllerInfo) throws RemoteException {
                if (TextUtils.isEmpty(str2)) {
                    Log.w(MediaSession2Stub.TAG, "getSearchResult(): Ignoring empty query from " + controllerInfo);
                } else if (i3 < 0) {
                    Log.w(MediaSession2Stub.TAG, "getSearchResult(): Ignoring negative page from " + controllerInfo);
                } else if (i4 < 1) {
                    Log.w(MediaSession2Stub.TAG, "getSearchResult(): Ignoring pageSize less than 1 from " + controllerInfo);
                } else {
                    MediaSession2Stub.this.getLibrarySession().onGetSearchResultOnExecutor(controllerInfo, str2, i3, i4, bundle2);
                }
            }
        });
    }

    public void subscribe(IMediaController2 iMediaController2, final String str, final Bundle bundle) {
        onBrowserCommand(iMediaController2, 34, new SessionRunnable() {
            public void run(MediaSession2.ControllerInfo controllerInfo) throws RemoteException {
                if (str == null) {
                    Log.w(MediaSession2Stub.TAG, "subscribe(): Ignoring null parentId from " + controllerInfo);
                    return;
                }
                MediaSession2Stub.this.getLibrarySession().onSubscribeOnExecutor(controllerInfo, str, bundle);
            }
        });
    }

    public void unsubscribe(IMediaController2 iMediaController2, final String str) {
        onBrowserCommand(iMediaController2, 35, new SessionRunnable() {
            public void run(MediaSession2.ControllerInfo controllerInfo) throws RemoteException {
                if (str == null) {
                    Log.w(MediaSession2Stub.TAG, "unsubscribe(): Ignoring null parentId from " + controllerInfo);
                    return;
                }
                MediaSession2Stub.this.getLibrarySession().onUnsubscribeOnExecutor(controllerInfo, str);
            }
        });
    }

    final class Controller2Cb extends MediaSession2.ControllerCb {
        private final IMediaController2 mIControllerCallback;

        Controller2Cb(IMediaController2 iMediaController2) {
            this.mIControllerCallback = iMediaController2;
        }

        /* access modifiers changed from: package-private */
        public IBinder getCallbackBinder() {
            return this.mIControllerCallback.asBinder();
        }

        /* access modifiers changed from: package-private */
        public void onCustomLayoutChanged(List<MediaSession2.CommandButton> list) throws RemoteException {
            this.mIControllerCallback.onCustomLayoutChanged(MediaUtils2.convertCommandButtonListToParcelImplList(list));
        }

        /* access modifiers changed from: package-private */
        public void onPlaybackInfoChanged(MediaController2.PlaybackInfo playbackInfo) throws RemoteException {
            this.mIControllerCallback.onPlaybackInfoChanged((ParcelImpl) ParcelUtils.toParcelable(playbackInfo));
        }

        /* access modifiers changed from: package-private */
        public void onAllowedCommandsChanged(SessionCommandGroup2 sessionCommandGroup2) throws RemoteException {
            this.mIControllerCallback.onAllowedCommandsChanged((ParcelImpl) ParcelUtils.toParcelable(sessionCommandGroup2));
        }

        /* access modifiers changed from: package-private */
        public void onCustomCommand(SessionCommand2 sessionCommand2, Bundle bundle, ResultReceiver resultReceiver) throws RemoteException {
            this.mIControllerCallback.onCustomCommand((ParcelImpl) ParcelUtils.toParcelable(sessionCommand2), bundle, resultReceiver);
        }

        /* access modifiers changed from: package-private */
        public void onPlayerStateChanged(long j, long j2, int i) throws RemoteException {
            this.mIControllerCallback.onPlayerStateChanged(j, j2, i);
        }

        /* access modifiers changed from: package-private */
        public void onPlaybackSpeedChanged(long j, long j2, float f) throws RemoteException {
            this.mIControllerCallback.onPlaybackSpeedChanged(j, j2, f);
        }

        /* access modifiers changed from: package-private */
        public void onBufferingStateChanged(MediaItem2 mediaItem2, int i, long j) throws RemoteException {
            this.mIControllerCallback.onBufferingStateChanged((ParcelImpl) ParcelUtils.toParcelable(mediaItem2), i, j);
        }

        /* access modifiers changed from: package-private */
        public void onSeekCompleted(long j, long j2, long j3) throws RemoteException {
            this.mIControllerCallback.onSeekCompleted(j, j2, j3);
        }

        /* access modifiers changed from: package-private */
        public void onError(int i, Bundle bundle) throws RemoteException {
            this.mIControllerCallback.onError(i, bundle);
        }

        /* access modifiers changed from: package-private */
        public void onCurrentMediaItemChanged(MediaItem2 mediaItem2) throws RemoteException {
            this.mIControllerCallback.onCurrentMediaItemChanged((ParcelImpl) ParcelUtils.toParcelable(mediaItem2));
        }

        /* access modifiers changed from: package-private */
        public void onPlaylistChanged(List<MediaItem2> list, MediaMetadata2 mediaMetadata2) throws RemoteException {
            Bundle bundle;
            MediaSession2.ControllerInfo controller = MediaSession2Stub.this.mConnectedControllersManager.getController(getCallbackBinder());
            if (MediaSession2Stub.this.mConnectedControllersManager.isAllowedCommand(controller, 18)) {
                IMediaController2 iMediaController2 = this.mIControllerCallback;
                List<ParcelImpl> convertMediaItem2ListToParcelImplList = MediaUtils2.convertMediaItem2ListToParcelImplList(list);
                if (mediaMetadata2 == null) {
                    bundle = null;
                } else {
                    bundle = mediaMetadata2.toBundle();
                }
                iMediaController2.onPlaylistChanged(convertMediaItem2ListToParcelImplList, bundle);
            } else if (MediaSession2Stub.this.mConnectedControllersManager.isAllowedCommand(controller, 20)) {
                this.mIControllerCallback.onPlaylistMetadataChanged(mediaMetadata2.toBundle());
            }
        }

        /* access modifiers changed from: package-private */
        public void onPlaylistMetadataChanged(MediaMetadata2 mediaMetadata2) throws RemoteException {
            if (MediaSession2Stub.this.mConnectedControllersManager.isAllowedCommand(MediaSession2Stub.this.mConnectedControllersManager.getController(getCallbackBinder()), 20)) {
                this.mIControllerCallback.onPlaylistMetadataChanged(mediaMetadata2.toBundle());
            }
        }

        /* access modifiers changed from: package-private */
        public void onShuffleModeChanged(int i) throws RemoteException {
            this.mIControllerCallback.onShuffleModeChanged(i);
        }

        /* access modifiers changed from: package-private */
        public void onRepeatModeChanged(int i) throws RemoteException {
            this.mIControllerCallback.onRepeatModeChanged(i);
        }

        /* access modifiers changed from: package-private */
        public void onRoutesInfoChanged(List<Bundle> list) throws RemoteException {
            this.mIControllerCallback.onRoutesInfoChanged(list);
        }

        /* access modifiers changed from: package-private */
        public void onGetLibraryRootDone(Bundle bundle, String str, Bundle bundle2) throws RemoteException {
            this.mIControllerCallback.onGetLibraryRootDone(bundle, str, bundle2);
        }

        /* access modifiers changed from: package-private */
        public void onChildrenChanged(String str, int i, Bundle bundle) throws RemoteException {
            this.mIControllerCallback.onChildrenChanged(str, i, bundle);
        }

        /* access modifiers changed from: package-private */
        public void onGetChildrenDone(String str, int i, int i2, List<MediaItem2> list, Bundle bundle) throws RemoteException {
            this.mIControllerCallback.onGetChildrenDone(str, i, i2, MediaUtils2.convertMediaItem2ListToParcelImplList(list), bundle);
        }

        /* access modifiers changed from: package-private */
        public void onGetItemDone(String str, MediaItem2 mediaItem2) throws RemoteException {
            this.mIControllerCallback.onGetItemDone(str, (ParcelImpl) ParcelUtils.toParcelable(mediaItem2));
        }

        /* access modifiers changed from: package-private */
        public void onSearchResultChanged(String str, int i, Bundle bundle) throws RemoteException {
            this.mIControllerCallback.onSearchResultChanged(str, i, bundle);
        }

        /* access modifiers changed from: package-private */
        public void onGetSearchResultDone(String str, int i, int i2, List<MediaItem2> list, Bundle bundle) throws RemoteException {
            this.mIControllerCallback.onGetSearchResultDone(str, i, i2, MediaUtils2.convertMediaItem2ListToParcelImplList(list), bundle);
        }

        /* access modifiers changed from: package-private */
        public void onDisconnected() throws RemoteException {
            this.mIControllerCallback.onDisconnected();
        }
    }
}

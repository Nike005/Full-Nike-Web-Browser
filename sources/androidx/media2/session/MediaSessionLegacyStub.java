package androidx.media2.session;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Process;
import android.os.RemoteException;
import android.os.ResultReceiver;
import android.support.p064v4.media.MediaDescriptionCompat;
import android.support.p064v4.media.MediaMetadataCompat;
import android.support.p064v4.media.RatingCompat;
import android.support.p064v4.media.session.MediaSessionCompat;
import android.support.p064v4.media.session.PlaybackStateCompat;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseArray;
import androidx.core.util.ObjectsCompat;
import androidx.media.MediaSessionManager;
import androidx.media2.common.MediaItem;
import androidx.media2.common.MediaMetadata;
import androidx.media2.common.SessionPlayer;
import androidx.media2.common.SubtitleData;
import androidx.media2.common.VideoSize;
import androidx.media2.session.MediaController;
import androidx.media2.session.MediaLibraryService;
import androidx.media2.session.MediaSession;
import androidx.media2.session.SessionCommandGroup;
import java.util.List;

class MediaSessionLegacyStub extends MediaSessionCompat.Callback {
    static final boolean DEBUG = Log.isLoggable(TAG, 3);
    private static final String TAG = "MediaSessionLegacyStub";
    static final SparseArray<SessionCommand> sCommandsForOnCommandRequest = new SparseArray<>();
    final ConnectedControllersManager<MediaSessionManager.RemoteUserInfo> mConnectedControllersManager;
    final Context mContext;
    final MediaSession.ControllerInfo mControllerInfoForAll;
    final Object mLock = new Object();
    final MediaSession.MediaSessionImpl mSessionImpl;
    final MediaSessionManager mSessionManager;

    @FunctionalInterface
    private interface SessionTask {
        void run(MediaSession.ControllerInfo controllerInfo) throws RemoteException;
    }

    public void onCustomAction(String str, Bundle bundle) {
    }

    public void onSetCaptioningEnabled(boolean z) {
    }

    static {
        for (SessionCommand next : new SessionCommandGroup.Builder().addAllPlayerCommands(1, false).addAllVolumeCommands(1).build().getCommands()) {
            sCommandsForOnCommandRequest.append(next.getCommandCode(), next);
        }
    }

    MediaSessionLegacyStub(MediaSession.MediaSessionImpl mediaSessionImpl) {
        this.mSessionImpl = mediaSessionImpl;
        Context context = mediaSessionImpl.getContext();
        this.mContext = context;
        this.mSessionManager = MediaSessionManager.getSessionManager(context);
        this.mControllerInfoForAll = new MediaSession.ControllerInfo(new MediaSessionManager.RemoteUserInfo(MediaSessionManager.RemoteUserInfo.LEGACY_CONTROLLER, Process.myPid(), Process.myUid()), false, new ControllerLegacyCbForAll(), (Bundle) null);
        this.mConnectedControllersManager = new ConnectedControllersManager<>(mediaSessionImpl);
    }

    public void onCommand(String str, final Bundle bundle, final ResultReceiver resultReceiver) {
        if (str != null) {
            final SessionCommand sessionCommand = new SessionCommand(str, (Bundle) null);
            dispatchSessionTask(sessionCommand, (SessionTask) new SessionTask() {
                public void run(MediaSession.ControllerInfo controllerInfo) throws RemoteException {
                    SessionResult onCustomCommand = MediaSessionLegacyStub.this.mSessionImpl.getCallback().onCustomCommand(MediaSessionLegacyStub.this.mSessionImpl.getInstance(), controllerInfo, sessionCommand, bundle);
                    ResultReceiver resultReceiver = resultReceiver;
                    if (resultReceiver != null) {
                        resultReceiver.send(onCustomCommand.getResultCode(), onCustomCommand.getCustomCommandResult());
                    }
                }
            });
        }
    }

    public void onPrepare() {
        dispatchSessionTask((int) SessionCommand.COMMAND_CODE_PLAYER_PREPARE, (SessionTask) new SessionTask() {
            public void run(MediaSession.ControllerInfo controllerInfo) throws RemoteException {
                MediaSessionLegacyStub.this.mSessionImpl.prepare();
            }
        });
    }

    public void onPrepareFromMediaId(final String str, final Bundle bundle) {
        dispatchSessionTask((int) SessionCommand.COMMAND_CODE_SESSION_PREPARE_FROM_MEDIA_ID, (SessionTask) new SessionTask() {
            public void run(MediaSession.ControllerInfo controllerInfo) throws RemoteException {
                if (TextUtils.isEmpty(str)) {
                    Log.w(MediaSessionLegacyStub.TAG, "onPrepareFromMediaId(): Ignoring empty mediaId from " + controllerInfo);
                    return;
                }
                MediaSessionLegacyStub.this.mSessionImpl.getCallback().onPrepareFromMediaId(MediaSessionLegacyStub.this.mSessionImpl.getInstance(), controllerInfo, str, bundle);
            }
        });
    }

    public void onPrepareFromSearch(final String str, final Bundle bundle) {
        dispatchSessionTask((int) SessionCommand.COMMAND_CODE_SESSION_PREPARE_FROM_SEARCH, (SessionTask) new SessionTask() {
            public void run(MediaSession.ControllerInfo controllerInfo) throws RemoteException {
                if (TextUtils.isEmpty(str)) {
                    Log.w(MediaSessionLegacyStub.TAG, "onPrepareFromSearch(): Ignoring empty query from " + controllerInfo);
                    return;
                }
                MediaSessionLegacyStub.this.mSessionImpl.getCallback().onPrepareFromSearch(MediaSessionLegacyStub.this.mSessionImpl.getInstance(), controllerInfo, str, bundle);
            }
        });
    }

    public void onPrepareFromUri(final Uri uri, final Bundle bundle) {
        if (uri != null) {
            dispatchSessionTask((int) SessionCommand.COMMAND_CODE_SESSION_PREPARE_FROM_URI, (SessionTask) new SessionTask() {
                public void run(MediaSession.ControllerInfo controllerInfo) throws RemoteException {
                    MediaSessionLegacyStub.this.mSessionImpl.getCallback().onPrepareFromUri(MediaSessionLegacyStub.this.mSessionImpl.getInstance(), controllerInfo, uri, bundle);
                }
            });
        }
    }

    public void onPlay() {
        dispatchSessionTask(10000, (SessionTask) new SessionTask() {
            public void run(MediaSession.ControllerInfo controllerInfo) throws RemoteException {
                MediaSessionLegacyStub.this.mSessionImpl.play();
            }
        });
    }

    public void onPlayFromMediaId(final String str, final Bundle bundle) {
        dispatchSessionTask((int) SessionCommand.COMMAND_CODE_SESSION_PLAY_FROM_MEDIA_ID, (SessionTask) new SessionTask() {
            public void run(MediaSession.ControllerInfo controllerInfo) throws RemoteException {
                if (TextUtils.isEmpty(str)) {
                    Log.w(MediaSessionLegacyStub.TAG, "onPlayFromMediaId(): Ignoring empty mediaId from " + controllerInfo);
                    return;
                }
                MediaSessionLegacyStub.this.mSessionImpl.getCallback().onPlayFromMediaId(MediaSessionLegacyStub.this.mSessionImpl.getInstance(), controllerInfo, str, bundle);
            }
        });
    }

    public void onPlayFromSearch(final String str, final Bundle bundle) {
        dispatchSessionTask((int) SessionCommand.COMMAND_CODE_SESSION_PLAY_FROM_SEARCH, (SessionTask) new SessionTask() {
            public void run(MediaSession.ControllerInfo controllerInfo) throws RemoteException {
                if (TextUtils.isEmpty(str)) {
                    Log.w(MediaSessionLegacyStub.TAG, "onPlayFromSearch(): Ignoring empty query from " + controllerInfo);
                    return;
                }
                MediaSessionLegacyStub.this.mSessionImpl.getCallback().onPlayFromSearch(MediaSessionLegacyStub.this.mSessionImpl.getInstance(), controllerInfo, str, bundle);
            }
        });
    }

    public void onPlayFromUri(final Uri uri, final Bundle bundle) {
        if (uri != null) {
            dispatchSessionTask((int) SessionCommand.COMMAND_CODE_SESSION_PLAY_FROM_URI, (SessionTask) new SessionTask() {
                public void run(MediaSession.ControllerInfo controllerInfo) throws RemoteException {
                    MediaSessionLegacyStub.this.mSessionImpl.getCallback().onPlayFromUri(MediaSessionLegacyStub.this.mSessionImpl.getInstance(), controllerInfo, uri, bundle);
                }
            });
        }
    }

    public void onPause() {
        dispatchSessionTask((int) SessionCommand.COMMAND_CODE_PLAYER_PAUSE, (SessionTask) new SessionTask() {
            public void run(MediaSession.ControllerInfo controllerInfo) throws RemoteException {
                MediaSessionLegacyStub.this.mSessionImpl.pause();
            }
        });
    }

    public void onStop() {
        dispatchSessionTask((int) SessionCommand.COMMAND_CODE_PLAYER_PAUSE, (SessionTask) new SessionTask() {
            public void run(MediaSession.ControllerInfo controllerInfo) throws RemoteException {
                MediaSessionLegacyStub.this.handleTaskOnExecutor(controllerInfo, (SessionCommand) null, SessionCommand.COMMAND_CODE_PLAYER_SEEK_TO, new SessionTask() {
                    public void run(MediaSession.ControllerInfo controllerInfo) throws RemoteException {
                        MediaSessionLegacyStub.this.mSessionImpl.pause();
                        MediaSessionLegacyStub.this.mSessionImpl.seekTo(0);
                    }
                });
            }
        });
    }

    public void onSeekTo(final long j) {
        dispatchSessionTask((int) SessionCommand.COMMAND_CODE_PLAYER_SEEK_TO, (SessionTask) new SessionTask() {
            public void run(MediaSession.ControllerInfo controllerInfo) throws RemoteException {
                MediaSessionLegacyStub.this.mSessionImpl.seekTo(j);
            }
        });
    }

    public void onSkipToNext() {
        dispatchSessionTask((int) SessionCommand.COMMAND_CODE_PLAYER_SKIP_TO_NEXT_PLAYLIST_ITEM, (SessionTask) new SessionTask() {
            public void run(MediaSession.ControllerInfo controllerInfo) throws RemoteException {
                MediaSessionLegacyStub.this.mSessionImpl.skipToNextItem();
            }
        });
    }

    public void onSkipToPrevious() {
        dispatchSessionTask((int) SessionCommand.COMMAND_CODE_PLAYER_SKIP_TO_PREVIOUS_PLAYLIST_ITEM, (SessionTask) new SessionTask() {
            public void run(MediaSession.ControllerInfo controllerInfo) throws RemoteException {
                MediaSessionLegacyStub.this.mSessionImpl.skipToPreviousItem();
            }
        });
    }

    public void onSetPlaybackSpeed(final float f) {
        dispatchSessionTask((int) SessionCommand.COMMAND_CODE_PLAYER_SET_SPEED, (SessionTask) new SessionTask() {
            public void run(MediaSession.ControllerInfo controllerInfo) throws RemoteException {
                MediaSessionLegacyStub.this.mSessionImpl.setPlaybackSpeed(f);
            }
        });
    }

    public void onSkipToQueueItem(final long j) {
        dispatchSessionTask((int) SessionCommand.COMMAND_CODE_PLAYER_SKIP_TO_PLAYLIST_ITEM, (SessionTask) new SessionTask() {
            public void run(MediaSession.ControllerInfo controllerInfo) throws RemoteException {
                if (MediaSessionLegacyStub.this.mSessionImpl.getPlayer().getPlaylist() != null) {
                    MediaSessionLegacyStub.this.mSessionImpl.skipToPlaylistItem((int) j);
                }
            }
        });
    }

    public void onFastForward() {
        dispatchSessionTask((int) SessionCommand.COMMAND_CODE_SESSION_FAST_FORWARD, (SessionTask) new SessionTask() {
            public void run(MediaSession.ControllerInfo controllerInfo) throws RemoteException {
                MediaSessionLegacyStub.this.mSessionImpl.getCallback().onFastForward(MediaSessionLegacyStub.this.mSessionImpl.getInstance(), controllerInfo);
            }
        });
    }

    public void onRewind() {
        dispatchSessionTask((int) SessionCommand.COMMAND_CODE_SESSION_REWIND, (SessionTask) new SessionTask() {
            public void run(MediaSession.ControllerInfo controllerInfo) throws RemoteException {
                MediaSessionLegacyStub.this.mSessionImpl.getCallback().onRewind(MediaSessionLegacyStub.this.mSessionImpl.getInstance(), controllerInfo);
            }
        });
    }

    public void onSetRating(RatingCompat ratingCompat) {
        onSetRating(ratingCompat, (Bundle) null);
    }

    public void onSetRating(final RatingCompat ratingCompat, Bundle bundle) {
        if (ratingCompat != null) {
            dispatchSessionTask((int) SessionCommand.COMMAND_CODE_SESSION_SET_RATING, (SessionTask) new SessionTask() {
                public void run(MediaSession.ControllerInfo controllerInfo) throws RemoteException {
                    MediaItem currentMediaItem = MediaSessionLegacyStub.this.mSessionImpl.getCurrentMediaItem();
                    if (currentMediaItem != null) {
                        MediaSessionLegacyStub.this.mSessionImpl.getCallback().onSetRating(MediaSessionLegacyStub.this.mSessionImpl.getInstance(), controllerInfo, currentMediaItem.getMediaId(), MediaUtils.convertToRating(ratingCompat));
                    }
                }
            });
        }
    }

    public void onSetRepeatMode(final int i) {
        dispatchSessionTask((int) SessionCommand.COMMAND_CODE_PLAYER_SET_REPEAT_MODE, (SessionTask) new SessionTask() {
            public void run(MediaSession.ControllerInfo controllerInfo) throws RemoteException {
                MediaSessionLegacyStub.this.mSessionImpl.setRepeatMode(i);
            }
        });
    }

    public void onSetShuffleMode(final int i) {
        dispatchSessionTask((int) SessionCommand.COMMAND_CODE_PLAYER_SET_SHUFFLE_MODE, (SessionTask) new SessionTask() {
            public void run(MediaSession.ControllerInfo controllerInfo) throws RemoteException {
                MediaSessionLegacyStub.this.mSessionImpl.setShuffleMode(i);
            }
        });
    }

    public void onAddQueueItem(MediaDescriptionCompat mediaDescriptionCompat) {
        onAddQueueItem(mediaDescriptionCompat, Integer.MAX_VALUE);
    }

    public void onAddQueueItem(final MediaDescriptionCompat mediaDescriptionCompat, final int i) {
        if (mediaDescriptionCompat != null) {
            dispatchSessionTask((int) SessionCommand.COMMAND_CODE_PLAYER_ADD_PLAYLIST_ITEM, (SessionTask) new SessionTask() {
                public void run(MediaSession.ControllerInfo controllerInfo) throws RemoteException {
                    String mediaId = mediaDescriptionCompat.getMediaId();
                    if (TextUtils.isEmpty(mediaId)) {
                        Log.w(MediaSessionLegacyStub.TAG, "onAddQueueItem(): Media ID shouldn't be empty");
                        return;
                    }
                    MediaSessionLegacyStub.this.mSessionImpl.addPlaylistItem(i, MediaSessionLegacyStub.this.mSessionImpl.getCallback().onCreateMediaItem(MediaSessionLegacyStub.this.mSessionImpl.getInstance(), controllerInfo, mediaId));
                }
            });
        }
    }

    public void onRemoveQueueItem(final MediaDescriptionCompat mediaDescriptionCompat) {
        if (mediaDescriptionCompat != null) {
            dispatchSessionTask((int) SessionCommand.COMMAND_CODE_PLAYER_REMOVE_PLAYLIST_ITEM, (SessionTask) new SessionTask() {
                public void run(MediaSession.ControllerInfo controllerInfo) throws RemoteException {
                    String mediaId = mediaDescriptionCompat.getMediaId();
                    if (TextUtils.isEmpty(mediaId)) {
                        Log.w(MediaSessionLegacyStub.TAG, "onRemoveQueueItem(): Media ID shouldn't be null");
                        return;
                    }
                    List<MediaItem> playlist = MediaSessionLegacyStub.this.mSessionImpl.getPlaylist();
                    for (int i = 0; i < playlist.size(); i++) {
                        if (TextUtils.equals(playlist.get(i).getMediaId(), mediaId)) {
                            MediaSessionLegacyStub.this.mSessionImpl.removePlaylistItem(i);
                            return;
                        }
                    }
                }
            });
        }
    }

    public void onRemoveQueueItemAt(final int i) {
        dispatchSessionTask((int) SessionCommand.COMMAND_CODE_PLAYER_REMOVE_PLAYLIST_ITEM, (SessionTask) new SessionTask() {
            public void run(MediaSession.ControllerInfo controllerInfo) throws RemoteException {
                if (i < 0) {
                    Log.w(MediaSessionLegacyStub.TAG, "onRemoveQueueItem(): index shouldn't be negative");
                } else {
                    MediaSessionLegacyStub.this.mSessionImpl.removePlaylistItem(i);
                }
            }
        });
    }

    /* access modifiers changed from: package-private */
    public MediaSession.ControllerInfo getControllersForAll() {
        return this.mControllerInfoForAll;
    }

    /* access modifiers changed from: package-private */
    public ConnectedControllersManager getConnectedControllersManager() {
        return this.mConnectedControllersManager;
    }

    private void dispatchSessionTask(int i, SessionTask sessionTask) {
        dispatchSessionTaskInternal((SessionCommand) null, i, sessionTask);
    }

    private void dispatchSessionTask(SessionCommand sessionCommand, SessionTask sessionTask) {
        dispatchSessionTaskInternal(sessionCommand, 0, sessionTask);
    }

    private void dispatchSessionTaskInternal(SessionCommand sessionCommand, int i, SessionTask sessionTask) {
        if (!this.mSessionImpl.isClosed()) {
            final MediaSessionManager.RemoteUserInfo currentControllerInfo = this.mSessionImpl.getSessionCompat().getCurrentControllerInfo();
            if (currentControllerInfo == null) {
                Log.d(TAG, "RemoteUserInfo is null, ignoring command=" + sessionCommand + ", commandCode=" + i);
                return;
            }
            final SessionCommand sessionCommand2 = sessionCommand;
            final int i2 = i;
            final SessionTask sessionTask2 = sessionTask;
            this.mSessionImpl.getCallbackExecutor().execute(new Runnable() {
                public void run() {
                    if (!MediaSessionLegacyStub.this.mSessionImpl.isClosed()) {
                        MediaSession.ControllerInfo controller = MediaSessionLegacyStub.this.mConnectedControllersManager.getController(currentControllerInfo);
                        if (controller == null) {
                            controller = new MediaSession.ControllerInfo(currentControllerInfo, MediaSessionLegacyStub.this.mSessionManager.isTrustedForMediaControl(currentControllerInfo), new ControllerLegacyCb(currentControllerInfo), (Bundle) null);
                        }
                        if (!MediaSessionLegacyStub.this.mConnectedControllersManager.isConnected(controller)) {
                            SessionCommandGroup onConnect = MediaSessionLegacyStub.this.mSessionImpl.getCallback().onConnect(MediaSessionLegacyStub.this.mSessionImpl.getInstance(), controller);
                            if (onConnect == null) {
                                try {
                                    controller.getControllerCb().onDisconnected(0);
                                    return;
                                } catch (RemoteException unused) {
                                    return;
                                }
                            } else {
                                MediaSessionLegacyStub.this.mConnectedControllersManager.addController(controller.getRemoteUserInfo(), controller, onConnect);
                            }
                        }
                        MediaSessionLegacyStub.this.handleTaskOnExecutor(controller, sessionCommand2, i2, sessionTask2);
                    }
                }
            });
        }
    }

    /* access modifiers changed from: package-private */
    public void handleTaskOnExecutor(MediaSession.ControllerInfo controllerInfo, SessionCommand sessionCommand, int i, SessionTask sessionTask) {
        SessionCommand sessionCommand2;
        if (sessionCommand != null) {
            if (this.mConnectedControllersManager.isAllowedCommand(controllerInfo, sessionCommand)) {
                sessionCommand2 = sCommandsForOnCommandRequest.get(sessionCommand.getCommandCode());
            } else {
                return;
            }
        } else if (this.mConnectedControllersManager.isAllowedCommand(controllerInfo, i)) {
            sessionCommand2 = sCommandsForOnCommandRequest.get(i);
        } else {
            return;
        }
        if (sessionCommand2 == null || this.mSessionImpl.getCallback().onCommandRequest(this.mSessionImpl.getInstance(), controllerInfo, sessionCommand2) == 0) {
            try {
                sessionTask.run(controllerInfo);
            } catch (RemoteException e) {
                Log.w(TAG, "Exception in " + controllerInfo, e);
            }
        } else if (DEBUG) {
            Log.d(TAG, "Command (" + sessionCommand2 + ") from " + controllerInfo + " was rejected by " + this.mSessionImpl);
        }
    }

    final class ControllerLegacyCb extends MediaSession.ControllerCb {
        private final MediaSessionManager.RemoteUserInfo mRemoteUserInfo;

        /* access modifiers changed from: package-private */
        public void onAllowedCommandsChanged(int i, SessionCommandGroup sessionCommandGroup) throws RemoteException {
        }

        /* access modifiers changed from: package-private */
        public void onChildrenChanged(int i, String str, int i2, MediaLibraryService.LibraryParams libraryParams) throws RemoteException {
        }

        /* access modifiers changed from: package-private */
        public void onDisconnected(int i) throws RemoteException {
        }

        /* access modifiers changed from: package-private */
        public void onLibraryResult(int i, LibraryResult libraryResult) throws RemoteException {
        }

        /* access modifiers changed from: package-private */
        public void onPlayerResult(int i, SessionPlayer.PlayerResult playerResult) throws RemoteException {
        }

        /* access modifiers changed from: package-private */
        public void onSearchResultChanged(int i, String str, int i2, MediaLibraryService.LibraryParams libraryParams) throws RemoteException {
        }

        /* access modifiers changed from: package-private */
        public void onSessionResult(int i, SessionResult sessionResult) throws RemoteException {
        }

        /* access modifiers changed from: package-private */
        public void onSubtitleData(int i, MediaItem mediaItem, SessionPlayer.TrackInfo trackInfo, SubtitleData subtitleData) {
        }

        /* access modifiers changed from: package-private */
        public void onTrackDeselected(int i, SessionPlayer.TrackInfo trackInfo) throws RemoteException {
        }

        /* access modifiers changed from: package-private */
        public void onTrackInfoChanged(int i, List<SessionPlayer.TrackInfo> list, SessionPlayer.TrackInfo trackInfo, SessionPlayer.TrackInfo trackInfo2, SessionPlayer.TrackInfo trackInfo3, SessionPlayer.TrackInfo trackInfo4) throws RemoteException {
        }

        /* access modifiers changed from: package-private */
        public void onTrackSelected(int i, SessionPlayer.TrackInfo trackInfo) throws RemoteException {
        }

        /* access modifiers changed from: package-private */
        public void onVideoSizeChanged(int i, MediaItem mediaItem, VideoSize videoSize) {
        }

        /* access modifiers changed from: package-private */
        public void sendCustomCommand(int i, SessionCommand sessionCommand, Bundle bundle) throws RemoteException {
        }

        /* access modifiers changed from: package-private */
        public void setCustomLayout(int i, List<MediaSession.CommandButton> list) throws RemoteException {
        }

        ControllerLegacyCb(MediaSessionManager.RemoteUserInfo remoteUserInfo) {
            this.mRemoteUserInfo = remoteUserInfo;
        }

        /* access modifiers changed from: package-private */
        public void onPlaybackInfoChanged(int i, MediaController.PlaybackInfo playbackInfo) throws RemoteException {
            throw new AssertionError("This shouldn't be called");
        }

        /* access modifiers changed from: package-private */
        public void onPlayerStateChanged(int i, long j, long j2, int i2) throws RemoteException {
            throw new AssertionError("This shouldn't be called");
        }

        /* access modifiers changed from: package-private */
        public void onPlaybackSpeedChanged(int i, long j, long j2, float f) throws RemoteException {
            throw new AssertionError("This shouldn't be called");
        }

        /* access modifiers changed from: package-private */
        public void onBufferingStateChanged(int i, MediaItem mediaItem, int i2, long j, long j2, long j3) throws RemoteException {
            throw new AssertionError("This shouldn't be called");
        }

        /* access modifiers changed from: package-private */
        public void onSeekCompleted(int i, long j, long j2, long j3) throws RemoteException {
            throw new AssertionError("This shouldn't be called");
        }

        /* access modifiers changed from: package-private */
        public void onCurrentMediaItemChanged(int i, MediaItem mediaItem, int i2, int i3, int i4) throws RemoteException {
            throw new AssertionError("This shouldn't be called");
        }

        /* access modifiers changed from: package-private */
        public void onPlaylistChanged(int i, List<MediaItem> list, MediaMetadata mediaMetadata, int i2, int i3, int i4) throws RemoteException {
            throw new AssertionError("This shouldn't be called");
        }

        /* access modifiers changed from: package-private */
        public void onPlaylistMetadataChanged(int i, MediaMetadata mediaMetadata) throws RemoteException {
            throw new AssertionError("This shouldn't be called");
        }

        /* access modifiers changed from: package-private */
        public void onShuffleModeChanged(int i, int i2, int i3, int i4, int i5) throws RemoteException {
            throw new AssertionError("This shouldn't be called");
        }

        /* access modifiers changed from: package-private */
        public void onRepeatModeChanged(int i, int i2, int i3, int i4, int i5) throws RemoteException {
            throw new AssertionError("This shouldn't be called");
        }

        /* access modifiers changed from: package-private */
        public void onPlaybackCompleted(int i) throws RemoteException {
            throw new AssertionError("This shouldn't be called");
        }

        public int hashCode() {
            return ObjectsCompat.hash(this.mRemoteUserInfo);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || obj.getClass() != ControllerLegacyCb.class) {
                return false;
            }
            return ObjectsCompat.equals(this.mRemoteUserInfo, ((ControllerLegacyCb) obj).mRemoteUserInfo);
        }
    }

    final class ControllerLegacyCbForAll extends MediaSession.ControllerCb {
        /* access modifiers changed from: package-private */
        public void onChildrenChanged(int i, String str, int i2, MediaLibraryService.LibraryParams libraryParams) throws RemoteException {
        }

        /* access modifiers changed from: package-private */
        public void onDisconnected(int i) throws RemoteException {
        }

        /* access modifiers changed from: package-private */
        public void onLibraryResult(int i, LibraryResult libraryResult) throws RemoteException {
        }

        /* access modifiers changed from: package-private */
        public void onPlaybackInfoChanged(int i, MediaController.PlaybackInfo playbackInfo) throws RemoteException {
        }

        /* access modifiers changed from: package-private */
        public void onPlayerResult(int i, SessionPlayer.PlayerResult playerResult) throws RemoteException {
        }

        /* access modifiers changed from: package-private */
        public void onSearchResultChanged(int i, String str, int i2, MediaLibraryService.LibraryParams libraryParams) throws RemoteException {
        }

        /* access modifiers changed from: package-private */
        public void onSessionResult(int i, SessionResult sessionResult) throws RemoteException {
        }

        /* access modifiers changed from: package-private */
        public void onSubtitleData(int i, MediaItem mediaItem, SessionPlayer.TrackInfo trackInfo, SubtitleData subtitleData) {
        }

        /* access modifiers changed from: package-private */
        public void onTrackDeselected(int i, SessionPlayer.TrackInfo trackInfo) throws RemoteException {
        }

        /* access modifiers changed from: package-private */
        public void onTrackInfoChanged(int i, List<SessionPlayer.TrackInfo> list, SessionPlayer.TrackInfo trackInfo, SessionPlayer.TrackInfo trackInfo2, SessionPlayer.TrackInfo trackInfo3, SessionPlayer.TrackInfo trackInfo4) throws RemoteException {
        }

        /* access modifiers changed from: package-private */
        public void onTrackSelected(int i, SessionPlayer.TrackInfo trackInfo) throws RemoteException {
        }

        /* access modifiers changed from: package-private */
        public void onVideoSizeChanged(int i, MediaItem mediaItem, VideoSize videoSize) {
        }

        /* access modifiers changed from: package-private */
        public void sendCustomCommand(int i, SessionCommand sessionCommand, Bundle bundle) throws RemoteException {
        }

        ControllerLegacyCbForAll() {
        }

        /* access modifiers changed from: package-private */
        public void setCustomLayout(int i, List<MediaSession.CommandButton> list) throws RemoteException {
            throw new AssertionError("This shouldn't be called");
        }

        /* access modifiers changed from: package-private */
        public void onAllowedCommandsChanged(int i, SessionCommandGroup sessionCommandGroup) throws RemoteException {
            throw new AssertionError("This shouldn't be called");
        }

        /* access modifiers changed from: package-private */
        public void onPlayerStateChanged(int i, long j, long j2, int i2) throws RemoteException {
            MediaSessionLegacyStub.this.mSessionImpl.getSessionCompat().setPlaybackState(MediaSessionLegacyStub.this.mSessionImpl.createPlaybackStateCompat());
        }

        /* access modifiers changed from: package-private */
        public void onPlaybackSpeedChanged(int i, long j, long j2, float f) throws RemoteException {
            MediaSessionLegacyStub.this.mSessionImpl.getSessionCompat().setPlaybackState(MediaSessionLegacyStub.this.mSessionImpl.createPlaybackStateCompat());
        }

        /* access modifiers changed from: package-private */
        public void onBufferingStateChanged(int i, MediaItem mediaItem, int i2, long j, long j2, long j3) throws RemoteException {
            MediaSessionLegacyStub.this.mSessionImpl.getSessionCompat().setPlaybackState(MediaSessionLegacyStub.this.mSessionImpl.createPlaybackStateCompat());
        }

        /* access modifiers changed from: package-private */
        public void onSeekCompleted(int i, long j, long j2, long j3) throws RemoteException {
            MediaSessionLegacyStub.this.mSessionImpl.getSessionCompat().setPlaybackState(MediaSessionLegacyStub.this.mSessionImpl.createPlaybackStateCompat());
        }

        /* access modifiers changed from: package-private */
        public void onCurrentMediaItemChanged(int i, MediaItem mediaItem, int i2, int i3, int i4) throws RemoteException {
            MediaMetadataCompat mediaMetadataCompat;
            MediaSessionCompat sessionCompat = MediaSessionLegacyStub.this.mSessionImpl.getSessionCompat();
            if (mediaItem == null) {
                mediaMetadataCompat = null;
            } else {
                mediaMetadataCompat = MediaUtils.convertToMediaMetadataCompat(mediaItem.getMetadata());
            }
            sessionCompat.setMetadata(mediaMetadataCompat);
        }

        /* access modifiers changed from: package-private */
        public void onPlaylistChanged(int i, List<MediaItem> list, MediaMetadata mediaMetadata, int i2, int i3, int i4) throws RemoteException {
            if (Build.VERSION.SDK_INT >= 21) {
                MediaSessionLegacyStub.this.mSessionImpl.getSessionCompat().setQueue(MediaUtils.convertToQueueItemList(list));
            } else if (list == null) {
                MediaSessionLegacyStub.this.mSessionImpl.getSessionCompat().setQueue((List<MediaSessionCompat.QueueItem>) null);
            } else {
                List<T> truncateListBySize = MediaUtils.truncateListBySize(MediaUtils.convertToQueueItemList(list), 262144);
                if (truncateListBySize.size() != list.size()) {
                    Log.i(MediaSessionLegacyStub.TAG, "Sending " + truncateListBySize.size() + " items out of " + list.size());
                }
                MediaSessionLegacyStub.this.mSessionImpl.getSessionCompat().setQueue(truncateListBySize);
            }
            onPlaylistMetadataChanged(i, mediaMetadata);
        }

        /* access modifiers changed from: package-private */
        public void onPlaylistMetadataChanged(int i, MediaMetadata mediaMetadata) throws RemoteException {
            CharSequence charSequence;
            CharSequence queueTitle = MediaSessionLegacyStub.this.mSessionImpl.getSessionCompat().getController().getQueueTitle();
            if (mediaMetadata != null) {
                charSequence = mediaMetadata.getText("android.media.metadata.DISPLAY_TITLE");
                if (charSequence == null) {
                    charSequence = mediaMetadata.getText("android.media.metadata.TITLE");
                }
            } else {
                charSequence = null;
            }
            if (!TextUtils.equals(queueTitle, charSequence)) {
                MediaSessionLegacyStub.this.mSessionImpl.getSessionCompat().setQueueTitle(charSequence);
            }
        }

        /* access modifiers changed from: package-private */
        public void onShuffleModeChanged(int i, int i2, int i3, int i4, int i5) throws RemoteException {
            MediaSessionLegacyStub.this.mSessionImpl.getSessionCompat().setShuffleMode(i2);
        }

        /* access modifiers changed from: package-private */
        public void onRepeatModeChanged(int i, int i2, int i3, int i4, int i5) throws RemoteException {
            MediaSessionLegacyStub.this.mSessionImpl.getSessionCompat().setRepeatMode(i2);
        }

        /* access modifiers changed from: package-private */
        public void onPlaybackCompleted(int i) throws RemoteException {
            PlaybackStateCompat createPlaybackStateCompat = MediaSessionLegacyStub.this.mSessionImpl.createPlaybackStateCompat();
            if (createPlaybackStateCompat.getState() != 2) {
                createPlaybackStateCompat = new PlaybackStateCompat.Builder(createPlaybackStateCompat).setState(2, createPlaybackStateCompat.getPosition(), createPlaybackStateCompat.getPlaybackSpeed()).build();
            }
            MediaSessionLegacyStub.this.mSessionImpl.getSessionCompat().setPlaybackState(createPlaybackStateCompat);
        }
    }
}

package androidx.media2;

import android.content.Context;
import android.net.Uri;
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
import androidx.media.MediaSessionManager;
import androidx.media2.MediaController2;
import androidx.media2.MediaSession2;
import androidx.media2.SessionCommandGroup2;
import java.util.List;

class MediaSessionLegacyStub extends MediaSessionCompat.Callback {
    static final boolean DEBUG = Log.isLoggable(TAG, 3);
    private static final String TAG = "MediaSessionLegacyStub";
    static final SparseArray<SessionCommand2> sCommandsForOnCommandRequest = new SparseArray<>();
    final ConnectedControllersManager<MediaSessionManager.RemoteUserInfo> mConnectedControllersManager;
    final Context mContext;
    final MediaSession2.ControllerInfo mControllerInfoForAll;
    final Object mLock = new Object();
    final MediaSession2.MediaSession2Impl mSessionImpl;
    final MediaSessionManager mSessionManager;

    @FunctionalInterface
    private interface SessionRunnable {
        void run(MediaSession2.ControllerInfo controllerInfo) throws RemoteException;
    }

    public void onCustomAction(String str, Bundle bundle) {
    }

    public void onSetCaptioningEnabled(boolean z) {
    }

    static {
        for (SessionCommand2 next : new SessionCommandGroup2.Builder().addAllPlaybackCommands().addAllPlaylistCommands().addAllVolumeCommands().build().getCommands()) {
            sCommandsForOnCommandRequest.append(next.getCommandCode(), next);
        }
    }

    MediaSessionLegacyStub(MediaSession2.MediaSession2Impl mediaSession2Impl) {
        this.mSessionImpl = mediaSession2Impl;
        Context context = mediaSession2Impl.getContext();
        this.mContext = context;
        this.mSessionManager = MediaSessionManager.getSessionManager(context);
        this.mControllerInfoForAll = new MediaSession2.ControllerInfo(new MediaSessionManager.RemoteUserInfo(MediaSessionManager.RemoteUserInfo.LEGACY_CONTROLLER, Process.myPid(), Process.myUid()), false, new ControllerLegacyCbForAll());
        this.mConnectedControllersManager = new ConnectedControllersManager<>(mediaSession2Impl);
    }

    public void onCommand(String str, final Bundle bundle, final ResultReceiver resultReceiver) {
        if (str != null) {
            final SessionCommand2 sessionCommand2 = new SessionCommand2(str, (Bundle) null);
            onSessionCommand(sessionCommand2, (SessionRunnable) new SessionRunnable() {
                public void run(MediaSession2.ControllerInfo controllerInfo) throws RemoteException {
                    MediaSessionLegacyStub.this.mSessionImpl.getCallback().onCustomCommand(MediaSessionLegacyStub.this.mSessionImpl.getInstance(), controllerInfo, sessionCommand2, bundle, resultReceiver);
                }
            });
        }
    }

    public void onPrepare() {
        onSessionCommand(6, (SessionRunnable) new SessionRunnable() {
            public void run(MediaSession2.ControllerInfo controllerInfo) throws RemoteException {
                MediaSessionLegacyStub.this.mSessionImpl.getInstance().prepare();
            }
        });
    }

    public void onPrepareFromMediaId(final String str, final Bundle bundle) {
        if (str != null) {
            onSessionCommand(25, (SessionRunnable) new SessionRunnable() {
                public void run(MediaSession2.ControllerInfo controllerInfo) throws RemoteException {
                    MediaSessionLegacyStub.this.mSessionImpl.getCallback().onPrepareFromMediaId(MediaSessionLegacyStub.this.mSessionImpl.getInstance(), controllerInfo, str, bundle);
                }
            });
        }
    }

    public void onPrepareFromSearch(final String str, final Bundle bundle) {
        if (str != null) {
            onSessionCommand(27, (SessionRunnable) new SessionRunnable() {
                public void run(MediaSession2.ControllerInfo controllerInfo) throws RemoteException {
                    MediaSessionLegacyStub.this.mSessionImpl.getCallback().onPrepareFromSearch(MediaSessionLegacyStub.this.mSessionImpl.getInstance(), controllerInfo, str, bundle);
                }
            });
        }
    }

    public void onPrepareFromUri(final Uri uri, final Bundle bundle) {
        if (uri != null) {
            onSessionCommand(26, (SessionRunnable) new SessionRunnable() {
                public void run(MediaSession2.ControllerInfo controllerInfo) throws RemoteException {
                    MediaSessionLegacyStub.this.mSessionImpl.getCallback().onPrepareFromUri(MediaSessionLegacyStub.this.mSessionImpl.getInstance(), controllerInfo, uri, bundle);
                }
            });
        }
    }

    public void onPlay() {
        onSessionCommand(1, (SessionRunnable) new SessionRunnable() {
            public void run(MediaSession2.ControllerInfo controllerInfo) throws RemoteException {
                MediaSessionLegacyStub.this.mSessionImpl.getInstance().play();
            }
        });
    }

    public void onPlayFromMediaId(final String str, final Bundle bundle) {
        if (str != null) {
            onSessionCommand(22, (SessionRunnable) new SessionRunnable() {
                public void run(MediaSession2.ControllerInfo controllerInfo) throws RemoteException {
                    MediaSessionLegacyStub.this.mSessionImpl.getCallback().onPlayFromMediaId(MediaSessionLegacyStub.this.mSessionImpl.getInstance(), controllerInfo, str, bundle);
                }
            });
        }
    }

    public void onPlayFromSearch(final String str, final Bundle bundle) {
        if (str != null) {
            onSessionCommand(24, (SessionRunnable) new SessionRunnable() {
                public void run(MediaSession2.ControllerInfo controllerInfo) throws RemoteException {
                    MediaSessionLegacyStub.this.mSessionImpl.getCallback().onPlayFromSearch(MediaSessionLegacyStub.this.mSessionImpl.getInstance(), controllerInfo, str, bundle);
                }
            });
        }
    }

    public void onPlayFromUri(final Uri uri, final Bundle bundle) {
        if (uri != null) {
            onSessionCommand(23, (SessionRunnable) new SessionRunnable() {
                public void run(MediaSession2.ControllerInfo controllerInfo) throws RemoteException {
                    MediaSessionLegacyStub.this.mSessionImpl.getCallback().onPlayFromUri(MediaSessionLegacyStub.this.mSessionImpl.getInstance(), controllerInfo, uri, bundle);
                }
            });
        }
    }

    public void onPause() {
        onSessionCommand(2, (SessionRunnable) new SessionRunnable() {
            public void run(MediaSession2.ControllerInfo controllerInfo) throws RemoteException {
                MediaSessionLegacyStub.this.mSessionImpl.getInstance().pause();
            }
        });
    }

    public void onStop() {
        onSessionCommand(2, (SessionRunnable) new SessionRunnable() {
            public void run(MediaSession2.ControllerInfo controllerInfo) throws RemoteException {
                MediaSessionLegacyStub.this.handleCommandOnExecutor(controllerInfo, (SessionCommand2) null, 9, new SessionRunnable() {
                    public void run(MediaSession2.ControllerInfo controllerInfo) throws RemoteException {
                        MediaSessionLegacyStub.this.mSessionImpl.getInstance().pause();
                        MediaSessionLegacyStub.this.mSessionImpl.getInstance().seekTo(0);
                    }
                });
            }
        });
    }

    public void onSeekTo(final long j) {
        onSessionCommand(9, (SessionRunnable) new SessionRunnable() {
            public void run(MediaSession2.ControllerInfo controllerInfo) throws RemoteException {
                MediaSessionLegacyStub.this.mSessionImpl.getInstance().seekTo(j);
            }
        });
    }

    public void onSkipToNext() {
        onSessionCommand(4, (SessionRunnable) new SessionRunnable() {
            public void run(MediaSession2.ControllerInfo controllerInfo) throws RemoteException {
                MediaSessionLegacyStub.this.mSessionImpl.getInstance().skipToNextItem();
            }
        });
    }

    public void onSkipToPrevious() {
        onSessionCommand(5, (SessionRunnable) new SessionRunnable() {
            public void run(MediaSession2.ControllerInfo controllerInfo) throws RemoteException {
                MediaSessionLegacyStub.this.mSessionImpl.getInstance().skipToPreviousItem();
            }
        });
    }

    public void onSkipToQueueItem(final long j) {
        onSessionCommand(12, (SessionRunnable) new SessionRunnable() {
            public void run(MediaSession2.ControllerInfo controllerInfo) throws RemoteException {
                List<MediaItem2> playlist = MediaSessionLegacyStub.this.mSessionImpl.getPlaylistAgent().getPlaylist();
                if (playlist != null) {
                    int i = 0;
                    while (i < playlist.size()) {
                        MediaItem2 mediaItem2 = playlist.get(i);
                        if (mediaItem2 == null || mediaItem2.getUuid().getMostSignificantBits() != j) {
                            i++;
                        } else {
                            MediaSessionLegacyStub.this.mSessionImpl.getInstance().skipToPlaylistItem(mediaItem2);
                            return;
                        }
                    }
                }
            }
        });
    }

    public void onFastForward() {
        onSessionCommand(7, (SessionRunnable) new SessionRunnable() {
            public void run(MediaSession2.ControllerInfo controllerInfo) throws RemoteException {
                MediaSessionLegacyStub.this.mSessionImpl.getCallback().onFastForward(MediaSessionLegacyStub.this.mSessionImpl.getInstance(), controllerInfo);
            }
        });
    }

    public void onRewind() {
        onSessionCommand(8, (SessionRunnable) new SessionRunnable() {
            public void run(MediaSession2.ControllerInfo controllerInfo) throws RemoteException {
                MediaSessionLegacyStub.this.mSessionImpl.getCallback().onRewind(MediaSessionLegacyStub.this.mSessionImpl.getInstance(), controllerInfo);
            }
        });
    }

    public void onSetRating(RatingCompat ratingCompat) {
        onSetRating(ratingCompat, (Bundle) null);
    }

    public void onSetRating(final RatingCompat ratingCompat, Bundle bundle) {
        if (ratingCompat != null) {
            onSessionCommand(28, (SessionRunnable) new SessionRunnable() {
                public void run(MediaSession2.ControllerInfo controllerInfo) throws RemoteException {
                    MediaItem2 currentMediaItem = MediaSessionLegacyStub.this.mSessionImpl.getCurrentMediaItem();
                    if (currentMediaItem != null) {
                        MediaSessionLegacyStub.this.mSessionImpl.getCallback().onSetRating(MediaSessionLegacyStub.this.mSessionImpl.getInstance(), controllerInfo, currentMediaItem.getMediaId(), MediaUtils2.convertToRating2(ratingCompat));
                    }
                }
            });
        }
    }

    public void onSetRepeatMode(final int i) {
        onSessionCommand(14, (SessionRunnable) new SessionRunnable() {
            public void run(MediaSession2.ControllerInfo controllerInfo) throws RemoteException {
                MediaSessionLegacyStub.this.mSessionImpl.getInstance().setRepeatMode(i);
            }
        });
    }

    public void onSetShuffleMode(final int i) {
        onSessionCommand(13, (SessionRunnable) new SessionRunnable() {
            public void run(MediaSession2.ControllerInfo controllerInfo) throws RemoteException {
                MediaSessionLegacyStub.this.mSessionImpl.getInstance().setShuffleMode(i);
            }
        });
    }

    public void onAddQueueItem(final MediaDescriptionCompat mediaDescriptionCompat) {
        if (mediaDescriptionCompat != null) {
            onSessionCommand(15, (SessionRunnable) new SessionRunnable() {
                public void run(MediaSession2.ControllerInfo controllerInfo) throws RemoteException {
                    MediaSessionLegacyStub.this.mSessionImpl.getInstance().addPlaylistItem(Integer.MAX_VALUE, MediaUtils2.convertToMediaItem2(mediaDescriptionCompat));
                }
            });
        }
    }

    public void onAddQueueItem(final MediaDescriptionCompat mediaDescriptionCompat, final int i) {
        if (mediaDescriptionCompat != null) {
            onSessionCommand(15, (SessionRunnable) new SessionRunnable() {
                public void run(MediaSession2.ControllerInfo controllerInfo) throws RemoteException {
                    MediaSessionLegacyStub.this.mSessionImpl.getInstance().addPlaylistItem(i, MediaUtils2.convertToMediaItem2(mediaDescriptionCompat));
                }
            });
        }
    }

    public void onRemoveQueueItem(final MediaDescriptionCompat mediaDescriptionCompat) {
        if (mediaDescriptionCompat != null) {
            onSessionCommand(16, (SessionRunnable) new SessionRunnable() {
                public void run(MediaSession2.ControllerInfo controllerInfo) throws RemoteException {
                    List<MediaItem2> playlist = MediaSessionLegacyStub.this.mSessionImpl.getInstance().getPlaylist();
                    for (int i = 0; i < playlist.size(); i++) {
                        MediaItem2 mediaItem2 = playlist.get(i);
                        if (TextUtils.equals(mediaItem2.getMediaId(), mediaDescriptionCompat.getMediaId())) {
                            MediaSessionLegacyStub.this.mSessionImpl.getInstance().removePlaylistItem(mediaItem2);
                            return;
                        }
                    }
                }
            });
        }
    }

    /* access modifiers changed from: package-private */
    public MediaSession2.ControllerInfo getControllersForAll() {
        return this.mControllerInfoForAll;
    }

    /* access modifiers changed from: package-private */
    public ConnectedControllersManager getConnectedControllersManager() {
        return this.mConnectedControllersManager;
    }

    private void onSessionCommand(int i, SessionRunnable sessionRunnable) {
        onSessionCommandInternal((SessionCommand2) null, i, sessionRunnable);
    }

    private void onSessionCommand(SessionCommand2 sessionCommand2, SessionRunnable sessionRunnable) {
        onSessionCommandInternal(sessionCommand2, 0, sessionRunnable);
    }

    private void onSessionCommandInternal(SessionCommand2 sessionCommand2, int i, SessionRunnable sessionRunnable) {
        final MediaSession2.ControllerInfo controllerInfo;
        if (!this.mSessionImpl.isClosed()) {
            MediaSessionManager.RemoteUserInfo currentControllerInfo = this.mSessionImpl.getSessionCompat().getCurrentControllerInfo();
            synchronized (this.mLock) {
                if (currentControllerInfo == null) {
                    controllerInfo = null;
                } else {
                    MediaSession2.ControllerInfo controller = this.mConnectedControllersManager.getController(currentControllerInfo);
                    if (controller == null) {
                        controller = new MediaSession2.ControllerInfo(currentControllerInfo, this.mSessionManager.isTrustedForMediaControl(currentControllerInfo), new ControllerLegacyCb(currentControllerInfo));
                    }
                    controllerInfo = controller;
                }
            }
            final SessionCommand2 sessionCommand22 = sessionCommand2;
            final int i2 = i;
            final SessionRunnable sessionRunnable2 = sessionRunnable;
            this.mSessionImpl.getCallbackExecutor().execute(new Runnable() {
                public void run() {
                    if (controllerInfo != null && !MediaSessionLegacyStub.this.mSessionImpl.isClosed()) {
                        if (!MediaSessionLegacyStub.this.mConnectedControllersManager.isConnected(controllerInfo)) {
                            SessionCommandGroup2 onConnect = MediaSessionLegacyStub.this.mSessionImpl.getCallback().onConnect(MediaSessionLegacyStub.this.mSessionImpl.getInstance(), controllerInfo);
                            if (onConnect == null) {
                                try {
                                    controllerInfo.getControllerCb().onDisconnected();
                                    return;
                                } catch (RemoteException unused) {
                                    return;
                                }
                            } else {
                                MediaSessionLegacyStub.this.mConnectedControllersManager.addController(controllerInfo.getRemoteUserInfo(), controllerInfo, onConnect);
                            }
                        }
                        MediaSessionLegacyStub.this.handleCommandOnExecutor(controllerInfo, sessionCommand22, i2, sessionRunnable2);
                    }
                }
            });
        }
    }

    /* access modifiers changed from: package-private */
    public void handleCommandOnExecutor(MediaSession2.ControllerInfo controllerInfo, SessionCommand2 sessionCommand2, int i, SessionRunnable sessionRunnable) {
        SessionCommand2 sessionCommand22;
        if (sessionCommand2 != null) {
            if (this.mConnectedControllersManager.isAllowedCommand(controllerInfo, sessionCommand2)) {
                sessionCommand22 = sCommandsForOnCommandRequest.get(sessionCommand2.getCommandCode());
            } else {
                return;
            }
        } else if (this.mConnectedControllersManager.isAllowedCommand(controllerInfo, i)) {
            sessionCommand22 = sCommandsForOnCommandRequest.get(i);
        } else {
            return;
        }
        if (sessionCommand22 == null || this.mSessionImpl.getCallback().onCommandRequest(this.mSessionImpl.getInstance(), controllerInfo, sessionCommand22)) {
            try {
                sessionRunnable.run(controllerInfo);
            } catch (RemoteException e) {
                Log.w(TAG, "Exception in " + controllerInfo, e);
            }
        } else if (DEBUG) {
            Log.d(TAG, "Command (" + sessionCommand22 + ") from " + controllerInfo + " was rejected by " + this.mSessionImpl);
        }
    }

    final class ControllerLegacyCb extends MediaSession2.ControllerCb {
        private final MediaSessionManager.RemoteUserInfo mRemoteUserInfo;

        /* access modifiers changed from: package-private */
        public void onAllowedCommandsChanged(SessionCommandGroup2 sessionCommandGroup2) throws RemoteException {
        }

        /* access modifiers changed from: package-private */
        public void onChildrenChanged(String str, int i, Bundle bundle) throws RemoteException {
        }

        /* access modifiers changed from: package-private */
        public void onCustomCommand(SessionCommand2 sessionCommand2, Bundle bundle, ResultReceiver resultReceiver) throws RemoteException {
        }

        /* access modifiers changed from: package-private */
        public void onCustomLayoutChanged(List<MediaSession2.CommandButton> list) throws RemoteException {
        }

        /* access modifiers changed from: package-private */
        public void onDisconnected() throws RemoteException {
        }

        /* access modifiers changed from: package-private */
        public void onGetChildrenDone(String str, int i, int i2, List<MediaItem2> list, Bundle bundle) throws RemoteException {
        }

        /* access modifiers changed from: package-private */
        public void onGetItemDone(String str, MediaItem2 mediaItem2) throws RemoteException {
        }

        /* access modifiers changed from: package-private */
        public void onGetLibraryRootDone(Bundle bundle, String str, Bundle bundle2) throws RemoteException {
        }

        /* access modifiers changed from: package-private */
        public void onGetSearchResultDone(String str, int i, int i2, List<MediaItem2> list, Bundle bundle) throws RemoteException {
        }

        /* access modifiers changed from: package-private */
        public void onRoutesInfoChanged(List<Bundle> list) throws RemoteException {
        }

        /* access modifiers changed from: package-private */
        public void onSearchResultChanged(String str, int i, Bundle bundle) throws RemoteException {
        }

        ControllerLegacyCb(MediaSessionManager.RemoteUserInfo remoteUserInfo) {
            this.mRemoteUserInfo = remoteUserInfo;
        }

        /* access modifiers changed from: package-private */
        public void onPlaybackInfoChanged(MediaController2.PlaybackInfo playbackInfo) throws RemoteException {
            throw new AssertionError("This shouldn't be called.");
        }

        /* access modifiers changed from: package-private */
        public void onPlayerStateChanged(long j, long j2, int i) throws RemoteException {
            throw new AssertionError("This shouldn't be called.");
        }

        /* access modifiers changed from: package-private */
        public void onPlaybackSpeedChanged(long j, long j2, float f) throws RemoteException {
            throw new AssertionError("This shouldn't be called.");
        }

        /* access modifiers changed from: package-private */
        public void onBufferingStateChanged(MediaItem2 mediaItem2, int i, long j) throws RemoteException {
            throw new AssertionError("This shouldn't be called.");
        }

        /* access modifiers changed from: package-private */
        public void onSeekCompleted(long j, long j2, long j3) throws RemoteException {
            throw new AssertionError("This shouldn't be called.");
        }

        /* access modifiers changed from: package-private */
        public void onError(int i, Bundle bundle) throws RemoteException {
            throw new AssertionError("This shouldn't be called.");
        }

        /* access modifiers changed from: package-private */
        public void onCurrentMediaItemChanged(MediaItem2 mediaItem2) throws RemoteException {
            throw new AssertionError("This shouldn't be called.");
        }

        /* access modifiers changed from: package-private */
        public void onPlaylistChanged(List<MediaItem2> list, MediaMetadata2 mediaMetadata2) throws RemoteException {
            throw new AssertionError("This shouldn't be called.");
        }

        /* access modifiers changed from: package-private */
        public void onPlaylistMetadataChanged(MediaMetadata2 mediaMetadata2) throws RemoteException {
            throw new AssertionError("This shouldn't be called.");
        }

        /* access modifiers changed from: package-private */
        public void onShuffleModeChanged(int i) throws RemoteException {
            throw new AssertionError("This shouldn't be called.");
        }

        /* access modifiers changed from: package-private */
        public void onRepeatModeChanged(int i) throws RemoteException {
            throw new AssertionError("This shouldn't be called.");
        }
    }

    final class ControllerLegacyCbForAll extends MediaSession2.ControllerCb {
        /* access modifiers changed from: package-private */
        public void onChildrenChanged(String str, int i, Bundle bundle) throws RemoteException {
        }

        /* access modifiers changed from: package-private */
        public void onCustomCommand(SessionCommand2 sessionCommand2, Bundle bundle, ResultReceiver resultReceiver) throws RemoteException {
        }

        /* access modifiers changed from: package-private */
        public void onDisconnected() throws RemoteException {
        }

        /* access modifiers changed from: package-private */
        public void onGetChildrenDone(String str, int i, int i2, List<MediaItem2> list, Bundle bundle) throws RemoteException {
        }

        /* access modifiers changed from: package-private */
        public void onGetItemDone(String str, MediaItem2 mediaItem2) throws RemoteException {
        }

        /* access modifiers changed from: package-private */
        public void onGetLibraryRootDone(Bundle bundle, String str, Bundle bundle2) throws RemoteException {
        }

        /* access modifiers changed from: package-private */
        public void onGetSearchResultDone(String str, int i, int i2, List<MediaItem2> list, Bundle bundle) throws RemoteException {
        }

        /* access modifiers changed from: package-private */
        public void onPlaybackInfoChanged(MediaController2.PlaybackInfo playbackInfo) throws RemoteException {
        }

        /* access modifiers changed from: package-private */
        public void onSearchResultChanged(String str, int i, Bundle bundle) throws RemoteException {
        }

        ControllerLegacyCbForAll() {
        }

        /* access modifiers changed from: package-private */
        public void onCustomLayoutChanged(List<MediaSession2.CommandButton> list) throws RemoteException {
            throw new AssertionError("This shouldn't be called.");
        }

        /* access modifiers changed from: package-private */
        public void onAllowedCommandsChanged(SessionCommandGroup2 sessionCommandGroup2) throws RemoteException {
            throw new AssertionError("This shouldn't be called.");
        }

        /* access modifiers changed from: package-private */
        public void onPlayerStateChanged(long j, long j2, int i) throws RemoteException {
            MediaSessionLegacyStub.this.mSessionImpl.getSessionCompat().setPlaybackState(MediaSessionLegacyStub.this.mSessionImpl.createPlaybackStateCompat());
        }

        /* access modifiers changed from: package-private */
        public void onPlaybackSpeedChanged(long j, long j2, float f) throws RemoteException {
            MediaSessionLegacyStub.this.mSessionImpl.getSessionCompat().setPlaybackState(MediaSessionLegacyStub.this.mSessionImpl.createPlaybackStateCompat());
        }

        /* access modifiers changed from: package-private */
        public void onBufferingStateChanged(MediaItem2 mediaItem2, int i, long j) throws RemoteException {
            MediaSessionLegacyStub.this.mSessionImpl.getSessionCompat().setPlaybackState(MediaSessionLegacyStub.this.mSessionImpl.createPlaybackStateCompat());
        }

        /* access modifiers changed from: package-private */
        public void onSeekCompleted(long j, long j2, long j3) throws RemoteException {
            MediaSessionLegacyStub.this.mSessionImpl.getSessionCompat().setPlaybackState(MediaSessionLegacyStub.this.mSessionImpl.createPlaybackStateCompat());
        }

        /* access modifiers changed from: package-private */
        public void onError(int i, Bundle bundle) throws RemoteException {
            MediaSessionLegacyStub.this.mSessionImpl.getSessionCompat().setPlaybackState(new PlaybackStateCompat.Builder(MediaSessionLegacyStub.this.mSessionImpl.createPlaybackStateCompat()).setErrorMessage(i, "").setExtras(bundle).build());
        }

        /* access modifiers changed from: package-private */
        public void onCurrentMediaItemChanged(MediaItem2 mediaItem2) throws RemoteException {
            MediaMetadataCompat mediaMetadataCompat;
            MediaSessionCompat sessionCompat = MediaSessionLegacyStub.this.mSessionImpl.getSessionCompat();
            if (mediaItem2 == null) {
                mediaMetadataCompat = null;
            } else {
                mediaMetadataCompat = MediaUtils2.convertToMediaMetadataCompat(mediaItem2.getMetadata());
            }
            sessionCompat.setMetadata(mediaMetadataCompat);
        }

        /* access modifiers changed from: package-private */
        public void onPlaylistChanged(List<MediaItem2> list, MediaMetadata2 mediaMetadata2) throws RemoteException {
            MediaSessionLegacyStub.this.mSessionImpl.getSessionCompat().setQueue(MediaUtils2.convertToQueueItemList(list));
            onPlaylistMetadataChanged(mediaMetadata2);
        }

        /* access modifiers changed from: package-private */
        public void onPlaylistMetadataChanged(MediaMetadata2 mediaMetadata2) throws RemoteException {
            CharSequence charSequence;
            CharSequence queueTitle = MediaSessionLegacyStub.this.mSessionImpl.getSessionCompat().getController().getQueueTitle();
            if (mediaMetadata2 != null) {
                charSequence = mediaMetadata2.getText("android.media.metadata.DISPLAY_TITLE");
                if (charSequence == null) {
                    charSequence = mediaMetadata2.getText("android.media.metadata.TITLE");
                }
            } else {
                charSequence = null;
            }
            if (!TextUtils.equals(queueTitle, charSequence)) {
                MediaSessionLegacyStub.this.mSessionImpl.getSessionCompat().setQueueTitle(charSequence);
            }
        }

        /* access modifiers changed from: package-private */
        public void onShuffleModeChanged(int i) throws RemoteException {
            MediaSessionLegacyStub.this.mSessionImpl.getSessionCompat().setShuffleMode(i);
        }

        /* access modifiers changed from: package-private */
        public void onRepeatModeChanged(int i) throws RemoteException {
            MediaSessionLegacyStub.this.mSessionImpl.getSessionCompat().setRepeatMode(i);
        }

        /* access modifiers changed from: package-private */
        public void onRoutesInfoChanged(List<Bundle> list) throws RemoteException {
            throw new AssertionError("This shouldn't be called.");
        }
    }
}

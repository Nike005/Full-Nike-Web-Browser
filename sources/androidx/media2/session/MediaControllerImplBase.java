package androidx.media2.session;

import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Process;
import android.os.RemoteException;
import android.os.SystemClock;
import android.support.p064v4.media.MediaBrowserCompat;
import android.util.Log;
import android.util.SparseArray;
import android.view.Surface;
import androidx.media2.common.MediaItem;
import androidx.media2.common.MediaMetadata;
import androidx.media2.common.MediaParcelUtils;
import androidx.media2.common.Rating;
import androidx.media2.common.SessionPlayer;
import androidx.media2.common.SubtitleData;
import androidx.media2.common.VideoSize;
import androidx.media2.session.IMediaSession;
import androidx.media2.session.MediaController;
import androidx.media2.session.MediaSession;
import androidx.media2.session.SequencedFutureManager;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.List;

class MediaControllerImplBase implements MediaController.MediaControllerImpl {
    static final boolean DEBUG = Log.isLoggable(TAG, 3);
    private static final SessionResult RESULT_WHEN_CLOSED = new SessionResult(1);
    static final String TAG = "MC2ImplBase";
    private static final boolean THROW_EXCEPTION_FOR_NULL_RESULT = true;
    private SessionCommandGroup mAllowedCommands;
    private long mBufferedPositionMs;
    private int mBufferingState;
    private SessionToken mConnectedToken;
    private final Context mContext;
    final MediaControllerStub mControllerStub;
    private MediaItem mCurrentMediaItem;
    private int mCurrentMediaItemIndex = -1;
    private final IBinder.DeathRecipient mDeathRecipient;
    private volatile IMediaSession mISession;
    final MediaController mInstance;
    private boolean mIsReleased;
    private final Object mLock = new Object();
    private int mNextMediaItemIndex = -1;
    private MediaController.PlaybackInfo mPlaybackInfo;
    private float mPlaybackSpeed;
    private int mPlayerState;
    private List<MediaItem> mPlaylist;
    private MediaMetadata mPlaylistMetadata;
    private long mPositionEventTimeMs;
    private long mPositionMs;
    private int mPreviousMediaItemIndex = -1;
    private int mRepeatMode;
    private SparseArray<SessionPlayer.TrackInfo> mSelectedTracks = new SparseArray<>();
    final SequencedFutureManager mSequencedFutureManager;
    private SessionServiceConnection mServiceConnection;
    private PendingIntent mSessionActivity;
    private int mShuffleMode;
    final SessionToken mToken;
    private List<SessionPlayer.TrackInfo> mTrackInfos;
    private VideoSize mVideoSize = new VideoSize(0, 0);

    @FunctionalInterface
    private interface RemoteSessionTask {
        void run(IMediaSession iMediaSession, int i) throws RemoteException;
    }

    public MediaBrowserCompat getBrowserCompat() {
        return null;
    }

    MediaControllerImplBase(Context context, MediaController mediaController, SessionToken sessionToken, Bundle bundle) {
        boolean z;
        this.mInstance = mediaController;
        if (context == null) {
            throw new NullPointerException("context shouldn't be null");
        } else if (sessionToken != null) {
            this.mContext = context;
            this.mSequencedFutureManager = new SequencedFutureManager();
            this.mControllerStub = new MediaControllerStub(this, this.mSequencedFutureManager);
            this.mToken = sessionToken;
            this.mDeathRecipient = new IBinder.DeathRecipient() {
                public void binderDied() {
                    MediaControllerImplBase.this.mInstance.close();
                }
            };
            if (this.mToken.getType() == 0) {
                this.mServiceConnection = null;
                z = requestConnectToSession(bundle);
            } else {
                this.mServiceConnection = new SessionServiceConnection(bundle);
                z = requestConnectToService();
            }
            if (!z) {
                this.mInstance.close();
            }
        } else {
            throw new NullPointerException("token shouldn't be null");
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0040, code lost:
        if (r1 == null) goto L_0x0057;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0042, code lost:
        r0 = r5.mSequencedFutureManager.obtainNextSequenceNumber();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:?, code lost:
        r1.asBinder().unlinkToDeath(r5.mDeathRecipient, 0);
        r1.release(r5.mControllerStub, r0);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void close() {
        /*
            r5 = this;
            boolean r0 = DEBUG
            if (r0 == 0) goto L_0x001c
            java.lang.String r0 = "MC2ImplBase"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "release from "
            r1.append(r2)
            androidx.media2.session.SessionToken r2 = r5.mToken
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            android.util.Log.d(r0, r1)
        L_0x001c:
            java.lang.Object r0 = r5.mLock
            monitor-enter(r0)
            androidx.media2.session.IMediaSession r1 = r5.mISession     // Catch:{ all -> 0x0067 }
            boolean r2 = r5.mIsReleased     // Catch:{ all -> 0x0067 }
            if (r2 == 0) goto L_0x0027
            monitor-exit(r0)     // Catch:{ all -> 0x0067 }
            return
        L_0x0027:
            r2 = 1
            r5.mIsReleased = r2     // Catch:{ all -> 0x0067 }
            androidx.media2.session.MediaControllerImplBase$SessionServiceConnection r2 = r5.mServiceConnection     // Catch:{ all -> 0x0067 }
            r3 = 0
            if (r2 == 0) goto L_0x0038
            android.content.Context r2 = r5.mContext     // Catch:{ all -> 0x0067 }
            androidx.media2.session.MediaControllerImplBase$SessionServiceConnection r4 = r5.mServiceConnection     // Catch:{ all -> 0x0067 }
            r2.unbindService(r4)     // Catch:{ all -> 0x0067 }
            r5.mServiceConnection = r3     // Catch:{ all -> 0x0067 }
        L_0x0038:
            r5.mISession = r3     // Catch:{ all -> 0x0067 }
            androidx.media2.session.MediaControllerStub r2 = r5.mControllerStub     // Catch:{ all -> 0x0067 }
            r2.destroy()     // Catch:{ all -> 0x0067 }
            monitor-exit(r0)     // Catch:{ all -> 0x0067 }
            if (r1 == 0) goto L_0x0057
            androidx.media2.session.SequencedFutureManager r0 = r5.mSequencedFutureManager
            int r0 = r0.obtainNextSequenceNumber()
            android.os.IBinder r2 = r1.asBinder()     // Catch:{ RemoteException -> 0x0057 }
            android.os.IBinder$DeathRecipient r3 = r5.mDeathRecipient     // Catch:{ RemoteException -> 0x0057 }
            r4 = 0
            r2.unlinkToDeath(r3, r4)     // Catch:{ RemoteException -> 0x0057 }
            androidx.media2.session.MediaControllerStub r2 = r5.mControllerStub     // Catch:{ RemoteException -> 0x0057 }
            r1.release(r2, r0)     // Catch:{ RemoteException -> 0x0057 }
        L_0x0057:
            androidx.media2.session.SequencedFutureManager r0 = r5.mSequencedFutureManager
            r0.close()
            androidx.media2.session.MediaController r0 = r5.mInstance
            androidx.media2.session.MediaControllerImplBase$2 r1 = new androidx.media2.session.MediaControllerImplBase$2
            r1.<init>()
            r0.notifyControllerCallback(r1)
            return
        L_0x0067:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0067 }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media2.session.MediaControllerImplBase.close():void");
    }

    public SessionToken getConnectedToken() {
        SessionToken sessionToken;
        synchronized (this.mLock) {
            sessionToken = isConnected() ? this.mConnectedToken : null;
        }
        return sessionToken;
    }

    public boolean isConnected() {
        boolean z;
        synchronized (this.mLock) {
            z = this.mISession != null;
        }
        return z;
    }

    private ListenableFuture<SessionResult> dispatchRemoteSessionTask(int i, RemoteSessionTask remoteSessionTask) {
        return dispatchRemoteSessionTaskInternal(i, (SessionCommand) null, remoteSessionTask);
    }

    private ListenableFuture<SessionResult> dispatchRemoteSessionTask(SessionCommand sessionCommand, RemoteSessionTask remoteSessionTask) {
        return dispatchRemoteSessionTaskInternal(0, sessionCommand, remoteSessionTask);
    }

    private ListenableFuture<SessionResult> dispatchRemoteSessionTaskInternal(int i, SessionCommand sessionCommand, RemoteSessionTask remoteSessionTask) {
        IMediaSession iMediaSession;
        if (sessionCommand != null) {
            iMediaSession = getSessionInterfaceIfAble(sessionCommand);
        } else {
            iMediaSession = getSessionInterfaceIfAble(i);
        }
        if (iMediaSession == null) {
            return SessionResult.createFutureWithResult(-4);
        }
        SequencedFutureManager.SequencedFuture createSequencedFuture = this.mSequencedFutureManager.createSequencedFuture(RESULT_WHEN_CLOSED);
        try {
            remoteSessionTask.run(iMediaSession, createSequencedFuture.getSequenceNumber());
        } catch (RemoteException e) {
            Log.w(TAG, "Cannot connect to the service or the session is gone", e);
            createSequencedFuture.set(new SessionResult(-100));
        }
        return createSequencedFuture;
    }

    public ListenableFuture<SessionResult> play() {
        return dispatchRemoteSessionTask(10000, (RemoteSessionTask) new RemoteSessionTask() {
            public void run(IMediaSession iMediaSession, int i) throws RemoteException {
                iMediaSession.play(MediaControllerImplBase.this.mControllerStub, i);
            }
        });
    }

    public ListenableFuture<SessionResult> pause() {
        return dispatchRemoteSessionTask((int) SessionCommand.COMMAND_CODE_PLAYER_PAUSE, (RemoteSessionTask) new RemoteSessionTask() {
            public void run(IMediaSession iMediaSession, int i) throws RemoteException {
                iMediaSession.pause(MediaControllerImplBase.this.mControllerStub, i);
            }
        });
    }

    public ListenableFuture<SessionResult> prepare() {
        return dispatchRemoteSessionTask((int) SessionCommand.COMMAND_CODE_PLAYER_PREPARE, (RemoteSessionTask) new RemoteSessionTask() {
            public void run(IMediaSession iMediaSession, int i) throws RemoteException {
                iMediaSession.prepare(MediaControllerImplBase.this.mControllerStub, i);
            }
        });
    }

    public ListenableFuture<SessionResult> fastForward() {
        return dispatchRemoteSessionTask((int) SessionCommand.COMMAND_CODE_SESSION_FAST_FORWARD, (RemoteSessionTask) new RemoteSessionTask() {
            public void run(IMediaSession iMediaSession, int i) throws RemoteException {
                iMediaSession.fastForward(MediaControllerImplBase.this.mControllerStub, i);
            }
        });
    }

    public ListenableFuture<SessionResult> rewind() {
        return dispatchRemoteSessionTask((int) SessionCommand.COMMAND_CODE_SESSION_REWIND, (RemoteSessionTask) new RemoteSessionTask() {
            public void run(IMediaSession iMediaSession, int i) throws RemoteException {
                iMediaSession.rewind(MediaControllerImplBase.this.mControllerStub, i);
            }
        });
    }

    public ListenableFuture<SessionResult> skipForward() {
        return dispatchRemoteSessionTask((int) SessionCommand.COMMAND_CODE_SESSION_SKIP_FORWARD, (RemoteSessionTask) new RemoteSessionTask() {
            public void run(IMediaSession iMediaSession, int i) throws RemoteException {
                iMediaSession.skipForward(MediaControllerImplBase.this.mControllerStub, i);
            }
        });
    }

    public ListenableFuture<SessionResult> skipBackward() {
        return dispatchRemoteSessionTask((int) SessionCommand.COMMAND_CODE_SESSION_SKIP_BACKWARD, (RemoteSessionTask) new RemoteSessionTask() {
            public void run(IMediaSession iMediaSession, int i) throws RemoteException {
                iMediaSession.skipBackward(MediaControllerImplBase.this.mControllerStub, i);
            }
        });
    }

    public ListenableFuture<SessionResult> seekTo(final long j) {
        if (j >= 0) {
            return dispatchRemoteSessionTask((int) SessionCommand.COMMAND_CODE_PLAYER_SEEK_TO, (RemoteSessionTask) new RemoteSessionTask() {
                public void run(IMediaSession iMediaSession, int i) throws RemoteException {
                    iMediaSession.seekTo(MediaControllerImplBase.this.mControllerStub, i, j);
                }
            });
        }
        throw new IllegalArgumentException("position shouldn't be negative");
    }

    public ListenableFuture<SessionResult> playFromMediaId(final String str, final Bundle bundle) {
        return dispatchRemoteSessionTask((int) SessionCommand.COMMAND_CODE_SESSION_PLAY_FROM_MEDIA_ID, (RemoteSessionTask) new RemoteSessionTask() {
            public void run(IMediaSession iMediaSession, int i) throws RemoteException {
                iMediaSession.playFromMediaId(MediaControllerImplBase.this.mControllerStub, i, str, bundle);
            }
        });
    }

    public ListenableFuture<SessionResult> playFromSearch(final String str, final Bundle bundle) {
        return dispatchRemoteSessionTask((int) SessionCommand.COMMAND_CODE_SESSION_PLAY_FROM_SEARCH, (RemoteSessionTask) new RemoteSessionTask() {
            public void run(IMediaSession iMediaSession, int i) throws RemoteException {
                iMediaSession.playFromSearch(MediaControllerImplBase.this.mControllerStub, i, str, bundle);
            }
        });
    }

    public ListenableFuture<SessionResult> playFromUri(final Uri uri, final Bundle bundle) {
        return dispatchRemoteSessionTask((int) SessionCommand.COMMAND_CODE_SESSION_PLAY_FROM_URI, (RemoteSessionTask) new RemoteSessionTask() {
            public void run(IMediaSession iMediaSession, int i) throws RemoteException {
                iMediaSession.playFromUri(MediaControllerImplBase.this.mControllerStub, i, uri, bundle);
            }
        });
    }

    public ListenableFuture<SessionResult> prepareFromMediaId(final String str, final Bundle bundle) {
        return dispatchRemoteSessionTask((int) SessionCommand.COMMAND_CODE_SESSION_PREPARE_FROM_MEDIA_ID, (RemoteSessionTask) new RemoteSessionTask() {
            public void run(IMediaSession iMediaSession, int i) throws RemoteException {
                iMediaSession.prepareFromMediaId(MediaControllerImplBase.this.mControllerStub, i, str, bundle);
            }
        });
    }

    public ListenableFuture<SessionResult> prepareFromSearch(final String str, final Bundle bundle) {
        return dispatchRemoteSessionTask((int) SessionCommand.COMMAND_CODE_SESSION_PREPARE_FROM_SEARCH, (RemoteSessionTask) new RemoteSessionTask() {
            public void run(IMediaSession iMediaSession, int i) throws RemoteException {
                iMediaSession.prepareFromSearch(MediaControllerImplBase.this.mControllerStub, i, str, bundle);
            }
        });
    }

    public ListenableFuture<SessionResult> prepareFromUri(final Uri uri, final Bundle bundle) {
        return dispatchRemoteSessionTask((int) SessionCommand.COMMAND_CODE_SESSION_PREPARE_FROM_URI, (RemoteSessionTask) new RemoteSessionTask() {
            public void run(IMediaSession iMediaSession, int i) throws RemoteException {
                iMediaSession.prepareFromUri(MediaControllerImplBase.this.mControllerStub, i, uri, bundle);
            }
        });
    }

    public ListenableFuture<SessionResult> setVolumeTo(final int i, final int i2) {
        return dispatchRemoteSessionTask(30000, (RemoteSessionTask) new RemoteSessionTask() {
            public void run(IMediaSession iMediaSession, int i) throws RemoteException {
                iMediaSession.setVolumeTo(MediaControllerImplBase.this.mControllerStub, i, i, i2);
            }
        });
    }

    public ListenableFuture<SessionResult> adjustVolume(final int i, final int i2) {
        return dispatchRemoteSessionTask((int) SessionCommand.COMMAND_CODE_VOLUME_ADJUST_VOLUME, (RemoteSessionTask) new RemoteSessionTask() {
            public void run(IMediaSession iMediaSession, int i) throws RemoteException {
                iMediaSession.adjustVolume(MediaControllerImplBase.this.mControllerStub, i, i, i2);
            }
        });
    }

    public PendingIntent getSessionActivity() {
        PendingIntent pendingIntent;
        synchronized (this.mLock) {
            pendingIntent = this.mSessionActivity;
        }
        return pendingIntent;
    }

    public int getPlayerState() {
        int i;
        synchronized (this.mLock) {
            i = this.mPlayerState;
        }
        return i;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0022, code lost:
        return Long.MIN_VALUE;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public long getDuration() {
        /*
            r3 = this;
            java.lang.Object r0 = r3.mLock
            monitor-enter(r0)
            androidx.media2.common.MediaItem r1 = r3.mCurrentMediaItem     // Catch:{ all -> 0x0025 }
            if (r1 != 0) goto L_0x0009
            r1 = 0
            goto L_0x000f
        L_0x0009:
            androidx.media2.common.MediaItem r1 = r3.mCurrentMediaItem     // Catch:{ all -> 0x0025 }
            androidx.media2.common.MediaMetadata r1 = r1.getMetadata()     // Catch:{ all -> 0x0025 }
        L_0x000f:
            if (r1 == 0) goto L_0x0021
            java.lang.String r2 = "android.media.metadata.DURATION"
            boolean r2 = r1.containsKey(r2)     // Catch:{ all -> 0x0025 }
            if (r2 == 0) goto L_0x0021
            java.lang.String r2 = "android.media.metadata.DURATION"
            long r1 = r1.getLong(r2)     // Catch:{ all -> 0x0025 }
            monitor-exit(r0)     // Catch:{ all -> 0x0025 }
            return r1
        L_0x0021:
            monitor-exit(r0)     // Catch:{ all -> 0x0025 }
            r0 = -9223372036854775808
            return r0
        L_0x0025:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0025 }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media2.session.MediaControllerImplBase.getDuration():long");
    }

    public long getCurrentPosition() {
        long j;
        synchronized (this.mLock) {
            if (this.mISession == null) {
                Log.w(TAG, "Session isn't active", new IllegalStateException());
                return Long.MIN_VALUE;
            } else if (this.mPlayerState != 2 || this.mBufferingState == 2) {
                long j2 = this.mPositionMs;
                return j2;
            } else {
                if (this.mInstance.mTimeDiff != null) {
                    j = this.mInstance.mTimeDiff.longValue();
                } else {
                    j = SystemClock.elapsedRealtime() - this.mPositionEventTimeMs;
                }
                long max = Math.max(0, this.mPositionMs + ((long) (this.mPlaybackSpeed * ((float) j))));
                return max;
            }
        }
    }

    public float getPlaybackSpeed() {
        synchronized (this.mLock) {
            if (this.mISession == null) {
                Log.w(TAG, "Session isn't active", new IllegalStateException());
                return 0.0f;
            }
            float f = this.mPlaybackSpeed;
            return f;
        }
    }

    public ListenableFuture<SessionResult> setPlaybackSpeed(final float f) {
        return dispatchRemoteSessionTask((int) SessionCommand.COMMAND_CODE_PLAYER_SET_SPEED, (RemoteSessionTask) new RemoteSessionTask() {
            public void run(IMediaSession iMediaSession, int i) throws RemoteException {
                iMediaSession.setPlaybackSpeed(MediaControllerImplBase.this.mControllerStub, i, f);
            }
        });
    }

    public int getBufferingState() {
        synchronized (this.mLock) {
            if (this.mISession == null) {
                Log.w(TAG, "Session isn't active", new IllegalStateException());
                return 0;
            }
            int i = this.mBufferingState;
            return i;
        }
    }

    public long getBufferedPosition() {
        synchronized (this.mLock) {
            if (this.mISession == null) {
                Log.w(TAG, "Session isn't active", new IllegalStateException());
                return Long.MIN_VALUE;
            }
            long j = this.mBufferedPositionMs;
            return j;
        }
    }

    public MediaController.PlaybackInfo getPlaybackInfo() {
        MediaController.PlaybackInfo playbackInfo;
        synchronized (this.mLock) {
            playbackInfo = this.mPlaybackInfo;
        }
        return playbackInfo;
    }

    public ListenableFuture<SessionResult> setRating(final String str, final Rating rating) {
        return dispatchRemoteSessionTask((int) SessionCommand.COMMAND_CODE_SESSION_SET_RATING, (RemoteSessionTask) new RemoteSessionTask() {
            public void run(IMediaSession iMediaSession, int i) throws RemoteException {
                iMediaSession.setRating(MediaControllerImplBase.this.mControllerStub, i, str, MediaParcelUtils.toParcelable(rating));
            }
        });
    }

    public ListenableFuture<SessionResult> sendCustomCommand(final SessionCommand sessionCommand, final Bundle bundle) {
        return dispatchRemoteSessionTask(sessionCommand, (RemoteSessionTask) new RemoteSessionTask() {
            public void run(IMediaSession iMediaSession, int i) throws RemoteException {
                iMediaSession.onCustomCommand(MediaControllerImplBase.this.mControllerStub, i, MediaParcelUtils.toParcelable(sessionCommand), bundle);
            }
        });
    }

    public List<MediaItem> getPlaylist() {
        List<MediaItem> list;
        synchronized (this.mLock) {
            list = this.mPlaylist;
        }
        return list;
    }

    public ListenableFuture<SessionResult> setPlaylist(final List<String> list, final MediaMetadata mediaMetadata) {
        return dispatchRemoteSessionTask((int) SessionCommand.COMMAND_CODE_PLAYER_SET_PLAYLIST, (RemoteSessionTask) new RemoteSessionTask() {
            public void run(IMediaSession iMediaSession, int i) throws RemoteException {
                iMediaSession.setPlaylist(MediaControllerImplBase.this.mControllerStub, i, list, MediaParcelUtils.toParcelable(mediaMetadata));
            }
        });
    }

    public ListenableFuture<SessionResult> setMediaItem(final String str) {
        return dispatchRemoteSessionTask((int) SessionCommand.COMMAND_CODE_PLAYER_SET_MEDIA_ITEM, (RemoteSessionTask) new RemoteSessionTask() {
            public void run(IMediaSession iMediaSession, int i) throws RemoteException {
                iMediaSession.setMediaItem(MediaControllerImplBase.this.mControllerStub, i, str);
            }
        });
    }

    public ListenableFuture<SessionResult> updatePlaylistMetadata(final MediaMetadata mediaMetadata) {
        return dispatchRemoteSessionTask((int) SessionCommand.COMMAND_CODE_PLAYER_UPDATE_LIST_METADATA, (RemoteSessionTask) new RemoteSessionTask() {
            public void run(IMediaSession iMediaSession, int i) throws RemoteException {
                iMediaSession.updatePlaylistMetadata(MediaControllerImplBase.this.mControllerStub, i, MediaParcelUtils.toParcelable(mediaMetadata));
            }
        });
    }

    public MediaMetadata getPlaylistMetadata() {
        MediaMetadata mediaMetadata;
        synchronized (this.mLock) {
            mediaMetadata = this.mPlaylistMetadata;
        }
        return mediaMetadata;
    }

    public ListenableFuture<SessionResult> addPlaylistItem(final int i, final String str) {
        return dispatchRemoteSessionTask((int) SessionCommand.COMMAND_CODE_PLAYER_ADD_PLAYLIST_ITEM, (RemoteSessionTask) new RemoteSessionTask() {
            public void run(IMediaSession iMediaSession, int i) throws RemoteException {
                iMediaSession.addPlaylistItem(MediaControllerImplBase.this.mControllerStub, i, i, str);
            }
        });
    }

    public ListenableFuture<SessionResult> removePlaylistItem(final int i) {
        return dispatchRemoteSessionTask((int) SessionCommand.COMMAND_CODE_PLAYER_REMOVE_PLAYLIST_ITEM, (RemoteSessionTask) new RemoteSessionTask() {
            public void run(IMediaSession iMediaSession, int i) throws RemoteException {
                iMediaSession.removePlaylistItem(MediaControllerImplBase.this.mControllerStub, i, i);
            }
        });
    }

    public ListenableFuture<SessionResult> replacePlaylistItem(final int i, final String str) {
        return dispatchRemoteSessionTask((int) SessionCommand.COMMAND_CODE_PLAYER_REPLACE_PLAYLIST_ITEM, (RemoteSessionTask) new RemoteSessionTask() {
            public void run(IMediaSession iMediaSession, int i) throws RemoteException {
                iMediaSession.replacePlaylistItem(MediaControllerImplBase.this.mControllerStub, i, i, str);
            }
        });
    }

    public MediaItem getCurrentMediaItem() {
        MediaItem mediaItem;
        synchronized (this.mLock) {
            mediaItem = this.mCurrentMediaItem;
        }
        return mediaItem;
    }

    public int getCurrentMediaItemIndex() {
        int i;
        synchronized (this.mLock) {
            i = this.mCurrentMediaItemIndex;
        }
        return i;
    }

    public int getPreviousMediaItemIndex() {
        int i;
        synchronized (this.mLock) {
            i = this.mPreviousMediaItemIndex;
        }
        return i;
    }

    public int getNextMediaItemIndex() {
        int i;
        synchronized (this.mLock) {
            i = this.mNextMediaItemIndex;
        }
        return i;
    }

    public ListenableFuture<SessionResult> skipToPreviousItem() {
        return dispatchRemoteSessionTask((int) SessionCommand.COMMAND_CODE_PLAYER_SKIP_TO_PREVIOUS_PLAYLIST_ITEM, (RemoteSessionTask) new RemoteSessionTask() {
            public void run(IMediaSession iMediaSession, int i) throws RemoteException {
                iMediaSession.skipToPreviousItem(MediaControllerImplBase.this.mControllerStub, i);
            }
        });
    }

    public ListenableFuture<SessionResult> skipToNextItem() {
        return dispatchRemoteSessionTask((int) SessionCommand.COMMAND_CODE_PLAYER_SKIP_TO_NEXT_PLAYLIST_ITEM, (RemoteSessionTask) new RemoteSessionTask() {
            public void run(IMediaSession iMediaSession, int i) throws RemoteException {
                iMediaSession.skipToNextItem(MediaControllerImplBase.this.mControllerStub, i);
            }
        });
    }

    public ListenableFuture<SessionResult> skipToPlaylistItem(final int i) {
        return dispatchRemoteSessionTask((int) SessionCommand.COMMAND_CODE_PLAYER_SKIP_TO_PLAYLIST_ITEM, (RemoteSessionTask) new RemoteSessionTask() {
            public void run(IMediaSession iMediaSession, int i) throws RemoteException {
                iMediaSession.skipToPlaylistItem(MediaControllerImplBase.this.mControllerStub, i, i);
            }
        });
    }

    public int getRepeatMode() {
        int i;
        synchronized (this.mLock) {
            i = this.mRepeatMode;
        }
        return i;
    }

    public ListenableFuture<SessionResult> setRepeatMode(final int i) {
        return dispatchRemoteSessionTask((int) SessionCommand.COMMAND_CODE_PLAYER_SET_REPEAT_MODE, (RemoteSessionTask) new RemoteSessionTask() {
            public void run(IMediaSession iMediaSession, int i) throws RemoteException {
                iMediaSession.setRepeatMode(MediaControllerImplBase.this.mControllerStub, i, i);
            }
        });
    }

    public int getShuffleMode() {
        int i;
        synchronized (this.mLock) {
            i = this.mShuffleMode;
        }
        return i;
    }

    public ListenableFuture<SessionResult> setShuffleMode(final int i) {
        return dispatchRemoteSessionTask((int) SessionCommand.COMMAND_CODE_PLAYER_SET_SHUFFLE_MODE, (RemoteSessionTask) new RemoteSessionTask() {
            public void run(IMediaSession iMediaSession, int i) throws RemoteException {
                iMediaSession.setShuffleMode(MediaControllerImplBase.this.mControllerStub, i, i);
            }
        });
    }

    public List<SessionPlayer.TrackInfo> getTrackInfo() {
        List<SessionPlayer.TrackInfo> list;
        synchronized (this.mLock) {
            list = this.mTrackInfos;
        }
        return list;
    }

    public ListenableFuture<SessionResult> selectTrack(final SessionPlayer.TrackInfo trackInfo) {
        return dispatchRemoteSessionTask((int) SessionCommand.COMMAND_CODE_PLAYER_SELECT_TRACK, (RemoteSessionTask) new RemoteSessionTask() {
            public void run(IMediaSession iMediaSession, int i) throws RemoteException {
                iMediaSession.selectTrack(MediaControllerImplBase.this.mControllerStub, i, MediaParcelUtils.toParcelable(trackInfo));
            }
        });
    }

    public ListenableFuture<SessionResult> deselectTrack(final SessionPlayer.TrackInfo trackInfo) {
        return dispatchRemoteSessionTask((int) SessionCommand.COMMAND_CODE_PLAYER_DESELECT_TRACK, (RemoteSessionTask) new RemoteSessionTask() {
            public void run(IMediaSession iMediaSession, int i) throws RemoteException {
                iMediaSession.deselectTrack(MediaControllerImplBase.this.mControllerStub, i, MediaParcelUtils.toParcelable(trackInfo));
            }
        });
    }

    public SessionPlayer.TrackInfo getSelectedTrack(int i) {
        SessionPlayer.TrackInfo trackInfo;
        synchronized (this.mLock) {
            trackInfo = this.mSelectedTracks.get(i);
        }
        return trackInfo;
    }

    public VideoSize getVideoSize() {
        VideoSize videoSize;
        synchronized (this.mLock) {
            videoSize = this.mVideoSize;
        }
        return videoSize;
    }

    public ListenableFuture<SessionResult> setSurface(final Surface surface) {
        return dispatchRemoteSessionTask((int) SessionCommand.COMMAND_CODE_PLAYER_SET_SURFACE, (RemoteSessionTask) new RemoteSessionTask() {
            public void run(IMediaSession iMediaSession, int i) throws RemoteException {
                iMediaSession.setSurface(MediaControllerImplBase.this.mControllerStub, i, surface);
            }
        });
    }

    public SessionCommandGroup getAllowedCommands() {
        synchronized (this.mLock) {
            if (this.mISession == null) {
                Log.w(TAG, "Session isn't active", new IllegalStateException());
                return null;
            }
            SessionCommandGroup sessionCommandGroup = this.mAllowedCommands;
            return sessionCommandGroup;
        }
    }

    public Context getContext() {
        return this.mContext;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0047, code lost:
        if (DEBUG == false) goto L_0x0066;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0049, code lost:
        android.util.Log.d(TAG, "bind to " + r5.mToken + " succeeded");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0066, code lost:
        return true;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean requestConnectToService() {
        /*
            r5 = this;
            android.content.Intent r0 = new android.content.Intent
            java.lang.String r1 = "androidx.media2.session.MediaSessionService"
            r0.<init>(r1)
            androidx.media2.session.SessionToken r1 = r5.mToken
            java.lang.String r1 = r1.getPackageName()
            androidx.media2.session.SessionToken r2 = r5.mToken
            java.lang.String r2 = r2.getServiceName()
            r0.setClassName(r1, r2)
            java.lang.Object r1 = r5.mLock
            monitor-enter(r1)
            android.content.Context r2 = r5.mContext     // Catch:{ all -> 0x0067 }
            androidx.media2.session.MediaControllerImplBase$SessionServiceConnection r3 = r5.mServiceConnection     // Catch:{ all -> 0x0067 }
            r4 = 1
            boolean r0 = r2.bindService(r0, r3, r4)     // Catch:{ all -> 0x0067 }
            if (r0 != 0) goto L_0x0044
            java.lang.String r0 = "MC2ImplBase"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x0067 }
            r2.<init>()     // Catch:{ all -> 0x0067 }
            java.lang.String r3 = "bind to "
            r2.append(r3)     // Catch:{ all -> 0x0067 }
            androidx.media2.session.SessionToken r3 = r5.mToken     // Catch:{ all -> 0x0067 }
            r2.append(r3)     // Catch:{ all -> 0x0067 }
            java.lang.String r3 = " failed"
            r2.append(r3)     // Catch:{ all -> 0x0067 }
            java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x0067 }
            android.util.Log.w(r0, r2)     // Catch:{ all -> 0x0067 }
            r0 = 0
            monitor-exit(r1)     // Catch:{ all -> 0x0067 }
            return r0
        L_0x0044:
            monitor-exit(r1)     // Catch:{ all -> 0x0067 }
            boolean r0 = DEBUG
            if (r0 == 0) goto L_0x0066
            java.lang.String r0 = "MC2ImplBase"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "bind to "
            r1.append(r2)
            androidx.media2.session.SessionToken r2 = r5.mToken
            r1.append(r2)
            java.lang.String r2 = " succeeded"
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            android.util.Log.d(r0, r1)
        L_0x0066:
            return r4
        L_0x0067:
            r0 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x0067 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media2.session.MediaControllerImplBase.requestConnectToService():boolean");
    }

    private boolean requestConnectToSession(Bundle bundle) {
        try {
            IMediaSession.Stub.asInterface((IBinder) this.mToken.getBinder()).connect(this.mControllerStub, this.mSequencedFutureManager.obtainNextSequenceNumber(), MediaParcelUtils.toParcelable(new ConnectionRequest(this.mContext.getPackageName(), Process.myPid(), bundle)));
            return true;
        } catch (RemoteException e) {
            Log.w(TAG, "Failed to call connection request.", e);
            return false;
        }
    }

    /* access modifiers changed from: package-private */
    public IMediaSession getSessionInterfaceIfAble(int i) {
        synchronized (this.mLock) {
            if (!this.mAllowedCommands.hasCommand(i)) {
                Log.w(TAG, "Controller isn't allowed to call command, commandCode=" + i);
                return null;
            }
            IMediaSession iMediaSession = this.mISession;
            return iMediaSession;
        }
    }

    /* access modifiers changed from: package-private */
    public IMediaSession getSessionInterfaceIfAble(SessionCommand sessionCommand) {
        synchronized (this.mLock) {
            if (!this.mAllowedCommands.hasCommand(sessionCommand)) {
                Log.w(TAG, "Controller isn't allowed to call command, command=" + sessionCommand);
                return null;
            }
            IMediaSession iMediaSession = this.mISession;
            return iMediaSession;
        }
    }

    /* access modifiers changed from: package-private */
    public void notifyCurrentMediaItemChanged(final MediaItem mediaItem, int i, int i2, int i3) {
        synchronized (this.mLock) {
            this.mCurrentMediaItem = mediaItem;
            this.mCurrentMediaItemIndex = i;
            this.mPreviousMediaItemIndex = i2;
            this.mNextMediaItemIndex = i3;
            if (this.mPlaylist != null && i >= 0 && i < this.mPlaylist.size()) {
                this.mPlaylist.set(i, mediaItem);
            }
        }
        this.mInstance.notifyControllerCallback(new MediaController.ControllerCallbackRunnable() {
            public void run(MediaController.ControllerCallback controllerCallback) {
                if (MediaControllerImplBase.this.mInstance.isConnected()) {
                    controllerCallback.onCurrentMediaItemChanged(MediaControllerImplBase.this.mInstance, mediaItem);
                }
            }
        });
    }

    /* access modifiers changed from: package-private */
    public void notifyPlayerStateChanges(long j, long j2, final int i) {
        synchronized (this.mLock) {
            this.mPositionEventTimeMs = j;
            this.mPositionMs = j2;
            this.mPlayerState = i;
        }
        this.mInstance.notifyControllerCallback(new MediaController.ControllerCallbackRunnable() {
            public void run(MediaController.ControllerCallback controllerCallback) {
                if (MediaControllerImplBase.this.mInstance.isConnected()) {
                    controllerCallback.onPlayerStateChanged(MediaControllerImplBase.this.mInstance, i);
                }
            }
        });
    }

    /* access modifiers changed from: package-private */
    public void notifyPlaybackSpeedChanges(long j, long j2, final float f) {
        synchronized (this.mLock) {
            this.mPositionEventTimeMs = j;
            this.mPositionMs = j2;
            this.mPlaybackSpeed = f;
        }
        this.mInstance.notifyControllerCallback(new MediaController.ControllerCallbackRunnable() {
            public void run(MediaController.ControllerCallback controllerCallback) {
                if (MediaControllerImplBase.this.mInstance.isConnected()) {
                    controllerCallback.onPlaybackSpeedChanged(MediaControllerImplBase.this.mInstance, f);
                }
            }
        });
    }

    /* access modifiers changed from: package-private */
    public void notifyBufferingStateChanged(final MediaItem mediaItem, final int i, long j, long j2, long j3) {
        synchronized (this.mLock) {
            this.mBufferingState = i;
            this.mBufferedPositionMs = j;
            this.mPositionEventTimeMs = j2;
            this.mPositionMs = j3;
        }
        this.mInstance.notifyControllerCallback(new MediaController.ControllerCallbackRunnable() {
            public void run(MediaController.ControllerCallback controllerCallback) {
                if (MediaControllerImplBase.this.mInstance.isConnected()) {
                    controllerCallback.onBufferingStateChanged(MediaControllerImplBase.this.mInstance, mediaItem, i);
                }
            }
        });
    }

    /* access modifiers changed from: package-private */
    public void notifyPlaylistChanges(final List<MediaItem> list, final MediaMetadata mediaMetadata, int i, int i2, int i3) {
        synchronized (this.mLock) {
            this.mPlaylist = list;
            this.mPlaylistMetadata = mediaMetadata;
            this.mCurrentMediaItemIndex = i;
            this.mPreviousMediaItemIndex = i2;
            this.mNextMediaItemIndex = i3;
            if (i >= 0 && i < list.size()) {
                this.mCurrentMediaItem = list.get(i);
            }
        }
        this.mInstance.notifyControllerCallback(new MediaController.ControllerCallbackRunnable() {
            public void run(MediaController.ControllerCallback controllerCallback) {
                if (MediaControllerImplBase.this.mInstance.isConnected()) {
                    controllerCallback.onPlaylistChanged(MediaControllerImplBase.this.mInstance, list, mediaMetadata);
                }
            }
        });
    }

    /* access modifiers changed from: package-private */
    public void notifyPlaylistMetadataChanges(final MediaMetadata mediaMetadata) {
        synchronized (this.mLock) {
            this.mPlaylistMetadata = mediaMetadata;
        }
        this.mInstance.notifyControllerCallback(new MediaController.ControllerCallbackRunnable() {
            public void run(MediaController.ControllerCallback controllerCallback) {
                if (MediaControllerImplBase.this.mInstance.isConnected()) {
                    controllerCallback.onPlaylistMetadataChanged(MediaControllerImplBase.this.mInstance, mediaMetadata);
                }
            }
        });
    }

    /* access modifiers changed from: package-private */
    public void notifyPlaybackInfoChanges(final MediaController.PlaybackInfo playbackInfo) {
        synchronized (this.mLock) {
            this.mPlaybackInfo = playbackInfo;
        }
        this.mInstance.notifyControllerCallback(new MediaController.ControllerCallbackRunnable() {
            public void run(MediaController.ControllerCallback controllerCallback) {
                if (MediaControllerImplBase.this.mInstance.isConnected()) {
                    controllerCallback.onPlaybackInfoChanged(MediaControllerImplBase.this.mInstance, playbackInfo);
                }
            }
        });
    }

    /* access modifiers changed from: package-private */
    public void notifyRepeatModeChanges(final int i, int i2, int i3, int i4) {
        synchronized (this.mLock) {
            this.mRepeatMode = i;
            this.mCurrentMediaItemIndex = i2;
            this.mPreviousMediaItemIndex = i3;
            this.mNextMediaItemIndex = i4;
        }
        this.mInstance.notifyControllerCallback(new MediaController.ControllerCallbackRunnable() {
            public void run(MediaController.ControllerCallback controllerCallback) {
                if (MediaControllerImplBase.this.mInstance.isConnected()) {
                    controllerCallback.onRepeatModeChanged(MediaControllerImplBase.this.mInstance, i);
                }
            }
        });
    }

    /* access modifiers changed from: package-private */
    public void notifyShuffleModeChanges(final int i, int i2, int i3, int i4) {
        synchronized (this.mLock) {
            this.mShuffleMode = i;
            this.mCurrentMediaItemIndex = i2;
            this.mPreviousMediaItemIndex = i3;
            this.mNextMediaItemIndex = i4;
        }
        this.mInstance.notifyControllerCallback(new MediaController.ControllerCallbackRunnable() {
            public void run(MediaController.ControllerCallback controllerCallback) {
                if (MediaControllerImplBase.this.mInstance.isConnected()) {
                    controllerCallback.onShuffleModeChanged(MediaControllerImplBase.this.mInstance, i);
                }
            }
        });
    }

    /* access modifiers changed from: package-private */
    public void notifyPlaybackCompleted() {
        this.mInstance.notifyControllerCallback(new MediaController.ControllerCallbackRunnable() {
            public void run(MediaController.ControllerCallback controllerCallback) {
                if (MediaControllerImplBase.this.mInstance.isConnected()) {
                    controllerCallback.onPlaybackCompleted(MediaControllerImplBase.this.mInstance);
                }
            }
        });
    }

    /* access modifiers changed from: package-private */
    public void notifySeekCompleted(long j, long j2, final long j3) {
        synchronized (this.mLock) {
            this.mPositionEventTimeMs = j;
            this.mPositionMs = j2;
        }
        this.mInstance.notifyControllerCallback(new MediaController.ControllerCallbackRunnable() {
            public void run(MediaController.ControllerCallback controllerCallback) {
                if (MediaControllerImplBase.this.mInstance.isConnected()) {
                    controllerCallback.onSeekCompleted(MediaControllerImplBase.this.mInstance, j3);
                }
            }
        });
    }

    /* access modifiers changed from: package-private */
    public void notifyVideoSizeChanged(final MediaItem mediaItem, final VideoSize videoSize) {
        synchronized (this.mLock) {
            this.mVideoSize = videoSize;
        }
        this.mInstance.notifyControllerCallback(new MediaController.ControllerCallbackRunnable() {
            public void run(MediaController.ControllerCallback controllerCallback) {
                if (MediaControllerImplBase.this.mInstance.isConnected()) {
                    controllerCallback.onVideoSizeChanged(MediaControllerImplBase.this.mInstance, mediaItem, videoSize);
                }
            }
        });
    }

    /* access modifiers changed from: package-private */
    public void notifyTrackInfoChanged(int i, final List<SessionPlayer.TrackInfo> list, SessionPlayer.TrackInfo trackInfo, SessionPlayer.TrackInfo trackInfo2, SessionPlayer.TrackInfo trackInfo3, SessionPlayer.TrackInfo trackInfo4) {
        synchronized (this.mLock) {
            this.mTrackInfos = list;
            this.mSelectedTracks.put(1, trackInfo);
            this.mSelectedTracks.put(2, trackInfo2);
            this.mSelectedTracks.put(4, trackInfo3);
            this.mSelectedTracks.put(5, trackInfo4);
        }
        this.mInstance.notifyControllerCallback(new MediaController.ControllerCallbackRunnable() {
            public void run(MediaController.ControllerCallback controllerCallback) {
                if (MediaControllerImplBase.this.mInstance.isConnected()) {
                    controllerCallback.onTrackInfoChanged(MediaControllerImplBase.this.mInstance, list);
                }
            }
        });
    }

    /* access modifiers changed from: package-private */
    public void notifyTrackSelected(int i, final SessionPlayer.TrackInfo trackInfo) {
        synchronized (this.mLock) {
            this.mSelectedTracks.put(trackInfo.getTrackType(), trackInfo);
        }
        this.mInstance.notifyControllerCallback(new MediaController.ControllerCallbackRunnable() {
            public void run(MediaController.ControllerCallback controllerCallback) {
                if (MediaControllerImplBase.this.mInstance.isConnected()) {
                    controllerCallback.onTrackSelected(MediaControllerImplBase.this.mInstance, trackInfo);
                }
            }
        });
    }

    /* access modifiers changed from: package-private */
    public void notifyTrackDeselected(int i, final SessionPlayer.TrackInfo trackInfo) {
        synchronized (this.mLock) {
            this.mSelectedTracks.remove(trackInfo.getTrackType());
        }
        this.mInstance.notifyControllerCallback(new MediaController.ControllerCallbackRunnable() {
            public void run(MediaController.ControllerCallback controllerCallback) {
                if (MediaControllerImplBase.this.mInstance.isConnected()) {
                    controllerCallback.onTrackDeselected(MediaControllerImplBase.this.mInstance, trackInfo);
                }
            }
        });
    }

    /* access modifiers changed from: package-private */
    public void notifySubtitleData(final MediaItem mediaItem, final SessionPlayer.TrackInfo trackInfo, final SubtitleData subtitleData) {
        this.mInstance.notifyControllerCallback(new MediaController.ControllerCallbackRunnable() {
            public void run(MediaController.ControllerCallback controllerCallback) {
                if (MediaControllerImplBase.this.mInstance.isConnected()) {
                    controllerCallback.onSubtitleData(MediaControllerImplBase.this.mInstance, mediaItem, trackInfo, subtitleData);
                }
            }
        });
    }

    /* access modifiers changed from: package-private */
    public void onConnectedNotLocked(IMediaSession iMediaSession, final SessionCommandGroup sessionCommandGroup, int i, MediaItem mediaItem, long j, long j2, float f, long j3, MediaController.PlaybackInfo playbackInfo, int i2, int i3, List<MediaItem> list, PendingIntent pendingIntent, int i4, int i5, int i6, Bundle bundle, VideoSize videoSize, List<SessionPlayer.TrackInfo> list2, SessionPlayer.TrackInfo trackInfo, SessionPlayer.TrackInfo trackInfo2, SessionPlayer.TrackInfo trackInfo3, SessionPlayer.TrackInfo trackInfo4) {
        IMediaSession iMediaSession2 = iMediaSession;
        SessionCommandGroup sessionCommandGroup2 = sessionCommandGroup;
        if (DEBUG) {
            Log.d(TAG, "onConnectedNotLocked sessionBinder=" + iMediaSession + ", allowedCommands=" + sessionCommandGroup);
        }
        if (iMediaSession2 == null || sessionCommandGroup2 == null) {
            this.mInstance.close();
            return;
        }
        boolean z = false;
        try {
            synchronized (this.mLock) {
                try {
                    if (!this.mIsReleased) {
                        if (this.mISession != null) {
                            Log.e(TAG, "Cannot be notified about the connection result many times. Probably a bug or malicious app.");
                            try {
                                this.mInstance.close();
                            } catch (Throwable th) {
                                th = th;
                                z = true;
                                throw th;
                            }
                        } else {
                            this.mAllowedCommands = sessionCommandGroup2;
                            this.mPlayerState = i;
                            this.mCurrentMediaItem = mediaItem;
                            this.mPositionEventTimeMs = j;
                            this.mPositionMs = j2;
                            this.mPlaybackSpeed = f;
                            this.mBufferedPositionMs = j3;
                            this.mPlaybackInfo = playbackInfo;
                            this.mRepeatMode = i2;
                            this.mShuffleMode = i3;
                            this.mPlaylist = list;
                            this.mSessionActivity = pendingIntent;
                            this.mISession = iMediaSession2;
                            this.mCurrentMediaItemIndex = i4;
                            this.mPreviousMediaItemIndex = i5;
                            this.mNextMediaItemIndex = i6;
                            this.mVideoSize = videoSize;
                            this.mTrackInfos = list2;
                            this.mSelectedTracks.put(1, trackInfo);
                            this.mSelectedTracks.put(2, trackInfo2);
                            this.mSelectedTracks.put(4, trackInfo3);
                            this.mSelectedTracks.put(5, trackInfo4);
                            this.mISession.asBinder().linkToDeath(this.mDeathRecipient, 0);
                            this.mConnectedToken = new SessionToken(new SessionTokenImplBase(this.mToken.getUid(), 0, this.mToken.getPackageName(), iMediaSession, bundle));
                            this.mInstance.notifyControllerCallback(new MediaController.ControllerCallbackRunnable() {
                                public void run(MediaController.ControllerCallback controllerCallback) {
                                    controllerCallback.onConnected(MediaControllerImplBase.this.mInstance, sessionCommandGroup);
                                }
                            });
                        }
                    }
                } catch (RemoteException e) {
                    if (DEBUG) {
                        Log.d(TAG, "Session died too early.", e);
                    }
                    this.mInstance.close();
                } catch (Throwable th2) {
                    th = th2;
                    throw th;
                }
            }
        } catch (Throwable th3) {
            if (z) {
                this.mInstance.close();
            }
            throw th3;
        }
    }

    /* access modifiers changed from: package-private */
    public void sendControllerResult(int i, SessionResult sessionResult) {
        IMediaSession iMediaSession;
        synchronized (this.mLock) {
            iMediaSession = this.mISession;
        }
        if (iMediaSession != null) {
            try {
                iMediaSession.onControllerResult(this.mControllerStub, i, MediaParcelUtils.toParcelable(sessionResult));
            } catch (RemoteException unused) {
                Log.w(TAG, "Error in sending");
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void onCustomCommand(final int i, final SessionCommand sessionCommand, final Bundle bundle) {
        if (DEBUG) {
            Log.d(TAG, "onCustomCommand cmd=" + sessionCommand.getCustomAction());
        }
        this.mInstance.notifyControllerCallback(new MediaController.ControllerCallbackRunnable() {
            public void run(MediaController.ControllerCallback controllerCallback) {
                SessionResult onCustomCommand = controllerCallback.onCustomCommand(MediaControllerImplBase.this.mInstance, sessionCommand, bundle);
                if (onCustomCommand != null) {
                    MediaControllerImplBase.this.sendControllerResult(i, onCustomCommand);
                    return;
                }
                throw new RuntimeException("ControllerCallback#onCustomCommand() has returned null, command=" + sessionCommand.getCustomAction());
            }
        });
    }

    /* access modifiers changed from: package-private */
    public void onAllowedCommandsChanged(final SessionCommandGroup sessionCommandGroup) {
        synchronized (this.mLock) {
            this.mAllowedCommands = sessionCommandGroup;
        }
        this.mInstance.notifyControllerCallback(new MediaController.ControllerCallbackRunnable() {
            public void run(MediaController.ControllerCallback controllerCallback) {
                controllerCallback.onAllowedCommandsChanged(MediaControllerImplBase.this.mInstance, sessionCommandGroup);
            }
        });
    }

    /* access modifiers changed from: package-private */
    public void onSetCustomLayout(final int i, final List<MediaSession.CommandButton> list) {
        this.mInstance.notifyControllerCallback(new MediaController.ControllerCallbackRunnable() {
            public void run(MediaController.ControllerCallback controllerCallback) {
                MediaControllerImplBase.this.sendControllerResult(i, new SessionResult(controllerCallback.onSetCustomLayout(MediaControllerImplBase.this.mInstance, list)));
            }
        });
    }

    private class SessionServiceConnection implements ServiceConnection {
        private final Bundle mConnectionHints;

        SessionServiceConnection(Bundle bundle) {
            this.mConnectionHints = bundle;
        }

        /* JADX WARNING: Can't wrap try/catch for region: R(6:16|17|19|20|21|24) */
        /* JADX WARNING: Code restructure failed: missing block: B:18:0x0094, code lost:
            r6 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:22:0x00b7, code lost:
            r5.this$0.mInstance.close();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:23:0x00be, code lost:
            throw r6;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:25:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x0096 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void onServiceConnected(android.content.ComponentName r6, android.os.IBinder r7) {
            /*
                r5 = this;
                java.lang.String r0 = "MC2ImplBase"
                boolean r1 = androidx.media2.session.MediaControllerImplBase.DEBUG     // Catch:{ RemoteException -> 0x0096 }
                if (r1 == 0) goto L_0x0022
                java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ RemoteException -> 0x0096 }
                r1.<init>()     // Catch:{ RemoteException -> 0x0096 }
                java.lang.String r2 = "onServiceConnected "
                r1.append(r2)     // Catch:{ RemoteException -> 0x0096 }
                r1.append(r6)     // Catch:{ RemoteException -> 0x0096 }
                java.lang.String r2 = " "
                r1.append(r2)     // Catch:{ RemoteException -> 0x0096 }
                r1.append(r5)     // Catch:{ RemoteException -> 0x0096 }
                java.lang.String r1 = r1.toString()     // Catch:{ RemoteException -> 0x0096 }
                android.util.Log.d(r0, r1)     // Catch:{ RemoteException -> 0x0096 }
            L_0x0022:
                androidx.media2.session.MediaControllerImplBase r1 = androidx.media2.session.MediaControllerImplBase.this     // Catch:{ RemoteException -> 0x0096 }
                androidx.media2.session.SessionToken r1 = r1.mToken     // Catch:{ RemoteException -> 0x0096 }
                java.lang.String r1 = r1.getPackageName()     // Catch:{ RemoteException -> 0x0096 }
                java.lang.String r2 = r6.getPackageName()     // Catch:{ RemoteException -> 0x0096 }
                boolean r1 = r1.equals(r2)     // Catch:{ RemoteException -> 0x0096 }
                if (r1 != 0) goto L_0x0060
                java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ RemoteException -> 0x0096 }
                r7.<init>()     // Catch:{ RemoteException -> 0x0096 }
                java.lang.String r1 = "Expected connection to "
                r7.append(r1)     // Catch:{ RemoteException -> 0x0096 }
                androidx.media2.session.MediaControllerImplBase r1 = androidx.media2.session.MediaControllerImplBase.this     // Catch:{ RemoteException -> 0x0096 }
                androidx.media2.session.SessionToken r1 = r1.mToken     // Catch:{ RemoteException -> 0x0096 }
                java.lang.String r1 = r1.getPackageName()     // Catch:{ RemoteException -> 0x0096 }
                r7.append(r1)     // Catch:{ RemoteException -> 0x0096 }
                java.lang.String r1 = " but is connected to "
                r7.append(r1)     // Catch:{ RemoteException -> 0x0096 }
                r7.append(r6)     // Catch:{ RemoteException -> 0x0096 }
                java.lang.String r7 = r7.toString()     // Catch:{ RemoteException -> 0x0096 }
                android.util.Log.wtf(r0, r7)     // Catch:{ RemoteException -> 0x0096 }
                androidx.media2.session.MediaControllerImplBase r6 = androidx.media2.session.MediaControllerImplBase.this
                androidx.media2.session.MediaController r6 = r6.mInstance
                r6.close()
                return
            L_0x0060:
                androidx.media2.session.IMediaSessionService r7 = androidx.media2.session.IMediaSessionService.Stub.asInterface(r7)     // Catch:{ RemoteException -> 0x0096 }
                if (r7 != 0) goto L_0x0073
                java.lang.String r7 = "Service interface is missing."
                android.util.Log.wtf(r0, r7)     // Catch:{ RemoteException -> 0x0096 }
                androidx.media2.session.MediaControllerImplBase r6 = androidx.media2.session.MediaControllerImplBase.this
                androidx.media2.session.MediaController r6 = r6.mInstance
                r6.close()
                return
            L_0x0073:
                androidx.media2.session.ConnectionRequest r1 = new androidx.media2.session.ConnectionRequest     // Catch:{ RemoteException -> 0x0096 }
                androidx.media2.session.MediaControllerImplBase r2 = androidx.media2.session.MediaControllerImplBase.this     // Catch:{ RemoteException -> 0x0096 }
                android.content.Context r2 = r2.getContext()     // Catch:{ RemoteException -> 0x0096 }
                java.lang.String r2 = r2.getPackageName()     // Catch:{ RemoteException -> 0x0096 }
                int r3 = android.os.Process.myPid()     // Catch:{ RemoteException -> 0x0096 }
                android.os.Bundle r4 = r5.mConnectionHints     // Catch:{ RemoteException -> 0x0096 }
                r1.<init>(r2, r3, r4)     // Catch:{ RemoteException -> 0x0096 }
                androidx.media2.session.MediaControllerImplBase r2 = androidx.media2.session.MediaControllerImplBase.this     // Catch:{ RemoteException -> 0x0096 }
                androidx.media2.session.MediaControllerStub r2 = r2.mControllerStub     // Catch:{ RemoteException -> 0x0096 }
                androidx.versionedparcelable.ParcelImpl r1 = androidx.media2.common.MediaParcelUtils.toParcelable(r1)     // Catch:{ RemoteException -> 0x0096 }
                r7.connect(r2, r1)     // Catch:{ RemoteException -> 0x0096 }
                goto L_0x00b6
            L_0x0094:
                r6 = move-exception
                goto L_0x00b7
            L_0x0096:
                java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ all -> 0x0094 }
                r7.<init>()     // Catch:{ all -> 0x0094 }
                java.lang.String r1 = "Service "
                r7.append(r1)     // Catch:{ all -> 0x0094 }
                r7.append(r6)     // Catch:{ all -> 0x0094 }
                java.lang.String r6 = " has died prematurely"
                r7.append(r6)     // Catch:{ all -> 0x0094 }
                java.lang.String r6 = r7.toString()     // Catch:{ all -> 0x0094 }
                android.util.Log.w(r0, r6)     // Catch:{ all -> 0x0094 }
                androidx.media2.session.MediaControllerImplBase r6 = androidx.media2.session.MediaControllerImplBase.this
                androidx.media2.session.MediaController r6 = r6.mInstance
                r6.close()
            L_0x00b6:
                return
            L_0x00b7:
                androidx.media2.session.MediaControllerImplBase r7 = androidx.media2.session.MediaControllerImplBase.this
                androidx.media2.session.MediaController r7 = r7.mInstance
                r7.close()
                throw r6
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.media2.session.MediaControllerImplBase.SessionServiceConnection.onServiceConnected(android.content.ComponentName, android.os.IBinder):void");
        }

        public void onServiceDisconnected(ComponentName componentName) {
            if (MediaControllerImplBase.DEBUG) {
                Log.w(MediaControllerImplBase.TAG, "Session service " + componentName + " is disconnected.");
            }
            MediaControllerImplBase.this.mInstance.close();
        }

        public void onBindingDied(ComponentName componentName) {
            MediaControllerImplBase.this.mInstance.close();
        }
    }
}

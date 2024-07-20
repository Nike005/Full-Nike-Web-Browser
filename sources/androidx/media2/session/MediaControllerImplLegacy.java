package androidx.media2.session;

import android.app.PendingIntent;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Process;
import android.os.RemoteException;
import android.os.ResultReceiver;
import android.support.p064v4.media.MediaBrowserCompat;
import android.support.p064v4.media.MediaMetadataCompat;
import android.support.p064v4.media.session.MediaControllerCompat;
import android.support.p064v4.media.session.MediaSessionCompat;
import android.support.p064v4.media.session.PlaybackStateCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.Surface;
import androidx.core.app.BundleCompat;
import androidx.media2.common.MediaItem;
import androidx.media2.common.MediaMetadata;
import androidx.media2.common.SessionPlayer;
import androidx.media2.common.VideoSize;
import androidx.media2.session.MediaController;
import androidx.media2.session.MediaSession;
import androidx.media2.session.futures.ResolvableFuture;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.List;

class MediaControllerImplLegacy implements MediaController.MediaControllerImpl {
    static final boolean DEBUG = Log.isLoggable(TAG, 3);
    private static final int ITEM_NONE = -1;
    private static final long POSITION_DIFF_TOLERANCE = 100;
    static final String SESSION_COMMAND_ON_CAPTIONING_ENABLED_CHANGED = "android.media.session.command.ON_CAPTIONING_ENALBED_CHANGED";
    static final String SESSION_COMMAND_ON_EXTRAS_CHANGED = "android.media.session.command.ON_EXTRAS_CHANGED";
    private static final String TAG = "MC2ImplLegacy";
    static final Bundle sDefaultRootExtras;
    SessionCommandGroup mAllowedCommands;
    MediaBrowserCompat mBrowserCompat;
    long mBufferedPosition;
    int mBufferingState;
    private volatile boolean mConnected;
    final Context mContext;
    MediaControllerCompat mControllerCompat;
    private ControllerCompatCallback mControllerCompatCallback;
    MediaItem mCurrentMediaItem;
    int mCurrentMediaItemIndex;
    List<MediaSession.CommandButton> mCustomLayout;
    final Handler mHandler;
    final HandlerThread mHandlerThread;
    MediaController mInstance;
    boolean mIsReleased;
    final Object mLock = new Object();
    MediaMetadataCompat mMediaMetadataCompat;
    MediaController.PlaybackInfo mPlaybackInfo;
    PlaybackStateCompat mPlaybackStateCompat;
    int mPlayerState;
    List<MediaItem> mPlaylist;
    MediaMetadata mPlaylistMetadata;
    List<MediaSessionCompat.QueueItem> mQueue;
    int mRepeatMode;
    int mShuffleMode;
    int mSkipToPlaylistIndex = -1;
    final SessionToken mToken;

    public int getNextMediaItemIndex() {
        return -1;
    }

    public int getPreviousMediaItemIndex() {
        return -1;
    }

    static {
        Bundle bundle = new Bundle();
        sDefaultRootExtras = bundle;
        bundle.putBoolean("androidx.media2.root_default_root", true);
    }

    MediaControllerImplLegacy(Context context, MediaController mediaController, SessionToken sessionToken) {
        this.mContext = context;
        this.mInstance = mediaController;
        HandlerThread handlerThread = new HandlerThread("MediaController_Thread");
        this.mHandlerThread = handlerThread;
        handlerThread.start();
        this.mHandler = new Handler(this.mHandlerThread.getLooper());
        this.mToken = sessionToken;
        if (sessionToken.getType() == 0) {
            synchronized (this.mLock) {
                this.mBrowserCompat = null;
            }
            connectToSession((MediaSessionCompat.Token) this.mToken.getBinder());
            return;
        }
        connectToService();
    }

    public void close() {
        if (DEBUG) {
            Log.d(TAG, "release from " + this.mToken);
        }
        synchronized (this.mLock) {
            if (!this.mIsReleased) {
                this.mHandler.removeCallbacksAndMessages((Object) null);
                if (Build.VERSION.SDK_INT >= 18) {
                    this.mHandlerThread.quitSafely();
                } else {
                    this.mHandlerThread.quit();
                }
                this.mIsReleased = true;
                if (this.mBrowserCompat != null) {
                    this.mBrowserCompat.disconnect();
                    this.mBrowserCompat = null;
                }
                if (this.mControllerCompat != null) {
                    this.mControllerCompat.unregisterCallback(this.mControllerCompatCallback);
                    this.mControllerCompat = null;
                }
                this.mConnected = false;
                this.mInstance.notifyControllerCallback(new MediaController.ControllerCallbackRunnable() {
                    public void run(MediaController.ControllerCallback controllerCallback) {
                        controllerCallback.onDisconnected(MediaControllerImplLegacy.this.mInstance);
                    }
                });
            }
        }
    }

    public SessionToken getConnectedToken() {
        SessionToken sessionToken;
        synchronized (this.mLock) {
            sessionToken = this.mConnected ? this.mToken : null;
        }
        return sessionToken;
    }

    private ListenableFuture<SessionResult> createFutureWithResult(int i) {
        MediaItem mediaItem;
        synchronized (this.mLock) {
            mediaItem = this.mCurrentMediaItem;
        }
        ResolvableFuture create = ResolvableFuture.create();
        create.set(new SessionResult(i, (Bundle) null, mediaItem));
        return create;
    }

    public boolean isConnected() {
        boolean z;
        synchronized (this.mLock) {
            z = this.mConnected;
        }
        return z;
    }

    public ListenableFuture<SessionResult> play() {
        synchronized (this.mLock) {
            if (!this.mConnected) {
                Log.w(TAG, "Session isn't active", new IllegalStateException());
                ListenableFuture<SessionResult> createFutureWithResult = createFutureWithResult(-100);
                return createFutureWithResult;
            }
            this.mControllerCompat.getTransportControls().play();
            return createFutureWithResult(0);
        }
    }

    public ListenableFuture<SessionResult> pause() {
        synchronized (this.mLock) {
            if (!this.mConnected) {
                Log.w(TAG, "Session isn't active", new IllegalStateException());
                ListenableFuture<SessionResult> createFutureWithResult = createFutureWithResult(-100);
                return createFutureWithResult;
            }
            this.mControllerCompat.getTransportControls().pause();
            return createFutureWithResult(0);
        }
    }

    public ListenableFuture<SessionResult> prepare() {
        synchronized (this.mLock) {
            if (!this.mConnected) {
                Log.w(TAG, "Session isn't active", new IllegalStateException());
                ListenableFuture<SessionResult> createFutureWithResult = createFutureWithResult(-100);
                return createFutureWithResult;
            }
            this.mControllerCompat.getTransportControls().prepare();
            return createFutureWithResult(0);
        }
    }

    public ListenableFuture<SessionResult> fastForward() {
        synchronized (this.mLock) {
            if (!this.mConnected) {
                Log.w(TAG, "Session isn't active", new IllegalStateException());
                ListenableFuture<SessionResult> createFutureWithResult = createFutureWithResult(-100);
                return createFutureWithResult;
            }
            this.mControllerCompat.getTransportControls().fastForward();
            return createFutureWithResult(0);
        }
    }

    public ListenableFuture<SessionResult> rewind() {
        synchronized (this.mLock) {
            if (!this.mConnected) {
                Log.w(TAG, "Session isn't active", new IllegalStateException());
                ListenableFuture<SessionResult> createFutureWithResult = createFutureWithResult(-100);
                return createFutureWithResult;
            }
            this.mControllerCompat.getTransportControls().rewind();
            return createFutureWithResult(0);
        }
    }

    public ListenableFuture<SessionResult> skipForward() {
        return createFutureWithResult(-6);
    }

    public ListenableFuture<SessionResult> skipBackward() {
        return createFutureWithResult(-6);
    }

    public ListenableFuture<SessionResult> seekTo(long j) {
        synchronized (this.mLock) {
            if (!this.mConnected) {
                Log.w(TAG, "Session isn't active", new IllegalStateException());
                ListenableFuture<SessionResult> createFutureWithResult = createFutureWithResult(-100);
                return createFutureWithResult;
            }
            this.mControllerCompat.getTransportControls().seekTo(j);
            return createFutureWithResult(0);
        }
    }

    public ListenableFuture<SessionResult> playFromMediaId(String str, Bundle bundle) {
        synchronized (this.mLock) {
            if (!this.mConnected) {
                Log.w(TAG, "Session isn't active", new IllegalStateException());
                ListenableFuture<SessionResult> createFutureWithResult = createFutureWithResult(-100);
                return createFutureWithResult;
            }
            this.mControllerCompat.getTransportControls().playFromMediaId(str, bundle);
            return createFutureWithResult(0);
        }
    }

    public ListenableFuture<SessionResult> playFromSearch(String str, Bundle bundle) {
        synchronized (this.mLock) {
            if (!this.mConnected) {
                Log.w(TAG, "Session isn't active", new IllegalStateException());
                ListenableFuture<SessionResult> createFutureWithResult = createFutureWithResult(-100);
                return createFutureWithResult;
            }
            this.mControllerCompat.getTransportControls().playFromSearch(str, bundle);
            return createFutureWithResult(0);
        }
    }

    public ListenableFuture<SessionResult> playFromUri(Uri uri, Bundle bundle) {
        synchronized (this.mLock) {
            if (!this.mConnected) {
                Log.w(TAG, "Session isn't active", new IllegalStateException());
                ListenableFuture<SessionResult> createFutureWithResult = createFutureWithResult(-100);
                return createFutureWithResult;
            }
            this.mControllerCompat.getTransportControls().playFromUri(uri, bundle);
            return createFutureWithResult(0);
        }
    }

    public ListenableFuture<SessionResult> prepareFromMediaId(String str, Bundle bundle) {
        synchronized (this.mLock) {
            if (!this.mConnected) {
                Log.w(TAG, "Session isn't active", new IllegalStateException());
                ListenableFuture<SessionResult> createFutureWithResult = createFutureWithResult(-100);
                return createFutureWithResult;
            }
            this.mControllerCompat.getTransportControls().prepareFromMediaId(str, bundle);
            return createFutureWithResult(0);
        }
    }

    public ListenableFuture<SessionResult> prepareFromSearch(String str, Bundle bundle) {
        synchronized (this.mLock) {
            if (!this.mConnected) {
                Log.w(TAG, "Session isn't active", new IllegalStateException());
                ListenableFuture<SessionResult> createFutureWithResult = createFutureWithResult(-100);
                return createFutureWithResult;
            }
            this.mControllerCompat.getTransportControls().prepareFromSearch(str, bundle);
            return createFutureWithResult(0);
        }
    }

    public ListenableFuture<SessionResult> prepareFromUri(Uri uri, Bundle bundle) {
        synchronized (this.mLock) {
            if (!this.mConnected) {
                Log.w(TAG, "Session isn't active", new IllegalStateException());
                ListenableFuture<SessionResult> createFutureWithResult = createFutureWithResult(-100);
                return createFutureWithResult;
            }
            this.mControllerCompat.getTransportControls().prepareFromUri(uri, bundle);
            return createFutureWithResult(0);
        }
    }

    public ListenableFuture<SessionResult> setVolumeTo(int i, int i2) {
        synchronized (this.mLock) {
            if (!this.mConnected) {
                Log.w(TAG, "Session isn't active", new IllegalStateException());
                ListenableFuture<SessionResult> createFutureWithResult = createFutureWithResult(-100);
                return createFutureWithResult;
            }
            this.mControllerCompat.setVolumeTo(i, i2);
            return createFutureWithResult(0);
        }
    }

    public ListenableFuture<SessionResult> adjustVolume(int i, int i2) {
        synchronized (this.mLock) {
            if (!this.mConnected) {
                Log.w(TAG, "Session isn't active", new IllegalStateException());
                ListenableFuture<SessionResult> createFutureWithResult = createFutureWithResult(-100);
                return createFutureWithResult;
            }
            this.mControllerCompat.adjustVolume(i, i2);
            return createFutureWithResult(0);
        }
    }

    public PendingIntent getSessionActivity() {
        synchronized (this.mLock) {
            if (!this.mConnected) {
                Log.w(TAG, "Session isn't active", new IllegalStateException());
                return null;
            }
            PendingIntent sessionActivity = this.mControllerCompat.getSessionActivity();
            return sessionActivity;
        }
    }

    public int getPlayerState() {
        synchronized (this.mLock) {
            if (!this.mConnected) {
                Log.w(TAG, "Session isn't active", new IllegalStateException());
                return 3;
            }
            int i = this.mPlayerState;
            return i;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0030, code lost:
        return Long.MIN_VALUE;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public long getDuration() {
        /*
            r6 = this;
            java.lang.Object r0 = r6.mLock
            monitor-enter(r0)
            boolean r1 = r6.mConnected     // Catch:{ all -> 0x0031 }
            r2 = -9223372036854775808
            if (r1 != 0) goto L_0x0017
            java.lang.String r1 = "MC2ImplLegacy"
            java.lang.String r4 = "Session isn't active"
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException     // Catch:{ all -> 0x0031 }
            r5.<init>()     // Catch:{ all -> 0x0031 }
            android.util.Log.w(r1, r4, r5)     // Catch:{ all -> 0x0031 }
            monitor-exit(r0)     // Catch:{ all -> 0x0031 }
            return r2
        L_0x0017:
            android.support.v4.media.MediaMetadataCompat r1 = r6.mMediaMetadataCompat     // Catch:{ all -> 0x0031 }
            if (r1 == 0) goto L_0x002f
            android.support.v4.media.MediaMetadataCompat r1 = r6.mMediaMetadataCompat     // Catch:{ all -> 0x0031 }
            java.lang.String r4 = "android.media.metadata.DURATION"
            boolean r1 = r1.containsKey(r4)     // Catch:{ all -> 0x0031 }
            if (r1 == 0) goto L_0x002f
            android.support.v4.media.MediaMetadataCompat r1 = r6.mMediaMetadataCompat     // Catch:{ all -> 0x0031 }
            java.lang.String r2 = "android.media.metadata.DURATION"
            long r1 = r1.getLong(r2)     // Catch:{ all -> 0x0031 }
            monitor-exit(r0)     // Catch:{ all -> 0x0031 }
            return r1
        L_0x002f:
            monitor-exit(r0)     // Catch:{ all -> 0x0031 }
            return r2
        L_0x0031:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0031 }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media2.session.MediaControllerImplLegacy.getDuration():long");
    }

    public long getCurrentPosition() {
        synchronized (this.mLock) {
            if (!this.mConnected) {
                Log.w(TAG, "Session isn't active", new IllegalStateException());
                return Long.MIN_VALUE;
            } else if (this.mPlaybackStateCompat == null) {
                return Long.MIN_VALUE;
            } else {
                long currentPosition = this.mPlaybackStateCompat.getCurrentPosition(this.mInstance.mTimeDiff);
                return currentPosition;
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0022, code lost:
        return r2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public float getPlaybackSpeed() {
        /*
            r5 = this;
            java.lang.Object r0 = r5.mLock
            monitor-enter(r0)
            boolean r1 = r5.mConnected     // Catch:{ all -> 0x0023 }
            r2 = 0
            if (r1 != 0) goto L_0x0016
            java.lang.String r1 = "MC2ImplLegacy"
            java.lang.String r3 = "Session isn't active"
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException     // Catch:{ all -> 0x0023 }
            r4.<init>()     // Catch:{ all -> 0x0023 }
            android.util.Log.w(r1, r3, r4)     // Catch:{ all -> 0x0023 }
            monitor-exit(r0)     // Catch:{ all -> 0x0023 }
            return r2
        L_0x0016:
            android.support.v4.media.session.PlaybackStateCompat r1 = r5.mPlaybackStateCompat     // Catch:{ all -> 0x0023 }
            if (r1 != 0) goto L_0x001b
            goto L_0x0021
        L_0x001b:
            android.support.v4.media.session.PlaybackStateCompat r1 = r5.mPlaybackStateCompat     // Catch:{ all -> 0x0023 }
            float r2 = r1.getPlaybackSpeed()     // Catch:{ all -> 0x0023 }
        L_0x0021:
            monitor-exit(r0)     // Catch:{ all -> 0x0023 }
            return r2
        L_0x0023:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0023 }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media2.session.MediaControllerImplLegacy.getPlaybackSpeed():float");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0026, code lost:
        return r2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int getBufferingState() {
        /*
            r5 = this;
            java.lang.Object r0 = r5.mLock
            monitor-enter(r0)
            boolean r1 = r5.mConnected     // Catch:{ all -> 0x0027 }
            r2 = 0
            if (r1 != 0) goto L_0x0016
            java.lang.String r1 = "MC2ImplLegacy"
            java.lang.String r3 = "Session isn't active"
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException     // Catch:{ all -> 0x0027 }
            r4.<init>()     // Catch:{ all -> 0x0027 }
            android.util.Log.w(r1, r3, r4)     // Catch:{ all -> 0x0027 }
            monitor-exit(r0)     // Catch:{ all -> 0x0027 }
            return r2
        L_0x0016:
            android.support.v4.media.session.PlaybackStateCompat r1 = r5.mPlaybackStateCompat     // Catch:{ all -> 0x0027 }
            if (r1 != 0) goto L_0x001b
            goto L_0x0025
        L_0x001b:
            android.support.v4.media.session.PlaybackStateCompat r1 = r5.mPlaybackStateCompat     // Catch:{ all -> 0x0027 }
            int r1 = r1.getState()     // Catch:{ all -> 0x0027 }
            int r2 = androidx.media2.session.MediaUtils.toBufferingState(r1)     // Catch:{ all -> 0x0027 }
        L_0x0025:
            monitor-exit(r0)     // Catch:{ all -> 0x0027 }
            return r2
        L_0x0027:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0027 }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media2.session.MediaControllerImplLegacy.getBufferingState():int");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0023, code lost:
        return r2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public long getBufferedPosition() {
        /*
            r6 = this;
            java.lang.Object r0 = r6.mLock
            monitor-enter(r0)
            boolean r1 = r6.mConnected     // Catch:{ all -> 0x0024 }
            r2 = -9223372036854775808
            if (r1 != 0) goto L_0x0017
            java.lang.String r1 = "MC2ImplLegacy"
            java.lang.String r4 = "Session isn't active"
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException     // Catch:{ all -> 0x0024 }
            r5.<init>()     // Catch:{ all -> 0x0024 }
            android.util.Log.w(r1, r4, r5)     // Catch:{ all -> 0x0024 }
            monitor-exit(r0)     // Catch:{ all -> 0x0024 }
            return r2
        L_0x0017:
            android.support.v4.media.session.PlaybackStateCompat r1 = r6.mPlaybackStateCompat     // Catch:{ all -> 0x0024 }
            if (r1 != 0) goto L_0x001c
            goto L_0x0022
        L_0x001c:
            android.support.v4.media.session.PlaybackStateCompat r1 = r6.mPlaybackStateCompat     // Catch:{ all -> 0x0024 }
            long r2 = r1.getBufferedPosition()     // Catch:{ all -> 0x0024 }
        L_0x0022:
            monitor-exit(r0)     // Catch:{ all -> 0x0024 }
            return r2
        L_0x0024:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0024 }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media2.session.MediaControllerImplLegacy.getBufferedPosition():long");
    }

    public MediaController.PlaybackInfo getPlaybackInfo() {
        synchronized (this.mLock) {
            if (!this.mConnected) {
                Log.w(TAG, "Session isn't active", new IllegalStateException());
                return null;
            }
            MediaController.PlaybackInfo playbackInfo = this.mPlaybackInfo;
            return playbackInfo;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x003e, code lost:
        return createFutureWithResult(0);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.google.common.util.concurrent.ListenableFuture<androidx.media2.session.SessionResult> setRating(java.lang.String r3, androidx.media2.common.Rating r4) {
        /*
            r2 = this;
            java.lang.Object r0 = r2.mLock
            monitor-enter(r0)
            boolean r1 = r2.mConnected     // Catch:{ all -> 0x003f }
            if (r1 != 0) goto L_0x001b
            java.lang.String r3 = "MC2ImplLegacy"
            java.lang.String r4 = "Session isn't active"
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException     // Catch:{ all -> 0x003f }
            r1.<init>()     // Catch:{ all -> 0x003f }
            android.util.Log.w(r3, r4, r1)     // Catch:{ all -> 0x003f }
            r3 = -100
            com.google.common.util.concurrent.ListenableFuture r3 = r2.createFutureWithResult(r3)     // Catch:{ all -> 0x003f }
            monitor-exit(r0)     // Catch:{ all -> 0x003f }
            return r3
        L_0x001b:
            androidx.media2.common.MediaItem r1 = r2.mCurrentMediaItem     // Catch:{ all -> 0x003f }
            if (r1 == 0) goto L_0x0038
            androidx.media2.common.MediaItem r1 = r2.mCurrentMediaItem     // Catch:{ all -> 0x003f }
            java.lang.String r1 = r1.getMediaId()     // Catch:{ all -> 0x003f }
            boolean r3 = r3.equals(r1)     // Catch:{ all -> 0x003f }
            if (r3 == 0) goto L_0x0038
            android.support.v4.media.session.MediaControllerCompat r3 = r2.mControllerCompat     // Catch:{ all -> 0x003f }
            android.support.v4.media.session.MediaControllerCompat$TransportControls r3 = r3.getTransportControls()     // Catch:{ all -> 0x003f }
            android.support.v4.media.RatingCompat r4 = androidx.media2.session.MediaUtils.convertToRatingCompat(r4)     // Catch:{ all -> 0x003f }
            r3.setRating(r4)     // Catch:{ all -> 0x003f }
        L_0x0038:
            monitor-exit(r0)     // Catch:{ all -> 0x003f }
            r3 = 0
            com.google.common.util.concurrent.ListenableFuture r3 = r2.createFutureWithResult(r3)
            return r3
        L_0x003f:
            r3 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x003f }
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media2.session.MediaControllerImplLegacy.setRating(java.lang.String, androidx.media2.common.Rating):com.google.common.util.concurrent.ListenableFuture");
    }

    public ListenableFuture<SessionResult> setPlaybackSpeed(float f) {
        synchronized (this.mLock) {
            if (!this.mConnected) {
                Log.w(TAG, "Session isn't active", new IllegalStateException());
                ListenableFuture<SessionResult> createFutureWithResult = createFutureWithResult(-100);
                return createFutureWithResult;
            }
            this.mControllerCompat.getTransportControls().setPlaybackSpeed(f);
            return createFutureWithResult(0);
        }
    }

    public ListenableFuture<SessionResult> sendCustomCommand(SessionCommand sessionCommand, Bundle bundle) {
        synchronized (this.mLock) {
            if (!this.mConnected) {
                Log.w(TAG, "Session isn't active", new IllegalStateException());
                ListenableFuture<SessionResult> createFutureWithResult = createFutureWithResult(-100);
                return createFutureWithResult;
            } else if (this.mAllowedCommands.hasCommand(sessionCommand)) {
                this.mControllerCompat.getTransportControls().sendCustomAction(sessionCommand.getCustomAction(), bundle);
                ListenableFuture<SessionResult> createFutureWithResult2 = createFutureWithResult(0);
                return createFutureWithResult2;
            } else {
                final ResolvableFuture create = ResolvableFuture.create();
                this.mControllerCompat.sendCommand(sessionCommand.getCustomAction(), bundle, new ResultReceiver(this.mHandler) {
                    /* access modifiers changed from: protected */
                    public void onReceiveResult(int i, Bundle bundle) {
                        create.set(new SessionResult(i, bundle));
                    }
                });
                return create;
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0026, code lost:
        return r2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.List<androidx.media2.common.MediaItem> getPlaylist() {
        /*
            r5 = this;
            java.lang.Object r0 = r5.mLock
            monitor-enter(r0)
            boolean r1 = r5.mConnected     // Catch:{ all -> 0x0027 }
            r2 = 0
            if (r1 != 0) goto L_0x0016
            java.lang.String r1 = "MC2ImplLegacy"
            java.lang.String r3 = "Session isn't active"
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException     // Catch:{ all -> 0x0027 }
            r4.<init>()     // Catch:{ all -> 0x0027 }
            android.util.Log.w(r1, r3, r4)     // Catch:{ all -> 0x0027 }
            monitor-exit(r0)     // Catch:{ all -> 0x0027 }
            return r2
        L_0x0016:
            java.util.List<androidx.media2.common.MediaItem> r1 = r5.mPlaylist     // Catch:{ all -> 0x0027 }
            if (r1 == 0) goto L_0x0025
            java.util.List<androidx.media2.common.MediaItem> r1 = r5.mPlaylist     // Catch:{ all -> 0x0027 }
            int r1 = r1.size()     // Catch:{ all -> 0x0027 }
            if (r1 != 0) goto L_0x0023
            goto L_0x0025
        L_0x0023:
            java.util.List<androidx.media2.common.MediaItem> r2 = r5.mPlaylist     // Catch:{ all -> 0x0027 }
        L_0x0025:
            monitor-exit(r0)     // Catch:{ all -> 0x0027 }
            return r2
        L_0x0027:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0027 }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media2.session.MediaControllerImplLegacy.getPlaylist():java.util.List");
    }

    public ListenableFuture<SessionResult> setPlaylist(List<String> list, MediaMetadata mediaMetadata) {
        return createFutureWithResult(-6);
    }

    public ListenableFuture<SessionResult> setMediaItem(String str) {
        return createFutureWithResult(-6);
    }

    public ListenableFuture<SessionResult> updatePlaylistMetadata(MediaMetadata mediaMetadata) {
        return createFutureWithResult(-6);
    }

    public MediaMetadata getPlaylistMetadata() {
        synchronized (this.mLock) {
            if (!this.mConnected) {
                Log.w(TAG, "Session isn't active", new IllegalStateException());
                return null;
            }
            MediaMetadata mediaMetadata = this.mPlaylistMetadata;
            return mediaMetadata;
        }
    }

    public ListenableFuture<SessionResult> addPlaylistItem(int i, String str) {
        synchronized (this.mLock) {
            if (!this.mConnected) {
                Log.w(TAG, "Session isn't active", new IllegalStateException());
                ListenableFuture<SessionResult> createFutureWithResult = createFutureWithResult(-100);
                return createFutureWithResult;
            }
            this.mControllerCompat.addQueueItem(MediaUtils.createMediaDescriptionCompat(str), i);
            return createFutureWithResult(0);
        }
    }

    public ListenableFuture<SessionResult> removePlaylistItem(int i) {
        synchronized (this.mLock) {
            if (!this.mConnected) {
                Log.w(TAG, "Session isn't active", new IllegalStateException());
                ListenableFuture<SessionResult> createFutureWithResult = createFutureWithResult(-100);
                return createFutureWithResult;
            }
            if (this.mQueue != null && i >= 0) {
                if (i < this.mQueue.size()) {
                    this.mControllerCompat.removeQueueItem(this.mQueue.get(i).getDescription());
                    return createFutureWithResult(0);
                }
            }
            ListenableFuture<SessionResult> createFutureWithResult2 = createFutureWithResult(-3);
            return createFutureWithResult2;
        }
    }

    public ListenableFuture<SessionResult> replacePlaylistItem(int i, String str) {
        synchronized (this.mLock) {
            if (!this.mConnected) {
                Log.w(TAG, "Session isn't active", new IllegalStateException());
                ListenableFuture<SessionResult> createFutureWithResult = createFutureWithResult(-100);
                return createFutureWithResult;
            }
            if (this.mPlaylist != null && i >= 0) {
                if (this.mPlaylist.size() > i) {
                    removePlaylistItem(i);
                    addPlaylistItem(i, str);
                    return createFutureWithResult(0);
                }
            }
            ListenableFuture<SessionResult> createFutureWithResult2 = createFutureWithResult(-100);
            return createFutureWithResult2;
        }
    }

    public MediaItem getCurrentMediaItem() {
        synchronized (this.mLock) {
            if (!this.mConnected) {
                Log.w(TAG, "Session isn't active", new IllegalStateException());
                return null;
            }
            MediaItem mediaItem = this.mCurrentMediaItem;
            return mediaItem;
        }
    }

    public int getCurrentMediaItemIndex() {
        return this.mCurrentMediaItemIndex;
    }

    public ListenableFuture<SessionResult> skipToPreviousItem() {
        synchronized (this.mLock) {
            if (!this.mConnected) {
                Log.w(TAG, "Session isn't active", new IllegalStateException());
                ListenableFuture<SessionResult> createFutureWithResult = createFutureWithResult(-100);
                return createFutureWithResult;
            }
            this.mControllerCompat.getTransportControls().skipToPrevious();
            return createFutureWithResult(0);
        }
    }

    public ListenableFuture<SessionResult> skipToNextItem() {
        synchronized (this.mLock) {
            if (!this.mConnected) {
                Log.w(TAG, "Session isn't active", new IllegalStateException());
                ListenableFuture<SessionResult> createFutureWithResult = createFutureWithResult(-100);
                return createFutureWithResult;
            }
            this.mControllerCompat.getTransportControls().skipToNext();
            return createFutureWithResult(0);
        }
    }

    public ListenableFuture<SessionResult> skipToPlaylistItem(int i) {
        synchronized (this.mLock) {
            if (!this.mConnected) {
                Log.w(TAG, "Session isn't active", new IllegalStateException());
                ListenableFuture<SessionResult> createFutureWithResult = createFutureWithResult(-100);
                return createFutureWithResult;
            }
            this.mSkipToPlaylistIndex = i;
            this.mControllerCompat.getTransportControls().skipToQueueItem(this.mQueue.get(i).getQueueId());
            return createFutureWithResult(0);
        }
    }

    public int getRepeatMode() {
        synchronized (this.mLock) {
            if (!this.mConnected) {
                Log.w(TAG, "Session isn't active", new IllegalStateException());
                return 0;
            }
            int i = this.mRepeatMode;
            return i;
        }
    }

    public ListenableFuture<SessionResult> setRepeatMode(int i) {
        synchronized (this.mLock) {
            if (!this.mConnected) {
                Log.w(TAG, "Session isn't active", new IllegalStateException());
                ListenableFuture<SessionResult> createFutureWithResult = createFutureWithResult(-100);
                return createFutureWithResult;
            }
            this.mControllerCompat.getTransportControls().setRepeatMode(i);
            return createFutureWithResult(0);
        }
    }

    public int getShuffleMode() {
        synchronized (this.mLock) {
            if (!this.mConnected) {
                Log.w(TAG, "Session isn't active", new IllegalStateException());
                return 0;
            }
            int i = this.mShuffleMode;
            return i;
        }
    }

    public ListenableFuture<SessionResult> setShuffleMode(int i) {
        synchronized (this.mLock) {
            if (!this.mConnected) {
                Log.w(TAG, "Session isn't active", new IllegalStateException());
                ListenableFuture<SessionResult> createFutureWithResult = createFutureWithResult(-100);
                return createFutureWithResult;
            }
            this.mControllerCompat.getTransportControls().setShuffleMode(i);
            return createFutureWithResult(0);
        }
    }

    public VideoSize getVideoSize() {
        Log.w(TAG, "Session doesn't support getting VideoSize");
        return new VideoSize(0, 0);
    }

    public ListenableFuture<SessionResult> setSurface(Surface surface) {
        Log.w(TAG, "Session doesn't support setting Surface");
        return createFutureWithResult(-6);
    }

    public List<SessionPlayer.TrackInfo> getTrackInfo() {
        Log.w(TAG, "Session doesn't support getting TrackInfo");
        return null;
    }

    public ListenableFuture<SessionResult> selectTrack(SessionPlayer.TrackInfo trackInfo) {
        Log.w(TAG, "Session doesn't support selecting track");
        return createFutureWithResult(-6);
    }

    public ListenableFuture<SessionResult> deselectTrack(SessionPlayer.TrackInfo trackInfo) {
        Log.w(TAG, "Session doesn't support deselecting track");
        return createFutureWithResult(-6);
    }

    public SessionPlayer.TrackInfo getSelectedTrack(int i) {
        Log.w(TAG, "Session doesn't support getting selected track");
        return null;
    }

    public SessionCommandGroup getAllowedCommands() {
        synchronized (this.mLock) {
            if (!this.mConnected) {
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

    public MediaBrowserCompat getBrowserCompat() {
        MediaBrowserCompat mediaBrowserCompat;
        synchronized (this.mLock) {
            mediaBrowserCompat = this.mBrowserCompat;
        }
        return mediaBrowserCompat;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x00b8, code lost:
        r4.mInstance.notifyControllerCallback(new androidx.media2.session.MediaControllerImplLegacy.C41653(r4));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x00c6, code lost:
        if (r1.isEmpty() != false) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x00c8, code lost:
        r4.mInstance.notifyControllerCallback(new androidx.media2.session.MediaControllerImplLegacy.C41664(r4));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:?, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onConnectedNotLocked() {
        /*
            r4 = this;
            boolean r0 = DEBUG
            if (r0 == 0) goto L_0x001c
            java.lang.String r0 = "MC2ImplLegacy"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "onConnectedNotLocked token="
            r1.append(r2)
            androidx.media2.session.SessionToken r2 = r4.mToken
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            android.util.Log.d(r0, r1)
        L_0x001c:
            java.lang.Object r0 = r4.mLock
            monitor-enter(r0)
            boolean r1 = r4.mIsReleased     // Catch:{ all -> 0x00d5 }
            if (r1 != 0) goto L_0x00d3
            boolean r1 = r4.mConnected     // Catch:{ all -> 0x00d5 }
            if (r1 == 0) goto L_0x0029
            goto L_0x00d3
        L_0x0029:
            android.support.v4.media.session.MediaControllerCompat r1 = r4.mControllerCompat     // Catch:{ all -> 0x00d5 }
            android.support.v4.media.session.PlaybackStateCompat r1 = r1.getPlaybackState()     // Catch:{ all -> 0x00d5 }
            r4.mPlaybackStateCompat = r1     // Catch:{ all -> 0x00d5 }
            android.support.v4.media.session.MediaControllerCompat r1 = r4.mControllerCompat     // Catch:{ all -> 0x00d5 }
            long r1 = r1.getFlags()     // Catch:{ all -> 0x00d5 }
            android.support.v4.media.session.PlaybackStateCompat r3 = r4.mPlaybackStateCompat     // Catch:{ all -> 0x00d5 }
            androidx.media2.session.SessionCommandGroup r1 = androidx.media2.session.MediaUtils.convertToSessionCommandGroup(r1, r3)     // Catch:{ all -> 0x00d5 }
            r4.mAllowedCommands = r1     // Catch:{ all -> 0x00d5 }
            android.support.v4.media.session.PlaybackStateCompat r1 = r4.mPlaybackStateCompat     // Catch:{ all -> 0x00d5 }
            int r1 = androidx.media2.session.MediaUtils.convertToPlayerState(r1)     // Catch:{ all -> 0x00d5 }
            r4.mPlayerState = r1     // Catch:{ all -> 0x00d5 }
            android.support.v4.media.session.PlaybackStateCompat r1 = r4.mPlaybackStateCompat     // Catch:{ all -> 0x00d5 }
            if (r1 != 0) goto L_0x004e
            r1 = -9223372036854775808
            goto L_0x0054
        L_0x004e:
            android.support.v4.media.session.PlaybackStateCompat r1 = r4.mPlaybackStateCompat     // Catch:{ all -> 0x00d5 }
            long r1 = r1.getBufferedPosition()     // Catch:{ all -> 0x00d5 }
        L_0x0054:
            r4.mBufferedPosition = r1     // Catch:{ all -> 0x00d5 }
            android.support.v4.media.session.PlaybackStateCompat r1 = r4.mPlaybackStateCompat     // Catch:{ all -> 0x00d5 }
            java.util.List r1 = androidx.media2.session.MediaUtils.convertToCustomLayout(r1)     // Catch:{ all -> 0x00d5 }
            r4.mCustomLayout = r1     // Catch:{ all -> 0x00d5 }
            androidx.media2.session.SessionCommandGroup r2 = r4.mAllowedCommands     // Catch:{ all -> 0x00d5 }
            android.support.v4.media.session.MediaControllerCompat r3 = r4.mControllerCompat     // Catch:{ all -> 0x00d5 }
            android.support.v4.media.session.MediaControllerCompat$PlaybackInfo r3 = r3.getPlaybackInfo()     // Catch:{ all -> 0x00d5 }
            androidx.media2.session.MediaController$PlaybackInfo r3 = androidx.media2.session.MediaUtils.toPlaybackInfo2(r3)     // Catch:{ all -> 0x00d5 }
            r4.mPlaybackInfo = r3     // Catch:{ all -> 0x00d5 }
            android.support.v4.media.session.MediaControllerCompat r3 = r4.mControllerCompat     // Catch:{ all -> 0x00d5 }
            int r3 = r3.getRepeatMode()     // Catch:{ all -> 0x00d5 }
            r4.mRepeatMode = r3     // Catch:{ all -> 0x00d5 }
            android.support.v4.media.session.MediaControllerCompat r3 = r4.mControllerCompat     // Catch:{ all -> 0x00d5 }
            int r3 = r3.getShuffleMode()     // Catch:{ all -> 0x00d5 }
            r4.mShuffleMode = r3     // Catch:{ all -> 0x00d5 }
            android.support.v4.media.session.MediaControllerCompat r3 = r4.mControllerCompat     // Catch:{ all -> 0x00d5 }
            java.util.List r3 = r3.getQueue()     // Catch:{ all -> 0x00d5 }
            java.util.List r3 = androidx.media2.session.MediaUtils.removeNullElements(r3)     // Catch:{ all -> 0x00d5 }
            r4.mQueue = r3     // Catch:{ all -> 0x00d5 }
            if (r3 == 0) goto L_0x009a
            int r3 = r3.size()     // Catch:{ all -> 0x00d5 }
            if (r3 != 0) goto L_0x0091
            goto L_0x009a
        L_0x0091:
            java.util.List<android.support.v4.media.session.MediaSessionCompat$QueueItem> r3 = r4.mQueue     // Catch:{ all -> 0x00d5 }
            java.util.List r3 = androidx.media2.session.MediaUtils.convertQueueItemListToMediaItemList(r3)     // Catch:{ all -> 0x00d5 }
            r4.mPlaylist = r3     // Catch:{ all -> 0x00d5 }
            goto L_0x009f
        L_0x009a:
            r3 = 0
            r4.mQueue = r3     // Catch:{ all -> 0x00d5 }
            r4.mPlaylist = r3     // Catch:{ all -> 0x00d5 }
        L_0x009f:
            android.support.v4.media.session.MediaControllerCompat r3 = r4.mControllerCompat     // Catch:{ all -> 0x00d5 }
            java.lang.CharSequence r3 = r3.getQueueTitle()     // Catch:{ all -> 0x00d5 }
            androidx.media2.common.MediaMetadata r3 = androidx.media2.session.MediaUtils.convertToMediaMetadata(r3)     // Catch:{ all -> 0x00d5 }
            r4.mPlaylistMetadata = r3     // Catch:{ all -> 0x00d5 }
            android.support.v4.media.session.MediaControllerCompat r3 = r4.mControllerCompat     // Catch:{ all -> 0x00d5 }
            android.support.v4.media.MediaMetadataCompat r3 = r3.getMetadata()     // Catch:{ all -> 0x00d5 }
            r4.setCurrentMediaItemLocked(r3)     // Catch:{ all -> 0x00d5 }
            r3 = 1
            r4.mConnected = r3     // Catch:{ all -> 0x00d5 }
            monitor-exit(r0)     // Catch:{ all -> 0x00d5 }
            androidx.media2.session.MediaController r0 = r4.mInstance
            androidx.media2.session.MediaControllerImplLegacy$3 r3 = new androidx.media2.session.MediaControllerImplLegacy$3
            r3.<init>(r2)
            r0.notifyControllerCallback(r3)
            boolean r0 = r1.isEmpty()
            if (r0 != 0) goto L_0x00d2
            androidx.media2.session.MediaController r0 = r4.mInstance
            androidx.media2.session.MediaControllerImplLegacy$4 r2 = new androidx.media2.session.MediaControllerImplLegacy$4
            r2.<init>(r1)
            r0.notifyControllerCallback(r2)
        L_0x00d2:
            return
        L_0x00d3:
            monitor-exit(r0)     // Catch:{ all -> 0x00d5 }
            return
        L_0x00d5:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x00d5 }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media2.session.MediaControllerImplLegacy.onConnectedNotLocked():void");
    }

    /* access modifiers changed from: package-private */
    public void connectToSession(MediaSessionCompat.Token token) {
        MediaControllerCompat mediaControllerCompat;
        try {
            mediaControllerCompat = new MediaControllerCompat(this.mContext, token);
        } catch (RemoteException e) {
            e.printStackTrace();
            mediaControllerCompat = null;
        }
        synchronized (this.mLock) {
            this.mControllerCompat = mediaControllerCompat;
            ControllerCompatCallback controllerCompatCallback = new ControllerCompatCallback();
            this.mControllerCompatCallback = controllerCompatCallback;
            this.mControllerCompat.registerCallback(controllerCompatCallback, this.mHandler);
        }
    }

    private void connectToService() {
        this.mHandler.post(new Runnable() {
            public void run() {
                synchronized (MediaControllerImplLegacy.this.mLock) {
                    MediaControllerImplLegacy.this.mBrowserCompat = new MediaBrowserCompat(MediaControllerImplLegacy.this.mContext, MediaControllerImplLegacy.this.mToken.getComponentName(), new ConnectionCallback(), MediaControllerImplLegacy.sDefaultRootExtras);
                    MediaControllerImplLegacy.this.mBrowserCompat.connect();
                }
            }
        });
    }

    private void sendCommand(int i) {
        sendCommand(i, (Bundle) null);
    }

    private void sendCommand(int i, Bundle bundle) {
        if (bundle == null) {
            bundle = new Bundle();
        }
        bundle.putInt("androidx.media2.argument.COMMAND_CODE", i);
        sendCommand("androidx.media2.controller.command.BY_COMMAND_CODE", bundle, (ResultReceiver) null);
    }

    private void sendCommand(String str) {
        sendCommand(str, (Bundle) null, (ResultReceiver) null);
    }

    /* access modifiers changed from: package-private */
    public void sendCommand(String str, ResultReceiver resultReceiver) {
        sendCommand(str, (Bundle) null, resultReceiver);
    }

    private void sendCommand(String str, Bundle bundle, ResultReceiver resultReceiver) {
        MediaControllerCompat mediaControllerCompat;
        ControllerCompatCallback controllerCompatCallback;
        if (bundle == null) {
            bundle = new Bundle();
        }
        synchronized (this.mLock) {
            mediaControllerCompat = this.mControllerCompat;
            controllerCompatCallback = this.mControllerCompatCallback;
        }
        BundleCompat.putBinder(bundle, "androidx.media2.argument.ICONTROLLER_CALLBACK", controllerCompatCallback.getIControllerCallback().asBinder());
        bundle.putString("androidx.media2.argument.PACKAGE_NAME", this.mContext.getPackageName());
        bundle.putInt("androidx.media2.argument.UID", Process.myUid());
        bundle.putInt("androidx.media2.argument.PID", Process.myPid());
        mediaControllerCompat.sendCommand(str, bundle, resultReceiver);
    }

    /* access modifiers changed from: package-private */
    public void setCurrentMediaItemLocked(MediaMetadataCompat mediaMetadataCompat) {
        this.mMediaMetadataCompat = mediaMetadataCompat;
        if (mediaMetadataCompat == null) {
            this.mCurrentMediaItemIndex = -1;
            this.mCurrentMediaItem = null;
        } else if (this.mQueue == null) {
            this.mCurrentMediaItemIndex = -1;
            this.mCurrentMediaItem = MediaUtils.convertToMediaItem(mediaMetadataCompat);
        } else {
            PlaybackStateCompat playbackStateCompat = this.mPlaybackStateCompat;
            if (playbackStateCompat != null) {
                long activeQueueItemId = playbackStateCompat.getActiveQueueItemId();
                for (int i = 0; i < this.mQueue.size(); i++) {
                    if (this.mQueue.get(i).getQueueId() == activeQueueItemId) {
                        this.mCurrentMediaItem = MediaUtils.convertToMediaItem(mediaMetadataCompat);
                        this.mCurrentMediaItemIndex = i;
                        return;
                    }
                }
            }
            String string = mediaMetadataCompat.getString("android.media.metadata.MEDIA_ID");
            if (string == null) {
                this.mCurrentMediaItemIndex = -1;
                this.mCurrentMediaItem = MediaUtils.convertToMediaItem(mediaMetadataCompat);
                return;
            }
            int i2 = this.mSkipToPlaylistIndex;
            if (i2 < 0 || i2 >= this.mQueue.size() || !TextUtils.equals(string, this.mQueue.get(this.mSkipToPlaylistIndex).getDescription().getMediaId())) {
                for (int i3 = 0; i3 < this.mQueue.size(); i3++) {
                    if (TextUtils.equals(string, this.mQueue.get(i3).getDescription().getMediaId())) {
                        this.mCurrentMediaItemIndex = i3;
                        this.mCurrentMediaItem = MediaUtils.convertToMediaItem(mediaMetadataCompat);
                        return;
                    }
                }
                this.mCurrentMediaItemIndex = -1;
                this.mCurrentMediaItem = MediaUtils.convertToMediaItem(this.mMediaMetadataCompat);
                return;
            }
            this.mCurrentMediaItem = MediaUtils.convertToMediaItem(mediaMetadataCompat);
            this.mCurrentMediaItemIndex = this.mSkipToPlaylistIndex;
            this.mSkipToPlaylistIndex = -1;
        }
    }

    private class ConnectionCallback extends MediaBrowserCompat.ConnectionCallback {
        ConnectionCallback() {
        }

        public void onConnected() {
            MediaBrowserCompat browserCompat = MediaControllerImplLegacy.this.getBrowserCompat();
            if (browserCompat != null) {
                MediaControllerImplLegacy.this.connectToSession(browserCompat.getSessionToken());
            } else if (MediaControllerImplLegacy.DEBUG) {
                Log.d(MediaControllerImplLegacy.TAG, "Controller is closed prematually", new IllegalStateException());
            }
        }

        public void onConnectionSuspended() {
            MediaControllerImplLegacy.this.close();
        }

        public void onConnectionFailed() {
            MediaControllerImplLegacy.this.close();
        }
    }

    private final class ControllerCompatCallback extends MediaControllerCompat.Callback {
        ControllerCompatCallback() {
        }

        public void onSessionReady() {
            MediaControllerImplLegacy.this.onConnectedNotLocked();
        }

        public void onSessionDestroyed() {
            MediaControllerImplLegacy.this.close();
        }

        public void onSessionEvent(final String str, final Bundle bundle) {
            synchronized (MediaControllerImplLegacy.this.mLock) {
                if (!MediaControllerImplLegacy.this.mIsReleased) {
                    MediaControllerImplLegacy.this.mInstance.notifyControllerCallback(new MediaController.ControllerCallbackRunnable() {
                        public void run(MediaController.ControllerCallback controllerCallback) {
                            controllerCallback.onCustomCommand(MediaControllerImplLegacy.this.mInstance, new SessionCommand(str, (Bundle) null), bundle);
                        }
                    });
                }
            }
        }

        /* JADX WARNING: Code restructure failed: missing block: B:24:0x00a4, code lost:
            if (r14.this$0.mInstance.mCallback != null) goto L_0x00a7;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:25:0x00a6, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:26:0x00a7, code lost:
            if (r1 == r3) goto L_0x00b5;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:27:0x00a9, code lost:
            r14.this$0.mInstance.notifyControllerCallback(new androidx.media2.session.MediaControllerImplLegacy.ControllerCompatCallback.C41772(r14));
         */
        /* JADX WARNING: Code restructure failed: missing block: B:28:0x00b5, code lost:
            if (r15 != null) goto L_0x00c6;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:29:0x00b7, code lost:
            if (r2 == null) goto L_?;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:30:0x00b9, code lost:
            r14.this$0.mInstance.notifyControllerCallback(new androidx.media2.session.MediaControllerImplLegacy.ControllerCompatCallback.C41783(r14));
         */
        /* JADX WARNING: Code restructure failed: missing block: B:31:0x00c6, code lost:
            if (r2 == null) goto L_0x00d2;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:33:0x00d0, code lost:
            if (r2.getState() == r15.getState()) goto L_0x00de;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:34:0x00d2, code lost:
            r14.this$0.mInstance.notifyControllerCallback(new androidx.media2.session.MediaControllerImplLegacy.ControllerCompatCallback.C41794(r14));
         */
        /* JADX WARNING: Code restructure failed: missing block: B:35:0x00de, code lost:
            if (r2 == null) goto L_0x00ec;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:37:0x00ea, code lost:
            if (r2.getPlaybackSpeed() == r15.getPlaybackSpeed()) goto L_0x00f8;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:38:0x00ec, code lost:
            r14.this$0.mInstance.notifyControllerCallback(new androidx.media2.session.MediaControllerImplLegacy.ControllerCompatCallback.C41805(r14));
         */
        /* JADX WARNING: Code restructure failed: missing block: B:39:0x00f8, code lost:
            if (r2 == null) goto L_0x0126;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:40:0x00fa, code lost:
            r0 = r15.getCurrentPosition(r14.this$0.mInstance.mTimeDiff);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:41:0x0118, code lost:
            if (java.lang.Math.abs(r0 - r2.getCurrentPosition(r14.this$0.mInstance.mTimeDiff)) <= androidx.media2.session.MediaControllerImplLegacy.POSITION_DIFF_TOLERANCE) goto L_0x0126;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:42:0x011a, code lost:
            r14.this$0.mInstance.notifyControllerCallback(new androidx.media2.session.MediaControllerImplLegacy.ControllerCompatCallback.C41816(r14));
         */
        /* JADX WARNING: Code restructure failed: missing block: B:44:0x012a, code lost:
            if (r7.equals(r8) != false) goto L_0x0138;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:45:0x012c, code lost:
            r14.this$0.mInstance.notifyControllerCallback(new androidx.media2.session.MediaControllerImplLegacy.ControllerCompatCallback.C41827(r14));
         */
        /* JADX WARNING: Code restructure failed: missing block: B:46:0x0138, code lost:
            r7 = true;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:47:0x0141, code lost:
            if (r5.size() != r6.size()) goto L_0x0169;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:48:0x0143, code lost:
            r0 = 0;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:50:0x0148, code lost:
            if (r0 >= r6.size()) goto L_0x0168;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:52:0x0162, code lost:
            if (androidx.core.util.ObjectsCompat.equals(r5.get(r0).getCommand(), r6.get(r0).getCommand()) != false) goto L_0x0165;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:53:0x0165, code lost:
            r0 = r0 + 1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:54:0x0168, code lost:
            r7 = false;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:55:0x0169, code lost:
            if (r7 == false) goto L_0x0177;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:56:0x016b, code lost:
            r14.this$0.mInstance.notifyControllerCallback(new androidx.media2.session.MediaControllerImplLegacy.ControllerCompatCallback.C41838(r14));
         */
        /* JADX WARNING: Code restructure failed: missing block: B:57:0x0177, code lost:
            if (r3 != null) goto L_0x017a;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:58:0x0179, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:59:0x017a, code lost:
            r15 = androidx.media2.session.MediaUtils.toBufferingState(r15.getState());
         */
        /* JADX WARNING: Code restructure failed: missing block: B:60:0x0182, code lost:
            if (r2 != null) goto L_0x0185;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:61:0x0185, code lost:
            r4 = androidx.media2.session.MediaUtils.toBufferingState(r2.getState());
         */
        /* JADX WARNING: Code restructure failed: missing block: B:62:0x018d, code lost:
            if (r15 == r4) goto L_?;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:63:0x018f, code lost:
            r14.this$0.mInstance.notifyControllerCallback(new androidx.media2.session.MediaControllerImplLegacy.ControllerCompatCallback.C41849(r14));
         */
        /* JADX WARNING: Code restructure failed: missing block: B:76:?, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:77:?, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:78:?, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:79:?, code lost:
            return;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void onPlaybackStateChanged(final android.support.p064v4.media.session.PlaybackStateCompat r15) {
            /*
                r14 = this;
                androidx.media2.session.MediaControllerImplLegacy r0 = androidx.media2.session.MediaControllerImplLegacy.this
                java.lang.Object r0 = r0.mLock
                monitor-enter(r0)
                androidx.media2.session.MediaControllerImplLegacy r1 = androidx.media2.session.MediaControllerImplLegacy.this     // Catch:{ all -> 0x019c }
                boolean r1 = r1.mIsReleased     // Catch:{ all -> 0x019c }
                if (r1 == 0) goto L_0x000d
                monitor-exit(r0)     // Catch:{ all -> 0x019c }
                return
            L_0x000d:
                androidx.media2.session.MediaControllerImplLegacy r1 = androidx.media2.session.MediaControllerImplLegacy.this     // Catch:{ all -> 0x019c }
                androidx.media2.common.MediaItem r1 = r1.mCurrentMediaItem     // Catch:{ all -> 0x019c }
                androidx.media2.session.MediaControllerImplLegacy r2 = androidx.media2.session.MediaControllerImplLegacy.this     // Catch:{ all -> 0x019c }
                android.support.v4.media.session.PlaybackStateCompat r2 = r2.mPlaybackStateCompat     // Catch:{ all -> 0x019c }
                androidx.media2.session.MediaControllerImplLegacy r3 = androidx.media2.session.MediaControllerImplLegacy.this     // Catch:{ all -> 0x019c }
                r3.mPlaybackStateCompat = r15     // Catch:{ all -> 0x019c }
                androidx.media2.session.MediaControllerImplLegacy r3 = androidx.media2.session.MediaControllerImplLegacy.this     // Catch:{ all -> 0x019c }
                int r4 = androidx.media2.session.MediaUtils.convertToPlayerState(r15)     // Catch:{ all -> 0x019c }
                r3.mPlayerState = r4     // Catch:{ all -> 0x019c }
                androidx.media2.session.MediaControllerImplLegacy r3 = androidx.media2.session.MediaControllerImplLegacy.this     // Catch:{ all -> 0x019c }
                if (r15 != 0) goto L_0x0028
                r4 = -9223372036854775808
                goto L_0x002c
            L_0x0028:
                long r4 = r15.getBufferedPosition()     // Catch:{ all -> 0x019c }
            L_0x002c:
                r3.mBufferedPosition = r4     // Catch:{ all -> 0x019c }
                androidx.media2.session.MediaControllerImplLegacy r3 = androidx.media2.session.MediaControllerImplLegacy.this     // Catch:{ all -> 0x019c }
                java.util.List<android.support.v4.media.session.MediaSessionCompat$QueueItem> r3 = r3.mQueue     // Catch:{ all -> 0x019c }
                r4 = 0
                if (r3 == 0) goto L_0x006d
                if (r15 == 0) goto L_0x006d
                r3 = 0
            L_0x0038:
                androidx.media2.session.MediaControllerImplLegacy r5 = androidx.media2.session.MediaControllerImplLegacy.this     // Catch:{ all -> 0x019c }
                java.util.List<android.support.v4.media.session.MediaSessionCompat$QueueItem> r5 = r5.mQueue     // Catch:{ all -> 0x019c }
                int r5 = r5.size()     // Catch:{ all -> 0x019c }
                if (r3 >= r5) goto L_0x006d
                androidx.media2.session.MediaControllerImplLegacy r5 = androidx.media2.session.MediaControllerImplLegacy.this     // Catch:{ all -> 0x019c }
                java.util.List<android.support.v4.media.session.MediaSessionCompat$QueueItem> r5 = r5.mQueue     // Catch:{ all -> 0x019c }
                java.lang.Object r5 = r5.get(r3)     // Catch:{ all -> 0x019c }
                android.support.v4.media.session.MediaSessionCompat$QueueItem r5 = (android.support.p064v4.media.session.MediaSessionCompat.QueueItem) r5     // Catch:{ all -> 0x019c }
                long r5 = r5.getQueueId()     // Catch:{ all -> 0x019c }
                long r7 = r15.getActiveQueueItemId()     // Catch:{ all -> 0x019c }
                int r9 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
                if (r9 != 0) goto L_0x006a
                androidx.media2.session.MediaControllerImplLegacy r5 = androidx.media2.session.MediaControllerImplLegacy.this     // Catch:{ all -> 0x019c }
                r5.mCurrentMediaItemIndex = r3     // Catch:{ all -> 0x019c }
                androidx.media2.session.MediaControllerImplLegacy r5 = androidx.media2.session.MediaControllerImplLegacy.this     // Catch:{ all -> 0x019c }
                androidx.media2.session.MediaControllerImplLegacy r6 = androidx.media2.session.MediaControllerImplLegacy.this     // Catch:{ all -> 0x019c }
                java.util.List<androidx.media2.common.MediaItem> r6 = r6.mPlaylist     // Catch:{ all -> 0x019c }
                java.lang.Object r6 = r6.get(r3)     // Catch:{ all -> 0x019c }
                androidx.media2.common.MediaItem r6 = (androidx.media2.common.MediaItem) r6     // Catch:{ all -> 0x019c }
                r5.mCurrentMediaItem = r6     // Catch:{ all -> 0x019c }
            L_0x006a:
                int r3 = r3 + 1
                goto L_0x0038
            L_0x006d:
                androidx.media2.session.MediaControllerImplLegacy r3 = androidx.media2.session.MediaControllerImplLegacy.this     // Catch:{ all -> 0x019c }
                androidx.media2.common.MediaItem r3 = r3.mCurrentMediaItem     // Catch:{ all -> 0x019c }
                androidx.media2.session.MediaControllerImplLegacy r5 = androidx.media2.session.MediaControllerImplLegacy.this     // Catch:{ all -> 0x019c }
                java.util.List<androidx.media2.session.MediaSession$CommandButton> r5 = r5.mCustomLayout     // Catch:{ all -> 0x019c }
                androidx.media2.session.MediaControllerImplLegacy r6 = androidx.media2.session.MediaControllerImplLegacy.this     // Catch:{ all -> 0x019c }
                java.util.List r7 = androidx.media2.session.MediaUtils.convertToCustomLayout(r15)     // Catch:{ all -> 0x019c }
                r6.mCustomLayout = r7     // Catch:{ all -> 0x019c }
                androidx.media2.session.MediaControllerImplLegacy r6 = androidx.media2.session.MediaControllerImplLegacy.this     // Catch:{ all -> 0x019c }
                java.util.List<androidx.media2.session.MediaSession$CommandButton> r6 = r6.mCustomLayout     // Catch:{ all -> 0x019c }
                androidx.media2.session.MediaControllerImplLegacy r7 = androidx.media2.session.MediaControllerImplLegacy.this     // Catch:{ all -> 0x019c }
                androidx.media2.session.SessionCommandGroup r7 = r7.mAllowedCommands     // Catch:{ all -> 0x019c }
                androidx.media2.session.MediaControllerImplLegacy r8 = androidx.media2.session.MediaControllerImplLegacy.this     // Catch:{ all -> 0x019c }
                androidx.media2.session.MediaControllerImplLegacy r9 = androidx.media2.session.MediaControllerImplLegacy.this     // Catch:{ all -> 0x019c }
                android.support.v4.media.session.MediaControllerCompat r9 = r9.mControllerCompat     // Catch:{ all -> 0x019c }
                long r9 = r9.getFlags()     // Catch:{ all -> 0x019c }
                androidx.media2.session.MediaControllerImplLegacy r11 = androidx.media2.session.MediaControllerImplLegacy.this     // Catch:{ all -> 0x019c }
                android.support.v4.media.session.PlaybackStateCompat r11 = r11.mPlaybackStateCompat     // Catch:{ all -> 0x019c }
                androidx.media2.session.SessionCommandGroup r9 = androidx.media2.session.MediaUtils.convertToSessionCommandGroup(r9, r11)     // Catch:{ all -> 0x019c }
                r8.mAllowedCommands = r9     // Catch:{ all -> 0x019c }
                androidx.media2.session.MediaControllerImplLegacy r8 = androidx.media2.session.MediaControllerImplLegacy.this     // Catch:{ all -> 0x019c }
                androidx.media2.session.SessionCommandGroup r8 = r8.mAllowedCommands     // Catch:{ all -> 0x019c }
                monitor-exit(r0)     // Catch:{ all -> 0x019c }
                androidx.media2.session.MediaControllerImplLegacy r0 = androidx.media2.session.MediaControllerImplLegacy.this
                androidx.media2.session.MediaController r0 = r0.mInstance
                androidx.media2.session.MediaController$ControllerCallback r0 = r0.mCallback
                if (r0 != 0) goto L_0x00a7
                return
            L_0x00a7:
                if (r1 == r3) goto L_0x00b5
                androidx.media2.session.MediaControllerImplLegacy r0 = androidx.media2.session.MediaControllerImplLegacy.this
                androidx.media2.session.MediaController r0 = r0.mInstance
                androidx.media2.session.MediaControllerImplLegacy$ControllerCompatCallback$2 r1 = new androidx.media2.session.MediaControllerImplLegacy$ControllerCompatCallback$2
                r1.<init>(r3)
                r0.notifyControllerCallback(r1)
            L_0x00b5:
                if (r15 != 0) goto L_0x00c6
                if (r2 == 0) goto L_0x00c5
                androidx.media2.session.MediaControllerImplLegacy r15 = androidx.media2.session.MediaControllerImplLegacy.this
                androidx.media2.session.MediaController r15 = r15.mInstance
                androidx.media2.session.MediaControllerImplLegacy$ControllerCompatCallback$3 r0 = new androidx.media2.session.MediaControllerImplLegacy$ControllerCompatCallback$3
                r0.<init>()
                r15.notifyControllerCallback(r0)
            L_0x00c5:
                return
            L_0x00c6:
                if (r2 == 0) goto L_0x00d2
                int r0 = r2.getState()
                int r1 = r15.getState()
                if (r0 == r1) goto L_0x00de
            L_0x00d2:
                androidx.media2.session.MediaControllerImplLegacy r0 = androidx.media2.session.MediaControllerImplLegacy.this
                androidx.media2.session.MediaController r0 = r0.mInstance
                androidx.media2.session.MediaControllerImplLegacy$ControllerCompatCallback$4 r1 = new androidx.media2.session.MediaControllerImplLegacy$ControllerCompatCallback$4
                r1.<init>(r15)
                r0.notifyControllerCallback(r1)
            L_0x00de:
                if (r2 == 0) goto L_0x00ec
                float r0 = r2.getPlaybackSpeed()
                float r1 = r15.getPlaybackSpeed()
                int r0 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1))
                if (r0 == 0) goto L_0x00f8
            L_0x00ec:
                androidx.media2.session.MediaControllerImplLegacy r0 = androidx.media2.session.MediaControllerImplLegacy.this
                androidx.media2.session.MediaController r0 = r0.mInstance
                androidx.media2.session.MediaControllerImplLegacy$ControllerCompatCallback$5 r1 = new androidx.media2.session.MediaControllerImplLegacy$ControllerCompatCallback$5
                r1.<init>(r15)
                r0.notifyControllerCallback(r1)
            L_0x00f8:
                if (r2 == 0) goto L_0x0126
                androidx.media2.session.MediaControllerImplLegacy r0 = androidx.media2.session.MediaControllerImplLegacy.this
                androidx.media2.session.MediaController r0 = r0.mInstance
                java.lang.Long r0 = r0.mTimeDiff
                long r0 = r15.getCurrentPosition(r0)
                androidx.media2.session.MediaControllerImplLegacy r9 = androidx.media2.session.MediaControllerImplLegacy.this
                androidx.media2.session.MediaController r9 = r9.mInstance
                java.lang.Long r9 = r9.mTimeDiff
                long r9 = r2.getCurrentPosition(r9)
                long r9 = r0 - r9
                long r9 = java.lang.Math.abs(r9)
                r11 = 100
                int r13 = (r9 > r11 ? 1 : (r9 == r11 ? 0 : -1))
                if (r13 <= 0) goto L_0x0126
                androidx.media2.session.MediaControllerImplLegacy r9 = androidx.media2.session.MediaControllerImplLegacy.this
                androidx.media2.session.MediaController r9 = r9.mInstance
                androidx.media2.session.MediaControllerImplLegacy$ControllerCompatCallback$6 r10 = new androidx.media2.session.MediaControllerImplLegacy$ControllerCompatCallback$6
                r10.<init>(r0)
                r9.notifyControllerCallback(r10)
            L_0x0126:
                boolean r0 = r7.equals(r8)
                if (r0 != 0) goto L_0x0138
                androidx.media2.session.MediaControllerImplLegacy r0 = androidx.media2.session.MediaControllerImplLegacy.this
                androidx.media2.session.MediaController r0 = r0.mInstance
                androidx.media2.session.MediaControllerImplLegacy$ControllerCompatCallback$7 r1 = new androidx.media2.session.MediaControllerImplLegacy$ControllerCompatCallback$7
                r1.<init>(r8)
                r0.notifyControllerCallback(r1)
            L_0x0138:
                int r0 = r5.size()
                int r1 = r6.size()
                r7 = 1
                if (r0 != r1) goto L_0x0169
                r0 = 0
            L_0x0144:
                int r1 = r6.size()
                if (r0 >= r1) goto L_0x0168
                java.lang.Object r1 = r5.get(r0)
                androidx.media2.session.MediaSession$CommandButton r1 = (androidx.media2.session.MediaSession.CommandButton) r1
                androidx.media2.session.SessionCommand r1 = r1.getCommand()
                java.lang.Object r8 = r6.get(r0)
                androidx.media2.session.MediaSession$CommandButton r8 = (androidx.media2.session.MediaSession.CommandButton) r8
                androidx.media2.session.SessionCommand r8 = r8.getCommand()
                boolean r1 = androidx.core.util.ObjectsCompat.equals(r1, r8)
                if (r1 != 0) goto L_0x0165
                goto L_0x0169
            L_0x0165:
                int r0 = r0 + 1
                goto L_0x0144
            L_0x0168:
                r7 = 0
            L_0x0169:
                if (r7 == 0) goto L_0x0177
                androidx.media2.session.MediaControllerImplLegacy r0 = androidx.media2.session.MediaControllerImplLegacy.this
                androidx.media2.session.MediaController r0 = r0.mInstance
                androidx.media2.session.MediaControllerImplLegacy$ControllerCompatCallback$8 r1 = new androidx.media2.session.MediaControllerImplLegacy$ControllerCompatCallback$8
                r1.<init>(r6)
                r0.notifyControllerCallback(r1)
            L_0x0177:
                if (r3 != 0) goto L_0x017a
                return
            L_0x017a:
                int r15 = r15.getState()
                int r15 = androidx.media2.session.MediaUtils.toBufferingState(r15)
                if (r2 != 0) goto L_0x0185
                goto L_0x018d
            L_0x0185:
                int r0 = r2.getState()
                int r4 = androidx.media2.session.MediaUtils.toBufferingState(r0)
            L_0x018d:
                if (r15 == r4) goto L_0x019b
                androidx.media2.session.MediaControllerImplLegacy r0 = androidx.media2.session.MediaControllerImplLegacy.this
                androidx.media2.session.MediaController r0 = r0.mInstance
                androidx.media2.session.MediaControllerImplLegacy$ControllerCompatCallback$9 r1 = new androidx.media2.session.MediaControllerImplLegacy$ControllerCompatCallback$9
                r1.<init>(r3, r15)
                r0.notifyControllerCallback(r1)
            L_0x019b:
                return
            L_0x019c:
                r15 = move-exception
                monitor-exit(r0)     // Catch:{ all -> 0x019c }
                goto L_0x01a0
            L_0x019f:
                throw r15
            L_0x01a0:
                goto L_0x019f
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.media2.session.MediaControllerImplLegacy.ControllerCompatCallback.onPlaybackStateChanged(android.support.v4.media.session.PlaybackStateCompat):void");
        }

        /* JADX WARNING: Code restructure failed: missing block: B:10:0x001d, code lost:
            r3.this$0.mInstance.notifyControllerCallback(new androidx.media2.session.MediaControllerImplLegacy.ControllerCompatCallback.C416910(r3));
         */
        /* JADX WARNING: Code restructure failed: missing block: B:18:?, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:19:?, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:9:0x001b, code lost:
            if (r1 == r4) goto L_?;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void onMetadataChanged(android.support.p064v4.media.MediaMetadataCompat r4) {
            /*
                r3 = this;
                androidx.media2.session.MediaControllerImplLegacy r0 = androidx.media2.session.MediaControllerImplLegacy.this
                java.lang.Object r0 = r0.mLock
                monitor-enter(r0)
                androidx.media2.session.MediaControllerImplLegacy r1 = androidx.media2.session.MediaControllerImplLegacy.this     // Catch:{ all -> 0x002a }
                boolean r1 = r1.mIsReleased     // Catch:{ all -> 0x002a }
                if (r1 == 0) goto L_0x000d
                monitor-exit(r0)     // Catch:{ all -> 0x002a }
                return
            L_0x000d:
                androidx.media2.session.MediaControllerImplLegacy r1 = androidx.media2.session.MediaControllerImplLegacy.this     // Catch:{ all -> 0x002a }
                androidx.media2.common.MediaItem r1 = r1.mCurrentMediaItem     // Catch:{ all -> 0x002a }
                androidx.media2.session.MediaControllerImplLegacy r2 = androidx.media2.session.MediaControllerImplLegacy.this     // Catch:{ all -> 0x002a }
                r2.setCurrentMediaItemLocked(r4)     // Catch:{ all -> 0x002a }
                androidx.media2.session.MediaControllerImplLegacy r4 = androidx.media2.session.MediaControllerImplLegacy.this     // Catch:{ all -> 0x002a }
                androidx.media2.common.MediaItem r4 = r4.mCurrentMediaItem     // Catch:{ all -> 0x002a }
                monitor-exit(r0)     // Catch:{ all -> 0x002a }
                if (r1 == r4) goto L_0x0029
                androidx.media2.session.MediaControllerImplLegacy r0 = androidx.media2.session.MediaControllerImplLegacy.this
                androidx.media2.session.MediaController r0 = r0.mInstance
                androidx.media2.session.MediaControllerImplLegacy$ControllerCompatCallback$10 r1 = new androidx.media2.session.MediaControllerImplLegacy$ControllerCompatCallback$10
                r1.<init>(r4)
                r0.notifyControllerCallback(r1)
            L_0x0029:
                return
            L_0x002a:
                r4 = move-exception
                monitor-exit(r0)     // Catch:{ all -> 0x002a }
                throw r4
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.media2.session.MediaControllerImplLegacy.ControllerCompatCallback.onMetadataChanged(android.support.v4.media.MediaMetadataCompat):void");
        }

        public void onQueueChanged(List<MediaSessionCompat.QueueItem> list) {
            synchronized (MediaControllerImplLegacy.this.mLock) {
                if (!MediaControllerImplLegacy.this.mIsReleased) {
                    MediaControllerImplLegacy.this.mQueue = MediaUtils.removeNullElements(list);
                    if (MediaControllerImplLegacy.this.mQueue != null) {
                        if (MediaControllerImplLegacy.this.mQueue.size() != 0) {
                            MediaControllerImplLegacy.this.mPlaylist = MediaUtils.convertQueueItemListToMediaItemList(MediaControllerImplLegacy.this.mQueue);
                            final List<MediaItem> list2 = MediaControllerImplLegacy.this.mPlaylist;
                            final MediaMetadata mediaMetadata = MediaControllerImplLegacy.this.mPlaylistMetadata;
                            MediaControllerImplLegacy.this.mInstance.notifyControllerCallback(new MediaController.ControllerCallbackRunnable() {
                                public void run(MediaController.ControllerCallback controllerCallback) {
                                    controllerCallback.onPlaylistChanged(MediaControllerImplLegacy.this.mInstance, list2, mediaMetadata);
                                }
                            });
                        }
                    }
                    MediaControllerImplLegacy.this.mQueue = null;
                    MediaControllerImplLegacy.this.mPlaylist = null;
                    final List<MediaItem> list22 = MediaControllerImplLegacy.this.mPlaylist;
                    final MediaMetadata mediaMetadata2 = MediaControllerImplLegacy.this.mPlaylistMetadata;
                    MediaControllerImplLegacy.this.mInstance.notifyControllerCallback(new MediaController.ControllerCallbackRunnable() {
                        public void run(MediaController.ControllerCallback controllerCallback) {
                            controllerCallback.onPlaylistChanged(MediaControllerImplLegacy.this.mInstance, list22, mediaMetadata2);
                        }
                    });
                }
            }
        }

        public void onQueueTitleChanged(CharSequence charSequence) {
            synchronized (MediaControllerImplLegacy.this.mLock) {
                if (!MediaControllerImplLegacy.this.mIsReleased) {
                    MediaControllerImplLegacy.this.mPlaylistMetadata = MediaUtils.convertToMediaMetadata(charSequence);
                    final MediaMetadata mediaMetadata = MediaControllerImplLegacy.this.mPlaylistMetadata;
                    MediaControllerImplLegacy.this.mInstance.notifyControllerCallback(new MediaController.ControllerCallbackRunnable() {
                        public void run(MediaController.ControllerCallback controllerCallback) {
                            controllerCallback.onPlaylistMetadataChanged(MediaControllerImplLegacy.this.mInstance, mediaMetadata);
                        }
                    });
                }
            }
        }

        public void onExtrasChanged(final Bundle bundle) {
            synchronized (MediaControllerImplLegacy.this.mLock) {
                if (!MediaControllerImplLegacy.this.mIsReleased) {
                    MediaControllerImplLegacy.this.mInstance.notifyControllerCallback(new MediaController.ControllerCallbackRunnable() {
                        public void run(MediaController.ControllerCallback controllerCallback) {
                            controllerCallback.onCustomCommand(MediaControllerImplLegacy.this.mInstance, new SessionCommand(MediaControllerImplLegacy.SESSION_COMMAND_ON_EXTRAS_CHANGED, (Bundle) null), bundle);
                        }
                    });
                }
            }
        }

        public void onAudioInfoChanged(final MediaControllerCompat.PlaybackInfo playbackInfo) {
            synchronized (MediaControllerImplLegacy.this.mLock) {
                if (!MediaControllerImplLegacy.this.mIsReleased) {
                    MediaControllerImplLegacy.this.mInstance.notifyControllerCallback(new MediaController.ControllerCallbackRunnable() {
                        public void run(MediaController.ControllerCallback controllerCallback) {
                            controllerCallback.onPlaybackInfoChanged(MediaControllerImplLegacy.this.mInstance, MediaUtils.toPlaybackInfo2(playbackInfo));
                        }
                    });
                }
            }
        }

        public void onCaptioningEnabledChanged(final boolean z) {
            synchronized (MediaControllerImplLegacy.this.mLock) {
                if (!MediaControllerImplLegacy.this.mIsReleased) {
                    MediaControllerImplLegacy.this.mInstance.notifyControllerCallback(new MediaController.ControllerCallbackRunnable() {
                        public void run(MediaController.ControllerCallback controllerCallback) {
                            Bundle bundle = new Bundle();
                            bundle.putBoolean("androidx.media2.argument.CAPTIONING_ENABLED", z);
                            controllerCallback.onCustomCommand(MediaControllerImplLegacy.this.mInstance, new SessionCommand(MediaControllerImplLegacy.SESSION_COMMAND_ON_CAPTIONING_ENABLED_CHANGED, (Bundle) null), bundle);
                        }
                    });
                }
            }
        }

        public void onRepeatModeChanged(final int i) {
            synchronized (MediaControllerImplLegacy.this.mLock) {
                if (!MediaControllerImplLegacy.this.mIsReleased) {
                    MediaControllerImplLegacy.this.mRepeatMode = i;
                    MediaControllerImplLegacy.this.mInstance.notifyControllerCallback(new MediaController.ControllerCallbackRunnable() {
                        public void run(MediaController.ControllerCallback controllerCallback) {
                            controllerCallback.onRepeatModeChanged(MediaControllerImplLegacy.this.mInstance, i);
                        }
                    });
                }
            }
        }

        public void onShuffleModeChanged(final int i) {
            synchronized (MediaControllerImplLegacy.this.mLock) {
                if (!MediaControllerImplLegacy.this.mIsReleased) {
                    MediaControllerImplLegacy.this.mShuffleMode = i;
                    MediaControllerImplLegacy.this.mInstance.notifyControllerCallback(new MediaController.ControllerCallbackRunnable() {
                        public void run(MediaController.ControllerCallback controllerCallback) {
                            controllerCallback.onShuffleModeChanged(MediaControllerImplLegacy.this.mInstance, i);
                        }
                    });
                }
            }
        }
    }
}

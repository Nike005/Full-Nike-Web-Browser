package androidx.media2;

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
import android.util.Log;
import androidx.core.app.BundleCompat;
import androidx.media2.MediaController2;
import androidx.media2.SessionCommandGroup2;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.Executor;

class MediaController2ImplLegacy implements MediaController2.MediaController2Impl {
    static final boolean DEBUG = Log.isLoggable(TAG, 3);
    private static final long POSITION_DIFF_TOLERANCE = 100;
    private static final String SESSION_COMMAND_ON_CAPTIONING_ENABLED_CHANGED = "android.media.session.command.ON_CAPTIONING_ENALBED_CHANGED";
    private static final String SESSION_COMMAND_ON_EXTRA_CHANGED = "android.media.session.command.ON_EXTRA_CHANGED";
    private static final String TAG = "MC2ImplLegacy";
    static final Bundle sDefaultRootExtras;
    SessionCommandGroup2 mAllowedCommands;
    MediaBrowserCompat mBrowserCompat;
    long mBufferedPosition;
    int mBufferingState;
    final MediaController2.ControllerCallback mCallback;
    final Executor mCallbackExecutor;
    private volatile boolean mConnected;
    final Context mContext;
    private MediaControllerCompat mControllerCompat;
    private ControllerCompatCallback mControllerCompatCallback;
    MediaItem2 mCurrentMediaItem;
    int mCurrentMediaItemIndex;
    final Handler mHandler;
    final HandlerThread mHandlerThread;
    MediaController2 mInstance;
    private boolean mIsReleased;
    final Object mLock = new Object();
    MediaMetadataCompat mMediaMetadataCompat;
    MediaController2.PlaybackInfo mPlaybackInfo;
    PlaybackStateCompat mPlaybackStateCompat;
    int mPlayerState;
    List<MediaItem2> mPlaylist;
    MediaMetadata2 mPlaylistMetadata;
    List<MediaSessionCompat.QueueItem> mQueue;
    int mRepeatMode;
    int mShuffleMode;
    MediaItem2 mSkipToPlaylistItem;
    final SessionToken2 mToken;

    public void setPlaybackSpeed(float f) {
    }

    public void setPlaylist(List<MediaItem2> list, MediaMetadata2 mediaMetadata2) {
    }

    public void skipBackward() {
    }

    public void skipForward() {
    }

    public void updatePlaylistMetadata(MediaMetadata2 mediaMetadata2) {
    }

    static {
        Bundle bundle = new Bundle();
        sDefaultRootExtras = bundle;
        bundle.putBoolean("androidx.media2.root_default_root", true);
    }

    MediaController2ImplLegacy(Context context, MediaController2 mediaController2, SessionToken2 sessionToken2, Executor executor, MediaController2.ControllerCallback controllerCallback) {
        this.mContext = context;
        this.mInstance = mediaController2;
        HandlerThread handlerThread = new HandlerThread("MediaController2_Thread");
        this.mHandlerThread = handlerThread;
        handlerThread.start();
        this.mHandler = new Handler(this.mHandlerThread.getLooper());
        this.mToken = sessionToken2;
        this.mCallback = controllerCallback;
        this.mCallbackExecutor = executor;
        if (sessionToken2.getType() == 0) {
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
                if (this.mControllerCompat != null) {
                    this.mControllerCompat.unregisterCallback(this.mControllerCompatCallback);
                }
                if (this.mBrowserCompat != null) {
                    this.mBrowserCompat.disconnect();
                    this.mBrowserCompat = null;
                }
                if (this.mControllerCompat != null) {
                    this.mControllerCompat.unregisterCallback(this.mControllerCompatCallback);
                    this.mControllerCompat = null;
                }
                this.mConnected = false;
                this.mCallbackExecutor.execute(new Runnable() {
                    public void run() {
                        MediaController2ImplLegacy.this.mCallback.onDisconnected(MediaController2ImplLegacy.this.mInstance);
                    }
                });
            }
        }
    }

    public SessionToken2 getSessionToken() {
        return this.mToken;
    }

    public boolean isConnected() {
        boolean z;
        synchronized (this.mLock) {
            z = this.mConnected;
        }
        return z;
    }

    public void play() {
        synchronized (this.mLock) {
            if (!this.mConnected) {
                Log.w(TAG, "Session isn't active", new IllegalStateException());
            } else {
                this.mControllerCompat.getTransportControls().play();
            }
        }
    }

    public void pause() {
        synchronized (this.mLock) {
            if (!this.mConnected) {
                Log.w(TAG, "Session isn't active", new IllegalStateException());
            } else {
                this.mControllerCompat.getTransportControls().pause();
            }
        }
    }

    public void reset() {
        synchronized (this.mLock) {
            if (!this.mConnected) {
                Log.w(TAG, "Session isn't active", new IllegalStateException());
            } else {
                this.mControllerCompat.getTransportControls().stop();
            }
        }
    }

    public void prepare() {
        synchronized (this.mLock) {
            if (!this.mConnected) {
                Log.w(TAG, "Session isn't active", new IllegalStateException());
            } else {
                this.mControllerCompat.getTransportControls().prepare();
            }
        }
    }

    public void fastForward() {
        synchronized (this.mLock) {
            if (!this.mConnected) {
                Log.w(TAG, "Session isn't active", new IllegalStateException());
            } else {
                this.mControllerCompat.getTransportControls().fastForward();
            }
        }
    }

    public void rewind() {
        synchronized (this.mLock) {
            if (!this.mConnected) {
                Log.w(TAG, "Session isn't active", new IllegalStateException());
            } else {
                this.mControllerCompat.getTransportControls().rewind();
            }
        }
    }

    public void seekTo(long j) {
        synchronized (this.mLock) {
            if (!this.mConnected) {
                Log.w(TAG, "Session isn't active", new IllegalStateException());
            } else {
                this.mControllerCompat.getTransportControls().seekTo(j);
            }
        }
    }

    public void playFromMediaId(String str, Bundle bundle) {
        synchronized (this.mLock) {
            if (!this.mConnected) {
                Log.w(TAG, "Session isn't active", new IllegalStateException());
            } else {
                this.mControllerCompat.getTransportControls().playFromMediaId(str, bundle);
            }
        }
    }

    public void playFromSearch(String str, Bundle bundle) {
        synchronized (this.mLock) {
            if (!this.mConnected) {
                Log.w(TAG, "Session isn't active", new IllegalStateException());
            } else {
                this.mControllerCompat.getTransportControls().playFromSearch(str, bundle);
            }
        }
    }

    public void playFromUri(Uri uri, Bundle bundle) {
        synchronized (this.mLock) {
            if (!this.mConnected) {
                Log.w(TAG, "Session isn't active", new IllegalStateException());
            } else {
                this.mControllerCompat.getTransportControls().playFromUri(uri, bundle);
            }
        }
    }

    public void prepareFromMediaId(String str, Bundle bundle) {
        synchronized (this.mLock) {
            if (!this.mConnected) {
                Log.w(TAG, "Session isn't active", new IllegalStateException());
            } else {
                this.mControllerCompat.getTransportControls().prepareFromMediaId(str, bundle);
            }
        }
    }

    public void prepareFromSearch(String str, Bundle bundle) {
        synchronized (this.mLock) {
            if (!this.mConnected) {
                Log.w(TAG, "Session isn't active", new IllegalStateException());
            } else {
                this.mControllerCompat.getTransportControls().prepareFromSearch(str, bundle);
            }
        }
    }

    public void prepareFromUri(Uri uri, Bundle bundle) {
        synchronized (this.mLock) {
            if (!this.mConnected) {
                Log.w(TAG, "Session isn't active", new IllegalStateException());
            } else {
                this.mControllerCompat.getTransportControls().prepareFromUri(uri, bundle);
            }
        }
    }

    public void setVolumeTo(int i, int i2) {
        synchronized (this.mLock) {
            if (!this.mConnected) {
                Log.w(TAG, "Session isn't active", new IllegalStateException());
            } else {
                this.mControllerCompat.setVolumeTo(i, i2);
            }
        }
    }

    public void adjustVolume(int i, int i2) {
        synchronized (this.mLock) {
            if (!this.mConnected) {
                Log.w(TAG, "Session isn't active", new IllegalStateException());
            } else {
                this.mControllerCompat.adjustVolume(i, i2);
            }
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
        return -1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public long getDuration() {
        /*
            r6 = this;
            java.lang.Object r0 = r6.mLock
            monitor-enter(r0)
            boolean r1 = r6.mConnected     // Catch:{ all -> 0x0031 }
            r2 = -1
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
        throw new UnsupportedOperationException("Method not decompiled: androidx.media2.MediaController2ImplLegacy.getDuration():long");
    }

    public long getCurrentPosition() {
        synchronized (this.mLock) {
            if (!this.mConnected) {
                Log.w(TAG, "Session isn't active", new IllegalStateException());
                return -1;
            } else if (this.mPlaybackStateCompat == null) {
                return -1;
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
        throw new UnsupportedOperationException("Method not decompiled: androidx.media2.MediaController2ImplLegacy.getPlaybackSpeed():float");
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
            int r2 = androidx.media2.MediaUtils2.toBufferingState(r1)     // Catch:{ all -> 0x0027 }
        L_0x0025:
            monitor-exit(r0)     // Catch:{ all -> 0x0027 }
            return r2
        L_0x0027:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0027 }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media2.MediaController2ImplLegacy.getBufferingState():int");
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
            r2 = -1
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
        throw new UnsupportedOperationException("Method not decompiled: androidx.media2.MediaController2ImplLegacy.getBufferedPosition():long");
    }

    public MediaController2.PlaybackInfo getPlaybackInfo() {
        synchronized (this.mLock) {
            if (!this.mConnected) {
                Log.w(TAG, "Session isn't active", new IllegalStateException());
                return null;
            }
            MediaController2.PlaybackInfo playbackInfo = this.mPlaybackInfo;
            return playbackInfo;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0033, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setRating(java.lang.String r3, androidx.media2.Rating2 r4) {
        /*
            r2 = this;
            java.lang.Object r0 = r2.mLock
            monitor-enter(r0)
            boolean r1 = r2.mConnected     // Catch:{ all -> 0x0034 }
            if (r1 != 0) goto L_0x0015
            java.lang.String r3 = "MC2ImplLegacy"
            java.lang.String r4 = "Session isn't active"
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException     // Catch:{ all -> 0x0034 }
            r1.<init>()     // Catch:{ all -> 0x0034 }
            android.util.Log.w(r3, r4, r1)     // Catch:{ all -> 0x0034 }
            monitor-exit(r0)     // Catch:{ all -> 0x0034 }
            return
        L_0x0015:
            androidx.media2.MediaItem2 r1 = r2.mCurrentMediaItem     // Catch:{ all -> 0x0034 }
            if (r1 == 0) goto L_0x0032
            androidx.media2.MediaItem2 r1 = r2.mCurrentMediaItem     // Catch:{ all -> 0x0034 }
            java.lang.String r1 = r1.getMediaId()     // Catch:{ all -> 0x0034 }
            boolean r3 = r3.equals(r1)     // Catch:{ all -> 0x0034 }
            if (r3 == 0) goto L_0x0032
            android.support.v4.media.session.MediaControllerCompat r3 = r2.mControllerCompat     // Catch:{ all -> 0x0034 }
            android.support.v4.media.session.MediaControllerCompat$TransportControls r3 = r3.getTransportControls()     // Catch:{ all -> 0x0034 }
            android.support.v4.media.RatingCompat r4 = androidx.media2.MediaUtils2.convertToRatingCompat(r4)     // Catch:{ all -> 0x0034 }
            r3.setRating(r4)     // Catch:{ all -> 0x0034 }
        L_0x0032:
            monitor-exit(r0)     // Catch:{ all -> 0x0034 }
            return
        L_0x0034:
            r3 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0034 }
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media2.MediaController2ImplLegacy.setRating(java.lang.String, androidx.media2.Rating2):void");
    }

    public void sendCustomCommand(SessionCommand2 sessionCommand2, Bundle bundle, ResultReceiver resultReceiver) {
        synchronized (this.mLock) {
            if (!this.mConnected) {
                Log.w(TAG, "Session isn't active", new IllegalStateException());
            } else {
                this.mControllerCompat.sendCommand(sessionCommand2.getCustomCommand(), bundle, resultReceiver);
            }
        }
    }

    public List<MediaItem2> getPlaylist() {
        synchronized (this.mLock) {
            if (!this.mConnected) {
                Log.w(TAG, "Session isn't active", new IllegalStateException());
                return null;
            }
            List<MediaItem2> list = this.mPlaylist;
            return list;
        }
    }

    public MediaMetadata2 getPlaylistMetadata() {
        synchronized (this.mLock) {
            if (!this.mConnected) {
                Log.w(TAG, "Session isn't active", new IllegalStateException());
                return null;
            }
            MediaMetadata2 mediaMetadata2 = this.mPlaylistMetadata;
            return mediaMetadata2;
        }
    }

    public void addPlaylistItem(int i, MediaItem2 mediaItem2) {
        synchronized (this.mLock) {
            if (!this.mConnected) {
                Log.w(TAG, "Session isn't active", new IllegalStateException());
            } else {
                this.mControllerCompat.addQueueItem(MediaUtils2.convertToMediaMetadataCompat(mediaItem2.getMetadata()).getDescription(), i);
            }
        }
    }

    public void removePlaylistItem(MediaItem2 mediaItem2) {
        synchronized (this.mLock) {
            if (!this.mConnected) {
                Log.w(TAG, "Session isn't active", new IllegalStateException());
            } else {
                this.mControllerCompat.removeQueueItem(MediaUtils2.convertToQueueItem(mediaItem2).getDescription());
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0033, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void replacePlaylistItem(int r3, androidx.media2.MediaItem2 r4) {
        /*
            r2 = this;
            java.lang.Object r0 = r2.mLock
            monitor-enter(r0)
            boolean r1 = r2.mConnected     // Catch:{ all -> 0x0034 }
            if (r1 != 0) goto L_0x0015
            java.lang.String r3 = "MC2ImplLegacy"
            java.lang.String r4 = "Session isn't active"
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException     // Catch:{ all -> 0x0034 }
            r1.<init>()     // Catch:{ all -> 0x0034 }
            android.util.Log.w(r3, r4, r1)     // Catch:{ all -> 0x0034 }
            monitor-exit(r0)     // Catch:{ all -> 0x0034 }
            return
        L_0x0015:
            java.util.List<androidx.media2.MediaItem2> r1 = r2.mPlaylist     // Catch:{ all -> 0x0034 }
            if (r1 == 0) goto L_0x0032
            java.util.List<androidx.media2.MediaItem2> r1 = r2.mPlaylist     // Catch:{ all -> 0x0034 }
            int r1 = r1.size()     // Catch:{ all -> 0x0034 }
            if (r1 > r3) goto L_0x0022
            goto L_0x0032
        L_0x0022:
            java.util.List<androidx.media2.MediaItem2> r1 = r2.mPlaylist     // Catch:{ all -> 0x0034 }
            java.lang.Object r1 = r1.get(r3)     // Catch:{ all -> 0x0034 }
            androidx.media2.MediaItem2 r1 = (androidx.media2.MediaItem2) r1     // Catch:{ all -> 0x0034 }
            r2.removePlaylistItem(r1)     // Catch:{ all -> 0x0034 }
            r2.addPlaylistItem(r3, r4)     // Catch:{ all -> 0x0034 }
            monitor-exit(r0)     // Catch:{ all -> 0x0034 }
            return
        L_0x0032:
            monitor-exit(r0)     // Catch:{ all -> 0x0034 }
            return
        L_0x0034:
            r3 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0034 }
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media2.MediaController2ImplLegacy.replacePlaylistItem(int, androidx.media2.MediaItem2):void");
    }

    public MediaItem2 getCurrentMediaItem() {
        synchronized (this.mLock) {
            if (!this.mConnected) {
                Log.w(TAG, "Session isn't active", new IllegalStateException());
                return null;
            }
            MediaItem2 mediaItem2 = this.mCurrentMediaItem;
            return mediaItem2;
        }
    }

    public void skipToPreviousItem() {
        synchronized (this.mLock) {
            if (!this.mConnected) {
                Log.w(TAG, "Session isn't active", new IllegalStateException());
            } else {
                this.mControllerCompat.getTransportControls().skipToPrevious();
            }
        }
    }

    public void skipToNextItem() {
        synchronized (this.mLock) {
            if (!this.mConnected) {
                Log.w(TAG, "Session isn't active", new IllegalStateException());
            } else {
                this.mControllerCompat.getTransportControls().skipToNext();
            }
        }
    }

    public void skipToPlaylistItem(MediaItem2 mediaItem2) {
        synchronized (this.mLock) {
            if (!this.mConnected) {
                Log.w(TAG, "Session isn't active", new IllegalStateException());
                return;
            }
            this.mSkipToPlaylistItem = mediaItem2;
            this.mControllerCompat.getTransportControls().skipToQueueItem(MediaUtils2.convertToQueueItem(mediaItem2).getQueueId());
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

    public void setRepeatMode(int i) {
        synchronized (this.mLock) {
            if (!this.mConnected) {
                Log.w(TAG, "Session isn't active", new IllegalStateException());
            } else {
                this.mControllerCompat.getTransportControls().setRepeatMode(i);
            }
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

    public void setShuffleMode(int i) {
        synchronized (this.mLock) {
            if (!this.mConnected) {
                Log.w(TAG, "Session isn't active", new IllegalStateException());
            } else {
                this.mControllerCompat.getTransportControls().setShuffleMode(i);
            }
        }
    }

    public void subscribeRoutesInfo() {
        synchronized (this.mLock) {
            if (!this.mConnected) {
                Log.w(TAG, "Session isn't active", new IllegalStateException());
            } else {
                sendCommand(36);
            }
        }
    }

    public void unsubscribeRoutesInfo() {
        synchronized (this.mLock) {
            if (!this.mConnected) {
                Log.w(TAG, "Session isn't active", new IllegalStateException());
            } else {
                sendCommand(37);
            }
        }
    }

    public void selectRoute(Bundle bundle) {
        synchronized (this.mLock) {
            if (!this.mConnected) {
                Log.w(TAG, "Session isn't active", new IllegalStateException());
                return;
            }
            Bundle bundle2 = new Bundle();
            bundle2.putBundle("androidx.media2.argument.ROUTE_BUNDLE", bundle);
            sendCommand(38, bundle2);
        }
    }

    public Context getContext() {
        return this.mContext;
    }

    public MediaController2.ControllerCallback getCallback() {
        return this.mCallback;
    }

    public Executor getCallbackExecutor() {
        return this.mCallbackExecutor;
    }

    public MediaBrowserCompat getBrowserCompat() {
        MediaBrowserCompat mediaBrowserCompat;
        synchronized (this.mLock) {
            mediaBrowserCompat = this.mBrowserCompat;
        }
        return mediaBrowserCompat;
    }

    public MediaController2 getInstance() {
        return this.mInstance;
    }

    /* access modifiers changed from: package-private */
    public void onConnectedNotLocked() {
        if (DEBUG) {
            Log.d(TAG, "onConnectedNotLocked token=" + this.mToken);
        }
        final SessionCommandGroup2.Builder builder = new SessionCommandGroup2.Builder();
        synchronized (this.mLock) {
            if (!this.mIsReleased) {
                if (!this.mConnected) {
                    long flags = this.mControllerCompat.getFlags();
                    builder.addAllPlaybackCommands();
                    builder.addAllVolumeCommands();
                    builder.addAllSessionCommands();
                    builder.removeCommand(39);
                    builder.removeCommand(36);
                    builder.removeCommand(37);
                    builder.removeCommand(38);
                    if ((flags & 4) != 0) {
                        builder.addAllPlaylistCommands();
                        builder.removeCommand(19);
                        builder.removeCommand(21);
                    }
                    builder.addCommand(new SessionCommand2(SESSION_COMMAND_ON_EXTRA_CHANGED, (Bundle) null));
                    builder.addCommand(new SessionCommand2(SESSION_COMMAND_ON_CAPTIONING_ENABLED_CHANGED, (Bundle) null));
                    this.mAllowedCommands = builder.build();
                    PlaybackStateCompat playbackState = this.mControllerCompat.getPlaybackState();
                    this.mPlaybackStateCompat = playbackState;
                    if (playbackState == null) {
                        this.mPlayerState = 0;
                        this.mBufferedPosition = -1;
                    } else {
                        this.mPlayerState = MediaUtils2.convertToPlayerState(playbackState.getState());
                        this.mBufferedPosition = this.mPlaybackStateCompat.getBufferedPosition();
                    }
                    this.mPlaybackInfo = MediaUtils2.toPlaybackInfo2(this.mControllerCompat.getPlaybackInfo());
                    this.mRepeatMode = this.mControllerCompat.getRepeatMode();
                    this.mShuffleMode = this.mControllerCompat.getShuffleMode();
                    this.mPlaylist = MediaUtils2.convertQueueItemListToMediaItem2List(this.mControllerCompat.getQueue());
                    this.mPlaylistMetadata = MediaUtils2.convertToMediaMetadata2(this.mControllerCompat.getQueueTitle());
                    setCurrentMediaItemLocked(this.mControllerCompat.getMetadata());
                    this.mConnected = true;
                    this.mCallbackExecutor.execute(new Runnable() {
                        public void run() {
                            MediaController2ImplLegacy.this.mCallback.onConnected(MediaController2ImplLegacy.this.mInstance, builder.build());
                        }
                    });
                }
            }
        }
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
        this.mCallbackExecutor.execute(new Runnable() {
            public void run() {
                synchronized (MediaController2ImplLegacy.this.mLock) {
                    MediaController2ImplLegacy.this.mBrowserCompat = new MediaBrowserCompat(MediaController2ImplLegacy.this.mContext, MediaController2ImplLegacy.this.mToken.getComponentName(), new ConnectionCallback(), MediaController2ImplLegacy.sDefaultRootExtras);
                    MediaController2ImplLegacy.this.mBrowserCompat.connect();
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
        } else if (this.mPlaylist == null) {
            this.mCurrentMediaItemIndex = -1;
            this.mCurrentMediaItem = MediaUtils2.convertToMediaItem2(mediaMetadataCompat);
        } else {
            String string = mediaMetadataCompat.getString("android.media.metadata.MEDIA_ID");
            PlaybackStateCompat playbackStateCompat = this.mPlaybackStateCompat;
            int i = 0;
            if (playbackStateCompat != null) {
                UUID createUuidByQueueIdAndMediaId = MediaUtils2.createUuidByQueueIdAndMediaId(playbackStateCompat.getActiveQueueItemId(), string);
                int i2 = 0;
                while (i2 < this.mPlaylist.size()) {
                    MediaItem2 mediaItem2 = this.mPlaylist.get(i2);
                    if (mediaItem2 == null || !createUuidByQueueIdAndMediaId.equals(mediaItem2.getUuid())) {
                        i2++;
                    } else {
                        this.mCurrentMediaItem = mediaItem2;
                        this.mCurrentMediaItemIndex = i2;
                        return;
                    }
                }
            }
            if (string == null) {
                this.mCurrentMediaItemIndex = -1;
                this.mCurrentMediaItem = MediaUtils2.convertToMediaItem2(mediaMetadataCompat);
                return;
            }
            MediaItem2 mediaItem22 = this.mSkipToPlaylistItem;
            if (mediaItem22 == null || !string.equals(mediaItem22.getMediaId())) {
                while (i < this.mPlaylist.size()) {
                    MediaItem2 mediaItem23 = this.mPlaylist.get(i);
                    if (mediaItem23 == null || !string.equals(mediaItem23.getMediaId())) {
                        i++;
                    } else {
                        this.mCurrentMediaItemIndex = i;
                        this.mCurrentMediaItem = mediaItem23;
                        return;
                    }
                }
                this.mCurrentMediaItemIndex = -1;
                this.mCurrentMediaItem = MediaUtils2.convertToMediaItem2(this.mMediaMetadataCompat);
                return;
            }
            MediaItem2 mediaItem24 = this.mSkipToPlaylistItem;
            this.mCurrentMediaItem = mediaItem24;
            this.mCurrentMediaItemIndex = this.mPlaylist.indexOf(mediaItem24);
            this.mSkipToPlaylistItem = null;
        }
    }

    private class ConnectionCallback extends MediaBrowserCompat.ConnectionCallback {
        ConnectionCallback() {
        }

        public void onConnected() {
            MediaBrowserCompat browserCompat = MediaController2ImplLegacy.this.getBrowserCompat();
            if (browserCompat != null) {
                MediaController2ImplLegacy.this.connectToSession(browserCompat.getSessionToken());
            } else if (MediaController2ImplLegacy.DEBUG) {
                Log.d(MediaController2ImplLegacy.TAG, "Controller is closed prematually", new IllegalStateException());
            }
        }

        public void onConnectionSuspended() {
            MediaController2ImplLegacy.this.close();
        }

        public void onConnectionFailed() {
            MediaController2ImplLegacy.this.close();
        }
    }

    private final class ControllerCompatCallback extends MediaControllerCompat.Callback {
        ControllerCompatCallback() {
        }

        public void onSessionReady() {
            MediaController2ImplLegacy.this.onConnectedNotLocked();
        }

        public void onSessionDestroyed() {
            MediaController2ImplLegacy.this.close();
        }

        public void onSessionEvent(final String str, final Bundle bundle) {
            MediaController2ImplLegacy.this.mCallbackExecutor.execute(new Runnable() {
                public void run() {
                    MediaController2ImplLegacy.this.mCallback.onCustomCommand(MediaController2ImplLegacy.this.mInstance, new SessionCommand2(str, (Bundle) null), bundle, (ResultReceiver) null);
                }
            });
        }

        public void onPlaybackStateChanged(final PlaybackStateCompat playbackStateCompat) {
            PlaybackStateCompat playbackStateCompat2;
            int i;
            final MediaItem2 mediaItem2;
            synchronized (MediaController2ImplLegacy.this.mLock) {
                playbackStateCompat2 = MediaController2ImplLegacy.this.mPlaybackStateCompat;
                MediaController2ImplLegacy.this.mPlaybackStateCompat = playbackStateCompat;
                MediaController2ImplLegacy.this.mPlayerState = MediaUtils2.convertToPlayerState(playbackStateCompat.getState());
                MediaController2ImplLegacy.this.mBufferedPosition = playbackStateCompat.getBufferedPosition();
                i = 0;
                if (MediaController2ImplLegacy.this.mQueue != null) {
                    for (int i2 = 0; i2 < MediaController2ImplLegacy.this.mQueue.size(); i2++) {
                        if (MediaController2ImplLegacy.this.mQueue.get(i2).getQueueId() == playbackStateCompat.getActiveQueueItemId()) {
                            MediaController2ImplLegacy.this.mCurrentMediaItemIndex = i2;
                            MediaController2ImplLegacy.this.mCurrentMediaItem = MediaController2ImplLegacy.this.mPlaylist.get(i2);
                        }
                    }
                }
                mediaItem2 = MediaController2ImplLegacy.this.mCurrentMediaItem;
            }
            if (playbackStateCompat != null) {
                if (playbackStateCompat2 == null || playbackStateCompat2.getState() != playbackStateCompat.getState()) {
                    MediaController2ImplLegacy.this.mCallbackExecutor.execute(new Runnable() {
                        public void run() {
                            MediaController2ImplLegacy.this.mCallback.onPlayerStateChanged(MediaController2ImplLegacy.this.mInstance, MediaUtils2.convertToPlayerState(playbackStateCompat.getState()));
                        }
                    });
                }
                if (playbackStateCompat2 == null || playbackStateCompat2.getPlaybackSpeed() != playbackStateCompat.getPlaybackSpeed()) {
                    MediaController2ImplLegacy.this.mCallbackExecutor.execute(new Runnable() {
                        public void run() {
                            MediaController2ImplLegacy.this.mCallback.onPlaybackSpeedChanged(MediaController2ImplLegacy.this.mInstance, playbackStateCompat.getPlaybackSpeed());
                        }
                    });
                }
                if (playbackStateCompat2 != null) {
                    final long currentPosition = playbackStateCompat.getCurrentPosition(MediaController2ImplLegacy.this.mInstance.mTimeDiff);
                    if (Math.abs(currentPosition - playbackStateCompat2.getCurrentPosition(MediaController2ImplLegacy.this.mInstance.mTimeDiff)) > MediaController2ImplLegacy.POSITION_DIFF_TOLERANCE) {
                        MediaController2ImplLegacy.this.mCallbackExecutor.execute(new Runnable() {
                            public void run() {
                                MediaController2ImplLegacy.this.mCallback.onSeekCompleted(MediaController2ImplLegacy.this.mInstance, currentPosition);
                            }
                        });
                    }
                }
                final int bufferingState = MediaUtils2.toBufferingState(playbackStateCompat.getState());
                if (playbackStateCompat2 != null) {
                    i = MediaUtils2.toBufferingState(playbackStateCompat2.getState());
                }
                if (bufferingState != i) {
                    MediaController2ImplLegacy.this.mCallbackExecutor.execute(new Runnable() {
                        public void run() {
                            MediaController2ImplLegacy.this.mCallback.onBufferingStateChanged(MediaController2ImplLegacy.this.mInstance, mediaItem2, bufferingState);
                        }
                    });
                }
            } else if (playbackStateCompat2 != null) {
                MediaController2ImplLegacy.this.mCallbackExecutor.execute(new Runnable() {
                    public void run() {
                        MediaController2ImplLegacy.this.mCallback.onPlayerStateChanged(MediaController2ImplLegacy.this.mInstance, 0);
                    }
                });
            }
        }

        public void onMetadataChanged(MediaMetadataCompat mediaMetadataCompat) {
            synchronized (MediaController2ImplLegacy.this.mLock) {
                MediaController2ImplLegacy.this.setCurrentMediaItemLocked(mediaMetadataCompat);
            }
        }

        public void onQueueChanged(List<MediaSessionCompat.QueueItem> list) {
            final List<MediaItem2> list2;
            final MediaMetadata2 mediaMetadata2;
            synchronized (MediaController2ImplLegacy.this.mLock) {
                MediaController2ImplLegacy.this.mQueue = list;
                MediaController2ImplLegacy.this.mPlaylist = MediaUtils2.convertQueueItemListToMediaItem2List(list);
                list2 = MediaController2ImplLegacy.this.mPlaylist;
                mediaMetadata2 = MediaController2ImplLegacy.this.mPlaylistMetadata;
            }
            MediaController2ImplLegacy.this.mCallbackExecutor.execute(new Runnable() {
                public void run() {
                    MediaController2ImplLegacy.this.mCallback.onPlaylistChanged(MediaController2ImplLegacy.this.mInstance, list2, mediaMetadata2);
                }
            });
        }

        public void onQueueTitleChanged(CharSequence charSequence) {
            final MediaMetadata2 mediaMetadata2;
            synchronized (MediaController2ImplLegacy.this.mLock) {
                MediaController2ImplLegacy.this.mPlaylistMetadata = MediaUtils2.convertToMediaMetadata2(charSequence);
                mediaMetadata2 = MediaController2ImplLegacy.this.mPlaylistMetadata;
            }
            MediaController2ImplLegacy.this.mCallbackExecutor.execute(new Runnable() {
                public void run() {
                    MediaController2ImplLegacy.this.mCallback.onPlaylistMetadataChanged(MediaController2ImplLegacy.this.mInstance, mediaMetadata2);
                }
            });
        }

        public void onExtrasChanged(final Bundle bundle) {
            MediaController2ImplLegacy.this.mCallbackExecutor.execute(new Runnable() {
                public void run() {
                    MediaController2ImplLegacy.this.mCallback.onCustomCommand(MediaController2ImplLegacy.this.mInstance, new SessionCommand2(MediaController2ImplLegacy.SESSION_COMMAND_ON_EXTRA_CHANGED, (Bundle) null), bundle, (ResultReceiver) null);
                }
            });
        }

        public void onAudioInfoChanged(final MediaControllerCompat.PlaybackInfo playbackInfo) {
            MediaController2ImplLegacy.this.mCallbackExecutor.execute(new Runnable() {
                public void run() {
                    MediaController2ImplLegacy.this.mCallback.onPlaybackInfoChanged(MediaController2ImplLegacy.this.mInstance, MediaUtils2.toPlaybackInfo2(playbackInfo));
                }
            });
        }

        public void onCaptioningEnabledChanged(boolean z) {
            MediaController2ImplLegacy.this.mCallbackExecutor.execute(new Runnable() {
                public void run() {
                    MediaController2ImplLegacy.this.mCallback.onCustomCommand(MediaController2ImplLegacy.this.mInstance, new SessionCommand2(MediaController2ImplLegacy.SESSION_COMMAND_ON_CAPTIONING_ENABLED_CHANGED, (Bundle) null), (Bundle) null, (ResultReceiver) null);
                }
            });
        }

        public void onRepeatModeChanged(final int i) {
            synchronized (MediaController2ImplLegacy.this.mLock) {
                MediaController2ImplLegacy.this.mRepeatMode = i;
            }
            MediaController2ImplLegacy.this.mCallbackExecutor.execute(new Runnable() {
                public void run() {
                    MediaController2ImplLegacy.this.mCallback.onRepeatModeChanged(MediaController2ImplLegacy.this.mInstance, i);
                }
            });
        }

        public void onShuffleModeChanged(final int i) {
            synchronized (MediaController2ImplLegacy.this.mLock) {
                MediaController2ImplLegacy.this.mShuffleMode = i;
            }
            MediaController2ImplLegacy.this.mCallbackExecutor.execute(new Runnable() {
                public void run() {
                    MediaController2ImplLegacy.this.mCallback.onShuffleModeChanged(MediaController2ImplLegacy.this.mInstance, i);
                }
            });
        }
    }
}

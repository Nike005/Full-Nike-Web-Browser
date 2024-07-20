package androidx.media2;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.media.AudioManager;
import android.os.Build;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Process;
import android.os.RemoteException;
import android.os.ResultReceiver;
import android.os.SystemClock;
import android.support.p064v4.media.session.MediaSessionCompat;
import android.support.p064v4.media.session.PlaybackStateCompat;
import android.text.TextUtils;
import android.util.Log;
import androidx.core.util.ObjectsCompat;
import androidx.media.AudioAttributesCompat;
import androidx.media.MediaBrowserServiceCompat;
import androidx.media2.MediaController2;
import androidx.media2.MediaMetadata2;
import androidx.media2.MediaPlayerConnector;
import androidx.media2.MediaPlaylistAgent;
import androidx.media2.MediaSession2;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.Executor;

class MediaSession2ImplBase implements MediaSession2.MediaSession2Impl {
    static final boolean DEBUG = Log.isLoggable(TAG, 3);
    private static final String DEFAULT_MEDIA_SESSION_TAG_DELIM = ".";
    private static final String DEFAULT_MEDIA_SESSION_TAG_PREFIX = "android.media.session2.id";
    static final String TAG = "MS2ImplBase";
    final AudioFocusHandler mAudioFocusHandler;
    private final AudioManager mAudioManager;
    private final MediaBrowserServiceCompat mBrowserServiceLegacyStub;
    final MediaSession2.SessionCallback mCallback;
    private final Executor mCallbackExecutor;
    private final Context mContext;
    private MediaSession2.OnDataSourceMissingHelper mDsmHelper;
    private final Handler mHandler;
    private final HandlerThread mHandlerThread;
    private final MediaSession2 mInstance;
    final Object mLock = new Object();
    MediaController2.PlaybackInfo mPlaybackInfo;
    private MediaPlayerConnector mPlayer;
    private final MediaPlayerConnector.PlayerEventCallback mPlayerEventCallback;
    private MediaPlaylistAgent mPlaylistAgent;
    private final MediaPlaylistAgent.PlaylistEventCallback mPlaylistEventCallback;
    private final MediaSession2Stub mSession2Stub;
    private final PendingIntent mSessionActivity;
    private final MediaSessionCompat mSessionCompat;
    private final MediaSessionLegacyStub mSessionLegacyStub;
    private SessionPlaylistAgentImplBase mSessionPlaylistAgent;
    private final SessionToken2 mSessionToken;

    @FunctionalInterface
    interface NotifyRunnable {
        void run(MediaSession2.ControllerCb controllerCb) throws RemoteException;
    }

    public void skipBackward() {
    }

    public void skipForward() {
    }

    MediaSession2ImplBase(MediaSession2 mediaSession2, Context context, String str, MediaPlayerConnector mediaPlayerConnector, MediaPlaylistAgent mediaPlaylistAgent, PendingIntent pendingIntent, Executor executor, MediaSession2.SessionCallback sessionCallback) {
        Context context2 = context;
        String str2 = str;
        PendingIntent pendingIntent2 = pendingIntent;
        this.mContext = context2;
        this.mInstance = mediaSession2;
        HandlerThread handlerThread = new HandlerThread("MediaController2_Thread");
        this.mHandlerThread = handlerThread;
        handlerThread.start();
        this.mHandler = new Handler(this.mHandlerThread.getLooper());
        this.mSession2Stub = new MediaSession2Stub(this);
        this.mSessionActivity = pendingIntent2;
        this.mCallback = sessionCallback;
        this.mCallbackExecutor = executor;
        this.mAudioManager = (AudioManager) context2.getSystemService("audio");
        this.mPlayerEventCallback = new MyPlayerEventCallback(this);
        this.mPlaylistEventCallback = new MyPlaylistEventCallback(this);
        this.mAudioFocusHandler = new AudioFocusHandler(context2, mediaSession2);
        String serviceName = getServiceName(context2, MediaLibraryService2.SERVICE_INTERFACE, str2);
        String serviceName2 = getServiceName(context2, MediaSessionService2.SERVICE_INTERFACE, str2);
        if (serviceName2 == null || serviceName == null) {
            if (serviceName != null) {
                this.mSessionToken = new SessionToken2(new SessionToken2ImplBase(Process.myUid(), 2, context.getPackageName(), serviceName, str, this.mSession2Stub));
            } else if (serviceName2 != null) {
                this.mSessionToken = new SessionToken2(new SessionToken2ImplBase(Process.myUid(), 1, context.getPackageName(), serviceName2, str, this.mSession2Stub));
            } else {
                this.mSessionToken = new SessionToken2(new SessionToken2ImplBase(Process.myUid(), 0, context.getPackageName(), (String) null, str, this.mSession2Stub));
            }
            this.mSessionCompat = new MediaSessionCompat(context2, TextUtils.join(DEFAULT_MEDIA_SESSION_TAG_DELIM, new String[]{DEFAULT_MEDIA_SESSION_TAG_PREFIX, str2}), this.mSessionToken.toBundle());
            this.mSessionLegacyStub = new MediaSessionLegacyStub(this);
            this.mSessionCompat.setSessionActivity(pendingIntent2);
            this.mSessionCompat.setFlags(7);
            this.mSessionCompat.setActive(true);
            if (this.mSessionToken.getType() == 0) {
                this.mBrowserServiceLegacyStub = null;
            } else {
                this.mBrowserServiceLegacyStub = createLegacyBrowserService(context2, this.mSessionToken, this.mSessionCompat.getSessionToken());
            }
            updatePlayer(mediaPlayerConnector, mediaPlaylistAgent);
            this.mSessionCompat.setCallback(this.mSessionLegacyStub, this.mHandler);
            return;
        }
        throw new IllegalArgumentException("Ambiguous session type. Multiple session services define the same id=" + str2);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0010, code lost:
        r0 = createPlaybackInfo(r9);
        r1 = r8.mLock;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0016, code lost:
        monitor-enter(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x001d, code lost:
        if (r0.equals(r8.mPlaybackInfo) != false) goto L_0x0021;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x001f, code lost:
        r2 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0021, code lost:
        r2 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0022, code lost:
        r3 = r8.mPlayer;
        r4 = r8.mPlaylistAgent;
        r8.mPlayer = r9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0028, code lost:
        if (r10 != null) goto L_0x003d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x002a, code lost:
        r10 = new androidx.media2.SessionPlaylistAgentImplBase(r8, r9);
        r8.mSessionPlaylistAgent = r10;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0033, code lost:
        if (r8.mDsmHelper == null) goto L_0x003a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0035, code lost:
        r10.setOnDataSourceMissingHelper(r8.mDsmHelper);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x003a, code lost:
        r10 = r8.mSessionPlaylistAgent;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x003f, code lost:
        if (r8.mSessionPlaylistAgent == null) goto L_0x0046;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0041, code lost:
        r8.mSessionPlaylistAgent.setPlayer(r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0046, code lost:
        r8.mPlaylistAgent = r10;
        r8.mPlaybackInfo = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x004c, code lost:
        if (r3 == r8.mPlayer) goto L_0x005e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x004e, code lost:
        if (r3 == null) goto L_0x0055;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0050, code lost:
        r3.unregisterPlayerEventCallback(r8.mPlayerEventCallback);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0055, code lost:
        r8.mPlayer.registerPlayerEventCallback(r8.mCallbackExecutor, r8.mPlayerEventCallback);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0060, code lost:
        if (r4 == r8.mPlaylistAgent) goto L_0x0072;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x0062, code lost:
        if (r4 == null) goto L_0x0069;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0064, code lost:
        r4.unregisterPlaylistEventCallback(r8.mPlaylistEventCallback);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x0069, code lost:
        r8.mPlaylistAgent.registerPlaylistEventCallback(r8.mCallbackExecutor, r8.mPlaylistEventCallback);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x0072, code lost:
        monitor-exit(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x0073, code lost:
        if (r3 != null) goto L_0x007f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x0075, code lost:
        r8.mSessionCompat.setPlaybackState(createPlaybackStateCompat());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x007f, code lost:
        if (r10 == r4) goto L_0x0084;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x0081, code lost:
        notifyAgentUpdatedNotLocked(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x0084, code lost:
        if (r9 == r3) goto L_0x0089;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x0086, code lost:
        notifyPlayerUpdatedNotLocked(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x0089, code lost:
        if (r2 == false) goto L_0x008e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x008b, code lost:
        notifyPlaybackInfoChangedNotLocked(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x0090, code lost:
        if ((r9 instanceof androidx.media2.BaseRemoteMediaPlayerConnector) == false) goto L_0x00b0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x0092, code lost:
        r5 = (androidx.media2.BaseRemoteMediaPlayerConnector) r9;
        r8.mSessionCompat.setPlaybackToRemote(new androidx.media2.MediaSession2ImplBase.C37631(r8, r5.getVolumeControlType(), (int) r5.getMaxPlayerVolume(), (int) r5.getPlayerVolume()));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x00b0, code lost:
        r8.mSessionCompat.setPlaybackToLocal(getLegacyStreamType(r9.getAudioAttributes()));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:?, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void updatePlayer(androidx.media2.MediaPlayerConnector r9, androidx.media2.MediaPlaylistAgent r10) {
        /*
            r8 = this;
            if (r9 == 0) goto L_0x00c4
            java.lang.Object r0 = r8.mLock
            monitor-enter(r0)
            androidx.media2.MediaPlayerConnector r1 = r8.mPlayer     // Catch:{ all -> 0x00c1 }
            if (r9 != r1) goto L_0x000f
            androidx.media2.MediaPlaylistAgent r1 = r8.mPlaylistAgent     // Catch:{ all -> 0x00c1 }
            if (r10 != r1) goto L_0x000f
            monitor-exit(r0)     // Catch:{ all -> 0x00c1 }
            return
        L_0x000f:
            monitor-exit(r0)     // Catch:{ all -> 0x00c1 }
            androidx.media2.MediaController2$PlaybackInfo r0 = r8.createPlaybackInfo(r9)
            java.lang.Object r1 = r8.mLock
            monitor-enter(r1)
            androidx.media2.MediaController2$PlaybackInfo r2 = r8.mPlaybackInfo     // Catch:{ all -> 0x00be }
            boolean r2 = r0.equals(r2)     // Catch:{ all -> 0x00be }
            if (r2 != 0) goto L_0x0021
            r2 = 1
            goto L_0x0022
        L_0x0021:
            r2 = 0
        L_0x0022:
            androidx.media2.MediaPlayerConnector r3 = r8.mPlayer     // Catch:{ all -> 0x00be }
            androidx.media2.MediaPlaylistAgent r4 = r8.mPlaylistAgent     // Catch:{ all -> 0x00be }
            r8.mPlayer = r9     // Catch:{ all -> 0x00be }
            if (r10 != 0) goto L_0x003d
            androidx.media2.SessionPlaylistAgentImplBase r10 = new androidx.media2.SessionPlaylistAgentImplBase     // Catch:{ all -> 0x00be }
            r10.<init>(r8, r9)     // Catch:{ all -> 0x00be }
            r8.mSessionPlaylistAgent = r10     // Catch:{ all -> 0x00be }
            androidx.media2.MediaSession2$OnDataSourceMissingHelper r5 = r8.mDsmHelper     // Catch:{ all -> 0x00be }
            if (r5 == 0) goto L_0x003a
            androidx.media2.MediaSession2$OnDataSourceMissingHelper r5 = r8.mDsmHelper     // Catch:{ all -> 0x00be }
            r10.setOnDataSourceMissingHelper(r5)     // Catch:{ all -> 0x00be }
        L_0x003a:
            androidx.media2.SessionPlaylistAgentImplBase r10 = r8.mSessionPlaylistAgent     // Catch:{ all -> 0x00be }
            goto L_0x0046
        L_0x003d:
            androidx.media2.SessionPlaylistAgentImplBase r5 = r8.mSessionPlaylistAgent     // Catch:{ all -> 0x00be }
            if (r5 == 0) goto L_0x0046
            androidx.media2.SessionPlaylistAgentImplBase r5 = r8.mSessionPlaylistAgent     // Catch:{ all -> 0x00be }
            r5.setPlayer(r9)     // Catch:{ all -> 0x00be }
        L_0x0046:
            r8.mPlaylistAgent = r10     // Catch:{ all -> 0x00be }
            r8.mPlaybackInfo = r0     // Catch:{ all -> 0x00be }
            androidx.media2.MediaPlayerConnector r5 = r8.mPlayer     // Catch:{ all -> 0x00be }
            if (r3 == r5) goto L_0x005e
            if (r3 == 0) goto L_0x0055
            androidx.media2.MediaPlayerConnector$PlayerEventCallback r5 = r8.mPlayerEventCallback     // Catch:{ all -> 0x00be }
            r3.unregisterPlayerEventCallback(r5)     // Catch:{ all -> 0x00be }
        L_0x0055:
            androidx.media2.MediaPlayerConnector r5 = r8.mPlayer     // Catch:{ all -> 0x00be }
            java.util.concurrent.Executor r6 = r8.mCallbackExecutor     // Catch:{ all -> 0x00be }
            androidx.media2.MediaPlayerConnector$PlayerEventCallback r7 = r8.mPlayerEventCallback     // Catch:{ all -> 0x00be }
            r5.registerPlayerEventCallback(r6, r7)     // Catch:{ all -> 0x00be }
        L_0x005e:
            androidx.media2.MediaPlaylistAgent r5 = r8.mPlaylistAgent     // Catch:{ all -> 0x00be }
            if (r4 == r5) goto L_0x0072
            if (r4 == 0) goto L_0x0069
            androidx.media2.MediaPlaylistAgent$PlaylistEventCallback r5 = r8.mPlaylistEventCallback     // Catch:{ all -> 0x00be }
            r4.unregisterPlaylistEventCallback(r5)     // Catch:{ all -> 0x00be }
        L_0x0069:
            androidx.media2.MediaPlaylistAgent r5 = r8.mPlaylistAgent     // Catch:{ all -> 0x00be }
            java.util.concurrent.Executor r6 = r8.mCallbackExecutor     // Catch:{ all -> 0x00be }
            androidx.media2.MediaPlaylistAgent$PlaylistEventCallback r7 = r8.mPlaylistEventCallback     // Catch:{ all -> 0x00be }
            r5.registerPlaylistEventCallback(r6, r7)     // Catch:{ all -> 0x00be }
        L_0x0072:
            monitor-exit(r1)     // Catch:{ all -> 0x00be }
            if (r3 != 0) goto L_0x007f
            android.support.v4.media.session.MediaSessionCompat r10 = r8.mSessionCompat
            android.support.v4.media.session.PlaybackStateCompat r0 = r8.createPlaybackStateCompat()
            r10.setPlaybackState(r0)
            goto L_0x008e
        L_0x007f:
            if (r10 == r4) goto L_0x0084
            r8.notifyAgentUpdatedNotLocked(r4)
        L_0x0084:
            if (r9 == r3) goto L_0x0089
            r8.notifyPlayerUpdatedNotLocked(r3)
        L_0x0089:
            if (r2 == 0) goto L_0x008e
            r8.notifyPlaybackInfoChangedNotLocked(r0)
        L_0x008e:
            boolean r10 = r9 instanceof androidx.media2.BaseRemoteMediaPlayerConnector
            if (r10 == 0) goto L_0x00b0
            r5 = r9
            androidx.media2.BaseRemoteMediaPlayerConnector r5 = (androidx.media2.BaseRemoteMediaPlayerConnector) r5
            androidx.media2.MediaSession2ImplBase$1 r9 = new androidx.media2.MediaSession2ImplBase$1
            int r2 = r5.getVolumeControlType()
            float r10 = r5.getMaxPlayerVolume()
            int r3 = (int) r10
            float r10 = r5.getPlayerVolume()
            int r4 = (int) r10
            r0 = r9
            r1 = r8
            r0.<init>(r2, r3, r4, r5)
            android.support.v4.media.session.MediaSessionCompat r10 = r8.mSessionCompat
            r10.setPlaybackToRemote(r9)
            goto L_0x00bd
        L_0x00b0:
            androidx.media.AudioAttributesCompat r9 = r9.getAudioAttributes()
            int r9 = r8.getLegacyStreamType(r9)
            android.support.v4.media.session.MediaSessionCompat r10 = r8.mSessionCompat
            r10.setPlaybackToLocal(r9)
        L_0x00bd:
            return
        L_0x00be:
            r9 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x00be }
            throw r9
        L_0x00c1:
            r9 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x00c1 }
            throw r9
        L_0x00c4:
            java.lang.IllegalArgumentException r9 = new java.lang.IllegalArgumentException
            java.lang.String r10 = "player shouldn't be null"
            r9.<init>(r10)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media2.MediaSession2ImplBase.updatePlayer(androidx.media2.MediaPlayerConnector, androidx.media2.MediaPlaylistAgent):void");
    }

    /* access modifiers changed from: package-private */
    public MediaController2.PlaybackInfo createPlaybackInfo(MediaPlayerConnector mediaPlayerConnector) {
        AudioAttributesCompat audioAttributes = mediaPlayerConnector.getAudioAttributes();
        int i = 2;
        if (!(mediaPlayerConnector instanceof BaseRemoteMediaPlayerConnector)) {
            int legacyStreamType = getLegacyStreamType(audioAttributes);
            if (Build.VERSION.SDK_INT >= 21 && this.mAudioManager.isVolumeFixed()) {
                i = 0;
            }
            return MediaController2.PlaybackInfo.createPlaybackInfo(1, audioAttributes, i, this.mAudioManager.getStreamMaxVolume(legacyStreamType), this.mAudioManager.getStreamVolume(legacyStreamType));
        }
        BaseRemoteMediaPlayerConnector baseRemoteMediaPlayerConnector = (BaseRemoteMediaPlayerConnector) mediaPlayerConnector;
        return MediaController2.PlaybackInfo.createPlaybackInfo(2, audioAttributes, baseRemoteMediaPlayerConnector.getVolumeControlType(), (int) baseRemoteMediaPlayerConnector.getMaxPlayerVolume(), (int) baseRemoteMediaPlayerConnector.getPlayerVolume());
    }

    private int getLegacyStreamType(AudioAttributesCompat audioAttributesCompat) {
        int legacyStreamType;
        if (audioAttributesCompat == null || (legacyStreamType = audioAttributesCompat.getLegacyStreamType()) == Integer.MIN_VALUE) {
            return 3;
        }
        return legacyStreamType;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0044, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void close() {
        /*
            r3 = this;
            java.lang.Object r0 = r3.mLock
            monitor-enter(r0)
            androidx.media2.MediaPlayerConnector r1 = r3.mPlayer     // Catch:{ all -> 0x0045 }
            if (r1 != 0) goto L_0x0009
            monitor-exit(r0)     // Catch:{ all -> 0x0045 }
            return
        L_0x0009:
            androidx.media2.AudioFocusHandler r1 = r3.mAudioFocusHandler     // Catch:{ all -> 0x0045 }
            r1.close()     // Catch:{ all -> 0x0045 }
            androidx.media2.MediaPlayerConnector r1 = r3.mPlayer     // Catch:{ all -> 0x0045 }
            androidx.media2.MediaPlayerConnector$PlayerEventCallback r2 = r3.mPlayerEventCallback     // Catch:{ all -> 0x0045 }
            r1.unregisterPlayerEventCallback(r2)     // Catch:{ all -> 0x0045 }
            r1 = 0
            r3.mPlayer = r1     // Catch:{ all -> 0x0045 }
            android.support.v4.media.session.MediaSessionCompat r2 = r3.mSessionCompat     // Catch:{ all -> 0x0045 }
            r2.release()     // Catch:{ all -> 0x0045 }
            androidx.media2.MediaSession2ImplBase$2 r2 = new androidx.media2.MediaSession2ImplBase$2     // Catch:{ all -> 0x0045 }
            r2.<init>()     // Catch:{ all -> 0x0045 }
            r3.notifyToAllControllers(r2)     // Catch:{ all -> 0x0045 }
            android.os.Handler r2 = r3.mHandler     // Catch:{ all -> 0x0045 }
            r2.removeCallbacksAndMessages(r1)     // Catch:{ all -> 0x0045 }
            android.os.HandlerThread r1 = r3.mHandlerThread     // Catch:{ all -> 0x0045 }
            boolean r1 = r1.isAlive()     // Catch:{ all -> 0x0045 }
            if (r1 == 0) goto L_0x0043
            int r1 = android.os.Build.VERSION.SDK_INT     // Catch:{ all -> 0x0045 }
            r2 = 18
            if (r1 < r2) goto L_0x003e
            android.os.HandlerThread r1 = r3.mHandlerThread     // Catch:{ all -> 0x0045 }
            r1.quitSafely()     // Catch:{ all -> 0x0045 }
            goto L_0x0043
        L_0x003e:
            android.os.HandlerThread r1 = r3.mHandlerThread     // Catch:{ all -> 0x0045 }
            r1.quit()     // Catch:{ all -> 0x0045 }
        L_0x0043:
            monitor-exit(r0)     // Catch:{ all -> 0x0045 }
            return
        L_0x0045:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0045 }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media2.MediaSession2ImplBase.close():void");
    }

    public MediaPlayerConnector getPlayer() {
        MediaPlayerConnector mediaPlayerConnector;
        synchronized (this.mLock) {
            mediaPlayerConnector = this.mPlayer;
        }
        return mediaPlayerConnector;
    }

    public MediaPlaylistAgent getPlaylistAgent() {
        MediaPlaylistAgent mediaPlaylistAgent;
        synchronized (this.mLock) {
            mediaPlaylistAgent = this.mPlaylistAgent;
        }
        return mediaPlaylistAgent;
    }

    public SessionToken2 getToken() {
        return this.mSessionToken;
    }

    public List<MediaSession2.ControllerInfo> getConnectedControllers() {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.mSession2Stub.getConnectedControllersManager().getConnectedControllers());
        arrayList.addAll(this.mSessionLegacyStub.getConnectedControllersManager().getConnectedControllers());
        return arrayList;
    }

    public void setCustomLayout(MediaSession2.ControllerInfo controllerInfo, final List<MediaSession2.CommandButton> list) {
        if (controllerInfo == null) {
            throw new IllegalArgumentException("controller shouldn't be null");
        } else if (list != null) {
            notifyToController(controllerInfo, new NotifyRunnable() {
                public void run(MediaSession2.ControllerCb controllerCb) throws RemoteException {
                    controllerCb.onCustomLayoutChanged(list);
                }
            });
        } else {
            throw new IllegalArgumentException("layout shouldn't be null");
        }
    }

    public void setAllowedCommands(MediaSession2.ControllerInfo controllerInfo, final SessionCommandGroup2 sessionCommandGroup2) {
        if (controllerInfo == null) {
            throw new IllegalArgumentException("controller shouldn't be null");
        } else if (sessionCommandGroup2 == null) {
            throw new IllegalArgumentException("commands shouldn't be null");
        } else if (this.mSession2Stub.getConnectedControllersManager().isConnected(controllerInfo)) {
            this.mSession2Stub.getConnectedControllersManager().updateAllowedCommands(controllerInfo, sessionCommandGroup2);
            notifyToController(controllerInfo, new NotifyRunnable() {
                public void run(MediaSession2.ControllerCb controllerCb) throws RemoteException {
                    controllerCb.onAllowedCommandsChanged(sessionCommandGroup2);
                }
            });
        } else {
            this.mSessionLegacyStub.getConnectedControllersManager().updateAllowedCommands(controllerInfo, sessionCommandGroup2);
        }
    }

    public void sendCustomCommand(final SessionCommand2 sessionCommand2, final Bundle bundle) {
        if (sessionCommand2 != null) {
            notifyToAllControllers(new NotifyRunnable() {
                public void run(MediaSession2.ControllerCb controllerCb) throws RemoteException {
                    controllerCb.onCustomCommand(sessionCommand2, bundle, (ResultReceiver) null);
                }
            });
            return;
        }
        throw new IllegalArgumentException("command shouldn't be null");
    }

    public void sendCustomCommand(MediaSession2.ControllerInfo controllerInfo, final SessionCommand2 sessionCommand2, final Bundle bundle, final ResultReceiver resultReceiver) {
        if (controllerInfo == null) {
            throw new IllegalArgumentException("controller shouldn't be null");
        } else if (sessionCommand2 != null) {
            notifyToController(controllerInfo, new NotifyRunnable() {
                public void run(MediaSession2.ControllerCb controllerCb) throws RemoteException {
                    controllerCb.onCustomCommand(sessionCommand2, bundle, resultReceiver);
                }
            });
        } else {
            throw new IllegalArgumentException("command shouldn't be null");
        }
    }

    public void play() {
        MediaPlayerConnector mediaPlayerConnector;
        synchronized (this.mLock) {
            mediaPlayerConnector = this.mPlayer;
        }
        if (mediaPlayerConnector != null) {
            if (this.mAudioFocusHandler.onPlayRequested()) {
                if (mediaPlayerConnector.getPlayerState() == 0) {
                    mediaPlayerConnector.prepare();
                }
                mediaPlayerConnector.play();
                return;
            }
            Log.w(TAG, "play() wouldn't be called because of the failure in audio focus");
        } else if (DEBUG) {
            Log.d(TAG, "API calls after the close()", new IllegalStateException());
        }
    }

    public void pause() {
        MediaPlayerConnector mediaPlayerConnector;
        synchronized (this.mLock) {
            mediaPlayerConnector = this.mPlayer;
        }
        if (mediaPlayerConnector != null) {
            this.mAudioFocusHandler.onPauseRequested();
            mediaPlayerConnector.pause();
        } else if (DEBUG) {
            Log.d(TAG, "API calls after the close()", new IllegalStateException());
        }
    }

    public void reset() {
        MediaPlayerConnector mediaPlayerConnector;
        synchronized (this.mLock) {
            mediaPlayerConnector = this.mPlayer;
        }
        if (mediaPlayerConnector != null) {
            mediaPlayerConnector.reset();
        } else if (DEBUG) {
            Log.d(TAG, "API calls after the close()", new IllegalStateException());
        }
    }

    public void prepare() {
        MediaPlayerConnector mediaPlayerConnector;
        synchronized (this.mLock) {
            mediaPlayerConnector = this.mPlayer;
        }
        if (mediaPlayerConnector != null) {
            mediaPlayerConnector.prepare();
        } else if (DEBUG) {
            Log.d(TAG, "API calls after the close()", new IllegalStateException());
        }
    }

    public void seekTo(long j) {
        MediaPlayerConnector mediaPlayerConnector;
        synchronized (this.mLock) {
            mediaPlayerConnector = this.mPlayer;
        }
        if (mediaPlayerConnector != null) {
            mediaPlayerConnector.seekTo(j);
        } else if (DEBUG) {
            Log.d(TAG, "API calls after the close()", new IllegalStateException());
        }
    }

    public void notifyError(final int i, final Bundle bundle) {
        notifyToAllControllers(new NotifyRunnable() {
            public void run(MediaSession2.ControllerCb controllerCb) throws RemoteException {
                controllerCb.onError(i, bundle);
            }
        });
    }

    public void notifyRoutesInfoChanged(MediaSession2.ControllerInfo controllerInfo, final List<Bundle> list) {
        notifyToController(controllerInfo, new NotifyRunnable() {
            public void run(MediaSession2.ControllerCb controllerCb) throws RemoteException {
                controllerCb.onRoutesInfoChanged(list);
            }
        });
    }

    public int getPlayerState() {
        MediaPlayerConnector mediaPlayerConnector;
        synchronized (this.mLock) {
            mediaPlayerConnector = this.mPlayer;
        }
        if (mediaPlayerConnector != null) {
            return mediaPlayerConnector.getPlayerState();
        }
        if (!DEBUG) {
            return 3;
        }
        Log.d(TAG, "API calls after the close()", new IllegalStateException());
        return 3;
    }

    public long getCurrentPosition() {
        MediaPlayerConnector mediaPlayerConnector;
        synchronized (this.mLock) {
            mediaPlayerConnector = this.mPlayer;
        }
        if (isInPlaybackState(mediaPlayerConnector)) {
            return mediaPlayerConnector.getCurrentPosition();
        }
        if (!DEBUG) {
            return -1;
        }
        Log.d(TAG, "API calls after the close()", new IllegalStateException());
        return -1;
    }

    public long getDuration() {
        MediaPlayerConnector mediaPlayerConnector;
        synchronized (this.mLock) {
            mediaPlayerConnector = this.mPlayer;
        }
        if (isInPlaybackState(mediaPlayerConnector)) {
            return mediaPlayerConnector.getDuration();
        }
        if (!DEBUG) {
            return -1;
        }
        Log.d(TAG, "API calls after the close()", new IllegalStateException());
        return -1;
    }

    public long getBufferedPosition() {
        MediaPlayerConnector mediaPlayerConnector;
        synchronized (this.mLock) {
            mediaPlayerConnector = this.mPlayer;
        }
        if (isInPlaybackState(mediaPlayerConnector)) {
            return mediaPlayerConnector.getBufferedPosition();
        }
        if (!DEBUG) {
            return -1;
        }
        Log.d(TAG, "API calls after the close()", new IllegalStateException());
        return -1;
    }

    public int getBufferingState() {
        MediaPlayerConnector mediaPlayerConnector;
        synchronized (this.mLock) {
            mediaPlayerConnector = this.mPlayer;
        }
        if (mediaPlayerConnector != null) {
            return mediaPlayerConnector.getBufferingState();
        }
        if (!DEBUG) {
            return 0;
        }
        Log.d(TAG, "API calls after the close()", new IllegalStateException());
        return 0;
    }

    public float getPlaybackSpeed() {
        MediaPlayerConnector mediaPlayerConnector;
        synchronized (this.mLock) {
            mediaPlayerConnector = this.mPlayer;
        }
        if (isInPlaybackState(mediaPlayerConnector)) {
            return mediaPlayerConnector.getPlaybackSpeed();
        }
        if (!DEBUG) {
            return 1.0f;
        }
        Log.d(TAG, "API calls after the close()", new IllegalStateException());
        return 1.0f;
    }

    public void setPlaybackSpeed(float f) {
        MediaPlayerConnector mediaPlayerConnector;
        synchronized (this.mLock) {
            mediaPlayerConnector = this.mPlayer;
        }
        if (mediaPlayerConnector != null) {
            mediaPlayerConnector.setPlaybackSpeed(f);
        } else if (DEBUG) {
            Log.d(TAG, "API calls after the close()", new IllegalStateException());
        }
    }

    public void setOnDataSourceMissingHelper(MediaSession2.OnDataSourceMissingHelper onDataSourceMissingHelper) {
        if (onDataSourceMissingHelper != null) {
            synchronized (this.mLock) {
                this.mDsmHelper = onDataSourceMissingHelper;
                if (this.mSessionPlaylistAgent != null) {
                    this.mSessionPlaylistAgent.setOnDataSourceMissingHelper(onDataSourceMissingHelper);
                }
            }
            return;
        }
        throw new IllegalArgumentException("helper shouldn't be null");
    }

    public void clearOnDataSourceMissingHelper() {
        synchronized (this.mLock) {
            this.mDsmHelper = null;
            if (this.mSessionPlaylistAgent != null) {
                this.mSessionPlaylistAgent.clearOnDataSourceMissingHelper();
            }
        }
    }

    public List<MediaItem2> getPlaylist() {
        MediaPlaylistAgent mediaPlaylistAgent;
        synchronized (this.mLock) {
            mediaPlaylistAgent = this.mPlaylistAgent;
        }
        if (mediaPlaylistAgent != null) {
            return mediaPlaylistAgent.getPlaylist();
        }
        if (!DEBUG) {
            return null;
        }
        Log.d(TAG, "API calls after the close()", new IllegalStateException());
        return null;
    }

    public void setPlaylist(List<MediaItem2> list, MediaMetadata2 mediaMetadata2) {
        MediaPlaylistAgent mediaPlaylistAgent;
        if (list != null) {
            synchronized (this.mLock) {
                mediaPlaylistAgent = this.mPlaylistAgent;
            }
            if (mediaPlaylistAgent != null) {
                mediaPlaylistAgent.setPlaylist(list, mediaMetadata2);
            } else if (DEBUG) {
                Log.d(TAG, "API calls after the close()", new IllegalStateException());
            }
        } else {
            throw new IllegalArgumentException("list shouldn't be null");
        }
    }

    public void skipToPlaylistItem(MediaItem2 mediaItem2) {
        MediaPlaylistAgent mediaPlaylistAgent;
        if (mediaItem2 != null) {
            synchronized (this.mLock) {
                mediaPlaylistAgent = this.mPlaylistAgent;
            }
            if (mediaPlaylistAgent != null) {
                mediaPlaylistAgent.skipToPlaylistItem(mediaItem2);
            } else if (DEBUG) {
                Log.d(TAG, "API calls after the close()", new IllegalStateException());
            }
        } else {
            throw new IllegalArgumentException("item shouldn't be null");
        }
    }

    public void skipToPreviousItem() {
        MediaPlaylistAgent mediaPlaylistAgent;
        synchronized (this.mLock) {
            mediaPlaylistAgent = this.mPlaylistAgent;
        }
        if (mediaPlaylistAgent != null) {
            mediaPlaylistAgent.skipToPreviousItem();
        } else if (DEBUG) {
            Log.d(TAG, "API calls after the close()", new IllegalStateException());
        }
    }

    public void skipToNextItem() {
        MediaPlaylistAgent mediaPlaylistAgent;
        synchronized (this.mLock) {
            mediaPlaylistAgent = this.mPlaylistAgent;
        }
        if (mediaPlaylistAgent != null) {
            mediaPlaylistAgent.skipToNextItem();
        } else if (DEBUG) {
            Log.d(TAG, "API calls after the close()", new IllegalStateException());
        }
    }

    public MediaMetadata2 getPlaylistMetadata() {
        MediaPlaylistAgent mediaPlaylistAgent;
        synchronized (this.mLock) {
            mediaPlaylistAgent = this.mPlaylistAgent;
        }
        if (mediaPlaylistAgent != null) {
            return mediaPlaylistAgent.getPlaylistMetadata();
        }
        if (!DEBUG) {
            return null;
        }
        Log.d(TAG, "API calls after the close()", new IllegalStateException());
        return null;
    }

    public void addPlaylistItem(int i, MediaItem2 mediaItem2) {
        MediaPlaylistAgent mediaPlaylistAgent;
        if (i < 0) {
            throw new IllegalArgumentException("index shouldn't be negative");
        } else if (mediaItem2 != null) {
            synchronized (this.mLock) {
                mediaPlaylistAgent = this.mPlaylistAgent;
            }
            if (mediaPlaylistAgent != null) {
                mediaPlaylistAgent.addPlaylistItem(i, mediaItem2);
            } else if (DEBUG) {
                Log.d(TAG, "API calls after the close()", new IllegalStateException());
            }
        } else {
            throw new IllegalArgumentException("item shouldn't be null");
        }
    }

    public void removePlaylistItem(MediaItem2 mediaItem2) {
        MediaPlaylistAgent mediaPlaylistAgent;
        if (mediaItem2 != null) {
            synchronized (this.mLock) {
                mediaPlaylistAgent = this.mPlaylistAgent;
            }
            if (mediaPlaylistAgent != null) {
                mediaPlaylistAgent.removePlaylistItem(mediaItem2);
            } else if (DEBUG) {
                Log.d(TAG, "API calls after the close()", new IllegalStateException());
            }
        } else {
            throw new IllegalArgumentException("item shouldn't be null");
        }
    }

    public void replacePlaylistItem(int i, MediaItem2 mediaItem2) {
        MediaPlaylistAgent mediaPlaylistAgent;
        if (i < 0) {
            throw new IllegalArgumentException("index shouldn't be negative");
        } else if (mediaItem2 != null) {
            synchronized (this.mLock) {
                mediaPlaylistAgent = this.mPlaylistAgent;
            }
            if (mediaPlaylistAgent != null) {
                mediaPlaylistAgent.replacePlaylistItem(i, mediaItem2);
            } else if (DEBUG) {
                Log.d(TAG, "API calls after the close()", new IllegalStateException());
            }
        } else {
            throw new IllegalArgumentException("item shouldn't be null");
        }
    }

    public MediaItem2 getCurrentMediaItem() {
        MediaPlaylistAgent mediaPlaylistAgent;
        synchronized (this.mLock) {
            mediaPlaylistAgent = this.mPlaylistAgent;
        }
        if (mediaPlaylistAgent != null) {
            return mediaPlaylistAgent.getCurrentMediaItem();
        }
        if (!DEBUG) {
            return null;
        }
        Log.d(TAG, "API calls after the close()", new IllegalStateException());
        return null;
    }

    public void updatePlaylistMetadata(MediaMetadata2 mediaMetadata2) {
        MediaPlaylistAgent mediaPlaylistAgent;
        synchronized (this.mLock) {
            mediaPlaylistAgent = this.mPlaylistAgent;
        }
        if (mediaPlaylistAgent != null) {
            mediaPlaylistAgent.updatePlaylistMetadata(mediaMetadata2);
        } else if (DEBUG) {
            Log.d(TAG, "API calls after the close()", new IllegalStateException());
        }
    }

    public int getRepeatMode() {
        MediaPlaylistAgent mediaPlaylistAgent;
        synchronized (this.mLock) {
            mediaPlaylistAgent = this.mPlaylistAgent;
        }
        if (mediaPlaylistAgent != null) {
            return mediaPlaylistAgent.getRepeatMode();
        }
        if (!DEBUG) {
            return 0;
        }
        Log.d(TAG, "API calls after the close()", new IllegalStateException());
        return 0;
    }

    public void setRepeatMode(int i) {
        MediaPlaylistAgent mediaPlaylistAgent;
        synchronized (this.mLock) {
            mediaPlaylistAgent = this.mPlaylistAgent;
        }
        if (mediaPlaylistAgent != null) {
            mediaPlaylistAgent.setRepeatMode(i);
        } else if (DEBUG) {
            Log.d(TAG, "API calls after the close()", new IllegalStateException());
        }
    }

    public int getShuffleMode() {
        MediaPlaylistAgent mediaPlaylistAgent;
        synchronized (this.mLock) {
            mediaPlaylistAgent = this.mPlaylistAgent;
        }
        if (mediaPlaylistAgent != null) {
            return mediaPlaylistAgent.getShuffleMode();
        }
        if (!DEBUG) {
            return 0;
        }
        Log.d(TAG, "API calls after the close()", new IllegalStateException());
        return 0;
    }

    public void setShuffleMode(int i) {
        MediaPlaylistAgent mediaPlaylistAgent;
        synchronized (this.mLock) {
            mediaPlaylistAgent = this.mPlaylistAgent;
        }
        if (mediaPlaylistAgent != null) {
            mediaPlaylistAgent.setShuffleMode(i);
        } else if (DEBUG) {
            Log.d(TAG, "API calls after the close()", new IllegalStateException());
        }
    }

    public MediaSession2 getInstance() {
        return this.mInstance;
    }

    public IBinder getSessionBinder() {
        return this.mSession2Stub.asBinder();
    }

    public Context getContext() {
        return this.mContext;
    }

    public Executor getCallbackExecutor() {
        return this.mCallbackExecutor;
    }

    public MediaSession2.SessionCallback getCallback() {
        return this.mCallback;
    }

    public MediaSessionCompat getSessionCompat() {
        return this.mSessionCompat;
    }

    public AudioFocusHandler getAudioFocusHandler() {
        return this.mAudioFocusHandler;
    }

    public boolean isClosed() {
        return !this.mHandlerThread.isAlive();
    }

    public PlaybackStateCompat createPlaybackStateCompat() {
        PlaybackStateCompat build;
        synchronized (this.mLock) {
            build = new PlaybackStateCompat.Builder().setState(MediaUtils2.convertToPlaybackStateCompatState(getPlayerState(), getBufferingState()), getCurrentPosition(), getPlaybackSpeed(), SystemClock.elapsedRealtime()).setActions(3670015).setBufferedPosition(getBufferedPosition()).build();
        }
        return build;
    }

    public MediaController2.PlaybackInfo getPlaybackInfo() {
        MediaController2.PlaybackInfo playbackInfo;
        synchronized (this.mLock) {
            playbackInfo = this.mPlaybackInfo;
        }
        return playbackInfo;
    }

    public PendingIntent getSessionActivity() {
        return this.mSessionActivity;
    }

    /* access modifiers changed from: package-private */
    public MediaBrowserServiceCompat createLegacyBrowserService(Context context, SessionToken2 sessionToken2, MediaSessionCompat.Token token) {
        if (sessionToken2.getType() != 1) {
            return null;
        }
        return new MediaSessionService2LegacyStub(context, this, token);
    }

    public IBinder getLegacyBrowserServiceBinder() {
        if (this.mBrowserServiceLegacyStub == null) {
            return null;
        }
        return this.mBrowserServiceLegacyStub.onBind(new Intent(MediaBrowserServiceCompat.SERVICE_INTERFACE));
    }

    /* access modifiers changed from: package-private */
    public MediaBrowserServiceCompat getLegacyBrowserService() {
        return this.mBrowserServiceLegacyStub;
    }

    private static String getServiceName(Context context, String str, String str2) {
        PackageManager packageManager = context.getPackageManager();
        Intent intent = new Intent(str);
        intent.setPackage(context.getPackageName());
        List<ResolveInfo> queryIntentServices = packageManager.queryIntentServices(intent, 128);
        String str3 = null;
        if (queryIntentServices != null) {
            for (int i = 0; i < queryIntentServices.size(); i++) {
                String sessionId = SessionToken2.getSessionId(queryIntentServices.get(i));
                if (!(sessionId == null || !TextUtils.equals(str2, sessionId) || queryIntentServices.get(i).serviceInfo == null)) {
                    if (str3 == null) {
                        str3 = queryIntentServices.get(i).serviceInfo.name;
                    } else {
                        throw new IllegalArgumentException("Ambiguous session type. Multiple session services define the same id=" + str2);
                    }
                }
            }
        }
        return str3;
    }

    private boolean isInPlaybackState(MediaPlayerConnector mediaPlayerConnector) {
        return (mediaPlayerConnector == null || mediaPlayerConnector.getPlayerState() == 0 || mediaPlayerConnector.getPlayerState() == 3) ? false : true;
    }

    private void notifyAgentUpdatedNotLocked(MediaPlaylistAgent mediaPlaylistAgent) {
        List<MediaItem2> playlist = mediaPlaylistAgent.getPlaylist();
        final List<MediaItem2> playlist2 = getPlaylist();
        if (!ObjectsCompat.equals(playlist, playlist2)) {
            notifyToAllControllers(new NotifyRunnable() {
                public void run(MediaSession2.ControllerCb controllerCb) throws RemoteException {
                    controllerCb.onPlaylistChanged(playlist2, MediaSession2ImplBase.this.getPlaylistMetadata());
                }
            });
        } else {
            MediaMetadata2 playlistMetadata = mediaPlaylistAgent.getPlaylistMetadata();
            final MediaMetadata2 playlistMetadata2 = getPlaylistMetadata();
            if (!ObjectsCompat.equals(playlistMetadata, playlistMetadata2)) {
                notifyToAllControllers(new NotifyRunnable() {
                    public void run(MediaSession2.ControllerCb controllerCb) throws RemoteException {
                        controllerCb.onPlaylistMetadataChanged(playlistMetadata2);
                    }
                });
            }
        }
        MediaItem2 currentMediaItem = mediaPlaylistAgent.getCurrentMediaItem();
        final MediaItem2 currentMediaItem2 = getCurrentMediaItem();
        if (!ObjectsCompat.equals(currentMediaItem, currentMediaItem2)) {
            notifyToAllControllers(new NotifyRunnable() {
                public void run(MediaSession2.ControllerCb controllerCb) throws RemoteException {
                    controllerCb.onCurrentMediaItemChanged(currentMediaItem2);
                }
            });
        }
        final int repeatMode = getRepeatMode();
        if (mediaPlaylistAgent.getRepeatMode() != repeatMode) {
            notifyToAllControllers(new NotifyRunnable() {
                public void run(MediaSession2.ControllerCb controllerCb) throws RemoteException {
                    controllerCb.onRepeatModeChanged(repeatMode);
                }
            });
        }
        final int shuffleMode = getShuffleMode();
        if (mediaPlaylistAgent.getShuffleMode() != shuffleMode) {
            notifyToAllControllers(new NotifyRunnable() {
                public void run(MediaSession2.ControllerCb controllerCb) throws RemoteException {
                    controllerCb.onShuffleModeChanged(shuffleMode);
                }
            });
        }
    }

    private void notifyPlayerUpdatedNotLocked(MediaPlayerConnector mediaPlayerConnector) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        long currentPosition = getCurrentPosition();
        final int playerState = getPlayerState();
        final long j = elapsedRealtime;
        final long j2 = currentPosition;
        notifyToAllControllers(new NotifyRunnable() {
            public void run(MediaSession2.ControllerCb controllerCb) throws RemoteException {
                controllerCb.onPlayerStateChanged(j, j2, playerState);
            }
        });
        final MediaItem2 currentMediaItem = getCurrentMediaItem();
        if (currentMediaItem != null) {
            final int bufferingState = getBufferingState();
            final long bufferedPosition = getBufferedPosition();
            notifyToAllControllers(new NotifyRunnable() {
                public void run(MediaSession2.ControllerCb controllerCb) throws RemoteException {
                    controllerCb.onBufferingStateChanged(currentMediaItem, bufferingState, bufferedPosition);
                }
            });
        }
        final float playbackSpeed = getPlaybackSpeed();
        if (playbackSpeed != mediaPlayerConnector.getPlaybackSpeed()) {
            final long j3 = elapsedRealtime;
            final long j4 = currentPosition;
            notifyToAllControllers(new NotifyRunnable() {
                public void run(MediaSession2.ControllerCb controllerCb) throws RemoteException {
                    controllerCb.onPlaybackSpeedChanged(j3, j4, playbackSpeed);
                }
            });
        }
    }

    /* access modifiers changed from: package-private */
    public void notifyPlaylistChangedOnExecutor(MediaPlaylistAgent mediaPlaylistAgent, final List<MediaItem2> list, final MediaMetadata2 mediaMetadata2) {
        synchronized (this.mLock) {
            if (mediaPlaylistAgent == this.mPlaylistAgent) {
                this.mCallback.onPlaylistChanged(this.mInstance, mediaPlaylistAgent, list, mediaMetadata2);
                notifyToAllControllers(new NotifyRunnable() {
                    public void run(MediaSession2.ControllerCb controllerCb) throws RemoteException {
                        controllerCb.onPlaylistChanged(list, mediaMetadata2);
                    }
                });
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void notifyPlaylistMetadataChangedOnExecutor(MediaPlaylistAgent mediaPlaylistAgent, final MediaMetadata2 mediaMetadata2) {
        synchronized (this.mLock) {
            if (mediaPlaylistAgent == this.mPlaylistAgent) {
                this.mCallback.onPlaylistMetadataChanged(this.mInstance, mediaPlaylistAgent, mediaMetadata2);
                notifyToAllControllers(new NotifyRunnable() {
                    public void run(MediaSession2.ControllerCb controllerCb) throws RemoteException {
                        controllerCb.onPlaylistMetadataChanged(mediaMetadata2);
                    }
                });
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void notifyRepeatModeChangedOnExecutor(MediaPlaylistAgent mediaPlaylistAgent, final int i) {
        synchronized (this.mLock) {
            if (mediaPlaylistAgent == this.mPlaylistAgent) {
                this.mCallback.onRepeatModeChanged(this.mInstance, mediaPlaylistAgent, i);
                notifyToAllControllers(new NotifyRunnable() {
                    public void run(MediaSession2.ControllerCb controllerCb) throws RemoteException {
                        controllerCb.onRepeatModeChanged(i);
                    }
                });
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void notifyShuffleModeChangedOnExecutor(MediaPlaylistAgent mediaPlaylistAgent, final int i) {
        synchronized (this.mLock) {
            if (mediaPlaylistAgent == this.mPlaylistAgent) {
                this.mCallback.onShuffleModeChanged(this.mInstance, mediaPlaylistAgent, i);
                notifyToAllControllers(new NotifyRunnable() {
                    public void run(MediaSession2.ControllerCb controllerCb) throws RemoteException {
                        controllerCb.onShuffleModeChanged(i);
                    }
                });
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void notifyPlaybackInfoChangedNotLocked(final MediaController2.PlaybackInfo playbackInfo) {
        notifyToAllControllers(new NotifyRunnable() {
            public void run(MediaSession2.ControllerCb controllerCb) throws RemoteException {
                controllerCb.onPlaybackInfoChanged(playbackInfo);
            }
        });
    }

    /* access modifiers changed from: package-private */
    public void notifyToController(MediaSession2.ControllerInfo controllerInfo, NotifyRunnable notifyRunnable) {
        if (controllerInfo != null) {
            try {
                notifyRunnable.run(controllerInfo.getControllerCb());
            } catch (DeadObjectException e) {
                if (DEBUG) {
                    Log.d(TAG, controllerInfo.toString() + " is gone", e);
                }
                this.mSession2Stub.getConnectedControllersManager().removeController(controllerInfo);
            } catch (RemoteException e2) {
                Log.w(TAG, "Exception in " + controllerInfo.toString(), e2);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void notifyToAllControllers(NotifyRunnable notifyRunnable) {
        List<MediaSession2.ControllerInfo> connectedControllers = this.mSession2Stub.getConnectedControllersManager().getConnectedControllers();
        for (int i = 0; i < connectedControllers.size(); i++) {
            notifyToController(connectedControllers.get(i), notifyRunnable);
        }
        notifyToController(this.mSessionLegacyStub.getControllersForAll(), notifyRunnable);
    }

    private static class MyPlayerEventCallback extends MediaPlayerConnector.PlayerEventCallback {
        private final WeakReference<MediaSession2ImplBase> mSession;

        MyPlayerEventCallback(MediaSession2ImplBase mediaSession2ImplBase) {
            this.mSession = new WeakReference<>(mediaSession2ImplBase);
        }

        public void onCurrentDataSourceChanged(final MediaPlayerConnector mediaPlayerConnector, final DataSourceDesc2 dataSourceDesc2) {
            final MediaSession2ImplBase session = getSession();
            if (session != null) {
                session.getCallbackExecutor().execute(new Runnable() {
                    public void run() {
                        final MediaItem2 mediaItem2;
                        DataSourceDesc2 dataSourceDesc2 = dataSourceDesc2;
                        if (dataSourceDesc2 == null) {
                            mediaItem2 = null;
                        } else {
                            mediaItem2 = MyPlayerEventCallback.this.getMediaItem(session, dataSourceDesc2);
                            if (mediaItem2 == null) {
                                Log.w(MediaSession2ImplBase.TAG, "Cannot obtain media item from the dsd=" + dataSourceDesc2);
                                return;
                            }
                        }
                        session.getCallback().onCurrentMediaItemChanged(session.getInstance(), mediaPlayerConnector, mediaItem2);
                        session.notifyToAllControllers(new NotifyRunnable() {
                            public void run(MediaSession2.ControllerCb controllerCb) throws RemoteException {
                                controllerCb.onCurrentMediaItemChanged(mediaItem2);
                            }
                        });
                    }
                });
            }
        }

        public void onMediaPrepared(final MediaPlayerConnector mediaPlayerConnector, final DataSourceDesc2 dataSourceDesc2) {
            final MediaSession2ImplBase session = getSession();
            if (session != null && dataSourceDesc2 != null) {
                session.getCallbackExecutor().execute(new Runnable() {
                    public void run() {
                        MediaMetadata2 mediaMetadata2;
                        MediaItem2 mediaItem = MyPlayerEventCallback.this.getMediaItem(session, dataSourceDesc2);
                        if (mediaItem != null) {
                            if (mediaItem.equals(session.getCurrentMediaItem())) {
                                long duration = session.getDuration();
                                if (duration >= 0) {
                                    MediaMetadata2 metadata = mediaItem.getMetadata();
                                    if (metadata == null) {
                                        mediaMetadata2 = new MediaMetadata2.Builder().putLong("android.media.metadata.DURATION", duration).putString("android.media.metadata.MEDIA_ID", mediaItem.getMediaId()).build();
                                    } else if (!metadata.containsKey("android.media.metadata.DURATION")) {
                                        mediaMetadata2 = new MediaMetadata2.Builder(metadata).putLong("android.media.metadata.DURATION", duration).build();
                                    } else {
                                        long j = metadata.getLong("android.media.metadata.DURATION");
                                        if (duration != j) {
                                            Log.w(MediaSession2ImplBase.TAG, "duration mismatch for an item. duration from player=" + duration + " duration from metadata=" + j + ". May be a timing issue?");
                                        }
                                        mediaMetadata2 = null;
                                    }
                                    if (mediaMetadata2 != null) {
                                        mediaItem.setMetadata(mediaMetadata2);
                                        session.notifyToAllControllers(new NotifyRunnable() {
                                            public void run(MediaSession2.ControllerCb controllerCb) throws RemoteException {
                                                controllerCb.onPlaylistChanged(session.getPlaylist(), session.getPlaylistMetadata());
                                            }
                                        });
                                    }
                                } else {
                                    return;
                                }
                            }
                            session.getCallback().onMediaPrepared(session.getInstance(), mediaPlayerConnector, mediaItem);
                        }
                    }
                });
            }
        }

        public void onPlayerStateChanged(final MediaPlayerConnector mediaPlayerConnector, final int i) {
            final MediaSession2ImplBase session = getSession();
            if (session != null) {
                session.getCallbackExecutor().execute(new Runnable() {
                    public void run() {
                        MediaController2.PlaybackInfo playbackInfo;
                        session.mAudioFocusHandler.onPlayerStateChanged(i);
                        session.getCallback().onPlayerStateChanged(session.getInstance(), mediaPlayerConnector, i);
                        session.notifyToAllControllers(new NotifyRunnable() {
                            public void run(MediaSession2.ControllerCb controllerCb) throws RemoteException {
                                controllerCb.onPlayerStateChanged(SystemClock.elapsedRealtime(), mediaPlayerConnector.getCurrentPosition(), i);
                            }
                        });
                        MediaController2.PlaybackInfo createPlaybackInfo = session.createPlaybackInfo(mediaPlayerConnector);
                        synchronized (session.mLock) {
                            playbackInfo = session.mPlaybackInfo;
                            session.mPlaybackInfo = createPlaybackInfo;
                        }
                        if (!ObjectsCompat.equals(createPlaybackInfo.getAudioAttributes(), playbackInfo.getAudioAttributes())) {
                            session.notifyPlaybackInfoChangedNotLocked(createPlaybackInfo);
                        }
                    }
                });
            }
        }

        public void onBufferingStateChanged(MediaPlayerConnector mediaPlayerConnector, DataSourceDesc2 dataSourceDesc2, int i) {
            final MediaSession2ImplBase session = getSession();
            if (session != null && dataSourceDesc2 != null) {
                final DataSourceDesc2 dataSourceDesc22 = dataSourceDesc2;
                final MediaPlayerConnector mediaPlayerConnector2 = mediaPlayerConnector;
                final int i2 = i;
                session.getCallbackExecutor().execute(new Runnable() {
                    public void run() {
                        final MediaItem2 mediaItem = MyPlayerEventCallback.this.getMediaItem(session, dataSourceDesc22);
                        if (mediaItem != null) {
                            session.getCallback().onBufferingStateChanged(session.getInstance(), mediaPlayerConnector2, mediaItem, i2);
                            session.notifyToAllControllers(new NotifyRunnable() {
                                public void run(MediaSession2.ControllerCb controllerCb) throws RemoteException {
                                    controllerCb.onBufferingStateChanged(mediaItem, i2, mediaPlayerConnector2.getBufferedPosition());
                                }
                            });
                        }
                    }
                });
            }
        }

        public void onPlaybackSpeedChanged(final MediaPlayerConnector mediaPlayerConnector, final float f) {
            final MediaSession2ImplBase session = getSession();
            if (session != null) {
                session.getCallbackExecutor().execute(new Runnable() {
                    public void run() {
                        session.getCallback().onPlaybackSpeedChanged(session.getInstance(), mediaPlayerConnector, f);
                        session.notifyToAllControllers(new NotifyRunnable() {
                            public void run(MediaSession2.ControllerCb controllerCb) throws RemoteException {
                                controllerCb.onPlaybackSpeedChanged(SystemClock.elapsedRealtime(), session.getCurrentPosition(), f);
                            }
                        });
                    }
                });
            }
        }

        public void onSeekCompleted(MediaPlayerConnector mediaPlayerConnector, long j) {
            final MediaSession2ImplBase session = getSession();
            if (session != null) {
                final MediaPlayerConnector mediaPlayerConnector2 = mediaPlayerConnector;
                final long j2 = j;
                session.getCallbackExecutor().execute(new Runnable() {
                    public void run() {
                        session.getCallback().onSeekCompleted(session.getInstance(), mediaPlayerConnector2, j2);
                        session.notifyToAllControllers(new NotifyRunnable() {
                            public void run(MediaSession2.ControllerCb controllerCb) throws RemoteException {
                                controllerCb.onSeekCompleted(SystemClock.elapsedRealtime(), session.getCurrentPosition(), j2);
                            }
                        });
                    }
                });
            }
        }

        private MediaSession2ImplBase getSession() {
            MediaSession2ImplBase mediaSession2ImplBase = (MediaSession2ImplBase) this.mSession.get();
            if (mediaSession2ImplBase == null && MediaSession2ImplBase.DEBUG) {
                Log.d(MediaSession2ImplBase.TAG, "Session is closed", new IllegalStateException());
            }
            return mediaSession2ImplBase;
        }

        /* access modifiers changed from: package-private */
        public MediaItem2 getMediaItem(MediaSession2ImplBase mediaSession2ImplBase, DataSourceDesc2 dataSourceDesc2) {
            MediaPlaylistAgent playlistAgent = mediaSession2ImplBase.getPlaylistAgent();
            if (playlistAgent != null) {
                MediaItem2 mediaItem = playlistAgent.getMediaItem(dataSourceDesc2);
                if (mediaItem == null && MediaSession2ImplBase.DEBUG) {
                    Log.d(MediaSession2ImplBase.TAG, "Could not find matching item for dsd=" + dataSourceDesc2, new NoSuchElementException());
                }
                return mediaItem;
            } else if (!MediaSession2ImplBase.DEBUG) {
                return null;
            } else {
                Log.d(MediaSession2ImplBase.TAG, "Session is closed", new IllegalStateException());
                return null;
            }
        }
    }

    private static class MyPlaylistEventCallback extends MediaPlaylistAgent.PlaylistEventCallback {
        private final WeakReference<MediaSession2ImplBase> mSession;

        MyPlaylistEventCallback(MediaSession2ImplBase mediaSession2ImplBase) {
            this.mSession = new WeakReference<>(mediaSession2ImplBase);
        }

        public void onPlaylistChanged(MediaPlaylistAgent mediaPlaylistAgent, List<MediaItem2> list, MediaMetadata2 mediaMetadata2) {
            MediaSession2ImplBase mediaSession2ImplBase = (MediaSession2ImplBase) this.mSession.get();
            if (mediaSession2ImplBase != null) {
                mediaSession2ImplBase.notifyPlaylistChangedOnExecutor(mediaPlaylistAgent, list, mediaMetadata2);
            }
        }

        public void onPlaylistMetadataChanged(MediaPlaylistAgent mediaPlaylistAgent, MediaMetadata2 mediaMetadata2) {
            MediaSession2ImplBase mediaSession2ImplBase = (MediaSession2ImplBase) this.mSession.get();
            if (mediaSession2ImplBase != null) {
                mediaSession2ImplBase.notifyPlaylistMetadataChangedOnExecutor(mediaPlaylistAgent, mediaMetadata2);
            }
        }

        public void onRepeatModeChanged(MediaPlaylistAgent mediaPlaylistAgent, int i) {
            MediaSession2ImplBase mediaSession2ImplBase = (MediaSession2ImplBase) this.mSession.get();
            if (mediaSession2ImplBase != null) {
                mediaSession2ImplBase.notifyRepeatModeChangedOnExecutor(mediaPlaylistAgent, i);
            }
        }

        public void onShuffleModeChanged(MediaPlaylistAgent mediaPlaylistAgent, int i) {
            MediaSession2ImplBase mediaSession2ImplBase = (MediaSession2ImplBase) this.mSession.get();
            if (mediaSession2ImplBase != null) {
                mediaSession2ImplBase.notifyShuffleModeChangedOnExecutor(mediaPlaylistAgent, i);
            }
        }
    }
}

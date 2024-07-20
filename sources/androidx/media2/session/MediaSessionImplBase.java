package androidx.media2.session;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Process;
import android.os.RemoteException;
import android.os.SystemClock;
import android.support.p064v4.media.session.MediaSessionCompat;
import android.support.p064v4.media.session.PlaybackStateCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Surface;
import androidx.core.util.ObjectsCompat;
import androidx.media.AudioAttributesCompat;
import androidx.media.MediaBrowserServiceCompat;
import androidx.media.VolumeProviderCompat;
import androidx.media2.common.BaseResult;
import androidx.media2.common.MediaItem;
import androidx.media2.common.MediaMetadata;
import androidx.media2.common.SessionPlayer;
import androidx.media2.common.SubtitleData;
import androidx.media2.common.VideoSize;
import androidx.media2.session.MediaController;
import androidx.media2.session.MediaSession;
import androidx.media2.session.SequencedFutureManager;
import androidx.media2.session.futures.AbstractResolvableFuture;
import com.google.common.util.concurrent.ListenableFuture;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicInteger;

class MediaSessionImplBase implements MediaSession.MediaSessionImpl {
    static final boolean DEBUG = Log.isLoggable(TAG, 3);
    private static final String DEFAULT_MEDIA_SESSION_TAG_DELIM = ".";
    private static final String DEFAULT_MEDIA_SESSION_TAG_PREFIX = "androidx.media2.session.id";
    private static final int ITEM_NONE = -1;
    private static final SessionResult RESULT_WHEN_CLOSED = new SessionResult(1);
    private static final Object STATIC_LOCK = new Object();
    static final String TAG = "MSImplBase";
    private static boolean sComponentNamesInitialized = false;
    private static ComponentName sServiceComponentName;
    private final AudioManager mAudioManager;
    private final BroadcastReceiver mBroadcastReceiver;
    private MediaBrowserServiceCompat mBrowserServiceLegacyStub;
    final MediaSession.SessionCallback mCallback;
    final Executor mCallbackExecutor;
    private final Context mContext;
    private final Handler mHandler;
    private final HandlerThread mHandlerThread;
    private final MediaSession mInstance;
    final Object mLock = new Object();
    private final PendingIntent mMediaButtonIntent;
    MediaController.PlaybackInfo mPlaybackInfo;
    private SessionPlayer mPlayer;
    private final SessionPlayer.PlayerCallback mPlayerCallback;
    private final PendingIntent mSessionActivity;
    private final MediaSessionCompat mSessionCompat;
    private final String mSessionId;
    private final MediaSessionLegacyStub mSessionLegacyStub;
    private final MediaSessionStub mSessionStub;
    private final SessionToken mSessionToken;
    final Uri mSessionUri;

    @FunctionalInterface
    interface PlayerTask<T> {
        T run(SessionPlayer sessionPlayer) throws Exception;
    }

    @FunctionalInterface
    interface RemoteControllerTask {
        void run(MediaSession.ControllerCb controllerCb, int i) throws RemoteException;
    }

    public void updatePlayer(SessionPlayer sessionPlayer, SessionPlayer sessionPlayer2) {
    }

    MediaSessionImplBase(MediaSession mediaSession, Context context, String str, SessionPlayer sessionPlayer, PendingIntent pendingIntent, Executor executor, MediaSession.SessionCallback sessionCallback, Bundle bundle) {
        ComponentName componentName;
        ComponentName componentName2;
        this.mContext = context;
        this.mInstance = mediaSession;
        HandlerThread handlerThread = new HandlerThread("MediaSession_Thread");
        this.mHandlerThread = handlerThread;
        handlerThread.start();
        this.mHandler = new Handler(this.mHandlerThread.getLooper());
        this.mSessionStub = new MediaSessionStub(this);
        this.mSessionActivity = pendingIntent;
        this.mCallback = sessionCallback;
        this.mCallbackExecutor = executor;
        this.mAudioManager = (AudioManager) context.getSystemService("audio");
        this.mPlayerCallback = new SessionPlayerCallback(this);
        this.mSessionId = str;
        this.mSessionUri = new Uri.Builder().scheme(MediaSessionImplBase.class.getName()).appendPath(str).appendPath(String.valueOf(SystemClock.elapsedRealtime())).build();
        this.mSessionToken = new SessionToken(new SessionTokenImplBase(Process.myUid(), 0, context.getPackageName(), this.mSessionStub, bundle));
        String join = TextUtils.join(DEFAULT_MEDIA_SESSION_TAG_DELIM, new String[]{DEFAULT_MEDIA_SESSION_TAG_PREFIX, str});
        synchronized (STATIC_LOCK) {
            if (!sComponentNamesInitialized) {
                ComponentName serviceComponentByAction = getServiceComponentByAction(MediaLibraryService.SERVICE_INTERFACE);
                sServiceComponentName = serviceComponentByAction;
                if (serviceComponentByAction == null) {
                    sServiceComponentName = getServiceComponentByAction(MediaSessionService.SERVICE_INTERFACE);
                }
                sComponentNamesInitialized = true;
            }
            componentName = sServiceComponentName;
        }
        if (componentName == null) {
            Intent intent = new Intent("android.intent.action.MEDIA_BUTTON", this.mSessionUri);
            intent.setPackage(context.getPackageName());
            this.mMediaButtonIntent = PendingIntent.getBroadcast(context, 0, intent, 0);
            ComponentName componentName3 = new ComponentName(context, context.getClass());
            this.mBroadcastReceiver = new MediaButtonReceiver();
            IntentFilter intentFilter = new IntentFilter("android.intent.action.MEDIA_BUTTON");
            intentFilter.addDataScheme(this.mSessionUri.getScheme());
            context.registerReceiver(this.mBroadcastReceiver, intentFilter);
            componentName2 = componentName3;
        } else {
            Intent intent2 = new Intent("android.intent.action.MEDIA_BUTTON", this.mSessionUri);
            intent2.setComponent(componentName);
            if (Build.VERSION.SDK_INT >= 26) {
                this.mMediaButtonIntent = PendingIntent.getForegroundService(this.mContext, 0, intent2, 0);
            } else {
                this.mMediaButtonIntent = PendingIntent.getService(this.mContext, 0, intent2, 0);
            }
            this.mBroadcastReceiver = null;
            componentName2 = componentName;
        }
        this.mSessionCompat = new MediaSessionCompat(context, join, componentName2, this.mMediaButtonIntent, this.mSessionToken.getExtras(), this.mSessionToken);
        this.mSessionLegacyStub = new MediaSessionLegacyStub(this);
        this.mSessionCompat.setSessionActivity(pendingIntent);
        this.mSessionCompat.setFlags(4);
        updatePlayer(sessionPlayer);
        this.mSessionCompat.setCallback(this.mSessionLegacyStub, this.mHandler);
        this.mSessionCompat.setActive(true);
    }

    public void updatePlayer(SessionPlayer sessionPlayer) {
        boolean z;
        SessionPlayer sessionPlayer2;
        MediaController.PlaybackInfo createPlaybackInfo = createPlaybackInfo(sessionPlayer, (AudioAttributesCompat) null);
        synchronized (this.mLock) {
            z = !createPlaybackInfo.equals(this.mPlaybackInfo);
            sessionPlayer2 = this.mPlayer;
            this.mPlayer = sessionPlayer;
            this.mPlaybackInfo = createPlaybackInfo;
            if (sessionPlayer2 != sessionPlayer) {
                if (sessionPlayer2 != null) {
                    sessionPlayer2.unregisterPlayerCallback(this.mPlayerCallback);
                }
                this.mPlayer.registerPlayerCallback(this.mCallbackExecutor, this.mPlayerCallback);
            }
        }
        if (sessionPlayer2 == null) {
            this.mSessionCompat.setPlaybackState(createPlaybackStateCompat());
        } else {
            if (sessionPlayer != sessionPlayer2) {
                final int playerState = getPlayerState();
                this.mCallbackExecutor.execute(new Runnable() {
                    public void run() {
                        MediaSessionImplBase.this.mCallback.onPlayerStateChanged(MediaSessionImplBase.this.getInstance(), playerState);
                    }
                });
                notifyPlayerUpdatedNotLocked(sessionPlayer2);
            }
            if (z) {
                notifyPlaybackInfoChangedNotLocked(createPlaybackInfo);
            }
        }
        if (sessionPlayer instanceof RemoteSessionPlayer) {
            final RemoteSessionPlayer remoteSessionPlayer = (RemoteSessionPlayer) sessionPlayer;
            this.mSessionCompat.setPlaybackToRemote(new VolumeProviderCompat(remoteSessionPlayer.getVolumeControlType(), remoteSessionPlayer.getMaxVolume(), remoteSessionPlayer.getVolume()) {
                public void onSetVolumeTo(int i) {
                    remoteSessionPlayer.setVolume(i);
                }

                public void onAdjustVolume(int i) {
                    remoteSessionPlayer.adjustVolume(i);
                }
            });
            return;
        }
        this.mSessionCompat.setPlaybackToLocal(getLegacyStreamType(sessionPlayer.getAudioAttributes()));
    }

    /* access modifiers changed from: package-private */
    public MediaController.PlaybackInfo createPlaybackInfo(SessionPlayer sessionPlayer, AudioAttributesCompat audioAttributesCompat) {
        if (audioAttributesCompat == null) {
            audioAttributesCompat = sessionPlayer.getAudioAttributes();
        }
        int i = 2;
        if (!(sessionPlayer instanceof RemoteSessionPlayer)) {
            int legacyStreamType = getLegacyStreamType(audioAttributesCompat);
            if (Build.VERSION.SDK_INT >= 21 && this.mAudioManager.isVolumeFixed()) {
                i = 0;
            }
            return MediaController.PlaybackInfo.createPlaybackInfo(1, audioAttributesCompat, i, this.mAudioManager.getStreamMaxVolume(legacyStreamType), this.mAudioManager.getStreamVolume(legacyStreamType));
        }
        RemoteSessionPlayer remoteSessionPlayer = (RemoteSessionPlayer) sessionPlayer;
        return MediaController.PlaybackInfo.createPlaybackInfo(2, audioAttributesCompat, remoteSessionPlayer.getVolumeControlType(), remoteSessionPlayer.getMaxVolume(), remoteSessionPlayer.getVolume());
    }

    private int getLegacyStreamType(AudioAttributesCompat audioAttributesCompat) {
        int legacyStreamType;
        if (audioAttributesCompat == null || (legacyStreamType = audioAttributesCompat.getLegacyStreamType()) == Integer.MIN_VALUE) {
            return 3;
        }
        return legacyStreamType;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0080, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void close() {
        /*
            r4 = this;
            java.lang.Object r0 = r4.mLock
            monitor-enter(r0)
            boolean r1 = r4.isClosed()     // Catch:{ all -> 0x0081 }
            if (r1 == 0) goto L_0x000b
            monitor-exit(r0)     // Catch:{ all -> 0x0081 }
            return
        L_0x000b:
            boolean r1 = DEBUG     // Catch:{ all -> 0x0081 }
            if (r1 == 0) goto L_0x0035
            java.lang.String r1 = "MSImplBase"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x0081 }
            r2.<init>()     // Catch:{ all -> 0x0081 }
            java.lang.String r3 = "Closing session, id="
            r2.append(r3)     // Catch:{ all -> 0x0081 }
            java.lang.String r3 = r4.getId()     // Catch:{ all -> 0x0081 }
            r2.append(r3)     // Catch:{ all -> 0x0081 }
            java.lang.String r3 = ", token="
            r2.append(r3)     // Catch:{ all -> 0x0081 }
            androidx.media2.session.SessionToken r3 = r4.getToken()     // Catch:{ all -> 0x0081 }
            r2.append(r3)     // Catch:{ all -> 0x0081 }
            java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x0081 }
            android.util.Log.d(r1, r2)     // Catch:{ all -> 0x0081 }
        L_0x0035:
            androidx.media2.common.SessionPlayer r1 = r4.mPlayer     // Catch:{ all -> 0x0081 }
            androidx.media2.common.SessionPlayer$PlayerCallback r2 = r4.mPlayerCallback     // Catch:{ all -> 0x0081 }
            r1.unregisterPlayerCallback(r2)     // Catch:{ all -> 0x0081 }
            android.support.v4.media.session.MediaSessionCompat r1 = r4.mSessionCompat     // Catch:{ all -> 0x0081 }
            r1.release()     // Catch:{ all -> 0x0081 }
            android.app.PendingIntent r1 = r4.mMediaButtonIntent     // Catch:{ all -> 0x0081 }
            r1.cancel()     // Catch:{ all -> 0x0081 }
            android.content.BroadcastReceiver r1 = r4.mBroadcastReceiver     // Catch:{ all -> 0x0081 }
            if (r1 == 0) goto L_0x0051
            android.content.Context r1 = r4.mContext     // Catch:{ all -> 0x0081 }
            android.content.BroadcastReceiver r2 = r4.mBroadcastReceiver     // Catch:{ all -> 0x0081 }
            r1.unregisterReceiver(r2)     // Catch:{ all -> 0x0081 }
        L_0x0051:
            androidx.media2.session.MediaSession$SessionCallback r1 = r4.mCallback     // Catch:{ all -> 0x0081 }
            androidx.media2.session.MediaSession r2 = r4.mInstance     // Catch:{ all -> 0x0081 }
            r1.onSessionClosed(r2)     // Catch:{ all -> 0x0081 }
            androidx.media2.session.MediaSessionImplBase$3 r1 = new androidx.media2.session.MediaSessionImplBase$3     // Catch:{ all -> 0x0081 }
            r1.<init>()     // Catch:{ all -> 0x0081 }
            r4.dispatchRemoteControllerTaskWithoutReturn(r1)     // Catch:{ all -> 0x0081 }
            android.os.Handler r1 = r4.mHandler     // Catch:{ all -> 0x0081 }
            r2 = 0
            r1.removeCallbacksAndMessages(r2)     // Catch:{ all -> 0x0081 }
            android.os.HandlerThread r1 = r4.mHandlerThread     // Catch:{ all -> 0x0081 }
            boolean r1 = r1.isAlive()     // Catch:{ all -> 0x0081 }
            if (r1 == 0) goto L_0x007f
            int r1 = android.os.Build.VERSION.SDK_INT     // Catch:{ all -> 0x0081 }
            r2 = 18
            if (r1 < r2) goto L_0x007a
            android.os.HandlerThread r1 = r4.mHandlerThread     // Catch:{ all -> 0x0081 }
            r1.quitSafely()     // Catch:{ all -> 0x0081 }
            goto L_0x007f
        L_0x007a:
            android.os.HandlerThread r1 = r4.mHandlerThread     // Catch:{ all -> 0x0081 }
            r1.quit()     // Catch:{ all -> 0x0081 }
        L_0x007f:
            monitor-exit(r0)     // Catch:{ all -> 0x0081 }
            return
        L_0x0081:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0081 }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media2.session.MediaSessionImplBase.close():void");
    }

    public SessionPlayer getPlayer() {
        SessionPlayer sessionPlayer;
        synchronized (this.mLock) {
            sessionPlayer = this.mPlayer;
        }
        return sessionPlayer;
    }

    public String getId() {
        return this.mSessionId;
    }

    public Uri getUri() {
        return this.mSessionUri;
    }

    public SessionToken getToken() {
        return this.mSessionToken;
    }

    public List<MediaSession.ControllerInfo> getConnectedControllers() {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.mSessionStub.getConnectedControllersManager().getConnectedControllers());
        arrayList.addAll(this.mSessionLegacyStub.getConnectedControllersManager().getConnectedControllers());
        return arrayList;
    }

    public boolean isConnected(MediaSession.ControllerInfo controllerInfo) {
        if (controllerInfo == null) {
            return false;
        }
        if (!controllerInfo.equals(this.mSessionLegacyStub.getControllersForAll()) && !this.mSessionStub.getConnectedControllersManager().isConnected(controllerInfo) && !this.mSessionLegacyStub.getConnectedControllersManager().isConnected(controllerInfo)) {
            return false;
        }
        return true;
    }

    public ListenableFuture<SessionResult> setCustomLayout(MediaSession.ControllerInfo controllerInfo, final List<MediaSession.CommandButton> list) {
        return dispatchRemoteControllerTask(controllerInfo, new RemoteControllerTask() {
            public void run(MediaSession.ControllerCb controllerCb, int i) throws RemoteException {
                controllerCb.setCustomLayout(i, list);
            }
        });
    }

    public void setAllowedCommands(MediaSession.ControllerInfo controllerInfo, final SessionCommandGroup sessionCommandGroup) {
        if (this.mSessionStub.getConnectedControllersManager().isConnected(controllerInfo)) {
            this.mSessionStub.getConnectedControllersManager().updateAllowedCommands(controllerInfo, sessionCommandGroup);
            dispatchRemoteControllerTaskWithoutReturn(controllerInfo, new RemoteControllerTask() {
                public void run(MediaSession.ControllerCb controllerCb, int i) throws RemoteException {
                    controllerCb.onAllowedCommandsChanged(i, sessionCommandGroup);
                }
            });
            return;
        }
        this.mSessionLegacyStub.getConnectedControllersManager().updateAllowedCommands(controllerInfo, sessionCommandGroup);
    }

    public void broadcastCustomCommand(final SessionCommand sessionCommand, final Bundle bundle) {
        dispatchRemoteControllerTaskWithoutReturn(new RemoteControllerTask() {
            public void run(MediaSession.ControllerCb controllerCb, int i) throws RemoteException {
                controllerCb.sendCustomCommand(i, sessionCommand, bundle);
            }
        });
    }

    public ListenableFuture<SessionResult> sendCustomCommand(MediaSession.ControllerInfo controllerInfo, final SessionCommand sessionCommand, final Bundle bundle) {
        return dispatchRemoteControllerTask(controllerInfo, new RemoteControllerTask() {
            public void run(MediaSession.ControllerCb controllerCb, int i) throws RemoteException {
                controllerCb.sendCustomCommand(i, sessionCommand, bundle);
            }
        });
    }

    public ListenableFuture<SessionPlayer.PlayerResult> play() {
        return dispatchPlayerTask(new PlayerTask<ListenableFuture<SessionPlayer.PlayerResult>>() {
            public ListenableFuture<SessionPlayer.PlayerResult> run(SessionPlayer sessionPlayer) throws Exception {
                if (sessionPlayer.getPlayerState() != 0) {
                    return sessionPlayer.play();
                }
                ListenableFuture<SessionPlayer.PlayerResult> prepare = sessionPlayer.prepare();
                ListenableFuture<SessionPlayer.PlayerResult> play = sessionPlayer.play();
                if (prepare == null || play == null) {
                    return null;
                }
                return CombinedCommandResultFuture.create(MediaUtils.DIRECT_EXECUTOR, prepare, play);
            }
        });
    }

    public ListenableFuture<SessionPlayer.PlayerResult> pause() {
        return dispatchPlayerTask(new PlayerTask<ListenableFuture<SessionPlayer.PlayerResult>>() {
            public ListenableFuture<SessionPlayer.PlayerResult> run(SessionPlayer sessionPlayer) throws Exception {
                return sessionPlayer.pause();
            }
        });
    }

    public ListenableFuture<SessionPlayer.PlayerResult> prepare() {
        return dispatchPlayerTask(new PlayerTask<ListenableFuture<SessionPlayer.PlayerResult>>() {
            public ListenableFuture<SessionPlayer.PlayerResult> run(SessionPlayer sessionPlayer) throws Exception {
                return sessionPlayer.prepare();
            }
        });
    }

    public ListenableFuture<SessionPlayer.PlayerResult> seekTo(final long j) {
        return dispatchPlayerTask(new PlayerTask<ListenableFuture<SessionPlayer.PlayerResult>>() {
            public ListenableFuture<SessionPlayer.PlayerResult> run(SessionPlayer sessionPlayer) throws Exception {
                return sessionPlayer.seekTo(j);
            }
        });
    }

    public int getPlayerState() {
        return ((Integer) dispatchPlayerTask(new PlayerTask<Integer>() {
            public Integer run(SessionPlayer sessionPlayer) throws Exception {
                return Integer.valueOf(sessionPlayer.getPlayerState());
            }
        }, 3)).intValue();
    }

    public long getCurrentPosition() {
        return ((Long) dispatchPlayerTask(new PlayerTask<Long>() {
            public Long run(SessionPlayer sessionPlayer) throws Exception {
                if (MediaSessionImplBase.this.isInPlaybackState(sessionPlayer)) {
                    return Long.valueOf(sessionPlayer.getCurrentPosition());
                }
                return null;
            }
        }, Long.MIN_VALUE)).longValue();
    }

    public long getDuration() {
        return ((Long) dispatchPlayerTask(new PlayerTask<Long>() {
            public Long run(SessionPlayer sessionPlayer) throws Exception {
                if (MediaSessionImplBase.this.isInPlaybackState(sessionPlayer)) {
                    return Long.valueOf(sessionPlayer.getDuration());
                }
                return null;
            }
        }, Long.MIN_VALUE)).longValue();
    }

    public long getBufferedPosition() {
        return ((Long) dispatchPlayerTask(new PlayerTask<Long>() {
            public Long run(SessionPlayer sessionPlayer) throws Exception {
                if (MediaSessionImplBase.this.isInPlaybackState(sessionPlayer)) {
                    return Long.valueOf(sessionPlayer.getBufferedPosition());
                }
                return null;
            }
        }, Long.MIN_VALUE)).longValue();
    }

    public int getBufferingState() {
        return ((Integer) dispatchPlayerTask(new PlayerTask<Integer>() {
            public Integer run(SessionPlayer sessionPlayer) throws Exception {
                return Integer.valueOf(sessionPlayer.getBufferingState());
            }
        }, 0)).intValue();
    }

    public float getPlaybackSpeed() {
        return ((Float) dispatchPlayerTask(new PlayerTask<Float>() {
            public Float run(SessionPlayer sessionPlayer) throws Exception {
                if (MediaSessionImplBase.this.isInPlaybackState(sessionPlayer)) {
                    return Float.valueOf(sessionPlayer.getPlaybackSpeed());
                }
                return null;
            }
        }, Float.valueOf(1.0f))).floatValue();
    }

    public ListenableFuture<SessionPlayer.PlayerResult> setPlaybackSpeed(final float f) {
        return dispatchPlayerTask(new PlayerTask<ListenableFuture<SessionPlayer.PlayerResult>>() {
            public ListenableFuture<SessionPlayer.PlayerResult> run(SessionPlayer sessionPlayer) throws Exception {
                return sessionPlayer.setPlaybackSpeed(f);
            }
        });
    }

    public List<MediaItem> getPlaylist() {
        return (List) dispatchPlayerTask(new PlayerTask<List<MediaItem>>() {
            public List<MediaItem> run(SessionPlayer sessionPlayer) throws Exception {
                return sessionPlayer.getPlaylist();
            }
        }, (Object) null);
    }

    public ListenableFuture<SessionPlayer.PlayerResult> setPlaylist(final List<MediaItem> list, final MediaMetadata mediaMetadata) {
        if (list != null) {
            return dispatchPlayerTask(new PlayerTask<ListenableFuture<SessionPlayer.PlayerResult>>() {
                public ListenableFuture<SessionPlayer.PlayerResult> run(SessionPlayer sessionPlayer) throws Exception {
                    return sessionPlayer.setPlaylist(list, mediaMetadata);
                }
            });
        }
        throw new NullPointerException("list shouldn't be null");
    }

    public ListenableFuture<SessionPlayer.PlayerResult> setMediaItem(final MediaItem mediaItem) {
        if (mediaItem != null) {
            return dispatchPlayerTask(new PlayerTask<ListenableFuture<SessionPlayer.PlayerResult>>() {
                public ListenableFuture<SessionPlayer.PlayerResult> run(SessionPlayer sessionPlayer) throws Exception {
                    return sessionPlayer.setMediaItem(mediaItem);
                }
            });
        }
        throw new NullPointerException("item shouldn't be null");
    }

    public ListenableFuture<SessionPlayer.PlayerResult> skipToPlaylistItem(final int i) {
        if (i >= 0) {
            return dispatchPlayerTask(new PlayerTask<ListenableFuture<SessionPlayer.PlayerResult>>() {
                public ListenableFuture<SessionPlayer.PlayerResult> run(SessionPlayer sessionPlayer) throws Exception {
                    if (i >= sessionPlayer.getPlaylist().size()) {
                        return SessionPlayer.PlayerResult.createFuture(-3);
                    }
                    return sessionPlayer.skipToPlaylistItem(i);
                }
            });
        }
        throw new IllegalArgumentException("index shouldn't be negative");
    }

    public ListenableFuture<SessionPlayer.PlayerResult> skipToPreviousItem() {
        return dispatchPlayerTask(new PlayerTask<ListenableFuture<SessionPlayer.PlayerResult>>() {
            public ListenableFuture<SessionPlayer.PlayerResult> run(SessionPlayer sessionPlayer) throws Exception {
                return sessionPlayer.skipToPreviousPlaylistItem();
            }
        });
    }

    public ListenableFuture<SessionPlayer.PlayerResult> skipToNextItem() {
        return dispatchPlayerTask(new PlayerTask<ListenableFuture<SessionPlayer.PlayerResult>>() {
            public ListenableFuture<SessionPlayer.PlayerResult> run(SessionPlayer sessionPlayer) throws Exception {
                return sessionPlayer.skipToNextPlaylistItem();
            }
        });
    }

    public MediaMetadata getPlaylistMetadata() {
        return (MediaMetadata) dispatchPlayerTask(new PlayerTask<MediaMetadata>() {
            public MediaMetadata run(SessionPlayer sessionPlayer) throws Exception {
                return sessionPlayer.getPlaylistMetadata();
            }
        }, (Object) null);
    }

    public ListenableFuture<SessionPlayer.PlayerResult> addPlaylistItem(final int i, final MediaItem mediaItem) {
        if (i < 0) {
            throw new IllegalArgumentException("index shouldn't be negative");
        } else if (mediaItem != null) {
            return dispatchPlayerTask(new PlayerTask<ListenableFuture<SessionPlayer.PlayerResult>>() {
                public ListenableFuture<SessionPlayer.PlayerResult> run(SessionPlayer sessionPlayer) throws Exception {
                    return sessionPlayer.addPlaylistItem(i, mediaItem);
                }
            });
        } else {
            throw new NullPointerException("item shouldn't be null");
        }
    }

    public ListenableFuture<SessionPlayer.PlayerResult> removePlaylistItem(final int i) {
        if (i >= 0) {
            return dispatchPlayerTask(new PlayerTask<ListenableFuture<SessionPlayer.PlayerResult>>() {
                public ListenableFuture<SessionPlayer.PlayerResult> run(SessionPlayer sessionPlayer) throws Exception {
                    if (i >= sessionPlayer.getPlaylist().size()) {
                        return SessionPlayer.PlayerResult.createFuture(-3);
                    }
                    return sessionPlayer.removePlaylistItem(i);
                }
            });
        }
        throw new IllegalArgumentException("index shouldn't be negative");
    }

    public ListenableFuture<SessionPlayer.PlayerResult> replacePlaylistItem(final int i, final MediaItem mediaItem) {
        if (i < 0) {
            throw new IllegalArgumentException("index shouldn't be negative");
        } else if (mediaItem != null) {
            return dispatchPlayerTask(new PlayerTask<ListenableFuture<SessionPlayer.PlayerResult>>() {
                public ListenableFuture<SessionPlayer.PlayerResult> run(SessionPlayer sessionPlayer) throws Exception {
                    return sessionPlayer.replacePlaylistItem(i, mediaItem);
                }
            });
        } else {
            throw new NullPointerException("item shouldn't be null");
        }
    }

    public MediaItem getCurrentMediaItem() {
        return (MediaItem) dispatchPlayerTask(new PlayerTask<MediaItem>() {
            public MediaItem run(SessionPlayer sessionPlayer) throws Exception {
                return sessionPlayer.getCurrentMediaItem();
            }
        }, (Object) null);
    }

    public int getCurrentMediaItemIndex() {
        return ((Integer) dispatchPlayerTask(new PlayerTask<Integer>() {
            public Integer run(SessionPlayer sessionPlayer) throws Exception {
                return Integer.valueOf(sessionPlayer.getCurrentMediaItemIndex());
            }
        }, -1)).intValue();
    }

    public int getPreviousMediaItemIndex() {
        return ((Integer) dispatchPlayerTask(new PlayerTask<Integer>() {
            public Integer run(SessionPlayer sessionPlayer) throws Exception {
                return Integer.valueOf(sessionPlayer.getPreviousMediaItemIndex());
            }
        }, -1)).intValue();
    }

    public int getNextMediaItemIndex() {
        return ((Integer) dispatchPlayerTask(new PlayerTask<Integer>() {
            public Integer run(SessionPlayer sessionPlayer) throws Exception {
                return Integer.valueOf(sessionPlayer.getNextMediaItemIndex());
            }
        }, -1)).intValue();
    }

    public ListenableFuture<SessionPlayer.PlayerResult> updatePlaylistMetadata(final MediaMetadata mediaMetadata) {
        return dispatchPlayerTask(new PlayerTask<ListenableFuture<SessionPlayer.PlayerResult>>() {
            public ListenableFuture<SessionPlayer.PlayerResult> run(SessionPlayer sessionPlayer) throws Exception {
                return sessionPlayer.updatePlaylistMetadata(mediaMetadata);
            }
        });
    }

    public int getRepeatMode() {
        return ((Integer) dispatchPlayerTask(new PlayerTask<Integer>() {
            public Integer run(SessionPlayer sessionPlayer) throws Exception {
                return Integer.valueOf(sessionPlayer.getRepeatMode());
            }
        }, 0)).intValue();
    }

    public ListenableFuture<SessionPlayer.PlayerResult> setRepeatMode(final int i) {
        return dispatchPlayerTask(new PlayerTask<ListenableFuture<SessionPlayer.PlayerResult>>() {
            public ListenableFuture<SessionPlayer.PlayerResult> run(SessionPlayer sessionPlayer) throws Exception {
                return sessionPlayer.setRepeatMode(i);
            }
        });
    }

    public int getShuffleMode() {
        return ((Integer) dispatchPlayerTask(new PlayerTask<Integer>() {
            public Integer run(SessionPlayer sessionPlayer) throws Exception {
                return Integer.valueOf(sessionPlayer.getShuffleMode());
            }
        }, 0)).intValue();
    }

    public ListenableFuture<SessionPlayer.PlayerResult> setShuffleMode(final int i) {
        return dispatchPlayerTask(new PlayerTask<ListenableFuture<SessionPlayer.PlayerResult>>() {
            public ListenableFuture<SessionPlayer.PlayerResult> run(SessionPlayer sessionPlayer) throws Exception {
                return sessionPlayer.setShuffleMode(i);
            }
        });
    }

    public VideoSize getVideoSize() {
        return (VideoSize) dispatchPlayerTask(new PlayerTask<VideoSize>() {
            public VideoSize run(SessionPlayer sessionPlayer) {
                return sessionPlayer.getVideoSizeInternal();
            }
        }, new VideoSize(0, 0));
    }

    public ListenableFuture<SessionPlayer.PlayerResult> setSurface(final Surface surface) {
        return dispatchPlayerTask(new PlayerTask<ListenableFuture<SessionPlayer.PlayerResult>>() {
            public ListenableFuture<SessionPlayer.PlayerResult> run(SessionPlayer sessionPlayer) {
                return sessionPlayer.setSurfaceInternal(surface);
            }
        });
    }

    public List<SessionPlayer.TrackInfo> getTrackInfo() {
        return (List) dispatchPlayerTask(new PlayerTask<List<SessionPlayer.TrackInfo>>() {
            public List<SessionPlayer.TrackInfo> run(SessionPlayer sessionPlayer) throws Exception {
                return sessionPlayer.getTrackInfoInternal();
            }
        }, (Object) null);
    }

    public ListenableFuture<SessionPlayer.PlayerResult> selectTrack(final SessionPlayer.TrackInfo trackInfo) {
        return dispatchPlayerTask(new PlayerTask<ListenableFuture<SessionPlayer.PlayerResult>>() {
            public ListenableFuture<SessionPlayer.PlayerResult> run(SessionPlayer sessionPlayer) throws Exception {
                return sessionPlayer.selectTrackInternal(trackInfo);
            }
        });
    }

    public ListenableFuture<SessionPlayer.PlayerResult> deselectTrack(final SessionPlayer.TrackInfo trackInfo) {
        return dispatchPlayerTask(new PlayerTask<ListenableFuture<SessionPlayer.PlayerResult>>() {
            public ListenableFuture<SessionPlayer.PlayerResult> run(SessionPlayer sessionPlayer) throws Exception {
                return sessionPlayer.deselectTrackInternal(trackInfo);
            }
        });
    }

    public SessionPlayer.TrackInfo getSelectedTrack(final int i) {
        return (SessionPlayer.TrackInfo) dispatchPlayerTask(new PlayerTask<SessionPlayer.TrackInfo>() {
            public SessionPlayer.TrackInfo run(SessionPlayer sessionPlayer) throws Exception {
                return sessionPlayer.getSelectedTrackInternal(i);
            }
        }, (Object) null);
    }

    public MediaSession getInstance() {
        return this.mInstance;
    }

    public Context getContext() {
        return this.mContext;
    }

    public Executor getCallbackExecutor() {
        return this.mCallbackExecutor;
    }

    public MediaSession.SessionCallback getCallback() {
        return this.mCallback;
    }

    public MediaSessionCompat getSessionCompat() {
        return this.mSessionCompat;
    }

    public boolean isClosed() {
        return !this.mHandlerThread.isAlive();
    }

    public PlaybackStateCompat createPlaybackStateCompat() {
        PlaybackStateCompat build;
        synchronized (this.mLock) {
            build = new PlaybackStateCompat.Builder().setState(MediaUtils.convertToPlaybackStateCompatState(getPlayerState(), getBufferingState()), getCurrentPosition(), getPlaybackSpeed(), SystemClock.elapsedRealtime()).setActions(3670015).setBufferedPosition(getBufferedPosition()).build();
        }
        return build;
    }

    public MediaController.PlaybackInfo getPlaybackInfo() {
        MediaController.PlaybackInfo playbackInfo;
        synchronized (this.mLock) {
            playbackInfo = this.mPlaybackInfo;
        }
        return playbackInfo;
    }

    public PendingIntent getSessionActivity() {
        return this.mSessionActivity;
    }

    /* access modifiers changed from: package-private */
    public MediaBrowserServiceCompat createLegacyBrowserService(Context context, SessionToken sessionToken, MediaSessionCompat.Token token) {
        return new MediaSessionServiceLegacyStub(context, this, token);
    }

    public void connectFromService(IMediaController iMediaController, String str, int i, int i2, Bundle bundle) {
        this.mSessionStub.connect(iMediaController, str, i, i2, bundle);
    }

    public IBinder getLegacyBrowserServiceBinder() {
        MediaBrowserServiceCompat mediaBrowserServiceCompat;
        synchronized (this.mLock) {
            if (this.mBrowserServiceLegacyStub == null) {
                this.mBrowserServiceLegacyStub = createLegacyBrowserService(this.mContext, this.mSessionToken, this.mSessionCompat.getSessionToken());
            }
            mediaBrowserServiceCompat = this.mBrowserServiceLegacyStub;
        }
        return mediaBrowserServiceCompat.onBind(new Intent(MediaBrowserServiceCompat.SERVICE_INTERFACE));
    }

    /* access modifiers changed from: package-private */
    public MediaBrowserServiceCompat getLegacyBrowserService() {
        MediaBrowserServiceCompat mediaBrowserServiceCompat;
        synchronized (this.mLock) {
            mediaBrowserServiceCompat = this.mBrowserServiceLegacyStub;
        }
        return mediaBrowserServiceCompat;
    }

    /* access modifiers changed from: package-private */
    public boolean isInPlaybackState(SessionPlayer sessionPlayer) {
        return (isClosed() || sessionPlayer.getPlayerState() == 0 || sessionPlayer.getPlayerState() == 3) ? false : true;
    }

    private MediaItem getCurrentMediaItemOrNull() {
        SessionPlayer sessionPlayer;
        synchronized (this.mLock) {
            sessionPlayer = this.mPlayer;
        }
        if (sessionPlayer != null) {
            return sessionPlayer.getCurrentMediaItem();
        }
        return null;
    }

    private List<MediaItem> getPlaylistOrNull() {
        SessionPlayer sessionPlayer;
        synchronized (this.mLock) {
            sessionPlayer = this.mPlayer;
        }
        if (sessionPlayer != null) {
            return sessionPlayer.getPlaylist();
        }
        return null;
    }

    /* JADX WARNING: type inference failed for: r5v0, types: [androidx.media2.session.MediaSessionImplBase$PlayerTask, androidx.media2.session.MediaSessionImplBase$PlayerTask<com.google.common.util.concurrent.ListenableFuture<androidx.media2.common.SessionPlayer$PlayerResult>>] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.google.common.util.concurrent.ListenableFuture<androidx.media2.common.SessionPlayer.PlayerResult> dispatchPlayerTask(androidx.media2.session.MediaSessionImplBase.PlayerTask<com.google.common.util.concurrent.ListenableFuture<androidx.media2.common.SessionPlayer.PlayerResult>> r5) {
        /*
            r4 = this;
            androidx.media2.session.futures.ResolvableFuture r0 = androidx.media2.session.futures.ResolvableFuture.create()
            androidx.media2.common.SessionPlayer$PlayerResult r1 = new androidx.media2.common.SessionPlayer$PlayerResult
            r2 = -2
            r3 = 0
            r1.<init>(r2, r3)
            r0.set(r1)
            java.lang.Object r5 = r4.dispatchPlayerTask(r5, r0)
            com.google.common.util.concurrent.ListenableFuture r5 = (com.google.common.util.concurrent.ListenableFuture) r5
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media2.session.MediaSessionImplBase.dispatchPlayerTask(androidx.media2.session.MediaSessionImplBase$PlayerTask):com.google.common.util.concurrent.ListenableFuture");
    }

    private <T> T dispatchPlayerTask(PlayerTask<T> playerTask, T t) {
        SessionPlayer sessionPlayer;
        synchronized (this.mLock) {
            sessionPlayer = this.mPlayer;
        }
        try {
            if (!isClosed()) {
                T run = playerTask.run(sessionPlayer);
                if (run != null) {
                    return run;
                }
            } else if (DEBUG) {
                Log.d(TAG, "API calls after the close()", new IllegalStateException());
            }
        } catch (Exception unused) {
        }
        return t;
    }

    private void notifyPlayerUpdatedNotLocked(SessionPlayer sessionPlayer) {
        List<MediaItem> playlist = sessionPlayer.getPlaylist();
        final List<MediaItem> playlistOrNull = getPlaylistOrNull();
        if (!ObjectsCompat.equals(playlist, playlistOrNull)) {
            dispatchRemoteControllerTaskWithoutReturn(new RemoteControllerTask() {
                public void run(MediaSession.ControllerCb controllerCb, int i) throws RemoteException {
                    controllerCb.onPlaylistChanged(i, playlistOrNull, MediaSessionImplBase.this.getPlaylistMetadata(), MediaSessionImplBase.this.getCurrentMediaItemIndex(), MediaSessionImplBase.this.getPreviousMediaItemIndex(), MediaSessionImplBase.this.getNextMediaItemIndex());
                }
            });
        } else {
            MediaMetadata playlistMetadata = sessionPlayer.getPlaylistMetadata();
            final MediaMetadata playlistMetadata2 = getPlaylistMetadata();
            if (!ObjectsCompat.equals(playlistMetadata, playlistMetadata2)) {
                dispatchRemoteControllerTaskWithoutReturn(new RemoteControllerTask() {
                    public void run(MediaSession.ControllerCb controllerCb, int i) throws RemoteException {
                        controllerCb.onPlaylistMetadataChanged(i, playlistMetadata2);
                    }
                });
            }
        }
        MediaItem currentMediaItem = sessionPlayer.getCurrentMediaItem();
        final MediaItem currentMediaItemOrNull = getCurrentMediaItemOrNull();
        if (!ObjectsCompat.equals(currentMediaItem, currentMediaItemOrNull)) {
            dispatchRemoteControllerTaskWithoutReturn(new RemoteControllerTask() {
                public void run(MediaSession.ControllerCb controllerCb, int i) throws RemoteException {
                    controllerCb.onCurrentMediaItemChanged(i, currentMediaItemOrNull, MediaSessionImplBase.this.getCurrentMediaItemIndex(), MediaSessionImplBase.this.getPreviousMediaItemIndex(), MediaSessionImplBase.this.getNextMediaItemIndex());
                }
            });
        }
        final int repeatMode = getRepeatMode();
        if (sessionPlayer.getRepeatMode() != repeatMode) {
            dispatchRemoteControllerTaskWithoutReturn(new RemoteControllerTask() {
                public void run(MediaSession.ControllerCb controllerCb, int i) throws RemoteException {
                    controllerCb.onRepeatModeChanged(i, repeatMode, MediaSessionImplBase.this.getCurrentMediaItemIndex(), MediaSessionImplBase.this.getPreviousMediaItemIndex(), MediaSessionImplBase.this.getNextMediaItemIndex());
                }
            });
        }
        final int shuffleMode = getShuffleMode();
        if (sessionPlayer.getShuffleMode() != shuffleMode) {
            dispatchRemoteControllerTaskWithoutReturn(new RemoteControllerTask() {
                public void run(MediaSession.ControllerCb controllerCb, int i) throws RemoteException {
                    controllerCb.onShuffleModeChanged(i, shuffleMode, MediaSessionImplBase.this.getCurrentMediaItemIndex(), MediaSessionImplBase.this.getPreviousMediaItemIndex(), MediaSessionImplBase.this.getNextMediaItemIndex());
                }
            });
        }
        long elapsedRealtime = SystemClock.elapsedRealtime();
        long currentPosition = getCurrentPosition();
        final int playerState = getPlayerState();
        final long j = elapsedRealtime;
        final long j2 = currentPosition;
        dispatchRemoteControllerTaskWithoutReturn(new RemoteControllerTask() {
            public void run(MediaSession.ControllerCb controllerCb, int i) throws RemoteException {
                controllerCb.onPlayerStateChanged(i, j, j2, playerState);
            }
        });
        final MediaItem currentMediaItemOrNull2 = getCurrentMediaItemOrNull();
        if (currentMediaItemOrNull2 != null) {
            final int bufferingState = getBufferingState();
            final long bufferedPosition = getBufferedPosition();
            dispatchRemoteControllerTaskWithoutReturn(new RemoteControllerTask() {
                public void run(MediaSession.ControllerCb controllerCb, int i) throws RemoteException {
                    controllerCb.onBufferingStateChanged(i, currentMediaItemOrNull2, bufferingState, bufferedPosition, SystemClock.elapsedRealtime(), MediaSessionImplBase.this.getCurrentPosition());
                }
            });
        }
        final float playbackSpeed = getPlaybackSpeed();
        if (playbackSpeed != sessionPlayer.getPlaybackSpeed()) {
            final long j3 = elapsedRealtime;
            final long j4 = currentPosition;
            dispatchRemoteControllerTaskWithoutReturn(new RemoteControllerTask() {
                public void run(MediaSession.ControllerCb controllerCb, int i) throws RemoteException {
                    controllerCb.onPlaybackSpeedChanged(i, j3, j4, playbackSpeed);
                }
            });
        }
    }

    /* access modifiers changed from: package-private */
    public void notifyPlaybackInfoChangedNotLocked(final MediaController.PlaybackInfo playbackInfo) {
        dispatchRemoteControllerTaskWithoutReturn(new RemoteControllerTask() {
            public void run(MediaSession.ControllerCb controllerCb, int i) throws RemoteException {
                controllerCb.onPlaybackInfoChanged(i, playbackInfo);
            }
        });
    }

    /* access modifiers changed from: package-private */
    public void dispatchRemoteControllerTaskWithoutReturn(RemoteControllerTask remoteControllerTask) {
        List<MediaSession.ControllerInfo> connectedControllers = this.mSessionStub.getConnectedControllersManager().getConnectedControllers();
        connectedControllers.add(this.mSessionLegacyStub.getControllersForAll());
        for (int i = 0; i < connectedControllers.size(); i++) {
            dispatchRemoteControllerTaskWithoutReturn(connectedControllers.get(i), remoteControllerTask);
        }
    }

    /* access modifiers changed from: package-private */
    public void dispatchRemoteControllerTaskWithoutReturn(MediaSession.ControllerInfo controllerInfo, RemoteControllerTask remoteControllerTask) {
        if (isConnected(controllerInfo)) {
            try {
                SequencedFutureManager sequencedFutureManager = this.mSessionStub.getConnectedControllersManager().getSequencedFutureManager(controllerInfo);
                remoteControllerTask.run(controllerInfo.getControllerCb(), sequencedFutureManager != null ? sequencedFutureManager.obtainNextSequenceNumber() : 0);
            } catch (DeadObjectException e) {
                onDeadObjectException(controllerInfo, e);
            } catch (RemoteException e2) {
                Log.w(TAG, "Exception in " + controllerInfo.toString(), e2);
            }
        }
    }

    private ListenableFuture<SessionResult> dispatchRemoteControllerTask(MediaSession.ControllerInfo controllerInfo, RemoteControllerTask remoteControllerTask) {
        ListenableFuture<SessionResult> listenableFuture;
        if (!isConnected(controllerInfo)) {
            return SessionResult.createFutureWithResult(-100);
        }
        try {
            SequencedFutureManager sequencedFutureManager = this.mSessionStub.getConnectedControllersManager().getSequencedFutureManager(controllerInfo);
            int i = 0;
            if (sequencedFutureManager != null) {
                listenableFuture = sequencedFutureManager.createSequencedFuture(RESULT_WHEN_CLOSED);
                i = ((SequencedFutureManager.SequencedFuture) listenableFuture).getSequenceNumber();
            } else {
                listenableFuture = SessionResult.createFutureWithResult(0);
            }
            remoteControllerTask.run(controllerInfo.getControllerCb(), i);
            return listenableFuture;
        } catch (DeadObjectException e) {
            onDeadObjectException(controllerInfo, e);
            return SessionResult.createFutureWithResult(-100);
        } catch (RemoteException e2) {
            Log.w(TAG, "Exception in " + controllerInfo.toString(), e2);
            return SessionResult.createFutureWithResult(-1);
        }
    }

    private void onDeadObjectException(MediaSession.ControllerInfo controllerInfo, DeadObjectException deadObjectException) {
        if (DEBUG) {
            Log.d(TAG, controllerInfo.toString() + " is gone", deadObjectException);
        }
        this.mSessionStub.getConnectedControllersManager().removeController(controllerInfo);
    }

    private ComponentName getServiceComponentByAction(String str) {
        PackageManager packageManager = this.mContext.getPackageManager();
        Intent intent = new Intent(str);
        intent.setPackage(this.mContext.getPackageName());
        List<ResolveInfo> queryIntentServices = packageManager.queryIntentServices(intent, 0);
        if (queryIntentServices == null || queryIntentServices.isEmpty()) {
            return null;
        }
        ResolveInfo resolveInfo = queryIntentServices.get(0);
        return new ComponentName(resolveInfo.serviceInfo.packageName, resolveInfo.serviceInfo.name);
    }

    private static class SessionPlayerCallback extends SessionPlayer.PlayerCallback {
        private final CurrentMediaItemListener mCurrentItemChangedListener;
        private List<MediaItem> mList;
        private MediaItem mMediaItem;
        private final PlaylistItemListener mPlaylistItemChangedListener;
        private final WeakReference<MediaSessionImplBase> mSession;

        SessionPlayerCallback(MediaSessionImplBase mediaSessionImplBase) {
            this.mSession = new WeakReference<>(mediaSessionImplBase);
            this.mCurrentItemChangedListener = new CurrentMediaItemListener(mediaSessionImplBase);
            this.mPlaylistItemChangedListener = new PlaylistItemListener(mediaSessionImplBase);
        }

        public void onCurrentMediaItemChanged(SessionPlayer sessionPlayer, final MediaItem mediaItem) {
            final MediaSessionImplBase session = getSession();
            if (session != null && sessionPlayer != null && session.getPlayer() == sessionPlayer) {
                synchronized (session.mLock) {
                    if (this.mMediaItem != null) {
                        this.mMediaItem.removeOnMetadataChangedListener(this.mCurrentItemChangedListener);
                    }
                    if (mediaItem != null) {
                        mediaItem.addOnMetadataChangedListener(session.mCallbackExecutor, this.mCurrentItemChangedListener);
                    }
                    this.mMediaItem = mediaItem;
                }
                updateDurationIfNeeded(sessionPlayer, mediaItem);
                session.dispatchRemoteControllerTaskWithoutReturn(new RemoteControllerTask() {
                    public void run(MediaSession.ControllerCb controllerCb, int i) throws RemoteException {
                        controllerCb.onCurrentMediaItemChanged(i, mediaItem, session.getCurrentMediaItemIndex(), session.getPreviousMediaItemIndex(), session.getNextMediaItemIndex());
                    }
                });
            }
        }

        public void onPlayerStateChanged(final SessionPlayer sessionPlayer, final int i) {
            MediaSessionImplBase session = getSession();
            if (session != null && sessionPlayer != null && session.getPlayer() == sessionPlayer) {
                session.getCallback().onPlayerStateChanged(session.getInstance(), i);
                updateDurationIfNeeded(sessionPlayer, sessionPlayer.getCurrentMediaItem());
                session.dispatchRemoteControllerTaskWithoutReturn(new RemoteControllerTask() {
                    public void run(MediaSession.ControllerCb controllerCb, int i) throws RemoteException {
                        controllerCb.onPlayerStateChanged(i, SystemClock.elapsedRealtime(), sessionPlayer.getCurrentPosition(), i);
                    }
                });
            }
        }

        public void onBufferingStateChanged(final SessionPlayer sessionPlayer, final MediaItem mediaItem, final int i) {
            updateDurationIfNeeded(sessionPlayer, mediaItem);
            dispatchRemoteControllerTask(sessionPlayer, new RemoteControllerTask() {
                public void run(MediaSession.ControllerCb controllerCb, int i) throws RemoteException {
                    controllerCb.onBufferingStateChanged(i, mediaItem, i, sessionPlayer.getBufferedPosition(), SystemClock.elapsedRealtime(), sessionPlayer.getCurrentPosition());
                }
            });
        }

        public void onPlaybackSpeedChanged(final SessionPlayer sessionPlayer, final float f) {
            dispatchRemoteControllerTask(sessionPlayer, new RemoteControllerTask() {
                public void run(MediaSession.ControllerCb controllerCb, int i) throws RemoteException {
                    controllerCb.onPlaybackSpeedChanged(i, SystemClock.elapsedRealtime(), sessionPlayer.getCurrentPosition(), f);
                }
            });
        }

        public void onSeekCompleted(final SessionPlayer sessionPlayer, final long j) {
            dispatchRemoteControllerTask(sessionPlayer, new RemoteControllerTask() {
                public void run(MediaSession.ControllerCb controllerCb, int i) throws RemoteException {
                    controllerCb.onSeekCompleted(i, SystemClock.elapsedRealtime(), sessionPlayer.getCurrentPosition(), j);
                }
            });
        }

        public void onPlaylistChanged(SessionPlayer sessionPlayer, final List<MediaItem> list, final MediaMetadata mediaMetadata) {
            final MediaSessionImplBase session = getSession();
            if (session != null && sessionPlayer != null && session.getPlayer() == sessionPlayer) {
                synchronized (session.mLock) {
                    if (this.mList != null) {
                        for (int i = 0; i < this.mList.size(); i++) {
                            this.mList.get(i).removeOnMetadataChangedListener(this.mPlaylistItemChangedListener);
                        }
                    }
                    if (list != null) {
                        for (int i2 = 0; i2 < list.size(); i2++) {
                            list.get(i2).addOnMetadataChangedListener(session.mCallbackExecutor, this.mPlaylistItemChangedListener);
                        }
                    }
                    this.mList = list;
                }
                dispatchRemoteControllerTask(sessionPlayer, new RemoteControllerTask() {
                    public void run(MediaSession.ControllerCb controllerCb, int i) throws RemoteException {
                        controllerCb.onPlaylistChanged(i, list, mediaMetadata, session.getCurrentMediaItemIndex(), session.getPreviousMediaItemIndex(), session.getNextMediaItemIndex());
                    }
                });
            }
        }

        public void onPlaylistMetadataChanged(SessionPlayer sessionPlayer, final MediaMetadata mediaMetadata) {
            dispatchRemoteControllerTask(sessionPlayer, new RemoteControllerTask() {
                public void run(MediaSession.ControllerCb controllerCb, int i) throws RemoteException {
                    controllerCb.onPlaylistMetadataChanged(i, mediaMetadata);
                }
            });
        }

        public void onRepeatModeChanged(SessionPlayer sessionPlayer, final int i) {
            final MediaSessionImplBase session = getSession();
            dispatchRemoteControllerTask(sessionPlayer, new RemoteControllerTask() {
                public void run(MediaSession.ControllerCb controllerCb, int i) throws RemoteException {
                    controllerCb.onRepeatModeChanged(i, i, session.getCurrentMediaItemIndex(), session.getPreviousMediaItemIndex(), session.getNextMediaItemIndex());
                }
            });
        }

        public void onShuffleModeChanged(SessionPlayer sessionPlayer, final int i) {
            final MediaSessionImplBase session = getSession();
            dispatchRemoteControllerTask(sessionPlayer, new RemoteControllerTask() {
                public void run(MediaSession.ControllerCb controllerCb, int i) throws RemoteException {
                    controllerCb.onShuffleModeChanged(i, i, session.getCurrentMediaItemIndex(), session.getPreviousMediaItemIndex(), session.getNextMediaItemIndex());
                }
            });
        }

        public void onPlaybackCompleted(SessionPlayer sessionPlayer) {
            dispatchRemoteControllerTask(sessionPlayer, new RemoteControllerTask() {
                public void run(MediaSession.ControllerCb controllerCb, int i) throws RemoteException {
                    controllerCb.onPlaybackCompleted(i);
                }
            });
        }

        public void onAudioAttributesChanged(SessionPlayer sessionPlayer, AudioAttributesCompat audioAttributesCompat) {
            MediaController.PlaybackInfo playbackInfo;
            MediaSessionImplBase session = getSession();
            if (session != null && sessionPlayer != null && session.getPlayer() == sessionPlayer) {
                MediaController.PlaybackInfo createPlaybackInfo = session.createPlaybackInfo(sessionPlayer, audioAttributesCompat);
                synchronized (session.mLock) {
                    playbackInfo = session.mPlaybackInfo;
                    session.mPlaybackInfo = createPlaybackInfo;
                }
                if (!ObjectsCompat.equals(createPlaybackInfo, playbackInfo)) {
                    session.notifyPlaybackInfoChangedNotLocked(createPlaybackInfo);
                }
            }
        }

        public void onVideoSizeChangedInternal(SessionPlayer sessionPlayer, final MediaItem mediaItem, final VideoSize videoSize) {
            dispatchRemoteControllerTask(sessionPlayer, new RemoteControllerTask() {
                public void run(MediaSession.ControllerCb controllerCb, int i) throws RemoteException {
                    controllerCb.onVideoSizeChanged(i, mediaItem, videoSize);
                }
            });
        }

        public void onTrackInfoChanged(SessionPlayer sessionPlayer, final List<SessionPlayer.TrackInfo> list) {
            final MediaSessionImplBase session = getSession();
            dispatchRemoteControllerTask(sessionPlayer, new RemoteControllerTask() {
                public void run(MediaSession.ControllerCb controllerCb, int i) throws RemoteException {
                    controllerCb.onTrackInfoChanged(i, list, session.getSelectedTrack(1), session.getSelectedTrack(2), session.getSelectedTrack(4), session.getSelectedTrack(5));
                }
            });
        }

        public void onTrackSelected(SessionPlayer sessionPlayer, final SessionPlayer.TrackInfo trackInfo) {
            dispatchRemoteControllerTask(sessionPlayer, new RemoteControllerTask() {
                public void run(MediaSession.ControllerCb controllerCb, int i) throws RemoteException {
                    controllerCb.onTrackSelected(i, trackInfo);
                }
            });
        }

        public void onTrackDeselected(SessionPlayer sessionPlayer, final SessionPlayer.TrackInfo trackInfo) {
            dispatchRemoteControllerTask(sessionPlayer, new RemoteControllerTask() {
                public void run(MediaSession.ControllerCb controllerCb, int i) throws RemoteException {
                    controllerCb.onTrackDeselected(i, trackInfo);
                }
            });
        }

        public void onSubtitleData(SessionPlayer sessionPlayer, final MediaItem mediaItem, final SessionPlayer.TrackInfo trackInfo, final SubtitleData subtitleData) {
            dispatchRemoteControllerTask(sessionPlayer, new RemoteControllerTask() {
                public void run(MediaSession.ControllerCb controllerCb, int i) throws RemoteException {
                    controllerCb.onSubtitleData(i, mediaItem, trackInfo, subtitleData);
                }
            });
        }

        private MediaSessionImplBase getSession() {
            MediaSessionImplBase mediaSessionImplBase = (MediaSessionImplBase) this.mSession.get();
            if (mediaSessionImplBase == null && MediaSessionImplBase.DEBUG) {
                Log.d(MediaSessionImplBase.TAG, "Session is closed", new IllegalStateException());
            }
            return mediaSessionImplBase;
        }

        private void dispatchRemoteControllerTask(SessionPlayer sessionPlayer, RemoteControllerTask remoteControllerTask) {
            MediaSessionImplBase session = getSession();
            if (session != null && sessionPlayer != null && session.getPlayer() == sessionPlayer) {
                session.dispatchRemoteControllerTaskWithoutReturn(remoteControllerTask);
            }
        }

        private void updateDurationIfNeeded(final SessionPlayer sessionPlayer, MediaItem mediaItem) {
            if (mediaItem != null && mediaItem.equals(sessionPlayer.getCurrentMediaItem())) {
                long duration = sessionPlayer.getDuration();
                if (duration > 0 && duration != Long.MIN_VALUE) {
                    MediaMetadata metadata = mediaItem.getMetadata();
                    if (metadata == null) {
                        metadata = new MediaMetadata.Builder().putLong("android.media.metadata.DURATION", duration).putString("android.media.metadata.MEDIA_ID", mediaItem.getMediaId()).putLong(MediaMetadata.METADATA_KEY_PLAYABLE, 1).build();
                    } else if (!metadata.containsKey("android.media.metadata.DURATION")) {
                        metadata = new MediaMetadata.Builder(metadata).putLong("android.media.metadata.DURATION", duration).build();
                    } else {
                        long j = metadata.getLong("android.media.metadata.DURATION");
                        if (duration != j) {
                            Log.w(MediaSessionImplBase.TAG, "duration mismatch for an item. duration from player=" + duration + " duration from metadata=" + j + ". May be a timing issue?");
                        } else {
                            return;
                        }
                    }
                    if (metadata != null) {
                        final MediaSessionImplBase session = getSession();
                        mediaItem.setMetadata(metadata);
                        dispatchRemoteControllerTask(sessionPlayer, new RemoteControllerTask() {
                            public void run(MediaSession.ControllerCb controllerCb, int i) throws RemoteException {
                                controllerCb.onPlaylistChanged(i, sessionPlayer.getPlaylist(), sessionPlayer.getPlaylistMetadata(), session.getCurrentMediaItemIndex(), session.getPreviousMediaItemIndex(), session.getNextMediaItemIndex());
                            }
                        });
                    }
                }
            }
        }
    }

    static class CurrentMediaItemListener implements MediaItem.OnMetadataChangedListener {
        private final WeakReference<MediaSessionImplBase> mSession;

        CurrentMediaItemListener(MediaSessionImplBase mediaSessionImplBase) {
            this.mSession = new WeakReference<>(mediaSessionImplBase);
        }

        public void onMetadataChanged(final MediaItem mediaItem) {
            MediaItem currentMediaItem;
            final MediaSessionImplBase mediaSessionImplBase = (MediaSessionImplBase) this.mSession.get();
            if (mediaSessionImplBase != null && mediaItem != null && (currentMediaItem = mediaSessionImplBase.getCurrentMediaItem()) != null && mediaItem.equals(currentMediaItem)) {
                mediaSessionImplBase.dispatchRemoteControllerTaskWithoutReturn(new RemoteControllerTask() {
                    public void run(MediaSession.ControllerCb controllerCb, int i) throws RemoteException {
                        controllerCb.onCurrentMediaItemChanged(i, mediaItem, mediaSessionImplBase.getCurrentMediaItemIndex(), mediaSessionImplBase.getPreviousMediaItemIndex(), mediaSessionImplBase.getNextMediaItemIndex());
                    }
                });
            }
        }
    }

    static class PlaylistItemListener implements MediaItem.OnMetadataChangedListener {
        private final WeakReference<MediaSessionImplBase> mSession;

        PlaylistItemListener(MediaSessionImplBase mediaSessionImplBase) {
            this.mSession = new WeakReference<>(mediaSessionImplBase);
        }

        /* JADX WARNING: Code restructure failed: missing block: B:3:0x000d, code lost:
            r1 = r0.getPlaylist();
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void onMetadataChanged(androidx.media2.common.MediaItem r5) {
            /*
                r4 = this;
                java.lang.ref.WeakReference<androidx.media2.session.MediaSessionImplBase> r0 = r4.mSession
                java.lang.Object r0 = r0.get()
                androidx.media2.session.MediaSessionImplBase r0 = (androidx.media2.session.MediaSessionImplBase) r0
                if (r0 == 0) goto L_0x0031
                if (r5 != 0) goto L_0x000d
                goto L_0x0031
            L_0x000d:
                java.util.List r1 = r0.getPlaylist()
                if (r1 != 0) goto L_0x0014
                return
            L_0x0014:
                r2 = 0
            L_0x0015:
                int r3 = r1.size()
                if (r2 >= r3) goto L_0x0031
                java.lang.Object r3 = r1.get(r2)
                boolean r3 = r5.equals(r3)
                if (r3 == 0) goto L_0x002e
                androidx.media2.session.MediaSessionImplBase$PlaylistItemListener$1 r5 = new androidx.media2.session.MediaSessionImplBase$PlaylistItemListener$1
                r5.<init>(r1, r0)
                r0.dispatchRemoteControllerTaskWithoutReturn(r5)
                return
            L_0x002e:
                int r2 = r2 + 1
                goto L_0x0015
            L_0x0031:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.media2.session.MediaSessionImplBase.PlaylistItemListener.onMetadataChanged(androidx.media2.common.MediaItem):void");
        }
    }

    static final class CombinedCommandResultFuture<T extends BaseResult> extends AbstractResolvableFuture<T> {
        final ListenableFuture<T>[] mFutures;
        AtomicInteger mSuccessCount = new AtomicInteger(0);

        public static <U extends BaseResult> CombinedCommandResultFuture create(Executor executor, ListenableFuture<U>... listenableFutureArr) {
            return new CombinedCommandResultFuture(executor, listenableFutureArr);
        }

        private CombinedCommandResultFuture(Executor executor, ListenableFuture<T>[] listenableFutureArr) {
            final int i = 0;
            this.mFutures = listenableFutureArr;
            while (true) {
                ListenableFuture<T>[] listenableFutureArr2 = this.mFutures;
                if (i < listenableFutureArr2.length) {
                    listenableFutureArr2[i].addListener(new Runnable() {
                        public void run() {
                            try {
                                BaseResult baseResult = (BaseResult) CombinedCommandResultFuture.this.mFutures[i].get();
                                int resultCode = baseResult.getResultCode();
                                if (resultCode != 0 && resultCode != 1) {
                                    for (int i = 0; i < CombinedCommandResultFuture.this.mFutures.length; i++) {
                                        if (!CombinedCommandResultFuture.this.mFutures[i].isCancelled() && !CombinedCommandResultFuture.this.mFutures[i].isDone() && i != i) {
                                            CombinedCommandResultFuture.this.mFutures[i].cancel(true);
                                        }
                                    }
                                    boolean unused = CombinedCommandResultFuture.this.set(baseResult);
                                } else if (CombinedCommandResultFuture.this.mSuccessCount.incrementAndGet() == CombinedCommandResultFuture.this.mFutures.length) {
                                    boolean unused2 = CombinedCommandResultFuture.this.set(baseResult);
                                }
                            } catch (Exception e) {
                                for (int i2 = 0; i2 < CombinedCommandResultFuture.this.mFutures.length; i2++) {
                                    if (!CombinedCommandResultFuture.this.mFutures[i2].isCancelled() && !CombinedCommandResultFuture.this.mFutures[i2].isDone() && i != i2) {
                                        CombinedCommandResultFuture.this.mFutures[i2].cancel(true);
                                    }
                                }
                                boolean unused3 = CombinedCommandResultFuture.this.setException(e);
                            }
                        }
                    }, executor);
                    i++;
                } else {
                    return;
                }
            }
        }
    }

    final class MediaButtonReceiver extends BroadcastReceiver {
        MediaButtonReceiver() {
        }

        public void onReceive(Context context, Intent intent) {
            KeyEvent keyEvent;
            if ("android.intent.action.MEDIA_BUTTON".equals(intent.getAction()) && ObjectsCompat.equals(intent.getData(), MediaSessionImplBase.this.mSessionUri) && (keyEvent = (KeyEvent) intent.getParcelableExtra("android.intent.extra.KEY_EVENT")) != null) {
                MediaSessionImplBase.this.getSessionCompat().getController().dispatchMediaButtonEvent(keyEvent);
            }
        }
    }
}

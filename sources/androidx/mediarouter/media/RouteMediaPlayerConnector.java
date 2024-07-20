package androidx.mediarouter.media;

import android.os.Handler;
import android.os.Looper;
import androidx.collection.ArrayMap;
import androidx.collection.SimpleArrayMap;
import androidx.media2.BaseRemoteMediaPlayerConnector;
import androidx.media2.DataSourceDesc2;
import androidx.media2.MediaPlayerConnector;
import androidx.mediarouter.media.MediaRouter;
import java.util.concurrent.Executor;

public abstract class RouteMediaPlayerConnector extends BaseRemoteMediaPlayerConnector {
    private final ArrayMap<MediaPlayerConnector.PlayerEventCallback, Executor> mCallbacks = new ArrayMap<>();
    private final Handler mHandler = new Handler(Looper.getMainLooper());
    final Object mLock = new Object();
    MediaRouter.RouteInfo mRoute;

    public final void updateRouteInfo(MediaRouter.RouteInfo routeInfo) {
        synchronized (this.mLock) {
            if (this.mRoute != routeInfo) {
                this.mHandler.removeCallbacksAndMessages((Object) null);
                this.mRoute = routeInfo;
            } else {
                notifyPlayerVolumeChanged();
            }
        }
    }

    public final float getMaxPlayerVolume() {
        synchronized (this.mLock) {
            if (this.mRoute == null) {
                return 1.0f;
            }
            float volumeMax = (float) this.mRoute.getVolumeMax();
            return volumeMax;
        }
    }

    public final float getPlayerVolume() {
        synchronized (this.mLock) {
            if (this.mRoute == null) {
                return 1.0f;
            }
            float volume = (float) this.mRoute.getVolume();
            return volume;
        }
    }

    public final void adjustPlayerVolume(final int i) {
        synchronized (this.mLock) {
            if (this.mRoute != null) {
                this.mHandler.post(new Runnable() {
                    public void run() {
                        synchronized (RouteMediaPlayerConnector.this.mLock) {
                            if (RouteMediaPlayerConnector.this.mRoute != null) {
                                RouteMediaPlayerConnector.this.mRoute.requestUpdateVolume(i);
                            }
                        }
                    }
                });
            }
        }
    }

    public final void setPlayerVolume(final float f) {
        synchronized (this.mLock) {
            if (this.mRoute != null) {
                this.mHandler.post(new Runnable() {
                    public void run() {
                        synchronized (RouteMediaPlayerConnector.this.mLock) {
                            if (RouteMediaPlayerConnector.this.mRoute != null) {
                                RouteMediaPlayerConnector.this.mRoute.requestSetVolume((int) (f + 0.5f));
                            }
                        }
                    }
                });
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x001a, code lost:
        return 0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int getVolumeControlType() {
        /*
            r4 = this;
            java.lang.Object r0 = r4.mLock
            monitor-enter(r0)
            androidx.mediarouter.media.MediaRouter$RouteInfo r1 = r4.mRoute     // Catch:{ all -> 0x001b }
            r2 = 0
            if (r1 == 0) goto L_0x0019
            androidx.mediarouter.media.MediaRouter$RouteInfo r1 = r4.mRoute     // Catch:{ all -> 0x001b }
            int r1 = r1.getVolumeHandling()     // Catch:{ all -> 0x001b }
            if (r1 == 0) goto L_0x0017
            r3 = 1
            if (r1 == r3) goto L_0x0014
            goto L_0x0019
        L_0x0014:
            r1 = 2
            monitor-exit(r0)     // Catch:{ all -> 0x001b }
            return r1
        L_0x0017:
            monitor-exit(r0)     // Catch:{ all -> 0x001b }
            return r2
        L_0x0019:
            monitor-exit(r0)     // Catch:{ all -> 0x001b }
            return r2
        L_0x001b:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x001b }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.mediarouter.media.RouteMediaPlayerConnector.getVolumeControlType():int");
    }

    public final void registerPlayerEventCallback(Executor executor, MediaPlayerConnector.PlayerEventCallback playerEventCallback) {
        if (executor == null) {
            throw new IllegalArgumentException("executor shouldn't be null");
        } else if (playerEventCallback != null) {
            synchronized (this.mLock) {
                this.mCallbacks.put(playerEventCallback, executor);
            }
        } else {
            throw new IllegalArgumentException("callback shouldn't be null");
        }
    }

    public final void unregisterPlayerEventCallback(MediaPlayerConnector.PlayerEventCallback playerEventCallback) {
        if (playerEventCallback != null) {
            synchronized (this.mLock) {
                this.mCallbacks.remove(playerEventCallback);
            }
            return;
        }
        throw new IllegalArgumentException("callback shouldn't be null");
    }

    public final void notifyCurrentDataSourceChanged() {
        notifyCurrentDataSourceChanged(getCurrentDataSource());
    }

    public final void notifyPlaybackCompleted() {
        notifyCurrentDataSourceChanged((DataSourceDesc2) null);
    }

    private void notifyCurrentDataSourceChanged(final DataSourceDesc2 dataSourceDesc2) {
        SimpleArrayMap<MediaPlayerConnector.PlayerEventCallback, Executor> callbacks = getCallbacks();
        for (int i = 0; i < callbacks.size(); i++) {
            final MediaPlayerConnector.PlayerEventCallback keyAt = callbacks.keyAt(i);
            callbacks.valueAt(i).execute(new Runnable() {
                public void run() {
                    keyAt.onCurrentDataSourceChanged(RouteMediaPlayerConnector.this, dataSourceDesc2);
                }
            });
        }
    }

    public final void notifyMediaPrepared(final DataSourceDesc2 dataSourceDesc2) {
        SimpleArrayMap<MediaPlayerConnector.PlayerEventCallback, Executor> callbacks = getCallbacks();
        for (int i = 0; i < callbacks.size(); i++) {
            final MediaPlayerConnector.PlayerEventCallback keyAt = callbacks.keyAt(i);
            callbacks.valueAt(i).execute(new Runnable() {
                public void run() {
                    keyAt.onMediaPrepared(RouteMediaPlayerConnector.this, dataSourceDesc2);
                }
            });
        }
    }

    public final void notifyPlayerStateChanged() {
        SimpleArrayMap<MediaPlayerConnector.PlayerEventCallback, Executor> callbacks = getCallbacks();
        final int playerState = getPlayerState();
        for (int i = 0; i < callbacks.size(); i++) {
            final MediaPlayerConnector.PlayerEventCallback keyAt = callbacks.keyAt(i);
            callbacks.valueAt(i).execute(new Runnable() {
                public void run() {
                    keyAt.onPlayerStateChanged(RouteMediaPlayerConnector.this, playerState);
                }
            });
        }
    }

    public final void notifyBufferingStateChanged(final DataSourceDesc2 dataSourceDesc2, final int i) {
        SimpleArrayMap<MediaPlayerConnector.PlayerEventCallback, Executor> callbacks = getCallbacks();
        for (int i2 = 0; i2 < callbacks.size(); i2++) {
            final MediaPlayerConnector.PlayerEventCallback keyAt = callbacks.keyAt(i2);
            callbacks.valueAt(i2).execute(new Runnable() {
                public void run() {
                    keyAt.onBufferingStateChanged(RouteMediaPlayerConnector.this, dataSourceDesc2, i);
                }
            });
        }
    }

    public final void notifyPlaybackSpeedChanged() {
        SimpleArrayMap<MediaPlayerConnector.PlayerEventCallback, Executor> callbacks = getCallbacks();
        final float playbackSpeed = getPlaybackSpeed();
        for (int i = 0; i < callbacks.size(); i++) {
            final MediaPlayerConnector.PlayerEventCallback keyAt = callbacks.keyAt(i);
            callbacks.valueAt(i).execute(new Runnable() {
                public void run() {
                    keyAt.onPlaybackSpeedChanged(RouteMediaPlayerConnector.this, playbackSpeed);
                }
            });
        }
    }

    public final void notifySeekCompleted(final long j) {
        SimpleArrayMap<MediaPlayerConnector.PlayerEventCallback, Executor> callbacks = getCallbacks();
        for (int i = 0; i < callbacks.size(); i++) {
            final MediaPlayerConnector.PlayerEventCallback keyAt = callbacks.keyAt(i);
            callbacks.valueAt(i).execute(new Runnable() {
                public void run() {
                    keyAt.onSeekCompleted(RouteMediaPlayerConnector.this, j);
                }
            });
        }
    }

    public final void notifyPlayerVolumeChanged() {
        SimpleArrayMap<MediaPlayerConnector.PlayerEventCallback, Executor> callbacks = getCallbacks();
        final float playerVolume = getPlayerVolume();
        for (int i = 0; i < callbacks.size(); i++) {
            if (callbacks.keyAt(i) instanceof BaseRemoteMediaPlayerConnector.RemotePlayerEventCallback) {
                final BaseRemoteMediaPlayerConnector.RemotePlayerEventCallback remotePlayerEventCallback = (BaseRemoteMediaPlayerConnector.RemotePlayerEventCallback) callbacks.keyAt(i);
                Executor valueAt = callbacks.valueAt(i);
                if (remotePlayerEventCallback instanceof BaseRemoteMediaPlayerConnector.RemotePlayerEventCallback) {
                    valueAt.execute(new Runnable() {
                        public void run() {
                            remotePlayerEventCallback.onPlayerVolumeChanged(RouteMediaPlayerConnector.this, playerVolume);
                        }
                    });
                }
            }
        }
    }

    private SimpleArrayMap<MediaPlayerConnector.PlayerEventCallback, Executor> getCallbacks() {
        SimpleArrayMap<MediaPlayerConnector.PlayerEventCallback, Executor> simpleArrayMap = new SimpleArrayMap<>();
        synchronized (this.mLock) {
            simpleArrayMap.putAll(this.mCallbacks);
        }
        return simpleArrayMap;
    }
}

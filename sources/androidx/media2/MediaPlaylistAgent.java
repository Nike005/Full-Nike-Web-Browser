package androidx.media2;

import android.util.Log;
import androidx.collection.SimpleArrayMap;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;
import java.util.concurrent.Executor;

public abstract class MediaPlaylistAgent {
    public static final int REPEAT_MODE_ALL = 2;
    public static final int REPEAT_MODE_GROUP = 3;
    public static final int REPEAT_MODE_NONE = 0;
    public static final int REPEAT_MODE_ONE = 1;
    public static final int SHUFFLE_MODE_ALL = 1;
    public static final int SHUFFLE_MODE_GROUP = 2;
    public static final int SHUFFLE_MODE_NONE = 0;
    private static final String TAG = "MediaPlaylistAgent";
    private final SimpleArrayMap<PlaylistEventCallback, Executor> mCallbacks = new SimpleArrayMap<>();
    private final Object mLock = new Object();

    public static abstract class PlaylistEventCallback {
        public void onPlaylistChanged(MediaPlaylistAgent mediaPlaylistAgent, List<MediaItem2> list, MediaMetadata2 mediaMetadata2) {
        }

        public void onPlaylistMetadataChanged(MediaPlaylistAgent mediaPlaylistAgent, MediaMetadata2 mediaMetadata2) {
        }

        public void onRepeatModeChanged(MediaPlaylistAgent mediaPlaylistAgent, int i) {
        }

        public void onShuffleModeChanged(MediaPlaylistAgent mediaPlaylistAgent, int i) {
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface RepeatMode {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface ShuffleMode {
    }

    public abstract void addPlaylistItem(int i, MediaItem2 mediaItem2);

    public abstract MediaItem2 getCurrentMediaItem();

    public abstract List<MediaItem2> getPlaylist();

    public abstract MediaMetadata2 getPlaylistMetadata();

    public abstract int getRepeatMode();

    public abstract int getShuffleMode();

    public abstract void removePlaylistItem(MediaItem2 mediaItem2);

    public abstract void replacePlaylistItem(int i, MediaItem2 mediaItem2);

    public abstract void setPlaylist(List<MediaItem2> list, MediaMetadata2 mediaMetadata2);

    public abstract void setRepeatMode(int i);

    public abstract void setShuffleMode(int i);

    public abstract void skipToNextItem();

    public abstract void skipToPlaylistItem(MediaItem2 mediaItem2);

    public abstract void skipToPreviousItem();

    public abstract void updatePlaylistMetadata(MediaMetadata2 mediaMetadata2);

    public final void registerPlaylistEventCallback(Executor executor, PlaylistEventCallback playlistEventCallback) {
        if (executor == null) {
            throw new IllegalArgumentException("executor shouldn't be null");
        } else if (playlistEventCallback != null) {
            synchronized (this.mLock) {
                if (this.mCallbacks.get(playlistEventCallback) != null) {
                    Log.w(TAG, "callback is already added. Ignoring.");
                } else {
                    this.mCallbacks.put(playlistEventCallback, executor);
                }
            }
        } else {
            throw new IllegalArgumentException("callback shouldn't be null");
        }
    }

    public final void unregisterPlaylistEventCallback(PlaylistEventCallback playlistEventCallback) {
        if (playlistEventCallback != null) {
            synchronized (this.mLock) {
                this.mCallbacks.remove(playlistEventCallback);
            }
            return;
        }
        throw new IllegalArgumentException("callback shouldn't be null");
    }

    /* access modifiers changed from: protected */
    public final void notifyPlaylistChanged() {
        SimpleArrayMap<PlaylistEventCallback, Executor> callbacks = getCallbacks();
        final List<MediaItem2> playlist = getPlaylist();
        final MediaMetadata2 playlistMetadata = getPlaylistMetadata();
        for (int i = 0; i < callbacks.size(); i++) {
            final PlaylistEventCallback keyAt = callbacks.keyAt(i);
            callbacks.valueAt(i).execute(new Runnable() {
                public void run() {
                    keyAt.onPlaylistChanged(MediaPlaylistAgent.this, playlist, playlistMetadata);
                }
            });
        }
    }

    /* access modifiers changed from: protected */
    public final void notifyPlaylistMetadataChanged() {
        SimpleArrayMap<PlaylistEventCallback, Executor> callbacks = getCallbacks();
        for (int i = 0; i < callbacks.size(); i++) {
            final PlaylistEventCallback keyAt = callbacks.keyAt(i);
            callbacks.valueAt(i).execute(new Runnable() {
                public void run() {
                    PlaylistEventCallback playlistEventCallback = keyAt;
                    MediaPlaylistAgent mediaPlaylistAgent = MediaPlaylistAgent.this;
                    playlistEventCallback.onPlaylistMetadataChanged(mediaPlaylistAgent, mediaPlaylistAgent.getPlaylistMetadata());
                }
            });
        }
    }

    /* access modifiers changed from: protected */
    public final void notifyShuffleModeChanged() {
        SimpleArrayMap<PlaylistEventCallback, Executor> callbacks = getCallbacks();
        for (int i = 0; i < callbacks.size(); i++) {
            final PlaylistEventCallback keyAt = callbacks.keyAt(i);
            callbacks.valueAt(i).execute(new Runnable() {
                public void run() {
                    PlaylistEventCallback playlistEventCallback = keyAt;
                    MediaPlaylistAgent mediaPlaylistAgent = MediaPlaylistAgent.this;
                    playlistEventCallback.onShuffleModeChanged(mediaPlaylistAgent, mediaPlaylistAgent.getShuffleMode());
                }
            });
        }
    }

    /* access modifiers changed from: protected */
    public final void notifyRepeatModeChanged() {
        SimpleArrayMap<PlaylistEventCallback, Executor> callbacks = getCallbacks();
        for (int i = 0; i < callbacks.size(); i++) {
            final PlaylistEventCallback keyAt = callbacks.keyAt(i);
            callbacks.valueAt(i).execute(new Runnable() {
                public void run() {
                    PlaylistEventCallback playlistEventCallback = keyAt;
                    MediaPlaylistAgent mediaPlaylistAgent = MediaPlaylistAgent.this;
                    playlistEventCallback.onRepeatModeChanged(mediaPlaylistAgent, mediaPlaylistAgent.getRepeatMode());
                }
            });
        }
    }

    public MediaItem2 getMediaItem(DataSourceDesc2 dataSourceDesc2) {
        if (dataSourceDesc2 != null) {
            List<MediaItem2> playlist = getPlaylist();
            if (playlist == null) {
                return null;
            }
            for (int i = 0; i < playlist.size(); i++) {
                MediaItem2 mediaItem2 = playlist.get(i);
                if (mediaItem2 != null && mediaItem2.getDataSourceDesc() != null && mediaItem2.getDataSourceDesc().equals(dataSourceDesc2)) {
                    return mediaItem2;
                }
            }
            return null;
        }
        throw new IllegalArgumentException("dsd shouldn't be null");
    }

    private SimpleArrayMap<PlaylistEventCallback, Executor> getCallbacks() {
        SimpleArrayMap<PlaylistEventCallback, Executor> simpleArrayMap = new SimpleArrayMap<>();
        synchronized (this.mLock) {
            simpleArrayMap.putAll(this.mCallbacks);
        }
        return simpleArrayMap;
    }
}

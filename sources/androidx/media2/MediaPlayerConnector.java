package androidx.media2;

import androidx.media.AudioAttributesCompat;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;
import java.util.concurrent.Executor;

public abstract class MediaPlayerConnector implements AutoCloseable {
    public static final int BUFFERING_STATE_BUFFERING_AND_PLAYABLE = 1;
    public static final int BUFFERING_STATE_BUFFERING_AND_STARVED = 2;
    public static final int BUFFERING_STATE_BUFFERING_COMPLETE = 3;
    public static final int BUFFERING_STATE_UNKNOWN = 0;
    public static final int PLAYER_STATE_ERROR = 3;
    public static final int PLAYER_STATE_IDLE = 0;
    public static final int PLAYER_STATE_PAUSED = 1;
    public static final int PLAYER_STATE_PLAYING = 2;
    public static final long UNKNOWN_TIME = -1;

    @Retention(RetentionPolicy.SOURCE)
    public @interface BuffState {
    }

    public static abstract class PlayerEventCallback {
        public void onBufferingStateChanged(MediaPlayerConnector mediaPlayerConnector, DataSourceDesc2 dataSourceDesc2, int i) {
        }

        public void onCurrentDataSourceChanged(MediaPlayerConnector mediaPlayerConnector, DataSourceDesc2 dataSourceDesc2) {
        }

        public void onMediaPrepared(MediaPlayerConnector mediaPlayerConnector, DataSourceDesc2 dataSourceDesc2) {
        }

        public void onPlaybackSpeedChanged(MediaPlayerConnector mediaPlayerConnector, float f) {
        }

        public void onPlayerStateChanged(MediaPlayerConnector mediaPlayerConnector, int i) {
        }

        public void onSeekCompleted(MediaPlayerConnector mediaPlayerConnector, long j) {
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface PlayerState {
    }

    public abstract AudioAttributesCompat getAudioAttributes();

    public long getBufferedPosition() {
        return -1;
    }

    public abstract int getBufferingState();

    public abstract DataSourceDesc2 getCurrentDataSource();

    public long getCurrentPosition() {
        return -1;
    }

    public long getDuration() {
        return -1;
    }

    public float getMaxPlayerVolume() {
        return 1.0f;
    }

    public float getPlaybackSpeed() {
        return 1.0f;
    }

    public abstract int getPlayerState();

    public abstract float getPlayerVolume();

    public boolean isReversePlaybackSupported() {
        return false;
    }

    public abstract void loopCurrent(boolean z);

    public abstract void pause();

    public abstract void play();

    public abstract void prepare();

    public abstract void registerPlayerEventCallback(Executor executor, PlayerEventCallback playerEventCallback);

    public abstract void reset();

    public abstract void seekTo(long j);

    public abstract void setAudioAttributes(AudioAttributesCompat audioAttributesCompat);

    public abstract void setDataSource(DataSourceDesc2 dataSourceDesc2);

    public abstract void setNextDataSource(DataSourceDesc2 dataSourceDesc2);

    public abstract void setNextDataSources(List<DataSourceDesc2> list);

    public abstract void setPlaybackSpeed(float f);

    public abstract void setPlayerVolume(float f);

    public abstract void skipToNext();

    public abstract void unregisterPlayerEventCallback(PlayerEventCallback playerEventCallback);
}

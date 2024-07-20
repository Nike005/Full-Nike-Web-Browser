package androidx.media2;

import android.content.Context;
import android.media.DeniedByServerException;
import android.media.MediaDrm;
import android.media.MediaDrmException;
import android.media.MediaFormat;
import android.os.PersistableBundle;
import android.view.Surface;
import androidx.media.AudioAttributesCompat;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.Executor;

public abstract class MediaPlayer2 {
    public static final int CALL_COMPLETED_ATTACH_AUX_EFFECT = 1;
    public static final int CALL_COMPLETED_DESELECT_TRACK = 2;
    public static final int CALL_COMPLETED_LOOP_CURRENT = 3;
    public static final int CALL_COMPLETED_NOTIFY_WHEN_COMMAND_LABEL_REACHED = 1000;
    public static final int CALL_COMPLETED_PAUSE = 4;
    public static final int CALL_COMPLETED_PLAY = 5;
    public static final int CALL_COMPLETED_PREPARE = 6;
    public static final int CALL_COMPLETED_PREPARE_DRM = 1001;
    public static final int CALL_COMPLETED_SEEK_TO = 14;
    public static final int CALL_COMPLETED_SELECT_TRACK = 15;
    public static final int CALL_COMPLETED_SET_AUDIO_ATTRIBUTES = 16;
    public static final int CALL_COMPLETED_SET_AUDIO_SESSION_ID = 17;
    public static final int CALL_COMPLETED_SET_AUX_EFFECT_SEND_LEVEL = 18;
    public static final int CALL_COMPLETED_SET_DATA_SOURCE = 19;
    public static final int CALL_COMPLETED_SET_NEXT_DATA_SOURCE = 22;
    public static final int CALL_COMPLETED_SET_NEXT_DATA_SOURCES = 23;
    public static final int CALL_COMPLETED_SET_PLAYBACK_PARAMS = 24;
    public static final int CALL_COMPLETED_SET_PLAYER_VOLUME = 26;
    public static final int CALL_COMPLETED_SET_SURFACE = 27;
    public static final int CALL_COMPLETED_SKIP_TO_NEXT = 29;
    public static final int CALL_STATUS_BAD_VALUE = 2;
    public static final int CALL_STATUS_ERROR_IO = 4;
    public static final int CALL_STATUS_ERROR_UNKNOWN = Integer.MIN_VALUE;
    public static final int CALL_STATUS_INVALID_OPERATION = 1;
    public static final int CALL_STATUS_NO_ERROR = 0;
    public static final int CALL_STATUS_PERMISSION_DENIED = 3;
    public static final int CALL_STATUS_SKIPPED = 5;
    public static final int MEDIA_ERROR_IO = -1004;
    public static final int MEDIA_ERROR_MALFORMED = -1007;
    public static final int MEDIA_ERROR_NOT_VALID_FOR_PROGRESSIVE_PLAYBACK = 200;
    public static final int MEDIA_ERROR_SYSTEM = Integer.MIN_VALUE;
    public static final int MEDIA_ERROR_TIMED_OUT = -110;
    public static final int MEDIA_ERROR_UNKNOWN = 1;
    public static final int MEDIA_ERROR_UNSUPPORTED = -1010;
    public static final int MEDIA_INFO_AUDIO_NOT_PLAYING = 804;
    public static final int MEDIA_INFO_AUDIO_RENDERING_START = 4;
    public static final int MEDIA_INFO_BAD_INTERLEAVING = 800;
    public static final int MEDIA_INFO_BUFFERING_END = 702;
    public static final int MEDIA_INFO_BUFFERING_START = 701;
    public static final int MEDIA_INFO_BUFFERING_UPDATE = 704;
    public static final int MEDIA_INFO_DATA_SOURCE_END = 5;
    public static final int MEDIA_INFO_DATA_SOURCE_LIST_END = 6;
    public static final int MEDIA_INFO_DATA_SOURCE_REPEAT = 7;
    public static final int MEDIA_INFO_DATA_SOURCE_START = 2;
    public static final int MEDIA_INFO_EXTERNAL_METADATA_UPDATE = 803;
    public static final int MEDIA_INFO_METADATA_UPDATE = 802;
    public static final int MEDIA_INFO_NETWORK_BANDWIDTH = 703;
    public static final int MEDIA_INFO_NOT_SEEKABLE = 801;
    public static final int MEDIA_INFO_PREPARED = 100;
    public static final int MEDIA_INFO_SUBTITLE_TIMED_OUT = 902;
    public static final int MEDIA_INFO_TIMED_TEXT_ERROR = 900;
    public static final int MEDIA_INFO_UNKNOWN = 1;
    public static final int MEDIA_INFO_UNSUPPORTED_SUBTITLE = 901;
    public static final int MEDIA_INFO_VIDEO_NOT_PLAYING = 805;
    public static final int MEDIA_INFO_VIDEO_RENDERING_START = 3;
    public static final int MEDIA_INFO_VIDEO_TRACK_LAGGING = 700;
    public static final int PLAYER_STATE_ERROR = 1005;
    public static final int PLAYER_STATE_IDLE = 1001;
    public static final int PLAYER_STATE_PAUSED = 1003;
    public static final int PLAYER_STATE_PLAYING = 1004;
    public static final int PLAYER_STATE_PREPARED = 1002;
    public static final int PREPARE_DRM_STATUS_PREPARATION_ERROR = 3;
    public static final int PREPARE_DRM_STATUS_PROVISIONING_NETWORK_ERROR = 1;
    public static final int PREPARE_DRM_STATUS_PROVISIONING_SERVER_ERROR = 2;
    public static final int PREPARE_DRM_STATUS_RESOURCE_BUSY = 5;
    public static final int PREPARE_DRM_STATUS_SUCCESS = 0;
    public static final int PREPARE_DRM_STATUS_UNSUPPORTED_SCHEME = 4;
    public static final int SEEK_CLOSEST = 3;
    public static final int SEEK_CLOSEST_SYNC = 2;
    public static final int SEEK_NEXT_SYNC = 1;
    public static final int SEEK_PREVIOUS_SYNC = 0;
    static final int SEPARATE_CALL_COMPLETE_CALLBACK_START = 1000;
    public static final int VIDEO_SCALING_MODE_SCALE_TO_FIT = 1;

    @Retention(RetentionPolicy.SOURCE)
    public @interface CallCompleted {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface CallStatus {
    }

    public static abstract class DrmEventCallback {
        public void onDrmInfo(MediaPlayer2 mediaPlayer2, DataSourceDesc2 dataSourceDesc2, DrmInfo drmInfo) {
        }

        public void onDrmPrepared(MediaPlayer2 mediaPlayer2, DataSourceDesc2 dataSourceDesc2, int i) {
        }
    }

    public static abstract class DrmInfo {
        public abstract Map<UUID, byte[]> getPssh();

        public abstract List<UUID> getSupportedSchemes();
    }

    public static abstract class EventCallback {
        public void onCallCompleted(MediaPlayer2 mediaPlayer2, DataSourceDesc2 dataSourceDesc2, int i, int i2) {
        }

        public void onCommandLabelReached(MediaPlayer2 mediaPlayer2, Object obj) {
        }

        public void onError(MediaPlayer2 mediaPlayer2, DataSourceDesc2 dataSourceDesc2, int i, int i2) {
        }

        public void onInfo(MediaPlayer2 mediaPlayer2, DataSourceDesc2 dataSourceDesc2, int i, int i2) {
        }

        public void onMediaTimeDiscontinuity(MediaPlayer2 mediaPlayer2, DataSourceDesc2 dataSourceDesc2, MediaTimestamp2 mediaTimestamp2) {
        }

        public void onSubtitleData(MediaPlayer2 mediaPlayer2, DataSourceDesc2 dataSourceDesc2, SubtitleData2 subtitleData2) {
        }

        public void onTimedMetaDataAvailable(MediaPlayer2 mediaPlayer2, DataSourceDesc2 dataSourceDesc2, TimedMetaData2 timedMetaData2) {
        }

        public void onVideoSizeChanged(MediaPlayer2 mediaPlayer2, DataSourceDesc2 dataSourceDesc2, int i, int i2) {
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface MediaError {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface MediaInfo {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface MediaPlayer2State {
    }

    public interface OnDrmConfigHelper {
        void onDrmConfig(MediaPlayer2 mediaPlayer2, DataSourceDesc2 dataSourceDesc2);
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface PrepareDrmStatusCode {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface SeekMode {
    }

    public static abstract class TrackInfo {
        public static final int MEDIA_TRACK_TYPE_AUDIO = 2;
        public static final int MEDIA_TRACK_TYPE_METADATA = 5;
        public static final int MEDIA_TRACK_TYPE_SUBTITLE = 4;
        public static final int MEDIA_TRACK_TYPE_TIMEDTEXT = 3;
        public static final int MEDIA_TRACK_TYPE_UNKNOWN = 0;
        public static final int MEDIA_TRACK_TYPE_VIDEO = 1;

        public abstract MediaFormat getFormat();

        public abstract String getLanguage();

        public abstract int getTrackType();

        public abstract String toString();
    }

    public abstract void attachAuxEffect(int i);

    public abstract void clearDrmEventCallback();

    public abstract void clearEventCallback();

    public abstract void clearPendingCommands();

    public abstract void close();

    public abstract void deselectTrack(int i);

    public abstract AudioAttributesCompat getAudioAttributes();

    public abstract int getAudioSessionId();

    public abstract long getBufferedPosition();

    public abstract DataSourceDesc2 getCurrentDataSource();

    public abstract long getCurrentPosition();

    public abstract DrmInfo getDrmInfo();

    public abstract MediaDrm.KeyRequest getDrmKeyRequest(byte[] bArr, byte[] bArr2, String str, int i, Map<String, String> map) throws NoDrmSchemeException;

    public abstract String getDrmPropertyString(String str) throws NoDrmSchemeException;

    public abstract long getDuration();

    public float getMaxPlayerVolume() {
        return 1.0f;
    }

    public abstract MediaPlayerConnector getMediaPlayerConnector();

    public abstract PersistableBundle getMetrics();

    public abstract PlaybackParams2 getPlaybackParams();

    public abstract float getPlayerVolume();

    public abstract int getSelectedTrack(int i);

    public abstract int getState();

    public abstract MediaTimestamp2 getTimestamp();

    public abstract List<TrackInfo> getTrackInfo();

    public abstract int getVideoHeight();

    public abstract int getVideoWidth();

    public abstract void loopCurrent(boolean z);

    public void notifyWhenCommandLabelReached(Object obj) {
    }

    public abstract void pause();

    public abstract void play();

    public abstract void prepare();

    public abstract void prepareDrm(UUID uuid);

    public abstract byte[] provideDrmKeyResponse(byte[] bArr, byte[] bArr2) throws NoDrmSchemeException, DeniedByServerException;

    public abstract void releaseDrm() throws NoDrmSchemeException;

    public abstract void reset();

    public abstract void restoreDrmKeys(byte[] bArr) throws NoDrmSchemeException;

    public abstract void seekTo(long j, int i);

    public abstract void selectTrack(int i);

    public abstract void setAudioAttributes(AudioAttributesCompat audioAttributesCompat);

    public abstract void setAudioSessionId(int i);

    public abstract void setAuxEffectSendLevel(float f);

    public abstract void setDataSource(DataSourceDesc2 dataSourceDesc2);

    public abstract void setDrmEventCallback(Executor executor, DrmEventCallback drmEventCallback);

    public abstract void setDrmPropertyString(String str, String str2) throws NoDrmSchemeException;

    public abstract void setEventCallback(Executor executor, EventCallback eventCallback);

    public abstract void setNextDataSource(DataSourceDesc2 dataSourceDesc2);

    public abstract void setNextDataSources(List<DataSourceDesc2> list);

    public abstract void setOnDrmConfigHelper(OnDrmConfigHelper onDrmConfigHelper);

    public abstract void setPlaybackParams(PlaybackParams2 playbackParams2);

    public abstract void setPlayerVolume(float f);

    public abstract void setSurface(Surface surface);

    public abstract void skipToNext();

    public static final MediaPlayer2 create(Context context) {
        return new MediaPlayer2Impl();
    }

    public void seekTo(long j) {
        seekTo(j, 0);
    }

    public static class NoDrmSchemeException extends MediaDrmException {
        public NoDrmSchemeException(String str) {
            super(str);
        }
    }

    public static final class MetricsConstants {
        public static final String CODEC_AUDIO = "android.media.mediaplayer.audio.codec";
        public static final String CODEC_VIDEO = "android.media.mediaplayer.video.codec";
        public static final String DURATION = "android.media.mediaplayer.durationMs";
        public static final String ERRORS = "android.media.mediaplayer.err";
        public static final String ERROR_CODE = "android.media.mediaplayer.errcode";
        public static final String FRAMES = "android.media.mediaplayer.frames";
        public static final String FRAMES_DROPPED = "android.media.mediaplayer.dropped";
        public static final String HEIGHT = "android.media.mediaplayer.height";
        public static final String MIME_TYPE_AUDIO = "android.media.mediaplayer.audio.mime";
        public static final String MIME_TYPE_VIDEO = "android.media.mediaplayer.video.mime";
        public static final String PLAYING = "android.media.mediaplayer.playingMs";
        public static final String WIDTH = "android.media.mediaplayer.width";

        private MetricsConstants() {
        }
    }
}

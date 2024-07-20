package androidx.media2;

import android.app.PendingIntent;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.support.p064v4.media.MediaBrowserCompat;
import android.text.TextUtils;
import androidx.media.AudioAttributesCompat;
import androidx.media2.MediaSession2;
import androidx.versionedparcelable.VersionedParcelable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;
import java.util.concurrent.Executor;

public class MediaController2 implements AutoCloseable {
    private final MediaController2Impl mImpl;
    Long mTimeDiff;

    public static abstract class ControllerCallback {
        public void onAllowedCommandsChanged(MediaController2 mediaController2, SessionCommandGroup2 sessionCommandGroup2) {
        }

        public void onBufferingStateChanged(MediaController2 mediaController2, MediaItem2 mediaItem2, int i) {
        }

        public void onConnected(MediaController2 mediaController2, SessionCommandGroup2 sessionCommandGroup2) {
        }

        public void onCurrentMediaItemChanged(MediaController2 mediaController2, MediaItem2 mediaItem2) {
        }

        public void onCustomCommand(MediaController2 mediaController2, SessionCommand2 sessionCommand2, Bundle bundle, ResultReceiver resultReceiver) {
        }

        public void onCustomLayoutChanged(MediaController2 mediaController2, List<MediaSession2.CommandButton> list) {
        }

        public void onDisconnected(MediaController2 mediaController2) {
        }

        public void onError(MediaController2 mediaController2, int i, Bundle bundle) {
        }

        public void onPlaybackInfoChanged(MediaController2 mediaController2, PlaybackInfo playbackInfo) {
        }

        public void onPlaybackSpeedChanged(MediaController2 mediaController2, float f) {
        }

        public void onPlayerStateChanged(MediaController2 mediaController2, int i) {
        }

        public void onPlaylistChanged(MediaController2 mediaController2, List<MediaItem2> list, MediaMetadata2 mediaMetadata2) {
        }

        public void onPlaylistMetadataChanged(MediaController2 mediaController2, MediaMetadata2 mediaMetadata2) {
        }

        public void onRepeatModeChanged(MediaController2 mediaController2, int i) {
        }

        public void onRoutesInfoChanged(MediaController2 mediaController2, List<Bundle> list) {
        }

        public void onSeekCompleted(MediaController2 mediaController2, long j) {
        }

        public void onShuffleModeChanged(MediaController2 mediaController2, int i) {
        }
    }

    interface MediaController2Impl extends AutoCloseable {
        void addPlaylistItem(int i, MediaItem2 mediaItem2);

        void adjustVolume(int i, int i2);

        void fastForward();

        MediaBrowserCompat getBrowserCompat();

        long getBufferedPosition();

        int getBufferingState();

        ControllerCallback getCallback();

        Executor getCallbackExecutor();

        Context getContext();

        MediaItem2 getCurrentMediaItem();

        long getCurrentPosition();

        long getDuration();

        MediaController2 getInstance();

        PlaybackInfo getPlaybackInfo();

        float getPlaybackSpeed();

        int getPlayerState();

        List<MediaItem2> getPlaylist();

        MediaMetadata2 getPlaylistMetadata();

        int getRepeatMode();

        PendingIntent getSessionActivity();

        SessionToken2 getSessionToken();

        int getShuffleMode();

        boolean isConnected();

        void pause();

        void play();

        void playFromMediaId(String str, Bundle bundle);

        void playFromSearch(String str, Bundle bundle);

        void playFromUri(Uri uri, Bundle bundle);

        void prepare();

        void prepareFromMediaId(String str, Bundle bundle);

        void prepareFromSearch(String str, Bundle bundle);

        void prepareFromUri(Uri uri, Bundle bundle);

        void removePlaylistItem(MediaItem2 mediaItem2);

        void replacePlaylistItem(int i, MediaItem2 mediaItem2);

        void reset();

        void rewind();

        void seekTo(long j);

        void selectRoute(Bundle bundle);

        void sendCustomCommand(SessionCommand2 sessionCommand2, Bundle bundle, ResultReceiver resultReceiver);

        void setPlaybackSpeed(float f);

        void setPlaylist(List<MediaItem2> list, MediaMetadata2 mediaMetadata2);

        void setRating(String str, Rating2 rating2);

        void setRepeatMode(int i);

        void setShuffleMode(int i);

        void setVolumeTo(int i, int i2);

        void skipBackward();

        void skipForward();

        void skipToNextItem();

        void skipToPlaylistItem(MediaItem2 mediaItem2);

        void skipToPreviousItem();

        void subscribeRoutesInfo();

        void unsubscribeRoutesInfo();

        void updatePlaylistMetadata(MediaMetadata2 mediaMetadata2);
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface VolumeDirection {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface VolumeFlags {
    }

    public MediaController2(Context context, SessionToken2 sessionToken2, Executor executor, ControllerCallback controllerCallback) {
        if (context == null) {
            throw new IllegalArgumentException("context shouldn't be null");
        } else if (sessionToken2 == null) {
            throw new IllegalArgumentException("token shouldn't be null");
        } else if (controllerCallback == null) {
            throw new IllegalArgumentException("callback shouldn't be null");
        } else if (executor != null) {
            this.mImpl = createImpl(context, sessionToken2, executor, controllerCallback);
        } else {
            throw new IllegalArgumentException("executor shouldn't be null");
        }
    }

    /* access modifiers changed from: package-private */
    public MediaController2Impl createImpl(Context context, SessionToken2 sessionToken2, Executor executor, ControllerCallback controllerCallback) {
        if (sessionToken2.isLegacySession()) {
            return new MediaController2ImplLegacy(context, this, sessionToken2, executor, controllerCallback);
        }
        return new MediaController2ImplBase(context, this, sessionToken2, executor, controllerCallback);
    }

    /* access modifiers changed from: package-private */
    public MediaController2Impl getImpl() {
        return this.mImpl;
    }

    public void close() {
        try {
            this.mImpl.close();
        } catch (Exception unused) {
        }
    }

    public SessionToken2 getSessionToken() {
        return this.mImpl.getSessionToken();
    }

    public boolean isConnected() {
        return this.mImpl.isConnected();
    }

    public void play() {
        this.mImpl.play();
    }

    public void pause() {
        this.mImpl.pause();
    }

    public void reset() {
        this.mImpl.reset();
    }

    public void prepare() {
        this.mImpl.prepare();
    }

    public void fastForward() {
        this.mImpl.fastForward();
    }

    public void rewind() {
        this.mImpl.rewind();
    }

    public void seekTo(long j) {
        this.mImpl.seekTo(j);
    }

    public void skipForward() {
        this.mImpl.skipForward();
    }

    public void skipBackward() {
        this.mImpl.skipBackward();
    }

    public void playFromMediaId(String str, Bundle bundle) {
        if (str != null) {
            this.mImpl.playFromMediaId(str, bundle);
            return;
        }
        throw new IllegalArgumentException("mediaId shouldn't be null");
    }

    public void playFromSearch(String str, Bundle bundle) {
        if (!TextUtils.isEmpty(str)) {
            this.mImpl.playFromSearch(str, bundle);
            return;
        }
        throw new IllegalArgumentException("query shouldn't be empty");
    }

    public void playFromUri(Uri uri, Bundle bundle) {
        if (uri != null) {
            this.mImpl.playFromUri(uri, bundle);
            return;
        }
        throw new IllegalArgumentException("uri shouldn't be null");
    }

    public void prepareFromMediaId(String str, Bundle bundle) {
        if (str != null) {
            this.mImpl.prepareFromMediaId(str, bundle);
            return;
        }
        throw new IllegalArgumentException("mediaId shouldn't be null");
    }

    public void prepareFromSearch(String str, Bundle bundle) {
        if (!TextUtils.isEmpty(str)) {
            this.mImpl.prepareFromSearch(str, bundle);
            return;
        }
        throw new IllegalArgumentException("query shouldn't be empty");
    }

    public void prepareFromUri(Uri uri, Bundle bundle) {
        if (uri != null) {
            this.mImpl.prepareFromUri(uri, bundle);
            return;
        }
        throw new IllegalArgumentException("uri shouldn't be null");
    }

    public void setVolumeTo(int i, int i2) {
        this.mImpl.setVolumeTo(i, i2);
    }

    public void adjustVolume(int i, int i2) {
        this.mImpl.adjustVolume(i, i2);
    }

    public PendingIntent getSessionActivity() {
        return this.mImpl.getSessionActivity();
    }

    public int getPlayerState() {
        return this.mImpl.getPlayerState();
    }

    public long getDuration() {
        return this.mImpl.getDuration();
    }

    public long getCurrentPosition() {
        return this.mImpl.getCurrentPosition();
    }

    public float getPlaybackSpeed() {
        return this.mImpl.getPlaybackSpeed();
    }

    public void setPlaybackSpeed(float f) {
        this.mImpl.setPlaybackSpeed(f);
    }

    public int getBufferingState() {
        return this.mImpl.getBufferingState();
    }

    public long getBufferedPosition() {
        return this.mImpl.getBufferedPosition();
    }

    public PlaybackInfo getPlaybackInfo() {
        return this.mImpl.getPlaybackInfo();
    }

    public void setRating(String str, Rating2 rating2) {
        if (str == null) {
            throw new IllegalArgumentException("mediaId shouldn't be null");
        } else if (rating2 != null) {
            this.mImpl.setRating(str, rating2);
        } else {
            throw new IllegalArgumentException("rating shouldn't be null");
        }
    }

    public void sendCustomCommand(SessionCommand2 sessionCommand2, Bundle bundle, ResultReceiver resultReceiver) {
        if (sessionCommand2 == null) {
            throw new IllegalArgumentException("command shouldn't be null");
        } else if (sessionCommand2.getCommandCode() == 0) {
            this.mImpl.sendCustomCommand(sessionCommand2, bundle, resultReceiver);
        } else {
            throw new IllegalArgumentException("command should be a custom command");
        }
    }

    public List<MediaItem2> getPlaylist() {
        return this.mImpl.getPlaylist();
    }

    public void setPlaylist(List<MediaItem2> list, MediaMetadata2 mediaMetadata2) {
        if (list != null) {
            this.mImpl.setPlaylist(list, mediaMetadata2);
            return;
        }
        throw new IllegalArgumentException("list shouldn't be null");
    }

    public void updatePlaylistMetadata(MediaMetadata2 mediaMetadata2) {
        this.mImpl.updatePlaylistMetadata(mediaMetadata2);
    }

    public MediaMetadata2 getPlaylistMetadata() {
        return this.mImpl.getPlaylistMetadata();
    }

    public void addPlaylistItem(int i, MediaItem2 mediaItem2) {
        if (i < 0) {
            throw new IllegalArgumentException("index shouldn't be negative");
        } else if (mediaItem2 != null) {
            this.mImpl.addPlaylistItem(i, mediaItem2);
        } else {
            throw new IllegalArgumentException("item shouldn't be null");
        }
    }

    public void removePlaylistItem(MediaItem2 mediaItem2) {
        if (mediaItem2 != null) {
            this.mImpl.removePlaylistItem(mediaItem2);
            return;
        }
        throw new IllegalArgumentException("item shouldn't be null");
    }

    public void replacePlaylistItem(int i, MediaItem2 mediaItem2) {
        if (i < 0) {
            throw new IllegalArgumentException("index shouldn't be negative");
        } else if (mediaItem2 != null) {
            this.mImpl.replacePlaylistItem(i, mediaItem2);
        } else {
            throw new IllegalArgumentException("item shouldn't be null");
        }
    }

    public MediaItem2 getCurrentMediaItem() {
        return this.mImpl.getCurrentMediaItem();
    }

    public void skipToPreviousItem() {
        this.mImpl.skipToPreviousItem();
    }

    public void skipToNextItem() {
        this.mImpl.skipToNextItem();
    }

    public void skipToPlaylistItem(MediaItem2 mediaItem2) {
        if (mediaItem2 != null) {
            this.mImpl.skipToPlaylistItem(mediaItem2);
            return;
        }
        throw new IllegalArgumentException("item shouldn't be null");
    }

    public int getRepeatMode() {
        return this.mImpl.getRepeatMode();
    }

    public void setRepeatMode(int i) {
        this.mImpl.setRepeatMode(i);
    }

    public int getShuffleMode() {
        return this.mImpl.getShuffleMode();
    }

    public void setShuffleMode(int i) {
        this.mImpl.setShuffleMode(i);
    }

    public void subscribeRoutesInfo() {
        this.mImpl.subscribeRoutesInfo();
    }

    public void unsubscribeRoutesInfo() {
        this.mImpl.unsubscribeRoutesInfo();
    }

    public void selectRoute(Bundle bundle) {
        if (bundle != null) {
            this.mImpl.selectRoute(bundle);
            return;
        }
        throw new IllegalArgumentException("route shouldn't be null");
    }

    public void setTimeDiff(Long l) {
        this.mTimeDiff = l;
    }

    /* access modifiers changed from: package-private */
    public Context getContext() {
        return this.mImpl.getContext();
    }

    /* access modifiers changed from: package-private */
    public ControllerCallback getCallback() {
        return this.mImpl.getCallback();
    }

    /* access modifiers changed from: package-private */
    public Executor getCallbackExecutor() {
        return this.mImpl.getCallbackExecutor();
    }

    /* access modifiers changed from: package-private */
    public MediaBrowserCompat getBrowserCompat() {
        return this.mImpl.getBrowserCompat();
    }

    public static final class PlaybackInfo implements VersionedParcelable {
        private static final String KEY_AUDIO_ATTRIBUTES = "android.media.audio_info.audio_attrs";
        private static final String KEY_CONTROL_TYPE = "android.media.audio_info.control_type";
        private static final String KEY_CURRENT_VOLUME = "android.media.audio_info.current_volume";
        private static final String KEY_MAX_VOLUME = "android.media.audio_info.max_volume";
        private static final String KEY_PLAYBACK_TYPE = "android.media.audio_info.playback_type";
        public static final int PLAYBACK_TYPE_LOCAL = 1;
        public static final int PLAYBACK_TYPE_REMOTE = 2;
        AudioAttributesCompat mAudioAttrsCompat;
        int mControlType;
        int mCurrentVolume;
        int mMaxVolume;
        int mPlaybackType;

        PlaybackInfo() {
        }

        PlaybackInfo(int i, AudioAttributesCompat audioAttributesCompat, int i2, int i3, int i4) {
            this.mPlaybackType = i;
            this.mAudioAttrsCompat = audioAttributesCompat;
            this.mControlType = i2;
            this.mMaxVolume = i3;
            this.mCurrentVolume = i4;
        }

        public int getPlaybackType() {
            return this.mPlaybackType;
        }

        public AudioAttributesCompat getAudioAttributes() {
            return this.mAudioAttrsCompat;
        }

        public int getControlType() {
            return this.mControlType;
        }

        public int getMaxVolume() {
            return this.mMaxVolume;
        }

        public int getCurrentVolume() {
            return this.mCurrentVolume;
        }

        /* access modifiers changed from: package-private */
        public Bundle toBundle() {
            Bundle bundle = new Bundle();
            bundle.putInt(KEY_PLAYBACK_TYPE, this.mPlaybackType);
            bundle.putInt(KEY_CONTROL_TYPE, this.mControlType);
            bundle.putInt(KEY_MAX_VOLUME, this.mMaxVolume);
            bundle.putInt(KEY_CURRENT_VOLUME, this.mCurrentVolume);
            AudioAttributesCompat audioAttributesCompat = this.mAudioAttrsCompat;
            if (audioAttributesCompat != null) {
                bundle.putBundle(KEY_AUDIO_ATTRIBUTES, audioAttributesCompat.toBundle());
            }
            return bundle;
        }

        static PlaybackInfo createPlaybackInfo(int i, AudioAttributesCompat audioAttributesCompat, int i2, int i3, int i4) {
            return new PlaybackInfo(i, audioAttributesCompat, i2, i3, i4);
        }

        static PlaybackInfo fromBundle(Bundle bundle) {
            if (bundle == null) {
                return null;
            }
            return createPlaybackInfo(bundle.getInt(KEY_PLAYBACK_TYPE), AudioAttributesCompat.fromBundle(bundle.getBundle(KEY_AUDIO_ATTRIBUTES)), bundle.getInt(KEY_CONTROL_TYPE), bundle.getInt(KEY_MAX_VOLUME), bundle.getInt(KEY_CURRENT_VOLUME));
        }
    }
}

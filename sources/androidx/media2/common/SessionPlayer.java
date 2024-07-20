package androidx.media2.common;

import android.media.MediaFormat;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import android.view.Surface;
import androidx.core.util.Pair;
import androidx.media.AudioAttributesCompat;
import androidx.media2.common.futures.ResolvableFuture;
import androidx.versionedparcelable.CustomVersionedParcelable;
import com.google.common.util.concurrent.ListenableFuture;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Executor;

public abstract class SessionPlayer implements AutoCloseable {
    public static final int BUFFERING_STATE_BUFFERING_AND_PLAYABLE = 1;
    public static final int BUFFERING_STATE_BUFFERING_AND_STARVED = 2;
    public static final int BUFFERING_STATE_COMPLETE = 3;
    public static final int BUFFERING_STATE_UNKNOWN = 0;
    public static final int INVALID_ITEM_INDEX = -1;
    public static final int PLAYER_STATE_ERROR = 3;
    public static final int PLAYER_STATE_IDLE = 0;
    public static final int PLAYER_STATE_PAUSED = 1;
    public static final int PLAYER_STATE_PLAYING = 2;
    public static final int REPEAT_MODE_ALL = 2;
    public static final int REPEAT_MODE_GROUP = 3;
    public static final int REPEAT_MODE_NONE = 0;
    public static final int REPEAT_MODE_ONE = 1;
    public static final int SHUFFLE_MODE_ALL = 1;
    public static final int SHUFFLE_MODE_GROUP = 2;
    public static final int SHUFFLE_MODE_NONE = 0;
    private static final String TAG = "SessionPlayer";
    public static final long UNKNOWN_TIME = Long.MIN_VALUE;
    private final List<Pair<PlayerCallback, Executor>> mCallbacks = new ArrayList();
    private final Object mLock = new Object();

    @Retention(RetentionPolicy.SOURCE)
    public @interface BuffState {
    }

    public static abstract class PlayerCallback {
        public void onAudioAttributesChanged(SessionPlayer sessionPlayer, AudioAttributesCompat audioAttributesCompat) {
        }

        public void onBufferingStateChanged(SessionPlayer sessionPlayer, MediaItem mediaItem, int i) {
        }

        public void onCurrentMediaItemChanged(SessionPlayer sessionPlayer, MediaItem mediaItem) {
        }

        public void onPlaybackCompleted(SessionPlayer sessionPlayer) {
        }

        public void onPlaybackSpeedChanged(SessionPlayer sessionPlayer, float f) {
        }

        public void onPlayerStateChanged(SessionPlayer sessionPlayer, int i) {
        }

        public void onPlaylistChanged(SessionPlayer sessionPlayer, List<MediaItem> list, MediaMetadata mediaMetadata) {
        }

        public void onPlaylistMetadataChanged(SessionPlayer sessionPlayer, MediaMetadata mediaMetadata) {
        }

        public void onRepeatModeChanged(SessionPlayer sessionPlayer, int i) {
        }

        public void onSeekCompleted(SessionPlayer sessionPlayer, long j) {
        }

        public void onShuffleModeChanged(SessionPlayer sessionPlayer, int i) {
        }

        public void onSubtitleData(SessionPlayer sessionPlayer, MediaItem mediaItem, TrackInfo trackInfo, SubtitleData subtitleData) {
        }

        public void onTrackDeselected(SessionPlayer sessionPlayer, TrackInfo trackInfo) {
        }

        public void onTrackInfoChanged(SessionPlayer sessionPlayer, List<TrackInfo> list) {
        }

        public void onTrackSelected(SessionPlayer sessionPlayer, TrackInfo trackInfo) {
        }

        public void onVideoSizeChangedInternal(SessionPlayer sessionPlayer, MediaItem mediaItem, VideoSize videoSize) {
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface PlayerState {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface RepeatMode {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface ShuffleMode {
    }

    public abstract ListenableFuture<PlayerResult> addPlaylistItem(int i, MediaItem mediaItem);

    public abstract AudioAttributesCompat getAudioAttributes();

    public abstract long getBufferedPosition();

    public abstract int getBufferingState();

    public abstract MediaItem getCurrentMediaItem();

    public abstract int getCurrentMediaItemIndex();

    public abstract long getCurrentPosition();

    public abstract long getDuration();

    public abstract int getNextMediaItemIndex();

    public abstract float getPlaybackSpeed();

    public abstract int getPlayerState();

    public abstract List<MediaItem> getPlaylist();

    public abstract MediaMetadata getPlaylistMetadata();

    public abstract int getPreviousMediaItemIndex();

    public abstract int getRepeatMode();

    public abstract int getShuffleMode();

    public abstract ListenableFuture<PlayerResult> pause();

    public abstract ListenableFuture<PlayerResult> play();

    public abstract ListenableFuture<PlayerResult> prepare();

    public abstract ListenableFuture<PlayerResult> removePlaylistItem(int i);

    public abstract ListenableFuture<PlayerResult> replacePlaylistItem(int i, MediaItem mediaItem);

    public abstract ListenableFuture<PlayerResult> seekTo(long j);

    public abstract ListenableFuture<PlayerResult> setAudioAttributes(AudioAttributesCompat audioAttributesCompat);

    public abstract ListenableFuture<PlayerResult> setMediaItem(MediaItem mediaItem);

    public abstract ListenableFuture<PlayerResult> setPlaybackSpeed(float f);

    public abstract ListenableFuture<PlayerResult> setPlaylist(List<MediaItem> list, MediaMetadata mediaMetadata);

    public abstract ListenableFuture<PlayerResult> setRepeatMode(int i);

    public abstract ListenableFuture<PlayerResult> setShuffleMode(int i);

    public abstract ListenableFuture<PlayerResult> skipToNextPlaylistItem();

    public abstract ListenableFuture<PlayerResult> skipToPlaylistItem(int i);

    public abstract ListenableFuture<PlayerResult> skipToPreviousPlaylistItem();

    public abstract ListenableFuture<PlayerResult> updatePlaylistMetadata(MediaMetadata mediaMetadata);

    public VideoSize getVideoSizeInternal() {
        throw new UnsupportedOperationException("getVideoSizeInternal is internal use only");
    }

    public ListenableFuture<PlayerResult> setSurfaceInternal(Surface surface) {
        return PlayerResult.createFuture(-6);
    }

    public final void registerPlayerCallback(Executor executor, PlayerCallback playerCallback) {
        if (executor == null) {
            throw new NullPointerException("executor shouldn't be null");
        } else if (playerCallback != null) {
            synchronized (this.mLock) {
                for (Pair next : this.mCallbacks) {
                    if (next.first == playerCallback && next.second != null) {
                        Log.w(TAG, "callback is already added. Ignoring.");
                        return;
                    }
                }
                this.mCallbacks.add(new Pair(playerCallback, executor));
            }
        } else {
            throw new NullPointerException("callback shouldn't be null");
        }
    }

    public final void unregisterPlayerCallback(PlayerCallback playerCallback) {
        if (playerCallback != null) {
            synchronized (this.mLock) {
                for (int size = this.mCallbacks.size() - 1; size >= 0; size--) {
                    if (this.mCallbacks.get(size).first == playerCallback) {
                        this.mCallbacks.remove(size);
                    }
                }
            }
            return;
        }
        throw new NullPointerException("callback shouldn't be null");
    }

    /* access modifiers changed from: protected */
    public final List<Pair<PlayerCallback, Executor>> getCallbacks() {
        ArrayList arrayList = new ArrayList();
        synchronized (this.mLock) {
            arrayList.addAll(this.mCallbacks);
        }
        return arrayList;
    }

    public List<TrackInfo> getTrackInfoInternal() {
        throw new UnsupportedOperationException("getTrackInfoInternal is for internal use only");
    }

    public ListenableFuture<PlayerResult> selectTrackInternal(TrackInfo trackInfo) {
        throw new UnsupportedOperationException("selectTrackInternal is for internal use only");
    }

    public ListenableFuture<PlayerResult> deselectTrackInternal(TrackInfo trackInfo) {
        throw new UnsupportedOperationException("deselectTrackInternal is for internal use only");
    }

    public TrackInfo getSelectedTrackInternal(int i) {
        throw new UnsupportedOperationException("getSelectedTrackInternal is for internal use only.");
    }

    public static final class TrackInfo extends CustomVersionedParcelable {
        public static final int MEDIA_TRACK_TYPE_AUDIO = 2;
        public static final int MEDIA_TRACK_TYPE_METADATA = 5;
        public static final int MEDIA_TRACK_TYPE_SUBTITLE = 4;
        public static final int MEDIA_TRACK_TYPE_UNKNOWN = 0;
        public static final int MEDIA_TRACK_TYPE_VIDEO = 1;
        MediaFormat mFormat;
        int mId;
        MediaItem mMediaItem;
        Bundle mParcelledFormat;
        int mTrackType;
        MediaItem mUpCastMediaItem;

        @Retention(RetentionPolicy.SOURCE)
        public @interface MediaTrackType {
        }

        TrackInfo() {
        }

        public TrackInfo(int i, MediaItem mediaItem, int i2, MediaFormat mediaFormat) {
            this.mId = i;
            this.mMediaItem = mediaItem;
            this.mTrackType = i2;
            this.mFormat = mediaFormat;
        }

        public int getTrackType() {
            return this.mTrackType;
        }

        public Locale getLanguage() {
            MediaFormat mediaFormat = this.mFormat;
            String string = mediaFormat != null ? mediaFormat.getString("language") : null;
            if (string == null) {
                string = "und";
            }
            return new Locale(string);
        }

        public MediaFormat getFormat() {
            if (this.mTrackType == 4) {
                return this.mFormat;
            }
            return null;
        }

        public int getId() {
            return this.mId;
        }

        public MediaItem getMediaItem() {
            return this.mMediaItem;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder(128);
            sb.append(getClass().getName());
            sb.append(", id: ");
            sb.append(this.mId);
            sb.append(", MediaItem: " + this.mMediaItem);
            sb.append(", TrackType: ");
            int i = this.mTrackType;
            if (i == 1) {
                sb.append("VIDEO");
            } else if (i == 2) {
                sb.append("AUDIO");
            } else if (i != 4) {
                sb.append("UNKNOWN");
            } else {
                sb.append("SUBTITLE");
            }
            sb.append(", Format: " + this.mFormat);
            return sb.toString();
        }

        public int hashCode() {
            int i;
            int i2 = this.mId + 31;
            MediaItem mediaItem = this.mMediaItem;
            if (mediaItem != null) {
                i = mediaItem.getMediaId() != null ? this.mMediaItem.getMediaId().hashCode() : this.mMediaItem.hashCode();
            } else {
                i = 0;
            }
            return (i2 * 31) + i;
        }

        /* JADX WARNING: Code restructure failed: missing block: B:38:0x0078, code lost:
            if (intEquals("is-default", r5.mFormat, r6.mFormat) == false) goto L_0x00a7;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean equals(java.lang.Object r6) {
            /*
                r5 = this;
                r0 = 1
                if (r5 != r6) goto L_0x0004
                return r0
            L_0x0004:
                r1 = 0
                if (r6 != 0) goto L_0x0008
                return r1
            L_0x0008:
                java.lang.Class r2 = r5.getClass()
                java.lang.Class r3 = r6.getClass()
                if (r2 == r3) goto L_0x0013
                return r1
            L_0x0013:
                androidx.media2.common.SessionPlayer$TrackInfo r6 = (androidx.media2.common.SessionPlayer.TrackInfo) r6
                int r2 = r5.mId
                int r3 = r6.mId
                if (r2 == r3) goto L_0x001c
                return r1
            L_0x001c:
                int r2 = r5.mTrackType
                int r3 = r6.mTrackType
                if (r2 == r3) goto L_0x0023
                return r1
            L_0x0023:
                android.media.MediaFormat r2 = r5.mFormat
                if (r2 != 0) goto L_0x002c
                android.media.MediaFormat r2 = r6.mFormat
                if (r2 != 0) goto L_0x002c
                goto L_0x007b
            L_0x002c:
                android.media.MediaFormat r2 = r5.mFormat
                if (r2 != 0) goto L_0x0035
                android.media.MediaFormat r2 = r6.mFormat
                if (r2 == 0) goto L_0x0035
                return r1
            L_0x0035:
                android.media.MediaFormat r2 = r5.mFormat
                if (r2 == 0) goto L_0x003e
                android.media.MediaFormat r2 = r6.mFormat
                if (r2 != 0) goto L_0x003e
                return r1
            L_0x003e:
                android.media.MediaFormat r2 = r5.mFormat
                android.media.MediaFormat r3 = r6.mFormat
                java.lang.String r4 = "language"
                boolean r2 = r5.stringEquals(r4, r2, r3)
                if (r2 == 0) goto L_0x00a7
                android.media.MediaFormat r2 = r5.mFormat
                android.media.MediaFormat r3 = r6.mFormat
                java.lang.String r4 = "mime"
                boolean r2 = r5.stringEquals(r4, r2, r3)
                if (r2 == 0) goto L_0x00a7
                android.media.MediaFormat r2 = r5.mFormat
                android.media.MediaFormat r3 = r6.mFormat
                java.lang.String r4 = "is-forced-subtitle"
                boolean r2 = r5.intEquals(r4, r2, r3)
                if (r2 == 0) goto L_0x00a7
                android.media.MediaFormat r2 = r5.mFormat
                android.media.MediaFormat r3 = r6.mFormat
                java.lang.String r4 = "is-autoselect"
                boolean r2 = r5.intEquals(r4, r2, r3)
                if (r2 == 0) goto L_0x00a7
                android.media.MediaFormat r2 = r5.mFormat
                android.media.MediaFormat r3 = r6.mFormat
                java.lang.String r4 = "is-default"
                boolean r2 = r5.intEquals(r4, r2, r3)
                if (r2 != 0) goto L_0x007b
                goto L_0x00a7
            L_0x007b:
                androidx.media2.common.MediaItem r2 = r5.mMediaItem
                if (r2 != 0) goto L_0x0084
                androidx.media2.common.MediaItem r2 = r6.mMediaItem
                if (r2 != 0) goto L_0x0084
                return r0
            L_0x0084:
                androidx.media2.common.MediaItem r0 = r5.mMediaItem
                if (r0 == 0) goto L_0x00a7
                androidx.media2.common.MediaItem r2 = r6.mMediaItem
                if (r2 != 0) goto L_0x008d
                goto L_0x00a7
            L_0x008d:
                java.lang.String r0 = r0.getMediaId()
                if (r0 == 0) goto L_0x009e
                androidx.media2.common.MediaItem r6 = r6.mMediaItem
                java.lang.String r6 = r6.getMediaId()
                boolean r6 = r0.equals(r6)
                return r6
            L_0x009e:
                androidx.media2.common.MediaItem r0 = r5.mMediaItem
                androidx.media2.common.MediaItem r6 = r6.mMediaItem
                boolean r6 = r0.equals(r6)
                return r6
            L_0x00a7:
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.media2.common.SessionPlayer.TrackInfo.equals(java.lang.Object):boolean");
        }

        public void onPreParceling(boolean z) {
            if (this.mFormat != null) {
                this.mParcelledFormat = new Bundle();
                parcelStringValue("language");
                parcelStringValue("mime");
                parcelIntValue("is-forced-subtitle");
                parcelIntValue("is-autoselect");
                parcelIntValue("is-default");
            }
            MediaItem mediaItem = this.mMediaItem;
            if (mediaItem != null && this.mUpCastMediaItem == null) {
                this.mUpCastMediaItem = new MediaItem(mediaItem);
            }
        }

        public void onPostParceling() {
            if (this.mParcelledFormat != null) {
                this.mFormat = new MediaFormat();
                unparcelStringValue("language");
                unparcelStringValue("mime");
                unparcelIntValue("is-forced-subtitle");
                unparcelIntValue("is-autoselect");
                unparcelIntValue("is-default");
            }
            if (this.mMediaItem == null) {
                this.mMediaItem = this.mUpCastMediaItem;
            }
        }

        private boolean stringEquals(String str, MediaFormat mediaFormat, MediaFormat mediaFormat2) {
            return TextUtils.equals(mediaFormat.getString(str), mediaFormat2.getString(str));
        }

        private boolean intEquals(String str, MediaFormat mediaFormat, MediaFormat mediaFormat2) {
            boolean containsKey = mediaFormat.containsKey(str);
            boolean containsKey2 = mediaFormat2.containsKey(str);
            if (!containsKey || !containsKey2) {
                if (containsKey || containsKey2) {
                    return false;
                }
                return true;
            } else if (mediaFormat.getInteger(str) == mediaFormat2.getInteger(str)) {
                return true;
            } else {
                return false;
            }
        }

        private void parcelIntValue(String str) {
            if (this.mFormat.containsKey(str)) {
                this.mParcelledFormat.putInt(str, this.mFormat.getInteger(str));
            }
        }

        private void parcelStringValue(String str) {
            if (this.mFormat.containsKey(str)) {
                this.mParcelledFormat.putString(str, this.mFormat.getString(str));
            }
        }

        private void unparcelIntValue(String str) {
            if (this.mParcelledFormat.containsKey(str)) {
                this.mFormat.setInteger(str, this.mParcelledFormat.getInt(str));
            }
        }

        private void unparcelStringValue(String str) {
            if (this.mParcelledFormat.containsKey(str)) {
                this.mFormat.setString(str, this.mParcelledFormat.getString(str));
            }
        }
    }

    public static class PlayerResult implements BaseResult {
        private final long mCompletionTime;
        private final MediaItem mItem;
        private final int mResultCode;

        @Retention(RetentionPolicy.SOURCE)
        public @interface ResultCode {
        }

        public PlayerResult(int i, MediaItem mediaItem) {
            this(i, mediaItem, SystemClock.elapsedRealtime());
        }

        private PlayerResult(int i, MediaItem mediaItem, long j) {
            this.mResultCode = i;
            this.mItem = mediaItem;
            this.mCompletionTime = j;
        }

        public static ListenableFuture<PlayerResult> createFuture(int i) {
            ResolvableFuture create = ResolvableFuture.create();
            create.set(new PlayerResult(i, (MediaItem) null));
            return create;
        }

        public int getResultCode() {
            return this.mResultCode;
        }

        public long getCompletionTime() {
            return this.mCompletionTime;
        }

        public MediaItem getMediaItem() {
            return this.mItem;
        }
    }
}

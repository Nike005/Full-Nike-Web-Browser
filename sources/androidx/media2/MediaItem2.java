package androidx.media2;

import android.os.Bundle;
import android.os.ParcelUuid;
import android.text.TextUtils;
import androidx.versionedparcelable.VersionedParcelable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.UUID;

public class MediaItem2 implements VersionedParcelable {
    public static final int FLAG_BROWSABLE = 1;
    public static final int FLAG_PLAYABLE = 2;
    private static final String KEY_FLAGS = "android.media.mediaitem2.flags";
    private static final String KEY_ID = "android.media.mediaitem2.id";
    private static final String KEY_METADATA = "android.media.mediaitem2.metadata";
    private static final String KEY_UUID = "android.media.mediaitem2.uuid";
    private DataSourceDesc2 mDataSourceDesc;
    int mFlags;
    String mId;
    MediaMetadata2 mMetadata;
    ParcelUuid mParcelUuid;

    @Retention(RetentionPolicy.SOURCE)
    public @interface Flags {
    }

    MediaItem2() {
    }

    MediaItem2(String str, DataSourceDesc2 dataSourceDesc2, MediaMetadata2 mediaMetadata2, int i) {
        this(str, dataSourceDesc2, mediaMetadata2, i, (ParcelUuid) null);
    }

    MediaItem2(String str, DataSourceDesc2 dataSourceDesc2, MediaMetadata2 mediaMetadata2, int i, ParcelUuid parcelUuid) {
        if (mediaMetadata2 == null || TextUtils.equals(str, mediaMetadata2.getMediaId())) {
            this.mId = str;
            this.mDataSourceDesc = dataSourceDesc2;
            this.mMetadata = mediaMetadata2;
            this.mFlags = i;
            this.mParcelUuid = parcelUuid == null ? new ParcelUuid(UUID.randomUUID()) : parcelUuid;
            return;
        }
        throw new IllegalArgumentException("metadata's id should be matched with the mediaid");
    }

    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putString(KEY_ID, this.mId);
        bundle.putInt(KEY_FLAGS, this.mFlags);
        MediaMetadata2 mediaMetadata2 = this.mMetadata;
        if (mediaMetadata2 != null) {
            bundle.putBundle(KEY_METADATA, mediaMetadata2.toBundle());
        }
        bundle.putParcelable(KEY_UUID, this.mParcelUuid);
        return bundle;
    }

    public static MediaItem2 fromBundle(Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        return fromBundle(bundle, (ParcelUuid) bundle.getParcelable(KEY_UUID));
    }

    static MediaItem2 fromBundle(Bundle bundle, ParcelUuid parcelUuid) {
        MediaMetadata2 mediaMetadata2 = null;
        if (bundle == null) {
            return null;
        }
        String string = bundle.getString(KEY_ID);
        Bundle bundle2 = bundle.getBundle(KEY_METADATA);
        if (bundle2 != null) {
            mediaMetadata2 = MediaMetadata2.fromBundle(bundle2);
        }
        return new MediaItem2(string, (DataSourceDesc2) null, mediaMetadata2, bundle.getInt(KEY_FLAGS), parcelUuid);
    }

    public String toString() {
        return "MediaItem2{" + "mId=" + this.mId + ", mFlags=" + this.mFlags + ", mMetadata=" + this.mMetadata + '}';
    }

    public int getFlags() {
        return this.mFlags;
    }

    public boolean isBrowsable() {
        return (this.mFlags & 1) != 0;
    }

    public boolean isPlayable() {
        return (this.mFlags & 2) != 0;
    }

    public void setMetadata(MediaMetadata2 mediaMetadata2) {
        if (mediaMetadata2 == null || TextUtils.equals(this.mId, mediaMetadata2.getMediaId())) {
            this.mMetadata = mediaMetadata2;
            return;
        }
        throw new IllegalArgumentException("metadata's id should be matched with the mediaId");
    }

    public MediaMetadata2 getMetadata() {
        return this.mMetadata;
    }

    public String getMediaId() {
        return this.mId;
    }

    public DataSourceDesc2 getDataSourceDesc() {
        return this.mDataSourceDesc;
    }

    public int hashCode() {
        return this.mParcelUuid.hashCode();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof MediaItem2)) {
            return false;
        }
        return this.mParcelUuid.equals(((MediaItem2) obj).mParcelUuid);
    }

    /* access modifiers changed from: package-private */
    public UUID getUuid() {
        return this.mParcelUuid.getUuid();
    }

    public static final class Builder {
        private DataSourceDesc2 mDataSourceDesc;
        private int mFlags;
        private String mMediaId;
        private MediaMetadata2 mMetadata;
        private UUID mUuid;

        public Builder(int i) {
            this.mFlags = i;
        }

        public Builder setMediaId(String str) {
            this.mMediaId = str;
            return this;
        }

        public Builder setMetadata(MediaMetadata2 mediaMetadata2) {
            this.mMetadata = mediaMetadata2;
            return this;
        }

        public Builder setDataSourceDesc(DataSourceDesc2 dataSourceDesc2) {
            this.mDataSourceDesc = dataSourceDesc2;
            return this;
        }

        /* access modifiers changed from: package-private */
        public Builder setUuid(UUID uuid) {
            this.mUuid = uuid;
            return this;
        }

        public MediaItem2 build() {
            MediaMetadata2 mediaMetadata2 = this.mMetadata;
            ParcelUuid parcelUuid = null;
            String string = mediaMetadata2 != null ? mediaMetadata2.getString("android.media.metadata.MEDIA_ID") : null;
            if (string == null) {
                string = this.mMediaId;
            }
            String str = string;
            DataSourceDesc2 dataSourceDesc2 = this.mDataSourceDesc;
            MediaMetadata2 mediaMetadata22 = this.mMetadata;
            int i = this.mFlags;
            if (this.mUuid != null) {
                parcelUuid = new ParcelUuid(this.mUuid);
            }
            return new MediaItem2(str, dataSourceDesc2, mediaMetadata22, i, parcelUuid);
        }
    }
}

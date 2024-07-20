package androidx.media2;

import android.os.ParcelUuid;
import androidx.versionedparcelable.VersionedParcel;

public final class MediaItem2Parcelizer {
    public static MediaItem2 read(VersionedParcel versionedParcel) {
        MediaItem2 mediaItem2 = new MediaItem2();
        mediaItem2.mId = versionedParcel.readString(mediaItem2.mId, 1);
        mediaItem2.mFlags = versionedParcel.readInt(mediaItem2.mFlags, 2);
        mediaItem2.mParcelUuid = (ParcelUuid) versionedParcel.readParcelable(mediaItem2.mParcelUuid, 3);
        mediaItem2.mMetadata = (MediaMetadata2) versionedParcel.readVersionedParcelable(mediaItem2.mMetadata, 4);
        return mediaItem2;
    }

    public static void write(MediaItem2 mediaItem2, VersionedParcel versionedParcel) {
        versionedParcel.setSerializationFlags(false, false);
        versionedParcel.writeString(mediaItem2.mId, 1);
        versionedParcel.writeInt(mediaItem2.mFlags, 2);
        versionedParcel.writeParcelable(mediaItem2.mParcelUuid, 3);
        versionedParcel.writeVersionedParcelable(mediaItem2.mMetadata, 4);
    }
}

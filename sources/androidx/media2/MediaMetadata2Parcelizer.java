package androidx.media2;

import androidx.versionedparcelable.VersionedParcel;

public final class MediaMetadata2Parcelizer {
    public static MediaMetadata2 read(VersionedParcel versionedParcel) {
        MediaMetadata2 mediaMetadata2 = new MediaMetadata2();
        mediaMetadata2.mBundle = versionedParcel.readBundle(mediaMetadata2.mBundle, 1);
        return mediaMetadata2;
    }

    public static void write(MediaMetadata2 mediaMetadata2, VersionedParcel versionedParcel) {
        versionedParcel.setSerializationFlags(false, false);
        versionedParcel.writeBundle(mediaMetadata2.mBundle, 1);
    }
}

package androidx.media2;

import androidx.versionedparcelable.VersionedParcel;

public final class Rating2Parcelizer {
    public static Rating2 read(VersionedParcel versionedParcel) {
        Rating2 rating2 = new Rating2();
        rating2.mRatingStyle = versionedParcel.readInt(rating2.mRatingStyle, 1);
        rating2.mRatingValue = versionedParcel.readFloat(rating2.mRatingValue, 2);
        return rating2;
    }

    public static void write(Rating2 rating2, VersionedParcel versionedParcel) {
        versionedParcel.setSerializationFlags(false, false);
        versionedParcel.writeInt(rating2.mRatingStyle, 1);
        versionedParcel.writeFloat(rating2.mRatingValue, 2);
    }
}

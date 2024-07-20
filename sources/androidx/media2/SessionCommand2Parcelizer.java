package androidx.media2;

import androidx.versionedparcelable.VersionedParcel;

public final class SessionCommand2Parcelizer {
    public static SessionCommand2 read(VersionedParcel versionedParcel) {
        SessionCommand2 sessionCommand2 = new SessionCommand2();
        sessionCommand2.mCommandCode = versionedParcel.readInt(sessionCommand2.mCommandCode, 1);
        sessionCommand2.mCustomCommand = versionedParcel.readString(sessionCommand2.mCustomCommand, 2);
        sessionCommand2.mExtras = versionedParcel.readBundle(sessionCommand2.mExtras, 3);
        return sessionCommand2;
    }

    public static void write(SessionCommand2 sessionCommand2, VersionedParcel versionedParcel) {
        versionedParcel.setSerializationFlags(false, false);
        versionedParcel.writeInt(sessionCommand2.mCommandCode, 1);
        versionedParcel.writeString(sessionCommand2.mCustomCommand, 2);
        versionedParcel.writeBundle(sessionCommand2.mExtras, 3);
    }
}

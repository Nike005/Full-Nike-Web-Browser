package androidx.media2;

import androidx.versionedparcelable.VersionedParcel;

public final class SessionCommandGroup2Parcelizer {
    public static SessionCommandGroup2 read(VersionedParcel versionedParcel) {
        SessionCommandGroup2 sessionCommandGroup2 = new SessionCommandGroup2();
        sessionCommandGroup2.mCommands = versionedParcel.readList(sessionCommandGroup2.mCommands, 1);
        return sessionCommandGroup2;
    }

    public static void write(SessionCommandGroup2 sessionCommandGroup2, VersionedParcel versionedParcel) {
        versionedParcel.setSerializationFlags(false, false);
        versionedParcel.writeList(sessionCommandGroup2.mCommands, 1);
    }
}

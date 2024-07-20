package androidx.media2;

import androidx.media2.MediaSession2;
import androidx.versionedparcelable.VersionedParcel;

public final class CommandButtonParcelizer {
    public static MediaSession2.CommandButton read(VersionedParcel versionedParcel) {
        MediaSession2.CommandButton commandButton = new MediaSession2.CommandButton();
        commandButton.mCommand = (SessionCommand2) versionedParcel.readVersionedParcelable(commandButton.mCommand, 1);
        commandButton.mIconResId = versionedParcel.readInt(commandButton.mIconResId, 2);
        commandButton.mDisplayName = versionedParcel.readString(commandButton.mDisplayName, 3);
        commandButton.mExtras = versionedParcel.readBundle(commandButton.mExtras, 4);
        commandButton.mEnabled = versionedParcel.readBoolean(commandButton.mEnabled, 5);
        return commandButton;
    }

    public static void write(MediaSession2.CommandButton commandButton, VersionedParcel versionedParcel) {
        versionedParcel.setSerializationFlags(false, false);
        versionedParcel.writeVersionedParcelable(commandButton.mCommand, 1);
        versionedParcel.writeInt(commandButton.mIconResId, 2);
        versionedParcel.writeString(commandButton.mDisplayName, 3);
        versionedParcel.writeBundle(commandButton.mExtras, 4);
        versionedParcel.writeBoolean(commandButton.mEnabled, 5);
    }
}

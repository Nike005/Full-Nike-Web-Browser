package androidx.media2;

import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import androidx.versionedparcelable.VersionedParcelable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class SessionCommandGroup2 implements VersionedParcelable {
    private static final String KEY_COMMANDS = "android.media.session2.commandgroup.commands";
    private static final String TAG = "SessionCommandGroup2";
    List<SessionCommand2> mCommands;

    public SessionCommandGroup2() {
        this.mCommands = new ArrayList();
    }

    public SessionCommandGroup2(Collection<SessionCommand2> collection) {
        ArrayList arrayList = new ArrayList();
        this.mCommands = arrayList;
        if (collection != null) {
            arrayList.addAll(collection);
        }
    }

    public void addCommand(SessionCommand2 sessionCommand2) {
        if (sessionCommand2 == null) {
            throw new IllegalArgumentException("command shouldn't be null");
        } else if (!hasCommand(sessionCommand2)) {
            this.mCommands.add(sessionCommand2);
        }
    }

    public void addCommand(int i) {
        if (i == 0) {
            throw new IllegalArgumentException("Use addCommand(SessionCommand2) for COMMAND_CODE_CUSTOM.");
        } else if (!hasCommand(i)) {
            this.mCommands.add(new SessionCommand2(i));
        }
    }

    public boolean hasCommand(SessionCommand2 sessionCommand2) {
        if (sessionCommand2 != null) {
            return this.mCommands.contains(sessionCommand2);
        }
        throw new IllegalArgumentException("command shouldn't be null");
    }

    public boolean hasCommand(int i) {
        if (i != 0) {
            for (SessionCommand2 commandCode : this.mCommands) {
                if (commandCode.getCommandCode() == i) {
                    return true;
                }
            }
            return false;
        }
        throw new IllegalArgumentException("Use hasCommand(Command) for custom command");
    }

    public Set<SessionCommand2> getCommands() {
        return new HashSet(this.mCommands);
    }

    public Bundle toBundle() {
        ArrayList arrayList = new ArrayList();
        for (SessionCommand2 bundle : this.mCommands) {
            arrayList.add(bundle.toBundle());
        }
        Bundle bundle2 = new Bundle();
        bundle2.putParcelableArrayList(KEY_COMMANDS, arrayList);
        return bundle2;
    }

    public static SessionCommandGroup2 fromBundle(Bundle bundle) {
        ArrayList parcelableArrayList;
        SessionCommand2 fromBundle;
        if (bundle == null || (parcelableArrayList = bundle.getParcelableArrayList(KEY_COMMANDS)) == null) {
            return null;
        }
        SessionCommandGroup2 sessionCommandGroup2 = new SessionCommandGroup2();
        for (int i = 0; i < parcelableArrayList.size(); i++) {
            Parcelable parcelable = (Parcelable) parcelableArrayList.get(i);
            if ((parcelable instanceof Bundle) && (fromBundle = SessionCommand2.fromBundle((Bundle) parcelable)) != null) {
                sessionCommandGroup2.addCommand(fromBundle);
            }
        }
        return sessionCommandGroup2;
    }

    public static final class Builder {
        private Set<SessionCommand2> mCommands;

        public Builder() {
            this.mCommands = new HashSet();
        }

        public Builder(SessionCommandGroup2 sessionCommandGroup2) {
            this.mCommands = sessionCommandGroup2.getCommands();
        }

        public Builder addCommand(SessionCommand2 sessionCommand2) {
            if (sessionCommand2 != null) {
                this.mCommands.add(sessionCommand2);
                return this;
            }
            throw new IllegalArgumentException("command shouldn't be null");
        }

        public Builder addCommand(int i) {
            if (i != 0) {
                this.mCommands.add(new SessionCommand2(i));
                return this;
            }
            throw new IllegalArgumentException("Use addCommand(SessionCommand2) for COMMAND_CODE_CUSTOM.");
        }

        public Builder addAllPredefinedCommands() {
            addAllPlaybackCommands();
            addAllPlaylistCommands();
            addAllVolumeCommands();
            addAllSessionCommands();
            addAllLibraryCommands();
            return this;
        }

        public Builder removeCommand(SessionCommand2 sessionCommand2) {
            if (sessionCommand2 != null) {
                this.mCommands.remove(sessionCommand2);
                return this;
            }
            throw new IllegalArgumentException("command shouldn't be null");
        }

        public Builder removeCommand(int i) {
            if (i != 0) {
                this.mCommands.remove(new SessionCommand2(i));
                return this;
            }
            throw new IllegalArgumentException("commandCode shouldn't be COMMAND_CODE_CUSTOM");
        }

        /* access modifiers changed from: package-private */
        public Builder addAllPlaybackCommands() {
            addCommand(2);
            addCommand(1);
            addCommand(6);
            addCommand(3);
            addCommand(9);
            addCommand(39);
            return this;
        }

        /* access modifiers changed from: package-private */
        public Builder addAllPlaylistCommands() {
            addCommand(15);
            addCommand(20);
            addCommand(18);
            addCommand(20);
            addCommand(16);
            addCommand(17);
            addCommand(19);
            addCommand(21);
            addCommand(14);
            addCommand(13);
            addCommand(4);
            addCommand(12);
            addCommand(5);
            return this;
        }

        /* access modifiers changed from: package-private */
        public Builder addAllVolumeCommands() {
            addCommand(11);
            addCommand(10);
            return this;
        }

        /* access modifiers changed from: package-private */
        public Builder addAllSessionCommands() {
            addCommand(7);
            addCommand(22);
            addCommand(24);
            addCommand(23);
            addCommand(25);
            addCommand(27);
            addCommand(26);
            addCommand(8);
            addCommand(38);
            addCommand(28);
            addCommand(36);
            addCommand(37);
            return this;
        }

        /* access modifiers changed from: package-private */
        public Builder addAllLibraryCommands() {
            addCommand(29);
            addCommand(30);
            addCommand(31);
            addCommand(32);
            addCommand(33);
            addCommand(34);
            addCommand(35);
            return this;
        }

        private void addCommandsWithPrefix(String str) {
            Field[] fields = SessionCommand2.class.getFields();
            if (fields != null) {
                for (int i = 0; i < fields.length; i++) {
                    if (fields[i].getName().startsWith(str) && !fields[i].getName().equals("COMMAND_CODE_CUSTOM")) {
                        try {
                            this.mCommands.add(new SessionCommand2(fields[i].getInt((Object) null)));
                        } catch (IllegalAccessException unused) {
                            Log.w(SessionCommandGroup2.TAG, "Unexpected " + fields[i] + " in MediaSession2");
                        }
                    }
                }
            }
        }

        public SessionCommandGroup2 build() {
            return new SessionCommandGroup2(this.mCommands);
        }
    }
}

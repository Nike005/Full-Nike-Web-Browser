package androidx.media2.session;

import android.os.Bundle;
import android.text.TextUtils;
import androidx.collection.ArrayMap;
import androidx.core.util.ObjectsCompat;
import androidx.versionedparcelable.VersionedParcelable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public final class SessionCommand implements VersionedParcelable {
    public static final int COMMAND_CODE_CUSTOM = 0;
    public static final int COMMAND_CODE_LIBRARY_GET_CHILDREN = 50003;
    public static final int COMMAND_CODE_LIBRARY_GET_ITEM = 50004;
    public static final int COMMAND_CODE_LIBRARY_GET_LIBRARY_ROOT = 50000;
    public static final int COMMAND_CODE_LIBRARY_GET_SEARCH_RESULT = 50006;
    public static final int COMMAND_CODE_LIBRARY_SEARCH = 50005;
    public static final int COMMAND_CODE_LIBRARY_SUBSCRIBE = 50001;
    public static final int COMMAND_CODE_LIBRARY_UNSUBSCRIBE = 50002;
    public static final int COMMAND_CODE_PLAYER_ADD_PLAYLIST_ITEM = 10013;
    public static final int COMMAND_CODE_PLAYER_DESELECT_TRACK = 11002;
    public static final int COMMAND_CODE_PLAYER_GET_CURRENT_MEDIA_ITEM = 10016;
    public static final int COMMAND_CODE_PLAYER_GET_PLAYLIST = 10005;
    public static final int COMMAND_CODE_PLAYER_GET_PLAYLIST_METADATA = 10012;
    public static final int COMMAND_CODE_PLAYER_PAUSE = 10001;
    public static final int COMMAND_CODE_PLAYER_PLAY = 10000;
    public static final int COMMAND_CODE_PLAYER_PREPARE = 10002;
    public static final int COMMAND_CODE_PLAYER_REMOVE_PLAYLIST_ITEM = 10014;
    public static final int COMMAND_CODE_PLAYER_REPLACE_PLAYLIST_ITEM = 10015;
    public static final int COMMAND_CODE_PLAYER_SEEK_TO = 10003;
    public static final int COMMAND_CODE_PLAYER_SELECT_TRACK = 11001;
    public static final int COMMAND_CODE_PLAYER_SET_MEDIA_ITEM = 10018;
    public static final int COMMAND_CODE_PLAYER_SET_PLAYLIST = 10006;
    public static final int COMMAND_CODE_PLAYER_SET_REPEAT_MODE = 10011;
    public static final int COMMAND_CODE_PLAYER_SET_SHUFFLE_MODE = 10010;
    public static final int COMMAND_CODE_PLAYER_SET_SPEED = 10004;
    public static final int COMMAND_CODE_PLAYER_SET_SURFACE = 11000;
    public static final int COMMAND_CODE_PLAYER_SKIP_TO_NEXT_PLAYLIST_ITEM = 10009;
    public static final int COMMAND_CODE_PLAYER_SKIP_TO_PLAYLIST_ITEM = 10007;
    public static final int COMMAND_CODE_PLAYER_SKIP_TO_PREVIOUS_PLAYLIST_ITEM = 10008;
    public static final int COMMAND_CODE_PLAYER_UPDATE_LIST_METADATA = 10017;
    public static final int COMMAND_CODE_SESSION_FAST_FORWARD = 40000;
    public static final int COMMAND_CODE_SESSION_PLAY_FROM_MEDIA_ID = 40004;
    public static final int COMMAND_CODE_SESSION_PLAY_FROM_SEARCH = 40005;
    public static final int COMMAND_CODE_SESSION_PLAY_FROM_URI = 40006;
    public static final int COMMAND_CODE_SESSION_PREPARE_FROM_MEDIA_ID = 40007;
    public static final int COMMAND_CODE_SESSION_PREPARE_FROM_SEARCH = 40008;
    public static final int COMMAND_CODE_SESSION_PREPARE_FROM_URI = 40009;
    public static final int COMMAND_CODE_SESSION_REWIND = 40001;
    public static final int COMMAND_CODE_SESSION_SET_RATING = 40010;
    public static final int COMMAND_CODE_SESSION_SKIP_BACKWARD = 40003;
    public static final int COMMAND_CODE_SESSION_SKIP_FORWARD = 40002;
    public static final int COMMAND_CODE_VOLUME_ADJUST_VOLUME = 30001;
    public static final int COMMAND_CODE_VOLUME_SET_VOLUME = 30000;
    public static final int COMMAND_VERSION_1 = 1;
    public static final int COMMAND_VERSION_CURRENT = 1;
    static final ArrayMap<Integer, Range> VERSION_LIBRARY_COMMANDS_MAP;
    static final ArrayMap<Integer, Range> VERSION_PLAYER_BASIC_COMMANDS_MAP = new ArrayMap<>();
    static final ArrayMap<Integer, Range> VERSION_PLAYER_HIDDEN_COMMANDS_MAP = new ArrayMap<>();
    static final ArrayMap<Integer, Range> VERSION_PLAYER_PLAYLIST_COMMANDS_MAP = new ArrayMap<>();
    static final ArrayMap<Integer, Range> VERSION_SESSION_COMMANDS_MAP;
    static final ArrayMap<Integer, Range> VERSION_VOLUME_COMMANDS_MAP;
    int mCommandCode;
    String mCustomAction;
    Bundle mCustomExtras;

    @Retention(RetentionPolicy.SOURCE)
    public @interface CommandCode {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface CommandVersion {
    }

    static {
        VERSION_PLAYER_BASIC_COMMANDS_MAP.put(1, new Range(10000, COMMAND_CODE_PLAYER_SET_SPEED));
        VERSION_PLAYER_PLAYLIST_COMMANDS_MAP.put(1, new Range(COMMAND_CODE_PLAYER_GET_PLAYLIST, COMMAND_CODE_PLAYER_SET_MEDIA_ITEM));
        VERSION_PLAYER_HIDDEN_COMMANDS_MAP.put(1, new Range(COMMAND_CODE_PLAYER_SET_SURFACE, COMMAND_CODE_PLAYER_DESELECT_TRACK));
        ArrayMap<Integer, Range> arrayMap = new ArrayMap<>();
        VERSION_VOLUME_COMMANDS_MAP = arrayMap;
        arrayMap.put(1, new Range(30000, COMMAND_CODE_VOLUME_ADJUST_VOLUME));
        ArrayMap<Integer, Range> arrayMap2 = new ArrayMap<>();
        VERSION_SESSION_COMMANDS_MAP = arrayMap2;
        arrayMap2.put(1, new Range(COMMAND_CODE_SESSION_FAST_FORWARD, COMMAND_CODE_SESSION_SET_RATING));
        ArrayMap<Integer, Range> arrayMap3 = new ArrayMap<>();
        VERSION_LIBRARY_COMMANDS_MAP = arrayMap3;
        arrayMap3.put(1, new Range(50000, COMMAND_CODE_LIBRARY_GET_SEARCH_RESULT));
    }

    SessionCommand() {
    }

    public SessionCommand(int i) {
        if (i != 0) {
            this.mCommandCode = i;
            this.mCustomAction = null;
            this.mCustomExtras = null;
            return;
        }
        throw new IllegalArgumentException("commandCode shouldn't be COMMAND_CODE_CUSTOM");
    }

    public SessionCommand(String str, Bundle bundle) {
        if (str != null) {
            this.mCommandCode = 0;
            this.mCustomAction = str;
            this.mCustomExtras = bundle;
            return;
        }
        throw new NullPointerException("action shouldn't be null");
    }

    public int getCommandCode() {
        return this.mCommandCode;
    }

    public String getCustomAction() {
        return this.mCustomAction;
    }

    public Bundle getCustomExtras() {
        return this.mCustomExtras;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof SessionCommand)) {
            return false;
        }
        SessionCommand sessionCommand = (SessionCommand) obj;
        if (this.mCommandCode != sessionCommand.mCommandCode || !TextUtils.equals(this.mCustomAction, sessionCommand.mCustomAction)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return ObjectsCompat.hash(this.mCustomAction, Integer.valueOf(this.mCommandCode));
    }

    static final class Range {
        public final int lower;
        public final int upper;

        Range(int i, int i2) {
            this.lower = i;
            this.upper = i2;
        }
    }
}

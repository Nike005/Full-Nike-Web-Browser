package androidx.media2.session;

import androidx.media2.common.SessionPlayer;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.concurrent.Future;

public abstract class RemoteSessionPlayer extends SessionPlayer {
    public static final int VOLUME_CONTROL_ABSOLUTE = 2;
    public static final int VOLUME_CONTROL_FIXED = 0;
    public static final int VOLUME_CONTROL_RELATIVE = 1;

    public static class Callback extends SessionPlayer.PlayerCallback {
        public void onVolumeChanged(RemoteSessionPlayer remoteSessionPlayer, int i) {
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface VolumeControlType {
    }

    public abstract Future<SessionPlayer.PlayerResult> adjustVolume(int i);

    public abstract int getMaxVolume();

    public abstract int getVolume();

    public abstract int getVolumeControlType();

    public abstract Future<SessionPlayer.PlayerResult> setVolume(int i);
}

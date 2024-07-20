package androidx.media2;

import androidx.media2.MediaPlayerConnector;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public abstract class BaseRemoteMediaPlayerConnector extends MediaPlayerConnector {
    public static final int VOLUME_CONTROL_ABSOLUTE = 2;
    public static final int VOLUME_CONTROL_FIXED = 0;
    public static final int VOLUME_CONTROL_RELATIVE = 1;

    public static class RemotePlayerEventCallback extends MediaPlayerConnector.PlayerEventCallback {
        public void onPlayerVolumeChanged(MediaPlayerConnector mediaPlayerConnector, float f) {
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface VolumeControlType {
    }

    public abstract void adjustPlayerVolume(int i);

    public abstract int getVolumeControlType();
}

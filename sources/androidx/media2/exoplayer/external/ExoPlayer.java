package androidx.media2.exoplayer.external;

import android.os.Looper;
import androidx.media2.exoplayer.external.PlayerMessage;
import androidx.media2.exoplayer.external.source.MediaSource;

public interface ExoPlayer extends Player {

    @Deprecated
    public interface ExoPlayerComponent extends PlayerMessage.Target {
    }

    @Deprecated
    void blockingSendMessages(ExoPlayerMessage... exoPlayerMessageArr);

    PlayerMessage createMessage(PlayerMessage.Target target);

    Looper getPlaybackLooper();

    SeekParameters getSeekParameters();

    void prepare(MediaSource mediaSource);

    void prepare(MediaSource mediaSource, boolean z, boolean z2);

    void retry();

    @Deprecated
    void sendMessages(ExoPlayerMessage... exoPlayerMessageArr);

    void setForegroundMode(boolean z);

    void setSeekParameters(SeekParameters seekParameters);

    @Deprecated
    public static final class ExoPlayerMessage {
        public final Object message;
        public final int messageType;
        public final PlayerMessage.Target target;

        @Deprecated
        public ExoPlayerMessage(PlayerMessage.Target target2, int i, Object obj) {
            this.target = target2;
            this.messageType = i;
            this.message = obj;
        }
    }
}

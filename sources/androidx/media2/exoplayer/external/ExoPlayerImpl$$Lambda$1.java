package androidx.media2.exoplayer.external;

import androidx.media2.exoplayer.external.BasePlayer;
import androidx.media2.exoplayer.external.Player;

final /* synthetic */ class ExoPlayerImpl$$Lambda$1 implements BasePlayer.ListenerInvocation {
    private final int arg$1;

    ExoPlayerImpl$$Lambda$1(int i) {
        this.arg$1 = i;
    }

    public void invokeListener(Player.EventListener eventListener) {
        eventListener.onRepeatModeChanged(this.arg$1);
    }
}

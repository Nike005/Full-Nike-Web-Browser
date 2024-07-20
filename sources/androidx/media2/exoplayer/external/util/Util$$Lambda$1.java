package androidx.media2.exoplayer.external.util;

import androidx.media2.exoplayer.external.text.TextOutput;
import java.util.List;

final /* synthetic */ class Util$$Lambda$1 implements TextOutput {
    static final TextOutput $instance = new Util$$Lambda$1();

    private Util$$Lambda$1() {
    }

    public void onCues(List list) {
        Util.lambda$getRendererCapabilities$1$Util(list);
    }
}

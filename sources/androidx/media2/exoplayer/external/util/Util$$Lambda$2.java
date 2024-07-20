package androidx.media2.exoplayer.external.util;

import androidx.media2.exoplayer.external.metadata.Metadata;
import androidx.media2.exoplayer.external.metadata.MetadataOutput;

final /* synthetic */ class Util$$Lambda$2 implements MetadataOutput {
    static final MetadataOutput $instance = new Util$$Lambda$2();

    private Util$$Lambda$2() {
    }

    public void onMetadata(Metadata metadata) {
        Util.lambda$getRendererCapabilities$2$Util(metadata);
    }
}

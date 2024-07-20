package androidx.media2.exoplayer.external.trackselection;

import androidx.media2.exoplayer.external.Format;
import androidx.media2.exoplayer.external.trackselection.BufferSizeAdaptationBuilder;

final /* synthetic */ class BufferSizeAdaptationBuilder$DynamicFormatFilter$$Lambda$0 implements BufferSizeAdaptationBuilder.DynamicFormatFilter {
    static final BufferSizeAdaptationBuilder.DynamicFormatFilter $instance = new BufferSizeAdaptationBuilder$DynamicFormatFilter$$Lambda$0();

    private BufferSizeAdaptationBuilder$DynamicFormatFilter$$Lambda$0() {
    }

    public boolean isFormatAllowed(Format format, int i, boolean z) {
        return BufferSizeAdaptationBuilder$DynamicFormatFilter$$CC.m6202x98f47864(format, i, z);
    }
}

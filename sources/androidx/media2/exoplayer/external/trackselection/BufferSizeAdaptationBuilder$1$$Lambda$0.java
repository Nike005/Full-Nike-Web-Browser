package androidx.media2.exoplayer.external.trackselection;

import androidx.media2.exoplayer.external.trackselection.BufferSizeAdaptationBuilder;
import androidx.media2.exoplayer.external.trackselection.TrackSelection;
import androidx.media2.exoplayer.external.trackselection.TrackSelectionUtil;
import androidx.media2.exoplayer.external.upstream.BandwidthMeter;

final /* synthetic */ class BufferSizeAdaptationBuilder$1$$Lambda$0 implements TrackSelectionUtil.AdaptiveTrackSelectionFactory {
    private final BufferSizeAdaptationBuilder.C39441 arg$1;
    private final BandwidthMeter arg$2;

    BufferSizeAdaptationBuilder$1$$Lambda$0(BufferSizeAdaptationBuilder.C39441 r1, BandwidthMeter bandwidthMeter) {
        this.arg$1 = r1;
        this.arg$2 = bandwidthMeter;
    }

    public TrackSelection createAdaptiveTrackSelection(TrackSelection.Definition definition) {
        return this.arg$1.lambda$createTrackSelections$0$BufferSizeAdaptationBuilder$1(this.arg$2, definition);
    }
}

package androidx.media2.exoplayer.external.trackselection;

import androidx.media2.exoplayer.external.trackselection.TrackSelection;
import androidx.media2.exoplayer.external.trackselection.TrackSelectionUtil;
import androidx.media2.exoplayer.external.upstream.BandwidthMeter;

final /* synthetic */ class TrackSelection$Factory$$Lambda$0 implements TrackSelectionUtil.AdaptiveTrackSelectionFactory {
    private final TrackSelection.Factory arg$1;
    private final BandwidthMeter arg$2;

    TrackSelection$Factory$$Lambda$0(TrackSelection.Factory factory, BandwidthMeter bandwidthMeter) {
        this.arg$1 = factory;
        this.arg$2 = bandwidthMeter;
    }

    public TrackSelection createAdaptiveTrackSelection(TrackSelection.Definition definition) {
        return this.arg$1.createTrackSelection(definition.group, this.arg$2, definition.tracks);
    }
}

package androidx.media2.exoplayer.external.trackselection;

import androidx.media2.exoplayer.external.source.TrackGroup;
import androidx.media2.exoplayer.external.trackselection.TrackSelection;
import androidx.media2.exoplayer.external.upstream.BandwidthMeter;

public abstract /* synthetic */ class TrackSelection$Factory$$CC {
    @Deprecated
    public static TrackSelection createTrackSelection$$dflt$$(TrackSelection.Factory factory, TrackGroup trackGroup, BandwidthMeter bandwidthMeter, int... iArr) {
        throw new UnsupportedOperationException();
    }

    public static TrackSelection[] createTrackSelections$$dflt$$(TrackSelection.Factory factory, TrackSelection.Definition[] definitionArr, BandwidthMeter bandwidthMeter) {
        return TrackSelectionUtil.createTrackSelectionsForDefinitions(definitionArr, new TrackSelection$Factory$$Lambda$0(factory, bandwidthMeter));
    }
}

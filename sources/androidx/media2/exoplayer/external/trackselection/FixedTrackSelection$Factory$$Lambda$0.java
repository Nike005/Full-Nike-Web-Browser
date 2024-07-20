package androidx.media2.exoplayer.external.trackselection;

import androidx.media2.exoplayer.external.trackselection.FixedTrackSelection;
import androidx.media2.exoplayer.external.trackselection.TrackSelection;
import androidx.media2.exoplayer.external.trackselection.TrackSelectionUtil;

final /* synthetic */ class FixedTrackSelection$Factory$$Lambda$0 implements TrackSelectionUtil.AdaptiveTrackSelectionFactory {
    private final FixedTrackSelection.Factory arg$1;

    FixedTrackSelection$Factory$$Lambda$0(FixedTrackSelection.Factory factory) {
        this.arg$1 = factory;
    }

    public TrackSelection createAdaptiveTrackSelection(TrackSelection.Definition definition) {
        return this.arg$1.lambda$createTrackSelections$0$FixedTrackSelection$Factory(definition);
    }
}

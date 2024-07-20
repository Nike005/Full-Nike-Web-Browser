package androidx.media2.exoplayer.external.trackselection;

import androidx.media2.exoplayer.external.trackselection.RandomTrackSelection;
import androidx.media2.exoplayer.external.trackselection.TrackSelection;
import androidx.media2.exoplayer.external.trackselection.TrackSelectionUtil;

final /* synthetic */ class RandomTrackSelection$Factory$$Lambda$0 implements TrackSelectionUtil.AdaptiveTrackSelectionFactory {
    private final RandomTrackSelection.Factory arg$1;

    RandomTrackSelection$Factory$$Lambda$0(RandomTrackSelection.Factory factory) {
        this.arg$1 = factory;
    }

    public TrackSelection createAdaptiveTrackSelection(TrackSelection.Definition definition) {
        return this.arg$1.lambda$createTrackSelections$0$RandomTrackSelection$Factory(definition);
    }
}

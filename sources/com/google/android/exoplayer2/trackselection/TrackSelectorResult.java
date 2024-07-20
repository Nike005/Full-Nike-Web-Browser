package com.google.android.exoplayer2.trackselection;

import com.google.android.exoplayer2.RendererConfiguration;
import com.google.android.exoplayer2.util.Util;

public final class TrackSelectorResult {

    /* renamed from: info  reason: collision with root package name */
    public final Object f5083info;
    public final int length;
    public final RendererConfiguration[] rendererConfigurations;
    public final TrackSelectionArray selections;

    public TrackSelectorResult(RendererConfiguration[] rendererConfigurationArr, TrackSelection[] trackSelectionArr, Object obj) {
        this.rendererConfigurations = rendererConfigurationArr;
        this.selections = new TrackSelectionArray(trackSelectionArr);
        this.f5083info = obj;
        this.length = rendererConfigurationArr.length;
    }

    public boolean isRendererEnabled(int i) {
        return this.rendererConfigurations[i] != null;
    }

    public boolean isEquivalent(TrackSelectorResult trackSelectorResult) {
        if (trackSelectorResult == null || trackSelectorResult.selections.length != this.selections.length) {
            return false;
        }
        for (int i = 0; i < this.selections.length; i++) {
            if (!isEquivalent(trackSelectorResult, i)) {
                return false;
            }
        }
        return true;
    }

    public boolean isEquivalent(TrackSelectorResult trackSelectorResult, int i) {
        if (trackSelectorResult != null && Util.areEqual(this.rendererConfigurations[i], trackSelectorResult.rendererConfigurations[i]) && Util.areEqual(this.selections.get(i), trackSelectorResult.selections.get(i))) {
            return true;
        }
        return false;
    }
}

package androidx.media2.exoplayer.external.source.ads;

import androidx.media2.exoplayer.external.Timeline;
import androidx.media2.exoplayer.external.source.ForwardingTimeline;
import androidx.media2.exoplayer.external.util.Assertions;

public final class SinglePeriodAdTimeline extends ForwardingTimeline {
    private final AdPlaybackState adPlaybackState;

    public SinglePeriodAdTimeline(Timeline timeline, AdPlaybackState adPlaybackState2) {
        super(timeline);
        boolean z = false;
        Assertions.checkState(timeline.getPeriodCount() == 1);
        Assertions.checkState(timeline.getWindowCount() == 1 ? true : z);
        this.adPlaybackState = adPlaybackState2;
    }

    public Timeline.Period getPeriod(int i, Timeline.Period period, boolean z) {
        this.timeline.getPeriod(i, period, z);
        period.set(period.f4080id, period.uid, period.windowIndex, period.durationUs, period.getPositionInWindowUs(), this.adPlaybackState);
        return period;
    }

    public Timeline.Window getWindow(int i, Timeline.Window window, boolean z, long j) {
        Timeline.Window window2 = super.getWindow(i, window, z, j);
        if (window2.durationUs == -9223372036854775807L) {
            window2.durationUs = this.adPlaybackState.contentDurationUs;
        }
        return window2;
    }
}

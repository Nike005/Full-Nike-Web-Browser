package androidx.media2.exoplayer.external.trackselection;

import androidx.media2.exoplayer.external.C3875C;
import androidx.media2.exoplayer.external.Format;
import androidx.media2.exoplayer.external.source.chunk.MediaChunk;
import androidx.media2.exoplayer.external.source.chunk.MediaChunkIterator;
import java.util.List;

public final class WindowedTrackBitrateEstimator implements TrackBitrateEstimator {
    private final long maxFutureDurationUs;
    private final long maxPastDurationUs;
    private final boolean useFormatBitrateAsLowerBound;

    public WindowedTrackBitrateEstimator(long j, long j2, boolean z) {
        this.maxPastDurationUs = C3875C.msToUs(j);
        this.maxFutureDurationUs = C3875C.msToUs(j2);
        this.useFormatBitrateAsLowerBound = z;
    }

    public int[] getBitrates(Format[] formatArr, List<? extends MediaChunk> list, MediaChunkIterator[] mediaChunkIteratorArr, int[] iArr) {
        if (this.maxFutureDurationUs <= 0 && this.maxPastDurationUs <= 0) {
            return TrackSelectionUtil.getFormatBitrates(formatArr, iArr);
        }
        return TrackSelectionUtil.getBitratesUsingPastAndFutureInfo(formatArr, list, this.maxPastDurationUs, mediaChunkIteratorArr, this.maxFutureDurationUs, this.useFormatBitrateAsLowerBound, iArr);
    }
}

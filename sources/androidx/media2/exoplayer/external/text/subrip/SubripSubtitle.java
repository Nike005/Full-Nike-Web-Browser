package androidx.media2.exoplayer.external.text.subrip;

import androidx.media2.exoplayer.external.text.Cue;
import androidx.media2.exoplayer.external.text.Subtitle;
import androidx.media2.exoplayer.external.util.Assertions;
import androidx.media2.exoplayer.external.util.Util;
import java.util.Collections;
import java.util.List;

final class SubripSubtitle implements Subtitle {
    private final long[] cueTimesUs;
    private final Cue[] cues;

    public SubripSubtitle(Cue[] cueArr, long[] jArr) {
        this.cues = cueArr;
        this.cueTimesUs = jArr;
    }

    public int getNextEventTimeIndex(long j) {
        int binarySearchCeil = Util.binarySearchCeil(this.cueTimesUs, j, false, false);
        if (binarySearchCeil < this.cueTimesUs.length) {
            return binarySearchCeil;
        }
        return -1;
    }

    public int getEventTimeCount() {
        return this.cueTimesUs.length;
    }

    public long getEventTime(int i) {
        boolean z = true;
        Assertions.checkArgument(i >= 0);
        if (i >= this.cueTimesUs.length) {
            z = false;
        }
        Assertions.checkArgument(z);
        return this.cueTimesUs[i];
    }

    public List<Cue> getCues(long j) {
        int binarySearchFloor = Util.binarySearchFloor(this.cueTimesUs, j, true, false);
        if (binarySearchFloor != -1) {
            Cue[] cueArr = this.cues;
            if (cueArr[binarySearchFloor] != null) {
                return Collections.singletonList(cueArr[binarySearchFloor]);
            }
        }
        return Collections.emptyList();
    }
}

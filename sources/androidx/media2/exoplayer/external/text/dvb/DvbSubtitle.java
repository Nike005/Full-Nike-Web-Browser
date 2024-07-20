package androidx.media2.exoplayer.external.text.dvb;

import androidx.media2.exoplayer.external.text.Cue;
import androidx.media2.exoplayer.external.text.Subtitle;
import java.util.List;

final class DvbSubtitle implements Subtitle {
    private final List<Cue> cues;

    public long getEventTime(int i) {
        return 0;
    }

    public int getEventTimeCount() {
        return 1;
    }

    public int getNextEventTimeIndex(long j) {
        return -1;
    }

    public DvbSubtitle(List<Cue> list) {
        this.cues = list;
    }

    public List<Cue> getCues(long j) {
        return this.cues;
    }
}

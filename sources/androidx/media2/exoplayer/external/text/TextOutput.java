package androidx.media2.exoplayer.external.text;

import java.util.List;

public interface TextOutput {
    void onCues(List<Cue> list);
}

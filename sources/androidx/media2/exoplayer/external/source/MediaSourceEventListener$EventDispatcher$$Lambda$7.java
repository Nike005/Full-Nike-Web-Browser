package androidx.media2.exoplayer.external.source;

import androidx.media2.exoplayer.external.source.MediaSource;
import androidx.media2.exoplayer.external.source.MediaSourceEventListener;

final /* synthetic */ class MediaSourceEventListener$EventDispatcher$$Lambda$7 implements Runnable {
    private final MediaSourceEventListener.EventDispatcher arg$1;
    private final MediaSourceEventListener arg$2;
    private final MediaSource.MediaPeriodId arg$3;
    private final MediaSourceEventListener.MediaLoadData arg$4;

    MediaSourceEventListener$EventDispatcher$$Lambda$7(MediaSourceEventListener.EventDispatcher eventDispatcher, MediaSourceEventListener mediaSourceEventListener, MediaSource.MediaPeriodId mediaPeriodId, MediaSourceEventListener.MediaLoadData mediaLoadData) {
        this.arg$1 = eventDispatcher;
        this.arg$2 = mediaSourceEventListener;
        this.arg$3 = mediaPeriodId;
        this.arg$4 = mediaLoadData;
    }

    public void run() {
        this.arg$1.mo34588xcce9218(this.arg$2, this.arg$3, this.arg$4);
    }
}

package androidx.media2.exoplayer.external.source.ads;

import androidx.media2.exoplayer.external.source.ads.AdsMediaSource;

final /* synthetic */ class AdsMediaSource$ComponentListener$$Lambda$0 implements Runnable {
    private final AdsMediaSource.ComponentListener arg$1;
    private final AdPlaybackState arg$2;

    AdsMediaSource$ComponentListener$$Lambda$0(AdsMediaSource.ComponentListener componentListener, AdPlaybackState adPlaybackState) {
        this.arg$1 = componentListener;
        this.arg$2 = adPlaybackState;
    }

    public void run() {
        this.arg$1.lambda$onAdPlaybackState$0$AdsMediaSource$ComponentListener(this.arg$2);
    }
}

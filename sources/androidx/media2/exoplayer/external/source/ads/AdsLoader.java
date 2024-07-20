package androidx.media2.exoplayer.external.source.ads;

import android.view.View;
import android.view.ViewGroup;
import androidx.media2.exoplayer.external.Player;
import androidx.media2.exoplayer.external.source.ads.AdsMediaSource;
import androidx.media2.exoplayer.external.upstream.DataSpec;
import java.io.IOException;

public interface AdsLoader {

    public interface AdViewProvider {
        View[] getAdOverlayViews();

        ViewGroup getAdViewGroup();
    }

    public interface EventListener {
        void onAdClicked();

        void onAdLoadError(AdsMediaSource.AdLoadException adLoadException, DataSpec dataSpec);

        void onAdPlaybackState(AdPlaybackState adPlaybackState);

        void onAdTapped();
    }

    void handlePrepareError(int i, int i2, IOException iOException);

    void release();

    void setPlayer(Player player);

    void setSupportedContentTypes(int... iArr);

    void start(EventListener eventListener, AdViewProvider adViewProvider);

    void stop();
}

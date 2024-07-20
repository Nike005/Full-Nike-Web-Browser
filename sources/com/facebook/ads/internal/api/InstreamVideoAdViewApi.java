package com.facebook.ads.internal.api;

import android.os.Bundle;
import com.facebook.ads.C5169Ad;
import com.facebook.ads.ExtraHints;
import com.facebook.ads.InstreamVideoAdListener;
import com.facebook.ads.InstreamVideoAdView;

public interface InstreamVideoAdViewApi extends C5169Ad {
    InstreamVideoAdView.InstreamVideoLoadConfigBuilder buildLoadAdConfig();

    void destroy();

    String getPlacementId();

    Bundle getSaveInstanceState();

    boolean isAdLoaded();

    void loadAd();

    void loadAd(InstreamVideoAdView.InstreamVideoLoadAdConfig instreamVideoLoadAdConfig);

    @Deprecated
    void loadAdFromBid(String str);

    void setAdListener(InstreamVideoAdListener instreamVideoAdListener);

    @Deprecated
    void setExtraHints(ExtraHints extraHints);

    void setIsAdLoaded(boolean z);

    boolean show();
}

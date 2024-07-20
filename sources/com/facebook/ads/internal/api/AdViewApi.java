package com.facebook.ads.internal.api;

import android.content.res.Configuration;
import com.facebook.ads.AdListener;
import com.facebook.ads.AdView;
import com.facebook.ads.C5169Ad;
import com.facebook.ads.ExtraHints;

public interface AdViewApi extends AdViewParentApi, C5169Ad {
    AdView.AdViewLoadConfigBuilder buildLoadAdConfig();

    void loadAd(AdView.AdViewLoadConfig adViewLoadConfig);

    void onConfigurationChanged(Configuration configuration);

    void setAdListener(AdListener adListener);

    @Deprecated
    void setExtraHints(ExtraHints extraHints);
}

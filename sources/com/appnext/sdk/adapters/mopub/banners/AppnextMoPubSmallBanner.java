package com.appnext.sdk.adapters.mopub.banners;

import android.content.Context;
import com.appnext.banners.SmallBannerAd;
import com.appnext.core.C4924Ad;

public class AppnextMoPubSmallBanner extends SmallBannerAd {
    private static final String TID = "311";

    public String getTID() {
        return TID;
    }

    public AppnextMoPubSmallBanner(Context context, String str) {
        super(context, str);
    }

    protected AppnextMoPubSmallBanner(C4924Ad ad) {
        super(ad);
    }
}

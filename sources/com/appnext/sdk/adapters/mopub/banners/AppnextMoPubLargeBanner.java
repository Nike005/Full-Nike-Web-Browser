package com.appnext.sdk.adapters.mopub.banners;

import android.content.Context;
import com.appnext.banners.LargeBannerAd;
import com.appnext.core.C4924Ad;

public class AppnextMoPubLargeBanner extends LargeBannerAd {
    private static final String TID = "311";

    public String getTID() {
        return TID;
    }

    public AppnextMoPubLargeBanner(Context context, String str) {
        super(context, str);
    }

    protected AppnextMoPubLargeBanner(C4924Ad ad) {
        super(ad);
    }
}

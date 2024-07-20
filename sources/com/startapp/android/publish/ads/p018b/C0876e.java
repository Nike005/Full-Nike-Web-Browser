package com.startapp.android.publish.ads.p018b;

import android.content.Context;
import com.startapp.android.publish.adsCommon.adListeners.AdEventListener;
import com.startapp.android.publish.cache.C1196d;
import com.startapp.android.publish.common.model.AdPreferences;

/* renamed from: com.startapp.android.publish.ads.b.e */
/* compiled from: StartAppSDK */
public class C0876e extends C0874c {
    private static final long serialVersionUID = 1;

    public C0876e(Context context) {
        super(context, AdPreferences.Placement.INAPP_RETURN);
    }

    /* access modifiers changed from: protected */
    public void loadAds(AdPreferences adPreferences, AdEventListener adEventListener) {
        new C0873b(this.context, this, adPreferences, adEventListener).mo14816c();
    }

    /* access modifiers changed from: protected */
    public long getFallbackAdCacheTtl() {
        return C1196d.m1803a().mo15089b().getReturnAdCacheTTL();
    }
}

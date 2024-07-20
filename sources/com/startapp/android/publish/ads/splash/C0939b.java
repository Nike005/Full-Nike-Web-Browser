package com.startapp.android.publish.ads.splash;

import android.content.Context;
import com.startapp.android.publish.ads.p018b.C0874c;
import com.startapp.android.publish.adsCommon.adListeners.AdEventListener;
import com.startapp.android.publish.common.model.AdPreferences;

/* renamed from: com.startapp.android.publish.ads.splash.b */
/* compiled from: StartAppSDK */
public class C0939b extends C0874c {
    private static final long serialVersionUID = 1;

    public C0939b(Context context) {
        super(context, AdPreferences.Placement.INAPP_OVERLAY);
    }

    @Deprecated
    public boolean load(AdPreferences adPreferences, AdEventListener adEventListener) {
        return super.load(adPreferences, adEventListener, false);
    }

    /* access modifiers changed from: protected */
    public void loadAds(AdPreferences adPreferences, AdEventListener adEventListener) {
        new C0938a(this.context, this, adPreferences, adEventListener).mo14816c();
    }
}

package com.startapp.android.publish.ads.p018b;

import android.content.Context;
import com.startapp.android.publish.adsCommon.adListeners.AdEventListener;
import com.startapp.android.publish.common.model.AdPreferences;

/* renamed from: com.startapp.android.publish.ads.b.d */
/* compiled from: StartAppSDK */
public class C0875d extends C0874c {
    private static final long serialVersionUID = 1;

    public C0875d(Context context) {
        super(context, AdPreferences.Placement.INAPP_OVERLAY);
    }

    public void loadAds(AdPreferences adPreferences, AdEventListener adEventListener) {
        new C0872a(this.context, this, adPreferences, adEventListener).mo14816c();
    }
}

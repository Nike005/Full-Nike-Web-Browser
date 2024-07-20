package com.startapp.android.publish.ads.p019c.p020a;

import android.content.Context;
import com.startapp.android.publish.ads.p018b.C0874c;
import com.startapp.android.publish.adsCommon.adListeners.AdEventListener;
import com.startapp.android.publish.common.model.AdPreferences;

/* renamed from: com.startapp.android.publish.ads.c.a.b */
/* compiled from: StartAppSDK */
public class C0904b extends C0874c {
    private static final long serialVersionUID = 1;

    public C0904b(Context context) {
        super(context, AdPreferences.Placement.INAPP_OFFER_WALL);
    }

    /* access modifiers changed from: protected */
    public void loadAds(AdPreferences adPreferences, AdEventListener adEventListener) {
        new C0903a(this.context, this, adPreferences, adEventListener).mo14816c();
    }
}

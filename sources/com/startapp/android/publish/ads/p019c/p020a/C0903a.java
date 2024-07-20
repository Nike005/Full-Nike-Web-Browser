package com.startapp.android.publish.ads.p019c.p020a;

import android.content.Context;
import com.startapp.android.publish.adsCommon.C1098b;
import com.startapp.android.publish.adsCommon.adListeners.AdEventListener;
import com.startapp.android.publish.common.model.AdPreferences;
import com.startapp.android.publish.common.model.GetAdRequest;
import com.startapp.android.publish.html.C1239a;

/* renamed from: com.startapp.android.publish.ads.c.a.a */
/* compiled from: StartAppSDK */
public class C0903a extends C1239a {
    public C0903a(Context context, C0904b bVar, AdPreferences adPreferences, AdEventListener adEventListener) {
        super(context, bVar, adPreferences, adEventListener, AdPreferences.Placement.INAPP_OFFER_WALL, true);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public GetAdRequest mo13957a() {
        GetAdRequest a = super.mo13957a();
        if (a == null) {
            return null;
        }
        a.setAdsNumber(C1098b.m1303a().mo14767g());
        return a;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo13754a(Boolean bool) {
        super.mo13754a(bool);
        mo15405a(bool.booleanValue());
    }
}

package com.startapp.android.publish.ads.p019c.p021b;

import android.content.Context;
import com.startapp.android.publish.ads.list3d.C0925e;
import com.startapp.android.publish.ads.list3d.C0926f;
import com.startapp.android.publish.adsCommon.C1040Ad;
import com.startapp.android.publish.adsCommon.C1098b;
import com.startapp.android.publish.adsCommon.adListeners.AdEventListener;
import com.startapp.android.publish.common.model.AdDetails;
import com.startapp.android.publish.common.model.AdPreferences;
import com.startapp.android.publish.common.model.GetAdRequest;
import com.startapp.android.publish.p016a.C0847a;
import java.util.List;

/* renamed from: com.startapp.android.publish.ads.c.b.a */
/* compiled from: StartAppSDK */
public class C0905a extends C0847a {
    public C0905a(Context context, C0906b bVar, AdPreferences adPreferences, AdEventListener adEventListener) {
        super(context, bVar, adPreferences, adEventListener, AdPreferences.Placement.INAPP_OFFER_WALL);
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
    public void mo13753a(C1040Ad ad) {
        C0906b bVar = (C0906b) ad;
        List<AdDetails> d = bVar.mo14937d();
        C0925e a = C0926f.m744a().mo14097a(bVar.mo13946a());
        a.mo14087a();
        if (d != null) {
            for (AdDetails a2 : d) {
                a.mo14090a(a2);
            }
        }
    }
}

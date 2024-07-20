package com.startapp.android.publish.ads.banner.banner3d;

import android.content.Context;
import com.startapp.android.publish.ads.banner.C0880a;
import com.startapp.android.publish.ads.banner.C0901c;
import com.startapp.android.publish.adsCommon.C1040Ad;
import com.startapp.android.publish.adsCommon.adListeners.AdEventListener;
import com.startapp.android.publish.common.model.AdPreferences;
import com.startapp.android.publish.common.model.GetAdRequest;
import com.startapp.android.publish.p016a.C0847a;

/* renamed from: com.startapp.android.publish.ads.banner.banner3d.c */
/* compiled from: StartAppSDK */
public class C0893c extends C0847a {

    /* renamed from: g */
    private int f490g = 0;

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo13753a(C1040Ad ad) {
    }

    public C0893c(Context context, C0888a aVar, int i, AdPreferences adPreferences, AdEventListener adEventListener) {
        super(context, aVar, adPreferences, adEventListener, AdPreferences.Placement.INAPP_BANNER);
        this.f490g = i;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public GetAdRequest mo13957a() {
        C0880a aVar = new C0880a();
        mo14815b((GetAdRequest) aVar);
        aVar.setAdsNumber(C0901c.m627a().mo13973b().mo13884f());
        aVar.setOffset(this.f490g);
        aVar.mo13900a(((C0888a) this.f1134b).mo13948b());
        return aVar;
    }
}

package com.startapp.android.publish.ads.banner.bannerstandard;

import android.content.Context;
import com.startapp.android.publish.ads.banner.C0880a;
import com.startapp.android.publish.ads.banner.C0901c;
import com.startapp.android.publish.adsCommon.C1118e;
import com.startapp.android.publish.adsCommon.adListeners.AdEventListener;
import com.startapp.android.publish.common.model.AdPreferences;
import com.startapp.android.publish.common.model.GetAdRequest;
import com.startapp.android.publish.html.C1239a;
import com.startapp.common.p043a.C1270g;

/* renamed from: com.startapp.android.publish.ads.banner.bannerstandard.b */
/* compiled from: StartAppSDK */
public class C0900b extends C1239a {

    /* renamed from: i */
    private int f491i = 0;

    public C0900b(Context context, C1118e eVar, int i, AdPreferences adPreferences, AdEventListener adEventListener) {
        super(context, eVar, adPreferences, adEventListener, AdPreferences.Placement.INAPP_BANNER, false);
        this.f491i = i;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public GetAdRequest mo13957a() {
        C0899a aVar = (C0899a) this.f1134b;
        C0880a aVar2 = new C0880a();
        mo14815b((GetAdRequest) aVar2);
        aVar2.setWidth(aVar.mo14845h());
        aVar2.setHeight(aVar.mo14847j());
        aVar2.setOffset(this.f491i);
        aVar2.setAdsNumber(C0901c.m627a().mo13973b().mo13885g());
        aVar2.mo13900a(aVar.mo13971b());
        aVar2.mo13899a(aVar.mo13972c());
        return aVar2;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo13754a(Boolean bool) {
        super.mo13754a(bool);
        mo15405a(bool.booleanValue());
        C1270g.m2076a(4, "Html onPostExecute, result=[" + bool + "]");
    }
}

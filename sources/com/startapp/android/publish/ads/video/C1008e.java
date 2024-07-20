package com.startapp.android.publish.ads.video;

import android.content.Context;
import com.startapp.android.publish.ads.p018b.C0874c;
import com.startapp.android.publish.ads.splash.SplashConfig;
import com.startapp.android.publish.ads.video.p024c.p025a.C1001e;
import com.startapp.android.publish.ads.video.p024c.p025a.p026a.C0991b;
import com.startapp.android.publish.adsCommon.adListeners.AdEventListener;
import com.startapp.android.publish.common.model.AdPreferences;
import com.startapp.common.p046c.C1295b;

/* renamed from: com.startapp.android.publish.ads.video.e */
/* compiled from: StartAppSDK */
public class C1008e extends C0874c {
    private static final long serialVersionUID = 1;
    private VideoAdDetails videoAdDetails = null;

    public C1008e(Context context) {
        super(context, AdPreferences.Placement.INAPP_OVERLAY);
    }

    /* access modifiers changed from: protected */
    public void loadAds(AdPreferences adPreferences, AdEventListener adEventListener) {
        new C0967b(this.context, this, adPreferences, adEventListener).mo14816c();
    }

    /* renamed from: b */
    public void mo14369b(String str) {
        super.mo14369b(str);
        m1010f(mo14830a(str, "@videoJson@"));
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public boolean mo13829a() {
        return this.videoAdDetails != null;
    }

    /* renamed from: d */
    public VideoAdDetails mo14370d() {
        return this.videoAdDetails;
    }

    /* renamed from: a */
    public void mo14368a(C1001e eVar, boolean z) {
        if (eVar != null) {
            this.videoAdDetails = new VideoAdDetails(eVar, z);
            C0991b g = eVar.mo14359g();
            if (g == null) {
                return;
            }
            if (g.mo14328d().intValue() > g.mo14330e().intValue()) {
                mo14832a(SplashConfig.Orientation.LANDSCAPE);
            } else {
                mo14832a(SplashConfig.Orientation.PORTRAIT);
            }
        }
    }

    /* renamed from: e */
    public void mo14371e() {
        this.videoAdDetails = null;
    }

    /* renamed from: f */
    private void m1010f(String str) {
        if (str != null) {
            this.videoAdDetails = (VideoAdDetails) C1295b.m2179a(str, VideoAdDetails.class);
        }
    }
}

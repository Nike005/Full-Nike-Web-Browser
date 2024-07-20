package com.startapp.android.publish.adsCommon;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import com.startapp.android.publish.adsCommon.StartAppAd;
import com.startapp.android.publish.adsCommon.adListeners.AdEventListener;
import com.startapp.android.publish.common.model.AdPreferences;
import com.startapp.common.p043a.C1270g;

/* renamed from: com.startapp.android.publish.adsCommon.f */
/* compiled from: StartAppSDK */
public class C1123f {

    /* renamed from: a */
    protected StartAppAd f1147a;

    /* renamed from: b */
    private boolean f1148b;

    /* renamed from: c */
    private AutoInterstitialPreferences f1149c;

    /* renamed from: d */
    private long f1150d;

    /* renamed from: e */
    private int f1151e;

    /* renamed from: com.startapp.android.publish.adsCommon.f$a */
    /* compiled from: StartAppSDK */
    private static class C1125a {
        /* access modifiers changed from: private */

        /* renamed from: a */
        public static final C1123f f1153a = new C1123f();
    }

    private C1123f() {
        this.f1148b = false;
        this.f1149c = null;
        this.f1150d = -1;
        this.f1151e = -1;
        this.f1147a = null;
    }

    /* renamed from: a */
    public static C1123f m1476a() {
        return C1125a.f1153a;
    }

    /* renamed from: b */
    public void mo14862b() {
        this.f1148b = true;
    }

    /* renamed from: c */
    public void mo14863c() {
        this.f1148b = false;
    }

    /* renamed from: a */
    public void mo14861a(AutoInterstitialPreferences autoInterstitialPreferences) {
        this.f1149c = autoInterstitialPreferences;
        this.f1150d = -1;
        this.f1151e = -1;
    }

    /* access modifiers changed from: protected */
    /* renamed from: d */
    public void mo14864d() {
        this.f1150d = System.currentTimeMillis();
        this.f1151e = 0;
    }

    /* renamed from: e */
    private boolean m1479e() {
        return this.f1148b && C1098b.m1303a().mo14755I();
    }

    /* renamed from: f */
    private boolean m1480f() {
        if (this.f1149c == null) {
            this.f1149c = new AutoInterstitialPreferences();
        }
        boolean z = this.f1150d <= 0 || System.currentTimeMillis() >= this.f1150d + ((long) (this.f1149c.getSecondsBetweenAds() * 1000));
        int i = this.f1151e;
        boolean z2 = i <= 0 || i >= this.f1149c.getActivitiesBetweenAds();
        if (!z || !z2) {
            return false;
        }
        return true;
    }

    /* renamed from: a */
    private boolean m1477a(Activity activity) {
        String name = activity.getClass().getName();
        if (!name.startsWith("com.startapp.android.publish." + "adsCommon.activities.OverlayActivity")) {
            if (!name.startsWith("com.startapp.android.publish." + "adsCommon.activities.FullScreenActivity")) {
                StringBuilder sb = new StringBuilder();
                sb.append("com.startapp.android.publish.");
                sb.append("ads.list3d.List3DActivity");
                return name.startsWith(sb.toString());
            }
        }
    }

    /* renamed from: b */
    private boolean m1478b(Activity activity) {
        return activity.getClass().getName().equals(C1174m.m1649a().mo15001g());
    }

    /* renamed from: a */
    public void mo14860a(Context context) {
        if (m1479e() && m1480f()) {
            if (this.f1147a == null) {
                this.f1147a = new StartAppAd(context);
            }
            this.f1147a.loadAd(StartAppAd.AdMode.AUTOMATIC, new AdPreferences().setAi(true), new AdEventListener() {
                public void onReceiveAd(C1040Ad ad) {
                    if (C1123f.this.f1147a.showAd()) {
                        C1270g.m2078a("InterActivityAdManager", 3, "ShowInterActivityAd");
                        C1123f.this.mo14864d();
                    }
                }

                public void onFailedToReceiveAd(C1040Ad ad) {
                    C1270g.m2078a("InterActivityAdManager", 3, "FailedToShowInterActivityAd, error: [" + ad.errorMessage + "]");
                }
            });
        }
    }

    /* renamed from: a */
    public void mo14859a(Activity activity, Bundle bundle) {
        if (bundle == null && !m1477a(activity) && !m1478b(activity)) {
            this.f1151e++;
            mo14860a((Context) activity);
        }
    }
}

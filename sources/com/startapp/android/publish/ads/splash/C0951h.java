package com.startapp.android.publish.ads.splash;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import com.startapp.android.publish.ads.splash.SplashConfig;
import com.startapp.android.publish.adsCommon.C1040Ad;
import com.startapp.android.publish.adsCommon.StartAppAd;
import com.startapp.android.publish.adsCommon.adListeners.AdDisplayListener;
import com.startapp.android.publish.adsCommon.adListeners.AdEventListener;
import com.startapp.android.publish.adsCommon.p028a.C1073f;
import com.startapp.android.publish.adsCommon.p033f.C1130d;
import com.startapp.android.publish.adsCommon.p033f.C1132f;
import com.startapp.android.publish.cache.C1195c;
import com.startapp.android.publish.common.model.AdPreferences;
import com.startapp.common.p043a.C1261c;
import com.startapp.common.p043a.C1270g;

/* renamed from: com.startapp.android.publish.ads.splash.h */
/* compiled from: StartAppSDK */
public class C0951h {

    /* renamed from: a */
    Activity f653a;

    /* renamed from: b */
    C0940c f654b;

    /* renamed from: c */
    C1195c f655c;

    /* renamed from: d */
    C0946d f656d = null;

    /* renamed from: e */
    boolean f657e = false;

    /* renamed from: f */
    C0961a f658f;

    /* renamed from: g */
    Runnable f659g = new Runnable() {
        public void run() {
            C0951h.this.f654b.mo14189a(C0951h.this.f656d, (C0948e) new C0948e() {
                /* renamed from: a */
                public void mo14207a() {
                    if (!C0951h.this.f657e && C0951h.this.f658f != null) {
                        C1270g.m2078a("Splash", 4, "Displaying Splash ad");
                        C0951h.this.f658f.showAd((AdDisplayListener) new AdDisplayListener() {
                            public void adNotDisplayed(C1040Ad ad) {
                            }

                            public void adHidden(C1040Ad ad) {
                                C0951h.this.f654b.mo14195c();
                            }

                            public void adDisplayed(C1040Ad ad) {
                                C0951h.this.f654b.mo14197d();
                            }

                            public void adClicked(C1040Ad ad) {
                                C0951h.this.f654b.mo14202i();
                            }
                        });
                        C0951h.this.mo14223f();
                        C0951h.this.f653a.finish();
                    }
                }
            });
        }
    };

    /* renamed from: h */
    private SplashConfig f660h;

    /* renamed from: i */
    private Handler f661i = new Handler();

    /* renamed from: j */
    private AdPreferences f662j;

    /* renamed from: k */
    private Runnable f663k = new Runnable() {
        public void run() {
            if (C0951h.this.mo14220c()) {
                C0951h.this.mo14221d();
                C0951h.this.mo14222e();
                return;
            }
            C0951h.this.f653a.finish();
        }
    };

    /* renamed from: l */
    private AdEventListener f664l = new AdEventListener() {
        public void onReceiveAd(C1040Ad ad) {
            C1270g.m2078a("Splash", 4, "Splash ad received");
            C0951h.this.f654b.mo14191a(C0951h.this.f659g);
        }

        public void onFailedToReceiveAd(C1040Ad ad) {
            if (C0951h.this.f658f != null) {
                C0951h.this.f654b.mo14193b();
            }
        }
    };

    /* renamed from: a */
    public void mo14217a() {
    }

    /* renamed from: com.startapp.android.publish.ads.splash.h$a */
    /* compiled from: StartAppSDK */
    private static class C0961a extends StartAppAd {
        private static final long serialVersionUID = 1;

        public C0961a(Context context) {
            super(context);
            this.placement = AdPreferences.Placement.INAPP_SPLASH;
        }

        /* access modifiers changed from: protected */
        public C1073f shouldDisplayAd(String str, AdPreferences.Placement placement) {
            return new C1073f(true);
        }
    }

    public C0951h(Activity activity, SplashConfig splashConfig, AdPreferences adPreferences) {
        this.f653a = activity;
        this.f660h = splashConfig;
        this.f662j = adPreferences;
        try {
            m800h();
            this.f654b = new C0940c(activity, this.f656d);
        } catch (Exception e) {
            C0940c cVar = new C0940c(activity);
            this.f654b = cVar;
            cVar.mo14188a();
            this.f654b.mo14193b();
            C1132f.m1527a(activity, C1130d.EXCEPTION, "SplashScreen.constructor - WebView failed", e.getMessage(), "");
        }
    }

    /* renamed from: h */
    private void m800h() {
        this.f660h.initSplashLogo(this.f653a);
        if (!m803k()) {
            this.f656d = this.f660h.initSplashHtml(this.f653a);
        }
    }

    /* renamed from: a */
    public void mo14218a(Bundle bundle) {
        C1270g.m2078a("Splash", 4, "========= Splash Screen Feature =========");
        this.f654b.mo14203j();
        if (!m801i()) {
            this.f661i.post(this.f663k);
            return;
        }
        this.f661i.postDelayed(this.f663k, 100);
        C1270g.m2078a("Splash", 4, "Splash screen orientation is being modified");
    }

    /* renamed from: b */
    public void mo14219b() {
        this.f661i.removeCallbacks(this.f663k);
        this.f654b.mo14198e();
    }

    /* renamed from: i */
    private boolean m801i() {
        int i = this.f653a.getResources().getConfiguration().orientation;
        if (this.f660h.getOrientation() == SplashConfig.Orientation.AUTO) {
            if (i == 2) {
                this.f660h.setOrientation(SplashConfig.Orientation.LANDSCAPE);
            } else {
                this.f660h.setOrientation(SplashConfig.Orientation.PORTRAIT);
            }
        }
        int i2 = C09607.f673a[this.f660h.getOrientation().ordinal()];
        boolean z = true;
        boolean z2 = false;
        if (i2 != 1) {
            if (i2 == 2) {
                if (i != 1) {
                    z = false;
                }
                C1261c.m2038b(this.f653a);
            }
            C1270g.m2078a("Splash", 4, "Set Orientation: [" + this.f660h.getOrientation().toString() + "]");
            return z2;
        }
        if (i != 2) {
            z = false;
        }
        C1261c.m2020a(this.f653a);
        z2 = z;
        C1270g.m2078a("Splash", 4, "Set Orientation: [" + this.f660h.getOrientation().toString() + "]");
        return z2;
    }

    /* renamed from: com.startapp.android.publish.ads.splash.h$7 */
    /* compiled from: StartAppSDK */
    static /* synthetic */ class C09607 {

        /* renamed from: a */
        static final /* synthetic */ int[] f673a;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        static {
            /*
                com.startapp.android.publish.ads.splash.SplashConfig$Orientation[] r0 = com.startapp.android.publish.ads.splash.SplashConfig.Orientation.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                f673a = r0
                com.startapp.android.publish.ads.splash.SplashConfig$Orientation r1 = com.startapp.android.publish.ads.splash.SplashConfig.Orientation.PORTRAIT     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = f673a     // Catch:{ NoSuchFieldError -> 0x001d }
                com.startapp.android.publish.ads.splash.SplashConfig$Orientation r1 = com.startapp.android.publish.ads.splash.SplashConfig.Orientation.LANDSCAPE     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.startapp.android.publish.ads.splash.C0951h.C09607.<clinit>():void");
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: c */
    public boolean mo14220c() {
        C1270g.m2078a("Splash", 4, "Displaying Splash screen");
        if (this.f660h.validate(this.f653a)) {
            View j = m802j();
            if (j == null) {
                return false;
            }
            this.f653a.setContentView(j, new ViewGroup.LayoutParams(-1, -1));
            return true;
        }
        throw new IllegalArgumentException(this.f660h.getErrorMessage());
    }

    /* renamed from: j */
    private View m802j() {
        if (m803k()) {
            return this.f660h.getLayout(this.f653a);
        }
        C0946d dVar = this.f656d;
        if (dVar != null) {
            return dVar.mo14214c();
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: d */
    public void mo14221d() {
        C1270g.m2078a("Splash", 4, "Loading Splash Ad");
        C0961a aVar = new C0961a(this.f653a.getApplicationContext());
        this.f658f = aVar;
        this.f655c = aVar.loadSplash(this.f662j, this.f664l);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: e */
    public void mo14222e() {
        C1270g.m2078a("Splash", 4, "Started Splash Loading Timer");
        this.f661i.postDelayed(new Runnable() {
            public void run() {
                if (C0951h.this.f654b.mo14194b(C0951h.this.f659g, C0951h.this.f655c)) {
                    C0951h.this.f658f = null;
                    C0951h.this.f655c = null;
                }
            }
        }, this.f660h.getMaxLoadAdTimeout().longValue());
        this.f661i.postDelayed(new Runnable() {
            public void run() {
                C0951h.this.f654b.mo14192a(C0951h.this.f659g, C0951h.this.f655c);
            }
        }, this.f660h.getMinSplashTime().getIndex());
    }

    /* access modifiers changed from: package-private */
    /* renamed from: f */
    public void mo14223f() {
        C1270g.m2078a("Splash", 4, "Started Splash Display Timer");
        if (this.f660h.getMaxAdDisplayTime() != SplashConfig.MaxAdDisplayTime.FOR_EVER) {
            this.f661i.postDelayed(new Runnable() {
                public void run() {
                    C0951h.this.f654b.mo14190a((StartAppAd) C0951h.this.f658f);
                }
            }, this.f660h.getMaxAdDisplayTime().getIndex());
        }
    }

    /* renamed from: g */
    public void mo14224g() {
        this.f657e = true;
        this.f654b.mo14201h();
    }

    /* renamed from: k */
    private boolean m803k() {
        return !this.f660h.isHtmlSplash() || this.f660h.isUserDefinedSplash();
    }
}

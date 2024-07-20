package com.startapp.android.publish.ads.splash;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import com.startapp.android.publish.adsCommon.C1103c;
import com.startapp.android.publish.adsCommon.StartAppAd;
import com.startapp.android.publish.adsCommon.p028a.C1073f;
import com.startapp.android.publish.adsCommon.p028a.C1074g;
import com.startapp.android.publish.cache.C1186a;
import com.startapp.android.publish.cache.C1195c;
import com.startapp.android.publish.common.metaData.C1231d;
import com.startapp.android.publish.common.metaData.MetaData;
import com.startapp.android.publish.common.model.AdPreferences;
import com.startapp.common.C1275b;
import com.startapp.common.Constants;
import com.startapp.common.p043a.C1270g;
import com.startapp.common.p043a.C1274i;

/* renamed from: com.startapp.android.publish.ads.splash.c */
/* compiled from: StartAppSDK */
public class C0940c {

    /* renamed from: a */
    Activity f621a;

    /* renamed from: b */
    boolean f622b;

    /* renamed from: c */
    C0945a f623c;

    /* renamed from: d */
    private boolean f624d;

    /* renamed from: e */
    private boolean f625e;

    /* renamed from: f */
    private boolean f626f;

    /* renamed from: g */
    private boolean f627g;

    /* renamed from: h */
    private boolean f628h;

    /* renamed from: i */
    private C0946d f629i;

    /* renamed from: j */
    private BroadcastReceiver f630j;

    /* renamed from: com.startapp.android.publish.ads.splash.c$a */
    /* compiled from: StartAppSDK */
    enum C0945a {
        LOADING,
        RECEIVED,
        DISPLAYED,
        HIDDEN,
        DO_NOT_DISPLAY
    }

    public C0940c(Activity activity) {
        this.f624d = false;
        this.f625e = true;
        this.f626f = false;
        this.f627g = false;
        this.f628h = false;
        this.f622b = false;
        this.f623c = C0945a.LOADING;
        this.f629i = null;
        this.f630j = new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                C0940c.this.mo14202i();
            }
        };
        this.f621a = activity;
    }

    public C0940c(Activity activity, C0946d dVar) {
        this(activity);
        this.f629i = dVar;
    }

    /* renamed from: a */
    public void mo14192a(final Runnable runnable, final C1195c cVar) {
        C1270g.m2078a("Splash", 4, "Minimum splash screen time has passed");
        this.f624d = true;
        C09411 r0 = new C1231d() {

            /* renamed from: d */
            private Runnable f634d = new Runnable() {
                public void run() {
                    C0940c.this.f622b = true;
                    if (C0940c.this.f623c != C0945a.DO_NOT_DISPLAY) {
                        C0940c.this.mo14196c(runnable, cVar);
                    }
                }
            };

            /* renamed from: a */
            public void mo14204a() {
                C1270g.m2078a("Splash", 4, "MetaData received");
                C0940c.this.f621a.runOnUiThread(this.f634d);
            }

            /* renamed from: b */
            public void mo14205b() {
                C1270g.m2078a("Splash", 4, "MetaData failed to receive - proceeding with old MetaData");
                C0940c.this.f621a.runOnUiThread(this.f634d);
            }
        };
        if (this.f623c != C0945a.DO_NOT_DISPLAY) {
            m759a((C1231d) r0);
        } else {
            m761k();
        }
    }

    /* renamed from: a */
    public void mo14188a() {
        this.f624d = true;
    }

    /* renamed from: a */
    private void m759a(C1231d dVar) {
        synchronized (MetaData.getLock()) {
            if (MetaData.getInstance().isReady()) {
                dVar.mo14204a();
            } else {
                MetaData.getInstance().addMetaDataListener(dVar);
            }
        }
    }

    /* renamed from: a */
    public void mo14191a(Runnable runnable) {
        C1270g.m2078a("Splash", 4, "Splash ad received");
        if (this.f623c == C0945a.LOADING) {
            this.f623c = C0945a.RECEIVED;
        }
        m760b(runnable);
    }

    /* renamed from: b */
    public void mo14193b() {
        C1270g.m2078a("Splash", 4, "Error receiving Ad");
        this.f623c = C0945a.DO_NOT_DISPLAY;
        m760b((Runnable) null);
    }

    /* renamed from: b */
    public boolean mo14194b(Runnable runnable, C1195c cVar) {
        if (!this.f628h) {
            if (this.f623c == C0945a.LOADING) {
                C1270g.m2078a("Splash", 4, "Splash Loading Timer Expired");
                this.f625e = false;
                this.f623c = C0945a.DO_NOT_DISPLAY;
                m761k();
                return true;
            } else if (this.f623c == C0945a.RECEIVED) {
                C1270g.m2078a("Splash", 4, "MetaData Loading Timer Expired - proceeding with old MetaData");
                this.f622b = true;
                mo14196c(runnable, cVar);
            }
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: c */
    public void mo14196c(Runnable runnable, C1195c cVar) {
        C1073f a = C1074g.m1233a().mo14667b().mo14661a(AdPreferences.Placement.INAPP_SPLASH, (String) null);
        C1270g.m2078a("Splash", 4, "checkAdRulesAndShowAd: shouldDisplayAd " + a.mo14664a());
        if (a.mo14664a()) {
            C1270g.m2078a("Splash", 4, "checkAdRulesAndShowAd: showAd");
            m760b(runnable);
            return;
        }
        C1270g.m2078a("Splash", 4, "Should not display splash ad");
        this.f623c = C0945a.DO_NOT_DISPLAY;
        if (cVar != null) {
            C1103c.m1383a((Context) this.f621a, C1103c.m1390a(C1186a.m1756a().mo15066b(cVar)), (String) null, a.mo14666c());
        }
        if (Constants.m1978a().booleanValue()) {
            C1274i.m2100a().mo15477a(this.f621a, a.mo14665b());
        }
        m761k();
    }

    /* renamed from: c */
    public void mo14195c() {
        C1270g.m2078a("Splash", 4, "Splash Screen has been hidden");
        this.f623c = C0945a.HIDDEN;
        mo14200g();
        if (!this.f621a.isFinishing()) {
            this.f621a.finish();
        }
    }

    /* renamed from: d */
    public void mo14197d() {
        this.f623c = C0945a.DISPLAYED;
    }

    /* renamed from: b */
    private void m760b(Runnable runnable) {
        if (!this.f624d) {
            return;
        }
        if (!this.f622b && runnable != null) {
            return;
        }
        if (this.f623c == C0945a.RECEIVED && runnable != null) {
            this.f625e = false;
            runnable.run();
        } else if (this.f623c != C0945a.LOADING) {
            m761k();
        }
    }

    /* renamed from: a */
    public void mo14190a(StartAppAd startAppAd) {
        if (this.f623c == C0945a.DISPLAYED) {
            C1270g.m2078a("Splash", 4, "Splash Ad Display Timeout");
            if (!this.f627g) {
                C1270g.m2078a("Splash", 4, "Closing Splash Ad");
                startAppAd.close();
                mo14195c();
            }
        }
    }

    /* renamed from: e */
    public void mo14198e() {
        if (this.f623c != C0945a.DISPLAYED && this.f623c != C0945a.DO_NOT_DISPLAY) {
            this.f623c = C0945a.DO_NOT_DISPLAY;
            if (this.f625e) {
                mo14199f();
            }
        }
    }

    /* renamed from: f */
    public void mo14199f() {
        C1270g.m2078a("Splash", 4, "User Canceled Splash Screen");
        mo14200g();
    }

    /* renamed from: k */
    private void m761k() {
        mo14189a(this.f629i, (C0948e) new C0948e() {
            /* renamed from: a */
            public void mo14207a() {
                C1270g.m2078a("Splash", 4, "Loading Main Activity");
                C0940c.this.mo14200g();
                if (!C0940c.this.f621a.isFinishing()) {
                    C0940c.this.f621a.finish();
                }
            }
        });
    }

    /* access modifiers changed from: package-private */
    /* renamed from: g */
    public void mo14200g() {
        if (!this.f626f) {
            this.f626f = true;
            C1275b.m2102a((Context) this.f621a).mo15481a(new Intent("com.startapp.android.splashHidden"));
        }
        if (this.f630j != null) {
            try {
                Log.v("startapp", "unregistering receiver");
                C1275b.m2102a((Context) this.f621a).mo15479a(this.f630j);
            } catch (IllegalArgumentException unused) {
            }
        }
    }

    /* renamed from: h */
    public void mo14201h() {
        this.f628h = true;
    }

    /* renamed from: i */
    public void mo14202i() {
        this.f627g = true;
    }

    /* renamed from: j */
    public void mo14203j() {
        C1275b.m2102a((Context) this.f621a).mo15480a(this.f630j, new IntentFilter("com.startapp.android.adInfoWasClickedBroadcastListener"));
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo14189a(C0946d dVar, C0948e eVar) {
        if (dVar == null) {
            eVar.mo14207a();
            return;
        }
        dVar.mo14211a(eVar);
        dVar.mo14212b();
    }
}

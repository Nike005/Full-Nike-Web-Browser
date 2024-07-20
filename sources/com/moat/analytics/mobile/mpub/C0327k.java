package com.moat.analytics.mobile.mpub;

import android.app.Application;
import android.content.Context;
import com.moat.analytics.mobile.mpub.C0310g;
import com.moat.analytics.mobile.mpub.C0351w;
import java.lang.ref.WeakReference;

/* renamed from: com.moat.analytics.mobile.mpub.k */
class C0327k extends MoatAnalytics implements C0351w.C0357b {

    /* renamed from: a */
    boolean f148a = false;

    /* renamed from: b */
    boolean f149b = false;

    /* renamed from: c */
    C0310g f150c;

    /* renamed from: d */
    WeakReference<Context> f151d;

    /* renamed from: e */
    private boolean f152e = false;

    /* renamed from: f */
    private String f153f;

    /* renamed from: g */
    private MoatOptions f154g;

    C0327k() {
    }

    /* renamed from: a */
    private void m194a(MoatOptions moatOptions, Application application) {
        if (this.f152e) {
            C0336p.m228a(3, "Analytics", (Object) this, "Moat SDK has already been started.");
            return;
        }
        this.f154g = moatOptions;
        C0351w.m264a().mo10464b();
        if (application != null) {
            if (moatOptions.loggingEnabled && C0339s.m240b(application.getApplicationContext())) {
                this.f148a = true;
            }
            this.f151d = new WeakReference<>(application.getApplicationContext());
            this.f152e = true;
            this.f149b = moatOptions.autoTrackGMAInterstitials;
            C0298a.m72a(application);
            C0351w.m264a().mo10463a((C0351w.C0357b) this);
            if (!moatOptions.disableAdIdCollection) {
                C0339s.m238a((Context) application);
            }
            C0336p.m231a("[SUCCESS] ", "Moat Analytics SDK Version 2.6.6 started");
            return;
        }
        throw new C0330n("Moat Analytics SDK didn't start, application was null");
    }

    /* renamed from: d */
    private void m195d() {
        if (this.f150c == null) {
            C0310g gVar = new C0310g(C0298a.m71a(), C0310g.C0315a.DISPLAY);
            this.f150c = gVar;
            gVar.mo10412a(this.f153f);
            C0336p.m228a(3, "Analytics", (Object) this, "Preparing native display tracking with partner code " + this.f153f);
            C0336p.m231a("[SUCCESS] ", "Prepared for native display tracking with partner code " + this.f153f);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public boolean mo10449a() {
        return this.f152e;
    }

    /* renamed from: b */
    public void mo10450b() {
        C0330n.m213a();
        if (this.f153f != null) {
            try {
                m195d();
            } catch (Exception e) {
                C0330n.m214a(e);
            }
        }
    }

    /* renamed from: c */
    public void mo10451c() {
    }

    public void prepareNativeDisplayTracking(String str) {
        this.f153f = str;
        if (C0351w.m264a().f197a != C0351w.C0359d.OFF) {
            try {
                m195d();
            } catch (Exception e) {
                C0330n.m214a(e);
            }
        }
    }

    public void start(Application application) {
        start(new MoatOptions(), application);
    }

    public void start(MoatOptions moatOptions, Application application) {
        try {
            m194a(moatOptions, application);
        } catch (Exception e) {
            C0330n.m214a(e);
        }
    }
}

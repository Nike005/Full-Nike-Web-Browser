package com.startapp.android.publish.cache;

import android.os.Handler;
import android.os.Looper;
import com.startapp.common.p043a.C1270g;

/* renamed from: com.startapp.android.publish.cache.e */
/* compiled from: StartAppSDK */
public abstract class C1197e {

    /* renamed from: a */
    protected C1200g f1334a;

    /* renamed from: b */
    private Handler f1335b = null;

    /* renamed from: c */
    private Long f1336c = null;

    /* renamed from: d */
    private boolean f1337d = false;

    /* access modifiers changed from: protected */
    /* renamed from: c */
    public abstract boolean mo15082c();

    /* access modifiers changed from: protected */
    /* renamed from: d */
    public abstract long mo15083d();

    /* access modifiers changed from: protected */
    /* renamed from: e */
    public String mo15084e() {
        return "CacheScheduledTask";
    }

    public C1197e(C1200g gVar) {
        this.f1334a = gVar;
    }

    /* renamed from: f */
    public void mo15091f() {
        if (!this.f1337d) {
            if (this.f1336c == null) {
                this.f1336c = Long.valueOf(System.currentTimeMillis());
            }
            if (mo15082c()) {
                if (this.f1335b == null) {
                    Looper myLooper = Looper.myLooper();
                    if (myLooper == null) {
                        myLooper = Looper.getMainLooper();
                    }
                    this.f1335b = new Handler(myLooper);
                }
                long d = mo15083d();
                if (d >= 0) {
                    this.f1337d = true;
                    String e = mo15084e();
                    C1270g.m2078a(e, 4, "Started for " + this.f1334a.mo15105c() + " - scheduled to: " + d);
                    this.f1335b.postDelayed(new Runnable() {
                        public void run() {
                            C1197e.this.mo15081b();
                        }
                    }, d);
                    return;
                }
                C1270g.m2078a(mo15084e(), 3, "Can't start, scheduled time < 0");
                return;
            }
            C1270g.m2078a(mo15084e(), 3, "Not allowed");
        }
    }

    /* renamed from: g */
    public void mo15092g() {
        m1809j();
        m1810k();
    }

    /* renamed from: h */
    public void mo15093h() {
        m1809j();
        this.f1337d = false;
    }

    /* renamed from: a */
    public void mo15080a() {
        String e = mo15084e();
        C1270g.m2078a(e, 4, "Resetting for " + this.f1334a.mo15105c());
        mo15092g();
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public void mo15081b() {
        String e = mo15084e();
        C1270g.m2078a(e, 3, "Time reached, reloading " + this.f1334a.mo15105c());
        m1810k();
        this.f1334a.mo15113k();
    }

    /* access modifiers changed from: protected */
    /* renamed from: i */
    public final Long mo15094i() {
        return this.f1336c;
    }

    /* renamed from: j */
    private void m1809j() {
        Handler handler = this.f1335b;
        if (handler != null) {
            handler.removeCallbacksAndMessages((Object) null);
        }
    }

    /* renamed from: k */
    private void m1810k() {
        this.f1336c = null;
        this.f1337d = false;
    }
}

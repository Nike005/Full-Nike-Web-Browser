package com.tappx.p048a;

import android.content.Context;
import com.tappx.p048a.C1388e1;
import com.tappx.p048a.C1410f3;
import com.tappx.sdk.android.AdRequest;
import com.tappx.sdk.android.TappxAdError;
import com.tappx.sdk.android.TappxInterstitial;
import com.tappx.sdk.android.TappxInterstitialListener;

/* renamed from: com.tappx.a.h2 */
public class C1433h2 extends C1406f2 {
    /* access modifiers changed from: private */

    /* renamed from: l */
    public final TappxInterstitial f1890l;

    /* renamed from: m */
    private final C1410f3 f1891m;

    /* renamed from: n */
    private final C1388e1 f1892n;
    /* access modifiers changed from: private */

    /* renamed from: o */
    public TappxInterstitialListener f1893o;
    /* access modifiers changed from: private */

    /* renamed from: p */
    public C1640u1 f1894p;
    /* access modifiers changed from: private */

    /* renamed from: q */
    public C1361d1 f1895q;
    /* access modifiers changed from: private */

    /* renamed from: r */
    public boolean f1896r;
    /* access modifiers changed from: private */

    /* renamed from: s */
    public boolean f1897s;

    /* renamed from: t */
    private final C1410f3.C1412b f1898t = new C1434a();

    /* renamed from: u */
    private C1388e1.C1389a f1899u = new C1435b();

    /* renamed from: com.tappx.a.h2$a */
    class C1434a implements C1410f3.C1412b {
        C1434a() {
        }

        /* renamed from: a */
        public void mo15794a() {
            C1433h2.this.m2777j();
        }
    }

    /* renamed from: com.tappx.a.h2$c */
    class C1436c implements Runnable {
        C1436c() {
        }

        public void run() {
            C1433h2.this.m2778k();
        }
    }

    public C1433h2(TappxInterstitial tappxInterstitial, Context context) {
        super(context, C1654v1.INTERSTITIAL);
        this.f1890l = tappxInterstitial;
        C1450i a = C1450i.m2819a(context);
        C1388e1 b = a.mo15855b();
        this.f1892n = b;
        b.mo15733a(this.f1899u);
        C1410f3 a2 = a.mo15854a();
        this.f1891m = a2;
        a2.mo15787a(this.f1898t);
    }

    /* access modifiers changed from: private */
    /* renamed from: i */
    public void m2776i() {
        if (this.f1896r) {
            this.f1896r = false;
            return;
        }
        TappxInterstitialListener tappxInterstitialListener = this.f1893o;
        if (tappxInterstitialListener != null) {
            tappxInterstitialListener.onInterstitialLoaded(this.f1890l);
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: j */
    public void m2777j() {
        this.f1891m.mo15792e();
        m2775h();
    }

    /* access modifiers changed from: private */
    /* renamed from: k */
    public void m2778k() {
        if (mo15844e()) {
            this.f1891m.mo15792e();
            if (this.f1895q != null) {
                TappxInterstitialListener tappxInterstitialListener = this.f1893o;
                if (tappxInterstitialListener != null) {
                    tappxInterstitialListener.onInterstitialShown(this.f1890l);
                }
                this.f1895q.mo15547g();
                this.f1895q = null;
            }
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: g */
    public void m2772g() {
        C1361d1 d1Var = this.f1895q;
        if (d1Var != null) {
            d1Var.mo15667b();
            this.f1895q = null;
        }
    }

    /* renamed from: h */
    private void m2775h() {
        this.f1896r = true;
        mo15781c();
    }

    /* access modifiers changed from: protected */
    /* renamed from: d */
    public void mo15783d() {
        super.mo15783d();
        this.f1892n.mo16031a();
    }

    /* renamed from: e */
    public boolean mo15844e() {
        return this.f1895q != null;
    }

    /* renamed from: f */
    public void mo15845f() {
        if (this.f1824g.mo15990e()) {
            this.f1824g.mo15981a(this.f1890l.getContext(), (Runnable) null);
            this.f1824g.mo15982a((Runnable) new C1436c());
            return;
        }
        m2778k();
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public void mo15778b(C1673w1 w1Var) {
        this.f1892n.mo16032a(mo15776b(), w1Var);
    }

    /* access modifiers changed from: private */
    /* renamed from: b */
    public void m2767b(TappxAdError tappxAdError) {
        if (this.f1896r) {
            this.f1896r = false;
            return;
        }
        TappxInterstitialListener tappxInterstitialListener = this.f1893o;
        if (tappxInterstitialListener != null) {
            tappxInterstitialListener.onInterstitialLoadFailed(this.f1890l, tappxAdError);
        }
    }

    /* renamed from: a */
    public void mo15842a(TappxInterstitialListener tappxInterstitialListener) {
        this.f1893o = tappxInterstitialListener;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo15774a(TappxAdError tappxAdError) {
        if (this.f1896r) {
            this.f1896r = false;
            return;
        }
        TappxInterstitialListener tappxInterstitialListener = this.f1893o;
        if (tappxInterstitialListener != null) {
            tappxInterstitialListener.onInterstitialLoadFailed(this.f1890l, tappxAdError);
        }
    }

    /* renamed from: com.tappx.a.h2$b */
    class C1435b implements C1388e1.C1389a {
        C1435b() {
        }

        /* renamed from: a */
        public void mo15736a(C1640u1 u1Var, C1361d1 d1Var) {
            C1433h2 h2Var = C1433h2.this;
            if (!h2Var.f1828k) {
                C1640u1 unused = h2Var.f1894p = u1Var;
                C1433h2.this.m2772g();
                C1361d1 unused2 = C1433h2.this.f1895q = d1Var;
                C1433h2.this.m2764a(u1Var);
                boolean z = C1433h2.this.f1897s && !C1433h2.this.f1896r;
                C1433h2.this.m2776i();
                if (z) {
                    C1433h2.this.mo15845f();
                }
            }
        }

        /* renamed from: b */
        public void mo15737b(C1640u1 u1Var) {
            if (C1433h2.this.f1893o != null) {
                C1433h2.this.f1893o.onInterstitialDismissed(C1433h2.this.f1890l);
            }
        }

        /* renamed from: a */
        public void mo15734a(C1313a2 a2Var) {
            C1433h2 h2Var = C1433h2.this;
            if (!h2Var.f1828k) {
                C1433h2.this.m2767b(h2Var.mo15777b(a2Var));
            }
        }

        /* renamed from: a */
        public void mo15735a(C1640u1 u1Var) {
            if (C1433h2.this.f1893o != null) {
                C1433h2.this.f1893o.onInterstitialClicked(C1433h2.this.f1890l);
            }
        }
    }

    /* renamed from: a */
    public void mo15773a(AdRequest adRequest) {
        m2772g();
        this.f1896r = false;
        super.mo15773a(adRequest);
    }

    /* renamed from: a */
    public void mo15770a() {
        super.mo15770a();
        mo15842a((TappxInterstitialListener) null);
        m2772g();
        this.f1892n.destroy();
        this.f1891m.mo15792e();
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public void m2764a(C1640u1 u1Var) {
        long c = u1Var.mo16207c() - System.currentTimeMillis();
        if (c > 0) {
            this.f1891m.mo15786a(c);
            this.f1891m.mo15791d();
            return;
        }
        this.f1891m.mo15792e();
    }

    /* renamed from: a */
    public void mo15843a(boolean z) {
        this.f1897s = z;
    }
}

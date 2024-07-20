package com.tappx.p048a;

import android.view.View;
import android.view.animation.Animation;
import android.widget.FrameLayout;
import com.integralads.avid.library.mopub.utils.AvidJSONUtil;
import com.tappx.p048a.C1410f3;
import com.tappx.p048a.C1531n0;
import com.tappx.sdk.android.AdRequest;
import com.tappx.sdk.android.TappxAdError;
import com.tappx.sdk.android.TappxBanner;
import com.tappx.sdk.android.TappxBannerListener;

/* renamed from: com.tappx.a.g2 */
public class C1422g2 extends C1406f2 {
    /* access modifiers changed from: private */

    /* renamed from: l */
    public final TappxBanner f1864l;

    /* renamed from: m */
    private final C1531n0 f1865m;
    /* access modifiers changed from: private */

    /* renamed from: n */
    public final C1410f3 f1866n;

    /* renamed from: o */
    private View f1867o;
    /* access modifiers changed from: private */

    /* renamed from: p */
    public TappxBannerListener f1868p;

    /* renamed from: q */
    private boolean f1869q;
    /* access modifiers changed from: private */

    /* renamed from: r */
    public boolean f1870r;
    /* access modifiers changed from: private */

    /* renamed from: s */
    public C1640u1 f1871s;

    /* renamed from: t */
    private boolean f1872t;

    /* renamed from: u */
    private int f1873u;

    /* renamed from: v */
    private final C1531n0.C1532a f1874v = new C1423a();

    /* renamed from: w */
    private final C1410f3.C1412b f1875w = new C1424b();

    /* renamed from: com.tappx.a.g2$b */
    class C1424b implements C1410f3.C1412b {
        C1424b() {
        }

        /* renamed from: a */
        public void mo15794a() {
            C1422g2.this.m2723j();
        }
    }

    C1422g2(TappxBanner tappxBanner) {
        super(tappxBanner.getContext(), C1654v1.f2373b);
        this.f1864l = tappxBanner;
        C1321b a = C1321b.m2239a(tappxBanner.getContext());
        C1531n0 b = a.mo15536b();
        this.f1865m = b;
        b.mo15974a(this.f1874v);
        C1410f3 a2 = a.mo15535a();
        this.f1866n = a2;
        a2.mo15787a(this.f1875w);
    }

    /* renamed from: h */
    private View m2721h() {
        if (this.f1867o == null) {
            this.f1867o = C1460i3.m2853b(this.f1864l.getContext());
        }
        return this.f1867o;
    }

    /* access modifiers changed from: private */
    /* renamed from: i */
    public void m2722i() {
        TappxBannerListener tappxBannerListener = this.f1868p;
        if (tappxBannerListener != null) {
            tappxBannerListener.onBannerLoaded(this.f1864l);
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: j */
    public void m2723j() {
        if (!this.f1828k && this.f1872t) {
            C1467j0.m2869a(C1400f.m2603b("WYP3IlFsQbao/nmzk+V5+EDTMrEq8ygXRWqwiT3aXVk"), new Object[0]);
            C1467j0.m2873d("fhrgFfJqgVZoVNjzyS7CzU1i9AA4GyPqlAJ20RCAJlg", new Object[0]);
            mo15781c();
        }
    }

    /* renamed from: c */
    private void m2713c(C1673w1 w1Var) {
        if (!this.f1869q) {
            m2711c(w1Var.mo16240c());
        }
    }

    /* renamed from: e */
    private void m2715e() {
        if (this.f1824g.mo15995j()) {
            this.f1864l.addView(m2721h(), new FrameLayout.LayoutParams(-2, -2, 83));
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: f */
    public void m2717f() {
        if (this.f1872t) {
            this.f1866n.mo15791d();
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: g */
    public void m2720g() {
        C1640u1 u1Var;
        boolean z = this.f1873u >= 50;
        if (!this.f1870r && (u1Var = this.f1871s) != null && this.f1872t && z) {
            this.f1870r = true;
            mo15782c(u1Var.mo16213g());
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: d */
    public void mo15783d() {
        super.mo15783d();
        this.f1865m.mo16031a();
    }

    /* renamed from: b */
    private String m2708b(TappxBanner.AdSize adSize) {
        if (adSize == TappxBanner.AdSize.SMART_BANNER) {
            return null;
        }
        return adSize.getWidth() + AvidJSONUtil.KEY_X + adSize.getHeight();
    }

    /* renamed from: c */
    private void m2711c(int i) {
        if (i == 0) {
            C1467j0.m2873d("Rfk0iXqG1NksAriLhvTIFrKC3X10rpfR3hyZYQqfkTdNYvQAOBsj6pQCdtEQgCZY", new Object[0]);
            this.f1866n.mo15792e();
            this.f1866n.mo15788a(false);
            return;
        }
        this.f1866n.mo15788a(true);
        if (i > 0) {
            this.f1866n.mo15786a((long) i);
        } else {
            this.f1866n.mo15789b();
        }
    }

    /* renamed from: a */
    public void mo15770a() {
        super.mo15770a();
        mo15823a((TappxBannerListener) null);
        this.f1864l.removeAllViews();
        this.f1865m.destroy();
        this.f1866n.mo15792e();
        this.f1871s = null;
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public void mo15778b(C1673w1 w1Var) {
        m2713c(w1Var);
        this.f1865m.mo16032a(this.f1864l.getContext(), w1Var);
    }

    /* renamed from: com.tappx.a.g2$a */
    class C1423a implements C1531n0.C1532a {
        C1423a() {
        }

        /* renamed from: a */
        public void mo15829a(C1640u1 u1Var, View view) {
            C1422g2 g2Var = C1422g2.this;
            if (!g2Var.f1828k) {
                boolean unused = g2Var.f1870r = false;
                C1640u1 unused2 = C1422g2.this.f1871s = u1Var;
                C1422g2.this.m2702a(view);
                C1422g2.this.m2717f();
                C1422g2.this.m2722i();
                C1422g2.this.m2705a(u1Var);
                C1422g2.this.m2720g();
            }
        }

        /* renamed from: b */
        public void mo15830b(C1640u1 u1Var) {
            C1422g2.this.f1866n.mo15785a();
            if (C1422g2.this.f1868p != null) {
                C1422g2.this.f1868p.onBannerExpanded(C1422g2.this.f1864l);
            }
        }

        /* renamed from: c */
        public void mo15831c(C1640u1 u1Var) {
            C1422g2.this.f1866n.mo15790c();
            if (C1422g2.this.f1868p != null) {
                C1422g2.this.f1868p.onBannerCollapsed(C1422g2.this.f1864l);
            }
        }

        /* renamed from: a */
        public void mo15827a(C1313a2 a2Var) {
            C1422g2 g2Var = C1422g2.this;
            if (!g2Var.f1828k) {
                C1422g2.this.m2710b(g2Var.mo15777b(a2Var));
                C1422g2.this.m2717f();
            }
        }

        /* renamed from: a */
        public void mo15828a(C1640u1 u1Var) {
            if (C1422g2.this.f1868p != null) {
                C1422g2.this.f1868p.onBannerClicked(C1422g2.this.f1864l);
            }
            C1422g2.this.mo15782c(u1Var.mo16211e());
        }
    }

    /* renamed from: com.tappx.a.g2$c */
    public static class C1425c {

        /* renamed from: a */
        private static volatile C1425c f1878a;

        /* renamed from: a */
        public static C1425c m2741a() {
            C1425c cVar = f1878a;
            if (cVar == null) {
                synchronized (C1425c.class) {
                    cVar = f1878a;
                    if (cVar == null) {
                        cVar = new C1425c();
                    }
                }
            }
            return cVar;
        }

        /* renamed from: a */
        public C1422g2 mo15832a(TappxBanner tappxBanner) {
            return new C1422g2(tappxBanner);
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: b */
    public void m2710b(TappxAdError tappxAdError) {
        TappxBannerListener tappxBannerListener = this.f1868p;
        if (tappxBannerListener != null) {
            tappxBannerListener.onBannerLoadFailed(this.f1864l, tappxAdError);
        }
    }

    /* renamed from: b */
    public void mo15825b(int i) {
        this.f1869q = i > 0;
        m2711c(i);
    }

    /* renamed from: a */
    public void mo15823a(TappxBannerListener tappxBannerListener) {
        this.f1868p = tappxBannerListener;
    }

    /* renamed from: b */
    public void mo15826b(boolean z) {
        C1467j0.m2873d("r+UiUzt9REOhqndIQXQTv4xLHJ5RqFQyDLMKVsbc2y8", String.valueOf(z));
        this.f1872t = z;
        if (z) {
            m2720g();
            this.f1866n.mo15790c();
            return;
        }
        this.f1866n.mo15785a();
    }

    /* renamed from: a */
    public void mo15822a(TappxBanner.AdSize adSize) {
        if (adSize == null) {
            adSize = TappxBanner.AdSize.SMART_BANNER;
        }
        mo15775a(m2708b(adSize));
    }

    /* renamed from: a */
    public void mo15773a(AdRequest adRequest) {
        if (this.f1824g.mo15990e()) {
            this.f1824g.mo15981a(this.f1864l.getContext(), (Runnable) null);
        }
        super.mo15773a(adRequest);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo15774a(TappxAdError tappxAdError) {
        TappxBannerListener tappxBannerListener = this.f1868p;
        if (tappxBannerListener != null) {
            tappxBannerListener.onBannerLoadFailed(this.f1864l, tappxAdError);
        }
        m2717f();
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public void m2702a(View view) {
        this.f1864l.removeAllViews();
        this.f1864l.addView(view, 0);
        m2715e();
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public void m2705a(C1640u1 u1Var) {
        Animation a = C1525m3.m3034a(C1326b3.m2279a(u1Var.mo16200a()));
        if (a != null) {
            this.f1864l.startAnimation(a);
        }
    }

    /* renamed from: a */
    public void mo15824a(boolean z) {
        this.f1866n.mo15788a(z);
    }

    /* renamed from: a */
    public void mo15821a(int i) {
        this.f1873u = i;
        m2720g();
    }
}

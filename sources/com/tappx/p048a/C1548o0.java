package com.tappx.p048a;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import com.tappx.p048a.C1517m0;
import com.tappx.p048a.C1531n0;
import java.lang.ref.WeakReference;
import java.util.List;

/* renamed from: com.tappx.a.o0 */
public class C1548o0 extends C1565p1 implements C1531n0 {
    /* access modifiers changed from: private */

    /* renamed from: d */
    public C1531n0.C1532a f2137d;

    /* renamed from: e */
    private final List<C1517m0.C1519b> f2138e;
    /* access modifiers changed from: private */

    /* renamed from: f */
    public C1517m0 f2139f;
    /* access modifiers changed from: private */

    /* renamed from: g */
    public C1517m0 f2140g;
    /* access modifiers changed from: private */

    /* renamed from: h */
    public C1550b f2141h;
    /* access modifiers changed from: private */

    /* renamed from: i */
    public C1550b f2142i;

    /* renamed from: com.tappx.a.o0$b */
    private final class C1550b implements C1517m0.C1520c {

        /* renamed from: a */
        private final C1517m0 f2143a;

        /* renamed from: b */
        private WeakReference<View> f2144b;

        /* renamed from: h */
        private boolean m3152h() {
            return this != C1548o0.this.f2142i;
        }

        /* renamed from: i */
        private boolean m3153i() {
            return this != C1548o0.this.f2141h;
        }

        /* renamed from: a */
        public void mo15959a(View view) {
            if (!m3153i()) {
                C1548o0.this.mo16050e();
                C1517m0 unused = C1548o0.this.f2139f = null;
                C1550b unused2 = C1548o0.this.f2141h = null;
                C1548o0.this.mo15766b();
                this.f2144b = new WeakReference<>(view);
                C1550b unused3 = C1548o0.this.f2142i = this;
                C1517m0 unused4 = C1548o0.this.f2140g = mo16029f();
                C1548o0.this.f2137d.mo15829a(mo16028e(), view);
            }
        }

        /* renamed from: b */
        public void mo15961b() {
            if (!m3152h()) {
                C1548o0.this.f2137d.mo15831c(mo16028e());
            }
        }

        /* renamed from: c */
        public void mo15962c() {
            if (!m3152h()) {
                C1548o0.this.f2137d.mo15830b(mo16028e());
            }
        }

        /* renamed from: d */
        public void mo15963d() {
            if (!m3152h()) {
                C1548o0.this.f2137d.mo15828a(mo16028e());
            }
        }

        /* renamed from: e */
        public C1640u1 mo16028e() {
            return this.f2143a.mo15955c();
        }

        /* renamed from: f */
        public C1517m0 mo16029f() {
            return this.f2143a;
        }

        /* access modifiers changed from: protected */
        /* renamed from: g */
        public View mo16030g() {
            WeakReference<View> weakReference = this.f2144b;
            if (weakReference != null) {
                return (View) weakReference.get();
            }
            return null;
        }

        private C1550b(C1517m0 m0Var) {
            this.f2143a = m0Var;
        }

        /* renamed from: a */
        public void mo15960a(C1313a2 a2Var) {
            if (!m3153i()) {
                C1548o0.this.mo16049d();
            }
        }

        /* renamed from: a */
        public void mo15958a() {
            boolean h = m3152h();
        }
    }

    public C1548o0(List<C1517m0.C1519b> list) {
        this.f2138e = list;
    }

    /* access modifiers changed from: protected */
    /* renamed from: c */
    public void mo15767c() {
        C1517m0 m0Var = this.f2139f;
        if (m0Var != null) {
            m0Var.mo15954b();
            this.f2139f = null;
            this.f2141h = null;
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public void mo15766b() {
        if (this.f2140g != null) {
            View g = this.f2142i.mo16030g();
            if (g != null) {
                m3142a(g);
            }
            this.f2140g.mo15954b();
            this.f2140g = null;
            this.f2142i = null;
        }
    }

    /* renamed from: a */
    public void mo15974a(C1531n0.C1532a aVar) {
        this.f2137d = aVar;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public boolean mo15765a(Context context, C1640u1 u1Var) {
        for (C1517m0.C1519b next : this.f2138e) {
            if (next.mo15902a(u1Var)) {
                C1517m0 a = next.mo15901a();
                this.f2139f = a;
                C1550b a2 = m3139a(a);
                this.f2141h = a2;
                this.f2139f.mo15952a(context, a2, u1Var);
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo15764a(C1313a2 a2Var) {
        C1531n0.C1532a aVar = this.f2137d;
        if (aVar != null) {
            aVar.mo15827a(a2Var);
        }
    }

    /* renamed from: a */
    private void m3142a(View view) {
        ViewParent parent = view.getParent();
        if (parent instanceof ViewGroup) {
            ((ViewGroup) parent).removeView(view);
        }
    }

    /* renamed from: a */
    private C1550b m3139a(C1517m0 m0Var) {
        return new C1550b(m0Var);
    }
}

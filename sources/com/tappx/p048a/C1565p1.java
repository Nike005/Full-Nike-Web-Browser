package com.tappx.p048a;

import android.content.Context;

/* renamed from: com.tappx.a.p1 */
abstract class C1565p1 implements C1551o1 {

    /* renamed from: a */
    private int f2179a = 0;

    /* renamed from: b */
    private C1673w1 f2180b;

    /* renamed from: c */
    private Context f2181c;

    C1565p1() {
    }

    /* renamed from: f */
    private void m3199f() {
        mo15767c();
        if (this.f2180b.mo16239b().size() <= this.f2179a) {
            mo15764a(C1313a2.NO_FILL);
            return;
        }
        C1640u1 u1Var = this.f2180b.mo16239b().get(this.f2179a);
        this.f2179a++;
        C1467j0.m2869a(C1400f.m2603b("mo5jy7IL/t1GLb3J/P8gjQ") + u1Var.mo16205b(), new Object[0]);
        C1467j0.m2873d("w73w5GD8aw/6JbEwVsPDUQ", u1Var.mo16205b());
        if (!mo15765a(this.f2181c, u1Var)) {
            m3199f();
        }
    }

    /* renamed from: a */
    public void mo16032a(Context context, C1673w1 w1Var) {
        m3198a(w1Var);
        this.f2181c = context;
        this.f2180b = w1Var;
        this.f2179a = 0;
        m3199f();
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public abstract void mo15764a(C1313a2 a2Var);

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public abstract boolean mo15765a(Context context, C1640u1 u1Var);

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public abstract void mo15766b();

    /* access modifiers changed from: protected */
    /* renamed from: c */
    public abstract void mo15767c();

    /* access modifiers changed from: protected */
    /* renamed from: d */
    public void mo16049d() {
        m3199f();
    }

    public void destroy() {
        mo15767c();
        mo15766b();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: e */
    public void mo16050e() {
    }

    /* renamed from: a */
    private void m3198a(C1673w1 w1Var) {
        String str = "{";
        for (C1640u1 u1Var : w1Var.mo16239b()) {
            str = str + u1Var.getClass().getSimpleName() + ",";
        }
        C1467j0.m2873d("vowRFCKLTs9aEktGgLPt1r38zreZaMrbDBiCU39LXJU", str + "}");
    }

    /* renamed from: a */
    public void mo16031a() {
        mo15767c();
    }
}

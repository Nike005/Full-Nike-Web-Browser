package com.tappx.p048a;

import android.content.Context;
import com.tappx.p048a.C1517m0;

/* renamed from: com.tappx.a.z0 */
public final class C1707z0 extends C1517m0<C1699y1> {

    /* renamed from: f */
    private C1311a1 f2576f;

    /* renamed from: com.tappx.a.z0$a */
    public static final class C1708a implements C1517m0.C1519b<C1699y1> {

        /* renamed from: a */
        private final C1624t2 f2577a;

        public C1708a(C1624t2 t2Var) {
            this.f2577a = t2Var;
        }

        /* renamed from: a */
        public C1517m0<C1699y1> mo15901a() {
            return new C1707z0(this.f2577a);
        }

        /* renamed from: a */
        public boolean mo15902a(C1640u1 u1Var) {
            return u1Var instanceof C1699y1;
        }
    }

    C1707z0(C1624t2 t2Var) {
        super(t2Var);
    }

    /* access modifiers changed from: protected */
    /* renamed from: e */
    public void mo15898e() {
        C1311a1 a1Var = this.f2576f;
        if (a1Var != null) {
            a1Var.mo15519a();
        }
        this.f2576f = null;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo15897b(Context context, C1517m0.C1520c cVar, C1699y1 y1Var) {
        mo15956d().mo16176a(y1Var.mo16212f());
        C1311a1 a1Var = new C1311a1(context);
        this.f2576f = a1Var;
        a1Var.mo15520a(y1Var, cVar);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public long mo15894a(C1699y1 y1Var) {
        int k = y1Var.mo16283k();
        if (k > 0) {
            return (long) k;
        }
        return super.mo15894a(y1Var);
    }
}

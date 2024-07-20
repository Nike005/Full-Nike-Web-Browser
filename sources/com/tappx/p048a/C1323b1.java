package com.tappx.p048a;

import android.content.Context;
import com.tappx.p048a.C1361d1;

/* renamed from: com.tappx.a.b1 */
public class C1323b1 extends C1361d1<C1699y1> {

    /* renamed from: f */
    private C1338c1 f1620f;

    /* renamed from: com.tappx.a.b1$a */
    public static class C1324a implements C1361d1.C1362a<C1699y1> {

        /* renamed from: a */
        private final C1624t2 f1621a;

        public C1324a(C1624t2 t2Var) {
            this.f1621a = t2Var;
        }

        /* renamed from: a */
        public C1361d1<C1699y1> mo15548a() {
            return new C1323b1(this.f1621a);
        }

        /* renamed from: a */
        public boolean mo15549a(C1640u1 u1Var) {
            return u1Var instanceof C1699y1;
        }
    }

    C1323b1(C1624t2 t2Var) {
        super(t2Var);
    }

    /* access modifiers changed from: protected */
    /* renamed from: e */
    public void mo15546e() {
        C1338c1 c1Var = this.f1620f;
        if (c1Var != null) {
            c1Var.mo15593a();
        }
    }

    /* renamed from: g */
    public void mo15547g() {
        C1338c1 c1Var = this.f1620f;
        if (c1Var != null) {
            c1Var.mo15595b();
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo15545b(Context context, C1361d1.C1363b bVar, C1699y1 y1Var) {
        mo15669d().mo16176a(y1Var.mo16212f());
        C1338c1 c1Var = new C1338c1(context);
        this.f1620f = c1Var;
        c1Var.mo15594a(y1Var, bVar, this);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public long mo15542a(C1699y1 y1Var) {
        int k = y1Var.mo16283k();
        if (k > 0) {
            return (long) k;
        }
        return (long) C1529n.f2089d;
    }
}

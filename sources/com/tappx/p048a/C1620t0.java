package com.tappx.p048a;

import android.content.Context;
import com.tappx.p048a.C1517m0;
import com.tappx.p048a.C1582q0;

/* renamed from: com.tappx.a.t0 */
public class C1620t0 extends C1517m0<C1685x1> {
    /* access modifiers changed from: private */

    /* renamed from: f */
    public C1685x1 f2303f;

    /* renamed from: g */
    private C1582q0 f2304g;

    /* renamed from: com.tappx.a.t0$a */
    class C1621a implements Runnable {
        C1621a() {
        }

        public void run() {
            C1620t0.this.mo15956d().mo16176a(C1620t0.this.f2303f.mo16212f());
        }
    }

    /* renamed from: com.tappx.a.t0$b */
    public static final class C1622b implements C1517m0.C1519b<C1685x1> {

        /* renamed from: a */
        private final C1624t2 f2306a;

        public C1622b(C1624t2 t2Var) {
            this.f2306a = t2Var;
        }

        /* renamed from: a */
        public C1517m0<C1685x1> mo15901a() {
            return new C1620t0(this.f2306a);
        }

        /* renamed from: a */
        public boolean mo15902a(C1640u1 u1Var) {
            return u1Var instanceof C1685x1;
        }
    }

    C1620t0(C1624t2 t2Var) {
        super(t2Var);
    }

    /* access modifiers changed from: protected */
    /* renamed from: e */
    public void mo15898e() {
        C1582q0 q0Var = this.f2304g;
        if (q0Var != null) {
            C1676w4.m3552b(q0Var.mo16069a());
            try {
                this.f2304g.mo16070a((C1517m0.C1520c) null, (Runnable) null);
                this.f2304g.destroy();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo15897b(Context context, C1517m0.C1520c cVar, C1685x1 x1Var) {
        C1467j0.m2873d("7qjY7245E0dfSy30XptPQ/SJdTfZfiiWf+eZ42wqMQY", new Object[0]);
        this.f2303f = x1Var;
        String h = x1Var.mo16259h();
        int k = x1Var.mo16262k();
        int i = x1Var.mo16260i();
        try {
            C1582q0 a = C1582q0.C1583a.m3248a(context);
            this.f2304g = a;
            a.mo16071a(h, k, i);
            this.f2304g.mo16070a(cVar, new C1621a());
            this.f2304g.loadAd();
        } catch (Exception | NoClassDefFoundError e) {
            e.printStackTrace();
            cVar.mo15960a(C1313a2.NO_FILL);
        }
    }
}

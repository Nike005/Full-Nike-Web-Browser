package com.tappx.p048a;

import android.content.Context;
import com.tappx.p048a.C1361d1;
import com.tappx.p048a.C1652v0;

/* renamed from: com.tappx.a.u0 */
public class C1637u0 extends C1361d1<C1685x1> {
    /* access modifiers changed from: private */

    /* renamed from: f */
    public C1685x1 f2344f;

    /* renamed from: g */
    private C1652v0 f2345g;

    /* renamed from: com.tappx.a.u0$a */
    class C1638a implements Runnable {
        C1638a() {
        }

        public void run() {
            C1637u0.this.mo15669d().mo16176a(C1637u0.this.f2344f.mo16212f());
        }
    }

    /* renamed from: com.tappx.a.u0$b */
    public static class C1639b implements C1361d1.C1362a<C1685x1> {

        /* renamed from: a */
        private final C1624t2 f2347a;

        public C1639b(C1624t2 t2Var) {
            this.f2347a = t2Var;
        }

        /* renamed from: a */
        public C1361d1<C1685x1> mo15548a() {
            return new C1637u0(this.f2347a);
        }

        /* renamed from: a */
        public boolean mo15549a(C1640u1 u1Var) {
            return u1Var instanceof C1685x1;
        }
    }

    C1637u0(C1624t2 t2Var) {
        super(t2Var);
    }

    /* access modifiers changed from: protected */
    /* renamed from: e */
    public void mo15546e() {
        C1652v0 v0Var = this.f2345g;
        if (v0Var != null) {
            try {
                v0Var.destroy();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /* renamed from: g */
    public void mo15547g() {
        C1652v0 v0Var = this.f2345g;
        if (v0Var != null) {
            try {
                v0Var.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo15545b(Context context, C1361d1.C1363b bVar, C1685x1 x1Var) {
        String h = x1Var.mo16259h();
        this.f2344f = x1Var;
        try {
            C1467j0.m2873d("EecDzDUbtS5qsctGaW8eDzBBqoEJJw2EaiO9g7mmMkc", new Object[0]);
            C1652v0 a = C1652v0.C1653a.m3493a(context);
            this.f2345g = a;
            a.mo16224a(h);
            this.f2345g.mo16223a(bVar, this, new C1638a());
            this.f2345g.loadAd();
        } catch (Exception | NoClassDefFoundError e) {
            e.printStackTrace();
            bVar.mo15672a(C1313a2.NO_FILL);
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public long mo15542a(C1685x1 x1Var) {
        long j = (long) x1Var.mo16261j();
        if (j > 0) {
            return j;
        }
        return super.mo15542a(x1Var);
    }
}

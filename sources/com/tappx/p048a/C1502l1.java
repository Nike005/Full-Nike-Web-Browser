package com.tappx.p048a;

import android.content.Context;
import com.tappx.p048a.C1361d1;
import com.tappx.p048a.C1649v;

/* renamed from: com.tappx.a.l1 */
public class C1502l1 extends C1361d1<C1709z1> {
    /* access modifiers changed from: private */

    /* renamed from: f */
    public C1361d1.C1363b f2030f;

    /* renamed from: g */
    private final C1649v f2031g;

    /* renamed from: h */
    private C1338c1 f2032h;
    /* access modifiers changed from: private */

    /* renamed from: i */
    public C1649v.C1651b f2033i;

    /* renamed from: com.tappx.a.l1$a */
    class C1503a implements C1516m<C1699y1> {
        C1503a() {
        }

        /* renamed from: a */
        public void mo15866a(C1699y1 y1Var) {
            C1649v.C1651b unused = C1502l1.this.f2033i = null;
            C1502l1.this.m2956a(y1Var);
            C1467j0.m2873d("hxsTS1PgJe7SvMvbIVXAleqYGWt1TgQOogRt9pTwP9Y", new Object[0]);
        }
    }

    /* renamed from: com.tappx.a.l1$b */
    class C1504b implements C1430h<C1649v.C1650a> {
        C1504b() {
        }

        /* renamed from: a */
        public void mo15840a(C1649v.C1650a aVar) {
            C1467j0.m2873d("hxsTS1PgJe7SvMvbIVXAlWNuK93hkAa0eyf9OlSh3dE", new Object[0]);
            C1649v.C1651b unused = C1502l1.this.f2033i = null;
            if (C1502l1.this.f2030f != null) {
                C1502l1.this.f2030f.mo15672a(C1313a2.NO_FILL);
            }
        }
    }

    /* renamed from: com.tappx.a.l1$c */
    public static class C1505c implements C1361d1.C1362a<C1709z1> {

        /* renamed from: a */
        private final C1624t2 f2036a;

        /* renamed from: b */
        private final C1649v f2037b;

        public C1505c(C1624t2 t2Var, C1649v vVar) {
            this.f2036a = t2Var;
            this.f2037b = vVar;
        }

        /* renamed from: a */
        public C1361d1<C1709z1> mo15548a() {
            return new C1502l1(this.f2036a, this.f2037b);
        }

        /* renamed from: a */
        public boolean mo15549a(C1640u1 u1Var) {
            return u1Var instanceof C1709z1;
        }
    }

    C1502l1(C1624t2 t2Var, C1649v vVar) {
        super(t2Var);
        this.f2031g = vVar;
    }

    /* access modifiers changed from: protected */
    /* renamed from: e */
    public void mo15546e() {
        C1338c1 c1Var = this.f2032h;
        if (c1Var != null) {
            c1Var.mo15593a();
        }
        C1649v.C1651b bVar = this.f2033i;
        if (bVar != null) {
            this.f2031g.mo16222a(bVar);
        }
        this.f2032h = null;
    }

    /* renamed from: g */
    public void mo15547g() {
        C1338c1 c1Var = this.f2032h;
        if (c1Var != null) {
            c1Var.mo15595b();
        }
    }

    /* renamed from: b */
    private void m2957b(C1699y1 y1Var) {
        int i = C1529n.f2089d;
        if (y1Var.mo16283k() > 0) {
            i = y1Var.mo16283k();
        }
        mo15664a((long) i);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo15545b(Context context, C1361d1.C1363b bVar, C1709z1 z1Var) {
        C1467j0.m2873d("EecDzDUbtS5qsctGaW8eD9qka7saamJrDJfaB/3470s", new Object[0]);
        this.f2030f = bVar;
        this.f2032h = new C1338c1(context);
        this.f2033i = this.f2031g.mo16221a(z1Var, (C1516m<C1699y1>) new C1503a(), (C1430h<C1649v.C1650a>) new C1504b());
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public void m2956a(C1699y1 y1Var) {
        m2957b(y1Var);
        mo15669d().mo16176a(y1Var.mo16212f());
        this.f2032h.mo15594a(y1Var, this.f2030f, this);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public long mo15542a(C1709z1 z1Var) {
        if (z1Var.mo16301i() > 0) {
            return (long) z1Var.mo16301i();
        }
        return super.mo15542a(z1Var);
    }
}

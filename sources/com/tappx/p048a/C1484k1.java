package com.tappx.p048a;

import android.content.Context;
import com.tappx.p048a.C1517m0;
import com.tappx.p048a.C1649v;

/* renamed from: com.tappx.a.k1 */
public class C1484k1 extends C1517m0<C1709z1> {

    /* renamed from: f */
    private final C1649v f1992f;

    /* renamed from: g */
    private C1311a1 f1993g;
    /* access modifiers changed from: private */

    /* renamed from: h */
    public C1517m0.C1520c f1994h;

    /* renamed from: i */
    private C1649v.C1651b f1995i;

    /* renamed from: com.tappx.a.k1$a */
    class C1485a implements C1516m<C1699y1> {
        C1485a() {
        }

        /* renamed from: a */
        public void mo15866a(C1699y1 y1Var) {
            C1484k1.this.m2907b(y1Var);
            C1484k1.this.m2905a(y1Var);
            C1467j0.m2873d("hxsTS1PgJe7SvMvbIVXAleqYGWt1TgQOogRt9pTwP9Y", new Object[0]);
        }
    }

    /* renamed from: com.tappx.a.k1$b */
    class C1486b implements C1430h<C1649v.C1650a> {
        C1486b() {
        }

        /* renamed from: a */
        public void mo15840a(C1649v.C1650a aVar) {
            C1467j0.m2873d("wA68d1p5v8MSlvKrjle67r38zreZaMrbDBiCU39LXJU", aVar);
            C1484k1.this.f1994h.mo15960a(C1313a2.NO_FILL);
        }
    }

    /* renamed from: com.tappx.a.k1$c */
    public static final class C1487c implements C1517m0.C1519b<C1709z1> {

        /* renamed from: a */
        private final C1624t2 f1998a;

        /* renamed from: b */
        private final C1649v f1999b;

        public C1487c(C1624t2 t2Var, C1649v vVar) {
            this.f1998a = t2Var;
            this.f1999b = vVar;
        }

        /* renamed from: a */
        public C1517m0<C1709z1> mo15901a() {
            return new C1484k1(this.f1998a, this.f1999b);
        }

        /* renamed from: a */
        public boolean mo15902a(C1640u1 u1Var) {
            return u1Var instanceof C1709z1;
        }
    }

    C1484k1(C1624t2 t2Var, C1649v vVar) {
        super(t2Var);
        this.f1992f = vVar;
    }

    /* access modifiers changed from: protected */
    /* renamed from: e */
    public void mo15898e() {
        C1649v.C1651b bVar = this.f1995i;
        if (bVar != null) {
            this.f1992f.mo16222a(bVar);
        }
        C1311a1 a1Var = this.f1993g;
        if (a1Var != null) {
            a1Var.mo15519a();
        }
        this.f1993g = null;
    }

    /* access modifiers changed from: private */
    /* renamed from: b */
    public void m2907b(C1699y1 y1Var) {
        int i = C1529n.f2089d;
        if (y1Var.mo16283k() > 0) {
            i = y1Var.mo16283k();
        }
        mo15951a((long) i);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo15897b(Context context, C1517m0.C1520c cVar, C1709z1 z1Var) {
        C1467j0.m2873d("7qjY7245E0dfSy30XptPQ6Kjsb63PLX1qtOqZ64iM50", new Object[0]);
        this.f1994h = cVar;
        this.f1993g = new C1311a1(context);
        this.f1995i = this.f1992f.mo16221a(z1Var, (C1516m<C1699y1>) new C1485a(), (C1430h<C1649v.C1650a>) new C1486b());
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public void m2905a(C1699y1 y1Var) {
        mo15956d().mo16176a(y1Var.mo16212f());
        this.f1993g.mo15520a(y1Var, this.f1994h);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public long mo15894a(C1709z1 z1Var) {
        if (z1Var.mo16301i() > 0) {
            return (long) z1Var.mo16301i();
        }
        return super.mo15894a(z1Var);
    }
}

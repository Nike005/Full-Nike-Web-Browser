package com.tappx.p048a;

import android.content.Context;
import com.tappx.p048a.C1354c5;
import com.tappx.p048a.C1361d1;

/* renamed from: com.tappx.a.c1 */
public class C1338c1 {

    /* renamed from: a */
    private final Context f1653a;
    /* access modifiers changed from: private */

    /* renamed from: b */
    public C1361d1.C1363b f1654b;

    /* renamed from: c */
    private C1382d5 f1655c;
    /* access modifiers changed from: private */

    /* renamed from: d */
    public C1361d1 f1656d;

    /* renamed from: e */
    private C1354c5.C1355a f1657e = new C1339a();

    /* renamed from: com.tappx.a.c1$a */
    class C1339a implements C1354c5.C1355a {
        C1339a() {
        }

        /* renamed from: a */
        public void mo15596a() {
            C1338c1.this.f1654b.mo15672a(C1313a2.INTERNAL_ERROR);
        }

        /* renamed from: b */
        public void mo15597b() {
            C1338c1.this.f1654b.mo15674b();
        }

        /* renamed from: c */
        public void mo15598c() {
            C1338c1.this.f1654b.mo15675c();
        }

        /* renamed from: d */
        public void mo15599d() {
            C1338c1.this.f1654b.mo15676d();
        }

        /* renamed from: e */
        public void mo15600e() {
            C1338c1.this.f1654b.mo15673a(C1338c1.this.f1656d);
        }
    }

    public C1338c1(Context context) {
        this.f1653a = context;
    }

    /* renamed from: a */
    public void mo15594a(C1699y1 y1Var, C1361d1.C1363b bVar, C1361d1 d1Var) {
        this.f1654b = bVar;
        this.f1656d = d1Var;
        C1382d5 d5Var = new C1382d5();
        this.f1655c = d5Var;
        d5Var.mo15722a(this.f1657e);
        this.f1655c.mo15721a(this.f1653a, y1Var.mo16280h(), new C1414f5().mo15795a(y1Var.mo16281i()).mo15805c(y1Var.mo16287o()).mo15804c(y1Var.mo16284l()).mo15800b(y1Var.mo16282j()).mo15801b(y1Var.mo16286n()).mo15798a(y1Var.mo16285m()).mo15796a(m2333a(y1Var.mo16209d())).mo15797a(C1326b3.m2279a(y1Var.mo16200a())));
    }

    /* renamed from: b */
    public void mo15595b() {
        C1382d5 d5Var = this.f1655c;
        if (d5Var != null) {
            d5Var.mo15723b();
        }
    }

    /* renamed from: a */
    private C1474j3 m2333a(int i) {
        if (i == 1) {
            return C1474j3.PORTRAIT;
        }
        if (i != 2) {
            return C1474j3.ANY;
        }
        return C1474j3.LANDSCAPE;
    }

    /* renamed from: a */
    public void mo15593a() {
        C1382d5 d5Var = this.f1655c;
        if (d5Var != null) {
            d5Var.mo15720a();
        }
    }
}

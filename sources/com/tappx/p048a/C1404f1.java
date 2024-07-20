package com.tappx.p048a;

import android.content.Context;
import com.tappx.p048a.C1361d1;
import com.tappx.p048a.C1388e1;
import java.util.List;

/* renamed from: com.tappx.a.f1 */
public class C1404f1 extends C1565p1 implements C1388e1 {
    /* access modifiers changed from: private */

    /* renamed from: d */
    public C1388e1.C1389a f1810d;

    /* renamed from: e */
    private final List<C1361d1.C1362a> f1811e;
    /* access modifiers changed from: private */

    /* renamed from: f */
    public C1361d1 f1812f;
    /* access modifiers changed from: private */

    /* renamed from: g */
    public C1361d1 f1813g;
    /* access modifiers changed from: private */

    /* renamed from: h */
    public C1405a f1814h;
    /* access modifiers changed from: private */

    /* renamed from: i */
    public C1405a f1815i;

    public C1404f1(List<C1361d1.C1362a> list) {
        this.f1811e = list;
    }

    /* access modifiers changed from: protected */
    /* renamed from: c */
    public void mo15767c() {
        C1361d1 d1Var = this.f1812f;
        if (d1Var != null) {
            d1Var.mo15667b();
            this.f1812f = null;
            this.f1814h = null;
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public void mo15766b() {
        C1361d1 d1Var = this.f1813g;
        if (d1Var != null) {
            d1Var.mo15667b();
            this.f1813g = null;
            this.f1815i = null;
        }
    }

    /* renamed from: a */
    public void mo15733a(C1388e1.C1389a aVar) {
        this.f1810d = aVar;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public boolean mo15765a(Context context, C1640u1 u1Var) {
        for (C1361d1.C1362a next : this.f1811e) {
            if (next.mo15549a(u1Var)) {
                C1361d1 a = next.mo15548a();
                this.f1812f = a;
                C1405a a2 = m2611a(a);
                this.f1814h = a2;
                this.f1812f.mo15665a(context, a2, u1Var);
                return true;
            }
        }
        return false;
    }

    /* renamed from: com.tappx.a.f1$a */
    private final class C1405a implements C1361d1.C1363b {

        /* renamed from: a */
        private final C1361d1 f1816a;

        public C1405a(C1361d1 d1Var) {
            this.f1816a = d1Var;
        }

        /* renamed from: g */
        private boolean m2623g() {
            return this != C1404f1.this.f1815i;
        }

        /* renamed from: h */
        private boolean m2624h() {
            return this != C1404f1.this.f1814h;
        }

        /* renamed from: a */
        public void mo15671a() {
        }

        /* renamed from: a */
        public void mo15673a(C1361d1 d1Var) {
            if (!m2624h()) {
                C1404f1.this.mo16050e();
                C1361d1 unused = C1404f1.this.f1812f = null;
                C1405a unused2 = C1404f1.this.f1814h = null;
                C1404f1.this.mo15766b();
                C1405a unused3 = C1404f1.this.f1815i = this;
                C1361d1 unused4 = C1404f1.this.f1813g = mo15769f();
                C1404f1.this.f1810d.mo15736a(mo15768e(), mo15769f());
            }
        }

        /* renamed from: b */
        public void mo15674b() {
        }

        /* renamed from: c */
        public void mo15675c() {
            C1404f1.this.f1810d.mo15735a(mo15768e());
        }

        /* renamed from: d */
        public void mo15676d() {
            if (!m2623g()) {
                C1404f1.this.f1810d.mo15737b(mo15768e());
            }
        }

        /* renamed from: e */
        public C1640u1 mo15768e() {
            return this.f1816a.mo15668c();
        }

        /* renamed from: f */
        public C1361d1 mo15769f() {
            return this.f1816a;
        }

        /* renamed from: a */
        public void mo15672a(C1313a2 a2Var) {
            if (!m2624h()) {
                C1404f1.this.mo16049d();
            }
        }
    }

    /* renamed from: a */
    private C1405a m2611a(C1361d1 d1Var) {
        return new C1405a(d1Var);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo15764a(C1313a2 a2Var) {
        C1388e1.C1389a aVar = this.f1810d;
        if (aVar != null) {
            aVar.mo15734a(a2Var);
        }
    }
}

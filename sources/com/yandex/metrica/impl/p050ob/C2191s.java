package com.yandex.metrica.impl.p050ob;

import android.text.TextUtils;
import com.yandex.metrica.CounterConfiguration;
import com.yandex.metrica.impl.C1807a;
import java.util.LinkedList;
import java.util.List;

/* renamed from: com.yandex.metrica.impl.ob.s */
public class C2191s {

    /* renamed from: a */
    private final C2198t f3779a;

    /* renamed from: b */
    private final C2058db f3780b;

    /* renamed from: c */
    private List<C2196e> f3781c;

    C2191s(C2198t tVar, C2058db dbVar) {
        this.f3779a = tVar;
        this.f3780b = dbVar;
        LinkedList linkedList = new LinkedList();
        this.f3781c = linkedList;
        linkedList.add(new C2193b(this.f3779a, this.f3780b));
        this.f3781c.add(new C2195d(this.f3779a, this.f3780b));
        this.f3781c.add(new C2194c(this.f3779a));
        this.f3781c.add(new C2192a(this.f3779a));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo17823a() {
        if (!C2058db.f3447a.values().contains(this.f3779a.mo17865l().mo17818a())) {
            for (C2196e d : this.f3781c) {
                d.mo17827d();
            }
        }
    }

    /* renamed from: com.yandex.metrica.impl.ob.s$a */
    static class C2192a extends C2196e {

        /* renamed from: a */
        private final C2063df f3782a;

        /* renamed from: b */
        private final C2016ca f3783b;

        C2192a(C2198t tVar) {
            super(tVar);
            this.f3782a = new C2063df(tVar.mo17866m(), tVar.mo17865l().toString());
            this.f3783b = tVar.mo17834F();
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public boolean mo17824a() {
            return this.f3782a.mo17581g();
        }

        /* access modifiers changed from: protected */
        /* renamed from: b */
        public void mo17825b() {
            long e = this.f3782a.mo17577e(-1);
            if (e != -1 && this.f3783b.mo17372f(-1) == -1) {
                this.f3783b.mo17382p(e);
            }
            long b = this.f3782a.mo17568b(Long.MIN_VALUE);
            if (b != Long.MIN_VALUE && this.f3783b.mo17359b(Long.MIN_VALUE) == Long.MIN_VALUE) {
                this.f3783b.mo17378l(b);
            }
            long g = this.f3782a.mo17580g(0);
            if (g != 0 && this.f3783b.mo17374h(0) == 0) {
                this.f3783b.mo17384r(g);
            }
            long i = this.f3782a.mo17583i(0);
            if (i != 0 && this.f3783b.mo17376j(0) == 0) {
                this.f3783b.mo17386t(i);
            }
            long d = this.f3782a.mo17575d(-1);
            if (-1 != d && this.f3783b.mo17371e(-1) == -1) {
                this.f3783b.mo17381o(d);
            }
            boolean booleanValue = this.f3782a.mo17565a(true).booleanValue();
            if (!this.f3783b.mo17358a(false) && booleanValue) {
                this.f3783b.mo17362b(booleanValue);
            }
            long a = this.f3782a.mo17562a(Long.MIN_VALUE);
            if (a != Long.MIN_VALUE && this.f3783b.mo17353a(Long.MIN_VALUE) == Long.MIN_VALUE) {
                this.f3783b.mo17377k(a);
            }
            long f = this.f3782a.mo17579f(0);
            if (f != 0 && this.f3783b.mo17373g(0) == 0) {
                this.f3783b.mo17383q(f);
            }
            long h = this.f3782a.mo17582h(0);
            if (h != 0 && this.f3783b.mo17375i(0) == 0) {
                this.f3783b.mo17385s(h);
            }
            C1807a.C1808a a2 = this.f3782a.mo17563a();
            if (a2 != null) {
                this.f3783b.mo17356a(a2);
            }
            String a3 = this.f3782a.mo17566a((String) null);
            if (!TextUtils.isEmpty(a3) && TextUtils.isEmpty(this.f3783b.mo17357a((String) null))) {
                this.f3783b.mo17361b(a3);
            }
            CounterConfiguration.C1774a b2 = this.f3782a.mo17569b();
            if (b2 != CounterConfiguration.C1774a.UNDEFINED && this.f3783b.mo17365c() == CounterConfiguration.C1774a.UNDEFINED) {
                this.f3783b.mo17355a(b2);
            }
            long c = this.f3782a.mo17572c(Long.MIN_VALUE);
            if (c != Long.MIN_VALUE && this.f3783b.mo17364c(Long.MIN_VALUE) == Long.MIN_VALUE) {
                this.f3783b.mo17379m(c);
            }
            this.f3783b.mo17398h();
            this.f3782a.mo17584l();
        }
    }

    /* renamed from: com.yandex.metrica.impl.ob.s$c */
    static class C2194c extends C2196e {

        /* renamed from: a */
        private final C2060dc f3784a;

        /* renamed from: b */
        private final C2013by f3785b;

        C2194c(C2198t tVar) {
            super(tVar);
            this.f3784a = tVar.mo17832D();
            this.f3785b = tVar.mo17831C();
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public boolean mo17824a() {
            return "DONE".equals(this.f3784a.mo17537c((String) null)) || "DONE".equals(this.f3784a.mo17535b((String) null));
        }

        /* access modifiers changed from: protected */
        /* renamed from: b */
        public void mo17825b() {
            if ("DONE".equals(this.f3784a.mo17537c((String) null))) {
                this.f3785b.mo17318b();
            }
            String e = this.f3784a.mo17541e((String) null);
            if (!TextUtils.isEmpty(e)) {
                this.f3785b.mo17319c(e);
            }
            if ("DONE".equals(this.f3784a.mo17535b((String) null))) {
                this.f3785b.mo17316a();
            }
            this.f3784a.mo17539d();
            this.f3784a.mo17542e();
            this.f3784a.mo17538c();
        }
    }

    /* renamed from: com.yandex.metrica.impl.ob.s$d */
    static class C2195d extends C2197f {
        C2195d(C2198t tVar, C2058db dbVar) {
            super(tVar, dbVar);
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public boolean mo17824a() {
            return mo17826c().mo17831C().mo17315a((String) null) == null;
        }

        /* access modifiers changed from: protected */
        /* renamed from: b */
        public void mo17825b() {
            CounterConfiguration j = mo17826c().mo17863j();
            C2058db e = mo17828e();
            if (j.mo16554C()) {
                e.mo17527c();
            } else {
                e.mo17525b();
            }
        }
    }

    /* renamed from: com.yandex.metrica.impl.ob.s$b */
    static class C2193b extends C2197f {
        C2193b(C2198t tVar, C2058db dbVar) {
            super(tVar, dbVar);
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public boolean mo17824a() {
            return mo17826c().mo17863j().mo16554C();
        }

        /* access modifiers changed from: protected */
        /* renamed from: b */
        public void mo17825b() {
            mo17828e().mo17523a();
        }
    }

    /* renamed from: com.yandex.metrica.impl.ob.s$f */
    private static abstract class C2197f extends C2196e {

        /* renamed from: a */
        private C2058db f3787a;

        C2197f(C2198t tVar, C2058db dbVar) {
            super(tVar);
            this.f3787a = dbVar;
        }

        /* renamed from: e */
        public C2058db mo17828e() {
            return this.f3787a;
        }
    }

    /* renamed from: com.yandex.metrica.impl.ob.s$e */
    private static abstract class C2196e {

        /* renamed from: a */
        private final C2198t f3786a;

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public abstract boolean mo17824a();

        /* access modifiers changed from: protected */
        /* renamed from: b */
        public abstract void mo17825b();

        C2196e(C2198t tVar) {
            this.f3786a = tVar;
        }

        /* access modifiers changed from: package-private */
        /* renamed from: c */
        public C2198t mo17826c() {
            return this.f3786a;
        }

        /* access modifiers changed from: package-private */
        /* renamed from: d */
        public void mo17827d() {
            if (mo17824a()) {
                mo17825b();
            }
        }
    }
}

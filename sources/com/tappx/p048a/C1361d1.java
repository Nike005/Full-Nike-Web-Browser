package com.tappx.p048a;

import android.content.Context;
import com.tappx.p048a.C1529n;
import com.tappx.p048a.C1533n1;
import com.tappx.p048a.C1640u1;

/* renamed from: com.tappx.a.d1 */
public abstract class C1361d1<T extends C1640u1> implements C1533n1.C1535b {

    /* renamed from: a */
    private final C1624t2 f1704a;

    /* renamed from: b */
    private final C1533n1 f1705b;

    /* renamed from: c */
    private T f1706c;

    /* renamed from: d */
    private boolean f1707d;

    /* renamed from: e */
    private C1361d1<T>.c f1708e;

    /* renamed from: com.tappx.a.d1$a */
    public interface C1362a<T extends C1640u1> {
        /* renamed from: a */
        C1361d1<T> mo15548a();

        /* renamed from: a */
        boolean mo15549a(C1640u1 u1Var);
    }

    /* renamed from: com.tappx.a.d1$b */
    public interface C1363b {
        /* renamed from: a */
        void mo15671a();

        /* renamed from: a */
        void mo15672a(C1313a2 a2Var);

        /* renamed from: a */
        void mo15673a(C1361d1 d1Var);

        /* renamed from: b */
        void mo15674b();

        /* renamed from: c */
        void mo15675c();

        /* renamed from: d */
        void mo15676d();
    }

    protected C1361d1(C1624t2 t2Var) {
        this(t2Var, new C1533n1());
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public long mo15542a(T t) {
        return 15000;
    }

    /* renamed from: a */
    public void mo15665a(Context context, C1363b bVar, C1640u1 u1Var) {
        try {
            this.f1706c = u1Var;
            mo15666a(false);
            this.f1708e = new C1364c(bVar);
            mo15670f();
            C1467j0.m2873d("w73w5GD8aw/6JbEwVsPDUQ", getClass().getSimpleName());
            mo15545b(context, this.f1708e, this.f1706c);
        } catch (ClassCastException unused) {
            bVar.mo15672a(C1313a2.INTERNAL_ERROR);
        }
    }

    /* renamed from: b */
    public void mo15667b() {
        this.f1705b.mo15975a();
        mo15546e();
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public abstract void mo15545b(Context context, C1363b bVar, T t);

    /* access modifiers changed from: package-private */
    /* renamed from: c */
    public T mo15668c() {
        return this.f1706c;
    }

    /* renamed from: d */
    public C1624t2 mo15669d() {
        return this.f1704a;
    }

    /* access modifiers changed from: protected */
    /* renamed from: e */
    public abstract void mo15546e();

    /* access modifiers changed from: package-private */
    /* renamed from: f */
    public void mo15670f() {
        mo15664a(mo15542a(this.f1706c));
    }

    /* renamed from: g */
    public abstract void mo15547g();

    /* renamed from: com.tappx.a.d1$c */
    private class C1364c implements C1363b {

        /* renamed from: a */
        private final C1363b f1709a;

        public C1364c(C1363b bVar) {
            this.f1709a = bVar;
        }

        /* renamed from: a */
        public void mo15673a(C1361d1 d1Var) {
            C1361d1.this.mo15666a(true);
            this.f1709a.mo15673a(d1Var);
        }

        /* renamed from: b */
        public void mo15674b() {
            C1361d1.this.mo15669d().mo16176a(C1361d1.this.mo15668c().mo16213g());
            this.f1709a.mo15674b();
        }

        /* renamed from: c */
        public void mo15675c() {
            C1361d1.this.mo15669d().mo16176a(C1361d1.this.mo15668c().mo16211e());
            this.f1709a.mo15675c();
        }

        /* renamed from: d */
        public void mo15676d() {
            this.f1709a.mo15676d();
        }

        /* renamed from: a */
        public void mo15672a(C1313a2 a2Var) {
            C1361d1.this.mo15666a(true);
            this.f1709a.mo15672a(a2Var);
        }

        /* renamed from: a */
        public void mo15671a() {
            this.f1709a.mo15671a();
        }
    }

    protected C1361d1(C1624t2 t2Var, C1533n1 n1Var) {
        this.f1704a = t2Var;
        this.f1705b = n1Var;
        n1Var.mo15977a((C1533n1.C1535b) this);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo15664a(long j) {
        if (j < C1529n.C1530a.f2092c) {
            C1467j0.m2873d("tJ/RDdwmde5sNRNl2OtVTfCw4OuAfiMIPu/zkSLGIT2zwNm3C7thqGQX04tsdosL", Long.valueOf(j));
            j = C1529n.C1530a.f2092c;
        } else if (j > C1529n.C1530a.f2093d) {
            C1467j0.m2873d("w73w5GD8aw/6JbEwVsPDUQ", Long.valueOf(j));
            j = C1529n.C1530a.f2093d;
        }
        this.f1705b.mo15976a(j);
    }

    /* renamed from: a */
    public void mo15666a(boolean z) {
        this.f1707d = z;
        if (z) {
            this.f1705b.mo15975a();
        }
    }

    /* renamed from: a */
    public void mo15663a() {
        if (!this.f1707d) {
            C1467j0.m2873d("wUWo9wuOBqc42QHm8/JVjGXXMTT2DoYHEa3wguYezUW0KEhBaolGwT3KPMo6Sz+d", new Object[0]);
            C1361d1<T>.c cVar = this.f1708e;
            if (cVar != null) {
                cVar.mo15672a(C1313a2.TIMEOUT);
            }
        }
    }
}

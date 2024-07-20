package com.tappx.p048a;

import android.content.Context;
import android.view.View;
import com.tappx.p048a.C1529n;
import com.tappx.p048a.C1533n1;
import com.tappx.p048a.C1640u1;

/* renamed from: com.tappx.a.m0 */
public abstract class C1517m0<T extends C1640u1> implements C1533n1.C1535b {

    /* renamed from: a */
    private final C1624t2 f2062a;

    /* renamed from: b */
    private final C1533n1 f2063b;

    /* renamed from: c */
    private C1517m0<T>.d f2064c;

    /* renamed from: d */
    private T f2065d;

    /* renamed from: e */
    private boolean f2066e;

    /* renamed from: com.tappx.a.m0$b */
    public interface C1519b<T extends C1640u1> {
        /* renamed from: a */
        C1517m0<T> mo15901a();

        /* renamed from: a */
        boolean mo15902a(C1640u1 u1Var);
    }

    /* renamed from: com.tappx.a.m0$c */
    public interface C1520c {
        /* renamed from: a */
        void mo15958a();

        /* renamed from: a */
        void mo15959a(View view);

        /* renamed from: a */
        void mo15960a(C1313a2 a2Var);

        /* renamed from: b */
        void mo15961b();

        /* renamed from: c */
        void mo15962c();

        /* renamed from: d */
        void mo15963d();
    }

    /* renamed from: com.tappx.a.m0$d */
    private class C1521d implements C1520c {

        /* renamed from: a */
        private final C1520c f2067a;

        /* renamed from: a */
        public void mo15959a(View view) {
            C1517m0.this.mo15953a(true);
            this.f2067a.mo15959a(view);
        }

        /* renamed from: b */
        public void mo15961b() {
            this.f2067a.mo15961b();
        }

        /* renamed from: c */
        public void mo15962c() {
            this.f2067a.mo15962c();
        }

        /* renamed from: d */
        public void mo15963d() {
            this.f2067a.mo15963d();
        }

        private C1521d(C1520c cVar) {
            this.f2067a = cVar;
        }

        /* renamed from: a */
        public void mo15960a(C1313a2 a2Var) {
            C1517m0.this.mo15953a(true);
            this.f2067a.mo15960a(a2Var);
        }

        /* renamed from: a */
        public void mo15958a() {
            this.f2067a.mo15958a();
        }
    }

    protected C1517m0(C1624t2 t2Var, C1533n1 n1Var) {
        this.f2062a = t2Var;
        this.f2063b = n1Var;
        n1Var.mo15977a((C1533n1.C1535b) this);
    }

    /* renamed from: a */
    public void mo15952a(Context context, C1520c cVar, C1640u1 u1Var) {
        try {
            this.f2065d = u1Var;
            mo15953a(false);
            this.f2064c = new C1521d(cVar);
            mo15957f();
            C1467j0.m2873d("w73w5GD8aw/6JbEwVsPDUQ", getClass().getSimpleName());
            mo15897b(context, this.f2064c, this.f2065d);
        } catch (ClassCastException unused) {
            cVar.mo15960a(C1313a2.INTERNAL_ERROR);
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public void mo15954b() {
        this.f2063b.mo15975a();
        mo15898e();
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public abstract void mo15897b(Context context, C1520c cVar, T t);

    /* access modifiers changed from: package-private */
    /* renamed from: c */
    public T mo15955c() {
        return this.f2065d;
    }

    /* renamed from: d */
    public C1624t2 mo15956d() {
        return this.f2062a;
    }

    /* access modifiers changed from: protected */
    /* renamed from: e */
    public abstract void mo15898e();

    /* access modifiers changed from: package-private */
    /* renamed from: f */
    public void mo15957f() {
        mo15951a(mo15894a(this.f2065d));
    }

    public C1517m0(C1624t2 t2Var) {
        this(t2Var, new C1533n1());
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo15951a(long j) {
        if (j < C1529n.C1530a.f2090a) {
            C1467j0.m2873d("/K518OsQwGWEySte999XTohBdjGQhBkPInQIUsTjG/cUwA8AiN+9hbf5gwErXHcf", Long.valueOf(j));
            j = C1529n.C1530a.f2090a;
        } else if (j > C1529n.C1530a.f2091b) {
            C1467j0.m2873d("wUWo9wuOBqc42QHm8/JVjGXXMTT2DoYHEa3wguYezUW0KEhBaolGwT3KPMo6Sz+d", Long.valueOf(j));
            j = C1529n.C1530a.f2091b;
        }
        this.f2063b.mo15976a(j);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public long mo15894a(T t) {
        return C1529n.f2086a;
    }

    /* renamed from: a */
    public void mo15663a() {
        if (!this.f2066e) {
            C1467j0.m2873d("tJ/RDdwmde5sNRNl2OtVTfCw4OuAfiMIPu/zkSLGIT2zwNm3C7thqGQX04tsdosL", new Object[0]);
            C1517m0<T>.d dVar = this.f2064c;
            if (dVar != null) {
                dVar.mo15960a(C1313a2.TIMEOUT);
            }
        }
    }

    /* renamed from: a */
    public void mo15953a(boolean z) {
        this.f2066e = z;
        if (z) {
            this.f2063b.mo15975a();
        }
    }
}

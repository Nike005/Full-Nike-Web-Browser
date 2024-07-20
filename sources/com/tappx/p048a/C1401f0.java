package com.tappx.p048a;

/* renamed from: com.tappx.a.f0 */
public class C1401f0<T> {

    /* renamed from: a */
    public final T f1808a;

    /* renamed from: b */
    public final C1309a0 f1809b;

    /* renamed from: com.tappx.a.f0$a */
    public interface C1402a {
        /* renamed from: a */
        void mo15762a(C1309a0 a0Var);
    }

    /* renamed from: com.tappx.a.f0$b */
    public interface C1403b<T> {
        /* renamed from: a */
        void mo15763a(T t);
    }

    private C1401f0(T t) {
        this.f1808a = t;
        this.f1809b = null;
    }

    /* renamed from: a */
    public static <T> C1401f0<T> m2606a(T t) {
        return new C1401f0<>(t);
    }

    /* renamed from: a */
    public static <T> C1401f0<T> m2605a(C1309a0 a0Var) {
        return new C1401f0<>(a0Var);
    }

    /* renamed from: a */
    public boolean mo15761a() {
        return this.f1809b == null;
    }

    private C1401f0(C1309a0 a0Var) {
        this.f1808a = null;
        this.f1809b = a0Var;
    }
}

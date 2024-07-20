package com.tappx.p048a;

import com.tappx.p048a.C1445h5;

/* renamed from: com.tappx.a.u5 */
public class C1647u5<T> {

    /* renamed from: a */
    public final T f2364a;

    /* renamed from: b */
    public final C1445h5.C1446a f2365b;

    /* renamed from: c */
    public final C1718z5 f2366c;

    /* renamed from: d */
    public boolean f2367d;

    /* renamed from: com.tappx.a.u5$a */
    public interface C1648a {
        /* renamed from: a */
        void mo15863a(C1718z5 z5Var);
    }

    private C1647u5(T t, C1445h5.C1446a aVar) {
        this.f2367d = false;
        this.f2364a = t;
        this.f2365b = aVar;
        this.f2366c = null;
    }

    /* renamed from: a */
    public static <T> C1647u5<T> m3483a(T t, C1445h5.C1446a aVar) {
        return new C1647u5<>(t, aVar);
    }

    /* renamed from: a */
    public static <T> C1647u5<T> m3482a(C1718z5 z5Var) {
        return new C1647u5<>(z5Var);
    }

    /* renamed from: a */
    public boolean mo16217a() {
        return this.f2366c == null;
    }

    private C1647u5(C1718z5 z5Var) {
        this.f2367d = false;
        this.f2364a = null;
        this.f2365b = null;
        this.f2366c = z5Var;
    }
}

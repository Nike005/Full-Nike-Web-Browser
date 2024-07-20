package com.tappx.p048a;

/* renamed from: com.tappx.a.l */
public class C1498l<E> {

    /* renamed from: a */
    private volatile E f2023a;

    /* renamed from: b */
    private final C1499a<E> f2024b;

    /* renamed from: com.tappx.a.l$a */
    public interface C1499a<E> {
        /* renamed from: a */
        E mo15591a();
    }

    public C1498l(C1499a<E> aVar) {
        this.f2024b = aVar;
    }

    /* renamed from: a */
    public E mo15926a() {
        E e = this.f2023a;
        if (e == null) {
            synchronized (this) {
                e = this.f2023a;
                if (e == null) {
                    this.f2023a = this.f2024b.mo15591a();
                    e = this.f2023a;
                }
            }
        }
        return e;
    }
}

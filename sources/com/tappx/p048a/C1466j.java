package com.tappx.p048a;

/* renamed from: com.tappx.a.j */
public class C1466j<T> implements C1357d<T> {

    /* renamed from: a */
    private final long f1959a;

    /* renamed from: b */
    private T f1960b;

    /* renamed from: c */
    private long f1961c;

    public C1466j(long j) {
        this.f1959a = j < 0 ? 0 : j;
    }

    /* renamed from: b */
    private long m2865b() {
        return C1385e.m2545b();
    }

    /* renamed from: a */
    public T mo15646a() {
        T t;
        long j;
        synchronized (this) {
            t = this.f1960b;
            j = this.f1961c;
        }
        if (t == null || j == 0 || Math.abs(m2865b() - j) > this.f1959a) {
            return null;
        }
        return t;
    }

    /* renamed from: a */
    public void mo15647a(T t) {
        synchronized (this) {
            this.f1960b = t;
            this.f1961c = m2865b();
        }
    }
}

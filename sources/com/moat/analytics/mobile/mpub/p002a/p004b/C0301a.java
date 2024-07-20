package com.moat.analytics.mobile.mpub.p002a.p004b;

import java.util.NoSuchElementException;

/* renamed from: com.moat.analytics.mobile.mpub.a.b.a */
public final class C0301a<T> {

    /* renamed from: a */
    private static final C0301a<?> f58a = new C0301a<>();

    /* renamed from: b */
    private final T f59b;

    private C0301a() {
        this.f59b = null;
    }

    private C0301a(T t) {
        if (t != null) {
            this.f59b = t;
            return;
        }
        throw new NullPointerException("Optional of null value.");
    }

    /* renamed from: a */
    public static <T> C0301a<T> m80a() {
        return f58a;
    }

    /* renamed from: a */
    public static <T> C0301a<T> m81a(T t) {
        return new C0301a<>(t);
    }

    /* renamed from: b */
    public static <T> C0301a<T> m82b(T t) {
        return t == null ? m80a() : m81a(t);
    }

    /* renamed from: b */
    public T mo10374b() {
        T t = this.f59b;
        if (t != null) {
            return t;
        }
        throw new NoSuchElementException("No value present");
    }

    /* renamed from: c */
    public T mo10375c(T t) {
        T t2 = this.f59b;
        return t2 != null ? t2 : t;
    }

    /* renamed from: c */
    public boolean mo10376c() {
        return this.f59b != null;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C0301a)) {
            return false;
        }
        T t = this.f59b;
        T t2 = ((C0301a) obj).f59b;
        if (t != t2) {
            return (t == null || t2 == null || !t.equals(t2)) ? false : true;
        }
        return true;
    }

    public int hashCode() {
        T t = this.f59b;
        if (t == null) {
            return 0;
        }
        return t.hashCode();
    }

    public String toString() {
        T t = this.f59b;
        if (t == null) {
            return "Optional.empty";
        }
        return String.format("Optional[%s]", new Object[]{t});
    }
}

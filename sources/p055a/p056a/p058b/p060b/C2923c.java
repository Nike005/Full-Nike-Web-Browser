package p055a.p056a.p058b.p060b;

import p055a.p056a.p058b.C2917a;
import p055a.p056a.p062d.C2944b;

/* renamed from: a.a.b.b.c */
/* compiled from: StartAppSDK */
public final class C2923c implements C2922b, C2944b<Object> {

    /* renamed from: a */
    private final Class<?> f4020a;

    public C2923c(Class<?> cls) {
        C2928h.m6157b(cls, "jClass");
        this.f4020a = cls;
    }

    /* renamed from: a */
    public Class<?> mo24966a() {
        return this.f4020a;
    }

    public boolean equals(Object obj) {
        return (obj instanceof C2923c) && C2928h.m6156a((Object) C2917a.m6136a(this), (Object) C2917a.m6136a((C2944b) obj));
    }

    public int hashCode() {
        return C2917a.m6136a(this).hashCode();
    }

    public String toString() {
        return mo24966a().toString() + " (Kotlin reflection is not available)";
    }
}

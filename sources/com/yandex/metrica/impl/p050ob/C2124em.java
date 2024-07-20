package com.yandex.metrica.impl.p050ob;

import java.io.IOException;

/* renamed from: com.yandex.metrica.impl.ob.em */
class C2124em implements C2138ey {

    /* renamed from: a */
    private final C2143fb f3649a;

    /* renamed from: b */
    private final C2143fb f3650b;

    public C2124em(C2146fe feVar, String str) throws IOException {
        C2125en enVar = new C2125en(feVar.mo17749b(), Integer.toString(str.hashCode()));
        this.f3649a = new C2143fb(enVar, "LIB-BLACK");
        this.f3650b = new C2143fb(enVar, "LIB-TRUST");
    }

    /* renamed from: a */
    public C2143fb mo17711a() {
        return this.f3649a;
    }

    /* renamed from: b */
    public C2143fb mo17712b() {
        throw new UnsupportedOperationException("white list isn't supported in custom container");
    }

    /* renamed from: c */
    public C2143fb mo17713c() {
        return this.f3650b;
    }
}

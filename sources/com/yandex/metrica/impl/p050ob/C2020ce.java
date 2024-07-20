package com.yandex.metrica.impl.p050ob;

import com.yandex.metrica.impl.C1887be;

/* renamed from: com.yandex.metrica.impl.ob.ce */
class C2020ce {

    /* renamed from: a */
    private final C2025ch f3394a;

    /* renamed from: b */
    private final C1887be.C1888a f3395b;

    /* renamed from: com.yandex.metrica.impl.ob.ce$a */
    enum C2021a {
        THIS,
        OTHER,
        UNKNOWN
    }

    C2020ce(C1887be.C1888a aVar, C2025ch chVar) {
        this.f3394a = chVar;
        this.f3395b = aVar;
    }

    /* renamed from: a */
    public String mo17452a() {
        return this.f3394a.mo17467c();
    }

    /* renamed from: a */
    public C2021a mo17451a(C2030cj cjVar) {
        return C2021a.THIS;
    }

    /* renamed from: b */
    public C2025ch mo17453b() {
        return this.f3394a;
    }

    public String toString() {
        return "Bid{mCredentials='" + this.f3394a + '\'' + ", mDescriptor=" + this.f3395b + '}';
    }

    /* renamed from: c */
    public C1887be.C1888a mo17454c() {
        return this.f3395b;
    }
}

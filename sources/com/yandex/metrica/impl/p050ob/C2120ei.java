package com.yandex.metrica.impl.p050ob;

import java.security.cert.X509Certificate;

/* renamed from: com.yandex.metrica.impl.ob.ei */
class C2120ei implements C2129er {

    /* renamed from: a */
    private final C2122ek f3644a;

    /* renamed from: b */
    private final C2122ek f3645b;

    /* renamed from: c */
    private final C2122ek f3646c;

    C2120ei(C2138ey eyVar) {
        this.f3644a = new C2122ek(eyVar.mo17711a());
        this.f3645b = new C2126eo(eyVar.mo17712b());
        this.f3646c = new C2122ek(eyVar.mo17713c());
    }

    /* renamed from: a */
    public boolean mo17708a(X509Certificate[] x509CertificateArr) {
        return this.f3644a.mo17714a(x509CertificateArr);
    }

    /* renamed from: b */
    public boolean mo17709b(X509Certificate[] x509CertificateArr) {
        return this.f3645b.mo17714a(x509CertificateArr);
    }

    /* renamed from: c */
    public boolean mo17710c(X509Certificate[] x509CertificateArr) {
        return this.f3646c.mo17714a(x509CertificateArr);
    }
}
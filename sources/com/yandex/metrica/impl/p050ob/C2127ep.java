package com.yandex.metrica.impl.p050ob;

import java.security.cert.X509Certificate;

/* renamed from: com.yandex.metrica.impl.ob.ep */
class C2127ep implements C2129er {

    /* renamed from: a */
    private final C2122ek f3655a;

    /* renamed from: b */
    private final C2122ek f3656b;

    /* renamed from: b */
    public boolean mo17709b(X509Certificate[] x509CertificateArr) {
        return false;
    }

    C2127ep(C2138ey eyVar) {
        this.f3655a = new C2122ek(eyVar.mo17711a());
        this.f3656b = new C2122ek(eyVar.mo17713c());
    }

    /* renamed from: a */
    public boolean mo17708a(X509Certificate[] x509CertificateArr) {
        return this.f3655a.mo17714a(x509CertificateArr);
    }

    /* renamed from: c */
    public boolean mo17710c(X509Certificate[] x509CertificateArr) {
        return this.f3656b.mo17714a(x509CertificateArr);
    }
}

package com.yandex.metrica.impl.p050ob;

import java.security.cert.X509Certificate;
import java.util.Set;

/* renamed from: com.yandex.metrica.impl.ob.ek */
class C2122ek {

    /* renamed from: a */
    private C2143fb f3648a;

    public C2122ek(C2143fb fbVar) {
        this.f3648a = fbVar;
    }

    /* renamed from: a */
    public boolean mo17714a(X509Certificate[] x509CertificateArr) {
        Set<String> b = this.f3648a.mo17735b();
        if (b.isEmpty()) {
            return false;
        }
        for (X509Certificate a : x509CertificateArr) {
            if (b.contains(C2148fg.m5678a(a))) {
                return true;
            }
        }
        return false;
    }
}

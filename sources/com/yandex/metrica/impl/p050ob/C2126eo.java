package com.yandex.metrica.impl.p050ob;

import java.security.cert.X509Certificate;
import java.util.Set;

/* renamed from: com.yandex.metrica.impl.ob.eo */
public class C2126eo extends C2122ek {

    /* renamed from: a */
    private C2143fb f3654a;

    public C2126eo(C2143fb fbVar) {
        super(fbVar);
        this.f3654a = fbVar;
    }

    /* renamed from: a */
    public boolean mo17714a(X509Certificate[] x509CertificateArr) {
        Set<String> b = this.f3654a.mo17735b();
        if (b.isEmpty()) {
            return false;
        }
        return b.contains(C2148fg.m5678a(x509CertificateArr[0]));
    }
}

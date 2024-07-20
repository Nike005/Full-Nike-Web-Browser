package com.yandex.metrica.impl.p050ob;

import java.security.cert.X509Certificate;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: com.yandex.metrica.impl.ob.ff */
public class C2147ff {

    /* renamed from: a */
    private static final AtomicInteger f3702a = new AtomicInteger(0);

    /* renamed from: b */
    private X509Certificate[] f3703b;

    /* renamed from: c */
    private boolean f3704c = false;

    C2147ff(X509Certificate[] x509CertificateArr) {
        f3702a.incrementAndGet();
        this.f3703b = x509CertificateArr;
    }

    /* renamed from: a */
    public X509Certificate[] mo17752a() {
        return (X509Certificate[]) this.f3703b.clone();
    }

    /* renamed from: b */
    public boolean mo17753b() {
        return this.f3704c;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: c */
    public void mo17754c() {
        this.f3704c = true;
    }
}

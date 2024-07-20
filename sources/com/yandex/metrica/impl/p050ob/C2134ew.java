package com.yandex.metrica.impl.p050ob;

import android.content.Context;
import java.security.GeneralSecurityException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/* renamed from: com.yandex.metrica.impl.ob.ew */
public class C2134ew implements C2154fk {

    /* renamed from: a */
    private final C2135a f3672a;

    public C2134ew(Context context, C2145fd fdVar) {
        this(new C2146fe(context), fdVar);
    }

    C2134ew(C2146fe feVar, C2145fd fdVar) {
        if (fdVar.mo17744d() != null) {
            this.f3672a = new C2135a(feVar, fdVar);
            return;
        }
        throw new IllegalArgumentException("UUID provider must be set");
    }

    public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
        throw new UnsupportedOperationException();
    }

    public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
        if (x509CertificateArr == null || x509CertificateArr.length == 0 || str == null || str.length() == 0) {
            throw new IllegalArgumentException("null or zero-length parameter");
        } else if (!m5628b(mo17726a(x509CertificateArr))) {
            throw new CertificateException("Can't trust certificate chain");
        }
    }

    public X509Certificate[] getAcceptedIssuers() {
        return this.f3672a.m5639d().mo17739a();
    }

    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0020 */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0026 A[SYNTHETIC, Splitter:B:12:0x0026] */
    /* renamed from: c */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean m5629c(java.security.cert.X509Certificate[] r4) {
        /*
            r3 = this;
            com.yandex.metrica.impl.ob.ew$a r0 = r3.f3672a
            com.yandex.metrica.impl.ob.eq r0 = r0.m5633a()
            com.yandex.metrica.impl.ob.fh r0 = r0.mo17721d()
            if (r0 == 0) goto L_0x0036
            java.util.concurrent.locks.ReentrantLock r1 = r0.mo17755a()
            r1.lock()
            boolean r2 = r3.m5630d(r4)     // Catch:{ CertificateException -> 0x0020 }
            if (r2 == 0) goto L_0x0020
            r1.unlock()
            r4 = 1
            return r4
        L_0x001e:
            r4 = move-exception
            goto L_0x0032
        L_0x0020:
            boolean r0 = r0.mo17757b()     // Catch:{ all -> 0x001e }
            if (r0 == 0) goto L_0x002e
            boolean r4 = r3.m5630d(r4)     // Catch:{ CertificateException -> 0x002e }
            r1.unlock()
            return r4
        L_0x002e:
            r1.unlock()
            goto L_0x0036
        L_0x0032:
            r1.unlock()
            throw r4
        L_0x0036:
            r4 = 0
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.yandex.metrica.impl.p050ob.C2134ew.m5629c(java.security.cert.X509Certificate[]):boolean");
    }

    /* renamed from: e */
    private boolean m5631e(X509Certificate[] x509CertificateArr) throws CertificateException {
        for (C2129er c : this.f3672a.m5641e()) {
            if (c.mo17710c(x509CertificateArr)) {
                return true;
            }
        }
        C2132eu unused = this.f3672a.m5638c();
        throw new C2123el(new C2147ff(x509CertificateArr));
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v4, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v5, resolved type: java.security.cert.X509Certificate[]} */
    /* access modifiers changed from: package-private */
    /* JADX WARNING: Multi-variable type inference failed */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.security.cert.X509Certificate[] mo17726a(java.security.cert.X509Certificate[] r9) {
        /*
            r8 = this;
            r0 = 0
            r2 = r9
            r1 = 0
        L_0x0003:
            int r3 = r2.length
            if (r1 >= r3) goto L_0x0045
            int r3 = r1 + 1
            r4 = r3
        L_0x0009:
            int r5 = r2.length
            r6 = 1
            if (r4 >= r5) goto L_0x0036
            r5 = r2[r1]
            java.security.Principal r5 = r5.getIssuerDN()
            r7 = r2[r4]
            java.security.Principal r7 = r7.getSubjectDN()
            boolean r5 = r5.equals(r7)
            if (r5 == 0) goto L_0x0033
            if (r4 == r3) goto L_0x0037
            if (r2 != r9) goto L_0x002a
            java.lang.Object r1 = r9.clone()
            r2 = r1
            java.security.cert.X509Certificate[] r2 = (java.security.cert.X509Certificate[]) r2
        L_0x002a:
            r1 = r2[r4]
            r5 = r2[r3]
            r2[r4] = r5
            r2[r3] = r1
            goto L_0x0037
        L_0x0033:
            int r4 = r4 + 1
            goto L_0x0009
        L_0x0036:
            r6 = 0
        L_0x0037:
            if (r6 != 0) goto L_0x0043
            int r9 = r2.length
            if (r3 == r9) goto L_0x0045
            java.security.cert.X509Certificate[] r9 = new java.security.cert.X509Certificate[r3]
            java.lang.System.arraycopy(r2, r0, r9, r0, r3)
            r2 = r9
            goto L_0x0045
        L_0x0043:
            r1 = r3
            goto L_0x0003
        L_0x0045:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.yandex.metrica.impl.p050ob.C2134ew.mo17726a(java.security.cert.X509Certificate[]):java.security.cert.X509Certificate[]");
    }

    /* renamed from: com.yandex.metrica.impl.ob.ew$a */
    private static class C2135a {

        /* renamed from: a */
        private final C2146fe f3673a;

        /* renamed from: b */
        private final C2145fd f3674b;

        /* renamed from: c */
        private volatile C2129er[] f3675c;

        /* renamed from: d */
        private volatile C2144fc f3676d;

        /* renamed from: e */
        private volatile C2132eu f3677e;

        /* renamed from: f */
        private volatile C2121ej f3678f;

        /* renamed from: g */
        private volatile C2128eq f3679g;

        public C2135a(C2146fe feVar, C2145fd fdVar) {
            this.f3673a = feVar;
            this.f3674b = fdVar;
        }

        /* access modifiers changed from: private */
        /* renamed from: a */
        public C2128eq m5633a() {
            if (this.f3679g == null) {
                synchronized (this) {
                    if (this.f3679g == null) {
                        this.f3679g = new C2128eq(this.f3673a, this.f3674b);
                    }
                }
            }
            return this.f3679g;
        }

        /* renamed from: b */
        private C2121ej m5635b() {
            if (this.f3678f == null) {
                synchronized (this) {
                    if (this.f3678f == null) {
                        this.f3678f = new C2121ej();
                    }
                }
            }
            return this.f3678f;
        }

        /* access modifiers changed from: private */
        /* renamed from: c */
        public C2132eu m5638c() {
            if (this.f3677e == null) {
                synchronized (this) {
                    if (this.f3677e == null) {
                        this.f3677e = new C2132eu(m5635b().mo17712b());
                    }
                }
            }
            return this.f3677e;
        }

        /* access modifiers changed from: private */
        /* renamed from: d */
        public C2144fc m5639d() {
            if (this.f3676d == null) {
                synchronized (this) {
                    if (this.f3676d == null) {
                        try {
                            this.f3676d = new C2144fc();
                        } catch (GeneralSecurityException e) {
                            throw new IllegalStateException("Can't get system trust manager", e);
                        }
                    }
                }
            }
            return this.f3676d;
        }

        /* access modifiers changed from: private */
        /* renamed from: e */
        public C2129er[] m5641e() {
            if (this.f3675c == null) {
                synchronized (this) {
                    if (this.f3675c == null) {
                        C2127ep epVar = new C2127ep(m5633a());
                        this.f3675c = new C2129er[]{new C2120ei(m5635b()), epVar};
                    }
                }
            }
            return this.f3675c;
        }
    }

    /* renamed from: b */
    private boolean m5628b(X509Certificate[] x509CertificateArr) throws CertificateException {
        try {
            if (this.f3672a.m5639d().mo17738a(x509CertificateArr)) {
                boolean d = m5630d(x509CertificateArr);
                this.f3672a.m5633a().mo17722e();
                return d;
            }
            throw new CertificateException("System doesn't trust certificate chain");
        } catch (C2123el unused) {
            boolean c = m5629c(x509CertificateArr);
            if (c) {
                return c;
            }
            this.f3672a.m5638c().mo17725a(x509CertificateArr);
            return m5630d(x509CertificateArr);
        }
    }

    /* renamed from: d */
    private boolean m5630d(X509Certificate[] x509CertificateArr) throws CertificateException {
        boolean z;
        C2129er[] d = this.f3672a.m5641e();
        int length = d.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                z = false;
                break;
            } else if (d[i].mo17709b(x509CertificateArr)) {
                z = true;
                break;
            } else {
                i++;
            }
        }
        if (!z) {
            C2129er[] d2 = this.f3672a.m5641e();
            int length2 = d2.length;
            int i2 = 0;
            while (i2 < length2) {
                if (!d2[i2].mo17708a(x509CertificateArr)) {
                    i2++;
                } else {
                    throw new CertificateException("There is blacklisted certificate in chain");
                }
            }
            return m5631e(x509CertificateArr);
        }
    }
}

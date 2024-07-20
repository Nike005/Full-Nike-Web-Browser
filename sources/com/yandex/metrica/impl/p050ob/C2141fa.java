package com.yandex.metrica.impl.p050ob;

import java.io.IOException;

/* renamed from: com.yandex.metrica.impl.ob.fa */
class C2141fa {

    /* renamed from: a */
    private static C2149fh f3684a;

    /* renamed from: b */
    private static C2138ey f3685b;

    /* renamed from: c */
    private static C2164fs f3686c;

    /* renamed from: a */
    static synchronized C2149fh m5649a(C2146fe feVar) {
        C2149fh fhVar;
        synchronized (C2141fa.class) {
            if (f3684a == null) {
                f3684a = new C2149fh(feVar, m5650b(feVar), m5651c(feVar), new C2145fd());
            }
            fhVar = f3684a;
        }
        return fhVar;
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(4:5|6|7|8) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0010 */
    /* renamed from: b */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static synchronized com.yandex.metrica.impl.p050ob.C2138ey m5650b(com.yandex.metrica.impl.p050ob.C2146fe r3) {
        /*
            java.lang.Class<com.yandex.metrica.impl.ob.fa> r0 = com.yandex.metrica.impl.p050ob.C2141fa.class
            monitor-enter(r0)
            com.yandex.metrica.impl.ob.ey r1 = f3685b     // Catch:{ all -> 0x001b }
            if (r1 != 0) goto L_0x0017
            com.yandex.metrica.impl.ob.fa$a r1 = new com.yandex.metrica.impl.ob.fa$a     // Catch:{ IOException -> 0x0010 }
            r2 = 0
            r1.<init>(r3, r2)     // Catch:{ IOException -> 0x0010 }
            f3685b = r1     // Catch:{ IOException -> 0x0010 }
            goto L_0x0017
        L_0x0010:
            com.yandex.metrica.impl.ob.et r3 = new com.yandex.metrica.impl.ob.et     // Catch:{ all -> 0x001b }
            r3.<init>()     // Catch:{ all -> 0x001b }
            f3685b = r3     // Catch:{ all -> 0x001b }
        L_0x0017:
            com.yandex.metrica.impl.ob.ey r3 = f3685b     // Catch:{ all -> 0x001b }
            monitor-exit(r0)
            return r3
        L_0x001b:
            r3 = move-exception
            monitor-exit(r0)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.yandex.metrica.impl.p050ob.C2141fa.m5650b(com.yandex.metrica.impl.ob.fe):com.yandex.metrica.impl.ob.ey");
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(6:2|3|(2:5|6)|7|8|9) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x000d */
    /* renamed from: c */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static synchronized com.yandex.metrica.impl.p050ob.C2164fs m5651c(com.yandex.metrica.impl.p050ob.C2146fe r2) {
        /*
            java.lang.Class<com.yandex.metrica.impl.ob.fa> r0 = com.yandex.metrica.impl.p050ob.C2141fa.class
            monitor-enter(r0)
            com.yandex.metrica.impl.ob.fs r1 = f3686c     // Catch:{ all -> 0x0011 }
            if (r1 != 0) goto L_0x000d
            com.yandex.metrica.impl.ob.fs r2 = r2.mo17751d()     // Catch:{ IOException | GeneralSecurityException -> 0x000d }
            f3686c = r2     // Catch:{ IOException | GeneralSecurityException -> 0x000d }
        L_0x000d:
            com.yandex.metrica.impl.ob.fs r2 = f3686c     // Catch:{ all -> 0x0011 }
            monitor-exit(r0)
            return r2
        L_0x0011:
            r2 = move-exception
            monitor-exit(r0)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.yandex.metrica.impl.p050ob.C2141fa.m5651c(com.yandex.metrica.impl.ob.fe):com.yandex.metrica.impl.ob.fs");
    }

    /* renamed from: com.yandex.metrica.impl.ob.fa$a */
    private static class C2142a implements C2138ey {

        /* renamed from: a */
        private static final String[] f3687a = {"LNFe+yc4/NZbJVynpxAeAd+brU3EPwGbtwF6VeUjI/Y=", "PL1/TTDEe9Cm2lb2X0tixyQC7zaPREm/V0IHJscTCmw=", "+B0DgmKB5hWEuHib00m2jvCJWBlOYI0NGTMmVjaVrJA=", "dy/Myn0WRtYGKBNP8ubn9boJWJi+WWmLzp0V+W9pqfM=", "OB84k4abNNzWpMVBdhI+TSgQmCqTKdPPQrwq6j4YdMU=", "AZQG1XXPKFo8LYu/gTPgz65IOcmcwYFb3yREhyWefNI=", "iZEDYF5LpvyxpOX9+x3+qDBXhdByZOUFatBA3JgW7sY=", "IQBnNBEiFuhj+8x6X8XLgh01V9Ic5/V3IRQLNFFc7v4=", "LvRiGEjRqfzurezaWuj8Wie2gyHMrW5Q06LspMnox7A="};

        /* renamed from: b */
        private final C2143fb f3688b;

        /* renamed from: c */
        private final C2143fb f3689c;

        /* synthetic */ C2142a(C2146fe feVar, byte b) throws IOException {
            this(feVar);
        }

        private C2142a(C2146fe feVar) throws IOException {
            C2125en enVar = new C2125en(feVar.mo17749b(), "lib");
            this.f3688b = new C2143fb(enVar, "LIB-BLACK");
            this.f3689c = new C2143fb(enVar, "LIB-TRUST", f3687a);
        }

        /* renamed from: a */
        public C2143fb mo17711a() {
            return this.f3688b;
        }

        /* renamed from: b */
        public C2143fb mo17712b() {
            throw new UnsupportedOperationException("white list isn't supported in shared container");
        }

        /* renamed from: c */
        public C2143fb mo17713c() {
            return this.f3689c;
        }
    }
}

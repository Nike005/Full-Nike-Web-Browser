package com.yandex.metrica.impl.p050ob;

import java.io.IOException;
import java.security.GeneralSecurityException;
import org.apache.commons.lang3.time.DateUtils;

/* renamed from: com.yandex.metrica.impl.ob.eq */
class C2128eq implements C2138ey {

    /* renamed from: a */
    private final C2145fd f3657a;

    /* renamed from: b */
    private C2138ey f3658b;

    /* renamed from: c */
    private C2149fh f3659c;

    C2128eq(C2146fe feVar, C2145fd fdVar) {
        C2164fs fsVar;
        this.f3657a = fdVar;
        if (fdVar.mo17745e()) {
            boolean a = C2148fg.m5679a(fdVar);
            boolean f = fdVar.mo17746f();
            this.f3658b = new C2131et();
            if (f) {
                if (a) {
                    fsVar = m5600a(feVar, fdVar);
                } else {
                    fsVar = C2141fa.m5651c(feVar);
                }
                this.f3659c = new C2149fh(feVar, this.f3658b, fsVar, fdVar);
            }
        } else {
            boolean a2 = C2148fg.m5679a(fdVar);
            boolean z = true;
            if (!(DateUtils.MILLIS_PER_DAY != fdVar.mo17740a()) && !a2) {
                z = false;
            }
            boolean f2 = fdVar.mo17746f();
            if (a2) {
                try {
                    this.f3658b = new C2124em(feVar, fdVar.mo17742b());
                } catch (IOException unused) {
                    this.f3658b = new C2131et();
                }
            } else {
                this.f3658b = C2141fa.m5650b(feVar);
            }
            if (f2) {
                if (z) {
                    this.f3659c = new C2149fh(feVar, this.f3658b, m5600a(feVar, fdVar), fdVar);
                } else {
                    this.f3659c = C2141fa.m5649a(feVar);
                }
            }
        }
        C2149fh fhVar = this.f3659c;
        if (fhVar != null) {
            fhVar.mo17756a(fdVar.mo17744d());
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: d */
    public C2149fh mo17721d() {
        return this.f3659c;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: e */
    public void mo17722e() {
        if (this.f3657a.mo17746f()) {
            this.f3659c.mo17758c();
        }
    }

    /* renamed from: a */
    public C2143fb mo17711a() {
        return this.f3658b.mo17711a();
    }

    /* renamed from: b */
    public C2143fb mo17712b() {
        return this.f3658b.mo17712b();
    }

    /* renamed from: c */
    public C2143fb mo17713c() {
        return this.f3658b.mo17713c();
    }

    /* renamed from: a */
    private static C2164fs m5600a(C2146fe feVar, C2145fd fdVar) {
        if (!C2148fg.m5679a(fdVar)) {
            return feVar.mo17751d();
        }
        try {
            return feVar.mo17747a(fdVar.mo17743c());
        } catch (IOException | GeneralSecurityException unused) {
            return null;
        }
    }
}

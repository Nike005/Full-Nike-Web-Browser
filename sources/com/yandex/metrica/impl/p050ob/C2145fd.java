package com.yandex.metrica.impl.p050ob;

import java.security.cert.X509Certificate;
import java.util.List;
import org.apache.commons.lang3.time.DateUtils;

/* renamed from: com.yandex.metrica.impl.ob.fd */
public class C2145fd {

    /* renamed from: a */
    private C2153fj f3693a;

    /* renamed from: b */
    private boolean f3694b;

    /* renamed from: c */
    private boolean f3695c;

    /* renamed from: d */
    private long f3696d;

    /* renamed from: e */
    private String f3697e;

    /* renamed from: f */
    private List<X509Certificate> f3698f;

    C2145fd() {
        this.f3694b = true;
        this.f3695c = false;
        this.f3696d = DateUtils.MILLIS_PER_DAY;
        this.f3697e = "https://certificate.mobile.yandex.net/api/v1/pins";
    }

    public C2145fd(C2153fj fjVar) {
        this.f3694b = true;
        this.f3695c = false;
        this.f3696d = DateUtils.MILLIS_PER_DAY;
        this.f3697e = "https://certificate.mobile.yandex.net/api/v1/pins";
        this.f3693a = fjVar;
    }

    public C2145fd(C2153fj fjVar, boolean z, boolean z2) {
        this(fjVar);
        this.f3694b = z;
        this.f3695c = z2;
    }

    /* renamed from: a */
    public void mo17741a(String str, List<X509Certificate> list) {
        this.f3697e = str;
        this.f3698f = list;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public long mo17740a() {
        return this.f3696d;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public String mo17742b() {
        return this.f3697e;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: c */
    public List<X509Certificate> mo17743c() {
        return this.f3698f;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: d */
    public C2153fj mo17744d() {
        return this.f3693a;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: e */
    public boolean mo17745e() {
        return this.f3695c;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: f */
    public boolean mo17746f() {
        return this.f3694b;
    }
}

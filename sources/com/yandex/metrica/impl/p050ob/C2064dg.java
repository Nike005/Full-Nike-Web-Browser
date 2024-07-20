package com.yandex.metrica.impl.p050ob;

import android.content.Context;

/* renamed from: com.yandex.metrica.impl.ob.dg */
public class C2064dg extends C2061dd {

    /* renamed from: c */
    private static final C2068dk f3511c = new C2068dk("SERVICE_API_LEVEL");

    /* renamed from: d */
    private static final C2068dk f3512d = new C2068dk("CLIENT_API_LEVEL");

    /* renamed from: e */
    private C2068dk f3513e;

    /* renamed from: f */
    private C2068dk f3514f;

    /* access modifiers changed from: protected */
    /* renamed from: f */
    public String mo17543f() {
        return "_migrationpreferences";
    }

    public C2064dg(Context context) {
        super(context, (String) null);
    }

    /* access modifiers changed from: protected */
    /* renamed from: h */
    public void mo17548h() {
        super.mo17548h();
        this.f3513e = new C2068dk(f3511c.mo17604a());
        this.f3514f = new C2068dk(f3512d.mo17604a());
    }

    /* renamed from: a */
    public int mo17585a() {
        return this.f3455b.getInt(this.f3513e.mo17606b(), -1);
    }

    /* renamed from: b */
    public C2064dg mo17586b() {
        mo17547h(this.f3513e.mo17606b());
        return this;
    }

    /* renamed from: c */
    public C2064dg mo17587c() {
        mo17547h(this.f3514f.mo17606b());
        return this;
    }
}

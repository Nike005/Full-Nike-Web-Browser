package com.yandex.metrica.impl.p050ob;

/* renamed from: com.yandex.metrica.impl.ob.cb */
public abstract class C2017cb {

    /* renamed from: a */
    private final C2004bq f3348a;

    /* renamed from: b */
    private final String f3349b;

    /* access modifiers changed from: protected */
    /* renamed from: f */
    public void mo17396f() {
    }

    static {
        C2017cb.class.getSimpleName();
    }

    public C2017cb(C2004bq bqVar) {
        this(bqVar, (String) null);
    }

    public C2017cb(C2004bq bqVar, String str) {
        this.f3348a = bqVar;
        this.f3349b = str;
        mo17396f();
    }

    /* renamed from: g */
    public String mo17397g() {
        return this.f3349b;
    }

    /* access modifiers changed from: protected */
    /* renamed from: q */
    public C2068dk mo17399q(String str) {
        return new C2068dk(str, mo17397g());
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public <T extends C2017cb> T mo17390a(String str, String str2) {
        synchronized (this) {
            this.f3348a.mo17300b(str, str2);
        }
        return this;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public <T extends C2017cb> T mo17389a(String str, long j) {
        synchronized (this) {
            this.f3348a.mo17299b(str, j);
        }
        return this;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public <T extends C2017cb> T mo17388a(String str, int i) {
        synchronized (this) {
            this.f3348a.mo17298b(str, i);
        }
        return this;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public <T extends C2017cb> T mo17391a(String str, boolean z) {
        synchronized (this) {
            this.f3348a.mo17301b(str, z);
        }
        return this;
    }

    /* access modifiers changed from: protected */
    /* renamed from: r */
    public <T extends C2017cb> T mo17400r(String str) {
        synchronized (this) {
            this.f3348a.mo17294a(str);
        }
        return this;
    }

    /* renamed from: h */
    public void mo17398h() {
        synchronized (this) {
            this.f3348a.mo17302b();
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public long mo17393b(String str, long j) {
        return this.f3348a.mo17293a(str, j);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public int mo17392b(String str, int i) {
        return this.f3348a.mo17292a(str, i);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public String mo17394b(String str, String str2) {
        return this.f3348a.mo17296a(str, str2);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public boolean mo17395b(String str, boolean z) {
        return this.f3348a.mo17297a(str, z);
    }
}

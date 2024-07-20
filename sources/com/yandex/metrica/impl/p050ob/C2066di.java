package com.yandex.metrica.impl.p050ob;

import android.content.Context;

/* renamed from: com.yandex.metrica.impl.ob.di */
public class C2066di extends C2061dd {

    /* renamed from: c */
    static final C2068dk f3517c = new C2068dk("PREF_KEY_DEVICE_ID_");

    /* renamed from: d */
    static final C2068dk f3518d = new C2068dk("PREF_KEY_UID_");

    /* renamed from: e */
    static final C2068dk f3519e = new C2068dk("STARTUP_CLIDS_MATCH_WITH_APP_CLIDS_");

    /* renamed from: f */
    static final C2068dk f3520f = new C2068dk("PREF_KEY_PINNING_UPDATE_URL");

    /* renamed from: g */
    private static final C2068dk f3521g = new C2068dk("PREF_KEY_HOST_URL_");

    /* renamed from: h */
    private static final C2068dk f3522h = new C2068dk("PREF_KEY_REPORT_URL_");

    /* renamed from: i */
    private static final C2068dk f3523i = new C2068dk("PREF_KEY_GET_AD_URL");

    /* renamed from: j */
    private static final C2068dk f3524j = new C2068dk("PREF_KEY_REPORT_AD_URL");

    /* renamed from: k */
    private static final C2068dk f3525k = new C2068dk("PREF_KEY_STARTUP_OBTAIN_TIME_");

    /* renamed from: l */
    private static final C2068dk f3526l = new C2068dk("PREF_KEY_STARTUP_ENCODED_CLIDS_");

    /* renamed from: m */
    private static final C2068dk f3527m = new C2068dk("PREF_KEY_DISTRIBUTION_REFERRER_");

    /* renamed from: n */
    private static final C2068dk f3528n = new C2068dk("PREF_KEY_EASY_COLLECTING_ENABLED_");

    /* renamed from: o */
    private C2068dk f3529o;

    /* renamed from: p */
    private C2068dk f3530p;

    /* renamed from: q */
    private C2068dk f3531q;

    /* renamed from: r */
    private C2068dk f3532r;

    /* renamed from: s */
    private C2068dk f3533s;

    /* renamed from: t */
    private C2068dk f3534t;

    /* renamed from: u */
    private C2068dk f3535u;

    /* renamed from: v */
    private C2068dk f3536v;

    /* renamed from: w */
    private C2068dk f3537w;

    /* renamed from: x */
    private C2068dk f3538x;

    /* access modifiers changed from: protected */
    /* renamed from: f */
    public String mo17543f() {
        return "_startupserviceinfopreferences";
    }

    public C2066di(Context context) {
        this(context, (String) null);
    }

    public C2066di(Context context, String str) {
        super(context, str);
    }

    /* access modifiers changed from: protected */
    /* renamed from: h */
    public void mo17548h() {
        super.mo17548h();
        this.f3529o = new C2068dk(f3517c.mo17604a());
        this.f3530p = new C2068dk(f3518d.mo17604a(), mo17550j());
        this.f3531q = new C2068dk(f3521g.mo17604a(), mo17550j());
        this.f3532r = new C2068dk(f3522h.mo17604a(), mo17550j());
        this.f3533s = new C2068dk(f3523i.mo17604a(), mo17550j());
        this.f3534t = new C2068dk(f3524j.mo17604a(), mo17550j());
        this.f3535u = new C2068dk(f3525k.mo17604a(), mo17550j());
        this.f3536v = new C2068dk(f3526l.mo17604a(), mo17550j());
        this.f3537w = new C2068dk(f3527m.mo17604a(), mo17550j());
        this.f3538x = new C2068dk(f3528n.mo17604a(), mo17550j());
    }

    /* renamed from: a */
    public long mo17590a(long j) {
        return this.f3455b.getLong(this.f3535u.mo17606b(), j);
    }

    /* renamed from: a */
    public String mo17592a(String str) {
        return this.f3455b.getString(this.f3529o.mo17606b(), str);
    }

    /* renamed from: b */
    public String mo17593b(String str) {
        return this.f3455b.getString(this.f3530p.mo17606b(), str);
    }

    /* renamed from: c */
    public String mo17595c(String str) {
        return this.f3455b.getString(this.f3531q.mo17606b(), str);
    }

    /* renamed from: d */
    public String mo17596d(String str) {
        return this.f3455b.getString(this.f3536v.mo17606b(), str);
    }

    /* renamed from: e */
    public String mo17597e(String str) {
        return this.f3455b.getString(this.f3532r.mo17606b(), str);
    }

    /* renamed from: f */
    public String mo17598f(String str) {
        return this.f3455b.getString(this.f3533s.mo17606b(), str);
    }

    /* renamed from: g */
    public String mo17599g(String str) {
        return this.f3455b.getString(this.f3534t.mo17606b(), str);
    }

    /* renamed from: a */
    public String mo17591a() {
        return this.f3455b.getString(this.f3537w.mo17604a(), (String) null);
    }

    /* renamed from: i */
    public C2066di mo17600i(String str) {
        return (C2066di) mo17546a(this.f3530p.mo17606b(), str);
    }

    /* renamed from: j */
    public C2066di mo17601j(String str) {
        return (C2066di) mo17546a(this.f3529o.mo17606b(), str);
    }

    /* renamed from: b */
    public static void m5379b(Context context) {
        C2069dl.m5401a(context, "_startupserviceinfopreferences").edit().remove(f3517c.mo17604a()).apply();
    }

    /* renamed from: b */
    public void mo17594b() {
        mo17547h(this.f3529o.mo17606b()).mo17547h(this.f3530p.mo17606b()).mo17547h(this.f3531q.mo17606b()).mo17547h(this.f3532r.mo17606b()).mo17547h(this.f3533s.mo17606b()).mo17547h(this.f3534t.mo17606b()).mo17547h(this.f3535u.mo17606b()).mo17547h(this.f3538x.mo17606b()).mo17547h(this.f3536v.mo17606b()).mo17547h(this.f3537w.mo17604a()).mo17547h(f3519e.mo17604a()).mo17547h(f3520f.mo17604a()).mo17551k();
    }
}

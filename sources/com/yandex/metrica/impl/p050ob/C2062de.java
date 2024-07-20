package com.yandex.metrica.impl.p050ob;

import android.content.Context;
import java.util.Map;

/* renamed from: com.yandex.metrica.impl.ob.de */
public class C2062de extends C2061dd {

    /* renamed from: c */
    private static final C2068dk f3458c = new C2068dk("UUID");

    /* renamed from: d */
    private static final C2068dk f3459d = new C2068dk("DEVICEID");

    /* renamed from: e */
    private static final C2068dk f3460e = new C2068dk("DEVICEID_2");

    /* renamed from: f */
    private static final C2068dk f3461f = new C2068dk("DEVICEID_3");

    /* renamed from: g */
    private static final C2068dk f3462g = new C2068dk("AD_URL_GET");

    /* renamed from: h */
    private static final C2068dk f3463h = new C2068dk("AD_URL_REPORT");

    /* renamed from: i */
    private static final C2068dk f3464i = new C2068dk("HOST_URL");

    /* renamed from: j */
    private static final C2068dk f3465j = new C2068dk("SERVER_TIME_OFFSET");

    /* renamed from: k */
    private static final C2068dk f3466k = new C2068dk("STARTUP_REQUEST_TIME");

    /* renamed from: l */
    private static final C2068dk f3467l = new C2068dk("CLIDS");

    /* renamed from: m */
    private C2068dk f3468m;

    /* renamed from: n */
    private C2068dk f3469n;

    /* renamed from: o */
    private C2068dk f3470o;

    /* renamed from: p */
    private C2068dk f3471p;

    /* renamed from: q */
    private C2068dk f3472q;

    /* renamed from: r */
    private C2068dk f3473r;

    /* renamed from: s */
    private C2068dk f3474s;

    /* renamed from: t */
    private C2068dk f3475t;

    /* renamed from: u */
    private C2068dk f3476u;

    /* access modifiers changed from: protected */
    /* renamed from: f */
    public String mo17543f() {
        return "_startupinfopreferences";
    }

    static {
        new C2068dk("UUID_SOURCE");
    }

    public C2062de(Context context) {
        super(context, (String) null);
    }

    /* access modifiers changed from: protected */
    /* renamed from: h */
    public void mo17548h() {
        super.mo17548h();
        this.f3468m = new C2068dk(f3458c.mo17604a());
        this.f3469n = new C2068dk(f3459d.mo17604a());
        this.f3470o = new C2068dk(f3460e.mo17604a());
        this.f3471p = new C2068dk(f3461f.mo17604a());
        this.f3472q = new C2068dk(f3462g.mo17604a());
        this.f3473r = new C2068dk(f3463h.mo17604a());
        new C2068dk(f3464i.mo17604a());
        this.f3474s = new C2068dk(f3465j.mo17604a());
        this.f3475t = new C2068dk(f3466k.mo17604a());
        this.f3476u = new C2068dk(f3467l.mo17604a());
    }

    /* renamed from: a */
    public String mo17554a(String str) {
        return this.f3455b.getString(this.f3468m.mo17606b(), str);
    }

    /* renamed from: b */
    public String mo17557b(String str) {
        return this.f3455b.getString(this.f3471p.mo17606b(), str);
    }

    /* renamed from: a */
    public String mo17553a() {
        return this.f3455b.getString(this.f3470o.mo17606b(), this.f3455b.getString(this.f3469n.mo17606b(), ""));
    }

    /* renamed from: c */
    public String mo17558c(String str) {
        return this.f3455b.getString(this.f3472q.mo17606b(), str);
    }

    /* renamed from: d */
    public String mo17560d(String str) {
        return this.f3455b.getString(this.f3473r.mo17606b(), str);
    }

    /* renamed from: a */
    public long mo17552a(long j) {
        return this.f3455b.getLong(this.f3474s.mo17604a(), j);
    }

    /* renamed from: b */
    public long mo17555b(long j) {
        return this.f3455b.getLong(this.f3475t.mo17606b(), j);
    }

    /* renamed from: e */
    public String mo17561e(String str) {
        return this.f3455b.getString(this.f3476u.mo17606b(), str);
    }

    /* renamed from: b */
    public C2062de mo17556b() {
        return (C2062de) mo17549i();
    }

    /* renamed from: c */
    public Map<String, ?> mo17559c() {
        return this.f3455b.getAll();
    }
}

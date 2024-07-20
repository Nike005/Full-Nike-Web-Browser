package com.yandex.metrica.impl.p050ob;

import com.yandex.metrica.CounterConfiguration;
import com.yandex.metrica.impl.C1807a;

/* renamed from: com.yandex.metrica.impl.ob.ca */
public class C2016ca extends C2017cb {

    /* renamed from: a */
    private static final C2068dk f3329a = new C2068dk("SESSION_SLEEP_START");

    /* renamed from: b */
    private static final C2068dk f3330b = new C2068dk("SESSION_ID");

    /* renamed from: c */
    private static final C2068dk f3331c = new C2068dk("SESSION_COUNTER_ID");

    /* renamed from: d */
    private static final C2068dk f3332d = new C2068dk("SESSION_INIT_TIME");

    /* renamed from: e */
    private static final C2068dk f3333e = new C2068dk("SESSION_IS_ALIVE_REPORT_NEEDED");

    /* renamed from: f */
    private static final C2068dk f3334f = new C2068dk("BG_SESSION_ID");

    /* renamed from: g */
    private static final C2068dk f3335g = new C2068dk("BG_SESSION_SLEEP_START");

    /* renamed from: h */
    private static final C2068dk f3336h = new C2068dk("BG_SESSION_COUNTER_ID");

    /* renamed from: i */
    private static final C2068dk f3337i = new C2068dk("BG_SESSION_INIT_TIME");

    /* renamed from: j */
    private static final C2068dk f3338j = new C2068dk("BG_SESSION_IS_ALIVE_REPORT_NEEDED");

    /* renamed from: k */
    private static final C2068dk f3339k = new C2068dk("COLLECT_INSTALLED_APPS");

    /* renamed from: l */
    private static final C2068dk f3340l = new C2068dk("IDENTITY_SEND_TIME");

    /* renamed from: m */
    private static final C2068dk f3341m = new C2068dk("PERMISSIONS_CHECK_TIME");

    /* renamed from: n */
    private static final C2068dk f3342n = new C2068dk("USER_INFO");

    /* renamed from: o */
    private static final C2068dk f3343o = new C2068dk("APP_ENVIRONMENT");

    /* renamed from: p */
    private static final C2068dk f3344p = new C2068dk("APP_ENVIRONMENT_REVISION");

    /* renamed from: q */
    private static final C2068dk f3345q = new C2068dk("LAST_MIGRATION_VERSION");

    /* renamed from: r */
    private static final C2068dk f3346r = new C2068dk("LAST_APP_VERSION_WITH_FEATURES");

    /* renamed from: s */
    private static final C2068dk f3347s = new C2068dk("APPLICATION_FEATURES");

    static {
        new C2068dk("SESSION_ALIVE_TIME");
    }

    public C2016ca(C2004bq bqVar) {
        super(bqVar);
    }

    /* renamed from: a */
    public long mo17353a(long j) {
        return mo17393b(f3332d.mo17606b(), j);
    }

    /* renamed from: b */
    public long mo17359b(long j) {
        return mo17393b(f3337i.mo17606b(), j);
    }

    /* renamed from: c */
    public long mo17364c(long j) {
        return mo17393b(f3340l.mo17606b(), j);
    }

    /* renamed from: d */
    public long mo17369d(long j) {
        return mo17393b(f3341m.mo17606b(), j);
    }

    /* renamed from: a */
    public int mo17352a(int i) {
        return mo17392b(f3346r.mo17606b(), i);
    }

    /* renamed from: e */
    public long mo17371e(long j) {
        return mo17393b(f3330b.mo17606b(), j);
    }

    /* renamed from: f */
    public long mo17372f(long j) {
        return mo17393b(f3334f.mo17606b(), j);
    }

    /* renamed from: g */
    public long mo17373g(long j) {
        return mo17393b(f3331c.mo17606b(), j);
    }

    /* renamed from: h */
    public long mo17374h(long j) {
        return mo17393b(f3336h.mo17606b(), j);
    }

    /* renamed from: a */
    public C1807a.C1808a mo17354a() {
        C1807a.C1808a aVar;
        synchronized (this) {
            aVar = new C1807a.C1808a(mo17394b(f3343o.mo17606b(), "{}"), mo17393b(f3344p.mo17606b(), 0));
        }
        return aVar;
    }

    /* renamed from: i */
    public long mo17375i(long j) {
        return mo17393b(f3329a.mo17606b(), j);
    }

    /* renamed from: j */
    public long mo17376j(long j) {
        return mo17393b(f3335g.mo17606b(), j);
    }

    /* renamed from: b */
    public String mo17363b() {
        return mo17394b(f3347s.mo17606b(), "");
    }

    /* renamed from: a */
    public boolean mo17358a(boolean z) {
        return mo17395b(f3333e.mo17606b(), z);
    }

    /* renamed from: c */
    public CounterConfiguration.C1774a mo17365c() {
        return CounterConfiguration.C1774a.m3973a(Long.valueOf(mo17393b(f3339k.mo17606b(), (long) CounterConfiguration.C1774a.UNDEFINED.f2723d)).intValue());
    }

    /* renamed from: a */
    public String mo17357a(String str) {
        return mo17394b(f3342n.mo17606b(), str);
    }

    /* renamed from: k */
    public C2016ca mo17377k(long j) {
        return (C2016ca) mo17389a(f3332d.mo17606b(), j);
    }

    /* renamed from: l */
    public C2016ca mo17378l(long j) {
        return (C2016ca) mo17389a(f3337i.mo17606b(), j);
    }

    /* renamed from: a */
    public C2016ca mo17356a(C1807a.C1808a aVar) {
        synchronized (this) {
            mo17390a(f3343o.mo17606b(), aVar.f2885a);
            mo17389a(f3344p.mo17606b(), aVar.f2886b);
        }
        return this;
    }

    /* renamed from: m */
    public C2016ca mo17379m(long j) {
        return (C2016ca) mo17389a(f3340l.mo17606b(), j);
    }

    /* renamed from: n */
    public C2016ca mo17380n(long j) {
        return (C2016ca) mo17389a(f3341m.mo17606b(), j);
    }

    /* renamed from: o */
    public C2016ca mo17381o(long j) {
        return (C2016ca) mo17389a(f3330b.mo17606b(), j);
    }

    /* renamed from: p */
    public C2016ca mo17382p(long j) {
        return (C2016ca) mo17389a(f3334f.mo17606b(), j);
    }

    /* renamed from: q */
    public C2016ca mo17383q(long j) {
        return (C2016ca) mo17389a(f3331c.mo17606b(), j);
    }

    /* renamed from: r */
    public C2016ca mo17384r(long j) {
        return (C2016ca) mo17389a(f3336h.mo17606b(), j);
    }

    /* renamed from: s */
    public C2016ca mo17385s(long j) {
        return (C2016ca) mo17389a(f3329a.mo17606b(), j);
    }

    /* renamed from: t */
    public C2016ca mo17386t(long j) {
        return (C2016ca) mo17389a(f3335g.mo17606b(), j);
    }

    /* renamed from: a */
    public C2016ca mo17355a(CounterConfiguration.C1774a aVar) {
        return (C2016ca) mo17389a(f3339k.mo17606b(), (long) aVar.f2723d);
    }

    /* renamed from: b */
    public C2016ca mo17361b(String str) {
        return (C2016ca) mo17390a(f3342n.mo17606b(), str);
    }

    /* renamed from: b */
    public C2016ca mo17362b(boolean z) {
        return (C2016ca) mo17391a(f3333e.mo17606b(), z);
    }

    /* renamed from: d */
    public long mo17368d() {
        return mo17393b(f3345q.mo17606b(), 0);
    }

    /* renamed from: u */
    public C2016ca mo17387u(long j) {
        return (C2016ca) mo17389a(f3345q.mo17606b(), j);
    }

    /* renamed from: c */
    public boolean mo17367c(boolean z) {
        return mo17395b(f3338j.mo17606b(), z);
    }

    /* renamed from: d */
    public C2016ca mo17370d(boolean z) {
        return (C2016ca) mo17391a(f3338j.mo17606b(), z);
    }

    /* renamed from: b */
    public C2016ca mo17360b(int i) {
        return (C2016ca) mo17388a(f3346r.mo17606b(), i);
    }

    /* renamed from: c */
    public C2016ca mo17366c(String str) {
        return (C2016ca) mo17390a(f3347s.mo17606b(), str);
    }
}

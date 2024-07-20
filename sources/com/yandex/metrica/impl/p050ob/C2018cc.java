package com.yandex.metrica.impl.p050ob;

import com.yandex.metrica.CounterConfiguration;

/* renamed from: com.yandex.metrica.impl.ob.cc */
public class C2018cc extends C2017cb {

    /* renamed from: a */
    static final C2068dk f3350a = new C2068dk("LOCATION_TRACKING_ENABLED");

    /* renamed from: b */
    static final C2068dk f3351b = new C2068dk("COLLECT_INSTALLED_APPS");

    /* renamed from: c */
    static final C2068dk f3352c = new C2068dk("REFERRER");

    /* renamed from: d */
    static final C2068dk f3353d = new C2068dk("PREF_KEY_OFFSET");

    /* renamed from: e */
    private static final C2068dk f3354e = new C2068dk("LAST_MIGRATION_VERSION");

    public C2018cc(C2004bq bqVar) {
        super(bqVar);
    }

    /* renamed from: a */
    public CounterConfiguration.C1774a mo17402a() {
        return CounterConfiguration.C1774a.m3973a(Long.valueOf(mo17393b(f3351b.mo17606b(), (long) CounterConfiguration.C1774a.UNDEFINED.f2723d)).intValue());
    }

    /* renamed from: a */
    public String mo17405a(String str) {
        return mo17394b(f3352c.mo17606b(), str);
    }

    /* renamed from: a */
    public C2018cc mo17404a(CounterConfiguration.C1774a aVar) {
        return (C2018cc) mo17389a(f3351b.mo17606b(), (long) aVar.f2723d);
    }

    /* renamed from: b */
    public C2018cc mo17409b(String str) {
        return (C2018cc) mo17390a(f3352c.mo17606b(), str);
    }

    /* renamed from: b */
    public C2018cc mo17407b() {
        return (C2018cc) mo17400r(f3352c.mo17606b());
    }

    /* renamed from: a */
    public int mo17401a(int i) {
        return mo17392b(f3354e.mo17606b(), i);
    }

    /* renamed from: b */
    public C2018cc mo17408b(int i) {
        return (C2018cc) mo17388a(f3354e.mo17606b(), i);
    }

    /* renamed from: a */
    public void mo17406a(boolean z) {
        mo17391a(f3350a.mo17606b(), z).mo17398h();
    }

    /* renamed from: c */
    public boolean mo17411c() {
        return mo17395b(f3350a.mo17606b(), false);
    }

    /* renamed from: c */
    public long mo17410c(int i) {
        return mo17393b(f3353d.mo17606b(), (long) i);
    }

    /* renamed from: a */
    public C2018cc mo17403a(long j) {
        return (C2018cc) mo17389a(f3353d.mo17606b(), j);
    }
}

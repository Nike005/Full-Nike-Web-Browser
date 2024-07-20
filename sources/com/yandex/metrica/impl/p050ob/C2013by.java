package com.yandex.metrica.impl.p050ob;

/* renamed from: com.yandex.metrica.impl.ob.by */
public class C2013by extends C2017cb {

    /* renamed from: a */
    private final C2068dk f3311a = new C2068dk("init_event_pref_key");

    /* renamed from: b */
    private final C2068dk f3312b = new C2068dk("first_event_pref_key");

    /* renamed from: c */
    private final C2068dk f3313c = new C2068dk("first_event_description_key");

    public C2013by(C2004bq bqVar) {
        super(bqVar);
    }

    /* renamed from: a */
    public void mo17316a() {
        mo17390a(this.f3311a.mo17606b(), "DONE").mo17398h();
    }

    /* renamed from: b */
    public void mo17318b() {
        mo17390a(this.f3312b.mo17606b(), "DONE").mo17398h();
    }

    /* renamed from: a */
    public String mo17315a(String str) {
        return mo17394b(this.f3311a.mo17606b(), str);
    }

    /* renamed from: b */
    public String mo17317b(String str) {
        return mo17394b(this.f3312b.mo17606b(), str);
    }

    /* renamed from: c */
    public boolean mo17320c() {
        return mo17315a((String) null) != null;
    }

    /* renamed from: d */
    public boolean mo17322d() {
        return mo17317b((String) null) != null;
    }

    /* renamed from: c */
    public void mo17319c(String str) {
        mo17390a(this.f3313c.mo17606b(), str).mo17398h();
    }

    /* renamed from: d */
    public String mo17321d(String str) {
        return mo17394b(this.f3313c.mo17606b(), str);
    }

    /* renamed from: e */
    public void mo17323e() {
        mo17400r(this.f3313c.mo17606b()).mo17398h();
    }
}

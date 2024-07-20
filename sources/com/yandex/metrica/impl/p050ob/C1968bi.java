package com.yandex.metrica.impl.p050ob;

import com.yandex.metrica.impl.C1915h;
import com.yandex.metrica.impl.C2208p;

/* renamed from: com.yandex.metrica.impl.ob.bi */
public class C1968bi {

    /* renamed from: a */
    private C1963be f3240a;

    /* renamed from: b */
    private C1961bc f3241b;

    /* renamed from: c */
    private C2198t f3242c;

    public C1968bi(C2198t tVar, C2016ca caVar) {
        this.f3242c = tVar;
        this.f3240a = new C1963be(tVar, new C1964bf(caVar));
        this.f3241b = new C1961bc(tVar, new C1962bd(caVar));
    }

    /* renamed from: a */
    public void mo17243a() {
        this.f3240a.mo17238i();
    }

    /* renamed from: b */
    public void mo17246b() {
        this.f3240a.mo17237h();
        this.f3241b.mo17237h();
    }

    /* renamed from: a */
    public boolean mo17245a(C1915h hVar) {
        return m4934a(hVar, this.f3240a, this.f3241b);
    }

    /* renamed from: b */
    public boolean mo17247b(C1915h hVar) {
        if (!this.f3240a.mo17235f()) {
            return m4934a(hVar, this.f3241b, this.f3240a);
        }
        return false;
    }

    /* renamed from: a */
    private boolean m4934a(C1915h hVar, C1965bg bgVar, C1965bg bgVar2) {
        if (!bgVar.mo17235f()) {
            if (bgVar.mo17240k()) {
                this.f3242c.mo17842a(C1915h.m4728a(hVar, C2208p.C2209a.EVENT_TYPE_ALIVE), mo17242a(bgVar));
                bgVar.mo17231a(false);
            } else if (bgVar2.mo17240k()) {
                this.f3242c.mo17842a(C1915h.m4728a(hVar, C2208p.C2209a.EVENT_TYPE_ALIVE), mo17242a(bgVar2));
                bgVar2.mo17231a(false);
            }
            bgVar2.mo17237h();
            bgVar.mo17233d();
            return true;
        }
        bgVar.mo17238i();
        return false;
    }

    /* renamed from: a */
    public void mo17244a(boolean z) {
        m4935f().mo17231a(z);
    }

    /* renamed from: c */
    public long[] mo17248c() {
        return new long[]{this.f3241b.mo17232c(), this.f3240a.mo17232c()};
    }

    /* renamed from: d */
    public C1969bj mo17249d() {
        C1965bg f = m4935f();
        return new C1969bj().mo17252a(f.mo17232c()).mo17254b(f.mo17239j()).mo17257c(f.mo17236g()).mo17253a(f.mo17213a());
    }

    /* renamed from: f */
    private C1965bg m4935f() {
        return this.f3240a.mo17235f() ? this.f3240a : this.f3241b;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public C1969bj mo17242a(C1965bg bgVar) {
        return new C1969bj().mo17252a(bgVar.mo17232c()).mo17253a(bgVar.mo17213a()).mo17254b(bgVar.mo17239j()).mo17257c(bgVar.mo17234e());
    }

    /* renamed from: e */
    public C1969bj mo17250e() {
        long currentTimeMillis = System.currentTimeMillis() / 1000;
        this.f3242c.mo17862i().mo17269a(currentTimeMillis, C1971bl.BACKGROUND);
        return new C1969bj().mo17252a(currentTimeMillis).mo17253a(C1971bl.BACKGROUND).mo17254b(0).mo17257c(0);
    }
}

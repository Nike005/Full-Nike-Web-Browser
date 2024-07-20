package com.yandex.metrica.impl.p050ob;

import com.yandex.metrica.impl.C1915h;
import com.yandex.metrica.impl.C2208p;

/* renamed from: com.yandex.metrica.impl.ob.ap */
public class C1946ap extends C1936af {

    /* renamed from: a */
    private C2013by f3218a;

    public C1946ap(C2198t tVar) {
        super(tVar);
        this.f3218a = tVar.mo17831C();
    }

    /* renamed from: a */
    public boolean mo17175a(C1915h hVar) {
        C1915h hVar2;
        C2198t a = mo17176a();
        if (this.f3218a.mo17320c()) {
            return false;
        }
        if (a.mo17863j().mo16613y()) {
            hVar2 = C1915h.m4728a(hVar, C2208p.C2209a.EVENT_TYPE_APP_UPDATE);
        } else {
            hVar2 = C1915h.m4728a(hVar, C2208p.C2209a.EVENT_TYPE_INIT);
        }
        a.mo17853d(hVar2.mo17107c(this.f3218a.mo17321d("")));
        a.mo17849b(true);
        this.f3218a.mo17316a();
        this.f3218a.mo17323e();
        return false;
    }
}

package com.yandex.metrica.impl.p050ob;

import com.yandex.metrica.impl.C1915h;
import com.yandex.metrica.impl.C2208p;

/* renamed from: com.yandex.metrica.impl.ob.ah */
public class C1938ah extends C1936af {

    /* renamed from: a */
    private C2013by f3213a = mo17176a().mo17831C();

    public C1938ah(C2198t tVar) {
        super(tVar);
    }

    /* renamed from: a */
    public boolean mo17175a(C1915h hVar) {
        C2198t a = mo17176a();
        if (this.f3213a.mo17322d()) {
            return false;
        }
        if (!this.f3213a.mo17320c()) {
            String b = hVar.mo17117b();
            this.f3213a.mo17319c(b);
            a.mo17853d(C1915h.m4728a(hVar, C2208p.C2209a.EVENT_TYPE_FIRST_ACTIVATION).mo17107c(b));
            a.mo17849b(true);
        }
        this.f3213a.mo17318b();
        return false;
    }
}

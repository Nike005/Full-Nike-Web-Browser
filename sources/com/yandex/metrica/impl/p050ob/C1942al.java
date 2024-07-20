package com.yandex.metrica.impl.p050ob;

import com.yandex.metrica.impl.C1915h;
import java.util.List;

/* renamed from: com.yandex.metrica.impl.ob.al */
public class C1942al extends C1936af {

    /* renamed from: a */
    private final C2003bp f3215a;

    /* renamed from: b */
    private final C2053cx f3216b;

    protected C1942al(C2198t tVar, C2003bp bpVar, C2053cx cxVar) {
        super(tVar);
        this.f3215a = bpVar;
        this.f3216b = cxVar;
    }

    /* renamed from: a */
    public boolean mo17175a(C1915h hVar) {
        C2198t a = mo17176a();
        if (!a.mo17831C().mo17322d() || !a.mo17829A()) {
            return false;
        }
        C2006br c = this.f3215a.mo17289c();
        List<C2052cw> a2 = this.f3216b.mo17517a(mo17176a().mo17866m(), c.mo17304a());
        if (a2 == null) {
            a.mo17870q();
            return false;
        }
        a.mo17857f(C1915h.m4729a(hVar, a2));
        c.mo17305a(a2);
        return false;
    }
}

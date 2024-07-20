package com.yandex.metrica.impl.p050ob;

import com.yandex.metrica.impl.C1894bi;
import com.yandex.metrica.impl.C1915h;
import com.yandex.metrica.impl.C2208p;

/* renamed from: com.yandex.metrica.impl.ob.an */
public class C1944an extends C1936af {
    public C1944an(C2198t tVar) {
        super(tVar);
    }

    /* renamed from: a */
    public boolean mo17175a(C1915h hVar) {
        C2198t a = mo17176a();
        if (!a.mo17863j().mo16554C()) {
            return false;
        }
        String b = mo17179b();
        if (C1894bi.m4622a(b)) {
            return false;
        }
        mo17180c();
        a.mo17841a(new C1915h(hVar).mo17107c("").mo17106b(b).mo17111a(C2208p.C2209a.EVENT_TYPE_REFERRER_DEPRECATED.mo17884a()));
        return false;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public String mo17179b() {
        return mo17176a().mo17873t();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: c */
    public void mo17180c() {
        mo17176a().mo17874u();
    }
}

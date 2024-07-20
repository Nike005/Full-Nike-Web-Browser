package com.yandex.metrica.impl.p050ob;

import com.yandex.metrica.impl.C1807a;
import java.util.HashMap;

/* renamed from: com.yandex.metrica.impl.ob.q */
public class C2189q {

    /* renamed from: a */
    private final HashMap<String, C1807a> f3775a = new HashMap<>();

    /* renamed from: a */
    public synchronized C1807a mo17815a(C2198t tVar, C2016ca caVar) {
        C1807a aVar;
        aVar = this.f3775a.get(tVar.mo17865l().toString());
        if (aVar == null) {
            C1807a.C1808a a = caVar.mo17354a();
            aVar = new C1807a(a.f2885a, a.f2886b);
            this.f3775a.put(tVar.mo17865l().toString(), aVar);
        }
        return aVar;
    }

    /* renamed from: a */
    public boolean mo17816a(C1807a.C1808a aVar, C2016ca caVar) {
        if (aVar.f2886b <= caVar.mo17354a().f2886b) {
            return false;
        }
        caVar.mo17356a(aVar).mo17398h();
        return true;
    }

    /* renamed from: b */
    public synchronized void mo17817b(C1807a.C1808a aVar, C2016ca caVar) {
        caVar.mo17356a(aVar).mo17398h();
    }
}

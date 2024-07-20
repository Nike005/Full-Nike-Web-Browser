package com.yandex.metrica.impl.p050ob;

import com.yandex.metrica.impl.C1915h;
import java.util.ArrayList;
import java.util.Iterator;

/* renamed from: com.yandex.metrica.impl.ob.ao */
public class C1945ao extends C1936af {

    /* renamed from: a */
    private final ArrayList<C1936af> f3217a;

    public C1945ao(C2198t tVar) {
        super(tVar);
        ArrayList<C1936af> arrayList = new ArrayList<>();
        this.f3217a = arrayList;
        arrayList.add(new C1939ai(tVar));
        if (tVar.mo17865l().mo17821d() && tVar.mo17866m().getPackageName().equals(tVar.mo17865l().mo17819b())) {
            this.f3217a.add(new C1942al(tVar, C2003bp.m5024a(mo17176a().mo17866m()), new C2053cx()));
            this.f3217a.add(new C1937ag(tVar));
        }
    }

    /* renamed from: a */
    public boolean mo17175a(C1915h hVar) {
        Iterator<C1936af> it = this.f3217a.iterator();
        while (it.hasNext()) {
            it.next().mo17175a(hVar);
        }
        return false;
    }
}

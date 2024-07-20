package com.yandex.metrica.impl.p050ob;

import com.yandex.metrica.impl.C1887be;
import com.yandex.metrica.impl.p050ob.C2020ce;

/* renamed from: com.yandex.metrica.impl.ob.cg */
public class C2024cg extends C2020ce {
    /* renamed from: a */
    public /* bridge */ /* synthetic */ String mo17452a() {
        return super.mo17452a();
    }

    /* renamed from: b */
    public /* bridge */ /* synthetic */ C2025ch mo17453b() {
        return super.mo17453b();
    }

    /* renamed from: c */
    public /* bridge */ /* synthetic */ C1887be.C1888a mo17454c() {
        return super.mo17454c();
    }

    public /* bridge */ /* synthetic */ String toString() {
        return super.toString();
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    C2024cg(C1887be.C1888a aVar, C2025ch chVar, C2025ch chVar2) {
        super(aVar, chVar == null ? chVar2 : chVar);
    }

    /* renamed from: a */
    public C2020ce.C2021a mo17451a(C2030cj cjVar) {
        C2025ch b = mo17453b();
        if (cjVar.equals(b.mo17468d())) {
            return C2020ce.C2021a.THIS;
        }
        if (b.mo17468d() != null) {
            return C2020ce.C2021a.OTHER;
        }
        if (b.mo17466b()) {
            return C2020ce.C2021a.OTHER;
        }
        return C2020ce.C2021a.UNKNOWN;
    }
}

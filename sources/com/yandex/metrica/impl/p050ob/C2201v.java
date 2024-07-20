package com.yandex.metrica.impl.p050ob;

import com.yandex.metrica.impl.C1915h;

/* renamed from: com.yandex.metrica.impl.ob.v */
public abstract class C2201v<T> {

    /* renamed from: a */
    private final C1931aa<T> f3813a;

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public abstract boolean mo17174a(C1915h hVar, C2203x<T> xVar);

    protected C2201v(C1931aa<T> aaVar) {
        this.f3813a = aaVar;
    }

    /* renamed from: a */
    public boolean mo17881a(C1915h hVar) {
        return mo17174a(hVar, mo17882b(hVar));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public C2203x<T> mo17882b(C1915h hVar) {
        return this.f3813a.mo17173a(hVar.mo17118c());
    }
}

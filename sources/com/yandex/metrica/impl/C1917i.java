package com.yandex.metrica.impl;

/* renamed from: com.yandex.metrica.impl.i */
abstract class C1917i {

    /* renamed from: a */
    private final C1918a f3183a;

    /* renamed from: com.yandex.metrica.impl.i$a */
    public interface C1918a {
        /* renamed from: a */
        boolean mo17095a(Throwable th);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public abstract void mo16880b(Throwable th);

    C1917i(C1918a aVar) {
        this.f3183a = aVar;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo17140a(Throwable th) {
        if (this.f3183a.mo17095a(th)) {
            mo16880b(th);
        }
    }
}

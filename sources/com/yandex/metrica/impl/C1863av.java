package com.yandex.metrica.impl;

import com.yandex.metrica.IReporter;
import com.yandex.metrica.impl.C1917i;

/* renamed from: com.yandex.metrica.impl.av */
public class C1863av extends C1917i {

    /* renamed from: a */
    private final IReporter f3002a;

    C1863av(IReporter iReporter, C1917i.C1918a aVar) {
        super(aVar);
        this.f3002a = iReporter;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public void mo16880b(Throwable th) {
        this.f3002a.reportUnhandledException(th);
    }
}

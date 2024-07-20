package com.yandex.metrica.impl;

import android.os.Handler;
import java.lang.ref.WeakReference;

/* renamed from: com.yandex.metrica.impl.x */
class C2240x implements Runnable {

    /* renamed from: a */
    private final WeakReference<Handler> f3913a;

    /* renamed from: b */
    private final WeakReference<C1876b> f3914b;

    C2240x(Handler handler, C1876b bVar) {
        this.f3913a = new WeakReference<>(handler);
        this.f3914b = new WeakReference<>(bVar);
    }

    public void run() {
        Handler handler = (Handler) this.f3913a.get();
        C1876b bVar = (C1876b) this.f3914b.get();
        if (handler != null && bVar != null && bVar.mo16951c()) {
            C2239w.m5980a(handler, bVar, this);
        }
    }
}

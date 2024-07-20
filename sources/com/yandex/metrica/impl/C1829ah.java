package com.yandex.metrica.impl;

import com.yandex.metrica.impl.p050ob.C2174g;
import com.yandex.metrica.impl.p050ob.C2180i;
import java.lang.Thread;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/* renamed from: com.yandex.metrica.impl.ah */
class C1829ah implements Thread.UncaughtExceptionHandler {

    /* renamed from: a */
    private final CopyOnWriteArrayList<C1917i> f2932a = new CopyOnWriteArrayList<>();

    /* renamed from: b */
    private final Thread.UncaughtExceptionHandler f2933b;

    public C1829ah(Thread.UncaughtExceptionHandler uncaughtExceptionHandler) {
        this.f2933b = uncaughtExceptionHandler;
    }

    public void uncaughtException(Thread thread, Throwable th) {
        try {
            C2174g.m5753a().mo17806b((C2180i) new C1841ao());
            Iterator<C1917i> it = this.f2932a.iterator();
            while (it.hasNext()) {
                it.next().mo17140a(th);
            }
        } finally {
            Thread.UncaughtExceptionHandler uncaughtExceptionHandler = this.f2933b;
            if (uncaughtExceptionHandler != null) {
                uncaughtExceptionHandler.uncaughtException(thread, th);
            }
        }
    }

    /* renamed from: a */
    public void mo16796a(C1917i iVar) {
        this.f2932a.add(iVar);
    }

    /* renamed from: b */
    public void mo16797b(C1917i iVar) {
        this.f2932a.remove(iVar);
    }
}

package com.yandex.metrica.impl;

import android.os.Handler;
import android.os.SystemClock;
import com.google.firebase.iid.ServiceStarter;

/* renamed from: com.yandex.metrica.impl.w */
class C2239w {

    /* renamed from: a */
    private final Handler f3910a;

    /* renamed from: b */
    private final C1876b f3911b;

    /* renamed from: c */
    private final C2240x f3912c;

    C2239w(Handler handler, C1876b bVar) {
        this.f3910a = handler;
        this.f3911b = bVar;
        this.f3912c = new C2240x(handler, bVar);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo17932a() {
        m5981b(this.f3910a, this.f3911b, this.f3912c);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public void mo17933b() {
        m5980a(this.f3910a, this.f3911b, this.f3912c);
    }

    /* renamed from: a */
    static void m5980a(Handler handler, C1876b bVar, Runnable runnable) {
        m5981b(handler, bVar, runnable);
        handler.postAtTime(runnable, m5979a(bVar), SystemClock.uptimeMillis() + ((long) (bVar.mo16952d().mo16887b().mo16579d() * ServiceStarter.ERROR_UNKNOWN)));
    }

    /* renamed from: b */
    private static void m5981b(Handler handler, C1876b bVar, Runnable runnable) {
        handler.removeCallbacks(runnable, m5979a(bVar));
    }

    /* renamed from: a */
    private static String m5979a(C1876b bVar) {
        return bVar.mo16952d().mo16887b().mo16597j();
    }
}

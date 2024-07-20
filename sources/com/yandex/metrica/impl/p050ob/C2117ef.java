package com.yandex.metrica.impl.p050ob;

import android.content.Context;

/* renamed from: com.yandex.metrica.impl.ob.ef */
public class C2117ef extends C2099dy {

    /* renamed from: a */
    private static final Object f3638a = new Object();

    /* renamed from: b */
    private static volatile C2117ef f3639b;

    /* renamed from: c */
    private C2099dy f3640c;

    /* renamed from: a */
    public static C2117ef m5566a(Context context) {
        if (f3639b == null) {
            synchronized (f3638a) {
                if (f3639b == null) {
                    f3639b = new C2117ef(context.getApplicationContext());
                }
            }
        }
        return f3639b;
    }

    C2117ef(Context context) {
        if (context.getPackageManager().hasSystemFeature("android.hardware.telephony")) {
            this.f3640c = new C2108eb(context);
        } else {
            this.f3640c = new C2114ec();
        }
    }

    /* renamed from: a */
    public synchronized void mo17690a() {
        this.f3640c.mo17690a();
    }

    /* renamed from: b */
    public synchronized void mo17691b() {
        this.f3640c.mo17691b();
    }

    /* renamed from: a */
    public synchronized void mo17675a(C2119eh ehVar) {
        this.f3640c.mo17675a(ehVar);
    }

    /* renamed from: a */
    public synchronized void mo17674a(C2107ea eaVar) {
        this.f3640c.mo17674a(eaVar);
    }
}

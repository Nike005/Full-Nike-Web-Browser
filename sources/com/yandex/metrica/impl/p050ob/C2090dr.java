package com.yandex.metrica.impl.p050ob;

import android.content.Context;

/* renamed from: com.yandex.metrica.impl.ob.dr */
public class C2090dr {

    /* renamed from: a */
    private static final Object f3573a = new Object();

    /* renamed from: b */
    private static volatile C2090dr f3574b;

    /* renamed from: c */
    private C2073dp f3575c;

    /* renamed from: a */
    public static C2090dr m5452a(Context context) {
        if (f3574b == null) {
            synchronized (f3573a) {
                if (f3574b == null) {
                    f3574b = new C2090dr(context);
                }
            }
        }
        return f3574b;
    }

    private C2090dr(Context context) {
        this.f3575c = new C2073dp(context);
    }

    /* renamed from: a */
    public void mo17641a() {
        this.f3575c.mo17614b();
    }

    /* renamed from: b */
    public void mo17642b() {
        this.f3575c.mo17612a();
    }
}

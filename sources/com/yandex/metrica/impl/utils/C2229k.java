package com.yandex.metrica.impl.utils;

import android.content.Context;
import com.yandex.metrica.impl.p050ob.C2003bp;
import com.yandex.metrica.impl.p050ob.C2018cc;

/* renamed from: com.yandex.metrica.impl.utils.k */
public class C2229k {

    /* renamed from: a */
    private volatile long f3901a;

    /* renamed from: b */
    private C2018cc f3902b;

    /* renamed from: com.yandex.metrica.impl.utils.k$a */
    private static class C2230a {

        /* renamed from: a */
        static C2229k f3903a = new C2229k((byte) 0);
    }

    /* synthetic */ C2229k(byte b) {
        this();
    }

    /* renamed from: a */
    public static C2229k m5967a() {
        return C2230a.f3903a;
    }

    private C2229k() {
    }

    /* renamed from: b */
    public synchronized long mo17930b() {
        return this.f3901a;
    }

    /* renamed from: a */
    public synchronized void mo17929a(Context context) {
        if (context != null) {
            C2018cc ccVar = new C2018cc(C2003bp.m5024a(context).mo17287b());
            this.f3902b = ccVar;
            this.f3901a = ccVar.mo17410c(0);
        }
    }

    /* renamed from: a */
    public synchronized void mo17928a(long j) {
        this.f3901a = (j - System.currentTimeMillis()) / 1000;
        if (this.f3902b != null) {
            this.f3902b.mo17403a(this.f3901a);
            this.f3902b.mo17398h();
        }
    }
}

package com.yandex.metrica.impl;

import android.os.SystemClock;

/* renamed from: com.yandex.metrica.impl.q */
class C2210q {

    /* renamed from: a */
    private long f3883a = (SystemClock.elapsedRealtime() - 2000000);

    /* renamed from: b */
    private boolean f3884b = true;

    C2210q() {
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public boolean mo17886a() {
        boolean z = this.f3884b;
        this.f3884b = false;
        long elapsedRealtime = SystemClock.elapsedRealtime() - this.f3883a;
        if (!z || elapsedRealtime <= 1000) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public void mo17887b() {
        this.f3884b = true;
        this.f3883a = SystemClock.elapsedRealtime();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: c */
    public boolean mo17888c() {
        return this.f3884b;
    }
}

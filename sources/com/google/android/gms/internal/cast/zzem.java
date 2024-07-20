package com.google.android.gms.internal.cast;

import android.os.Build;
import android.os.Looper;

final class zzem extends ThreadLocal<zzel> {
    zzem() {
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ Object initialValue() {
        if (Build.VERSION.SDK_INT >= 16) {
            return new zzer();
        }
        Looper myLooper = Looper.myLooper();
        if (myLooper != null) {
            return new zzeq(myLooper);
        }
        throw new IllegalStateException("The current thread must have a looper!");
    }
}

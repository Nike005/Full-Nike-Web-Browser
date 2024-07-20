package com.tappx.p048a;

import android.os.Handler;
import android.os.Looper;

/* renamed from: com.tappx.a.g3 */
public class C1426g3 {

    /* renamed from: a */
    private static final Handler f1879a = new Handler(Looper.getMainLooper());

    /* renamed from: a */
    public static boolean m2744a() {
        return Looper.getMainLooper() == Looper.myLooper();
    }

    /* renamed from: a */
    public static void m2743a(Runnable runnable) {
        f1879a.post(runnable);
    }
}

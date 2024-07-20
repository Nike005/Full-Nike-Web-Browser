package com.appnext.base.p082b;

import android.content.Context;

/* renamed from: com.appnext.base.b.e */
public final class C4901e {

    /* renamed from: fr */
    private static Context f4630fr;

    private C4901e() {
    }

    public static void init(Context context) {
        if (context != null) {
            f4630fr = context.getApplicationContext();
            return;
        }
        throw new IllegalArgumentException("context shouldn't be null");
    }

    public static Context getContext() {
        return f4630fr;
    }

    public static String getPackageName() {
        return f4630fr.getPackageName();
    }
}

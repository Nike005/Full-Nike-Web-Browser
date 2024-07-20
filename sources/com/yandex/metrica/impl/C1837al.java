package com.yandex.metrica.impl;

import android.content.Context;

/* renamed from: com.yandex.metrica.impl.al */
public final class C1837al {
    /* renamed from: a */
    public static boolean m4248a(Context context, String str) {
        if (str != null) {
            try {
                if (context.checkCallingOrSelfPermission(str) != 0) {
                    return false;
                }
            } catch (Exception unused) {
                return false;
            }
        }
        return true;
    }

    /* renamed from: a */
    public static boolean m4247a(Context context) {
        return m4248a(context, "android.permission.ACCESS_COARSE_LOCATION") || m4248a(context, "android.permission.ACCESS_FINE_LOCATION");
    }
}

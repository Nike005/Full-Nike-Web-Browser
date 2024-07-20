package com.yandex.metrica.impl.p050ob;

import android.content.Context;
import android.content.SharedPreferences;

/* renamed from: com.yandex.metrica.impl.ob.dl */
public class C2069dl {
    /* renamed from: a */
    public static SharedPreferences m5401a(Context context, String str) {
        return context.getSharedPreferences(context.getPackageName() + str, 0);
    }

    /* renamed from: a */
    public static void m5402a(SharedPreferences sharedPreferences, String str, int i) {
        if (sharedPreferences != null && sharedPreferences.contains(str)) {
            try {
                sharedPreferences.edit().remove(str).putLong(str, (long) sharedPreferences.getInt(str, i)).apply();
            } catch (ClassCastException unused) {
            }
        }
    }
}

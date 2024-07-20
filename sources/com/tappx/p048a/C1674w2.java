package com.tappx.p048a;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/* renamed from: com.tappx.a.w2 */
class C1674w2 {
    /* renamed from: a */
    private static SharedPreferences m3544a(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    /* renamed from: b */
    public static void m3546b(Context context, String str, String str2) {
        SharedPreferences.Editor edit = m3544a(context).edit();
        edit.putString(str, str2);
        edit.apply();
    }

    /* renamed from: a */
    public static String m3545a(Context context, String str, String str2) {
        return m3544a(context).getString(str, str2).trim();
    }
}

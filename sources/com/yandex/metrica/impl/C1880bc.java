package com.yandex.metrica.impl;

import android.os.Build;
import org.apache.commons.lang3.StringUtils;

/* renamed from: com.yandex.metrica.impl.bc */
public class C1880bc {
    /* renamed from: a */
    public static String m4536a() {
        return "2.73";
    }

    /* renamed from: b */
    public static boolean m4538b() {
        return m4541c("com.yandex.metrica.YandexMetricaInternal");
    }

    /* renamed from: c */
    public static String m4540c() {
        return C1881a.f3084a;
    }

    /* renamed from: a */
    public static String m4537a(String str) {
        String str2;
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append("/");
        sb.append(m4536a());
        sb.append(".7854 (");
        if (Build.MODEL.startsWith(Build.MANUFACTURER)) {
            str2 = C1894bi.m4625b(Build.MODEL);
        } else {
            str2 = C1894bi.m4625b(Build.MANUFACTURER) + StringUtils.SPACE + Build.MODEL;
        }
        sb.append(str2);
        sb.append("; Android ");
        sb.append(Build.VERSION.RELEASE);
        sb.append(")");
        return sb.toString();
    }

    /* renamed from: com.yandex.metrica.impl.bc$a */
    static class C1881a {

        /* renamed from: a */
        static final String f3084a = new C1881a().mo17032a();

        C1881a() {
        }

        /* access modifiers changed from: package-private */
        /* renamed from: a */
        public String mo17032a() {
            if (mo17033a("com.unity3d.player.UnityPlayer")) {
                return "unity";
            }
            if (mo17033a("mono.MonoPackageManager")) {
                return "xamarin";
            }
            if (mo17033a("org.apache.cordova.CordovaPlugin")) {
                return "cordova";
            }
            return mo17033a("com.facebook.react.ReactRootView") ? "react" : "native";
        }

        /* access modifiers changed from: package-private */
        /* renamed from: a */
        public boolean mo17033a(String str) {
            return C1880bc.m4541c(str);
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: c */
    public static boolean m4541c(String str) {
        try {
            return Class.forName(str) != null;
        } catch (ClassNotFoundException unused) {
            return false;
        }
    }
}

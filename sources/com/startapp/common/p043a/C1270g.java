package com.startapp.common.p043a;

import android.util.Log;
import com.startapp.common.Constants;

/* renamed from: com.startapp.common.a.g */
/* compiled from: StartAppSDK */
public class C1270g {
    /* renamed from: a */
    public static void m2076a(int i, String str) {
        if (Constants.m1978a().booleanValue()) {
            m2077a(i, str, (Throwable) null);
        }
    }

    /* renamed from: a */
    public static void m2078a(String str, int i, String str2) {
        if (Constants.m1978a().booleanValue()) {
            m2079a(str, i, str2, (Throwable) null);
        }
    }

    /* renamed from: a */
    public static void m2077a(int i, String str, Throwable th) {
        m2079a((String) null, i, str, th);
    }

    /* renamed from: a */
    public static void m2079a(String str, int i, String str2, Throwable th) {
        String str3;
        if (str == null) {
            str3 = "";
        } else {
            str3 = "." + str;
        }
        if (Constants.m1978a().booleanValue()) {
            StringBuffer stringBuffer = new StringBuffer(str2);
            if (stringBuffer.length() > 4000) {
                m2080b("startapp" + str3, i, "sb.length = " + stringBuffer.length(), th);
                int length = stringBuffer.length() / 4000;
                int i2 = 0;
                while (i2 <= length) {
                    int i3 = i2 + 1;
                    int i4 = i3 * 4000;
                    if (i4 >= stringBuffer.length()) {
                        m2080b("startapp" + str3, i, i2 + "/" + length + ":" + stringBuffer.substring(i2 * 4000), (Throwable) null);
                    } else {
                        m2080b("startapp" + str3, i, i2 + "/" + length + ":" + stringBuffer.substring(i2 * 4000, i4), (Throwable) null);
                    }
                    i2 = i3;
                }
                return;
            }
            m2080b("startapp" + str3, i, str2, th);
        }
    }

    /* renamed from: b */
    private static void m2080b(String str, int i, String str2, Throwable th) {
        if (i == 2) {
            Log.v(str, str2, th);
        } else if (i == 3) {
            Log.d(str, str2, th);
        } else if (i == 4) {
            Log.i(str, str2, th);
        } else if (i == 5) {
            Log.w(str, str2, th);
        } else if (i == 6) {
            Log.e(str, str2, th);
        }
    }
}

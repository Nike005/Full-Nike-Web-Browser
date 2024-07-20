package com.yandex.metrica.impl;

import android.text.TextUtils;
import java.util.regex.Pattern;

/* renamed from: com.yandex.metrica.impl.bi */
public final class C1894bi {
    /* renamed from: b */
    public static String m4626b(String str, String str2) {
        return str == null ? str2 : str;
    }

    static {
        Pattern.compile("[^0-9a-zA-Z,`’\\.\\+\\-'\\s\"]");
        Pattern.compile("\\s+");
    }

    /* renamed from: a */
    public static boolean m4623a(String str, String str2) {
        if (str == null && str2 == null) {
            return true;
        }
        if (str == null || str2 == null) {
            return false;
        }
        return str.equals(str2);
    }

    /* renamed from: a */
    public static boolean m4622a(String str) {
        return str == null || str.length() == 0;
    }

    /* renamed from: a */
    public static boolean m4624a(String... strArr) {
        if (strArr == null) {
            return false;
        }
        for (String a : strArr) {
            if (m4622a(a)) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: c */
    public static String m4628c(String str, String str2) {
        return m4622a(str) ? str2 : str;
    }

    /* renamed from: b */
    public static String m4625b(String str) {
        if (m4622a(str)) {
            return "";
        }
        char charAt = str.charAt(0);
        if (Character.isUpperCase(charAt)) {
            return str;
        }
        return Character.toUpperCase(charAt) + str.substring(1);
    }

    /* renamed from: c */
    public static byte[] m4629c(String str) {
        try {
            return str.getBytes("UTF-8");
        } catch (Exception unused) {
            return new byte[0];
        }
    }

    /* renamed from: b */
    public static final String m4627b(String... strArr) {
        return TextUtils.join(",", strArr);
    }
}

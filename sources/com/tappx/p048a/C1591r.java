package com.tappx.p048a;

/* renamed from: com.tappx.a.r */
public class C1591r {
    /* renamed from: a */
    public static String m3293a(String str) {
        return m3294a(str, 256);
    }

    /* renamed from: a */
    private static String m3294a(String str, int i) {
        if (str == null || str.trim().isEmpty()) {
            return null;
        }
        return str.length() > i ? str.substring(0, i) : str;
    }
}

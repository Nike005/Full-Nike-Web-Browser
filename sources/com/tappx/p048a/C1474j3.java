package com.tappx.p048a;

/* renamed from: com.tappx.a.j3 */
public enum C1474j3 {
    ANY,
    PORTRAIT,
    LANDSCAPE;

    /* renamed from: a */
    public static C1474j3 m2883a(String str) {
        if (str == null) {
            return null;
        }
        try {
            return valueOf(str);
        } catch (Exception unused) {
            return ANY;
        }
    }

    /* renamed from: a */
    public static String m2884a(C1474j3 j3Var) {
        if (j3Var == null) {
            return null;
        }
        return j3Var.name();
    }
}

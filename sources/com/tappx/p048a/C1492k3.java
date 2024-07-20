package com.tappx.p048a;

/* renamed from: com.tappx.a.k3 */
public enum C1492k3 {
    NONE,
    FROM_RIGHT,
    FROM_LEFT,
    FROM_LEFT_BOUNCE,
    FROM_RIGHT_BOUNCE,
    RANDOM;

    /* renamed from: a */
    public static C1492k3 m2928a(String str) {
        if (str == null) {
            return null;
        }
        try {
            return valueOf(str);
        } catch (Exception unused) {
            return null;
        }
    }

    /* renamed from: a */
    public static String m2929a(C1492k3 k3Var) {
        if (k3Var == null) {
            return null;
        }
        return k3Var.name();
    }
}

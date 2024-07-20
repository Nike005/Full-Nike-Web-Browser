package p055a.p056a.p063e;

import p055a.p056a.p058b.p060b.C2928h;

/* renamed from: a.a.e.k */
/* compiled from: StartAppSDK */
class C2963k extends C2962j {
    /* renamed from: a */
    public static /* bridge */ /* synthetic */ boolean m6176a(String str, String str2, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return C2955c.m6175a(str, str2, z);
    }

    /* renamed from: a */
    public static final boolean m6175a(String str, String str2, boolean z) {
        C2928h.m6157b(str, "$receiver");
        C2928h.m6157b(str2, "prefix");
        if (!z) {
            return str.startsWith(str2);
        }
        return C2955c.m6174a(str, 0, str2, 0, str2.length(), z);
    }

    /* renamed from: a */
    public static final boolean m6174a(String str, int i, String str2, int i2, int i3, boolean z) {
        C2928h.m6157b(str, "$receiver");
        C2928h.m6157b(str2, "other");
        if (!z) {
            return str.regionMatches(i, str2, i2, i3);
        }
        return str.regionMatches(z, i, str2, i2, i3);
    }
}

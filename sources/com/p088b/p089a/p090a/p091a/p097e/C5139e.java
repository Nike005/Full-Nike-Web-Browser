package com.p088b.p089a.p090a.p091a.p097e;

import android.text.TextUtils;
import com.p088b.p089a.p090a.p091a.C5102a;
import com.p088b.p089a.p090a.p091a.p093b.C5115f;
import com.p088b.p089a.p090a.p091a.p093b.C5118i;

/* renamed from: com.b.a.a.a.e.e */
public class C5139e {
    /* renamed from: a */
    public static void m7133a() {
        if (!C5102a.m6972b()) {
            throw new IllegalStateException("Method called before OMID activation");
        }
    }

    /* renamed from: a */
    public static void m7134a(C5115f fVar) {
        if (fVar.equals(C5115f.NONE)) {
            throw new IllegalArgumentException("Impression owner is none");
        }
    }

    /* renamed from: a */
    public static void m7135a(C5118i iVar) {
        if (iVar.mo41855j()) {
            throw new IllegalStateException("AdSession is started");
        }
    }

    /* renamed from: a */
    public static void m7136a(Object obj, String str) {
        if (obj == null) {
            throw new IllegalArgumentException(str);
        }
    }

    /* renamed from: a */
    public static void m7137a(String str, int i, String str2) {
        if (str.length() > i) {
            throw new IllegalArgumentException(str2);
        }
    }

    /* renamed from: a */
    public static void m7138a(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException(str2);
        }
    }

    /* renamed from: b */
    public static void m7139b(C5118i iVar) {
        if (iVar.mo41856k()) {
            throw new IllegalStateException("AdSession is finished");
        }
    }

    /* renamed from: c */
    public static void m7140c(C5118i iVar) {
        m7145h(iVar);
        m7139b(iVar);
    }

    /* renamed from: d */
    public static void m7141d(C5118i iVar) {
        if (iVar.mo41851f().mo41917d() != null) {
            throw new IllegalStateException("AdEvents already exists for AdSession");
        }
    }

    /* renamed from: e */
    public static void m7142e(C5118i iVar) {
        if (iVar.mo41851f().mo41918e() != null) {
            throw new IllegalStateException("VideoEvents already exists for AdSession");
        }
    }

    /* renamed from: f */
    public static void m7143f(C5118i iVar) {
        if (!iVar.mo41857l()) {
            throw new IllegalStateException("Impression event is not expected from the Native AdSession");
        }
    }

    /* renamed from: g */
    public static void m7144g(C5118i iVar) {
        if (!iVar.mo41858m()) {
            throw new IllegalStateException("Cannot create VideoEvents for JavaScript AdSession");
        }
    }

    /* renamed from: h */
    private static void m7145h(C5118i iVar) {
        if (!iVar.mo41855j()) {
            throw new IllegalStateException("AdSession is not started");
        }
    }
}

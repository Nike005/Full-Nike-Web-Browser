package com.moat.analytics.mobile.mpub;

import android.util.Log;
import android.view.View;
import com.mopub.mobileads.VastExtensionXmlManager;

/* renamed from: com.moat.analytics.mobile.mpub.p */
class C0336p {
    C0336p() {
    }

    /* renamed from: a */
    static String m226a(View view) {
        if (view == null) {
            return "null";
        }
        return view.getClass().getSimpleName() + "@" + view.hashCode();
    }

    /* renamed from: a */
    private static String m227a(String str) {
        return VastExtensionXmlManager.MOAT + str;
    }

    /* renamed from: a */
    static void m228a(int i, String str, Object obj, String str2) {
        String str3;
        if (C0351w.m264a().f198b) {
            String a = m227a(str);
            if (obj == null) {
                str3 = String.format("message = %s", new Object[]{str2});
            } else {
                str3 = String.format("id = %s, message = %s", new Object[]{Integer.valueOf(obj.hashCode()), str2});
            }
            Log.println(i, a, str3);
        }
    }

    /* renamed from: a */
    static void m229a(String str, int i, String str2, Object obj, String str3) {
        m228a(i, str2, obj, str3);
        m231a(str, str3);
    }

    /* renamed from: a */
    static void m230a(String str, Object obj, String str2, Throwable th) {
        if (C0351w.m264a().f198b) {
            Log.e(m227a(str), String.format("id = %s, message = %s", new Object[]{Integer.valueOf(obj.hashCode()), str2}), th);
        }
    }

    /* renamed from: a */
    static void m231a(String str, String str2) {
        if (!C0351w.m264a().f198b && ((C0327k) MoatAnalytics.getInstance()).f148a) {
            int i = 2;
            if (str.equals("[ERROR] ")) {
                i = 6;
            }
            Log.println(i, "MoatAnalytics", str + str2);
        }
    }

    /* renamed from: b */
    static void m232b(int i, String str, Object obj, String str2) {
        if (C0351w.m264a().f199c) {
            String a = m227a(str);
            Object[] objArr = new Object[2];
            objArr[0] = obj == null ? "null" : Integer.valueOf(obj.hashCode());
            objArr[1] = str2;
            Log.println(i, a, String.format("id = %s, message = %s", objArr));
        }
    }
}

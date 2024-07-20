package com.startapp.android.publish.adsCommon.p034g.p035a;

import android.content.Context;
import android.webkit.WebView;
import com.integralads.avid.library.mopub.utils.AvidJSONUtil;
import com.startapp.android.publish.adsCommon.Utils.C1060h;
import com.startapp.android.publish.adsCommon.Utils.C1061i;
import com.startapp.common.p043a.C1270g;

/* renamed from: com.startapp.android.publish.adsCommon.g.a.c */
/* compiled from: StartAppSDK */
public final class C1140c {
    /* renamed from: a */
    public static void m1544a(String str, WebView webView) {
        C1270g.m2078a("MraidJsFunctions", 3, "setPlacementType: " + str);
        C1061i.m1188a(webView, "mraid.setPlacementType", str);
    }

    /* renamed from: a */
    public static void m1543a(C1141d dVar, WebView webView) {
        C1270g.m2078a("MraidJsFunctions", 3, "fireStateChangeEvent: " + dVar);
        C1061i.m1188a(webView, "mraid.fireStateChangeEvent", dVar.toString());
    }

    /* renamed from: a */
    public static void m1539a(Context context, int i, int i2, WebView webView) {
        C1270g.m2078a("MraidJsFunctions", 3, "setScreenSize " + i + AvidJSONUtil.KEY_X + i2);
        C1061i.m1188a(webView, "mraid.setScreenSize", Integer.valueOf(C1060h.m1171b(context, i)), Integer.valueOf(C1060h.m1171b(context, i2)));
    }

    /* renamed from: b */
    public static void m1546b(Context context, int i, int i2, WebView webView) {
        C1270g.m2078a("MraidJsFunctions", 3, "setMaxSize " + i + AvidJSONUtil.KEY_X + i2);
        C1061i.m1188a(webView, "mraid.setMaxSize", Integer.valueOf(C1060h.m1171b(context, i)), Integer.valueOf(C1060h.m1171b(context, i2)));
    }

    /* renamed from: a */
    public static void m1538a(Context context, int i, int i2, int i3, int i4, WebView webView) {
        C1270g.m2078a("MraidJsFunctions", 3, "setCurrentPosition [" + i + "," + i2 + "] (" + i3 + AvidJSONUtil.KEY_X + i4 + ")");
        C1061i.m1188a(webView, "mraid.setCurrentPosition", Integer.valueOf(C1060h.m1171b(context, i)), Integer.valueOf(C1060h.m1171b(context, i2)), Integer.valueOf(C1060h.m1171b(context, i3)), Integer.valueOf(C1060h.m1171b(context, i4)));
    }

    /* renamed from: b */
    public static void m1545b(Context context, int i, int i2, int i3, int i4, WebView webView) {
        C1270g.m2078a("MraidJsFunctions", 3, "setDefaultPosition [" + i + "," + i2 + "] (" + i3 + AvidJSONUtil.KEY_X + i4 + ")");
        C1061i.m1188a(webView, "mraid.setDefaultPosition", Integer.valueOf(C1060h.m1171b(context, i)), Integer.valueOf(C1060h.m1171b(context, i2)), Integer.valueOf(C1060h.m1171b(context, i3)), Integer.valueOf(C1060h.m1171b(context, i4)));
    }

    /* renamed from: a */
    public static void m1540a(WebView webView) {
        C1270g.m2078a("MraidJsFunctions", 3, "fireReadyEvent");
        C1061i.m1188a(webView, "mraid.fireReadyEvent", new Object[0]);
    }

    /* renamed from: a */
    public static void m1542a(WebView webView, boolean z) {
        C1270g.m2078a("MraidJsFunctions", 3, "fireViewableChangeEvent " + z);
        C1061i.m1188a(webView, "mraid.fireViewableChangeEvent", Boolean.valueOf(z));
    }

    /* renamed from: a */
    public static void m1541a(WebView webView, String str, boolean z) {
        C1270g.m2078a("MraidJsFunctions", 3, "setSupports feature: " + str + ", isSupported:" + z);
        C1061i.m1189a(webView, false, "mraid.setSupports", str, Boolean.valueOf(z));
    }
}

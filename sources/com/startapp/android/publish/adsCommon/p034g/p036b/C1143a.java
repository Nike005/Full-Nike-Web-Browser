package com.startapp.android.publish.adsCommon.p034g.p036b;

import android.content.Context;
import android.webkit.WebView;
import com.startapp.android.publish.adsCommon.p034g.p035a.C1140c;

/* renamed from: com.startapp.android.publish.adsCommon.g.b.a */
/* compiled from: StartAppSDK */
public final class C1143a {
    /* renamed from: a */
    public static void m1551a(Context context, WebView webView, C1144b bVar) {
        if (bVar == null) {
            bVar = new C1144b(context);
        }
        C1140c.m1541a(webView, "mraid.SUPPORTED_FEATURES.CALENDAR", bVar.mo14929a());
        C1140c.m1541a(webView, "mraid.SUPPORTED_FEATURES.INLINEVIDEO", bVar.mo14931b());
        C1140c.m1541a(webView, "mraid.SUPPORTED_FEATURES.SMS", bVar.mo14932c());
        C1140c.m1541a(webView, "mraid.SUPPORTED_FEATURES.STOREPICTURE", bVar.mo14933d());
        C1140c.m1541a(webView, "mraid.SUPPORTED_FEATURES.TEL", bVar.mo14934e());
    }
}

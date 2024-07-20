package com.truenet.android.p049a;

import android.content.Context;
import android.net.ConnectivityManager;
import p055a.p056a.C2969h;
import p055a.p056a.p058b.p060b.C2928h;

/* renamed from: com.truenet.android.a.d */
/* compiled from: StartAppSDK */
public final class C1748d {
    /* renamed from: a */
    public static final ConnectivityManager m3851a(Context context) {
        C2928h.m6157b(context, "$receiver");
        Object systemService = context.getSystemService("connectivity");
        if (systemService != null) {
            return (ConnectivityManager) systemService;
        }
        throw new C2969h("null cannot be cast to non-null type android.net.ConnectivityManager");
    }

    /* renamed from: b */
    public static final C1749e m3852b(Context context) {
        C2928h.m6157b(context, "$receiver");
        return new C1749e(context);
    }
}

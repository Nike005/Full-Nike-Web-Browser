package com.truenet.android.p049a;

import android.content.Context;
import android.telephony.TelephonyManager;
import android.view.WindowManager;
import p055a.p056a.C2969h;
import p055a.p056a.p058b.p060b.C2928h;

/* renamed from: com.truenet.android.a.c */
/* compiled from: StartAppSDK */
public final class C1747c {
    /* renamed from: a */
    public static final TelephonyManager m3849a(Context context) {
        C2928h.m6157b(context, "$receiver");
        Object systemService = context.getSystemService("phone");
        if (systemService != null) {
            return (TelephonyManager) systemService;
        }
        throw new C2969h("null cannot be cast to non-null type android.telephony.TelephonyManager");
    }

    /* renamed from: b */
    public static final WindowManager m3850b(Context context) {
        C2928h.m6157b(context, "$receiver");
        Object systemService = context.getSystemService("window");
        if (systemService != null) {
            return (WindowManager) systemService;
        }
        throw new C2969h("null cannot be cast to non-null type android.view.WindowManager");
    }
}

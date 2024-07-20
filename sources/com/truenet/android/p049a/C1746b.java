package com.truenet.android.p049a;

import android.content.res.Configuration;
import android.os.Build;
import java.util.Locale;
import p055a.p056a.p058b.p060b.C2928h;

/* renamed from: com.truenet.android.a.b */
/* compiled from: StartAppSDK */
public final class C1746b {
    /* renamed from: a */
    public static final Locale m3848a(Configuration configuration) {
        C2928h.m6157b(configuration, "$receiver");
        return Build.VERSION.SDK_INT >= 24 ? configuration.getLocales().get(0) : configuration.locale;
    }
}

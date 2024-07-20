package com.tappx.sdk.android;

import android.content.Context;
import com.tappx.p048a.C1547o;
import com.tappx.p048a.C1552o2;

public abstract class Tappx {
    public static TappxPrivacyManager getPrivacyManager(Context context) {
        return C1552o2.m3165a(context).mo16034a();
    }

    public static String getVersion() {
        return BuildConfig.SDK_VERSION;
    }

    protected static void sbmp(boolean z) {
        C1547o.f2135a = z;
        C1547o.f2136b = z;
    }
}

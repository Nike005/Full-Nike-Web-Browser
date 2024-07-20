package com.startapp.android.publish.ads.splash;

import android.content.Context;
import com.startapp.android.publish.adsCommon.adListeners.AdEventListener;
import com.startapp.android.publish.common.model.AdPreferences;
import com.startapp.android.publish.html.C1239a;

/* renamed from: com.startapp.android.publish.ads.splash.a */
/* compiled from: StartAppSDK */
public class C0938a extends C1239a {
    public C0938a(Context context, C0939b bVar, AdPreferences adPreferences, AdEventListener adEventListener) {
        super(context, bVar, adPreferences, adEventListener, AdPreferences.Placement.INAPP_SPLASH, true);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo13754a(Boolean bool) {
        super.mo13754a(bool);
        mo15405a(bool.booleanValue());
    }
}

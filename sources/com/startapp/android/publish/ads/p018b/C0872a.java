package com.startapp.android.publish.ads.p018b;

import android.content.Context;
import com.startapp.android.publish.adsCommon.C1118e;
import com.startapp.android.publish.adsCommon.adListeners.AdEventListener;
import com.startapp.android.publish.common.model.AdPreferences;
import com.startapp.android.publish.html.C1239a;

/* renamed from: com.startapp.android.publish.ads.b.a */
/* compiled from: StartAppSDK */
public class C0872a extends C1239a {
    public C0872a(Context context, C1118e eVar, AdPreferences adPreferences, AdEventListener adEventListener) {
        super(context, eVar, adPreferences, adEventListener, AdPreferences.Placement.INAPP_OVERLAY, true);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo13754a(Boolean bool) {
        super.mo13754a(bool);
        mo15405a(bool.booleanValue());
    }
}

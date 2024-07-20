package com.startapp.android.publish.ads.nativead;

import android.content.Context;
import com.startapp.android.publish.adsCommon.C1148h;
import com.startapp.android.publish.adsCommon.adListeners.AdEventListener;
import com.startapp.android.publish.common.model.AdPreferences;

/* renamed from: com.startapp.android.publish.ads.nativead.b */
/* compiled from: StartAppSDK */
public class C0936b extends C1148h {
    private static final long serialVersionUID = 1;
    private NativeAdPreferences config;

    public C0936b(Context context, NativeAdPreferences nativeAdPreferences) {
        super(context, AdPreferences.Placement.INAPP_NATIVE);
        this.config = nativeAdPreferences;
    }

    /* access modifiers changed from: protected */
    public void loadAds(AdPreferences adPreferences, AdEventListener adEventListener) {
        new C0935a(this.context, this, adPreferences, adEventListener, this.config).mo14816c();
    }
}

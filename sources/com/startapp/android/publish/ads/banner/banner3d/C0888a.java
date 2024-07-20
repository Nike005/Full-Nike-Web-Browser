package com.startapp.android.publish.ads.banner.banner3d;

import android.content.Context;
import com.startapp.android.publish.adsCommon.C1148h;
import com.startapp.android.publish.adsCommon.adListeners.AdEventListener;
import com.startapp.android.publish.common.model.AdPreferences;

/* renamed from: com.startapp.android.publish.ads.banner.banner3d.a */
/* compiled from: StartAppSDK */
public class C0888a extends C1148h {
    private static final long serialVersionUID = 1;
    private boolean fixedSize;
    private int offset;

    public C0888a(Context context, int i) {
        super(context, AdPreferences.Placement.INAPP_BANNER);
        this.offset = i;
    }

    /* access modifiers changed from: protected */
    public void loadAds(AdPreferences adPreferences, AdEventListener adEventListener) {
        new C0893c(this.context, this, this.offset, adPreferences, adEventListener).mo14816c();
        this.offset++;
    }

    /* renamed from: a */
    public int mo13946a() {
        return this.offset;
    }

    /* renamed from: a */
    public void mo13947a(boolean z) {
        this.fixedSize = z;
    }

    /* renamed from: b */
    public boolean mo13948b() {
        return this.fixedSize;
    }
}

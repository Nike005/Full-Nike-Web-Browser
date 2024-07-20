package com.startapp.android.publish.ads.banner.bannerstandard;

import android.content.Context;
import com.startapp.android.publish.adsCommon.C1118e;
import com.startapp.android.publish.adsCommon.adListeners.AdEventListener;
import com.startapp.android.publish.common.model.AdPreferences;

/* renamed from: com.startapp.android.publish.ads.banner.bannerstandard.a */
/* compiled from: StartAppSDK */
public class C0899a extends C1118e {
    private static final long serialVersionUID = 1;
    private int bannerType;
    private boolean fixedSize;
    private int offset = 0;

    public C0899a(Context context, int i) {
        super(context, AdPreferences.Placement.INAPP_BANNER);
        this.offset = i;
    }

    /* access modifiers changed from: protected */
    public void loadAds(AdPreferences adPreferences, AdEventListener adEventListener) {
        new C0900b(this.context, this, this.offset, adPreferences, adEventListener).mo14816c();
        this.offset++;
    }

    /* renamed from: a */
    public int mo13829a() {
        return this.offset;
    }

    /* renamed from: a */
    public void mo13970a(boolean z) {
        this.fixedSize = z;
    }

    /* renamed from: b */
    public boolean mo13971b() {
        return this.fixedSize;
    }

    /* renamed from: c */
    public int mo13972c() {
        return this.bannerType;
    }

    /* renamed from: a */
    public void mo13969a(int i) {
        this.bannerType = i;
    }
}

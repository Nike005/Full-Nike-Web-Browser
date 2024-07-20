package com.startapp.android.publish.ads.nativead;

import android.content.Context;
import com.startapp.android.publish.adsCommon.C1040Ad;
import com.startapp.android.publish.adsCommon.adListeners.AdEventListener;
import com.startapp.android.publish.common.model.AdPreferences;
import com.startapp.android.publish.common.model.GetAdRequest;
import com.startapp.android.publish.p016a.C0847a;

/* renamed from: com.startapp.android.publish.ads.nativead.a */
/* compiled from: StartAppSDK */
public class C0935a extends C0847a {

    /* renamed from: g */
    private NativeAdPreferences f619g;

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo13753a(C1040Ad ad) {
    }

    public C0935a(Context context, C1040Ad ad, AdPreferences adPreferences, AdEventListener adEventListener, NativeAdPreferences nativeAdPreferences) {
        super(context, ad, adPreferences, adEventListener, AdPreferences.Placement.INAPP_NATIVE);
        this.f619g = nativeAdPreferences;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public GetAdRequest mo13957a() {
        GetAdRequest a = super.mo13957a();
        if (a == null) {
            return null;
        }
        a.setAdsNumber(this.f619g.getAdsNumber());
        if (this.f619g.getImageSize() != null) {
            a.setWidth(this.f619g.getImageSize().getWidth());
            a.setHeight(this.f619g.getImageSize().getHeight());
        } else {
            int primaryImageSize = this.f619g.getPrimaryImageSize();
            int i = 2;
            if (primaryImageSize == -1) {
                primaryImageSize = 2;
            }
            a.setPrimaryImg(Integer.toString(primaryImageSize));
            int secondaryImageSize = this.f619g.getSecondaryImageSize();
            if (secondaryImageSize != -1) {
                i = secondaryImageSize;
            }
            a.setMoreImg(Integer.toString(i));
        }
        if (this.f619g.isContentAd()) {
            a.setContentAd(this.f619g.isContentAd());
        }
        return a;
    }
}

package com.startapp.android.publish.ads.p019c.p021b;

import android.content.Context;
import android.content.Intent;
import com.startapp.android.publish.ads.list3d.C0926f;
import com.startapp.android.publish.ads.list3d.List3DActivity;
import com.startapp.android.publish.adsCommon.AdsConstants;
import com.startapp.android.publish.adsCommon.C1040Ad;
import com.startapp.android.publish.adsCommon.C1103c;
import com.startapp.android.publish.adsCommon.C1136g;
import com.startapp.android.publish.adsCommon.C1148h;
import com.startapp.android.publish.adsCommon.Utils.C1061i;
import com.startapp.android.publish.adsCommon.adListeners.AdDisplayListener;
import com.startapp.android.publish.adsCommon.adListeners.AdEventListener;
import com.startapp.android.publish.common.model.AdPreferences;
import java.util.UUID;

/* renamed from: com.startapp.android.publish.ads.c.b.b */
/* compiled from: StartAppSDK */
public class C0906b extends C1148h implements C1136g {

    /* renamed from: a */
    private static String f495a = null;
    private static final long serialVersionUID = 1;
    private final String uuid = UUID.randomUUID().toString();

    public C0906b(Context context) {
        super(context, AdPreferences.Placement.INAPP_OFFER_WALL);
        if (f495a == null) {
            f495a = C1061i.m1203c(context);
        }
    }

    /* access modifiers changed from: protected */
    public void loadAds(AdPreferences adPreferences, AdEventListener adEventListener) {
        new C0905a(this.context, this, adPreferences, adEventListener).mo14816c();
    }

    /* renamed from: a */
    public boolean mo13830a(String str) {
        C0926f.m744a().mo14097a(this.uuid).mo14093b(C1103c.m1366a());
        boolean a = this.activityExtra != null ? this.activityExtra.mo14647a() : false;
        if (hasAdCacheTtlPassed()) {
            setNotDisplayedReason(AdDisplayListener.NotDisplayedReason.AD_EXPIRED);
            return false;
        }
        Intent intent = new Intent(this.context, List3DActivity.class);
        intent.putExtra("adInfoOverride", getAdInfoOverride());
        intent.putExtra("fullscreen", a);
        intent.putExtra("adTag", str);
        intent.putExtra("lastLoadTime", getLastLoadTime());
        intent.putExtra("adCacheTtl", getAdCacheTtl());
        intent.putExtra("position", C1103c.m1392b());
        intent.putExtra("listModelUuid", this.uuid);
        intent.addFlags(343932928);
        this.context.startActivity(intent);
        if (AdsConstants.OVERRIDE_NETWORK.booleanValue()) {
            return true;
        }
        setState(C1040Ad.AdState.UN_INITIALIZED);
        return true;
    }

    /* renamed from: a_ */
    public String mo13831a_() {
        return f495a;
    }

    /* renamed from: a */
    public String mo13946a() {
        return this.uuid;
    }

    public Long getLastLoadTime() {
        return super.getLastLoadTime();
    }

    public Long getAdCacheTtl() {
        return super.getAdCacheTtl();
    }

    public boolean hasAdCacheTtlPassed() {
        return super.hasAdCacheTtlPassed();
    }

    public boolean getVideoCancelCallBack() {
        return super.getVideoCancelCallBack();
    }

    public void setVideoCancelCallBack(boolean z) {
        super.setVideoCancelCallBack(z);
    }
}

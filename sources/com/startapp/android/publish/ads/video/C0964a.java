package com.startapp.android.publish.ads.video;

import android.content.Context;
import android.util.Pair;
import com.startapp.android.publish.ads.video.C1038h;
import com.startapp.android.publish.adsCommon.C1040Ad;
import com.startapp.android.publish.adsCommon.Utils.C1055d;
import com.startapp.android.publish.adsCommon.Utils.C1056e;
import com.startapp.android.publish.adsCommon.Utils.C1061i;
import com.startapp.android.publish.common.model.AdPreferences;
import com.startapp.android.publish.common.model.GetAdRequest;

/* renamed from: com.startapp.android.publish.ads.video.a */
/* compiled from: StartAppSDK */
public class C0964a extends GetAdRequest {

    /* renamed from: a */
    private GetAdRequest.VideoRequestType f675a;

    /* renamed from: b */
    private GetAdRequest.VideoRequestMode f676b = GetAdRequest.VideoRequestMode.INTERSTITIAL;

    public void fillAdPreferences(Context context, AdPreferences adPreferences, AdPreferences.Placement placement, Pair<String, String> pair) {
        super.fillAdPreferences(context, adPreferences, placement, pair);
        m822a(context);
        m821a();
    }

    /* renamed from: a */
    private void m821a() {
        if (getType() == C1040Ad.AdType.REWARDED_VIDEO) {
            this.f676b = GetAdRequest.VideoRequestMode.REWARDED;
        }
        if (getType() == C1040Ad.AdType.VIDEO) {
            this.f676b = GetAdRequest.VideoRequestMode.INTERSTITIAL;
        }
    }

    /* renamed from: a */
    private void m822a(Context context) {
        if (getType() != null) {
            if (getType() == C1040Ad.AdType.NON_VIDEO) {
                this.f675a = GetAdRequest.VideoRequestType.DISABLED;
            } else if (getType() == C1040Ad.AdType.VIDEO_NO_VAST) {
                this.f675a = GetAdRequest.VideoRequestType.FORCED_NONVAST;
            } else if (isAdTypeVideo()) {
                this.f675a = GetAdRequest.VideoRequestType.FORCED;
            }
        } else if (C1038h.m1114a(context) != C1038h.C1039a.ELIGIBLE) {
            this.f675a = GetAdRequest.VideoRequestType.DISABLED;
        } else if (!C1061i.m1194a(2)) {
            this.f675a = GetAdRequest.VideoRequestType.FORCED;
        } else {
            this.f675a = GetAdRequest.VideoRequestType.ENABLED;
        }
    }

    public C1056e getNameValueMap() {
        C1056e nameValueMap = super.getNameValueMap();
        if (nameValueMap == null) {
            nameValueMap = new C1055d();
        }
        nameValueMap.mo14631a("video", (Object) this.f675a, false);
        nameValueMap.mo14631a("videoMode", (Object) this.f676b, false);
        return nameValueMap;
    }
}

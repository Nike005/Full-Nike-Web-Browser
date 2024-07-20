package com.startapp.android.publish.ads.p018b;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import com.startapp.android.publish.ads.splash.C0939b;
import com.startapp.android.publish.adsCommon.AdsConstants;
import com.startapp.android.publish.adsCommon.C1040Ad;
import com.startapp.android.publish.adsCommon.C1098b;
import com.startapp.android.publish.adsCommon.C1103c;
import com.startapp.android.publish.adsCommon.C1118e;
import com.startapp.android.publish.adsCommon.C1136g;
import com.startapp.android.publish.adsCommon.C1182n;
import com.startapp.android.publish.adsCommon.Utils.C1061i;
import com.startapp.android.publish.adsCommon.activities.AppWallActivity;
import com.startapp.android.publish.adsCommon.activities.FullScreenActivity;
import com.startapp.android.publish.adsCommon.activities.OverlayActivity;
import com.startapp.android.publish.adsCommon.adListeners.AdDisplayListener;
import com.startapp.android.publish.common.model.AdPreferences;

/* renamed from: com.startapp.android.publish.ads.b.c */
/* compiled from: StartAppSDK */
public abstract class C0874c extends C1118e implements C1136g {
    private static final long serialVersionUID = 1;

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public boolean mo13829a() {
        return false;
    }

    public C0874c(Context context, AdPreferences.Placement placement) {
        super(context, placement);
    }

    /* JADX WARNING: type inference failed for: r10v5, types: [java.lang.Boolean[], java.io.Serializable] */
    /* renamed from: a */
    public boolean mo13830a(String str) {
        String b = C1103c.m1392b();
        if (!mo13829a() || !C1098b.m1303a().mo14754H().mo15023a().equals(C1182n.C1183a.DISABLED) || !b.equals("back")) {
            if (!AdsConstants.OVERRIDE_NETWORK.booleanValue()) {
                setState(C1040Ad.AdState.UN_INITIALIZED);
            }
            if (mo14843f() == null) {
                setNotDisplayedReason(AdDisplayListener.NotDisplayedReason.INTERNAL_ERROR);
                return false;
            } else if (hasAdCacheTtlPassed()) {
                setNotDisplayedReason(AdDisplayListener.NotDisplayedReason.AD_EXPIRED);
                return false;
            } else {
                boolean a = this.activityExtra != null ? this.activityExtra.mo14647a() : false;
                Intent intent = new Intent(this.context, m560f(b));
                intent.putExtra("fileUrl", "exit.html");
                String[] l = mo14849l();
                String a2 = C1103c.m1366a();
                for (int i = 0; i < l.length; i++) {
                    if (l[i] != null && !"".equals(l[i])) {
                        l[i] = l[i] + a2;
                    }
                }
                intent.putExtra("tracking", l);
                intent.putExtra("trackingClickUrl", mo14850m());
                intent.putExtra("packageNames", mo14852o());
                intent.putExtra("htmlUuid", mo14844g());
                intent.putExtra("smartRedirect", this.smartRedirect);
                intent.putExtra("browserEnabled", mo14848k());
                intent.putExtra("placement", this.placement.getIndex());
                intent.putExtra("adInfoOverride", getAdInfoOverride());
                intent.putExtra("ad", this);
                intent.putExtra("videoAd", mo13829a());
                intent.putExtra("fullscreen", a);
                intent.putExtra("orientation", mo13832b());
                intent.putExtra("adTag", str);
                intent.putExtra("lastLoadTime", getLastLoadTime());
                intent.putExtra("adCacheTtl", getAdCacheTtl());
                intent.putExtra("closingUrl", mo14846i());
                if (mo14853p() != null) {
                    intent.putExtra("delayImpressionSeconds", mo14853p());
                }
                intent.putExtra("sendRedirectHops", mo14854q());
                intent.putExtra("mraidAd", mo14855r());
                if (mo14855r()) {
                    intent.putExtra("activityShouldLockOrientation", false);
                }
                if (C1061i.m1194a(8) && (this instanceof C0939b)) {
                    intent.putExtra("isSplash", true);
                }
                intent.putExtra("position", b);
                intent.addFlags(343932928);
                this.context.startActivity(intent);
                return true;
            }
        } else {
            setNotDisplayedReason(AdDisplayListener.NotDisplayedReason.VIDEO_BACK);
            return false;
        }
    }

    /* renamed from: f */
    private Class<?> m560f(String str) {
        if (m561g(str)) {
            return FullScreenActivity.class;
        }
        return C1061i.m1174a(getContext(), (Class<? extends Activity>) OverlayActivity.class, (Class<? extends Activity>) AppWallActivity.class);
    }

    /* renamed from: g */
    private boolean m561g(String str) {
        return (mo14370d() || mo13829a() || mo14855r() || str.equals("back")) && C1061i.m1196a(getContext(), (Class<? extends Activity>) FullScreenActivity.class);
    }

    /* renamed from: d */
    private boolean mo14370d() {
        return (mo14851n() == 0 || mo14851n() == this.context.getResources().getConfiguration().orientation) ? false : true;
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public int mo13832b() {
        return mo14851n() == 0 ? this.context.getResources().getConfiguration().orientation : mo14851n();
    }

    /* renamed from: a_ */
    public String mo13831a_() {
        return super.mo13831a_();
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

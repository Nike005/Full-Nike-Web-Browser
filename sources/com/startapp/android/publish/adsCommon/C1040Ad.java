package com.startapp.android.publish.adsCommon;

import android.content.Context;
import com.startapp.android.publish.adsCommon.Utils.C1059g;
import com.startapp.android.publish.adsCommon.Utils.C1061i;
import com.startapp.android.publish.adsCommon.adListeners.AdDisplayListener;
import com.startapp.android.publish.adsCommon.adListeners.AdEventListener;
import com.startapp.android.publish.adsCommon.adListeners.C1080b;
import com.startapp.android.publish.adsCommon.adinformation.C1093c;
import com.startapp.android.publish.cache.C1196d;
import com.startapp.android.publish.common.metaData.C1231d;
import com.startapp.android.publish.common.metaData.MetaData;
import com.startapp.android.publish.common.model.AdPreferences;
import java.io.Serializable;

/* renamed from: com.startapp.android.publish.adsCommon.Ad */
/* compiled from: StartAppSDK */
public abstract class C1040Ad implements Serializable {
    private static boolean init = false;
    private static final long serialVersionUID = 1;
    protected C1067a activityExtra;
    protected Long adCacheTtl = null;
    private C1093c adInfoOverride;
    protected boolean belowMinCPM = false;
    protected transient Context context;
    protected String errorMessage = null;
    protected Serializable extraData = null;
    private Long lastLoadTime = null;
    private AdDisplayListener.NotDisplayedReason notDisplayedReason;
    protected AdPreferences.Placement placement;
    private AdState state = AdState.UN_INITIALIZED;
    private AdType type;
    private boolean videoCancelCallBack;

    /* renamed from: com.startapp.android.publish.adsCommon.Ad$AdState */
    /* compiled from: StartAppSDK */
    public enum AdState {
        UN_INITIALIZED,
        PROCESSING,
        READY
    }

    /* renamed from: com.startapp.android.publish.adsCommon.Ad$AdType */
    /* compiled from: StartAppSDK */
    public enum AdType {
        INTERSTITIAL,
        RICH_TEXT,
        VIDEO,
        REWARDED_VIDEO,
        NON_VIDEO,
        VIDEO_NO_VAST
    }

    /* access modifiers changed from: protected */
    public abstract void loadAds(AdPreferences adPreferences, AdEventListener adEventListener);

    @Deprecated
    public boolean show() {
        return false;
    }

    public C1040Ad(Context context2, AdPreferences.Placement placement2) {
        this.context = context2;
        this.placement = placement2;
        if (C1061i.m1206e()) {
            this.adInfoOverride = C1093c.m1277a();
        }
    }

    public Serializable getExtraData() {
        return this.extraData;
    }

    public Context getContext() {
        return this.context;
    }

    public void setContext(Context context2) {
        this.context = context2;
    }

    public void setActivityExtra(C1067a aVar) {
        this.activityExtra = aVar;
    }

    public void setExtraData(Serializable serializable) {
        this.extraData = serializable;
    }

    public AdState getState() {
        return this.state;
    }

    public boolean isBelowMinCPM() {
        return this.belowMinCPM;
    }

    public void setState(AdState adState) {
        this.state = adState;
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }

    public void setErrorMessage(String str) {
        this.errorMessage = str;
    }

    public C1093c getAdInfoOverride() {
        return this.adInfoOverride;
    }

    public void setAdInfoOverride(C1093c cVar) {
        this.adInfoOverride = cVar;
    }

    /* access modifiers changed from: protected */
    public AdPreferences.Placement getPlacement() {
        return this.placement;
    }

    /* access modifiers changed from: protected */
    public void setPlacement(AdPreferences.Placement placement2) {
        this.placement = placement2;
    }

    @Deprecated
    public boolean load() {
        return load(new AdPreferences(), (AdEventListener) null);
    }

    @Deprecated
    public boolean load(AdEventListener adEventListener) {
        return load(new AdPreferences(), adEventListener);
    }

    @Deprecated
    public boolean load(AdPreferences adPreferences) {
        return load(adPreferences, (AdEventListener) null);
    }

    @Deprecated
    public boolean load(AdPreferences adPreferences, AdEventListener adEventListener) {
        return load(adPreferences, adEventListener, true);
    }

    /* access modifiers changed from: protected */
    public boolean load(final AdPreferences adPreferences, AdEventListener adEventListener, boolean z) {
        boolean z2;
        final C1080b bVar = new C1080b(adEventListener);
        final C10411 r10 = new AdEventListener() {
            public void onReceiveAd(C1040Ad ad) {
                C1040Ad.this.setLastLoadTime(Long.valueOf(System.currentTimeMillis()));
                bVar.onReceiveAd(ad);
            }

            public void onFailedToReceiveAd(C1040Ad ad) {
                bVar.onFailedToReceiveAd(ad);
            }
        };
        if (!init) {
            C1168l.m1638c(this.context);
            init = true;
        }
        C1061i.m1184a(this.context, adPreferences);
        String str = "";
        if (adPreferences.getProductId() == null || str.equals(adPreferences.getProductId())) {
            str = "app ID was not set.";
            z2 = true;
        } else {
            z2 = false;
        }
        if (this.state != AdState.UN_INITIALIZED) {
            str = "load() was already called.";
            z2 = true;
        }
        if (!C1061i.m1195a(this.context)) {
            str = "network not available.";
            z2 = true;
        }
        if (!canShowAd()) {
            str = "serving ads disabled";
            z2 = true;
        }
        if (z2) {
            setErrorMessage("Ad wasn't loaded: " + str);
            r10.onFailedToReceiveAd(this);
            return false;
        }
        setState(AdState.PROCESSING);
        C10422 r7 = new C1231d() {
            /* renamed from: a */
            public void mo14204a() {
                MetaData.getInstance().removeMetaDataListener(this);
                C1040Ad.this.loadAds(adPreferences, r10);
            }

            /* renamed from: b */
            public void mo14205b() {
                MetaData.getInstance().removeMetaDataListener(this);
                C1040Ad.this.loadAds(adPreferences, r10);
            }
        };
        if (adPreferences.getType() != null) {
            setType(adPreferences.getType());
        }
        MetaData.getInstance().loadFromServer(this.context, adPreferences, C1059g.m1157d().mo14637c(), z, r7);
        return true;
    }

    public boolean isReady() {
        return this.state == AdState.READY && !hasAdCacheTtlPassed();
    }

    public AdDisplayListener.NotDisplayedReason getNotDisplayedReason() {
        return this.notDisplayedReason;
    }

    /* access modifiers changed from: protected */
    public void setNotDisplayedReason(AdDisplayListener.NotDisplayedReason notDisplayedReason2) {
        this.notDisplayedReason = notDisplayedReason2;
    }

    /* access modifiers changed from: protected */
    public Long getAdCacheTtl() {
        long fallbackAdCacheTtl = getFallbackAdCacheTtl();
        Long l = this.adCacheTtl;
        if (l != null) {
            fallbackAdCacheTtl = Math.min(l.longValue(), fallbackAdCacheTtl);
        }
        return Long.valueOf(fallbackAdCacheTtl);
    }

    /* access modifiers changed from: protected */
    public long getFallbackAdCacheTtl() {
        return C1196d.m1803a().mo15089b().getAdCacheTtl();
    }

    /* access modifiers changed from: protected */
    public Long getLastLoadTime() {
        return this.lastLoadTime;
    }

    /* access modifiers changed from: private */
    public void setLastLoadTime(Long l) {
        this.lastLoadTime = l;
    }

    /* access modifiers changed from: protected */
    public boolean hasAdCacheTtlPassed() {
        if (this.lastLoadTime != null && System.currentTimeMillis() - this.lastLoadTime.longValue() > getAdCacheTtl().longValue()) {
            return true;
        }
        return false;
    }

    private void setType(AdType adType) {
        this.type = adType;
    }

    public AdType getType() {
        return this.type;
    }

    /* access modifiers changed from: protected */
    public boolean getVideoCancelCallBack() {
        return this.videoCancelCallBack;
    }

    /* access modifiers changed from: protected */
    public void setVideoCancelCallBack(boolean z) {
        this.videoCancelCallBack = z;
    }

    /* access modifiers changed from: protected */
    public boolean canShowAd() {
        return MetaData.getInstance().canShowAd();
    }
}

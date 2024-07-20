package com.startapp.android.publish.adsCommon;

import acr.browser.lightning.activity.BrowserActivity;
import com.appnext.ads.fullscreen.RewardedVideo;
import com.startapp.common.p046c.C5303f;
import java.io.Serializable;
import java.util.concurrent.TimeUnit;

/* renamed from: com.startapp.android.publish.adsCommon.n */
/* compiled from: StartAppSDK */
public class C1182n implements Serializable {
    private static final long serialVersionUID = 1;
    @C5303f(mo45478b = C1183a.class)
    private C1183a backMode = C1183a.DISABLED;
    private int maxCachedVideos = 3;
    private int maxTimeForCachingIndicator = 10;
    private int maxVastCampaignExclude = 10;
    private int maxVastLevels = 7;
    private long minAvailableStorageRequired = 20;
    private int minTimeForCachingIndicator = 1;
    private int nativePlayerProbability = 100;
    private boolean progressive = false;
    private int progressiveBufferInPercentage = 20;
    private int progressiveInitialBufferInPercentage = 5;
    private int progressiveMinBufferToPlayFromCache = 30;
    private int rewardGrantPercentage = 100;
    private String soundMode = RewardedVideo.VIDEO_MODE_DEFAULT;
    private int vastMediaPicker = 0;
    private int vastPreferredBitrate = 0;
    private int vastRetryTimeout = BrowserActivity.ONE_MINUTE_BY_MILLISECONDS;
    private int vastTimeout = 30000;
    private int videoErrorsThreshold = 2;
    private boolean videoFallback = false;

    /* renamed from: com.startapp.android.publish.adsCommon.n$a */
    /* compiled from: StartAppSDK */
    public enum C1183a {
        DISABLED,
        SKIP,
        CLOSE,
        BOTH
    }

    /* renamed from: a */
    public C1183a mo15023a() {
        return this.backMode;
    }

    /* renamed from: b */
    public int mo15024b() {
        return this.maxCachedVideos;
    }

    /* renamed from: c */
    public long mo15025c() {
        return this.minAvailableStorageRequired;
    }

    /* renamed from: d */
    public int mo15026d() {
        return this.rewardGrantPercentage;
    }

    /* renamed from: e */
    public int mo15027e() {
        return this.videoErrorsThreshold;
    }

    /* renamed from: f */
    public long mo15028f() {
        return TimeUnit.SECONDS.toMillis((long) this.minTimeForCachingIndicator);
    }

    /* renamed from: g */
    public long mo15029g() {
        return TimeUnit.SECONDS.toMillis((long) this.maxTimeForCachingIndicator);
    }

    /* renamed from: h */
    public boolean mo15030h() {
        return this.videoFallback;
    }

    /* renamed from: i */
    public boolean mo15031i() {
        return this.progressive;
    }

    /* renamed from: j */
    public int mo15032j() {
        return this.progressiveBufferInPercentage;
    }

    /* renamed from: k */
    public int mo15033k() {
        return this.progressiveInitialBufferInPercentage;
    }

    /* renamed from: l */
    public int mo15034l() {
        return this.progressiveMinBufferToPlayFromCache;
    }

    /* renamed from: m */
    public String mo15035m() {
        return this.soundMode;
    }

    /* renamed from: n */
    public int mo15036n() {
        return this.maxVastLevels;
    }

    /* renamed from: o */
    public int mo15037o() {
        return this.vastTimeout;
    }

    /* renamed from: p */
    public int mo15038p() {
        return this.vastRetryTimeout;
    }

    /* renamed from: q */
    public int mo15039q() {
        return this.maxVastCampaignExclude;
    }

    /* renamed from: r */
    public int mo15040r() {
        return this.vastMediaPicker;
    }

    /* renamed from: s */
    public int mo15041s() {
        return this.vastPreferredBitrate;
    }
}

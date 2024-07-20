package com.startapp.android.publish.cache;

import com.startapp.android.publish.adsCommon.StartAppAd;
import com.startapp.common.p046c.C5303f;
import java.io.Serializable;
import java.util.EnumSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/* compiled from: StartAppSDK */
public class ACMConfig implements Serializable {
    public static final int DEFAULT_CACHE_SIZE = 7;
    private static final long serialVersionUID = 1;
    private long adCacheTTL = 3600;
    @C5303f(mo45478b = EnumSet.class, mo45479c = StartAppAd.AdMode.class)
    private Set<StartAppAd.AdMode> autoLoad = EnumSet.of(StartAppAd.AdMode.FULLPAGE);
    @C5303f(mo45477a = true)
    private FailuresHandler failuresHandler = new FailuresHandler();
    private boolean localCache = true;
    private int maxCacheSize = 7;
    private long returnAdCacheTTL = 3600;
    private boolean returnAdShouldLoadInBg = true;

    public long getAdCacheTtl() {
        return TimeUnit.SECONDS.toMillis(this.adCacheTTL);
    }

    public long getReturnAdCacheTTL() {
        return TimeUnit.SECONDS.toMillis(this.returnAdCacheTTL);
    }

    public Set<StartAppAd.AdMode> getAutoLoad() {
        return this.autoLoad;
    }

    public boolean isLocalCache() {
        return this.localCache;
    }

    public boolean shouldReturnAdLoadInBg() {
        return this.returnAdShouldLoadInBg;
    }

    public FailuresHandler getFailuresHandler() {
        return this.failuresHandler;
    }

    public int getMaxCacheSize() {
        return this.maxCacheSize;
    }

    public void setMaxCacheSize(int i) {
        this.maxCacheSize = i;
    }
}

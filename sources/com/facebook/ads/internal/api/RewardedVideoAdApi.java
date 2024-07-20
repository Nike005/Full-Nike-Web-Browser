package com.facebook.ads.internal.api;

import com.facebook.ads.ExtraHints;
import com.facebook.ads.FullScreenAd;
import com.facebook.ads.RewardData;
import com.facebook.ads.RewardedVideoAd;
import com.facebook.ads.RewardedVideoAdListener;

public interface RewardedVideoAdApi extends FullScreenAd {
    RewardedVideoAd.RewardedVideoAdLoadConfigBuilder buildLoadAdConfig();

    RewardedVideoAd.RewardedVideoAdShowConfigBuilder buildShowAdConfig();

    void destroy();

    String getPlacementId();

    int getVideoDuration();

    boolean isAdLoaded();

    void loadAd();

    void loadAd(RewardedVideoAd.RewardedVideoLoadAdConfig rewardedVideoLoadAdConfig);

    @Deprecated
    void loadAd(boolean z);

    @Deprecated
    void loadAdFromBid(String str);

    @Deprecated
    void loadAdFromBid(String str, boolean z);

    @Deprecated
    void setAdListener(RewardedVideoAdListener rewardedVideoAdListener);

    @Deprecated
    void setExtraHints(ExtraHints extraHints);

    @Deprecated
    void setRewardData(RewardData rewardData);

    boolean show();

    @Deprecated
    boolean show(int i);

    boolean show(RewardedVideoAd.RewardedVideoShowAdConfig rewardedVideoShowAdConfig);
}

package com.facebook.ads;

public interface RewardedVideoAdListener extends AdListener {
    void onLoggingImpression(C5169Ad ad);

    void onRewardedVideoClosed();

    void onRewardedVideoCompleted();
}

package com.appsgeyser.sdk.ads.fastTrack.adapters;

import android.content.Context;
import android.view.ViewGroup;
import com.appsgeyser.sdk.ads.fastTrack.FastTrackSdkModel;
import com.appsgeyser.sdk.ads.fastTrack.adapters.FastTrackBaseAdapter;
import java.util.ArrayList;
import java.util.List;

public class FastTrackDisabledAdapter extends FastTrackBaseAdapter {
    /* access modifiers changed from: protected */
    public void init() {
    }

    public void initBannerView(ViewGroup viewGroup, String str) {
    }

    public void loadFullscreen() {
    }

    public void loadNativeAds(int i) {
    }

    public void loadRewardedVideo() {
    }

    public void onDestroy() {
    }

    public FastTrackDisabledAdapter(FastTrackSdkModel fastTrackSdkModel, Context context) {
        super(fastTrackSdkModel, context);
    }

    public void showFullscreen(String str, String str2, boolean z) {
        if (this.fullscreenListener != null) {
            this.fullscreenListener.onRequest();
            this.fullscreenListener.onFailedToShow();
        }
    }

    public void showRewardedVideo(FastTrackBaseAdapter.RewardedVideoListener rewardedVideoListener, String str) {
        rewardedVideoListener.onVideoDeactivated();
    }

    public List<Object> getNativeAds(int i) {
        return new ArrayList();
    }
}

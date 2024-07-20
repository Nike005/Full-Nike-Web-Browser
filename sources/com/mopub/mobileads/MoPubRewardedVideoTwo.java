package com.mopub.mobileads;

import android.app.Activity;
import com.mopub.common.MoPubReward;
import com.mopub.common.logging.MoPubLog;
import com.mopub.mobileads.MoPubRewardedAd;
import com.mopub.mobileads.RewardedVastVideoInterstitialTwo;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo45499bv = {1, 0, 3}, mo45500d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u0000\n\u0002\b\u0006\u0018\u0000 \u00152\u00020\u0001:\u0002\u0015\u0016B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\t\u001a\u00020\nH\u0014J8\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0012\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00110\u00102\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n0\u0010H\u0014J\b\u0010\u0013\u001a\u00020\fH\u0014J\b\u0010\u0014\u001a\u00020\fH\u0014R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006\u0017"}, mo45501d2 = {"Lcom/mopub/mobileads/MoPubRewardedVideoTwo;", "Lcom/mopub/mobileads/MoPubRewardedAd;", "()V", "rewardedVastVideoInterstitial", "Lcom/mopub/mobileads/RewardedVastVideoInterstitialTwo;", "getRewardedVastVideoInterstitial", "()Lcom/mopub/mobileads/RewardedVastVideoInterstitialTwo;", "setRewardedVastVideoInterstitial", "(Lcom/mopub/mobileads/RewardedVastVideoInterstitialTwo;)V", "getAdNetworkId", "", "loadWithSdkInitialized", "", "activity", "Landroid/app/Activity;", "localExtras", "", "", "serverExtras", "onInvalidate", "show", "Companion", "MoPubRewardedVideoTwoListener", "mopub-sdk-rewardedvideo_release"}, mo45502k = 1, mo45503mv = {1, 1, 16})
/* compiled from: MoPubRewardedVideoTwo.kt */
public final class MoPubRewardedVideoTwo extends MoPubRewardedAd {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final String MOPUB_REWARDED_VIDEO_TWO_ID = "mopub_rewarded_video_two_id";
    private RewardedVastVideoInterstitialTwo rewardedVastVideoInterstitial = new RewardedVastVideoInterstitialTwo();

    public final RewardedVastVideoInterstitialTwo getRewardedVastVideoInterstitial() {
        return this.rewardedVastVideoInterstitial;
    }

    public final void setRewardedVastVideoInterstitial(RewardedVastVideoInterstitialTwo rewardedVastVideoInterstitialTwo) {
        this.rewardedVastVideoInterstitial = rewardedVastVideoInterstitialTwo;
    }

    /* access modifiers changed from: protected */
    public String getAdNetworkId() {
        String str = this.mAdUnitId;
        return str != null ? str : MOPUB_REWARDED_VIDEO_TWO_ID;
    }

    /* access modifiers changed from: protected */
    public void onInvalidate() {
        RewardedVastVideoInterstitialTwo rewardedVastVideoInterstitialTwo = this.rewardedVastVideoInterstitial;
        if (rewardedVastVideoInterstitialTwo != null) {
            rewardedVastVideoInterstitialTwo.onInvalidate();
        }
        this.rewardedVastVideoInterstitial = null;
        super.onInvalidate();
    }

    /* access modifiers changed from: protected */
    public void loadWithSdkInitialized(Activity activity, Map<String, ? extends Object> map, Map<String, String> map2) throws Exception {
        Intrinsics.checkParameterIsNotNull(activity, "activity");
        Intrinsics.checkParameterIsNotNull(map, "localExtras");
        Intrinsics.checkParameterIsNotNull(map2, "serverExtras");
        super.loadWithSdkInitialized(activity, map, map2);
        RewardedVastVideoInterstitialTwo rewardedVastVideoInterstitialTwo = this.rewardedVastVideoInterstitial;
        if (rewardedVastVideoInterstitialTwo != null) {
            rewardedVastVideoInterstitialTwo.loadInterstitial(activity, new MoPubRewardedVideoTwoListener(), map, map2);
            return;
        }
        MoPubLog.log(MoPubLog.AdLogEvent.CUSTOM, "rewardedVastVideoInterstitialTwo is null. Has this class been invalidated?");
    }

    /* access modifiers changed from: protected */
    public void show() {
        if (!isReady() || this.rewardedVastVideoInterstitial == null) {
            MoPubLog.log(MoPubLog.AdLogEvent.CUSTOM, "Unable to show MoPub rewarded video");
            return;
        }
        MoPubLog.log(MoPubLog.AdLogEvent.CUSTOM, "Showing MoPub rewarded video.");
        RewardedVastVideoInterstitialTwo rewardedVastVideoInterstitialTwo = this.rewardedVastVideoInterstitial;
        if (rewardedVastVideoInterstitialTwo != null) {
            rewardedVastVideoInterstitialTwo.showInterstitial();
        }
    }

    @Metadata(mo45499bv = {1, 0, 3}, mo45500d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\b\u0004\u0018\u00002\u00060\u0001R\u00020\u00022\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\b\u0010\u0005\u001a\u00020\u0006H\u0016¨\u0006\u0007"}, mo45501d2 = {"Lcom/mopub/mobileads/MoPubRewardedVideoTwo$MoPubRewardedVideoTwoListener;", "Lcom/mopub/mobileads/MoPubRewardedAd$MoPubRewardedAdListener;", "Lcom/mopub/mobileads/MoPubRewardedAd;", "Lcom/mopub/mobileads/RewardedVastVideoInterstitialTwo$RewardedVideoInterstitialListenerTwo;", "(Lcom/mopub/mobileads/MoPubRewardedVideoTwo;)V", "onVideoComplete", "", "mopub-sdk-rewardedvideo_release"}, mo45502k = 1, mo45503mv = {1, 1, 16})
    /* compiled from: MoPubRewardedVideoTwo.kt */
    private final class MoPubRewardedVideoTwoListener extends MoPubRewardedAd.MoPubRewardedAdListener implements RewardedVastVideoInterstitialTwo.RewardedVideoInterstitialListenerTwo {
        public MoPubRewardedVideoTwoListener() {
            super(MoPubRewardedVideoTwo.class);
        }

        public void onVideoComplete() {
            String rewardedAdCurrencyName = MoPubRewardedVideoTwo.this.getRewardedAdCurrencyName();
            if (rewardedAdCurrencyName != null) {
                MoPubRewardedVideoManager.onRewardedVideoCompleted(this.mCustomEventClass, MoPubRewardedVideoTwo.this.getAdNetworkId(), MoPubReward.success(rewardedAdCurrencyName, MoPubRewardedVideoTwo.this.getRewardedAdCurrencyAmount()));
                return;
            }
            MoPubLog.log(MoPubLog.AdLogEvent.CUSTOM, "No rewarded video was loaded, so no reward is possible");
        }
    }

    @Metadata(mo45499bv = {1, 0, 3}, mo45500d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0005"}, mo45501d2 = {"Lcom/mopub/mobileads/MoPubRewardedVideoTwo$Companion;", "", "()V", "MOPUB_REWARDED_VIDEO_TWO_ID", "", "mopub-sdk-rewardedvideo_release"}, mo45502k = 1, mo45503mv = {1, 1, 16})
    /* compiled from: MoPubRewardedVideoTwo.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }
}

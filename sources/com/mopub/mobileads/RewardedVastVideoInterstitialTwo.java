package com.mopub.mobileads;

import android.content.Context;
import com.mopub.common.logging.MoPubLog;
import com.mopub.mobileads.CustomEventInterstitial;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Mockable
@Metadata(mo45499bv = {1, 0, 3}, mo45500d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0017\u0018\u0000 \u00182\u00020\u0001:\u0002\u0018\u0019B\u0005¢\u0006\u0002\u0010\u0002J@\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0012\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00120\u00102\u0012\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00110\u0010H\u0016J\b\u0010\u0014\u001a\u00020\nH\u0016J\u0012\u0010\u0015\u001a\u00020\n2\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0016R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006\u001a"}, mo45501d2 = {"Lcom/mopub/mobileads/RewardedVastVideoInterstitialTwo;", "Lcom/mopub/mobileads/VastVideoInterstitialTwo;", "()V", "rewardedVideoBroadcastReceiver", "Lcom/mopub/mobileads/RewardedVideoBroadcastReceiverTwo;", "getRewardedVideoBroadcastReceiver", "()Lcom/mopub/mobileads/RewardedVideoBroadcastReceiverTwo;", "setRewardedVideoBroadcastReceiver", "(Lcom/mopub/mobileads/RewardedVideoBroadcastReceiverTwo;)V", "loadInterstitial", "", "context", "Landroid/content/Context;", "customEventInterstitialListener", "Lcom/mopub/mobileads/CustomEventInterstitial$CustomEventInterstitialListener;", "localExtras", "", "", "", "serverExtras", "onInvalidate", "onVastVideoConfigurationPrepared", "vastVideoConfig", "Lcom/mopub/mobileads/VastVideoConfig;", "Companion", "RewardedVideoInterstitialListenerTwo", "mopub-sdk-rewardedvideo_release"}, mo45502k = 1, mo45503mv = {1, 1, 16})
/* compiled from: RewardedVastVideoInterstitialTwo.kt */
public class RewardedVastVideoInterstitialTwo extends VastVideoInterstitialTwo {
    /* access modifiers changed from: private */
    public static final String ADAPTER_NAME;
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private RewardedVideoBroadcastReceiverTwo rewardedVideoBroadcastReceiver;

    @Metadata(mo45499bv = {1, 0, 3}, mo45500d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&¨\u0006\u0004"}, mo45501d2 = {"Lcom/mopub/mobileads/RewardedVastVideoInterstitialTwo$RewardedVideoInterstitialListenerTwo;", "Lcom/mopub/mobileads/CustomEventInterstitial$CustomEventInterstitialListener;", "onVideoComplete", "", "mopub-sdk-rewardedvideo_release"}, mo45502k = 1, mo45503mv = {1, 1, 16})
    /* compiled from: RewardedVastVideoInterstitialTwo.kt */
    public interface RewardedVideoInterstitialListenerTwo extends CustomEventInterstitial.CustomEventInterstitialListener {
        void onVideoComplete();
    }

    public RewardedVideoBroadcastReceiverTwo getRewardedVideoBroadcastReceiver() {
        return this.rewardedVideoBroadcastReceiver;
    }

    public void setRewardedVideoBroadcastReceiver(RewardedVideoBroadcastReceiverTwo rewardedVideoBroadcastReceiverTwo) {
        this.rewardedVideoBroadcastReceiver = rewardedVideoBroadcastReceiverTwo;
    }

    public void loadInterstitial(Context context, CustomEventInterstitial.CustomEventInterstitialListener customEventInterstitialListener, Map<String, ? extends Object> map, Map<String, String> map2) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(customEventInterstitialListener, "customEventInterstitialListener");
        Intrinsics.checkParameterIsNotNull(map, "localExtras");
        Intrinsics.checkParameterIsNotNull(map2, "serverExtras");
        MoPubLog.log(MoPubLog.AdapterLogEvent.LOAD_ATTEMPTED, ADAPTER_NAME);
        super.loadInterstitial(context, customEventInterstitialListener, map, map2);
        if (customEventInterstitialListener instanceof RewardedVideoInterstitialListenerTwo) {
            RewardedVideoBroadcastReceiverTwo rewardedVideoBroadcastReceiverTwo = new RewardedVideoBroadcastReceiverTwo((RewardedVideoInterstitialListenerTwo) customEventInterstitialListener, this.mBroadcastIdentifier);
            rewardedVideoBroadcastReceiverTwo.register(rewardedVideoBroadcastReceiverTwo, context);
            setRewardedVideoBroadcastReceiver(rewardedVideoBroadcastReceiverTwo);
        }
    }

    public void onVastVideoConfigurationPrepared(VastVideoConfig vastVideoConfig) {
        MoPubLog.log(MoPubLog.AdapterLogEvent.LOAD_SUCCESS, ADAPTER_NAME);
        if (vastVideoConfig != null) {
            vastVideoConfig.setIsRewardedVideo(true);
        }
        super.onVastVideoConfigurationPrepared(vastVideoConfig);
    }

    public void onInvalidate() {
        super.onInvalidate();
        RewardedVideoBroadcastReceiverTwo rewardedVideoBroadcastReceiver2 = getRewardedVideoBroadcastReceiver();
        if (rewardedVideoBroadcastReceiver2 != null) {
            rewardedVideoBroadcastReceiver2.unregister(getRewardedVideoBroadcastReceiver());
        }
    }

    @Metadata(mo45499bv = {1, 0, 3}, mo45500d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, mo45501d2 = {"Lcom/mopub/mobileads/RewardedVastVideoInterstitialTwo$Companion;", "", "()V", "ADAPTER_NAME", "", "getADAPTER_NAME", "()Ljava/lang/String;", "mopub-sdk-rewardedvideo_release"}, mo45502k = 1, mo45503mv = {1, 1, 16})
    /* compiled from: RewardedVastVideoInterstitialTwo.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final String getADAPTER_NAME() {
            return RewardedVastVideoInterstitialTwo.ADAPTER_NAME;
        }
    }

    static {
        String simpleName = RewardedVastVideoInterstitialTwo.class.getSimpleName();
        Intrinsics.checkExpressionValueIsNotNull(simpleName, "RewardedVastVideoInterst…wo::class.java.simpleName");
        ADAPTER_NAME = simpleName;
    }
}

package com.mopub.mobileads;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.mopub.common.Constants;
import com.mopub.common.IntentActions;
import com.mopub.mobileads.RewardedVastVideoInterstitialTwo;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Mockable
@Metadata(mo45499bv = {1, 0, 3}, mo45500d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0017\u0018\u0000 \u000f2\u00020\u0001:\u0001\u000fB\u0017\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\u0007\u001a\u00020\bH\u0016J\u0018\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0016R\u0010\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, mo45501d2 = {"Lcom/mopub/mobileads/RewardedVideoBroadcastReceiverTwo;", "Lcom/mopub/mobileads/BaseBroadcastReceiver;", "rewardedVideoListener", "Lcom/mopub/mobileads/RewardedVastVideoInterstitialTwo$RewardedVideoInterstitialListenerTwo;", "broadcastIdentifier", "", "(Lcom/mopub/mobileads/RewardedVastVideoInterstitialTwo$RewardedVideoInterstitialListenerTwo;J)V", "getIntentFilter", "Landroid/content/IntentFilter;", "onReceive", "", "context", "Landroid/content/Context;", "intent", "Landroid/content/Intent;", "Companion", "mopub-sdk-rewardedvideo_release"}, mo45502k = 1, mo45503mv = {1, 1, 16})
/* compiled from: RewardedVideoBroadcastReceiverTwo.kt */
public class RewardedVideoBroadcastReceiverTwo extends BaseBroadcastReceiver {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final IntentFilter sIntentFilter = new IntentFilter(IntentActions.ACTION_REWARDED_VIDEO_COMPLETE);
    private final RewardedVastVideoInterstitialTwo.RewardedVideoInterstitialListenerTwo rewardedVideoListener;

    public RewardedVideoBroadcastReceiverTwo(RewardedVastVideoInterstitialTwo.RewardedVideoInterstitialListenerTwo rewardedVideoInterstitialListenerTwo, long j) {
        super(j);
        this.rewardedVideoListener = rewardedVideoInterstitialListenerTwo;
    }

    public IntentFilter getIntentFilter() {
        return sIntentFilter;
    }

    public void onReceive(Context context, Intent intent) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(intent, Constants.INTENT_SCHEME);
        if (this.rewardedVideoListener != null && shouldConsumeBroadcast(intent) && Intrinsics.areEqual((Object) IntentActions.ACTION_REWARDED_VIDEO_COMPLETE, (Object) intent.getAction())) {
            this.rewardedVideoListener.onVideoComplete();
            unregister(this);
        }
    }

    @Metadata(mo45499bv = {1, 0, 3}, mo45500d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0005"}, mo45501d2 = {"Lcom/mopub/mobileads/RewardedVideoBroadcastReceiverTwo$Companion;", "", "()V", "sIntentFilter", "Landroid/content/IntentFilter;", "mopub-sdk-rewardedvideo_release"}, mo45502k = 1, mo45503mv = {1, 1, 16})
    /* compiled from: RewardedVideoBroadcastReceiverTwo.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }
}

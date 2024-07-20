package com.appsgeyser.sdk.ads.fastTrack.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.util.Log;
import android.view.ViewGroup;
import com.appsgeyser.sdk.ads.fastTrack.FastTrackAdsController;
import com.appsgeyser.sdk.ads.fastTrack.FastTrackSdkModel;
import com.appsgeyser.sdk.ads.fastTrack.adapters.FastTrackBaseAdapter;
import com.appsgeyser.sdk.configuration.Configuration;
import com.appsgeyser.sdk.configuration.Constants;
import com.appsgeyser.sdk.configuration.PreferencesCoder;
import com.appsgeyser.sdk.p087ui.AppsgeyserProgressDialog;
import java.util.HashMap;
import java.util.List;

public abstract class FastTrackBaseAdapter {
    final HashMap<String, String> appDetails = new HashMap<>();
    final HashMap<String, String> bannerDetails = new HashMap<>();
    ViewGroup bannerViewContainer;
    Context context;
    FastTrackSdkModel fastTrackSdkModel;
    FullscreenListener fullscreenListener;
    Handler handler;
    final HashMap<String, String> interstitialDetails = new HashMap<>();
    boolean isInForeground = true;
    final HashMap<String, String> nativeAdsDetails = new HashMap<>();
    boolean pendingFullscreenRequest;
    PreferencesCoder preferencesCoder;
    AppsgeyserProgressDialog progressDialog;
    final HashMap<String, String> rewardedDetails = new HashMap<>();
    String rewardedVideoCurrentPlacement;
    RewardedVideoListener rewardedVideoListener;
    boolean videoDownloadError;
    boolean videoShowRequested;

    public interface FullscreenListener {
        void onClose();

        void onFailedToShow();

        void onRequest();

        void onShow();
    }

    public interface RewardedVideoListener {
        void onVideoClicked();

        void onVideoClosed();

        void onVideoDeactivated();

        void onVideoError(String str);

        void onVideoFinished();

        void onVideoOpened();
    }

    public abstract List<Object> getNativeAds(int i);

    /* access modifiers changed from: protected */
    public abstract void init();

    public abstract void initBannerView(ViewGroup viewGroup, String str);

    public abstract void loadFullscreen();

    public abstract void loadNativeAds(int i);

    public abstract void loadRewardedVideo();

    public abstract void onDestroy();

    public abstract void showFullscreen(String str, String str2, boolean z);

    public abstract void showRewardedVideo(RewardedVideoListener rewardedVideoListener2, String str);

    FastTrackBaseAdapter(FastTrackSdkModel fastTrackSdkModel2, Context context2) {
        this.fastTrackSdkModel = fastTrackSdkModel2;
        this.context = context2;
        Configuration instance = Configuration.getInstance(context2);
        this.appDetails.put("widget_id", instance.getApplicationId());
        this.appDetails.put("wdid", instance.getApplicationId());
        if (fastTrackSdkModel2.getAdditionalReportingParams() != null) {
            try {
                this.appDetails.putAll(fastTrackSdkModel2.getAdditionalReportingParams());
            } catch (NullPointerException unused) {
                Log.d(FastTrackAdsController.fastTrackLogTag, "NPE while adding reporting params");
            }
        }
        this.handler = new Handler(context2.getMainLooper());
        this.preferencesCoder = new PreferencesCoder(context2);
        init();
    }

    /* access modifiers changed from: package-private */
    public Integer getBannerViewRefreshRate(String str) {
        Integer num = (this.fastTrackSdkModel.getBannerPlacementsRefreshTimerMap() == null || str == null) ? null : this.fastTrackSdkModel.getBannerPlacementsRefreshTimerMap().get(str);
        if (num != null) {
            return num;
        }
        return Integer.valueOf(this.fastTrackSdkModel.getDefaultBannerRefreshTimer() != null ? this.fastTrackSdkModel.getDefaultBannerRefreshTimer().intValue() : 15000);
    }

    public void onResume(Context context2) {
        Context context3 = this.context;
        if (context3 != null && !context3.equals(context2)) {
            this.context = context2;
        }
        this.isInForeground = true;
    }

    public void onPause() {
        this.isInForeground = false;
    }

    /* access modifiers changed from: package-private */
    public Integer getFullscreenIntensityPoints(String str) {
        Integer num = (this.fastTrackSdkModel.getFullscreenPlacementsIntensityMap() == null || str == null) ? null : this.fastTrackSdkModel.getFullscreenPlacementsIntensityMap().get(str);
        if (num != null) {
            return num;
        }
        return Integer.valueOf(this.fastTrackSdkModel.getDefaultFullscreenIntensity() != null ? this.fastTrackSdkModel.getDefaultFullscreenIntensity().intValue() : 100);
    }

    /* access modifiers changed from: package-private */
    public Integer getFullscreenFrequencyTimerValue() {
        Integer fullscreenFrequencyTimer = this.fastTrackSdkModel.getFullscreenFrequencyTimer();
        return Integer.valueOf((fullscreenFrequencyTimer == null || fullscreenFrequencyTimer.intValue() < 15000) ? Constants.FULLSCREEN_BANNER_DEFAULT_FREQUENCY_TIMER : fullscreenFrequencyTimer.intValue());
    }

    /* access modifiers changed from: package-private */
    public Integer getFullscreenPendingDelayTimerValue() {
        Integer fullscreenPendingDelayTimer = this.fastTrackSdkModel.getFullscreenPendingDelayTimer();
        return Integer.valueOf((fullscreenPendingDelayTimer == null || fullscreenPendingDelayTimer.intValue() < 10000) ? 15000 : fullscreenPendingDelayTimer.intValue());
    }

    public void setFullscreenListener(FullscreenListener fullscreenListener2) {
        this.fullscreenListener = fullscreenListener2;
    }

    public void showRewardedVideoWithDialog(RewardedVideoListener rewardedVideoListener2, String str, String str2, String str3, String str4) {
        if (getRewardedVideoActivationStatus(str).booleanValue()) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this.context);
            builder.setMessage(str2);
            builder.setPositiveButton(str3, new DialogInterface.OnClickListener(rewardedVideoListener2, str) {
                public final /* synthetic */ FastTrackBaseAdapter.RewardedVideoListener f$1;
                public final /* synthetic */ String f$2;

                {
                    this.f$1 = r2;
                    this.f$2 = r3;
                }

                public final void onClick(DialogInterface dialogInterface, int i) {
                    FastTrackBaseAdapter.this.lambda$showRewardedVideoWithDialog$1$FastTrackBaseAdapter(this.f$1, this.f$2, dialogInterface, i);
                }
            });
            builder.setNegativeButton(str4, $$Lambda$FastTrackBaseAdapter$KmFmpbK36z6kMdulVpRLfR8k2v8.INSTANCE);
            builder.show();
            return;
        }
        rewardedVideoListener2.onVideoDeactivated();
        Log.d(FastTrackAdsController.fastTrackLogTag, "Rewarded placement deactivated");
    }

    public /* synthetic */ void lambda$showRewardedVideoWithDialog$1$FastTrackBaseAdapter(RewardedVideoListener rewardedVideoListener2, String str, DialogInterface dialogInterface, int i) {
        this.handler.post(new Runnable(rewardedVideoListener2, str, dialogInterface) {
            public final /* synthetic */ FastTrackBaseAdapter.RewardedVideoListener f$1;
            public final /* synthetic */ String f$2;
            public final /* synthetic */ DialogInterface f$3;

            {
                this.f$1 = r2;
                this.f$2 = r3;
                this.f$3 = r4;
            }

            public final void run() {
                FastTrackBaseAdapter.this.lambda$null$0$FastTrackBaseAdapter(this.f$1, this.f$2, this.f$3);
            }
        });
    }

    public /* synthetic */ void lambda$null$0$FastTrackBaseAdapter(RewardedVideoListener rewardedVideoListener2, String str, DialogInterface dialogInterface) {
        showRewardedVideo(rewardedVideoListener2, str);
        dialogInterface.dismiss();
    }

    public Boolean getRewardedVideoActivationStatus(String str) {
        boolean z = true;
        Boolean bool = true;
        if (!(this.fastTrackSdkModel.getRewardedPlacementsActivationMap() == null || str == null)) {
            bool = this.fastTrackSdkModel.getRewardedPlacementsActivationMap().get(str);
        }
        if (bool != null) {
            z = bool.booleanValue();
        }
        return Boolean.valueOf(z);
    }

    public void setContext(Context context2) {
        this.context = context2;
    }

    public Context getContext() {
        return this.context;
    }

    public AppsgeyserProgressDialog getProgressDialog() {
        return this.progressDialog;
    }

    public void setProgressDialog(AppsgeyserProgressDialog appsgeyserProgressDialog) {
        this.progressDialog = appsgeyserProgressDialog;
    }

    public FullscreenListener getFullscreenListener() {
        return this.fullscreenListener;
    }

    public RewardedVideoListener getRewardedVideoListener() {
        return this.rewardedVideoListener;
    }
}

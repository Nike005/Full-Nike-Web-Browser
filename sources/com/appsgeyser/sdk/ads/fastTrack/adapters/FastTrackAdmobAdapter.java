package com.appsgeyser.sdk.ads.fastTrack.adapters;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import com.appsgeyser.sdk.C5051R;
import com.appsgeyser.sdk.GuidGenerator;
import com.appsgeyser.sdk.ads.fastTrack.FastTrackAdsController;
import com.appsgeyser.sdk.ads.fastTrack.FastTrackSdkModel;
import com.appsgeyser.sdk.ads.fastTrack.adapters.FastTrackAdmobAdapter;
import com.appsgeyser.sdk.ads.fastTrack.adapters.FastTrackBaseAdapter;
import com.appsgeyser.sdk.configuration.Constants;
import com.appsgeyser.sdk.p087ui.AppsgeyserProgressDialog;
import com.appsgeyser.sdk.server.StatController;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.formats.UnifiedNativeAd;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;
import com.startapp.android.publish.adsCommon.StartAppAd;
import com.startapp.android.publish.adsCommon.StartAppSDK;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class FastTrackAdmobAdapter extends FastTrackBaseAdapter {
    private AdLoader adLoader;
    /* access modifiers changed from: private */
    public String bannerPlacementId;
    /* access modifiers changed from: private */
    public boolean bannerRequestFailReported;
    /* access modifiers changed from: private */
    public AdView bannerView;
    /* access modifiers changed from: private */
    public Runnable bannerViewRefreshRunnable = new Runnable() {
        public void run() {
            if (FastTrackAdmobAdapter.this.bannerView != null) {
                Log.d(FastTrackAdsController.fastTrackLogTag, "admob banner attempt to load");
                HashMap hashMap = FastTrackAdmobAdapter.this.bannerDetails;
                hashMap.put("details", "banner id: " + FastTrackAdmobAdapter.this.bannerPlacementId);
                FastTrackAdmobAdapter.this.bannerDetails.put("uniqid", GuidGenerator.generateNewGuid());
                StatController.getInstance().sendRequestAsyncByKey(StatController.KEY_FT_BANNER_SDK_REQUEST, FastTrackAdmobAdapter.this.bannerDetails, FastTrackAdmobAdapter.this.context, true);
                FastTrackAdmobAdapter.this.bannerView.loadAd(new AdRequest.Builder().build());
                boolean unused = FastTrackAdmobAdapter.this.bannerRequestFailReported = false;
                FastTrackAdmobAdapter.this.handler.postDelayed(FastTrackAdmobAdapter.this.bannerViewRepeatRequestRunnable, 15000);
                return;
            }
            Log.d(FastTrackAdsController.fastTrackLogTag, "admob banner attempt to load failed: bannerView null");
        }
    };
    /* access modifiers changed from: private */
    public Runnable bannerViewRepeatRequestRunnable = new Runnable() {
        public void run() {
            if (FastTrackAdmobAdapter.this.bannerView != null) {
                Log.d(FastTrackAdsController.fastTrackLogTag, "admob banner repeat attempt to load");
                HashMap hashMap = FastTrackAdmobAdapter.this.bannerDetails;
                hashMap.put("details", "banner id: " + FastTrackAdmobAdapter.this.bannerPlacementId);
                if (!FastTrackAdmobAdapter.this.bannerRequestFailReported) {
                    StatController.getInstance().sendRequestAsyncByKey(StatController.KEY_FT_BANNER_SDK_NOFILL, FastTrackAdmobAdapter.this.bannerDetails, FastTrackAdmobAdapter.this.context, true);
                }
                FastTrackAdmobAdapter.this.bannerDetails.put("uniqid", GuidGenerator.generateNewGuid());
                StatController.getInstance().sendRequestAsyncByKey(StatController.KEY_FT_BANNER_SDK_REQUEST, FastTrackAdmobAdapter.this.bannerDetails, FastTrackAdmobAdapter.this.context, true);
                FastTrackAdmobAdapter.this.bannerView.loadAd(new AdRequest.Builder().addNetworkExtrasBundle(AdMobAdapter.class, FastTrackAdmobAdapter.this.extras != null ? FastTrackAdmobAdapter.this.extras : new Bundle()).build());
                boolean unused = FastTrackAdmobAdapter.this.bannerRequestFailReported = false;
                FastTrackAdmobAdapter.this.handler.postDelayed(FastTrackAdmobAdapter.this.bannerViewRepeatRequestRunnable, 15000);
            }
        }
    };
    /* access modifiers changed from: private */
    public Bundle extras;
    /* access modifiers changed from: private */
    public Runnable fullscreenPendingRequestCancelRunnable = new Runnable() {
        public final void run() {
            FastTrackAdmobAdapter.this.lambda$new$1$FastTrackAdmobAdapter();
        }
    };
    /* access modifiers changed from: private */
    public String fullscreenPlacementId;
    /* access modifiers changed from: private */
    public InterstitialAd interstitialAd;
    private List<Object> loadedNativeAds = new ArrayList();
    private String nativeAdsPlacementId;
    private RewardedVideoAd rewardedVideoAd;
    /* access modifiers changed from: private */
    public String rewardedVideoPlacementId;
    /* access modifiers changed from: private */
    public Runnable rewardedVideoShowCancelRunnable = new Runnable() {
        public final void run() {
            FastTrackAdmobAdapter.this.lambda$new$2$FastTrackAdmobAdapter();
        }
    };

    public FastTrackAdmobAdapter(FastTrackSdkModel fastTrackSdkModel, Context context) {
        super(fastTrackSdkModel, context);
    }

    /* access modifiers changed from: protected */
    public void init() {
        this.fullscreenPlacementId = this.fastTrackSdkModel.getFullscreenPlacementId();
        this.bannerPlacementId = this.fastTrackSdkModel.getBannerPlacementId();
        this.rewardedVideoPlacementId = this.fastTrackSdkModel.getRewardedVideoPlacementId();
        this.nativeAdsPlacementId = this.fastTrackSdkModel.getNativeAdsPlacementId();
        MobileAds.initialize(this.context.getApplicationContext(), this.fastTrackSdkModel.getAppId());
        String str = this.fullscreenPlacementId;
        if (str == null || str.isEmpty()) {
            Log.d(FastTrackAdsController.fastTrackLogTag, "admob fullscreen disabled");
        } else {
            this.interstitialDetails.putAll(this.appDetails);
            if (this.fastTrackSdkModel.isCustomFullscreenActivated()) {
                this.interstitialDetails.put("ad_source", StatController.FT_NETWORK_ADMOB_CUSTOM);
                this.interstitialDetails.put("net_name", StatController.FT_NETWORK_ADMOB_CUSTOM);
                this.interstitialDetails.put("net_name_FS", StatController.FT_NETWORK_ADMOB_CUSTOM);
                Log.d(FastTrackAdsController.fastTrackLogTag, "admob fullscreen: custom");
            } else {
                this.interstitialDetails.put("ad_source", StatController.FT_NETWORK_ADMOB);
                this.interstitialDetails.put("net_name", StatController.FT_NETWORK_ADMOB);
                this.interstitialDetails.put("net_name_FS", StatController.FT_NETWORK_ADMOB);
                Log.d(FastTrackAdsController.fastTrackLogTag, "admob fullscreen: platform");
            }
        }
        String str2 = this.bannerPlacementId;
        if (str2 == null || str2.isEmpty()) {
            Log.d(FastTrackAdsController.fastTrackLogTag, "admob banner disabled");
        } else {
            this.bannerDetails.putAll(this.appDetails);
            if (this.fastTrackSdkModel.isCustomBannerActivated()) {
                this.bannerDetails.put("ad_source", StatController.FT_NETWORK_ADMOB_CUSTOM);
                this.bannerDetails.put("net_name", StatController.FT_NETWORK_ADMOB_CUSTOM);
                this.bannerDetails.put("net_name_FS", StatController.FT_NETWORK_ADMOB_CUSTOM);
                Log.d(FastTrackAdsController.fastTrackLogTag, "admob banner: custom");
            } else {
                this.bannerDetails.put("ad_source", StatController.FT_NETWORK_ADMOB);
                this.bannerDetails.put("net_name", StatController.FT_NETWORK_ADMOB);
                this.bannerDetails.put("net_name_FS", StatController.FT_NETWORK_ADMOB);
                Log.d(FastTrackAdsController.fastTrackLogTag, "admob banner: platform");
            }
        }
        String str3 = this.rewardedVideoPlacementId;
        if (str3 == null || str3.isEmpty()) {
            Log.d(FastTrackAdsController.fastTrackLogTag, "admob rewarded disabled");
        } else {
            this.rewardedDetails.putAll(this.appDetails);
            if (this.fastTrackSdkModel.isCustomRewardedActivated()) {
                this.rewardedDetails.put("ad_source", StatController.FT_NETWORK_ADMOB_CUSTOM);
                this.rewardedDetails.put("net_name", StatController.FT_NETWORK_ADMOB_CUSTOM);
                this.rewardedDetails.put("net_name_FS", StatController.FT_NETWORK_ADMOB_CUSTOM);
                Log.d(FastTrackAdsController.fastTrackLogTag, "admob rewarded: custom");
            } else {
                this.rewardedDetails.put("ad_source", StatController.FT_NETWORK_ADMOB);
                this.rewardedDetails.put("net_name", StatController.FT_NETWORK_ADMOB);
                this.rewardedDetails.put("net_name_FS", StatController.FT_NETWORK_ADMOB);
                Log.d(FastTrackAdsController.fastTrackLogTag, "admob rewarded: platform");
            }
        }
        String str4 = this.nativeAdsPlacementId;
        if (str4 == null || str4.isEmpty()) {
            Log.d(FastTrackAdsController.fastTrackLogTag, "admob native disabled");
        } else {
            this.nativeAdsDetails.putAll(this.appDetails);
            if (this.fastTrackSdkModel.isCustomRewardedActivated()) {
                this.nativeAdsDetails.put("ad_source", StatController.FT_NETWORK_ADMOB_CUSTOM);
                this.nativeAdsDetails.put("net_name", StatController.FT_NETWORK_ADMOB_CUSTOM);
                this.nativeAdsDetails.put("net_name_FS", StatController.FT_NETWORK_ADMOB_CUSTOM);
                Log.d(FastTrackAdsController.fastTrackLogTag, "admob native: custom");
            } else {
                this.nativeAdsDetails.put("ad_source", StatController.FT_NETWORK_ADMOB);
                this.nativeAdsDetails.put("net_name", StatController.FT_NETWORK_ADMOB);
                this.nativeAdsDetails.put("net_name_FS", StatController.FT_NETWORK_ADMOB);
                Log.d(FastTrackAdsController.fastTrackLogTag, "admob native: platform");
            }
        }
        if (this.fastTrackSdkModel.getStartAppId() != null && !this.fastTrackSdkModel.getStartAppId().isEmpty()) {
            StartAppSDK.init(this.context, this.fastTrackSdkModel.getStartAppId(), false);
            StartAppAd.disableSplash();
            StartAppSDK.setUserConsent(this.context, "pas", System.currentTimeMillis(), true);
        }
        String contentRating = this.fastTrackSdkModel.getContentRating();
        this.extras = new Bundle();
        if (contentRating != null && !contentRating.isEmpty()) {
            this.extras.putSerializable("max_ad_content_rating", contentRating);
        }
    }

    public void initBannerView(final ViewGroup viewGroup, String str) {
        final Integer bannerViewRefreshRate = getBannerViewRefreshRate(str);
        String str2 = this.bannerPlacementId;
        if (str2 != null && !str2.isEmpty() && bannerViewRefreshRate.intValue() != 0) {
            Log.d(FastTrackAdsController.fastTrackLogTag, "admob banner initializing: " + this.bannerPlacementId);
            AdView adView = new AdView(this.context);
            this.bannerView = adView;
            adView.setAdSize(AdSize.SMART_BANNER);
            this.bannerView.setAdUnitId(this.bannerPlacementId);
            this.bannerView.setAdListener(new AdListener() {
                public void onAdClosed() {
                    super.onAdClosed();
                    Log.d(FastTrackAdsController.fastTrackLogTag, "admob banner onAdClosed");
                }

                public void onAdFailedToLoad(int i) {
                    super.onAdFailedToLoad(i);
                    if (i == 3) {
                        HashMap hashMap = FastTrackAdmobAdapter.this.bannerDetails;
                        hashMap.put("details", "banner id: " + FastTrackAdmobAdapter.this.bannerPlacementId);
                        StatController.getInstance().sendRequestAsyncByKey(StatController.KEY_FT_BANNER_SDK_NOFILL, FastTrackAdmobAdapter.this.bannerDetails, FastTrackAdmobAdapter.this.context, true);
                    } else {
                        HashMap hashMap2 = FastTrackAdmobAdapter.this.bannerDetails;
                        hashMap2.put("details", "banner id: " + FastTrackAdmobAdapter.this.bannerPlacementId + "; error_desc: error code " + i);
                        StatController.getInstance().sendRequestAsyncByKey(StatController.KEY_FT_BANNER_SDK_ERROR, FastTrackAdmobAdapter.this.bannerDetails, FastTrackAdmobAdapter.this.context, true);
                    }
                    boolean unused = FastTrackAdmobAdapter.this.bannerRequestFailReported = true;
                    Log.d(FastTrackAdsController.fastTrackLogTag, "admob banner onAdFailedToLoad: " + i);
                }

                public void onAdLeftApplication() {
                    super.onAdLeftApplication();
                    HashMap hashMap = FastTrackAdmobAdapter.this.bannerDetails;
                    hashMap.put("details", "banner id: " + FastTrackAdmobAdapter.this.bannerPlacementId);
                    StatController.getInstance().sendRequestAsyncByKey(StatController.KEY_FT_BANNER_SDK_CLICK, FastTrackAdmobAdapter.this.bannerDetails, FastTrackAdmobAdapter.this.context, true);
                    Log.d(FastTrackAdsController.fastTrackLogTag, "admob banner onAdLeftApplication");
                }

                public void onAdOpened() {
                    super.onAdOpened();
                    Log.d(FastTrackAdsController.fastTrackLogTag, "admob banner onAdOpened");
                }

                public void onAdLoaded() {
                    super.onAdLoaded();
                    if (viewGroup == null || FastTrackAdmobAdapter.this.bannerView == null) {
                        Log.d(FastTrackAdsController.fastTrackLogTag, "admob banner loaded, but bannerViewContainer is null");
                    } else {
                        HashMap hashMap = FastTrackAdmobAdapter.this.bannerDetails;
                        hashMap.put("details", "banner id: " + FastTrackAdmobAdapter.this.bannerPlacementId);
                        StatController.getInstance().sendRequestAsyncByKey(StatController.KEY_FT_BANNER_SDK_IMPRESSION, FastTrackAdmobAdapter.this.bannerDetails, FastTrackAdmobAdapter.this.context, true);
                        viewGroup.setVisibility(0);
                        FastTrackAdmobAdapter.this.handler.removeCallbacks(FastTrackAdmobAdapter.this.bannerViewRepeatRequestRunnable);
                        FastTrackAdmobAdapter.this.handler.removeCallbacks(FastTrackAdmobAdapter.this.bannerViewRefreshRunnable);
                        FastTrackAdmobAdapter.this.handler.postDelayed(FastTrackAdmobAdapter.this.bannerViewRefreshRunnable, (long) bannerViewRefreshRate.intValue());
                    }
                    Log.d(FastTrackAdsController.fastTrackLogTag, "admob banner onAdLoaded");
                }

                public void onAdClicked() {
                    super.onAdClicked();
                    Log.d(FastTrackAdsController.fastTrackLogTag, "admob banner onAdClicked");
                }

                public void onAdImpression() {
                    super.onAdImpression();
                    Log.d(FastTrackAdsController.fastTrackLogTag, "admob banner onAdImpression");
                }
            });
            Log.d(FastTrackAdsController.fastTrackLogTag, "admob banner attempt to attach bannerView to container");
            this.bannerViewContainer = viewGroup;
            this.bannerViewContainer.addView(this.bannerView);
            this.handler.post(this.bannerViewRefreshRunnable);
        }
    }

    public void loadFullscreen() {
        String str = this.fullscreenPlacementId;
        if (str != null && !str.isEmpty()) {
            InterstitialAd interstitialAd2 = new InterstitialAd(this.context);
            this.interstitialAd = interstitialAd2;
            interstitialAd2.setAdUnitId(this.fullscreenPlacementId);
            Log.d(FastTrackAdsController.fastTrackLogTag, "admob fullscreen initializing: " + this.fullscreenPlacementId);
            this.interstitialAd.setAdListener(new AdListener() {
                public void onAdClosed() {
                    super.onAdClosed();
                    if (FastTrackAdmobAdapter.this.progressDialog != null && FastTrackAdmobAdapter.this.progressDialog.isShowing()) {
                        try {
                            FastTrackAdmobAdapter.this.progressDialog.dismiss();
                        } catch (IllegalArgumentException unused) {
                            Log.d(FastTrackAdsController.fastTrackLogTag, "progressDialog dismissal IAE");
                        }
                    }
                    FastTrackAdmobAdapter.this.loadFullscreen();
                    if (FastTrackAdmobAdapter.this.fullscreenListener != null) {
                        FastTrackAdmobAdapter.this.fullscreenListener.onClose();
                    }
                    Log.d(FastTrackAdsController.fastTrackLogTag, "admob fs onAdClosed");
                }

                public void onAdFailedToLoad(int i) {
                    super.onAdFailedToLoad(i);
                    if (i == 3) {
                        HashMap hashMap = FastTrackAdmobAdapter.this.interstitialDetails;
                        hashMap.put("details", "fs id: " + FastTrackAdmobAdapter.this.fullscreenPlacementId);
                        StatController.getInstance().sendRequestAsyncByKey(StatController.KEY_FT_INTERSTITIAL_SDK_NOFILL, FastTrackAdmobAdapter.this.interstitialDetails, FastTrackAdmobAdapter.this.context, true);
                    } else {
                        HashMap hashMap2 = FastTrackAdmobAdapter.this.interstitialDetails;
                        hashMap2.put("details", "fs id: " + FastTrackAdmobAdapter.this.fullscreenPlacementId + "; error_desc: error code " + i);
                        StatController.getInstance().sendRequestAsyncByKey(StatController.KEY_FT_INTERSTITIAL_SDK_ERROR, FastTrackAdmobAdapter.this.interstitialDetails, FastTrackAdmobAdapter.this.context, true);
                    }
                    FastTrackAdmobAdapter.this.handler.postDelayed(new Runnable() {
                        public final void run() {
                            FastTrackAdmobAdapter.C50594.this.lambda$onAdFailedToLoad$0$FastTrackAdmobAdapter$4();
                        }
                    }, 30000);
                    Log.d(FastTrackAdsController.fastTrackLogTag, "admob fs onAdFailedToLoad " + i);
                }

                public /* synthetic */ void lambda$onAdFailedToLoad$0$FastTrackAdmobAdapter$4() {
                    FastTrackAdmobAdapter.this.loadFullscreen();
                }

                public void onAdLeftApplication() {
                    super.onAdLeftApplication();
                    HashMap hashMap = FastTrackAdmobAdapter.this.interstitialDetails;
                    hashMap.put("details", "fs id: " + FastTrackAdmobAdapter.this.fullscreenPlacementId);
                    StatController.getInstance().sendRequestAsyncByKey(StatController.KEY_FT_INTERSTITIAL_SDK_CLICK, FastTrackAdmobAdapter.this.interstitialDetails, FastTrackAdmobAdapter.this.context, true);
                    Log.d(FastTrackAdsController.fastTrackLogTag, "admob fs onAdLeftApplication");
                }

                public void onAdOpened() {
                    super.onAdOpened();
                    HashMap hashMap = FastTrackAdmobAdapter.this.interstitialDetails;
                    hashMap.put("details", "fs id: " + FastTrackAdmobAdapter.this.fullscreenPlacementId);
                    StatController.getInstance().sendRequestAsyncByKey(StatController.KEY_FT_INTERSTITIAL_SDK_IMPRESSION, FastTrackAdmobAdapter.this.interstitialDetails, FastTrackAdmobAdapter.this.context, true);
                    if (FastTrackAdmobAdapter.this.fullscreenListener != null) {
                        FastTrackAdmobAdapter.this.fullscreenListener.onShow();
                    }
                    Log.d(FastTrackAdsController.fastTrackLogTag, "admob fs onAdOpened");
                }

                public void onAdLoaded() {
                    super.onAdLoaded();
                    if (FastTrackAdmobAdapter.this.pendingFullscreenRequest && FastTrackAdmobAdapter.this.isInForeground) {
                        FastTrackAdmobAdapter.this.pendingFullscreenRequest = false;
                        Log.d(FastTrackAdsController.fastTrackLogTag, "admob fullscreen loaded, pending request processing");
                        FastTrackAdmobAdapter.this.handler.removeCallbacks(FastTrackAdmobAdapter.this.fullscreenPendingRequestCancelRunnable);
                        AppsgeyserProgressDialog appsgeyserProgressDialog = FastTrackAdmobAdapter.this.progressDialog;
                        appsgeyserProgressDialog.show();
                        FastTrackAdmobAdapter.this.handler.postDelayed(new Runnable(appsgeyserProgressDialog) {
                            public final /* synthetic */ AppsgeyserProgressDialog f$1;

                            {
                                this.f$1 = r2;
                            }

                            public final void run() {
                                FastTrackAdmobAdapter.C50594.this.lambda$onAdLoaded$1$FastTrackAdmobAdapter$4(this.f$1);
                            }
                        }, 2000);
                    }
                    Log.d(FastTrackAdsController.fastTrackLogTag, "admob fs onAdLoaded");
                }

                public /* synthetic */ void lambda$onAdLoaded$1$FastTrackAdmobAdapter$4(AppsgeyserProgressDialog appsgeyserProgressDialog) {
                    try {
                        appsgeyserProgressDialog.dismiss();
                    } catch (IllegalArgumentException unused) {
                        Log.d(FastTrackAdsController.fastTrackLogTag, "progressDialog dismissal IAE");
                    }
                    FastTrackAdmobAdapter.this.interstitialAd.show();
                }

                public void onAdClicked() {
                    super.onAdClicked();
                    Log.d(FastTrackAdsController.fastTrackLogTag, "admob fs onAdClicked");
                }

                public void onAdImpression() {
                    super.onAdImpression();
                    Log.d(FastTrackAdsController.fastTrackLogTag, "admob fs onAdImpression");
                }
            });
            Log.d(FastTrackAdsController.fastTrackLogTag, "admob fullscreen attempt to load");
            HashMap hashMap = this.interstitialDetails;
            hashMap.put("details", "fs id: " + this.fullscreenPlacementId);
            this.interstitialDetails.put("uniqid", GuidGenerator.generateNewGuid());
            InterstitialAd interstitialAd3 = this.interstitialAd;
            AdRequest.Builder builder = new AdRequest.Builder();
            Class<AdMobAdapter> cls = AdMobAdapter.class;
            Bundle bundle = this.extras;
            if (bundle == null) {
                bundle = new Bundle();
            }
            interstitialAd3.loadAd(builder.addNetworkExtrasBundle(cls, bundle).build());
            StatController.getInstance().sendRequestAsyncByKey(StatController.KEY_FT_INTERSTITIAL_SDK_REQUEST, this.interstitialDetails, this.context, true);
        }
    }

    public void showFullscreen(String str, String str2, boolean z) {
        if (!z || System.currentTimeMillis() - this.preferencesCoder.getPrefLong(Constants.PREFS_APPSGEYSER_FULLSCREEN_LAST_REQUEST_TIMING, 0) > ((long) getFullscreenFrequencyTimerValue().intValue())) {
            this.preferencesCoder.savePrefLong(Constants.PREFS_APPSGEYSER_FULLSCREEN_LAST_REQUEST_TIMING, System.currentTimeMillis());
            Log.d(FastTrackAdsController.fastTrackLogTag, "admob fullscreen show request");
            if (this.fullscreenListener != null) {
                this.fullscreenListener.onRequest();
            }
            if (new Random().nextInt(100) + 1 > getFullscreenIntensityPoints(str2).intValue()) {
                Log.d(FastTrackAdsController.fastTrackLogTag, "admob fullscreen attempt to show canceled due to intensity settings");
                if (this.fullscreenListener != null) {
                    this.fullscreenListener.onFailedToShow();
                }
            } else if (this.interstitialAd != null) {
                HashMap hashMap = this.interstitialDetails;
                hashMap.put("details", "fs id: " + this.fullscreenPlacementId);
                StatController.getInstance().sendRequestAsyncByKey(StatController.KEY_FT_INTERSTITIAL_SDK_ATTEMPT, this.interstitialDetails, this.context, true);
                if (this.interstitialAd.isLoaded()) {
                    Log.d(FastTrackAdsController.fastTrackLogTag, "admob fullscreen attempt to show");
                    AppsgeyserProgressDialog appsgeyserProgressDialog = this.progressDialog;
                    appsgeyserProgressDialog.show();
                    this.handler.postDelayed(new Runnable(appsgeyserProgressDialog) {
                        public final /* synthetic */ AppsgeyserProgressDialog f$1;

                        {
                            this.f$1 = r2;
                        }

                        public final void run() {
                            FastTrackAdmobAdapter.this.lambda$showFullscreen$0$FastTrackAdmobAdapter(this.f$1);
                        }
                    }, 2000);
                    return;
                }
                Log.d(FastTrackAdsController.fastTrackLogTag, "admob fullscreen not loaded yet, waiting for load");
                this.pendingFullscreenRequest = true;
                this.handler.postDelayed(this.fullscreenPendingRequestCancelRunnable, (long) getFullscreenPendingDelayTimerValue().intValue());
            } else {
                Log.d(FastTrackAdsController.fastTrackLogTag, "admob fullscreen disabled");
                if (this.fullscreenListener != null) {
                    this.fullscreenListener.onFailedToShow();
                }
            }
        } else {
            Log.d(FastTrackAdsController.fastTrackLogTag, "admob fullscreen show request was cancelled due to frequency timing settings");
        }
    }

    public /* synthetic */ void lambda$showFullscreen$0$FastTrackAdmobAdapter(AppsgeyserProgressDialog appsgeyserProgressDialog) {
        try {
            appsgeyserProgressDialog.dismiss();
        } catch (IllegalArgumentException unused) {
            Log.d(FastTrackAdsController.fastTrackLogTag, "progressDialog dismissal IAE");
        }
        this.interstitialAd.show();
    }

    public /* synthetic */ void lambda$new$1$FastTrackAdmobAdapter() {
        this.pendingFullscreenRequest = false;
        if (this.fullscreenListener != null) {
            this.fullscreenListener.onFailedToShow();
        }
        Log.d(FastTrackAdsController.fastTrackLogTag, "admob fullscreen not loaded, cancelling wait");
    }

    public void loadRewardedVideo() {
        String str = this.rewardedVideoPlacementId;
        if (str != null && !str.isEmpty()) {
            RewardedVideoAd rewardedVideoAdInstance = MobileAds.getRewardedVideoAdInstance(this.context);
            this.rewardedVideoAd = rewardedVideoAdInstance;
            rewardedVideoAdInstance.setRewardedVideoAdListener(new RewardedVideoAdListener() {
                public void onRewardedVideoAdLoaded() {
                    FastTrackAdmobAdapter.this.videoDownloadError = false;
                    if (FastTrackAdmobAdapter.this.rewardedVideoListener != null) {
                        FastTrackAdmobAdapter fastTrackAdmobAdapter = FastTrackAdmobAdapter.this;
                        fastTrackAdmobAdapter.showRewardedVideo(fastTrackAdmobAdapter.rewardedVideoListener, FastTrackAdmobAdapter.this.rewardedVideoCurrentPlacement);
                    }
                    FastTrackAdmobAdapter.this.handler.removeCallbacks(FastTrackAdmobAdapter.this.rewardedVideoShowCancelRunnable);
                    Log.d(FastTrackAdsController.fastTrackLogTag, "admob rewarded onRewardedVideoAdLoaded");
                }

                public void onRewardedVideoAdOpened() {
                    FastTrackAdmobAdapter.this.progressDialog.dismiss();
                    HashMap hashMap = FastTrackAdmobAdapter.this.rewardedDetails;
                    hashMap.put("details", "rewarded id: " + FastTrackAdmobAdapter.this.rewardedVideoPlacementId);
                    StatController.getInstance().sendRequestAsyncByKey(StatController.KEY_FT_REWARDED_SDK_IMPRESSION, FastTrackAdmobAdapter.this.rewardedDetails, FastTrackAdmobAdapter.this.context, true);
                    FastTrackAdmobAdapter.this.videoShowRequested = false;
                    if (FastTrackAdmobAdapter.this.rewardedVideoListener != null) {
                        FastTrackAdmobAdapter.this.rewardedVideoListener.onVideoOpened();
                    }
                    Log.d(FastTrackAdsController.fastTrackLogTag, "admob rewarded onRewardedVideoAdOpened");
                }

                public void onRewardedVideoStarted() {
                    Log.d(FastTrackAdsController.fastTrackLogTag, "admob rewarded onRewardedVideoStarted");
                }

                public void onRewardedVideoAdClosed() {
                    if (FastTrackAdmobAdapter.this.rewardedVideoListener != null) {
                        FastTrackAdmobAdapter.this.rewardedVideoListener.onVideoClosed();
                        FastTrackAdmobAdapter.this.rewardedVideoListener = null;
                    }
                    FastTrackAdmobAdapter.this.loadRewardedVideo();
                    Log.d(FastTrackAdsController.fastTrackLogTag, "admob rewarded onRewardedVideoAdClosed");
                }

                public void onRewarded(RewardItem rewardItem) {
                    if (FastTrackAdmobAdapter.this.rewardedVideoListener != null) {
                        FastTrackAdmobAdapter.this.rewardedVideoListener.onVideoFinished();
                    }
                    HashMap hashMap = FastTrackAdmobAdapter.this.rewardedDetails;
                    hashMap.put("details", "rewarded id: " + FastTrackAdmobAdapter.this.rewardedVideoPlacementId);
                    StatController.getInstance().sendRequestAsyncByKey(StatController.KEY_FT_REWARDED_SDK_COMPLETION, FastTrackAdmobAdapter.this.rewardedDetails, FastTrackAdmobAdapter.this.context, true);
                    Log.d(FastTrackAdsController.fastTrackLogTag, "admob rewarded onRewarded");
                }

                public void onRewardedVideoAdLeftApplication() {
                    HashMap hashMap = FastTrackAdmobAdapter.this.rewardedDetails;
                    hashMap.put("details", "rewarded id: " + FastTrackAdmobAdapter.this.rewardedVideoPlacementId);
                    StatController.getInstance().sendRequestAsyncByKey(StatController.KEY_FT_REWARDED_SDK_CLICK, FastTrackAdmobAdapter.this.rewardedDetails, FastTrackAdmobAdapter.this.context, true);
                    if (FastTrackAdmobAdapter.this.rewardedVideoListener != null) {
                        FastTrackAdmobAdapter.this.rewardedVideoListener.onVideoClicked();
                    }
                    Log.d(FastTrackAdsController.fastTrackLogTag, "admob rewarded onRewardedVideoAdLeftApplication");
                }

                public void onRewardedVideoAdFailedToLoad(int i) {
                    FastTrackAdmobAdapter.this.videoDownloadError = true;
                    if (FastTrackAdmobAdapter.this.rewardedVideoListener != null) {
                        if (FastTrackAdmobAdapter.this.videoShowRequested) {
                            FastTrackAdmobAdapter.this.rewardedVideoListener.onVideoError(FastTrackAdmobAdapter.this.context.getResources().getString(C5051R.string.appsgeysersdk_fasttrack_no_rew_video));
                            FastTrackAdmobAdapter.this.videoShowRequested = false;
                        }
                        FastTrackAdmobAdapter.this.rewardedVideoListener = null;
                    }
                    if (i == 3) {
                        HashMap hashMap = FastTrackAdmobAdapter.this.rewardedDetails;
                        hashMap.put("details", "rewarded id: " + FastTrackAdmobAdapter.this.rewardedVideoPlacementId);
                        StatController.getInstance().sendRequestAsyncByKey(StatController.KEY_FT_REWARDED_SDK_NOFILL, FastTrackAdmobAdapter.this.rewardedDetails, FastTrackAdmobAdapter.this.context, true);
                    } else {
                        HashMap hashMap2 = FastTrackAdmobAdapter.this.rewardedDetails;
                        hashMap2.put("details", "rewarded id: " + FastTrackAdmobAdapter.this.rewardedVideoPlacementId + "; error_desc: error code " + i);
                        StatController.getInstance().sendRequestAsyncByKey(StatController.KEY_FT_REWARDED_SDK_ERROR, FastTrackAdmobAdapter.this.rewardedDetails, FastTrackAdmobAdapter.this.context, true);
                    }
                    FastTrackAdmobAdapter.this.handler.postDelayed(new Runnable() {
                        public final void run() {
                            FastTrackAdmobAdapter.C50605.this.lambda$onRewardedVideoAdFailedToLoad$0$FastTrackAdmobAdapter$5();
                        }
                    }, 30000);
                    Log.d(FastTrackAdsController.fastTrackLogTag, "admob rewarded onRewardedVideoAdFailedToLoad: " + i);
                }

                public /* synthetic */ void lambda$onRewardedVideoAdFailedToLoad$0$FastTrackAdmobAdapter$5() {
                    FastTrackAdmobAdapter.this.loadRewardedVideo();
                }

                public void onRewardedVideoCompleted() {
                    Log.d(FastTrackAdsController.fastTrackLogTag, "admob rewarded onRewardedVideoCompleted");
                }
            });
            Log.d(FastTrackAdsController.fastTrackLogTag, "admob rewarded attempt to load");
            HashMap hashMap = this.rewardedDetails;
            hashMap.put("details", "rewarded id: " + this.rewardedVideoPlacementId);
            this.rewardedDetails.put("uniqid", GuidGenerator.generateNewGuid());
            RewardedVideoAd rewardedVideoAd2 = this.rewardedVideoAd;
            String str2 = this.rewardedVideoPlacementId;
            AdRequest.Builder builder = new AdRequest.Builder();
            Class<AdMobAdapter> cls = AdMobAdapter.class;
            Bundle bundle = this.extras;
            if (bundle == null) {
                bundle = new Bundle();
            }
            rewardedVideoAd2.loadAd(str2, builder.addNetworkExtrasBundle(cls, bundle).build());
            StatController.getInstance().sendRequestAsyncByKey(StatController.KEY_FT_REWARDED_SDK_REQUEST, this.rewardedDetails, this.context, true);
        }
    }

    public void showRewardedVideo(FastTrackBaseAdapter.RewardedVideoListener rewardedVideoListener, String str) {
        this.rewardedVideoListener = rewardedVideoListener;
        this.rewardedVideoCurrentPlacement = str;
        if (this.rewardedVideoAd == null || !getRewardedVideoActivationStatus(str).booleanValue()) {
            Log.d(FastTrackAdsController.fastTrackLogTag, "Rewarded video placement disabled");
            this.rewardedVideoListener.onVideoDeactivated();
            this.rewardedVideoListener = null;
        } else if (this.rewardedVideoAd.isLoaded()) {
            this.videoShowRequested = true;
            this.rewardedVideoAd.show();
        } else if (this.videoDownloadError) {
            this.rewardedVideoListener.onVideoError(this.context.getResources().getString(C5051R.string.appsgeysersdk_fasttrack_no_rew_video));
            this.rewardedVideoListener = null;
        } else {
            if (this.progressDialog == null) {
                this.progressDialog = new AppsgeyserProgressDialog(this.context);
            }
            this.progressDialog.show();
            this.handler.postDelayed(this.rewardedVideoShowCancelRunnable, 10000);
        }
    }

    public /* synthetic */ void lambda$new$2$FastTrackAdmobAdapter() {
        if (this.progressDialog != null) {
            this.progressDialog.dismiss();
            this.progressDialog = null;
        }
        if (this.rewardedVideoListener != null) {
            this.rewardedVideoListener.onVideoError(this.context.getResources().getString(C5051R.string.appsgeysersdk_fasttrack_no_rew_video));
            this.rewardedVideoListener = null;
        }
    }

    public void loadNativeAds(int i) {
        String str = this.nativeAdsPlacementId;
        if (str != null && !str.isEmpty()) {
            AdLoader build = new AdLoader.Builder(this.context, this.nativeAdsPlacementId).forUnifiedNativeAd(new UnifiedNativeAd.OnUnifiedNativeAdLoadedListener() {
                public final void onUnifiedNativeAdLoaded(UnifiedNativeAd unifiedNativeAd) {
                    FastTrackAdmobAdapter.this.lambda$loadNativeAds$3$FastTrackAdmobAdapter(unifiedNativeAd);
                }
            }).withAdListener(new AdListener() {
                public void onAdFailedToLoad(int i) {
                    Log.d(FastTrackAdsController.fastTrackLogTag, "admob native onAdFailedToLoad");
                }

                public void onAdClicked() {
                    Log.d(FastTrackAdsController.fastTrackLogTag, "admob native onAdClicked");
                }

                public void onAdImpression() {
                    Log.d(FastTrackAdsController.fastTrackLogTag, "admob native onAdImpression");
                }

                public void onAdClosed() {
                    Log.d(FastTrackAdsController.fastTrackLogTag, "admob native onAdClosed");
                }

                public void onAdLeftApplication() {
                    Log.d(FastTrackAdsController.fastTrackLogTag, "admob native onAdLeftApplication");
                }

                public void onAdOpened() {
                    Log.d(FastTrackAdsController.fastTrackLogTag, "admob native onAdOpened");
                }

                public void onAdLoaded() {
                    Log.d(FastTrackAdsController.fastTrackLogTag, "admob native onAdLoaded");
                }
            }).build();
            this.adLoader = build;
            AdRequest.Builder builder = new AdRequest.Builder();
            Class<AdMobAdapter> cls = AdMobAdapter.class;
            Bundle bundle = this.extras;
            if (bundle == null) {
                bundle = new Bundle();
            }
            build.loadAds(builder.addNetworkExtrasBundle(cls, bundle).build(), i);
        }
    }

    public /* synthetic */ void lambda$loadNativeAds$3$FastTrackAdmobAdapter(UnifiedNativeAd unifiedNativeAd) {
        this.loadedNativeAds.add(unifiedNativeAd);
    }

    public List<Object> getNativeAds(int i) {
        ArrayList arrayList = new ArrayList();
        for (Object next : this.loadedNativeAds) {
            for (int i2 = 0; i2 < i; i2++) {
                arrayList.add(next);
            }
        }
        this.loadedNativeAds.removeAll(arrayList);
        return arrayList;
    }

    public void onResume(Context context) {
        super.onResume(context);
        RewardedVideoAd rewardedVideoAd2 = this.rewardedVideoAd;
        if (rewardedVideoAd2 != null) {
            rewardedVideoAd2.resume(this.context);
        }
    }

    public void onPause() {
        super.onPause();
        if (this.bannerView != null) {
            if (this.bannerViewContainer != null) {
                Log.d(FastTrackAdsController.fastTrackLogTag, "admob banner attempt to detach bannerView from container");
                this.bannerViewContainer.removeView(this.bannerView);
                this.bannerViewContainer = null;
            }
            this.bannerView.destroy();
            this.bannerView = null;
        }
        this.handler.removeCallbacks(this.bannerViewRefreshRunnable);
        this.handler.removeCallbacks(this.bannerViewRepeatRequestRunnable);
        RewardedVideoAd rewardedVideoAd2 = this.rewardedVideoAd;
        if (rewardedVideoAd2 != null) {
            rewardedVideoAd2.pause(this.context);
        }
    }

    public void onDestroy() {
        RewardedVideoAd rewardedVideoAd2 = this.rewardedVideoAd;
        if (rewardedVideoAd2 != null) {
            rewardedVideoAd2.destroy(this.context);
        }
    }
}
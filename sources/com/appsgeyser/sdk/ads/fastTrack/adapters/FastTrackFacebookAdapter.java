package com.appsgeyser.sdk.ads.fastTrack.adapters;

import android.content.Context;
import android.util.Log;
import android.view.ViewGroup;
import com.appsgeyser.sdk.GuidGenerator;
import com.appsgeyser.sdk.ads.fastTrack.FastTrackAdsController;
import com.appsgeyser.sdk.ads.fastTrack.FastTrackSdkModel;
import com.appsgeyser.sdk.ads.fastTrack.adapters.FastTrackBaseAdapter;
import com.appsgeyser.sdk.ads.fastTrack.adapters.FastTrackFacebookAdapter;
import com.appsgeyser.sdk.configuration.Constants;
import com.appsgeyser.sdk.p087ui.AppsgeyserProgressDialog;
import com.appsgeyser.sdk.server.StatController;
import com.facebook.ads.AdError;
import com.facebook.ads.AdListener;
import com.facebook.ads.AdSize;
import com.facebook.ads.AdView;
import com.facebook.ads.AudienceNetworkAds;
import com.facebook.ads.C5169Ad;
import com.facebook.ads.InterstitialAd;
import com.facebook.ads.InterstitialAdListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import org.apache.commons.lang3.StringUtils;

public class FastTrackFacebookAdapter extends FastTrackBaseAdapter {
    /* access modifiers changed from: private */
    public AdListener adListener;
    /* access modifiers changed from: private */
    public AdView adView;
    /* access modifiers changed from: private */
    public String bannerPlacementId;
    /* access modifiers changed from: private */
    public boolean bannerRequestFailReported;
    /* access modifiers changed from: private */
    public Runnable bannerViewRefreshRunnable = new Runnable() {
        public void run() {
            if (FastTrackFacebookAdapter.this.adView != null) {
                Log.d(FastTrackAdsController.fastTrackLogTag, "facebook banner attempt to load");
                HashMap hashMap = FastTrackFacebookAdapter.this.bannerDetails;
                hashMap.put("details", "banner id: " + FastTrackFacebookAdapter.this.bannerPlacementId);
                FastTrackFacebookAdapter.this.bannerDetails.put("uniqid", GuidGenerator.generateNewGuid());
                StatController.getInstance().sendRequestAsyncByKey(StatController.KEY_FT_BANNER_SDK_REQUEST, FastTrackFacebookAdapter.this.bannerDetails, FastTrackFacebookAdapter.this.context, true);
                FastTrackFacebookAdapter.this.adView.loadAd(FastTrackFacebookAdapter.this.adView.buildLoadAdConfig().withAdListener(FastTrackFacebookAdapter.this.adListener).build());
                boolean unused = FastTrackFacebookAdapter.this.bannerRequestFailReported = false;
                FastTrackFacebookAdapter.this.handler.postDelayed(FastTrackFacebookAdapter.this.bannerViewRepeatRequestRunnable, 15000);
                return;
            }
            Log.d(FastTrackAdsController.fastTrackLogTag, "facebook banner attempt to load failed: bannerView null");
        }
    };
    /* access modifiers changed from: private */
    public Runnable bannerViewRepeatRequestRunnable = new Runnable() {
        public void run() {
            if (FastTrackFacebookAdapter.this.adView != null) {
                Log.d(FastTrackAdsController.fastTrackLogTag, "admob banner repeat attempt to load");
                HashMap hashMap = FastTrackFacebookAdapter.this.bannerDetails;
                hashMap.put("details", "banner id: " + FastTrackFacebookAdapter.this.bannerPlacementId);
                if (!FastTrackFacebookAdapter.this.bannerRequestFailReported) {
                    StatController.getInstance().sendRequestAsyncByKey(StatController.KEY_FT_BANNER_SDK_NOFILL, FastTrackFacebookAdapter.this.bannerDetails, FastTrackFacebookAdapter.this.context, true);
                }
                FastTrackFacebookAdapter.this.bannerDetails.put("uniqid", GuidGenerator.generateNewGuid());
                StatController.getInstance().sendRequestAsyncByKey(StatController.KEY_FT_BANNER_SDK_REQUEST, FastTrackFacebookAdapter.this.bannerDetails, FastTrackFacebookAdapter.this.context, true);
                FastTrackFacebookAdapter.this.adView.loadAd(FastTrackFacebookAdapter.this.adView.buildLoadAdConfig().withAdListener(FastTrackFacebookAdapter.this.adListener).build());
                boolean unused = FastTrackFacebookAdapter.this.bannerRequestFailReported = false;
                FastTrackFacebookAdapter.this.handler.postDelayed(FastTrackFacebookAdapter.this.bannerViewRepeatRequestRunnable, 15000);
            }
        }
    };
    /* access modifiers changed from: private */
    public Runnable fullscreenPendingRequestCancelRunnable = new Runnable() {
        public final void run() {
            FastTrackFacebookAdapter.this.lambda$new$1$FastTrackFacebookAdapter();
        }
    };
    /* access modifiers changed from: private */
    public String fullscreenPlacementId;
    /* access modifiers changed from: private */
    public InterstitialAd interstitialAd;

    public void loadNativeAds(int i) {
    }

    public void loadRewardedVideo() {
    }

    public void onDestroy() {
    }

    public FastTrackFacebookAdapter(FastTrackSdkModel fastTrackSdkModel, Context context) {
        super(fastTrackSdkModel, context);
    }

    /* access modifiers changed from: protected */
    public void init() {
        this.fullscreenPlacementId = this.fastTrackSdkModel.getFullscreenPlacementId();
        this.bannerPlacementId = this.fastTrackSdkModel.getBannerPlacementId();
        AudienceNetworkAds.initialize(this.context.getApplicationContext());
        String str = this.fullscreenPlacementId;
        if (str == null || str.isEmpty()) {
            Log.d(FastTrackAdsController.fastTrackLogTag, "facebook fullscreen disabled");
        } else {
            this.interstitialDetails.putAll(this.appDetails);
            if (this.fastTrackSdkModel.isCustomFullscreenActivated()) {
                this.interstitialDetails.put("ad_source", StatController.FT_NETWORK_FACEBOOK_CUSTOM);
                this.interstitialDetails.put("net_name", StatController.FT_NETWORK_FACEBOOK_CUSTOM);
                this.interstitialDetails.put("net_name_FS", StatController.FT_NETWORK_FACEBOOK_CUSTOM);
                Log.d(FastTrackAdsController.fastTrackLogTag, "facebook fullscreen: custom");
            } else {
                this.interstitialDetails.put("ad_source", StatController.FT_NETWORK_FACEBOOK);
                this.interstitialDetails.put("net_name", StatController.FT_NETWORK_FACEBOOK);
                this.interstitialDetails.put("net_name_FS", StatController.FT_NETWORK_FACEBOOK);
                Log.d(FastTrackAdsController.fastTrackLogTag, "facebook fullscreen: platform");
            }
        }
        String str2 = this.bannerPlacementId;
        if (str2 == null || str2.isEmpty()) {
            Log.d(FastTrackAdsController.fastTrackLogTag, "facebook banner disabled");
            return;
        }
        this.bannerDetails.putAll(this.appDetails);
        if (this.fastTrackSdkModel.isCustomBannerActivated()) {
            this.bannerDetails.put("ad_source", StatController.FT_NETWORK_FACEBOOK_CUSTOM);
            this.bannerDetails.put("net_name", StatController.FT_NETWORK_FACEBOOK_CUSTOM);
            this.bannerDetails.put("net_name_FS", StatController.FT_NETWORK_FACEBOOK_CUSTOM);
            Log.d(FastTrackAdsController.fastTrackLogTag, "facebook banner: custom");
            return;
        }
        this.bannerDetails.put("ad_source", StatController.FT_NETWORK_FACEBOOK);
        this.bannerDetails.put("net_name", StatController.FT_NETWORK_FACEBOOK);
        this.bannerDetails.put("net_name_FS", StatController.FT_NETWORK_FACEBOOK);
        Log.d(FastTrackAdsController.fastTrackLogTag, "facebook banner: platform");
    }

    public void initBannerView(final ViewGroup viewGroup, String str) {
        final Integer bannerViewRefreshRate = getBannerViewRefreshRate(str);
        String str2 = this.bannerPlacementId;
        if (str2 != null && !str2.isEmpty() && bannerViewRefreshRate.intValue() != 0) {
            Log.d(FastTrackAdsController.fastTrackLogTag, "facebook banner initializing: " + this.bannerPlacementId);
            this.adView = new AdView(this.context, this.bannerPlacementId, AdSize.BANNER_HEIGHT_50);
            this.adListener = new AdListener() {
                public void onError(C5169Ad ad, AdError adError) {
                    if (adError.getErrorCode() == 1001) {
                        HashMap hashMap = FastTrackFacebookAdapter.this.bannerDetails;
                        hashMap.put("details", "banner id: " + FastTrackFacebookAdapter.this.bannerPlacementId);
                        StatController.getInstance().sendRequestAsyncByKey(StatController.KEY_FT_BANNER_SDK_NOFILL, FastTrackFacebookAdapter.this.bannerDetails, FastTrackFacebookAdapter.this.context, true);
                    } else {
                        HashMap hashMap2 = FastTrackFacebookAdapter.this.bannerDetails;
                        hashMap2.put("details", "banner id: " + FastTrackFacebookAdapter.this.bannerPlacementId + "; error_desc: error code " + adError.getErrorCode() + StringUtils.SPACE + adError.getErrorMessage());
                        StatController.getInstance().sendRequestAsyncByKey(StatController.KEY_FT_BANNER_SDK_ERROR, FastTrackFacebookAdapter.this.bannerDetails, FastTrackFacebookAdapter.this.context, true);
                    }
                    boolean unused = FastTrackFacebookAdapter.this.bannerRequestFailReported = true;
                    Log.d(FastTrackAdsController.fastTrackLogTag, "facebook banner onError: " + adError.getErrorCode() + StringUtils.SPACE + adError.getErrorMessage());
                }

                public void onAdClicked(C5169Ad ad) {
                    HashMap hashMap = FastTrackFacebookAdapter.this.bannerDetails;
                    hashMap.put("details", "banner id: " + FastTrackFacebookAdapter.this.bannerPlacementId);
                    StatController.getInstance().sendRequestAsyncByKey(StatController.KEY_FT_BANNER_SDK_CLICK, FastTrackFacebookAdapter.this.bannerDetails, FastTrackFacebookAdapter.this.context, true);
                    Log.d(FastTrackAdsController.fastTrackLogTag, "facebook banner onAdClicked");
                }

                public void onAdLoaded(C5169Ad ad) {
                    if (viewGroup == null || FastTrackFacebookAdapter.this.adView == null) {
                        Log.d(FastTrackAdsController.fastTrackLogTag, "facebook banner loaded, but bannerViewContainer is null");
                    } else {
                        HashMap hashMap = FastTrackFacebookAdapter.this.bannerDetails;
                        hashMap.put("details", "banner id: " + FastTrackFacebookAdapter.this.bannerPlacementId);
                        StatController.getInstance().sendRequestAsyncByKey(StatController.KEY_FT_BANNER_SDK_IMPRESSION, FastTrackFacebookAdapter.this.bannerDetails, FastTrackFacebookAdapter.this.context, true);
                        viewGroup.setVisibility(0);
                        FastTrackFacebookAdapter.this.handler.removeCallbacks(FastTrackFacebookAdapter.this.bannerViewRepeatRequestRunnable);
                        FastTrackFacebookAdapter.this.handler.removeCallbacks(FastTrackFacebookAdapter.this.bannerViewRefreshRunnable);
                        FastTrackFacebookAdapter.this.handler.postDelayed(FastTrackFacebookAdapter.this.bannerViewRefreshRunnable, (long) bannerViewRefreshRate.intValue());
                    }
                    Log.d(FastTrackAdsController.fastTrackLogTag, "facebook banner onAdLoaded");
                }

                public void onLoggingImpression(C5169Ad ad) {
                    Log.d(FastTrackAdsController.fastTrackLogTag, "facebook banner onLoggingImpression");
                }
            };
            Log.d(FastTrackAdsController.fastTrackLogTag, "admob banner attempt to attach bannerView to container");
            this.bannerViewContainer = viewGroup;
            this.bannerViewContainer.addView(this.adView);
            this.handler.post(this.bannerViewRefreshRunnable);
        }
    }

    public void loadFullscreen() {
        String str = this.fullscreenPlacementId;
        if (str != null && !str.isEmpty()) {
            this.interstitialAd = new InterstitialAd(this.context, this.fullscreenPlacementId);
            Log.d(FastTrackAdsController.fastTrackLogTag, "facebook fullscreen initializing: " + this.fullscreenPlacementId);
            C50654 r0 = new InterstitialAdListener() {
                public void onInterstitialDismissed(C5169Ad ad) {
                    if (FastTrackFacebookAdapter.this.progressDialog != null && FastTrackFacebookAdapter.this.progressDialog.isShowing()) {
                        try {
                            FastTrackFacebookAdapter.this.progressDialog.dismiss();
                        } catch (IllegalArgumentException unused) {
                            Log.d(FastTrackAdsController.fastTrackLogTag, "progressDialog dismissal IAE");
                        }
                    }
                    FastTrackFacebookAdapter.this.loadFullscreen();
                    if (FastTrackFacebookAdapter.this.fullscreenListener != null) {
                        FastTrackFacebookAdapter.this.fullscreenListener.onClose();
                    }
                    Log.d(FastTrackAdsController.fastTrackLogTag, "facebook fs onInterstitialDismissed");
                }

                public void onError(C5169Ad ad, AdError adError) {
                    if (adError.getErrorCode() == 1001) {
                        HashMap hashMap = FastTrackFacebookAdapter.this.interstitialDetails;
                        hashMap.put("details", "fs id: " + FastTrackFacebookAdapter.this.fullscreenPlacementId);
                        StatController.getInstance().sendRequestAsyncByKey(StatController.KEY_FT_INTERSTITIAL_SDK_NOFILL, FastTrackFacebookAdapter.this.interstitialDetails, FastTrackFacebookAdapter.this.context, true);
                    } else {
                        HashMap hashMap2 = FastTrackFacebookAdapter.this.interstitialDetails;
                        hashMap2.put("details", "fs id: " + FastTrackFacebookAdapter.this.fullscreenPlacementId + "; error_desc: error code " + adError.getErrorCode() + StringUtils.SPACE + adError.getErrorMessage());
                        StatController.getInstance().sendRequestAsyncByKey(StatController.KEY_FT_INTERSTITIAL_SDK_ERROR, FastTrackFacebookAdapter.this.interstitialDetails, FastTrackFacebookAdapter.this.context, true);
                    }
                    FastTrackFacebookAdapter.this.handler.postDelayed(new Runnable() {
                        public final void run() {
                            FastTrackFacebookAdapter.C50654.this.lambda$onError$0$FastTrackFacebookAdapter$4();
                        }
                    }, 30000);
                    Log.d(FastTrackAdsController.fastTrackLogTag, "facebook fs onError " + adError.getErrorCode() + StringUtils.SPACE + adError.getErrorMessage());
                }

                public /* synthetic */ void lambda$onError$0$FastTrackFacebookAdapter$4() {
                    FastTrackFacebookAdapter.this.loadFullscreen();
                }

                public void onAdClicked(C5169Ad ad) {
                    HashMap hashMap = FastTrackFacebookAdapter.this.interstitialDetails;
                    hashMap.put("details", "fs id: " + FastTrackFacebookAdapter.this.fullscreenPlacementId);
                    StatController.getInstance().sendRequestAsyncByKey(StatController.KEY_FT_INTERSTITIAL_SDK_CLICK, FastTrackFacebookAdapter.this.interstitialDetails, FastTrackFacebookAdapter.this.context, true);
                    Log.d(FastTrackAdsController.fastTrackLogTag, "facebook fs onAdClicked");
                }

                public void onInterstitialDisplayed(C5169Ad ad) {
                    HashMap hashMap = FastTrackFacebookAdapter.this.interstitialDetails;
                    hashMap.put("details", "fs id: " + FastTrackFacebookAdapter.this.fullscreenPlacementId);
                    StatController.getInstance().sendRequestAsyncByKey(StatController.KEY_FT_INTERSTITIAL_SDK_IMPRESSION, FastTrackFacebookAdapter.this.interstitialDetails, FastTrackFacebookAdapter.this.context, true);
                    if (FastTrackFacebookAdapter.this.fullscreenListener != null) {
                        FastTrackFacebookAdapter.this.fullscreenListener.onShow();
                    }
                    Log.d(FastTrackAdsController.fastTrackLogTag, "facebook fs onAdOpened");
                }

                public void onAdLoaded(C5169Ad ad) {
                    if (FastTrackFacebookAdapter.this.pendingFullscreenRequest && FastTrackFacebookAdapter.this.isInForeground) {
                        FastTrackFacebookAdapter.this.pendingFullscreenRequest = false;
                        Log.d(FastTrackAdsController.fastTrackLogTag, "facebook fullscreen loaded, pending request processing");
                        FastTrackFacebookAdapter.this.handler.removeCallbacks(FastTrackFacebookAdapter.this.fullscreenPendingRequestCancelRunnable);
                        AppsgeyserProgressDialog appsgeyserProgressDialog = FastTrackFacebookAdapter.this.progressDialog;
                        appsgeyserProgressDialog.show();
                        FastTrackFacebookAdapter.this.handler.postDelayed(new Runnable(appsgeyserProgressDialog) {
                            public final /* synthetic */ AppsgeyserProgressDialog f$1;

                            {
                                this.f$1 = r2;
                            }

                            public final void run() {
                                FastTrackFacebookAdapter.C50654.this.lambda$onAdLoaded$1$FastTrackFacebookAdapter$4(this.f$1);
                            }
                        }, 2000);
                    }
                    Log.d(FastTrackAdsController.fastTrackLogTag, "facebook fs onAdLoaded");
                }

                public /* synthetic */ void lambda$onAdLoaded$1$FastTrackFacebookAdapter$4(AppsgeyserProgressDialog appsgeyserProgressDialog) {
                    try {
                        appsgeyserProgressDialog.dismiss();
                    } catch (IllegalArgumentException unused) {
                        Log.d(FastTrackAdsController.fastTrackLogTag, "progressDialog dismissal IAE");
                    }
                    FastTrackFacebookAdapter.this.interstitialAd.show();
                }

                public void onLoggingImpression(C5169Ad ad) {
                    Log.d(FastTrackAdsController.fastTrackLogTag, "facebook fs onLoggingImpression");
                }
            };
            Log.d(FastTrackAdsController.fastTrackLogTag, "facebook fullscreen attempt to load");
            HashMap hashMap = this.interstitialDetails;
            hashMap.put("details", "fs id: " + this.fullscreenPlacementId);
            this.interstitialDetails.put("uniqid", GuidGenerator.generateNewGuid());
            InterstitialAd interstitialAd2 = this.interstitialAd;
            interstitialAd2.loadAd(interstitialAd2.buildLoadAdConfig().withAdListener(r0).build());
            StatController.getInstance().sendRequestAsyncByKey(StatController.KEY_FT_INTERSTITIAL_SDK_REQUEST, this.interstitialDetails, this.context, true);
        }
    }

    public void showFullscreen(String str, String str2, boolean z) {
        if (!z || System.currentTimeMillis() - this.preferencesCoder.getPrefLong(Constants.PREFS_APPSGEYSER_FULLSCREEN_LAST_REQUEST_TIMING, 0) > ((long) getFullscreenFrequencyTimerValue().intValue())) {
            this.preferencesCoder.savePrefLong(Constants.PREFS_APPSGEYSER_FULLSCREEN_LAST_REQUEST_TIMING, System.currentTimeMillis());
            Log.d(FastTrackAdsController.fastTrackLogTag, "facebook fullscreen show request");
            if (this.fullscreenListener != null) {
                this.fullscreenListener.onRequest();
            }
            if (new Random().nextInt(100) + 1 > getFullscreenIntensityPoints(str2).intValue()) {
                Log.d(FastTrackAdsController.fastTrackLogTag, "facebook fullscreen attempt to show canceled due to intensity settings");
                if (this.fullscreenListener != null) {
                    this.fullscreenListener.onFailedToShow();
                }
            } else if (this.interstitialAd != null) {
                HashMap hashMap = this.interstitialDetails;
                hashMap.put("details", "fs id: " + this.fullscreenPlacementId);
                StatController.getInstance().sendRequestAsyncByKey(StatController.KEY_FT_INTERSTITIAL_SDK_ATTEMPT, this.interstitialDetails, this.context, true);
                if (this.interstitialAd.isAdLoaded()) {
                    Log.d(FastTrackAdsController.fastTrackLogTag, "facebook fullscreen attempt to show");
                    AppsgeyserProgressDialog appsgeyserProgressDialog = this.progressDialog;
                    appsgeyserProgressDialog.show();
                    this.handler.postDelayed(new Runnable(appsgeyserProgressDialog) {
                        public final /* synthetic */ AppsgeyserProgressDialog f$1;

                        {
                            this.f$1 = r2;
                        }

                        public final void run() {
                            FastTrackFacebookAdapter.this.lambda$showFullscreen$0$FastTrackFacebookAdapter(this.f$1);
                        }
                    }, 2000);
                    return;
                }
                Log.d(FastTrackAdsController.fastTrackLogTag, "facebook fullscreen not loaded yet, waiting for load");
                this.pendingFullscreenRequest = true;
                this.handler.postDelayed(this.fullscreenPendingRequestCancelRunnable, (long) getFullscreenPendingDelayTimerValue().intValue());
            } else {
                Log.d(FastTrackAdsController.fastTrackLogTag, "facebook fullscreen disabled");
                if (this.fullscreenListener != null) {
                    this.fullscreenListener.onFailedToShow();
                }
            }
        } else {
            Log.d(FastTrackAdsController.fastTrackLogTag, "facebook fullscreen show request was cancelled due to frequency timing settings");
        }
    }

    public /* synthetic */ void lambda$showFullscreen$0$FastTrackFacebookAdapter(AppsgeyserProgressDialog appsgeyserProgressDialog) {
        try {
            appsgeyserProgressDialog.dismiss();
        } catch (IllegalArgumentException unused) {
            Log.d(FastTrackAdsController.fastTrackLogTag, "progressDialog dismissal IAE");
        }
        this.interstitialAd.show();
    }

    public /* synthetic */ void lambda$new$1$FastTrackFacebookAdapter() {
        this.pendingFullscreenRequest = false;
        if (this.fullscreenListener != null) {
            this.fullscreenListener.onFailedToShow();
        }
        Log.d(FastTrackAdsController.fastTrackLogTag, "facebook fullscreen not loaded, cancelling wait");
    }

    public void showRewardedVideo(FastTrackBaseAdapter.RewardedVideoListener rewardedVideoListener, String str) {
        rewardedVideoListener.onVideoDeactivated();
    }

    public List<Object> getNativeAds(int i) {
        return new ArrayList();
    }

    public void onPause() {
        super.onPause();
        if (this.adView != null) {
            if (this.bannerViewContainer != null) {
                Log.d(FastTrackAdsController.fastTrackLogTag, "facebook banner attempt to detach bannerView from container");
                this.bannerViewContainer.removeView(this.adView);
                this.bannerViewContainer = null;
            }
            this.adView.destroy();
            this.adView = null;
        }
        this.handler.removeCallbacks(this.bannerViewRefreshRunnable);
        this.handler.removeCallbacks(this.bannerViewRepeatRequestRunnable);
    }
}

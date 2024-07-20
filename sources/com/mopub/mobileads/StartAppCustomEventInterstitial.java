package com.mopub.mobileads;

import android.content.Context;
import com.mopub.mobileads.CustomEventInterstitial;
import com.startapp.android.publish.adsCommon.C1040Ad;
import com.startapp.android.publish.adsCommon.StartAppAd;
import com.startapp.android.publish.adsCommon.adListeners.AdDisplayListener;
import com.startapp.android.publish.adsCommon.adListeners.AdEventListener;
import java.util.Map;

public class StartAppCustomEventInterstitial extends CustomEventInterstitial {
    public static final String AD_MODE_KEY = "adMode";

    /* renamed from: ad */
    private StartAppAd f264ad;
    private String adTag;
    /* access modifiers changed from: private */
    public CustomEventInterstitial.CustomEventInterstitialListener listener;

    /* access modifiers changed from: protected */
    public void onInvalidate() {
    }

    /* access modifiers changed from: protected */
    public void loadInterstitial(Context context, final CustomEventInterstitial.CustomEventInterstitialListener customEventInterstitialListener, Map<String, Object> map, Map<String, String> map2) {
        StartAppCustomEventUtils.checkInit(context, map2);
        this.listener = customEventInterstitialListener;
        this.adTag = StartAppCustomEventUtils.getStringFromExtras("adTag", map2);
        StartAppAd startAppAd = new StartAppAd(context);
        this.f264ad = startAppAd;
        startAppAd.loadAd(getAdMode(map, map2), StartAppCustomEventUtils.extractAdPrefs(context, map, map2), new AdEventListener() {
            public void onReceiveAd(C1040Ad ad) {
                customEventInterstitialListener.onInterstitialLoaded();
            }

            public void onFailedToReceiveAd(C1040Ad ad) {
                customEventInterstitialListener.onInterstitialFailed(MoPubErrorCode.UNSPECIFIED);
            }
        });
    }

    public void showInterstitial() {
        this.f264ad.showAd(this.adTag, new AdDisplayListener() {
            public void adNotDisplayed(C1040Ad ad) {
            }

            public void adHidden(C1040Ad ad) {
                StartAppCustomEventInterstitial.this.listener.onInterstitialDismissed();
            }

            public void adDisplayed(C1040Ad ad) {
                StartAppCustomEventInterstitial.this.listener.onInterstitialShown();
            }

            public void adClicked(C1040Ad ad) {
                StartAppCustomEventInterstitial.this.listener.onInterstitialClicked();
            }
        });
    }

    private StartAppAd.AdMode getAdMode(Map<String, Object> map, Map<String, String> map2) {
        StartAppAd.AdMode adMode;
        if (!(map2 == null || map2.get(AD_MODE_KEY) == null)) {
            if (map2.get(AD_MODE_KEY).equalsIgnoreCase("AdMode.FULLPAGE")) {
                return StartAppAd.AdMode.FULLPAGE;
            }
            if (map2.get(AD_MODE_KEY).equalsIgnoreCase("AdMode.OVERLAY")) {
                return StartAppAd.AdMode.OVERLAY;
            }
            if (map2.get(AD_MODE_KEY).equalsIgnoreCase("AdMode.OFFERWALL")) {
                return StartAppAd.AdMode.OFFERWALL;
            }
            if (map2.get(AD_MODE_KEY).equalsIgnoreCase("AdMode.AUTOMATIC")) {
                return StartAppAd.AdMode.AUTOMATIC;
            }
        }
        if (map.get(StartAppExtras.STARTAPP_EXTRAS_KEY) == null || (adMode = ((StartAppInterstitialExtras) map.get(StartAppExtras.STARTAPP_EXTRAS_KEY)).getAdMode()) == null) {
            return StartAppAd.AdMode.AUTOMATIC;
        }
        return adMode;
    }
}

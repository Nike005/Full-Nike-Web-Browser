package com.facebook.ads;

public interface InterstitialAdListener extends AdListener {
    void onInterstitialDismissed(C5169Ad ad);

    void onInterstitialDisplayed(C5169Ad ad);
}

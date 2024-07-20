package com.facebook.ads;

public interface AdListener {
    void onAdClicked(C5169Ad ad);

    void onAdLoaded(C5169Ad ad);

    void onError(C5169Ad ad, AdError adError);

    void onLoggingImpression(C5169Ad ad);
}

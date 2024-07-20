package com.facebook.ads;

/* renamed from: com.facebook.ads.Ad */
public interface C5169Ad {

    /* renamed from: com.facebook.ads.Ad$LoadAdConfig */
    public interface LoadAdConfig {
    }

    /* renamed from: com.facebook.ads.Ad$LoadConfigBuilder */
    public interface LoadConfigBuilder {
        LoadAdConfig build();

        LoadConfigBuilder withBid(String str);
    }

    void destroy();

    String getPlacementId();

    boolean isAdInvalidated();

    void loadAd();

    @Deprecated
    void loadAdFromBid(String str);

    @Deprecated
    void setExtraHints(ExtraHints extraHints);
}

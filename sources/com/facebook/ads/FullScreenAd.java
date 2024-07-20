package com.facebook.ads;

import com.facebook.ads.C5169Ad;

public interface FullScreenAd extends C5169Ad {

    public interface ShowAdConfig {
    }

    public interface ShowConfigBuilder {
        ShowAdConfig build();
    }

    C5169Ad.LoadConfigBuilder buildLoadAdConfig();

    ShowConfigBuilder buildShowAdConfig();

    boolean show();
}

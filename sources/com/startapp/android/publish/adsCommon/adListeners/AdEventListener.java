package com.startapp.android.publish.adsCommon.adListeners;

import com.startapp.android.publish.adsCommon.C1040Ad;

/* compiled from: StartAppSDK */
public interface AdEventListener {
    void onFailedToReceiveAd(C1040Ad ad);

    void onReceiveAd(C1040Ad ad);
}

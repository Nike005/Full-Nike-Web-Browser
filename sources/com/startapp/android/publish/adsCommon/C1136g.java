package com.startapp.android.publish.adsCommon;

import android.content.Context;
import com.startapp.android.publish.adsCommon.C1040Ad;
import com.startapp.android.publish.adsCommon.adListeners.AdEventListener;
import com.startapp.android.publish.common.model.AdPreferences;

/* renamed from: com.startapp.android.publish.adsCommon.g */
/* compiled from: StartAppSDK */
public interface C1136g {
    /* renamed from: a */
    boolean mo13830a(String str);

    /* renamed from: a_ */
    String mo13831a_();

    Long getAdCacheTtl();

    Long getLastLoadTime();

    C1040Ad.AdState getState();

    boolean getVideoCancelCallBack();

    boolean hasAdCacheTtlPassed();

    boolean isBelowMinCPM();

    boolean isReady();

    boolean load(AdPreferences adPreferences, AdEventListener adEventListener);

    void setActivityExtra(C1067a aVar);

    void setContext(Context context);

    void setVideoCancelCallBack(boolean z);
}

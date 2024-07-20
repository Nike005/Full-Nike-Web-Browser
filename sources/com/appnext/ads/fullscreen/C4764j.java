package com.appnext.ads.fullscreen;

import android.net.Uri;
import com.appnext.core.AppnextAd;
import com.appnext.core.C4986p;

/* renamed from: com.appnext.ads.fullscreen.j */
public interface C4764j {
    void closeClicked();

    long closeDelay();

    int getCaptionTextTime();

    C4986p getConfigManager();

    String getCtaText();

    String getLanguage();

    boolean getMute();

    AppnextAd getSelectedAd();

    Uri getSelectedVideoUri();

    int getTemplate(String str);

    void installClicked(AppnextAd appnextAd);

    boolean isInstalled();

    boolean isRewarded();

    void privacyClicked();

    void report(String str, String str2);

    boolean showClose();

    void videoEnded();

    void videoStarted();
}

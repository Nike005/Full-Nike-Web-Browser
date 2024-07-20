package com.appnext.ads.fullscreen;

import com.appnext.core.AppnextAd;
import com.appnext.core.C4986p;
import java.util.ArrayList;

/* renamed from: com.appnext.ads.fullscreen.h */
public interface C4762h {
    void closeClicked();

    C4986p getConfigManager();

    String getCtaText();

    String getLanguage();

    ArrayList<AppnextAd> getPostRollAds();

    int getTemplate(String str);

    void installClicked(AppnextAd appnextAd);

    boolean isRewarded();

    void privacyClicked();

    void report(String str, String str2);
}

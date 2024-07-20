package com.appnext.ads.fullscreen;

import com.appnext.core.AppnextAd;
import com.appnext.core.C4986p;
import java.util.ArrayList;

/* renamed from: com.appnext.ads.fullscreen.i */
public interface C4763i {
    void closeClicked();

    C4986p getConfigManager();

    ArrayList<AppnextAd> getPreRollAds();

    int getTemplate(String str);

    void privacyClicked();

    void report(String str, String str2);

    void videoSelected(AppnextAd appnextAd);
}

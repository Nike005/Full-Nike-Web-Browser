package com.appnext.banners;

import com.appnext.core.C4967f;
import com.appnext.core.C4986p;
import java.util.HashMap;
import org.htmlcleaner.CleanerProperties;

/* renamed from: com.appnext.banners.d */
public final class C4858d extends C4986p {

    /* renamed from: df */
    private static C4858d f4501df;

    /* access modifiers changed from: protected */
    /* renamed from: n */
    public final HashMap<String, String> mo40628n() {
        return null;
    }

    /* renamed from: S */
    public static synchronized C4858d m6456S() {
        C4858d dVar;
        synchronized (C4858d.class) {
            if (f4501df == null) {
                f4501df = new C4858d();
            }
            dVar = f4501df;
        }
        return dVar;
    }

    private C4858d() {
    }

    /* access modifiers changed from: protected */
    public final String getUrl() {
        return "https://cdn.appnext.com/tools/sdk/confign/banner/" + C4967f.m6838bi() + "/banner_config.txt";
    }

    /* access modifiers changed from: protected */
    /* renamed from: o */
    public final HashMap<String, String> mo40629o() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("urlApp_protection", CleanerProperties.BOOL_ATT_TRUE);
        hashMap.put("resolve_timeout", "8");
        hashMap.put("postpone_impression_sec", "0");
        hashMap.put("postpone_vta_sec", "0");
        hashMap.put("pview", CleanerProperties.BOOL_ATT_TRUE);
        hashMap.put("banner_expiration_time", "0");
        hashMap.put("ads_caching_time_minutes", "0");
        hashMap.put("new_button_text", "Install");
        hashMap.put("existing_button_text", "Open");
        hashMap.put("gdpr", "false");
        hashMap.put("BANNER_cpiActiveFlow", "d");
        hashMap.put("BANNER_cpcActiveFlow", "b");
        hashMap.put("LARGE_BANNER_cpiActiveFlow", "d");
        hashMap.put("LARGE_BANNER_cpcActiveFlow", "b");
        hashMap.put("MEDIUM_RECTANGLE_cpiActiveFlow", "d");
        hashMap.put("MEDIUM_RECTANGLE_cpcActiveFlow", "b");
        hashMap.put("didPrivacy", "false");
        hashMap.put("impOne", CleanerProperties.BOOL_ATT_TRUE);
        hashMap.put("_arFlag", CleanerProperties.BOOL_ATT_TRUE);
        hashMap.put("banner_ar", "10");
        hashMap.put("large_banner_ar", "10");
        hashMap.put("medium_rectangle_ar", "10");
        hashMap.put("stp_flag", "false");
        return hashMap;
    }
}

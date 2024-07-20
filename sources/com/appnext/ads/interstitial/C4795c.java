package com.appnext.ads.interstitial;

import com.appnext.core.C4967f;
import com.appnext.core.C4986p;
import java.util.HashMap;
import org.htmlcleaner.CleanerProperties;

/* renamed from: com.appnext.ads.interstitial.c */
public final class C4795c extends C4986p {

    /* renamed from: cn */
    private static C4795c f4388cn;

    /* renamed from: aQ */
    private String f4389aQ = ("https://cdn.appnext.com/tools/sdk/confign/interstitial/" + C4967f.m6838bi() + "/interstitial_config.txt");

    /* renamed from: aR */
    private HashMap<String, String> f4390aR = null;

    /* renamed from: K */
    public static synchronized C4795c m6424K() {
        C4795c cVar;
        synchronized (C4795c.class) {
            if (f4388cn == null) {
                f4388cn = new C4795c();
            }
            cVar = f4388cn;
        }
        return cVar;
    }

    private C4795c() {
    }

    /* access modifiers changed from: protected */
    public final String getUrl() {
        return this.f4389aQ;
    }

    /* access modifiers changed from: protected */
    /* renamed from: n */
    public final HashMap<String, String> mo40628n() {
        return this.f4390aR;
    }

    public final void setUrl(String str) {
        this.f4389aQ = str;
    }

    /* renamed from: a */
    public final void mo40626a(HashMap<String, String> hashMap) {
        this.f4390aR = hashMap;
    }

    /* access modifiers changed from: protected */
    /* renamed from: o */
    public final HashMap<String, String> mo40629o() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("creative", Interstitial.TYPE_MANAGED);
        hashMap.put("auto_play", CleanerProperties.BOOL_ATT_TRUE);
        hashMap.put("mute", "false");
        hashMap.put("pview", CleanerProperties.BOOL_ATT_TRUE);
        hashMap.put("min_internet_connection", "2g");
        hashMap.put("min_internet_connection_video", "3g");
        hashMap.put("urlApp_protection", "false");
        hashMap.put("can_close", CleanerProperties.BOOL_ATT_TRUE);
        hashMap.put("video_length", "15");
        hashMap.put("button_text", "");
        hashMap.put("button_color", "");
        hashMap.put("skip_title", "");
        hashMap.put("remove_poster_on_auto_play", CleanerProperties.BOOL_ATT_TRUE);
        hashMap.put("banner_expiration_time", "0");
        hashMap.put("show_rating", CleanerProperties.BOOL_ATT_TRUE);
        hashMap.put("show_desc", CleanerProperties.BOOL_ATT_TRUE);
        hashMap.put("new_button_text", "Install");
        hashMap.put("existing_button_text", "Open");
        hashMap.put("postpone_vta_sec", "0");
        hashMap.put("postpone_impression_sec", "0");
        hashMap.put("resolve_timeout", "8");
        hashMap.put("ads_caching_time_minutes", "0");
        hashMap.put("gdpr", "false");
        hashMap.put("clickType_A", "0");
        hashMap.put("didPrivacy", "false");
        hashMap.put("S1", "[{\"id\":\"multi\",\"p\":50},{\"id\":\"single\",\"p\":50}]");
        hashMap.put("stp_flag", "false");
        return hashMap;
    }
}

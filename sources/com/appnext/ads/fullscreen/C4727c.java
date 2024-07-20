package com.appnext.ads.fullscreen;

import com.appnext.core.C4967f;
import com.appnext.core.C4986p;
import java.util.HashMap;
import org.htmlcleaner.CleanerProperties;

/* renamed from: com.appnext.ads.fullscreen.c */
public final class C4727c extends C4986p {

    /* renamed from: aP */
    private static C4727c f4257aP;

    /* renamed from: aQ */
    private String f4258aQ = ("https://cdn.appnext.com/tools/sdk/confign/fullscreen/" + C4967f.m6838bi() + "/fullscreen_config.txt");

    /* renamed from: aR */
    private HashMap<String, String> f4259aR = null;

    /* renamed from: m */
    public static synchronized C4727c m6296m() {
        C4727c cVar;
        synchronized (C4727c.class) {
            if (f4257aP == null) {
                f4257aP = new C4727c();
            }
            cVar = f4257aP;
        }
        return cVar;
    }

    private C4727c() {
    }

    /* access modifiers changed from: protected */
    public final String getUrl() {
        return this.f4258aQ;
    }

    /* access modifiers changed from: protected */
    /* renamed from: n */
    public final HashMap<String, String> mo40628n() {
        return this.f4259aR;
    }

    public final void setUrl(String str) {
        this.f4258aQ = str;
    }

    /* renamed from: a */
    public final void mo40626a(HashMap<String, String> hashMap) {
        this.f4259aR = hashMap;
    }

    /* access modifiers changed from: protected */
    /* renamed from: o */
    public final HashMap<String, String> mo40629o() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("can_close", CleanerProperties.BOOL_ATT_TRUE);
        hashMap.put("show_close", CleanerProperties.BOOL_ATT_TRUE);
        hashMap.put("show_close_time", "2000");
        hashMap.put("video_length", "15");
        hashMap.put("mute", "false");
        hashMap.put("urlApp_protection", "false");
        hashMap.put("pview", CleanerProperties.BOOL_ATT_TRUE);
        hashMap.put("min_internet_connection_video", "3g");
        hashMap.put("banner_expiration_time", "0");
        hashMap.put("postpone_vta_sec", "0");
        hashMap.put("postpone_impression_sec", "0");
        hashMap.put("resolve_timeout", "8");
        hashMap.put("num_saved_videos", "5");
        hashMap.put("caption_text_time", "3");
        hashMap.put("ads_caching_time_minutes", "0");
        hashMap.put("gdpr", "false");
        hashMap.put("clickType_a", "0");
        hashMap.put("clickType_b", "0");
        hashMap.put("didPrivacy", "false");
        hashMap.put("stp_flag", "false");
        return hashMap;
    }
}

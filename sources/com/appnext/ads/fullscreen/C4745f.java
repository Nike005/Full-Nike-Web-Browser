package com.appnext.ads.fullscreen;

import com.appnext.core.C4967f;
import com.appnext.core.C4986p;
import java.util.HashMap;
import org.htmlcleaner.CleanerProperties;

/* renamed from: com.appnext.ads.fullscreen.f */
public final class C4745f extends C4986p {

    /* renamed from: bt */
    private static C4745f f4300bt;

    /* renamed from: aQ */
    private String f4301aQ = ("https://cdn.appnext.com/tools/sdk/confign/rewarded/" + C4967f.m6838bi() + "/rewarded_config.txt");

    /* renamed from: aR */
    private HashMap<String, String> f4302aR = null;

    /* renamed from: q */
    public static synchronized C4745f m6314q() {
        C4745f fVar;
        synchronized (C4745f.class) {
            if (f4300bt == null) {
                f4300bt = new C4745f();
            }
            fVar = f4300bt;
        }
        return fVar;
    }

    private C4745f() {
    }

    /* access modifiers changed from: protected */
    public final String getUrl() {
        return this.f4301aQ;
    }

    /* access modifiers changed from: protected */
    /* renamed from: n */
    public final HashMap<String, String> mo40628n() {
        return this.f4302aR;
    }

    public final void setUrl(String str) {
        this.f4301aQ = str;
    }

    /* renamed from: a */
    public final void mo40626a(HashMap<String, String> hashMap) {
        this.f4302aR = hashMap;
    }

    /* access modifiers changed from: protected */
    /* renamed from: o */
    public final HashMap<String, String> mo40629o() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("can_close", CleanerProperties.BOOL_ATT_TRUE);
        hashMap.put("show_close", "false");
        hashMap.put("video_length", "15");
        hashMap.put("mute", "false");
        hashMap.put("urlApp_protection", "false");
        hashMap.put("pview", CleanerProperties.BOOL_ATT_TRUE);
        hashMap.put("min_internet_connection_video", "3g");
        hashMap.put("banner_expiration_time", "0");
        hashMap.put("default_mode", "normal");
        hashMap.put("postpone_vta_sec", "0");
        hashMap.put("postpone_impression_sec", "0");
        hashMap.put("resolve_timeout", "8");
        hashMap.put("num_saved_videos", "5");
        hashMap.put("caption_text_time", "3");
        hashMap.put("pre_title_string1", "Which Ad");
        hashMap.put("pre_title_string2", "Would you like to watch?");
        hashMap.put("pre_cta_string", "Play this ad");
        hashMap.put("ads_caching_time_minutes", "0");
        hashMap.put("gdpr", "false");
        hashMap.put("clickType_a", "0");
        hashMap.put("clickType_b", "0");
        hashMap.put("didPrivacy", "false");
        hashMap.put("stp_flag", "false");
        return hashMap;
    }
}

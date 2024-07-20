package com.moat.analytics.mobile.mpub;

import android.view.View;
import com.mopub.mobileads.VastIconXmlManager;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.json.JSONObject;

/* renamed from: com.moat.analytics.mobile.mpub.y */
class C0365y extends C0305c implements ReactiveVideoTracker {

    /* renamed from: l */
    private Integer f236l;

    public C0365y(String str) {
        super(str);
        C0336p.m228a(3, "ReactiveVideoTracker", (Object) this, "Initializing.");
        C0336p.m231a("[SUCCESS] ", mo10380a() + " created");
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public String mo10380a() {
        return "ReactiveVideoTracker";
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public JSONObject mo10398a(MoatAdEvent moatAdEvent) {
        if (moatAdEvent.f45d == MoatAdEventType.AD_EVT_COMPLETE && !moatAdEvent.f43b.equals(MoatAdEvent.f41a) && !mo10399a(moatAdEvent.f43b, this.f236l)) {
            moatAdEvent.f45d = MoatAdEventType.AD_EVT_STOPPED;
        }
        return super.mo10398a(moatAdEvent);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo10384a(List<String> list) {
        if (this.f236l.intValue() >= 1000) {
            super.mo10384a(list);
            return;
        }
        throw new C0330n(String.format(Locale.ROOT, "Invalid duration = %d. Please make sure duration is in milliseconds.", new Object[]{this.f236l}));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: i */
    public Map<String, Object> mo10402i() {
        Integer num;
        HashMap hashMap = new HashMap();
        View view = (View) this.f76k.get();
        int i = 0;
        if (view != null) {
            i = Integer.valueOf(view.getWidth());
            num = Integer.valueOf(view.getHeight());
        } else {
            num = 0;
        }
        hashMap.put(VastIconXmlManager.DURATION, this.f236l);
        hashMap.put("width", i);
        hashMap.put("height", num);
        return hashMap;
    }

    public boolean trackVideoAd(Map<String, String> map, Integer num, View view) {
        try {
            mo10386c();
            mo10388d();
            this.f236l = num;
            return super.mo10400a(map, view);
        } catch (Exception e) {
            mo10383a("trackVideoAd", e);
            return false;
        }
    }
}

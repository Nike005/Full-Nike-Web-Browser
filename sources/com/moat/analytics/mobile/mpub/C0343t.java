package com.moat.analytics.mobile.mpub;

import android.graphics.Rect;
import android.view.View;
import com.moat.analytics.mobile.mpub.NativeDisplayTracker;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.moat.analytics.mobile.mpub.t */
class C0343t extends C0304b implements NativeDisplayTracker {

    /* renamed from: g */
    private final Map<String, String> f192g;

    /* renamed from: h */
    private final Set<NativeDisplayTracker.MoatUserInteractionType> f193h = new HashSet();

    C0343t(View view, Map<String, String> map) {
        super(view, true, false);
        C0330n e;
        C0330n nVar;
        C0336p.m228a(3, "NativeDisplayTracker", (Object) this, "Initializing.");
        this.f192g = map;
        if (view == null) {
            C0336p.m229a("[ERROR] ", 3, "NativeDisplayTracker", this, "NativeDisplayTracker initialization not successful, " + "Target view is null");
            nVar = new C0330n("Target view is null");
        } else {
            if (map == null || map.isEmpty()) {
                C0336p.m229a("[ERROR] ", 3, "NativeDisplayTracker", this, "NativeDisplayTracker initialization not successful, " + "AdIds is null or empty");
                e = new C0330n("AdIds is null or empty");
            } else {
                C0310g gVar = ((C0327k) C0327k.getInstance()).f150c;
                if (gVar == null) {
                    C0336p.m229a("[ERROR] ", 3, "NativeDisplayTracker", this, "NativeDisplayTracker initialization not successful, " + "prepareNativeDisplayTracking was not called successfully");
                    nVar = new C0330n("prepareNativeDisplayTracking was not called successfully");
                } else {
                    super.mo10382a(gVar.f87b);
                    try {
                        super.mo10381a(gVar.f86a);
                        m251i();
                        C0336p.m231a("[SUCCESS] ", mo10380a() + " created for " + mo10391g() + ", with adIds:" + map.toString());
                        return;
                    } catch (C0330n e2) {
                        e = e2;
                    }
                }
            }
            this.f61a = e;
            return;
        }
        this.f61a = nVar;
    }

    /* renamed from: a */
    private static String m250a(Map<String, String> map) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (int i = 0; i < 8; i++) {
            String str = "moatClientLevel" + i;
            if (map.containsKey(str)) {
                linkedHashMap.put(str, map.get(str));
            }
        }
        for (int i2 = 0; i2 < 8; i2++) {
            String str2 = "moatClientSlicer" + i2;
            if (map.containsKey(str2)) {
                linkedHashMap.put(str2, map.get(str2));
            }
        }
        for (String next : map.keySet()) {
            if (!linkedHashMap.containsKey(next)) {
                linkedHashMap.put(next, map.get(next));
            }
        }
        return new JSONObject(linkedHashMap).toString();
    }

    /* renamed from: i */
    private void m251i() {
        if (this.f63c != null) {
            this.f63c.mo10437a(m252j());
        }
    }

    /* renamed from: j */
    private String m252j() {
        try {
            String a = m250a(this.f192g);
            C0336p.m228a(3, "NativeDisplayTracker", (Object) this, "Parsed ad ids = " + a);
            return "{\"adIds\":" + a + ", \"adKey\":\"" + this.f65e + "\", \"adSize\":" + m253k() + "}";
        } catch (Exception e) {
            C0330n.m214a(e);
            return "";
        }
    }

    /* renamed from: k */
    private String m253k() {
        try {
            Rect a = C0366z.m304a(super.mo10390f());
            int width = a.width();
            int height = a.height();
            HashMap hashMap = new HashMap();
            hashMap.put("width", Integer.toString(width));
            hashMap.put("height", Integer.toString(height));
            return new JSONObject(hashMap).toString();
        } catch (Exception e) {
            C0330n.m214a(e);
            return null;
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public String mo10380a() {
        return "NativeDisplayTracker";
    }

    public void reportUserInteractionEvent(NativeDisplayTracker.MoatUserInteractionType moatUserInteractionType) {
        try {
            C0336p.m228a(3, "NativeDisplayTracker", (Object) this, "reportUserInteractionEvent:" + moatUserInteractionType.name());
            if (!this.f193h.contains(moatUserInteractionType)) {
                this.f193h.add(moatUserInteractionType);
                JSONObject jSONObject = new JSONObject();
                jSONObject.accumulate("adKey", this.f65e);
                jSONObject.accumulate("event", moatUserInteractionType.name().toLowerCase());
                if (this.f63c != null) {
                    this.f63c.mo10441b(jSONObject.toString());
                }
            }
        } catch (JSONException e) {
            e = e;
            C0336p.m232b(2, "NativeDisplayTracker", this, "Got JSON exception");
            C0330n.m214a(e);
        } catch (Exception e2) {
            e = e2;
            C0330n.m214a(e);
        }
    }
}

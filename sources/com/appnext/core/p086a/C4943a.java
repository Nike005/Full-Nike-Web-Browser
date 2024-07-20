package com.appnext.core.p086a;

import java.util.HashMap;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.appnext.core.a.a */
public final class C4943a {

    /* renamed from: hT */
    private HashMap<String, String> f4717hT;

    /* renamed from: hU */
    private HashMap<String, HashMap<String, String>> f4718hU = new HashMap<>();

    public C4943a(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            this.f4717hT = m6733d(jSONObject.getJSONObject(C4944b.f4720hW));
            this.f4718hU.put(C4944b.f4721hX, m6733d(jSONObject.getJSONObject(C4944b.f4721hX)));
            this.f4718hU.put(C4944b.f4722hY, m6733d(jSONObject.getJSONObject(C4944b.f4722hY)));
        } catch (Throwable unused) {
        }
    }

    /* renamed from: t */
    public final String mo41224t(String str, String str2) {
        if (this.f4718hU.containsKey(str)) {
            return (String) this.f4718hU.get(str).get(str2);
        }
        return null;
    }

    /* renamed from: d */
    public final String mo41223d(String str) {
        return this.f4717hT.get(str);
    }

    /* renamed from: d */
    private static HashMap<String, String> m6733d(JSONObject jSONObject) throws JSONException {
        HashMap<String, String> hashMap = new HashMap<>();
        Iterator<String> keys = jSONObject.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            hashMap.put(next, jSONObject.getString(next));
        }
        return hashMap;
    }
}

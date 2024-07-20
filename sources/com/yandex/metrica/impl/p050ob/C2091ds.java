package com.yandex.metrica.impl.p050ob;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.yandex.metrica.impl.ob.ds */
public class C2091ds {

    /* renamed from: a */
    private final long f3576a;

    /* renamed from: b */
    private final String f3577b;

    /* renamed from: c */
    private final List<Integer> f3578c;

    public C2091ds(long j, String str, List<Integer> list) {
        this.f3576a = j;
        this.f3577b = str;
        this.f3578c = list;
    }

    public C2091ds(String str) throws JSONException {
        JSONObject jSONObject = new JSONObject(str);
        this.f3576a = jSONObject.getLong("seconds_to_live");
        this.f3577b = jSONObject.getString("token");
        this.f3578c = m5455a(jSONObject.getJSONArray("ports"));
    }

    /* renamed from: a */
    private static ArrayList<Integer> m5455a(JSONArray jSONArray) throws JSONException {
        ArrayList<Integer> arrayList = new ArrayList<>(jSONArray.length());
        for (int i = 0; i < jSONArray.length(); i++) {
            arrayList.add(Integer.valueOf(jSONArray.getInt(i)));
        }
        return arrayList;
    }

    /* renamed from: a */
    public long mo17643a() {
        return this.f3576a;
    }

    /* renamed from: b */
    public String mo17644b() {
        return this.f3577b;
    }

    /* renamed from: c */
    public List<Integer> mo17645c() {
        return this.f3578c;
    }

    /* renamed from: d */
    public String mo17646d() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("seconds_to_live", this.f3576a);
        jSONObject.put("token", this.f3577b);
        jSONObject.put("ports", new JSONArray(this.f3578c));
        return jSONObject.toString();
    }
}

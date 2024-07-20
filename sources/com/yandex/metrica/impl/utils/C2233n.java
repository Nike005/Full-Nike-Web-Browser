package com.yandex.metrica.impl.utils;

import android.text.TextUtils;
import com.yandex.metrica.C1796d;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.yandex.metrica.impl.utils.n */
public class C2233n {

    /* renamed from: com.yandex.metrica.impl.utils.n$a */
    public enum C2234a {
        LOGIN("login"),
        LOGOUT("logout"),
        SWITCH("switch"),
        UPDATE("update");
        

        /* renamed from: e */
        private String f3909e;

        private C2234a(String str) {
            this.f3909e = str;
        }

        public String toString() {
            return this.f3909e;
        }
    }

    /* renamed from: a */
    public static C1796d m5974a(String str) {
        C1796d dVar = new C1796d();
        if (!TextUtils.isEmpty(str)) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                dVar.mo16692a(jSONObject.optString("UserInfo.UserId", (String) null));
                dVar.mo16695b(jSONObject.optString("UserInfo.Type", (String) null));
                dVar.mo16693a((Map<String, String>) C2223g.m5950a(jSONObject.optJSONObject("UserInfo.Options")));
            } catch (JSONException unused) {
            }
        }
        return dVar;
    }

    /* renamed from: a */
    public static String m5975a(C2234a aVar) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.putOpt("action", aVar.toString());
            return jSONObject.toString();
        } catch (JSONException unused) {
            return null;
        }
    }
}

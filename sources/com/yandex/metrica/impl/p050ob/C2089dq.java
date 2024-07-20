package com.yandex.metrica.impl.p050ob;

import android.text.TextUtils;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.yandex.metrica.impl.ob.dq */
public class C2089dq {

    /* renamed from: a */
    private final JSONObject f3572a = new JSONObject();

    /* renamed from: a */
    public void mo17637a(String str) {
        m5446a("uuid", str);
    }

    /* renamed from: b */
    public void mo17638b(String str) {
        m5446a("device_id", str);
    }

    /* renamed from: c */
    public void mo17639c(String str) {
        m5446a("google_aid", str);
    }

    /* renamed from: d */
    public void mo17640d(String str) {
        m5446a("android_id", str);
    }

    /* renamed from: a */
    private void m5446a(String str, String str2) {
        if (!(TextUtils.isEmpty(str) || TextUtils.isEmpty(str2))) {
            try {
                this.f3572a.put(str, str2);
            } catch (JSONException unused) {
            }
        }
    }

    /* renamed from: a */
    public String mo17636a() throws JSONException {
        return this.f3572a.toString();
    }
}

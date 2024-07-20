package com.tappx.p048a;

import android.content.SharedPreferences;
import android.webkit.URLUtil;

/* renamed from: com.tappx.a.t */
public class C1619t {

    /* renamed from: a */
    private final SharedPreferences f2302a;

    public C1619t(SharedPreferences sharedPreferences) {
        this.f2302a = sharedPreferences;
    }

    /* renamed from: a */
    public String mo16172a() {
        String string = this.f2302a.getString("tappx_privacy_consent_endpoint", (String) null);
        if (string == null || !URLUtil.isValidUrl(string)) {
            return C1529n.m3050c();
        }
        return string;
    }

    /* renamed from: a */
    public void mo16173a(String str) {
        this.f2302a.edit().putString("tappx_privacy_consent_endpoint", str).apply();
    }
}

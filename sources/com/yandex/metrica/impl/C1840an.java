package com.yandex.metrica.impl;

import android.text.TextUtils;
import com.yandex.metrica.PreloadInfo;
import com.yandex.metrica.impl.utils.C2228j;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.yandex.metrica.impl.an */
public class C1840an {

    /* renamed from: a */
    private PreloadInfo f2955a;

    public C1840an(PreloadInfo preloadInfo) {
        if (preloadInfo == null) {
            return;
        }
        if (TextUtils.isEmpty(preloadInfo.getTrackingId())) {
            C2228j.m5960f().mo17909c("Required field \"PreloadInfo.trackingId\" is empty!\nThis preload info will be skipped.");
        } else {
            this.f2955a = preloadInfo;
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public String mo16838a() {
        if (this.f2955a == null) {
            return "";
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("preloadInfo", mo16839b());
        } catch (JSONException unused) {
        }
        return jSONObject.toString();
    }

    /* renamed from: b */
    public JSONObject mo16839b() {
        if (this.f2955a == null) {
            return null;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("trackingId", this.f2955a.getTrackingId());
            if (!this.f2955a.getAdditionalParams().isEmpty()) {
                jSONObject.put("additionalParams", new JSONObject(this.f2955a.getAdditionalParams()));
            }
        } catch (JSONException unused) {
        }
        return jSONObject;
    }
}

package com.startapp.android.publish.adsCommon.Utils;

import com.startapp.common.C1301e;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.startapp.android.publish.adsCommon.Utils.c */
/* compiled from: StartAppSDK */
public class C1054c extends C1056e {

    /* renamed from: a */
    private JSONObject f983a;

    public C1054c() {
        this.f983a = null;
        this.f983a = new JSONObject();
    }

    /* renamed from: a */
    public void mo14627a(String str, Object obj, boolean z, boolean z2) {
        if (z && obj == null) {
            throw new C1301e("Required key: [" + str + "] is missing", (Throwable) null);
        } else if (obj != null && !obj.toString().equals("")) {
            try {
                this.f983a.put(str, obj);
            } catch (JSONException e) {
                if (z) {
                    throw new C1301e("failed converting to json object value: [" + obj + "]", e);
                }
            }
        }
    }

    /* renamed from: a */
    public void mo14628a(String str, Set<String> set, boolean z, boolean z2) {
        if (z && (set == null || set.size() == 0)) {
            throw new C1301e("Required key: [" + str + "] is missing", (Throwable) null);
        } else if (set != null && set.size() > 0) {
            try {
                this.f983a.put(str, new JSONArray(set));
            } catch (JSONException e) {
                if (z) {
                    throw new C1301e("failed converting to json array values: [" + set + "]", e);
                }
            }
        }
    }

    public String toString() {
        return this.f983a.toString();
    }
}

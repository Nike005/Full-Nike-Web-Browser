package com.yandex.metrica.impl;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.ResolveInfo;
import android.os.SystemClock;
import android.util.Base64;
import com.yandex.metrica.impl.utils.C2217b;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.HashSet;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.yandex.metrica.impl.t */
public final class C2214t {

    /* renamed from: a */
    JSONObject f3887a;

    public C2214t(String str) {
        try {
            this.f3887a = new JSONObject(str);
        } catch (Exception unused) {
            this.f3887a = new JSONObject();
        }
    }

    /* renamed from: a */
    public C2214t mo17889a() {
        try {
            mo17893c();
            mo17892b();
        } catch (Exception unused) {
        }
        return this;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public C2214t mo17892b() throws JSONException {
        ((JSONObject) m5907a(this.f3887a, "dfid", new JSONObject())).put("boot_time", (System.currentTimeMillis() - SystemClock.elapsedRealtime()) / 1000);
        return this;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public C2214t mo17891a(Context context, boolean z) throws JSONException, UnsupportedEncodingException {
        JSONObject jSONObject = (JSONObject) m5907a((JSONObject) m5907a(this.f3887a, "dfid", new JSONObject()), "au", new JSONObject());
        JSONArray jSONArray = (JSONArray) m5907a(jSONObject, "aun", new JSONArray());
        JSONArray jSONArray2 = (JSONArray) m5907a(jSONObject, "ausf", new JSONArray());
        JSONArray jSONArray3 = (JSONArray) m5907a(jSONObject, "audf", new JSONArray());
        JSONArray jSONArray4 = (JSONArray) m5907a(jSONObject, "aulu", new JSONArray());
        JSONArray jSONArray5 = new JSONArray();
        if (z) {
            m5907a(jSONObject, "aufi", jSONArray5);
        }
        HashSet hashSet = new HashSet();
        for (ResolveInfo resolveInfo : C1897bk.m4642a(context, new String(Base64.decode("YW5kcm9pZC5pbnRlbnQuYWN0aW9uLk1BSU4=", 0), "UTF-8"), new String(Base64.decode("YW5kcm9pZC5pbnRlbnQuY2F0ZWdvcnkuTEFVTkNIRVI=", 0), "UTF-8"))) {
            ApplicationInfo applicationInfo = resolveInfo.activityInfo.applicationInfo;
            if (hashSet.add(applicationInfo.packageName)) {
                jSONArray.put(applicationInfo.packageName);
                boolean z2 = (applicationInfo.flags & 1) == 1;
                jSONArray2.put(z2);
                jSONArray4.put(new File(applicationInfo.sourceDir).lastModified());
                jSONArray3.put(true ^ applicationInfo.enabled);
                if (z) {
                    if (z2) {
                        jSONArray5.put(0);
                    } else if (C1897bk.m4658c(context, applicationInfo.packageName) == null) {
                        jSONArray5.put(0);
                    } else {
                        jSONArray5.put(C1897bk.m4658c(context, applicationInfo.packageName).firstInstallTime / 1000);
                    }
                }
            }
        }
        return this;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public C2214t mo17890a(Context context) throws JSONException {
        JSONObject jSONObject = (JSONObject) m5907a((JSONObject) m5907a(this.f3887a, "dfid", new JSONObject()), "apps", new JSONObject());
        JSONArray jSONArray = (JSONArray) m5907a(jSONObject, "names", new JSONArray());
        JSONArray jSONArray2 = (JSONArray) m5907a(jSONObject, "system_flags", new JSONArray());
        JSONArray jSONArray3 = (JSONArray) m5907a(jSONObject, "disabled_flags", new JSONArray());
        JSONArray jSONArray4 = (JSONArray) m5907a(jSONObject, "first_install_time", new JSONArray());
        JSONArray jSONArray5 = (JSONArray) m5907a(jSONObject, "last_update_time", new JSONArray());
        jSONObject.put("version", 0);
        for (PackageInfo next : context.getPackageManager().getInstalledPackages(0)) {
            jSONArray.put(next.packageName);
            jSONArray2.put((next.applicationInfo.flags & 1) == 1);
            jSONArray3.put(!next.applicationInfo.enabled);
            jSONArray4.put(next.firstInstallTime / 1000);
            jSONArray5.put(next.lastUpdateTime / 1000);
        }
        return this;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: c */
    public C2214t mo17893c() throws JSONException {
        JSONObject jSONObject = (JSONObject) m5907a(this.f3887a, "dfid", new JSONObject());
        long a = C1838am.m4249a(true);
        long a2 = C1838am.m4249a(false);
        long c = C1838am.m4253c(true);
        long c2 = C1838am.m4253c(false);
        jSONObject.put("tds", a + a2);
        jSONObject.put("fds", c + c2);
        return this;
    }

    /* renamed from: a */
    static <T> T m5907a(JSONObject jSONObject, String str, T t) throws JSONException {
        if (!jSONObject.has(str)) {
            jSONObject.put(str, t);
        }
        return jSONObject.get(str);
    }

    public String toString() {
        return this.f3887a.toString();
    }

    /* renamed from: d */
    public String mo17894d() {
        return Base64.encodeToString(new C2217b().mo17897a(C1894bi.m4629c(this.f3887a.toString())), 0);
    }
}

package com.p088b.p089a.p090a.p091a.p097e;

import android.os.Build;
import org.json.JSONObject;

/* renamed from: com.b.a.a.a.e.a */
public final class C5134a {
    /* renamed from: a */
    public static String m7111a() {
        return Build.MANUFACTURER + "; " + Build.MODEL;
    }

    /* renamed from: b */
    public static String m7112b() {
        return Integer.toString(Build.VERSION.SDK_INT);
    }

    /* renamed from: c */
    public static String m7113c() {
        return "Android";
    }

    /* renamed from: d */
    public static JSONObject m7114d() {
        JSONObject jSONObject = new JSONObject();
        C5135b.m7120a(jSONObject, "deviceType", m7111a());
        C5135b.m7120a(jSONObject, "osVersion", m7112b());
        C5135b.m7120a(jSONObject, "os", m7113c());
        return jSONObject;
    }
}

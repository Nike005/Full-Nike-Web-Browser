package com.startapp.common.p047d;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.HttpURLConnection;
import java.net.URI;
import java.util.List;
import java.util.Map;

/* renamed from: com.startapp.common.d.a */
/* compiled from: StartAppSDK */
public class C1299a {

    /* renamed from: a */
    private static CookieManager f1560a;

    /* renamed from: a */
    public static void m2191a(Context context) {
        f1560a = new CookieManager(new C1300b(context), CookiePolicy.ACCEPT_ALL);
    }

    /* renamed from: b */
    public static void m2193b(Context context) {
        if (Build.VERSION.SDK_INT >= 9) {
            m2191a(context);
        }
    }

    /* renamed from: a */
    public static void m2192a(HttpURLConnection httpURLConnection, String str) {
        CookieManager a;
        Map<String, List<String>> map;
        if (Build.VERSION.SDK_INT >= 9 && (a = m2190a()) != null && (map = a.get(URI.create(str), httpURLConnection.getRequestProperties())) != null && map.size() > 0 && map.get("Cookie").size() > 0) {
            httpURLConnection.addRequestProperty("Cookie", TextUtils.join("=", map.get("Cookie")));
        }
    }

    /* renamed from: b */
    public static void m2194b(HttpURLConnection httpURLConnection, String str) {
        CookieManager a;
        if (Build.VERSION.SDK_INT >= 9 && (a = m2190a()) != null) {
            a.put(URI.create(str), httpURLConnection.getHeaderFields());
        }
    }

    /* renamed from: a */
    public static CookieManager m2190a() {
        return f1560a;
    }
}

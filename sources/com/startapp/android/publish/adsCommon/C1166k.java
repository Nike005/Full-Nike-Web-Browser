package com.startapp.android.publish.adsCommon;

import android.content.Context;
import android.content.SharedPreferences;
import com.startapp.android.publish.adsCommon.Utils.C1061i;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.startapp.android.publish.adsCommon.k */
/* compiled from: StartAppSDK */
public class C1166k {

    /* renamed from: a */
    private static SharedPreferences f1248a;

    /* renamed from: a */
    public static SharedPreferences m1605a(Context context) {
        if (f1248a == null) {
            f1248a = context.getApplicationContext().getSharedPreferences("com.startapp.android.publish", 0);
        }
        return f1248a;
    }

    /* renamed from: a */
    public static Boolean m1606a(Context context, String str, Boolean bool) {
        return Boolean.valueOf(m1605a(context).getBoolean(str, bool.booleanValue()));
    }

    /* renamed from: b */
    public static void m1613b(Context context, String str, Boolean bool) {
        C1061i.m1187a(m1605a(context).edit().putBoolean(str, bool.booleanValue()));
    }

    /* renamed from: a */
    public static String m1610a(Context context, String str, String str2) {
        return m1605a(context).getString(str, str2);
    }

    /* renamed from: b */
    public static void m1617b(Context context, String str, String str2) {
        C1061i.m1187a(m1605a(context).edit().putString(str, str2));
    }

    /* renamed from: a */
    public static Integer m1608a(Context context, String str, Integer num) {
        return Integer.valueOf(m1605a(context).getInt(str, num.intValue()));
    }

    /* renamed from: b */
    public static void m1615b(Context context, String str, Integer num) {
        C1061i.m1187a(m1605a(context).edit().putInt(str, num.intValue()));
    }

    /* renamed from: a */
    public static Float m1607a(Context context, String str, Float f) {
        return Float.valueOf(m1605a(context).getFloat(str, f.floatValue()));
    }

    /* renamed from: b */
    public static void m1614b(Context context, String str, Float f) {
        C1061i.m1187a(m1605a(context).edit().putFloat(str, f.floatValue()));
    }

    /* renamed from: a */
    public static Long m1609a(Context context, String str, Long l) {
        return Long.valueOf(m1605a(context).getLong(str, l.longValue()));
    }

    /* renamed from: b */
    public static void m1616b(Context context, String str, Long l) {
        C1061i.m1187a(m1605a(context).edit().putLong(str, l.longValue()));
    }

    /* renamed from: a */
    public static void m1611a(Context context, String str, Map<String, String> map) {
        m1617b(context, str, new JSONObject(map).toString());
    }

    /* renamed from: b */
    public static Map<String, String> m1612b(Context context, String str, Map<String, String> map) {
        try {
            JSONObject jSONObject = new JSONObject(m1605a(context).getString(str, (String) null));
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                map.put(next, (String) jSONObject.get(next));
            }
            return map;
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Throwable unused) {
        }
        return map;
    }
}

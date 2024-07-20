package com.p088b.p089a.p090a.p091a.p097e;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.os.Build;
import android.view.WindowManager;
import com.integralads.avid.library.mopub.utils.AvidJSONUtil;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.b.a.a.a.e.b */
public class C5135b {

    /* renamed from: a */
    static float f4998a = Resources.getSystem().getDisplayMetrics().density;

    /* renamed from: b */
    private static WindowManager f4999b;

    /* renamed from: c */
    private static String[] f5000c = {AvidJSONUtil.KEY_X, AvidJSONUtil.KEY_Y, "width", "height"};

    /* renamed from: com.b.a.a.a.e.b$a */
    private static class C5136a {

        /* renamed from: a */
        final float f5001a;

        /* renamed from: b */
        final float f5002b;

        C5136a(float f, float f2) {
            this.f5001a = f;
            this.f5002b = f2;
        }
    }

    /* renamed from: a */
    static float m7115a(int i) {
        return ((float) i) / f4998a;
    }

    /* renamed from: a */
    public static JSONObject m7116a(int i, int i2, int i3, int i4) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(AvidJSONUtil.KEY_X, (double) m7115a(i));
            jSONObject.put(AvidJSONUtil.KEY_Y, (double) m7115a(i2));
            jSONObject.put("width", (double) m7115a(i3));
            jSONObject.put("height", (double) m7115a(i4));
        } catch (JSONException e) {
            C5137c.m7131a("Error with creating viewStateObject", e);
        }
        return jSONObject;
    }

    /* renamed from: a */
    public static void m7117a(Context context) {
        if (context != null) {
            f4998a = context.getResources().getDisplayMetrics().density;
            f4999b = (WindowManager) context.getSystemService("window");
        }
    }

    /* renamed from: a */
    public static void m7118a(JSONObject jSONObject) {
        C5136a b = m7124b(jSONObject);
        try {
            jSONObject.put("width", (double) b.f5001a);
            jSONObject.put("height", (double) b.f5002b);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /* renamed from: a */
    public static void m7119a(JSONObject jSONObject, String str) {
        try {
            jSONObject.put("adSessionId", str);
        } catch (JSONException e) {
            C5137c.m7131a("Error with setting ad session id", e);
        }
    }

    /* renamed from: a */
    public static void m7120a(JSONObject jSONObject, String str, Object obj) {
        try {
            jSONObject.put(str, obj);
        } catch (JSONException e) {
            C5137c.m7131a("JSONException during JSONObject.put for name [" + str + "]", e);
        }
    }

    /* renamed from: a */
    public static void m7121a(JSONObject jSONObject, List<String> list) {
        JSONArray jSONArray = new JSONArray();
        for (String put : list) {
            jSONArray.put(put);
        }
        try {
            jSONObject.put(AvidJSONUtil.KEY_IS_FRIENDLY_OBSTRUCTION_FOR, jSONArray);
        } catch (JSONException e) {
            C5137c.m7131a("Error with setting friendly obstruction", e);
        }
    }

    /* renamed from: a */
    public static void m7122a(JSONObject jSONObject, JSONObject jSONObject2) {
        try {
            JSONArray optJSONArray = jSONObject.optJSONArray(AvidJSONUtil.KEY_CHILD_VIEWS);
            if (optJSONArray == null) {
                optJSONArray = new JSONArray();
                jSONObject.put(AvidJSONUtil.KEY_CHILD_VIEWS, optJSONArray);
            }
            optJSONArray.put(jSONObject2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /* renamed from: a */
    private static boolean m7123a(JSONArray jSONArray, JSONArray jSONArray2) {
        if (jSONArray == null && jSONArray2 == null) {
            return true;
        }
        return (jSONArray == null || jSONArray2 == null || jSONArray.length() != jSONArray2.length()) ? false : true;
    }

    /* renamed from: b */
    private static C5136a m7124b(JSONObject jSONObject) {
        float f;
        float f2 = 0.0f;
        if (Build.VERSION.SDK_INT < 17) {
            JSONArray optJSONArray = jSONObject.optJSONArray(AvidJSONUtil.KEY_CHILD_VIEWS);
            if (optJSONArray != null) {
                int length = optJSONArray.length();
                float f3 = 0.0f;
                for (int i = 0; i < length; i++) {
                    JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                    if (optJSONObject != null) {
                        double optDouble = optJSONObject.optDouble(AvidJSONUtil.KEY_X);
                        double optDouble2 = optJSONObject.optDouble(AvidJSONUtil.KEY_Y);
                        double optDouble3 = optJSONObject.optDouble("width");
                        double optDouble4 = optJSONObject.optDouble("height");
                        f2 = Math.max(f2, (float) (optDouble + optDouble3));
                        f3 = Math.max(f3, (float) (optDouble2 + optDouble4));
                    }
                }
                f = f3;
                return new C5136a(f2, f);
            }
        } else if (f4999b != null) {
            Point point = new Point(0, 0);
            f4999b.getDefaultDisplay().getRealSize(point);
            f2 = m7115a(point.x);
            f = m7115a(point.y);
            return new C5136a(f2, f);
        }
        f = 0.0f;
        return new C5136a(f2, f);
    }

    /* renamed from: b */
    public static boolean m7125b(JSONObject jSONObject, JSONObject jSONObject2) {
        if (jSONObject == null && jSONObject2 == null) {
            return true;
        }
        return jSONObject != null && jSONObject2 != null && m7126c(jSONObject, jSONObject2) && m7127d(jSONObject, jSONObject2) && m7128e(jSONObject, jSONObject2) && m7129f(jSONObject, jSONObject2);
    }

    /* renamed from: c */
    private static boolean m7126c(JSONObject jSONObject, JSONObject jSONObject2) {
        for (String str : f5000c) {
            if (jSONObject.optDouble(str) != jSONObject2.optDouble(str)) {
                return false;
            }
        }
        return true;
    }

    /* renamed from: d */
    private static boolean m7127d(JSONObject jSONObject, JSONObject jSONObject2) {
        return jSONObject.optString("adSessionId", "").equals(jSONObject2.optString("adSessionId", ""));
    }

    /* renamed from: e */
    private static boolean m7128e(JSONObject jSONObject, JSONObject jSONObject2) {
        JSONArray optJSONArray = jSONObject.optJSONArray(AvidJSONUtil.KEY_IS_FRIENDLY_OBSTRUCTION_FOR);
        JSONArray optJSONArray2 = jSONObject2.optJSONArray(AvidJSONUtil.KEY_IS_FRIENDLY_OBSTRUCTION_FOR);
        if (optJSONArray == null && optJSONArray2 == null) {
            return true;
        }
        if (!m7123a(optJSONArray, optJSONArray2)) {
            return false;
        }
        for (int i = 0; i < optJSONArray.length(); i++) {
            if (!optJSONArray.optString(i, "").equals(optJSONArray2.optString(i, ""))) {
                return false;
            }
        }
        return true;
    }

    /* renamed from: f */
    private static boolean m7129f(JSONObject jSONObject, JSONObject jSONObject2) {
        JSONArray optJSONArray = jSONObject.optJSONArray(AvidJSONUtil.KEY_CHILD_VIEWS);
        JSONArray optJSONArray2 = jSONObject2.optJSONArray(AvidJSONUtil.KEY_CHILD_VIEWS);
        if (optJSONArray == null && optJSONArray2 == null) {
            return true;
        }
        if (!m7123a(optJSONArray, optJSONArray2)) {
            return false;
        }
        for (int i = 0; i < optJSONArray.length(); i++) {
            if (!m7125b(optJSONArray.optJSONObject(i), optJSONArray2.optJSONObject(i))) {
                return false;
            }
        }
        return true;
    }
}

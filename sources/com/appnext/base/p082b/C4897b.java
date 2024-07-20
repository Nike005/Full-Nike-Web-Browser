package com.appnext.base.p082b;

import android.text.TextUtils;
import com.appnext.base.p078a.C4880a;
import com.appnext.base.p078a.p080b.C4886b;
import com.appnext.base.p078a.p080b.C4887c;
import com.appnext.base.p078a.p081c.C4892d;
import com.appnext.base.p082b.C4899d;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

/* renamed from: com.appnext.base.b.b */
public final class C4897b {
    /* renamed from: d */
    public static boolean m6567d(C4887c cVar) {
        if (cVar == null) {
            return false;
        }
        try {
            int i = C4905i.m6591aR().getInt(cVar.getKey() + C4905i.f4636fA, 0);
            try {
                if (i >= Integer.parseInt(cVar.mo40961an()) || i == 0) {
                    return true;
                }
                return false;
            } catch (NumberFormatException unused) {
            }
        } catch (Throwable unused2) {
            return false;
        }
    }

    /* renamed from: E */
    private static List<C4886b> m6560E(String str) {
        try {
            return C4880a.m6472X().mo40941aa().mo40990v(str);
        } catch (Throwable unused) {
            return null;
        }
    }

    /* renamed from: F */
    public static boolean m6561F(String str) {
        List<C4886b> E = m6560E(str);
        return E == null || E.size() == 0;
    }

    /* renamed from: a */
    public static JSONObject m6565a(String str, Date date, C4899d.C4900a aVar) {
        JSONObject jSONObject = new JSONObject();
        try {
            String L = C4904h.m6584aO().mo41008L(str);
            if (TextUtils.isEmpty(L)) {
                return jSONObject;
            }
            jSONObject.put("data", C4906j.m6601b(L, aVar));
            jSONObject.put("date", C4906j.m6594a(date));
            return jSONObject;
        } catch (Throwable unused) {
        }
    }

    /* renamed from: G */
    public static void m6562G(String str) {
        if (str != null) {
            try {
                C4905i.m6591aR().putInt(str + C4905i.f4636fA, 0);
            } catch (Throwable unused) {
            }
        }
    }

    /* renamed from: H */
    public static void m6563H(String str) {
        try {
            C4905i.m6591aR().putLong(str + C4905i.f4641fy, System.currentTimeMillis());
            String str2 = str + C4905i.f4636fA;
            C4905i.m6591aR().putInt(str2, C4905i.m6591aR().getInt(str2, 0) + 1);
        } catch (Throwable unused) {
        }
    }

    /* renamed from: a */
    public static JSONArray m6564a(List<C4886b> list, boolean z) {
        try {
            JSONArray jSONArray = new JSONArray();
            for (C4886b next : list) {
                String ai = next.mo40951ai();
                if (!ai.isEmpty()) {
                    String K = C4904h.m6584aO().mo41007K(ai);
                    if (!TextUtils.isEmpty(K)) {
                        JSONObject jSONObject = new JSONObject();
                        jSONObject.put(C4892d.f4593ec, next.mo40950ah());
                        jSONObject.put(C4892d.COLUMN_TYPE, next.getType());
                        jSONObject.put(C4892d.f4594ed, K);
                        jSONObject.put(C4892d.f4596ef, next.getDataType());
                        jSONArray.put(jSONObject);
                    }
                }
            }
            return jSONArray;
        } catch (Throwable unused) {
            return null;
        }
    }

    /* renamed from: a */
    public static boolean m6566a(String str, Map<String, String> map) {
        try {
            return C4906j.m6603b(str, map);
        } catch (Throwable unused) {
            return false;
        }
    }
}

package com.startapp.common.p046c;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.startapp.common.c.b */
/* compiled from: StartAppSDK */
public class C1295b {
    /* JADX WARNING: Removed duplicated region for block: B:13:0x0014 A[SYNTHETIC, Splitter:B:13:0x0014] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static <T> T m2179a(java.lang.String r2, java.lang.Class<T> r3) {
        /*
            r0 = 0
            com.startapp.common.c.a r1 = new com.startapp.common.c.a     // Catch:{ all -> 0x0011 }
            r1.<init>(r2)     // Catch:{ all -> 0x0011 }
            java.lang.Object r2 = r1.mo15503a(r3, (org.json.JSONObject) r0)     // Catch:{ all -> 0x000e }
            r1.close()     // Catch:{ IOException -> 0x000d }
        L_0x000d:
            return r2
        L_0x000e:
            r2 = move-exception
            r0 = r1
            goto L_0x0012
        L_0x0011:
            r2 = move-exception
        L_0x0012:
            if (r0 == 0) goto L_0x0017
            r0.close()     // Catch:{ IOException -> 0x0017 }
        L_0x0017:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.startapp.common.p046c.C1295b.m2179a(java.lang.String, java.lang.Class):java.lang.Object");
    }

    /* renamed from: a */
    public static String m2180a(Object obj) {
        return m2182c(obj).toString();
    }

    /* renamed from: c */
    private static JSONObject m2182c(Object obj) {
        Class cls = obj.getClass();
        ArrayList<Field> arrayList = new ArrayList<>();
        while (cls != null && !Object.class.equals(cls)) {
            arrayList.addAll(Arrays.asList(cls.getDeclaredFields()));
            cls = cls.getSuperclass();
        }
        JSONObject jSONObject = new JSONObject();
        for (Field field : arrayList) {
            int modifiers = field.getModifiers();
            if (!Modifier.isStatic(modifiers) && !Modifier.isTransient(modifiers)) {
                try {
                    field.setAccessible(true);
                    if (field.get(obj) != null) {
                        String a = C1296c.m2183a(field);
                        if (C1296c.m2188e(field)) {
                            jSONObject.put(a, m2182c(field.get(obj)));
                        } else if (C1296c.m2186c(field)) {
                            JSONArray jSONArray = new JSONArray();
                            for (Object b : (List) field.get(obj)) {
                                jSONArray.put(m2181b(b));
                            }
                            jSONObject.put(a, jSONArray);
                        } else if (C1296c.m2187d(field)) {
                            JSONArray jSONArray2 = new JSONArray();
                            for (Object b2 : (Set) field.get(obj)) {
                                jSONArray2.put(m2181b(b2));
                            }
                            jSONObject.put(a, jSONArray2);
                        } else if (C1296c.m2185b(field)) {
                            JSONObject jSONObject2 = new JSONObject();
                            for (Map.Entry entry : ((Map) field.get(obj)).entrySet()) {
                                jSONObject2.put(entry.getKey().toString(), m2181b(entry.getValue()));
                            }
                            jSONObject.put(a, jSONObject2);
                        } else {
                            jSONObject.put(a, field.get(obj));
                        }
                    }
                } catch (IllegalAccessException | IllegalArgumentException | JSONException unused) {
                }
            }
        }
        return jSONObject;
    }

    /* renamed from: b */
    public static Object m2181b(Object obj) {
        if (C1296c.m2184a(obj)) {
            return obj;
        }
        return m2182c(obj);
    }
}

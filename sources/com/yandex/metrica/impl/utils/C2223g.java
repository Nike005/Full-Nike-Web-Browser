package com.yandex.metrica.impl.utils;

import android.text.TextUtils;
import com.yandex.metrica.impl.C1897bk;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.yandex.metrica.impl.utils.g */
public class C2223g {

    /* renamed from: com.yandex.metrica.impl.utils.g$a */
    public static class C2224a extends JSONObject {
        public C2224a(String str) throws JSONException {
            super(str);
        }

        /* renamed from: a */
        public String mo17922a(String str) {
            try {
                return super.getString(str);
            } catch (Exception unused) {
                return "";
            }
        }

        /* renamed from: a */
        public Object mo17921a(String str, Object obj) {
            try {
                return super.get(str);
            } catch (Exception unused) {
                return obj;
            }
        }

        /* renamed from: b */
        public boolean mo17923b(String str) {
            try {
                return NULL != super.get(str);
            } catch (Exception unused) {
            }
        }
    }

    /* renamed from: a */
    public static Object m5946a(Object obj) {
        if (obj == null) {
            return null;
        }
        try {
            if (obj.getClass().isArray()) {
                int length = Array.getLength(obj);
                ArrayList arrayList = new ArrayList(length);
                for (int i = 0; i < length; i++) {
                    arrayList.add(m5946a(Array.get(obj, i)));
                }
                return new JSONArray(arrayList);
            } else if (obj instanceof Collection) {
                Collection<Object> collection = (Collection) obj;
                ArrayList arrayList2 = new ArrayList(collection.size());
                for (Object a : collection) {
                    arrayList2.add(m5946a(a));
                }
                return new JSONArray(arrayList2);
            } else if (!(obj instanceof Map)) {
                return obj;
            } else {
                LinkedHashMap linkedHashMap = new LinkedHashMap();
                for (Map.Entry entry : ((Map) obj).entrySet()) {
                    String obj2 = entry.getKey().toString();
                    if (obj2 != null) {
                        linkedHashMap.put(obj2, m5946a(entry.getValue()));
                    }
                }
                return new JSONObject(linkedHashMap);
            }
        } catch (Exception unused) {
            return null;
        }
    }

    /* renamed from: a */
    public static String m5948a(Map map) {
        if (C1897bk.m4653a(map)) {
            return null;
        }
        if (C1897bk.m4650a(19)) {
            return new JSONObject(map).toString();
        }
        return m5946a((Object) map).toString();
    }

    /* renamed from: a */
    public static String m5947a(List<String> list) {
        if (C1897bk.m4652a((Collection) list)) {
            return null;
        }
        if (C1897bk.m4650a(19)) {
            return new JSONArray(list).toString();
        }
        return m5946a((Object) list).toString();
    }

    /* renamed from: a */
    public static HashMap<String, String> m5949a(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            return m5950a(new JSONObject(str));
        } catch (JSONException unused) {
            return null;
        }
    }

    /* renamed from: b */
    public static List<String> m5951b(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            JSONArray jSONArray = new JSONArray(str);
            ArrayList arrayList = new ArrayList(jSONArray.length());
            int i = 0;
            while (i < jSONArray.length()) {
                try {
                    arrayList.add(jSONArray.getString(i));
                    i++;
                } catch (JSONException unused) {
                }
            }
            return arrayList;
        } catch (JSONException unused2) {
            return null;
        }
    }

    /* renamed from: a */
    public static HashMap<String, String> m5950a(JSONObject jSONObject) {
        if (JSONObject.NULL.equals(jSONObject)) {
            return null;
        }
        HashMap<String, String> hashMap = new HashMap<>();
        Iterator<String> keys = jSONObject.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            String optString = jSONObject.optString(next);
            if (optString != null) {
                hashMap.put(next, optString);
            }
        }
        return hashMap;
    }
}

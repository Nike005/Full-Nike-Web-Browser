package com.yandex.metrica.impl.utils;

import android.text.TextUtils;
import com.yandex.metrica.impl.C1897bk;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.yandex.metrica.impl.utils.l */
public class C2231l {
    /* renamed from: a */
    public static String m5971a(Map<String, String> map) {
        if (C1897bk.m4653a((Map) map)) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (Map.Entry next : map.entrySet()) {
            if (!TextUtils.isEmpty((CharSequence) next.getKey())) {
                sb.append((String) next.getKey());
                sb.append(":");
                sb.append((String) next.getValue());
                sb.append(",");
            }
        }
        sb.setLength(sb.length() - 1);
        return sb.toString();
    }

    /* renamed from: a */
    public static Map<String, String> m5972a(String str) {
        HashMap hashMap = new HashMap();
        if (!TextUtils.isEmpty(str)) {
            for (String str2 : str.split(",")) {
                int indexOf = str2.indexOf(":");
                if (indexOf != -1) {
                    String substring = str2.substring(0, indexOf);
                    if (!TextUtils.isEmpty(substring)) {
                        hashMap.put(substring, str2.substring(indexOf + 1));
                    }
                }
            }
        }
        return hashMap;
    }
}

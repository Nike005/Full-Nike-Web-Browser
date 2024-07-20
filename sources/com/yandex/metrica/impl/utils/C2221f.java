package com.yandex.metrica.impl.utils;

import android.text.TextUtils;
import com.yandex.metrica.impl.C1894bi;
import java.io.UnsupportedEncodingException;
import java.util.Map;

/* renamed from: com.yandex.metrica.impl.utils.f */
public class C2221f {

    /* renamed from: com.yandex.metrica.impl.utils.f$a */
    public static class C2222a {

        /* renamed from: a */
        private final int f3894a;

        /* renamed from: b */
        private final int f3895b;

        /* renamed from: c */
        private final int f3896c;

        public C2222a(int i, int i2, int i3) {
            this.f3894a = i;
            this.f3895b = i2;
            this.f3896c = i3;
        }

        /* renamed from: a */
        public int mo17918a() {
            return this.f3894a;
        }

        /* renamed from: b */
        public int mo17919b() {
            return this.f3895b;
        }

        /* renamed from: c */
        public int mo17920c() {
            return this.f3896c;
        }

        /* renamed from: d */
        public static C2222a m5942d() {
            return new C2222a(30, 50, 100);
        }
    }

    /* renamed from: a */
    public boolean mo17915a(String str, String str2) {
        return !C1894bi.m4623a(str, str2);
    }

    /* renamed from: a */
    public Map<String, String> mo17914a(Map<String, String> map, String str, String str2, C2222a aVar, String str3) {
        if (map != null) {
            String a = mo17913a(str, aVar.mo17919b(), str3);
            String a2 = mo17913a(str2, aVar.mo17920c(), str3);
            if (map.size() < aVar.mo17918a() || map.containsKey(a)) {
                map.put(a, a2);
            } else {
                mo17917b(a, aVar.mo17918a(), str3);
            }
        }
        return map;
    }

    /* renamed from: a */
    public String mo17913a(String str, int i, String str2) {
        if (str == null || str.length() <= i) {
            return str;
        }
        String substring = str.substring(0, i);
        C2228j.m5960f().mo17905b("\"%s\"'s parameter %s size exceeded limit of %d characters", str2, str, Integer.valueOf(i));
        return substring;
    }

    /* renamed from: a */
    public String mo17912a(String str, int i) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        try {
            byte[] bytes = str.getBytes("UTF-8");
            return bytes.length > i ? new String(bytes, 0, i, "UTF-8") : str;
        } catch (UnsupportedEncodingException unused) {
            return str;
        }
    }

    /* renamed from: a */
    public byte[] mo17916a(byte[] bArr, int i) {
        if (bArr.length <= i) {
            return bArr;
        }
        byte[] bArr2 = new byte[i];
        System.arraycopy(bArr, 0, bArr2, 0, i);
        return bArr2;
    }

    /* renamed from: b */
    public void mo17917b(String str, int i, String str2) {
        C2228j.m5960f().mo17905b("The %s has reached the limit of %d items. Item with key %s will be ignored", str2, Integer.valueOf(i), str);
    }
}

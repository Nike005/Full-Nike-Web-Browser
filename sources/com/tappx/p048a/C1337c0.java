package com.tappx.p048a;

import java.io.UnsupportedEncodingException;
import java.util.Map;
import org.apache.commons.lang3.CharEncoding;

/* renamed from: com.tappx.a.c0 */
public class C1337c0 {

    /* renamed from: a */
    public final Map<String, String> f1650a;

    /* renamed from: b */
    public final byte[] f1651b;

    /* renamed from: c */
    public final long f1652c;

    public C1337c0(int i, Map<String, String> map, byte[] bArr, long j) {
        this.f1651b = bArr;
        this.f1650a = map;
        this.f1652c = j;
    }

    /* renamed from: a */
    public String mo15592a() {
        if (this.f1651b == null) {
            return null;
        }
        try {
            return new String(this.f1651b, m2329a(this.f1650a));
        } catch (UnsupportedEncodingException unused) {
            return new String(this.f1651b);
        }
    }

    /* renamed from: a */
    static String m2330a(Map<String, String> map, String str) {
        String str2 = map.get("Content-Type");
        if (str2 != null) {
            String[] split = str2.split(";");
            for (int i = 1; i < split.length; i++) {
                String[] split2 = split[i].trim().split("=");
                if (split2.length == 2 && split2[0].equals("charset")) {
                    return split2[1];
                }
            }
        }
        return str;
    }

    /* renamed from: a */
    public static String m2329a(Map<String, String> map) {
        return m2330a(map, CharEncoding.ISO_8859_1);
    }
}

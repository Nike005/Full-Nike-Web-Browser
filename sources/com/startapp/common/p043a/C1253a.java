package com.startapp.common.p043a;

import android.util.Base64;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;

/* renamed from: com.startapp.common.a.a */
/* compiled from: StartAppSDK */
public class C1253a {

    /* renamed from: a */
    private static final byte[] f1476a = {10, 30, 84, 95, 101, 20, 0, 14, 15, 80, 36, 84, 64, 82, 84, 64, 80, 80, 65, 78, 84, 73, 70, 82, 65, 85, 68, 75, 69, 89, 1, 2, 3, 8, 15, 42, 10, 51, 44, 32};

    /* renamed from: b */
    private static final String f1477b = "ts";

    /* renamed from: c */
    private static final String f1478c = "tsh";

    /* renamed from: d */
    private static final String f1479d = "afh";

    /* renamed from: e */
    private static final String f1480e = "MD5";

    /* renamed from: f */
    private static final String f1481f = "UTF-8";

    /* renamed from: g */
    private static final byte[] f1482g = {12, 31, 86, 96, 103, 10, 28, 15, 17, 28, 36, 84, 64, 82, 84, 64, 80, 80, 69, 78, 67, 82, 89, 80, 84, 73, 79, 78, 75, 69, 89, 4, 32, 18, 16, 18, 11, 53, 45, 34};

    /* renamed from: a */
    public static String m1981a() {
        return f1477b;
    }

    /* renamed from: b */
    public static String m1985b() {
        return f1478c;
    }

    /* renamed from: c */
    public static String m1987c() {
        return f1479d;
    }

    /* renamed from: a */
    public static String m1982a(String str) {
        String str2 = "";
        if (str != null) {
            try {
                str2 = URLDecoder.decode(str, f1481f);
            } catch (UnsupportedEncodingException unused) {
            }
        }
        String d = m1989d();
        return "&" + f1477b + "=" + d + "&" + f1479d + "=" + m1986b(str2 + d);
    }

    /* renamed from: d */
    public static String m1989d() {
        int hashCode = f1476a.hashCode();
        long currentTimeMillis = System.currentTimeMillis();
        if (hashCode > 0) {
            int i = (int) ((((currentTimeMillis * 25214903917L) + 11) & 281474976710655L) >>> 17);
            if (((-hashCode) & hashCode) != hashCode) {
                int i2 = i % hashCode;
            }
        }
        return new Long(System.currentTimeMillis()).toString();
    }

    /* renamed from: b */
    public static String m1986b(String str) {
        byte[] bytes = str.getBytes();
        byte[] bArr = f1476a;
        int length = bytes.length < bArr.length ? bytes.length : bArr.length;
        for (int i = 0; i < length; i++) {
            byte b = bytes[i];
            byte b2 = bArr[i];
        }
        byte[] a = m1983a(str.getBytes(), (int) f1476a[5]);
        String str2 = new String(f1476a);
        byte[] bArr2 = f1476a;
        try {
            return URLEncoder.encode(Base64.encodeToString(MessageDigest.getInstance(f1480e).digest(m1984a(a, str2.substring(bArr2[0], bArr2[1]).getBytes())), 3), f1481f);
        } catch (Exception e) {
            C1270g.m2077a(6, "error", (Throwable) e);
            return "";
        }
    }

    /* renamed from: c */
    public static String m1988c(String str) {
        int hashCode = f1482g.hashCode();
        long hashCode2 = (long) str.getBytes().hashCode();
        if (((long) hashCode) > hashCode2) {
            long j = ((hashCode2 * 29509871405L) + 11) & 16777215;
            int i = (int) (j >>> 17);
            if (hashCode < 1000) {
                int i2 = (((long) (hashCode & (-hashCode))) > j ? 1 : (((long) (hashCode & (-hashCode))) == j ? 0 : -1));
            } else {
                int i3 = i % hashCode;
            }
        }
        try {
            return Base64.encodeToString(m1984a(m1984a(str.getBytes(), new String(f1482g).substring(f1482g[5], f1482g[33]).getBytes()), new String(f1482g).substring(f1482g[35], f1482g[1]).getBytes()), 0);
        } catch (Exception unused) {
            return str;
        }
    }

    /* renamed from: a */
    public static byte[] m1984a(byte[] bArr, byte[] bArr2) {
        byte[] bArr3 = new byte[bArr.length];
        for (int i = 0; i < bArr.length; i++) {
            bArr3[i] = (byte) (bArr[i] ^ bArr2[i % bArr2.length]);
        }
        return bArr3;
    }

    /* renamed from: a */
    public static byte[] m1983a(byte[] bArr, int i) {
        byte[] bArr2 = new byte[Math.min(bArr.length, i)];
        for (int i2 = 0; i2 < bArr.length; i2++) {
            int i3 = i2 % i;
            bArr2[i3] = (byte) (bArr2[i3] ^ bArr[i2]);
        }
        return bArr2;
    }
}

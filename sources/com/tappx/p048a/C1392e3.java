package com.tappx.p048a;

import java.security.MessageDigest;

/* renamed from: com.tappx.a.e3 */
public abstract class C1392e3 {
    /* renamed from: a */
    public static String m2563a(String str) {
        try {
            return m2564a(MessageDigest.getInstance("MD5").digest(str.getBytes()));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    private static String m2564a(byte[] bArr) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bArr) {
            String hexString = Integer.toHexString(b & 255);
            if (hexString.length() == 1) {
                sb.append('0');
            }
            sb.append(hexString);
        }
        return sb.toString();
    }
}

package com.appnext.base.p082b;

import android.text.TextUtils;
import android.util.Base64;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.zip.CRC32;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* renamed from: com.appnext.base.b.h */
public final class C4904h {

    /* renamed from: fw */
    private static final C4904h f4635fw = new C4904h();

    private static void init() {
    }

    private C4904h() {
    }

    /* renamed from: aO */
    public static C4904h m6584aO() {
        return f4635fw;
    }

    /* renamed from: I */
    public static Long m6580I(String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            CRC32 crc32 = new CRC32();
            crc32.update(str.getBytes());
            return Long.valueOf(crc32.getValue());
        } catch (Throwable unused) {
            return null;
        }
    }

    /* renamed from: J */
    public static String m6581J(String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            CRC32 crc32 = new CRC32();
            crc32.update(str.getBytes());
            return Long.toHexString(crc32.getValue());
        } catch (Throwable unused) {
            return null;
        }
    }

    /* renamed from: f */
    public final byte[] mo41010f(String str, String str2) {
        try {
            if (!TextUtils.isEmpty(str)) {
                if (!TextUtils.isEmpty(str2)) {
                    byte[] bArr = new byte[16];
                    new SecureRandom().nextBytes(bArr);
                    Cipher instance = Cipher.getInstance("AES/CBC/PKCS5Padding");
                    instance.init(1, new SecretKeySpec(Base64.decode(str2, 2), "AES"), new IvParameterSpec(bArr));
                    return m6583a(bArr, instance.doFinal(str.getBytes("UTF-8")));
                }
            }
        } catch (Throwable unused) {
        }
        return null;
    }

    /* renamed from: K */
    public final String mo41007K(String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            byte[] bArr = new byte[16];
            new SecureRandom().nextBytes(bArr);
            Cipher instance = Cipher.getInstance("AES/CBC/PKCS5Padding");
            byte[] aP = m6585aP();
            if (aP == null) {
                return null;
            }
            instance.init(1, new SecretKeySpec(aP, "AES"), new IvParameterSpec(bArr));
            return Base64.encodeToString(m6583a(bArr, instance.doFinal(str.getBytes("utf-8"))), 2);
        } catch (Throwable unused) {
            return null;
        }
    }

    /* renamed from: aP */
    private static byte[] m6585aP() {
        try {
            return Arrays.copyOf((C4902f.getKey()).getBytes("utf-8"), 16);
        } catch (Throwable unused) {
            return null;
        }
    }

    /* renamed from: L */
    public final String mo41008L(String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            byte[] decode = Base64.decode(str, 2);
            byte[] copyOfRange = Arrays.copyOfRange(decode, 0, 16);
            byte[] copyOfRange2 = Arrays.copyOfRange(decode, 16, decode.length);
            Cipher instance = Cipher.getInstance("AES/CBC/PKCS5Padding");
            byte[] aP = m6585aP();
            if (aP == null) {
                return null;
            }
            instance.init(2, new SecretKeySpec(aP, "AES"), new IvParameterSpec(copyOfRange));
            return new String(instance.doFinal(copyOfRange2), "utf-8");
        } catch (Throwable unused) {
            return null;
        }
    }

    /* renamed from: aQ */
    private static String m6586aQ() {
        try {
            KeyGenerator instance = KeyGenerator.getInstance("AES");
            instance.init(128);
            return Base64.encodeToString(instance.generateKey().getEncoded(), 0);
        } catch (Throwable unused) {
            return null;
        }
    }

    /* renamed from: a */
    private static byte[] m6583a(byte[] bArr, byte[] bArr2) {
        int length = bArr2.length + 16;
        byte[] bArr3 = new byte[length];
        int i = 0;
        while (i < length) {
            bArr3[i] = i < 16 ? bArr[i] : bArr2[i - 16];
            i++;
        }
        return bArr3;
    }

    /* renamed from: M */
    public final String mo41009M(String str) {
        try {
            MessageDigest instance = MessageDigest.getInstance("SHA-256");
            instance.update(str.getBytes());
            byte[] digest = instance.digest();
            StringBuffer stringBuffer = new StringBuffer();
            for (byte b : digest) {
                String hexString = Integer.toHexString(b & 255);
                if (hexString.length() == 1) {
                    stringBuffer.append('0');
                }
                stringBuffer.append(hexString);
            }
            return stringBuffer.toString();
        } catch (Throwable unused) {
            return null;
        }
    }

    /* renamed from: a */
    private static String m6582a(byte[] bArr) {
        StringBuffer stringBuffer = new StringBuffer();
        for (byte b : bArr) {
            String hexString = Integer.toHexString(b & 255);
            if (hexString.length() == 1) {
                stringBuffer.append('0');
            }
            stringBuffer.append(hexString);
        }
        return stringBuffer.toString();
    }
}

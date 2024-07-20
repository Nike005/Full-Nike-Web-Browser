package com.tappx.p048a;

import android.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/* renamed from: com.tappx.a.f */
public class C1400f {

    /* renamed from: c */
    private static volatile C1400f f1803c;

    /* renamed from: d */
    private static final byte[] f1804d = {81, 80, 55, 68, 109, 116, 116, 52, 54, 67, 104, 99, 71, 108, 52, 81, 67, 102, 100, 86};

    /* renamed from: e */
    private static final byte[] f1805e = {-30, 31, 11, 37, 23, 88};

    /* renamed from: a */
    private Cipher f1806a;

    /* renamed from: b */
    private Cipher f1807b;

    public C1400f() {
        this(f1804d, f1805e);
    }

    /* renamed from: a */
    public static C1400f m2601a() {
        C1400f fVar = f1803c;
        if (fVar == null) {
            synchronized (C1400f.class) {
                fVar = f1803c;
                if (fVar == null) {
                    fVar = new C1400f();
                    f1803c = fVar;
                }
            }
        }
        return fVar;
    }

    /* renamed from: b */
    public static String m2603b(String str) {
        return m2601a().mo15760a(str);
    }

    public C1400f(String str) {
        this(str.getBytes(), f1805e);
    }

    public C1400f(byte[] bArr, byte[] bArr2) {
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(m2602a(bArr, bArr2), "AES");
            Cipher instance = Cipher.getInstance("AES");
            this.f1806a = instance;
            instance.init(1, secretKeySpec);
            Cipher instance2 = Cipher.getInstance("AES");
            this.f1807b = instance2;
            instance2.init(2, secretKeySpec);
        } catch (Exception unused) {
        }
    }

    /* renamed from: a */
    private static byte[] m2602a(byte[] bArr, byte[] bArr2) {
        if (bArr == null) {
            return bArr2;
        }
        if (bArr2 == null) {
            return bArr;
        }
        byte[] bArr3 = new byte[24];
        System.arraycopy(bArr, 0, bArr3, 0, bArr.length);
        System.arraycopy(bArr2, 0, bArr3, bArr.length, 24 - bArr.length);
        return bArr3;
    }

    /* renamed from: a */
    public String mo15760a(String str) {
        if (str == null) {
            return null;
        }
        try {
            return new String(this.f1807b.doFinal(Base64.decode(str, 0)), "UTF-8");
        } catch (Exception unused) {
            return "";
        }
    }
}

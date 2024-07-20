package com.yandex.metrica.impl.utils;

import android.util.Base64;
import com.yandex.metrica.impl.C1897bk;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.Cipher;

/* renamed from: com.yandex.metrica.impl.utils.b */
public class C2217b {

    /* renamed from: a */
    private final String f3891a;

    /* renamed from: b */
    private final String f3892b;

    public C2217b() {
        this("AES/CBC/PKCS7Padding", "RSA/ECB/PKCS1Padding");
    }

    C2217b(String str, String str2) {
        this.f3891a = str;
        this.f3892b = str2;
    }

    /* renamed from: a */
    public byte[] mo17897a(byte[] bArr) {
        try {
            SecureRandom secureRandom = new SecureRandom();
            byte[] bArr2 = new byte[16];
            byte[] bArr3 = new byte[16];
            secureRandom.nextBytes(bArr3);
            secureRandom.nextBytes(bArr2);
            return mo17898a(bArr, bArr3, bArr2, KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(Base64.decode("MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDhmH/m2qrRjxDHP794CeaZpENQNYydf8pqyXJilo6XxK+n+pvo27VxWfB3Z1yHrtKow+eZXKLQzrQ8wZMfRgADrYCQJ20y2hGZEUCN1tGSM+xqVKMeCtVi3NvQa54Cx7mT5ECVsH5DKEs/aeScDHP56FzcgEbtOSwyRZ8dsEM0wwIDAQAB", 0))));
        } catch (NoSuchAlgorithmException | InvalidKeySpecException unused) {
            return null;
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public byte[] mo17898a(byte[] bArr, byte[] bArr2, byte[] bArr3, PublicKey publicKey) {
        ByteArrayOutputStream byteArrayOutputStream;
        ByteArrayOutputStream byteArrayOutputStream2 = null;
        try {
            ByteArrayOutputStream byteArrayOutputStream3 = new ByteArrayOutputStream(bArr2.length + bArr3.length);
            byteArrayOutputStream3.write(bArr2);
            byteArrayOutputStream3.write(bArr3);
            byte[] byteArray = byteArrayOutputStream3.toByteArray();
            byteArrayOutputStream3.close();
            Cipher instance = Cipher.getInstance(this.f3892b);
            instance.init(1, publicKey);
            byteArrayOutputStream = new ByteArrayOutputStream(bArr.length);
            try {
                byteArrayOutputStream.write(instance.doFinal(byteArray));
                byteArrayOutputStream.write(new C2216a(this.f3891a, bArr2, bArr3).mo17896a(bArr));
                byte[] byteArray2 = byteArrayOutputStream.toByteArray();
                C1897bk.m4645a((Closeable) byteArrayOutputStream);
                return byteArray2;
            } catch (Exception unused) {
                C1897bk.m4645a((Closeable) byteArrayOutputStream);
                return null;
            } catch (Throwable th) {
                th = th;
                byteArrayOutputStream2 = byteArrayOutputStream;
                C1897bk.m4645a((Closeable) byteArrayOutputStream2);
                throw th;
            }
        } catch (Exception unused2) {
            byteArrayOutputStream = null;
            C1897bk.m4645a((Closeable) byteArrayOutputStream);
            return null;
        } catch (Throwable th2) {
            th = th2;
            C1897bk.m4645a((Closeable) byteArrayOutputStream2);
            throw th;
        }
    }
}

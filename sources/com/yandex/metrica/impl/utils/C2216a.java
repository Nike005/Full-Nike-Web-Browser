package com.yandex.metrica.impl.utils;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* renamed from: com.yandex.metrica.impl.utils.a */
public class C2216a {

    /* renamed from: a */
    private final String f3888a;

    /* renamed from: b */
    private final byte[] f3889b;

    /* renamed from: c */
    private final byte[] f3890c;

    public C2216a(String str, byte[] bArr, byte[] bArr2) {
        this.f3888a = str;
        this.f3889b = bArr;
        this.f3890c = bArr2;
    }

    /* renamed from: a */
    public byte[] mo17896a(byte[] bArr) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        SecretKeySpec secretKeySpec = new SecretKeySpec(this.f3889b, "AES");
        Cipher instance = Cipher.getInstance(this.f3888a);
        instance.init(1, secretKeySpec, new IvParameterSpec(this.f3890c));
        return instance.doFinal(bArr);
    }
}

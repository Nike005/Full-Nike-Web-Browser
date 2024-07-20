package com.startapp.p005a.p006a.p011e;

/* renamed from: com.startapp.a.a.e.c */
/* compiled from: StartAppSDK */
class C0818c {

    /* renamed from: a */
    private static final char[] f354a = "0123456789abcdef".toCharArray();

    C0818c() {
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public byte[] mo13704a(String str) {
        if (m389b(str)) {
            return null;
        }
        byte[] bArr = new byte[(str.length() / 2)];
        int length = str.length();
        for (int i = 0; i < length; i += 2) {
            bArr[i / 2] = (byte) ((Character.digit(str.charAt(i), 16) << 4) + Character.digit(str.charAt(i + 1), 16));
        }
        return bArr;
    }

    /* renamed from: b */
    private boolean m389b(String str) {
        return str.length() % 2 != 0;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public String mo13703a(byte[] bArr) {
        char[] cArr = new char[(bArr.length * 2)];
        for (int i = 0; i < bArr.length; i++) {
            int i2 = i * 2;
            char[] cArr2 = f354a;
            cArr[i2] = cArr2[(bArr[i] & 240) >>> 4];
            cArr[i2 + 1] = cArr2[bArr[i] & 15];
        }
        return new String(cArr);
    }
}

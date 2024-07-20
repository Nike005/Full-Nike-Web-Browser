package com.startapp.p005a.p006a.p009c;

import org.altbeacon.bluetooth.Pdu;

/* renamed from: com.startapp.a.a.c.a */
/* compiled from: StartAppSDK */
public class C0804a extends C0805b {

    /* renamed from: a */
    static final byte[] f313a = {13, 10};

    /* renamed from: d */
    private static final byte[] f314d = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 43, 47};

    /* renamed from: e */
    private static final byte[] f315e = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 45, 95};

    /* renamed from: f */
    private static final byte[] f316f = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, 62, -1, 63, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -1, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, Pdu.GATT_SERVICE_UUID_PDU_TYPE, 23, 24, 25, -1, -1, -1, -1, 63, -1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51};

    /* renamed from: g */
    private final byte[] f317g;

    /* renamed from: h */
    private final byte[] f318h;

    /* renamed from: i */
    private final byte[] f319i;

    /* renamed from: j */
    private final int f320j;

    /* renamed from: k */
    private final int f321k;

    public C0804a() {
        this(0);
    }

    public C0804a(boolean z) {
        this(76, f313a, z);
    }

    public C0804a(int i) {
        this(i, f313a);
    }

    public C0804a(int i, byte[] bArr) {
        this(i, bArr, false);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C0804a(int i, byte[] bArr, boolean z) {
        super(3, 4, i, bArr == null ? 0 : bArr.length);
        this.f318h = f316f;
        if (bArr == null) {
            this.f321k = 4;
            this.f319i = null;
        } else if (mo13688c(bArr)) {
            String a = C0810f.m380a(bArr);
            throw new IllegalArgumentException("lineSeparator must not contain base64 characters: [" + a + "]");
        } else if (i > 0) {
            this.f321k = bArr.length + 4;
            byte[] bArr2 = new byte[bArr.length];
            this.f319i = bArr2;
            System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
        } else {
            this.f321k = 4;
            this.f319i = null;
        }
        this.f320j = this.f321k - 1;
        this.f317g = z ? f315e : f314d;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v3, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v26, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v27, resolved type: byte} */
    /* access modifiers changed from: package-private */
    /* JADX WARNING: Multi-variable type inference failed */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void mo13681a(byte[] r8, int r9, int r10, com.startapp.p005a.p006a.p009c.C0805b.C0806a r11) {
        /*
            r7 = this;
            boolean r0 = r11.f332f
            if (r0 == 0) goto L_0x0005
            return
        L_0x0005:
            r0 = 0
            r1 = 1
            if (r10 >= 0) goto L_0x00e3
            r11.f332f = r1
            int r8 = r11.f334h
            if (r8 != 0) goto L_0x0014
            int r8 = r7.f324c
            if (r8 != 0) goto L_0x0014
            return
        L_0x0014:
            int r8 = r7.f321k
            byte[] r8 = r7.mo13685a(r8, r11)
            int r9 = r11.f330d
            int r10 = r11.f334h
            if (r10 == 0) goto L_0x00bf
            r2 = 61
            r3 = 2
            if (r10 == r1) goto L_0x0085
            if (r10 != r3) goto L_0x006c
            int r10 = r11.f330d
            int r1 = r10 + 1
            r11.f330d = r1
            byte[] r1 = r7.f317g
            int r4 = r11.f327a
            int r4 = r4 >> 10
            r4 = r4 & 63
            byte r1 = r1[r4]
            r8[r10] = r1
            int r10 = r11.f330d
            int r1 = r10 + 1
            r11.f330d = r1
            byte[] r1 = r7.f317g
            int r4 = r11.f327a
            int r4 = r4 >> 4
            r4 = r4 & 63
            byte r1 = r1[r4]
            r8[r10] = r1
            int r10 = r11.f330d
            int r1 = r10 + 1
            r11.f330d = r1
            byte[] r1 = r7.f317g
            int r4 = r11.f327a
            int r3 = r4 << 2
            r3 = r3 & 63
            byte r1 = r1[r3]
            r8[r10] = r1
            byte[] r10 = r7.f317g
            byte[] r1 = f314d
            if (r10 != r1) goto L_0x00bf
            int r10 = r11.f330d
            int r1 = r10 + 1
            r11.f330d = r1
            r8[r10] = r2
            goto L_0x00bf
        L_0x006c:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            java.lang.String r10 = "Impossible modulus "
            r9.append(r10)
            int r10 = r11.f334h
            r9.append(r10)
            java.lang.String r9 = r9.toString()
            r8.<init>(r9)
            throw r8
        L_0x0085:
            int r10 = r11.f330d
            int r1 = r10 + 1
            r11.f330d = r1
            byte[] r1 = r7.f317g
            int r4 = r11.f327a
            int r3 = r4 >> 2
            r3 = r3 & 63
            byte r1 = r1[r3]
            r8[r10] = r1
            int r10 = r11.f330d
            int r1 = r10 + 1
            r11.f330d = r1
            byte[] r1 = r7.f317g
            int r3 = r11.f327a
            int r3 = r3 << 4
            r3 = r3 & 63
            byte r1 = r1[r3]
            r8[r10] = r1
            byte[] r10 = r7.f317g
            byte[] r1 = f314d
            if (r10 != r1) goto L_0x00bf
            int r10 = r11.f330d
            int r1 = r10 + 1
            r11.f330d = r1
            r8[r10] = r2
            int r10 = r11.f330d
            int r1 = r10 + 1
            r11.f330d = r1
            r8[r10] = r2
        L_0x00bf:
            int r10 = r11.f333g
            int r1 = r11.f330d
            int r1 = r1 - r9
            int r10 = r10 + r1
            r11.f333g = r10
            int r9 = r7.f324c
            if (r9 <= 0) goto L_0x0175
            int r9 = r11.f333g
            if (r9 <= 0) goto L_0x0175
            byte[] r9 = r7.f319i
            int r10 = r11.f330d
            byte[] r1 = r7.f319i
            int r1 = r1.length
            java.lang.System.arraycopy(r9, r0, r8, r10, r1)
            int r8 = r11.f330d
            byte[] r9 = r7.f319i
            int r9 = r9.length
            int r8 = r8 + r9
            r11.f330d = r8
            goto L_0x0175
        L_0x00e3:
            r2 = 0
        L_0x00e4:
            if (r2 >= r10) goto L_0x0175
            int r3 = r7.f321k
            byte[] r3 = r7.mo13685a(r3, r11)
            int r4 = r11.f334h
            int r4 = r4 + r1
            int r4 = r4 % 3
            r11.f334h = r4
            int r4 = r9 + 1
            byte r9 = r8[r9]
            if (r9 >= 0) goto L_0x00fb
            int r9 = r9 + 256
        L_0x00fb:
            int r5 = r11.f327a
            int r5 = r5 << 8
            int r5 = r5 + r9
            r11.f327a = r5
            int r9 = r11.f334h
            if (r9 != 0) goto L_0x0170
            int r9 = r11.f330d
            int r5 = r9 + 1
            r11.f330d = r5
            byte[] r5 = r7.f317g
            int r6 = r11.f327a
            int r6 = r6 >> 18
            r6 = r6 & 63
            byte r5 = r5[r6]
            r3[r9] = r5
            int r9 = r11.f330d
            int r5 = r9 + 1
            r11.f330d = r5
            byte[] r5 = r7.f317g
            int r6 = r11.f327a
            int r6 = r6 >> 12
            r6 = r6 & 63
            byte r5 = r5[r6]
            r3[r9] = r5
            int r9 = r11.f330d
            int r5 = r9 + 1
            r11.f330d = r5
            byte[] r5 = r7.f317g
            int r6 = r11.f327a
            int r6 = r6 >> 6
            r6 = r6 & 63
            byte r5 = r5[r6]
            r3[r9] = r5
            int r9 = r11.f330d
            int r5 = r9 + 1
            r11.f330d = r5
            byte[] r5 = r7.f317g
            int r6 = r11.f327a
            r6 = r6 & 63
            byte r5 = r5[r6]
            r3[r9] = r5
            int r9 = r11.f333g
            int r9 = r9 + 4
            r11.f333g = r9
            int r9 = r7.f324c
            if (r9 <= 0) goto L_0x0170
            int r9 = r7.f324c
            int r5 = r11.f333g
            if (r9 > r5) goto L_0x0170
            byte[] r9 = r7.f319i
            int r5 = r11.f330d
            byte[] r6 = r7.f319i
            int r6 = r6.length
            java.lang.System.arraycopy(r9, r0, r3, r5, r6)
            int r9 = r11.f330d
            byte[] r3 = r7.f319i
            int r3 = r3.length
            int r9 = r9 + r3
            r11.f330d = r9
            r11.f333g = r0
        L_0x0170:
            int r2 = r2 + 1
            r9 = r4
            goto L_0x00e4
        L_0x0175:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.startapp.p005a.p006a.p009c.C0804a.mo13681a(byte[], int, int, com.startapp.a.a.c.b$a):void");
    }

    /* renamed from: a */
    public static String m362a(byte[] bArr) {
        return C0810f.m380a(m363a(bArr, false));
    }

    /* renamed from: a */
    public static byte[] m363a(byte[] bArr, boolean z) {
        return m364a(bArr, z, false);
    }

    /* renamed from: a */
    public static byte[] m364a(byte[] bArr, boolean z, boolean z2) {
        return m365a(bArr, z, z2, Integer.MAX_VALUE);
    }

    /* renamed from: a */
    public static byte[] m365a(byte[] bArr, boolean z, boolean z2, int i) {
        if (bArr == null || bArr.length == 0) {
            return bArr;
        }
        C0804a aVar = z ? new C0804a(z2) : new C0804a(0, f313a, z2);
        long d = aVar.mo13689d(bArr);
        if (d <= ((long) i)) {
            return aVar.mo13687b(bArr);
        }
        throw new IllegalArgumentException("Input array too big, the output array would be bigger (" + d + ") than the specified maximum size of " + i);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public boolean mo13682a(byte b) {
        if (b >= 0) {
            byte[] bArr = this.f318h;
            return b < bArr.length && bArr[b] != -1;
        }
    }
}

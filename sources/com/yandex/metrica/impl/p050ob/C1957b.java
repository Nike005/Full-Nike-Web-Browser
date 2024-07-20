package com.yandex.metrica.impl.p050ob;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

/* renamed from: com.yandex.metrica.impl.ob.b */
public final class C1957b {

    /* renamed from: a */
    private final byte[] f3220a;

    /* renamed from: b */
    private final int f3221b;

    /* renamed from: c */
    private int f3222c;

    /* renamed from: d */
    public static int m4851d(long j) {
        if ((-128 & j) == 0) {
            return 1;
        }
        if ((-16384 & j) == 0) {
            return 2;
        }
        if ((-2097152 & j) == 0) {
            return 3;
        }
        if ((-268435456 & j) == 0) {
            return 4;
        }
        if ((-34359738368L & j) == 0) {
            return 5;
        }
        if ((-4398046511104L & j) == 0) {
            return 6;
        }
        if ((-562949953421312L & j) == 0) {
            return 7;
        }
        if ((-72057594037927936L & j) == 0) {
            return 8;
        }
        return (j & Long.MIN_VALUE) == 0 ? 9 : 10;
    }

    /* renamed from: i */
    public static int m4856i(int i) {
        if ((i & -128) == 0) {
            return 1;
        }
        if ((i & -16384) == 0) {
            return 2;
        }
        if ((-2097152 & i) == 0) {
            return 3;
        }
        return (i & -268435456) == 0 ? 4 : 5;
    }

    /* renamed from: j */
    public static int m4857j(int i) {
        return (i >> 31) ^ (i << 1);
    }

    private C1957b(byte[] bArr, int i, int i2) {
        this.f3220a = bArr;
        this.f3222c = i;
        this.f3221b = i + i2;
    }

    /* renamed from: a */
    public static C1957b m4841a(byte[] bArr, int i, int i2) {
        return new C1957b(bArr, i, i2);
    }

    /* renamed from: a */
    public void mo17187a(int i, double d) throws IOException {
        mo17211g(i, 1);
        mo17185a(d);
    }

    /* renamed from: a */
    public void mo17189a(int i, long j) throws IOException {
        mo17211g(i, 0);
        mo17194a(j);
    }

    /* renamed from: b */
    public void mo17202b(int i, long j) throws IOException {
        mo17211g(i, 0);
        mo17203b(j);
    }

    /* renamed from: a */
    public void mo17188a(int i, int i2) throws IOException {
        mo17211g(i, 0);
        mo17186a(i2);
    }

    /* renamed from: a */
    public void mo17192a(int i, boolean z) throws IOException {
        mo17211g(i, 0);
        mo17197a(z);
    }

    /* renamed from: a */
    public void mo17191a(int i, String str) throws IOException {
        mo17211g(i, 2);
        mo17196a(str);
    }

    /* renamed from: a */
    public void mo17190a(int i, C2056d dVar) throws IOException {
        mo17211g(i, 2);
        mo17195a(dVar);
    }

    /* renamed from: a */
    public void mo17193a(int i, byte[] bArr) throws IOException {
        mo17211g(i, 2);
        mo17198a(bArr);
    }

    /* renamed from: b */
    public void mo17201b(int i, int i2) throws IOException {
        mo17211g(i, 0);
        mo17200b(i2);
    }

    /* renamed from: c */
    public void mo17207c(int i, int i2) throws IOException {
        mo17211g(i, 0);
        mo17206c(i2);
    }

    /* renamed from: a */
    public void mo17185a(double d) throws IOException {
        mo17209e(Double.doubleToLongBits(d));
    }

    /* renamed from: a */
    public void mo17194a(long j) throws IOException {
        mo17208c(j);
    }

    /* renamed from: b */
    public void mo17203b(long j) throws IOException {
        mo17208c(j);
    }

    /* renamed from: a */
    public void mo17186a(int i) throws IOException {
        if (i >= 0) {
            mo17212h(i);
        } else {
            mo17208c((long) i);
        }
    }

    /* renamed from: a */
    public void mo17197a(boolean z) throws IOException {
        mo17210f(z ? 1 : 0);
    }

    /* renamed from: a */
    public void mo17196a(String str) throws IOException {
        byte[] bytes = str.getBytes("UTF-8");
        mo17212h(bytes.length);
        mo17204b(bytes);
    }

    /* renamed from: a */
    public void mo17195a(C2056d dVar) throws IOException {
        mo17212h(dVar.mo17518a());
        dVar.mo16675a(this);
    }

    /* renamed from: a */
    public void mo17198a(byte[] bArr) throws IOException {
        mo17212h(bArr.length);
        mo17204b(bArr);
    }

    /* renamed from: b */
    public void mo17200b(int i) throws IOException {
        mo17212h(i);
    }

    /* renamed from: c */
    public void mo17206c(int i) throws IOException {
        mo17212h(m4857j(i));
    }

    /* renamed from: d */
    public static int m4848d(int i) {
        return m4855g(i) + 8;
    }

    /* renamed from: c */
    public static int m4847c(int i, long j) {
        return m4855g(i) + m4851d(j);
    }

    /* renamed from: d */
    public static int m4850d(int i, long j) {
        return m4855g(i) + m4851d(j);
    }

    /* renamed from: d */
    public static int m4849d(int i, int i2) {
        return m4855g(i) + (i2 >= 0 ? m4856i(i2) : 10);
    }

    /* renamed from: e */
    public static int m4852e(int i) {
        return m4855g(i) + 1;
    }

    /* renamed from: b */
    public static int m4843b(int i, String str) {
        return m4855g(i) + m4846b(str);
    }

    /* renamed from: b */
    public static int m4842b(int i, C2056d dVar) {
        return m4855g(i) + m4845b(dVar);
    }

    /* renamed from: b */
    public static int m4844b(int i, byte[] bArr) {
        return m4855g(i) + m4856i(bArr.length) + bArr.length;
    }

    /* renamed from: e */
    public static int m4853e(int i, int i2) {
        return m4855g(i) + m4856i(i2);
    }

    /* renamed from: f */
    public static int m4854f(int i, int i2) {
        return m4855g(i) + m4856i(m4857j(i2));
    }

    /* renamed from: b */
    public static int m4846b(String str) {
        try {
            byte[] bytes = str.getBytes("UTF-8");
            return m4856i(bytes.length) + bytes.length;
        } catch (UnsupportedEncodingException unused) {
            throw new RuntimeException("UTF-8 not supported.");
        }
    }

    /* renamed from: b */
    public static int m4845b(C2056d dVar) {
        int b = dVar.mo17519b();
        return m4856i(b) + b;
    }

    /* renamed from: a */
    public int mo17183a() {
        return this.f3221b - this.f3222c;
    }

    /* renamed from: b */
    public void mo17199b() {
        if (mo17183a() != 0) {
            throw new IllegalStateException("Did not write as much data as expected.");
        }
    }

    /* renamed from: com.yandex.metrica.impl.ob.b$a */
    public static class C1958a extends IOException {
        private static final long serialVersionUID = -6947486886997889499L;

        C1958a(int i, int i2) {
            super("CodedOutputStream was writing to a flat byte array and ran out of space (pos " + i + " limit " + i2 + ").");
        }
    }

    /* renamed from: a */
    public void mo17184a(byte b) throws IOException {
        int i = this.f3222c;
        if (i != this.f3221b) {
            byte[] bArr = this.f3220a;
            this.f3222c = i + 1;
            bArr[i] = b;
            return;
        }
        throw new C1958a(this.f3222c, this.f3221b);
    }

    /* renamed from: f */
    public void mo17210f(int i) throws IOException {
        mo17184a((byte) i);
    }

    /* renamed from: b */
    public void mo17204b(byte[] bArr) throws IOException {
        mo17205b(bArr, 0, bArr.length);
    }

    /* renamed from: b */
    public void mo17205b(byte[] bArr, int i, int i2) throws IOException {
        int i3 = this.f3221b;
        int i4 = this.f3222c;
        if (i3 - i4 >= i2) {
            System.arraycopy(bArr, i, this.f3220a, i4, i2);
            this.f3222c += i2;
            return;
        }
        throw new C1958a(this.f3222c, this.f3221b);
    }

    /* renamed from: g */
    public void mo17211g(int i, int i2) throws IOException {
        mo17212h(C2140f.m5648a(i, i2));
    }

    /* renamed from: g */
    public static int m4855g(int i) {
        return m4856i(C2140f.m5648a(i, 0));
    }

    /* renamed from: h */
    public void mo17212h(int i) throws IOException {
        while ((i & -128) != 0) {
            mo17210f((i & 127) | 128);
            i >>>= 7;
        }
        mo17210f(i);
    }

    /* renamed from: c */
    public void mo17208c(long j) throws IOException {
        while ((-128 & j) != 0) {
            mo17210f((((int) j) & 127) | 128);
            j >>>= 7;
        }
        mo17210f((int) j);
    }

    /* renamed from: e */
    public void mo17209e(long j) throws IOException {
        mo17210f(((int) j) & 255);
        mo17210f(((int) (j >> 8)) & 255);
        mo17210f(((int) (j >> 16)) & 255);
        mo17210f(((int) (j >> 24)) & 255);
        mo17210f(((int) (j >> 32)) & 255);
        mo17210f(((int) (j >> 40)) & 255);
        mo17210f(((int) (j >> 48)) & 255);
        mo17210f(((int) (j >> 56)) & 255);
    }
}

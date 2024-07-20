package com.startapp.p005a.p006a.p009c;

import java.util.Arrays;

/* renamed from: com.startapp.a.a.c.b */
/* compiled from: StartAppSDK */
public abstract class C0805b {

    /* renamed from: a */
    private final int f322a;

    /* renamed from: b */
    protected final byte f323b = 61;

    /* renamed from: c */
    protected final int f324c;

    /* renamed from: d */
    private final int f325d;

    /* renamed from: e */
    private final int f326e;

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public int mo13683a() {
        return 8192;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public abstract void mo13681a(byte[] bArr, int i, int i2, C0806a aVar);

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public abstract boolean mo13682a(byte b);

    /* renamed from: com.startapp.a.a.c.b$a */
    /* compiled from: StartAppSDK */
    static class C0806a {

        /* renamed from: a */
        int f327a;

        /* renamed from: b */
        long f328b;

        /* renamed from: c */
        byte[] f329c;

        /* renamed from: d */
        int f330d;

        /* renamed from: e */
        int f331e;

        /* renamed from: f */
        boolean f332f;

        /* renamed from: g */
        int f333g;

        /* renamed from: h */
        int f334h;

        C0806a() {
        }

        public String toString() {
            return String.format("%s[buffer=%s, currentLinePos=%s, eof=%s, ibitWorkArea=%s, lbitWorkArea=%s, modulus=%s, pos=%s, readPos=%s]", new Object[]{getClass().getSimpleName(), Arrays.toString(this.f329c), Integer.valueOf(this.f333g), Boolean.valueOf(this.f332f), Integer.valueOf(this.f327a), Long.valueOf(this.f328b), Integer.valueOf(this.f334h), Integer.valueOf(this.f330d), Integer.valueOf(this.f331e)});
        }
    }

    protected C0805b(int i, int i2, int i3, int i4) {
        this.f322a = i;
        this.f325d = i2;
        this.f324c = i3 > 0 && i4 > 0 ? (i3 / i2) * i2 : 0;
        this.f326e = i4;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public int mo13684a(C0806a aVar) {
        if (aVar.f329c != null) {
            return aVar.f330d - aVar.f331e;
        }
        return 0;
    }

    /* renamed from: b */
    private byte[] m368b(C0806a aVar) {
        if (aVar.f329c == null) {
            aVar.f329c = new byte[mo13683a()];
            aVar.f330d = 0;
            aVar.f331e = 0;
        } else {
            byte[] bArr = new byte[(aVar.f329c.length * 2)];
            System.arraycopy(aVar.f329c, 0, bArr, 0, aVar.f329c.length);
            aVar.f329c = bArr;
        }
        return aVar.f329c;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public byte[] mo13685a(int i, C0806a aVar) {
        if (aVar.f329c == null || aVar.f329c.length < aVar.f330d + i) {
            return m368b(aVar);
        }
        return aVar.f329c;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public int mo13686b(byte[] bArr, int i, int i2, C0806a aVar) {
        if (aVar.f329c == null) {
            return aVar.f332f ? -1 : 0;
        }
        int min = Math.min(mo13684a(aVar), i2);
        System.arraycopy(aVar.f329c, aVar.f331e, bArr, i, min);
        aVar.f331e += min;
        if (aVar.f331e >= aVar.f330d) {
            aVar.f329c = null;
        }
        return min;
    }

    /* renamed from: b */
    public byte[] mo13687b(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return bArr;
        }
        C0806a aVar = new C0806a();
        mo13681a(bArr, 0, bArr.length, aVar);
        mo13681a(bArr, 0, -1, aVar);
        int i = aVar.f330d - aVar.f331e;
        byte[] bArr2 = new byte[i];
        mo13686b(bArr2, 0, i, aVar);
        return bArr2;
    }

    /* access modifiers changed from: protected */
    /* renamed from: c */
    public boolean mo13688c(byte[] bArr) {
        if (bArr == null) {
            return false;
        }
        for (byte b : bArr) {
            if (61 == b || mo13682a(b)) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: d */
    public long mo13689d(byte[] bArr) {
        int length = bArr.length;
        int i = this.f322a;
        long j = ((long) (((length + i) - 1) / i)) * ((long) this.f325d);
        int i2 = this.f324c;
        return i2 > 0 ? j + ((((((long) i2) + j) - 1) / ((long) i2)) * ((long) this.f326e)) : j;
    }
}

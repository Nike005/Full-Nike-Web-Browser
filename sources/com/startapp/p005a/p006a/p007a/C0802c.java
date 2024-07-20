package com.startapp.p005a.p006a.p007a;

import java.io.Serializable;

/* renamed from: com.startapp.a.a.a.c */
/* compiled from: StartAppSDK */
public class C0802c implements Serializable {

    /* renamed from: a */
    static final /* synthetic */ boolean f305a = (!C0802c.class.desiredAssertionStatus());

    /* renamed from: d */
    private final long[][] f306d;

    /* renamed from: e */
    private int f307e;

    /* renamed from: f */
    private final int f308f;

    /* renamed from: d */
    private int m354d(long j) {
        return (int) (((j - 1) >>> 6) + 1);
    }

    public C0802c(long j) {
        int d = m354d(j);
        this.f307e = d;
        int i = d % 4096;
        int i2 = d / 4096;
        int i3 = (i == 0 ? 0 : 1) + i2;
        this.f308f = i3;
        if (i3 <= 100) {
            this.f306d = new long[i3][];
            for (int i4 = 0; i4 < i2; i4++) {
                this.f306d[i4] = new long[4096];
            }
            if (i != 0) {
                long[][] jArr = this.f306d;
                jArr[jArr.length - 1] = new long[i];
                return;
            }
            return;
        }
        throw new RuntimeException("HighPageCountException pageCount = " + this.f308f);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public long mo13674a() {
        return ((long) this.f307e) << 6;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo13675a(long j) {
        int b = m351b(j);
        long[] jArr = this.f306d[b / 4096];
        int i = b % 4096;
        jArr[i] = (1 << (((int) j) & 63)) | jArr[i];
    }

    /* renamed from: b */
    private int m351b(long j) {
        int i = (int) (j >> 6);
        if (i >= this.f307e) {
            m353c(j + 1);
            this.f307e = i + 1;
        }
        return i;
    }

    /* renamed from: c */
    private void m353c(long j) {
        m352b(m354d(j));
    }

    /* renamed from: b */
    private void m352b(int i) {
        if (!f305a && i > this.f307e) {
            throw new AssertionError("Growing of paged bitset is not supported");
        }
    }

    /* renamed from: b */
    public int mo13677b() {
        return this.f307e;
    }

    /* renamed from: c */
    public int mo13678c() {
        return this.f308f;
    }

    /* renamed from: a */
    public long[] mo13676a(int i) {
        return this.f306d[i];
    }
}

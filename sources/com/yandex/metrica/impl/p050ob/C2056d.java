package com.yandex.metrica.impl.p050ob;

import java.io.IOException;

/* renamed from: com.yandex.metrica.impl.ob.d */
public abstract class C2056d {

    /* renamed from: a */
    protected volatile int f3445a = -1;

    /* renamed from: a */
    public void mo16675a(C1957b bVar) throws IOException {
    }

    /* access modifiers changed from: protected */
    /* renamed from: c */
    public int mo16676c() {
        return 0;
    }

    /* renamed from: a */
    public int mo17518a() {
        if (this.f3445a < 0) {
            mo17519b();
        }
        return this.f3445a;
    }

    /* renamed from: b */
    public int mo17519b() {
        int c = mo16676c();
        this.f3445a = c;
        return c;
    }

    /* renamed from: a */
    public static final byte[] m5290a(C2056d dVar) {
        int b = dVar.mo17519b();
        byte[] bArr = new byte[b];
        try {
            C1957b a = C1957b.m4841a(bArr, 0, b);
            dVar.mo16675a(a);
            a.mo17199b();
            return bArr;
        } catch (IOException e) {
            throw new RuntimeException("Serializing to a byte array threw an IOException (should never happen).", e);
        }
    }

    public String toString() {
        return C2106e.m5521a(this);
    }
}

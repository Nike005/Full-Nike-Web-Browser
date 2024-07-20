package com.yandex.metrica.impl.p050ob;

import java.io.UnsupportedEncodingException;

/* renamed from: com.yandex.metrica.impl.ob.fp */
public abstract class C2160fp<T> extends C2169fu<T> {

    /* renamed from: a */
    private final String f3720a;

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public abstract T mo17770b(C2168ft ftVar) throws C2162fr;

    static {
        String.format("application/json; charset=%s", new Object[]{"utf-8"});
    }

    public C2160fp(int i, String str, String str2) {
        super(i, str);
        this.f3720a = str2;
    }

    /* renamed from: c */
    public byte[] mo17771c() {
        try {
            if (this.f3720a == null) {
                return null;
            }
            return this.f3720a.getBytes("utf-8");
        } catch (UnsupportedEncodingException unused) {
            return null;
        }
    }
}

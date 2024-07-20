package com.appnext.base.services;

import com.appnext.base.operations.C4912b;

/* renamed from: com.appnext.base.services.a */
public final class C4917a {

    /* renamed from: eA */
    private static volatile C4917a f4669eA;

    /* renamed from: dP */
    private String f4670dP;

    private C4917a() {
    }

    /* renamed from: aK */
    public static C4917a m6668aK() {
        if (f4669eA == null) {
            synchronized (C4912b.class) {
                if (f4669eA == null) {
                    f4669eA = new C4917a();
                }
            }
        }
        return f4669eA;
    }

    public final synchronized void setKey(String str) {
        this.f4670dP = str;
    }

    public final synchronized String getKey() {
        return this.f4670dP;
    }
}

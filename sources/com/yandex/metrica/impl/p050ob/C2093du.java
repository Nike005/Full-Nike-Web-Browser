package com.yandex.metrica.impl.p050ob;

import android.os.Bundle;

/* renamed from: com.yandex.metrica.impl.ob.du */
public enum C2093du {
    UNKNOWN(0),
    NETWORK(1),
    PARSE(2);
    

    /* renamed from: d */
    private int f3584d;

    private C2093du(int i) {
        this.f3584d = i;
    }

    /* renamed from: a */
    public int mo17649a() {
        return this.f3584d;
    }

    /* renamed from: a */
    public Bundle mo17650a(Bundle bundle) {
        bundle.putInt("startup_error_key_code", mo17649a());
        return bundle;
    }

    /* renamed from: b */
    public static C2093du m5462b(Bundle bundle) {
        int i = bundle.getInt("startup_error_key_code");
        C2093du duVar = UNKNOWN;
        if (i == 1) {
            return NETWORK;
        }
        if (i != 2) {
            return duVar;
        }
        return PARSE;
    }
}

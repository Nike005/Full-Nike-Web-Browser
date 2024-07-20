package com.yandex.metrica.impl.p050ob;

import android.text.TextUtils;

/* renamed from: com.yandex.metrica.impl.ob.cu */
public class C2050cu {

    /* renamed from: a */
    private final String f3440a;

    public C2050cu(String str) {
        this.f3440a = str;
    }

    /* renamed from: a */
    public C2045cq mo17510a(String str) {
        if (TextUtils.isEmpty(this.f3440a) || !C2041co.m5269a().mo17505c()) {
            return new C2047cr(str);
        }
        return new C2048cs(str);
    }
}

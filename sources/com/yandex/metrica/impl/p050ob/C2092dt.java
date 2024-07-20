package com.yandex.metrica.impl.p050ob;

import com.yandex.metrica.impl.utils.C2223g;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.yandex.metrica.impl.ob.dt */
public class C2092dt {

    /* renamed from: a */
    private final Map<String, Integer> f3579a = new HashMap();

    /* renamed from: a */
    public void mo17648a(String str, int i) {
        this.f3579a.put(str, Integer.valueOf(i));
    }

    /* renamed from: a */
    public String mo17647a() {
        return C2223g.m5948a((Map) this.f3579a);
    }
}

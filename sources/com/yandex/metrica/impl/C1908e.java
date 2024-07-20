package com.yandex.metrica.impl;

import com.yandex.metrica.impl.utils.C2221f;
import java.util.HashMap;

/* renamed from: com.yandex.metrica.impl.e */
public class C1908e extends C1915h {

    /* renamed from: f */
    private final HashMap<C1909a, Integer> f3154f;

    /* renamed from: g */
    private final C2221f f3155g;

    /* renamed from: com.yandex.metrica.impl.e$a */
    private enum C1909a {
        NAME,
        VALUE,
        USER_INFO
    }

    public C1908e() {
        this.f3154f = new HashMap<>();
        this.f3155g = new C2221f();
    }

    public C1908e(String str, int i) {
        this("", str, i);
    }

    public C1908e(String str, String str2, int i) {
        this(str, str2, i, 0);
    }

    public C1908e(String str, String str2, int i, int i2) {
        this.f3154f = new HashMap<>();
        this.f3155g = new C2221f();
        this.f3169b = m4713i(str);
        this.f3168a = m4712h(str2);
        this.f3170c = i;
        this.f3171d = i2;
    }

    /* renamed from: a */
    private void m4711a(String str, String str2, C1909a aVar) {
        if (this.f3155g.mo17915a(str, str2)) {
            this.f3154f.put(aVar, Integer.valueOf(C1894bi.m4629c(str).length - C1894bi.m4629c(str2).length));
        } else {
            this.f3154f.remove(aVar);
        }
        this.f3172e = 0;
        for (Integer intValue : this.f3154f.values()) {
            this.f3172e += intValue.intValue();
        }
    }

    /* renamed from: h */
    private String m4712h(String str) {
        String a = this.f3155g.mo17913a(str, 1000, "event name");
        m4711a(str, a, C1909a.NAME);
        return a;
    }

    /* renamed from: i */
    private String m4713i(String str) {
        String a = this.f3155g.mo17912a(str, 245760);
        m4711a(str, a, C1909a.VALUE);
        return a;
    }

    /* renamed from: a */
    public C1915h mo17105a(String str) {
        String a = this.f3155g.mo17913a(str, 10000, "UserInfo");
        m4711a(str, a, C1909a.USER_INFO);
        return super.mo17105a(a);
    }

    /* renamed from: b */
    public C1915h mo17106b(String str) {
        return super.mo17106b(m4712h(str));
    }

    /* renamed from: c */
    public C1915h mo17107c(String str) {
        return super.mo17107c(m4713i(str));
    }
}

package com.yandex.metrica.impl.p050ob;

/* renamed from: com.yandex.metrica.impl.ob.dk */
public class C2068dk {

    /* renamed from: a */
    private final String f3540a;

    /* renamed from: b */
    private final String f3541b;

    public C2068dk(String str) {
        this(str, (String) null);
    }

    public C2068dk(String str, String str2) {
        this.f3540a = str;
        this.f3541b = mo17605a(str2);
    }

    /* renamed from: a */
    public String mo17604a() {
        return this.f3540a;
    }

    /* renamed from: b */
    public String mo17606b() {
        return this.f3541b;
    }

    /* renamed from: a */
    public String mo17605a(String str) {
        if (str == null) {
            return this.f3540a;
        }
        return this.f3540a + str;
    }
}

package com.yandex.metrica;

/* renamed from: com.yandex.metrica.a */
public enum C1779a {
    PHONE("phone"),
    TABLET("tablet"),
    TV("tv");
    

    /* renamed from: d */
    private final String f2763d;

    private C1779a(String str) {
        this.f2763d = str;
    }

    /* renamed from: a */
    public String mo16673a() {
        return this.f2763d;
    }

    /* renamed from: a */
    public static C1779a m3992a(String str) {
        for (C1779a aVar : values()) {
            if (aVar.f2763d.equals(str)) {
                return aVar;
            }
        }
        return null;
    }
}

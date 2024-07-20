package com.yandex.metrica.impl.p050ob;

/* renamed from: com.yandex.metrica.impl.ob.cw */
public class C2052cw {

    /* renamed from: a */
    private final String f3441a;

    /* renamed from: b */
    private final boolean f3442b;

    public C2052cw(String str, boolean z) {
        this.f3441a = str;
        this.f3442b = z;
    }

    /* renamed from: a */
    public boolean mo17512a() {
        return this.f3442b;
    }

    /* renamed from: b */
    public String mo17513b() {
        return this.f3441a;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        C2052cw cwVar = (C2052cw) obj;
        if (this.f3442b != cwVar.f3442b) {
            return false;
        }
        return this.f3441a.equals(cwVar.f3441a);
    }

    public int hashCode() {
        return (this.f3441a.hashCode() * 31) + (this.f3442b ? 1 : 0);
    }
}

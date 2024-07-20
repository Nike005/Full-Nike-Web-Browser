package com.tappx.p048a;

import android.text.TextUtils;

/* renamed from: com.tappx.a.m5 */
public final class C1528m5 {

    /* renamed from: a */
    private final String f2084a;

    /* renamed from: b */
    private final String f2085b;

    public C1528m5(String str, String str2) {
        this.f2084a = str;
        this.f2085b = str2;
    }

    /* renamed from: a */
    public final String mo15969a() {
        return this.f2084a;
    }

    /* renamed from: b */
    public final String mo15970b() {
        return this.f2085b;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || C1528m5.class != obj.getClass()) {
            return false;
        }
        C1528m5 m5Var = (C1528m5) obj;
        if (!TextUtils.equals(this.f2084a, m5Var.f2084a) || !TextUtils.equals(this.f2085b, m5Var.f2085b)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (this.f2084a.hashCode() * 31) + this.f2085b.hashCode();
    }

    public String toString() {
        return "Header[name=" + this.f2084a + ",value=" + this.f2085b + "]";
    }
}

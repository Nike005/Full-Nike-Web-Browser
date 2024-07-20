package com.p088b.p089a.p090a.p091a.p093b;

import com.p088b.p089a.p090a.p091a.p097e.C5139e;

/* renamed from: com.b.a.a.a.b.g */
public class C5116g {

    /* renamed from: a */
    private final String f4951a;

    /* renamed from: b */
    private final String f4952b;

    private C5116g(String str, String str2) {
        this.f4951a = str;
        this.f4952b = str2;
    }

    /* renamed from: a */
    public static C5116g m7015a(String str, String str2) {
        C5139e.m7138a(str, "Name is null or empty");
        C5139e.m7138a(str2, "Version is null or empty");
        return new C5116g(str, str2);
    }

    /* renamed from: a */
    public String mo41843a() {
        return this.f4951a;
    }

    /* renamed from: b */
    public String mo41844b() {
        return this.f4952b;
    }
}

package com.appnext.base.p078a.p080b;

import java.util.Date;

/* renamed from: com.appnext.base.a.b.b */
public final class C4886b extends C4888d {

    /* renamed from: dG */
    private String f4568dG;

    /* renamed from: dH */
    private String f4569dH;

    /* renamed from: dI */
    private String f4570dI;

    /* renamed from: dJ */
    private Date f4571dJ;
    private String mDataType;

    public C4886b(String str, String str2, String str3) {
        this(str, str, str2, (Date) null, str3);
    }

    public C4886b(String str, String str2, String str3, String str4) {
        this(str, str2, str3, (Date) null, str4);
    }

    public C4886b(String str, String str2, String str3, Date date, String str4) {
        this.f4568dG = str;
        this.f4569dH = str2;
        this.f4570dI = str3;
        this.f4571dJ = date;
        this.mDataType = str4;
    }

    /* renamed from: ah */
    public final String mo40950ah() {
        return this.f4568dG;
    }

    public final String getType() {
        return this.f4569dH;
    }

    /* renamed from: ai */
    public final String mo40951ai() {
        return this.f4570dI;
    }

    /* renamed from: aj */
    public final Date mo40952aj() {
        return this.f4571dJ;
    }

    public final String getDataType() {
        return this.mDataType;
    }
}

package com.startapp.android.publish.adsCommon.p033f;

import com.startapp.android.publish.adsCommon.Utils.C1054c;
import com.startapp.android.publish.adsCommon.Utils.C1056e;
import com.startapp.common.p043a.C1253a;

/* renamed from: com.startapp.android.publish.adsCommon.f.b */
/* compiled from: StartAppSDK */
public class C1127b extends C1131e {

    /* renamed from: a */
    private String f1155a;

    /* renamed from: b */
    private String f1156b;

    /* renamed from: c */
    private boolean f1157c;

    /* renamed from: d */
    private String f1158d;

    public C1127b(C1130d dVar) {
        super(dVar);
    }

    public C1056e getNameValueJson() {
        C1056e nameValueJson = super.getNameValueJson();
        if (nameValueJson == null) {
            nameValueJson = new C1054c();
        }
        nameValueJson.mo14631a("sens", (Object) mo14872a(), false);
        nameValueJson.mo14631a("bt", (Object) mo14875b(), false);
        nameValueJson.mo14631a("isService", (Object) Boolean.valueOf(mo14878c()), false);
        nameValueJson.mo14631a("packagingType", (Object) mo14879d(), false);
        return nameValueJson;
    }

    /* renamed from: a */
    public String mo14872a() {
        return this.f1155a;
    }

    /* renamed from: b */
    public String mo14875b() {
        return this.f1156b;
    }

    /* renamed from: a */
    public void mo14873a(String str) {
        if (str != null) {
            this.f1155a = C1253a.m1988c(str);
        }
    }

    /* renamed from: b */
    public void mo14876b(String str) {
        if (str != null) {
            this.f1156b = C1253a.m1988c(str);
        }
    }

    /* renamed from: c */
    public boolean mo14878c() {
        return this.f1157c;
    }

    /* renamed from: a */
    public void mo14874a(boolean z) {
        this.f1157c = z;
    }

    /* renamed from: d */
    public String mo14879d() {
        return this.f1158d;
    }

    /* renamed from: c */
    public void mo14877c(String str) {
        this.f1158d = str;
    }

    public String toString() {
        return super.toString() + " DataEventRequest [" + "sensors=" + this.f1155a + ", bluetooth=" + this.f1156b + ", isService=" + this.f1157c + ", packagingType=" + this.f1158d + "]";
    }
}

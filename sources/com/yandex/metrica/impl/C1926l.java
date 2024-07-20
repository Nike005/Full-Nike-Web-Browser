package com.yandex.metrica.impl;

import android.content.ContentValues;
import com.yandex.metrica.impl.utils.C2223g;
import java.util.LinkedHashMap;
import java.util.Map;

/* renamed from: com.yandex.metrica.impl.l */
abstract class C1926l extends C1835ak {

    /* renamed from: a */
    static final ContentValues f3194a = new ContentValues();

    /* renamed from: b */
    final Map<String, String> f3195b = new LinkedHashMap();

    /* renamed from: c */
    final C1877ba f3196c = new C1877ba();

    C1926l() {
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public C1926l mo17152a(ContentValues contentValues) {
        this.f3195b.clear();
        for (Map.Entry next : contentValues.valueSet()) {
            this.f3195b.put(next.getKey(), next.getValue().toString());
        }
        mo17153b(contentValues);
        return this;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public void mo17153b(ContentValues contentValues) {
        String asString = contentValues.getAsString("report_request_parameters");
        if (!C1894bi.m4622a(asString)) {
            try {
                C2223g.C2224a aVar = new C2223g.C2224a(asString);
                this.f3196c.mo16984b(aVar.mo17922a("dId"));
                this.f3196c.mo16977a(aVar.mo17922a("uId"));
                this.f3196c.mo16996e(aVar.mo17922a("kitVer"));
                this.f3196c.mo16998f(aVar.mo17922a("clientKitVer"));
                this.f3196c.mo17000g(aVar.mo17922a("kitBuildNumber"));
                this.f3196c.mo17002h(aVar.mo17922a("kitBuildType"));
                this.f3196c.mo17008k(aVar.mo17922a("appVer"));
                this.f3196c.mo17024s(aVar.optString("app_debuggable", "0"));
                this.f3196c.mo17012m(aVar.mo17922a("appBuild"));
                this.f3196c.mo17004i(aVar.mo17922a("osVer"));
                this.f3196c.mo16972a(aVar.optInt("osApiLev", -1));
                this.f3196c.mo17006j(aVar.mo17922a("lang"));
                this.f3196c.mo17020q(aVar.mo17922a("root"));
            } catch (Exception unused) {
            }
        }
    }

    /* renamed from: a */
    public String mo16814a() {
        return super.mo16814a() + " [" + this.f3195b.toString() + "]";
    }
}

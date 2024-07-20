package com.startapp.android.publish.ads.banner;

import com.startapp.android.publish.adsCommon.Utils.C1055d;
import com.startapp.android.publish.adsCommon.Utils.C1056e;
import com.startapp.android.publish.common.model.GetAdRequest;

/* renamed from: com.startapp.android.publish.ads.banner.a */
/* compiled from: StartAppSDK */
public class C0880a extends GetAdRequest {

    /* renamed from: a */
    private boolean f464a;

    /* renamed from: b */
    private int f465b;

    /* renamed from: a */
    public void mo13900a(boolean z) {
        this.f464a = z;
    }

    /* renamed from: a */
    public boolean mo13901a() {
        return this.f464a;
    }

    /* renamed from: b */
    public int mo13902b() {
        return this.f465b;
    }

    /* renamed from: a */
    public void mo13899a(int i) {
        this.f465b = i;
    }

    public C1056e getNameValueMap() {
        C1056e nameValueMap = super.getNameValueMap();
        if (nameValueMap == null) {
            nameValueMap = new C1055d();
        }
        m584a(nameValueMap);
        return nameValueMap;
    }

    /* renamed from: a */
    private void m584a(C1056e eVar) {
        eVar.mo14631a("fixedSize", (Object) Boolean.valueOf(mo13901a()), false);
        eVar.mo14631a("bnrt", (Object) Integer.valueOf(mo13902b()), false);
    }
}

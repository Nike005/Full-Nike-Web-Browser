package com.startapp.android.publish.adsCommon;

import android.content.Context;
import com.startapp.android.publish.adsCommon.Utils.C1055d;
import com.startapp.android.publish.adsCommon.Utils.C1056e;
import com.startapp.android.publish.adsCommon.p040i.C1157a;
import com.startapp.android.publish.adsCommon.p040i.C1160b;
import com.startapp.common.p043a.C1261c;

/* renamed from: com.startapp.android.publish.adsCommon.j */
/* compiled from: StartAppSDK */
public class C1161j extends BaseRequest {

    /* renamed from: a */
    private C1160b f1235a;

    /* renamed from: b */
    private String f1236b;

    public C1161j(Context context) {
        this.f1235a = C1157a.m1588a(context);
        this.f1236b = C1261c.m2049j(context);
    }

    public C1056e getNameValueMap() {
        C1056e nameValueMap = super.getNameValueMap();
        if (nameValueMap == null) {
            nameValueMap = new C1055d();
        }
        nameValueMap.mo14631a("placement", (Object) "INAPP_DOWNLOAD", true);
        C1160b bVar = this.f1235a;
        if (bVar != null) {
            nameValueMap.mo14631a("install_referrer", (Object) bVar.mo14953a(), true);
            nameValueMap.mo14631a("referrer_click_timestamp_seconds", (Object) Long.valueOf(this.f1235a.mo14954b()), true);
            nameValueMap.mo14631a("install_begin_timestamp_seconds", (Object) Long.valueOf(this.f1235a.mo14955c()), true);
        }
        nameValueMap.mo14631a("apkSig", (Object) this.f1236b, true);
        return nameValueMap;
    }
}

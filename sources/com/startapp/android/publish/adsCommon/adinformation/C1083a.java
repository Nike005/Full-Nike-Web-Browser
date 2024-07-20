package com.startapp.android.publish.adsCommon.adinformation;

import android.content.Context;
import com.startapp.android.publish.adsCommon.AdsConstants;
import com.startapp.android.publish.adsCommon.Utils.C1061i;
import com.startapp.android.publish.adsCommon.p033f.C1130d;
import com.startapp.android.publish.adsCommon.p033f.C1132f;
import com.startapp.common.p043a.C1267e;
import com.startapp.common.p046c.C5303f;
import java.io.Serializable;

/* renamed from: com.startapp.android.publish.adsCommon.adinformation.a */
/* compiled from: StartAppSDK */
public class C1083a implements Serializable {

    /* renamed from: a */
    private static C1083a f1040a = new C1083a();

    /* renamed from: b */
    private static Object f1041b = new Object();
    private static final long serialVersionUID = 1;
    @C5303f(mo45477a = true)
    private AdInformationConfig AdInformation = AdInformationConfig.m1237a();
    private String adInformationMetadataUpdateVersion = AdsConstants.f961h;

    public C1083a() {
        mo14709a().mo14695g();
    }

    /* renamed from: a */
    public AdInformationConfig mo14709a() {
        return this.AdInformation;
    }

    /* renamed from: a */
    public static void m1254a(Context context) {
        C1083a aVar = (C1083a) C1267e.m2057a(context, "StartappAdInfoMetadata", C1083a.class);
        C1083a aVar2 = new C1083a();
        if (aVar != null) {
            boolean a = C1061i.m1198a(aVar, aVar2);
            if (!aVar.m1258f() && a) {
                C1132f.m1527a(context, C1130d.METADATA_NULL, "AdInformationMetaData", "", "");
            }
            aVar.m1257e();
            f1040a = aVar;
        } else {
            f1040a = aVar2;
        }
        m1256b().mo14709a().mo14695g();
    }

    /* renamed from: e */
    private void m1257e() {
        this.AdInformation.mo14699k();
    }

    /* renamed from: b */
    public static C1083a m1256b() {
        return f1040a;
    }

    /* renamed from: a */
    public static void m1255a(Context context, C1083a aVar) {
        synchronized (f1041b) {
            aVar.adInformationMetadataUpdateVersion = AdsConstants.f961h;
            f1040a = aVar;
            AdInformationConfig.m1238a(m1256b().AdInformation);
            m1256b().mo14709a().mo14695g();
            C1267e.m2061a(context, "StartappAdInfoMetadata", (Serializable) aVar);
        }
    }

    /* renamed from: c */
    public String mo14710c() {
        return this.AdInformation.mo14690b();
    }

    /* renamed from: d */
    public String mo14711d() {
        return this.AdInformation.mo14691c();
    }

    /* renamed from: f */
    private boolean m1258f() {
        return !AdsConstants.f961h.equals(this.adInformationMetadataUpdateVersion);
    }
}

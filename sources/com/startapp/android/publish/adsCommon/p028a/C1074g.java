package com.startapp.android.publish.adsCommon.p028a;

import com.startapp.android.publish.adsCommon.AdsConstants;
import com.startapp.common.p046c.C5303f;
import java.io.Serializable;

/* renamed from: com.startapp.android.publish.adsCommon.a.g */
/* compiled from: StartAppSDK */
public class C1074g implements Serializable {

    /* renamed from: a */
    private static transient C1074g f1016a = new C1074g();
    @C5303f(mo45477a = true)
    private C1072e adRules = new C1072e();
    private String adaptMetaDataUpdateVersion = AdsConstants.f961h;

    private C1074g() {
    }

    /* renamed from: a */
    public static C1074g m1233a() {
        return f1016a;
    }

    /* renamed from: b */
    public C1072e mo14667b() {
        return this.adRules;
    }
}

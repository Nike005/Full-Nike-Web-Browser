package com.startapp.android.publish.ads.splash;

import android.content.Context;
import com.startapp.android.publish.adsCommon.AdsConstants;
import com.startapp.android.publish.adsCommon.Utils.C1061i;
import com.startapp.android.publish.adsCommon.p033f.C1130d;
import com.startapp.android.publish.adsCommon.p033f.C1132f;
import com.startapp.common.p043a.C1267e;
import com.startapp.common.p046c.C5303f;
import java.io.Serializable;

/* renamed from: com.startapp.android.publish.ads.splash.f */
/* compiled from: StartAppSDK */
public class C0949f implements Serializable {

    /* renamed from: a */
    private static transient C0949f f647a = new C0949f();

    /* renamed from: b */
    private static Object f648b = new Object();
    private static final long serialVersionUID = 1;
    @C5303f(mo45477a = true)
    private SplashConfig SplashConfig = new SplashConfig();
    private String splashMetadataUpdateVersion = AdsConstants.f961h;

    private C0949f() {
    }

    /* renamed from: a */
    public SplashConfig mo14216a() {
        return this.SplashConfig;
    }

    /* renamed from: b */
    public static C0949f m790b() {
        return f647a;
    }

    /* renamed from: a */
    public static void m789a(Context context, C0949f fVar) {
        synchronized (f648b) {
            fVar.splashMetadataUpdateVersion = AdsConstants.f961h;
            f647a = fVar;
            C1267e.m2061a(context, "StartappSplashMetadata", (Serializable) fVar);
        }
    }

    /* renamed from: a */
    public static void m788a(Context context) {
        C0949f fVar = (C0949f) C1267e.m2057a(context, "StartappSplashMetadata", C0949f.class);
        C0949f fVar2 = new C0949f();
        if (fVar != null) {
            boolean a = C1061i.m1198a(fVar, fVar2);
            if (!fVar.m791c() && a) {
                C1132f.m1527a(context, C1130d.METADATA_NULL, "SplashMetaData", "", "");
            }
            f647a = fVar;
            return;
        }
        f647a = fVar2;
    }

    /* renamed from: c */
    private boolean m791c() {
        return !AdsConstants.f961h.equals(this.splashMetadataUpdateVersion);
    }
}

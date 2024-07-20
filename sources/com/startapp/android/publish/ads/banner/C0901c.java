package com.startapp.android.publish.ads.banner;

import android.content.Context;
import com.startapp.android.publish.adsCommon.AdsConstants;
import com.startapp.android.publish.adsCommon.Utils.C1061i;
import com.startapp.android.publish.adsCommon.p033f.C1130d;
import com.startapp.android.publish.adsCommon.p033f.C1132f;
import com.startapp.common.p043a.C1267e;
import com.startapp.common.p046c.C5303f;
import java.io.Serializable;

/* renamed from: com.startapp.android.publish.ads.banner.c */
/* compiled from: StartAppSDK */
public class C0901c implements Serializable {

    /* renamed from: a */
    private static transient Object f492a = new Object();

    /* renamed from: b */
    private static transient C0901c f493b = new C0901c();
    private static final long serialVersionUID = 1;
    @C5303f(mo45477a = true)
    private BannerOptions BannerOptions = new BannerOptions();
    private String bannerMetadataUpdateVersion = AdsConstants.f961h;

    /* renamed from: a */
    public static C0901c m627a() {
        return f493b;
    }

    /* renamed from: b */
    public BannerOptions mo13973b() {
        return this.BannerOptions;
    }

    /* renamed from: c */
    public BannerOptions mo13974c() {
        return new BannerOptions(this.BannerOptions);
    }

    /* renamed from: a */
    public static void m629a(Context context, C0901c cVar) {
        synchronized (f492a) {
            cVar.bannerMetadataUpdateVersion = AdsConstants.f961h;
            f493b = cVar;
            C1267e.m2061a(context, "StartappBannerMetadata", (Serializable) cVar);
        }
    }

    /* renamed from: a */
    public static void m628a(Context context) {
        C0901c cVar = (C0901c) C1267e.m2057a(context, "StartappBannerMetadata", C0901c.class);
        C0901c cVar2 = new C0901c();
        if (cVar != null) {
            boolean a = C1061i.m1198a(cVar, cVar2);
            if (!cVar.m630d() && a) {
                C1132f.m1527a(context, C1130d.METADATA_NULL, "BannerMetaData", "", "");
            }
            f493b = cVar;
            return;
        }
        f493b = cVar2;
    }

    /* renamed from: d */
    private boolean m630d() {
        return !AdsConstants.f961h.equals(this.bannerMetadataUpdateVersion);
    }
}

package com.startapp.android.publish.cache;

import android.content.Context;
import com.startapp.android.publish.adsCommon.AdsConstants;
import com.startapp.android.publish.adsCommon.Utils.C1061i;
import com.startapp.android.publish.adsCommon.p033f.C1130d;
import com.startapp.android.publish.adsCommon.p033f.C1132f;
import com.startapp.common.p043a.C1267e;
import com.startapp.common.p046c.C5303f;
import java.io.Serializable;

/* renamed from: com.startapp.android.publish.cache.d */
/* compiled from: StartAppSDK */
public class C1196d implements Serializable {

    /* renamed from: a */
    private static transient C1196d f1333a = new C1196d();
    private static final long serialVersionUID = 1;
    @C5303f(mo45477a = true)
    private ACMConfig ACM = new ACMConfig();
    private String cacheMetaDataUpdateVersion = AdsConstants.f961h;
    private float sendCacheSizeProb = 20.0f;

    private C1196d() {
    }

    /* renamed from: a */
    public static C1196d m1803a() {
        return f1333a;
    }

    /* renamed from: b */
    public ACMConfig mo15089b() {
        return this.ACM;
    }

    /* renamed from: a */
    public static void m1805a(Context context, C1196d dVar) {
        dVar.cacheMetaDataUpdateVersion = AdsConstants.f961h;
        f1333a = dVar;
        C1267e.m2061a(context, "StartappCacheMetadata", (Serializable) dVar);
    }

    /* renamed from: a */
    public static void m1804a(Context context) {
        C1196d dVar = (C1196d) C1267e.m2057a(context, "StartappCacheMetadata", C1196d.class);
        C1196d dVar2 = new C1196d();
        if (dVar != null) {
            boolean a = C1061i.m1198a(dVar, dVar2);
            if (!dVar.m1806d() && a) {
                C1132f.m1527a(context, C1130d.METADATA_NULL, "CacheMetaData", "", "");
            }
            f1333a = dVar;
            return;
        }
        f1333a = dVar2;
    }

    /* renamed from: d */
    private boolean m1806d() {
        return !AdsConstants.f961h.equals(this.cacheMetaDataUpdateVersion);
    }

    /* renamed from: c */
    public float mo15090c() {
        return this.sendCacheSizeProb;
    }
}

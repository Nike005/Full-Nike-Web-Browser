package com.startapp.android.publish.cache;

import com.startapp.android.publish.adsCommon.C1136g;
import com.startapp.android.publish.adsCommon.C1174m;
import com.startapp.common.p043a.C1270g;

/* renamed from: com.startapp.android.publish.cache.f */
/* compiled from: StartAppSDK */
public class C1199f extends C1197e {
    /* access modifiers changed from: protected */
    /* renamed from: e */
    public String mo15084e() {
        return "CacheTTLReloadTimer";
    }

    public C1199f(C1200g gVar) {
        super(gVar);
    }

    /* access modifiers changed from: protected */
    /* renamed from: c */
    public boolean mo15082c() {
        return C1174m.m1649a().mo14979a(this.f1334a.mo15105c());
    }

    /* access modifiers changed from: protected */
    /* renamed from: d */
    public long mo15083d() {
        C1136g b = this.f1334a.mo15103b();
        if (b == null) {
            C1270g.m2078a("CacheTTLReloadTimer", 3, "Missing ad");
            return -1;
        }
        Long adCacheTtl = b.getAdCacheTtl();
        Long lastLoadTime = b.getLastLoadTime();
        if (adCacheTtl == null || lastLoadTime == null) {
            C1270g.m2078a("CacheTTLReloadTimer", 3, "Missing TTL or last loading time");
            return -1;
        }
        long longValue = adCacheTtl.longValue() - (System.currentTimeMillis() - lastLoadTime.longValue());
        if (longValue >= 0) {
            return longValue;
        }
        return 0;
    }
}

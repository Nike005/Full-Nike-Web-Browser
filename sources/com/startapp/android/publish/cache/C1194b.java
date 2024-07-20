package com.startapp.android.publish.cache;

import com.startapp.android.publish.adsCommon.C1174m;
import com.startapp.common.p043a.C1270g;
import java.util.concurrent.TimeUnit;

/* renamed from: com.startapp.android.publish.cache.b */
/* compiled from: StartAppSDK */
public class C1194b extends C1197e {

    /* renamed from: b */
    private final FailuresHandler f1330b = C1196d.m1803a().mo15089b().getFailuresHandler();

    /* renamed from: c */
    private int f1331c = 0;

    /* renamed from: d */
    private boolean f1332d = false;

    /* access modifiers changed from: protected */
    /* renamed from: e */
    public String mo15084e() {
        return "CacheErrorReloadTimer";
    }

    public C1194b(C1200g gVar) {
        super(gVar);
    }

    /* renamed from: a */
    public void mo15080a() {
        super.mo15080a();
        this.f1331c = 0;
        this.f1332d = false;
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public void mo15081b() {
        m1795j();
        super.mo15081b();
    }

    /* access modifiers changed from: protected */
    /* renamed from: c */
    public boolean mo15082c() {
        if (!C1174m.m1649a().mo15007l() || !m1796k()) {
            return false;
        }
        if (this.f1332d) {
            return this.f1330b.isInfiniteLastRetry();
        }
        return true;
    }

    /* renamed from: j */
    private void m1795j() {
        if (this.f1331c == this.f1330b.getIntervals().size() - 1) {
            this.f1332d = true;
            C1270g.m2078a("CacheErrorReloadTimer", 4, "Reached end index: " + this.f1331c);
            return;
        }
        this.f1331c++;
        C1270g.m2078a("CacheErrorReloadTimer", 4, "Advanced to index: " + this.f1331c);
    }

    /* renamed from: k */
    private boolean m1796k() {
        FailuresHandler failuresHandler = this.f1330b;
        return (failuresHandler == null || failuresHandler.getIntervals() == null) ? false : true;
    }

    /* access modifiers changed from: protected */
    /* renamed from: d */
    public long mo15083d() {
        Long i;
        if (this.f1331c >= this.f1330b.getIntervals().size() || (i = mo15094i()) == null) {
            return -1;
        }
        long millis = TimeUnit.SECONDS.toMillis((long) this.f1330b.getIntervals().get(this.f1331c).intValue()) - (System.currentTimeMillis() - i.longValue());
        if (millis >= 0) {
            return millis;
        }
        return 0;
    }
}

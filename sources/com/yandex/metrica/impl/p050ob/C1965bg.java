package com.yandex.metrica.impl.p050ob;

import android.os.SystemClock;
import android.text.TextUtils;
import com.yandex.metrica.impl.C1877ba;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import org.json.JSONObject;

/* renamed from: com.yandex.metrica.impl.ob.bg */
public abstract class C1965bg {

    /* renamed from: a */
    protected C2198t f3223a;

    /* renamed from: b */
    protected C1970bk f3224b;

    /* renamed from: c */
    protected long f3225c = this.f3224b.mo17215a(-1);

    /* renamed from: d */
    protected long f3226d;

    /* renamed from: e */
    protected AtomicLong f3227e = new AtomicLong(this.f3224b.mo17221e(0));

    /* renamed from: f */
    private boolean f3228f = this.f3224b.mo17218b(true);

    /* renamed from: g */
    private volatile C1966a f3229g;

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public abstract C1971bl mo17213a();

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public abstract int mo17214b();

    C1965bg(C2198t tVar, C1970bk bkVar) {
        this.f3223a = tVar;
        this.f3224b = bkVar;
        this.f3226d = bkVar.mo17219c(SystemClock.elapsedRealtime());
        this.f3224b.mo17220d(this.f3226d).mo17259a();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: c */
    public long mo17232c() {
        return this.f3225c;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: e */
    public long mo17234e() {
        return this.f3224b.mo17223g(0) - TimeUnit.MILLISECONDS.toSeconds(this.f3226d);
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0052 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0053 A[RETURN] */
    /* renamed from: f */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean mo17235f() {
        /*
            r10 = this;
            long r0 = r10.f3225c
            r2 = 0
            r4 = 1
            r5 = 0
            int r6 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r6 < 0) goto L_0x004f
            com.yandex.metrica.impl.ob.bk r0 = r10.f3224b
            long r0 = r0.mo17223g(r2)
            long r6 = android.os.SystemClock.elapsedRealtime()
            r8 = 1000(0x3e8, double:4.94E-321)
            long r6 = r6 / r8
            long r6 = r6 - r0
            long r0 = r10.mo17236g()
            int r8 = (r6 > r2 ? 1 : (r6 == r2 ? 0 : -1))
            if (r8 < 0) goto L_0x0032
            int r2 = r10.mo17214b()
            long r2 = (long) r2
            int r8 = (r6 > r2 ? 1 : (r6 == r2 ? 0 : -1))
            if (r8 >= 0) goto L_0x0032
            long r2 = com.yandex.metrica.impl.p050ob.C1967bh.f3239c
            int r6 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r6 < 0) goto L_0x0030
            goto L_0x0032
        L_0x0030:
            r0 = 0
            goto L_0x0033
        L_0x0032:
            r0 = 1
        L_0x0033:
            if (r0 != 0) goto L_0x004f
            com.yandex.metrica.impl.ob.bg$a r0 = r10.m4920l()
            if (r0 == 0) goto L_0x0049
            com.yandex.metrica.impl.ob.t r1 = r10.f3223a
            com.yandex.metrica.impl.ba r1 = r1.mo17860h()
            boolean r0 = r0.mo17241a(r1)
            if (r0 != 0) goto L_0x0049
            r0 = 1
            goto L_0x004a
        L_0x0049:
            r0 = 0
        L_0x004a:
            if (r0 == 0) goto L_0x004d
            goto L_0x004f
        L_0x004d:
            r0 = 0
            goto L_0x0050
        L_0x004f:
            r0 = 1
        L_0x0050:
            if (r0 != 0) goto L_0x0053
            return r4
        L_0x0053:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.yandex.metrica.impl.p050ob.C1965bg.mo17235f():boolean");
    }

    /* access modifiers changed from: package-private */
    /* renamed from: g */
    public long mo17236g() {
        return TimeUnit.MILLISECONDS.toSeconds(SystemClock.elapsedRealtime() - this.f3226d);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: h */
    public synchronized void mo17237h() {
        this.f3224b.mo17224h(-2147483648L).mo17259a();
        this.f3229g = null;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: i */
    public void mo17238i() {
        this.f3224b.mo17224h(SystemClock.elapsedRealtime() / 1000).mo17259a();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: j */
    public long mo17239j() {
        long andIncrement = this.f3227e.getAndIncrement();
        this.f3224b.mo17222f(this.f3227e.get()).mo17259a();
        return andIncrement;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: k */
    public boolean mo17240k() {
        return this.f3228f && mo17232c() > 0;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo17231a(boolean z) {
        if (this.f3228f != z) {
            this.f3228f = z;
            this.f3224b.mo17216a(z).mo17259a();
        }
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(5:3|4|(3:6|7|(1:9))|10|11) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:10:0x0033 */
    /* renamed from: l */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.yandex.metrica.impl.p050ob.C1965bg.C1966a m4920l() {
        /*
            r4 = this;
            com.yandex.metrica.impl.ob.bg$a r0 = r4.f3229g
            if (r0 != 0) goto L_0x0038
            monitor-enter(r4)
            com.yandex.metrica.impl.ob.bg$a r0 = r4.f3229g     // Catch:{ all -> 0x0035 }
            if (r0 != 0) goto L_0x0033
            com.yandex.metrica.impl.ob.t r0 = r4.f3223a     // Catch:{ Exception -> 0x0033 }
            com.yandex.metrica.impl.ob.bn r0 = r0.mo17862i()     // Catch:{ Exception -> 0x0033 }
            long r1 = r4.mo17232c()     // Catch:{ Exception -> 0x0033 }
            com.yandex.metrica.impl.ob.bl r3 = r4.mo17213a()     // Catch:{ Exception -> 0x0033 }
            android.content.ContentValues r0 = r0.mo17275c(r1, r3)     // Catch:{ Exception -> 0x0033 }
            java.lang.String r1 = "report_request_parameters"
            java.lang.String r0 = r0.getAsString(r1)     // Catch:{ Exception -> 0x0033 }
            boolean r1 = android.text.TextUtils.isEmpty(r0)     // Catch:{ Exception -> 0x0033 }
            if (r1 != 0) goto L_0x0033
            org.json.JSONObject r1 = new org.json.JSONObject     // Catch:{ Exception -> 0x0033 }
            r1.<init>(r0)     // Catch:{ Exception -> 0x0033 }
            com.yandex.metrica.impl.ob.bg$a r0 = new com.yandex.metrica.impl.ob.bg$a     // Catch:{ Exception -> 0x0033 }
            r0.<init>(r1)     // Catch:{ Exception -> 0x0033 }
            r4.f3229g = r0     // Catch:{ Exception -> 0x0033 }
        L_0x0033:
            monitor-exit(r4)     // Catch:{ all -> 0x0035 }
            goto L_0x0038
        L_0x0035:
            r0 = move-exception
            monitor-exit(r4)     // Catch:{ all -> 0x0035 }
            throw r0
        L_0x0038:
            com.yandex.metrica.impl.ob.bg$a r0 = r4.f3229g
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.yandex.metrica.impl.p050ob.C1965bg.m4920l():com.yandex.metrica.impl.ob.bg$a");
    }

    /* renamed from: com.yandex.metrica.impl.ob.bg$a */
    static class C1966a {

        /* renamed from: a */
        private final String f3230a;

        /* renamed from: b */
        private final String f3231b;

        /* renamed from: c */
        private final String f3232c;

        /* renamed from: d */
        private final String f3233d;

        /* renamed from: e */
        private final String f3234e;

        /* renamed from: f */
        private final String f3235f;

        /* renamed from: g */
        private final int f3236g;

        C1966a(JSONObject jSONObject) {
            this.f3230a = jSONObject.optString("kitVer");
            this.f3231b = jSONObject.optString("clientKitVer");
            this.f3232c = jSONObject.optString("kitBuildNumber");
            this.f3233d = jSONObject.optString("appVer");
            this.f3234e = jSONObject.optString("appBuild");
            this.f3235f = jSONObject.optString("osVer");
            this.f3236g = jSONObject.optInt("osApiLev", -1);
        }

        /* access modifiers changed from: package-private */
        /* renamed from: a */
        public boolean mo17241a(C1877ba baVar) {
            return TextUtils.equals(baVar.mo17001h(), this.f3230a) && TextUtils.equals(baVar.mo17003i(), this.f3231b) && TextUtils.equals(baVar.mo17007k(), this.f3232c) && TextUtils.equals(baVar.mo17029x(), this.f3233d) && TextUtils.equals(baVar.mo17031z(), this.f3234e) && TextUtils.equals(baVar.mo17019q(), this.f3235f) && this.f3236g == baVar.mo17021r();
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: d */
    public synchronized void mo17233d() {
        this.f3225c = System.currentTimeMillis() / 1000;
        this.f3227e.set(0);
        this.f3226d = SystemClock.elapsedRealtime();
        this.f3229g = null;
        this.f3224b.mo17225i(this.f3225c).mo17224h(SystemClock.elapsedRealtime() / 1000).mo17220d(this.f3226d).mo17222f(this.f3227e.get()).mo17259a();
        this.f3223a.mo17862i().mo17269a(this.f3225c, mo17213a());
        mo17231a(true);
    }
}

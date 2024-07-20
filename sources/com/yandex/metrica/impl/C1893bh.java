package com.yandex.metrica.impl;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import com.integralads.avid.library.mopub.session.internal.InternalAvidAdSessionContext;
import com.yandex.metrica.impl.C1890bg;
import com.yandex.metrica.impl.p050ob.C2019cd;
import com.yandex.metrica.impl.p050ob.C2026ci;
import com.yandex.metrica.impl.p050ob.C2045cq;
import com.yandex.metrica.impl.p050ob.C2050cu;
import com.yandex.metrica.impl.p050ob.C2093du;
import com.yandex.metrica.impl.p050ob.C2174g;
import com.yandex.metrica.impl.p050ob.C2180i;
import com.yandex.metrica.impl.p050ob.C2187o;
import com.yandex.metrica.impl.p050ob.C2198t;
import com.yandex.metrica.impl.utils.C2229k;
import com.yandex.metrica.impl.utils.C2231l;
import java.util.List;
import java.util.Map;
import org.json.JSONException;

/* renamed from: com.yandex.metrica.impl.bh */
class C1893bh extends C1835ak {

    /* renamed from: a */
    private C1877ba f3112a;

    /* renamed from: b */
    private Context f3113b;

    /* renamed from: c */
    private C2198t f3114c;

    /* renamed from: m */
    private C2019cd f3115m;

    /* renamed from: n */
    private boolean f3116n = false;

    /* renamed from: o */
    private C2093du f3117o;

    /* renamed from: p */
    private List<String> f3118p;

    /* renamed from: n */
    public boolean mo16833n() {
        return true;
    }

    /* renamed from: p */
    public long mo16835p() {
        return 0;
    }

    public C1893bh(C2198t tVar) {
        this.f3114c = tVar;
        this.f3113b = tVar.mo17866m();
        this.f3112a = tVar.mo17860h();
        this.f3115m = tVar.mo17833E();
        this.f3118p = this.f3112a.mo16958D();
    }

    /* renamed from: b */
    public boolean mo16821b() {
        mo17074a(false);
        this.f3112a.mo16983b(this.f3114c);
        if (mo17075s()) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: d */
    public C2045cq mo16823d() {
        return new C2050cu(this.f3115m.mo17433h((String) null)).mo17510a(mo16827h());
    }

    /* access modifiers changed from: package-private */
    /* renamed from: s */
    public boolean mo17075s() {
        boolean z;
        boolean z2 = !this.f3112a.mo16980a(this.f3115m.mo17412a(0));
        String a = C2231l.m5971a(this.f3114c.mo17863j().mo16608u());
        if (!z2 && !TextUtils.isEmpty(a)) {
            if (a.equals(this.f3115m.mo17443m())) {
                if ((System.currentTimeMillis() - this.f3115m.mo17444n()) / 1000 <= 86400) {
                    z2 = false;
                }
            }
            z2 = true;
        }
        if (z2) {
            return z2;
        }
        String d = C2026ci.m5230a().mo17478d();
        if (TextUtils.isEmpty(d)) {
            z = TextUtils.isEmpty(m4604a(this.f3112a));
        } else {
            z = TextUtils.equals(m4604a(this.f3112a), d);
        }
        return !z;
    }

    /* renamed from: a */
    private static String m4604a(C1877ba baVar) {
        String c = baVar.mo16986c();
        String e = baVar.mo16994e();
        if (TextUtils.isEmpty(c)) {
            return TextUtils.isEmpty(e) ? "" : e;
        }
        return c;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo17072a(Uri.Builder builder) {
        builder.path("analytics/startup");
        builder.appendQueryParameter("deviceid", m4604a(this.f3112a));
        builder.appendQueryParameter("app_platform", this.f3112a.mo17011m());
        builder.appendQueryParameter("protocol_version", this.f3112a.mo16997f());
        builder.appendQueryParameter("analytics_sdk_version", this.f3112a.mo17001h());
        builder.appendQueryParameter("analytics_sdk_version_name", this.f3112a.mo16999g());
        builder.appendQueryParameter("model", this.f3112a.mo17017p());
        builder.appendQueryParameter("manufacturer", this.f3112a.mo17015o());
        builder.appendQueryParameter("os_version", this.f3112a.mo17019q());
        builder.appendQueryParameter("screen_width", String.valueOf(this.f3112a.mo17023s()));
        builder.appendQueryParameter("screen_height", String.valueOf(this.f3112a.mo17025t()));
        builder.appendQueryParameter("screen_dpi", String.valueOf(this.f3112a.mo17026u()));
        builder.appendQueryParameter("scalefactor", String.valueOf(this.f3112a.mo17027v()));
        builder.appendQueryParameter("locale", this.f3112a.mo17028w());
        builder.appendQueryParameter("device_type", this.f3112a.mo16961G());
        builder.appendQueryParameter("query_hosts", InternalAvidAdSessionContext.AVID_API_LEVEL);
        builder.appendQueryParameter("features", C1894bi.m4627b("easy_collecting", "package_info", "socket", "permissions_collecting", "features_collecting"));
        builder.appendQueryParameter("browsers", "1");
        builder.appendQueryParameter("socket", "1");
        builder.appendQueryParameter("app_id", this.f3114c.mo17865l().mo17819b());
        builder.appendQueryParameter("app_debuggable", this.f3112a.mo16968N());
        Map<String, String> u = this.f3114c.mo17863j().mo16608u();
        String v = this.f3114c.mo17863j().mo16609v();
        if (TextUtils.isEmpty(v)) {
            v = this.f3115m.mo17423c();
        }
        if (!C1897bk.m4653a((Map) u)) {
            builder.appendQueryParameter("distribution_customization", "1");
            m4605a(builder, "clids_set", C2231l.m5971a(u));
            if (!TextUtils.isEmpty(v)) {
                builder.appendQueryParameter("install_referrer", v);
            }
        }
        m4605a(builder, "uuid", this.f3112a.mo16981b());
    }

    /* renamed from: a */
    private static void m4605a(Uri.Builder builder, String str, String str2) {
        if (!TextUtils.isEmpty(str2)) {
            builder.appendQueryParameter(str, str2);
        }
    }

    /* renamed from: c */
    public boolean mo16822c() {
        this.f2950k = false;
        if (mo17076t()) {
            this.f2950k = true;
        } else if (200 == this.f2947h) {
            Map<String, String> u = this.f3114c.mo17863j().mo16608u();
            C1890bg.C1891a a = C1890bg.m4561a(this.f2948i);
            if (C1890bg.C1891a.C1892a.OK == a.mo17064k()) {
                this.f3115m.mo17450u(this.f3112a.mo17030y());
                this.f3112a.mo16973a(a);
                Long a2 = C1890bg.m4562a(mo16831l());
                if (a2 != null) {
                    C2229k.m5967a().mo17928a(a2.longValue());
                }
                this.f3112a.mo16984b(C2026ci.m5230a().mo17477c(this.f3113b, this.f3112a.mo16986c()));
                String str = "";
                if (a.mo17068o() == null) {
                    C2174g.m5753a().mo17803a((Class<? extends C2180i>) C2187o.class);
                } else {
                    try {
                        str = a.mo17068o().mo17646d();
                        C2174g.m5753a().mo17806b((C2180i) new C2187o(a.mo17068o()));
                    } catch (JSONException unused) {
                    }
                }
                mo17073a(this.f3112a, str);
                this.f3114c.mo17844a(C2231l.m5972a(this.f3112a.mo17030y()).equals(u));
                C1921j.m4769a(this.f3114c.mo17864k(), this.f3112a, a);
                this.f2950k = true;
            } else {
                this.f3117o = C2093du.PARSE;
            }
        }
        return this.f2950k;
    }

    /* renamed from: g */
    public void mo16826g() {
        this.f3117o = C2093du.NETWORK;
    }

    /* renamed from: f */
    public void mo16825f() {
        if (!this.f2950k) {
            if (this.f3117o == null) {
                this.f3117o = C2093du.UNKNOWN;
            }
            C1921j.m4770a(this.f3114c.mo17864k(), this.f3117o);
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x00ce, code lost:
        return;
     */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void mo17073a(com.yandex.metrica.impl.C1877ba r5, java.lang.String r6) {
        /*
            r4 = this;
            monitor-enter(r4)
            boolean r0 = r4.mo17076t()     // Catch:{ all -> 0x00cf }
            if (r0 == 0) goto L_0x0009
            monitor-exit(r4)
            return
        L_0x0009:
            com.yandex.metrica.impl.ob.cd r0 = r4.f3115m     // Catch:{ all -> 0x00cf }
            java.lang.String r1 = r5.mo16981b()     // Catch:{ all -> 0x00cf }
            com.yandex.metrica.impl.ob.cd r0 = r0.mo17436j(r1)     // Catch:{ all -> 0x00cf }
            java.util.List r1 = r5.mo16959E()     // Catch:{ all -> 0x00cf }
            com.yandex.metrica.impl.ob.cd r0 = r0.mo17413a((java.util.List<java.lang.String>) r1)     // Catch:{ all -> 0x00cf }
            java.lang.String r1 = r5.mo16957C()     // Catch:{ all -> 0x00cf }
            com.yandex.metrica.impl.ob.cd r0 = r0.mo17440l(r1)     // Catch:{ all -> 0x00cf }
            java.lang.String r1 = r5.mo16956B()     // Catch:{ all -> 0x00cf }
            com.yandex.metrica.impl.ob.cd r0 = r0.mo17442m(r1)     // Catch:{ all -> 0x00cf }
            java.lang.String r1 = r5.mo16955A()     // Catch:{ all -> 0x00cf }
            com.yandex.metrica.impl.ob.cd r0 = r0.mo17445n(r1)     // Catch:{ all -> 0x00cf }
            java.lang.String r1 = r5.mo16966L()     // Catch:{ all -> 0x00cf }
            com.yandex.metrica.impl.ob.cd r0 = r0.mo17434i(r1)     // Catch:{ all -> 0x00cf }
            boolean r1 = r5.mo16962H()     // Catch:{ all -> 0x00cf }
            com.yandex.metrica.impl.ob.cd r0 = r0.mo17414a((boolean) r1)     // Catch:{ all -> 0x00cf }
            boolean r1 = r5.mo16963I()     // Catch:{ all -> 0x00cf }
            com.yandex.metrica.impl.ob.cd r0 = r0.mo17419b((boolean) r1)     // Catch:{ all -> 0x00cf }
            boolean r1 = r5.mo16964J()     // Catch:{ all -> 0x00cf }
            com.yandex.metrica.impl.ob.cd r0 = r0.mo17422c((boolean) r1)     // Catch:{ all -> 0x00cf }
            boolean r1 = r5.mo16965K()     // Catch:{ all -> 0x00cf }
            com.yandex.metrica.impl.ob.cd r0 = r0.mo17425d((boolean) r1)     // Catch:{ all -> 0x00cf }
            r0.mo17449t(r6)     // Catch:{ all -> 0x00cf }
            java.lang.String r6 = r5.mo17030y()     // Catch:{ all -> 0x00cf }
            boolean r0 = android.text.TextUtils.isEmpty(r6)     // Catch:{ all -> 0x00cf }
            if (r0 != 0) goto L_0x006d
            com.yandex.metrica.impl.ob.cd r0 = r4.f3115m     // Catch:{ all -> 0x00cf }
            r0.mo17447p(r6)     // Catch:{ all -> 0x00cf }
        L_0x006d:
            com.yandex.metrica.impl.ob.cd r6 = r4.f3115m     // Catch:{ all -> 0x00cf }
            r6.mo17398h()     // Catch:{ all -> 0x00cf }
            long r0 = java.lang.System.currentTimeMillis()     // Catch:{ all -> 0x00cf }
            r2 = 1000(0x3e8, double:4.94E-321)
            long r0 = r0 / r2
            r4.mo17071a((long) r0)     // Catch:{ all -> 0x00cf }
            com.yandex.metrica.impl.ob.co r6 = com.yandex.metrica.impl.p050ob.C2041co.m5269a()     // Catch:{ all -> 0x00cf }
            android.content.Context r0 = r4.f3113b     // Catch:{ all -> 0x00cf }
            com.yandex.metrica.impl.ba r1 = r4.f3112a     // Catch:{ all -> 0x00cf }
            java.lang.String r1 = r1.mo16981b()     // Catch:{ all -> 0x00cf }
            java.lang.String r2 = r5.mo16966L()     // Catch:{ all -> 0x00cf }
            r6.mo17503a(r0, r1, r2)     // Catch:{ all -> 0x00cf }
            java.lang.String r6 = r5.mo16986c()     // Catch:{ all -> 0x00cf }
            boolean r6 = com.yandex.metrica.impl.C1894bi.m4622a((java.lang.String) r6)     // Catch:{ all -> 0x00cf }
            if (r6 != 0) goto L_0x00cd
            android.content.Intent r6 = new android.content.Intent     // Catch:{ all -> 0x00cf }
            java.lang.String r0 = "com.yandex.metrica.intent.action.SYNC"
            r6.<init>(r0)     // Catch:{ all -> 0x00cf }
            java.lang.String r0 = "CAUSE"
            java.lang.String r1 = "CAUSE_DEVICE_ID"
            r6.putExtra(r0, r1)     // Catch:{ all -> 0x00cf }
            java.lang.String r0 = "SYNC_TO_PKG"
            com.yandex.metrica.impl.ob.t r1 = r4.f3114c     // Catch:{ all -> 0x00cf }
            com.yandex.metrica.impl.ob.r r1 = r1.mo17865l()     // Catch:{ all -> 0x00cf }
            java.lang.String r1 = r1.mo17819b()     // Catch:{ all -> 0x00cf }
            r6.putExtra(r0, r1)     // Catch:{ all -> 0x00cf }
            java.lang.String r0 = "SYNC_DATA"
            java.lang.String r1 = r5.mo16986c()     // Catch:{ all -> 0x00cf }
            r6.putExtra(r0, r1)     // Catch:{ all -> 0x00cf }
            java.lang.String r0 = "SYNC_DATA_2"
            java.lang.String r5 = r5.mo16981b()     // Catch:{ all -> 0x00cf }
            r6.putExtra(r0, r5)     // Catch:{ all -> 0x00cf }
            android.content.Context r5 = r4.f3113b     // Catch:{ all -> 0x00cf }
            r5.sendBroadcast(r6)     // Catch:{ all -> 0x00cf }
        L_0x00cd:
            monitor-exit(r4)
            return
        L_0x00cf:
            r5 = move-exception
            monitor-exit(r4)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.yandex.metrica.impl.C1893bh.mo17073a(com.yandex.metrica.impl.ba, java.lang.String):void");
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public synchronized void mo17071a(long j) {
        this.f3115m.mo17417b(j).mo17398h();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public synchronized void mo17074a(boolean z) {
        this.f3116n = z;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: t */
    public synchronized boolean mo17076t() {
        return this.f3116n;
    }

    /* renamed from: o */
    public boolean mo16834o() {
        return mo16836q() + 1 < this.f3118p.size();
    }

    /* renamed from: e */
    public void mo16824e() {
        super.mo16824e();
        Uri.Builder buildUpon = Uri.parse(this.f3118p.get(mo16836q())).buildUpon();
        mo17072a(buildUpon);
        mo16816a(buildUpon.build().toString());
    }

    /* renamed from: a */
    public String mo16814a() {
        return "Startup task for component: " + this.f3114c.mo17865l().toString();
    }
}

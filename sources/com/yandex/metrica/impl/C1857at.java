package com.yandex.metrica.impl;

import android.database.Cursor;
import android.net.Uri;
import android.text.TextUtils;
import com.yandex.metrica.C1781c;
import com.yandex.metrica.impl.C1807a;
import com.yandex.metrica.impl.C1835ak;
import com.yandex.metrica.impl.p050ob.C1957b;
import com.yandex.metrica.impl.p050ob.C1971bl;
import com.yandex.metrica.impl.p050ob.C2000bn;
import com.yandex.metrica.impl.p050ob.C2045cq;
import com.yandex.metrica.impl.p050ob.C2049ct;
import com.yandex.metrica.impl.p050ob.C2056d;
import com.yandex.metrica.impl.p050ob.C2116ee;
import com.yandex.metrica.impl.p050ob.C2117ef;
import com.yandex.metrica.impl.p050ob.C2118eg;
import com.yandex.metrica.impl.p050ob.C2119eh;
import com.yandex.metrica.impl.p050ob.C2198t;
import com.yandex.metrica.impl.utils.C2221f;
import com.yandex.metrica.impl.utils.C2228j;
import com.yandex.metrica.impl.utils.C2232m;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.yandex.metrica.impl.at */
class C1857at extends C1926l {

    /* renamed from: m */
    C1781c.C1782a f2984m;

    /* renamed from: n */
    C1877ba f2985n;

    /* renamed from: o */
    C2000bn f2986o;

    /* renamed from: p */
    C2198t f2987p;

    /* renamed from: q */
    List<Long> f2988q;

    /* renamed from: r */
    int f2989r = 0;

    /* renamed from: s */
    int f2990s = -1;

    /* renamed from: t */
    private C1861c f2991t;

    /* renamed from: u */
    private final C2221f f2992u = new C2221f();

    /* renamed from: v */
    private boolean f2993v;

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public boolean mo16873a(long j) {
        return -2 == j;
    }

    public C1857at(C2198t tVar) {
        this.f2987p = tVar;
        this.f2986o = tVar.mo17862i();
        this.f2985n = tVar.mo17860h();
        this.f2989r = C1957b.m4842b(1, (C2056d) C1842ap.m4265a(Long.valueOf(System.currentTimeMillis() / 1000), Long.valueOf(C2232m.m5973a())));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: v */
    public void mo16874v() {
        Uri.Builder buildUpon = Uri.parse(this.f2985n.mo16957C()).buildUpon();
        buildUpon.path("report");
        buildUpon.appendQueryParameter("deviceid", C1894bi.m4628c(this.f3196c.mo16986c(), this.f2985n.mo16986c()));
        buildUpon.appendQueryParameter("uuid", C1894bi.m4628c(this.f3196c.mo16981b(), this.f2985n.mo16981b()));
        buildUpon.appendQueryParameter("analytics_sdk_version", C1894bi.m4628c(this.f3196c.mo17001h(), this.f2985n.mo17001h()));
        buildUpon.appendQueryParameter("client_analytics_sdk_version", C1894bi.m4628c(this.f3196c.mo17003i(), this.f2985n.mo17003i()));
        buildUpon.appendQueryParameter("app_version_name", C1894bi.m4628c(this.f3196c.mo17029x(), this.f2985n.mo17029x()));
        buildUpon.appendQueryParameter("app_build_number", C1894bi.m4628c(this.f3196c.mo17031z(), this.f2985n.mo17031z()));
        buildUpon.appendQueryParameter("os_version", C1894bi.m4628c(this.f3196c.mo17019q(), this.f2985n.mo17019q()));
        if (this.f3196c.mo17021r() > 0) {
            buildUpon.appendQueryParameter("os_api_level", String.valueOf(this.f3196c.mo17021r()));
        }
        if (!TextUtils.isEmpty(this.f3196c.mo17007k())) {
            buildUpon.appendQueryParameter("analytics_sdk_build_number", this.f3196c.mo17007k());
        }
        if (!TextUtils.isEmpty(this.f3196c.mo17009l())) {
            buildUpon.appendQueryParameter("analytics_sdk_build_type", this.f3196c.mo17009l());
        }
        if (!TextUtils.isEmpty(this.f3196c.mo16968N())) {
            buildUpon.appendQueryParameter("app_debuggable", this.f3196c.mo16968N());
        }
        buildUpon.appendQueryParameter("locale", C1894bi.m4628c(this.f3196c.mo17028w(), this.f2985n.mo17028w()));
        buildUpon.appendQueryParameter("is_rooted", C1894bi.m4628c(this.f3196c.mo16960F(), this.f2985n.mo16960F()));
        buildUpon.appendQueryParameter("app_framework", C1894bi.m4628c(this.f3196c.mo16991d(), this.f2985n.mo16991d()));
        buildUpon.appendQueryParameter(this.f2985n.mo17005j() >= 200 ? "api_key_128" : "api_key", mo16877y());
        buildUpon.appendQueryParameter("app_id", this.f2987p.mo17865l().mo17819b());
        buildUpon.appendQueryParameter("app_platform", this.f2985n.mo17011m());
        buildUpon.appendQueryParameter("protocol_version", this.f2985n.mo16997f());
        buildUpon.appendQueryParameter("model", this.f2985n.mo17017p());
        buildUpon.appendQueryParameter("manufacturer", this.f2985n.mo17015o());
        buildUpon.appendQueryParameter("screen_width", String.valueOf(this.f2985n.mo17023s()));
        buildUpon.appendQueryParameter("screen_height", String.valueOf(this.f2985n.mo17025t()));
        buildUpon.appendQueryParameter("screen_dpi", String.valueOf(this.f2985n.mo17026u()));
        buildUpon.appendQueryParameter("scalefactor", String.valueOf(this.f2985n.mo17027v()));
        buildUpon.appendQueryParameter("device_type", this.f2985n.mo16961G());
        buildUpon.appendQueryParameter("android_id", this.f2985n.mo17013n());
        String a = this.f2985n.mo16970a(this.f2987p.mo17866m());
        if (!TextUtils.isEmpty(a)) {
            buildUpon.appendQueryParameter("adv_id", a);
        }
        String y = this.f2985n.mo17030y();
        if (!TextUtils.isEmpty(y)) {
            buildUpon.appendQueryParameter("clids_set", y);
        }
        mo16816a(buildUpon.build().toString());
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public C1781c.C1782a mo16870a(C1861c cVar, C1781c.C1782a.C1785c[] cVarArr) {
        C1781c.C1782a aVar = new C1781c.C1782a();
        mo16872a(aVar);
        aVar.f2765c = (C1781c.C1782a.C1786d[]) cVar.f2999a.toArray(new C1781c.C1782a.C1786d[cVar.f2999a.size()]);
        aVar.f2766d = m4329a(cVar.f3001c);
        aVar.f2767e = cVarArr;
        this.f2989r += C1957b.m4855g(8);
        return aVar;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo16872a(final C1781c.C1782a aVar) {
        C2117ef.m5566a(this.f2987p.mo17866m()).mo17675a((C2119eh) new C2119eh() {
            /* renamed from: a */
            public void mo16879a(C2118eg egVar) {
                C1781c.C1782a aVar = aVar;
                List<String> c = egVar.mo17707c();
                if (!C1897bk.m4652a((Collection) c)) {
                    aVar.f2768f = new String[c.size()];
                    for (int i = 0; i < c.size(); i++) {
                        String str = c.get(i);
                        if (!TextUtils.isEmpty(str)) {
                            aVar.f2768f[i] = str;
                            C1857at.this.f2989r += C1957b.m4846b(aVar.f2768f[i]);
                            C1857at.this.f2989r += C1957b.m4855g(9);
                        }
                    }
                }
                C1781c.C1782a aVar2 = aVar;
                List<C2116ee> a = egVar.mo17705a();
                if (!C1897bk.m4652a((Collection) a)) {
                    aVar2.f2769g = new C1781c.C1782a.C1794e[a.size()];
                    for (int i2 = 0; i2 < a.size(); i2++) {
                        aVar2.f2769g[i2] = C1842ap.m4263a(a.get(i2));
                        C1857at.this.f2989r += C1957b.m4845b((C2056d) aVar2.f2769g[i2]);
                        C1857at.this.f2989r += C1957b.m4855g(10);
                    }
                }
            }
        });
    }

    /* renamed from: b */
    public boolean mo16821b() {
        if (!this.f2985n.mo16967M()) {
            return false;
        }
        this.f2988q = null;
        this.f2993v = this.f2987p.mo17836H();
        C1781c.C1782a.C1785c[] w = mo16875w();
        C1861c x = mo16876x();
        this.f2991t = x;
        if (x.f2999a.isEmpty()) {
            return false;
        }
        this.f2984m = mo16870a(this.f2991t, w);
        mo16874v();
        this.f2988q = this.f2991t.f3000b;
        return true;
    }

    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0051 */
    /* renamed from: e */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void mo16824e() {
        /*
            r5 = this;
            super.mo16824e()
            com.yandex.metrica.c$a r0 = r5.f2984m
            long r1 = java.lang.System.currentTimeMillis()
            r3 = 1000(0x3e8, double:4.94E-321)
            long r1 = r1 / r3
            java.lang.Long r1 = java.lang.Long.valueOf(r1)
            long r2 = com.yandex.metrica.impl.utils.C2232m.m5973a()
            java.lang.Long r2 = java.lang.Long.valueOf(r2)
            com.yandex.metrica.c$a$f r1 = com.yandex.metrica.impl.C1842ap.m4265a(r1, r2)
            r0.f2764b = r1
            com.yandex.metrica.c$a r0 = r5.f2984m
            byte[] r0 = com.yandex.metrica.impl.p050ob.C2056d.m5290a((com.yandex.metrica.impl.p050ob.C2056d) r0)
            java.io.ByteArrayOutputStream r1 = new java.io.ByteArrayOutputStream
            r1.<init>()
            r2 = 0
            java.util.zip.GZIPOutputStream r3 = new java.util.zip.GZIPOutputStream     // Catch:{ Exception -> 0x0051 }
            r3.<init>(r1)     // Catch:{ Exception -> 0x0051 }
            r2 = 0
            int r4 = r0.length     // Catch:{ Exception -> 0x004d, all -> 0x004a }
            r3.write(r0, r2, r4)     // Catch:{ Exception -> 0x004d, all -> 0x004a }
            r3.finish()     // Catch:{ Exception -> 0x004d, all -> 0x004a }
            byte[] r2 = r1.toByteArray()     // Catch:{ Exception -> 0x004d, all -> 0x004a }
            r5.mo16818a((byte[]) r2)     // Catch:{ Exception -> 0x004d, all -> 0x004a }
            java.lang.String r2 = "gzip"
            r5.mo16819b((java.lang.String) r2)     // Catch:{ Exception -> 0x004d, all -> 0x004a }
            com.yandex.metrica.impl.C1897bk.m4645a((java.io.Closeable) r1)
            com.yandex.metrica.impl.C1897bk.m4645a((java.io.Closeable) r3)
            return
        L_0x004a:
            r0 = move-exception
            r2 = r3
            goto L_0x0060
        L_0x004d:
            r2 = r3
            goto L_0x0051
        L_0x004f:
            r0 = move-exception
            goto L_0x0060
        L_0x0051:
            r5.mo16818a((byte[]) r0)     // Catch:{ all -> 0x004f }
            java.lang.String r0 = "identity"
            r5.mo16819b((java.lang.String) r0)     // Catch:{ all -> 0x004f }
            com.yandex.metrica.impl.C1897bk.m4645a((java.io.Closeable) r1)
            com.yandex.metrica.impl.C1897bk.m4645a((java.io.Closeable) r2)
            return
        L_0x0060:
            com.yandex.metrica.impl.C1897bk.m4645a((java.io.Closeable) r1)
            com.yandex.metrica.impl.C1897bk.m4645a((java.io.Closeable) r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.yandex.metrica.impl.C1857at.mo16824e():void");
    }

    /* access modifiers changed from: package-private */
    /* renamed from: w */
    public C1781c.C1782a.C1785c[] mo16875w() {
        C1781c.C1782a.C1785c[] a = C1842ap.m4269a(this.f2987p.mo17866m());
        if (a != null) {
            int length = a.length;
            for (int i = 0; i < length; i++) {
                this.f2989r += C1957b.m4845b((C2056d) a[i]);
            }
        }
        return a;
    }

    /* renamed from: a */
    private static C1781c.C1782a.C1783a[] m4329a(JSONObject jSONObject) {
        int length = jSONObject.length();
        if (length <= 0) {
            return null;
        }
        C1781c.C1782a.C1783a[] aVarArr = new C1781c.C1782a.C1783a[length];
        Iterator<String> keys = jSONObject.keys();
        int i = 0;
        while (keys.hasNext()) {
            String next = keys.next();
            try {
                C1781c.C1782a.C1783a aVar = new C1781c.C1782a.C1783a();
                aVar.f2771b = next;
                aVar.f2772c = jSONObject.getString(next);
                aVarArr[i] = aVar;
            } catch (JSONException unused) {
            }
            i++;
        }
        return aVarArr;
    }

    /* renamed from: c */
    public boolean mo16822c() {
        boolean z = true;
        this.f2950k = mo16830k() == 200;
        boolean z2 = mo16830k() == 400;
        if (!this.f2950k && !z2) {
            z = false;
        }
        if (z) {
            C1781c.C1782a.C1786d[] dVarArr = this.f2984m.f2765c;
            for (int i = 0; i < dVarArr.length; i++) {
                C1781c.C1782a.C1786d dVar = dVarArr[i];
                this.f2986o.mo17268a(this.f2988q.get(i).longValue(), C1842ap.m4266a(dVar.f2786c.f2822d).mo17260a(), dVar.f2787d.length);
                C1842ap.m4268a();
            }
            this.f2986o.mo17263a(this.f2987p.mo17838a().mo17248c());
        }
        return this.f2950k;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: d */
    public C2045cq mo16823d() {
        return new C2049ct().mo17509a(mo16827h());
    }

    /* renamed from: f */
    public void mo16825f() {
        if (this.f2950k) {
            C2228j p = this.f2987p.mo17869p();
            if (p.mo17906b()) {
                for (int i = 0; i < this.f2991t.f2999a.size(); i++) {
                    p.mo17926a(this.f2991t.f2999a.get(i), "Event sent");
                }
            }
        }
        this.f2991t = null;
    }

    /* renamed from: o */
    public boolean mo16834o() {
        boolean z = true;
        boolean z2 = (!mo16837r()) & (mo16836q() < 3);
        if (400 == mo16830k()) {
            z = false;
        }
        return z2 & z;
    }

    /* renamed from: p */
    public long mo16835p() {
        return (mo16836q() + 1) % 3 != 0 ? C1835ak.C1836a.f2952a : C1835ak.C1836a.f2953b;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v4, resolved type: com.yandex.metrica.impl.a$a} */
    /* JADX WARNING: type inference failed for: r3v0 */
    /* JADX WARNING: type inference failed for: r3v2, types: [android.database.Cursor] */
    /* JADX WARNING: type inference failed for: r3v3, types: [android.database.Cursor] */
    /* JADX WARNING: type inference failed for: r3v5 */
    /* JADX WARNING: type inference failed for: r3v6 */
    /* JADX WARNING: type inference failed for: r3v13 */
    /* access modifiers changed from: protected */
    /* JADX WARNING: Can't wrap try/catch for region: R(8:(1:13)(2:14|(1:39))|16|17|18|19|20|21|(1:42)(1:37)) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:20:0x0093 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x0099 A[EDGE_INSN: B:37:0x0099->B:23:0x0099 ?: BREAK  , SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x0014 A[SYNTHETIC] */
    /* renamed from: x */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.yandex.metrica.impl.C1857at.C1861c mo16876x() {
        /*
            r12 = this;
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            org.json.JSONObject r2 = new org.json.JSONObject
            r2.<init>()
            r3 = 0
            android.database.Cursor r4 = r12.mo16878z()     // Catch:{ Exception -> 0x00a7, all -> 0x00a2 }
        L_0x0014:
            boolean r5 = r4.moveToNext()     // Catch:{ Exception -> 0x00a0, all -> 0x009d }
            if (r5 == 0) goto L_0x0099
            android.content.ContentValues r5 = new android.content.ContentValues     // Catch:{ Exception -> 0x00a0, all -> 0x009d }
            r5.<init>()     // Catch:{ Exception -> 0x00a0, all -> 0x009d }
            com.yandex.metrica.impl.utils.C2220e.m5935a(r4, r5)     // Catch:{ Exception -> 0x00a0, all -> 0x009d }
            java.lang.String r6 = "id"
            java.lang.Long r6 = r5.getAsLong(r6)     // Catch:{ Exception -> 0x00a0, all -> 0x009d }
            long r6 = r6.longValue()     // Catch:{ Exception -> 0x00a0, all -> 0x009d }
            java.lang.String r8 = "type"
            java.lang.Integer r8 = r5.getAsInteger(r8)     // Catch:{ Exception -> 0x00a0, all -> 0x009d }
            com.yandex.metrica.impl.ob.bl r8 = com.yandex.metrica.impl.p050ob.C1971bl.m4964a(r8)     // Catch:{ Exception -> 0x00a0, all -> 0x009d }
            boolean r9 = r12.mo16873a((long) r6)     // Catch:{ Exception -> 0x00a0, all -> 0x009d }
            if (r9 != 0) goto L_0x0014
            com.yandex.metrica.c$a$f r5 = com.yandex.metrica.impl.C1842ap.m4264a((android.content.ContentValues) r5)     // Catch:{ Exception -> 0x00a0, all -> 0x009d }
            int r8 = com.yandex.metrica.impl.C1842ap.m4259a((com.yandex.metrica.impl.p050ob.C1971bl) r8)     // Catch:{ Exception -> 0x00a0, all -> 0x009d }
            com.yandex.metrica.impl.ba r9 = r12.f2985n     // Catch:{ Exception -> 0x00a0, all -> 0x009d }
            java.lang.String r9 = r9.mo17028w()     // Catch:{ Exception -> 0x00a0, all -> 0x009d }
            com.yandex.metrica.c$a$d$b r5 = com.yandex.metrica.impl.C1842ap.m4261a(r9, r8, r5)     // Catch:{ Exception -> 0x00a0, all -> 0x009d }
            int r8 = r12.f2989r     // Catch:{ Exception -> 0x00a0, all -> 0x009d }
            r9 = 1
            r10 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
            int r9 = com.yandex.metrica.impl.p050ob.C1957b.m4847c((int) r9, (long) r10)     // Catch:{ Exception -> 0x00a0, all -> 0x009d }
            int r8 = r8 + r9
            r12.f2989r = r8     // Catch:{ Exception -> 0x00a0, all -> 0x009d }
            r9 = 2
            int r9 = com.yandex.metrica.impl.p050ob.C1957b.m4842b((int) r9, (com.yandex.metrica.impl.p050ob.C2056d) r5)     // Catch:{ Exception -> 0x00a0, all -> 0x009d }
            int r8 = r8 + r9
            r12.f2989r = r8     // Catch:{ Exception -> 0x00a0, all -> 0x009d }
            r9 = 250880(0x3d400, float:3.51558E-40)
            if (r8 >= r9) goto L_0x0099
            com.yandex.metrica.impl.at$b r5 = r12.mo16871a((long) r6, (com.yandex.metrica.C1781c.C1782a.C1786d.C1792b) r5)     // Catch:{ Exception -> 0x00a0, all -> 0x009d }
            if (r5 == 0) goto L_0x0014
            if (r3 != 0) goto L_0x0075
            com.yandex.metrica.impl.a$a r3 = r5.f2997b     // Catch:{ Exception -> 0x00a0, all -> 0x009d }
            goto L_0x007d
        L_0x0075:
            com.yandex.metrica.impl.a$a r8 = r5.f2997b     // Catch:{ Exception -> 0x00a0, all -> 0x009d }
            boolean r8 = r3.equals(r8)     // Catch:{ Exception -> 0x00a0, all -> 0x009d }
            if (r8 == 0) goto L_0x0099
        L_0x007d:
            java.lang.Long r6 = java.lang.Long.valueOf(r6)     // Catch:{ Exception -> 0x00a0, all -> 0x009d }
            r1.add(r6)     // Catch:{ Exception -> 0x00a0, all -> 0x009d }
            com.yandex.metrica.c$a$d r6 = r5.f2996a     // Catch:{ Exception -> 0x00a0, all -> 0x009d }
            r0.add(r6)     // Catch:{ Exception -> 0x00a0, all -> 0x009d }
            org.json.JSONObject r6 = new org.json.JSONObject     // Catch:{ JSONException -> 0x0093 }
            com.yandex.metrica.impl.a$a r7 = r5.f2997b     // Catch:{ JSONException -> 0x0093 }
            java.lang.String r7 = r7.f2885a     // Catch:{ JSONException -> 0x0093 }
            r6.<init>(r7)     // Catch:{ JSONException -> 0x0093 }
            r2 = r6
        L_0x0093:
            boolean r5 = r5.f2998c     // Catch:{ Exception -> 0x00a0, all -> 0x009d }
            if (r5 != 0) goto L_0x0099
            goto L_0x0014
        L_0x0099:
            com.yandex.metrica.impl.C1897bk.m4643a((android.database.Cursor) r4)
            goto L_0x00aa
        L_0x009d:
            r0 = move-exception
            r3 = r4
            goto L_0x00a3
        L_0x00a0:
            r3 = r4
            goto L_0x00a7
        L_0x00a2:
            r0 = move-exception
        L_0x00a3:
            com.yandex.metrica.impl.C1897bk.m4643a((android.database.Cursor) r3)
            throw r0
        L_0x00a7:
            com.yandex.metrica.impl.C1897bk.m4643a((android.database.Cursor) r3)
        L_0x00aa:
            com.yandex.metrica.impl.at$c r3 = new com.yandex.metrica.impl.at$c
            r3.<init>(r0, r1, r2)
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.yandex.metrica.impl.C1857at.mo16876x():com.yandex.metrica.impl.at$c");
    }

    /* renamed from: a */
    private static int m4328a(C1807a.C1808a aVar) {
        try {
            C1781c.C1782a.C1783a[] a = m4329a(new JSONObject(aVar.f2885a));
            if (a == null) {
                return 0;
            }
            int i = 0;
            for (C1781c.C1782a.C1783a b : a) {
                i += C1957b.m4842b(7, (C2056d) b);
            }
            return i;
        } catch (JSONException unused) {
            return 0;
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x010b, code lost:
        r1 = r10;
        r12 = r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x0165, code lost:
        r11 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x0166, code lost:
        r1 = r10;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x0168, code lost:
        r12 = null;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x0165 A[ExcHandler: all (th java.lang.Throwable), Splitter:B:3:0x0015] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.yandex.metrica.impl.C1857at.C1860b mo16871a(long r10, com.yandex.metrica.C1781c.C1782a.C1786d.C1792b r12) {
        /*
            r9 = this;
            com.yandex.metrica.c$a$d r0 = new com.yandex.metrica.c$a$d
            r0.<init>()
            r0.f2785b = r10
            r0.f2786c = r12
            int r12 = r12.f2822d
            com.yandex.metrica.impl.ob.bl r12 = com.yandex.metrica.impl.C1842ap.m4266a((int) r12)
            r1 = 0
            r2 = 0
            android.database.Cursor r10 = r9.mo16869a((long) r10, (com.yandex.metrica.impl.p050ob.C1971bl) r12)     // Catch:{ Exception -> 0x0170, all -> 0x016b }
            java.util.ArrayList r11 = new java.util.ArrayList     // Catch:{ Exception -> 0x0168, all -> 0x0165 }
            r11.<init>()     // Catch:{ Exception -> 0x0168, all -> 0x0165 }
            r12 = r1
        L_0x001b:
            boolean r3 = r10.moveToNext()     // Catch:{ Exception -> 0x0169, all -> 0x0165 }
            if (r3 == 0) goto L_0x0149
            android.content.ContentValues r3 = new android.content.ContentValues     // Catch:{ Exception -> 0x0169, all -> 0x0165 }
            r3.<init>()     // Catch:{ Exception -> 0x0169, all -> 0x0165 }
            com.yandex.metrica.impl.utils.C2220e.m5935a(r10, r3)     // Catch:{ Exception -> 0x0169, all -> 0x0165 }
            java.lang.String r4 = "type"
            java.lang.Integer r4 = r3.getAsInteger(r4)     // Catch:{ Exception -> 0x0169, all -> 0x0165 }
            int r4 = r4.intValue()     // Catch:{ Exception -> 0x0169, all -> 0x0165 }
            boolean r5 = r9.f2993v     // Catch:{ Exception -> 0x0169, all -> 0x0165 }
            com.yandex.metrica.impl.ap$b r4 = com.yandex.metrica.impl.C1842ap.C1845b.m4276a(r4, r5)     // Catch:{ Exception -> 0x0169, all -> 0x0165 }
            java.lang.String r5 = "custom_type"
            java.lang.Integer r5 = r3.getAsInteger(r5)     // Catch:{ Exception -> 0x0169, all -> 0x0165 }
            com.yandex.metrica.impl.ap$b r4 = r4.mo16846b((java.lang.Integer) r5)     // Catch:{ Exception -> 0x0169, all -> 0x0165 }
            java.lang.String r5 = "name"
            java.lang.String r5 = r3.getAsString(r5)     // Catch:{ Exception -> 0x0169, all -> 0x0165 }
            com.yandex.metrica.impl.ap$b r4 = r4.mo16844a((java.lang.String) r5)     // Catch:{ Exception -> 0x0169, all -> 0x0165 }
            java.lang.String r5 = "value"
            java.lang.String r5 = r3.getAsString(r5)     // Catch:{ Exception -> 0x0169, all -> 0x0165 }
            com.yandex.metrica.impl.ap$b r4 = r4.mo16847b((java.lang.String) r5)     // Catch:{ Exception -> 0x0169, all -> 0x0165 }
            java.lang.String r5 = "time"
            java.lang.Long r5 = r3.getAsLong(r5)     // Catch:{ Exception -> 0x0169, all -> 0x0165 }
            long r5 = r5.longValue()     // Catch:{ Exception -> 0x0169, all -> 0x0165 }
            com.yandex.metrica.impl.ap$b r4 = r4.mo16842a((long) r5)     // Catch:{ Exception -> 0x0169, all -> 0x0165 }
            java.lang.String r5 = "number"
            java.lang.Integer r5 = r3.getAsInteger(r5)     // Catch:{ Exception -> 0x0169, all -> 0x0165 }
            int r5 = r5.intValue()     // Catch:{ Exception -> 0x0169, all -> 0x0165 }
            com.yandex.metrica.impl.ap$b r4 = r4.mo16841a((int) r5)     // Catch:{ Exception -> 0x0169, all -> 0x0165 }
            java.lang.String r5 = "cell_info"
            java.lang.String r5 = r3.getAsString(r5)     // Catch:{ Exception -> 0x0169, all -> 0x0165 }
            com.yandex.metrica.impl.ap$b r4 = r4.mo16855e(r5)     // Catch:{ Exception -> 0x0169, all -> 0x0165 }
            java.lang.String r5 = "location_info"
            java.lang.String r5 = r3.getAsString(r5)     // Catch:{ Exception -> 0x0169, all -> 0x0165 }
            com.yandex.metrica.impl.ap$b r4 = r4.mo16850c((java.lang.String) r5)     // Catch:{ Exception -> 0x0169, all -> 0x0165 }
            java.lang.String r5 = "wifi_network_info"
            java.lang.String r5 = r3.getAsString(r5)     // Catch:{ Exception -> 0x0169, all -> 0x0165 }
            com.yandex.metrica.impl.ap$b r4 = r4.mo16852d(r5)     // Catch:{ Exception -> 0x0169, all -> 0x0165 }
            java.lang.String r5 = "error_environment"
            java.lang.String r5 = r3.getAsString(r5)     // Catch:{ Exception -> 0x0169, all -> 0x0165 }
            com.yandex.metrica.impl.ap$b r4 = r4.mo16858g(r5)     // Catch:{ Exception -> 0x0169, all -> 0x0165 }
            java.lang.String r5 = "user_info"
            java.lang.String r5 = r3.getAsString(r5)     // Catch:{ Exception -> 0x0169, all -> 0x0165 }
            com.yandex.metrica.impl.ap$b r4 = r4.mo16859h(r5)     // Catch:{ Exception -> 0x0169, all -> 0x0165 }
            java.lang.String r5 = "truncated"
            java.lang.Integer r5 = r3.getAsInteger(r5)     // Catch:{ Exception -> 0x0169, all -> 0x0165 }
            int r5 = r5.intValue()     // Catch:{ Exception -> 0x0169, all -> 0x0165 }
            com.yandex.metrica.impl.ap$b r4 = r4.mo16845b((int) r5)     // Catch:{ Exception -> 0x0169, all -> 0x0165 }
            java.lang.String r5 = "connection_type"
            java.lang.Integer r5 = r3.getAsInteger(r5)     // Catch:{ Exception -> 0x0169, all -> 0x0165 }
            int r5 = r5.intValue()     // Catch:{ Exception -> 0x0169, all -> 0x0165 }
            com.yandex.metrica.impl.ap$b r4 = r4.mo16849c((int) r5)     // Catch:{ Exception -> 0x0169, all -> 0x0165 }
            java.lang.String r5 = "cellular_connection_type"
            java.lang.String r5 = r3.getAsString(r5)     // Catch:{ Exception -> 0x0169, all -> 0x0165 }
            com.yandex.metrica.impl.ap$b r4 = r4.mo16860i(r5)     // Catch:{ Exception -> 0x0169, all -> 0x0165 }
            java.lang.String r5 = "wifi_access_point"
            java.lang.String r5 = r3.getAsString(r5)     // Catch:{ Exception -> 0x0169, all -> 0x0165 }
            com.yandex.metrica.impl.ap$b r4 = r4.mo16857f(r5)     // Catch:{ Exception -> 0x0169, all -> 0x0165 }
            java.lang.Integer r5 = r4.mo16851c()     // Catch:{ Exception -> 0x0169, all -> 0x0165 }
            if (r5 == 0) goto L_0x00e0
            com.yandex.metrica.c$a$d$a r4 = r4.mo16854e()     // Catch:{ Exception -> 0x0169, all -> 0x0165 }
            goto L_0x00e1
        L_0x00e0:
            r4 = r1
        L_0x00e1:
            if (r4 == 0) goto L_0x001b
            com.yandex.metrica.impl.a$a r5 = new com.yandex.metrica.impl.a$a     // Catch:{ Exception -> 0x0169, all -> 0x0165 }
            java.lang.String r6 = "app_environment"
            java.lang.String r6 = r3.getAsString(r6)     // Catch:{ Exception -> 0x0169, all -> 0x0165 }
            java.lang.String r7 = "app_environment_revision"
            java.lang.Long r3 = r3.getAsLong(r7)     // Catch:{ Exception -> 0x0169, all -> 0x0165 }
            long r7 = r3.longValue()     // Catch:{ Exception -> 0x0169, all -> 0x0165 }
            r5.<init>(r6, r7)     // Catch:{ Exception -> 0x0169, all -> 0x0165 }
            if (r12 != 0) goto L_0x010e
            int r12 = r9.f2990s     // Catch:{ Exception -> 0x010b, all -> 0x0165 }
            if (r12 >= 0) goto L_0x0109
            int r12 = m4328a((com.yandex.metrica.impl.C1807a.C1808a) r5)     // Catch:{ Exception -> 0x010b, all -> 0x0165 }
            r9.f2990s = r12     // Catch:{ Exception -> 0x010b, all -> 0x0165 }
            int r3 = r9.f2989r     // Catch:{ Exception -> 0x010b, all -> 0x0165 }
            int r3 = r3 + r12
            r9.f2989r = r3     // Catch:{ Exception -> 0x010b, all -> 0x0165 }
        L_0x0109:
            r12 = r5
            goto L_0x0116
        L_0x010b:
            r1 = r10
            r12 = r5
            goto L_0x0171
        L_0x010e:
            boolean r3 = r12.equals(r5)     // Catch:{ Exception -> 0x0169, all -> 0x0165 }
            if (r3 != 0) goto L_0x0116
            r2 = 1
            goto L_0x0149
        L_0x0116:
            com.yandex.metrica.impl.utils.f r3 = r9.f2992u     // Catch:{ Exception -> 0x0169, all -> 0x0165 }
            byte[] r5 = r4.f2793f     // Catch:{ Exception -> 0x0169, all -> 0x0165 }
            r6 = 245760(0x3c000, float:3.44383E-40)
            byte[] r3 = r3.mo17916a((byte[]) r5, (int) r6)     // Catch:{ Exception -> 0x0169, all -> 0x0165 }
            byte[] r5 = r4.f2793f     // Catch:{ Exception -> 0x0169, all -> 0x0165 }
            boolean r5 = r5.equals(r3)     // Catch:{ Exception -> 0x0169, all -> 0x0165 }
            if (r5 != 0) goto L_0x0135
            r4.f2793f = r3     // Catch:{ Exception -> 0x0169, all -> 0x0165 }
            int r5 = r4.f2798k     // Catch:{ Exception -> 0x0169, all -> 0x0165 }
            byte[] r6 = r4.f2793f     // Catch:{ Exception -> 0x0169, all -> 0x0165 }
            int r6 = r6.length     // Catch:{ Exception -> 0x0169, all -> 0x0165 }
            int r3 = r3.length     // Catch:{ Exception -> 0x0169, all -> 0x0165 }
            int r6 = r6 - r3
            int r5 = r5 + r6
            r4.f2798k = r5     // Catch:{ Exception -> 0x0169, all -> 0x0165 }
        L_0x0135:
            int r3 = r9.f2989r     // Catch:{ Exception -> 0x0169, all -> 0x0165 }
            r5 = 3
            int r5 = com.yandex.metrica.impl.p050ob.C1957b.m4842b((int) r5, (com.yandex.metrica.impl.p050ob.C2056d) r4)     // Catch:{ Exception -> 0x0169, all -> 0x0165 }
            int r3 = r3 + r5
            r9.f2989r = r3     // Catch:{ Exception -> 0x0169, all -> 0x0165 }
            r5 = 250880(0x3d400, float:3.51558E-40)
            if (r3 >= r5) goto L_0x0149
            r11.add(r4)     // Catch:{ Exception -> 0x0169, all -> 0x0165 }
            goto L_0x001b
        L_0x0149:
            int r3 = r11.size()     // Catch:{ Exception -> 0x0169, all -> 0x0165 }
            if (r3 <= 0) goto L_0x0161
            int r1 = r11.size()     // Catch:{ Exception -> 0x0169, all -> 0x0165 }
            com.yandex.metrica.c$a$d$a[] r1 = new com.yandex.metrica.C1781c.C1782a.C1786d.C1787a[r1]     // Catch:{ Exception -> 0x0169, all -> 0x0165 }
            java.lang.Object[] r11 = r11.toArray(r1)     // Catch:{ Exception -> 0x0169, all -> 0x0165 }
            com.yandex.metrica.c$a$d$a[] r11 = (com.yandex.metrica.C1781c.C1782a.C1786d.C1787a[]) r11     // Catch:{ Exception -> 0x0169, all -> 0x0165 }
            r0.f2787d = r11     // Catch:{ Exception -> 0x0169, all -> 0x0165 }
            com.yandex.metrica.impl.C1897bk.m4643a((android.database.Cursor) r10)
            goto L_0x0174
        L_0x0161:
            com.yandex.metrica.impl.C1897bk.m4643a((android.database.Cursor) r10)
            return r1
        L_0x0165:
            r11 = move-exception
            r1 = r10
            goto L_0x016c
        L_0x0168:
            r12 = r1
        L_0x0169:
            r1 = r10
            goto L_0x0171
        L_0x016b:
            r11 = move-exception
        L_0x016c:
            com.yandex.metrica.impl.C1897bk.m4643a((android.database.Cursor) r1)
            throw r11
        L_0x0170:
            r12 = r1
        L_0x0171:
            com.yandex.metrica.impl.C1897bk.m4643a((android.database.Cursor) r1)
        L_0x0174:
            com.yandex.metrica.impl.at$b r10 = new com.yandex.metrica.impl.at$b
            r10.<init>(r0, r12, r2)
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.yandex.metrica.impl.C1857at.mo16871a(long, com.yandex.metrica.c$a$d$b):com.yandex.metrica.impl.at$b");
    }

    /* access modifiers changed from: protected */
    /* renamed from: y */
    public String mo16877y() {
        return this.f2985n.mo16969a();
    }

    /* access modifiers changed from: protected */
    /* renamed from: z */
    public Cursor mo16878z() {
        return this.f2986o.mo17266a((Map<String, String>) this.f3195b);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public Cursor mo16869a(long j, C1971bl blVar) {
        return this.f2986o.mo17273b(j, blVar);
    }

    /* renamed from: com.yandex.metrica.impl.at$c */
    static final class C1861c {

        /* renamed from: a */
        final List<C1781c.C1782a.C1786d> f2999a;

        /* renamed from: b */
        final List<Long> f3000b;

        /* renamed from: c */
        final JSONObject f3001c;

        C1861c(List<C1781c.C1782a.C1786d> list, List<Long> list2, JSONObject jSONObject) {
            this.f2999a = list;
            this.f3000b = list2;
            this.f3001c = jSONObject;
        }
    }

    /* renamed from: com.yandex.metrica.impl.at$b */
    static final class C1860b {

        /* renamed from: a */
        final C1781c.C1782a.C1786d f2996a;

        /* renamed from: b */
        final C1807a.C1808a f2997b;

        /* renamed from: c */
        final boolean f2998c;

        C1860b(C1781c.C1782a.C1786d dVar, C1807a.C1808a aVar, boolean z) {
            this.f2996a = dVar;
            this.f2997b = aVar;
            this.f2998c = z;
        }
    }

    /* renamed from: A */
    public static C1859a m4327A() {
        return new C1859a();
    }

    /* renamed from: com.yandex.metrica.impl.at$a */
    static class C1859a {
        C1859a() {
        }

        /* access modifiers changed from: package-private */
        /* renamed from: a */
        public C1857at mo16868a(C2198t tVar) {
            return new C1857at(tVar);
        }
    }
}

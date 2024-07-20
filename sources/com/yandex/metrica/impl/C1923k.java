package com.yandex.metrica.impl;

import android.content.ContentValues;
import android.content.Context;
import android.location.Location;
import android.text.TextUtils;
import com.yandex.metrica.impl.p050ob.C2200u;
import org.json.JSONObject;

/* renamed from: com.yandex.metrica.impl.k */
public final class C1923k {

    /* renamed from: a */
    private Context f3189a;
    /* access modifiers changed from: private */

    /* renamed from: b */
    public ContentValues f3190b;

    /* renamed from: c */
    private C2200u f3191c;

    public C1923k(Context context) {
        this.f3189a = context;
    }

    /* renamed from: a */
    public C1923k mo17145a(ContentValues contentValues) {
        this.f3190b = contentValues;
        return this;
    }

    /* renamed from: a */
    public C1923k mo17146a(C2200u uVar) {
        this.f3191c = uVar;
        return this;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public Location mo17150b() {
        if (!this.f3191c.mo17863j().mo16600m()) {
            return null;
        }
        Location t = this.f3191c.mo17863j().mo16607t();
        if (t != null) {
            return t;
        }
        Location c = C2241y.m5985a(this.f3189a).mo17941c();
        return c == null ? C2241y.m5985a(this.f3189a).mo17942d() : c;
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x00a9 A[SYNTHETIC, Splitter:B:12:0x00a9] */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x016c  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x0176  */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void mo17149a(com.yandex.metrica.impl.C1915h r7, com.yandex.metrica.impl.C1807a.C1808a r8) {
        /*
            r6 = this;
            android.content.ContentValues r0 = r6.f3190b
            java.lang.String r1 = r7.mo17115a()
            java.lang.String r2 = "name"
            r0.put(r2, r1)
            android.content.ContentValues r0 = r6.f3190b
            java.lang.String r1 = r7.mo17117b()
            java.lang.String r2 = "value"
            r0.put(r2, r1)
            android.content.ContentValues r0 = r6.f3190b
            int r1 = r7.mo17118c()
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            java.lang.String r2 = "type"
            r0.put(r2, r1)
            android.content.ContentValues r0 = r6.f3190b
            int r1 = r7.mo17120d()
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            java.lang.String r2 = "custom_type"
            r0.put(r2, r1)
            android.content.ContentValues r0 = r6.f3190b
            java.lang.String r1 = r7.mo17130i()
            java.lang.String r2 = "error_environment"
            r0.put(r2, r1)
            android.content.ContentValues r0 = r6.f3190b
            java.lang.String r1 = r7.mo17132k()
            java.lang.String r2 = "user_info"
            r0.put(r2, r1)
            android.content.ContentValues r0 = r6.f3190b
            int r1 = r7.mo17136o()
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            java.lang.String r2 = "truncated"
            r0.put(r2, r1)
            android.content.ContentValues r0 = r6.f3190b
            android.content.Context r1 = r6.f3189a
            java.lang.String r2 = "connectivity"
            java.lang.Object r2 = r1.getSystemService(r2)
            android.net.ConnectivityManager r2 = (android.net.ConnectivityManager) r2
            java.lang.String r3 = "android.permission.ACCESS_NETWORK_STATE"
            boolean r1 = com.yandex.metrica.impl.C1837al.m4248a(r1, r3)
            r3 = 1
            if (r1 == 0) goto L_0x0083
            android.net.NetworkInfo r1 = r2.getActiveNetworkInfo()
            if (r1 == 0) goto L_0x0083
            int r2 = r1.getType()
            if (r2 != r3) goto L_0x007b
            goto L_0x0084
        L_0x007b:
            int r1 = r1.getType()
            if (r1 != 0) goto L_0x0083
            r3 = 0
            goto L_0x0084
        L_0x0083:
            r3 = 2
        L_0x0084:
            java.lang.Integer r1 = java.lang.Integer.valueOf(r3)
            java.lang.String r2 = "connection_type"
            r0.put(r2, r1)
            android.content.ContentValues r0 = r6.f3190b
            java.lang.String r1 = r8.f2885a
            java.lang.String r2 = "app_environment"
            r0.put(r2, r1)
            android.content.ContentValues r0 = r6.f3190b
            long r1 = r8.f2886b
            java.lang.Long r8 = java.lang.Long.valueOf(r1)
            java.lang.String r1 = "app_environment_revision"
            r0.put(r1, r8)
            android.location.Location r8 = r6.mo17150b()
            if (r8 == 0) goto L_0x013c
            org.json.JSONObject r0 = new org.json.JSONObject     // Catch:{ Exception -> 0x013b }
            r0.<init>()     // Catch:{ Exception -> 0x013b }
            java.lang.String r1 = "lat"
            double r2 = r8.getLatitude()     // Catch:{ Exception -> 0x013b }
            r0.put(r1, r2)     // Catch:{ Exception -> 0x013b }
            java.lang.String r1 = "lon"
            double r2 = r8.getLongitude()     // Catch:{ Exception -> 0x013b }
            r0.put(r1, r2)     // Catch:{ Exception -> 0x013b }
            java.lang.String r1 = "timestamp"
            long r2 = r8.getTime()     // Catch:{ Exception -> 0x013b }
            java.lang.Long r2 = java.lang.Long.valueOf(r2)     // Catch:{ Exception -> 0x013b }
            r0.putOpt(r1, r2)     // Catch:{ Exception -> 0x013b }
            java.lang.String r1 = "precision"
            boolean r2 = r8.hasAccuracy()     // Catch:{ Exception -> 0x013b }
            r3 = 0
            if (r2 == 0) goto L_0x00df
            float r2 = r8.getAccuracy()     // Catch:{ Exception -> 0x013b }
            java.lang.Float r2 = java.lang.Float.valueOf(r2)     // Catch:{ Exception -> 0x013b }
            goto L_0x00e0
        L_0x00df:
            r2 = r3
        L_0x00e0:
            r0.putOpt(r1, r2)     // Catch:{ Exception -> 0x013b }
            java.lang.String r1 = "direction"
            boolean r2 = r8.hasBearing()     // Catch:{ Exception -> 0x013b }
            if (r2 == 0) goto L_0x00f4
            float r2 = r8.getBearing()     // Catch:{ Exception -> 0x013b }
            java.lang.Float r2 = java.lang.Float.valueOf(r2)     // Catch:{ Exception -> 0x013b }
            goto L_0x00f5
        L_0x00f4:
            r2 = r3
        L_0x00f5:
            r0.putOpt(r1, r2)     // Catch:{ Exception -> 0x013b }
            java.lang.String r1 = "speed"
            boolean r2 = r8.hasSpeed()     // Catch:{ Exception -> 0x013b }
            if (r2 == 0) goto L_0x0109
            float r2 = r8.getSpeed()     // Catch:{ Exception -> 0x013b }
            java.lang.Float r2 = java.lang.Float.valueOf(r2)     // Catch:{ Exception -> 0x013b }
            goto L_0x010a
        L_0x0109:
            r2 = r3
        L_0x010a:
            r0.putOpt(r1, r2)     // Catch:{ Exception -> 0x013b }
            java.lang.String r1 = "altitude"
            boolean r2 = r8.hasAltitude()     // Catch:{ Exception -> 0x013b }
            if (r2 == 0) goto L_0x011e
            double r4 = r8.getAltitude()     // Catch:{ Exception -> 0x013b }
            java.lang.Double r2 = java.lang.Double.valueOf(r4)     // Catch:{ Exception -> 0x013b }
            goto L_0x011f
        L_0x011e:
            r2 = r3
        L_0x011f:
            r0.putOpt(r1, r2)     // Catch:{ Exception -> 0x013b }
            java.lang.String r1 = "provider"
            java.lang.String r8 = r8.getProvider()     // Catch:{ Exception -> 0x013b }
            java.lang.String r8 = com.yandex.metrica.impl.C1894bi.m4628c(r8, r3)     // Catch:{ Exception -> 0x013b }
            r0.putOpt(r1, r8)     // Catch:{ Exception -> 0x013b }
            android.content.ContentValues r8 = r6.f3190b     // Catch:{ Exception -> 0x013b }
            java.lang.String r1 = "location_info"
            java.lang.String r0 = r0.toString()     // Catch:{ Exception -> 0x013b }
            r8.put(r1, r0)     // Catch:{ Exception -> 0x013b }
            goto L_0x013c
        L_0x013b:
        L_0x013c:
            android.content.Context r8 = r6.f3189a
            com.yandex.metrica.impl.ob.ef r8 = com.yandex.metrica.impl.p050ob.C2117ef.m5566a((android.content.Context) r8)
            com.yandex.metrica.impl.k$2 r0 = new com.yandex.metrica.impl.k$2
            r0.<init>()
            r8.mo17675a((com.yandex.metrica.impl.p050ob.C2119eh) r0)
            com.yandex.metrica.impl.k$1 r0 = new com.yandex.metrica.impl.k$1
            r0.<init>()
            r8.mo17674a((com.yandex.metrica.impl.p050ob.C2107ea) r0)
            android.content.Context r8 = r6.f3189a
            com.yandex.metrica.impl.bm r8 = com.yandex.metrica.impl.C1899bm.m4659a((android.content.Context) r8)
            org.json.JSONArray r7 = r7.mo17128g()
            org.json.JSONArray r0 = r8.mo17084a()
            int r1 = r0.length()
            int r2 = r7.length()
            java.lang.String r3 = "wifi_network_info"
            if (r1 <= r2) goto L_0x0176
            android.content.ContentValues r7 = r6.f3190b
            java.lang.String r0 = r0.toString()
            r7.put(r3, r0)
            goto L_0x017f
        L_0x0176:
            android.content.ContentValues r0 = r6.f3190b
            java.lang.String r7 = r7.toString()
            r0.put(r3, r7)
        L_0x017f:
            r6.mo17148a((com.yandex.metrica.impl.C1899bm) r8)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.yandex.metrica.impl.C1923k.mo17149a(com.yandex.metrica.impl.h, com.yandex.metrica.impl.a$a):void");
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo17148a(C1899bm bmVar) {
        String b = bmVar.mo17085b(this.f3189a);
        if (!TextUtils.isEmpty(b)) {
            int c = bmVar.mo17087c(this.f3189a);
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("ssid", b);
                jSONObject.put("state", c);
                this.f3190b.put("wifi_access_point", jSONObject.toString());
            } catch (Exception unused) {
            }
        }
    }

    /* renamed from: a */
    public void mo17147a() {
        C1877ba h = this.f3191c.mo17860h();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.putOpt("dId", h.mo16986c());
            jSONObject.putOpt("uId", h.mo16981b());
            jSONObject.putOpt("appVer", h.mo17029x());
            jSONObject.putOpt("appBuild", h.mo17031z());
            jSONObject.putOpt("kitVer", h.mo17001h());
            jSONObject.putOpt("clientKitVer", h.mo17003i());
            jSONObject.putOpt("kitBuildNumber", h.mo17007k());
            jSONObject.putOpt("kitBuildType", h.mo17009l());
            jSONObject.putOpt("osVer", h.mo17019q());
            jSONObject.putOpt("osApiLev", Integer.valueOf(h.mo17021r()));
            jSONObject.putOpt("lang", h.mo17028w());
            jSONObject.putOpt("root", h.mo16960F());
            jSONObject.putOpt("app_debuggable", h.mo16968N());
        } catch (Exception unused) {
            jSONObject = new JSONObject();
        }
        this.f3190b.put("report_request_parameters", jSONObject.toString());
    }
}

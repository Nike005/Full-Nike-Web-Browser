package com.startapp.android.publish.common.metaData;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.startapp.android.publish.ads.banner.C0901c;
import com.startapp.android.publish.ads.splash.C0949f;
import com.startapp.android.publish.adsCommon.AdsConstants;
import com.startapp.android.publish.adsCommon.C1098b;
import com.startapp.android.publish.adsCommon.Utils.C1061i;
import com.startapp.android.publish.adsCommon.adinformation.C1083a;
import com.startapp.android.publish.adsCommon.p033f.C1130d;
import com.startapp.android.publish.adsCommon.p033f.C1131e;
import com.startapp.android.publish.adsCommon.p033f.C1132f;
import com.startapp.android.publish.adsCommon.p042k.C1167a;
import com.startapp.android.publish.cache.C1196d;
import com.startapp.android.publish.common.metaData.MetaDataRequest;
import com.startapp.android.publish.common.model.AdPreferences;
import com.startapp.common.C1303g;
import com.startapp.common.p043a.C1270g;
import java.net.UnknownHostException;
import java.util.Map;

/* renamed from: com.startapp.android.publish.common.metaData.c */
/* compiled from: StartAppSDK */
public class C1228c {

    /* renamed from: a */
    private final Context f1397a;

    /* renamed from: b */
    private final AdPreferences f1398b;

    /* renamed from: c */
    private MetaDataRequest.C1224a f1399c;

    /* renamed from: d */
    private MetaData f1400d = null;

    /* renamed from: e */
    private C0901c f1401e = null;

    /* renamed from: f */
    private C0949f f1402f = null;

    /* renamed from: g */
    private C1196d f1403g = null;

    /* renamed from: h */
    private C1083a f1404h = null;

    /* renamed from: i */
    private C1098b f1405i = null;

    /* renamed from: j */
    private boolean f1406j = false;

    public C1228c(Context context, AdPreferences adPreferences, MetaDataRequest.C1224a aVar) {
        this.f1397a = context;
        this.f1398b = adPreferences;
        this.f1399c = aVar;
    }

    /* renamed from: a */
    public void mo15245a() {
        C1303g.m2206a(C1303g.C1307a.HIGH, (Runnable) new Runnable() {
            public void run() {
                final Boolean c = C1228c.this.mo15248c();
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    public void run() {
                        C1228c.this.mo15246a(c);
                    }
                });
            }
        });
    }

    /* renamed from: b */
    public void mo15247b() {
        this.f1406j = true;
    }

    /* access modifiers changed from: protected */
    /* renamed from: c */
    public Boolean mo15248c() {
        C1270g.m2076a(3, "Loading MetaData");
        MetaDataRequest metaDataRequest = new MetaDataRequest(this.f1397a, this.f1399c);
        try {
            metaDataRequest.fillApplicationDetails(this.f1397a, this.f1398b, false);
            metaDataRequest.fillLocationDetails(this.f1398b, this.f1397a);
            C1270g.m2076a(3, "Networking MetaData");
            String a = C1167a.m1619a(this.f1397a, AdsConstants.m1127a(AdsConstants.ServiceApiType.METADATA), metaDataRequest, (Map<String, String>) null).mo15466a();
            this.f1400d = (MetaData) C1061i.m1176a(a, MetaData.class);
            if (!C1061i.m1193a()) {
                this.f1405i = (C1098b) C1061i.m1176a(a, C1098b.class);
                if (C1061i.m1194a(16) || C1061i.m1194a(32)) {
                    this.f1401e = (C0901c) C1061i.m1176a(a, C0901c.class);
                }
                if (C1061i.m1194a(8)) {
                    this.f1402f = (C0949f) C1061i.m1176a(a, C0949f.class);
                }
                if (C1061i.m1194a(512)) {
                    this.f1403g = (C1196d) C1061i.m1176a(a, C1196d.class);
                }
                if (C1061i.m1206e()) {
                    this.f1404h = (C1083a) C1061i.m1176a(a, C1083a.class);
                }
            }
            m1900d();
            return Boolean.TRUE;
        } catch (Exception e) {
            C1270g.m2077a(6, "Unable to handle GetMetaData command!!!!", (Throwable) e);
            if (!(e instanceof UnknownHostException) || !e.getMessage().contains("init.startappservice.com")) {
                C1132f.m1527a(this.f1397a, C1130d.EXCEPTION, "GetMetaDataAsync.doInBackground - MetaData request failed", e.getMessage(), "");
            }
            return Boolean.FALSE;
        }
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(5:2|3|(4:9|(10:11|12|17|(2:21|22)|27|(2:29|30)|35|(2:37|38)|43|(2:45|46))|51|52)|53|54) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:53:0x00a2 */
    /* renamed from: d */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void m1900d() {
        /*
            r3 = this;
            java.lang.Object r0 = com.startapp.android.publish.common.metaData.MetaData.getLock()
            monitor-enter(r0)
            boolean r1 = r3.f1406j     // Catch:{ all -> 0x00a4 }
            if (r1 != 0) goto L_0x00a2
            com.startapp.android.publish.common.metaData.MetaData r1 = r3.f1400d     // Catch:{ all -> 0x00a4 }
            if (r1 == 0) goto L_0x00a2
            android.content.Context r1 = r3.f1397a     // Catch:{ all -> 0x00a4 }
            if (r1 == 0) goto L_0x00a2
            boolean r1 = com.startapp.android.publish.adsCommon.Utils.C1061i.m1193a()     // Catch:{ all -> 0x00a4 }
            if (r1 != 0) goto L_0x0097
            android.content.Context r1 = r3.f1397a     // Catch:{ Exception -> 0x001f }
            com.startapp.android.publish.adsCommon.b r2 = r3.f1405i     // Catch:{ Exception -> 0x001f }
            com.startapp.android.publish.adsCommon.C1098b.m1305a(r1, r2)     // Catch:{ Exception -> 0x001f }
            goto L_0x0029
        L_0x001f:
            r1 = move-exception
            java.lang.String r2 = "GetMetaDataAsyncTask-adscommon update fail"
            java.lang.String r1 = r1.getMessage()     // Catch:{ all -> 0x00a4 }
            r3.m1899a(r2, r1)     // Catch:{ all -> 0x00a4 }
        L_0x0029:
            r1 = 16
            boolean r1 = com.startapp.android.publish.adsCommon.Utils.C1061i.m1194a((long) r1)     // Catch:{ all -> 0x00a4 }
            if (r1 != 0) goto L_0x0039
            r1 = 32
            boolean r1 = com.startapp.android.publish.adsCommon.Utils.C1061i.m1194a((long) r1)     // Catch:{ all -> 0x00a4 }
            if (r1 == 0) goto L_0x004b
        L_0x0039:
            android.content.Context r1 = r3.f1397a     // Catch:{ Exception -> 0x0041 }
            com.startapp.android.publish.ads.banner.c r2 = r3.f1401e     // Catch:{ Exception -> 0x0041 }
            com.startapp.android.publish.ads.banner.C0901c.m629a(r1, r2)     // Catch:{ Exception -> 0x0041 }
            goto L_0x004b
        L_0x0041:
            r1 = move-exception
            java.lang.String r2 = "GetMetaDataAsyncTask-banner update fail"
            java.lang.String r1 = r1.getMessage()     // Catch:{ all -> 0x00a4 }
            r3.m1899a(r2, r1)     // Catch:{ all -> 0x00a4 }
        L_0x004b:
            r1 = 8
            boolean r1 = com.startapp.android.publish.adsCommon.Utils.C1061i.m1194a((long) r1)     // Catch:{ all -> 0x00a4 }
            if (r1 == 0) goto L_0x0065
            android.content.Context r1 = r3.f1397a     // Catch:{ Exception -> 0x005b }
            com.startapp.android.publish.ads.splash.f r2 = r3.f1402f     // Catch:{ Exception -> 0x005b }
            com.startapp.android.publish.ads.splash.C0949f.m789a(r1, r2)     // Catch:{ Exception -> 0x005b }
            goto L_0x0065
        L_0x005b:
            r1 = move-exception
            java.lang.String r2 = "GetMetaDataAsyncTask-splash update fail"
            java.lang.String r1 = r1.getMessage()     // Catch:{ all -> 0x00a4 }
            r3.m1899a(r2, r1)     // Catch:{ all -> 0x00a4 }
        L_0x0065:
            r1 = 512(0x200, double:2.53E-321)
            boolean r1 = com.startapp.android.publish.adsCommon.Utils.C1061i.m1194a((long) r1)     // Catch:{ all -> 0x00a4 }
            if (r1 == 0) goto L_0x007f
            android.content.Context r1 = r3.f1397a     // Catch:{ Exception -> 0x0075 }
            com.startapp.android.publish.cache.d r2 = r3.f1403g     // Catch:{ Exception -> 0x0075 }
            com.startapp.android.publish.cache.C1196d.m1805a(r1, r2)     // Catch:{ Exception -> 0x0075 }
            goto L_0x007f
        L_0x0075:
            r1 = move-exception
            java.lang.String r2 = "GetMetaDataAsyncTask-cache update fail"
            java.lang.String r1 = r1.getMessage()     // Catch:{ all -> 0x00a4 }
            r3.m1899a(r2, r1)     // Catch:{ all -> 0x00a4 }
        L_0x007f:
            boolean r1 = com.startapp.android.publish.adsCommon.Utils.C1061i.m1206e()     // Catch:{ all -> 0x00a4 }
            if (r1 == 0) goto L_0x0097
            android.content.Context r1 = r3.f1397a     // Catch:{ Exception -> 0x008d }
            com.startapp.android.publish.adsCommon.adinformation.a r2 = r3.f1404h     // Catch:{ Exception -> 0x008d }
            com.startapp.android.publish.adsCommon.adinformation.C1083a.m1255a(r1, r2)     // Catch:{ Exception -> 0x008d }
            goto L_0x0097
        L_0x008d:
            r1 = move-exception
            java.lang.String r2 = "GetMetaDataAsyncTask-adInfo update fail"
            java.lang.String r1 = r1.getMessage()     // Catch:{ all -> 0x00a4 }
            r3.m1899a(r2, r1)     // Catch:{ all -> 0x00a4 }
        L_0x0097:
            android.content.Context r1 = r3.f1397a     // Catch:{ Exception -> 0x00a2 }
            com.startapp.android.publish.common.metaData.MetaData r2 = r3.f1400d     // Catch:{ Exception -> 0x00a2 }
            java.lang.String r2 = r2.getAssetsBaseUrl()     // Catch:{ Exception -> 0x00a2 }
            com.startapp.android.publish.common.metaData.MetaData.preCacheResources(r1, r2)     // Catch:{ Exception -> 0x00a2 }
        L_0x00a2:
            monitor-exit(r0)     // Catch:{ all -> 0x00a4 }
            return
        L_0x00a4:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x00a4 }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.startapp.android.publish.common.metaData.C1228c.m1900d():void");
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo15246a(Boolean bool) {
        synchronized (MetaData.getLock()) {
            if (!this.f1406j) {
                if (!bool.booleanValue() || this.f1400d == null || this.f1397a == null) {
                    MetaData.failedLoading();
                } else {
                    try {
                        MetaData.update(this.f1397a, this.f1400d);
                    } catch (Exception e) {
                        m1899a("GetMetaDataAsyncTask.onPostExecute-metadata update fail", e.getMessage());
                    }
                }
            }
        }
    }

    /* renamed from: a */
    private void m1899a(String str, String str2) {
        C1132f.m1529a(this.f1397a, new C1131e(C1130d.EXCEPTION, str, str2), "");
    }
}

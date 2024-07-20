package com.startapp.android.publish.cache;

import android.app.Activity;
import android.content.Context;
import com.startapp.android.publish.ads.p018b.C0875d;
import com.startapp.android.publish.ads.p018b.C0876e;
import com.startapp.android.publish.ads.p019c.p020a.C0904b;
import com.startapp.android.publish.ads.p019c.p021b.C0906b;
import com.startapp.android.publish.ads.splash.C0939b;
import com.startapp.android.publish.ads.video.C1008e;
import com.startapp.android.publish.adsCommon.AdsConstants;
import com.startapp.android.publish.adsCommon.C1040Ad;
import com.startapp.android.publish.adsCommon.C1067a;
import com.startapp.android.publish.adsCommon.C1098b;
import com.startapp.android.publish.adsCommon.C1136g;
import com.startapp.android.publish.adsCommon.StartAppAd;
import com.startapp.android.publish.adsCommon.Utils.C1061i;
import com.startapp.android.publish.adsCommon.adListeners.AdEventListener;
import com.startapp.android.publish.adsCommon.adListeners.C1080b;
import com.startapp.android.publish.adsCommon.p029b.C1102c;
import com.startapp.android.publish.cache.C1207i;
import com.startapp.android.publish.common.metaData.MetaData;
import com.startapp.android.publish.common.model.AdPreferences;
import com.startapp.common.p043a.C1270g;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

/* renamed from: com.startapp.android.publish.cache.g */
/* compiled from: StartAppSDK */
public class C1200g {

    /* renamed from: h */
    public static boolean f1339h = false;

    /* renamed from: a */
    protected C1136g f1340a;

    /* renamed from: b */
    protected AtomicBoolean f1341b;

    /* renamed from: c */
    protected long f1342c;

    /* renamed from: d */
    protected C1199f f1343d;

    /* renamed from: e */
    protected C1194b f1344e;

    /* renamed from: f */
    protected Map<AdEventListener, List<StartAppAd>> f1345f;

    /* renamed from: g */
    protected C1205b f1346g;
    /* access modifiers changed from: private */

    /* renamed from: i */
    public final AdPreferences.Placement f1347i;

    /* renamed from: j */
    private Context f1348j;

    /* renamed from: k */
    private C1067a f1349k;

    /* renamed from: l */
    private AdPreferences f1350l;

    /* renamed from: m */
    private String f1351m;

    /* renamed from: n */
    private boolean f1352n;

    /* renamed from: o */
    private int f1353o;

    /* renamed from: p */
    private boolean f1354p;

    /* renamed from: com.startapp.android.publish.cache.g$b */
    /* compiled from: StartAppSDK */
    public interface C1205b {
        /* renamed from: a */
        void mo15079a(C1200g gVar);
    }

    /* renamed from: com.startapp.android.publish.cache.g$a */
    /* compiled from: StartAppSDK */
    class C1204a implements AdEventListener {

        /* renamed from: b */
        private boolean f1360b = false;

        /* renamed from: c */
        private boolean f1361c = false;

        C1204a() {
        }

        public void onReceiveAd(C1040Ad ad) {
            List<StartAppAd> list;
            boolean z = C1200g.this.f1340a != null && C1200g.this.f1340a.getVideoCancelCallBack();
            if (!this.f1360b && !z) {
                this.f1360b = true;
                synchronized (C1200g.this.f1345f) {
                    for (AdEventListener next : C1200g.this.f1345f.keySet()) {
                        if (!(next == null || (list = C1200g.this.f1345f.get(next)) == null)) {
                            for (StartAppAd startAppAd : list) {
                                startAppAd.setErrorMessage(ad.getErrorMessage());
                                new C1080b(next).onReceiveAd(startAppAd);
                            }
                        }
                    }
                    C1200g.this.f1345f.clear();
                }
            }
            C1200g.this.f1343d.mo15091f();
            C1200g.this.f1344e.mo15080a();
            C1200g.this.f1341b.set(false);
        }

        public void onFailedToReceiveAd(C1040Ad ad) {
            List<StartAppAd> list;
            ConcurrentHashMap concurrentHashMap;
            ConcurrentHashMap concurrentHashMap2 = null;
            if (!this.f1361c) {
                synchronized (C1200g.this.f1345f) {
                    concurrentHashMap = new ConcurrentHashMap(C1200g.this.f1345f);
                    C1200g.this.f1340a = null;
                    C1200g.this.f1345f.clear();
                }
                concurrentHashMap2 = concurrentHashMap;
            }
            if (concurrentHashMap2 != null) {
                for (AdEventListener adEventListener : concurrentHashMap2.keySet()) {
                    if (!(adEventListener == null || (list = (List) concurrentHashMap2.get(adEventListener)) == null)) {
                        for (StartAppAd startAppAd : list) {
                            startAppAd.setErrorMessage(ad.getErrorMessage());
                            new C1080b(adEventListener).onFailedToReceiveAd(startAppAd);
                        }
                    }
                }
            }
            this.f1361c = true;
            C1200g.this.f1344e.mo15091f();
            C1200g.this.f1343d.mo15080a();
            C1200g.this.f1341b.set(false);
        }
    }

    public C1200g(Context context, AdPreferences.Placement placement, AdPreferences adPreferences) {
        this.f1340a = null;
        this.f1341b = new AtomicBoolean(false);
        this.f1351m = null;
        this.f1352n = false;
        this.f1343d = null;
        this.f1344e = null;
        this.f1345f = new ConcurrentHashMap();
        this.f1354p = true;
        this.f1347i = placement;
        this.f1350l = adPreferences;
        m1824a(context);
        m1826o();
    }

    public C1200g(Context context, AdPreferences.Placement placement, AdPreferences adPreferences, boolean z) {
        this(context, placement, adPreferences);
        this.f1354p = z;
    }

    /* renamed from: a */
    private void m1824a(Context context) {
        if (context instanceof Activity) {
            this.f1348j = context.getApplicationContext();
            this.f1349k = new C1067a((Activity) context);
            return;
        }
        this.f1348j = context;
        this.f1349k = null;
    }

    /* renamed from: o */
    private void m1826o() {
        this.f1343d = new C1199f(this);
        this.f1344e = new C1194b(this);
    }

    /* renamed from: a */
    public AdPreferences mo15096a() {
        return this.f1350l;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo15100a(AdPreferences adPreferences) {
        this.f1350l = adPreferences;
    }

    /* renamed from: b */
    public C1136g mo15103b() {
        return this.f1340a;
    }

    /* access modifiers changed from: protected */
    /* renamed from: c */
    public AdPreferences.Placement mo15105c() {
        return this.f1347i;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo15101a(String str) {
        this.f1351m = str;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo15102a(boolean z) {
        this.f1352n = z;
    }

    /* renamed from: a */
    public void mo15098a(StartAppAd startAppAd, AdEventListener adEventListener) {
        m1825a(startAppAd, adEventListener, false);
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public void mo15104b(boolean z) {
        m1825a((StartAppAd) null, (AdEventListener) null, z);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:30:0x008a, code lost:
        return;
     */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x001a A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0065  */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void m1825a(com.startapp.android.publish.adsCommon.StartAppAd r6, com.startapp.android.publish.adsCommon.adListeners.AdEventListener r7, boolean r8) {
        /*
            r5 = this;
            java.util.Map<com.startapp.android.publish.adsCommon.adListeners.AdEventListener, java.util.List<com.startapp.android.publish.adsCommon.StartAppAd>> r0 = r5.f1345f
            monitor-enter(r0)
            boolean r1 = r5.mo15109g()     // Catch:{ all -> 0x008b }
            r2 = 0
            r3 = 1
            if (r1 == 0) goto L_0x0016
            boolean r1 = r5.m1832u()     // Catch:{ all -> 0x008b }
            if (r1 != 0) goto L_0x0016
            if (r8 == 0) goto L_0x0014
            goto L_0x0016
        L_0x0014:
            r8 = 0
            goto L_0x0017
        L_0x0016:
            r8 = 1
        L_0x0017:
            r1 = 3
            if (r8 == 0) goto L_0x0065
            if (r6 == 0) goto L_0x0035
            if (r7 == 0) goto L_0x0035
            java.util.Map<com.startapp.android.publish.adsCommon.adListeners.AdEventListener, java.util.List<com.startapp.android.publish.adsCommon.StartAppAd>> r8 = r5.f1345f     // Catch:{ all -> 0x008b }
            java.lang.Object r8 = r8.get(r7)     // Catch:{ all -> 0x008b }
            java.util.List r8 = (java.util.List) r8     // Catch:{ all -> 0x008b }
            if (r8 != 0) goto L_0x0032
            java.util.ArrayList r8 = new java.util.ArrayList     // Catch:{ all -> 0x008b }
            r8.<init>()     // Catch:{ all -> 0x008b }
            java.util.Map<com.startapp.android.publish.adsCommon.adListeners.AdEventListener, java.util.List<com.startapp.android.publish.adsCommon.StartAppAd>> r4 = r5.f1345f     // Catch:{ all -> 0x008b }
            r4.put(r7, r8)     // Catch:{ all -> 0x008b }
        L_0x0032:
            r8.add(r6)     // Catch:{ all -> 0x008b }
        L_0x0035:
            java.util.concurrent.atomic.AtomicBoolean r6 = r5.f1341b     // Catch:{ all -> 0x008b }
            boolean r6 = r6.compareAndSet(r2, r3)     // Catch:{ all -> 0x008b }
            if (r6 == 0) goto L_0x004b
            com.startapp.android.publish.cache.f r6 = r5.f1343d     // Catch:{ all -> 0x008b }
            r6.mo15092g()     // Catch:{ all -> 0x008b }
            com.startapp.android.publish.cache.b r6 = r5.f1344e     // Catch:{ all -> 0x008b }
            r6.mo15092g()     // Catch:{ all -> 0x008b }
            r5.m1827p()     // Catch:{ all -> 0x008b }
            goto L_0x0089
        L_0x004b:
            java.lang.String r6 = "CachedAd"
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ all -> 0x008b }
            r7.<init>()     // Catch:{ all -> 0x008b }
            com.startapp.android.publish.common.model.AdPreferences$Placement r8 = r5.f1347i     // Catch:{ all -> 0x008b }
            r7.append(r8)     // Catch:{ all -> 0x008b }
            java.lang.String r8 = " ad is currently loading"
            r7.append(r8)     // Catch:{ all -> 0x008b }
            java.lang.String r7 = r7.toString()     // Catch:{ all -> 0x008b }
            com.startapp.common.p043a.C1270g.m2078a((java.lang.String) r6, (int) r1, (java.lang.String) r7)     // Catch:{ all -> 0x008b }
            monitor-exit(r0)     // Catch:{ all -> 0x008b }
            return
        L_0x0065:
            java.lang.String r8 = "CachedAd"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x008b }
            r2.<init>()     // Catch:{ all -> 0x008b }
            com.startapp.android.publish.common.model.AdPreferences$Placement r3 = r5.f1347i     // Catch:{ all -> 0x008b }
            r2.append(r3)     // Catch:{ all -> 0x008b }
            java.lang.String r3 = " ad already loaded"
            r2.append(r3)     // Catch:{ all -> 0x008b }
            java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x008b }
            com.startapp.common.p043a.C1270g.m2078a((java.lang.String) r8, (int) r1, (java.lang.String) r2)     // Catch:{ all -> 0x008b }
            if (r6 == 0) goto L_0x0089
            if (r7 == 0) goto L_0x0089
            com.startapp.android.publish.adsCommon.adListeners.b r8 = new com.startapp.android.publish.adsCommon.adListeners.b     // Catch:{ all -> 0x008b }
            r8.<init>(r7)     // Catch:{ all -> 0x008b }
            r8.onReceiveAd(r6)     // Catch:{ all -> 0x008b }
        L_0x0089:
            monitor-exit(r0)     // Catch:{ all -> 0x008b }
            return
        L_0x008b:
            r6 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x008b }
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.startapp.android.publish.cache.C1200g.m1825a(com.startapp.android.publish.adsCommon.StartAppAd, com.startapp.android.publish.adsCommon.adListeners.AdEventListener, boolean):void");
    }

    /* access modifiers changed from: protected */
    /* renamed from: d */
    public void mo15106d() {
        C1270g.m2078a("CachedAd", 4, "Invalidating: " + this.f1347i);
        if (mo15109g()) {
            if (C1102c.m1363a(this.f1348j, (C1040Ad) this.f1340a) || m1832u()) {
                C1270g.m2078a("CachedAd", 3, "App present or cache TTL passed, reloading");
                mo15104b(true);
            } else if (!this.f1341b.get()) {
                this.f1343d.mo15091f();
            }
        } else if (!this.f1341b.get()) {
            this.f1344e.mo15091f();
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: e */
    public void mo15107e() {
        this.f1344e.mo15093h();
    }

    /* access modifiers changed from: protected */
    /* renamed from: f */
    public void mo15108f() {
        this.f1343d.mo15093h();
    }

    /* renamed from: g */
    public boolean mo15109g() {
        C1136g gVar = this.f1340a;
        return gVar != null && gVar.isReady();
    }

    /* renamed from: h */
    public boolean mo15110h() {
        return this.f1354p;
    }

    /* renamed from: i */
    public C1136g mo15111i() {
        if (!mo15109g()) {
            return null;
        }
        C1136g gVar = this.f1340a;
        mo15115m();
        if (!AdsConstants.OVERRIDE_NETWORK.booleanValue() && mo15110h()) {
            C1270g.m2078a("CachedAd", 3, "Ad shown, reloading " + this.f1347i);
            mo15104b(true);
            return gVar;
        } else if (mo15110h()) {
            return gVar;
        } else {
            C1205b bVar = this.f1346g;
            if (bVar != null) {
                bVar.mo15079a(this);
            }
            C1199f fVar = this.f1343d;
            if (fVar == null) {
                return gVar;
            }
            fVar.mo15080a();
            return gVar;
        }
    }

    /* renamed from: com.startapp.android.publish.cache.g$3 */
    /* compiled from: StartAppSDK */
    static /* synthetic */ class C12033 {

        /* renamed from: a */
        static final /* synthetic */ int[] f1358a;

        /* JADX WARNING: Can't wrap try/catch for region: R(12:0|1|2|3|4|5|6|7|8|9|10|12) */
        /* JADX WARNING: Code restructure failed: missing block: B:13:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                com.startapp.android.publish.common.model.AdPreferences$Placement[] r0 = com.startapp.android.publish.common.model.AdPreferences.Placement.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                f1358a = r0
                com.startapp.android.publish.common.model.AdPreferences$Placement r1 = com.startapp.android.publish.common.model.AdPreferences.Placement.INAPP_FULL_SCREEN     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = f1358a     // Catch:{ NoSuchFieldError -> 0x001d }
                com.startapp.android.publish.common.model.AdPreferences$Placement r1 = com.startapp.android.publish.common.model.AdPreferences.Placement.INAPP_OVERLAY     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = f1358a     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.startapp.android.publish.common.model.AdPreferences$Placement r1 = com.startapp.android.publish.common.model.AdPreferences.Placement.INAPP_OFFER_WALL     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = f1358a     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.startapp.android.publish.common.model.AdPreferences$Placement r1 = com.startapp.android.publish.common.model.AdPreferences.Placement.INAPP_RETURN     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = f1358a     // Catch:{ NoSuchFieldError -> 0x003e }
                com.startapp.android.publish.common.model.AdPreferences$Placement r1 = com.startapp.android.publish.common.model.AdPreferences.Placement.INAPP_SPLASH     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.startapp.android.publish.cache.C1200g.C12033.<clinit>():void");
        }
    }

    /* renamed from: j */
    public C1136g mo15112j() {
        C1136g gVar;
        C1061i.m1184a(this.f1348j, this.f1350l);
        int i = C12033.f1358a[this.f1347i.ordinal()];
        if (i == 1) {
            gVar = new C0875d(this.f1348j);
        } else if (i != 2) {
            if (i == 3) {
                gVar = m1833v();
            } else if (i == 4) {
                gVar = new C0876e(this.f1348j);
            } else if (i != 5) {
                gVar = new C0875d(this.f1348j);
            } else {
                gVar = new C0939b(this.f1348j);
            }
        } else if (C1061i.m1194a(4)) {
            gVar = new C1008e(this.f1348j);
        } else {
            gVar = new C0875d(this.f1348j);
        }
        C1270g.m2078a("CachedAd", 4, "ad Type: [" + gVar.getClass().toString() + "]");
        return gVar;
    }

    /* renamed from: p */
    private void m1827p() {
        C1136g gVar = this.f1340a;
        if (gVar != null) {
            gVar.setVideoCancelCallBack(false);
        }
        if (m1828q()) {
            mo15102a(false);
            m1829r();
            return;
        }
        mo15116n();
    }

    /* renamed from: q */
    private boolean m1828q() {
        return this.f1352n && this.f1351m != null;
    }

    /* renamed from: r */
    private void m1829r() {
        C1270g.m2078a("CachedAd", 4, "Loading " + this.f1347i + " from disk " + "file name: " + this.f1351m);
        final C1204a aVar = new C1204a();
        C1207i.m1871a(this.f1348j, this.f1351m, (C1207i.C1215a) new C1207i.C1215a() {
            /* renamed from: a */
            public void mo15117a(C1136g gVar) {
                C1270g.m2078a("CachedAd", 4, "Success loading from disk: " + C1200g.this.f1347i);
                C1200g.this.f1340a = gVar;
            }
        }, (AdEventListener) new AdEventListener() {
            public void onReceiveAd(C1040Ad ad) {
                aVar.onReceiveAd(ad);
            }

            public void onFailedToReceiveAd(C1040Ad ad) {
                C1270g.m2078a("CachedAd", 3, "Failed to load " + C1200g.this.f1347i + " from disk");
                C1200g.this.f1340a = null;
                C1200g.this.mo15116n();
            }
        });
    }

    /* renamed from: a */
    public void mo15099a(C1205b bVar) {
        this.f1346g = bVar;
    }

    /* renamed from: k */
    public boolean mo15113k() {
        if (m1830s()) {
            mo15104b(true);
            m1831t();
            return true;
        }
        C1205b bVar = this.f1346g;
        if (bVar == null) {
            return false;
        }
        bVar.mo15079a(this);
        return false;
    }

    /* renamed from: s */
    private boolean m1830s() {
        return this.f1353o < MetaData.getInstance().getStopAutoLoadAmount();
    }

    /* renamed from: t */
    private void m1831t() {
        this.f1353o++;
    }

    /* renamed from: l */
    public int mo15114l() {
        return this.f1353o;
    }

    /* renamed from: a */
    public void mo15097a(int i) {
        this.f1353o = i;
    }

    /* access modifiers changed from: protected */
    /* renamed from: m */
    public void mo15115m() {
        this.f1353o = 0;
    }

    /* access modifiers changed from: protected */
    /* renamed from: n */
    public void mo15116n() {
        C1270g.m2078a("CachedAd", 4, "Loading " + this.f1347i + " from server");
        C1136g j = mo15112j();
        this.f1340a = j;
        j.setActivityExtra(this.f1349k);
        this.f1340a.load(this.f1350l, new C1204a());
        this.f1342c = System.currentTimeMillis();
    }

    /* renamed from: u */
    private boolean m1832u() {
        C1136g gVar = this.f1340a;
        if (gVar == null) {
            return false;
        }
        return gVar.hasAdCacheTtlPassed();
    }

    /* renamed from: v */
    private C1136g m1833v() {
        boolean z = new Random().nextInt(100) < C1098b.m1303a().mo14764d();
        boolean a = C1061i.m1197a(this.f1350l, "forceOfferWall3D");
        boolean a2 = true ^ C1061i.m1197a(this.f1350l, "forceOfferWall2D");
        boolean a3 = C1061i.m1194a(64);
        if (m1834w() || (a3 && ((z || a) && a2))) {
            return new C0906b(this.f1348j);
        }
        return new C0904b(this.f1348j);
    }

    /* renamed from: w */
    private boolean m1834w() {
        return C1061i.m1194a(64) && !C1061i.m1194a(128);
    }
}

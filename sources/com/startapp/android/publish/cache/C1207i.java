package com.startapp.android.publish.cache;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.startapp.android.publish.ads.list3d.C0925e;
import com.startapp.android.publish.ads.list3d.C0926f;
import com.startapp.android.publish.ads.p018b.C0874c;
import com.startapp.android.publish.ads.p019c.p021b.C0906b;
import com.startapp.android.publish.adsCommon.C1040Ad;
import com.startapp.android.publish.adsCommon.C1098b;
import com.startapp.android.publish.adsCommon.C1118e;
import com.startapp.android.publish.adsCommon.C1136g;
import com.startapp.android.publish.adsCommon.Utils.C1061i;
import com.startapp.android.publish.adsCommon.adListeners.AdEventListener;
import com.startapp.android.publish.adsCommon.p029b.C1099a;
import com.startapp.android.publish.adsCommon.p029b.C1100b;
import com.startapp.android.publish.adsCommon.p029b.C1102c;
import com.startapp.android.publish.adsCommon.p033f.C1130d;
import com.startapp.android.publish.adsCommon.p033f.C1132f;
import com.startapp.android.publish.common.model.AdDetails;
import com.startapp.android.publish.common.model.AdPreferences;
import com.startapp.common.C1303g;
import com.startapp.common.p043a.C1267e;
import com.startapp.common.p043a.C1270g;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/* renamed from: com.startapp.android.publish.cache.i */
/* compiled from: StartAppSDK */
public class C1207i {

    /* renamed from: com.startapp.android.publish.cache.i$a */
    /* compiled from: StartAppSDK */
    protected interface C1215a {
        /* renamed from: a */
        void mo15117a(C1136g gVar);
    }

    /* renamed from: com.startapp.android.publish.cache.i$c */
    /* compiled from: StartAppSDK */
    protected interface C1217c {
        /* renamed from: a */
        void mo15076a(List<C1216b> list);
    }

    /* renamed from: com.startapp.android.publish.cache.i$e */
    /* compiled from: StartAppSDK */
    protected interface C1219e {
        /* renamed from: a */
        void mo15077a();
    }

    /* renamed from: a */
    protected static String m1862a() {
        return "startapp_ads";
    }

    /* renamed from: a */
    private static boolean m1874a(C0874c cVar) {
        return true;
    }

    /* renamed from: com.startapp.android.publish.cache.i$b */
    /* compiled from: StartAppSDK */
    protected static class C1216b implements Serializable {
        private static final long serialVersionUID = 1;
        protected AdPreferences adPreferences;
        private int numberOfLoadedAd;
        protected AdPreferences.Placement placement;

        protected C1216b(AdPreferences.Placement placement2, AdPreferences adPreferences2) {
            this.placement = placement2;
            this.adPreferences = adPreferences2;
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public AdPreferences.Placement mo15130a() {
            return this.placement;
        }

        /* access modifiers changed from: protected */
        /* renamed from: b */
        public AdPreferences mo15132b() {
            return this.adPreferences;
        }

        /* access modifiers changed from: protected */
        /* renamed from: c */
        public int mo15133c() {
            return this.numberOfLoadedAd;
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public void mo15131a(int i) {
            this.numberOfLoadedAd = i;
        }
    }

    /* renamed from: com.startapp.android.publish.cache.i$d */
    /* compiled from: StartAppSDK */
    protected static class C1218d implements Serializable {
        private static final long serialVersionUID = 1;

        /* renamed from: ad */
        private C1136g f1377ad;
        private String html;

        protected C1218d(C1136g gVar) {
            m1885a(gVar);
            m1886c();
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public C1136g mo15134a() {
            return this.f1377ad;
        }

        /* access modifiers changed from: protected */
        /* renamed from: b */
        public String mo15135b() {
            return this.html;
        }

        /* renamed from: a */
        private void m1885a(C1136g gVar) {
            this.f1377ad = gVar;
        }

        /* renamed from: c */
        private void m1886c() {
            C1136g gVar = this.f1377ad;
            if (gVar != null && (gVar instanceof C1118e)) {
                this.html = ((C1118e) gVar).mo14843f();
            }
        }
    }

    /* renamed from: a */
    protected static void m1869a(final Context context, final C1219e eVar) {
        C1303g.m2206a(C1303g.C1307a.DEFAULT, (Runnable) new Runnable() {
            public void run() {
                try {
                    C1267e.m2060a(context, C1207i.m1862a());
                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        public void run() {
                            eVar.mo15077a();
                        }
                    });
                } catch (Exception e) {
                    C1132f.m1527a(context, C1130d.EXCEPTION, " DiskAdCacheManager.deleteDiskCacheAsync - Unexpected Thread Exception", e.getMessage(), "");
                }
            }
        });
    }

    /* renamed from: a */
    protected static void m1870a(Context context, AdPreferences.Placement placement, AdPreferences adPreferences, String str, int i) {
        C1216b bVar = new C1216b(placement, adPreferences);
        bVar.mo15131a(i);
        C1267e.m2071b(context, m1875b(), str, (Serializable) bVar);
    }

    /* renamed from: a */
    protected static void m1867a(final Context context, final C1217c cVar) {
        C1303g.m2206a(C1303g.C1307a.HIGH, (Runnable) new Runnable() {
            public void run() {
                try {
                    final List<C1216b> b = C1267e.m2069b(context, C1207i.m1875b(), C1216b.class);
                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        public void run() {
                            cVar.mo15076a(b);
                        }
                    });
                } catch (Exception e) {
                    C1132f.m1527a(context, C1130d.EXCEPTION, " DiskAdCacheManager.loadCacheKeysAsync - Unexpected Thread Exception", e.getMessage(), "");
                }
            }
        });
    }

    /* renamed from: a */
    protected static void m1866a(Context context, C1200g gVar, String str) {
        C1267e.m2071b(context, m1876c(), str, (Serializable) new C1218d(gVar.mo15103b()));
    }

    /* renamed from: a */
    protected static void m1871a(final Context context, final String str, final C1215a aVar, final AdEventListener adEventListener) {
        C1303g.m2206a(C1303g.C1307a.HIGH, (Runnable) new Runnable() {
            public void run() {
                try {
                    final C1218d dVar = (C1218d) C1267e.m2066b(context, C1207i.m1876c(), str, C1218d.class);
                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        public void run() {
                            try {
                                if (dVar == null) {
                                    C1270g.m2078a("DiskAdCacheManager", 4, "File not found or error: " + str);
                                    adEventListener.onFailedToReceiveAd((C1040Ad) null);
                                    return;
                                }
                                if (dVar.mo15134a() != null) {
                                    if (dVar.mo15134a().isReady()) {
                                        if (dVar.mo15134a().hasAdCacheTtlPassed()) {
                                            C1270g.m2078a("DiskAdCacheManager", 3, "Disk ad TTL has passed");
                                            adEventListener.onFailedToReceiveAd((C1040Ad) null);
                                            return;
                                        }
                                        C1207i.m1868a(context, dVar, aVar, adEventListener);
                                        return;
                                    }
                                }
                                C1270g.m2078a("DiskAdCacheManager", 3, "Disk ad is not ready or null");
                                adEventListener.onFailedToReceiveAd((C1040Ad) null);
                            } catch (Exception e) {
                                C1132f.m1527a(context, C1130d.EXCEPTION, "DiskAdCacheManager.loadCachedAdAsync - Unexpected Thread Exception", e.getMessage(), "");
                                adEventListener.onFailedToReceiveAd((C1040Ad) null);
                            }
                        }
                    });
                } catch (Exception e) {
                    C1132f.m1527a(context, C1130d.EXCEPTION, "DiskAdCacheManager.loadCachedAdAsync - Unexpected Thread Exception", e.getMessage(), "");
                    adEventListener.onFailedToReceiveAd((C1040Ad) null);
                }
            }
        });
    }

    /* renamed from: b */
    protected static String m1875b() {
        return m1862a().concat(File.separator).concat("keys");
    }

    /* renamed from: c */
    protected static String m1876c() {
        return m1862a().concat(File.separator).concat("interstitials");
    }

    /* renamed from: a */
    protected static void m1868a(Context context, C1218d dVar, C1215a aVar, AdEventListener adEventListener) {
        C1136g a = dVar.mo15134a();
        a.setContext(context);
        if (C1061i.m1194a(2) && (a instanceof C0874c)) {
            m1864a(context, (C0874c) a, dVar.mo15135b(), aVar, adEventListener);
        } else if (!C1061i.m1194a(64) || !(a instanceof C0906b)) {
            C1270g.m2078a("DiskAdCacheManager", 4, "Unsupported disk ad type");
            adEventListener.onFailedToReceiveAd((C1040Ad) null);
        } else {
            m1865a(context, (C0906b) a, aVar, adEventListener);
        }
    }

    /* renamed from: a */
    private static void m1865a(Context context, C0906b bVar, C1215a aVar, AdEventListener adEventListener) {
        List<AdDetails> d = bVar.mo14937d();
        if (d == null) {
            C1270g.m2078a("DiskAdCacheManager", 4, "No ad details");
            adEventListener.onFailedToReceiveAd((C1040Ad) null);
            return;
        }
        if (C1098b.m1303a().mo14751E()) {
            d = C1102c.m1360a(context, d, 0, new HashSet());
        }
        if (d == null || d.size() <= 0) {
            C1270g.m2078a("DiskAdCacheManager", 4, "App presence - no interstitials to display");
            adEventListener.onFailedToReceiveAd((C1040Ad) null);
            return;
        }
        aVar.mo15117a(bVar);
        m1872a(bVar, adEventListener, d);
    }

    /* renamed from: a */
    private static void m1864a(Context context, C0874c cVar, String str, C1215a aVar, AdEventListener adEventListener) {
        if (str == null || str.equals("")) {
            C1270g.m2078a("DiskAdCacheManager", 3, "Missing Html");
            adEventListener.onFailedToReceiveAd((C1040Ad) null);
        } else if (!m1874a(cVar)) {
            C1270g.m2078a("DiskAdCacheManager", 3, "Missing video file");
            adEventListener.onFailedToReceiveAd((C1040Ad) null);
        } else if (!m1873a(context, str)) {
            C1270g.m2078a("DiskAdCacheManager", 3, "App is present");
            adEventListener.onFailedToReceiveAd((C1040Ad) null);
        } else {
            C1186a.m1756a().mo15061a(str, cVar.mo14844g());
            aVar.mo15117a(cVar);
            m1863a(context, cVar, str, adEventListener);
        }
    }

    /* renamed from: a */
    private static void m1863a(Context context, final C0874c cVar, String str, final AdEventListener adEventListener) {
        C1061i.m1185a(context, str, (C1061i.C1066a) new C1061i.C1066a() {
            /* renamed from: a */
            public void mo14645a() {
                adEventListener.onReceiveAd(cVar);
            }

            /* renamed from: a */
            public void mo14646a(String str) {
                C1270g.m2078a("DiskAdCacheManager", 3, "Html Cache failed: " + str);
                adEventListener.onFailedToReceiveAd(cVar);
            }
        });
    }

    /* renamed from: a */
    private static void m1872a(C0906b bVar, AdEventListener adEventListener, List<AdDetails> list) {
        C0925e a = C0926f.m744a().mo14097a(bVar.mo13946a());
        a.mo14087a();
        for (AdDetails a2 : list) {
            a.mo14090a(a2);
        }
        adEventListener.onReceiveAd(bVar);
    }

    /* renamed from: a */
    private static boolean m1873a(Context context, String str) {
        List<C1099a> a;
        if (!C1098b.m1303a().mo14751E() || (a = C1102c.m1362a(str, 0)) == null) {
            return true;
        }
        ArrayList arrayList = new ArrayList();
        if (!C1102c.m1359a(context, a, 0, (Set<String>) new HashSet(), (List<C1099a>) arrayList).booleanValue()) {
            return true;
        }
        new C1100b(context, arrayList).mo14795a();
        return false;
    }
}

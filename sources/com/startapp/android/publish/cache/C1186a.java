package com.startapp.android.publish.cache;

import android.content.Context;
import com.startapp.android.publish.ads.p018b.C0876e;
import com.startapp.android.publish.adsCommon.C1040Ad;
import com.startapp.android.publish.adsCommon.C1098b;
import com.startapp.android.publish.adsCommon.C1103c;
import com.startapp.android.publish.adsCommon.C1136g;
import com.startapp.android.publish.adsCommon.C1166k;
import com.startapp.android.publish.adsCommon.C1174m;
import com.startapp.android.publish.adsCommon.StartAppAd;
import com.startapp.android.publish.adsCommon.Utils.C1061i;
import com.startapp.android.publish.adsCommon.adListeners.AdEventListener;
import com.startapp.android.publish.adsCommon.p033f.C1130d;
import com.startapp.android.publish.adsCommon.p033f.C1131e;
import com.startapp.android.publish.adsCommon.p033f.C1132f;
import com.startapp.android.publish.cache.C1200g;
import com.startapp.android.publish.cache.C1207i;
import com.startapp.android.publish.common.metaData.C1231d;
import com.startapp.android.publish.common.metaData.MetaData;
import com.startapp.android.publish.common.model.AdPreferences;
import com.startapp.common.C1303g;
import com.startapp.common.p043a.C1267e;
import com.startapp.common.p043a.C1270g;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Random;
import java.util.Set;
import java.util.UUID;
import java.util.WeakHashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/* renamed from: com.startapp.android.publish.cache.a */
/* compiled from: StartAppSDK */
public class C1186a {

    /* renamed from: c */
    private static C1186a f1307c = new C1186a();

    /* renamed from: a */
    final Map<C1195c, C1200g> f1308a = new ConcurrentHashMap();

    /* renamed from: b */
    protected boolean f1309b = false;

    /* renamed from: d */
    private Map<String, String> f1310d = new WeakHashMap();

    /* renamed from: e */
    private boolean f1311e = false;

    /* renamed from: f */
    private Queue<C1193a> f1312f = new ConcurrentLinkedQueue();

    /* renamed from: g */
    private C1200g.C1205b f1313g;

    /* renamed from: h */
    private Context f1314h;

    /* renamed from: com.startapp.android.publish.cache.a$a */
    /* compiled from: StartAppSDK */
    private class C1193a {

        /* renamed from: a */
        StartAppAd f1325a;

        /* renamed from: b */
        AdPreferences.Placement f1326b;

        /* renamed from: c */
        AdPreferences f1327c;

        /* renamed from: d */
        AdEventListener f1328d;

        C1193a(StartAppAd startAppAd, AdPreferences.Placement placement, AdPreferences adPreferences, AdEventListener adEventListener) {
            this.f1325a = startAppAd;
            this.f1326b = placement;
            this.f1327c = adPreferences;
            this.f1328d = adEventListener;
        }
    }

    private C1186a() {
    }

    /* renamed from: a */
    public static C1186a m1756a() {
        return f1307c;
    }

    /* renamed from: a */
    public C1195c mo15057a(Context context, StartAppAd startAppAd, AdPreferences adPreferences, AdEventListener adEventListener) {
        if (!m1763b(AdPreferences.Placement.INAPP_SPLASH)) {
            return null;
        }
        C1270g.m2078a("AdCacheManager", 3, "Loading splash");
        return mo15055a(context, startAppAd, AdPreferences.Placement.INAPP_SPLASH, adPreferences, adEventListener);
    }

    /* renamed from: a */
    public C1195c mo15058a(Context context, AdPreferences adPreferences) {
        if (!m1763b(AdPreferences.Placement.INAPP_RETURN)) {
            return null;
        }
        C1270g.m2078a("AdCacheManager", 3, "Loading return ad");
        return mo15055a(context, (StartAppAd) null, AdPreferences.Placement.INAPP_RETURN, adPreferences, (AdEventListener) null);
    }

    /* renamed from: a */
    public C1195c mo15054a(Context context, StartAppAd startAppAd, StartAppAd.AdMode adMode, AdPreferences adPreferences, AdEventListener adEventListener) {
        if (adPreferences == null) {
            adPreferences = new AdPreferences();
        }
        AdPreferences adPreferences2 = adPreferences;
        AdPreferences.Placement b = m1762b(adMode, adPreferences2);
        m1758a(adMode, adPreferences2);
        return mo15056a(context, startAppAd, b, adPreferences2, adEventListener, false, 0);
    }

    /* renamed from: a */
    public void mo15063a(final Context context) {
        this.f1314h = context.getApplicationContext();
        if (m1765e()) {
            this.f1311e = true;
            C1207i.m1867a(context, (C1207i.C1217c) new C1207i.C1217c() {
                /* renamed from: a */
                public void mo15076a(List<C1207i.C1216b> list) {
                    if (list != null) {
                        try {
                            for (C1207i.C1216b next : list) {
                                if (C1186a.this.m1763b(next.placement)) {
                                    C1270g.m2078a("AdCacheManager", 4, "Loading from disk: " + next.placement);
                                    C1186a.this.mo15056a(context, (StartAppAd) null, next.mo15130a(), next.mo15132b(), (AdEventListener) null, true, next.mo15133c());
                                }
                            }
                        } catch (Exception e) {
                            C1270g.m2079a("AdCacheManager", 6, "loadFromDisk - onCacheKeysLoaded failed", e);
                        }
                    }
                    C1186a.this.mo15075d(context);
                }
            });
        }
    }

    /* renamed from: b */
    public void mo15068b() {
        if (!this.f1311e) {
            synchronized (this.f1308a) {
                for (C1200g d : this.f1308a.values()) {
                    d.mo15106d();
                }
            }
        }
    }

    /* renamed from: a */
    public void mo15064a(Context context, boolean z) {
        m1764e(context);
        m1760a(z);
    }

    /* renamed from: b */
    public void mo15069b(Context context) {
        this.f1309b = true;
        C1207i.m1869a(context, (C1207i.C1219e) new C1207i.C1219e() {
            /* renamed from: a */
            public void mo15077a() {
                C1186a.this.f1309b = false;
            }
        });
    }

    /* renamed from: c */
    public void mo15073c(final Context context) {
        C11893 r0 = new C1231d() {
            /* renamed from: a */
            public void mo14204a() {
                MetaData.getInstance().removeMetaDataListener(this);
                Set<StartAppAd.AdMode> autoLoad = C1196d.m1803a().mo15089b().getAutoLoad();
                if (autoLoad != null) {
                    for (StartAppAd.AdMode next : C1186a.this.mo15062a(autoLoad)) {
                        C1270g.m2078a("preCache", 3, "preCacheAds load " + next.name());
                        int b = C1098b.m1303a().mo14762b();
                        if (next == StartAppAd.AdMode.FULLPAGE) {
                            if (b > 0) {
                                C1186a.this.mo15054a(context, (StartAppAd) null, StartAppAd.AdMode.FULLPAGE, new AdPreferences(), (AdEventListener) null);
                            }
                        } else if (next != StartAppAd.AdMode.OFFERWALL) {
                            C1186a.this.mo15054a(context, (StartAppAd) null, next, new AdPreferences(), (AdEventListener) null);
                        } else if (b < 100) {
                            C1186a.this.mo15054a(context, (StartAppAd) null, StartAppAd.AdMode.OFFERWALL, new AdPreferences(), (AdEventListener) null);
                        }
                        String a = C1186a.this.mo15059a(next);
                        if (a != null) {
                            C1166k.m1615b(context, a, Integer.valueOf(C1166k.m1608a(context, a, (Integer) 0).intValue() + 1));
                        }
                    }
                }
            }

            /* renamed from: b */
            public void mo14205b() {
                C1270g.m2078a("AdCacheManager", 3, "Failed loading metadata, unable to pre-cache.");
                MetaData.getInstance().removeMetaDataListener(this);
            }
        };
        synchronized (MetaData.getLock()) {
            if (MetaData.getInstance().isReady()) {
                r0.mo14204a();
            } else {
                MetaData.getInstance().addMetaDataListener(r0);
            }
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public Set<StartAppAd.AdMode> mo15062a(Set<StartAppAd.AdMode> set) {
        Iterator<StartAppAd.AdMode> it = set.iterator();
        while (it.hasNext()) {
            StartAppAd.AdMode next = it.next();
            boolean z = false;
            if (C1166k.m1608a(this.f1314h, mo15059a(next), (Integer) 0).intValue() >= MetaData.getInstance().getStopAutoLoadPreCacheAmount()) {
                z = true;
            }
            if (z) {
                C1270g.m2078a("preCache", 3, "preCacheAds.remove " + next.name());
                it.remove();
            }
        }
        if (!C1061i.m1194a(128) && !C1061i.m1194a(64)) {
            set.remove(StartAppAd.AdMode.OFFERWALL);
        }
        if (!C1061i.m1194a(2) && !C1061i.m1194a(4)) {
            set.remove(StartAppAd.AdMode.FULLPAGE);
        }
        if (!C1061i.m1194a(4)) {
            set.remove(StartAppAd.AdMode.REWARDED_VIDEO);
            set.remove(StartAppAd.AdMode.VIDEO);
        }
        return set;
    }

    /* renamed from: c */
    public int mo15070c() {
        return this.f1308a.size();
    }

    /* renamed from: a */
    public C1136g mo15053a(C1195c cVar) {
        if (cVar == null) {
            C1270g.m2078a("AdCacheManager", 3, "Cache key is null");
            return null;
        }
        C1270g.m2078a("AdCacheManager", 3, "Retrieving ad with " + cVar);
        C1200g gVar = this.f1308a.get(cVar);
        if (gVar != null) {
            return gVar.mo15111i();
        }
        return null;
    }

    /* renamed from: b */
    public C1136g mo15066b(C1195c cVar) {
        C1200g gVar = cVar != null ? this.f1308a.get(cVar) : null;
        if (gVar != null) {
            return gVar.mo15103b();
        }
        return null;
    }

    /* renamed from: d */
    public synchronized List<C1200g> mo15074d() {
        return new ArrayList(this.f1308a.values());
    }

    /* renamed from: a */
    public String mo15060a(String str) {
        return mo15061a(str, UUID.randomUUID().toString());
    }

    /* renamed from: a */
    public String mo15061a(String str, String str2) {
        this.f1310d.put(str2, str);
        return str2;
    }

    /* renamed from: b */
    public String mo15067b(String str) {
        return this.f1310d.get(str);
    }

    /* renamed from: c */
    public String mo15072c(String str) {
        C1270g.m2078a("AdCacheManager", 3, "cache size: " + this.f1310d.size() + " - removing " + str);
        return this.f1310d.remove(str);
    }

    /* renamed from: e */
    private boolean m1765e() {
        return !this.f1309b && C1196d.m1803a().mo15089b().isLocalCache();
    }

    /* access modifiers changed from: protected */
    /* renamed from: d */
    public void mo15075d(Context context) {
        this.f1311e = false;
        for (C1193a aVar : this.f1312f) {
            if (m1763b(aVar.f1326b)) {
                C1270g.m2078a("AdCacheManager", 4, "Loading pending request for: " + aVar.f1326b);
                mo15055a(context, aVar.f1325a, aVar.f1326b, aVar.f1327c, aVar.f1328d);
            }
        }
        this.f1312f.clear();
    }

    /* renamed from: e */
    private void m1764e(final Context context) {
        C1270g.m2078a("AdCacheManager", 3, "Saving to disk: eneter save to disk ");
        if (m1765e()) {
            C1270g.m2078a("AdCacheManager", 3, "Saving to disk: cache to disk is enebaled ");
            C1303g.m2206a(C1303g.C1307a.DEFAULT, (Runnable) new Runnable() {
                public void run() {
                    try {
                        C1267e.m2060a(context, C1207i.m1875b());
                        C1267e.m2060a(context, C1207i.m1876c());
                        C1195c cVar = null;
                        for (Map.Entry next : C1186a.this.f1308a.entrySet()) {
                            C1195c cVar2 = (C1195c) next.getKey();
                            if (cVar2.mo15085a() == AdPreferences.Placement.INAPP_SPLASH) {
                                cVar = cVar2;
                            } else {
                                C1200g gVar = (C1200g) next.getValue();
                                C1270g.m2078a("AdCacheManager", 3, "Saving to disk: " + cVar2.toString());
                                C1207i.m1870a(context, cVar2.mo15085a(), gVar.mo15096a(), C1186a.this.mo15071c(cVar2), gVar.mo15114l());
                                C1207i.m1866a(context, gVar, C1186a.this.mo15071c(cVar2));
                            }
                        }
                        if (cVar != null) {
                            C1186a.this.f1308a.remove(cVar);
                        }
                    } catch (Exception e) {
                        C1270g.m2078a("AdCacheManager", 3, "Saving to disk: exception caught");
                        C1132f.m1527a(context, C1130d.EXCEPTION, "AdCacheManager.saveToDisk - Unexpected Thread Exception", e.getMessage(), "");
                    }
                }
            });
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: c */
    public String mo15071c(C1195c cVar) {
        return String.valueOf(cVar.hashCode()).replace('-', '_');
    }

    /* renamed from: a */
    private void m1760a(boolean z) {
        for (C1200g next : this.f1308a.values()) {
            if (next.mo15103b() == null || !C1061i.m1194a(2) || !(next.mo15103b() instanceof C0876e) || z) {
                next.mo15108f();
            } else if (!C1196d.m1803a().mo15089b().shouldReturnAdLoadInBg()) {
                next.mo15108f();
            }
            next.mo15107e();
        }
    }

    /* renamed from: a */
    public C1195c mo15055a(Context context, StartAppAd startAppAd, AdPreferences.Placement placement, AdPreferences adPreferences, AdEventListener adEventListener) {
        return mo15056a(context, startAppAd, placement, adPreferences, adEventListener, false, 0);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public C1195c mo15056a(Context context, StartAppAd startAppAd, AdPreferences.Placement placement, AdPreferences adPreferences, AdEventListener adEventListener, boolean z, int i) {
        C1200g gVar;
        this.f1314h = context.getApplicationContext();
        if (adPreferences == null) {
            adPreferences = new AdPreferences();
        }
        AdPreferences adPreferences2 = adPreferences;
        C1195c cVar = new C1195c(placement, adPreferences2);
        if (!this.f1311e || z) {
            AdPreferences adPreferences3 = new AdPreferences(adPreferences2);
            synchronized (this.f1308a) {
                gVar = this.f1308a.get(cVar);
                if (gVar == null) {
                    C1270g.m2078a("AdCacheManager", 3, "CachedAd for " + placement + " not found. Adding new CachedAd with " + cVar);
                    if (C11926.f1323a[placement.ordinal()] != 1) {
                        gVar = new C1200g(context, placement, adPreferences3);
                    } else {
                        gVar = new C1200g(context, placement, adPreferences3, false);
                    }
                    gVar.mo15099a(m1766f());
                    if (z) {
                        gVar.mo15101a(mo15071c(cVar));
                        gVar.mo15102a(true);
                        gVar.mo15097a(i);
                    }
                    m1759a(cVar, gVar, context);
                } else {
                    C1270g.m2078a("AdCacheManager", 3, "CachedAd for " + placement + " already exists.");
                    gVar.mo15100a(adPreferences3);
                }
            }
            gVar.mo15098a(startAppAd, adEventListener);
            return cVar;
        }
        C1270g.m2078a("AdCacheManager", 4, "Adding to pending queue: " + placement);
        this.f1312f.add(new C1193a(startAppAd, placement, adPreferences2, adEventListener));
        return cVar;
    }

    /* renamed from: a */
    public void mo15065a(AdPreferences.Placement placement) {
        synchronized (this.f1308a) {
            Iterator<Map.Entry<C1195c, C1200g>> it = this.f1308a.entrySet().iterator();
            while (it.hasNext()) {
                if (((C1195c) it.next().getKey()).mo15085a() == placement) {
                    it.remove();
                }
            }
        }
    }

    /* renamed from: a */
    private void m1759a(C1195c cVar, C1200g gVar, Context context) {
        synchronized (this.f1308a) {
            int maxCacheSize = C1196d.m1803a().mo15089b().getMaxCacheSize();
            if (maxCacheSize != 0 && mo15070c() >= maxCacheSize) {
                long j = Long.MAX_VALUE;
                C1195c cVar2 = null;
                for (C1195c next : this.f1308a.keySet()) {
                    C1200g gVar2 = this.f1308a.get(next);
                    if (gVar2.mo15105c() == gVar.mo15105c() && gVar2.f1342c < j) {
                        j = gVar2.f1342c;
                        cVar2 = next;
                    }
                }
                if (cVar2 != null) {
                    this.f1308a.remove(cVar2);
                }
            }
            this.f1308a.put(cVar, gVar);
            if (Math.random() * 100.0d < ((double) C1196d.m1803a().mo15090c())) {
                C1132f.m1529a(context, new C1131e(C1130d.GENERAL, "Cache Size", String.valueOf(mo15070c())), "");
            }
        }
    }

    /* renamed from: a */
    private void m1758a(StartAppAd.AdMode adMode, AdPreferences adPreferences) {
        if (adMode.equals(StartAppAd.AdMode.REWARDED_VIDEO)) {
            C1103c.m1384a(adPreferences, "type", C1040Ad.AdType.REWARDED_VIDEO);
        }
        if (adMode.equals(StartAppAd.AdMode.VIDEO)) {
            C1103c.m1384a(adPreferences, "type", C1040Ad.AdType.VIDEO);
        }
    }

    /* renamed from: com.startapp.android.publish.cache.a$6 */
    /* compiled from: StartAppSDK */
    static /* synthetic */ class C11926 {

        /* renamed from: a */
        static final /* synthetic */ int[] f1323a;

        /* renamed from: b */
        static final /* synthetic */ int[] f1324b;

        /* JADX WARNING: Can't wrap try/catch for region: R(17:0|(2:1|2)|3|5|6|7|8|9|10|11|12|13|14|15|17|18|(3:19|20|22)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(20:0|1|2|3|5|6|7|8|9|10|11|12|13|14|15|17|18|19|20|22) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0033 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x003e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x005a */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0028 */
        static {
            /*
                com.startapp.android.publish.adsCommon.StartAppAd$AdMode[] r0 = com.startapp.android.publish.adsCommon.StartAppAd.AdMode.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                f1324b = r0
                r1 = 1
                com.startapp.android.publish.adsCommon.StartAppAd$AdMode r2 = com.startapp.android.publish.adsCommon.StartAppAd.AdMode.OFFERWALL     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r0[r2] = r1     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                r0 = 2
                int[] r2 = f1324b     // Catch:{ NoSuchFieldError -> 0x001d }
                com.startapp.android.publish.adsCommon.StartAppAd$AdMode r3 = com.startapp.android.publish.adsCommon.StartAppAd.AdMode.OVERLAY     // Catch:{ NoSuchFieldError -> 0x001d }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2[r3] = r0     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r2 = f1324b     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.startapp.android.publish.adsCommon.StartAppAd$AdMode r3 = com.startapp.android.publish.adsCommon.StartAppAd.AdMode.FULLPAGE     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r4 = 3
                r2[r3] = r4     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r2 = f1324b     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.startapp.android.publish.adsCommon.StartAppAd$AdMode r3 = com.startapp.android.publish.adsCommon.StartAppAd.AdMode.VIDEO     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r4 = 4
                r2[r3] = r4     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r2 = f1324b     // Catch:{ NoSuchFieldError -> 0x003e }
                com.startapp.android.publish.adsCommon.StartAppAd$AdMode r3 = com.startapp.android.publish.adsCommon.StartAppAd.AdMode.REWARDED_VIDEO     // Catch:{ NoSuchFieldError -> 0x003e }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r4 = 5
                r2[r3] = r4     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r2 = f1324b     // Catch:{ NoSuchFieldError -> 0x0049 }
                com.startapp.android.publish.adsCommon.StartAppAd$AdMode r3 = com.startapp.android.publish.adsCommon.StartAppAd.AdMode.AUTOMATIC     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r4 = 6
                r2[r3] = r4     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                com.startapp.android.publish.common.model.AdPreferences$Placement[] r2 = com.startapp.android.publish.common.model.AdPreferences.Placement.values()
                int r2 = r2.length
                int[] r2 = new int[r2]
                f1323a = r2
                com.startapp.android.publish.common.model.AdPreferences$Placement r3 = com.startapp.android.publish.common.model.AdPreferences.Placement.INAPP_SPLASH     // Catch:{ NoSuchFieldError -> 0x005a }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x005a }
                r2[r3] = r1     // Catch:{ NoSuchFieldError -> 0x005a }
            L_0x005a:
                int[] r1 = f1323a     // Catch:{ NoSuchFieldError -> 0x0064 }
                com.startapp.android.publish.common.model.AdPreferences$Placement r2 = com.startapp.android.publish.common.model.AdPreferences.Placement.INAPP_RETURN     // Catch:{ NoSuchFieldError -> 0x0064 }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0064 }
                r1[r2] = r0     // Catch:{ NoSuchFieldError -> 0x0064 }
            L_0x0064:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.startapp.android.publish.cache.C1186a.C11926.<clinit>():void");
        }
    }

    /* renamed from: b */
    private AdPreferences.Placement m1762b(StartAppAd.AdMode adMode, AdPreferences adPreferences) {
        boolean z = false;
        switch (C11926.f1324b[adMode.ordinal()]) {
            case 1:
                if (C1061i.m1194a(128) || C1061i.m1194a(64)) {
                    z = true;
                }
                return z ? AdPreferences.Placement.INAPP_OFFER_WALL : AdPreferences.Placement.INAPP_FULL_SCREEN;
            case 2:
            case 3:
            case 4:
            case 5:
                return AdPreferences.Placement.INAPP_OVERLAY;
            case 6:
                if (C1061i.m1194a(128) || C1061i.m1194a(64)) {
                    z = true;
                }
                boolean a = C1061i.m1194a(4);
                boolean a2 = C1061i.m1194a(2);
                if (a && a2 && z) {
                    if (new Random().nextInt(100) < C1098b.m1303a().mo14762b()) {
                        return m1757a(adPreferences);
                    }
                    return AdPreferences.Placement.INAPP_FULL_SCREEN;
                } else if (a || a2) {
                    return AdPreferences.Placement.INAPP_OVERLAY;
                } else {
                    if (z) {
                        return AdPreferences.Placement.INAPP_OFFER_WALL;
                    }
                }
                break;
        }
        return AdPreferences.Placement.INAPP_FULL_SCREEN;
    }

    /* renamed from: a */
    private AdPreferences.Placement m1757a(AdPreferences adPreferences) {
        if ((new Random().nextInt(100) < C1098b.m1303a().mo14763c() || C1061i.m1197a(adPreferences, "forceFullpage")) && !C1061i.m1197a(adPreferences, "forceOverlay")) {
            return AdPreferences.Placement.INAPP_FULL_SCREEN;
        }
        return AdPreferences.Placement.INAPP_OVERLAY;
    }

    /* access modifiers changed from: private */
    /* renamed from: b */
    public boolean m1763b(AdPreferences.Placement placement) {
        int i = C11926.f1323a[placement.ordinal()];
        if (i != 1) {
            if (i != 2) {
                return true;
            }
            if (!C1174m.m1649a().mo15003h() || C1098b.m1303a().mo14785y()) {
                return false;
            }
            return true;
        } else if (!C1174m.m1649a().mo15006k() || C1098b.m1303a().mo14786z()) {
            return false;
        } else {
            return true;
        }
    }

    /* renamed from: f */
    private C1200g.C1205b m1766f() {
        if (this.f1313g == null) {
            this.f1313g = new C1200g.C1205b() {
                /* renamed from: a */
                public void mo15079a(C1200g gVar) {
                    synchronized (C1186a.this.f1308a) {
                        C1195c cVar = null;
                        for (C1195c next : C1186a.this.f1308a.keySet()) {
                            if (C1186a.this.f1308a.get(next) == gVar) {
                                cVar = next;
                            }
                        }
                        if (cVar != null) {
                            C1186a.this.f1308a.remove(cVar);
                        }
                    }
                }
            };
        }
        return this.f1313g;
    }

    /* renamed from: a */
    public String mo15059a(StartAppAd.AdMode adMode) {
        if (adMode == null) {
            return null;
        }
        return "autoLoadNotShownAdPrefix" + adMode.name();
    }
}

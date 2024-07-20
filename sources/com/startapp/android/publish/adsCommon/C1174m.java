package com.startapp.android.publish.adsCommon;

import android.app.Activity;
import android.app.Application;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.WebView;
import androidx.browser.customtabs.CustomTabsService;
import com.mopub.common.logging.MoPubLog;
import com.startapp.android.publish.ads.banner.C0901c;
import com.startapp.android.publish.ads.p018b.C0876e;
import com.startapp.android.publish.ads.splash.C0949f;
import com.startapp.android.publish.ads.splash.SplashConfig;
import com.startapp.android.publish.ads.splash.SplashHideListener;
import com.startapp.android.publish.adsCommon.AdsConstants;
import com.startapp.android.publish.adsCommon.Utils.C1051b;
import com.startapp.android.publish.adsCommon.Utils.C1059g;
import com.startapp.android.publish.adsCommon.Utils.C1061i;
import com.startapp.android.publish.adsCommon.activities.FullScreenActivity;
import com.startapp.android.publish.adsCommon.adinformation.C1083a;
import com.startapp.android.publish.adsCommon.p028a.C1068a;
import com.startapp.android.publish.adsCommon.p028a.C1069b;
import com.startapp.android.publish.adsCommon.p028a.C1073f;
import com.startapp.android.publish.adsCommon.p032e.C1119a;
import com.startapp.android.publish.adsCommon.p033f.C1128c;
import com.startapp.android.publish.adsCommon.p033f.C1130d;
import com.startapp.android.publish.adsCommon.p033f.C1132f;
import com.startapp.android.publish.adsCommon.p042k.C1167a;
import com.startapp.android.publish.cache.C1186a;
import com.startapp.android.publish.cache.C1195c;
import com.startapp.android.publish.cache.C1196d;
import com.startapp.android.publish.common.metaData.C1231d;
import com.startapp.android.publish.common.metaData.MetaData;
import com.startapp.android.publish.common.metaData.MetaDataRequest;
import com.startapp.android.publish.common.model.AdPreferences;
import com.startapp.common.C1292c;
import com.startapp.common.C1303g;
import com.startapp.common.Constants;
import com.startapp.common.p043a.C1261c;
import com.startapp.common.p043a.C1267e;
import com.startapp.common.p043a.C1270g;
import com.startapp.common.p043a.C1274i;
import com.startapp.common.p047d.C1299a;
import com.truenet.android.TrueNetSDK;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

/* renamed from: com.startapp.android.publish.adsCommon.m */
/* compiled from: StartAppSDK */
public class C1174m {
    /* access modifiers changed from: private */

    /* renamed from: A */
    public ServiceConnection f1263A;

    /* renamed from: a */
    private SDKAdPreferences f1264a;

    /* renamed from: b */
    private boolean f1265b;

    /* renamed from: c */
    private boolean f1266c;

    /* renamed from: d */
    private boolean f1267d;

    /* renamed from: e */
    private boolean f1268e;

    /* renamed from: f */
    private boolean f1269f;

    /* renamed from: g */
    private long f1270g;

    /* renamed from: h */
    private Application f1271h;

    /* renamed from: i */
    private HashMap<Integer, Integer> f1272i;

    /* renamed from: j */
    private Object f1273j;

    /* renamed from: k */
    private Activity f1274k;

    /* renamed from: l */
    private boolean f1275l;

    /* renamed from: m */
    private String f1276m;

    /* renamed from: n */
    private boolean f1277n;

    /* renamed from: o */
    private boolean f1278o;

    /* renamed from: p */
    private boolean f1279p;

    /* renamed from: q */
    private boolean f1280q;

    /* renamed from: r */
    private Map<String, String> f1281r;

    /* renamed from: s */
    private Bundle f1282s;

    /* renamed from: t */
    private C1195c f1283t;

    /* renamed from: u */
    private boolean f1284u;

    /* renamed from: v */
    private boolean f1285v;

    /* renamed from: w */
    private boolean f1286w;

    /* renamed from: x */
    private boolean f1287x;

    /* renamed from: y */
    private C1136g f1288y;
    /* access modifiers changed from: private */

    /* renamed from: z */
    public boolean f1289z;

    /* renamed from: com.startapp.android.publish.adsCommon.m$a */
    /* compiled from: StartAppSDK */
    private static class C1181a {
        /* access modifiers changed from: private */

        /* renamed from: a */
        public static final C1174m f1299a = new C1174m();
    }

    /* renamed from: b */
    public void mo14982b(Activity activity, Bundle bundle) {
    }

    private C1174m() {
        this.f1265b = C1061i.m1194a(512);
        this.f1267d = false;
        this.f1268e = false;
        this.f1269f = false;
        this.f1271h = null;
        this.f1272i = new HashMap<>();
        this.f1275l = false;
        this.f1277n = false;
        this.f1278o = true;
        this.f1279p = false;
        this.f1280q = false;
        this.f1282s = null;
        this.f1283t = null;
        this.f1285v = false;
        this.f1286w = false;
        this.f1287x = false;
        this.f1288y = null;
        this.f1289z = false;
    }

    /* renamed from: a */
    public static C1174m m1649a() {
        return C1181a.f1299a;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo14977a(Context context, String str, String str2, SDKAdPreferences sDKAdPreferences, boolean z) {
        try {
            if (C1061i.m1194a(2)) {
                if (!C1061i.m1196a(context, (Class<? extends Activity>) FullScreenActivity.class)) {
                    Log.w("StartAppSDKInternal", "FullScreenActivity is missing from AndroidManifest.xml");
                }
            }
            if (context instanceof Activity) {
                this.f1276m = context.getClass().getName();
            }
            context = context.getApplicationContext();
            try {
                C1119a.m1473a(context);
            } catch (Exception e) {
                C1132f.m1527a(context, C1130d.EXCEPTION, "init AdsExceptionHandler", e.getMessage(), "");
            }
            mo14984b(!C1061i.m1208f(context));
            m1678q(context);
            C1168l.m1630a(context);
            if (!this.f1275l) {
                C1292c.m2161c(context);
                C1051b.m1136a(context);
                m1671k(context);
                this.f1275l = true;
                C1270g.m2078a("StartAppSDKInternal", 3, "Initialize StartAppSDK with DevID:[" + str + "], AppID:[" + str2 + "]");
                C1061i.m1186a(context, str, str2);
                this.f1264a = sDKAdPreferences;
                C1267e.m2070b(context, "shared_prefs_sdk_ad_prefs", (Serializable) sDKAdPreferences);
                C1299a.m2193b(context);
                boolean booleanValue = C1166k.m1606a(context, "shared_prefs_first_init", (Boolean) true).booleanValue();
                C1270g.m2078a("StartAppSDKInternal", 3, "First Initialization: [" + booleanValue + "]");
                if (booleanValue) {
                    m1674n(context);
                    C1166k.m1615b(context, "totalSessions", (Integer) 0);
                    C1166k.m1616b(context, "firstSessionTime", Long.valueOf(System.currentTimeMillis()));
                    C1166k.m1613b(context, "shared_prefs_first_init", (Boolean) false);
                }
                mo14974a(context, MetaDataRequest.C1224a.LAUNCH);
                m1672l(context);
                m1654a(context, z);
                if (this.f1265b) {
                    m1673m(context);
                }
                m1680r(context);
            }
            m1670j(context);
            mo14998f(context);
        } catch (Exception e2) {
            C1132f.m1527a(context, C1130d.EXCEPTION, "StartAppSDKInternal.intialize - unexpected error occurd", e2.getMessage(), "");
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo14973a(Context context) {
        C1166k.m1613b(context, "periodicInfoEventPaused", (Boolean) true);
        C1051b.m1135a(786564404);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public void mo14983b(Context context) {
        C1166k.m1613b(context, "periodicMetadataPaused", (Boolean) true);
        C1051b.m1135a(586482792);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: c */
    public void mo14987c(Context context) {
        C1166k.m1613b(context, "periodicInfoEventPaused", (Boolean) false);
        C1051b.m1138a(context, C1166k.m1609a(context, "periodicInfoEventTriggerTime", Long.valueOf(C1051b.m1140b(context))).longValue());
    }

    /* access modifiers changed from: package-private */
    /* renamed from: d */
    public void mo14991d(Context context) {
        C1166k.m1613b(context, "periodicMetadataPaused", (Boolean) false);
        C1051b.m1139a(context, Long.valueOf(C1166k.m1609a(context, "periodicMetadataTriggerTime", Long.valueOf(C1051b.m1134a())).longValue()));
    }

    /* renamed from: j */
    private void m1670j(final Context context) {
        C1166k.m1613b(context, "periodicInfoEventPaused", (Boolean) false);
        C1166k.m1613b(context, "periodicMetadataPaused", (Boolean) false);
        C11751 r0 = new C1231d() {
            /* renamed from: a */
            public void mo14204a() {
                if (MetaData.getInstance().isUserAgentEnabled()) {
                    C1174m.this.m1652a(context, TimeUnit.SECONDS.toMillis(MetaData.getInstance().getUserAgentDelayInSeconds()));
                }
                C1051b.m1141c(context);
                C1051b.m1142d(context);
                C1174m.this.mo14995e(context);
                if (Build.VERSION.SDK_INT <= 8) {
                    return;
                }
                if (MetaData.getInstance().getTrueNetEnabled()) {
                    if (!C1174m.this.f1289z) {
                        boolean unused = C1174m.this.f1289z = true;
                        Context context = context;
                        TrueNetSDK.with(context, C1166k.m1610a(context, "shared_prefs_appId", (String) null));
                    }
                    TrueNetSDK.enable(context, true);
                } else if (C1174m.this.f1289z) {
                    TrueNetSDK.enable(context, false);
                }
            }

            /* renamed from: b */
            public void mo14205b() {
                C1270g.m2078a("StartAppSDKInternal", 3, "setPeriodicAlarms: onFailedLoadingMeta");
                if (MetaData.getInstance().isUserAgentEnabled()) {
                    C1174m.this.m1652a(context, TimeUnit.SECONDS.toMillis(10));
                }
            }
        };
        if (MetaData.getInstance().isReady()) {
            r0.mo14204a();
        } else {
            MetaData.getInstance().addMetaDataListener(r0);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: e */
    public void mo14995e(Context context) {
        C1128c cVar = new C1128c(context, false);
        cVar.mo14882c().mo14877c(AdsConstants.f963j);
        cVar.mo14880a();
        if (mo14997e()) {
            C1132f.m1527a(context, C1130d.GENERAL, "packagingType", AdsConstants.f963j, "");
        }
    }

    /* renamed from: k */
    private void m1671k(Context context) {
        MetaData.init(context);
        if (!C1061i.m1193a()) {
            C1098b.m1304a(context);
            if (C1061i.m1194a(16) || C1061i.m1194a(32)) {
                C0901c.m628a(context);
            }
            if (C1061i.m1194a(8)) {
                C0949f.m788a(context);
            }
            if (this.f1265b) {
                C1196d.m1804a(context);
            }
            if (C1061i.m1206e()) {
                C1083a.m1254a(context);
            }
        }
    }

    /* renamed from: l */
    private void m1672l(Context context) {
        Integer a = C1166k.m1608a(context, "shared_prefs_app_version_id", (Integer) -1);
        int d = C1261c.m2043d(context);
        if (a.intValue() > 0 && d > a.intValue()) {
            this.f1280q = true;
        }
        C1166k.m1615b(context, "shared_prefs_app_version_id", Integer.valueOf(d));
    }

    /* renamed from: m */
    private void m1673m(Context context) {
        if (this.f1280q || !C1196d.m1803a().mo15089b().isLocalCache()) {
            C1270g.m2078a("StartAppSDKInternal", 3, "App version changed or disabled in meta - deleting existing cache");
            C1186a.m1756a().mo15069b(context);
        } else if (this.f1266c) {
            C1186a.m1756a().mo15063a(context);
        }
        m1676p(context);
        C1186a.m1756a().mo15073c(context);
    }

    /* renamed from: a */
    private void m1654a(Context context, boolean z) {
        m1668g(false);
        m1666f(false);
        if (!C1261c.m2040b() || !C1061i.m1194a(2)) {
            C1270g.m2078a("StartAppSDKInternal", 6, "Cannot activate return interstitials, cache to disk, ttl reload - api lower than 14");
            return;
        }
        m1668g(z);
        m1666f(true);
        C1270g.m2078a("StartAppSDKInternal", 3, "Return Ads: [" + z + "]");
    }

    /* renamed from: n */
    private void m1674n(final Context context) {
        C1270g.m2078a("StartAppSDKInternal", 3, "Sending Download Event");
        C1303g.m2206a(C1303g.C1307a.DEFAULT, (Runnable) new Runnable() {
            public void run() {
                try {
                    C1161j jVar = new C1161j(context);
                    AdPreferences adPreferences = new AdPreferences();
                    C1061i.m1184a(context, adPreferences);
                    jVar.fillApplicationDetails(context, adPreferences);
                    C1167a.m1619a(context, AdsConstants.m1127a(AdsConstants.ServiceApiType.DOWNLOAD), jVar, (Map<String, String>) null);
                } catch (Exception e) {
                    C1270g.m2077a(6, "Error occured while sending download event", (Throwable) e);
                    C1132f.m1527a(context, C1130d.EXCEPTION, "StartAppSDKInternal.sendDownloadEvent", e.getMessage(), "");
                }
            }
        });
    }

    /* renamed from: f */
    public void mo14998f(Context context) {
        if (C1261c.m2040b()) {
            if (context instanceof Activity) {
                Activity activity = (Activity) context;
                this.f1274k = activity;
                this.f1271h = activity.getApplication();
            } else if (context.getApplicationContext() instanceof Application) {
                this.f1271h = (Application) context.getApplicationContext();
            } else {
                C1270g.m2078a("StartAppSDKInternal", 6, "Cannot register activity life cycle callbacks - context is not an Activity");
                return;
            }
            try {
                if (!(this.f1273j == null || this.f1271h == null)) {
                    m1651a(this.f1271h, this.f1273j);
                    C1270g.m2078a("StartAppSDKInternal", 3, "Unregistered LifeCycle Callbacks");
                }
            } catch (Exception unused) {
            }
            C1270g.m2078a("StartAppSDKInternal", 3, "Registring LifeCycle Callbacks");
            this.f1273j = m1650a(this.f1271h);
            return;
        }
        C1270g.m2078a("StartAppSDKInternal", 6, "Cannot register activity life cycle callbacks - api lower than 14");
    }

    /* renamed from: a */
    public void mo14972a(Activity activity, Bundle bundle) {
        if (bundle == null && this.f1276m != null && activity.getClass().getName().equals(this.f1276m)) {
            this.f1275l = false;
        }
        this.f1282s = bundle;
    }

    /* renamed from: a */
    public void mo14971a(Activity activity) {
        C1270g.m2078a("StartAppSDKInternal", 3, "onActivityStarted [" + activity.getClass().getName() + "]");
        if (C1061i.m1194a(8) && !C1098b.m1303a().mo14786z() && !this.f1286w && !mo14985b(MoPubLog.LOGTAG) && !mo14985b("AdMob") && !this.f1287x && activity.getClass().getName().equals(this.f1276m) && !mo15004i() && this.f1272i.size() == 0) {
            StartAppAd.showSplash(activity, this.f1282s, new SplashConfig(), new AdPreferences(), (SplashHideListener) null, false);
        }
        this.f1287x = true;
        if (this.f1267d) {
            m1667g(activity);
        }
        this.f1269f = false;
        this.f1267d = false;
        if (this.f1272i.get(Integer.valueOf(activity.hashCode())) == null) {
            this.f1272i.put(Integer.valueOf(activity.hashCode()), Integer.valueOf(new Integer(0).intValue() + 1));
            C1270g.m2078a("StartAppSDKInternal", 3, "Activity Added:[" + activity.getClass().getName() + "]");
            return;
        }
        C1270g.m2078a("StartAppSDKInternal", 3, "Activity [" + activity.getClass().getName() + "] already exists");
    }

    /* renamed from: b */
    public void mo14981b(Activity activity) {
        if (this.f1265b && this.f1268e) {
            this.f1268e = false;
            C1186a.m1756a().mo15068b();
        }
        if (this.f1277n) {
            this.f1277n = false;
            C1168l.m1638c(activity.getApplicationContext());
        }
        this.f1274k = activity;
    }

    /* renamed from: b */
    public void mo14980b() {
        this.f1277n = true;
        this.f1268e = true;
    }

    /* renamed from: c */
    public boolean mo14989c() {
        return this.f1279p;
    }

    /* renamed from: a */
    public void mo14978a(boolean z) {
        this.f1279p = z;
    }

    /* renamed from: d */
    public boolean mo14993d() {
        return this.f1278o;
    }

    /* renamed from: b */
    public void mo14984b(boolean z) {
        this.f1278o = z;
    }

    /* renamed from: e */
    public boolean mo14997e() {
        return this.f1280q;
    }

    /* renamed from: c */
    public void mo14986c(Activity activity) {
        this.f1270g = System.currentTimeMillis();
        this.f1274k = null;
    }

    /* renamed from: d */
    public void mo14990d(Activity activity) {
        C1270g.m2078a("StartAppSDKInternal", 3, "onActivityStopped [" + activity.getClass().getName() + "]");
        Integer num = this.f1272i.get(Integer.valueOf(activity.hashCode()));
        if (num != null) {
            Integer valueOf = Integer.valueOf(num.intValue() - 1);
            if (valueOf.intValue() == 0) {
                this.f1272i.remove(Integer.valueOf(activity.hashCode()));
            } else {
                this.f1272i.put(Integer.valueOf(activity.hashCode()), valueOf);
            }
            C1270g.m2078a("StartAppSDKInternal", 3, "Activity removed:[" + activity.getClass().getName() + "]");
            if (this.f1272i.size() == 0) {
                if (!this.f1269f) {
                    m1665f(activity);
                }
                if (this.f1265b) {
                    C1186a.m1756a().mo15064a(activity.getApplicationContext(), this.f1269f);
                    this.f1268e = true;
                    return;
                }
                return;
            }
            return;
        }
        C1270g.m2078a("StartAppSDKInternal", 3, "Activity hadn't been found:[" + activity.getClass().getName() + "]");
    }

    /* renamed from: e */
    public void mo14994e(Activity activity) {
        if (activity.getClass().getName().equals(this.f1276m)) {
            this.f1287x = false;
        }
        if (this.f1272i.size() == 0) {
            this.f1267d = false;
        }
    }

    /* renamed from: f */
    public boolean mo14999f() {
        Activity activity = this.f1274k;
        if (activity != null) {
            return activity.isTaskRoot();
        }
        return true;
    }

    /* renamed from: g */
    public String mo15001g() {
        return this.f1276m;
    }

    /* renamed from: h */
    public boolean mo15003h() {
        return this.f1284u;
    }

    /* renamed from: i */
    public boolean mo15004i() {
        return this.f1285v;
    }

    /* renamed from: c */
    public void mo14988c(boolean z) {
        this.f1285v = z;
    }

    @Deprecated
    /* renamed from: j */
    public void mo15005j() {
        mo14992d(false);
    }

    /* renamed from: d */
    public void mo14992d(boolean z) {
        this.f1286w = !z;
        if (!z) {
            C1186a.m1756a().mo15065a(AdPreferences.Placement.INAPP_SPLASH);
        }
    }

    /* renamed from: k */
    public boolean mo15006k() {
        return !this.f1286w;
    }

    /* renamed from: l */
    public boolean mo15007l() {
        return this.f1266c && !this.f1267d && !this.f1269f;
    }

    /* renamed from: a */
    public boolean mo14979a(AdPreferences.Placement placement) {
        if (!this.f1266c || this.f1269f) {
            return false;
        }
        if (!this.f1267d) {
            return true;
        }
        if (placement != AdPreferences.Placement.INAPP_RETURN || !C1196d.m1803a().mo15089b().shouldReturnAdLoadInBg()) {
            return false;
        }
        return true;
    }

    /* renamed from: f */
    private void m1666f(boolean z) {
        this.f1266c = z;
    }

    /* renamed from: g */
    private void m1668g(boolean z) {
        this.f1284u = z;
    }

    /* renamed from: m */
    public void mo15008m() {
        this.f1267d = false;
        this.f1269f = true;
    }

    /* renamed from: n */
    public boolean mo15009n() {
        return this.f1266c && this.f1267d;
    }

    /* renamed from: a */
    public void mo14976a(Context context, String str, String str2) {
        if (this.f1281r == null) {
            this.f1281r = new TreeMap();
        }
        this.f1281r.put(str, str2);
        m1675o(context);
    }

    /* renamed from: o */
    private void m1675o(Context context) {
        C1166k.m1611a(context, "sharedPrefsWrappers", this.f1281r);
    }

    /* renamed from: a */
    public String mo14970a(String str) {
        Map<String, String> map = this.f1281r;
        if (map == null) {
            return null;
        }
        return map.get(str);
    }

    /* renamed from: o */
    public Map<String, String> mo15010o() {
        return this.f1281r;
    }

    /* renamed from: b */
    public boolean mo14985b(String str) {
        return mo14970a(str) != null;
    }

    /* renamed from: g */
    public SDKAdPreferences mo15000g(Context context) {
        if (this.f1264a == null) {
            SDKAdPreferences sDKAdPreferences = (SDKAdPreferences) C1267e.m2057a(context, "shared_prefs_sdk_ad_prefs", SDKAdPreferences.class);
            if (sDKAdPreferences == null) {
                this.f1264a = new SDKAdPreferences();
            } else {
                this.f1264a = sDKAdPreferences;
            }
        }
        return this.f1264a;
    }

    /* renamed from: f */
    private void m1665f(Activity activity) {
        this.f1267d = true;
        m1676p(activity);
        if (C1292c.m2158a() != null) {
            C1292c.m2158a().mo15501b(activity);
        }
    }

    /* renamed from: g */
    private void m1667g(Activity activity) {
        if (MetaData.getInstance().canShowAd() && mo15003h() && !C1098b.m1303a().mo14785y() && !C1061i.m1193a() && !mo14989c() && m1679q()) {
            C1136g a = C1186a.m1756a().mo15053a(this.f1283t);
            this.f1288y = a;
            if (a != null && a.isReady()) {
                C1073f a2 = C1098b.m1303a().mo14752F().mo14661a(AdPreferences.Placement.INAPP_RETURN, (String) null);
                if (!a2.mo14664a()) {
                    C1103c.m1383a((Context) activity, ((C0876e) this.f1288y).mo14849l(), (String) null, a2.mo14666c());
                    if (Constants.m1978a().booleanValue()) {
                        C1274i.m2100a().mo15477a(activity, a2.mo14665b());
                    }
                } else if (this.f1288y.mo13830a((String) null)) {
                    C1069b.m1217a().mo14655a(new C1068a(AdPreferences.Placement.INAPP_RETURN, (String) null));
                }
            }
        }
        if (C1292c.m2158a() != null) {
            C1292c.m2158a().mo15499a(activity);
        }
        if (m1681r()) {
            mo14974a((Context) activity, MetaDataRequest.C1224a.APP_IDLE);
        }
    }

    /* renamed from: q */
    private boolean m1679q() {
        return System.currentTimeMillis() - this.f1270g > C1098b.m1303a().mo14784x();
    }

    /* renamed from: r */
    private boolean m1681r() {
        return System.currentTimeMillis() - this.f1270g > MetaData.getInstance().getSessionMaxBackgroundTime();
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo14974a(Context context, MetaDataRequest.C1224a aVar) {
        C1059g.m1157d().mo14635a(context, aVar);
    }

    /* access modifiers changed from: protected */
    /* renamed from: e */
    public void mo14996e(boolean z) {
        boolean z2 = z && C1261c.m2040b();
        m1668g(z2);
        if (!z2) {
            C1186a.m1756a().mo15065a(AdPreferences.Placement.INAPP_RETURN);
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo14975a(Context context, String str, long j, boolean z, boolean z2) {
        if (!TextUtils.isEmpty(str)) {
            StringBuilder sb = new StringBuilder();
            String str2 = "1";
            sb.append(z ? str2 : "0");
            sb.append(z2 ? "M" : "A");
            C1132f.m1527a(context, C1130d.USER_CONSENT, str, sb.toString(), "");
            if (str.toLowerCase().equals("pas")) {
                if (!z) {
                    str2 = "0";
                }
                C1166k.m1617b(context, "USER_CONSENT_PERSONALIZED_ADS_SERVING", str2);
                C1059g.m1157d().mo14635a(context, MetaDataRequest.C1224a.PAS);
                return;
            }
            return;
        }
        C1270g.m2078a("StartAppSDKInternal", 6, "setUserConsent: empty consentType");
    }

    /* renamed from: h */
    public void mo15002h(Context context) {
        m1661b(context, "android.permission.ACCESS_FINE_LOCATION", "USER_CONSENT_FINE_LOCATION");
        m1661b(context, "android.permission.ACCESS_COARSE_LOCATION", "USER_CONSENT_COARSE_LOCATION");
    }

    /* renamed from: b */
    private void m1661b(Context context, String str, String str2) {
        boolean booleanValue = C1166k.m1606a(context, str2, (Boolean) false).booleanValue();
        boolean a = C1261c.m2031a(context, str);
        if (booleanValue != a) {
            C1166k.m1613b(context, str2, Boolean.valueOf(a));
            mo14975a(context, str, System.currentTimeMillis(), a, false);
        }
    }

    /* renamed from: p */
    private void m1676p(Context context) {
        m1653a(context, new AdPreferences());
    }

    /* renamed from: a */
    private void m1653a(Context context, AdPreferences adPreferences) {
        if (mo15003h() && !C1098b.m1303a().mo14785y()) {
            this.f1283t = C1186a.m1756a().mo15058a(context, adPreferences);
        }
    }

    /* renamed from: q */
    private static void m1678q(Context context) {
        TreeMap treeMap = new TreeMap();
        if (m1685u()) {
            treeMap.put("Cordova", C1061i.m1199b());
        }
        if (m1683s()) {
            treeMap.put("AdMob", m1664d("com.startapp.android.mediation.admob"));
        }
        if (m1684t()) {
            treeMap.put(MoPubLog.LOGTAG, m1664d("com.mopub.mobileads"));
        }
        if (m1686v() && !m1649a().mo15010o().containsKey("B4A")) {
            treeMap.put(MoPubLog.LOGTAG, "0");
        }
        if (!treeMap.isEmpty()) {
            C1166k.m1611a(context, "sharedPrefsWrappers", (Map<String, String>) treeMap);
        }
    }

    /* renamed from: s */
    private static boolean m1683s() {
        return m1663c("com.startapp.android.mediation.admob.StartAppCustomEvent");
    }

    /* renamed from: t */
    private static boolean m1684t() {
        return m1663c("com.mopub.mobileads.StartAppCustomEventInterstitial");
    }

    /* renamed from: p */
    public static boolean m1677p() {
        return m1649a().mo14970a("Unity") != null;
    }

    /* renamed from: u */
    private static boolean m1685u() {
        return m1663c("org.apache.cordova.CordovaPlugin");
    }

    /* renamed from: v */
    private static boolean m1686v() {
        return m1663c("anywheresoftware.b4a.BA");
    }

    /* renamed from: c */
    private static boolean m1663c(String str) {
        try {
            Class.forName(str);
            return true;
        } catch (ClassNotFoundException | Exception unused) {
            return false;
        }
    }

    /* renamed from: d */
    private static String m1664d(String str) {
        try {
            return (String) Class.forName(str + ".StartAppConstants").getField("WRAPPER_VERSION").get((Object) null);
        } catch (Exception unused) {
            return "0";
        }
    }

    /* renamed from: a */
    public static Object m1650a(Application application) {
        C11773 r0 = new Application.ActivityLifecycleCallbacks() {
            public void onActivityStopped(Activity activity) {
                C1270g.m2078a("StartAppSDKInternal", 3, "onActivityStopped [" + activity.getClass().getName() + "]");
                C1174m.m1649a().mo14990d(activity);
            }

            public void onActivityStarted(Activity activity) {
                C1270g.m2078a("StartAppSDKInternal", 3, "onActivityStarted [" + activity.getClass().getName() + "]");
                C1174m.m1649a().mo14971a(activity);
            }

            public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
                C1270g.m2078a("StartAppSDKInternal", 3, "onActivitySaveInstanceState [" + activity.getClass().getName() + "]");
                C1174m.m1649a().mo14982b(activity, bundle);
            }

            public void onActivityResumed(Activity activity) {
                C1270g.m2078a("StartAppSDKInternal", 3, "onActivityResumed [" + activity.getClass().getName() + "]");
                C1174m.m1649a().mo14981b(activity);
            }

            public void onActivityPaused(Activity activity) {
                C1270g.m2078a("StartAppSDKInternal", 3, "onActivityPaused [" + activity.getClass().getName() + "]");
                C1174m.m1649a().mo14986c(activity);
            }

            public void onActivityDestroyed(Activity activity) {
                C1270g.m2078a("StartAppSDKInternal", 3, "onActivityDestroyed [" + activity.getClass().getName() + "]");
                C1174m.m1649a().mo14994e(activity);
            }

            public void onActivityCreated(Activity activity, Bundle bundle) {
                C1270g.m2078a("StartAppSDKInternal", 3, "onActivityCreated [" + activity.getClass().getName() + ", " + bundle + "]");
                C1174m.m1649a().mo14972a(activity, bundle);
                if (C1061i.m1194a(2)) {
                    C1123f.m1476a().mo14859a(activity, bundle);
                }
            }
        };
        application.registerActivityLifecycleCallbacks(r0);
        return r0;
    }

    /* renamed from: a */
    public static void m1651a(Application application, Object obj) {
        application.unregisterActivityLifecycleCallbacks((Application.ActivityLifecycleCallbacks) obj);
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public void m1652a(final Context context, long j) {
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            public void run() {
                try {
                    C1166k.m1617b(context, "User-Agent", new WebView(context).getSettings().getUserAgentString());
                } catch (Exception e) {
                    C1132f.m1527a(context, C1130d.EXCEPTION, "NetworkUtils.saveUserAgent - Webview failed", e.getMessage(), "");
                }
            }
        }, j);
    }

    /* renamed from: r */
    private void m1680r(final Context context) {
        C1303g.m2206a(C1303g.C1307a.DEFAULT, (Runnable) new Runnable() {
            public void run() {
                String i;
                if (Build.VERSION.SDK_INT < 18 || (i = C1174m.m1682s(context)) == null) {
                    C1174m.this.m1662b(context, false);
                    return;
                }
                ServiceConnection unused = C1174m.this.f1263A = new ServiceConnection() {
                    public void onServiceDisconnected(ComponentName componentName) {
                    }

                    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                        C1174m.this.m1662b(context, true);
                        context.unbindService(C1174m.this.f1263A);
                    }
                };
                try {
                    Intent intent = new Intent(CustomTabsService.ACTION_CUSTOM_TABS_CONNECTION);
                    intent.setPackage(i);
                    if (!context.bindService(intent, C1174m.this.f1263A, 33)) {
                        C1174m.this.m1662b(context, false);
                    }
                } catch (Exception e) {
                    C1270g.m2079a("StartAppSDKInternal", 6, "failed to check checkChromeTabsSupport", e);
                    C1174m.this.m1662b(context, false);
                }
            }
        });
    }

    /* access modifiers changed from: private */
    /* renamed from: s */
    public static String m1682s(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("http://www.example.com"));
            ResolveInfo resolveActivity = packageManager.resolveActivity(intent, 0);
            String str = resolveActivity != null ? resolveActivity.activityInfo.packageName : null;
            List<ResolveInfo> queryIntentActivities = packageManager.queryIntentActivities(intent, 0);
            ArrayList arrayList = new ArrayList();
            for (ResolveInfo next : queryIntentActivities) {
                Intent intent2 = new Intent();
                intent2.setAction(CustomTabsService.ACTION_CUSTOM_TABS_CONNECTION);
                intent2.setPackage(next.activityInfo.packageName);
                if (packageManager.resolveService(intent2, 0) != null) {
                    arrayList.add(next.activityInfo.packageName);
                }
            }
            if (arrayList.isEmpty()) {
                return null;
            }
            if (arrayList.size() == 1) {
                return (String) arrayList.get(0);
            }
            if (!TextUtils.isEmpty(str) && !m1657a(context, intent) && arrayList.contains(str)) {
                return str;
            }
            if (arrayList.contains("com.android.chrome")) {
                return "com.android.chrome";
            }
            return null;
        } catch (Exception unused) {
            C1270g.m2078a("StartAppSDKInternal", 6, "Exception inside getPackageNameToUse");
            return null;
        }
    }

    /* renamed from: a */
    private static boolean m1657a(Context context, Intent intent) {
        try {
            List<ResolveInfo> queryIntentActivities = context.getPackageManager().queryIntentActivities(intent, 64);
            if (queryIntentActivities != null) {
                if (queryIntentActivities.size() != 0) {
                    for (ResolveInfo next : queryIntentActivities) {
                        IntentFilter intentFilter = next.filter;
                        if (intentFilter != null) {
                            if (intentFilter.countDataAuthorities() == 0) {
                                continue;
                            } else if (intentFilter.countDataPaths() != 0) {
                                if (next.activityInfo != null) {
                                    return true;
                                }
                            }
                        }
                    }
                    return false;
                }
            }
            return false;
        } catch (RuntimeException unused) {
            C1270g.m2078a("StartAppSDKInternal", 6, "Runtime exception while getting specialized handlers");
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: b */
    public void m1662b(Context context, boolean z) {
        C1166k.m1613b(context, "chromeTabs", Boolean.valueOf(z));
    }
}

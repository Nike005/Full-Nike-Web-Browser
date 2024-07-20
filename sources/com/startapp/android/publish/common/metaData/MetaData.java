package com.startapp.android.publish.common.metaData;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import com.startapp.android.publish.adsCommon.AdsConstants;
import com.startapp.android.publish.adsCommon.C1166k;
import com.startapp.android.publish.adsCommon.Utils.C1049a;
import com.startapp.android.publish.adsCommon.Utils.C1061i;
import com.startapp.android.publish.adsCommon.p033f.C1126a;
import com.startapp.android.publish.adsCommon.p033f.C1130d;
import com.startapp.android.publish.adsCommon.p033f.C1132f;
import com.startapp.android.publish.common.metaData.MetaDataRequest;
import com.startapp.android.publish.common.model.AdPreferences;
import com.startapp.common.C1249a;
import com.startapp.common.Constants;
import com.startapp.common.p043a.C1261c;
import com.startapp.common.p043a.C1267e;
import com.startapp.common.p043a.C1270g;
import com.startapp.common.p046c.C1295b;
import com.startapp.common.p046c.C5303f;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/* compiled from: StartAppSDK */
public class MetaData implements Serializable {
    public static final String DEFAULT_AD_PLATFORM_HOST = "https://req.startappservice.com/1.5/";
    public static final boolean DEFAULT_ALWAYS_SEND_TOKEN = true;
    public static final String DEFAULT_ASSETS_BASE_URL_SECURED = "";
    public static final boolean DEFAULT_BT_ENABLED = false;
    public static final boolean DEFAULT_COMPRESSION_ENABLED = false;
    public static final boolean DEFAULT_INAPPBROWSER = true;
    public static final Set<String> DEFAULT_INSTALLERS_LIST = new HashSet(Arrays.asList(new String[]{Constants.f1469a}));
    public static final Set<Integer> DEFAULT_INVALID_NETWORK_CODES_INFO_EVENTS = new HashSet(Arrays.asList(new Integer[]{204}));
    public static final long DEFAULT_LAST_KNOWN_LOCATION_THRESHOLD = 30;
    public static final String DEFAULT_LOCATION_SOURCE = "API";
    public static final String DEFAULT_METADATA_HOST = "https://init.startappservice.com/1.5/";
    public static final boolean DEFAULT_OM_SDK_STATE = false;
    public static final boolean DEFAULT_PERIODIC_INFOEVENT_ENABLED = false;
    public static final int DEFAULT_PERIODIC_INFOEVENT_INTERVAL = 360;
    public static final int[] DEFAULT_PERIODIC_INFOEVENT_INTERVALS = {60, 60, 240};
    public static final boolean DEFAULT_PERIODIC_INFOEVENT_ON_RUN_TIME = false;
    public static final boolean DEFAULT_PERIODIC_METADATA_ENABLED = false;
    public static final int DEFAULT_PERIODIC_METADATA_INTERVAL = 360;
    public static final Set<String> DEFAULT_PRE_INSTALLED_PACKAGES = new HashSet(Arrays.asList(new String[]{"com.facebook.katana", "com.yandex.browser"}));
    public static final String DEFAULT_PROFILE_ID = null;
    public static final int DEFAULT_SESSION_MAX_BACKGROUND_TIME = 1800;
    public static final boolean DEFAULT_SIMPLE_TOKEN_ENABLED = true;
    public static final int DEFAULT_STOP_AUTO_LOAD_AMOUNT = 3;
    public static final int DEFAULT_STOP_AUTO_LOAD_PRE_CAHE_AMOUNT = 3;
    public static final boolean DEFAULT_WF_SCAN_ENABLED = false;
    public static final String KEY_METADATA = "metaData";
    private static transient MetaData instance = new MetaData();
    private static transient Object lock = new Object();
    private static final long serialVersionUID = 1;
    private static transient C1228c task;
    private long IABDisplayImpressionDelayInSeconds = 1;
    private long IABVideoImpressionDelayInSeconds = 2;
    @C5303f(mo45477a = true)
    private C1237h SimpleToken = new C1237h();
    private boolean SupportIABViewability = true;
    private String adPlatformBannerHostSecured;
    public String adPlatformHostSecured = DEFAULT_AD_PLATFORM_HOST;
    private String adPlatformNativeHostSecured;
    private String adPlatformOverlayHostSecured;
    private String adPlatformReturnHostSecured;
    private String adPlatformSplashHostSecured;
    private boolean alwaysSendToken = true;
    @C5303f(mo45477a = true)
    public C1126a analytics = new C1126a();
    private String assetsBaseUrlSecured = "";
    @C5303f(mo45477a = true)
    private C1227b btConfig = new C1227b();
    private boolean btEnabled = false;
    private boolean chromeCustomeTabsExternal = true;
    private boolean chromeCustomeTabsInternal = true;
    private boolean compressionEnabled = false;
    private boolean dns = false;
    private boolean inAppBrowser = true;
    @C5303f(mo45478b = C1223b.class)
    private C1223b inAppBrowserPreLoad;
    @C5303f(mo45478b = HashSet.class)
    private Set<String> installersList = DEFAULT_INSTALLERS_LIST;
    @C5303f(mo45478b = HashSet.class)
    private Set<Integer> invalidForRetry = new HashSet();
    @C5303f(mo45478b = HashSet.class)
    private Set<Integer> invalidNetworkCodesInfoEvents = DEFAULT_INVALID_NETWORK_CODES_INFO_EVENTS;
    private boolean isToken1Mandatory = true;
    private transient boolean loading = false;
    @C5303f(mo45477a = true)
    private LocationConfig location = new LocationConfig();
    public String metaDataHostSecured = DEFAULT_METADATA_HOST;
    private transient List<C1231d> metaDataListeners = new ArrayList();
    private String metadataUpdateVersion = AdsConstants.f961h;
    private boolean omSdkEnabled = false;
    private int[] periodicEventIntMin = DEFAULT_PERIODIC_INFOEVENT_INTERVALS;
    private boolean periodicInfoEventEnabled = false;
    private int periodicInfoEventIntervalInMinutes = 360;
    private boolean periodicInfoEventOnRunTimeEnabled = false;
    private boolean periodicMetaDataEnabled = false;
    private int periodicMetaDataIntervalInMinutes = 360;
    @C5303f(mo45478b = HashSet.class)
    private Set<String> preInstalledPackages = DEFAULT_PRE_INSTALLED_PACKAGES;
    private String profileId = DEFAULT_PROFILE_ID;
    private transient boolean ready = false;
    @C5303f(mo45477a = true)
    private C1236g sensorsConfig = new C1236g();
    private int sessionMaxBackgroundTime = DEFAULT_SESSION_MAX_BACKGROUND_TIME;
    private boolean simpleToken2 = true;
    private int stopAutoLoadAmount = 3;
    private int stopAutoLoadPreCacheAmount = 3;
    private boolean trueNetEnabled = false;
    private long userAgentDelayInSeconds = 5;
    private boolean userAgentEnabled = true;
    private boolean webViewSecured = true;
    private boolean wfScanEnabled = false;

    /* renamed from: com.startapp.android.publish.common.metaData.MetaData$b */
    /* compiled from: StartAppSDK */
    public enum C1223b {
        DISABLED,
        CONTENT,
        FULL
    }

    private MetaData() {
    }

    public C1237h getSimpleTokenConfig() {
        return this.SimpleToken;
    }

    /* access modifiers changed from: protected */
    public void setSimpleTokenConfig(C1237h hVar) {
        this.SimpleToken = hVar;
    }

    public static void init(Context context) {
        MetaData metaData = (MetaData) C1267e.m2057a(context, "StartappMetadata", MetaData.class);
        MetaData metaData2 = new MetaData();
        if (metaData != null) {
            boolean a = C1061i.m1198a(metaData, metaData2);
            if (!metaData.isMetaDataVersionChanged() && a) {
                C1132f.m1527a(context, C1130d.METADATA_NULL, "MetaData", "", "");
            }
            metaData.initTransientFields();
            instance = metaData;
        } else {
            instance = metaData2;
        }
        getInstance().applyAdPlatformProtocolToHosts();
    }

    public static void update(Context context, MetaData metaData) {
        synchronized (lock) {
            metaData.metaDataListeners = getInstance().metaDataListeners;
            instance = metaData;
            if (Constants.m1978a().booleanValue()) {
                C1270g.m2076a(3, "MetaData received:");
                C1270g.m2076a(3, C1295b.m2180a(metaData));
            }
            getInstance().applyAdPlatformProtocolToHosts();
            metaData.metadataUpdateVersion = AdsConstants.f961h;
            C1267e.m2070b(context, "StartappMetadata", (Serializable) metaData);
            C1270g.m2076a(3, "MetaData saved:");
            getInstance().loading = false;
            getInstance().ready = true;
            if (getInstance().metaDataListeners != null) {
                ArrayList<C1231d> arrayList = new ArrayList<>(getInstance().metaDataListeners);
                getInstance().metaDataListeners.clear();
                for (C1231d a : arrayList) {
                    a.mo14204a();
                }
            }
            C1166k.m1615b(context, "totalSessions", Integer.valueOf(C1166k.m1608a(context, "totalSessions", (Integer) 0).intValue() + 1));
            task = null;
        }
    }

    public static void failedLoading() {
        ArrayList<C1231d> arrayList;
        synchronized (lock) {
            if (getInstance().metaDataListeners != null) {
                arrayList = new ArrayList<>(getInstance().metaDataListeners);
                getInstance().metaDataListeners.clear();
            } else {
                arrayList = null;
            }
            getInstance().loading = false;
        }
        if (arrayList != null) {
            for (C1231d b : arrayList) {
                b.mo14205b();
            }
        }
    }

    public static boolean isLoadedFromServer(Context context) {
        return context.getFileStreamPath("StartappMetadata").exists();
    }

    public void loadFromServer(Context context, AdPreferences adPreferences, MetaDataRequest.C1224a aVar, boolean z, C1231d dVar) {
        loadFromServer(context, adPreferences, aVar, z, dVar, false);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0018, code lost:
        if (r6 == false) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x001a, code lost:
        if (r7 == null) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x001c, code lost:
        r7.mo14204a();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0051, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:?, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void loadFromServer(android.content.Context r3, com.startapp.android.publish.common.model.AdPreferences r4, com.startapp.android.publish.common.metaData.MetaDataRequest.C1224a r5, boolean r6, com.startapp.android.publish.common.metaData.C1231d r7, boolean r8) {
        /*
            r2 = this;
            if (r6 != 0) goto L_0x0007
            if (r7 == 0) goto L_0x0007
            r7.mo14204a()
        L_0x0007:
            java.lang.Object r0 = lock
            monitor-enter(r0)
            com.startapp.android.publish.common.metaData.MetaData r1 = getInstance()     // Catch:{ all -> 0x0052 }
            boolean r1 = r1.isReady()     // Catch:{ all -> 0x0052 }
            if (r1 == 0) goto L_0x0020
            if (r8 == 0) goto L_0x0017
            goto L_0x0020
        L_0x0017:
            monitor-exit(r0)     // Catch:{ all -> 0x0052 }
            if (r6 == 0) goto L_0x001f
            if (r7 == 0) goto L_0x001f
            r7.mo14204a()
        L_0x001f:
            return
        L_0x0020:
            com.startapp.android.publish.common.metaData.MetaData r1 = getInstance()     // Catch:{ all -> 0x0052 }
            boolean r1 = r1.isLoading()     // Catch:{ all -> 0x0052 }
            if (r1 == 0) goto L_0x002c
            if (r8 == 0) goto L_0x0045
        L_0x002c:
            r8 = 1
            r2.loading = r8     // Catch:{ all -> 0x0052 }
            r8 = 0
            r2.ready = r8     // Catch:{ all -> 0x0052 }
            com.startapp.android.publish.common.metaData.c r8 = task     // Catch:{ all -> 0x0052 }
            if (r8 == 0) goto L_0x003b
            com.startapp.android.publish.common.metaData.c r8 = task     // Catch:{ all -> 0x0052 }
            r8.mo15247b()     // Catch:{ all -> 0x0052 }
        L_0x003b:
            com.startapp.android.publish.common.metaData.c r8 = new com.startapp.android.publish.common.metaData.c     // Catch:{ all -> 0x0052 }
            r8.<init>(r3, r4, r5)     // Catch:{ all -> 0x0052 }
            task = r8     // Catch:{ all -> 0x0052 }
            r8.mo15245a()     // Catch:{ all -> 0x0052 }
        L_0x0045:
            if (r6 == 0) goto L_0x0050
            if (r7 == 0) goto L_0x0050
            com.startapp.android.publish.common.metaData.MetaData r3 = getInstance()     // Catch:{ all -> 0x0052 }
            r3.addMetaDataListener(r7)     // Catch:{ all -> 0x0052 }
        L_0x0050:
            monitor-exit(r0)     // Catch:{ all -> 0x0052 }
            return
        L_0x0052:
            r3 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0052 }
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.startapp.android.publish.common.metaData.MetaData.loadFromServer(android.content.Context, com.startapp.android.publish.common.model.AdPreferences, com.startapp.android.publish.common.metaData.MetaDataRequest$a, boolean, com.startapp.android.publish.common.metaData.d, boolean):void");
    }

    public void addMetaDataListener(C1231d dVar) {
        synchronized (lock) {
            this.metaDataListeners.add(dVar);
        }
    }

    public void removeMetaDataListener(C1231d dVar) {
        synchronized (lock) {
            this.metaDataListeners.remove(dVar);
        }
    }

    public static Object getLock() {
        return lock;
    }

    public boolean isLoading() {
        return this.loading;
    }

    public boolean isReady() {
        return this.ready;
    }

    public void setReady(boolean z) {
        this.ready = z;
    }

    private boolean isMetaDataVersionChanged() {
        return !AdsConstants.f961h.equals(this.metadataUpdateVersion);
    }

    public String getAssetsBaseUrl() {
        String str = this.assetsBaseUrlSecured;
        return str != null ? str : "";
    }

    public boolean isPeriodicMetaDataEnabled() {
        return this.periodicMetaDataEnabled;
    }

    public void setPeriodicMetaDataEnabled(boolean z) {
        this.periodicMetaDataEnabled = z;
    }

    public int getPeriodicMetaDataInterval() {
        return this.periodicMetaDataIntervalInMinutes;
    }

    public void setPeriodicMetaDataInterval(int i) {
        this.periodicMetaDataIntervalInMinutes = i;
    }

    public boolean isPeriodicInfoEventEnabled() {
        return this.periodicInfoEventEnabled;
    }

    public boolean isPeriodicInfoEventOnRunTimeEnabled() {
        return this.periodicInfoEventOnRunTimeEnabled;
    }

    public void setPeriodicInfoEventEnabled(boolean z) {
        this.periodicInfoEventEnabled = z;
    }

    public int getPeriodicInfoEventIntervalInMinutes(Context context) {
        int[] iArr = this.periodicEventIntMin;
        if (iArr == null || iArr.length < 3) {
            this.periodicEventIntMin = DEFAULT_PERIODIC_INFOEVENT_INTERVALS;
        }
        if (C1261c.m2031a(context, "android.permission.ACCESS_FINE_LOCATION")) {
            int i = this.periodicEventIntMin[0];
            if (i <= 0) {
                return DEFAULT_PERIODIC_INFOEVENT_INTERVALS[0];
            }
            return i;
        } else if (!C1261c.m2031a(context, "android.permission.ACCESS_COARSE_LOCATION")) {
            return this.periodicEventIntMin[2];
        } else {
            int i2 = this.periodicEventIntMin[1];
            if (i2 <= 0) {
                return DEFAULT_PERIODIC_INFOEVENT_INTERVALS[1];
            }
            return i2;
        }
    }

    public void setPeriodicInfoEventIntervalInMinutes(int i) {
        this.periodicInfoEventIntervalInMinutes = i;
    }

    public Set<Integer> getInvalidForRetry() {
        Set<Integer> set = this.invalidForRetry;
        if (set != null) {
            return set;
        }
        return new HashSet();
    }

    public Set<Integer> getInvalidNetworkCodesInfoEvents() {
        Set<Integer> set = this.invalidNetworkCodesInfoEvents;
        if (set != null) {
            return set;
        }
        return DEFAULT_INVALID_NETWORK_CODES_INFO_EVENTS;
    }

    public String getMetaDataHost() {
        if (AdsConstants.OVERRIDE_HOST != null) {
            return AdsConstants.OVERRIDE_HOST;
        }
        return this.metaDataHostSecured;
    }

    public String getAdPlatformHost() {
        if (AdsConstants.OVERRIDE_HOST != null) {
            return AdsConstants.OVERRIDE_HOST;
        }
        String str = this.adPlatformHostSecured;
        return str != null ? str : DEFAULT_AD_PLATFORM_HOST;
    }

    /* renamed from: com.startapp.android.publish.common.metaData.MetaData$1 */
    /* compiled from: StartAppSDK */
    static /* synthetic */ class C12211 {

        /* renamed from: a */
        static final /* synthetic */ int[] f1380a;

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
                f1380a = r0
                com.startapp.android.publish.common.model.AdPreferences$Placement r1 = com.startapp.android.publish.common.model.AdPreferences.Placement.INAPP_BANNER     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = f1380a     // Catch:{ NoSuchFieldError -> 0x001d }
                com.startapp.android.publish.common.model.AdPreferences$Placement r1 = com.startapp.android.publish.common.model.AdPreferences.Placement.INAPP_OVERLAY     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = f1380a     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.startapp.android.publish.common.model.AdPreferences$Placement r1 = com.startapp.android.publish.common.model.AdPreferences.Placement.INAPP_NATIVE     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = f1380a     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.startapp.android.publish.common.model.AdPreferences$Placement r1 = com.startapp.android.publish.common.model.AdPreferences.Placement.INAPP_RETURN     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = f1380a     // Catch:{ NoSuchFieldError -> 0x003e }
                com.startapp.android.publish.common.model.AdPreferences$Placement r1 = com.startapp.android.publish.common.model.AdPreferences.Placement.INAPP_SPLASH     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.startapp.android.publish.common.metaData.MetaData.C12211.<clinit>():void");
        }
    }

    public String getAdPlatformHost(AdPreferences.Placement placement) {
        int i = C12211.f1380a[placement.ordinal()];
        if (i == 1) {
            String str = this.adPlatformBannerHostSecured;
            return str != null ? str : getAdPlatformHost();
        } else if (i == 2) {
            String str2 = this.adPlatformOverlayHostSecured;
            return str2 != null ? str2 : getAdPlatformHost();
        } else if (i == 3) {
            String str3 = this.adPlatformNativeHostSecured;
            return str3 != null ? str3 : getAdPlatformHost();
        } else if (i == 4) {
            String str4 = this.adPlatformReturnHostSecured;
            return str4 != null ? str4 : getAdPlatformHost();
        } else if (i != 5) {
            return getAdPlatformHost();
        } else {
            String str5 = this.adPlatformSplashHostSecured;
            return str5 != null ? str5 : getAdPlatformHost();
        }
    }

    public String getHostForWebview() {
        return getHostForWebview(getInstance().getAdPlatformHost(), Build.VERSION.SDK_INT, this.webViewSecured);
    }

    public static String getHostForWebview(String str, int i, boolean z) {
        int indexOf;
        String str2 = (i > 26 || z) ? com.mopub.common.Constants.HTTPS : com.mopub.common.Constants.HTTP;
        if (str.startsWith(str2 + "://") || (indexOf = str.indexOf(58)) == -1) {
            return str;
        }
        return str2 + str.substring(indexOf);
    }

    public long getSessionMaxBackgroundTime() {
        return TimeUnit.SECONDS.toMillis((long) this.sessionMaxBackgroundTime);
    }

    public Set<String> getInstallersList() {
        return this.installersList;
    }

    public void setInstallersList(Set<String> set) {
        this.installersList = set;
    }

    public Set<String> getPreInstalledPackages() {
        Set<String> set = this.preInstalledPackages;
        if (set == null) {
            set = DEFAULT_PRE_INSTALLED_PACKAGES;
        }
        return Collections.unmodifiableSet(set);
    }

    public void setPreInstalledPackages(Set<String> set) {
        this.preInstalledPackages = set;
    }

    public boolean isSimpleToken2() {
        return this.simpleToken2;
    }

    public void setSimpleToken2(boolean z) {
        this.simpleToken2 = z;
    }

    public boolean isAlwaysSendToken() {
        return this.alwaysSendToken;
    }

    public void setAlwaysSendToken(boolean z) {
        this.alwaysSendToken = z;
    }

    public boolean isToken1Mandatory() {
        return this.isToken1Mandatory;
    }

    public boolean isCompressionEnabled() {
        return this.compressionEnabled;
    }

    public void setCompressionEnabled(boolean z) {
        this.compressionEnabled = z;
    }

    public boolean isInAppBrowser() {
        return C1061i.m1194a(256) && this.inAppBrowser;
    }

    public void setInAppBrowser(boolean z) {
        this.inAppBrowser = z;
    }

    public C1223b getInAppBrowserPreLoad() {
        return this.inAppBrowserPreLoad;
    }

    public void setInAppBrowserPreLoad(C1223b bVar) {
        this.inAppBrowserPreLoad = bVar;
    }

    public String getProfileId() {
        return this.profileId;
    }

    public C1126a getAnalyticsConfig() {
        return this.analytics;
    }

    public C1236g getSensorsConfig() {
        return this.sensorsConfig;
    }

    public C1227b getBluetoothConfig() {
        return this.btConfig;
    }

    public LocationConfig getLocationConfig() {
        return this.location;
    }

    public boolean isWfScanEnabled() {
        return this.wfScanEnabled;
    }

    public static MetaData getInstance() {
        return instance;
    }

    public long getIABDisplayImpressionDelayInSeconds() {
        return this.IABDisplayImpressionDelayInSeconds;
    }

    public long getIABVideoImpressionDelayInSeconds() {
        return this.IABVideoImpressionDelayInSeconds;
    }

    public long getUserAgentDelayInSeconds() {
        return this.userAgentDelayInSeconds;
    }

    public boolean isUserAgentEnabled() {
        return this.userAgentEnabled;
    }

    public boolean isSupportIABViewability() {
        return this.SupportIABViewability;
    }

    public void applyAdPlatformProtocolToHosts() {
        this.adPlatformHostSecured = replaceAdProtocol(this.adPlatformHostSecured, DEFAULT_AD_PLATFORM_HOST);
        this.metaDataHostSecured = replaceAdProtocol(this.metaDataHostSecured, DEFAULT_METADATA_HOST);
        this.adPlatformBannerHostSecured = replaceAdProtocol(this.adPlatformBannerHostSecured, (String) null);
        this.adPlatformSplashHostSecured = replaceAdProtocol(this.adPlatformSplashHostSecured, (String) null);
        this.adPlatformReturnHostSecured = replaceAdProtocol(this.adPlatformReturnHostSecured, (String) null);
        this.adPlatformOverlayHostSecured = replaceAdProtocol(this.adPlatformOverlayHostSecured, (String) null);
        this.adPlatformNativeHostSecured = replaceAdProtocol(this.adPlatformNativeHostSecured, (String) null);
    }

    public boolean canShowAd() {
        return !this.dns;
    }

    public int getStopAutoLoadAmount() {
        return this.stopAutoLoadAmount;
    }

    public int getStopAutoLoadPreCacheAmount() {
        return this.stopAutoLoadPreCacheAmount;
    }

    public boolean getTrueNetEnabled() {
        return this.trueNetEnabled;
    }

    public boolean getChromeCustomeTabsInternal() {
        return this.chromeCustomeTabsInternal;
    }

    public boolean getChromeCustomeTabsExternal() {
        return this.chromeCustomeTabsExternal;
    }

    private String replaceAdProtocol(String str, String str2) {
        return str != null ? str.replace("%AdPlatformProtocol%", "1.5") : str2;
    }

    private void initTransientFields() {
        this.loading = false;
        this.ready = false;
        this.metaDataListeners = new ArrayList();
    }

    public static void preCacheResources(Context context, String str) {
        if (str != null && !str.equals("")) {
            if (!C1049a.m1133a(context, "close_button.png", ".png") && !C1061i.m1193a()) {
                new C1249a(str + "close_button" + ".png", new C1222a(context, "close_button"), 0).mo15438a();
            }
            if (C1061i.m1194a(256)) {
                for (String str2 : AdsConstants.f964k) {
                    if (!C1049a.m1133a(context, str2 + ".png", ".png")) {
                        new C1249a(str + str2 + ".png", new C1222a(context, str2), 0).mo15438a();
                    }
                }
            }
            if (C1061i.m1194a(64)) {
                for (String str3 : AdsConstants.f965l) {
                    if (!C1049a.m1133a(context, str3 + ".png", ".png")) {
                        new C1249a(str + str3 + ".png", new C1222a(context, str3), 0).mo15438a();
                    }
                }
                if (!C1049a.m1133a(context, "logo.png", ".png")) {
                    new C1249a(str + "logo" + ".png", new C1222a(context, "logo"), 0).mo15438a();
                }
            } else if (C1061i.m1194a(32)) {
                for (String str4 : AdsConstants.f965l) {
                    if (!C1049a.m1133a(context, str4 + ".png", ".png")) {
                        new C1249a(str + str4 + ".png", new C1222a(context, str4), 0).mo15438a();
                    }
                }
            }
        }
    }

    public boolean isOmsdkEnabled() {
        return this.omSdkEnabled;
    }

    /* renamed from: com.startapp.android.publish.common.metaData.MetaData$a */
    /* compiled from: StartAppSDK */
    public static class C1222a implements C1249a.C1252a {

        /* renamed from: a */
        Context f1381a;

        /* renamed from: b */
        String f1382b;

        public C1222a(Context context, String str) {
            this.f1381a = context;
            this.f1382b = str;
        }

        /* renamed from: a */
        public void mo13933a(Bitmap bitmap, int i) {
            if (bitmap != null) {
                C1049a.m1132a(this.f1381a, bitmap, this.f1382b, ".png");
            }
        }
    }
}

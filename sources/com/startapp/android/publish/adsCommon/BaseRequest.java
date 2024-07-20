package com.startapp.android.publish.adsCommon;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.SystemClock;
import android.provider.Settings;
import android.telephony.CellLocation;
import android.telephony.TelephonyManager;
import android.telephony.cdma.CdmaCellLocation;
import android.telephony.gsm.GsmCellLocation;
import android.text.TextUtils;
import com.startapp.android.publish.adsCommon.Utils.C1054c;
import com.startapp.android.publish.adsCommon.Utils.C1055d;
import com.startapp.android.publish.adsCommon.Utils.C1056e;
import com.startapp.android.publish.adsCommon.Utils.C1059g;
import com.startapp.android.publish.adsCommon.Utils.C1061i;
import com.startapp.android.publish.adsCommon.Utils.NameValueSerializer;
import com.startapp.android.publish.common.metaData.MetaData;
import com.startapp.android.publish.common.model.AdPreferences;
import com.startapp.common.C1292c;
import com.startapp.common.p043a.C1253a;
import com.startapp.common.p043a.C1254b;
import com.startapp.common.p043a.C1261c;
import com.startapp.common.p043a.C1269f;
import com.startapp.common.p043a.C1271h;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/* compiled from: StartAppSDK */
public abstract class BaseRequest implements NameValueSerializer {

    /* renamed from: OS */
    private static final String f968OS = "android";
    private String androidId;
    private int appCode;
    private Boolean appOnForeground;
    private String appVersion;
    private String blat;
    private String blon;
    private String bssid;
    private String cellSignalLevel;
    private String cellTimingAdv;
    private String cid;
    private String clientSessionId;
    private float density;
    private String deviceIP;
    private String deviceVersion;
    private Map<String, String> frameworksMap = new TreeMap();
    private int height;
    private Set<String> inputLangs;
    private String installerPkg;
    private String isp;
    private String ispName;
    private String lac;
    private String locale;
    private List<Location> locations = null;
    private String manufacturer;
    private String model;
    private String netOper;
    private String networkOperName;
    private String networkType;

    /* renamed from: os */
    private String f969os = "android";
    private String packageId;
    private Map<String, String> parameters = new HashMap();
    private String personalizedAdsServing;
    private String productId;
    private String publisherId;
    protected Integer retry;
    private Boolean roaming;
    private boolean root;
    private long sdkFlavor = new BigInteger(AdsConstants.f962i, 2).longValue();
    private int sdkId = 3;
    private String sdkVersion = AdsConstants.f961h;
    private String signalLevel;
    private boolean simulator;
    private String ssid;
    private String subProductId;
    private String subPublisherId;
    private Boolean unknownSourcesAllowed;
    private boolean usbDebug;
    private C1254b.C1258c userAdvertisingId;
    private String wfScanRes;
    private int width;
    private String wifiRSSILevel;
    private String wifiSignalLevel;

    public Map<String, String> getParameters() {
        return this.parameters;
    }

    public void setParameters(Map<String, String> map) {
        this.parameters = map;
    }

    public String getPublisherId() {
        return this.publisherId;
    }

    public void setPublisherId(String str) {
        this.publisherId = str;
    }

    public long getSdkFlavor() {
        return this.sdkFlavor;
    }

    public void setSdkFlavor(long j) {
        this.sdkFlavor = j;
    }

    public String getPackageId() {
        return this.packageId;
    }

    public void setPackageId(String str) {
        this.packageId = str;
    }

    public String getAndroidId() {
        return this.androidId;
    }

    private void setAndroidId(Context context) {
        if (C1261c.m2032a(context, "com.google.android.gms", 0)) {
            try {
                if (Integer.parseInt(Integer.toString(context.getPackageManager().getPackageInfo("com.google.android.gms", 0).versionCode).substring(0, 1)) >= 4) {
                    this.androidId = Settings.Secure.getString(context.getContentResolver(), "android_id");
                }
            } catch (PackageManager.NameNotFoundException | Exception unused) {
            }
        }
    }

    public String getInstallerPkg() {
        return this.installerPkg;
    }

    public void setInstallerPkg(String str) {
        this.installerPkg = str;
    }

    public String getProductId() {
        return this.productId;
    }

    public void setProductId(String str) {
        this.productId = str;
    }

    public String getNetworkType() {
        return this.networkType;
    }

    public String getSignalLevel() {
        return this.signalLevel;
    }

    private void setNetworkType(Context context) {
        this.networkType = C1271h.m2083a(context);
    }

    private void setNetworkLevels(Context context) {
        try {
            C1292c a = C1292c.m2158a();
            if (a != null) {
                this.cellSignalLevel = a.mo15500b();
                C1271h.C1273b bVar = null;
                if (MetaData.getInstance().isWfScanEnabled()) {
                    bVar = C1271h.m2082a(context, this.networkType);
                }
                if (bVar == null) {
                    this.signalLevel = this.cellSignalLevel;
                } else if (bVar.mo15471a() == null) {
                    this.signalLevel = bVar.mo15475c();
                    this.wifiRSSILevel = bVar.mo15473b();
                    this.wifiSignalLevel = bVar.mo15475c();
                } else {
                    this.signalLevel = bVar.mo15471a();
                    this.wifiRSSILevel = bVar.mo15471a();
                    this.wifiSignalLevel = bVar.mo15471a();
                }
            } else {
                this.signalLevel = "e106";
                this.cellSignalLevel = "e106";
                this.wifiSignalLevel = "e106";
                this.wifiRSSILevel = "e106";
            }
        } catch (Exception unused) {
        }
    }

    public String getCellSignalLevel() {
        return this.cellSignalLevel;
    }

    public String getWifiSignalLevel() {
        return this.wifiSignalLevel;
    }

    public String getWifiRssiLevel() {
        return this.wifiRSSILevel;
    }

    public C1254b.C1258c getUserAdvertisingId() {
        return this.userAdvertisingId;
    }

    public void setUserAdvertisingId(C1254b.C1258c cVar) {
        this.userAdvertisingId = cVar;
    }

    public String getIsp() {
        return this.isp;
    }

    public void setIsp(String str) {
        this.isp = str;
    }

    public String getIspName() {
        return this.ispName;
    }

    public void setIspName(String str) {
        this.ispName = str;
    }

    public String getNetOper() {
        return this.netOper;
    }

    public void setNetOper(String str) {
        this.netOper = str;
    }

    public String getNetworkOperName() {
        return this.networkOperName;
    }

    public void setNetworkOperName(String str) {
        this.networkOperName = str;
    }

    public String getCid() {
        return this.cid;
    }

    public void setCid(String str) {
        this.cid = str;
    }

    public String getLac() {
        return this.lac;
    }

    public void setLac(String str) {
        this.lac = str;
    }

    public String getBlat() {
        return this.blat;
    }

    public void setBlat(String str) {
        this.blat = str;
    }

    public String getBlon() {
        return this.blon;
    }

    public void setBlon(String str) {
        this.blon = str;
    }

    public String getSsid() {
        return this.ssid;
    }

    public void setSsid(String str) {
        this.ssid = str;
    }

    public String getBssid() {
        return this.bssid;
    }

    public void setBssid(String str) {
        this.bssid = str;
    }

    public String getWfScanRes() {
        return this.wfScanRes;
    }

    public void setWfScanRes(String str) {
        this.wfScanRes = str;
    }

    public String getModel() {
        return this.model;
    }

    public void setRetry(int i) {
        this.retry = null;
    }

    public void setModel(String str) {
        this.model = str;
    }

    public String getManufacturer() {
        return this.manufacturer;
    }

    public void setManufacturer(String str) {
        this.manufacturer = str;
    }

    public String getDeviceVersion() {
        return this.deviceVersion;
    }

    public void setDeviceVersion(String str) {
        this.deviceVersion = str;
    }

    public String getLocale() {
        return this.locale;
    }

    public void setLocale(String str) {
        this.locale = str;
    }

    public String getSubPublisherId() {
        return this.subPublisherId;
    }

    public void setSubPublisherId(String str) {
        this.subPublisherId = str;
    }

    public String getSubProductId() {
        return this.subProductId;
    }

    public void setSubProductId(String str) {
        this.subProductId = str;
    }

    public String getOs() {
        return this.f969os;
    }

    public int getWidth() {
        return this.width;
    }

    public void setWidth(int i) {
        this.width = i;
    }

    public int getHeight() {
        return this.height;
    }

    public void setHeight(int i) {
        this.height = i;
    }

    public String getSdkVersion() {
        return this.sdkVersion;
    }

    public void setSdkVersion(String str) {
        this.sdkVersion = str;
    }

    public String getSessionId() {
        String str = this.clientSessionId;
        return str == null ? "" : str;
    }

    public void setSessionId(String str) {
        this.clientSessionId = str;
    }

    public Boolean isRoaming() {
        return this.roaming;
    }

    public void setRoaming(Context context) {
        this.roaming = C1271h.m2089c(context);
    }

    public String getDeviceIP() {
        return this.deviceIP;
    }

    public void setDeviceIP(WifiInfo wifiInfo) {
        this.deviceIP = C1271h.m2086a(wifiInfo);
    }

    public Boolean isUnknownSourcesAllowed() {
        return this.unknownSourcesAllowed;
    }

    public void setUnknownSourcesAllowed(Boolean bool) {
        this.unknownSourcesAllowed = bool;
    }

    public float getDensity() {
        return this.density;
    }

    public void setDensity(float f) {
        this.density = f;
    }

    public Boolean isAppOnForeground() {
        return this.appOnForeground;
    }

    public void setAppOnForeground(Context context) {
        try {
            this.appOnForeground = Boolean.valueOf(C1061i.m1207e(context));
        } catch (Exception unused) {
            this.appOnForeground = null;
        }
    }

    public Set<String> getInputLangs() {
        return this.inputLangs;
    }

    public void setInputLangs(Set<String> set) {
        this.inputLangs = set;
    }

    public String getAppVersion() {
        return this.appVersion;
    }

    public void setAppVersion(String str) {
        this.appVersion = str;
    }

    public int getAppCode() {
        return this.appCode;
    }

    public void setAppCode(int i) {
        this.appCode = i;
    }

    public List<Location> getLocations() {
        return this.locations;
    }

    public void setLocations(List<Location> list) {
        this.locations = list;
    }

    public String toString() {
        return "BaseRequest [parameters=" + this.parameters + "]";
    }

    public void fillApplicationDetails(Context context, AdPreferences adPreferences) {
        fillApplicationDetails(context, adPreferences, true);
    }

    public void fillApplicationDetails(Context context, AdPreferences adPreferences, boolean z) {
        setPublisherId(adPreferences.getPublisherId());
        setProductId(adPreferences.getProductId());
        this.frameworksMap = C1166k.m1612b(context, "sharedPrefsWrappers", this.frameworksMap);
        if (!AdsConstants.f960g.booleanValue()) {
            setUserAdvertisingId(C1254b.m1990a().mo15441a(context));
            if (this.userAdvertisingId == null) {
                setAndroidId(context);
            }
        }
        setPackageId(context.getPackageName());
        setInstallerPkg(C1061i.m1205d(context));
        setManufacturer(Build.MANUFACTURER);
        setModel(Build.MODEL);
        setDeviceVersion(Integer.toString(Build.VERSION.SDK_INT));
        setLocale(context.getResources().getConfiguration().locale.toString());
        setInputLangs(C1261c.m2037b(context));
        setWidth(context.getResources().getDisplayMetrics().widthPixels);
        setHeight(context.getResources().getDisplayMetrics().heightPixels);
        setDensity(context.getResources().getDisplayMetrics().density);
        setAppOnForeground(context);
        setSessionId(C1059g.m1157d().mo14634a());
        setUnknownSourcesAllowed(Boolean.valueOf(C1261c.m2030a(context)));
        setRoaming(context);
        setNetworkType(context);
        setNetworkLevels(context);
        setAppVersion(C1261c.m2044e(context));
        setAppCode(C1261c.m2043d(context));
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            if (telephonyManager != null) {
                fillSimDetails(telephonyManager);
                fillNetworkOperatorDetails(context, telephonyManager);
                fillCellLocationDetails(context, telephonyManager);
                setCellTimingAdv(context, telephonyManager);
            }
        } catch (Exception unused) {
        }
        fillWifiDetails(context, z);
        this.usbDebug = C1261c.m2046g(context);
        this.root = C1261c.m2047h(context);
        this.simulator = C1261c.m2048i(context);
        this.personalizedAdsServing = C1166k.m1610a(context, "USER_CONSENT_PERSONALIZED_ADS_SERVING", (String) null);
    }

    public C1056e getNameValueJson() {
        C1054c cVar = new C1054c();
        addParams(cVar);
        return cVar;
    }

    public C1056e getNameValueMap() {
        C1055d dVar = new C1055d();
        addParams(dVar);
        return dVar;
    }

    private void addParams(C1056e eVar) {
        String a;
        eVar.mo14631a("publisherId", (Object) this.publisherId, false);
        eVar.mo14631a("productId", (Object) this.productId, true);
        eVar.mo14631a("os", (Object) this.f969os, true);
        eVar.mo14631a("sdkVersion", (Object) this.sdkVersion, false);
        eVar.mo14631a("flavor", (Object) Long.valueOf(this.sdkFlavor), false);
        Map<String, String> map = this.frameworksMap;
        if (map != null && !map.isEmpty()) {
            String str = "";
            for (String next : this.frameworksMap.keySet()) {
                str = str + next + ":" + this.frameworksMap.get(next) + ";";
            }
            eVar.mo14627a("frameworksData", (Object) str.substring(0, str.length() - 1), false, false);
        }
        eVar.mo14631a("packageId", (Object) this.packageId, false);
        eVar.mo14631a("installerPkg", (Object) this.installerPkg, false);
        C1254b.C1258c cVar = this.userAdvertisingId;
        if (cVar != null) {
            eVar.mo14631a("userAdvertisingId", (Object) cVar.mo15450a(), false);
            if (this.userAdvertisingId.mo15454b()) {
                eVar.mo14631a("limat", (Object) Boolean.valueOf(this.userAdvertisingId.mo15454b()), false);
            }
            eVar.mo14631a("advertisingIdSource", (Object) this.userAdvertisingId.mo15455c(), false);
        } else {
            String str2 = this.androidId;
            if (str2 != null) {
                eVar.mo14631a("userId", (Object) str2, false);
            }
        }
        eVar.mo14631a("model", (Object) this.model, false);
        eVar.mo14631a("manufacturer", (Object) this.manufacturer, false);
        eVar.mo14631a("deviceVersion", (Object) this.deviceVersion, false);
        eVar.mo14631a("locale", (Object) this.locale, false);
        eVar.mo14632a("inputLangs", this.inputLangs, false);
        eVar.mo14631a("isp", (Object) this.isp, false);
        eVar.mo14631a("ispName", (Object) this.ispName, false);
        eVar.mo14631a("netOper", (Object) getNetOper(), false);
        eVar.mo14631a("networkOperName", (Object) getNetworkOperName(), false);
        eVar.mo14631a("cid", (Object) getCid(), false);
        eVar.mo14631a("lac", (Object) getLac(), false);
        eVar.mo14631a("blat", (Object) getBlat(), false);
        eVar.mo14631a("blon", (Object) getBlon(), false);
        eVar.mo14631a("ssid", (Object) getSsid(), false);
        eVar.mo14631a("bssid", (Object) getBssid(), false);
        eVar.mo14631a("wfScanRes", (Object) getWfScanRes(), false);
        eVar.mo14631a("subPublisherId", (Object) this.subPublisherId, false);
        eVar.mo14631a("subProductId", (Object) this.subProductId, false);
        eVar.mo14631a("retryCount", (Object) this.retry, false);
        eVar.mo14631a("roaming", (Object) isRoaming(), false);
        eVar.mo14631a("deviceIP", (Object) getDeviceIP(), false);
        eVar.mo14631a("grid", (Object) getNetworkType(), false);
        eVar.mo14631a("silev", (Object) getSignalLevel(), false);
        eVar.mo14631a("cellSignalLevel", (Object) getCellSignalLevel(), false);
        if (getWifiSignalLevel() != null) {
            eVar.mo14631a("wifiSignalLevel", (Object) getWifiSignalLevel(), false);
        }
        if (getWifiRssiLevel() != null) {
            eVar.mo14631a("wifiRssiLevel", (Object) getWifiRssiLevel(), false);
        }
        if (getCellTimingAdv() != null) {
            eVar.mo14631a("cellTimingAdv", (Object) getCellTimingAdv(), false);
        }
        eVar.mo14631a("outsource", (Object) isUnknownSourcesAllowed(), false);
        eVar.mo14631a("width", (Object) String.valueOf(this.width), false);
        eVar.mo14631a("height", (Object) String.valueOf(this.height), false);
        eVar.mo14631a("density", (Object) String.valueOf(this.density), false);
        eVar.mo14631a("fgApp", (Object) isAppOnForeground(), false);
        eVar.mo14631a("sdkId", (Object) String.valueOf(this.sdkId), true);
        eVar.mo14631a("clientSessionId", (Object) this.clientSessionId, false);
        eVar.mo14631a("appVersion", (Object) this.appVersion, false);
        eVar.mo14631a("appCode", (Object) Integer.valueOf(this.appCode), false);
        eVar.mo14631a("timeSinceBoot", (Object) Long.valueOf(getTimeSinceBoot()), false);
        if (getLocations() != null && getLocations().size() > 0 && (a = C1269f.m2073a(getLocations())) != null && !a.equals("")) {
            eVar.mo14631a("locations", (Object) C1253a.m1988c(a), false);
        }
        eVar.mo14631a("udbg", (Object) Boolean.valueOf(this.usbDebug), false);
        eVar.mo14631a("root", (Object) Boolean.valueOf(this.root), false);
        eVar.mo14631a("smltr", (Object) Boolean.valueOf(this.simulator), false);
        eVar.mo14631a("pas", (Object) this.personalizedAdsServing, false);
    }

    public String getRequestString() {
        return getNameValueMap().toString();
    }

    private void fillSimDetails(TelephonyManager telephonyManager) {
        if (telephonyManager.getSimState() == 5) {
            setIsp(telephonyManager.getSimOperator());
            setIspName(telephonyManager.getSimOperatorName());
        }
    }

    private void fillNetworkOperatorDetails(Context context, TelephonyManager telephonyManager) {
        int phoneType = telephonyManager.getPhoneType();
        if (phoneType != 0 && phoneType != 2) {
            String networkOperator = telephonyManager.getNetworkOperator();
            if (networkOperator != null) {
                setNetOper(C1253a.m1988c(networkOperator));
            }
            String networkOperatorName = telephonyManager.getNetworkOperatorName();
            if (networkOperatorName != null) {
                setNetworkOperName(C1253a.m1988c(networkOperatorName));
            }
        }
    }

    private void fillCellLocationDetails(Context context, TelephonyManager telephonyManager) {
        CellLocation cellLocation;
        if ((!C1261c.m2031a(context, "android.permission.ACCESS_FINE_LOCATION") && !C1261c.m2031a(context, "android.permission.ACCESS_COARSE_LOCATION")) || (cellLocation = telephonyManager.getCellLocation()) == null) {
            return;
        }
        if (cellLocation instanceof GsmCellLocation) {
            GsmCellLocation gsmCellLocation = (GsmCellLocation) cellLocation;
            setCid(C1253a.m1988c(String.valueOf(gsmCellLocation.getCid())));
            setLac(C1253a.m1988c(String.valueOf(gsmCellLocation.getLac())));
        } else if (cellLocation instanceof CdmaCellLocation) {
            CdmaCellLocation cdmaCellLocation = (CdmaCellLocation) cellLocation;
            setBlat(C1253a.m1988c(String.valueOf(cdmaCellLocation.getBaseStationLatitude())));
            setBlon(C1253a.m1988c(String.valueOf(cdmaCellLocation.getBaseStationLongitude())));
        }
    }

    private String getCellTimingAdv() {
        return this.cellTimingAdv;
    }

    private void setCellTimingAdv(Context context, TelephonyManager telephonyManager) {
        this.cellTimingAdv = C1261c.m2036b(context, telephonyManager);
    }

    private static long getTimeSinceBoot() {
        return SystemClock.elapsedRealtime();
    }

    public void fillLocationDetails(AdPreferences adPreferences, Context context) {
        boolean z;
        this.locations = new ArrayList();
        boolean z2 = true;
        if (adPreferences == null || adPreferences.getLatitude() == null || adPreferences.getLongitude() == null) {
            z = false;
        } else {
            Location location = new Location("loc");
            location.setLongitude(adPreferences.getLongitude().doubleValue());
            location.setLongitude(adPreferences.getLongitude().doubleValue());
            location.setProvider(MetaData.DEFAULT_LOCATION_SOURCE);
            this.locations.add(location);
            z = true;
        }
        C1174m.m1649a().mo15002h(context);
        List<Location> a = C1269f.m2074a(context, MetaData.getInstance().getLocationConfig().isFiEnabled(), MetaData.getInstance().getLocationConfig().isCoEnabled());
        if (a == null || a.size() <= 0) {
            z2 = z;
        } else {
            this.locations.addAll(a);
        }
        setUsingLocation(context, z2);
    }

    public static void setUsingLocation(Context context, boolean z) {
        C1166k.m1613b(context, "shared_prefs_using_location", Boolean.valueOf(z));
    }

    private void fillWifiDetails(Context context, boolean z) {
        WifiManager wifiManager;
        List<ScanResult> a;
        WifiInfo connectionInfo;
        try {
            if (MetaData.getInstance().isWfScanEnabled() && (wifiManager = (WifiManager) context.getSystemService("wifi")) != null && C1261c.m2031a(context, "android.permission.ACCESS_WIFI_STATE")) {
                if (getNetworkType().equals("WIFI") && (connectionInfo = wifiManager.getConnectionInfo()) != null) {
                    setDeviceIP(connectionInfo);
                    String ssid2 = connectionInfo.getSSID();
                    String bssid2 = connectionInfo.getBSSID();
                    if (ssid2 != null) {
                        setSsid(C1253a.m1988c(ssid2));
                    }
                    if (bssid2 != null) {
                        setBssid(C1253a.m1988c(bssid2));
                    }
                }
                if (z && (a = C1261c.m2017a(context, wifiManager)) != null && !a.equals(Collections.EMPTY_LIST)) {
                    ArrayList arrayList = new ArrayList();
                    for (int i = 0; i < Math.min(5, a.size()); i++) {
                        arrayList.add(new C1044a(a.get(i)));
                    }
                    setWfScanRes(C1253a.m1988c(TextUtils.join(";", arrayList)));
                }
            }
        } catch (Exception unused) {
        }
    }

    /* renamed from: com.startapp.android.publish.adsCommon.BaseRequest$a */
    /* compiled from: StartAppSDK */
    static class C1044a {

        /* renamed from: a */
        private ScanResult f970a;

        public C1044a(ScanResult scanResult) {
            this.f970a = scanResult;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            ScanResult scanResult = this.f970a;
            if (scanResult != null) {
                sb.append(scanResult.SSID);
                sb.append(',');
                sb.append(this.f970a.BSSID);
                sb.append(',');
                sb.append(WifiManager.calculateSignalLevel(this.f970a.level, 5));
                sb.append(',');
                sb.append(this.f970a.level);
                sb.append(',');
                long a = C1261c.m2014a(this.f970a);
                if (a != 0) {
                    sb.append(a);
                }
                sb.append(',');
                CharSequence b = C1261c.m2035b(this.f970a);
                if (b != null) {
                    sb.append(b);
                }
            }
            return sb.toString();
        }
    }
}

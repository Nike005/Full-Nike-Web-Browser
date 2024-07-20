package com.yandex.metrica.impl;

import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import com.yandex.metrica.impl.C1906d;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONObject;

/* renamed from: com.yandex.metrica.impl.bm */
class C1899bm implements C1906d {

    /* renamed from: b */
    private static volatile C1899bm f3128b;

    /* renamed from: c */
    private static final Object f3129c = new Object();

    /* renamed from: a */
    private final WifiManager f3130a;

    /* renamed from: d */
    private C1906d.C1907a<JSONArray> f3131d = new C1906d.C1907a<>();

    /* renamed from: e */
    private C1906d.C1907a<List<C1900a>> f3132e = new C1906d.C1907a<>();

    private C1899bm(Context context) {
        this.f3130a = (WifiManager) context.getSystemService("wifi");
    }

    /* renamed from: a */
    static C1899bm m4659a(Context context) {
        if (f3128b == null) {
            synchronized (f3129c) {
                if (f3128b == null) {
                    f3128b = new C1899bm(context.getApplicationContext());
                }
            }
        }
        return f3128b;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public synchronized JSONArray mo17084a() {
        if (!m4662d()) {
            return new JSONArray();
        }
        if (this.f3131d.mo17102b() || this.f3131d.mo17103c()) {
            this.f3131d.mo17101a(m4661c());
        }
        return this.f3131d.mo17100a();
    }

    /* renamed from: c */
    private JSONArray m4661c() {
        try {
            List<ScanResult> scanResults = this.f3130a.getScanResults();
            JSONArray jSONArray = new JSONArray();
            String str = null;
            WifiInfo connectionInfo = this.f3130a.getConnectionInfo();
            if (connectionInfo != null) {
                str = connectionInfo.getBSSID();
            }
            for (ScanResult next : scanResults) {
                if (next != null) {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("mac", next.BSSID.toUpperCase(Locale.US).replace(":", ""));
                    jSONObject.put("signal_strength", next.level);
                    jSONObject.put("ssid", next.SSID);
                    jSONObject.put("is_connected", next.BSSID.equals(str));
                    jSONArray.put(jSONObject);
                }
            }
            return jSONArray;
        } catch (Exception unused) {
            return new JSONArray();
        }
    }

    /* renamed from: d */
    private boolean m4662d() {
        try {
            return this.f3130a.isWifiEnabled();
        } catch (Exception unused) {
            return false;
        }
    }

    /* renamed from: b */
    public List<C1900a> mo17086b() {
        if (this.f3132e.mo17102b() || this.f3132e.mo17103c()) {
            ArrayList arrayList = new ArrayList();
            m4660a((List<C1900a>) arrayList);
            this.f3132e.mo17101a(arrayList);
        }
        return this.f3132e.mo17100a();
    }

    /* renamed from: a */
    private static void m4660a(List<C1900a> list) {
        StringBuilder sb = new StringBuilder();
        try {
            Iterator<T> it = Collections.list(NetworkInterface.getNetworkInterfaces()).iterator();
            while (it.hasNext()) {
                NetworkInterface networkInterface = (NetworkInterface) it.next();
                byte[] hardwareAddress = networkInterface.getHardwareAddress();
                if (hardwareAddress != null) {
                    for (byte valueOf : hardwareAddress) {
                        sb.append(String.format(Locale.US, "%02X:", new Object[]{Byte.valueOf(valueOf)}));
                    }
                    if (sb.length() > 0) {
                        sb.deleteCharAt(sb.length() - 1);
                        list.add(new C1900a(networkInterface.getName(), sb.toString()));
                        sb.setLength(0);
                    }
                }
            }
        } catch (Throwable unused) {
        }
    }

    /* renamed from: com.yandex.metrica.impl.bm$a */
    public static final class C1900a {

        /* renamed from: a */
        public final String f3133a;

        /* renamed from: b */
        public final String f3134b;

        public C1900a(String str, String str2) {
            this.f3133a = str;
            this.f3134b = str2;
        }
    }

    /* renamed from: b */
    public String mo17085b(Context context) {
        WifiConfiguration wifiConfiguration;
        try {
            if (C1837al.m4248a(context, "android.permission.ACCESS_WIFI_STATE") && (wifiConfiguration = (WifiConfiguration) this.f3130a.getClass().getMethod("getWifiApConfiguration", new Class[0]).invoke(this.f3130a, new Object[0])) != null) {
                return wifiConfiguration.SSID;
            }
        } catch (Throwable unused) {
        }
        return null;
    }

    /* renamed from: c */
    public int mo17087c(Context context) {
        try {
            if (!C1837al.m4248a(context, "android.permission.ACCESS_WIFI_STATE")) {
                return -1;
            }
            int intValue = ((Integer) this.f3130a.getClass().getMethod("getWifiApState", new Class[0]).invoke(this.f3130a, new Object[0])).intValue();
            if (intValue > 10) {
                intValue -= 10;
            }
            return intValue;
        } catch (Throwable unused) {
            return -1;
        }
    }
}

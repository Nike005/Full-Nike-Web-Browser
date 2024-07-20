package com.yandex.metrica.impl;

import android.location.Location;
import android.text.TextUtils;
import com.appnext.base.p082b.C4899d;
import com.appnext.base.p082b.C4905i;
import com.mopub.network.ImpressionData;
import com.yandex.metrica.PreloadInfo;
import com.yandex.metrica.YandexMetricaConfig;
import com.yandex.metrica.impl.utils.C2223g;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.yandex.metrica.impl.ab */
public class C1810ab {
    /* renamed from: a */
    public String mo16763a(YandexMetricaConfig yandexMetricaConfig) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("apikey", yandexMetricaConfig.getApiKey());
            jSONObject.put(ImpressionData.APP_VERSION, yandexMetricaConfig.getAppVersion());
            jSONObject.put("session_timeout", yandexMetricaConfig.getSessionTimeout());
            jSONObject.put("error_env", C2223g.m5948a((Map) yandexMetricaConfig.getErrorEnvironment()));
            jSONObject.put("location", m4129a(yandexMetricaConfig.getLocation()));
            jSONObject.put("preload_info", m4130a(yandexMetricaConfig.getPreloadInfo()));
            jSONObject.put("collect_apps", yandexMetricaConfig.isCollectInstalledApps());
            jSONObject.put("logs", yandexMetricaConfig.isLogEnabled());
            jSONObject.put("crash_enabled", yandexMetricaConfig.isReportCrashEnabled());
            jSONObject.put("crash_native_enabled", yandexMetricaConfig.isReportNativeCrashEnabled());
            jSONObject.put("location_enabled", yandexMetricaConfig.isTrackLocationEnabled());
            return jSONObject.toString();
        } catch (JSONException unused) {
            return "";
        }
    }

    /* renamed from: a */
    public YandexMetricaConfig mo16762a(String str) {
        if (!TextUtils.isEmpty(str)) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                YandexMetricaConfig.Builder newConfigBuilder = YandexMetricaConfig.newConfigBuilder(jSONObject.getString("apikey"));
                if (jSONObject.has(ImpressionData.APP_VERSION)) {
                    newConfigBuilder.setAppVersion(jSONObject.optString(ImpressionData.APP_VERSION));
                }
                if (jSONObject.has("session_timeout")) {
                    newConfigBuilder.setSessionTimeout(jSONObject.getInt("session_timeout"));
                }
                HashMap<String, String> a = C2223g.m5949a(jSONObject.optString("error_env"));
                if (a != null && a.size() > 0) {
                    for (Map.Entry next : a.entrySet()) {
                        newConfigBuilder.putErrorEnvironmentValue((String) next.getKey(), (String) next.getValue());
                    }
                }
                newConfigBuilder.setLocation(m4132c(jSONObject.optString("location")));
                newConfigBuilder.setPreloadInfo(m4131b(jSONObject.optString("preload_info")));
                if (jSONObject.has("collect_apps")) {
                    newConfigBuilder.setCollectInstalledApps(jSONObject.optBoolean("collect_apps"));
                }
                if (jSONObject.has("logs") && jSONObject.optBoolean("logs")) {
                    newConfigBuilder.setLogEnabled();
                }
                if (jSONObject.has("crash_enabled")) {
                    newConfigBuilder.setReportCrashesEnabled(jSONObject.optBoolean("crash_enabled"));
                }
                if (jSONObject.has("crash_native_enabled")) {
                    newConfigBuilder.setReportNativeCrashesEnabled(jSONObject.optBoolean("crash_native_enabled"));
                }
                if (jSONObject.has("location_enabled")) {
                    newConfigBuilder.setTrackLocationEnabled(jSONObject.optBoolean("location_enabled"));
                }
                return newConfigBuilder.build();
            } catch (JSONException unused) {
            }
        }
        return null;
    }

    /* renamed from: a */
    private static String m4130a(PreloadInfo preloadInfo) {
        if (preloadInfo == null) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("trackid", preloadInfo.getTrackingId());
            jSONObject.put("params", C2223g.m5948a((Map) preloadInfo.getAdditionalParams()));
            return jSONObject.toString();
        } catch (JSONException unused) {
            return null;
        }
    }

    /* renamed from: b */
    private static PreloadInfo m4131b(String str) throws JSONException {
        String str2 = null;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        JSONObject jSONObject = new JSONObject(str);
        if (jSONObject.has("trackid")) {
            str2 = jSONObject.optString("trackid");
        }
        PreloadInfo.Builder newBuilder = PreloadInfo.newBuilder(str2);
        HashMap<String, String> a = C2223g.m5949a(jSONObject.optString("params"));
        if (a != null && a.size() > 0) {
            for (Map.Entry next : a.entrySet()) {
                newBuilder.setAdditionalParams((String) next.getKey(), (String) next.getValue());
            }
        }
        return newBuilder.build();
    }

    /* renamed from: a */
    private static String m4129a(Location location) {
        if (location == null) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("provider", location.getProvider());
            jSONObject.put(C4899d.f4625fl, location.getTime());
            jSONObject.put("accuracy", (double) location.getAccuracy());
            jSONObject.put("alt", location.getAltitude());
            jSONObject.put("lng", location.getLongitude());
            jSONObject.put(C4905i.f4638fC, location.getLatitude());
            return jSONObject.toString();
        } catch (JSONException unused) {
            return null;
        }
    }

    /* renamed from: c */
    private static Location m4132c(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            Location location = new Location(jSONObject.has("provider") ? jSONObject.optString("provider") : null);
            location.setLongitude(jSONObject.getDouble("lng"));
            location.setLatitude(jSONObject.getDouble(C4905i.f4638fC));
            location.setTime(jSONObject.optLong(C4899d.f4625fl));
            location.setAccuracy((float) jSONObject.optDouble("accuracy"));
            location.setAltitude((double) ((float) jSONObject.optDouble("alt")));
            return location;
        } catch (JSONException unused) {
            return null;
        }
    }
}

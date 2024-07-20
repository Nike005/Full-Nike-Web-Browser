package com.yandex.metrica;

import android.app.Activity;
import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.location.Location;
import com.yandex.metrica.impl.C1897bk;
import com.yandex.metrica.impl.C1902bo;
import com.yandex.metrica.impl.utils.C2228j;
import java.util.Map;

public final class YandexMetrica {
    public static int getLibraryApiLevel() {
        return 58;
    }

    public static String getLibraryVersion() {
        return "2.73";
    }

    private YandexMetrica() {
    }

    public static void activate(Context context, String str) {
        C1902bo.m4673a(context, C1797e.m4050a(str).mo16725b());
    }

    public static void activate(Context context, YandexMetricaConfig yandexMetricaConfig) {
        C1902bo.m4673a(context, C1797e.m4051a(yandexMetricaConfig));
    }

    public static void onResumeActivity(Activity activity) {
        C1902bo.m4681c().mo17950b(activity);
    }

    public static void onPauseActivity(Activity activity) {
        C1902bo.m4681c().mo17951c(activity);
    }

    public static void enableActivityAutoTracking(Application application) {
        C1902bo.m4681c().mo17948a(application);
    }

    public static void reportEvent(String str) {
        C1902bo.m4681c().reportEvent(str);
    }

    public static void reportError(String str, Throwable th) {
        C1902bo.m4681c().reportError(str, th);
    }

    public static void reportUnhandledException(Throwable th) {
        C1902bo.m4681c().reportUnhandledException(th);
    }

    public static void reportNativeCrash(String str) {
        C1902bo.m4681c().mo16953d(str);
    }

    public static void reportEvent(String str, String str2) {
        C1902bo.m4681c().reportEvent(str, str2);
    }

    public static void reportEvent(String str, Map<String, Object> map) {
        C1902bo.m4681c().reportEvent(str, map);
    }

    public static void reportAppOpen(Activity activity) {
        C1902bo.m4681c().mo17947a(activity);
    }

    public static void reportAppOpen(String str) {
        C1902bo.m4681c().mo17954e(str);
    }

    public static void setSessionTimeout(int i) {
        C1902bo.m4671a(i);
    }

    public static void setReportCrashesEnabled(boolean z) {
        C1902bo.m4676a(z);
    }

    public static void setReportNativeCrashesEnabled(boolean z) {
        C1902bo.m4680b(z);
    }

    public static void setLocation(Location location) {
        C1902bo.m4674a(location);
    }

    public static void setTrackLocationEnabled(boolean z) {
        C1902bo.m4683c(z);
    }

    public static void setCustomAppVersion(String str) {
        C1902bo.m4682c(str);
    }

    public static void setLogEnabled() {
        C2228j.m5960f().mo17899a();
    }

    public static void setCollectInstalledApps(boolean z) {
        C1902bo.m4684d(z);
    }

    public static IReporter getReporter(Context context, String str) {
        C1897bk.m4647a(str);
        C1902bo.m4672a(context);
        return C1902bo.m4677b().mo17088a(str);
    }

    public static void setEnvironmentValue(String str, String str2) {
        C1902bo.m4675a(str, str2);
    }

    public static boolean isCollectInstalledApps() {
        return C1902bo.m4686e();
    }

    public static void registerReferrerBroadcastReceivers(BroadcastReceiver... broadcastReceiverArr) {
        MetricaEventHandler.m3974a(broadcastReceiverArr);
    }

    public static void requestDeferredDeeplinkParameters(DeferredDeeplinkParametersListener deferredDeeplinkParametersListener) {
        C1902bo.m4677b().mo17091a(deferredDeeplinkParametersListener);
    }
}

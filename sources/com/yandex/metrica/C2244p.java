package com.yandex.metrica;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.Location;
import com.yandex.metrica.impl.C1852aq;
import com.yandex.metrica.impl.C1880bc;
import com.yandex.metrica.impl.C1887be;
import com.yandex.metrica.impl.C1901bn;
import com.yandex.metrica.impl.C1902bo;
import com.yandex.metrica.impl.C2241y;
import com.yandex.metrica.impl.interact.CellularNetworkInfo;
import com.yandex.metrica.impl.interact.DeviceInfo;
import java.util.Map;

/* renamed from: com.yandex.metrica.p */
public final class C2244p {
    /* renamed from: u */
    public static String m6017u(String str) {
        return C1880bc.m4537a(str);
    }

    public static Boolean plat() {
        return C1901bn.m4669c();
    }

    public static boolean iifa() {
        return C1901bn.m4667a();
    }

    public static String pgai() {
        return C1901bn.m4668b();
    }

    public static String gmsvn(int i) {
        return C1852aq.m4306a(i);
    }

    public static YandexMetricaConfig cpcwh(YandexMetricaConfig yandexMetricaConfig, String str) {
        return C1797e.m4052b(yandexMetricaConfig).mo16730d(str).mo16725b();
    }

    public static void rolu(Context context, Object obj) {
        C2241y.m5985a(context).mo17937a(obj);
    }

    public static void urolu(Context context, Object obj) {
        C2241y.m5985a(context).mo17940b(obj);
    }

    public static Location glkl(Context context) {
        return C2241y.m5985a(context).mo17942d();
    }

    public static Integer gbc(Context context) {
        Intent registerReceiver = context.getApplicationContext().registerReceiver((BroadcastReceiver) null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
        if (registerReceiver == null) {
            return null;
        }
        int intExtra = registerReceiver.getIntExtra("level", -1);
        int intExtra2 = registerReceiver.getIntExtra("scale", -1);
        if (intExtra < 0 || intExtra2 <= 0) {
            return null;
        }
        return Integer.valueOf(Math.round((((float) intExtra) / ((float) intExtra2)) * 100.0f));
    }

    /* renamed from: a */
    public static void m6016a(IIdentifierCallback iIdentifierCallback) {
        C1902bo.m4677b().mo17092a(iIdentifierCallback);
    }

    public static DeviceInfo gdi(Context context) {
        return DeviceInfo.getInstance(context);
    }

    public static String gcni(Context context) {
        return new CellularNetworkInfo(context).getCelluralInfo();
    }

    public static String guid() {
        return C1902bo.m4677b().mo17094f();
    }

    public static String mpn(Context context) {
        return C1887be.m4557d(context);
    }

    public static void rce(int i, String str, String str2, Map<String, String> map) {
        C1902bo.m4681c().mo16939a(i, str, str2, map);
    }
}

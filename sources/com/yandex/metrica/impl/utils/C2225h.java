package com.yandex.metrica.impl.utils;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.ServiceInfo;
import com.yandex.metrica.MetricaService;

/* renamed from: com.yandex.metrica.impl.utils.h */
public class C2225h {

    /* renamed from: com.yandex.metrica.impl.utils.h$a */
    public static final class C2226a implements Runnable {

        /* renamed from: a */
        final Context f3897a;

        public C2226a(Context context) {
            this.f3897a = context;
        }

        public void run() {
            Context context = this.f3897a;
            try {
                PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 516);
                if (packageInfo.services != null) {
                    for (ServiceInfo serviceInfo : packageInfo.services) {
                        if (MetricaService.class.getName().equals(serviceInfo.name) && !serviceInfo.enabled) {
                            context.getPackageManager().setComponentEnabledSetting(new ComponentName(context, MetricaService.class), 1, 1);
                        }
                    }
                }
            } catch (Exception unused) {
            }
        }
    }
}

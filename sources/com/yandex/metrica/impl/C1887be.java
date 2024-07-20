package com.yandex.metrica.impl;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageItemInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.net.Uri;
import android.os.Bundle;
import com.yandex.metrica.IMetricaService;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* renamed from: com.yandex.metrica.impl.be */
public final class C1887be {

    /* renamed from: com.yandex.metrica.impl.be$a */
    public static class C1888a implements Comparable<C1888a> {

        /* renamed from: a */
        public final int f3086a;

        /* renamed from: b */
        public final int f3087b;

        /* renamed from: c */
        public final long f3088c;

        /* renamed from: d */
        public final ServiceInfo f3089d;

        /* renamed from: e */
        public final String f3090e;

        public C1888a(ServiceInfo serviceInfo, int i, int i2, long j) {
            this.f3086a = i2;
            this.f3087b = i;
            this.f3089d = serviceInfo;
            this.f3088c = j;
            this.f3090e = serviceInfo.applicationInfo.packageName;
        }

        /* renamed from: a */
        public int compareTo(C1888a aVar) {
            int i = this.f3087b;
            if (i != aVar.f3087b) {
                return Integer.valueOf(i).compareTo(Integer.valueOf(aVar.f3087b));
            }
            long j = this.f3088c;
            if (j != aVar.f3088c) {
                return Long.valueOf(j).compareTo(Long.valueOf(aVar.f3088c));
            }
            return 0;
        }

        public String toString() {
            return "MetricaServiceDescriptor{apiLevel=" + this.f3086a + ", score=" + this.f3087b + ", timeInstalled=" + this.f3088c + '}';
        }
    }

    /* renamed from: a */
    public static Intent m4552a(Context context) {
        Intent intent = new Intent(IMetricaService.class.getName(), Uri.parse("metrica://" + context.getPackageName()));
        if (C1897bk.m4656b(11)) {
            intent.addFlags(32);
        }
        return intent;
    }

    /* renamed from: a */
    public static List<ResolveInfo> m4553a(Context context, Intent intent) {
        List<ResolveInfo> queryIntentServices = context.getPackageManager().queryIntentServices(intent, 128);
        if (queryIntentServices != null) {
            return queryIntentServices;
        }
        return new ArrayList();
    }

    /* renamed from: b */
    public static List<C1888a> m4555b(Context context) {
        PackageManager packageManager = context.getPackageManager();
        ArrayList arrayList = new ArrayList();
        for (ResolveInfo next : m4553a(context, m4552a(context))) {
            ServiceInfo serviceInfo = next.serviceInfo;
            if (!((!serviceInfo.enabled) | (!serviceInfo.exported)) && !(!C1894bi.m4622a(serviceInfo.permission))) {
                long a = m4551a(packageManager, serviceInfo.packageName);
                if (m4554a(packageManager, serviceInfo.packageName, "android.permission.INTERNET")) {
                    int a2 = m4550a((PackageItemInfo) serviceInfo);
                    arrayList.add(new C1888a(next.serviceInfo, (a2 << 5) + ((m4554a(packageManager, serviceInfo.packageName, "android.permission.ACCESS_COARSE_LOCATION") ? 1 : 0) * true) + ((m4554a(packageManager, serviceInfo.packageName, "android.permission.ACCESS_FINE_LOCATION") ? 1 : 0) * true) + ((m4554a(packageManager, serviceInfo.packageName, "android.permission.ACCESS_WIFI_STATE") ? 1 : 0) * true) + ((m4554a(packageManager, serviceInfo.packageName, "android.permission.ACCESS_NETWORK_STATE") ? 1 : 0) * true) + ((m4554a(packageManager, serviceInfo.packageName, "android.permission.READ_PHONE_STATE") ? 1 : 0) * true), a2, a));
                }
            }
        }
        Collections.sort(arrayList);
        return arrayList;
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x002a A[Catch:{ Exception -> 0x002e }] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static long m4551a(android.content.pm.PackageManager r8, java.lang.String r9) {
        /*
            r0 = 8
            r1 = 0
            r2 = -1
            boolean r0 = com.yandex.metrica.impl.C1897bk.m4656b((int) r0)     // Catch:{ Exception -> 0x0018 }
            if (r0 == 0) goto L_0x0018
            android.content.pm.PackageInfo r0 = r8.getPackageInfo(r9, r1)     // Catch:{ Exception -> 0x0018 }
            long r4 = r0.firstInstallTime     // Catch:{ Exception -> 0x0018 }
            long r6 = r0.lastUpdateTime     // Catch:{ Exception -> 0x0018 }
            long r4 = java.lang.Math.max(r4, r6)     // Catch:{ Exception -> 0x0018 }
            goto L_0x0019
        L_0x0018:
            r4 = r2
        L_0x0019:
            android.content.pm.ApplicationInfo r8 = r8.getApplicationInfo(r9, r1)     // Catch:{ Exception -> 0x002e }
            java.lang.String r8 = r8.sourceDir     // Catch:{ Exception -> 0x002e }
            java.io.File r9 = new java.io.File     // Catch:{ Exception -> 0x002e }
            r9.<init>(r8)     // Catch:{ Exception -> 0x002e }
            boolean r8 = r9.exists()     // Catch:{ Exception -> 0x002e }
            if (r8 == 0) goto L_0x002e
            long r2 = r9.lastModified()     // Catch:{ Exception -> 0x002e }
        L_0x002e:
            long r8 = java.lang.Math.max(r4, r2)
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.yandex.metrica.impl.C1887be.m4551a(android.content.pm.PackageManager, java.lang.String):long");
    }

    /* renamed from: a */
    private static boolean m4554a(PackageManager packageManager, String str, String str2) {
        return str2 == null || packageManager.checkPermission(str2, str) == 0;
    }

    /* renamed from: a */
    public static int m4550a(PackageItemInfo packageItemInfo) {
        if (packageItemInfo.metaData != null) {
            try {
                return packageItemInfo.metaData.getInt("metrica:api:level");
            } catch (Exception unused) {
            }
        }
        return -1;
    }

    /* renamed from: c */
    public static Intent m4556c(Context context) {
        return m4552a(context).putExtras(m4558e(context)).setPackage(context.getApplicationContext().getPackageName());
    }

    /* renamed from: e */
    private static Bundle m4558e(Context context) {
        try {
            Bundle bundle = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData;
            return bundle == null ? new Bundle() : bundle;
        } catch (Exception unused) {
            return new Bundle();
        }
    }

    /* renamed from: d */
    public static String m4557d(Context context) {
        List<C1888a> b = m4555b(context);
        ServiceInfo serviceInfo = b.get(b.size() - 1).f3089d;
        return new ComponentName(serviceInfo.packageName, serviceInfo.name).getPackageName();
    }
}

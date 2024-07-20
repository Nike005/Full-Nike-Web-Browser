package com.truenet.android;

import android.content.Context;
import android.content.res.Resources;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import com.startapp.common.C1292c;
import com.truenet.android.p049a.C1747c;
import p055a.p056a.p058b.p060b.C2928h;

/* renamed from: com.truenet.android.a */
/* compiled from: StartAppSDK */
public final class C1744a {

    /* renamed from: a */
    private final Context f2659a;

    /* renamed from: b */
    private final TelephonyManager f2660b;

    public C1744a(Context context, TelephonyManager telephonyManager) {
        C2928h.m6157b(context, "context");
        C2928h.m6157b(telephonyManager, "telephonyManager");
        this.f2659a = context;
        this.f2660b = telephonyManager;
        C1292c.m2161c(context);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ C1744a(Context context, TelephonyManager telephonyManager, int i, C2925e eVar) {
        this(context, (i & 2) != 0 ? C1747c.m3849a(context) : telephonyManager);
    }

    /* renamed from: b */
    private final boolean m3845b() {
        Resources system = Resources.getSystem();
        C2928h.m6154a((Object) system, "Resources.getSystem()");
        DisplayMetrics displayMetrics = system.getDisplayMetrics();
        double d = (double) (((float) displayMetrics.widthPixels) / displayMetrics.xdpi);
        double d2 = (double) (((float) displayMetrics.heightPixels) / displayMetrics.ydpi);
        Double.isNaN(d);
        Double.isNaN(d);
        Double.isNaN(d2);
        Double.isNaN(d2);
        return Math.sqrt((d * d) + (d2 * d2)) > 6.5d;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:8:0x006a, code lost:
        r2 = r0.f2660b.getNetworkOperatorName();
     */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.truenet.android.DeviceInfo mo16517a() {
        /*
            r28 = this;
            r0 = r28
            java.util.List r1 = p055a.p056a.p057a.C2903g.m6118a()
            r2 = r1
            java.util.Collection r2 = (java.util.Collection) r2
            boolean r2 = r2.isEmpty()
            r3 = 1
            r2 = r2 ^ r3
            java.lang.String r4 = ""
            if (r2 == 0) goto L_0x0032
            java.lang.Object r2 = p055a.p056a.p057a.C2903g.m6126c(r1)
            android.location.Location r2 = (android.location.Location) r2
            double r5 = r2.getLatitude()
            java.lang.String r2 = java.lang.String.valueOf(r5)
            java.lang.Object r1 = p055a.p056a.p057a.C2903g.m6126c(r1)
            android.location.Location r1 = (android.location.Location) r1
            double r5 = r1.getLongitude()
            java.lang.String r1 = java.lang.String.valueOf(r5)
            r7 = r1
            r6 = r2
            goto L_0x0034
        L_0x0032:
            r6 = r4
            r7 = r6
        L_0x0034:
            android.content.Context r1 = r0.f2659a
            android.content.res.Resources r1 = r1.getResources()
            java.lang.String r2 = "context.resources"
            p055a.p056a.p058b.p060b.C2928h.m6154a((java.lang.Object) r1, (java.lang.String) r2)
            android.content.res.Configuration r1 = r1.getConfiguration()
            java.lang.String r2 = "context.resources.configuration"
            p055a.p056a.p058b.p060b.C2928h.m6154a((java.lang.Object) r1, (java.lang.String) r2)
            java.util.Locale r1 = com.truenet.android.p049a.C1746b.m3848a(r1)
            com.startapp.common.a.b r2 = com.startapp.common.p043a.C1254b.m1990a()
            android.content.Context r5 = r0.f2659a
            com.startapp.common.a.b$c r2 = r2.mo15441a((android.content.Context) r5)
            java.lang.String r5 = "AdvertisingIdSingleton.g…getAdvertisingId(context)"
            p055a.p056a.p058b.p060b.C2928h.m6154a((java.lang.Object) r2, (java.lang.String) r5)
            java.lang.String r10 = r2.mo15450a()
            android.telephony.TelephonyManager r2 = r0.f2660b
            int r2 = r2.getPhoneType()
            if (r2 == 0) goto L_0x0075
            r5 = 2
            if (r2 == r5) goto L_0x0075
            android.telephony.TelephonyManager r2 = r0.f2660b
            java.lang.String r2 = r2.getNetworkOperatorName()
            if (r2 == 0) goto L_0x0075
            r17 = r2
            goto L_0x0077
        L_0x0075:
            r17 = r4
        L_0x0077:
            android.telephony.TelephonyManager r2 = r0.f2660b
            int r5 = r2.getSimState()
            r8 = 5
            if (r5 != r8) goto L_0x0085
            java.lang.String r2 = r2.getSimOperator()
            goto L_0x0086
        L_0x0085:
            r2 = r4
        L_0x0086:
            android.telephony.TelephonyManager r5 = r0.f2660b
            int r9 = r5.getSimState()
            if (r9 != r8) goto L_0x0094
            java.lang.String r5 = r5.getSimOperatorName()
            r8 = r5
            goto L_0x0095
        L_0x0094:
            r8 = r4
        L_0x0095:
            android.content.Context r5 = r0.f2659a
            java.lang.String r9 = "android.permission.ACCESS_FINE_LOCATION"
            boolean r5 = com.truenet.android.p049a.C1753h.m3862a(r5, r9)
            if (r5 != 0) goto L_0x00ab
            android.content.Context r5 = r0.f2659a
            java.lang.String r9 = "android.permission.ACCESS_COARSE_LOCATION"
            boolean r5 = com.truenet.android.p049a.C1753h.m3862a(r5, r9)
            if (r5 == 0) goto L_0x00aa
            goto L_0x00ab
        L_0x00aa:
            r3 = 0
        L_0x00ab:
            if (r3 == 0) goto L_0x00be
            android.telephony.TelephonyManager r5 = r0.f2660b
            int r5 = com.truenet.android.p049a.C1750f.m3854a(r5)
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)
            java.lang.String r5 = java.lang.String.valueOf(r5)
            r20 = r5
            goto L_0x00c0
        L_0x00be:
            r20 = r4
        L_0x00c0:
            if (r3 == 0) goto L_0x00d0
            android.telephony.TelephonyManager r3 = r0.f2660b
            int r3 = com.truenet.android.p049a.C1750f.m3855b(r3)
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
            java.lang.String r4 = java.lang.String.valueOf(r3)
        L_0x00d0:
            r21 = r4
            com.startapp.common.c r3 = com.startapp.common.C1292c.m2158a()
            java.lang.String r4 = "NetworkStats.get()"
            p055a.p056a.p058b.p060b.C2928h.m6154a((java.lang.Object) r3, (java.lang.String) r4)
            java.lang.String r3 = r3.mo15500b()
            com.truenet.android.a.i$a r4 = com.truenet.android.p049a.C1754i.f2667a
            android.content.Context r5 = r0.f2659a
            java.lang.String r4 = r4.mo16520a(r5)
            boolean r5 = r28.m3845b()
            if (r5 == 0) goto L_0x00f0
            java.lang.String r5 = "tablet"
            goto L_0x00f2
        L_0x00f0:
            java.lang.String r5 = "phone"
        L_0x00f2:
            r24 = r5
            com.truenet.android.DeviceInfo r27 = new com.truenet.android.DeviceInfo
            r5 = r27
            java.lang.String r1 = r1.toString()
            r9 = r1
            java.lang.String r11 = "locale.toString()"
            p055a.p056a.p058b.p060b.C2928h.m6154a((java.lang.Object) r1, (java.lang.String) r11)
            java.lang.String r1 = "advertisingId"
            p055a.p056a.p058b.p060b.C2928h.m6154a((java.lang.Object) r10, (java.lang.String) r1)
            int r1 = android.os.Build.VERSION.SDK_INT
            java.lang.String r12 = java.lang.String.valueOf(r1)
            java.lang.String r1 = android.os.Build.MODEL
            r13 = r1
            java.lang.String r11 = "Build.MODEL"
            p055a.p056a.p058b.p060b.C2928h.m6154a((java.lang.Object) r1, (java.lang.String) r11)
            java.lang.String r1 = android.os.Build.MANUFACTURER
            r14 = r1
            java.lang.String r11 = "Build.MANUFACTURER"
            p055a.p056a.p058b.p060b.C2928h.m6154a((java.lang.Object) r1, (java.lang.String) r11)
            java.lang.String r1 = android.os.Build.VERSION.RELEASE
            r15 = r1
            java.lang.String r11 = "Build.VERSION.RELEASE"
            p055a.p056a.p058b.p060b.C2928h.m6154a((java.lang.Object) r1, (java.lang.String) r11)
            android.content.Context r1 = r0.f2659a
            java.lang.String r1 = r1.getPackageName()
            r16 = r1
            java.lang.String r11 = "context.packageName"
            p055a.p056a.p058b.p060b.C2928h.m6154a((java.lang.Object) r1, (java.lang.String) r11)
            java.lang.String r1 = "ips"
            p055a.p056a.p058b.p060b.C2928h.m6154a((java.lang.Object) r2, (java.lang.String) r1)
            java.lang.String r1 = "ipsName"
            p055a.p056a.p058b.p060b.C2928h.m6154a((java.lang.Object) r8, (java.lang.String) r1)
            android.content.Context r1 = r0.f2659a
            com.truenet.android.a.e r1 = com.truenet.android.p049a.C1748d.m3852b(r1)
            java.lang.String r22 = r1.mo16518a()
            java.lang.String r1 = "signalLevel"
            p055a.p056a.p058b.p060b.C2928h.m6154a((java.lang.Object) r3, (java.lang.String) r1)
            java.lang.String r11 = "android"
            java.lang.String r25 = "1.0.16"
            java.lang.String r26 = ""
            r1 = r8
            r8 = r4
            r18 = r2
            r19 = r1
            r23 = r3
            r5.<init>(r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24, r25, r26)
            return r27
        */
        throw new UnsupportedOperationException("Method not decompiled: com.truenet.android.C1744a.mo16517a():com.truenet.android.DeviceInfo");
    }
}

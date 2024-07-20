package com.startapp.android.publish.adsCommon.p033f;

import android.app.ActivityManager;
import android.content.Context;
import com.startapp.android.publish.adsCommon.Utils.C1061i;
import com.startapp.android.publish.adsCommon.p033f.C1133g;
import com.startapp.android.publish.common.metaData.MetaData;
import com.startapp.android.publish.common.model.AdPreferences;
import com.startapp.common.p043a.C1261c;
import com.startapp.common.p043a.C1270g;

/* renamed from: com.startapp.android.publish.adsCommon.f.f */
/* compiled from: StartAppSDK */
public class C1132f {
    /* renamed from: a */
    public static void m1527a(Context context, C1130d dVar, String str, String str2, String str3) {
        m1530a(context, new C1131e(dVar, str, str2), str3, (C1133g.C1135a) null);
    }

    /* renamed from: a */
    public static void m1528a(Context context, C1130d dVar, String str, String str2, String str3, C1133g.C1135a aVar) {
        m1530a(context, new C1131e(dVar, str, str2), str3, aVar);
    }

    /* renamed from: a */
    public static void m1529a(Context context, C1131e eVar, String str) {
        m1530a(context, eVar, str, (C1133g.C1135a) null);
    }

    /* renamed from: a */
    public static void m1530a(Context context, C1131e eVar, String str, C1133g.C1135a aVar) {
        if (!MetaData.getInstance().getAnalyticsConfig().mo14867c()) {
            eVar.mo14889e(str);
            eVar.mo14885a(context);
            try {
                eVar.mo14891f(C1061i.m1200b(context));
                ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
                ((ActivityManager) context.getSystemService("activity")).getMemoryInfo(memoryInfo);
                eVar.mo14895h(Long.toString(memoryInfo.availMem / 1048576));
                Long a = C1261c.m2016a(memoryInfo);
                if (a != null) {
                    eVar.mo14893g(Long.toString((a.longValue() - memoryInfo.availMem) / 1048576));
                }
            } catch (Throwable th) {
                C1270g.m2079a("InfoEventsManager", 6, "Error filling infoEvent", th);
            }
            C1270g.m2078a("InfoEventsManager", 3, "Sending " + eVar);
            new C1133g(context, new AdPreferences(), eVar, aVar).mo14904a();
        }
    }
}

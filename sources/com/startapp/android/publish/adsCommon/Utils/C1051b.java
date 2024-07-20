package com.startapp.android.publish.adsCommon.Utils;

import android.content.Context;
import android.os.SystemClock;
import com.startapp.android.publish.adsCommon.C1166k;
import com.startapp.android.publish.adsCommon.p033f.C1130d;
import com.startapp.android.publish.adsCommon.p033f.C1132f;
import com.startapp.android.publish.common.metaData.C1232e;
import com.startapp.android.publish.common.metaData.C1234f;
import com.startapp.android.publish.common.metaData.MetaData;
import com.startapp.common.p043a.C1270g;
import com.startapp.common.p044b.C1279a;
import com.startapp.common.p044b.C1289b;
import com.startapp.common.p044b.p045a.C1284a;
import com.startapp.common.p044b.p045a.C1285b;
import com.startapp.common.p044b.p045a.C1288c;

/* renamed from: com.startapp.android.publish.adsCommon.Utils.b */
/* compiled from: StartAppSDK */
public class C1051b {

    /* renamed from: a */
    private static volatile boolean f982a = false;

    /* renamed from: com.startapp.android.publish.adsCommon.Utils.b$a */
    /* compiled from: StartAppSDK */
    public static final class C1053a implements C1284a {
        public C1285b create(int i) {
            if (i == 586482792) {
                return new C1234f();
            }
            if (i != 786564404) {
                return null;
            }
            return new C1232e();
        }
    }

    /* renamed from: a */
    public static void m1136a(Context context) {
        if (!f982a) {
            f982a = true;
            C1279a.m2118a((C1288c) new C1288c() {
                /* renamed from: a */
                public void mo14625a(int i, String str, String str2, Throwable th) {
                    C1270g.m2079a(str, i, str2, th);
                }
            });
            C1279a.m2109a(context);
            C1279a.m2117a((C1284a) new C1053a());
        }
    }

    /* renamed from: a */
    public static long m1134a() {
        return SystemClock.elapsedRealtime() + (((long) MetaData.getInstance().getPeriodicMetaDataInterval()) * 60000);
    }

    /* renamed from: b */
    public static long m1140b(Context context) {
        return SystemClock.elapsedRealtime() + (((long) MetaData.getInstance().getPeriodicInfoEventIntervalInMinutes(context)) * 60000);
    }

    /* renamed from: c */
    public static void m1141c(Context context) {
        m1139a(context, Long.valueOf(m1140b(context)));
    }

    /* renamed from: a */
    public static void m1139a(Context context, Long l) {
        C1270g.m2078a("StartAppWall.DataUtils", 3, "setMetaDataPeriodicAlarm executes " + l);
        if (!C1166k.m1606a(context, "periodicMetadataPaused", (Boolean) false).booleanValue() && MetaData.getInstance().isPeriodicMetaDataEnabled()) {
            m1137a(context, 586482792, l.longValue() - SystemClock.elapsedRealtime(), "periodicMetadataTriggerTime");
        }
    }

    /* renamed from: d */
    public static void m1142d(Context context) {
        m1138a(context, m1140b(context));
    }

    /* renamed from: a */
    public static void m1138a(Context context, long j) {
        C1270g.m2078a("StartAppWall.DataUtils", 3, "setInfoEventPeriodicAlarm executes " + j);
        if (!C1166k.m1606a(context, "periodicInfoEventPaused", (Boolean) false).booleanValue() && MetaData.getInstance().isPeriodicInfoEventEnabled()) {
            m1137a(context, 786564404, j - SystemClock.elapsedRealtime(), "periodicInfoEventTriggerTime");
        }
    }

    /* renamed from: a */
    public static void m1135a(int i) {
        C1279a.m2115a(i, false);
    }

    /* renamed from: a */
    private static void m1137a(Context context, int i, long j, String str) {
        if (C1279a.m2122a(new C1289b.C1291a(i).mo15493a(j).mo15497a())) {
            C1166k.m1616b(context, str, Long.valueOf(j + SystemClock.elapsedRealtime()));
            return;
        }
        C1130d dVar = C1130d.EXCEPTION;
        C1132f.m1527a(context, dVar, "Util.setPeriodicAlarm - failed setting alarm " + i, "", "");
    }
}

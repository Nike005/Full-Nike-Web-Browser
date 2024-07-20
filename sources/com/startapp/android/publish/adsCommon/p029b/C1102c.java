package com.startapp.android.publish.adsCommon.p029b;

import android.content.Context;
import com.startapp.android.publish.adsCommon.C1040Ad;
import com.startapp.android.publish.adsCommon.C1098b;
import com.startapp.android.publish.adsCommon.C1118e;
import com.startapp.android.publish.adsCommon.C1148h;
import com.startapp.android.publish.adsCommon.C1168l;
import com.startapp.android.publish.adsCommon.Utils.C1061i;
import com.startapp.android.publish.common.model.AdDetails;
import com.startapp.common.p043a.C1261c;
import com.startapp.common.p043a.C1270g;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/* renamed from: com.startapp.android.publish.adsCommon.b.c */
/* compiled from: StartAppSDK */
public class C1102c {
    /* renamed from: a */
    public static boolean m1363a(Context context, C1040Ad ad) {
        if (ad == null) {
            return false;
        }
        HashSet hashSet = new HashSet();
        if (ad instanceof C1118e) {
            return m1359a(context, m1362a(((C1118e) ad).mo14843f(), 0), 0, (Set<String>) hashSet, (List<C1099a>) new ArrayList()).booleanValue();
        }
        if (!(ad instanceof C1148h)) {
            return false;
        }
        List<AdDetails> a = m1361a(context, ((C1148h) ad).mo14937d(), 0, (Set<String>) hashSet, false);
        if (a == null || a.size() == 0) {
            return true;
        }
        return false;
    }

    /* renamed from: a */
    public static List<AdDetails> m1360a(Context context, List<AdDetails> list, int i, Set<String> set) {
        return m1361a(context, list, i, set, true);
    }

    /* renamed from: a */
    public static List<AdDetails> m1361a(Context context, List<AdDetails> list, int i, Set<String> set, boolean z) {
        Context context2 = context;
        int i2 = i;
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        ArrayList arrayList4 = new ArrayList();
        boolean z2 = false;
        for (AdDetails next : list) {
            C1099a aVar = new C1099a(next.getTrackingUrl(), next.getAppPresencePackage(), i2, next.getMinAppVersion());
            boolean z3 = next.getAppPresencePackage() != null && next.getAppPresencePackage().startsWith("!");
            String appPresencePackage = next.getAppPresencePackage();
            if (z3) {
                appPresencePackage = appPresencePackage.substring(1);
            }
            boolean a = C1261c.m2032a(context2, appPresencePackage, next.getMinAppVersion());
            boolean z4 = C1098b.m1303a().mo14751E() && ((a && !z3) || (!a && z3));
            arrayList3.add(aVar);
            if (z4) {
                aVar.mo14791b(a);
                aVar.mo14789a(false);
                if (!z3) {
                    arrayList2.add(next);
                    arrayList4.add(aVar);
                }
                set.add(next.getPackageName());
                C1270g.m2078a("AppPresenceUtil", 3, "App Presence:[" + next.getPackageName() + "]");
                z2 = true;
            } else {
                Set<String> set2 = set;
                arrayList.add(next);
            }
            C1270g.m2078a("AppPresenceUtil", 3, "App Not Presence:[" + next.getPackageName() + "]");
        }
        if (arrayList.size() < 5 && (list.size() != 1 || i2 > 0)) {
            int min = Math.min(5 - arrayList.size(), arrayList2.size());
            arrayList.addAll(arrayList2.subList(0, min));
            for (C1099a a2 : arrayList4.subList(0, min)) {
                a2.mo14789a(true);
            }
        }
        if (z2) {
            C1168l.m1638c(context);
            if (z) {
                new C1100b(context2, arrayList3).mo14795a();
            }
        }
        return arrayList;
    }

    /* renamed from: a */
    public static List<C1099a> m1362a(String str, int i) {
        ArrayList arrayList = new ArrayList();
        String[] strArr = new String[0];
        String a = C1061i.m1179a(str, "@tracking@", "@tracking@");
        if (a != null) {
            strArr = a.split(",");
        }
        String[] strArr2 = new String[0];
        String a2 = C1061i.m1179a(str, "@appPresencePackage@", "@appPresencePackage@");
        if (a2 != null) {
            strArr2 = a2.split(",");
        }
        String[] strArr3 = new String[0];
        String a3 = C1061i.m1179a(str, "@minAppVersion@", "@minAppVersion@");
        if (a3 != null) {
            strArr3 = a3.split(",");
        }
        int i2 = 0;
        while (i2 < strArr2.length) {
            arrayList.add(new C1099a(strArr.length > i2 ? strArr[i2] : null, strArr2[i2], i, strArr3.length > i2 ? Integer.valueOf(strArr3[i2]).intValue() : 0));
            i2++;
        }
        while (i2 < strArr.length) {
            arrayList.add(new C1099a(strArr[i2], "", i, strArr3.length > i2 ? Integer.valueOf(strArr3[i2]).intValue() : 0));
            i2++;
        }
        return arrayList;
    }

    /* renamed from: a */
    public static Boolean m1359a(Context context, List<C1099a> list, int i, Set<String> set, List<C1099a> list2) {
        boolean z = false;
        for (C1099a next : list) {
            boolean startsWith = next.mo14790b().startsWith("!");
            String b = next.mo14790b();
            if (startsWith) {
                b = b.substring(1);
            }
            boolean a = C1261c.m2032a(context, b, next.mo14794e());
            if ((!startsWith && a) || (startsWith && !a)) {
                C1270g.m2078a("AppPresenceUtil", 3, "in isAppPresent, skipAd is true");
                next.mo14791b(a);
                z = i == 0;
                if (z && !startsWith) {
                    set.add(next.mo14790b());
                } else if (!z && next.mo14787a() != null) {
                    next.mo14788a(next.mo14787a() + "&isShown=" + next.mo14792c() + "&appPresence=" + next.mo14793d());
                }
            }
            list2.add(next);
        }
        if (z) {
            for (int i2 = 0; i2 < list2.size(); i2++) {
                list2.get(i2).mo14789a(false);
            }
        }
        return Boolean.valueOf(z);
    }
}

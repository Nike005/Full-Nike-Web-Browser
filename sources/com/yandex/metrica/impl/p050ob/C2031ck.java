package com.yandex.metrica.impl.p050ob;

import android.content.Context;
import android.content.pm.PackageItemInfo;
import com.yandex.metrica.YandexMetrica;
import com.yandex.metrica.impl.C1887be;
import com.yandex.metrica.impl.C1894bi;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;

/* renamed from: com.yandex.metrica.impl.ob.ck */
public class C2031ck {

    /* renamed from: a */
    private C2026ci f3423a;

    public C2031ck(C2026ci ciVar) {
        this.f3423a = ciVar;
    }

    /* renamed from: a */
    public String mo17492a(Context context) {
        return mo17494b(context);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public String mo17494b(Context context) {
        C2030cj cjVar = new C2030cj(context);
        List<C1887be.C1888a> c = mo17495c(context);
        ArrayList<C2020ce> arrayList = new ArrayList<>(c.size());
        LinkedList linkedList = new LinkedList();
        Iterator<C1887be.C1888a> it = c.iterator();
        while (true) {
            Object obj = null;
            if (!it.hasNext()) {
                break;
            }
            C1887be.C1888a next = it.next();
            if (C1887be.m4550a((PackageItemInfo) next.f3089d) < 29) {
                linkedList.add(next);
            } else {
                if (this.f3423a.mo17481e()) {
                    String str = next.f3089d.applicationInfo.packageName;
                    C2025ch a = this.f3423a.mo17472a(context, str);
                    C2025ch b = this.f3423a.mo17474b(context, str);
                    if (!(a == null && b == null)) {
                        obj = new C2024cg(next, b, a);
                    }
                } else {
                    C2025ch a2 = this.f3423a.mo17472a(context, next.f3089d.applicationInfo.packageName);
                    if (a2 != null && !C1894bi.m4622a(a2.mo17467c())) {
                        obj = new C2020ce(next, a2);
                    }
                }
                if (obj != null) {
                    arrayList.add(obj);
                }
            }
        }
        Iterator it2 = linkedList.iterator();
        while (it2.hasNext()) {
            C1887be.C1888a aVar = (C1887be.C1888a) it2.next();
            String f = this.f3423a.mo17483f(context, aVar.f3089d.packageName);
            if (!C1894bi.m4622a(f)) {
                arrayList.add(new C2020ce(aVar, new C2025ch(f, (C2030cj) null, -1)));
            }
        }
        if (!arrayList.isEmpty()) {
            HashMap hashMap = new HashMap();
            for (C2020ce ceVar : arrayList) {
                String a3 = ceVar.mo17452a();
                C2022cf cfVar = (C2022cf) hashMap.get(a3);
                if (cfVar == null) {
                    cfVar = new C2022cf(a3, cjVar);
                    hashMap.put(a3, cfVar);
                }
                cfVar.mo17456a(ceVar);
            }
            if (hashMap.size() == 1) {
                Iterator it3 = hashMap.values().iterator();
                if (it3.hasNext()) {
                    return ((C2022cf) it3.next()).mo17459c();
                }
                final StringBuilder sb = new StringBuilder();
                sb.append("Smth wrong when iterate through initial candidates list");
                sb.append(10);
                YandexMetrica.getReporter(context, "20799a27-fa80-4b36-b2db-0f8141f24180").reportEvent("multiple_device_ids", (Map<String, Object>) new HashMap<String, Object>() {
                    {
                        put("error", sb.toString());
                    }
                });
            } else {
                m5258c(context, arrayList);
                return m5254a(context, (Map<String, C2022cf>) hashMap);
            }
        }
        return "";
    }

    /* renamed from: a */
    private String m5254a(Context context, Map<String, C2022cf> map) {
        m5256a(context, "method_carriers_count", map.size());
        ArrayList arrayList = new ArrayList();
        int i = 0;
        for (C2022cf next : map.values()) {
            int b = next.mo17458b();
            if (b > i) {
                arrayList.clear();
                arrayList.add(next);
                i = b;
            } else if (b == i) {
                arrayList.add(next);
            }
        }
        if (arrayList.size() == 1) {
            return ((C2022cf) arrayList.get(0)).mo17459c();
        }
        String a = ((C2022cf) arrayList.get(0)).mo17458b() == 1 ? m5253a(context, (ArrayList<C2022cf>) arrayList) : null;
        if (a != null) {
            return a;
        }
        List<C2022cf> a2 = m5255a((List<C2022cf>) arrayList);
        if (a2 == null) {
            return mo17493a(context, (List<C2022cf>) arrayList);
        }
        return mo17493a(context, a2);
    }

    /* renamed from: a */
    private static List<C2022cf> m5255a(List<C2022cf> list) {
        ArrayList arrayList = new ArrayList();
        for (C2022cf next : list) {
            if (!next.mo17457a()) {
                arrayList.add(next);
            }
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        return arrayList;
    }

    /* renamed from: a */
    private static String m5253a(Context context, ArrayList<C2022cf> arrayList) {
        String packageName = context.getPackageName();
        Iterator<C2022cf> it = arrayList.iterator();
        C2022cf cfVar = null;
        C2022cf cfVar2 = null;
        while (it.hasNext()) {
            C2022cf next = it.next();
            if (packageName.equals(next.mo17460d().get(0).mo17454c().f3090e)) {
                cfVar = next;
            } else {
                cfVar2 = next;
            }
        }
        if (cfVar == null) {
            return null;
        }
        if (!cfVar2.mo17457a()) {
            return cfVar2.mo17459c();
        }
        if (cfVar.mo17457a()) {
            return cfVar2.mo17459c();
        }
        return cfVar.mo17459c();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public String mo17493a(Context context, List<C2022cf> list) {
        m5256a(context, "method_first_installed", list.size());
        ArrayList arrayList = new ArrayList();
        long j = Long.MAX_VALUE;
        for (C2022cf next : list) {
            Long e = next.mo17461e();
            int compareTo = e.compareTo(j);
            if (compareTo < 0) {
                arrayList.clear();
                arrayList.add(next);
                j = e;
            } else if (compareTo == 0) {
                arrayList.add(next);
            }
        }
        if (arrayList.size() == 1) {
            return ((C2022cf) arrayList.get(0)).mo17459c();
        }
        return m5257b(context, arrayList);
    }

    /* renamed from: b */
    private static String m5257b(Context context, List<C2022cf> list) {
        m5256a(context, "method_device_id_comparing", list.size());
        String str = "";
        for (C2022cf next : list) {
            if (next.mo17459c().compareTo(str) > 0) {
                str = next.mo17459c();
            }
        }
        return str;
    }

    /* renamed from: c */
    private static void m5258c(Context context, List<C2020ce> list) {
        final StringBuilder sb = new StringBuilder();
        for (C2020ce next : list) {
            sb.append(next.mo17454c().f3089d.packageName);
            sb.append(StringUtils.SPACE);
            sb.append(next.toString());
            sb.append(10);
        }
        YandexMetrica.getReporter(context, "20799a27-fa80-4b36-b2db-0f8141f24180").reportEvent("multiple_device_ids", (Map<String, Object>) new HashMap<String, Object>() {
            {
                put("data", sb.toString());
            }
        });
    }

    /* access modifiers changed from: package-private */
    /* renamed from: c */
    public List<C1887be.C1888a> mo17495c(Context context) {
        return C1887be.m4555b(context);
    }

    /* renamed from: a */
    private static void m5256a(Context context, final String str, final int i) {
        YandexMetrica.getReporter(context, "20799a27-fa80-4b36-b2db-0f8141f24180").reportEvent("multiple_device_ids", (Map<String, Object>) new HashMap<String, Object>() {
            {
                put(str, new HashMap<String, Object>() {
                    {
                        put("candidates_count", Integer.valueOf(i));
                    }
                });
            }
        });
    }
}

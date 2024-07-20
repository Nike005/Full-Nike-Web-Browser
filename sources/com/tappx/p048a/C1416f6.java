package com.tappx.p048a;

import com.tappx.p048a.C1445h5;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.TreeMap;

/* renamed from: com.tappx.a.f6 */
public class C1416f6 {
    /* renamed from: a */
    public static C1445h5.C1446a m2680a(C1590q5 q5Var) {
        long j;
        long j2;
        boolean z;
        long j3;
        long j4;
        long j5;
        C1590q5 q5Var2 = q5Var;
        long currentTimeMillis = System.currentTimeMillis();
        Map<String, String> map = q5Var2.f2230c;
        String str = map.get("Date");
        long b = str != null ? m2685b(str) : 0;
        String str2 = map.get("Cache-Control");
        int i = 0;
        if (str2 != null) {
            String[] split = str2.split(",", 0);
            int i2 = 0;
            j2 = 0;
            j = 0;
            while (i < split.length) {
                String trim = split[i].trim();
                if (trim.equals("no-cache") || trim.equals("no-store")) {
                    return null;
                }
                if (trim.startsWith("max-age=")) {
                    try {
                        j2 = Long.parseLong(trim.substring(8));
                    } catch (Exception unused) {
                    }
                } else if (trim.startsWith("stale-while-revalidate=")) {
                    j = Long.parseLong(trim.substring(23));
                } else if (trim.equals("must-revalidate") || trim.equals("proxy-revalidate")) {
                    i2 = 1;
                }
                i++;
            }
            i = i2;
            z = true;
        } else {
            z = false;
            j2 = 0;
            j = 0;
        }
        String str3 = map.get("Expires");
        long b2 = str3 != null ? m2685b(str3) : 0;
        String str4 = map.get("Last-Modified");
        long b3 = str4 != null ? m2685b(str4) : 0;
        String str5 = map.get("ETag");
        if (z) {
            long j6 = currentTimeMillis + (j2 * 1000);
            if (i != 0) {
                j5 = j6;
            } else {
                Long.signum(j);
                j5 = (j * 1000) + j6;
            }
            j3 = j6;
            j4 = j5;
        } else {
            j3 = 0;
            if (b <= 0 || b2 < b) {
                j4 = 0;
            } else {
                j4 = currentTimeMillis + (b2 - b);
                j3 = j4;
            }
        }
        C1445h5.C1446a aVar = new C1445h5.C1446a();
        aVar.f1915a = q5Var2.f2229b;
        aVar.f1916b = str5;
        aVar.f1920f = j3;
        aVar.f1919e = j4;
        aVar.f1917c = b;
        aVar.f1918d = b3;
        aVar.f1921g = map;
        aVar.f1922h = q5Var2.f2231d;
        return aVar;
    }

    /* renamed from: b */
    public static long m2685b(String str) {
        try {
            return m2682a("EEE, dd MMM yyyy HH:mm:ss zzz").parse(str).getTime();
        } catch (ParseException e) {
            if ("0".equals(str) || "-1".equals(str)) {
                C1318a6.m2235d("Unable to parse dateStr: %s, falling back to 0", str);
                return 0;
            }
            C1318a6.m2232a(e, "Unable to parse dateStr: %s, falling back to 0", str);
            return 0;
        }
    }

    /* renamed from: a */
    static String m2681a(long j) {
        return m2682a("EEE, dd MMM yyyy HH:mm:ss 'GMT'").format(new Date(j));
    }

    /* renamed from: a */
    private static SimpleDateFormat m2682a(String str) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(str, Locale.US);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        return simpleDateFormat;
    }

    /* renamed from: a */
    static Map<String, String> m2684a(List<C1528m5> list) {
        TreeMap treeMap = new TreeMap(String.CASE_INSENSITIVE_ORDER);
        for (C1528m5 next : list) {
            treeMap.put(next.mo15969a(), next.mo15970b());
        }
        return treeMap;
    }

    /* renamed from: a */
    static List<C1528m5> m2683a(Map<String, String> map) {
        ArrayList arrayList = new ArrayList(map.size());
        for (Map.Entry next : map.entrySet()) {
            arrayList.add(new C1528m5((String) next.getKey(), (String) next.getValue()));
        }
        return arrayList;
    }
}

package com.yandex.metrica.impl;

import android.content.ContentValues;
import android.content.Context;
import android.text.TextUtils;
import android.util.Base64;
import android.util.SparseArray;
import com.appnext.base.p082b.C4905i;
import com.integralads.avid.library.mopub.utils.AvidJSONUtil;
import com.mopub.network.ImpressionData;
import com.yandex.metrica.C1781c;
import com.yandex.metrica.C1796d;
import com.yandex.metrica.impl.C1899bm;
import com.yandex.metrica.impl.C2208p;
import com.yandex.metrica.impl.p050ob.C1971bl;
import com.yandex.metrica.impl.p050ob.C2116ee;
import com.yandex.metrica.impl.utils.C2223g;
import com.yandex.metrica.impl.utils.C2233n;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.yandex.metrica.impl.ap */
final class C1842ap {

    /* renamed from: a */
    private static Map<C1971bl, Integer> f2956a;

    /* renamed from: b */
    private static SparseArray<C1971bl> f2957b;

    /* renamed from: a */
    public static void m4268a() {
    }

    static {
        HashMap hashMap = new HashMap();
        hashMap.put(C1971bl.FOREGROUND, 0);
        hashMap.put(C1971bl.BACKGROUND, 1);
        f2956a = Collections.unmodifiableMap(hashMap);
        SparseArray<C1971bl> sparseArray = new SparseArray<>();
        sparseArray.put(0, C1971bl.FOREGROUND);
        sparseArray.put(1, C1971bl.BACKGROUND);
        f2957b = sparseArray;
    }

    /* renamed from: a */
    public static C1781c.C1782a.C1795f m4264a(ContentValues contentValues) {
        return m4265a(contentValues.getAsLong("start_time"), contentValues.getAsLong("server_time_offset"));
    }

    /* renamed from: a */
    public static C1781c.C1782a.C1794e m4263a(C2116ee eeVar) {
        C1781c.C1782a.C1794e eVar = new C1781c.C1782a.C1794e();
        if (eeVar.mo17700a() != null) {
            eVar.f2829b = eeVar.mo17700a().intValue();
        }
        if (eeVar.mo17701b() != null) {
            eVar.f2830c = eeVar.mo17701b().intValue();
        }
        if (!TextUtils.isEmpty(eeVar.mo17703d())) {
            eVar.f2831d = eeVar.mo17703d();
        }
        eVar.f2832e = eeVar.mo17702c();
        if (!TextUtils.isEmpty(eeVar.mo17704e())) {
            eVar.f2833f = eeVar.mo17704e();
        }
        return eVar;
    }

    /* renamed from: a */
    public static C1971bl m4266a(int i) {
        return f2957b.get(i);
    }

    /* renamed from: a */
    public static List<C1781c.C1782a.C1786d.C1793c> m4267a(String str) {
        try {
            ArrayList arrayList = new ArrayList();
            JSONArray jSONArray = new JSONArray(str);
            for (int i = 0; i < jSONArray.length(); i++) {
                try {
                    arrayList.add(m4262a(jSONArray.getJSONObject(i)));
                } catch (Exception unused) {
                }
            }
            return arrayList;
        } catch (Exception unused2) {
            return new ArrayList();
        }
    }

    /* renamed from: a */
    public static C1781c.C1782a.C1786d.C1793c m4262a(JSONObject jSONObject) throws JSONException {
        try {
            C1781c.C1782a.C1786d.C1793c cVar = new C1781c.C1782a.C1786d.C1793c();
            cVar.f2824b = jSONObject.getString("mac");
            cVar.f2825c = jSONObject.getInt("signal_strength");
            cVar.f2826d = jSONObject.getString("ssid");
            cVar.f2827e = jSONObject.optBoolean("is_connected");
            return cVar;
        } catch (Exception unused) {
            C1781c.C1782a.C1786d.C1793c cVar2 = new C1781c.C1782a.C1786d.C1793c();
            cVar2.f2824b = jSONObject.getString("mac");
            return cVar2;
        }
    }

    /* renamed from: b */
    static C1781c.C1782a.C1786d.C1787a.C1789b.C1791b m4271b(String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            JSONObject jSONObject = new JSONObject(str);
            C1781c.C1782a.C1786d.C1787a.C1789b.C1791b bVar = new C1781c.C1782a.C1786d.C1787a.C1789b.C1791b();
            bVar.f2818b = jSONObject.optString("ssid");
            int optInt = jSONObject.optInt("state", -1);
            if (!(optInt == 0 || optInt == 1 || optInt == 2)) {
                if (optInt == 3) {
                    bVar.f2819c = 2;
                } else if (optInt != 4) {
                }
                return bVar;
            }
            bVar.f2819c = 1;
            return bVar;
        } catch (Exception unused) {
            return null;
        }
    }

    /* renamed from: a */
    public static C1781c.C1782a.C1795f m4265a(Long l, Long l2) {
        long longValue = l.longValue();
        C1781c.C1782a.C1795f fVar = new C1781c.C1782a.C1795f();
        fVar.f2834b = longValue;
        fVar.f2835c = ((GregorianCalendar) GregorianCalendar.getInstance()).getTimeZone().getOffset(longValue * 1000) / 1000;
        if (l2 != null) {
            fVar.f2836d = l2.longValue();
        }
        return fVar;
    }

    /* renamed from: a */
    public static C1781c.C1782a.C1786d.C1792b m4261a(String str, int i, C1781c.C1782a.C1795f fVar) {
        C1781c.C1782a.C1786d.C1792b bVar = new C1781c.C1782a.C1786d.C1792b();
        bVar.f2820b = fVar;
        bVar.f2821c = str;
        bVar.f2822d = i;
        return bVar;
    }

    /* renamed from: a */
    static int m4259a(C1971bl blVar) {
        return f2956a.get(blVar).intValue();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0037, code lost:
        return new com.yandex.metrica.C1781c.C1782a.C1786d.C1787a.C1789b.C1790a[]{m4270b(new org.json.JSONObject(r5))};
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:?, code lost:
        return null;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:12:0x0029 */
    /* renamed from: c */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.yandex.metrica.C1781c.C1782a.C1786d.C1787a.C1789b.C1790a[] m4272c(java.lang.String r5) {
        /*
            r0 = 0
            boolean r1 = android.text.TextUtils.isEmpty(r5)     // Catch:{ JSONException -> 0x0029 }
            if (r1 != 0) goto L_0x0038
            org.json.JSONArray r1 = new org.json.JSONArray     // Catch:{ JSONException -> 0x0029 }
            r1.<init>(r5)     // Catch:{ JSONException -> 0x0029 }
            int r2 = r1.length()     // Catch:{ JSONException -> 0x0029 }
            com.yandex.metrica.c$a$d$a$b$a[] r2 = new com.yandex.metrica.C1781c.C1782a.C1786d.C1787a.C1789b.C1790a[r2]     // Catch:{ JSONException -> 0x0029 }
            r3 = 0
        L_0x0013:
            int r4 = r1.length()     // Catch:{ JSONException -> 0x0029 }
            if (r3 >= r4) goto L_0x0028
            org.json.JSONObject r4 = r1.getJSONObject(r3)     // Catch:{ JSONException -> 0x0029 }
            if (r4 == 0) goto L_0x0025
            com.yandex.metrica.c$a$d$a$b$a r4 = m4270b((org.json.JSONObject) r4)     // Catch:{ JSONException -> 0x0029 }
            r2[r3] = r4     // Catch:{ JSONException -> 0x0029 }
        L_0x0025:
            int r3 = r3 + 1
            goto L_0x0013
        L_0x0028:
            return r2
        L_0x0029:
            org.json.JSONObject r1 = new org.json.JSONObject     // Catch:{ Exception -> 0x0038 }
            r1.<init>(r5)     // Catch:{ Exception -> 0x0038 }
            r5 = 1
            com.yandex.metrica.c$a$d$a$b$a[] r5 = new com.yandex.metrica.C1781c.C1782a.C1786d.C1787a.C1789b.C1790a[r5]     // Catch:{ Exception -> 0x0038 }
            com.yandex.metrica.c$a$d$a$b$a r1 = m4270b((org.json.JSONObject) r1)     // Catch:{ Exception -> 0x0038 }
            r5[r0] = r1     // Catch:{ Exception -> 0x0038 }
            return r5
        L_0x0038:
            r5 = 0
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.yandex.metrica.impl.C1842ap.m4272c(java.lang.String):com.yandex.metrica.c$a$d$a$b$a[]");
    }

    /* renamed from: b */
    static C1781c.C1782a.C1786d.C1787a.C1789b.C1790a m4270b(JSONObject jSONObject) {
        int optInt;
        C1781c.C1782a.C1786d.C1787a.C1789b.C1790a aVar = new C1781c.C1782a.C1786d.C1787a.C1789b.C1790a();
        if (jSONObject.has("signal_strength") && (optInt = jSONObject.optInt("signal_strength")) != -1) {
            aVar.f2810c = optInt;
        }
        if (jSONObject.has("cell_id")) {
            aVar.f2809b = jSONObject.optInt("cell_id");
        }
        if (jSONObject.has("lac")) {
            aVar.f2811d = jSONObject.optInt("lac");
        }
        if (jSONObject.has("country_code")) {
            aVar.f2812e = jSONObject.optInt("country_code");
        }
        if (jSONObject.has("operator_id")) {
            aVar.f2813f = jSONObject.optInt("operator_id");
        }
        if (jSONObject.has("operator_name")) {
            aVar.f2814g = jSONObject.optString("operator_name");
        }
        if (jSONObject.has("is_connected")) {
            aVar.f2815h = jSONObject.optBoolean("is_connected");
        }
        aVar.f2816i = jSONObject.optInt("cell_type", 0);
        if (jSONObject.has("pci")) {
            aVar.f2817j = jSONObject.optInt("pci");
        }
        return aVar;
    }

    /* renamed from: d */
    public static C1781c.C1782a.C1784b m4273d(String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            C2223g.C2224a aVar = new C2223g.C2224a(str);
            C1781c.C1782a.C1784b bVar = new C1781c.C1782a.C1784b();
            bVar.f2774c = aVar.getDouble("lon");
            bVar.f2773b = aVar.getDouble(C4905i.f4638fC);
            if (aVar.mo17923b("altitude")) {
                bVar.f2779h = aVar.getInt("altitude");
            }
            if (aVar.mo17923b("direction")) {
                bVar.f2777f = aVar.getInt("direction");
            }
            if (aVar.mo17923b(ImpressionData.PRECISION)) {
                bVar.f2776e = aVar.getInt(ImpressionData.PRECISION);
            }
            if (aVar.mo17923b("speed")) {
                bVar.f2778g = aVar.getInt("speed");
            }
            if (aVar.mo17923b(AvidJSONUtil.KEY_TIMESTAMP)) {
                bVar.f2775d = aVar.getLong(AvidJSONUtil.KEY_TIMESTAMP) / 1000;
            }
            if (aVar.mo17923b("provider")) {
                String a = aVar.mo17922a("provider");
                if ("gps".equals(a)) {
                    bVar.f2780i = 1;
                } else if ("network".equals(a)) {
                    bVar.f2780i = 2;
                }
            }
            return bVar;
        } catch (Exception unused) {
            return null;
        }
    }

    /* renamed from: a */
    public static C1781c.C1782a.C1786d.C1787a.C1789b m4260a(int i, String str, String str2, String str3, String str4) {
        C1781c.C1782a.C1786d.C1787a.C1789b bVar = new C1781c.C1782a.C1786d.C1787a.C1789b();
        bVar.f2805d = i;
        if (str != null) {
            bVar.f2806e = str;
        }
        C1781c.C1782a.C1786d.C1787a.C1789b.C1790a[] c = m4272c(str3);
        List<C1781c.C1782a.C1786d.C1793c> a = m4267a(str2);
        if (c != null) {
            bVar.f2803b = c;
        }
        if (a != null) {
            bVar.f2804c = (C1781c.C1782a.C1786d.C1793c[]) a.toArray(new C1781c.C1782a.C1786d.C1793c[a.size()]);
        }
        if (!TextUtils.isEmpty(str4)) {
            bVar.f2807f = m4271b(str4);
        }
        return bVar;
    }

    /* renamed from: e */
    public static C1781c.C1782a.C1786d.C1787a.C1788a m4274e(String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            C1796d a = C2233n.m5974a(str);
            C1781c.C1782a.C1786d.C1787a.C1788a aVar = new C1781c.C1782a.C1786d.C1787a.C1788a();
            aVar.f2800b = a.mo16691a();
            if (!TextUtils.isEmpty(a.mo16694b())) {
                aVar.f2801c = a.mo16694b();
            }
            if (!C1897bk.m4653a((Map) a.mo16696c())) {
                aVar.f2802d = C2223g.m5948a((Map) a.mo16696c());
            }
            return aVar;
        } catch (Exception unused) {
            return null;
        }
    }

    /* renamed from: a */
    public static C1781c.C1782a.C1785c[] m4269a(Context context) {
        List<C1899bm.C1900a> b = C1899bm.m4659a(context).mo17086b();
        if (C1897bk.m4652a((Collection) b)) {
            return null;
        }
        C1781c.C1782a.C1785c[] cVarArr = new C1781c.C1782a.C1785c[b.size()];
        for (int i = 0; i < b.size(); i++) {
            C1781c.C1782a.C1785c cVar = new C1781c.C1782a.C1785c();
            C1899bm.C1900a aVar = b.get(i);
            cVar.f2782b = aVar.f3133a;
            cVar.f2783c = aVar.f3134b;
            cVarArr[i] = cVar;
        }
        return cVarArr;
    }

    /* renamed from: com.yandex.metrica.impl.ap$b */
    static class C1845b {

        /* renamed from: p */
        private static final Map<C2208p.C2209a, Class<?>> f2959p;

        /* renamed from: q */
        private static final Map<C2208p.C2209a, Integer> f2960q;

        /* renamed from: a */
        protected String f2961a;

        /* renamed from: b */
        protected String f2962b;

        /* renamed from: c */
        protected int f2963c;

        /* renamed from: d */
        protected long f2964d;

        /* renamed from: e */
        protected String f2965e;

        /* renamed from: f */
        protected String f2966f;

        /* renamed from: g */
        protected String f2967g;

        /* renamed from: h */
        protected Integer f2968h;

        /* renamed from: i */
        protected Integer f2969i;

        /* renamed from: j */
        protected String f2970j;

        /* renamed from: k */
        protected String f2971k;

        /* renamed from: l */
        protected int f2972l;

        /* renamed from: m */
        protected int f2973m;

        /* renamed from: n */
        protected String f2974n;

        /* renamed from: o */
        protected String f2975o;

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public String mo16840a() {
            return "";
        }

        /* access modifiers changed from: protected */
        /* renamed from: b */
        public byte[] mo16848b() {
            return new byte[0];
        }

        /* access modifiers changed from: protected */
        /* renamed from: f */
        public int mo16856f() {
            return 0;
        }

        static {
            Class<C1845b> cls = C1845b.class;
            HashMap hashMap = new HashMap();
            hashMap.put(C2208p.C2209a.EVENT_TYPE_REGULAR, C1848e.class);
            hashMap.put(C2208p.C2209a.EVENT_TYPE_REFERRER_DEPRECATED, C1849f.class);
            hashMap.put(C2208p.C2209a.EVENT_TYPE_ACTIVITY_START_DEPRECATED, cls);
            hashMap.put(C2208p.C2209a.EVENT_TYPE_ALIVE, cls);
            hashMap.put(C2208p.C2209a.EVENT_TYPE_EXCEPTION_UNHANDLED_DEPRECATED, C1849f.class);
            hashMap.put(C2208p.C2209a.EVENT_TYPE_NATIVE_CRASH, C1851h.class);
            hashMap.put(C2208p.C2209a.EVENT_TYPE_EXCEPTION_USER, C1848e.class);
            hashMap.put(C2208p.C2209a.EVENT_TYPE_IDENTITY, C1850g.class);
            hashMap.put(C2208p.C2209a.EVENT_TYPE_STATBOX, C1848e.class);
            hashMap.put(C2208p.C2209a.EVENT_TYPE_SET_USER_INFO, C1848e.class);
            hashMap.put(C2208p.C2209a.EVENT_TYPE_REPORT_USER_INFO, C1848e.class);
            hashMap.put(C2208p.C2209a.EVENT_TYPE_EXCEPTION_UNHANDLED, C1848e.class);
            hashMap.put(C2208p.C2209a.EVENT_TYPE_START, cls);
            hashMap.put(C2208p.C2209a.EVENT_TYPE_CUSTOM_EVENT, C1846c.class);
            hashMap.put(C2208p.C2209a.EVENT_TYPE_APP_OPEN, C1848e.class);
            hashMap.put(C2208p.C2209a.EVENT_TYPE_PERMISSIONS, C1844a.class);
            hashMap.put(C2208p.C2209a.EVENT_TYPE_APP_FEATURES, C1844a.class);
            f2959p = Collections.unmodifiableMap(hashMap);
            HashMap hashMap2 = new HashMap();
            hashMap2.put(C2208p.C2209a.EVENT_TYPE_INIT, 1);
            hashMap2.put(C2208p.C2209a.EVENT_TYPE_REGULAR, 4);
            hashMap2.put(C2208p.C2209a.EVENT_TYPE_REFERRER_DEPRECATED, 5);
            hashMap2.put(C2208p.C2209a.EVENT_TYPE_ACTIVITY_START_DEPRECATED, 2);
            hashMap2.put(C2208p.C2209a.EVENT_TYPE_ALIVE, 7);
            hashMap2.put(C2208p.C2209a.EVENT_TYPE_EXCEPTION_UNHANDLED_DEPRECATED, 3);
            hashMap2.put(C2208p.C2209a.EVENT_TYPE_EXCEPTION_UNHANDLED, 3);
            hashMap2.put(C2208p.C2209a.EVENT_TYPE_NATIVE_CRASH, 3);
            hashMap2.put(C2208p.C2209a.EVENT_TYPE_EXCEPTION_USER, 6);
            hashMap2.put(C2208p.C2209a.EVENT_TYPE_IDENTITY, 8);
            hashMap2.put(C2208p.C2209a.EVENT_TYPE_STATBOX, 11);
            hashMap2.put(C2208p.C2209a.EVENT_TYPE_SET_USER_INFO, 12);
            hashMap2.put(C2208p.C2209a.EVENT_TYPE_REPORT_USER_INFO, 12);
            hashMap2.put(C2208p.C2209a.EVENT_TYPE_FIRST_ACTIVATION, 13);
            hashMap2.put(C2208p.C2209a.EVENT_TYPE_START, 2);
            hashMap2.put(C2208p.C2209a.EVENT_TYPE_APP_OPEN, 16);
            hashMap2.put(C2208p.C2209a.EVENT_TYPE_APP_UPDATE, 17);
            hashMap2.put(C2208p.C2209a.EVENT_TYPE_PERMISSIONS, 18);
            hashMap2.put(C2208p.C2209a.EVENT_TYPE_APP_FEATURES, 19);
            f2960q = Collections.unmodifiableMap(hashMap2);
        }

        /* renamed from: a */
        static C1845b m4276a(int i, boolean z) {
            C1845b bVar;
            C2208p.C2209a a = C2208p.C2209a.m5885a(i);
            int i2 = C18431.f2958a[a.ordinal()];
            Class cls = (i2 == 1 || i2 == 2 || i2 == 3) ? z ? C1848e.class : C1847d.class : f2959p.get(a);
            Integer num = f2960q.get(a);
            try {
                bVar = (C1845b) cls.newInstance();
            } catch (Exception unused) {
                bVar = new C1845b();
            }
            return bVar.mo16843a(num);
        }

        /* access modifiers changed from: package-private */
        /* renamed from: a */
        public C1845b mo16844a(String str) {
            this.f2961a = str;
            return this;
        }

        /* access modifiers changed from: package-private */
        /* renamed from: b */
        public C1845b mo16847b(String str) {
            this.f2962b = str;
            return this;
        }

        /* access modifiers changed from: package-private */
        /* renamed from: a */
        public C1845b mo16841a(int i) {
            this.f2963c = i;
            return this;
        }

        /* access modifiers changed from: package-private */
        /* renamed from: a */
        public C1845b mo16842a(long j) {
            this.f2964d = j;
            return this;
        }

        /* access modifiers changed from: package-private */
        /* renamed from: c */
        public C1845b mo16850c(String str) {
            this.f2965e = str;
            return this;
        }

        /* access modifiers changed from: package-private */
        /* renamed from: d */
        public C1845b mo16852d(String str) {
            this.f2967g = str;
            return this;
        }

        /* access modifiers changed from: package-private */
        /* renamed from: e */
        public C1845b mo16855e(String str) {
            this.f2966f = str;
            return this;
        }

        /* access modifiers changed from: package-private */
        /* renamed from: a */
        public C1845b mo16843a(Integer num) {
            this.f2968h = num;
            return this;
        }

        /* access modifiers changed from: package-private */
        /* renamed from: f */
        public C1845b mo16857f(String str) {
            this.f2975o = str;
            return this;
        }

        /* access modifiers changed from: package-private */
        /* renamed from: b */
        public C1845b mo16846b(Integer num) {
            this.f2969i = num;
            return this;
        }

        /* access modifiers changed from: package-private */
        /* renamed from: g */
        public C1845b mo16858g(String str) {
            this.f2970j = str;
            return this;
        }

        /* access modifiers changed from: package-private */
        /* renamed from: h */
        public C1845b mo16859h(String str) {
            this.f2971k = str;
            return this;
        }

        /* access modifiers changed from: package-private */
        /* renamed from: b */
        public C1845b mo16845b(int i) {
            this.f2972l = i;
            return this;
        }

        /* access modifiers changed from: package-private */
        /* renamed from: c */
        public C1845b mo16849c(int i) {
            this.f2973m = i;
            return this;
        }

        /* access modifiers changed from: package-private */
        /* renamed from: i */
        public C1845b mo16860i(String str) {
            this.f2974n = str;
            return this;
        }

        /* access modifiers changed from: protected */
        /* renamed from: c */
        public Integer mo16851c() {
            return this.f2968h;
        }

        /* access modifiers changed from: protected */
        /* renamed from: d */
        public String mo16853d() {
            return this.f2970j;
        }

        /* access modifiers changed from: package-private */
        /* renamed from: e */
        public C1781c.C1782a.C1786d.C1787a mo16854e() {
            C1781c.C1782a.C1786d.C1787a aVar = new C1781c.C1782a.C1786d.C1787a();
            C1781c.C1782a.C1786d.C1787a.C1789b a = C1842ap.m4260a(this.f2973m, this.f2974n, this.f2967g, this.f2966f, this.f2975o);
            C1781c.C1782a.C1784b d = C1842ap.m4273d(this.f2965e);
            C1781c.C1782a.C1786d.C1787a.C1788a e = C1842ap.m4274e(this.f2971k);
            if (a != null) {
                aVar.f2795h = a;
            }
            if (d != null) {
                aVar.f2794g = d;
            }
            if (mo16840a() != null) {
                aVar.f2792e = mo16840a();
            }
            if (mo16848b() != null) {
                aVar.f2793f = mo16848b();
            }
            if (mo16853d() != null) {
                aVar.f2796i = mo16853d();
            }
            if (e != null) {
                aVar.f2797j = e;
            }
            aVar.f2791d = mo16851c().intValue();
            aVar.f2789b = (long) this.f2963c;
            aVar.f2790c = this.f2964d;
            aVar.f2798k = this.f2972l;
            aVar.f2799l = mo16856f();
            return aVar;
        }
    }

    /* renamed from: com.yandex.metrica.impl.ap$1 */
    static /* synthetic */ class C18431 {

        /* renamed from: a */
        static final /* synthetic */ int[] f2958a;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|(3:5|6|8)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        static {
            /*
                com.yandex.metrica.impl.p$a[] r0 = com.yandex.metrica.impl.C2208p.C2209a.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                f2958a = r0
                com.yandex.metrica.impl.p$a r1 = com.yandex.metrica.impl.C2208p.C2209a.EVENT_TYPE_INIT     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = f2958a     // Catch:{ NoSuchFieldError -> 0x001d }
                com.yandex.metrica.impl.p$a r1 = com.yandex.metrica.impl.C2208p.C2209a.EVENT_TYPE_FIRST_ACTIVATION     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = f2958a     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.yandex.metrica.impl.p$a r1 = com.yandex.metrica.impl.C2208p.C2209a.EVENT_TYPE_APP_UPDATE     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.yandex.metrica.impl.C1842ap.C18431.<clinit>():void");
        }
    }

    /* renamed from: com.yandex.metrica.impl.ap$f */
    static class C1849f extends C1845b {
        C1849f() {
        }

        /* access modifiers changed from: protected */
        /* renamed from: b */
        public byte[] mo16848b() {
            return C1894bi.m4629c(this.f2961a);
        }
    }

    /* renamed from: com.yandex.metrica.impl.ap$c */
    static class C1846c extends C1848e {
        C1846c() {
        }

        /* access modifiers changed from: protected */
        /* renamed from: c */
        public Integer mo16851c() {
            return this.f2969i;
        }
    }

    /* renamed from: com.yandex.metrica.impl.ap$e */
    static class C1848e extends C1845b {
        C1848e() {
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public String mo16840a() {
            return this.f2961a;
        }

        /* access modifiers changed from: protected */
        /* renamed from: b */
        public byte[] mo16848b() {
            if (this.f2962b != null) {
                return C1894bi.m4629c(this.f2962b);
            }
            return super.mo16848b();
        }
    }

    /* renamed from: com.yandex.metrica.impl.ap$a */
    static class C1844a extends C1848e {
        /* access modifiers changed from: protected */
        /* renamed from: a */
        public String mo16840a() {
            return "";
        }

        C1844a() {
        }
    }

    /* renamed from: com.yandex.metrica.impl.ap$d */
    static class C1847d extends C1845b {
        C1847d() {
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public String mo16840a() {
            return this.f2961a;
        }
    }

    /* renamed from: com.yandex.metrica.impl.ap$h */
    static class C1851h extends C1845b {
        C1851h() {
        }

        /* access modifiers changed from: protected */
        /* renamed from: b */
        public byte[] mo16848b() {
            return C1894bi.m4629c(C2211r.m5902c(this.f2962b));
        }
    }

    /* renamed from: com.yandex.metrica.impl.ap$g */
    static class C1850g extends C1845b {
        /* renamed from: f */
        public int mo16856f() {
            return 1;
        }

        C1850g() {
        }

        /* access modifiers changed from: protected */
        /* renamed from: b */
        public byte[] mo16848b() {
            return Base64.decode(this.f2962b, 0);
        }
    }
}

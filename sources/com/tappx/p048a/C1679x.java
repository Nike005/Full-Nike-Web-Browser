package com.tappx.p048a;

import android.util.Base64;
import android.webkit.URLUtil;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.tappx.a.x */
public class C1679x {

    /* renamed from: c */
    private static final String f2415c = C1400f.m2603b("alkSd/cjeMOnRhI1+y0Fgw");
    /* access modifiers changed from: private */

    /* renamed from: d */
    public static final String f2416d = C1400f.m2603b("7lduO4sGkp1tZzDJbYS8pw");
    /* access modifiers changed from: private */

    /* renamed from: e */
    public static final String f2417e = C1400f.m2603b("HnTZ8Ox2OnyDxdD5hsZrLQ");
    /* access modifiers changed from: private */

    /* renamed from: f */
    public static final String f2418f = C1400f.m2603b("AkEIgV9lhHi/w7lKhAfWaA");
    /* access modifiers changed from: private */

    /* renamed from: g */
    public static final String f2419g = C1400f.m2603b("Vj56qeUSUIexpZcT0zkYTw");
    /* access modifiers changed from: private */

    /* renamed from: h */
    public static final String f2420h = C1400f.m2603b("/spXhZX81bJqCykir0NEjw");
    /* access modifiers changed from: private */

    /* renamed from: i */
    public static final String f2421i = C1400f.m2603b("ju52neF4RY1ixmfCi1hgfA");
    /* access modifiers changed from: private */

    /* renamed from: j */
    public static final String f2422j = C1400f.m2603b("lZVtXyq5ZD6wNwR+ZIHPqA");
    /* access modifiers changed from: private */

    /* renamed from: k */
    public static final String f2423k = C1400f.m2603b("yzM431Q8gFGgERkBwgxdJg");
    /* access modifiers changed from: private */

    /* renamed from: l */
    public static final String f2424l = C1400f.m2603b("oRIavRQJX4wGvDJt1kOzlg");
    /* access modifiers changed from: private */

    /* renamed from: m */
    public static final String f2425m = C1400f.m2603b("5/KYAj40HA8DjDIwU73ybw");
    /* access modifiers changed from: private */

    /* renamed from: n */
    public static final String f2426n = C1400f.m2603b("Ku0dXCSVFeVQPviFMELOqg");
    /* access modifiers changed from: private */

    /* renamed from: o */
    public static final String f2427o = C1400f.m2603b("7nlBnTgzxYha5wGn2VLJtw");
    /* access modifiers changed from: private */

    /* renamed from: p */
    public static final String f2428p = C1400f.m2603b("2mDdVFzY5fPJSVCM3S7xnA");
    /* access modifiers changed from: private */

    /* renamed from: q */
    public static final String f2429q = C1400f.m2603b("Dgw5Kh1C4hRzacbFtYeP2Q");

    /* renamed from: r */
    private static final String f2430r = C1400f.m2603b("kZlsOHt4BykwnGQBWo5ciQ");

    /* renamed from: s */
    private static final String f2431s = C1400f.m2603b("vDgIOL0Ac/85bIB4sUn2dg");

    /* renamed from: t */
    private static final String f2432t = C1400f.m2603b("VbMgRD4jVg4hNau0Ow7yWHX5dBZylyqDyPIjra0JMrA");

    /* renamed from: u */
    private static final String f2433u = C1400f.m2603b("Rn7//KdU5SMi4HFSPVjQzQ");

    /* renamed from: v */
    private static final String f2434v = C1400f.m2603b("CJcKTQGQcFh0cXOvBHlbc1De0+8fG8Rx/CGjhGnsKs8");

    /* renamed from: w */
    private static final String f2435w = C1400f.m2603b("OwINE7I1OlDbXaPClfMqJg");

    /* renamed from: x */
    private static final String f2436x = C1400f.m2603b("J6xwyZwxZoE3V4vmDtoW2w");

    /* renamed from: y */
    private static final String f2437y = C1400f.m2603b("93poZZjBiuurmpEnoLn+8A");

    /* renamed from: a */
    private final C1682c f2438a;

    /* renamed from: b */
    private final C1681b f2439b;

    /* renamed from: com.tappx.a.x$b */
    static class C1681b extends C1680a {
        C1681b() {
        }

        /* renamed from: b */
        public C1673w1 mo16253b(C1337c0 c0Var) {
            C1673w1 a = mo16251a(c0Var);
            a.mo16239b().add(m3578a(c0Var, c0Var.mo15592a()));
            return a;
        }

        /* renamed from: a */
        private C1699y1 m3578a(C1337c0 c0Var, String str) {
            return m3580a(c0Var.f1650a, str);
        }

        /* access modifiers changed from: private */
        /* renamed from: a */
        public C1699y1 m3580a(Map<String, String> map, String str) {
            C1699y1 y1Var = new C1699y1(str);
            boolean z = false;
            y1Var.mo16275b(mo16250a(map, C1679x.f2418f, 0) == 1);
            y1Var.mo16273a(mo16250a(map, C1679x.f2419g, 0) == 1);
            int a = mo16250a(map, C1679x.f2420h, 0);
            int a2 = mo16250a(map, C1679x.f2421i, 0);
            y1Var.mo16279e(a);
            y1Var.mo16276c(a2);
            y1Var.mo16274b(mo16250a(map, C1679x.f2422j, 0));
            if (mo16250a(map, C1679x.f2423k, 1) > 0) {
                z = true;
            }
            y1Var.mo16277c(z);
            y1Var.mo16278d(mo16250a(map, C1679x.f2429q, C1529n.f2089d));
            mo16252a((C1640u1) y1Var, map, true);
            return y1Var;
        }
    }

    C1679x(C1682c cVar, C1681b bVar) {
        this.f2438a = cVar;
        this.f2439b = bVar;
    }

    /* renamed from: a */
    public C1673w1 mo16248a(C1337c0 c0Var) {
        String str = c0Var.f1650a.get(f2415c);
        int a = m3556a(c0Var.f1650a.get(f2433u));
        boolean equals = "1".equals(c0Var.f1650a.get(f2430r));
        String str2 = c0Var.f1650a.get(f2434v);
        if (f2435w.equals(str)) {
            C1673w1 w1Var = new C1673w1();
            w1Var.mo16237a(equals, a, str2);
            return w1Var;
        } else if (f2436x.equals(str)) {
            C1673w1 b = this.f2439b.mo16253b(c0Var);
            b.mo16237a(equals, a, str2);
            return b;
        } else if (f2437y.equals(str)) {
            C1673w1 b2 = this.f2438a.mo16254b(c0Var);
            b2.mo16237a(equals, a, str2);
            return b2;
        } else {
            throw new C1340c2();
        }
    }

    /* renamed from: b */
    public C1473j2 mo16249b(C1337c0 c0Var) {
        String str = c0Var.f1650a.get(f2431s);
        if (URLUtil.isValidUrl(str)) {
            boolean equalsIgnoreCase = "1".equalsIgnoreCase(c0Var.f1650a.get(f2430r));
            String a = c0Var.mo15592a();
            if (a != null && a.length() < 10) {
                a = null;
            }
            return new C1473j2(equalsIgnoreCase, str, a, c0Var.f1650a.get(f2432t));
        }
        throw new C1340c2();
    }

    /* renamed from: com.tappx.a.x$a */
    static class C1680a {

        /* renamed from: a */
        static final String f2440a = C1400f.m2603b("Z0s98+TEac+mapO900zQZA");

        /* renamed from: b */
        static final String f2441b = C1400f.m2603b("UjhhaCwcEZ+voViDfkR/pA");

        /* renamed from: c */
        static final String f2442c = C1400f.m2603b("SNfY2H1acX2p46/zyMOc/g");

        /* renamed from: d */
        static final String f2443d = C1400f.m2603b("On2W1poIktAVirYlBse78g");

        /* renamed from: e */
        static final String f2444e = C1400f.m2603b("LC4el1lDkKxbZdxa4Qatkw");

        /* renamed from: f */
        static final String f2445f = C1400f.m2603b("rj1rf34CVwuKwyr8EiViZg");

        C1680a() {
        }

        /* renamed from: b */
        private int m3574b(Map<String, String> map, String str, int i) {
            String str2 = map.get(C1679x.f2428p);
            if (str2 == null) {
                return i;
            }
            String lowerCase = str2.toLowerCase(Locale.US);
            char c = 65535;
            int hashCode = lowerCase.hashCode();
            if (hashCode != 108) {
                if (hashCode == 112 && lowerCase.equals("p")) {
                    c = 1;
                }
            } else if (lowerCase.equals("l")) {
                c = 0;
            }
            if (c != 0) {
                return c != 1 ? 0 : 1;
            }
            return 2;
        }

        /* access modifiers changed from: package-private */
        /* renamed from: a */
        public C1673w1 mo16251a(C1337c0 c0Var) {
            C1673w1 w1Var = new C1673w1();
            int a = mo16250a(c0Var.f1650a, C1679x.f2416d, -1);
            if (a >= 0) {
                w1Var.mo16236a(a);
            }
            return w1Var;
        }

        /* access modifiers changed from: package-private */
        /* renamed from: a */
        public void mo16252a(C1640u1 u1Var, Map<String, String> map, boolean z) {
            u1Var.mo16202a(System.currentTimeMillis() + ((long) mo16250a(map, C1679x.f2417e, Integer.MAX_VALUE)));
            if (z) {
                u1Var.mo16208c(map.get(C1679x.f2424l));
                u1Var.mo16210d(map.get(C1679x.f2425m));
                u1Var.mo16206b(map.get(C1679x.f2426n));
            }
            u1Var.mo16203a(m3573a(map.get(C1679x.f2427o)));
            u1Var.mo16201a(m3574b(map, C1679x.f2428p, 0));
        }

        /* renamed from: a */
        private C1623t1 m3573a(String str) {
            if (str == null) {
                str = f2444e;
            }
            if (f2442c.equals(str)) {
                return C1623t1.LEFT_TO_RIGHT;
            }
            if (f2443d.equals(str)) {
                return C1623t1.LEFT_TO_RIGHT_BOUNCE;
            }
            if (f2440a.equals(str)) {
                return C1623t1.RIGHT_TO_LEFT;
            }
            if (f2441b.equals(str)) {
                return C1623t1.RIGHT_TO_LEFT_BOUNCE;
            }
            if (f2445f.equals(str)) {
                return C1623t1.RANDOM;
            }
            if (f2444e.equals(str)) {
                return C1623t1.NONE;
            }
            return C1623t1.NONE;
        }

        /* access modifiers changed from: package-private */
        /* renamed from: a */
        public int mo16250a(Map<String, String> map, String str, int i) {
            if (map != null && map.containsKey(str)) {
                try {
                    return Integer.parseInt(map.get(str));
                } catch (NumberFormatException unused) {
                }
            }
            return i;
        }
    }

    public C1679x() {
        this(new C1682c(new C1681b()), new C1681b());
    }

    /* renamed from: com.tappx.a.x$c */
    static class C1682c extends C1680a {

        /* renamed from: h */
        private static final String f2446h = C1400f.m2603b("93poZZjBiuurmpEnoLn+8A");

        /* renamed from: i */
        private static final String f2447i = C1400f.m2603b("z1WSfDFTLljcsXuTNgQuhw");

        /* renamed from: j */
        private static final String f2448j = C1400f.m2603b("TpIeqY5wTOn5nxfmmaemDg");

        /* renamed from: k */
        private static final String f2449k = C1400f.m2603b("HdGvihwA9tMW9jAtG5Z60Q");

        /* renamed from: l */
        private static final String f2450l = C1400f.m2603b("YESpEXhzZPY6QF+iX8uR8g");

        /* renamed from: m */
        private static final String f2451m = C1400f.m2603b("I/pQEmQKlNm3lpGS1bYL+Q");

        /* renamed from: n */
        private static final String f2452n = C1400f.m2603b("iG+mgBqNtiuZUD3OEMyj7g");

        /* renamed from: o */
        private static final String f2453o = C1400f.m2603b("F/t8yr5FjQKjF6cIbBfYMixH+Fztgcl0R+2/K6oLtTgAyMZUaugwtGwADx5ljGYC");

        /* renamed from: p */
        private static final String f2454p = C1400f.m2603b("F/t8yr5FjQKjF6cIbBfYMixH+Fztgcl0R+2/K6oLtTjG9Fb6F0/lw1+DOmSdANB4awFmKet7rldflQ3Vwu0LPQ");

        /* renamed from: q */
        private static final String f2455q = C1400f.m2603b("F/t8yr5FjQKjF6cIbBfYMixH+Fztgcl0R+2/K6oLtTgCFo4ZNldq2wwXprZPFh7t");

        /* renamed from: r */
        private static final String f2456r = C1400f.m2603b("F/t8yr5FjQKjF6cIbBfYMixH+Fztgcl0R+2/K6oLtThB5bdhPbTjs1czbisynSJYtqmQfNBs4mU/7S8aPfuZvQ");

        /* renamed from: s */
        private static final String f2457s = C1400f.m2603b("F/t8yr5FjQKjF6cIbBfYMixH+Fztgcl0R+2/K6oLtTh1OnzYsahK/T3EEh8sxhNj");

        /* renamed from: t */
        private static final String f2458t = C1400f.m2603b("F/t8yr5FjQKjF6cIbBfYMixH+Fztgcl0R+2/K6oLtTjW2bWAjnZ1cI+e8UDyUDXA7/OH75yQRbt4jtPR7dvcLA");

        /* renamed from: u */
        private static final String f2459u = C1400f.m2603b("WbAHWTB1FBrtYHVp67i8eA");

        /* renamed from: v */
        private static final String f2460v = C1400f.m2603b("sjSpR32rYnS73sNt0yqplA");

        /* renamed from: w */
        private static final String f2461w = C1400f.m2603b("wD7tiqYisob3SCbvprbs4Q");

        /* renamed from: x */
        private static final String f2462x = C1400f.m2603b("KkIRPS49FJ604X5/25Zilw");

        /* renamed from: g */
        private final C1681b f2463g;

        C1682c(C1681b bVar) {
            this.f2463g = bVar;
        }

        /* renamed from: a */
        private C1640u1 m3582a(Map<String, String> map, JSONObject jSONObject) {
            JSONObject jSONObject2 = jSONObject.getJSONObject(f2448j);
            try {
                C1699y1 a = this.f2463g.m3580a(map, new String(Base64.decode(jSONObject2.optString(f2449k), 0), "utf-8"));
                m3585a(jSONObject2.optJSONObject(f2451m), (C1640u1) a);
                return a;
            } catch (UnsupportedEncodingException unused) {
                throw new C1340c2();
            }
        }

        /* renamed from: c */
        private C1709z1 m3587c(Map<String, String> map, JSONObject jSONObject, int i) {
            C1709z1 z1Var = new C1709z1();
            mo16252a((C1640u1) z1Var, map, false);
            JSONObject jSONObject2 = jSONObject.getJSONObject(f2448j);
            m3585a(jSONObject2.optJSONObject(f2451m), (C1640u1) z1Var);
            int optInt = jSONObject2.optInt(f2447i, i);
            String string = jSONObject2.getString(f2449k);
            z1Var.mo16298b(optInt);
            z1Var.mo16299e(string);
            return z1Var;
        }

        /* renamed from: b */
        public C1673w1 mo16254b(C1337c0 c0Var) {
            try {
                C1673w1 a = mo16251a(c0Var);
                JSONObject jSONObject = new JSONObject(c0Var.mo15592a());
                int optInt = jSONObject.optInt(f2447i);
                JSONArray jSONArray = jSONObject.getJSONArray(f2446h);
                for (int i = 0; i < jSONArray.length(); i++) {
                    JSONObject jSONObject2 = jSONArray.getJSONObject(i);
                    C1640u1 b = m3586b(c0Var.f1650a, jSONObject2, optInt);
                    if (b != null) {
                        b.mo16204a(jSONObject2.optString(f2452n));
                        a.mo16239b().add(b);
                    }
                }
                return a;
            } catch (JSONException unused) {
                throw new C1340c2();
            }
        }

        /* renamed from: a */
        private C1685x1 m3583a(Map<String, String> map, JSONObject jSONObject, int i) {
            C1685x1 x1Var = new C1685x1();
            mo16252a((C1640u1) x1Var, map, true);
            JSONObject jSONObject2 = jSONObject.getJSONObject(f2448j);
            String string = jSONObject2.getString(f2450l);
            x1Var.mo16256c(jSONObject2.optInt(f2447i, i));
            x1Var.mo16258e(string);
            m3585a(jSONObject2.optJSONObject(f2451m), (C1640u1) x1Var);
            try {
                int parseInt = Integer.parseInt(map.get(C1679x.f2420h));
                int parseInt2 = Integer.parseInt(map.get(C1679x.f2421i));
                x1Var.mo16257d(parseInt);
                x1Var.mo16255b(parseInt2);
                return x1Var;
            } catch (Exception unused) {
                throw new C1340c2();
            }
        }

        /* renamed from: b */
        private C1640u1 m3586b(Map<String, String> map, JSONObject jSONObject, int i) {
            try {
                String string = jSONObject.getString(f2462x);
                if (!f2453o.equals(string) && !f2454p.equals(string)) {
                    if (!f2459u.equals(string)) {
                        if (!f2455q.equals(string) && !f2456r.equals(string)) {
                            if (!f2460v.equals(string)) {
                                if (!f2457s.equals(string) && !f2458t.equals(string)) {
                                    if (!f2461w.equals(string)) {
                                        return null;
                                    }
                                }
                                return m3582a(map, jSONObject);
                            }
                        }
                        return m3587c(map, jSONObject, i);
                    }
                }
                return m3583a(map, jSONObject, i);
            } catch (C1340c2 | JSONException unused) {
                return null;
            }
        }

        /* renamed from: a */
        private void m3584a(C1640u1 u1Var, String str, String str2) {
            String f = u1Var.mo16212f();
            if (f != null) {
                u1Var.mo16208c(f.replaceAll(Pattern.quote(str), str2));
            }
            String g = u1Var.mo16213g();
            if (g != null) {
                u1Var.mo16210d(g.replaceAll(Pattern.quote(str), str2));
            }
            String e = u1Var.mo16211e();
            if (e != null) {
                u1Var.mo16206b(e.replaceAll(Pattern.quote(str), str2));
            }
        }

        /* renamed from: a */
        private void m3585a(JSONObject jSONObject, C1640u1 u1Var) {
            if (jSONObject != null) {
                Iterator<String> keys = jSONObject.keys();
                while (keys.hasNext()) {
                    String next = keys.next();
                    String optString = jSONObject.optString(next, (String) null);
                    if (optString != null) {
                        m3584a(u1Var, next, optString);
                    }
                }
            }
        }
    }

    /* renamed from: a */
    private int m3556a(String str) {
        if (str == null) {
            return 0;
        }
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException unused) {
            return 0;
        }
    }
}

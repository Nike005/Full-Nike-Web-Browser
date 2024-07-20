package com.yandex.metrica.impl;

import android.text.TextUtils;
import com.mopub.common.Constants;
import com.yandex.metrica.impl.p050ob.C2091ds;
import com.yandex.metrica.impl.p050ob.C2092dt;
import com.yandex.metrica.impl.utils.C2223g;
import com.yandex.metrica.impl.utils.C2231l;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.yandex.metrica.impl.bg */
class C1890bg {

    /* renamed from: com.yandex.metrica.impl.bg$a */
    static class C1891a {

        /* renamed from: a */
        private C1892a f3092a;

        /* renamed from: b */
        private boolean f3093b;

        /* renamed from: c */
        private boolean f3094c;

        /* renamed from: d */
        private boolean f3095d;

        /* renamed from: e */
        private List<String> f3096e;

        /* renamed from: f */
        private String f3097f;

        /* renamed from: g */
        private String f3098g;

        /* renamed from: h */
        private String f3099h;

        /* renamed from: i */
        private String f3100i;

        /* renamed from: j */
        private String f3101j;

        /* renamed from: k */
        private String f3102k;

        /* renamed from: l */
        private String f3103l;

        /* renamed from: m */
        private String f3104m;

        /* renamed from: n */
        private C2092dt f3105n = new C2092dt();

        /* renamed from: o */
        private C2091ds f3106o = null;

        /* renamed from: p */
        private boolean f3107p;

        /* renamed from: q */
        private boolean f3108q;

        /* renamed from: com.yandex.metrica.impl.bg$a$a */
        public enum C1892a {
            BAD,
            OK
        }

        C1891a() {
        }

        /* access modifiers changed from: package-private */
        /* renamed from: a */
        public void mo17042a(boolean z) {
            this.f3093b = z;
        }

        /* renamed from: a */
        public boolean mo17043a() {
            return this.f3093b;
        }

        /* access modifiers changed from: package-private */
        /* renamed from: b */
        public void mo17045b(boolean z) {
            this.f3094c = z;
        }

        /* renamed from: b */
        public boolean mo17046b() {
            return this.f3094c;
        }

        /* access modifiers changed from: package-private */
        /* renamed from: a */
        public void mo17041a(List<String> list) {
            this.f3096e = list;
        }

        /* access modifiers changed from: package-private */
        /* renamed from: c */
        public List<String> mo17047c() {
            return this.f3096e;
        }

        /* access modifiers changed from: package-private */
        /* renamed from: a */
        public void mo17040a(String str) {
            this.f3097f = str;
        }

        /* renamed from: d */
        public String mo17050d() {
            return this.f3097f;
        }

        /* access modifiers changed from: package-private */
        /* renamed from: b */
        public void mo17044b(String str) {
            this.f3098g = str;
        }

        /* renamed from: e */
        public String mo17053e() {
            return this.f3098g;
        }

        /* access modifiers changed from: package-private */
        /* renamed from: c */
        public void mo17048c(String str) {
            this.f3099h = str;
        }

        /* renamed from: f */
        public String mo17056f() {
            return this.f3099h;
        }

        /* access modifiers changed from: package-private */
        /* renamed from: d */
        public void mo17051d(String str) {
            this.f3101j = str;
        }

        /* renamed from: g */
        public String mo17058g() {
            return this.f3101j;
        }

        /* access modifiers changed from: package-private */
        /* renamed from: e */
        public void mo17054e(String str) {
            this.f3102k = str;
        }

        /* renamed from: h */
        public String mo17060h() {
            return this.f3102k;
        }

        /* access modifiers changed from: package-private */
        /* renamed from: f */
        public void mo17057f(String str) {
            this.f3103l = str;
        }

        /* renamed from: i */
        public String mo17062i() {
            return this.f3103l;
        }

        /* access modifiers changed from: package-private */
        /* renamed from: g */
        public void mo17059g(String str) {
            this.f3104m = str;
        }

        /* renamed from: j */
        public String mo17063j() {
            return this.f3104m;
        }

        /* access modifiers changed from: package-private */
        /* renamed from: a */
        public void mo17037a(C1892a aVar) {
            this.f3092a = aVar;
        }

        /* renamed from: k */
        public C1892a mo17064k() {
            return this.f3092a;
        }

        /* renamed from: a */
        public void mo17039a(C2092dt dtVar) {
            this.f3105n = dtVar;
        }

        /* renamed from: l */
        public C2092dt mo17065l() {
            return this.f3105n;
        }

        /* renamed from: m */
        public String mo17066m() {
            return this.f3100i;
        }

        /* renamed from: h */
        public void mo17061h(String str) {
            this.f3100i = str;
        }

        /* renamed from: n */
        public boolean mo17067n() {
            return this.f3095d;
        }

        /* renamed from: c */
        public void mo17049c(boolean z) {
            this.f3095d = z;
        }

        /* access modifiers changed from: package-private */
        /* renamed from: a */
        public void mo17038a(C2091ds dsVar) {
            this.f3106o = dsVar;
        }

        /* renamed from: o */
        public C2091ds mo17068o() {
            return this.f3106o;
        }

        /* renamed from: d */
        public void mo17052d(boolean z) {
            this.f3107p = z;
        }

        /* renamed from: p */
        public boolean mo17069p() {
            return this.f3107p;
        }

        /* renamed from: e */
        public void mo17055e(boolean z) {
            this.f3108q = z;
        }

        /* renamed from: q */
        public boolean mo17070q() {
            return this.f3108q;
        }
    }

    /* renamed from: a */
    private static String m4563a(JSONObject jSONObject, String str) {
        try {
            return jSONObject.getJSONObject(str).getString("value");
        } catch (Exception unused) {
            return "";
        }
    }

    /* renamed from: b */
    private static String m4567b(JSONObject jSONObject, String str) {
        try {
            return jSONObject.getJSONObject(str).getJSONArray(Constants.VIDEO_TRACKING_URLS_KEY).getString(0);
        } catch (Exception unused) {
            return "";
        }
    }

    /* renamed from: c */
    private static List<String> m4569c(JSONObject jSONObject, String str) {
        try {
            JSONArray jSONArray = jSONObject.getJSONObject(str).getJSONArray(Constants.VIDEO_TRACKING_URLS_KEY);
            if (jSONArray == null || jSONArray.length() <= 0) {
                return null;
            }
            ArrayList arrayList = new ArrayList(jSONArray.length());
            for (int i = 0; i < jSONArray.length(); i++) {
                arrayList.add(jSONArray.getString(i));
            }
            return arrayList;
        } catch (Exception unused) {
            return null;
        }
    }

    /* renamed from: a */
    public static C1891a m4561a(byte[] bArr) {
        C1891a aVar = new C1891a();
        try {
            C2223g.C2224a aVar2 = new C2223g.C2224a(new String(bArr, "UTF-8"));
            aVar.mo17054e(m4563a((JSONObject) aVar2, "device_id"));
            aVar.mo17057f(m4563a((JSONObject) aVar2, "uuid"));
            JSONObject jSONObject = (JSONObject) aVar2.mo17921a("query_hosts", new JSONObject());
            if (jSONObject.has("list")) {
                JSONObject jSONObject2 = jSONObject.getJSONObject("list");
                String b = m4567b(jSONObject2, "get_ad");
                if (m4566a(b)) {
                    aVar.mo17040a(b);
                }
                String b2 = m4567b(jSONObject2, "report");
                if (m4566a(b2)) {
                    aVar.mo17044b(b2);
                }
                String b3 = m4567b(jSONObject2, "report_ad");
                if (m4566a(b3)) {
                    aVar.mo17048c(b3);
                }
                String b4 = m4567b(jSONObject2, "ssl_pinning");
                if (m4566a(b4)) {
                    aVar.mo17051d(b4);
                }
                String b5 = m4567b(jSONObject2, "bind_id");
                if (m4566a(b5)) {
                    aVar.mo17061h(b5);
                }
                List<String> c = m4569c(jSONObject2, "startup");
                if (!C1897bk.m4652a((Collection) c)) {
                    aVar.mo17041a(c);
                }
            }
            JSONObject optJSONObject = ((JSONObject) aVar2.mo17921a("distribution_customization", new JSONObject())).optJSONObject("clids");
            if (optJSONObject != null) {
                m4565a(aVar, optJSONObject);
            }
            JSONObject jSONObject3 = (JSONObject) aVar2.mo17921a("features", new JSONObject());
            aVar.mo17042a(false);
            aVar.mo17045b(false);
            if (jSONObject3.has("list")) {
                JSONObject jSONObject4 = jSONObject3.getJSONObject("list");
                if (jSONObject4.has("easy_collecting")) {
                    aVar.mo17042a(jSONObject4.getJSONObject("easy_collecting").optBoolean("enabled", false));
                }
                if (jSONObject4.has("package_info")) {
                    aVar.mo17045b(jSONObject4.getJSONObject("package_info").optBoolean("enabled", false));
                }
                if (jSONObject4.has("socket")) {
                    aVar.mo17049c(jSONObject4.getJSONObject("socket").optBoolean("enabled", false));
                }
                if (jSONObject4.has("permissions_collecting")) {
                    aVar.mo17052d(jSONObject4.getJSONObject("permissions_collecting").optBoolean("enabled", false));
                }
                if (jSONObject4.has("features_collecting")) {
                    aVar.mo17055e(jSONObject4.getJSONObject("features_collecting").optBoolean("enabled", false));
                }
            }
            m4564a(aVar, aVar2);
            if (aVar.mo17067n()) {
                m4568b(aVar, aVar2);
            }
            aVar.mo17037a(C1891a.C1892a.OK);
            return aVar;
        } catch (Exception unused) {
            C1891a aVar3 = new C1891a();
            aVar3.mo17037a(C1891a.C1892a.BAD);
            return aVar3;
        }
    }

    /* renamed from: a */
    private static void m4564a(C1891a aVar, C2223g.C2224a aVar2) throws JSONException {
        JSONArray optJSONArray;
        JSONObject optJSONObject = aVar2.optJSONObject("browsers");
        if (optJSONObject != null && (optJSONArray = optJSONObject.optJSONArray("list")) != null) {
            C2092dt dtVar = new C2092dt();
            for (int i = 0; i < optJSONArray.length(); i++) {
                JSONObject jSONObject = optJSONArray.getJSONObject(i);
                String optString = jSONObject.optString("package_id");
                if (!TextUtils.isEmpty(optString)) {
                    dtVar.mo17648a(optString, jSONObject.optInt("min_interval_seconds"));
                }
            }
            aVar.mo17039a(dtVar);
        }
    }

    /* renamed from: b */
    private static void m4568b(C1891a aVar, C2223g.C2224a aVar2) {
        JSONObject optJSONObject = aVar2.optJSONObject("socket");
        if (optJSONObject != null) {
            long optLong = optJSONObject.optLong("seconds_to_live");
            String optString = optJSONObject.optString("token");
            JSONArray optJSONArray = optJSONObject.optJSONArray("ports");
            if (optLong > 0 && m4566a(optString) && optJSONArray != null && optJSONArray.length() > 0) {
                ArrayList arrayList = new ArrayList(optJSONArray.length());
                for (int i = 0; i < optJSONArray.length(); i++) {
                    int optInt = optJSONArray.optInt(i);
                    if (optInt != 0) {
                        arrayList.add(Integer.valueOf(optInt));
                    }
                }
                if (!arrayList.isEmpty()) {
                    aVar.mo17038a(new C2091ds(optLong, optString, arrayList));
                }
            }
        }
    }

    /* renamed from: a */
    private static boolean m4566a(String str) {
        return !C1894bi.m4622a(str);
    }

    /* renamed from: a */
    private static void m4565a(C1891a aVar, JSONObject jSONObject) throws JSONException {
        HashMap hashMap = new HashMap();
        Iterator<String> keys = jSONObject.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            JSONObject optJSONObject = jSONObject.optJSONObject(next);
            if (optJSONObject != null && optJSONObject.has("value")) {
                hashMap.put(next, optJSONObject.getString("value"));
            }
        }
        aVar.mo17059g(C2231l.m5971a((Map<String, String>) hashMap));
    }

    /* renamed from: a */
    public static Long m4562a(Map<String, List<String>> map) {
        if (!C1897bk.m4653a((Map) map)) {
            List list = map.get("Date");
            if (!C1897bk.m4652a((Collection) list)) {
                try {
                    return Long.valueOf(new SimpleDateFormat("E, d MMM yyyy HH:mm:ss z", Locale.US).parse((String) list.get(0)).getTime());
                } catch (Exception unused) {
                }
            }
        }
        return null;
    }
}

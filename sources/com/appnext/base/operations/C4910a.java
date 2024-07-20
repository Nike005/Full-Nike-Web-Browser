package com.appnext.base.operations;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Pair;
import com.appnext.base.C4878a;
import com.appnext.base.p078a.C4880a;
import com.appnext.base.p078a.p080b.C4886b;
import com.appnext.base.p078a.p080b.C4887c;
import com.appnext.base.p078a.p081c.C4890b;
import com.appnext.base.p078a.p081c.C4892d;
import com.appnext.base.p082b.C4897b;
import com.appnext.base.p082b.C4899d;
import com.appnext.base.p082b.C4905i;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

/* renamed from: com.appnext.base.operations.a */
public abstract class C4910a {

    /* renamed from: em */
    private static final String f4651em = "collectedData";

    /* renamed from: en */
    private static final String f4652en = "collectedDataType";

    /* renamed from: eo */
    private static final String f4653eo = "lastCollectedData";

    /* renamed from: el */
    private C4911a f4654el;

    /* renamed from: ep */
    protected C4887c f4655ep;

    /* renamed from: com.appnext.base.operations.a$a */
    public interface C4911a {
        /* renamed from: aH */
        void mo41049aH();

        /* renamed from: b */
        void mo41050b(C4878a aVar);
    }

    /* renamed from: az */
    protected static boolean m6620az() {
        return true;
    }

    /* renamed from: c */
    protected static HashMap<Pair<String, String>, JSONArray> m6621c(HashMap<Pair<String, String>, JSONArray> hashMap) {
        return hashMap;
    }

    public static void cancel() {
    }

    /* renamed from: aC */
    public abstract void mo41036aC();

    /* renamed from: aD */
    public abstract void mo41037aD();

    /* renamed from: aF */
    public boolean mo41039aF() {
        return true;
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public List<C4886b> mo41045b(List<C4886b> list) {
        return list;
    }

    /* access modifiers changed from: protected */
    public abstract List<C4886b> getData();

    /* access modifiers changed from: protected */
    public abstract String getKey();

    public C4910a(C4887c cVar, Bundle bundle, Object obj) {
        this.f4655ep = cVar;
    }

    /* renamed from: a */
    public final void mo41034a(C4911a aVar) {
        this.f4654el = aVar;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final long mo41032a(List<C4886b> list) {
        try {
            JSONArray a = C4897b.m6564a(list, true);
            if (a == null || a.length() <= 0) {
                return -1;
            }
            return C4880a.m6472X().mo40941aa().mo40983a(a);
        } catch (Throwable unused) {
            return -1;
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: av */
    public final void mo41041av() {
        Map<String, String> ax;
        try {
            Long valueOf = Long.valueOf(System.currentTimeMillis());
            String key = this.f4655ep.getKey();
            C4905i.m6591aR().putLong(key + C4905i.f4642fz, valueOf.longValue());
            List<C4886b> data = getData();
            if (data != null && !data.isEmpty()) {
                mo41032a(data);
            }
            String key2 = getKey();
            if (data != null) {
                JSONArray d = m6623d(data);
                if (d != null) {
                    C4905i.m6591aR().putString(m6616A(key2), d.toString());
                }
            }
        } catch (Throwable unused) {
            return;
        }
        String key3 = this.f4655ep.getKey();
        try {
            C4905i.m6591aR().putLong(key3 + C4905i.f4641fy, System.currentTimeMillis());
            String str = key3 + C4905i.f4636fA;
            C4905i.m6591aR().putInt(str, C4905i.m6591aR().getInt(str, 0) + 1);
        } catch (Throwable unused2) {
        }
        C4878a aVar = null;
        if (mo41035aA() && (ax = mo41043ax()) != null && !ax.isEmpty()) {
            Long valueOf2 = Long.valueOf(System.currentTimeMillis());
            String key4 = this.f4655ep.getKey();
            Long.valueOf(C4905i.m6591aR().getLong(key4 + C4905i.f4642fz, -1));
            C4905i.m6591aR().putLong(key4 + C4905i.f4642fz, valueOf2.longValue());
            if (!C4897b.m6566a(key4, ax)) {
                aVar = new C4878a(C4878a.C4879a.NoInternet$1d8b5b4a);
            }
        }
        mo41033a(aVar);
    }

    /* renamed from: a */
    private boolean m6618a(Map<String, String> map) {
        Long valueOf = Long.valueOf(System.currentTimeMillis());
        String key = this.f4655ep.getKey();
        C4905i aR = C4905i.m6591aR();
        Long.valueOf(aR.getLong(key + C4905i.f4642fz, -1));
        C4905i aR2 = C4905i.m6591aR();
        aR2.putLong(key + C4905i.f4642fz, valueOf.longValue());
        return C4897b.m6566a(key, map);
    }

    /* access modifiers changed from: protected */
    /* renamed from: ay */
    public final C4887c mo41044ay() {
        return this.f4655ep;
    }

    /* renamed from: d */
    private static JSONObject m6624d(C4886b bVar) {
        return C4897b.m6565a(bVar.mo40951ai(), bVar.mo40952aj(), C4899d.C4900a.valueOf(bVar.getDataType()));
    }

    /* renamed from: a */
    public final void mo41033a(C4878a aVar) {
        C4912b.m6644aI().mo41051a(this);
        C4911a aVar2 = this.f4654el;
        if (aVar2 == null) {
            return;
        }
        if (aVar != null) {
            aVar2.mo41050b(aVar);
        } else {
            aVar2.mo41049aH();
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: aA */
    public boolean mo41035aA() {
        return C4897b.m6567d(this.f4655ep);
    }

    /* renamed from: aB */
    protected static C4892d m6619aB() {
        return C4880a.m6472X().mo40941aa();
    }

    /* renamed from: c */
    private void m6622c(List<String> list) {
        if (!list.isEmpty()) {
            C4890b aa = C4880a.m6472X().mo40941aa();
            for (String u : list) {
                aa.mo40989u(u);
            }
        }
    }

    /* renamed from: aE */
    public final boolean mo41038aE() {
        try {
            String key = this.f4655ep.getKey();
            C4905i aR = C4905i.m6591aR();
            if (System.currentTimeMillis() - Long.valueOf(aR.getLong(key + C4905i.f4642fz, -1)).longValue() >= 900000) {
                return mo41039aF();
            }
            StringBuilder sb = new StringBuilder();
            sb.append(key);
            sb.append(" less then interval");
            return false;
        } catch (Throwable unused) {
            return false;
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: aG */
    public C4899d.C4900a mo41040aG() {
        return C4899d.C4900a.String;
    }

    protected static Date getDate() {
        return new Date();
    }

    /* renamed from: a */
    private void m6617a(String str, List<C4886b> list) {
        if (list != null) {
            try {
                JSONArray d = m6623d(list);
                if (d != null) {
                    C4905i.m6591aR().putString(m6616A(str), d.toString());
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    /* renamed from: A */
    private static String m6616A(String str) {
        return f4653eo + "_" + str;
    }

    /* renamed from: d */
    private static JSONArray m6623d(List<C4886b> list) {
        if (list != null) {
            try {
                if (!list.isEmpty()) {
                    JSONArray jSONArray = new JSONArray();
                    for (C4886b next : list) {
                        JSONObject jSONObject = new JSONObject();
                        jSONObject.put(f4651em, next.mo40951ai());
                        jSONObject.put(f4652en, next.getType());
                        jSONArray.put(jSONObject);
                    }
                    return jSONArray;
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
        return null;
    }

    /* access modifiers changed from: protected */
    /* renamed from: e */
    public final boolean mo41046e(List<C4886b> list) {
        if (list != null) {
            try {
                if (!list.isEmpty()) {
                    JSONArray d = m6623d(list);
                    if (d != null) {
                        String jSONArray = d.toString();
                        String string = C4905i.m6591aR().getString(m6616A(getKey()), (String) null);
                        if (TextUtils.isEmpty(string) || TextUtils.isEmpty(jSONArray) || !string.equals(jSONArray)) {
                            return true;
                        }
                        return false;
                    }
                }
            } catch (Throwable unused) {
            }
        }
        return true;
    }

    /* access modifiers changed from: protected */
    /* renamed from: aw */
    public final List<C4886b> mo41042aw() {
        return C4880a.m6472X().mo40941aa().mo40991w(this.f4655ep.getKey());
    }

    /* access modifiers changed from: protected */
    /* renamed from: ax */
    public final Map<String, String> mo41043ax() {
        List<C4886b> b;
        List<C4886b> w = C4880a.m6472X().mo40941aa().mo40991w(this.f4655ep.getKey());
        if (w == null || w.isEmpty() || (b = mo41045b(w)) == null || b.isEmpty()) {
            return null;
        }
        HashMap hashMap = new HashMap();
        for (C4886b next : b) {
            Pair pair = new Pair(next.mo40950ah(), next.getType());
            if (hashMap.containsKey(pair)) {
                ((JSONArray) hashMap.get(pair)).put(m6624d(next));
            } else {
                JSONArray jSONArray = new JSONArray();
                jSONArray.put(m6624d(next));
                hashMap.put(pair, jSONArray);
            }
        }
        HashMap hashMap2 = new HashMap();
        ArrayList arrayList = new ArrayList();
        for (Map.Entry entry : hashMap.entrySet()) {
            String str = (String) ((Pair) entry.getKey()).second;
            hashMap2.put(str, ((JSONArray) entry.getValue()).toString());
            arrayList.add(str);
        }
        String key = this.f4655ep.getKey();
        if (key != null) {
            try {
                C4905i.m6591aR().putInt(key + C4905i.f4636fA, 0);
            } catch (Throwable unused) {
            }
        }
        m6622c((List<String>) arrayList);
        C4897b.m6561F(this.f4655ep.getKey());
        return hashMap2;
    }
}

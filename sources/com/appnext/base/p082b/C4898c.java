package com.appnext.base.p082b;

import android.os.PersistableBundle;
import com.appnext.base.p078a.p080b.C4887c;
import org.json.JSONObject;

/* renamed from: com.appnext.base.b.c */
public final class C4898c {
    public static final String DATA = "data";
    public static final String KEY = "key";
    public static final String STATUS = "status";

    /* renamed from: eO */
    public static final String f4601eO = "cycle";

    /* renamed from: eP */
    public static final String f4602eP = "cycle_type";

    /* renamed from: eQ */
    public static final String f4603eQ = "sample";

    /* renamed from: eR */
    public static final String f4604eR = "sample_type";

    /* renamed from: eS */
    public static final String f4605eS = "service_key";

    /* renamed from: e */
    public static PersistableBundle m6570e(C4887c cVar) {
        PersistableBundle persistableBundle = new PersistableBundle();
        try {
            String str = "";
            persistableBundle.putString("key", cVar.getKey() != null ? cVar.getKey() : str);
            persistableBundle.putString(f4601eO, cVar.mo40961an() != null ? cVar.mo40961an() : str);
            persistableBundle.putString(f4602eP, cVar.mo40962ao() != null ? cVar.mo40962ao() : str);
            cVar.mo40959al();
            persistableBundle.putString(f4603eQ, cVar.mo40959al());
            persistableBundle.putString(f4604eR, cVar.mo40960am() != null ? cVar.mo40960am() : str);
            persistableBundle.putString("service_key", cVar.mo40963ap() != null ? cVar.mo40963ap() : str);
            if (cVar.mo40958ak() != null) {
                str = cVar.mo40958ak();
            }
            persistableBundle.putString("status", str);
            if (cVar.mo40964aq() != null) {
                persistableBundle.putString("data", cVar.mo40964aq().toString());
            }
            JSONObject aq = cVar.mo40964aq();
            if (aq != null) {
                persistableBundle.putString("data", aq.toString());
            }
        } catch (Throwable th) {
            th.getMessage();
        }
        return persistableBundle;
    }

    /* renamed from: b */
    public static C4887c m6568b(PersistableBundle persistableBundle) {
        if (persistableBundle == null) {
            return null;
        }
        String string = persistableBundle.getString("key", "");
        String string2 = persistableBundle.getString(f4601eO, "");
        String string3 = persistableBundle.getString(f4602eP, "");
        return new C4887c(persistableBundle.getString("status", ""), persistableBundle.getString(f4603eQ, ""), persistableBundle.getString(f4604eR, ""), string2, string3, string, persistableBundle.getString("service_key", ""), persistableBundle.getString("data", (String) null));
    }

    /* renamed from: c */
    public static C4887c m6569c(JSONObject jSONObject) {
        JSONObject jSONObject2 = jSONObject;
        if (jSONObject2 == null) {
            return null;
        }
        try {
            String string = jSONObject2.has("key") ? jSONObject2.getString("key") : null;
            String string2 = jSONObject2.has(f4601eO) ? jSONObject2.getString(f4601eO) : null;
            String string3 = jSONObject2.has(f4602eP) ? jSONObject2.getString(f4602eP) : null;
            return new C4887c(jSONObject2.has("status") ? jSONObject2.getString("status") : null, jSONObject2.has(f4603eQ) ? jSONObject2.getString(f4603eQ) : null, jSONObject2.has(f4604eR) ? jSONObject2.getString(f4604eR) : null, string2, string3, string, jSONObject2.has("service_key") ? jSONObject2.getString("service_key") : null, jSONObject2.has("data") ? jSONObject2.getString("data") : null);
        } catch (Throwable unused) {
            return null;
        }
    }
}

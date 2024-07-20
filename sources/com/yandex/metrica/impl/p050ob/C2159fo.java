package com.yandex.metrica.impl.p050ob;

import com.yandex.metrica.impl.p050ob.C2162fr;
import java.io.UnsupportedEncodingException;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.yandex.metrica.impl.ob.fo */
public class C2159fo extends C2160fp<JSONObject> {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C2159fo(int i, String str, JSONObject jSONObject) {
        super(i, str, jSONObject == null ? null : jSONObject.toString());
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public JSONObject mo17770b(C2168ft ftVar) throws C2162fr {
        try {
            return new JSONObject(new String(ftVar.f3739a, C2161fq.m5715a(ftVar.f3740b, "utf-8")));
        } catch (UnsupportedEncodingException e) {
            C2162fr.C2163a aVar = C2162fr.C2163a.PARSE;
            throw new C2162fr((Throwable) e);
        } catch (JSONException e2) {
            C2162fr.C2163a aVar2 = C2162fr.C2163a.PARSE;
            throw new C2162fr((Throwable) e2);
        }
    }
}

package com.appnext.base.operations.imp;

import android.os.Bundle;
import android.text.TextUtils;
import com.appnext.base.p078a.C4880a;
import com.appnext.base.p078a.p080b.C4885a;
import com.appnext.base.p078a.p080b.C4886b;
import com.appnext.base.p078a.p080b.C4887c;
import com.appnext.base.p082b.C4899d;
import com.appnext.base.p082b.C4904h;
import com.startapp.android.mediation.admob.StartAppNative;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

public class acapc extends acap {
    public acapc(C4887c cVar, Bundle bundle, Object obj) {
        super(cVar, bundle, obj);
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public final List<C4886b> mo41045b(List<C4886b> list) {
        Class<acapc> cls = acapc.class;
        List<C4886b> b = super.mo41045b(list);
        if (b == null || b.isEmpty()) {
            return null;
        }
        HashMap hashMap = new HashMap();
        for (C4886b ai : b) {
            String L = C4904h.m6584aO().mo41008L(ai.mo40951ai());
            if (TextUtils.isEmpty(L)) {
                return null;
            }
            List<C4885a> r = C4880a.m6472X().mo40940Z().mo40974r(L);
            if (r.size() > 0) {
                Integer ag = r.get(0).mo40948ag();
                if (!hashMap.containsKey(ag)) {
                    hashMap.put(ag, 1);
                } else {
                    hashMap.put(ag, Integer.valueOf(((Integer) hashMap.get(ag)).intValue() + 1));
                }
            }
        }
        if (hashMap.isEmpty()) {
            return null;
        }
        JSONArray jSONArray = new JSONArray();
        for (Map.Entry entry : hashMap.entrySet()) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put(StartAppNative.EXTRAS_CATEGORY, entry.getKey());
                jSONObject.put("appcount", entry.getValue());
            } catch (Throwable unused) {
            }
            jSONArray.put(jSONObject);
        }
        String K = C4904h.m6584aO().mo41007K(jSONArray.toString());
        if (TextUtils.isEmpty(K)) {
            return null;
        }
        C4886b bVar = new C4886b(cls.getSimpleName(), cls.getSimpleName(), K, new Date(), C4899d.C4900a.JSONArray.getType());
        ArrayList arrayList = new ArrayList();
        arrayList.add(bVar);
        if (arrayList.isEmpty()) {
            return null;
        }
        return arrayList;
    }

    /* access modifiers changed from: protected */
    /* renamed from: aG */
    public final C4899d.C4900a mo41040aG() {
        return C4899d.C4900a.JSONArray;
    }

    /* access modifiers changed from: protected */
    public final String getKey() {
        return acapc.class.getSimpleName();
    }
}

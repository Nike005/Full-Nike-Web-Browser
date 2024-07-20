package com.appnext.base.operations.imp;

import android.os.Bundle;
import com.appnext.base.operations.C4913c;
import com.appnext.base.p078a.C4880a;
import com.appnext.base.p078a.p080b.C4887c;
import com.appnext.base.p082b.C4898c;
import com.appnext.base.p082b.C4899d;
import com.appnext.base.p082b.C4901e;
import com.appnext.base.p082b.C4905i;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class cdm extends C4913c {
    private final String TAG = "cdm";

    /* renamed from: ey */
    private final String f4664ey = "[ { \"status\": \"on\", \"sample\":\"1\", \"sample_type\":\"hour\", \"cycle\":\"1\", \"cycle_type\":\"interval\", \"key\":\"cdm\" } ]";

    /* access modifiers changed from: protected */
    /* renamed from: aA */
    public final boolean mo41035aA() {
        return false;
    }

    /* renamed from: aF */
    public final boolean mo41039aF() {
        return true;
    }

    public cdm(C4887c cVar, Bundle bundle, Object obj) {
        super(cVar, bundle, obj);
        C4905i.m6591aR().init(C4901e.getContext());
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x003c */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.List<com.appnext.base.p078a.p080b.C4886b> getData() {
        /*
            r5 = this;
            java.lang.String r0 = "[ { \"status\": \"on\", \"sample\":\"1\", \"sample_type\":\"hour\", \"cycle\":\"1\", \"cycle_type\":\"interval\", \"key\":\"cdm\" } ]"
            android.content.Context r1 = com.appnext.base.p082b.C4901e.getContext()
            boolean r1 = com.appnext.base.p082b.C4906j.m6608i(r1)
            r2 = 0
            if (r1 == 0) goto L_0x000e
            return r2
        L_0x000e:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ HttpRetryException -> 0x0038, all -> 0x003c }
            java.lang.String r3 = "http://cdn.appnext.com/tools/services/4.7.2/config.json?packageId="
            r1.<init>(r3)     // Catch:{ HttpRetryException -> 0x0038, all -> 0x003c }
            android.content.Context r3 = com.appnext.base.p082b.C4901e.getContext()     // Catch:{ HttpRetryException -> 0x0038, all -> 0x003c }
            java.lang.String r3 = r3.getPackageName()     // Catch:{ HttpRetryException -> 0x0038, all -> 0x003c }
            r1.append(r3)     // Catch:{ HttpRetryException -> 0x0038, all -> 0x003c }
            java.lang.String r3 = "&dosv="
            r1.append(r3)     // Catch:{ HttpRetryException -> 0x0038, all -> 0x003c }
            int r3 = android.os.Build.VERSION.SDK_INT     // Catch:{ HttpRetryException -> 0x0038, all -> 0x003c }
            r1.append(r3)     // Catch:{ HttpRetryException -> 0x0038, all -> 0x003c }
            java.lang.String r3 = "&lvid=4.7.2"
            r1.append(r3)     // Catch:{ HttpRetryException -> 0x0038, all -> 0x003c }
            java.lang.String r1 = r1.toString()     // Catch:{ HttpRetryException -> 0x0038, all -> 0x003c }
            java.lang.String r0 = com.appnext.core.C4967f.m6815a((java.lang.String) r1, (java.util.HashMap<java.lang.String, java.lang.String>) r2)     // Catch:{ HttpRetryException -> 0x0038, all -> 0x003c }
            goto L_0x003c
        L_0x0038:
            r1 = move-exception
            r1.responseCode()
        L_0x003c:
            com.appnext.base.a.a r1 = com.appnext.base.p078a.C4880a.m6472X()     // Catch:{ all -> 0x0076 }
            com.appnext.base.a.c.c r1 = r1.mo40942ab()     // Catch:{ all -> 0x0076 }
            java.util.List r1 = r1.mo40977as()     // Catch:{ all -> 0x0076 }
            if (r1 == 0) goto L_0x0055
            android.content.Context r3 = com.appnext.base.p082b.C4901e.getContext()     // Catch:{ all -> 0x0076 }
            com.appnext.base.services.b.a r3 = com.appnext.base.services.p084b.C4922a.m6693d(r3)     // Catch:{ all -> 0x0076 }
            r3.mo41072h(r1)     // Catch:{ all -> 0x0076 }
        L_0x0055:
            java.util.List r0 = m6658C(r0)     // Catch:{ all -> 0x0076 }
            java.util.Iterator r0 = r0.iterator()     // Catch:{ all -> 0x0076 }
        L_0x005d:
            boolean r1 = r0.hasNext()     // Catch:{ all -> 0x0076 }
            if (r1 == 0) goto L_0x0076
            java.lang.Object r1 = r0.next()     // Catch:{ all -> 0x0076 }
            com.appnext.base.a.b.c r1 = (com.appnext.base.p078a.p080b.C4887c) r1     // Catch:{ all -> 0x0076 }
            android.content.Context r3 = com.appnext.base.p082b.C4901e.getContext()     // Catch:{ all -> 0x0076 }
            com.appnext.base.services.b.a r3 = com.appnext.base.services.p084b.C4922a.m6693d(r3)     // Catch:{ all -> 0x0076 }
            r4 = 0
            r3.mo41070a(r1, r4)     // Catch:{ all -> 0x0076 }
            goto L_0x005d
        L_0x0076:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appnext.base.operations.imp.cdm.getData():java.util.List");
    }

    /* renamed from: C */
    private static List<C4887c> m6658C(String str) {
        try {
            ArrayList arrayList = new ArrayList();
            JSONArray jSONArray = new JSONArray(str);
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                if (jSONObject.getString("status").equals(C4899d.f4618fe)) {
                    jSONObject.put("service_key", jSONObject.optString("key") + System.currentTimeMillis());
                    arrayList.add(C4898c.m6569c(jSONObject));
                }
            }
            C4880a.m6472X().mo40942ab().delete();
            C4880a.m6472X().mo40942ab().mo40978b(jSONArray);
            return arrayList;
        } catch (Throwable th) {
            th.getMessage();
            return new ArrayList();
        }
    }

    /* access modifiers changed from: protected */
    public final String getKey() {
        return cdm.class.getSimpleName();
    }
}

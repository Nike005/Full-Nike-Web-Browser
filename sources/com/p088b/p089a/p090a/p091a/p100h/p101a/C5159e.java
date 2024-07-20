package com.p088b.p089a.p090a.p091a.p100h.p101a;

import com.p088b.p089a.p090a.p091a.p093b.C5118i;
import com.p088b.p089a.p090a.p091a.p095c.C5120a;
import com.p088b.p089a.p090a.p091a.p100h.p101a.C5154b;
import java.util.HashSet;
import org.json.JSONObject;

/* renamed from: com.b.a.a.a.h.a.e */
public class C5159e extends C5153a {
    public C5159e(C5154b.C5156b bVar, HashSet<String> hashSet, JSONObject jSONObject, double d) {
        super(bVar, hashSet, jSONObject, d);
    }

    /* renamed from: b */
    private void m7205b(String str) {
        C5120a a = C5120a.m7050a();
        if (a != null) {
            for (C5118i next : a.mo41866b()) {
                if (this.f5029a.contains(next.mo41852g())) {
                    next.mo41851f().mo41915b(str, this.f5031c);
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public String doInBackground(Object... objArr) {
        return this.f5030b.toString();
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void onPostExecute(String str) {
        m7205b(str);
        super.onPostExecute(str);
    }
}

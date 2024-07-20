package com.p088b.p089a.p090a.p091a.p100h;

import com.p088b.p089a.p090a.p091a.p100h.p101a.C5154b;
import com.p088b.p089a.p090a.p091a.p100h.p101a.C5157c;
import com.p088b.p089a.p090a.p091a.p100h.p101a.C5158d;
import com.p088b.p089a.p090a.p091a.p100h.p101a.C5159e;
import com.p088b.p089a.p090a.p091a.p100h.p101a.C5160f;
import java.util.HashSet;
import org.json.JSONObject;

/* renamed from: com.b.a.a.a.h.c */
public class C5162c implements C5154b.C5156b {

    /* renamed from: a */
    private JSONObject f5044a;

    /* renamed from: b */
    private final C5157c f5045b;

    public C5162c(C5157c cVar) {
        this.f5045b = cVar;
    }

    /* renamed from: a */
    public JSONObject mo41938a() {
        return this.f5044a;
    }

    /* renamed from: a */
    public void mo41939a(JSONObject jSONObject) {
        this.f5044a = jSONObject;
    }

    /* renamed from: a */
    public void mo41955a(JSONObject jSONObject, HashSet<String> hashSet, double d) {
        this.f5045b.mo41940b(new C5160f(this, hashSet, jSONObject, d));
    }

    /* renamed from: b */
    public void mo41956b() {
        this.f5045b.mo41940b(new C5158d(this));
    }

    /* renamed from: b */
    public void mo41957b(JSONObject jSONObject, HashSet<String> hashSet, double d) {
        this.f5045b.mo41940b(new C5159e(this, hashSet, jSONObject, d));
    }
}

package com.p088b.p089a.p090a.p091a.p099g;

import android.webkit.WebView;
import com.p088b.p089a.p090a.p091a.p093b.C5109a;
import com.p088b.p089a.p090a.p091a.p093b.C5112c;
import com.p088b.p089a.p090a.p091a.p093b.C5113d;
import com.p088b.p089a.p090a.p091a.p093b.C5117h;
import com.p088b.p089a.p090a.p091a.p093b.C5118i;
import com.p088b.p089a.p090a.p091a.p093b.p094a.C5110a;
import com.p088b.p089a.p090a.p091a.p095c.C5124c;
import com.p088b.p089a.p090a.p091a.p095c.C5125d;
import com.p088b.p089a.p090a.p091a.p097e.C5134a;
import com.p088b.p089a.p090a.p091a.p097e.C5135b;
import com.p088b.p089a.p090a.p091a.p097e.C5138d;
import com.p088b.p089a.p090a.p091a.p098f.C5142b;
import org.json.JSONArray;
import org.json.JSONObject;

/* renamed from: com.b.a.a.a.g.a */
public abstract class C5143a {

    /* renamed from: a */
    private C5142b f5003a = new C5142b((WebView) null);

    /* renamed from: b */
    private C5109a f5004b;

    /* renamed from: c */
    private C5110a f5005c;

    /* renamed from: d */
    private C5144a f5006d;

    /* renamed from: e */
    private double f5007e;

    /* renamed from: com.b.a.a.a.g.a$a */
    enum C5144a {
        AD_STATE_IDLE,
        AD_STATE_VISIBLE,
        AD_STATE_HIDDEN
    }

    public C5143a() {
        mo41922i();
    }

    /* renamed from: a */
    public void mo41903a() {
    }

    /* renamed from: a */
    public void mo41904a(float f) {
        C5125d.m7072a().mo41881a(mo41916c(), f);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo41905a(WebView webView) {
        this.f5003a = new C5142b(webView);
    }

    /* renamed from: a */
    public void mo41906a(C5110a aVar) {
        this.f5005c = aVar;
    }

    /* renamed from: a */
    public void mo41907a(C5109a aVar) {
        this.f5004b = aVar;
    }

    /* renamed from: a */
    public void mo41908a(C5112c cVar) {
        C5125d.m7072a().mo41886a(mo41916c(), cVar.mo41834c());
    }

    /* renamed from: a */
    public void mo41909a(C5118i iVar, C5113d dVar) {
        String g = iVar.mo41852g();
        JSONObject jSONObject = new JSONObject();
        C5135b.m7120a(jSONObject, "environment", "app");
        C5135b.m7120a(jSONObject, "adSessionType", dVar.mo41840f());
        C5135b.m7120a(jSONObject, "deviceInfo", C5134a.m7114d());
        JSONArray jSONArray = new JSONArray();
        jSONArray.put("clid");
        jSONArray.put("vlid");
        C5135b.m7120a(jSONObject, "supports", jSONArray);
        JSONObject jSONObject2 = new JSONObject();
        C5135b.m7120a(jSONObject2, "partnerName", dVar.mo41835a().mo41843a());
        C5135b.m7120a(jSONObject2, "partnerVersion", dVar.mo41835a().mo41844b());
        C5135b.m7120a(jSONObject, "omidNativeInfo", jSONObject2);
        JSONObject jSONObject3 = new JSONObject();
        C5135b.m7120a(jSONObject3, "libraryVersion", "1.2.0-Startapp");
        C5135b.m7120a(jSONObject3, "appId", C5124c.m7069a().mo41879b().getApplicationContext().getPackageName());
        C5135b.m7120a(jSONObject, "app", jSONObject3);
        if (dVar.mo41838d() != null) {
            C5135b.m7120a(jSONObject, "customReferenceData", dVar.mo41838d());
        }
        JSONObject jSONObject4 = new JSONObject();
        for (C5117h next : dVar.mo41836b()) {
            C5135b.m7120a(jSONObject4, next.mo41845a(), next.mo41847c());
        }
        C5125d.m7072a().mo41883a(mo41916c(), g, jSONObject, jSONObject4);
    }

    /* renamed from: a */
    public void mo41910a(String str) {
        C5125d.m7072a().mo41882a(mo41916c(), str, (JSONObject) null);
    }

    /* renamed from: a */
    public void mo41911a(String str, double d) {
        if (d > this.f5007e) {
            this.f5006d = C5144a.AD_STATE_VISIBLE;
            C5125d.m7072a().mo41891c(mo41916c(), str);
        }
    }

    /* renamed from: a */
    public void mo41912a(String str, JSONObject jSONObject) {
        C5125d.m7072a().mo41882a(mo41916c(), str, jSONObject);
    }

    /* renamed from: a */
    public void mo41913a(boolean z) {
        if (mo41919f()) {
            C5125d.m7072a().mo41892d(mo41916c(), z ? "foregrounded" : "backgrounded");
        }
    }

    /* renamed from: b */
    public void mo41914b() {
        this.f5003a.clear();
    }

    /* renamed from: b */
    public void mo41915b(String str, double d) {
        if (d > this.f5007e && this.f5006d != C5144a.AD_STATE_HIDDEN) {
            this.f5006d = C5144a.AD_STATE_HIDDEN;
            C5125d.m7072a().mo41891c(mo41916c(), str);
        }
    }

    /* renamed from: c */
    public WebView mo41916c() {
        return (WebView) this.f5003a.get();
    }

    /* renamed from: d */
    public C5109a mo41917d() {
        return this.f5004b;
    }

    /* renamed from: e */
    public C5110a mo41918e() {
        return this.f5005c;
    }

    /* renamed from: f */
    public boolean mo41919f() {
        return this.f5003a.get() != null;
    }

    /* renamed from: g */
    public void mo41920g() {
        C5125d.m7072a().mo41880a(mo41916c());
    }

    /* renamed from: h */
    public void mo41921h() {
        C5125d.m7072a().mo41889b(mo41916c());
    }

    /* renamed from: i */
    public void mo41922i() {
        this.f5007e = C5138d.m7132a();
        this.f5006d = C5144a.AD_STATE_IDLE;
    }
}

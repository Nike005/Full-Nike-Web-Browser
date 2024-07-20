package com.p088b.p089a.p090a.p091a.p093b.p094a;

import com.mopub.mobileads.VastIconXmlManager;
import com.p088b.p089a.p090a.p091a.p093b.C5111b;
import com.p088b.p089a.p090a.p091a.p093b.C5118i;
import com.p088b.p089a.p090a.p091a.p095c.C5127e;
import com.p088b.p089a.p090a.p091a.p097e.C5135b;
import com.p088b.p089a.p090a.p091a.p097e.C5139e;
import org.json.JSONObject;

/* renamed from: com.b.a.a.a.b.a.a */
public final class C5110a {

    /* renamed from: a */
    private final C5118i f4934a;

    private C5110a(C5118i iVar) {
        this.f4934a = iVar;
    }

    /* renamed from: a */
    public static C5110a m6985a(C5111b bVar) {
        C5118i iVar = (C5118i) bVar;
        C5139e.m7136a((Object) bVar, "AdSession is null");
        C5139e.m7144g(iVar);
        C5139e.m7135a(iVar);
        C5139e.m7139b(iVar);
        C5139e.m7142e(iVar);
        C5110a aVar = new C5110a(iVar);
        iVar.mo41851f().mo41906a(aVar);
        return aVar;
    }

    /* renamed from: b */
    private void m6986b(float f) {
        if (f <= 0.0f) {
            throw new IllegalArgumentException("Invalid Video duration");
        }
    }

    /* renamed from: c */
    private void m6987c(float f) {
        if (f < 0.0f || f > 1.0f) {
            throw new IllegalArgumentException("Invalid Video volume");
        }
    }

    /* renamed from: a */
    public void mo41818a() {
        C5139e.m7140c(this.f4934a);
        this.f4934a.mo41851f().mo41910a("firstQuartile");
    }

    /* renamed from: a */
    public void mo41819a(float f) {
        m6987c(f);
        C5139e.m7140c(this.f4934a);
        JSONObject jSONObject = new JSONObject();
        C5135b.m7120a(jSONObject, "videoPlayerVolume", Float.valueOf(f));
        C5135b.m7120a(jSONObject, "deviceVolume", Float.valueOf(C5127e.m7086a().mo41897d()));
        this.f4934a.mo41851f().mo41912a("volumeChange", jSONObject);
    }

    /* renamed from: a */
    public void mo41820a(float f, float f2) {
        m6986b(f);
        m6987c(f2);
        C5139e.m7140c(this.f4934a);
        JSONObject jSONObject = new JSONObject();
        C5135b.m7120a(jSONObject, VastIconXmlManager.DURATION, Float.valueOf(f));
        C5135b.m7120a(jSONObject, "videoPlayerVolume", Float.valueOf(f2));
        C5135b.m7120a(jSONObject, "deviceVolume", Float.valueOf(C5127e.m7086a().mo41897d()));
        this.f4934a.mo41851f().mo41912a("start", jSONObject);
    }

    /* renamed from: b */
    public void mo41821b() {
        C5139e.m7140c(this.f4934a);
        this.f4934a.mo41851f().mo41910a("midpoint");
    }

    /* renamed from: c */
    public void mo41822c() {
        C5139e.m7140c(this.f4934a);
        this.f4934a.mo41851f().mo41910a("thirdQuartile");
    }

    /* renamed from: d */
    public void mo41823d() {
        C5139e.m7140c(this.f4934a);
        this.f4934a.mo41851f().mo41910a("complete");
    }

    /* renamed from: e */
    public void mo41824e() {
        C5139e.m7140c(this.f4934a);
        this.f4934a.mo41851f().mo41910a("pause");
    }

    /* renamed from: f */
    public void mo41825f() {
        C5139e.m7140c(this.f4934a);
        this.f4934a.mo41851f().mo41910a("bufferStart");
    }

    /* renamed from: g */
    public void mo41826g() {
        C5139e.m7140c(this.f4934a);
        this.f4934a.mo41851f().mo41910a("bufferFinish");
    }

    /* renamed from: h */
    public void mo41827h() {
        C5139e.m7140c(this.f4934a);
        this.f4934a.mo41851f().mo41910a("skipped");
    }
}

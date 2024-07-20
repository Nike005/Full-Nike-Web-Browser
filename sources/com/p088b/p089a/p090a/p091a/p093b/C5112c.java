package com.p088b.p089a.p090a.p091a.p093b;

import com.p088b.p089a.p090a.p091a.p097e.C5135b;
import com.p088b.p089a.p090a.p091a.p097e.C5139e;
import org.json.JSONObject;

/* renamed from: com.b.a.a.a.b.c */
public class C5112c {

    /* renamed from: a */
    private final C5115f f4935a;

    /* renamed from: b */
    private final C5115f f4936b;

    /* renamed from: c */
    private final boolean f4937c;

    private C5112c(C5115f fVar, C5115f fVar2, boolean z) {
        this.f4935a = fVar;
        if (fVar2 == null) {
            this.f4936b = C5115f.NONE;
        } else {
            this.f4936b = fVar2;
        }
        this.f4937c = z;
    }

    /* renamed from: a */
    public static C5112c m7003a(C5115f fVar, C5115f fVar2, boolean z) {
        C5139e.m7136a((Object) fVar, "Impression owner is null");
        C5139e.m7134a(fVar);
        return new C5112c(fVar, fVar2, z);
    }

    /* renamed from: a */
    public boolean mo41832a() {
        return C5115f.NATIVE == this.f4935a;
    }

    /* renamed from: b */
    public boolean mo41833b() {
        return C5115f.NATIVE == this.f4936b;
    }

    /* renamed from: c */
    public JSONObject mo41834c() {
        JSONObject jSONObject = new JSONObject();
        C5135b.m7120a(jSONObject, "impressionOwner", this.f4935a);
        C5135b.m7120a(jSONObject, "videoEventsOwner", this.f4936b);
        C5135b.m7120a(jSONObject, "isolateVerificationScripts", Boolean.valueOf(this.f4937c));
        return jSONObject;
    }
}

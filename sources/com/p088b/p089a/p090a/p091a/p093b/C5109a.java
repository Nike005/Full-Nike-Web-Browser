package com.p088b.p089a.p090a.p091a.p093b;

import com.p088b.p089a.p090a.p091a.p097e.C5139e;

/* renamed from: com.b.a.a.a.b.a */
public final class C5109a {

    /* renamed from: a */
    private final C5118i f4933a;

    private C5109a(C5118i iVar) {
        this.f4933a = iVar;
    }

    /* renamed from: a */
    public static C5109a m6983a(C5111b bVar) {
        C5118i iVar = (C5118i) bVar;
        C5139e.m7136a((Object) bVar, "AdSession is null");
        C5139e.m7141d(iVar);
        C5139e.m7139b(iVar);
        C5109a aVar = new C5109a(iVar);
        iVar.mo41851f().mo41907a(aVar);
        return aVar;
    }

    /* renamed from: a */
    public void mo41817a() {
        C5139e.m7139b(this.f4933a);
        C5139e.m7143f(this.f4933a);
        if (!this.f4933a.mo41854i()) {
            try {
                this.f4933a.mo41828a();
            } catch (Exception unused) {
            }
        }
        if (this.f4933a.mo41854i()) {
            this.f4933a.mo41850e();
        }
    }
}

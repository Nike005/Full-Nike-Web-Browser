package com.startapp.p005a.p006a.p012f;

import com.startapp.p005a.p006a.p007a.C0802c;
import com.startapp.p005a.p006a.p010d.C0815e;
import com.startapp.p005a.p006a.p011e.C0817b;
import com.startapp.p005a.p006a.p013g.C0823a;
import com.startapp.p005a.p006a.p013g.C0826c;

/* renamed from: com.startapp.a.a.f.a */
/* compiled from: StartAppSDK */
public class C0821a {

    /* renamed from: a */
    private final C0817b f356a;

    /* renamed from: b */
    private final C0826c f357b;

    public C0821a(C0817b bVar, C0826c cVar) {
        this.f357b = cVar;
        this.f356a = bVar;
    }

    /* renamed from: a */
    public String mo13708a(C0823a aVar, C0802c cVar, long j) {
        try {
            String a = this.f356a.mo13702a(cVar);
            C0815e b = this.f357b.mo13715b(aVar);
            return j + "-" + aVar.mo13709a() + "-" + b.mo13699a(a);
        } catch (Throwable unused) {
            return null;
        }
    }
}

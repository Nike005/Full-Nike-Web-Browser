package com.yandex.metrica.impl;

import android.os.Bundle;
import android.os.ResultReceiver;
import com.yandex.metrica.CounterConfiguration;
import com.yandex.metrica.impl.p050ob.C2096dw;
import com.yandex.metrica.impl.utils.C2221f;
import java.util.HashMap;

/* renamed from: com.yandex.metrica.impl.aw */
class C1864aw {

    /* renamed from: a */
    static final HashMap<String, String> f3003a = new HashMap<String, String>() {
        {
            put("20799a27-fa80-4b36-b2db-0f8141f24180", "13");
            put("01528cc0-dd34-494d-9218-24af1317e1ee", "17233");
            put("4e610cd2-753f-4bfc-9b05-772ce8905c5e", "21952");
            put("67bb016b-be40-4c08-a190-96a3f3b503d3", "22675");
            put("e4250327-8d3c-4d35-b9e8-3c1720a64b91", "22678");
            put("6c5f504e-8928-47b5-bfb5-73af8d8bf4b4", "30404");
            put("7d962ba4-a392-449a-a02d-6c5be5613928", "30407");
        }
    };

    /* renamed from: b */
    protected final CounterConfiguration f3004b = new CounterConfiguration();

    /* renamed from: c */
    protected C1929o f3005c;

    /* renamed from: d */
    protected C1840an f3006d;

    /* renamed from: e */
    private C2210q f3007e = new C2210q();

    protected C1864aw() {
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo16884a(C2221f.C2222a aVar) {
        this.f3005c = new C1929o(aVar);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public CounterConfiguration mo16887b() {
        return this.f3004b;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: c */
    public Bundle mo16889c() {
        return this.f3004b.mo16557F();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo16883a(C2096dw dwVar) {
        mo16888b(dwVar);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: d */
    public void mo16890d() {
        this.f3007e.mo17887b();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: e */
    public boolean mo16891e() {
        return this.f3007e.mo17886a();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public boolean mo16886a() {
        return this.f3007e.mo17888c();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public void mo16888b(C2096dw dwVar) {
        if (dwVar != null) {
            this.f3004b.mo16581d(dwVar.mo17651a());
            this.f3004b.mo16585e(dwVar.mo17659c());
            this.f3004b.mo16588f(dwVar.mo17657b());
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo16882a(C1921j jVar) {
        this.f3004b.mo16563a((ResultReceiver) jVar);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo16885a(String str, String str2) {
        this.f3005c.mo17172a(str, str2);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: f */
    public String mo16892f() {
        return this.f3005c.mo17171a();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: g */
    public C1840an mo16893g() {
        return this.f3006d;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo16881a(C1840an anVar) {
        this.f3006d = anVar;
    }
}

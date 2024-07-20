package com.yandex.metrica.impl;

import android.content.Context;
import android.os.Handler;
import com.yandex.metrica.C1780b;
import com.yandex.metrica.C1797e;
import com.yandex.metrica.impl.p050ob.C2096dw;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.yandex.metrica.impl.ax */
class C1866ax {
    /* access modifiers changed from: private */

    /* renamed from: a */
    public Context f3008a;
    /* access modifiers changed from: private */

    /* renamed from: b */
    public C1868ay f3009b;
    /* access modifiers changed from: private */

    /* renamed from: c */
    public C1921j f3010c;
    /* access modifiers changed from: private */

    /* renamed from: d */
    public Handler f3011d;
    /* access modifiers changed from: private */

    /* renamed from: e */
    public C2096dw f3012e;

    /* renamed from: f */
    private Map<String, C1780b> f3013f;

    /* synthetic */ C1866ax(byte b) {
        this();
    }

    private C1866ax() {
        this.f3013f = new HashMap();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public C2243z mo16895a(C1797e eVar, boolean z) {
        if (!this.f3013f.containsKey(eVar.getApiKey())) {
            C2243z zVar = new C2243z(this.f3008a, eVar, this.f3009b);
            m4376a((C1876b) zVar);
            zVar.mo17949a(eVar, z);
            zVar.mo16938a();
            this.f3009b.mo16909a(zVar);
            this.f3013f.put(eVar.getApiKey(), zVar);
            return zVar;
        }
        throw new IllegalArgumentException(String.format("Failed to activate AppMetrica with provided API Key. API Key %s has already been used by another reporter.", new Object[]{eVar.getApiKey()}));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public synchronized C1780b mo16894a(String str) {
        C1809aa aaVar;
        C1780b bVar = this.f3013f.get(str);
        aaVar = bVar;
        if (bVar == null) {
            C1809aa aaVar2 = new C1809aa(this.f3008a, C1864aw.f3003a.get(str), str, this.f3009b);
            m4376a((C1876b) aaVar2);
            aaVar2.mo16938a();
            this.f3013f.put(str, aaVar2);
            aaVar = aaVar2;
        }
        return aaVar;
    }

    /* renamed from: a */
    private void m4376a(C1876b bVar) {
        bVar.mo16942a(new C2239w(this.f3011d, bVar));
        bVar.mo16940a(this.f3010c);
        bVar.mo16941a(this.f3012e);
    }

    /* renamed from: com.yandex.metrica.impl.ax$a */
    static class C1867a {

        /* renamed from: a */
        C1866ax f3014a = new C1866ax((byte) 0);

        C1867a() {
        }

        /* access modifiers changed from: package-private */
        /* renamed from: a */
        public C1867a mo16896a(Context context) {
            Context unused = this.f3014a.f3008a = context;
            return this;
        }

        /* access modifiers changed from: package-private */
        /* renamed from: a */
        public C1867a mo16898a(C1868ay ayVar) {
            C1868ay unused = this.f3014a.f3009b = ayVar;
            return this;
        }

        /* access modifiers changed from: package-private */
        /* renamed from: a */
        public C1867a mo16899a(C1921j jVar) {
            C1921j unused = this.f3014a.f3010c = jVar;
            return this;
        }

        /* access modifiers changed from: package-private */
        /* renamed from: a */
        public C1867a mo16897a(Handler handler) {
            Handler unused = this.f3014a.f3011d = handler;
            return this;
        }

        /* access modifiers changed from: package-private */
        /* renamed from: a */
        public C1867a mo16900a(C2096dw dwVar) {
            C2096dw unused = this.f3014a.f3012e = dwVar;
            return this;
        }

        /* access modifiers changed from: package-private */
        /* renamed from: a */
        public C1866ax mo16901a() {
            return this.f3014a;
        }
    }
}

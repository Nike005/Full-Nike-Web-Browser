package com.yandex.metrica.impl.p050ob;

import android.util.Base64;
import java.util.HashSet;
import java.util.Set;

/* renamed from: com.yandex.metrica.impl.ob.fb */
class C2143fb {

    /* renamed from: a */
    private C2133ev f3690a;

    /* renamed from: b */
    private String f3691b;

    C2143fb(C2133ev evVar, String str) {
        this(evVar, str, (String[]) null);
    }

    C2143fb(C2133ev evVar, String str, String[] strArr) {
        this.f3690a = evVar;
        this.f3691b = str;
        if (strArr != null) {
            evVar.mo17718a(str, strArr);
        }
    }

    /* renamed from: a */
    public void mo17733a() {
        this.f3690a.mo17717a(this.f3691b, (Set<String>) new HashSet());
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public Set<String> mo17735b() {
        Set<String> a = this.f3690a.mo17716a(this.f3691b);
        return a == null ? new HashSet() : a;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: c */
    public long mo17736c() {
        return this.f3690a.mo17715a();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: d */
    public void mo17737d() {
        this.f3690a.mo17720b();
    }

    /* renamed from: a */
    public boolean mo17734a(String str) {
        if (Base64.decode(str, 2).length == 32) {
            return this.f3690a.mo17719a(this.f3691b, str);
        }
        throw new IllegalArgumentException("pin has bad length");
    }
}

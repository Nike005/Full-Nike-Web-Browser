package com.yandex.metrica.impl.p050ob;

import com.yandex.metrica.impl.p050ob.C2180i;

/* renamed from: com.yandex.metrica.impl.ob.k */
public class C2182k<T extends C2180i> {

    /* renamed from: a */
    private final C2181j<T> f3765a;

    /* renamed from: b */
    private final C2179h<T> f3766b;

    /* synthetic */ C2182k(C2183a aVar, byte b) {
        this(aVar);
    }

    private C2182k(C2183a aVar) {
        this.f3765a = aVar.f3768b;
        this.f3766b = aVar.f3767a;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final void mo17811a(C2180i iVar) {
        this.f3765a.mo16931a(iVar);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public final boolean mo17812b(C2180i iVar) {
        C2179h<T> hVar = this.f3766b;
        if (hVar == null) {
            return false;
        }
        return hVar.mo17625a(iVar);
    }

    /* renamed from: a */
    public static <T extends C2180i> C2183a<T> m5767a(C2181j<T> jVar) {
        return new C2183a<>(jVar);
    }

    /* renamed from: com.yandex.metrica.impl.ob.k$a */
    public static final class C2183a<T extends C2180i> {

        /* renamed from: a */
        public C2179h<T> f3767a;
        /* access modifiers changed from: private */

        /* renamed from: b */
        public C2181j<T> f3768b;

        C2183a(C2181j<T> jVar) {
            this.f3768b = jVar;
        }

        /* renamed from: a */
        public C2183a<T> mo17813a(C2179h<T> hVar) {
            this.f3767a = hVar;
            return this;
        }

        /* renamed from: a */
        public C2182k<T> mo17814a() {
            return new C2182k<>(this, (byte) 0);
        }
    }
}

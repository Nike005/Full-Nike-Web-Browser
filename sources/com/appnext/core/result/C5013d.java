package com.appnext.core.result;

/* renamed from: com.appnext.core.result.d */
public final class C5013d {

    /* renamed from: ik */
    private static C5013d f4891ik;

    /* renamed from: if */
    private C5012c f4892if;

    private C5013d() {
    }

    /* renamed from: br */
    public static synchronized C5013d m6946br() {
        C5013d dVar;
        synchronized (C5013d.class) {
            if (f4891ik == null) {
                f4891ik = new C5013d();
            }
            dVar = f4891ik;
        }
        return dVar;
    }

    /* renamed from: bs */
    public final C5012c mo41340bs() {
        return this.f4892if;
    }

    /* renamed from: a */
    public final void mo41339a(C5012c cVar) {
        this.f4892if = cVar;
    }
}

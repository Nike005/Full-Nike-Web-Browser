package com.tappx.p048a;

/* renamed from: com.tappx.a.i2 */
class C1456i2 {

    /* renamed from: a */
    private final C1649v f1942a;

    /* renamed from: com.tappx.a.i2$a */
    class C1457a implements C1516m<Void> {

        /* renamed from: a */
        final /* synthetic */ C1459c f1943a;

        C1457a(C1456i2 i2Var, C1459c cVar) {
            this.f1943a = cVar;
        }

        /* renamed from: a */
        public void mo15866a(Void voidR) {
            this.f1943a.mo15869a(true);
        }
    }

    /* renamed from: com.tappx.a.i2$b */
    class C1458b implements C1430h<Void> {

        /* renamed from: a */
        final /* synthetic */ C1459c f1944a;

        C1458b(C1456i2 i2Var, C1459c cVar) {
            this.f1944a = cVar;
        }

        /* renamed from: a */
        public void mo15840a(Void voidR) {
            this.f1944a.mo15869a(false);
        }
    }

    /* renamed from: com.tappx.a.i2$c */
    public interface C1459c {
        /* renamed from: a */
        void mo15869a(boolean z);
    }

    C1456i2(C1649v vVar) {
        this.f1942a = vVar;
    }

    /* renamed from: a */
    public void mo15865a(C1566p2 p2Var, long j, C1459c cVar) {
        this.f1942a.mo16218a(j, p2Var, new C1457a(this, cVar), new C1458b(this, cVar));
    }
}

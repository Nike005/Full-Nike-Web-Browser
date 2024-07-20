package com.tappx.p048a;

/* renamed from: com.tappx.a.k2 */
class C1488k2 {

    /* renamed from: a */
    private final C1649v f2000a;
    /* access modifiers changed from: private */

    /* renamed from: b */
    public final C1619t f2001b;

    /* renamed from: com.tappx.a.k2$a */
    class C1489a implements C1516m<C1473j2> {

        /* renamed from: a */
        final /* synthetic */ C1491c f2002a;

        C1489a(C1491c cVar) {
            this.f2002a = cVar;
        }

        /* renamed from: a */
        public void mo15866a(C1473j2 j2Var) {
            String c = j2Var.mo15887c();
            if (c != null) {
                C1488k2.this.f2001b.mo16173a(c);
            }
            if (j2Var.mo15888d()) {
                this.f2002a.mo15907a(j2Var.mo15885a(), j2Var.mo15886b());
            } else {
                this.f2002a.mo15908b();
            }
        }
    }

    /* renamed from: com.tappx.a.k2$b */
    class C1490b implements C1430h<Void> {

        /* renamed from: a */
        final /* synthetic */ C1491c f2004a;

        C1490b(C1488k2 k2Var, C1491c cVar) {
            this.f2004a = cVar;
        }

        /* renamed from: a */
        public void mo15840a(Void voidR) {
            this.f2004a.mo15906a();
        }
    }

    /* renamed from: com.tappx.a.k2$c */
    public interface C1491c {
        /* renamed from: a */
        void mo15906a();

        /* renamed from: a */
        void mo15907a(String str, String str2);

        /* renamed from: b */
        void mo15908b();
    }

    public C1488k2(C1649v vVar, C1619t tVar) {
        this.f2000a = vVar;
        this.f2001b = tVar;
    }

    /* renamed from: a */
    public void mo15903a(C1491c cVar) {
        this.f2000a.mo16220a(new C1489a(cVar), new C1490b(this, cVar));
    }
}

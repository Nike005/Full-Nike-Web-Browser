package com.tappx.p048a;

import com.tappx.p048a.C1584q1;
import com.tappx.p048a.C1649v;
import com.tappx.sdk.android.AdRequest;

/* renamed from: com.tappx.a.r1 */
public class C1593r1 implements C1584q1 {

    /* renamed from: a */
    private final C1649v f2235a;

    /* renamed from: b */
    private final C1482k0 f2236b;

    /* renamed from: c */
    private final C1365d2 f2237c;

    /* renamed from: d */
    private C1584q1.C1585a f2238d;

    /* renamed from: e */
    private C1649v.C1651b f2239e;

    /* renamed from: com.tappx.a.r1$a */
    class C1594a implements C1516m<C1673w1> {
        C1594a() {
        }

        /* renamed from: a */
        public void mo15866a(C1673w1 w1Var) {
            C1593r1.this.m3302b(w1Var);
        }
    }

    /* renamed from: com.tappx.a.r1$b */
    class C1595b implements C1430h<C1649v.C1650a> {
        C1595b() {
        }

        /* renamed from: a */
        public void mo15840a(C1649v.C1650a aVar) {
            C1593r1.this.m3301b(aVar);
        }
    }

    /* renamed from: com.tappx.a.r1$c */
    static /* synthetic */ class C1596c {

        /* renamed from: a */
        static final /* synthetic */ int[] f2242a;

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|(3:7|8|10)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        static {
            /*
                com.tappx.a.v$a[] r0 = com.tappx.p048a.C1649v.C1650a.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                f2242a = r0
                com.tappx.a.v$a r1 = com.tappx.p048a.C1649v.C1650a.DEVELOPER_ERROR     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = f2242a     // Catch:{ NoSuchFieldError -> 0x001d }
                com.tappx.a.v$a r1 = com.tappx.p048a.C1649v.C1650a.SERVER_ERROR     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = f2242a     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.tappx.a.v$a r1 = com.tappx.p048a.C1649v.C1650a.NO_FILL     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = f2242a     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.tappx.a.v$a r1 = com.tappx.p048a.C1649v.C1650a.NETWORK_ERROR     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.tappx.p048a.C1593r1.C1596c.<clinit>():void");
        }
    }

    public C1593r1(C1649v vVar, C1482k0 k0Var, C1365d2 d2Var) {
        this.f2235a = vVar;
        this.f2236b = k0Var;
        this.f2237c = d2Var;
    }

    /* access modifiers changed from: private */
    /* renamed from: b */
    public void m3302b(C1673w1 w1Var) {
        if (w1Var.mo16243f()) {
            mo16102a(C1313a2.NO_FILL);
        } else {
            mo16103a(w1Var);
        }
    }

    public void destroy() {
        mo16101a();
    }

    /* renamed from: a */
    public void mo16074a(C1584q1.C1585a aVar) {
        this.f2238d = aVar;
    }

    /* renamed from: a */
    public void mo16075a(String str, String str2, C1654v1 v1Var, AdRequest adRequest) {
        mo16101a();
        this.f2239e = this.f2235a.mo16219a(this.f2236b.mo15893a(str, v1Var, str2, adRequest), (C1516m<C1673w1>) new C1594a(), (C1430h<C1649v.C1650a>) new C1595b());
        this.f2237c.mo15677a();
    }

    /* access modifiers changed from: private */
    /* renamed from: b */
    public void m3301b(C1649v.C1650a aVar) {
        m3300a(aVar);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo16101a() {
        C1649v.C1651b bVar = this.f2239e;
        if (bVar != null) {
            this.f2235a.mo16222a(bVar);
            this.f2239e = null;
        }
    }

    /* renamed from: a */
    private void m3300a(C1649v.C1650a aVar) {
        int i = C1596c.f2242a[aVar.ordinal()];
        if (i == 1) {
            mo16102a(C1313a2.DEVELOPER_ERROR);
        } else if (i == 2) {
            mo16102a(C1313a2.SERVER_ERROR);
        } else if (i == 3) {
            mo16102a(C1313a2.NO_FILL);
        } else if (i != 4) {
            mo16102a(C1313a2.UNSPECIFIED);
        } else {
            mo16102a(C1313a2.NETWORK_ERROR);
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo16102a(C1313a2 a2Var) {
        this.f2239e = null;
        C1584q1.C1585a aVar = this.f2238d;
        if (aVar != null) {
            aVar.mo15771a(a2Var);
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo16103a(C1673w1 w1Var) {
        this.f2239e = null;
        C1584q1.C1585a aVar = this.f2238d;
        if (aVar != null) {
            aVar.mo15772a(w1Var);
        }
    }
}

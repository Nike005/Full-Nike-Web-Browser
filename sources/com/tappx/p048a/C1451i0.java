package com.tappx.p048a;

import com.tappx.p048a.C1309a0;
import com.tappx.p048a.C1358d0;
import com.tappx.p048a.C1401f0;
import com.tappx.p048a.C1615s5;
import com.tappx.p048a.C1647u5;
import java.util.Map;

/* renamed from: com.tappx.a.i0 */
final class C1451i0<T> extends C1615s5<T> {

    /* renamed from: q */
    private final C1358d0<T> f1928q;

    /* renamed from: com.tappx.a.i0$a */
    class C1452a implements C1647u5.C1648a {

        /* renamed from: a */
        final /* synthetic */ C1358d0 f1929a;

        C1452a(C1358d0 d0Var) {
            this.f1929a = d0Var;
        }

        /* renamed from: a */
        public void mo15863a(C1718z5 z5Var) {
            C1309a0 a0Var;
            C1401f0.C1402a b = this.f1929a.mo15655b();
            if (b != null) {
                if (z5Var instanceof C1706z) {
                    a0Var = ((C1706z) z5Var).mo16295a();
                } else {
                    C1590q5 q5Var = z5Var.f2598a;
                    Map<String, String> map = q5Var != null ? q5Var.f2230c : null;
                    C1590q5 q5Var2 = z5Var.f2598a;
                    int i = q5Var2 != null ? q5Var2.f2228a : -1;
                    if (C1451i0.m2828d(z5Var)) {
                        a0Var = new C1309a0(C1309a0.C1310a.SERVER_ERROR, map, i);
                    } else {
                        a0Var = new C1309a0(C1309a0.C1310a.NETWORK_ERROR, map, i);
                    }
                }
                b.mo15762a(a0Var);
            }
        }
    }

    /* renamed from: com.tappx.a.i0$b */
    static /* synthetic */ class C1453b {

        /* renamed from: a */
        static final /* synthetic */ int[] f1930a;

        /* renamed from: b */
        static final /* synthetic */ int[] f1931b;

        /* JADX WARNING: Can't wrap try/catch for region: R(23:0|(2:1|2)|3|(2:5|6)|7|9|10|11|(2:13|14)|15|17|18|19|20|21|22|23|24|25|26|27|28|(3:29|30|32)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(27:0|1|2|3|(2:5|6)|7|9|10|11|13|14|15|17|18|19|20|21|22|23|24|25|26|27|28|29|30|32) */
        /* JADX WARNING: Can't wrap try/catch for region: R(28:0|1|2|3|5|6|7|9|10|11|13|14|15|17|18|19|20|21|22|23|24|25|26|27|28|29|30|32) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x0044 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x004e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:23:0x0058 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:25:0x0062 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:27:0x006d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:29:0x0078 */
        static {
            /*
                com.tappx.a.d0$b[] r0 = com.tappx.p048a.C1358d0.C1360b.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                f1931b = r0
                r1 = 1
                com.tappx.a.d0$b r2 = com.tappx.p048a.C1358d0.C1360b.LOW     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r0[r2] = r1     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                r0 = 2
                int[] r2 = f1931b     // Catch:{ NoSuchFieldError -> 0x001d }
                com.tappx.a.d0$b r3 = com.tappx.p048a.C1358d0.C1360b.HIGH     // Catch:{ NoSuchFieldError -> 0x001d }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2[r3] = r0     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                r2 = 3
                int[] r3 = f1931b     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.tappx.a.d0$b r4 = com.tappx.p048a.C1358d0.C1360b.IMMEDIATE     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r3[r4] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                r3 = 4
                int[] r4 = f1931b     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.tappx.a.d0$b r5 = com.tappx.p048a.C1358d0.C1360b.NORMAL     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r5 = r5.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r4[r5] = r3     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                com.tappx.a.d0$a[] r4 = com.tappx.p048a.C1358d0.C1359a.values()
                int r4 = r4.length
                int[] r4 = new int[r4]
                f1930a = r4
                com.tappx.a.d0$a r5 = com.tappx.p048a.C1358d0.C1359a.POST     // Catch:{ NoSuchFieldError -> 0x0044 }
                int r5 = r5.ordinal()     // Catch:{ NoSuchFieldError -> 0x0044 }
                r4[r5] = r1     // Catch:{ NoSuchFieldError -> 0x0044 }
            L_0x0044:
                int[] r1 = f1930a     // Catch:{ NoSuchFieldError -> 0x004e }
                com.tappx.a.d0$a r4 = com.tappx.p048a.C1358d0.C1359a.DELETE     // Catch:{ NoSuchFieldError -> 0x004e }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x004e }
                r1[r4] = r0     // Catch:{ NoSuchFieldError -> 0x004e }
            L_0x004e:
                int[] r0 = f1930a     // Catch:{ NoSuchFieldError -> 0x0058 }
                com.tappx.a.d0$a r1 = com.tappx.p048a.C1358d0.C1359a.HEAD     // Catch:{ NoSuchFieldError -> 0x0058 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0058 }
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0058 }
            L_0x0058:
                int[] r0 = f1930a     // Catch:{ NoSuchFieldError -> 0x0062 }
                com.tappx.a.d0$a r1 = com.tappx.p048a.C1358d0.C1359a.OPTIONS     // Catch:{ NoSuchFieldError -> 0x0062 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0062 }
                r0[r1] = r3     // Catch:{ NoSuchFieldError -> 0x0062 }
            L_0x0062:
                int[] r0 = f1930a     // Catch:{ NoSuchFieldError -> 0x006d }
                com.tappx.a.d0$a r1 = com.tappx.p048a.C1358d0.C1359a.PATCH     // Catch:{ NoSuchFieldError -> 0x006d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x006d }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x006d }
            L_0x006d:
                int[] r0 = f1930a     // Catch:{ NoSuchFieldError -> 0x0078 }
                com.tappx.a.d0$a r1 = com.tappx.p048a.C1358d0.C1359a.PUT     // Catch:{ NoSuchFieldError -> 0x0078 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0078 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0078 }
            L_0x0078:
                int[] r0 = f1930a     // Catch:{ NoSuchFieldError -> 0x0083 }
                com.tappx.a.d0$a r1 = com.tappx.p048a.C1358d0.C1359a.GET     // Catch:{ NoSuchFieldError -> 0x0083 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0083 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0083 }
            L_0x0083:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.tappx.p048a.C1451i0.C1453b.<clinit>():void");
        }
    }

    public C1451i0(C1358d0<T> d0Var) {
        super(m2825a(d0Var.mo15657d()), d0Var.mo15660g(), new C1452a(d0Var));
        this.f1928q = d0Var;
        mo15857a(d0Var);
        mo16138a(d0Var.mo15662i());
    }

    /* renamed from: a */
    private static int m2825a(C1358d0.C1359a aVar) {
        switch (C1453b.f1930a[aVar.ordinal()]) {
            case 1:
                return 1;
            case 2:
                return 3;
            case 3:
                return 4;
            case 4:
                return 5;
            case 5:
                return 7;
            case 6:
                return 2;
            default:
                return 0;
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: d */
    public static boolean m2828d(C1718z5 z5Var) {
        int i;
        C1590q5 q5Var = z5Var.f2598a;
        if (q5Var != null && (i = q5Var.f2228a) >= 500 && i <= 599) {
            return true;
        }
        return false;
    }

    /* renamed from: b */
    public byte[] mo15859b() {
        return this.f1928q.mo15654a();
    }

    /* renamed from: f */
    public Map<String, String> mo15860f() {
        return this.f1928q.mo15656c();
    }

    /* renamed from: j */
    public byte[] mo15861j() {
        return mo15859b();
    }

    /* renamed from: m */
    public C1615s5.C1618c mo15862m() {
        return m2826a(this.f1928q.mo15658e());
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public C1647u5<T> mo15856a(C1590q5 q5Var) {
        C1401f0<T> a = this.f1928q.mo15648a(new C1337c0(q5Var.f2228a, q5Var.f2230c, q5Var.f2229b, q5Var.f2233f));
        if (a.mo15761a()) {
            return C1647u5.m3483a(a.f1808a, C1416f6.m2680a(q5Var));
        }
        return C1647u5.m3482a(new C1706z(a.f1809b));
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo15858a(T t) {
        this.f1928q.mo15652a(t);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo15857a(C1358d0<T> d0Var) {
        C1418g0 f = d0Var.mo15659f();
        if (f != null) {
            mo16147b(f.mo15819d());
            mo16137a((C1678w5) new C1497k5(f.mo15817b(), f.mo15818c(), f.mo15815a()));
        }
    }

    /* renamed from: a */
    private C1615s5.C1618c m2826a(C1358d0.C1360b bVar) {
        int i = C1453b.f1931b[bVar.ordinal()];
        if (i == 1) {
            return C1615s5.C1618c.LOW;
        }
        if (i == 2) {
            return C1615s5.C1618c.HIGH;
        }
        if (i != 3) {
            return C1615s5.C1618c.NORMAL;
        }
        return C1615s5.C1618c.IMMEDIATE;
    }
}

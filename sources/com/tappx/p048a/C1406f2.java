package com.tappx.p048a;

import android.content.Context;
import com.tappx.p048a.C1584q1;
import com.tappx.sdk.android.AdRequest;
import com.tappx.sdk.android.TappxAdError;

/* renamed from: com.tappx.a.f2 */
public abstract class C1406f2 implements C1584q1.C1585a {

    /* renamed from: a */
    private final C1584q1 f1818a;

    /* renamed from: b */
    private final Context f1819b;

    /* renamed from: c */
    private final C1624t2 f1820c;

    /* renamed from: d */
    private final C1654v1 f1821d;

    /* renamed from: e */
    private final C1432h1 f1822e;

    /* renamed from: f */
    private final C1608s1 f1823f;

    /* renamed from: g */
    protected final C1536n2 f1824g;

    /* renamed from: h */
    protected AdRequest f1825h;

    /* renamed from: i */
    private String f1826i;

    /* renamed from: j */
    private String f1827j;

    /* renamed from: k */
    boolean f1828k;

    /* renamed from: com.tappx.a.f2$a */
    class C1407a implements Runnable {

        /* renamed from: a */
        final /* synthetic */ AdRequest f1829a;

        C1407a(AdRequest adRequest) {
            this.f1829a = adRequest;
        }

        public void run() {
            C1406f2.this.m2634c(this.f1829a);
        }
    }

    /* renamed from: com.tappx.a.f2$b */
    static /* synthetic */ class C1408b {

        /* renamed from: a */
        static final /* synthetic */ int[] f1831a;

        /* JADX WARNING: Can't wrap try/catch for region: R(14:0|1|2|3|4|5|6|7|8|9|10|11|12|14) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x003e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                com.tappx.a.a2[] r0 = com.tappx.p048a.C1313a2.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                f1831a = r0
                com.tappx.a.a2 r1 = com.tappx.p048a.C1313a2.DEVELOPER_ERROR     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = f1831a     // Catch:{ NoSuchFieldError -> 0x001d }
                com.tappx.a.a2 r1 = com.tappx.p048a.C1313a2.INTERNAL_ERROR     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = f1831a     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.tappx.a.a2 r1 = com.tappx.p048a.C1313a2.NETWORK_ERROR     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = f1831a     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.tappx.a.a2 r1 = com.tappx.p048a.C1313a2.NO_FILL     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = f1831a     // Catch:{ NoSuchFieldError -> 0x003e }
                com.tappx.a.a2 r1 = com.tappx.p048a.C1313a2.SERVER_ERROR     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = f1831a     // Catch:{ NoSuchFieldError -> 0x0049 }
                com.tappx.a.a2 r1 = com.tappx.p048a.C1313a2.UNSPECIFIED     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.tappx.p048a.C1406f2.C1408b.<clinit>():void");
        }
    }

    /* renamed from: com.tappx.a.f2$c */
    private static final class C1409c extends Exception {
        private C1409c() {
        }

        /* synthetic */ C1409c(C1407a aVar) {
            this();
        }
    }

    C1406f2(Context context, C1654v1 v1Var) {
        this(context, v1Var, C1552o2.m3165a(context).mo16036c(), new C1608s1(context));
    }

    /* renamed from: e */
    private C1332c mo15844e() {
        return C1332c.m2291a(this.f1819b);
    }

    /* renamed from: f */
    private void mo15845f() {
        boolean z = false;
        if (this.f1826i == null) {
            C1467j0.m2871b(C1400f.m2603b("ql/gpRHeskeYCNYrbDS7nxNb5jI2ynHn201S0j/Gqul8JUVryuBrMPjdaYQ+79ST"), new Object[0]);
            z = true;
        }
        if (z) {
            mo15774a(TappxAdError.DEVELOPER_ERROR);
            throw new C1409c((C1407a) null);
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public abstract void mo15774a(TappxAdError tappxAdError);

    /* renamed from: b */
    public Context mo15776b() {
        return this.f1819b;
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public abstract void mo15778b(C1673w1 w1Var);

    /* renamed from: c */
    public void mo15781c() {
        mo15779b(this.f1825h);
    }

    /* access modifiers changed from: protected */
    /* renamed from: d */
    public void mo15783d() {
    }

    C1406f2(Context context, C1654v1 v1Var, C1536n2 n2Var, C1608s1 s1Var) {
        this.f1819b = context;
        this.f1821d = v1Var;
        C1332c e = mo15844e();
        this.f1824g = n2Var;
        this.f1818a = e.mo15590o();
        this.f1822e = e.mo15582g();
        this.f1818a.mo16074a(this);
        this.f1820c = e.mo15580e();
        this.f1823f = s1Var;
    }

    /* access modifiers changed from: private */
    /* renamed from: c */
    public void m2634c(AdRequest adRequest) {
        C1467j0.m2869a(C1400f.m2603b("mo5jy7IL/t1GLb3J/P8gjQ"), new Object[0]);
        C1467j0.m2873d("CpJSwAt+xAYUOl939gSabw", new Object[0]);
        this.f1825h = adRequest;
        if (adRequest == null) {
            adRequest = new AdRequest();
        }
        try {
            mo15845f();
            if (!this.f1822e.mo15841a()) {
                mo15774a(TappxAdError.NETWORK_ERROR);
                return;
            }
            mo15783d();
            this.f1818a.mo16075a(this.f1826i, this.f1827j, this.f1821d, adRequest);
        } catch (C1409c unused) {
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo15775a(String str) {
        this.f1827j = str;
    }

    /* renamed from: b */
    public void mo15780b(String str) {
        this.f1826i = str;
    }

    /* renamed from: a */
    public void mo15770a() {
        this.f1828k = true;
        this.f1818a.destroy();
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public void mo15779b(AdRequest adRequest) {
        try {
            this.f1823f.mo16121a();
            this.f1824g.mo15982a((Runnable) new C1407a(adRequest));
        } catch (Exception unused) {
            mo15774a(TappxAdError.NO_FILL);
        }
    }

    /* renamed from: a */
    public void mo15773a(AdRequest adRequest) {
        mo15779b(adRequest);
    }

    /* renamed from: a */
    public final void mo15772a(C1673w1 w1Var) {
        if (!this.f1828k) {
            this.f1824g.mo15985a(w1Var.mo16238a(), w1Var.mo16242e(), w1Var.mo16241d());
            C1467j0.m2869a(C1400f.m2603b("ftLVnAFo4UVdmS7TEXHP3z1+tuYsCsVdhGwkH7sMMCI"), new Object[0]);
            C1467j0.m2873d("7+KAkb3Ej2KFLftBLdWrHXNw5SyHuZNhHCgeqkrxnXg", new Object[0]);
            mo15778b(w1Var);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public TappxAdError mo15777b(C1313a2 a2Var) {
        int i = C1408b.f1831a[a2Var.ordinal()];
        if (i == 1) {
            return TappxAdError.DEVELOPER_ERROR;
        }
        if (i == 2) {
            return TappxAdError.INTERNAL_ERROR;
        }
        if (i == 3) {
            return TappxAdError.NETWORK_ERROR;
        }
        if (i == 4) {
            return TappxAdError.NO_FILL;
        }
        if (i != 5) {
            return TappxAdError.UNSPECIFIED;
        }
        return TappxAdError.SERVER_ERROR;
    }

    /* renamed from: a */
    public final void mo15771a(C1313a2 a2Var) {
        if (!this.f1828k) {
            TappxAdError b = mo15777b(a2Var);
            String b2 = C1400f.m2603b("8V2SkLfQtXT7yOPHxqrPlAg6jp+lx+rvQTk+I2vfHWM");
            C1467j0.m2869a(b2 + b, new Object[0]);
            C1467j0.m2873d("lgaGjSo8VdlXgzQ7qLaLqzOElG/CkYie3dvHgxY0q1o", String.valueOf(b));
            mo15774a(b);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: c */
    public void mo15782c(String str) {
        if (str != null) {
            this.f1820c.mo16176a(str);
        }
    }
}

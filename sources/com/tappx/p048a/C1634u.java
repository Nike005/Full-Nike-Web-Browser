package com.tappx.p048a;

import android.net.Uri;
import com.tappx.p048a.C1309a0;
import com.tappx.p048a.C1358d0;
import com.tappx.p048a.C1401f0;
import com.tappx.p048a.C1471j1;
import com.tappx.p048a.C1500l0;
import com.tappx.p048a.C1522m1;
import java.util.Map;

/* renamed from: com.tappx.a.u */
public class C1634u extends C1358d0<Void> {

    /* renamed from: l */
    private static final String f2327l = C1400f.m2603b("Y3lXBmQ23xTYiukQ1UnbWw");

    /* renamed from: m */
    private static final String f2328m = C1400f.m2603b("KG6txY+dAsHV+aE9vCpHOQ");

    /* renamed from: n */
    private static final String f2329n = C1400f.m2603b("FzLBfq4NHhh6H3aZu09wNg");

    /* renamed from: o */
    private static final String f2330o = C1400f.m2603b("5RPecgzrVUOe/I8D8SnSVA");

    /* renamed from: p */
    private static final String f2331p = C1400f.m2603b("p2JtzU2YCqXoi6X+GUHC9A");

    /* renamed from: q */
    private static final String f2332q = C1400f.m2603b("ChYe7NtYsJ5it5MJ0kItoQ");

    /* renamed from: f */
    private final C1522m1.C1523a f2333f;

    /* renamed from: g */
    private final C1471j1.C1472a f2334g;

    /* renamed from: h */
    private final C1500l0.C1501a f2335h;

    /* renamed from: i */
    private final long f2336i;

    /* renamed from: j */
    private final C1566p2 f2337j;

    /* renamed from: k */
    private final String f2338k;

    /* renamed from: com.tappx.a.u$a */
    static /* synthetic */ class C1635a {

        /* renamed from: a */
        static final /* synthetic */ int[] f2339a;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        static {
            /*
                com.tappx.a.p2[] r0 = com.tappx.p048a.C1566p2.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                f2339a = r0
                com.tappx.a.p2 r1 = com.tappx.p048a.C1566p2.GRANTED_DEVELOPER     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = f2339a     // Catch:{ NoSuchFieldError -> 0x001d }
                com.tappx.a.p2 r1 = com.tappx.p048a.C1566p2.DENIED_DEVELOPER     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.tappx.p048a.C1634u.C1635a.<clinit>():void");
        }
    }

    /* renamed from: com.tappx.a.u$b */
    public static class C1636b {

        /* renamed from: a */
        private final C1522m1.C1523a f2340a;

        /* renamed from: b */
        private final C1619t f2341b;

        /* renamed from: c */
        private final C1471j1.C1472a f2342c;

        /* renamed from: d */
        private final C1500l0.C1501a f2343d;

        public C1636b(C1522m1.C1523a aVar, C1619t tVar, C1471j1.C1472a aVar2, C1500l0.C1501a aVar3) {
            this.f2340a = aVar;
            this.f2341b = tVar;
            this.f2342c = aVar2;
            this.f2343d = aVar3;
        }

        /* access modifiers changed from: package-private */
        /* renamed from: a */
        public C1634u mo16196a(C1401f0.C1403b<Void> bVar, C1401f0.C1402a aVar, long j, C1566p2 p2Var) {
            return new C1634u(this.f2341b.mo16172a(), bVar, aVar, this.f2340a, this.f2342c, this.f2343d, j, p2Var);
        }
    }

    C1634u(String str, C1401f0.C1403b<Void> bVar, C1401f0.C1402a aVar, C1522m1.C1523a aVar2, C1471j1.C1472a aVar3, C1500l0.C1501a aVar4, long j, C1566p2 p2Var) {
        super(bVar, aVar);
        this.f2338k = str;
        this.f2333f = aVar2;
        this.f2334g = aVar3;
        this.f2335h = aVar4;
        mo15653a(false);
        mo15651a(new C1418g0(10000, 1, 1.0f));
        this.f2336i = j;
        this.f2337j = p2Var;
    }

    /* renamed from: a */
    private String m3436a(C1566p2 p2Var) {
        int i = C1635a.f2339a[p2Var.ordinal()];
        return (i == 1 || i == 2) ? "d" : "u";
    }

    /* renamed from: a */
    public byte[] mo15654a() {
        return null;
    }

    /* renamed from: c */
    public Map<String, String> mo15656c() {
        return mo15661h();
    }

    /* renamed from: d */
    public C1358d0.C1359a mo15657d() {
        return C1358d0.C1359a.GET;
    }

    /* renamed from: g */
    public String mo15660g() {
        Uri.Builder buildUpon = Uri.parse(this.f2338k).buildUpon();
        C1522m1 b = this.f2333f.mo15965b();
        C1500l0 a = this.f2335h.mo15927a();
        C1471j1 a2 = this.f2334g.mo15884a();
        buildUpon.appendQueryParameter(f2327l, b.f2073e);
        buildUpon.appendQueryParameter(f2328m, b.f2072d);
        buildUpon.appendQueryParameter(f2329n, this.f2337j.mo16051a() ? "0" : "1");
        buildUpon.appendQueryParameter(NotificationBundleProcessor.PUSH_MINIFIED_BUTTONS_LIST, m3436a(this.f2337j));
        buildUpon.appendQueryParameter(f2330o, String.valueOf(this.f2336i));
        buildUpon.appendQueryParameter(f2331p, a2.f1968a);
        buildUpon.appendQueryParameter(f2332q, a.f2025a);
        return buildUpon.build().toString();
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public C1401f0<Void> mo15648a(C1337c0 c0Var) {
        if ("1".equals(c0Var.mo15592a())) {
            return C1401f0.m2606a(null);
        }
        return C1401f0.m2605a(new C1309a0(C1309a0.C1310a.PARSE_ERROR));
    }
}

package com.yandex.metrica.impl.p050ob;

import java.util.ArrayList;
import java.util.List;

/* renamed from: com.yandex.metrica.impl.ob.cf */
class C2022cf {

    /* renamed from: a */
    private final String f3400a;

    /* renamed from: b */
    private final C2030cj f3401b;

    /* renamed from: c */
    private int f3402c;

    /* renamed from: d */
    private final List<C2020ce> f3403d = new ArrayList();

    /* renamed from: e */
    private final List<C2020ce> f3404e = new ArrayList();

    /* renamed from: f */
    private final List<C2020ce> f3405f = new ArrayList();

    C2022cf(String str, C2030cj cjVar) {
        this.f3400a = str;
        this.f3401b = cjVar;
    }

    /* renamed from: a */
    public void mo17456a(C2020ce ceVar) {
        this.f3402c += ceVar.mo17454c().f3087b;
        this.f3403d.add(ceVar);
        int i = C20231.f3406a[ceVar.mo17451a(this.f3401b).ordinal()];
        if (i == 1) {
            this.f3404e.add(ceVar);
        } else if (i == 2) {
            this.f3405f.add(ceVar);
        }
    }

    /* renamed from: com.yandex.metrica.impl.ob.cf$1 */
    static /* synthetic */ class C20231 {

        /* renamed from: a */
        static final /* synthetic */ int[] f3406a;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        static {
            /*
                com.yandex.metrica.impl.ob.ce$a[] r0 = com.yandex.metrica.impl.p050ob.C2020ce.C2021a.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                f3406a = r0
                com.yandex.metrica.impl.ob.ce$a r1 = com.yandex.metrica.impl.p050ob.C2020ce.C2021a.THIS     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = f3406a     // Catch:{ NoSuchFieldError -> 0x001d }
                com.yandex.metrica.impl.ob.ce$a r1 = com.yandex.metrica.impl.p050ob.C2020ce.C2021a.OTHER     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.yandex.metrica.impl.p050ob.C2022cf.C20231.<clinit>():void");
        }
    }

    /* renamed from: a */
    public boolean mo17457a() {
        return !this.f3405f.isEmpty();
    }

    /* renamed from: b */
    public int mo17458b() {
        return this.f3403d.size();
    }

    /* renamed from: c */
    public String mo17459c() {
        return this.f3400a;
    }

    /* renamed from: d */
    public List<C2020ce> mo17460d() {
        return this.f3403d;
    }

    /* renamed from: e */
    public Long mo17461e() {
        long j = Long.MAX_VALUE;
        for (C2020ce c : this.f3403d) {
            Long valueOf = Long.valueOf(c.mo17454c().f3088c);
            if (valueOf.compareTo(j) < 0) {
                j = valueOf;
            }
        }
        return j;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return this.f3400a.equals(((C2022cf) obj).f3400a);
    }

    public int hashCode() {
        return this.f3400a.hashCode();
    }
}

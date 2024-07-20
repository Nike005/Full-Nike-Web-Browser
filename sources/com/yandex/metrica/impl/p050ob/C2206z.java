package com.yandex.metrica.impl.p050ob;

import com.yandex.metrica.impl.C2208p;
import java.util.LinkedList;

/* renamed from: com.yandex.metrica.impl.ob.z */
public class C2206z extends C1931aa<C1936af> {

    /* renamed from: a */
    private final C1960bb f3832a;

    /* renamed from: b */
    private final C1959ba f3833b;

    /* renamed from: c */
    private final C1956az f3834c;

    /* renamed from: d */
    private final C1946ap f3835d;

    /* renamed from: e */
    private final C1954ax f3836e;

    /* renamed from: f */
    private final C1941ak f3837f;

    public C2206z(C2198t tVar) {
        this.f3832a = new C1960bb(tVar);
        this.f3833b = new C1959ba(tVar);
        this.f3834c = new C1956az(tVar);
        this.f3835d = new C1946ap(tVar);
        this.f3836e = new C1954ax(tVar);
        this.f3837f = new C1941ak(tVar);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public C2203x<C1936af> mo17173a(int i) {
        LinkedList linkedList = new LinkedList();
        switch (C22071.f3838a[C2208p.C2209a.m5885a(i).ordinal()]) {
            case 1:
                linkedList.add(this.f3836e);
                break;
            case 2:
                linkedList.add(this.f3836e);
                linkedList.add(this.f3835d);
                break;
            case 3:
                linkedList.add(this.f3832a);
                linkedList.add(this.f3833b);
                linkedList.add(this.f3834c);
                break;
            case 4:
            case 5:
                linkedList.add(this.f3835d);
                break;
            case 6:
                linkedList.add(this.f3837f);
                break;
        }
        return new C2202w(linkedList);
    }

    /* renamed from: com.yandex.metrica.impl.ob.z$1 */
    static /* synthetic */ class C22071 {

        /* renamed from: a */
        static final /* synthetic */ int[] f3838a;

        /* JADX WARNING: Can't wrap try/catch for region: R(14:0|1|2|3|4|5|6|7|8|9|10|11|12|14) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x003e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                com.yandex.metrica.impl.p$a[] r0 = com.yandex.metrica.impl.C2208p.C2209a.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                f3838a = r0
                com.yandex.metrica.impl.p$a r1 = com.yandex.metrica.impl.C2208p.C2209a.EVENT_TYPE_ACTIVITY_START_DEPRECATED     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = f3838a     // Catch:{ NoSuchFieldError -> 0x001d }
                com.yandex.metrica.impl.p$a r1 = com.yandex.metrica.impl.C2208p.C2209a.EVENT_TYPE_START     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = f3838a     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.yandex.metrica.impl.p$a r1 = com.yandex.metrica.impl.C2208p.C2209a.EVENT_TYPE_SESSION_START_MANUALLY     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = f3838a     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.yandex.metrica.impl.p$a r1 = com.yandex.metrica.impl.C2208p.C2209a.EVENT_TYPE_INIT     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = f3838a     // Catch:{ NoSuchFieldError -> 0x003e }
                com.yandex.metrica.impl.p$a r1 = com.yandex.metrica.impl.C2208p.C2209a.EVENT_TYPE_INIT_BACKGROUND     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = f3838a     // Catch:{ NoSuchFieldError -> 0x0049 }
                com.yandex.metrica.impl.p$a r1 = com.yandex.metrica.impl.C2208p.C2209a.EVENT_TYPE_ACTIVITY_END     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.yandex.metrica.impl.p050ob.C2206z.C22071.<clinit>():void");
        }
    }
}

package com.tappx.p048a;

import android.content.Context;
import com.tappx.p048a.C1484k1;
import com.tappx.p048a.C1517m0;
import com.tappx.p048a.C1529n;
import com.tappx.p048a.C1620t0;
import com.tappx.p048a.C1707z0;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.tappx.a.b */
public class C1321b {

    /* renamed from: b */
    private static volatile C1321b f1618b;

    /* renamed from: a */
    private final Context f1619a;

    private C1321b(Context context) {
        this.f1619a = context;
    }

    /* renamed from: a */
    public static C1321b m2239a(Context context) {
        C1321b bVar = f1618b;
        if (bVar == null) {
            synchronized (C1321b.class) {
                bVar = f1618b;
                if (bVar == null) {
                    f1618b = new C1321b(context.getApplicationContext());
                    C1321b bVar2 = f1618b;
                    return bVar2;
                }
            }
        }
        return bVar;
    }

    /* renamed from: e */
    private List<C1517m0.C1519b> m2240e() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new C1707z0.C1708a(mo15538d()));
        arrayList.add(new C1484k1.C1487c(mo15538d(), mo15537c().mo15587l()));
        arrayList.add(new C1620t0.C1622b(mo15538d()));
        return arrayList;
    }

    /* renamed from: b */
    public C1531n0 mo15536b() {
        return new C1548o0(m2240e());
    }

    /* access modifiers changed from: package-private */
    /* renamed from: c */
    public C1332c mo15537c() {
        return C1332c.m2291a(this.f1619a);
    }

    /* renamed from: d */
    public C1624t2 mo15538d() {
        return mo15537c().mo15580e();
    }

    /* renamed from: a */
    public C1410f3 mo15535a() {
        return new C1410f3(C1529n.C1530a.f2094e, C1529n.C1530a.f2095f, C1529n.C1530a.f2096g);
    }
}

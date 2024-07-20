package com.tappx.p048a;

import android.content.Context;
import com.tappx.p048a.C1323b1;
import com.tappx.p048a.C1361d1;
import com.tappx.p048a.C1502l1;
import com.tappx.p048a.C1529n;
import com.tappx.p048a.C1637u0;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.tappx.a.i */
public class C1450i {

    /* renamed from: b */
    private static volatile C1450i f1926b;

    /* renamed from: a */
    private final C1332c f1927a;

    public C1450i(Context context) {
        this.f1927a = C1332c.m2291a(context);
    }

    /* renamed from: a */
    public static C1450i m2819a(Context context) {
        C1450i iVar = f1926b;
        if (iVar == null) {
            synchronized (C1450i.class) {
                iVar = f1926b;
                if (iVar == null) {
                    iVar = new C1450i(context);
                }
            }
        }
        return iVar;
    }

    /* renamed from: c */
    private C1649v m2820c() {
        return this.f1927a.mo15587l();
    }

    /* renamed from: d */
    private C1624t2 m2821d() {
        return this.f1927a.mo15580e();
    }

    /* renamed from: e */
    private List<C1361d1.C1362a> m2822e() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new C1323b1.C1324a(m2821d()));
        arrayList.add(new C1502l1.C1505c(m2821d(), m2820c()));
        arrayList.add(new C1637u0.C1639b(m2821d()));
        return arrayList;
    }

    /* renamed from: b */
    public C1388e1 mo15855b() {
        return new C1404f1(m2822e());
    }

    /* renamed from: a */
    public C1410f3 mo15854a() {
        return new C1410f3(C1529n.C1530a.f2097h, C1529n.C1530a.f2098i, C1529n.C1530a.f2099j);
    }
}

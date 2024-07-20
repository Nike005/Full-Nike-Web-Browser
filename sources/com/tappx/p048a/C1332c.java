package com.tappx.p048a;

import android.content.Context;
import android.content.SharedPreferences;
import com.tappx.p048a.C1419g1;
import com.tappx.p048a.C1454i1;
import com.tappx.p048a.C1471j1;
import com.tappx.p048a.C1498l;
import com.tappx.p048a.C1500l0;
import com.tappx.p048a.C1522m1;
import com.tappx.p048a.C1559p0;
import com.tappx.p048a.C1580q;
import com.tappx.p048a.C1604s;
import com.tappx.p048a.C1634u;
import com.tappx.p048a.C1694y;

/* renamed from: com.tappx.a.c */
public class C1332c {

    /* renamed from: f */
    private static volatile C1332c f1640f;
    /* access modifiers changed from: private */

    /* renamed from: a */
    public final Context f1641a;

    /* renamed from: b */
    private final C1498l<C1322b0> f1642b = new C1498l<>(new C1333a());

    /* renamed from: c */
    private final C1498l<C1624t2> f1643c = new C1498l<>(new C1334b());

    /* renamed from: d */
    private final C1498l<C1649v> f1644d = new C1498l<>(new C1335c());

    /* renamed from: e */
    private final C1498l<C1365d2> f1645e = new C1498l<>(new C1336d());

    /* renamed from: com.tappx.a.c$a */
    class C1333a implements C1498l.C1499a<C1322b0> {
        C1333a() {
        }

        /* renamed from: a */
        public C1322b0 m2322a() {
            return new C1431h0(C1332c.this.f1641a);
        }
    }

    /* renamed from: com.tappx.a.c$b */
    class C1334b implements C1498l.C1499a<C1624t2> {
        C1334b() {
        }

        /* renamed from: a */
        public C1624t2 m2324a() {
            return new C1641u2(C1332c.this.m2298q());
        }
    }

    /* renamed from: com.tappx.a.c$c */
    class C1335c implements C1498l.C1499a<C1649v> {
        C1335c() {
        }

        /* renamed from: a */
        public C1649v m2326a() {
            return new C1661w(C1332c.this.m2298q(), C1332c.this.mo15589n(), C1332c.this.m2303v(), C1332c.this.m2302u(), C1332c.this.mo15584i(), C1332c.this.m2297p());
        }
    }

    /* renamed from: com.tappx.a.c$d */
    class C1336d implements C1498l.C1499a<C1365d2> {
        C1336d() {
        }

        /* renamed from: a */
        public C1365d2 m2328a() {
            return new C1365d2(C1332c.this.mo15588m(), C1332c.this.m2300s());
        }
    }

    public C1332c(Context context) {
        this.f1641a = context;
        m2305x();
    }

    /* access modifiers changed from: private */
    /* renamed from: p */
    public String m2297p() {
        return C1529n.m3048a();
    }

    /* access modifiers changed from: private */
    /* renamed from: q */
    public C1322b0 m2298q() {
        return this.f1642b.mo15926a();
    }

    /* renamed from: r */
    private C1365d2 m2299r() {
        return this.f1645e.mo15926a();
    }

    /* access modifiers changed from: private */
    /* renamed from: s */
    public C1536n2 m2300s() {
        return C1552o2.m3165a(mo15577b()).mo16036c();
    }

    /* renamed from: t */
    private C1471j1.C1472a m2301t() {
        return C1471j1.C1472a.m2877a(this.f1641a);
    }

    /* access modifiers changed from: private */
    /* renamed from: u */
    public C1580q.C1581a m2302u() {
        return new C1580q.C1581a(m2304w());
    }

    /* access modifiers changed from: private */
    /* renamed from: v */
    public C1604s.C1605a m2303v() {
        return new C1604s.C1605a(m2304w());
    }

    /* renamed from: w */
    private C1679x m2304w() {
        return new C1679x();
    }

    /* renamed from: x */
    private void m2305x() {
        new C1557p(this.f1641a).mo16042a();
    }

    /* renamed from: g */
    public C1432h1 mo15582g() {
        return new C1432h1(this.f1641a);
    }

    /* renamed from: h */
    public C1454i1.C1455a mo15583h() {
        return C1454i1.C1455a.m2837a(this.f1641a);
    }

    /* renamed from: i */
    public C1634u.C1636b mo15584i() {
        return new C1634u.C1636b(mo15586k(), new C1619t(mo15585j()), m2301t(), mo15578c());
    }

    /* renamed from: j */
    public SharedPreferences mo15585j() {
        return this.f1641a.getSharedPreferences("tappx", 0);
    }

    /* renamed from: k */
    public C1522m1.C1523a mo15586k() {
        return C1522m1.C1523a.m3023a(this.f1641a);
    }

    /* renamed from: l */
    public C1649v mo15587l() {
        return this.f1644d.mo15926a();
    }

    /* renamed from: m */
    public C1700y2 mo15588m() {
        return C1710z2.m3747a(this.f1641a);
    }

    /* renamed from: n */
    public C1694y.C1695a mo15589n() {
        return new C1694y.C1695a(m2304w(), mo15579d(), mo15586k(), mo15578c(), mo15583h(), mo15581f(), m2301t());
    }

    /* renamed from: o */
    public C1584q1 mo15590o() {
        return new C1593r1(mo15587l(), mo15576a(), m2299r());
    }

    /* renamed from: a */
    public static C1332c m2291a(Context context) {
        C1332c cVar = f1640f;
        if (cVar == null) {
            synchronized (C1332c.class) {
                cVar = f1640f;
                if (cVar == null) {
                    f1640f = new C1332c(context.getApplicationContext());
                    C1332c cVar2 = f1640f;
                    return cVar2;
                }
            }
        }
        return cVar;
    }

    /* renamed from: b */
    public Context mo15577b() {
        return this.f1641a;
    }

    /* renamed from: c */
    public C1500l0.C1501a mo15578c() {
        return C1500l0.C1501a.m2949a(this.f1641a);
    }

    /* renamed from: d */
    public C1559p0.C1561b mo15579d() {
        return C1559p0.C1561b.m3186a(this.f1641a);
    }

    /* renamed from: e */
    public C1624t2 mo15580e() {
        return this.f1643c.mo15926a();
    }

    /* renamed from: f */
    public C1419g1.C1421b mo15581f() {
        return C1419g1.C1421b.m2693a(this.f1641a);
    }

    /* renamed from: a */
    public C1482k0 mo15576a() {
        return new C1482k0();
    }
}

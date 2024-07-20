package com.tappx.p048a;

import com.tappx.p048a.C1401f0;

/* renamed from: com.tappx.a.u2 */
public class C1641u2 implements C1624t2 {
    /* access modifiers changed from: private */

    /* renamed from: a */
    public final C1322b0 f2355a;

    /* renamed from: com.tappx.a.u2$b */
    private final class C1643b implements C1401f0.C1402a {

        /* renamed from: a */
        private final C1655v2 f2356a;

        /* renamed from: b */
        private int f2357b;

        /* renamed from: a */
        public void mo15762a(C1309a0 a0Var) {
            int i = this.f2357b + 1;
            this.f2357b = i;
            if (i > 4) {
                C1467j0.m2873d("VVPuWC/9Kuu7F3i2uDo+EpXhKnuxQFx794EdWq4sqJx9G87i++pCpDIUbWEx83NA", Integer.valueOf(i - 1));
                return;
            }
            int a = m3468a(i);
            C1467j0.m2873d("nLLZ8hYKbSEKzUbM0u+Pir24N5Oaw+Lx+MoBG+cviQs", String.valueOf(a));
            C1641u2.this.f2355a.mo15540a(this.f2356a, a);
        }

        private C1643b(C1655v2 v2Var) {
            this.f2357b = 0;
            this.f2356a = v2Var;
        }

        /* renamed from: a */
        private int m3468a(int i) {
            return (int) (Math.pow(2.0d, (double) (((float) i) * 1.5f)) * 1000.0d);
        }
    }

    public C1641u2(C1322b0 b0Var) {
        this.f2355a = b0Var;
    }

    /* renamed from: a */
    public void mo16176a(String str) {
        if (str != null) {
            C1467j0.m2873d("0fBLEtCaOL9UAJMNctGOmg", str);
            C1655v2 v2Var = new C1655v2(str, (C1401f0.C1403b<Void>) null, (C1401f0.C1402a) null);
            v2Var.mo15650a((C1401f0.C1402a) new C1643b(v2Var));
            this.f2355a.mo15539a(v2Var);
        }
    }
}

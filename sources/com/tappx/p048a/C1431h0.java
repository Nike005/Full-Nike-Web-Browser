package com.tappx.p048a;

import android.content.Context;
import com.mopub.common.Constants;
import com.tappx.p048a.C1309a0;
import com.tappx.p048a.C1401f0;

/* renamed from: com.tappx.a.h0 */
public final class C1431h0 implements C1322b0 {

    /* renamed from: a */
    private final C1629t5 f1887a;

    /* renamed from: b */
    private final C1386e0 f1888b;

    public C1431h0(Context context) {
        this(C1479j6.m2894a(context), new C1386e0());
    }

    /* renamed from: a */
    public void mo15539a(C1358d0<?> d0Var) {
        if (!m2755a(d0Var.mo15660g())) {
            C1401f0.C1402a b = d0Var.mo15655b();
            if (b != null) {
                b.mo15762a(new C1309a0(C1309a0.C1310a.PARSE_ERROR));
                return;
            }
            return;
        }
        C1451i0 i0Var = new C1451i0(d0Var);
        i0Var.mo16146b((Object) d0Var);
        this.f1887a.mo16186a(i0Var);
    }

    /* renamed from: b */
    public void mo15541b(C1358d0 d0Var) {
        if (!this.f1888b.mo15731a((C1358d0<?>) d0Var)) {
            this.f1887a.mo16189a((Object) d0Var);
        }
    }

    C1431h0(C1629t5 t5Var, C1386e0 e0Var) {
        this.f1888b = e0Var;
        this.f1887a = t5Var;
        e0Var.mo15729a((C1322b0) this);
    }

    /* renamed from: a */
    private boolean m2755a(String str) {
        return str != null && str.startsWith(Constants.HTTP);
    }

    /* renamed from: a */
    public void mo15540a(C1358d0<?> d0Var, int i) {
        this.f1888b.mo15730a(d0Var, (long) i);
    }
}

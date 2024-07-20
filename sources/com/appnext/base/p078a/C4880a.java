package com.appnext.base.p078a;

import com.appnext.base.p078a.p081c.C4889a;
import com.appnext.base.p078a.p081c.C4890b;
import com.appnext.base.p078a.p081c.C4891c;

/* renamed from: com.appnext.base.a.a */
public class C4880a {

    /* renamed from: dw */
    private static volatile C4880a f4555dw;

    /* renamed from: dt */
    private C4889a f4556dt = new C4889a();

    /* renamed from: du */
    private C4890b f4557du = new C4890b();

    /* renamed from: dv */
    private C4891c f4558dv = new C4891c();

    /* renamed from: X */
    public static C4880a m6472X() {
        if (f4555dw == null) {
            synchronized (C4880a.class) {
                if (f4555dw == null) {
                    f4555dw = new C4880a();
                }
            }
        }
        return f4555dw;
    }

    private C4880a() {
    }

    /* renamed from: Y */
    private void m6473Y() {
        this.f4556dt = new C4889a();
        this.f4557du = new C4890b();
        this.f4558dv = new C4891c();
    }

    /* renamed from: Z */
    public final C4889a mo40940Z() {
        return this.f4556dt;
    }

    /* renamed from: aa */
    public final C4890b mo40941aa() {
        return this.f4557du;
    }

    /* renamed from: ab */
    public final C4891c mo40942ab() {
        return this.f4558dv;
    }
}

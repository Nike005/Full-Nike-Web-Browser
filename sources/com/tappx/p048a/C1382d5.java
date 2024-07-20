package com.tappx.p048a;

import android.content.Context;
import com.tappx.p048a.C1354c5;

/* renamed from: com.tappx.a.d5 */
public class C1382d5 implements C1354c5 {

    /* renamed from: a */
    private final C1329b5 f1759a;

    /* renamed from: b */
    private C1354c5.C1355a f1760b;

    /* renamed from: c */
    private Context f1761c;

    /* renamed from: d */
    private String f1762d;

    /* renamed from: e */
    private C1414f5 f1763e;

    /* renamed from: f */
    private int f1764f;

    public C1382d5() {
        this(new C1329b5());
    }

    /* renamed from: a */
    public void mo15722a(C1354c5.C1355a aVar) {
        this.f1760b = aVar;
    }

    /* renamed from: b */
    public void mo15723b() {
        C1717z4.m3785a(this.f1761c, this.f1762d, this.f1763e, this.f1764f);
    }

    C1382d5(C1329b5 b5Var) {
        this.f1764f = -1;
        this.f1759a = b5Var;
    }

    /* renamed from: a */
    public void mo15721a(Context context, String str, C1414f5 f5Var) {
        this.f1761c = context;
        this.f1762d = str;
        this.f1763e = f5Var;
        int a = C1317a5.m2228a(this.f1760b);
        this.f1764f = a;
        this.f1759a.mo15574a(a, this, context, str, this.f1760b, f5Var);
    }

    /* renamed from: a */
    public void mo15720a() {
        C1317a5.m2230b(this.f1764f);
        this.f1759a.mo15573a();
    }
}

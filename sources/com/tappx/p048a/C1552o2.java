package com.tappx.p048a;

import android.content.Context;
import com.tappx.sdk.android.TappxPrivacyManager;

/* renamed from: com.tappx.a.o2 */
public final class C1552o2 {

    /* renamed from: c */
    private static volatile C1552o2 f2146c;

    /* renamed from: a */
    private final Context f2147a;

    /* renamed from: b */
    private final C1524m2 f2148b = new C1524m2();

    private C1552o2(Context context) {
        this.f2147a = context;
    }

    /* renamed from: a */
    public static C1552o2 m3165a(Context context) {
        C1552o2 o2Var = f2146c;
        if (o2Var == null) {
            synchronized (C1552o2.class) {
                o2Var = f2146c;
                if (o2Var == null) {
                    f2146c = new C1552o2(context.getApplicationContext());
                    C1552o2 o2Var2 = f2146c;
                    return o2Var2;
                }
            }
        }
        return o2Var;
    }

    /* renamed from: d */
    private C1332c m3166d() {
        return C1332c.m2291a(this.f2147a);
    }

    /* renamed from: e */
    private C1649v m3167e() {
        return m3166d().mo15587l();
    }

    /* renamed from: f */
    private C1619t m3168f() {
        return new C1619t(m3166d().mo15585j());
    }

    /* renamed from: g */
    private C1456i2 m3169g() {
        return new C1456i2(m3167e());
    }

    /* renamed from: h */
    private C1586q2 m3170h() {
        return new C1586q2(m3166d().mo15585j());
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public C1524m2 mo16035b() {
        return this.f2148b;
    }

    /* renamed from: c */
    public C1536n2 mo16036c() {
        return new C1536n2(m3170h(), new C1488k2(m3167e(), m3168f()), mo16035b(), m3169g());
    }

    /* renamed from: a */
    public TappxPrivacyManager mo16034a() {
        return new C1597r2(mo16036c());
    }
}

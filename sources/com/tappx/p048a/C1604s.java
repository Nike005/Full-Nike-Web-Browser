package com.tappx.p048a;

import android.net.Uri;
import com.tappx.p048a.C1309a0;
import com.tappx.p048a.C1358d0;
import com.tappx.p048a.C1401f0;
import java.io.UnsupportedEncodingException;
import java.util.Map;

/* renamed from: com.tappx.a.s */
public class C1604s extends C1358d0<C1699y1> {

    /* renamed from: i */
    private static final String f2247i = C1400f.m2603b("7lAm3cGZz4MMRzfjNT4s5w");

    /* renamed from: f */
    private final String f2248f;

    /* renamed from: g */
    private final C1679x f2249g;

    /* renamed from: h */
    private final String f2250h;

    /* renamed from: com.tappx.a.s$a */
    public static class C1605a {

        /* renamed from: a */
        private final C1679x f2251a;

        public C1605a(C1679x xVar) {
            this.f2251a = xVar;
        }

        /* access modifiers changed from: package-private */
        /* renamed from: a */
        public C1604s mo16120a(String str, String str2, C1401f0.C1403b<C1699y1> bVar, C1401f0.C1402a aVar) {
            return new C1604s(this.f2251a, str, str2, bVar, aVar);
        }
    }

    C1604s(C1679x xVar, String str, String str2, C1401f0.C1403b<C1699y1> bVar, C1401f0.C1402a aVar) {
        super(bVar, aVar);
        this.f2249g = xVar;
        this.f2248f = str + C1529n.m3051d();
        this.f2250h = str2;
        mo15653a(false);
        mo15651a(new C1418g0(10000, 1, 1.0f));
    }

    /* renamed from: a */
    public byte[] mo15654a() {
        String str = this.f2250h;
        if (str == null) {
            return null;
        }
        try {
            return str.getBytes("utf-8");
        } catch (UnsupportedEncodingException unused) {
            C1467j0.m2873d("mcDfyrZIyDh7srkDi3vhYS4jCqm7NCw5DOnMQ6j4pn8", new Object[0]);
            return null;
        }
    }

    /* renamed from: c */
    public Map<String, String> mo15656c() {
        return mo15661h();
    }

    /* renamed from: d */
    public C1358d0.C1359a mo15657d() {
        return C1358d0.C1359a.POST;
    }

    /* renamed from: g */
    public String mo15660g() {
        Uri.Builder buildUpon = Uri.parse(this.f2248f).buildUpon();
        buildUpon.appendQueryParameter(f2247i, String.valueOf(System.currentTimeMillis() / 1000));
        return buildUpon.build().toString();
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public C1401f0<C1699y1> mo15648a(C1337c0 c0Var) {
        try {
            C1467j0.m2873d("zEfD4hGYqgGlREP0sIIg/hstxJ7mtJesC+XfeD//U5A", String.valueOf(c0Var.f1652c), this.f2248f);
            C1673w1 a = this.f2249g.mo16248a(c0Var);
            if (a.mo16243f()) {
                return C1401f0.m2605a(new C1309a0(C1309a0.C1310a.NO_FILL));
            }
            C1640u1 u1Var = a.mo16239b().get(0);
            if (u1Var instanceof C1699y1) {
                return C1401f0.m2606a((C1699y1) u1Var);
            }
            throw new C1340c2();
        } catch (C1340c2 unused) {
            return C1401f0.m2605a(new C1309a0(C1309a0.C1310a.PARSE_ERROR));
        }
    }
}

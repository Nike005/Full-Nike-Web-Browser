package com.tappx.p048a;

import android.net.Uri;
import com.tappx.p048a.C1309a0;
import com.tappx.p048a.C1358d0;
import com.tappx.p048a.C1401f0;
import java.util.Locale;
import java.util.Map;

/* renamed from: com.tappx.a.q */
public class C1580q extends C1358d0<C1473j2> {

    /* renamed from: h */
    private static final String f2222h = C1400f.m2603b("Atea2vjkWMaKJqXPDr3CPg");

    /* renamed from: f */
    private final String f2223f = C1529n.m3049b();

    /* renamed from: g */
    private final C1679x f2224g;

    /* renamed from: com.tappx.a.q$a */
    public static class C1581a {

        /* renamed from: a */
        private final C1679x f2225a;

        public C1581a(C1679x xVar) {
            this.f2225a = xVar;
        }

        /* access modifiers changed from: package-private */
        /* renamed from: a */
        public C1580q mo16068a(C1401f0.C1403b<C1473j2> bVar, C1401f0.C1402a aVar) {
            return new C1580q(this.f2225a, bVar, aVar);
        }
    }

    C1580q(C1679x xVar, C1401f0.C1403b<C1473j2> bVar, C1401f0.C1402a aVar) {
        super(bVar, aVar);
        this.f2224g = xVar;
        mo15653a(true);
        mo15651a(new C1418g0(10000, 1, 1.0f));
    }

    /* renamed from: j */
    private String m3238j() {
        Locale locale = Locale.getDefault();
        if (locale == null) {
            return "ES";
        }
        return locale.getLanguage().toUpperCase(Locale.US);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public C1401f0<C1473j2> mo15648a(C1337c0 c0Var) {
        try {
            return C1401f0.m2606a(this.f2224g.mo16249b(c0Var));
        } catch (C1340c2 unused) {
            return C1401f0.m2605a(new C1309a0(C1309a0.C1310a.PARSE_ERROR));
        }
    }

    /* renamed from: a */
    public byte[] mo15654a() {
        return null;
    }

    /* renamed from: c */
    public Map<String, String> mo15656c() {
        return mo15661h();
    }

    /* renamed from: d */
    public C1358d0.C1359a mo15657d() {
        return C1358d0.C1359a.GET;
    }

    /* renamed from: g */
    public String mo15660g() {
        Uri.Builder buildUpon = Uri.parse(this.f2223f).buildUpon();
        buildUpon.appendQueryParameter(f2222h, m3238j());
        return buildUpon.build().toString();
    }
}

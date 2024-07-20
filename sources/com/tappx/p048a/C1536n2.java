package com.tappx.p048a;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import com.tappx.p048a.C1456i2;
import com.tappx.p048a.C1488k2;
import com.tappx.sdk.android.PrivacyConsentActivity;
import java.lang.ref.WeakReference;
import java.util.concurrent.TimeUnit;

/* renamed from: com.tappx.a.n2 */
public class C1536n2 {

    /* renamed from: f */
    private static final long f2105f = TimeUnit.DAYS.toSeconds(365);
    /* access modifiers changed from: private */

    /* renamed from: a */
    public final C1586q2 f2106a;

    /* renamed from: b */
    private final C1488k2 f2107b;
    /* access modifiers changed from: private */

    /* renamed from: c */
    public final C1524m2 f2108c;

    /* renamed from: d */
    private final C1456i2 f2109d;
    /* access modifiers changed from: private */

    /* renamed from: e */
    public boolean f2110e;

    /* renamed from: com.tappx.a.n2$a */
    class C1537a implements Runnable {

        /* renamed from: a */
        final /* synthetic */ WeakReference f2111a;

        /* renamed from: b */
        final /* synthetic */ Runnable f2112b;

        C1537a(WeakReference weakReference, Runnable runnable) {
            this.f2111a = weakReference;
            this.f2112b = runnable;
        }

        public void run() {
            C1536n2.this.m3071a((WeakReference<Context>) this.f2111a);
            if (this.f2112b != null) {
                C1536n2.this.f2108c.mo15967a(this.f2112b);
            }
        }
    }

    /* renamed from: com.tappx.a.n2$d */
    class C1540d implements C1542f {

        /* renamed from: a */
        final /* synthetic */ Context f2118a;

        /* renamed from: b */
        final /* synthetic */ String f2119b;

        C1540d(Context context, String str) {
            this.f2118a = context;
            this.f2119b = str;
        }

        /* renamed from: a */
        public void mo15997a() {
        }

        /* renamed from: a */
        public void mo15998a(String str, String str2) {
            C1536n2.this.m3066a(this.f2118a, this.f2119b, str2);
        }

        /* renamed from: b */
        public void mo15999b() {
        }
    }

    /* renamed from: com.tappx.a.n2$e */
    class C1541e implements C1456i2.C1459c {
        C1541e() {
        }

        /* renamed from: a */
        public void mo15869a(boolean z) {
            if (z) {
                C1536n2.this.f2106a.mo16087b(false);
            }
            boolean unused = C1536n2.this.f2110e = false;
        }
    }

    /* renamed from: com.tappx.a.n2$f */
    public interface C1542f {
        /* renamed from: a */
        void mo15997a();

        /* renamed from: a */
        void mo15998a(String str, String str2);

        /* renamed from: b */
        void mo15999b();
    }

    C1536n2(C1586q2 q2Var, C1488k2 k2Var, C1524m2 m2Var, C1456i2 i2Var) {
        this.f2106a = q2Var;
        this.f2107b = k2Var;
        this.f2108c = m2Var;
        this.f2109d = i2Var;
    }

    /* renamed from: c */
    private void m3075c(C1542f fVar) {
        boolean m = this.f2106a.mo16100m();
        Boolean d = this.f2106a.mo16091d();
        if (Boolean.FALSE.equals(d) && !m) {
            fVar.mo15999b();
        } else if (!Boolean.TRUE.equals(d) || m) {
            m3067a(fVar);
        } else {
            m3074b(fVar);
        }
    }

    /* renamed from: k */
    private void m3076k() {
        long e = this.f2106a.mo16092e();
        if (e > 0 && Math.abs(m3077l() - e) > f2105f) {
            this.f2106a.mo16085b();
        }
    }

    /* renamed from: l */
    private long m3077l() {
        return System.currentTimeMillis() / 1000;
    }

    /* renamed from: d */
    public void mo15989d() {
        this.f2106a.mo16089c(true);
    }

    /* renamed from: e */
    public boolean mo15990e() {
        return this.f2106a.mo16099l();
    }

    /* renamed from: f */
    public void mo15991f() {
        m3070a(C1566p2.DENIED_USER);
    }

    /* renamed from: g */
    public void mo15992g() {
        m3070a(C1566p2.GRANTED_USER);
    }

    /* renamed from: h */
    public void mo15993h() {
        this.f2106a.mo16077a();
        m3070a(C1566p2.DENIED_DEVELOPER);
    }

    /* renamed from: i */
    public void mo15994i() {
        this.f2106a.mo16077a();
        m3070a(C1566p2.GRANTED_DEVELOPER);
    }

    /* renamed from: j */
    public boolean mo15995j() {
        if (!Boolean.TRUE.equals(this.f2106a.mo16091d())) {
            return false;
        }
        return this.f2106a.mo16094g().mo16052b();
    }

    /* renamed from: com.tappx.a.n2$c */
    class C1539c implements C1488k2.C1491c {

        /* renamed from: a */
        final /* synthetic */ C1542f f2116a;

        C1539c(C1542f fVar) {
            this.f2116a = fVar;
        }

        /* renamed from: a */
        public void mo15907a(String str, String str2) {
            C1536n2.this.f2106a.mo16082a(true, str);
            this.f2116a.mo15998a(str, str2);
        }

        /* renamed from: b */
        public void mo15908b() {
            C1536n2.this.f2106a.mo16082a(false, (String) null);
            this.f2116a.mo15999b();
        }

        /* renamed from: a */
        public void mo15906a() {
            this.f2116a.mo15997a();
        }
    }

    /* renamed from: b */
    private void m3074b(C1542f fVar) {
        C1566p2 g = this.f2106a.mo16094g();
        String i = this.f2106a.mo16096i();
        if (g != C1566p2.MISSING_ANSWER) {
            fVar.mo15999b();
        } else if (i == null) {
            m3067a(fVar);
        } else {
            fVar.mo15998a(i, (String) null);
        }
    }

    /* renamed from: a */
    private void m3070a(C1566p2 p2Var) {
        if (this.f2106a.mo16094g() != p2Var) {
            this.f2106a.mo16080a(p2Var);
            this.f2106a.mo16089c(false);
            this.f2106a.mo16087b(true);
            this.f2106a.mo16079a(m3077l());
            mo15979a();
        }
    }

    /* renamed from: com.tappx.a.n2$b */
    class C1538b implements C1542f {

        /* renamed from: a */
        final /* synthetic */ WeakReference f2114a;

        C1538b(WeakReference weakReference) {
            this.f2114a = weakReference;
        }

        /* renamed from: a */
        public void mo15998a(String str, String str2) {
            Context context = (Context) this.f2114a.get();
            if (context == null) {
                C1536n2.this.f2108c.mo15966a();
            } else {
                C1536n2.this.m3066a(context, str, str2);
            }
        }

        /* renamed from: b */
        public void mo15999b() {
            C1536n2.this.f2108c.mo15966a();
        }

        /* renamed from: a */
        public void mo15997a() {
            C1536n2.this.f2108c.mo15966a();
        }
    }

    /* renamed from: b */
    public void mo15987b(String str) {
        this.f2106a.mo16088c(str);
    }

    /* renamed from: a */
    public void mo15984a(boolean z) {
        this.f2106a.mo16084a(z);
    }

    /* renamed from: b */
    public String mo15986b() {
        String h = this.f2106a.mo16095h();
        if (h == null || h.length() <= 5) {
            return null;
        }
        return h;
    }

    /* renamed from: c */
    public C1609s2 mo15988c() {
        return new C1609s2(this.f2106a.mo16091d(), this.f2106a.mo16094g(), this.f2106a.mo16093f(), this.f2106a.mo16098k(), this.f2106a.mo16092e());
    }

    /* renamed from: a */
    public void mo15982a(Runnable runnable) {
        this.f2108c.mo15967a(runnable);
    }

    /* renamed from: a */
    public void mo15981a(Context context, Runnable runnable) {
        WeakReference weakReference = new WeakReference(context);
        m3076k();
        this.f2108c.mo15967a(new C1537a(weakReference, runnable));
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public void m3071a(WeakReference<Context> weakReference) {
        this.f2108c.mo15968b();
        m3075c(new C1538b(weakReference));
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public void m3066a(Context context, String str, String str2) {
        Intent a = C1506l2.m2970a(context, str, str2);
        if (!(context instanceof Activity)) {
            a.addFlags(268435456);
        }
        try {
            context.startActivity(a);
        } catch (Exception unused) {
            String name = PrivacyConsentActivity.class.getName();
            String simpleName = PrivacyConsentActivity.class.getSimpleName();
            C1467j0.m2871b(C1400f.m2603b("dfKcWOaG8KPoMfm5zts08Qlu05+R8BIzO3YcOMbimy7M7b66oYD1J20myZSpOoOWRYcUsjDmTjtwSPWh2TgTXA"), name, simpleName);
        }
    }

    /* renamed from: a */
    private void m3067a(C1542f fVar) {
        this.f2107b.mo15903a((C1488k2.C1491c) new C1539c(fVar));
    }

    /* renamed from: a */
    public void mo15980a(Context context) {
        Boolean d = this.f2106a.mo16091d();
        boolean equals = Boolean.TRUE.equals(d);
        String i = this.f2106a.mo16096i();
        if (equals && i != null) {
            m3066a(context, i, (String) null);
        } else if (!Boolean.FALSE.equals(d)) {
            m3067a((C1542f) new C1540d(context, i));
        }
    }

    /* renamed from: a */
    public void mo15983a(String str) {
        this.f2106a.mo16086b(str);
    }

    /* renamed from: a */
    public void mo15985a(boolean z, int i, String str) {
        this.f2106a.mo16081a(Boolean.valueOf(z));
        if (str != null) {
            this.f2106a.mo16083a(str);
        }
        if (this.f2106a.mo16097j() != i) {
            this.f2106a.mo16078a(i);
            mo15989d();
        }
    }

    /* renamed from: a */
    public void mo15979a() {
        C1566p2 g;
        if (!this.f2110e && this.f2106a.mo16090c() && (g = this.f2106a.mo16094g()) != C1566p2.MISSING_ANSWER) {
            this.f2110e = true;
            this.f2109d.mo15865a(g, Math.max(m3077l() - this.f2106a.mo16092e(), 0), new C1541e());
        }
    }
}

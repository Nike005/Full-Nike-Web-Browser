package com.tappx.p048a;

import android.content.Context;
import android.view.View;
import com.tappx.p048a.C1625t3;
import com.tappx.p048a.C1687x3;

/* renamed from: com.tappx.a.u3 */
public class C1644u3 implements C1625t3 {
    /* access modifiers changed from: private */

    /* renamed from: a */
    public C1625t3.C1627b f2359a;

    /* renamed from: b */
    private final Context f2360b;
    /* access modifiers changed from: private */

    /* renamed from: c */
    public C1687x3 f2361c;

    /* renamed from: d */
    private C1687x3.C1691d f2362d = new C1645a();

    /* renamed from: com.tappx.a.u3$a */
    class C1645a implements C1687x3.C1691d {
        C1645a() {
        }

        /* renamed from: a */
        public void mo16214a() {
            C1467j0.m2873d("EO6JnLxOUsi6kIdAfPMA//Kib626NzkhHKkXIfYGxxc", new Object[0]);
            if (C1644u3.this.f2359a != null) {
                C1644u3.this.f2359a.mo15526d();
            }
        }

        /* renamed from: b */
        public void mo16215b() {
            if (C1644u3.this.f2359a != null) {
                C1644u3.this.f2359a.mo15524b();
            }
        }

        /* renamed from: c */
        public void mo16216c() {
            C1467j0.m2873d("xhf99ytwwl8bVeOsPAy3pg", new Object[0]);
            if (C1644u3.this.f2359a != null) {
                C1644u3.this.f2359a.mo15522a((View) C1644u3.this.f2361c);
            }
        }
    }

    C1644u3(Context context) {
        this.f2360b = context;
    }

    /* renamed from: a */
    public void mo16178a() {
    }

    /* renamed from: a */
    public void mo16180a(boolean z) {
    }

    public void destroy() {
        C1687x3 x3Var = this.f2361c;
        if (x3Var != null) {
            x3Var.setListener((C1687x3.C1691d) null);
            this.f2361c.destroy();
            this.f2361c = null;
        }
    }

    /* renamed from: a */
    public void mo16179a(C1625t3.C1627b bVar) {
        this.f2359a = bVar;
    }

    /* renamed from: a */
    public View mo16177a(C1328b4 b4Var, String str, C1625t3.C1626a aVar) {
        C1467j0.m2873d("h0fTNqXwKZ+DG4kdf/GC5w", new Object[0]);
        if (this.f2361c == null) {
            C1687x3 x3Var = new C1687x3(this.f2360b, m3471a(aVar));
            this.f2361c = x3Var;
            x3Var.setListener(this.f2362d);
            if (b4Var == C1328b4.INLINE || C1547o.f2135a) {
                this.f2361c.mo15939a();
            }
            this.f2361c.mo16263a(str);
        }
        return this.f2361c;
    }

    /* renamed from: a */
    private boolean m3471a(C1625t3.C1626a aVar) {
        if (aVar == null) {
            return false;
        }
        return aVar.mo16183a();
    }
}

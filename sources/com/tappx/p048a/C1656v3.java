package com.tappx.p048a;

import android.content.Context;
import android.view.View;
import com.tappx.p048a.C1367d4;
import com.tappx.p048a.C1625t3;

/* renamed from: com.tappx.a.v3 */
public class C1656v3 implements C1625t3 {
    /* access modifiers changed from: private */

    /* renamed from: a */
    public C1625t3.C1627b f2378a;

    /* renamed from: b */
    private final Context f2379b;

    /* renamed from: c */
    private C1367d4 f2380c;

    /* renamed from: d */
    private C1367d4.C1375h f2381d = new C1657a();

    /* renamed from: e */
    private C1367d4.C1381k f2382e = new C1658b();

    /* renamed from: com.tappx.a.v3$b */
    class C1658b implements C1367d4.C1381k {
        C1658b() {
        }

        /* renamed from: a */
        public void mo15719a(boolean z) {
            if (C1656v3.this.f2378a != null) {
                C1656v3.this.f2378a.mo15523a(z);
            }
        }
    }

    C1656v3(Context context) {
        this.f2379b = context;
    }

    public void destroy() {
        C1367d4 d4Var = this.f2380c;
        if (d4Var != null) {
            d4Var.mo15682a((C1367d4.C1375h) null);
            this.f2380c.mo15691b();
            this.f2380c = null;
        }
    }

    /* renamed from: com.tappx.a.v3$a */
    class C1657a implements C1367d4.C1375h {
        C1657a() {
        }

        /* renamed from: a */
        public void mo15706a(View view) {
            C1467j0.m2873d("v5lNHAiXCIZ1hAylTNDUIT+qLa9pGoGxoSFqUJi0Wwg", new Object[0]);
            if (C1656v3.this.f2378a != null) {
                C1656v3.this.f2378a.mo15522a(view);
            }
        }

        /* renamed from: b */
        public void mo15707b() {
            if (C1656v3.this.f2378a != null) {
                C1656v3.this.f2378a.mo15524b();
            }
        }

        /* renamed from: c */
        public void mo15708c() {
            if (C1656v3.this.f2378a != null) {
                C1656v3.this.f2378a.mo15525c();
            }
        }

        /* renamed from: d */
        public void mo15709d() {
            C1467j0.m2873d("ZJVkXnYZGc0zgB3S4AsbuD81KHR8Nkg8UponZZuzRBk", new Object[0]);
            if (C1656v3.this.f2378a != null) {
                C1656v3.this.f2378a.mo15526d();
            }
        }

        /* renamed from: a */
        public void mo15705a() {
            if (C1656v3.this.f2378a != null) {
                C1656v3.this.f2378a.mo15521a();
            }
        }
    }

    /* renamed from: a */
    public void mo16179a(C1625t3.C1627b bVar) {
        this.f2378a = bVar;
    }

    /* renamed from: a */
    public View mo16177a(C1328b4 b4Var, String str, C1625t3.C1626a aVar) {
        C1467j0.m2873d("3ZJsjFJl8424bBJ0FHBsPsvg6JPdFtnXjH4FLENWtoY", new Object[0]);
        if (this.f2380c == null) {
            C1367d4 d4Var = new C1367d4(this.f2379b, b4Var);
            this.f2380c = d4Var;
            d4Var.mo15682a(this.f2381d);
            this.f2380c.mo15684a(str);
            this.f2380c.mo15683a(this.f2382e);
        }
        return this.f2380c.mo15694c();
    }

    /* renamed from: a */
    public void mo16180a(boolean z) {
        C1367d4 d4Var = this.f2380c;
        if (d4Var != null) {
            d4Var.mo15693b(z);
        }
    }

    /* renamed from: a */
    public void mo16178a() {
        C1367d4 d4Var = this.f2380c;
        if (d4Var != null) {
            d4Var.mo15698g();
        }
    }
}

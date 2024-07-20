package com.tappx.p048a;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import com.tappx.p048a.C1517m0;
import com.tappx.p048a.C1625t3;

/* renamed from: com.tappx.a.a1 */
public class C1311a1 {

    /* renamed from: a */
    private final Context f1586a;
    /* access modifiers changed from: private */

    /* renamed from: b */
    public int f1587b;
    /* access modifiers changed from: private */

    /* renamed from: c */
    public int f1588c;

    /* renamed from: d */
    private C1625t3 f1589d;
    /* access modifiers changed from: private */

    /* renamed from: e */
    public C1517m0.C1520c f1590e;

    /* renamed from: f */
    private C1625t3.C1627b f1591f = new C1312a();

    public C1311a1(Context context) {
        this.f1586a = context;
    }

    /* renamed from: com.tappx.a.a1$a */
    class C1312a implements C1625t3.C1627b {
        C1312a() {
        }

        /* renamed from: a */
        public void mo15522a(View view) {
            view.setLayoutParams(new ViewGroup.LayoutParams(C1311a1.this.f1587b, C1311a1.this.f1588c));
            C1311a1.this.f1590e.mo15959a(view);
        }

        /* renamed from: a */
        public void mo15523a(boolean z) {
        }

        /* renamed from: b */
        public void mo15524b() {
            C1311a1.this.f1590e.mo15963d();
        }

        /* renamed from: c */
        public void mo15525c() {
            C1311a1.this.f1590e.mo15962c();
        }

        /* renamed from: d */
        public void mo15526d() {
            C1311a1.this.f1590e.mo15960a(C1313a2.UNSPECIFIED);
        }

        /* renamed from: a */
        public void mo15521a() {
            C1311a1.this.f1590e.mo15961b();
        }
    }

    /* renamed from: a */
    public void mo15519a() {
        C1625t3 t3Var = this.f1589d;
        if (t3Var != null) {
            t3Var.destroy();
        }
    }

    /* renamed from: a */
    public void mo15520a(C1699y1 y1Var, C1517m0.C1520c cVar) {
        this.f1590e = cVar;
        String h = y1Var.mo16280h();
        C1625t3 a = C1675w3.m3547a(this.f1586a, h);
        this.f1589d = a;
        a.mo16179a(this.f1591f);
        this.f1589d.mo16177a(C1328b4.INLINE, h, (C1625t3.C1626a) null);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) this.f1586a.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
        int l = y1Var.mo16284l();
        int j = y1Var.mo16282j();
        this.f1587b = (int) TypedValue.applyDimension(1, (float) l, displayMetrics);
        this.f1588c = (int) TypedValue.applyDimension(1, (float) j, displayMetrics);
    }
}

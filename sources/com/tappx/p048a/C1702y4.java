package com.tappx.p048a;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.animation.Animation;
import android.widget.FrameLayout;
import com.tappx.p048a.C1354c5;
import com.tappx.p048a.C1394e5;
import com.tappx.p048a.C1568p3;
import com.tappx.p048a.C1625t3;

/* renamed from: com.tappx.a.y4 */
public final class C1702y4 {
    /* access modifiers changed from: private */

    /* renamed from: a */
    public final Activity f2564a;

    /* renamed from: b */
    private final C1536n2 f2565b;

    /* renamed from: c */
    private final C1394e5 f2566c;
    /* access modifiers changed from: private */

    /* renamed from: d */
    public C1354c5.C1355a f2567d;

    /* renamed from: e */
    private C1414f5 f2568e;

    /* renamed from: f */
    private C1625t3 f2569f;

    /* renamed from: g */
    private C1568p3 f2570g;

    /* renamed from: h */
    private int f2571h;

    /* renamed from: i */
    private C1625t3.C1627b f2572i;

    /* renamed from: com.tappx.a.y4$a */
    class C1703a implements C1568p3.C1574f {
        C1703a() {
        }

        /* renamed from: a */
        public void mo15700a() {
            C1702y4.this.f2564a.finish();
        }
    }

    /* renamed from: com.tappx.a.y4$b */
    class C1704b implements C1625t3.C1627b {
        C1704b() {
        }

        /* renamed from: a */
        public void mo15521a() {
            C1702y4.this.f2564a.finish();
        }

        /* renamed from: a */
        public void mo15522a(View view) {
        }

        /* renamed from: b */
        public void mo15524b() {
            if (C1702y4.this.f2567d != null) {
                C1702y4.this.f2567d.mo15598c();
            }
        }

        /* renamed from: c */
        public void mo15525c() {
        }

        /* renamed from: d */
        public void mo15526d() {
            if (C1702y4.this.f2567d != null) {
                C1702y4.this.f2567d.mo15596a();
            }
            C1702y4.this.f2564a.finish();
        }

        /* renamed from: a */
        public void mo15523a(boolean z) {
            if (z) {
                C1702y4.this.m3720j();
            } else {
                C1702y4.this.m3722l();
            }
        }
    }

    protected C1702y4(Activity activity, C1536n2 n2Var, C1394e5 e5Var) {
        this.f2572i = new C1704b();
        this.f2564a = activity;
        this.f2565b = n2Var;
        this.f2566c = e5Var;
    }

    /* renamed from: e */
    private void m3715e() {
        if (this.f2565b.mo15995j()) {
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2, 83);
            this.f2570g.addView(m3719i(), layoutParams);
        }
    }

    /* renamed from: f */
    private View m3716f() {
        View h = m3718h();
        C1568p3 p3Var = new C1568p3(this.f2564a);
        this.f2570g = p3Var;
        p3Var.setCloseListener(new C1703a());
        FrameLayout.LayoutParams g = m3717g();
        g.gravity = 17;
        h.setLayoutParams(g);
        this.f2570g.mo16055a(h, g);
        this.f2570g.mo16054a(this.f2568e.mo15803c(), this.f2568e.mo15811h());
        m3715e();
        m3710a(this.f2570g, this.f2568e.mo15802b());
        return this.f2570g;
    }

    /* renamed from: g */
    private FrameLayout.LayoutParams m3717g() {
        int i;
        int d;
        Display defaultDisplay = this.f2564a.getWindowManager().getDefaultDisplay();
        int e = this.f2568e.mo15808e();
        int width = defaultDisplay.getWidth();
        int i2 = -1;
        if (e <= 0 || (i = C1588q3.m3288d((float) e, this.f2564a)) > width) {
            i = -1;
        }
        int d2 = this.f2568e.mo15806d();
        int height = defaultDisplay.getHeight();
        if (d2 > 0 && (d = C1588q3.m3288d((float) d2, this.f2564a)) <= height) {
            i2 = d;
        }
        return new FrameLayout.LayoutParams(i, i2);
    }

    /* renamed from: h */
    private View m3718h() {
        String a = C1701y3.m3706a(this.f2564a.getIntent());
        if (a == null) {
            this.f2564a.finish();
            return new View(this.f2564a);
        }
        C1625t3 a2 = m3709a(a);
        this.f2569f = a2;
        a2.mo16179a(this.f2572i);
        return this.f2569f.mo16177a(C1328b4.INTERSTITIAL, a, new C1625t3.C1626a().mo16182a(this.f2568e.mo15810g()));
    }

    /* renamed from: i */
    private View m3719i() {
        return C1460i3.m2854c(this.f2564a);
    }

    /* access modifiers changed from: private */
    /* renamed from: j */
    public void m3720j() {
        this.f2570g.setCloseEnabled(false);
    }

    /* renamed from: k */
    private void m3721k() {
        this.f2564a.getWindow().setBackgroundDrawable(new ColorDrawable(this.f2568e.mo15809f() ? C1529n.f2087b : -16777216));
    }

    /* access modifiers changed from: private */
    /* renamed from: l */
    public void m3722l() {
        this.f2570g.setCloseEnabled(true);
    }

    /* renamed from: a */
    public void mo16291a(Bundle bundle) {
        int intExtra = this.f2564a.getIntent().getIntExtra("aavc_otZMuRlffpTHI9DsaLyI", -1);
        this.f2571h = intExtra;
        C1354c5.C1355a a = C1317a5.m2229a(intExtra);
        this.f2567d = a;
        if (a != null) {
            a.mo15597b();
        }
        C1414f5 f5Var = (C1414f5) this.f2564a.getIntent().getParcelableExtra("aavc_fagZVUC6pOQOxawVwpVy");
        this.f2568e = f5Var;
        if (f5Var == null) {
            this.f2564a.finish();
            return;
        }
        m3721k();
        this.f2564a.requestWindowFeature(1);
        this.f2564a.getWindow().addFlags(1024);
        m3711a(this.f2568e.mo15799a());
        this.f2564a.setContentView(m3716f());
    }

    /* renamed from: b */
    public void mo16292b() {
        C1625t3 t3Var = this.f2569f;
        if (t3Var != null) {
            t3Var.mo16180a(this.f2564a.isFinishing());
        }
    }

    /* renamed from: c */
    public void mo16293c() {
        C1625t3 t3Var = this.f2569f;
        if (t3Var != null) {
            t3Var.mo16178a();
        }
    }

    /* renamed from: d */
    public boolean mo16294d() {
        return this.f2570g.mo16056a();
    }

    /* renamed from: a */
    private void m3711a(C1474j3 j3Var) {
        if (j3Var != null && j3Var != C1474j3.ANY) {
            C1588q3.m3284a(this.f2564a, j3Var);
        }
    }

    /* renamed from: a */
    private void m3710a(View view, C1492k3 k3Var) {
        Animation a = C1525m3.m3034a(k3Var);
        if (a != null) {
            view.startAnimation(a);
        }
    }

    /* renamed from: a */
    private C1625t3 m3709a(String str) {
        C1394e5.C1395a a = this.f2566c.mo15743a(this.f2571h);
        if (a != null) {
            return a.mo15745a();
        }
        C1625t3 a2 = C1675w3.m3547a(this.f2564a, str);
        a2.mo16177a(C1328b4.INTERSTITIAL, str, new C1625t3.C1626a().mo16182a(this.f2568e.mo15810g()));
        return a2;
    }

    /* renamed from: a */
    public void mo16290a() {
        C1625t3 t3Var = this.f2569f;
        if (t3Var != null) {
            t3Var.destroy();
        }
        this.f2570g.removeAllViews();
        C1354c5.C1355a aVar = this.f2567d;
        if (aVar != null) {
            aVar.mo15599d();
        }
        this.f2567d = null;
    }

    public C1702y4(Activity activity) {
        this(activity, C1552o2.m3165a(activity).mo16036c(), C1394e5.m2567a());
    }
}

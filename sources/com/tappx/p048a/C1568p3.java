package com.tappx.p048a;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.StateListDrawable;
import android.util.StateSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.tappx.p048a.C1610s3;

/* renamed from: com.tappx.a.p3 */
public class C1568p3 extends FrameLayout {

    /* renamed from: a */
    private final C1610s3 f2189a;
    /* access modifiers changed from: private */

    /* renamed from: b */
    public boolean f2190b;

    /* renamed from: c */
    private boolean f2191c;

    /* renamed from: d */
    private StateListDrawable f2192d;

    /* renamed from: e */
    private C1573e f2193e;

    /* renamed from: f */
    private C1572d f2194f;

    /* renamed from: g */
    private C1574f f2195g;
    /* access modifiers changed from: private */

    /* renamed from: h */
    public TextView f2196h;

    /* renamed from: i */
    private boolean f2197i;

    /* renamed from: j */
    private boolean f2198j;

    /* renamed from: k */
    private final C1610s3.C1612b f2199k;

    /* renamed from: l */
    private View.OnClickListener f2200l;

    /* renamed from: com.tappx.a.p3$a */
    class C1569a implements C1610s3.C1612b {
        C1569a() {
        }

        /* renamed from: a */
        public void mo16062a(int i) {
            String str;
            boolean z = true;
            boolean z2 = i <= 0;
            if (!z2 && C1568p3.this.f2190b) {
                z = false;
            }
            C1568p3.this.setCloseVisible(z);
            if (z2) {
                str = null;
            } else {
                str = String.valueOf(i);
            }
            C1568p3.this.f2196h.setText(str);
            C1568p3.this.f2196h.setEnabled(z2);
        }
    }

    /* renamed from: com.tappx.a.p3$b */
    class C1570b implements View.OnClickListener {
        C1570b() {
        }

        public void onClick(View view) {
            C1568p3.this.m3218e();
        }
    }

    /* renamed from: com.tappx.a.p3$c */
    static /* synthetic */ class C1571c {

        /* renamed from: a */
        static final /* synthetic */ int[] f2203a;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|(3:5|6|8)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        static {
            /*
                com.tappx.a.p3$e[] r0 = com.tappx.p048a.C1568p3.C1573e.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                f2203a = r0
                com.tappx.a.p3$e r1 = com.tappx.p048a.C1568p3.C1573e.DISABLED     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = f2203a     // Catch:{ NoSuchFieldError -> 0x001d }
                com.tappx.a.p3$e r1 = com.tappx.p048a.C1568p3.C1573e.TRANSPARENT     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = f2203a     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.tappx.a.p3$e r1 = com.tappx.p048a.C1568p3.C1573e.VISIBLE     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.tappx.p048a.C1568p3.C1571c.<clinit>():void");
        }
    }

    /* renamed from: com.tappx.a.p3$d */
    public enum C1572d {
        TOP_LEFT(51),
        TOP_CENTER(49),
        TOP_RIGHT(53),
        CENTER(17),
        BOTTOM_LEFT(83),
        BOTTOM_CENTER(81),
        BOTTOM_RIGHT(85);
        

        /* renamed from: a */
        private final int f2212a;

        private C1572d(int i) {
            this.f2212a = i;
        }

        /* access modifiers changed from: package-private */
        /* renamed from: a */
        public int mo16064a() {
            return this.f2212a;
        }
    }

    /* renamed from: com.tappx.a.p3$e */
    private enum C1573e {
        VISIBLE,
        TRANSPARENT,
        DISABLED
    }

    /* renamed from: com.tappx.a.p3$f */
    public interface C1574f {
        /* renamed from: a */
        void mo15700a();
    }

    public C1568p3(Context context) {
        this(context, new C1610s3());
    }

    /* renamed from: d */
    private void m3217d() {
        this.f2196h = new TextView(getContext());
        StateListDrawable stateListDrawable = new StateListDrawable();
        this.f2192d = stateListDrawable;
        stateListDrawable.addState(FrameLayout.SELECTED_STATE_SET, C1393e4.INTERSTITIAL_CLOSE_BUTTON_PRESSED.mo15742a(getContext()));
        this.f2192d.addState(FrameLayout.ENABLED_STATE_SET, C1393e4.INTERSTITIAL_CLOSE_BUTTON_NORMAL.mo15742a(getContext()));
        this.f2192d.addState(StateSet.WILD_CARD, C1393e4.INTERSTITIAL_CLOSE_BUTTON_DISABLED.mo15742a(getContext()));
        this.f2196h.setBackgroundDrawable(this.f2192d);
        this.f2196h.setOnClickListener(this.f2200l);
        this.f2196h.setTextColor(-1);
        this.f2196h.setTypeface(Typeface.SANS_SERIF);
        this.f2196h.setTextSize(18.0f);
        this.f2196h.setGravity(17);
        m3215c();
    }

    /* access modifiers changed from: private */
    /* renamed from: e */
    public void m3218e() {
        if (this.f2189a.mo16130b()) {
            playSoundEffect(0);
            C1574f fVar = this.f2195g;
            if (fVar != null) {
                fVar.mo15700a();
            }
        }
    }

    /* renamed from: f */
    private void m3219f() {
        for (int i = 0; i < getChildCount(); i++) {
            View childAt = getChildAt(i);
            if (childAt != this.f2196h) {
                removeView(childAt);
            }
        }
    }

    /* renamed from: g */
    private void m3220g() {
        C1573e eVar;
        boolean z = this.f2197i && this.f2198j;
        boolean z2 = this.f2191c;
        if (!z) {
            eVar = C1573e.DISABLED;
        } else if (z2) {
            eVar = C1573e.TRANSPARENT;
        } else {
            eVar = C1573e.VISIBLE;
        }
        if (eVar != this.f2193e) {
            this.f2193e = eVar;
            m3211a(eVar);
        }
    }

    private FrameLayout.LayoutParams getCloseButtonLayoutParams() {
        int b = C1588q3.m3286b(10.0f, getContext());
        int b2 = C1588q3.m3286b(30.0f, getContext());
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(b2, b2, this.f2194f.mo16064a());
        layoutParams.setMargins(b, b, b, b);
        return layoutParams;
    }

    /* access modifiers changed from: private */
    public void setCloseVisible(boolean z) {
        this.f2198j = z;
        m3220g();
    }

    public void setCloseEnabled(boolean z) {
        this.f2197i = z;
        m3220g();
    }

    public void setCloseListener(C1574f fVar) {
        this.f2195g = fVar;
    }

    public void setClosePosition(C1572d dVar) {
        this.f2194f = dVar;
        this.f2196h.setLayoutParams(getCloseButtonLayoutParams());
    }

    public void setInvisibleClose(boolean z) {
        this.f2191c = z;
        m3220g();
    }

    public C1568p3(Context context, C1610s3 s3Var) {
        super(context);
        this.f2193e = C1573e.VISIBLE;
        this.f2194f = C1572d.TOP_RIGHT;
        this.f2197i = true;
        this.f2198j = true;
        this.f2199k = new C1569a();
        this.f2200l = new C1570b();
        this.f2189a = s3Var;
        s3Var.mo16129a(this.f2199k);
        m3217d();
    }

    /* renamed from: c */
    private void m3215c() {
        addView(this.f2196h, getCloseButtonLayoutParams());
    }

    /* renamed from: b */
    public boolean mo16057b() {
        return this.f2197i;
    }

    /* renamed from: a */
    public void mo16054a(int i, boolean z) {
        if (i > 0) {
            this.f2190b = z;
            this.f2189a.mo16128a((long) i);
        }
    }

    /* renamed from: a */
    private void m3211a(C1573e eVar) {
        int i = C1571c.f2203a[eVar.ordinal()];
        int i2 = 0;
        StateListDrawable stateListDrawable = null;
        if (i == 1) {
            i2 = 8;
        } else if (i != 2) {
            stateListDrawable = this.f2192d;
        }
        this.f2196h.setBackgroundDrawable(stateListDrawable);
        this.f2196h.setVisibility(i2);
    }

    /* renamed from: a */
    public void mo16055a(View view, FrameLayout.LayoutParams layoutParams) {
        m3219f();
        addView(view, 0, layoutParams);
    }

    /* renamed from: a */
    public boolean mo16056a() {
        return getVisibility() == 0 && this.f2189a.mo16130b();
    }
}

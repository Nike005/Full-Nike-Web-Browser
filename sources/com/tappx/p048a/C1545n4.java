package com.tappx.p048a;

import android.content.Context;
import android.graphics.Rect;

/* renamed from: com.tappx.a.n4 */
class C1545n4 {

    /* renamed from: a */
    private final Context f2126a;

    /* renamed from: b */
    private final Rect f2127b = new Rect();

    /* renamed from: c */
    private final Rect f2128c = new Rect();

    /* renamed from: d */
    private final Rect f2129d = new Rect();

    /* renamed from: e */
    private final Rect f2130e = new Rect();

    /* renamed from: f */
    private final Rect f2131f = new Rect();

    /* renamed from: g */
    private final Rect f2132g = new Rect();

    /* renamed from: h */
    private final Rect f2133h = new Rect();

    /* renamed from: i */
    private final Rect f2134i = new Rect();

    C1545n4(Context context, float f) {
        this.f2126a = context.getApplicationContext();
    }

    /* renamed from: a */
    private void m3125a(Rect rect, Rect rect2) {
        rect2.set(C1588q3.m3290f((float) rect.left, this.f2126a), C1588q3.m3290f((float) rect.top, this.f2126a), C1588q3.m3290f((float) rect.right, this.f2126a), C1588q3.m3290f((float) rect.bottom, this.f2126a));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public Rect mo16020b() {
        return this.f2132g;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: c */
    public void mo16023c(int i, int i2, int i3, int i4) {
        this.f2129d.set(i, i2, i3 + i, i4 + i2);
        m3125a(this.f2129d, this.f2130e);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: d */
    public Rect mo16024d() {
        return this.f2134i;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: e */
    public Rect mo16025e() {
        return this.f2129d;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: f */
    public Rect mo16026f() {
        return this.f2130e;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: g */
    public Rect mo16027g() {
        return this.f2128c;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public void mo16021b(int i, int i2, int i3, int i4) {
        this.f2133h.set(i, i2, i3 + i, i4 + i2);
        m3125a(this.f2133h, this.f2134i);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: c */
    public Rect mo16022c() {
        return this.f2133h;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo16018a(int i, int i2) {
        this.f2127b.set(0, 0, i, i2);
        m3125a(this.f2127b, this.f2128c);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo16019a(int i, int i2, int i3, int i4) {
        this.f2131f.set(i, i2, i3 + i, i4 + i2);
        m3125a(this.f2131f, this.f2132g);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public Rect mo16017a() {
        return this.f2131f;
    }
}

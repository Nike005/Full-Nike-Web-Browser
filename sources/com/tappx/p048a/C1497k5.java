package com.tappx.p048a;

/* renamed from: com.tappx.a.k5 */
public class C1497k5 implements C1678w5 {

    /* renamed from: a */
    private int f2019a;

    /* renamed from: b */
    private int f2020b;

    /* renamed from: c */
    private final int f2021c;

    /* renamed from: d */
    private final float f2022d;

    public C1497k5() {
        this(2500, 1, 1.0f);
    }

    /* renamed from: a */
    public int mo15922a() {
        return this.f2019a;
    }

    /* renamed from: b */
    public int mo15924b() {
        return this.f2020b;
    }

    /* access modifiers changed from: protected */
    /* renamed from: c */
    public boolean mo15925c() {
        return this.f2020b <= this.f2021c;
    }

    public C1497k5(int i, int i2, float f) {
        this.f2019a = i;
        this.f2021c = i2;
        this.f2022d = f;
    }

    /* renamed from: a */
    public void mo15923a(C1718z5 z5Var) {
        this.f2020b++;
        int i = this.f2019a;
        this.f2019a = i + ((int) (((float) i) * this.f2022d));
        if (!mo15925c()) {
            throw z5Var;
        }
    }
}

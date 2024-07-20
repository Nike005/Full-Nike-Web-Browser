package com.yandex.metrica.impl.p050ob;

/* renamed from: com.yandex.metrica.impl.ob.fw */
public class C2173fw {

    /* renamed from: a */
    private int f3749a;

    /* renamed from: b */
    private int f3750b;

    /* renamed from: c */
    private final int f3751c;

    /* renamed from: d */
    private final float f3752d;

    public C2173fw() {
        this(2500, 1, 1.0f);
    }

    public C2173fw(int i, int i2, float f) {
        this.f3749a = i;
        this.f3751c = i2;
        this.f3752d = f;
    }

    /* renamed from: a */
    public int mo17798a() {
        return this.f3749a;
    }

    /* renamed from: a */
    public void mo17799a(C2162fr frVar) throws C2162fr {
        this.f3750b++;
        int i = this.f3749a;
        this.f3749a = (int) (((float) i) + (((float) i) * this.f3752d));
        if (!mo17800b()) {
            throw frVar;
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public boolean mo17800b() {
        return this.f3750b <= this.f3751c;
    }
}

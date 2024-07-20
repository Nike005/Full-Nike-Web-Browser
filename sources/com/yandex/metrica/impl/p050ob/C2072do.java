package com.yandex.metrica.impl.p050ob;

/* renamed from: com.yandex.metrica.impl.ob.do */
public class C2072do {

    /* renamed from: a */
    protected int f3547a = 0;

    /* renamed from: b */
    private final int f3548b;

    /* renamed from: c */
    private boolean f3549c;

    public C2072do(int i) {
        this.f3548b = i;
    }

    /* renamed from: b */
    public boolean mo17609b() {
        return this.f3549c && this.f3547a < this.f3548b;
    }

    /* renamed from: a */
    public void mo17608a() {
        this.f3547a++;
        this.f3549c = false;
    }

    /* renamed from: c */
    public void mo17610c() {
        this.f3549c = true;
    }
}

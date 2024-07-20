package com.yandex.metrica.impl.p050ob;

import java.util.Random;

/* renamed from: com.yandex.metrica.impl.ob.dm */
public class C2070dm {

    /* renamed from: a */
    private int f3542a;

    /* renamed from: b */
    private int f3543b;

    /* renamed from: c */
    private Random f3544c;

    /* renamed from: d */
    private int f3545d;

    public C2070dm(int i) {
        if (i <= 0 || i > 31) {
            this.f3542a = 31;
        } else {
            this.f3542a = i;
        }
        this.f3544c = new Random();
    }

    /* renamed from: a */
    public int mo17607a() {
        int i = this.f3543b;
        if (i < this.f3542a) {
            int i2 = i + 1;
            this.f3543b = i2;
            this.f3545d = 1 << i2;
        }
        return this.f3544c.nextInt(this.f3545d);
    }
}

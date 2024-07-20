package com.tappx.p048a;

import java.util.ArrayList;
import java.util.List;

/* renamed from: com.tappx.a.m2 */
public class C1524m2 {

    /* renamed from: a */
    private final List<Runnable> f2078a = new ArrayList();

    /* renamed from: b */
    private boolean f2079b;

    /* renamed from: c */
    private void m3029c() {
        ArrayList<Runnable> arrayList;
        synchronized (this.f2078a) {
            arrayList = new ArrayList<>(this.f2078a);
            this.f2078a.clear();
        }
        for (Runnable run : arrayList) {
            run.run();
        }
    }

    /* renamed from: a */
    public void mo15966a() {
        this.f2079b = false;
        m3029c();
    }

    /* renamed from: b */
    public void mo15968b() {
        if (!this.f2079b) {
            this.f2079b = true;
        }
    }

    /* renamed from: a */
    public void mo15967a(Runnable runnable) {
        if (this.f2079b) {
            this.f2078a.add(runnable);
        } else {
            runnable.run();
        }
    }
}

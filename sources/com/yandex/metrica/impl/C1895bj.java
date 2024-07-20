package com.yandex.metrica.impl;

import android.content.ContentValues;
import com.yandex.metrica.impl.C1857at;
import com.yandex.metrica.impl.p050ob.C2198t;
import java.io.Closeable;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

/* renamed from: com.yandex.metrica.impl.bj */
public class C1895bj implements Closeable {

    /* renamed from: a */
    private C2198t f3119a;

    /* renamed from: b */
    private final C1877ba f3120b;

    /* renamed from: c */
    private final Object f3121c = new Object();

    /* renamed from: d */
    private final C1832aj f3122d;

    /* renamed from: e */
    private C1893bh f3123e;

    /* renamed from: f */
    private boolean f3124f = false;

    /* renamed from: g */
    private Runnable f3125g = new Runnable() {
        public void run() {
            C1895bj.this.mo17079a();
        }
    };

    public C1895bj(C2198t tVar, Executor executor) {
        this.f3119a = tVar;
        this.f3120b = tVar.mo17860h();
        C1832aj a = mo17077a(tVar, executor);
        this.f3122d = a;
        a.start();
        this.f3123e = mo17078a(this.f3119a);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public C1893bh mo17078a(C2198t tVar) {
        return new C1893bh(tVar);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public C1832aj mo17077a(C2198t tVar, Executor executor) {
        C1832aj ajVar = new C1832aj(executor, tVar.mo17865l());
        ajVar.setName("NetworkCore [" + tVar.mo17865l() + "]");
        return ajVar;
    }

    public void close() {
        synchronized (this.f3121c) {
            if (!this.f3124f) {
                m4631d();
                if (this.f3122d.isAlive()) {
                    this.f3122d.mo16805a();
                }
                this.f3124f = true;
            }
        }
    }

    /* renamed from: a */
    public void mo17079a() {
        synchronized (this.f3121c) {
            if (!this.f3124f) {
                synchronized (this.f3121c) {
                    if (!this.f3124f) {
                        if (this.f3123e.mo17075s()) {
                            C1893bh bhVar = new C1893bh(this.f3119a);
                            this.f3123e = bhVar;
                            this.f3122d.mo16806a((C1835ak) bhVar);
                        }
                        if (C1897bk.m4657b(this.f3120b.mo16969a())) {
                            m4630a(C1855as.m4316u(), (Long) -2L);
                            m4630a(C1857at.m4327A(), (Long) null);
                        }
                    }
                }
                m4631d();
            }
        }
    }

    /* renamed from: a */
    private void m4630a(C1857at.C1859a aVar, Long l) {
        List<ContentValues> a = this.f3119a.mo17862i().mo17267a(l);
        if (a.isEmpty()) {
            a.add(C1926l.f3194a);
        }
        for (ContentValues next : a) {
            try {
                this.f3122d.mo16806a((C1835ak) aVar.mo16868a(this.f3119a).mo17152a(next));
            } catch (Exception unused) {
                return;
            }
        }
    }

    /* renamed from: d */
    private void m4631d() {
        this.f3119a.mo17867n().removeCallbacks(this.f3125g);
    }

    /* renamed from: b */
    public void mo17080b() {
        synchronized (this.f3121c) {
            if (!this.f3124f) {
                m4631d();
                if (this.f3119a.mo17863j().mo16571b() > 0) {
                    this.f3119a.mo17867n().postDelayed(this.f3125g, TimeUnit.SECONDS.toMillis((long) this.f3119a.mo17863j().mo16571b()));
                }
            }
        }
    }

    /* renamed from: c */
    public void mo17081c() {
        synchronized (this.f3121c) {
            if (!this.f3124f && !this.f3122d.mo16807b(this.f3123e)) {
                this.f3123e.mo17074a(true);
                this.f3123e.mo17071a(0);
                C1893bh bhVar = new C1893bh(this.f3119a);
                this.f3123e = bhVar;
                this.f3122d.mo16806a((C1835ak) bhVar);
            }
        }
    }
}

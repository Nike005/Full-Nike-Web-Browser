package com.tappx.p048a;

import android.os.Handler;
import android.os.Looper;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: com.tappx.a.t5 */
public class C1629t5 {

    /* renamed from: a */
    private final AtomicInteger f2315a;

    /* renamed from: b */
    private final Set<C1615s5<?>> f2316b;

    /* renamed from: c */
    private final PriorityBlockingQueue<C1615s5<?>> f2317c;

    /* renamed from: d */
    private final PriorityBlockingQueue<C1615s5<?>> f2318d;

    /* renamed from: e */
    private final C1445h5 f2319e;

    /* renamed from: f */
    private final C1546n5 f2320f;

    /* renamed from: g */
    private final C1660v5 f2321g;

    /* renamed from: h */
    private final C1556o5[] f2322h;

    /* renamed from: i */
    private C1462i5 f2323i;

    /* renamed from: j */
    private final List<C1633d> f2324j;

    /* renamed from: k */
    private final List<C1631b> f2325k;

    /* renamed from: com.tappx.a.t5$a */
    class C1630a implements C1632c {

        /* renamed from: a */
        final /* synthetic */ Object f2326a;

        C1630a(C1629t5 t5Var, Object obj) {
            this.f2326a = obj;
        }

        /* renamed from: a */
        public boolean mo16193a(C1615s5<?> s5Var) {
            return s5Var.mo16160o() == this.f2326a;
        }
    }

    /* renamed from: com.tappx.a.t5$b */
    public interface C1631b {
        /* renamed from: a */
        void mo16194a(C1615s5<?> s5Var, int i);
    }

    /* renamed from: com.tappx.a.t5$c */
    public interface C1632c {
        /* renamed from: a */
        boolean mo16193a(C1615s5<?> s5Var);
    }

    @Deprecated
    /* renamed from: com.tappx.a.t5$d */
    public interface C1633d<T> {
        /* renamed from: a */
        void mo16195a(C1615s5<T> s5Var);
    }

    public C1629t5(C1445h5 h5Var, C1546n5 n5Var, int i, C1660v5 v5Var) {
        this.f2315a = new AtomicInteger();
        this.f2316b = new HashSet();
        this.f2317c = new PriorityBlockingQueue<>();
        this.f2318d = new PriorityBlockingQueue<>();
        this.f2324j = new ArrayList();
        this.f2325k = new ArrayList();
        this.f2319e = h5Var;
        this.f2320f = n5Var;
        this.f2322h = new C1556o5[i];
        this.f2321g = v5Var;
    }

    /* renamed from: a */
    public int mo16185a() {
        return this.f2315a.incrementAndGet();
    }

    /* renamed from: b */
    public void mo16190b() {
        mo16192c();
        C1462i5 i5Var = new C1462i5(this.f2317c, this.f2318d, this.f2319e, this.f2321g);
        this.f2323i = i5Var;
        i5Var.start();
        for (int i = 0; i < this.f2322h.length; i++) {
            C1556o5 o5Var = new C1556o5(this.f2318d, this.f2320f, this.f2319e, this.f2321g);
            this.f2322h[i] = o5Var;
            o5Var.start();
        }
    }

    /* renamed from: c */
    public void mo16192c() {
        C1462i5 i5Var = this.f2323i;
        if (i5Var != null) {
            i5Var.mo15870a();
        }
        for (C1556o5 o5Var : this.f2322h) {
            if (o5Var != null) {
                o5Var.mo16039a();
            }
        }
    }

    /* renamed from: a */
    public void mo16188a(C1632c cVar) {
        synchronized (this.f2316b) {
            for (C1615s5 next : this.f2316b) {
                if (cVar.mo16193a(next)) {
                    next.mo16139a();
                }
            }
        }
    }

    /* renamed from: a */
    public void mo16189a(Object obj) {
        if (obj != null) {
            mo16188a((C1632c) new C1630a(this, obj));
            return;
        }
        throw new IllegalArgumentException("Cannot cancelAll with a null tag");
    }

    /* renamed from: a */
    public <T> C1615s5<T> mo16186a(C1615s5<T> s5Var) {
        s5Var.mo16136a(this);
        synchronized (this.f2316b) {
            this.f2316b.add(s5Var);
        }
        s5Var.mo16145b(mo16185a());
        s5Var.mo16144a("add-to-queue");
        mo16187a(s5Var, 0);
        if (!s5Var.mo16169w()) {
            this.f2318d.add(s5Var);
            return s5Var;
        }
        this.f2317c.add(s5Var);
        return s5Var;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public <T> void mo16191b(C1615s5<T> s5Var) {
        synchronized (this.f2316b) {
            this.f2316b.remove(s5Var);
        }
        synchronized (this.f2324j) {
            for (C1633d a : this.f2324j) {
                a.mo16195a(s5Var);
            }
        }
        mo16187a(s5Var, 5);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo16187a(C1615s5<?> s5Var, int i) {
        synchronized (this.f2325k) {
            for (C1631b a : this.f2325k) {
                a.mo16194a(s5Var, i);
            }
        }
    }

    public C1629t5(C1445h5 h5Var, C1546n5 n5Var, int i) {
        this(h5Var, n5Var, i, new C1513l5(new Handler(Looper.getMainLooper())));
    }

    public C1629t5(C1445h5 h5Var, C1546n5 n5Var) {
        this(h5Var, n5Var, 4);
    }
}

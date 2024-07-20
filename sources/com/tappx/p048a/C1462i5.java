package com.tappx.p048a;

import android.os.Process;
import com.tappx.p048a.C1445h5;
import com.tappx.p048a.C1615s5;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;

/* renamed from: com.tappx.a.i5 */
public class C1462i5 extends Thread {

    /* renamed from: g */
    private static final boolean f1947g = C1318a6.f1610b;

    /* renamed from: a */
    private final BlockingQueue<C1615s5<?>> f1948a;
    /* access modifiers changed from: private */

    /* renamed from: b */
    public final BlockingQueue<C1615s5<?>> f1949b;

    /* renamed from: c */
    private final C1445h5 f1950c;
    /* access modifiers changed from: private */

    /* renamed from: d */
    public final C1660v5 f1951d;

    /* renamed from: e */
    private volatile boolean f1952e = false;

    /* renamed from: f */
    private final C1464b f1953f;

    /* renamed from: com.tappx.a.i5$a */
    class C1463a implements Runnable {

        /* renamed from: a */
        final /* synthetic */ C1615s5 f1954a;

        C1463a(C1615s5 s5Var) {
            this.f1954a = s5Var;
        }

        public void run() {
            try {
                C1462i5.this.f1949b.put(this.f1954a);
            } catch (InterruptedException unused) {
                Thread.currentThread().interrupt();
            }
        }
    }

    /* renamed from: com.tappx.a.i5$b */
    private static class C1464b implements C1615s5.C1617b {

        /* renamed from: a */
        private final Map<String, List<C1615s5<?>>> f1956a = new HashMap();

        /* renamed from: b */
        private final C1462i5 f1957b;

        C1464b(C1462i5 i5Var) {
            this.f1957b = i5Var;
        }

        /* access modifiers changed from: private */
        /* JADX WARNING: Code restructure failed: missing block: B:11:0x0039, code lost:
            return true;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:17:0x0051, code lost:
            return false;
         */
        /* renamed from: b */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public synchronized boolean m2861b(com.tappx.p048a.C1615s5<?> r6) {
            /*
                r5 = this;
                monitor-enter(r5)
                java.lang.String r0 = r6.mo16153e()     // Catch:{ all -> 0x0052 }
                java.util.Map<java.lang.String, java.util.List<com.tappx.a.s5<?>>> r1 = r5.f1956a     // Catch:{ all -> 0x0052 }
                boolean r1 = r1.containsKey(r0)     // Catch:{ all -> 0x0052 }
                r2 = 1
                r3 = 0
                if (r1 == 0) goto L_0x003a
                java.util.Map<java.lang.String, java.util.List<com.tappx.a.s5<?>>> r1 = r5.f1956a     // Catch:{ all -> 0x0052 }
                java.lang.Object r1 = r1.get(r0)     // Catch:{ all -> 0x0052 }
                java.util.List r1 = (java.util.List) r1     // Catch:{ all -> 0x0052 }
                if (r1 != 0) goto L_0x001e
                java.util.ArrayList r1 = new java.util.ArrayList     // Catch:{ all -> 0x0052 }
                r1.<init>()     // Catch:{ all -> 0x0052 }
            L_0x001e:
                java.lang.String r4 = "waiting-for-response"
                r6.mo16144a((java.lang.String) r4)     // Catch:{ all -> 0x0052 }
                r1.add(r6)     // Catch:{ all -> 0x0052 }
                java.util.Map<java.lang.String, java.util.List<com.tappx.a.s5<?>>> r6 = r5.f1956a     // Catch:{ all -> 0x0052 }
                r6.put(r0, r1)     // Catch:{ all -> 0x0052 }
                boolean r6 = com.tappx.p048a.C1318a6.f1610b     // Catch:{ all -> 0x0052 }
                if (r6 == 0) goto L_0x0038
                java.lang.Object[] r6 = new java.lang.Object[r2]     // Catch:{ all -> 0x0052 }
                r6[r3] = r0     // Catch:{ all -> 0x0052 }
                java.lang.String r0 = "Request for cacheKey=%s is in flight, putting on hold."
                com.tappx.p048a.C1318a6.m2233b(r0, r6)     // Catch:{ all -> 0x0052 }
            L_0x0038:
                monitor-exit(r5)
                return r2
            L_0x003a:
                java.util.Map<java.lang.String, java.util.List<com.tappx.a.s5<?>>> r1 = r5.f1956a     // Catch:{ all -> 0x0052 }
                r4 = 0
                r1.put(r0, r4)     // Catch:{ all -> 0x0052 }
                r6.mo16141a((com.tappx.p048a.C1615s5.C1617b) r5)     // Catch:{ all -> 0x0052 }
                boolean r6 = com.tappx.p048a.C1318a6.f1610b     // Catch:{ all -> 0x0052 }
                if (r6 == 0) goto L_0x0050
                java.lang.Object[] r6 = new java.lang.Object[r2]     // Catch:{ all -> 0x0052 }
                r6[r3] = r0     // Catch:{ all -> 0x0052 }
                java.lang.String r0 = "new request, sending to network %s"
                com.tappx.p048a.C1318a6.m2233b(r0, r6)     // Catch:{ all -> 0x0052 }
            L_0x0050:
                monitor-exit(r5)
                return r3
            L_0x0052:
                r6 = move-exception
                monitor-exit(r5)
                throw r6
            */
            throw new UnsupportedOperationException("Method not decompiled: com.tappx.p048a.C1462i5.C1464b.m2861b(com.tappx.a.s5):boolean");
        }

        /* renamed from: a */
        public void mo15875a(C1615s5<?> s5Var, C1647u5<?> u5Var) {
            List<C1615s5> remove;
            C1445h5.C1446a aVar = u5Var.f2365b;
            if (aVar == null || aVar.mo15849a()) {
                mo15874a(s5Var);
                return;
            }
            String e = s5Var.mo16153e();
            synchronized (this) {
                remove = this.f1956a.remove(e);
            }
            if (remove != null) {
                if (C1318a6.f1610b) {
                    C1318a6.m2235d("Releasing %d waiting requests for cacheKey=%s.", Integer.valueOf(remove.size()), e);
                }
                for (C1615s5 a : remove) {
                    this.f1957b.f1951d.mo15946a((C1615s5<?>) a, u5Var);
                }
            }
        }

        /* renamed from: a */
        public synchronized void mo15874a(C1615s5<?> s5Var) {
            String e = s5Var.mo16153e();
            List remove = this.f1956a.remove(e);
            if (remove != null && !remove.isEmpty()) {
                if (C1318a6.f1610b) {
                    C1318a6.m2235d("%d waiting requests for cacheKey=%s; resend to network", Integer.valueOf(remove.size()), e);
                }
                C1615s5 s5Var2 = (C1615s5) remove.remove(0);
                this.f1956a.put(e, remove);
                s5Var2.mo16141a((C1615s5.C1617b) this);
                try {
                    this.f1957b.f1949b.put(s5Var2);
                } catch (InterruptedException e2) {
                    C1318a6.m2234c("Couldn't add request to queue. %s", e2.toString());
                    Thread.currentThread().interrupt();
                    this.f1957b.mo15870a();
                }
            }
            return;
        }
    }

    public C1462i5(BlockingQueue<C1615s5<?>> blockingQueue, BlockingQueue<C1615s5<?>> blockingQueue2, C1445h5 h5Var, C1660v5 v5Var) {
        this.f1948a = blockingQueue;
        this.f1949b = blockingQueue2;
        this.f1950c = h5Var;
        this.f1951d = v5Var;
        this.f1953f = new C1464b(this);
    }

    public void run() {
        if (f1947g) {
            C1318a6.m2235d("start new dispatcher", new Object[0]);
        }
        Process.setThreadPriority(10);
        this.f1950c.mo15749a();
        while (true) {
            try {
                m2857b();
            } catch (InterruptedException unused) {
                if (this.f1952e) {
                    Thread.currentThread().interrupt();
                    return;
                }
                C1318a6.m2234c("Ignoring spurious interrupt of CacheDispatcher thread; use quit() to terminate it", new Object[0]);
            }
        }
    }

    /* renamed from: b */
    private void m2857b() {
        mo15871a(this.f1948a.take());
    }

    /* renamed from: a */
    public void mo15870a() {
        this.f1952e = true;
        interrupt();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo15871a(C1615s5<?> s5Var) {
        s5Var.mo16144a("cache-queue-take");
        s5Var.mo16140a(1);
        try {
            if (s5Var.mo16165t()) {
                s5Var.mo16149b("cache-discard-canceled");
                return;
            }
            C1445h5.C1446a a = this.f1950c.mo15747a(s5Var.mo16153e());
            if (a == null) {
                s5Var.mo16144a("cache-miss");
                if (!this.f1953f.m2861b(s5Var)) {
                    this.f1949b.put(s5Var);
                }
                s5Var.mo16140a(2);
            } else if (a.mo15849a()) {
                s5Var.mo16144a("cache-hit-expired");
                s5Var.mo16135a(a);
                if (!this.f1953f.m2861b(s5Var)) {
                    this.f1949b.put(s5Var);
                }
                s5Var.mo16140a(2);
            } else {
                s5Var.mo16144a("cache-hit");
                C1647u5<?> a2 = s5Var.mo15856a(new C1590q5(a.f1915a, a.f1921g));
                s5Var.mo16144a("cache-hit-parsed");
                if (!a.mo15850b()) {
                    this.f1951d.mo15946a(s5Var, a2);
                } else {
                    s5Var.mo16144a("cache-hit-refresh-needed");
                    s5Var.mo16135a(a);
                    a2.f2367d = true;
                    if (!this.f1953f.m2861b(s5Var)) {
                        this.f1951d.mo15947a(s5Var, a2, new C1463a(s5Var));
                    } else {
                        this.f1951d.mo15946a(s5Var, a2);
                    }
                }
                s5Var.mo16140a(2);
            }
        } finally {
            s5Var.mo16140a(2);
        }
    }
}

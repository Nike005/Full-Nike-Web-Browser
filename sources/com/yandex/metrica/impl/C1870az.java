package com.yandex.metrica.impl;

import android.content.Context;
import android.content.Intent;
import android.os.RemoteException;
import com.yandex.metrica.IMetricaService;
import com.yandex.metrica.impl.C1812ad;
import com.yandex.metrica.impl.p050ob.C2174g;
import com.yandex.metrica.impl.p050ob.C2180i;
import com.yandex.metrica.impl.p050ob.C2181j;
import com.yandex.metrica.impl.p050ob.C2182k;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/* renamed from: com.yandex.metrica.impl.az */
public class C1870az implements C1812ad.C1815a {
    /* access modifiers changed from: private */

    /* renamed from: a */
    public final C2213s f3025a;
    /* access modifiers changed from: private */

    /* renamed from: b */
    public final C1812ad f3026b;
    /* access modifiers changed from: private */

    /* renamed from: c */
    public final Object f3027c = new Object();
    /* access modifiers changed from: private */

    /* renamed from: d */
    public final ExecutorService f3028d = Executors.newSingleThreadExecutor();

    /* renamed from: com.yandex.metrica.impl.az$c */
    public interface C1874c {
        /* renamed from: a */
        C1915h mo16925a(C1915h hVar);
    }

    public C1870az(C2213s sVar) {
        this.f3025a = sVar;
        C1812ad a = sVar.mo16902a();
        this.f3026b = a;
        a.mo16773a((C1812ad.C1815a) this);
    }

    /* renamed from: a */
    public Future<Void> mo16926a(C1875d dVar) {
        return this.f3028d.submit(dVar.mo16936c() ? new C1871a(this, dVar, (byte) 0) : new C1872b(this, dVar, (byte) 0));
    }

    /* renamed from: a */
    public void mo16781a() {
        synchronized (this.f3027c) {
            this.f3027c.notifyAll();
        }
    }

    /* renamed from: com.yandex.metrica.impl.az$b */
    private class C1872b implements Callable<Void> {

        /* renamed from: b */
        final C1875d f3030b;

        /* renamed from: c */
        boolean f3031c;

        /* synthetic */ C1872b(C1870az azVar, C1875d dVar, byte b) {
            this(dVar);
        }

        private C1872b(C1875d dVar) {
            this.f3030b = dVar;
            C2174g.m5753a().mo17805a(this, C1841ao.class, C2182k.m5767a(new C2181j<C1841ao>() {
                /* renamed from: a */
                public /* bridge */ /* synthetic */ void mo16931a(C2180i iVar) {
                    mo16930a();
                }

                /* renamed from: a */
                public void mo16930a() {
                    C1872b.this.f3031c = true;
                }
            }).mo17814a());
        }

        /* renamed from: a */
        public Void call() {
            int i = 0;
            do {
                try {
                    IMetricaService e = C1870az.this.f3026b.mo16777e();
                    if (e != null && m4420a(e, this.f3030b)) {
                        break;
                    }
                    i++;
                    if (!mo16928b()) {
                        break;
                    } else if (this.f3031c) {
                        break;
                    }
                } catch (Throwable unused) {
                    C2174g.m5753a().mo17804a((Object) this);
                    return null;
                }
            } while (i >= 3);
            C2174g.m5753a().mo17804a((Object) this);
            return null;
        }

        /* access modifiers changed from: package-private */
        /* JADX WARNING: Can't wrap try/catch for region: R(4:5|6|7|8) */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0029 */
        /* renamed from: b */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean mo16928b() {
            /*
                r5 = this;
                com.yandex.metrica.impl.az r0 = com.yandex.metrica.impl.C1870az.this
                com.yandex.metrica.impl.ad r0 = r0.f3026b
                r0.mo16772a()
                com.yandex.metrica.impl.az r0 = com.yandex.metrica.impl.C1870az.this
                java.lang.Object r0 = r0.f3027c
                monitor-enter(r0)
                com.yandex.metrica.impl.az r1 = com.yandex.metrica.impl.C1870az.this     // Catch:{ all -> 0x003e }
                com.yandex.metrica.impl.ad r1 = r1.f3026b     // Catch:{ all -> 0x003e }
                boolean r1 = r1.mo16776d()     // Catch:{ all -> 0x003e }
                if (r1 != 0) goto L_0x003b
                com.yandex.metrica.impl.az r1 = com.yandex.metrica.impl.C1870az.this     // Catch:{ InterruptedException -> 0x0029 }
                java.lang.Object r1 = r1.f3027c     // Catch:{ InterruptedException -> 0x0029 }
                r2 = 500(0x1f4, double:2.47E-321)
                r4 = 0
                r1.wait(r2, r4)     // Catch:{ InterruptedException -> 0x0029 }
                goto L_0x003b
            L_0x0029:
                com.yandex.metrica.impl.az r1 = com.yandex.metrica.impl.C1870az.this     // Catch:{ all -> 0x003e }
                java.lang.Object r1 = r1.f3027c     // Catch:{ all -> 0x003e }
                r1.notifyAll()     // Catch:{ all -> 0x003e }
                com.yandex.metrica.impl.az r1 = com.yandex.metrica.impl.C1870az.this     // Catch:{ all -> 0x003e }
                java.util.concurrent.ExecutorService r1 = r1.f3028d     // Catch:{ all -> 0x003e }
                r1.shutdownNow()     // Catch:{ all -> 0x003e }
            L_0x003b:
                monitor-exit(r0)     // Catch:{ all -> 0x003e }
                r0 = 1
                return r0
            L_0x003e:
                r1 = move-exception
                monitor-exit(r0)     // Catch:{ all -> 0x003e }
                throw r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.yandex.metrica.impl.C1870az.C1872b.mo16928b():boolean");
        }

        /* renamed from: a */
        private boolean m4420a(IMetricaService iMetricaService, C1875d dVar) {
            try {
                C1870az.this.f3025a.mo16904a(iMetricaService, dVar.mo16935b(), dVar.f3035b);
                return true;
            } catch (RemoteException unused) {
                return false;
            }
        }
    }

    /* renamed from: com.yandex.metrica.impl.az$a */
    private class C1871a extends C1872b {
        /* synthetic */ C1871a(C1870az azVar, C1875d dVar, byte b) {
            this(dVar);
        }

        private C1871a(C1875d dVar) {
            super(C1870az.this, dVar, (byte) 0);
        }

        /* renamed from: a */
        public Void call() {
            C1870az.this.f3026b.mo16774b();
            return super.call();
        }

        /* access modifiers changed from: package-private */
        /* renamed from: b */
        public boolean mo16928b() {
            C1875d dVar = this.f3030b;
            Context b = C1870az.this.f3025a.mo16917b();
            Intent c = C1887be.m4556c(b);
            c.putExtras(dVar.f3034a.mo17110a(dVar.f3035b.mo16889c()));
            b.startService(c);
            return false;
        }
    }

    /* renamed from: com.yandex.metrica.impl.az$d */
    public static class C1875d {
        /* access modifiers changed from: private */

        /* renamed from: a */
        public C1915h f3034a;
        /* access modifiers changed from: private */

        /* renamed from: b */
        public C1864aw f3035b;

        /* renamed from: c */
        private boolean f3036c = false;

        /* renamed from: d */
        private C1874c f3037d;

        C1875d(C1915h hVar, C1864aw awVar) {
            this.f3034a = hVar;
            this.f3035b = awVar;
        }

        /* access modifiers changed from: package-private */
        /* renamed from: a */
        public C1875d mo16933a(C1874c cVar) {
            this.f3037d = cVar;
            return this;
        }

        /* access modifiers changed from: package-private */
        /* renamed from: a */
        public C1875d mo16934a(boolean z) {
            this.f3036c = z;
            return this;
        }

        /* access modifiers changed from: package-private */
        /* renamed from: a */
        public C1864aw mo16932a() {
            return this.f3035b;
        }

        /* access modifiers changed from: package-private */
        /* renamed from: b */
        public C1915h mo16935b() {
            C1874c cVar = this.f3037d;
            return cVar != null ? cVar.mo16925a(this.f3034a) : this.f3034a;
        }

        /* access modifiers changed from: package-private */
        /* renamed from: c */
        public boolean mo16936c() {
            return this.f3036c;
        }

        public String toString() {
            return "ReportToSend{mReport=" + this.f3034a + ", mEnvironment=" + this.f3035b + ", mCrash=" + this.f3036c + ", mAction=" + this.f3037d + '}';
        }
    }
}

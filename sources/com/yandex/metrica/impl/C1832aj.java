package com.yandex.metrica.impl;

import com.yandex.metrica.impl.p050ob.C2040cn;
import com.yandex.metrica.impl.p050ob.C2045cq;
import com.yandex.metrica.impl.p050ob.C2190r;
import java.util.Locale;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;

/* renamed from: com.yandex.metrica.impl.aj */
class C1832aj extends Thread {

    /* renamed from: a */
    private final Executor f2934a;

    /* renamed from: b */
    private Executor f2935b;

    /* renamed from: c */
    private final BlockingQueue<C1834b> f2936c = new LinkedBlockingQueue();

    /* renamed from: d */
    private final Object f2937d = new Object();

    /* renamed from: e */
    private volatile C1834b f2938e;

    public C1832aj(Executor executor, C2190r rVar) {
        this.f2934a = executor;
        this.f2935b = new C2040cn();
        String.format(Locale.US, "[%s:%s]", new Object[]{"NetworkTaskQueue", rVar.toString()});
    }

    /* renamed from: a */
    public void mo16806a(C1835ak akVar) {
        synchronized (this.f2937d) {
            C1834b bVar = new C1834b(akVar, (byte) 0);
            if (!m4216a(bVar)) {
                this.f2936c.offer(bVar);
            }
        }
    }

    /* renamed from: a */
    public void mo16805a() {
        this.f2938e = null;
        this.f2936c.clear();
        interrupt();
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(3:10|11|17) */
    /* JADX WARNING: Code restructure failed: missing block: B:11:?, code lost:
        java.lang.Thread.currentThread().interrupt();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x003c, code lost:
        r5.f2938e = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x003e, code lost:
        throw r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0030, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:10:0x0032 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void run() {
        /*
            r5 = this;
        L_0x0000:
            java.lang.Thread r0 = java.lang.Thread.currentThread()
            boolean r0 = r0.isInterrupted()
            if (r0 != 0) goto L_0x003f
            r0 = 0
            java.util.concurrent.BlockingQueue<com.yandex.metrica.impl.aj$b> r1 = r5.f2936c     // Catch:{ InterruptedException -> 0x0032 }
            java.lang.Object r1 = r1.take()     // Catch:{ InterruptedException -> 0x0032 }
            com.yandex.metrica.impl.aj$b r1 = (com.yandex.metrica.impl.C1832aj.C1834b) r1     // Catch:{ InterruptedException -> 0x0032 }
            r5.f2938e = r1     // Catch:{ InterruptedException -> 0x0032 }
            com.yandex.metrica.impl.aj$b r1 = r5.f2938e     // Catch:{ InterruptedException -> 0x0032 }
            com.yandex.metrica.impl.ak r1 = r1.f2941a     // Catch:{ InterruptedException -> 0x0032 }
            boolean r2 = r1.mo16833n()     // Catch:{ InterruptedException -> 0x0032 }
            if (r2 == 0) goto L_0x0024
            java.util.concurrent.Executor r2 = r5.f2934a     // Catch:{ InterruptedException -> 0x0032 }
            goto L_0x0026
        L_0x0024:
            java.util.concurrent.Executor r2 = r5.f2935b     // Catch:{ InterruptedException -> 0x0032 }
        L_0x0026:
            com.yandex.metrica.impl.aj$a r3 = new com.yandex.metrica.impl.aj$a     // Catch:{ InterruptedException -> 0x0032 }
            r4 = 0
            r3.<init>(r5, r1, r4)     // Catch:{ InterruptedException -> 0x0032 }
            r2.execute(r3)     // Catch:{ InterruptedException -> 0x0032 }
            goto L_0x0039
        L_0x0030:
            r1 = move-exception
            goto L_0x003c
        L_0x0032:
            java.lang.Thread r1 = java.lang.Thread.currentThread()     // Catch:{ all -> 0x0030 }
            r1.interrupt()     // Catch:{ all -> 0x0030 }
        L_0x0039:
            r5.f2938e = r0
            goto L_0x0000
        L_0x003c:
            r5.f2938e = r0
            throw r1
        L_0x003f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.yandex.metrica.impl.C1832aj.run():void");
    }

    /* renamed from: b */
    public boolean mo16807b(C1835ak akVar) {
        return m4216a(new C1834b(akVar, (byte) 0));
    }

    /* renamed from: a */
    private boolean m4216a(C1834b bVar) {
        return this.f2936c.contains(bVar) || bVar.equals(this.f2938e);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: c */
    public void mo16808c(C1835ak akVar) throws InterruptedException {
        boolean b = akVar.mo16821b();
        C2045cq d = akVar.mo16823d();
        if (b && !d.mo17508b()) {
            b = false;
        }
        while (!Thread.currentThread().isInterrupted() && r0) {
            mo16809d(akVar);
            boolean z = !akVar.mo16822c() && akVar.mo16834o();
            if (z) {
                Thread.sleep(akVar.mo16835p());
            }
        }
        akVar.mo16825f();
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v6, resolved type: java.io.OutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v8, resolved type: java.io.OutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v9, resolved type: java.io.OutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v10, resolved type: java.io.InputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v14, resolved type: java.io.OutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v19, resolved type: java.io.OutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v20, resolved type: java.io.OutputStream} */
    /* JADX WARNING: type inference failed for: r4v6, types: [java.io.BufferedInputStream, java.io.InputStream] */
    /* access modifiers changed from: package-private */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* renamed from: d */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void mo16809d(com.yandex.metrica.impl.C1835ak r8) {
        /*
            r7 = this;
            r0 = 0
            r8.mo16824e()     // Catch:{ all -> 0x0095 }
            com.yandex.metrica.impl.ob.cq r1 = r8.mo16823d()     // Catch:{ all -> 0x0095 }
            java.net.HttpURLConnection r1 = r1.mo17507a()     // Catch:{ all -> 0x0095 }
            r2 = 2
            int r3 = r8.mo16828i()     // Catch:{ all -> 0x0093 }
            r4 = 1
            if (r2 != r3) goto L_0x0049
            byte[] r2 = r8.mo16829j()     // Catch:{ all -> 0x0093 }
            if (r2 == 0) goto L_0x0049
            int r3 = r2.length     // Catch:{ all -> 0x0093 }
            if (r3 <= 0) goto L_0x0049
            java.lang.String r3 = r8.mo16832m()     // Catch:{ all -> 0x0093 }
            r1.setDoOutput(r4)     // Catch:{ all -> 0x0093 }
            java.lang.String r5 = "Accept-Encoding"
            r1.setRequestProperty(r5, r3)     // Catch:{ all -> 0x0093 }
            java.lang.String r5 = "Content-Encoding"
            r1.setRequestProperty(r5, r3)     // Catch:{ all -> 0x0093 }
            java.io.OutputStream r3 = r1.getOutputStream()     // Catch:{ all -> 0x0093 }
            java.io.BufferedOutputStream r5 = new java.io.BufferedOutputStream     // Catch:{ all -> 0x0046 }
            int r2 = r2.length     // Catch:{ all -> 0x0046 }
            r5.<init>(r3, r2)     // Catch:{ all -> 0x0046 }
            byte[] r2 = r8.mo16829j()     // Catch:{ all -> 0x008f }
            r5.write(r2)     // Catch:{ all -> 0x008f }
            r5.flush()     // Catch:{ all -> 0x008f }
            com.yandex.metrica.impl.C1897bk.m4645a((java.io.Closeable) r3)     // Catch:{ all -> 0x008f }
            goto L_0x004b
        L_0x0046:
            r2 = r0
            r4 = r2
            goto L_0x0099
        L_0x0049:
            r3 = r0
            r5 = r3
        L_0x004b:
            int r2 = r1.getResponseCode()     // Catch:{ all -> 0x008f }
            r8.mo16815a((int) r2)     // Catch:{ all -> 0x008f }
            java.util.Map r6 = r1.getHeaderFields()     // Catch:{ all -> 0x008f }
            r8.mo16817a((java.util.Map<java.lang.String, java.util.List<java.lang.String>>) r6)     // Catch:{ all -> 0x008f }
            r6 = 400(0x190, float:5.6E-43)
            if (r2 == r6) goto L_0x0062
            r6 = 500(0x1f4, float:7.0E-43)
            if (r2 == r6) goto L_0x0062
            goto L_0x0063
        L_0x0062:
            r4 = 0
        L_0x0063:
            if (r4 == 0) goto L_0x007e
            java.io.InputStream r2 = r1.getInputStream()     // Catch:{ all -> 0x008f }
            java.io.BufferedInputStream r4 = new java.io.BufferedInputStream     // Catch:{ all -> 0x007c }
            r6 = 8000(0x1f40, float:1.121E-41)
            r4.<init>(r2, r6)     // Catch:{ all -> 0x007c }
            byte[] r0 = com.yandex.metrica.impl.C2211r.m5901b((java.io.InputStream) r4)     // Catch:{ all -> 0x0091 }
            r8.mo16820b((byte[]) r0)     // Catch:{ all -> 0x0091 }
            com.yandex.metrica.impl.C1897bk.m4645a((java.io.Closeable) r2)     // Catch:{ all -> 0x0091 }
            r0 = r4
            goto L_0x007f
        L_0x007c:
            r4 = r0
            goto L_0x0091
        L_0x007e:
            r2 = r0
        L_0x007f:
            com.yandex.metrica.impl.C1897bk.m4645a((java.io.Closeable) r5)
            com.yandex.metrica.impl.C1897bk.m4645a((java.io.Closeable) r0)
        L_0x0085:
            com.yandex.metrica.impl.C1897bk.m4645a((java.io.Closeable) r3)
            com.yandex.metrica.impl.C1897bk.m4645a((java.io.Closeable) r2)
            com.yandex.metrica.impl.C1897bk.m4649a((java.net.HttpURLConnection) r1)
            return
        L_0x008f:
            r2 = r0
            r4 = r2
        L_0x0091:
            r0 = r5
            goto L_0x0099
        L_0x0093:
            r2 = r0
            goto L_0x0097
        L_0x0095:
            r1 = r0
            r2 = r1
        L_0x0097:
            r3 = r2
            r4 = r3
        L_0x0099:
            r8.mo16826g()     // Catch:{ all -> 0x00a3 }
            com.yandex.metrica.impl.C1897bk.m4645a((java.io.Closeable) r0)
            com.yandex.metrica.impl.C1897bk.m4645a((java.io.Closeable) r4)
            goto L_0x0085
        L_0x00a3:
            r8 = move-exception
            com.yandex.metrica.impl.C1897bk.m4645a((java.io.Closeable) r0)
            com.yandex.metrica.impl.C1897bk.m4645a((java.io.Closeable) r4)
            com.yandex.metrica.impl.C1897bk.m4645a((java.io.Closeable) r3)
            com.yandex.metrica.impl.C1897bk.m4645a((java.io.Closeable) r2)
            com.yandex.metrica.impl.C1897bk.m4649a((java.net.HttpURLConnection) r1)
            goto L_0x00b5
        L_0x00b4:
            throw r8
        L_0x00b5:
            goto L_0x00b4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.yandex.metrica.impl.C1832aj.mo16809d(com.yandex.metrica.impl.ak):void");
    }

    /* renamed from: com.yandex.metrica.impl.aj$b */
    private static class C1834b {
        /* access modifiers changed from: private */

        /* renamed from: a */
        public final C1835ak f2941a;

        /* renamed from: b */
        private final String f2942b;

        /* synthetic */ C1834b(C1835ak akVar, byte b) {
            this(akVar);
        }

        private C1834b(C1835ak akVar) {
            this.f2941a = akVar;
            this.f2942b = akVar.mo16814a();
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            return this.f2942b.equals(((C1834b) obj).f2942b);
        }

        public int hashCode() {
            return this.f2942b.hashCode();
        }
    }

    /* renamed from: com.yandex.metrica.impl.aj$a */
    private class C1833a implements Runnable {

        /* renamed from: b */
        private final C1835ak f2940b;

        /* synthetic */ C1833a(C1832aj ajVar, C1835ak akVar, byte b) {
            this(akVar);
        }

        private C1833a(C1835ak akVar) {
            this.f2940b = akVar;
        }

        public void run() {
            try {
                C1832aj.this.mo16808c(this.f2940b);
            } catch (InterruptedException unused) {
                Thread.currentThread().interrupt();
            }
        }
    }
}

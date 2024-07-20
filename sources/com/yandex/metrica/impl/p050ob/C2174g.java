package com.yandex.metrica.impl.p050ob;

import java.util.Iterator;
import java.util.List;
import java.util.WeakHashMap;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.LinkedBlockingQueue;

/* renamed from: com.yandex.metrica.impl.ob.g */
public class C2174g {

    /* renamed from: a */
    private final Thread f3753a;
    /* access modifiers changed from: private */

    /* renamed from: b */
    public volatile boolean f3754b = true;
    /* access modifiers changed from: private */

    /* renamed from: c */
    public final BlockingQueue<C2176a> f3755c = new LinkedBlockingQueue();

    /* renamed from: d */
    private ConcurrentHashMap<Class, CopyOnWriteArrayList<C2182k<? extends C2180i>>> f3756d = new ConcurrentHashMap<>();

    /* renamed from: e */
    private WeakHashMap<Object, CopyOnWriteArrayList<C2178c>> f3757e = new WeakHashMap<>();

    /* renamed from: f */
    private ConcurrentHashMap<Class, C2180i> f3758f = new ConcurrentHashMap<>();

    /* renamed from: com.yandex.metrica.impl.ob.g$b */
    private static final class C2177b {
        /* access modifiers changed from: private */

        /* renamed from: a */
        public static final C2174g f3762a = new C2174g();
    }

    /* renamed from: com.yandex.metrica.impl.ob.g$c */
    private static class C2178c {

        /* renamed from: a */
        final CopyOnWriteArrayList<C2182k<? extends C2180i>> f3763a;

        /* renamed from: b */
        final C2182k<? extends C2180i> f3764b;

        /* synthetic */ C2178c(CopyOnWriteArrayList copyOnWriteArrayList, C2182k kVar, byte b) {
            this(copyOnWriteArrayList, kVar);
        }

        private C2178c(CopyOnWriteArrayList<C2182k<? extends C2180i>> copyOnWriteArrayList, C2182k<? extends C2180i> kVar) {
            this.f3763a = copyOnWriteArrayList;
            this.f3764b = kVar;
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public void mo17809a() {
            this.f3763a.remove(this.f3764b);
        }

        /* access modifiers changed from: protected */
        public void finalize() throws Throwable {
            super.finalize();
            mo17809a();
        }
    }

    /* renamed from: com.yandex.metrica.impl.ob.g$a */
    private static class C2176a {

        /* renamed from: a */
        private final C2180i f3760a;

        /* renamed from: b */
        private final C2182k<? extends C2180i> f3761b;

        /* synthetic */ C2176a(C2180i iVar, C2182k kVar, byte b) {
            this(iVar, kVar);
        }

        private C2176a(C2180i iVar, C2182k<? extends C2180i> kVar) {
            this.f3760a = iVar;
            this.f3761b = kVar;
        }

        /* access modifiers changed from: package-private */
        /* renamed from: a */
        public void mo17808a() {
            try {
                if (!this.f3761b.mo17812b(this.f3760a)) {
                    this.f3761b.mo17811a(this.f3760a);
                }
            } catch (Throwable unused) {
            }
        }
    }

    /* renamed from: a */
    public static final C2174g m5753a() {
        return C2177b.f3762a;
    }

    C2174g() {
        Thread thread = new Thread(new Runnable() {
            public void run() {
                while (C2174g.this.f3754b) {
                    try {
                        ((C2176a) C2174g.this.f3755c.take()).mo17808a();
                    } catch (InterruptedException unused) {
                    }
                }
            }
        }, "Bus Dispatcher");
        this.f3753a = thread;
        thread.start();
    }

    /* renamed from: a */
    public synchronized void mo17801a(C2180i iVar) {
        CopyOnWriteArrayList copyOnWriteArrayList = this.f3756d.get(iVar.getClass());
        if (copyOnWriteArrayList != null) {
            Iterator it = copyOnWriteArrayList.iterator();
            while (it.hasNext()) {
                mo17802a(iVar, (C2182k) it.next());
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo17802a(C2180i iVar, C2182k<? extends C2180i> kVar) {
        this.f3755c.add(new C2176a(iVar, kVar, (byte) 0));
    }

    /* renamed from: b */
    public synchronized void mo17806b(C2180i iVar) {
        mo17801a(iVar);
        this.f3758f.put(iVar.getClass(), iVar);
    }

    /* renamed from: a */
    public synchronized void mo17803a(Class<? extends C2180i> cls) {
        this.f3758f.remove(cls);
    }

    /* renamed from: a */
    public synchronized void mo17805a(Object obj, Class cls, C2182k<? extends C2180i> kVar) {
        CopyOnWriteArrayList copyOnWriteArrayList = this.f3756d.get(cls);
        if (copyOnWriteArrayList == null) {
            copyOnWriteArrayList = new CopyOnWriteArrayList();
            this.f3756d.put(cls, copyOnWriteArrayList);
        }
        copyOnWriteArrayList.add(kVar);
        CopyOnWriteArrayList copyOnWriteArrayList2 = this.f3757e.get(obj);
        if (copyOnWriteArrayList2 == null) {
            copyOnWriteArrayList2 = new CopyOnWriteArrayList();
            this.f3757e.put(obj, copyOnWriteArrayList2);
        }
        copyOnWriteArrayList2.add(new C2178c(copyOnWriteArrayList, kVar, (byte) 0));
        C2180i iVar = this.f3758f.get(cls);
        if (iVar != null) {
            mo17802a(iVar, kVar);
        }
    }

    /* renamed from: a */
    public synchronized void mo17804a(Object obj) {
        List<C2178c> remove = this.f3757e.remove(obj);
        if (remove != null) {
            for (C2178c a : remove) {
                a.mo17809a();
            }
        }
    }
}

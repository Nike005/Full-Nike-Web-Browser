package com.yandex.metrica.impl.p050ob;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import com.yandex.metrica.impl.p050ob.C2169fu;
import java.lang.Thread;

/* renamed from: com.yandex.metrica.impl.ob.fs */
public class C2164fs {
    /* access modifiers changed from: private */

    /* renamed from: a */
    public C2161fq f3730a;

    /* renamed from: b */
    private HandlerThread f3731b;

    /* renamed from: c */
    private C2166b f3732c;
    /* access modifiers changed from: private */

    /* renamed from: d */
    public volatile Handler f3733d;

    public C2164fs(C2161fq fqVar) {
        this(fqVar, (Handler) null);
    }

    public C2164fs(C2161fq fqVar, Handler handler) {
        this.f3730a = fqVar;
        this.f3731b = new HandlerThread(C2164fs.class.getSimpleName() + '@' + Integer.toHexString(hashCode()));
        this.f3733d = handler;
    }

    /* renamed from: a */
    public <T> void mo17773a(C2169fu<T> fuVar, C2169fu.C2171b<T> bVar, C2169fu.C2170a aVar) {
        m5720a();
        fuVar.mo17780a(bVar);
        fuVar.mo17779a(aVar);
        this.f3732c.mo17775a(fuVar);
    }

    /* renamed from: a */
    private synchronized void m5720a() {
        if (this.f3731b.getState() == Thread.State.NEW) {
            this.f3731b.start();
            Looper looper = this.f3731b.getLooper();
            this.f3732c = new C2166b(this, looper, (byte) 0);
            if (this.f3733d == null) {
                this.f3733d = new Handler(looper);
            }
        }
    }

    /* renamed from: com.yandex.metrica.impl.ob.fs$b */
    private class C2166b extends Handler {
        /* synthetic */ C2166b(C2164fs fsVar, Looper looper, byte b) {
            this(looper);
        }

        private C2166b(Looper looper) {
            super(looper);
        }

        public void handleMessage(Message message) {
            C2169fu fuVar = (C2169fu) message.obj;
            C2169fu.C2171b e = fuVar.mo17782e();
            try {
                C2164fs.this.f3733d.post(new C2167c(e, fuVar.mo17770b(C2164fs.this.f3730a.mo17772a((C2169fu<?>) fuVar)), (byte) 0));
            } catch (C2162fr e2) {
                C2164fs.this.f3733d.post(new C2165a(fuVar.mo17783f(), e2, (byte) 0));
            }
        }

        /* renamed from: a */
        public <T> void mo17775a(C2169fu<T> fuVar) {
            Message message = new Message();
            message.obj = fuVar;
            sendMessage(message);
        }
    }

    /* renamed from: com.yandex.metrica.impl.ob.fs$c */
    private static class C2167c<T> implements Runnable {

        /* renamed from: a */
        private C2169fu.C2171b<T> f3737a;

        /* renamed from: b */
        private T f3738b;

        /* synthetic */ C2167c(C2169fu.C2171b bVar, Object obj, byte b) {
            this(bVar, obj);
        }

        private C2167c(C2169fu.C2171b bVar, T t) {
            this.f3737a = bVar;
            this.f3738b = t;
        }

        public void run() {
            C2169fu.C2171b<T> bVar = this.f3737a;
            if (bVar != null) {
                bVar.mo17761a(this.f3738b);
            }
        }
    }

    /* renamed from: com.yandex.metrica.impl.ob.fs$a */
    private static class C2165a implements Runnable {

        /* renamed from: a */
        private C2169fu.C2170a f3734a;

        /* renamed from: b */
        private C2162fr f3735b;

        /* synthetic */ C2165a(C2169fu.C2170a aVar, C2162fr frVar, byte b) {
            this(aVar, frVar);
        }

        private C2165a(C2169fu.C2170a aVar, C2162fr frVar) {
            this.f3734a = aVar;
            this.f3735b = frVar;
        }

        public void run() {
            C2169fu.C2170a aVar = this.f3734a;
            if (aVar != null) {
                aVar.mo17763a(this.f3735b);
            }
        }
    }
}

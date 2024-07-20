package com.moat.analytics.mobile.mpub;

import android.os.Handler;
import android.os.Looper;
import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import org.altbeacon.beacon.service.scanner.CycledLeScanner;

/* renamed from: com.moat.analytics.mobile.mpub.w */
class C0351w {

    /* renamed from: h */
    private static C0351w f195h;
    /* access modifiers changed from: private */

    /* renamed from: i */
    public static final Queue<C0358c> f196i = new ConcurrentLinkedQueue();

    /* renamed from: a */
    volatile C0359d f197a = C0359d.OFF;

    /* renamed from: b */
    volatile boolean f198b = false;

    /* renamed from: c */
    volatile boolean f199c = false;

    /* renamed from: d */
    volatile int f200d = 200;

    /* renamed from: e */
    volatile int f201e = 10;

    /* renamed from: f */
    private long f202f = CycledLeScanner.ANDROID_N_MAX_SCAN_DURATION_MILLIS;
    /* access modifiers changed from: private */

    /* renamed from: g */
    public long f203g = 60000;
    /* access modifiers changed from: private */

    /* renamed from: j */
    public Handler f204j;
    /* access modifiers changed from: private */

    /* renamed from: k */
    public final AtomicBoolean f205k = new AtomicBoolean(false);
    /* access modifiers changed from: private */

    /* renamed from: l */
    public volatile long f206l = 0;
    /* access modifiers changed from: private */

    /* renamed from: m */
    public final AtomicInteger f207m = new AtomicInteger(0);
    /* access modifiers changed from: private */

    /* renamed from: n */
    public final AtomicBoolean f208n = new AtomicBoolean(false);

    /* renamed from: com.moat.analytics.mobile.mpub.w$a */
    private class C0355a implements Runnable {

        /* renamed from: b */
        private final Handler f214b;

        /* renamed from: c */
        private final String f215c;
        /* access modifiers changed from: private */

        /* renamed from: d */
        public final C0360e f216d;

        private C0355a(String str, Handler handler, C0360e eVar) {
            this.f216d = eVar;
            this.f214b = handler;
            this.f215c = "https://sejs.moatads.com/" + str + "/android/" + "422d7e6" + "/status.json";
        }

        /* renamed from: a */
        private void m280a() {
            String b = m281b();
            final C0329m mVar = new C0329m(b);
            C0351w.this.f198b = mVar.mo10453a();
            C0351w.this.f199c = mVar.mo10454b();
            C0351w.this.f200d = mVar.mo10455c();
            C0351w.this.f201e = mVar.mo10456d();
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                public void run() {
                    try {
                        C0355a.this.f216d.mo10466a(mVar);
                    } catch (Exception e) {
                        C0330n.m214a(e);
                    }
                }
            });
            long unused = C0351w.this.f206l = System.currentTimeMillis();
            C0351w.this.f208n.compareAndSet(true, false);
            if (b != null) {
                C0351w.this.f207m.set(0);
            } else if (C0351w.this.f207m.incrementAndGet() < 10) {
                C0351w wVar = C0351w.this;
                wVar.m265a(wVar.f203g);
            }
        }

        /* renamed from: b */
        private String m281b() {
            try {
                return C0337q.m233a(this.f215c + "?ts=" + System.currentTimeMillis() + "&v=" + "2.6.6").mo10374b();
            } catch (Exception unused) {
                return null;
            }
        }

        public void run() {
            try {
                m280a();
            } catch (Exception e) {
                C0330n.m214a(e);
            }
            this.f214b.removeCallbacks(this);
            Looper myLooper = Looper.myLooper();
            if (myLooper != null) {
                myLooper.quit();
            }
        }
    }

    /* renamed from: com.moat.analytics.mobile.mpub.w$b */
    interface C0357b {
        /* renamed from: b */
        void mo10450b();

        /* renamed from: c */
        void mo10451c();
    }

    /* renamed from: com.moat.analytics.mobile.mpub.w$c */
    private class C0358c {

        /* renamed from: a */
        final Long f219a;

        /* renamed from: b */
        final C0357b f220b;

        C0358c(Long l, C0357b bVar) {
            this.f219a = l;
            this.f220b = bVar;
        }
    }

    /* renamed from: com.moat.analytics.mobile.mpub.w$d */
    enum C0359d {
        OFF,
        ON
    }

    /* renamed from: com.moat.analytics.mobile.mpub.w$e */
    interface C0360e {
        /* renamed from: a */
        void mo10466a(C0329m mVar);
    }

    private C0351w() {
        try {
            this.f204j = new Handler(Looper.getMainLooper());
        } catch (Exception e) {
            C0330n.m214a(e);
        }
    }

    /* renamed from: a */
    static synchronized C0351w m264a() {
        C0351w wVar;
        synchronized (C0351w.class) {
            if (f195h == null) {
                f195h = new C0351w();
            }
            wVar = f195h;
        }
        return wVar;
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public void m265a(final long j) {
        if (this.f208n.compareAndSet(false, true)) {
            C0336p.m228a(3, "OnOff", (Object) this, "Performing status check.");
            new Thread() {
                public void run() {
                    Looper.prepare();
                    Handler handler = new Handler();
                    handler.postDelayed(new C0355a("MPUB", handler, new C0360e() {
                        /* renamed from: a */
                        public void mo10466a(C0329m mVar) {
                            synchronized (C0351w.f196i) {
                                boolean z = ((C0327k) MoatAnalytics.getInstance()).f148a;
                                if (C0351w.this.f197a != mVar.mo10457e() || (C0351w.this.f197a == C0359d.OFF && z)) {
                                    C0351w.this.f197a = mVar.mo10457e();
                                    if (C0351w.this.f197a == C0359d.OFF && z) {
                                        C0351w.this.f197a = C0359d.ON;
                                    }
                                    if (C0351w.this.f197a == C0359d.ON && !C0319i.m142a().mo10432b()) {
                                        C0351w.this.f197a = C0359d.OFF;
                                    }
                                    if (C0351w.this.f197a == C0359d.ON) {
                                        C0336p.m228a(3, "OnOff", (Object) this, "Moat enabled - Version 2.6.6");
                                    }
                                    for (C0358c cVar : C0351w.f196i) {
                                        if (C0351w.this.f197a == C0359d.ON) {
                                            cVar.f220b.mo10450b();
                                        } else {
                                            cVar.f220b.mo10451c();
                                        }
                                    }
                                }
                                while (!C0351w.f196i.isEmpty()) {
                                    C0351w.f196i.remove();
                                }
                            }
                        }
                    }), j);
                    Looper.loop();
                }
            }.start();
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: d */
    public void m272d() {
        synchronized (f196i) {
            long currentTimeMillis = System.currentTimeMillis();
            Iterator it = f196i.iterator();
            while (it.hasNext()) {
                if (currentTimeMillis - ((C0358c) it.next()).f219a.longValue() >= 60000) {
                    it.remove();
                }
            }
            if (f196i.size() >= 15) {
                for (int i = 0; i < 5; i++) {
                    f196i.remove();
                }
            }
        }
    }

    /* renamed from: e */
    private void m274e() {
        if (this.f205k.compareAndSet(false, true)) {
            this.f204j.postDelayed(new Runnable() {
                public void run() {
                    try {
                        if (C0351w.f196i.size() > 0) {
                            C0351w.this.m272d();
                            C0351w.this.f204j.postDelayed(this, 60000);
                            return;
                        }
                        C0351w.this.f205k.compareAndSet(true, false);
                        C0351w.this.f204j.removeCallbacks(this);
                    } catch (Exception e) {
                        C0330n.m214a(e);
                    }
                }
            }, 60000);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo10463a(C0357b bVar) {
        if (this.f197a == C0359d.ON) {
            bVar.mo10450b();
            return;
        }
        m272d();
        f196i.add(new C0358c(Long.valueOf(System.currentTimeMillis()), bVar));
        m274e();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public void mo10464b() {
        if (System.currentTimeMillis() - this.f206l > this.f202f) {
            m265a(0);
        }
    }
}

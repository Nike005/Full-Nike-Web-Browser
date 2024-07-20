package com.tappx.p048a;

import android.net.TrafficStats;
import android.os.Build;
import android.os.Process;
import android.os.SystemClock;
import java.util.concurrent.BlockingQueue;

/* renamed from: com.tappx.a.o5 */
public class C1556o5 extends Thread {

    /* renamed from: a */
    private final BlockingQueue<C1615s5<?>> f2152a;

    /* renamed from: b */
    private final C1546n5 f2153b;

    /* renamed from: c */
    private final C1445h5 f2154c;

    /* renamed from: d */
    private final C1660v5 f2155d;

    /* renamed from: e */
    private volatile boolean f2156e = false;

    public C1556o5(BlockingQueue<C1615s5<?>> blockingQueue, C1546n5 n5Var, C1445h5 h5Var, C1660v5 v5Var) {
        this.f2152a = blockingQueue;
        this.f2153b = n5Var;
        this.f2154c = h5Var;
        this.f2155d = v5Var;
    }

    /* renamed from: b */
    private void m3180b(C1615s5<?> s5Var) {
        if (Build.VERSION.SDK_INT >= 14) {
            TrafficStats.setThreadStatsTag(s5Var.mo16162q());
        }
    }

    /* renamed from: a */
    public void mo16039a() {
        this.f2156e = true;
        interrupt();
    }

    public void run() {
        Process.setThreadPriority(10);
        while (true) {
            try {
                m3179b();
            } catch (InterruptedException unused) {
                if (this.f2156e) {
                    Thread.currentThread().interrupt();
                    return;
                }
                C1318a6.m2234c("Ignoring spurious interrupt of NetworkDispatcher thread; use quit() to terminate it", new Object[0]);
            }
        }
    }

    /* renamed from: b */
    private void m3179b() {
        mo16040a(this.f2152a.take());
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo16040a(C1615s5<?> s5Var) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        s5Var.mo16140a(3);
        try {
            s5Var.mo16144a("network-queue-take");
            if (s5Var.mo16165t()) {
                s5Var.mo16149b("network-discard-cancelled");
                s5Var.mo16168v();
                s5Var.mo16140a(4);
                return;
            }
            m3180b(s5Var);
            C1590q5 a = this.f2153b.mo15645a(s5Var);
            s5Var.mo16144a("network-http-complete");
            if (!a.f2232e || !s5Var.mo16164s()) {
                C1647u5<?> a2 = s5Var.mo15856a(a);
                s5Var.mo16144a("network-parse-complete");
                if (s5Var.mo16169w() && a2.f2365b != null) {
                    this.f2154c.mo15750a(s5Var.mo16153e(), a2.f2365b);
                    s5Var.mo16144a("network-cache-written");
                }
                s5Var.mo16167u();
                this.f2155d.mo15946a(s5Var, a2);
                s5Var.mo16142a(a2);
                s5Var.mo16140a(4);
                return;
            }
            s5Var.mo16149b("not-modified");
            s5Var.mo16168v();
            s5Var.mo16140a(4);
        } catch (C1718z5 e) {
            e.mo16310a(SystemClock.elapsedRealtime() - elapsedRealtime);
            m3178a(s5Var, e);
            s5Var.mo16168v();
        } catch (Exception e2) {
            C1318a6.m2232a(e2, "Unhandled exception %s", e2.toString());
            C1718z5 z5Var = new C1718z5((Throwable) e2);
            z5Var.mo16310a(SystemClock.elapsedRealtime() - elapsedRealtime);
            this.f2155d.mo15948a(s5Var, z5Var);
            s5Var.mo16168v();
        } catch (Throwable th) {
            s5Var.mo16140a(4);
            throw th;
        }
    }

    /* renamed from: a */
    private void m3178a(C1615s5<?> s5Var, C1718z5 z5Var) {
        this.f2155d.mo15948a(s5Var, s5Var.mo16148b(z5Var));
    }
}

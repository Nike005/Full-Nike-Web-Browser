package com.tappx.p048a;

import android.os.Handler;
import java.util.concurrent.Executor;

/* renamed from: com.tappx.a.l5 */
public class C1513l5 implements C1660v5 {

    /* renamed from: a */
    private final Executor f2057a;

    /* renamed from: com.tappx.a.l5$a */
    class C1514a implements Executor {

        /* renamed from: a */
        final /* synthetic */ Handler f2058a;

        C1514a(C1513l5 l5Var, Handler handler) {
            this.f2058a = handler;
        }

        public void execute(Runnable runnable) {
            this.f2058a.post(runnable);
        }
    }

    /* renamed from: com.tappx.a.l5$b */
    private static class C1515b implements Runnable {

        /* renamed from: a */
        private final C1615s5 f2059a;

        /* renamed from: b */
        private final C1647u5 f2060b;

        /* renamed from: c */
        private final Runnable f2061c;

        public C1515b(C1615s5 s5Var, C1647u5 u5Var, Runnable runnable) {
            this.f2059a = s5Var;
            this.f2060b = u5Var;
            this.f2061c = runnable;
        }

        public void run() {
            if (this.f2059a.mo16165t()) {
                this.f2059a.mo16149b("canceled-at-delivery");
                return;
            }
            if (this.f2060b.mo16217a()) {
                this.f2059a.mo15858a(this.f2060b.f2364a);
            } else {
                this.f2059a.mo16143a(this.f2060b.f2366c);
            }
            if (this.f2060b.f2367d) {
                this.f2059a.mo16144a("intermediate-response");
            } else {
                this.f2059a.mo16149b("done");
            }
            Runnable runnable = this.f2061c;
            if (runnable != null) {
                runnable.run();
            }
        }
    }

    public C1513l5(Handler handler) {
        this.f2057a = new C1514a(this, handler);
    }

    /* renamed from: a */
    public void mo15946a(C1615s5<?> s5Var, C1647u5<?> u5Var) {
        mo15947a(s5Var, u5Var, (Runnable) null);
    }

    /* renamed from: a */
    public void mo15947a(C1615s5<?> s5Var, C1647u5<?> u5Var, Runnable runnable) {
        s5Var.mo16167u();
        s5Var.mo16144a("post-response");
        this.f2057a.execute(new C1515b(s5Var, u5Var, runnable));
    }

    /* renamed from: a */
    public void mo15948a(C1615s5<?> s5Var, C1718z5 z5Var) {
        s5Var.mo16144a("post-error");
        this.f2057a.execute(new C1515b(s5Var, C1647u5.m3482a(z5Var), (Runnable) null));
    }
}

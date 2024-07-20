package com.tappx.p048a;

import android.os.Handler;
import android.os.Looper;

/* renamed from: com.tappx.a.n1 */
public class C1533n1 {

    /* renamed from: a */
    private final Handler f2101a;
    /* access modifiers changed from: private */

    /* renamed from: b */
    public C1535b f2102b;

    /* renamed from: c */
    private final Runnable f2103c;

    /* renamed from: com.tappx.a.n1$a */
    class C1534a implements Runnable {
        C1534a() {
        }

        public void run() {
            if (C1533n1.this.f2102b != null) {
                C1533n1.this.f2102b.mo15663a();
            }
        }
    }

    /* renamed from: com.tappx.a.n1$b */
    public interface C1535b {
        /* renamed from: a */
        void mo15663a();
    }

    C1533n1(Handler handler) {
        this.f2103c = new C1534a();
        this.f2101a = handler;
    }

    /* renamed from: b */
    private void m3060b() {
        this.f2101a.removeCallbacks(this.f2103c);
    }

    /* renamed from: a */
    public void mo15977a(C1535b bVar) {
        this.f2102b = bVar;
    }

    /* renamed from: a */
    public void mo15976a(long j) {
        m3060b();
        this.f2101a.postDelayed(this.f2103c, j);
    }

    /* renamed from: a */
    public void mo15975a() {
        m3060b();
    }

    public C1533n1() {
        this(new Handler(Looper.getMainLooper()));
    }
}

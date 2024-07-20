package com.tappx.p048a;

import android.os.Handler;
import android.os.Looper;

/* renamed from: com.tappx.a.f3 */
public class C1410f3 {

    /* renamed from: a */
    private final Handler f1832a;

    /* renamed from: b */
    private final long f1833b;

    /* renamed from: c */
    private final long f1834c;

    /* renamed from: d */
    private final long f1835d;

    /* renamed from: e */
    private long f1836e;
    /* access modifiers changed from: private */

    /* renamed from: f */
    public C1412b f1837f;
    /* access modifiers changed from: private */

    /* renamed from: g */
    public long f1838g;

    /* renamed from: h */
    private long f1839h;

    /* renamed from: i */
    private boolean f1840i;

    /* renamed from: j */
    private final Runnable f1841j;

    /* renamed from: com.tappx.a.f3$a */
    class C1411a implements Runnable {
        C1411a() {
        }

        public void run() {
            long unused = C1410f3.this.f1838g = 0;
            if (C1410f3.this.f1837f != null) {
                C1410f3.this.f1837f.mo15794a();
            }
        }
    }

    /* renamed from: com.tappx.a.f3$b */
    public interface C1412b {
        /* renamed from: a */
        void mo15794a();
    }

    C1410f3(Handler handler, long j, long j2, long j3) {
        this.f1840i = true;
        this.f1841j = new C1411a();
        this.f1832a = handler;
        this.f1833b = j;
        this.f1834c = j2;
        this.f1835d = j3;
        mo15786a(j3);
    }

    /* renamed from: f */
    private long m2653f() {
        return System.currentTimeMillis();
    }

    /* renamed from: g */
    private void m2654g() {
        this.f1832a.removeCallbacks(this.f1841j);
    }

    /* renamed from: b */
    public void mo15789b() {
        mo15786a(this.f1835d);
    }

    /* renamed from: c */
    public void mo15790c() {
        if (this.f1840i) {
            long j = this.f1836e;
            long j2 = this.f1838g;
            if (j2 > 0 && j2 < j) {
                j -= j2;
            }
            m2654g();
            this.f1832a.postDelayed(this.f1841j, j);
            this.f1839h = m2653f();
        }
    }

    /* renamed from: d */
    public void mo15791d() {
        if (this.f1840i) {
            this.f1838g = 0;
            mo15790c();
        }
    }

    /* renamed from: e */
    public void mo15792e() {
        mo15785a();
        this.f1838g = 0;
    }

    /* renamed from: a */
    public void mo15787a(C1412b bVar) {
        this.f1837f = bVar;
    }

    /* renamed from: a */
    public void mo15785a() {
        m2654g();
        this.f1838g += m2653f() - this.f1839h;
    }

    /* renamed from: a */
    public void mo15786a(long j) {
        long j2 = this.f1833b;
        if (j < j2) {
            j = j2;
        }
        long j3 = this.f1834c;
        if (j > j3) {
            j = j3;
        }
        this.f1836e = j;
    }

    /* renamed from: a */
    public void mo15788a(boolean z) {
        this.f1840i = z;
        if (!z) {
            mo15792e();
        }
    }

    public C1410f3(long j, long j2, long j3) {
        this(new Handler(Looper.getMainLooper()), j, j2, j3);
    }
}

package com.tappx.p048a;

import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import com.tappx.p048a.C1529n;

/* renamed from: com.tappx.a.s3 */
public class C1610s3 {
    /* access modifiers changed from: private */

    /* renamed from: a */
    public final Handler f2265a = new Handler(Looper.getMainLooper());
    /* access modifiers changed from: private */

    /* renamed from: b */
    public int f2266b = -1;
    /* access modifiers changed from: private */

    /* renamed from: c */
    public long f2267c;
    /* access modifiers changed from: private */

    /* renamed from: d */
    public C1612b f2268d;
    /* access modifiers changed from: private */

    /* renamed from: e */
    public final Runnable f2269e = new C1611a();

    /* renamed from: com.tappx.a.s3$a */
    class C1611a implements Runnable {
        C1611a() {
        }

        public void run() {
            int floor = (int) Math.floor((double) (((float) (C1610s3.this.f2267c - C1610s3.this.mo16127a())) / 1000.0f));
            if (floor > 0) {
                C1610s3.this.f2265a.removeCallbacks(C1610s3.this.f2269e);
                C1610s3.this.f2265a.postDelayed(C1610s3.this.f2269e, 200);
            }
            if (floor != C1610s3.this.f2266b) {
                int unused = C1610s3.this.f2266b = floor;
                if (C1610s3.this.f2268d != null) {
                    C1610s3.this.f2268d.mo16062a(floor);
                }
            }
        }
    }

    /* renamed from: com.tappx.a.s3$b */
    public interface C1612b {
        /* renamed from: a */
        void mo16062a(int i);
    }

    /* renamed from: b */
    public boolean mo16130b() {
        return this.f2267c == 0 || mo16127a() > this.f2267c;
    }

    /* renamed from: a */
    public void mo16128a(long j) {
        long j2 = C1529n.C1530a.f2100k;
        if (j > j2) {
            j = j2;
        }
        this.f2267c = mo16127a() + j;
        this.f2269e.run();
    }

    /* renamed from: a */
    public void mo16129a(C1612b bVar) {
        this.f2268d = bVar;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public long mo16127a() {
        return SystemClock.elapsedRealtime();
    }
}

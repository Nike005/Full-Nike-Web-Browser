package com.appnext.base.p082b;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;

/* renamed from: com.appnext.base.b.g */
public class C4903g {

    /* renamed from: fs */
    private static C4903g f4631fs;
    private static Context mContext;

    /* renamed from: ft */
    private Handler f4632ft;

    /* renamed from: fu */
    private Handler f4633fu;

    /* renamed from: fv */
    private HandlerThread f4634fv;

    public C4903g() {
        try {
            this.f4632ft = new Handler(Looper.getMainLooper());
            HandlerThread handlerThread = new HandlerThread("ExecutesManagerWorkerThread");
            this.f4634fv = handlerThread;
            handlerThread.start();
            this.f4633fu = new Handler(this.f4634fv.getLooper());
        } catch (Throwable unused) {
        }
    }

    /* renamed from: aN */
    public static C4903g m6575aN() {
        if (f4631fs == null) {
            synchronized (C4903g.class) {
                if (f4631fs == null) {
                    f4631fs = new C4903g();
                }
            }
        }
        return f4631fs;
    }

    /* renamed from: a */
    public final void mo41002a(Runnable runnable) {
        if (runnable != null) {
            try {
                this.f4632ft.post(runnable);
            } catch (Throwable unused) {
            }
        }
    }

    /* renamed from: a */
    public final void mo41003a(Runnable runnable, long j) {
        if (runnable != null) {
            try {
                this.f4632ft.postDelayed(runnable, j);
            } catch (Throwable unused) {
            }
        }
    }

    /* renamed from: b */
    public final void mo41004b(Runnable runnable) {
        try {
            this.f4633fu.post(runnable);
        } catch (Throwable unused) {
        }
    }

    /* renamed from: b */
    public final void mo41005b(Runnable runnable, long j) {
        if (runnable != null) {
            try {
                this.f4633fu.postDelayed(runnable, j);
            } catch (Throwable unused) {
            }
        }
    }

    /* access modifiers changed from: protected */
    public void finalize() {
        try {
            this.f4633fu.removeCallbacks((Runnable) null);
            this.f4634fv.quit();
            super.finalize();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }
}

package com.startapp.common;

import android.os.Build;
import com.startapp.common.p043a.C1270g;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: com.startapp.common.g */
/* compiled from: StartAppSDK */
public final class C1303g {
    /* access modifiers changed from: private */

    /* renamed from: a */
    public static final String f1565a = C1303g.class.getSimpleName();

    /* renamed from: b */
    private static final int f1566b = (Build.VERSION.SDK_INT < 22 ? 10 : 20);

    /* renamed from: c */
    private static final int f1567c = (Build.VERSION.SDK_INT < 22 ? 4 : 8);

    /* renamed from: d */
    private static final ThreadFactory f1568d = new ThreadFactory() {

        /* renamed from: a */
        private final AtomicInteger f1574a = new AtomicInteger(1);

        public Thread newThread(Runnable runnable) {
            return new Thread(runnable, "highPriorityThreadFactory #" + this.f1574a.getAndIncrement());
        }
    };

    /* renamed from: e */
    private static final ThreadFactory f1569e = new ThreadFactory() {

        /* renamed from: a */
        private final AtomicInteger f1575a = new AtomicInteger(1);

        public Thread newThread(Runnable runnable) {
            return new Thread(runnable, "defaultPriorityThreadFactory #" + this.f1575a.getAndIncrement());
        }
    };

    /* renamed from: f */
    private static final RejectedExecutionHandler f1570f = new RejectedExecutionHandler() {
        public void rejectedExecution(Runnable runnable, ThreadPoolExecutor threadPoolExecutor) {
            String a = C1303g.f1565a;
            C1270g.m2078a(a, 6, "ThreadPoolExecutor rejected execution! " + threadPoolExecutor);
        }
    };

    /* renamed from: g */
    private static final Executor f1571g;

    /* renamed from: h */
    private static final Executor f1572h;

    /* renamed from: i */
    private static final ScheduledExecutorService f1573i = new ScheduledThreadPoolExecutor(1);

    /* renamed from: com.startapp.common.g$a */
    /* compiled from: StartAppSDK */
    public enum C1307a {
        DEFAULT,
        HIGH
    }

    static {
        int i = f1566b;
        f1571g = new ThreadPoolExecutor(i, i, 20, TimeUnit.SECONDS, new LinkedBlockingQueue(), f1568d, f1570f);
        int i2 = f1567c;
        f1572h = new ThreadPoolExecutor(i2, i2, 20, TimeUnit.SECONDS, new LinkedBlockingQueue(), f1569e, f1570f);
    }

    /* renamed from: a */
    public static ScheduledFuture<?> m2205a(Runnable runnable, long j) {
        return f1573i.schedule(runnable, j, TimeUnit.MILLISECONDS);
    }

    /* renamed from: a */
    public static void m2206a(C1307a aVar, Runnable runnable) {
        Executor executor;
        try {
            if (aVar.equals(C1307a.HIGH)) {
                executor = f1571g;
            } else {
                executor = f1572h;
            }
            executor.execute(runnable);
        } catch (Exception unused) {
            String str = f1565a;
            C1270g.m2078a(str, 6, "executeWithPriority failed to execute! " + null);
        }
    }
}

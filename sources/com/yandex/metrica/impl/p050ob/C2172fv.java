package com.yandex.metrica.impl.p050ob;

import com.yandex.metrica.impl.p050ob.C2169fu;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* renamed from: com.yandex.metrica.impl.ob.fv */
public class C2172fv<T> implements C2169fu.C2170a, C2169fu.C2171b<T>, Future<T> {

    /* renamed from: a */
    private boolean f3746a = false;

    /* renamed from: b */
    private T f3747b;

    /* renamed from: c */
    private C2162fr f3748c;

    public boolean isCancelled() {
        return false;
    }

    /* renamed from: a */
    public static <E> C2172fv<E> m5746a() {
        return new C2172fv<>();
    }

    private C2172fv() {
    }

    public synchronized boolean cancel(boolean z) {
        return false;
    }

    public T get() throws InterruptedException, ExecutionException {
        try {
            return m5747a((Long) null);
        } catch (TimeoutException e) {
            throw new AssertionError(e);
        }
    }

    public T get(long j, TimeUnit timeUnit) throws InterruptedException, ExecutionException, TimeoutException {
        return m5747a(Long.valueOf(TimeUnit.MILLISECONDS.convert(j, timeUnit)));
    }

    /* renamed from: a */
    private synchronized T m5747a(Long l) throws InterruptedException, ExecutionException, TimeoutException {
        if (this.f3748c != null) {
            throw new ExecutionException(this.f3748c);
        } else if (this.f3746a) {
            return this.f3747b;
        } else {
            if (l == null) {
                wait(0);
            } else if (l.longValue() > 0) {
                wait(l.longValue());
            }
            if (this.f3748c != null) {
                throw new ExecutionException(this.f3748c);
            } else if (this.f3746a) {
                return this.f3747b;
            } else {
                throw new TimeoutException();
            }
        }
    }

    public synchronized boolean isDone() {
        return this.f3746a || this.f3748c != null || isCancelled();
    }

    /* renamed from: a */
    public synchronized void mo17761a(T t) {
        this.f3746a = true;
        this.f3747b = t;
        notifyAll();
    }

    /* renamed from: a */
    public synchronized void mo17763a(C2162fr frVar) {
        this.f3748c = frVar;
        notifyAll();
    }
}

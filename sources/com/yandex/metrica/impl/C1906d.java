package com.yandex.metrica.impl;

import java.util.concurrent.TimeUnit;

/* renamed from: com.yandex.metrica.impl.d */
public interface C1906d {

    /* renamed from: com.yandex.metrica.impl.d$a */
    public static class C1907a<T> {

        /* renamed from: a */
        public static final long f3150a = TimeUnit.SECONDS.toMillis(10);

        /* renamed from: b */
        private long f3151b;

        /* renamed from: c */
        private long f3152c;

        /* renamed from: d */
        private T f3153d;

        public C1907a() {
            this(f3150a);
        }

        public C1907a(long j) {
            this.f3152c = 0;
            this.f3153d = null;
            this.f3151b = j;
        }

        /* renamed from: a */
        public T mo17100a() {
            return this.f3153d;
        }

        /* renamed from: a */
        public void mo17101a(T t) {
            this.f3153d = t;
            this.f3152c = System.currentTimeMillis();
        }

        /* renamed from: b */
        public final boolean mo17102b() {
            return this.f3153d == null;
        }

        /* renamed from: c */
        public final boolean mo17103c() {
            long j = this.f3151b;
            long currentTimeMillis = System.currentTimeMillis() - this.f3152c;
            return currentTimeMillis > j || currentTimeMillis < 0;
        }

        /* renamed from: d */
        public T mo17104d() {
            if (mo17103c()) {
                return null;
            }
            return this.f3153d;
        }
    }
}

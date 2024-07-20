package p054rx.internal.util;

import java.util.concurrent.atomic.AtomicLong;
import p054rx.Producer;

/* renamed from: rx.internal.util.BackpressureDrainManager */
public final class BackpressureDrainManager extends AtomicLong implements Producer {
    private static final long serialVersionUID = 2826241102729529449L;
    final BackpressureQueueCallback actual;
    boolean emitting;
    Throwable exception;
    volatile boolean terminated;

    /* renamed from: rx.internal.util.BackpressureDrainManager$BackpressureQueueCallback */
    public interface BackpressureQueueCallback {
        boolean accept(Object obj);

        void complete(Throwable th);

        Object peek();

        Object poll();
    }

    public BackpressureDrainManager(BackpressureQueueCallback backpressureQueueCallback) {
        this.actual = backpressureQueueCallback;
    }

    public boolean isTerminated() {
        return this.terminated;
    }

    public void terminate() {
        this.terminated = true;
    }

    public void terminate(Throwable th) {
        if (!this.terminated) {
            this.exception = th;
            this.terminated = true;
        }
    }

    public void terminateAndDrain() {
        this.terminated = true;
        drain();
    }

    public void terminateAndDrain(Throwable th) {
        if (!this.terminated) {
            this.exception = th;
            this.terminated = true;
            drain();
        }
    }

    public void request(long j) {
        boolean z;
        if (j != 0) {
            while (true) {
                long j2 = get();
                boolean z2 = true;
                z = j2 == 0;
                long j3 = Long.MAX_VALUE;
                if (j2 == Long.MAX_VALUE) {
                    break;
                }
                if (j == Long.MAX_VALUE) {
                    j3 = j;
                } else {
                    if (j2 <= Long.MAX_VALUE - j) {
                        j3 = j2 + j;
                    }
                    z2 = z;
                }
                if (compareAndSet(j2, j3)) {
                    z = z2;
                    break;
                }
            }
            if (z) {
                drain();
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:?, code lost:
        r5 = r13.actual;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0014, code lost:
        r6 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0019, code lost:
        if (r2 > 0) goto L_0x001d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x001b, code lost:
        if (r1 == false) goto L_0x0038;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x001d, code lost:
        if (r1 == false) goto L_0x0032;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0023, code lost:
        if (r5.peek() != null) goto L_0x002d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:?, code lost:
        r5.complete(r13.exception);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x002a, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x002b, code lost:
        r1 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x002f, code lost:
        if (r2 != 0) goto L_0x0032;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:?, code lost:
        r9 = r5.poll();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0036, code lost:
        if (r9 != null) goto L_0x0079;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0038, code lost:
        monitor-enter(r13);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:?, code lost:
        r1 = r13.terminated;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x003f, code lost:
        if (r5.peek() == null) goto L_0x0043;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0041, code lost:
        r2 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0043, code lost:
        r2 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x004f, code lost:
        if (get() != Long.MAX_VALUE) goto L_0x005b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x0051, code lost:
        if (r2 != false) goto L_0x0059;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x0053, code lost:
        if (r1 != false) goto L_0x0059;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:?, code lost:
        r13.emitting = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x0057, code lost:
        monitor-exit(r13);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x0058, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x0059, code lost:
        r2 = Long.MAX_VALUE;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:?, code lost:
        r9 = addAndGet((long) (-r6));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x0063, code lost:
        if (r9 == 0) goto L_0x0067;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x0065, code lost:
        if (r2 != false) goto L_0x006c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x0067, code lost:
        if (r1 == false) goto L_0x006f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x0069, code lost:
        if (r2 == false) goto L_0x006c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x006c, code lost:
        r2 = r9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x006d, code lost:
        monitor-exit(r13);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:?, code lost:
        r13.emitting = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x0071, code lost:
        monitor-exit(r13);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x0072, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x0073, code lost:
        r1 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x0074, code lost:
        r0 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x0075, code lost:
        monitor-exit(r13);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:?, code lost:
        throw r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x0077, code lost:
        r1 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x007d, code lost:
        if (r5.accept(r9) == false) goto L_0x0080;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x007f, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x0080, code lost:
        r2 = r2 - 1;
        r6 = r6 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x0086, code lost:
        r1 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:69:0x0087, code lost:
        r0 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x0088, code lost:
        if (r0 == false) goto L_0x008a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:0x008a, code lost:
        monitor-enter(r13);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:73:?, code lost:
        r13.emitting = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:79:0x0092, code lost:
        throw r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x000d, code lost:
        r2 = get();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void drain() {
        /*
            r13 = this;
            monitor-enter(r13)
            boolean r0 = r13.emitting     // Catch:{ all -> 0x0093 }
            if (r0 == 0) goto L_0x0007
            monitor-exit(r13)     // Catch:{ all -> 0x0093 }
            return
        L_0x0007:
            r0 = 1
            r13.emitting = r0     // Catch:{ all -> 0x0093 }
            boolean r1 = r13.terminated     // Catch:{ all -> 0x0093 }
            monitor-exit(r13)     // Catch:{ all -> 0x0093 }
            long r2 = r13.get()
            r4 = 0
            rx.internal.util.BackpressureDrainManager$BackpressureQueueCallback r5 = r13.actual     // Catch:{ all -> 0x0086 }
        L_0x0014:
            r6 = 0
        L_0x0015:
            r7 = 0
            int r9 = (r2 > r7 ? 1 : (r2 == r7 ? 0 : -1))
            if (r9 > 0) goto L_0x001d
            if (r1 == 0) goto L_0x0038
        L_0x001d:
            if (r1 == 0) goto L_0x0032
            java.lang.Object r9 = r5.peek()     // Catch:{ all -> 0x0086 }
            if (r9 != 0) goto L_0x002d
            java.lang.Throwable r1 = r13.exception     // Catch:{ all -> 0x002b }
            r5.complete(r1)     // Catch:{ all -> 0x002b }
            return
        L_0x002b:
            r1 = move-exception
            goto L_0x0088
        L_0x002d:
            int r9 = (r2 > r7 ? 1 : (r2 == r7 ? 0 : -1))
            if (r9 != 0) goto L_0x0032
            goto L_0x0038
        L_0x0032:
            java.lang.Object r9 = r5.poll()     // Catch:{ all -> 0x0086 }
            if (r9 != 0) goto L_0x0079
        L_0x0038:
            monitor-enter(r13)     // Catch:{ all -> 0x0086 }
            boolean r1 = r13.terminated     // Catch:{ all -> 0x0073 }
            java.lang.Object r2 = r5.peek()     // Catch:{ all -> 0x0073 }
            if (r2 == 0) goto L_0x0043
            r2 = 1
            goto L_0x0044
        L_0x0043:
            r2 = 0
        L_0x0044:
            long r9 = r13.get()     // Catch:{ all -> 0x0073 }
            r11 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
            int r3 = (r9 > r11 ? 1 : (r9 == r11 ? 0 : -1))
            if (r3 != 0) goto L_0x005b
            if (r2 != 0) goto L_0x0059
            if (r1 != 0) goto L_0x0059
            r13.emitting = r4     // Catch:{ all -> 0x0077 }
            monitor-exit(r13)     // Catch:{ all -> 0x0077 }
            return
        L_0x0059:
            r2 = r11
            goto L_0x006d
        L_0x005b:
            int r3 = -r6
            long r9 = (long) r3
            long r9 = r13.addAndGet(r9)     // Catch:{ all -> 0x0073 }
            int r3 = (r9 > r7 ? 1 : (r9 == r7 ? 0 : -1))
            if (r3 == 0) goto L_0x0067
            if (r2 != 0) goto L_0x006c
        L_0x0067:
            if (r1 == 0) goto L_0x006f
            if (r2 == 0) goto L_0x006c
            goto L_0x006f
        L_0x006c:
            r2 = r9
        L_0x006d:
            monitor-exit(r13)     // Catch:{ all -> 0x0073 }
            goto L_0x0014
        L_0x006f:
            r13.emitting = r4     // Catch:{ all -> 0x0077 }
            monitor-exit(r13)     // Catch:{ all -> 0x0077 }
            return
        L_0x0073:
            r1 = move-exception
            r0 = 0
        L_0x0075:
            monitor-exit(r13)     // Catch:{ all -> 0x0077 }
            throw r1     // Catch:{ all -> 0x002b }
        L_0x0077:
            r1 = move-exception
            goto L_0x0075
        L_0x0079:
            boolean r7 = r5.accept(r9)     // Catch:{ all -> 0x0086 }
            if (r7 == 0) goto L_0x0080
            return
        L_0x0080:
            r7 = 1
            long r2 = r2 - r7
            int r6 = r6 + 1
            goto L_0x0015
        L_0x0086:
            r1 = move-exception
            r0 = 0
        L_0x0088:
            if (r0 != 0) goto L_0x0092
            monitor-enter(r13)
            r13.emitting = r4     // Catch:{ all -> 0x008f }
            monitor-exit(r13)     // Catch:{ all -> 0x008f }
            goto L_0x0092
        L_0x008f:
            r0 = move-exception
            monitor-exit(r13)     // Catch:{ all -> 0x008f }
            throw r0
        L_0x0092:
            throw r1
        L_0x0093:
            r0 = move-exception
            monitor-exit(r13)     // Catch:{ all -> 0x0093 }
            goto L_0x0097
        L_0x0096:
            throw r0
        L_0x0097:
            goto L_0x0096
        */
        throw new UnsupportedOperationException("Method not decompiled: p054rx.internal.util.BackpressureDrainManager.drain():void");
    }
}

package p054rx.internal.operators;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import p054rx.Observable;
import p054rx.Observer;
import p054rx.Producer;
import p054rx.Subscriber;
import p054rx.Subscription;
import p054rx.internal.util.LinkedArrayList;
import p054rx.subscriptions.SerialSubscription;

/* renamed from: rx.internal.operators.CachedObservable */
public final class CachedObservable<T> extends Observable<T> {
    private final CacheState<T> state;

    public static <T> CachedObservable<T> from(Observable<? extends T> observable) {
        return from(observable, 16);
    }

    public static <T> CachedObservable<T> from(Observable<? extends T> observable, int i) {
        if (i >= 1) {
            CacheState cacheState = new CacheState(observable, i);
            return new CachedObservable<>(new CachedSubscribe(cacheState), cacheState);
        }
        throw new IllegalArgumentException("capacityHint > 0 required");
    }

    private CachedObservable(Observable.OnSubscribe<T> onSubscribe, CacheState<T> cacheState) {
        super(onSubscribe);
        this.state = cacheState;
    }

    /* access modifiers changed from: package-private */
    public boolean isConnected() {
        return this.state.isConnected;
    }

    /* access modifiers changed from: package-private */
    public boolean hasObservers() {
        return this.state.producers.length != 0;
    }

    /* renamed from: rx.internal.operators.CachedObservable$CacheState */
    static final class CacheState<T> extends LinkedArrayList implements Observer<T> {
        static final ReplayProducer<?>[] EMPTY = new ReplayProducer[0];
        final SerialSubscription connection = new SerialSubscription();
        volatile boolean isConnected;
        volatile ReplayProducer<?>[] producers = EMPTY;
        final Observable<? extends T> source;
        boolean sourceDone;

        public CacheState(Observable<? extends T> observable, int i) {
            super(i);
            this.source = observable;
        }

        public void addProducer(ReplayProducer<T> replayProducer) {
            synchronized (this.connection) {
                ReplayProducer<?>[] replayProducerArr = this.producers;
                int length = replayProducerArr.length;
                ReplayProducer<?>[] replayProducerArr2 = new ReplayProducer[(length + 1)];
                System.arraycopy(replayProducerArr, 0, replayProducerArr2, 0, length);
                replayProducerArr2[length] = replayProducer;
                this.producers = replayProducerArr2;
            }
        }

        public void removeProducer(ReplayProducer<T> replayProducer) {
            synchronized (this.connection) {
                ReplayProducer<?>[] replayProducerArr = this.producers;
                int length = replayProducerArr.length;
                int i = -1;
                int i2 = 0;
                while (true) {
                    if (i2 >= length) {
                        break;
                    } else if (replayProducerArr[i2].equals(replayProducer)) {
                        i = i2;
                        break;
                    } else {
                        i2++;
                    }
                }
                if (i >= 0) {
                    if (length == 1) {
                        this.producers = EMPTY;
                        return;
                    }
                    ReplayProducer<?>[] replayProducerArr2 = new ReplayProducer[(length - 1)];
                    System.arraycopy(replayProducerArr, 0, replayProducerArr2, 0, i);
                    System.arraycopy(replayProducerArr, i + 1, replayProducerArr2, i, (length - i) - 1);
                    this.producers = replayProducerArr2;
                }
            }
        }

        public void connect() {
            C26501 r0 = new Subscriber<T>() {
                public void onNext(T t) {
                    CacheState.this.onNext(t);
                }

                public void onError(Throwable th) {
                    CacheState.this.onError(th);
                }

                public void onCompleted() {
                    CacheState.this.onCompleted();
                }
            };
            this.connection.set(r0);
            this.source.unsafeSubscribe(r0);
            this.isConnected = true;
        }

        public void onNext(T t) {
            if (!this.sourceDone) {
                add(NotificationLite.next(t));
                dispatch();
            }
        }

        public void onError(Throwable th) {
            if (!this.sourceDone) {
                this.sourceDone = true;
                add(NotificationLite.error(th));
                this.connection.unsubscribe();
                dispatch();
            }
        }

        public void onCompleted() {
            if (!this.sourceDone) {
                this.sourceDone = true;
                add(NotificationLite.completed());
                this.connection.unsubscribe();
                dispatch();
            }
        }

        /* access modifiers changed from: package-private */
        public void dispatch() {
            for (ReplayProducer<?> replay : this.producers) {
                replay.replay();
            }
        }
    }

    /* renamed from: rx.internal.operators.CachedObservable$CachedSubscribe */
    static final class CachedSubscribe<T> extends AtomicBoolean implements Observable.OnSubscribe<T> {
        private static final long serialVersionUID = -2817751667698696782L;
        final CacheState<T> state;

        public CachedSubscribe(CacheState<T> cacheState) {
            this.state = cacheState;
        }

        public void call(Subscriber<? super T> subscriber) {
            ReplayProducer replayProducer = new ReplayProducer(subscriber, this.state);
            this.state.addProducer(replayProducer);
            subscriber.add(replayProducer);
            subscriber.setProducer(replayProducer);
            if (!get() && compareAndSet(false, true)) {
                this.state.connect();
            }
        }
    }

    /* renamed from: rx.internal.operators.CachedObservable$ReplayProducer */
    static final class ReplayProducer<T> extends AtomicLong implements Producer, Subscription {
        private static final long serialVersionUID = -2557562030197141021L;
        final Subscriber<? super T> child;
        Object[] currentBuffer;
        int currentIndexInBuffer;
        boolean emitting;
        int index;
        boolean missed;
        final CacheState<T> state;

        public ReplayProducer(Subscriber<? super T> subscriber, CacheState<T> cacheState) {
            this.child = subscriber;
            this.state = cacheState;
        }

        public void request(long j) {
            long j2;
            long j3;
            do {
                j2 = get();
                if (j2 >= 0) {
                    j3 = j2 + j;
                    if (j3 < 0) {
                        j3 = Long.MAX_VALUE;
                    }
                } else {
                    return;
                }
            } while (!compareAndSet(j2, j3));
            replay();
        }

        public long produced(long j) {
            return addAndGet(-j);
        }

        public boolean isUnsubscribed() {
            return get() < 0;
        }

        public void unsubscribe() {
            if (get() >= 0 && getAndSet(-1) >= 0) {
                this.state.removeProducer(this);
            }
        }

        /* JADX WARNING: Code restructure failed: missing block: B:100:?, code lost:
            r15.emitting = false;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:106:0x00e9, code lost:
            throw r1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:11:?, code lost:
            r2 = r15.child;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:120:?, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:122:?, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:12:0x0010, code lost:
            r3 = get();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:13:0x0018, code lost:
            if (r3 >= 0) goto L_0x001b;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:14:0x001a, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:15:0x001b, code lost:
            r7 = r15.state.size();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:16:0x0021, code lost:
            if (r7 == 0) goto L_0x00c5;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:17:0x0023, code lost:
            r8 = r15.currentBuffer;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:18:0x0025, code lost:
            if (r8 != null) goto L_0x002f;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:19:0x0027, code lost:
            r8 = r15.state.head();
            r15.currentBuffer = r8;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:20:0x002f, code lost:
            r9 = r8.length - 1;
            r10 = r15.index;
            r11 = r15.currentIndexInBuffer;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:21:0x0037, code lost:
            if (r3 != 0) goto L_0x005e;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:22:0x0039, code lost:
            r3 = r8[r11];
         */
        /* JADX WARNING: Code restructure failed: missing block: B:23:0x003f, code lost:
            if (p054rx.internal.operators.NotificationLite.isCompleted(r3) == false) goto L_0x004d;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:24:0x0041, code lost:
            r2.onCompleted();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:26:?, code lost:
            unsubscribe();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:27:0x0047, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:28:0x0048, code lost:
            r2 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:29:0x0049, code lost:
            r1 = r2;
            r4 = true;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:32:0x0051, code lost:
            if (p054rx.internal.operators.NotificationLite.isError(r3) == false) goto L_0x00c5;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:33:0x0053, code lost:
            r2.onError(p054rx.internal.operators.NotificationLite.getError(r3));
         */
        /* JADX WARNING: Code restructure failed: missing block: B:35:?, code lost:
            unsubscribe();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:36:0x005d, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:38:0x0060, code lost:
            if (r3 <= 0) goto L_0x00c5;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:39:0x0062, code lost:
            r12 = 0;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:40:0x0063, code lost:
            if (r10 >= r7) goto L_0x00b4;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:42:0x0067, code lost:
            if (r3 <= 0) goto L_0x00b4;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:45:0x006d, code lost:
            if (r2.isUnsubscribed() == false) goto L_0x0070;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:46:0x006f, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:47:0x0070, code lost:
            if (r11 != r9) goto L_0x0079;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:48:0x0072, code lost:
            r8 = (java.lang.Object[]) r8[r9];
            r11 = 0;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:49:0x0079, code lost:
            r13 = r8[r11];
         */
        /* JADX WARNING: Code restructure failed: missing block: B:52:0x007f, code lost:
            if (p054rx.internal.operators.NotificationLite.accept(r2, r13) == false) goto L_0x0088;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:54:?, code lost:
            unsubscribe();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:55:0x0084, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:56:0x0085, code lost:
            r3 = th;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:57:0x0086, code lost:
            r4 = true;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:58:0x0088, code lost:
            r11 = r11 + 1;
            r10 = r10 + 1;
            r3 = r3 - 1;
            r12 = r12 + 1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:59:0x0092, code lost:
            r3 = th;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:60:0x0093, code lost:
            r4 = false;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:62:?, code lost:
            p054rx.exceptions.Exceptions.throwIfFatal(r3);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:64:?, code lost:
            unsubscribe();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:65:0x009e, code lost:
            if (p054rx.internal.operators.NotificationLite.isError(r13) != false) goto L_?;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:68:0x00a6, code lost:
            r2.onError(p054rx.exceptions.OnErrorThrowable.addValueAsLastCause(r3, p054rx.internal.operators.NotificationLite.getValue(r13)));
         */
        /* JADX WARNING: Code restructure failed: missing block: B:69:0x00b2, code lost:
            r1 = th;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:72:0x00b8, code lost:
            if (r2.isUnsubscribed() == false) goto L_0x00bb;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:73:0x00ba, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:74:0x00bb, code lost:
            r15.index = r10;
            r15.currentIndexInBuffer = r11;
            r15.currentBuffer = r8;
            produced((long) r12);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:75:0x00c5, code lost:
            monitor-enter(r15);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:78:0x00c8, code lost:
            if (r15.missed != false) goto L_0x00ce;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:79:0x00ca, code lost:
            r15.emitting = false;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:81:?, code lost:
            monitor-exit(r15);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:82:0x00cd, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:84:?, code lost:
            r15.missed = false;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:85:0x00d0, code lost:
            monitor-exit(r15);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:86:0x00d3, code lost:
            r2 = th;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:87:0x00d4, code lost:
            r1 = false;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:89:?, code lost:
            monitor-exit(r15);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:91:?, code lost:
            throw r2;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:92:0x00d7, code lost:
            r2 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:93:0x00d8, code lost:
            r4 = r1;
            r1 = r2;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:94:0x00db, code lost:
            r2 = th;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:95:0x00dd, code lost:
            r1 = th;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:96:0x00de, code lost:
            r4 = false;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:97:0x00df, code lost:
            if (r4 == false) goto L_0x00e1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:98:0x00e1, code lost:
            monitor-enter(r15);
         */
        /* JADX WARNING: Removed duplicated region for block: B:122:? A[RETURN, SYNTHETIC] */
        /* JADX WARNING: Removed duplicated region for block: B:68:0x00a6 A[Catch:{ all -> 0x0048 }] */
        /* JADX WARNING: Removed duplicated region for block: B:98:0x00e1  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void replay() {
            /*
                r15 = this;
                monitor-enter(r15)
                boolean r0 = r15.emitting     // Catch:{ all -> 0x00ea }
                r1 = 1
                if (r0 == 0) goto L_0x000a
                r15.missed = r1     // Catch:{ all -> 0x00ea }
                monitor-exit(r15)     // Catch:{ all -> 0x00ea }
                return
            L_0x000a:
                r15.emitting = r1     // Catch:{ all -> 0x00ea }
                monitor-exit(r15)     // Catch:{ all -> 0x00ea }
                r0 = 0
                rx.Subscriber<? super T> r2 = r15.child     // Catch:{ all -> 0x00dd }
            L_0x0010:
                long r3 = r15.get()     // Catch:{ all -> 0x00dd }
                r5 = 0
                int r7 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
                if (r7 >= 0) goto L_0x001b
                return
            L_0x001b:
                rx.internal.operators.CachedObservable$CacheState<T> r7 = r15.state     // Catch:{ all -> 0x00dd }
                int r7 = r7.size()     // Catch:{ all -> 0x00dd }
                if (r7 == 0) goto L_0x00c5
                java.lang.Object[] r8 = r15.currentBuffer     // Catch:{ all -> 0x00dd }
                if (r8 != 0) goto L_0x002f
                rx.internal.operators.CachedObservable$CacheState<T> r8 = r15.state     // Catch:{ all -> 0x00dd }
                java.lang.Object[] r8 = r8.head()     // Catch:{ all -> 0x00dd }
                r15.currentBuffer = r8     // Catch:{ all -> 0x00dd }
            L_0x002f:
                int r9 = r8.length     // Catch:{ all -> 0x00dd }
                int r9 = r9 - r1
                int r10 = r15.index     // Catch:{ all -> 0x00dd }
                int r11 = r15.currentIndexInBuffer     // Catch:{ all -> 0x00dd }
                int r12 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
                if (r12 != 0) goto L_0x005e
                r3 = r8[r11]     // Catch:{ all -> 0x00dd }
                boolean r4 = p054rx.internal.operators.NotificationLite.isCompleted(r3)     // Catch:{ all -> 0x00dd }
                if (r4 == 0) goto L_0x004d
                r2.onCompleted()     // Catch:{ all -> 0x00dd }
                r15.unsubscribe()     // Catch:{ all -> 0x0048 }
                return
            L_0x0048:
                r2 = move-exception
                r1 = r2
                r4 = 1
                goto L_0x00df
            L_0x004d:
                boolean r4 = p054rx.internal.operators.NotificationLite.isError(r3)     // Catch:{ all -> 0x00dd }
                if (r4 == 0) goto L_0x00c5
                java.lang.Throwable r3 = p054rx.internal.operators.NotificationLite.getError(r3)     // Catch:{ all -> 0x00dd }
                r2.onError(r3)     // Catch:{ all -> 0x00dd }
                r15.unsubscribe()     // Catch:{ all -> 0x0048 }
                return
            L_0x005e:
                int r12 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
                if (r12 <= 0) goto L_0x00c5
                r12 = 0
            L_0x0063:
                if (r10 >= r7) goto L_0x00b4
                int r13 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
                if (r13 <= 0) goto L_0x00b4
                boolean r13 = r2.isUnsubscribed()     // Catch:{ all -> 0x00dd }
                if (r13 == 0) goto L_0x0070
                return
            L_0x0070:
                if (r11 != r9) goto L_0x0079
                r8 = r8[r9]     // Catch:{ all -> 0x00dd }
                java.lang.Object[] r8 = (java.lang.Object[]) r8     // Catch:{ all -> 0x00dd }
                java.lang.Object[] r8 = (java.lang.Object[]) r8     // Catch:{ all -> 0x00dd }
                r11 = 0
            L_0x0079:
                r13 = r8[r11]     // Catch:{ all -> 0x00dd }
                boolean r14 = p054rx.internal.operators.NotificationLite.accept(r2, r13)     // Catch:{ all -> 0x0092 }
                if (r14 == 0) goto L_0x0088
                r15.unsubscribe()     // Catch:{ all -> 0x0085 }
                return
            L_0x0085:
                r3 = move-exception
                r4 = 1
                goto L_0x0094
            L_0x0088:
                int r11 = r11 + 1
                int r10 = r10 + 1
                r13 = 1
                long r3 = r3 - r13
                int r12 = r12 + 1
                goto L_0x0063
            L_0x0092:
                r3 = move-exception
                r4 = 0
            L_0x0094:
                p054rx.exceptions.Exceptions.throwIfFatal(r3)     // Catch:{ all -> 0x00b2 }
                r15.unsubscribe()     // Catch:{ all -> 0x0048 }
                boolean r4 = p054rx.internal.operators.NotificationLite.isError(r13)     // Catch:{ all -> 0x0048 }
                if (r4 != 0) goto L_0x00b1
                boolean r4 = p054rx.internal.operators.NotificationLite.isCompleted(r13)     // Catch:{ all -> 0x0048 }
                if (r4 != 0) goto L_0x00b1
                java.lang.Object r4 = p054rx.internal.operators.NotificationLite.getValue(r13)     // Catch:{ all -> 0x0048 }
                java.lang.Throwable r3 = p054rx.exceptions.OnErrorThrowable.addValueAsLastCause(r3, r4)     // Catch:{ all -> 0x0048 }
                r2.onError(r3)     // Catch:{ all -> 0x0048 }
            L_0x00b1:
                return
            L_0x00b2:
                r1 = move-exception
                goto L_0x00df
            L_0x00b4:
                boolean r3 = r2.isUnsubscribed()     // Catch:{ all -> 0x00dd }
                if (r3 == 0) goto L_0x00bb
                return
            L_0x00bb:
                r15.index = r10     // Catch:{ all -> 0x00dd }
                r15.currentIndexInBuffer = r11     // Catch:{ all -> 0x00dd }
                r15.currentBuffer = r8     // Catch:{ all -> 0x00dd }
                long r3 = (long) r12     // Catch:{ all -> 0x00dd }
                r15.produced(r3)     // Catch:{ all -> 0x00dd }
            L_0x00c5:
                monitor-enter(r15)     // Catch:{ all -> 0x00dd }
                boolean r3 = r15.missed     // Catch:{ all -> 0x00d3 }
                if (r3 != 0) goto L_0x00ce
                r15.emitting = r0     // Catch:{ all -> 0x00d3 }
                monitor-exit(r15)     // Catch:{ all -> 0x00db }
                return
            L_0x00ce:
                r15.missed = r0     // Catch:{ all -> 0x00d3 }
                monitor-exit(r15)     // Catch:{ all -> 0x00d3 }
                goto L_0x0010
            L_0x00d3:
                r2 = move-exception
                r1 = 0
            L_0x00d5:
                monitor-exit(r15)     // Catch:{ all -> 0x00db }
                throw r2     // Catch:{ all -> 0x00d7 }
            L_0x00d7:
                r2 = move-exception
                r4 = r1
                r1 = r2
                goto L_0x00df
            L_0x00db:
                r2 = move-exception
                goto L_0x00d5
            L_0x00dd:
                r1 = move-exception
                r4 = 0
            L_0x00df:
                if (r4 != 0) goto L_0x00e9
                monitor-enter(r15)
                r15.emitting = r0     // Catch:{ all -> 0x00e6 }
                monitor-exit(r15)     // Catch:{ all -> 0x00e6 }
                goto L_0x00e9
            L_0x00e6:
                r0 = move-exception
                monitor-exit(r15)     // Catch:{ all -> 0x00e6 }
                throw r0
            L_0x00e9:
                throw r1
            L_0x00ea:
                r0 = move-exception
                monitor-exit(r15)     // Catch:{ all -> 0x00ea }
                goto L_0x00ee
            L_0x00ed:
                throw r0
            L_0x00ee:
                goto L_0x00ed
            */
            throw new UnsupportedOperationException("Method not decompiled: p054rx.internal.operators.CachedObservable.ReplayProducer.replay():void");
        }
    }
}
package p054rx.internal.operators;

import java.util.Iterator;
import java.util.concurrent.atomic.AtomicLong;
import p054rx.Observable;
import p054rx.Observer;
import p054rx.Producer;
import p054rx.Subscriber;
import p054rx.exceptions.Exceptions;

/* renamed from: rx.internal.operators.OnSubscribeFromIterable */
public final class OnSubscribeFromIterable<T> implements Observable.OnSubscribe<T> {

    /* renamed from: is */
    final Iterable<? extends T> f3979is;

    public OnSubscribeFromIterable(Iterable<? extends T> iterable) {
        if (iterable != null) {
            this.f3979is = iterable;
            return;
        }
        throw new NullPointerException("iterable must not be null");
    }

    public void call(Subscriber<? super T> subscriber) {
        try {
            Iterator<? extends T> it = this.f3979is.iterator();
            boolean hasNext = it.hasNext();
            if (subscriber.isUnsubscribed()) {
                return;
            }
            if (!hasNext) {
                subscriber.onCompleted();
            } else {
                subscriber.setProducer(new IterableProducer(subscriber, it));
            }
        } catch (Throwable th) {
            Exceptions.throwOrReport(th, (Observer<?>) subscriber);
        }
    }

    /* renamed from: rx.internal.operators.OnSubscribeFromIterable$IterableProducer */
    static final class IterableProducer<T> extends AtomicLong implements Producer {
        private static final long serialVersionUID = -8730475647105475802L;

        /* renamed from: it */
        private final Iterator<? extends T> f3980it;

        /* renamed from: o */
        private final Subscriber<? super T> f3981o;

        IterableProducer(Subscriber<? super T> subscriber, Iterator<? extends T> it) {
            this.f3981o = subscriber;
            this.f3980it = it;
        }

        public void request(long j) {
            if (get() != Long.MAX_VALUE) {
                if (j == Long.MAX_VALUE && compareAndSet(0, Long.MAX_VALUE)) {
                    fastPath();
                } else if (j > 0 && BackpressureUtils.getAndAddRequest(this, j) == 0) {
                    slowPath(j);
                }
            }
        }

        /* access modifiers changed from: package-private */
        public void slowPath(long j) {
            Subscriber<? super T> subscriber = this.f3981o;
            Iterator<? extends T> it = this.f3980it;
            do {
                long j2 = 0;
                while (true) {
                    if (j2 == j) {
                        j = get();
                        if (j2 == j) {
                            j = BackpressureUtils.produced(this, j2);
                        }
                    } else if (!subscriber.isUnsubscribed()) {
                        try {
                            subscriber.onNext(it.next());
                            if (!subscriber.isUnsubscribed()) {
                                try {
                                    if (it.hasNext()) {
                                        j2++;
                                    } else if (!subscriber.isUnsubscribed()) {
                                        subscriber.onCompleted();
                                        return;
                                    } else {
                                        return;
                                    }
                                } catch (Throwable th) {
                                    Exceptions.throwOrReport(th, (Observer<?>) subscriber);
                                    return;
                                }
                            } else {
                                return;
                            }
                        } catch (Throwable th2) {
                            Exceptions.throwOrReport(th2, (Observer<?>) subscriber);
                            return;
                        }
                    } else {
                        return;
                    }
                }
            } while (j != 0);
        }

        /* access modifiers changed from: package-private */
        public void fastPath() {
            Subscriber<? super T> subscriber = this.f3981o;
            Iterator<? extends T> it = this.f3980it;
            while (!subscriber.isUnsubscribed()) {
                try {
                    subscriber.onNext(it.next());
                    if (!subscriber.isUnsubscribed()) {
                        try {
                            if (!it.hasNext()) {
                                if (!subscriber.isUnsubscribed()) {
                                    subscriber.onCompleted();
                                    return;
                                }
                                return;
                            }
                        } catch (Throwable th) {
                            Exceptions.throwOrReport(th, (Observer<?>) subscriber);
                            return;
                        }
                    } else {
                        return;
                    }
                } catch (Throwable th2) {
                    Exceptions.throwOrReport(th2, (Observer<?>) subscriber);
                    return;
                }
            }
        }
    }
}

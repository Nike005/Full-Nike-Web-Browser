package p054rx.internal.operators;

import java.util.concurrent.atomic.AtomicLong;
import p054rx.Observable;
import p054rx.Producer;
import p054rx.Subscriber;
import p054rx.plugins.RxJavaHooks;

/* renamed from: rx.internal.operators.OperatorTake */
public final class OperatorTake<T> implements Observable.Operator<T, T> {
    final int limit;

    public OperatorTake(int i) {
        if (i >= 0) {
            this.limit = i;
            return;
        }
        throw new IllegalArgumentException("limit >= 0 required but it was " + i);
    }

    public Subscriber<? super T> call(final Subscriber<? super T> subscriber) {
        C27681 r0 = new Subscriber<T>() {
            boolean completed;
            int count;

            public void onCompleted() {
                if (!this.completed) {
                    this.completed = true;
                    subscriber.onCompleted();
                }
            }

            public void onError(Throwable th) {
                if (!this.completed) {
                    this.completed = true;
                    try {
                        subscriber.onError(th);
                    } finally {
                        unsubscribe();
                    }
                } else {
                    RxJavaHooks.onError(th);
                }
            }

            public void onNext(T t) {
                if (!isUnsubscribed()) {
                    int i = this.count;
                    this.count = i + 1;
                    if (i < OperatorTake.this.limit) {
                        boolean z = this.count == OperatorTake.this.limit;
                        subscriber.onNext(t);
                        if (z && !this.completed) {
                            this.completed = true;
                            try {
                                subscriber.onCompleted();
                            } finally {
                                unsubscribe();
                            }
                        }
                    }
                }
            }

            public void setProducer(final Producer producer) {
                subscriber.setProducer(new Producer() {
                    final AtomicLong requested = new AtomicLong(0);

                    public void request(long j) {
                        long j2;
                        long min;
                        if (j > 0 && !C27681.this.completed) {
                            do {
                                j2 = this.requested.get();
                                min = Math.min(j, ((long) OperatorTake.this.limit) - j2);
                                if (min == 0) {
                                    return;
                                }
                            } while (!this.requested.compareAndSet(j2, j2 + min));
                            producer.request(min);
                        }
                    }
                });
            }
        };
        if (this.limit == 0) {
            subscriber.onCompleted();
            r0.unsubscribe();
        }
        subscriber.add(r0);
        return r0;
    }
}

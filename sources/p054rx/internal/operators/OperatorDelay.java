package p054rx.internal.operators;

import java.util.concurrent.TimeUnit;
import p054rx.Observable;
import p054rx.Scheduler;
import p054rx.Subscriber;
import p054rx.functions.Action0;

/* renamed from: rx.internal.operators.OperatorDelay */
public final class OperatorDelay<T> implements Observable.Operator<T, T> {
    final long delay;
    final Scheduler scheduler;
    final TimeUnit unit;

    public OperatorDelay(long j, TimeUnit timeUnit, Scheduler scheduler2) {
        this.delay = j;
        this.unit = timeUnit;
        this.scheduler = scheduler2;
    }

    public Subscriber<? super T> call(final Subscriber<? super T> subscriber) {
        final Scheduler.Worker createWorker = this.scheduler.createWorker();
        subscriber.add(createWorker);
        return new Subscriber<T>(subscriber) {
            boolean done;

            public void onCompleted() {
                createWorker.schedule(new Action0() {
                    public void call() {
                        if (!C27021.this.done) {
                            C27021.this.done = true;
                            subscriber.onCompleted();
                        }
                    }
                }, OperatorDelay.this.delay, OperatorDelay.this.unit);
            }

            public void onError(final Throwable th) {
                createWorker.schedule(new Action0() {
                    public void call() {
                        if (!C27021.this.done) {
                            C27021.this.done = true;
                            subscriber.onError(th);
                            createWorker.unsubscribe();
                        }
                    }
                });
            }

            public void onNext(final T t) {
                createWorker.schedule(new Action0() {
                    public void call() {
                        if (!C27021.this.done) {
                            subscriber.onNext(t);
                        }
                    }
                }, OperatorDelay.this.delay, OperatorDelay.this.unit);
            }
        };
    }
}

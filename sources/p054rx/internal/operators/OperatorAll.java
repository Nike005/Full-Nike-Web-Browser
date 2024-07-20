package p054rx.internal.operators;

import p054rx.Observable;
import p054rx.Observer;
import p054rx.Subscriber;
import p054rx.exceptions.Exceptions;
import p054rx.functions.Func1;
import p054rx.internal.producers.SingleDelayedProducer;
import p054rx.plugins.RxJavaHooks;

/* renamed from: rx.internal.operators.OperatorAll */
public final class OperatorAll<T> implements Observable.Operator<Boolean, T> {
    final Func1<? super T, Boolean> predicate;

    public OperatorAll(Func1<? super T, Boolean> func1) {
        this.predicate = func1;
    }

    public Subscriber<? super T> call(final Subscriber<? super Boolean> subscriber) {
        final SingleDelayedProducer singleDelayedProducer = new SingleDelayedProducer(subscriber);
        C26881 r1 = new Subscriber<T>() {
            boolean done;

            public void onNext(T t) {
                if (!this.done) {
                    try {
                        if (!OperatorAll.this.predicate.call(t).booleanValue()) {
                            this.done = true;
                            singleDelayedProducer.setValue(false);
                            unsubscribe();
                        }
                    } catch (Throwable th) {
                        Exceptions.throwOrReport(th, (Observer<?>) this, (Object) t);
                    }
                }
            }

            public void onError(Throwable th) {
                if (!this.done) {
                    this.done = true;
                    subscriber.onError(th);
                    return;
                }
                RxJavaHooks.onError(th);
            }

            public void onCompleted() {
                if (!this.done) {
                    this.done = true;
                    singleDelayedProducer.setValue(true);
                }
            }
        };
        subscriber.add(r1);
        subscriber.setProducer(singleDelayedProducer);
        return r1;
    }
}

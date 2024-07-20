package p054rx.internal.operators;

import java.util.concurrent.atomic.AtomicReference;
import p054rx.Observable;
import p054rx.Observer;
import p054rx.Subscriber;
import p054rx.exceptions.Exceptions;
import p054rx.functions.Func2;
import p054rx.observers.SerializedSubscriber;

/* renamed from: rx.internal.operators.OperatorWithLatestFrom */
public final class OperatorWithLatestFrom<T, U, R> implements Observable.Operator<R, T> {
    static final Object EMPTY = new Object();
    final Observable<? extends U> other;
    final Func2<? super T, ? super U, ? extends R> resultSelector;

    public OperatorWithLatestFrom(Observable<? extends U> observable, Func2<? super T, ? super U, ? extends R> func2) {
        this.other = observable;
        this.resultSelector = func2;
    }

    public Subscriber<? super T> call(Subscriber<? super R> subscriber) {
        final SerializedSubscriber serializedSubscriber = new SerializedSubscriber(subscriber, false);
        subscriber.add(serializedSubscriber);
        final AtomicReference atomicReference = new AtomicReference(EMPTY);
        final AtomicReference atomicReference2 = atomicReference;
        final SerializedSubscriber serializedSubscriber2 = serializedSubscriber;
        C27931 r0 = new Subscriber<T>(serializedSubscriber, true) {
            public void onNext(T t) {
                Object obj = atomicReference2.get();
                if (obj != OperatorWithLatestFrom.EMPTY) {
                    try {
                        serializedSubscriber2.onNext(OperatorWithLatestFrom.this.resultSelector.call(t, obj));
                    } catch (Throwable th) {
                        Exceptions.throwOrReport(th, (Observer<?>) this);
                    }
                }
            }

            public void onError(Throwable th) {
                serializedSubscriber2.onError(th);
                serializedSubscriber2.unsubscribe();
            }

            public void onCompleted() {
                serializedSubscriber2.onCompleted();
                serializedSubscriber2.unsubscribe();
            }
        };
        C27942 r02 = new Subscriber<U>() {
            public void onNext(U u) {
                atomicReference.set(u);
            }

            public void onError(Throwable th) {
                serializedSubscriber.onError(th);
                serializedSubscriber.unsubscribe();
            }

            public void onCompleted() {
                if (atomicReference.get() == OperatorWithLatestFrom.EMPTY) {
                    serializedSubscriber.onCompleted();
                    serializedSubscriber.unsubscribe();
                }
            }
        };
        serializedSubscriber.add(r0);
        serializedSubscriber.add(r02);
        this.other.unsafeSubscribe(r02);
        return r0;
    }
}

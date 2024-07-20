package p054rx.internal.operators;

import p054rx.Observable;
import p054rx.Subscriber;
import p054rx.observers.SerializedSubscriber;

/* renamed from: rx.internal.operators.OperatorTakeUntil */
public final class OperatorTakeUntil<T, E> implements Observable.Operator<T, T> {
    private final Observable<? extends E> other;

    public OperatorTakeUntil(Observable<? extends E> observable) {
        this.other = observable;
    }

    public Subscriber<? super T> call(Subscriber<? super T> subscriber) {
        final SerializedSubscriber serializedSubscriber = new SerializedSubscriber(subscriber, false);
        final C27721 r2 = new Subscriber<T>(false, serializedSubscriber) {
            public void onNext(T t) {
                serializedSubscriber.onNext(t);
            }

            public void onError(Throwable th) {
                try {
                    serializedSubscriber.onError(th);
                } finally {
                    serializedSubscriber.unsubscribe();
                }
            }

            public void onCompleted() {
                try {
                    serializedSubscriber.onCompleted();
                } finally {
                    serializedSubscriber.unsubscribe();
                }
            }
        };
        C27732 r1 = new Subscriber<E>() {
            public void onStart() {
                request(Long.MAX_VALUE);
            }

            public void onCompleted() {
                r2.onCompleted();
            }

            public void onError(Throwable th) {
                r2.onError(th);
            }

            public void onNext(E e) {
                onCompleted();
            }
        };
        serializedSubscriber.add(r2);
        serializedSubscriber.add(r1);
        subscriber.add(serializedSubscriber);
        this.other.unsafeSubscribe(r1);
        return r2;
    }
}

package p054rx.internal.operators;

import p054rx.Observable;
import p054rx.Subscriber;

/* renamed from: rx.internal.operators.OnSubscribeTakeLastOne */
public final class OnSubscribeTakeLastOne<T> implements Observable.OnSubscribe<T> {
    final Observable<T> source;

    public OnSubscribeTakeLastOne(Observable<T> observable) {
        this.source = observable;
    }

    public void call(Subscriber<? super T> subscriber) {
        new TakeLastOneSubscriber(subscriber).subscribeTo(this.source);
    }

    /* renamed from: rx.internal.operators.OnSubscribeTakeLastOne$TakeLastOneSubscriber */
    static final class TakeLastOneSubscriber<T> extends DeferredScalarSubscriber<T, T> {
        static final Object EMPTY = new Object();

        public TakeLastOneSubscriber(Subscriber<? super T> subscriber) {
            super(subscriber);
            this.value = EMPTY;
        }

        public void onNext(T t) {
            this.value = t;
        }

        public void onCompleted() {
            Object obj = this.value;
            if (obj == EMPTY) {
                complete();
            } else {
                complete(obj);
            }
        }
    }
}

package p054rx.internal.operators;

import p054rx.Observable;
import p054rx.Subscriber;

/* renamed from: rx.internal.operators.EmptyObservableHolder */
public enum EmptyObservableHolder implements Observable.OnSubscribe<Object> {
    INSTANCE;
    
    static final Observable<Object> EMPTY = null;

    static {
        EmptyObservableHolder emptyObservableHolder;
        EMPTY = Observable.unsafeCreate(emptyObservableHolder);
    }

    public static <T> Observable<T> instance() {
        return EMPTY;
    }

    public void call(Subscriber<? super Object> subscriber) {
        subscriber.onCompleted();
    }
}

package p054rx.internal.util;

import p054rx.Observer;
import p054rx.Subscriber;

/* renamed from: rx.internal.util.ObserverSubscriber */
public final class ObserverSubscriber<T> extends Subscriber<T> {
    final Observer<? super T> observer;

    public ObserverSubscriber(Observer<? super T> observer2) {
        this.observer = observer2;
    }

    public void onNext(T t) {
        this.observer.onNext(t);
    }

    public void onError(Throwable th) {
        this.observer.onError(th);
    }

    public void onCompleted() {
        this.observer.onCompleted();
    }
}

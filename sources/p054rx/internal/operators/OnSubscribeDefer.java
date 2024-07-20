package p054rx.internal.operators;

import p054rx.Observable;
import p054rx.Observer;
import p054rx.Subscriber;
import p054rx.exceptions.Exceptions;
import p054rx.functions.Func0;
import p054rx.observers.Subscribers;

/* renamed from: rx.internal.operators.OnSubscribeDefer */
public final class OnSubscribeDefer<T> implements Observable.OnSubscribe<T> {
    final Func0<? extends Observable<? extends T>> observableFactory;

    public OnSubscribeDefer(Func0<? extends Observable<? extends T>> func0) {
        this.observableFactory = func0;
    }

    public void call(Subscriber<? super T> subscriber) {
        try {
            ((Observable) this.observableFactory.call()).unsafeSubscribe(Subscribers.wrap(subscriber));
        } catch (Throwable th) {
            Exceptions.throwOrReport(th, (Observer<?>) subscriber);
        }
    }
}

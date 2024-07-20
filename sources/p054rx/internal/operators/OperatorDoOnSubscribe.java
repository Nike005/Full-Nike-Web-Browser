package p054rx.internal.operators;

import p054rx.Observable;
import p054rx.Subscriber;
import p054rx.functions.Action0;
import p054rx.observers.Subscribers;

/* renamed from: rx.internal.operators.OperatorDoOnSubscribe */
public class OperatorDoOnSubscribe<T> implements Observable.Operator<T, T> {
    private final Action0 subscribe;

    public OperatorDoOnSubscribe(Action0 action0) {
        this.subscribe = action0;
    }

    public Subscriber<? super T> call(Subscriber<? super T> subscriber) {
        this.subscribe.call();
        return Subscribers.wrap(subscriber);
    }
}

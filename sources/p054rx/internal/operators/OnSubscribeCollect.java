package p054rx.internal.operators;

import p054rx.Observable;
import p054rx.Subscriber;
import p054rx.exceptions.Exceptions;
import p054rx.functions.Action2;
import p054rx.functions.Func0;

/* renamed from: rx.internal.operators.OnSubscribeCollect */
public final class OnSubscribeCollect<T, R> implements Observable.OnSubscribe<R> {
    final Func0<R> collectionFactory;
    final Action2<R, ? super T> collector;
    final Observable<T> source;

    public OnSubscribeCollect(Observable<T> observable, Func0<R> func0, Action2<R, ? super T> action2) {
        this.source = observable;
        this.collectionFactory = func0;
        this.collector = action2;
    }

    public void call(Subscriber<? super R> subscriber) {
        try {
            new CollectSubscriber(subscriber, this.collectionFactory.call(), this.collector).subscribeTo(this.source);
        } catch (Throwable th) {
            Exceptions.throwIfFatal(th);
            subscriber.onError(th);
        }
    }

    /* renamed from: rx.internal.operators.OnSubscribeCollect$CollectSubscriber */
    static final class CollectSubscriber<T, R> extends DeferredScalarSubscriberSafe<T, R> {
        final Action2<R, ? super T> collector;

        public CollectSubscriber(Subscriber<? super R> subscriber, R r, Action2<R, ? super T> action2) {
            super(subscriber);
            this.value = r;
            this.hasValue = true;
            this.collector = action2;
        }

        public void onNext(T t) {
            if (!this.done) {
                try {
                    this.collector.call(this.value, t);
                } catch (Throwable th) {
                    Exceptions.throwIfFatal(th);
                    unsubscribe();
                    onError(th);
                }
            }
        }
    }
}

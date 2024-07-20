package p054rx.internal.operators;

import p054rx.Observable;
import p054rx.Single;
import p054rx.SingleSubscriber;
import p054rx.Subscriber;
import p054rx.plugins.RxJavaHooks;
import p054rx.subscriptions.SerialSubscription;

/* renamed from: rx.internal.operators.SingleOnSubscribeDelaySubscriptionOther */
public final class SingleOnSubscribeDelaySubscriptionOther<T> implements Single.OnSubscribe<T> {
    final Single<? extends T> main;
    final Observable<?> other;

    public SingleOnSubscribeDelaySubscriptionOther(Single<? extends T> single, Observable<?> observable) {
        this.main = single;
        this.other = observable;
    }

    public void call(final SingleSubscriber<? super T> singleSubscriber) {
        final C27961 r0 = new SingleSubscriber<T>() {
            public void onSuccess(T t) {
                singleSubscriber.onSuccess(t);
            }

            public void onError(Throwable th) {
                singleSubscriber.onError(th);
            }
        };
        final SerialSubscription serialSubscription = new SerialSubscription();
        singleSubscriber.add(serialSubscription);
        C27972 r3 = new Subscriber<Object>() {
            boolean done;

            public void onNext(Object obj) {
                onCompleted();
            }

            public void onError(Throwable th) {
                if (this.done) {
                    RxJavaHooks.onError(th);
                    return;
                }
                this.done = true;
                r0.onError(th);
            }

            public void onCompleted() {
                if (!this.done) {
                    this.done = true;
                    serialSubscription.set(r0);
                    SingleOnSubscribeDelaySubscriptionOther.this.main.subscribe(r0);
                }
            }
        };
        serialSubscription.set(r3);
        this.other.subscribe(r3);
    }
}

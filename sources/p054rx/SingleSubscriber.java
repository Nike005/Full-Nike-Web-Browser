package p054rx;

import p054rx.internal.util.SubscriptionList;

/* renamed from: rx.SingleSubscriber */
public abstract class SingleSubscriber<T> implements Subscription {

    /* renamed from: cs */
    private final SubscriptionList f3972cs = new SubscriptionList();

    public abstract void onError(Throwable th);

    public abstract void onSuccess(T t);

    public final void add(Subscription subscription) {
        this.f3972cs.add(subscription);
    }

    public final void unsubscribe() {
        this.f3972cs.unsubscribe();
    }

    public final boolean isUnsubscribed() {
        return this.f3972cs.isUnsubscribed();
    }
}

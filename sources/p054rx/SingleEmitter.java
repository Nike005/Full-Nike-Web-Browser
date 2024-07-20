package p054rx;

import p054rx.functions.Cancellable;

/* renamed from: rx.SingleEmitter */
public interface SingleEmitter<T> {
    void onError(Throwable th);

    void onSuccess(T t);

    void setCancellation(Cancellable cancellable);

    void setSubscription(Subscription subscription);
}
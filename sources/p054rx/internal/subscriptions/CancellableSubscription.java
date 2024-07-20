package p054rx.internal.subscriptions;

import java.util.concurrent.atomic.AtomicReference;
import p054rx.Subscription;
import p054rx.exceptions.Exceptions;
import p054rx.functions.Cancellable;
import p054rx.plugins.RxJavaHooks;

/* renamed from: rx.internal.subscriptions.CancellableSubscription */
public final class CancellableSubscription extends AtomicReference<Cancellable> implements Subscription {
    private static final long serialVersionUID = 5718521705281392066L;

    public CancellableSubscription(Cancellable cancellable) {
        super(cancellable);
    }

    public boolean isUnsubscribed() {
        return get() == null;
    }

    public void unsubscribe() {
        Cancellable cancellable;
        if (get() != null && (cancellable = (Cancellable) getAndSet((Object) null)) != null) {
            try {
                cancellable.cancel();
            } catch (Exception e) {
                Exceptions.throwIfFatal(e);
                RxJavaHooks.onError(e);
            }
        }
    }
}

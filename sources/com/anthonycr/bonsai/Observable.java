package com.anthonycr.bonsai;

import com.anthonycr.bonsai.ObservableAction;
import com.anthonycr.bonsai.ObservableOnSubscribe;
import com.anthonycr.bonsai.ObservableSubscriber;

public abstract class Observable<ActionT extends ObservableAction<SubscriberT>, OnSubscribeT extends ObservableOnSubscribe, SubscriberT extends ObservableSubscriber> {
    /* access modifiers changed from: private */
    public final ActionT action;
    private Scheduler observerThread;
    private Scheduler subscriberThread;

    /* access modifiers changed from: protected */
    public abstract SubscriberT createSubscriberWrapper(OnSubscribeT onsubscribet, Scheduler scheduler, Scheduler scheduler2);

    protected Observable(ActionT actiont) {
        this.action = actiont;
    }

    /* access modifiers changed from: protected */
    public final void setActionScheduler(Scheduler scheduler) {
        this.subscriberThread = scheduler;
    }

    /* access modifiers changed from: protected */
    public final void setObserverScheduler(Scheduler scheduler) {
        this.observerThread = scheduler;
    }

    public final Subscription subscribe() {
        return startSubscription((ObservableOnSubscribe) null);
    }

    public final Subscription subscribe(OnSubscribeT onsubscribet) {
        Preconditions.checkNonNull(onsubscribet);
        return startSubscription(onsubscribet);
    }

    private Subscription startSubscription(OnSubscribeT onsubscribet) {
        Scheduler immediate = Schedulers.immediate();
        final ObservableSubscriber createSubscriberWrapper = createSubscriberWrapper(onsubscribet, this.observerThread, immediate);
        Preconditions.checkNonNull(createSubscriberWrapper);
        createSubscriberWrapper.onStart();
        executeOnSubscriberThread(new Runnable() {
            public void run() {
                try {
                    Observable.this.action.onSubscribe(createSubscriberWrapper);
                } catch (Exception e) {
                    createSubscriberWrapper.onError(e);
                }
            }
        }, immediate);
        return createSubscriberWrapper;
    }

    private void executeOnSubscriberThread(Runnable runnable, Scheduler scheduler) {
        Scheduler scheduler2 = this.subscriberThread;
        if (scheduler2 != null) {
            scheduler2.execute(runnable);
        } else {
            scheduler.execute(runnable);
        }
    }
}

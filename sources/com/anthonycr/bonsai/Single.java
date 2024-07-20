package com.anthonycr.bonsai;

public class Single<T> extends Observable<SingleAction<T>, SingleOnSubscribe<T>, SingleSubscriber<T>> {
    private Single(SingleAction<T> singleAction) {
        super(singleAction);
    }

    public static <T> Single<T> create(SingleAction<T> singleAction) {
        Preconditions.checkNonNull(singleAction);
        return new Single<>(singleAction);
    }

    public static <T> Single<T> empty() {
        return new Single<>(new SingleAction<T>() {
            public void onSubscribe(SingleSubscriber<T> singleSubscriber) {
                singleSubscriber.onComplete();
            }
        });
    }

    public final Single<T> subscribeOn(Scheduler scheduler) {
        setActionScheduler(scheduler);
        return this;
    }

    public final Single<T> observeOn(Scheduler scheduler) {
        setObserverScheduler(scheduler);
        return this;
    }

    /* access modifiers changed from: protected */
    public SingleSubscriber<T> createSubscriberWrapper(SingleOnSubscribe<T> singleOnSubscribe, Scheduler scheduler, Scheduler scheduler2) {
        return new SingleSubscriberWrapper(singleOnSubscribe, scheduler, scheduler2);
    }
}

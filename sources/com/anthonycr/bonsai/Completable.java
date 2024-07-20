package com.anthonycr.bonsai;

public class Completable extends Observable<CompletableAction, CompletableOnSubscribe, CompletableSubscriber> {
    private Completable(CompletableAction completableAction) {
        super(completableAction);
    }

    public static Completable create(CompletableAction completableAction) {
        Preconditions.checkNonNull(completableAction);
        return new Completable(completableAction);
    }

    public static Completable empty() {
        return new Completable(new CompletableAction() {
            public void onSubscribe(CompletableSubscriber completableSubscriber) {
                completableSubscriber.onComplete();
            }
        });
    }

    public final Completable subscribeOn(Scheduler scheduler) {
        setActionScheduler(scheduler);
        return this;
    }

    public final Completable observeOn(Scheduler scheduler) {
        setObserverScheduler(scheduler);
        return this;
    }

    /* access modifiers changed from: protected */
    public CompletableSubscriber createSubscriberWrapper(CompletableOnSubscribe completableOnSubscribe, Scheduler scheduler, Scheduler scheduler2) {
        return new CompletableSubscriberWrapper(completableOnSubscribe, scheduler, scheduler2);
    }
}

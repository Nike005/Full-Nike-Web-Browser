package com.anthonycr.bonsai;

import com.anthonycr.bonsai.CompletableOnSubscribe;

class CompletableSubscriberWrapper<T extends CompletableOnSubscribe> extends ObservableSubscriberWrapper<T> implements CompletableSubscriber {
    protected volatile boolean onCompleteExecuted;

    CompletableSubscriberWrapper(T t, Scheduler scheduler, Scheduler scheduler2) {
        super(t, scheduler, scheduler2);
    }

    public void onComplete() {
        CompletableOnSubscribe completableOnSubscribe = (CompletableOnSubscribe) this.onSubscribe;
        if (!this.onCompleteExecuted) {
            if (completableOnSubscribe != null && !this.onErrorExecuted) {
                executeOnObserverThread(new OnCompleteRunnable(completableOnSubscribe));
            }
            this.onCompleteExecuted = true;
            unsubscribe();
            return;
        }
        throw new RuntimeException("onComplete called more than once");
    }
}

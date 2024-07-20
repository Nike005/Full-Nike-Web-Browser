package com.anthonycr.bonsai;

class SingleSubscriberWrapper<T> extends CompletableSubscriberWrapper<SingleOnSubscribe<T>> implements SingleSubscriber<T> {
    private volatile boolean onItemExecuted = false;

    SingleSubscriberWrapper(SingleOnSubscribe<T> singleOnSubscribe, Scheduler scheduler, Scheduler scheduler2) {
        super(singleOnSubscribe, scheduler, scheduler2);
    }

    public void onItem(T t) {
        SingleOnSubscribe singleOnSubscribe = (SingleOnSubscribe) this.onSubscribe;
        if (this.onCompleteExecuted) {
            throw new RuntimeException("onItem should not be called after onComplete has been called");
        } else if (!this.onItemExecuted) {
            if (singleOnSubscribe != null && !this.onErrorExecuted) {
                executeOnObserverThread(new OnItemRunnable(singleOnSubscribe, t));
            }
            this.onItemExecuted = true;
        } else {
            throw new RuntimeException("onItem should not be called multiple times");
        }
    }
}

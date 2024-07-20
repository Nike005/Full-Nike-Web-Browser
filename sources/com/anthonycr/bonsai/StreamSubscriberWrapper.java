package com.anthonycr.bonsai;

class StreamSubscriberWrapper<T> extends CompletableSubscriberWrapper<StreamOnSubscribe<T>> implements StreamSubscriber<T> {
    StreamSubscriberWrapper(StreamOnSubscribe<T> streamOnSubscribe, Scheduler scheduler, Scheduler scheduler2) {
        super(streamOnSubscribe, scheduler, scheduler2);
    }

    public void onNext(T t) {
        StreamOnSubscribe streamOnSubscribe = (StreamOnSubscribe) this.onSubscribe;
        if (this.onCompleteExecuted) {
            throw new RuntimeException("onNext should not be called after onComplete has been called");
        } else if (streamOnSubscribe != null && !this.onErrorExecuted) {
            executeOnObserverThread(new OnNextRunnable(streamOnSubscribe, t));
        }
    }
}

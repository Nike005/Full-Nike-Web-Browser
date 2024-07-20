package com.anthonycr.bonsai;

public class Stream<T> extends Observable<StreamAction<T>, StreamOnSubscribe<T>, StreamSubscriber<T>> {
    private Stream(StreamAction<T> streamAction) {
        super(streamAction);
    }

    public static <T> Stream<T> create(StreamAction<T> streamAction) {
        Preconditions.checkNonNull(streamAction);
        return new Stream<>(streamAction);
    }

    public static <T> Stream<T> empty() {
        return new Stream<>(new StreamAction<T>() {
            public void onSubscribe(StreamSubscriber<T> streamSubscriber) {
                streamSubscriber.onComplete();
            }
        });
    }

    public final Stream<T> subscribeOn(Scheduler scheduler) {
        setActionScheduler(scheduler);
        return this;
    }

    public final Stream<T> observeOn(Scheduler scheduler) {
        setObserverScheduler(scheduler);
        return this;
    }

    /* access modifiers changed from: protected */
    public StreamSubscriber<T> createSubscriberWrapper(StreamOnSubscribe<T> streamOnSubscribe, Scheduler scheduler, Scheduler scheduler2) {
        return new StreamSubscriberWrapper(streamOnSubscribe, scheduler, scheduler2);
    }
}

package com.anthonycr.bonsai;

class OnNextRunnable<T> implements Runnable {
    private final T item;
    private final StreamOnSubscribe<T> onSubscribe;

    OnNextRunnable(StreamOnSubscribe<T> streamOnSubscribe, T t) {
        this.onSubscribe = streamOnSubscribe;
        this.item = t;
    }

    public void run() {
        this.onSubscribe.onNext(this.item);
    }
}

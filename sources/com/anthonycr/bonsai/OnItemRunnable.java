package com.anthonycr.bonsai;

class OnItemRunnable<T> implements Runnable {
    private final T item;
    private final SingleOnSubscribe<T> onSubscribe;

    OnItemRunnable(SingleOnSubscribe<T> singleOnSubscribe, T t) {
        this.onSubscribe = singleOnSubscribe;
        this.item = t;
    }

    public void run() {
        this.onSubscribe.onItem(this.item);
    }
}

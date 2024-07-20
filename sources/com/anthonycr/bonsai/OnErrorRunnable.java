package com.anthonycr.bonsai;

class OnErrorRunnable implements Runnable {
    private final ObservableOnSubscribe onSubscribe;
    private final Throwable throwable;

    OnErrorRunnable(ObservableOnSubscribe observableOnSubscribe, Throwable th) {
        this.onSubscribe = observableOnSubscribe;
        this.throwable = th;
    }

    public void run() {
        this.onSubscribe.onError(this.throwable);
    }
}

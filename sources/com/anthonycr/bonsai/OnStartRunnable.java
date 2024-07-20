package com.anthonycr.bonsai;

class OnStartRunnable implements Runnable {
    private final ObservableOnSubscribe onSubscribe;

    OnStartRunnable(ObservableOnSubscribe observableOnSubscribe) {
        this.onSubscribe = observableOnSubscribe;
    }

    public void run() {
        this.onSubscribe.onStart();
    }
}

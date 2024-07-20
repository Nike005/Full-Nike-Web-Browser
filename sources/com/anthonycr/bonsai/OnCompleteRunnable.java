package com.anthonycr.bonsai;

class OnCompleteRunnable implements Runnable {
    private final CompletableOnSubscribe onSubscribe;

    OnCompleteRunnable(CompletableOnSubscribe completableOnSubscribe) {
        this.onSubscribe = completableOnSubscribe;
    }

    public void run() {
        this.onSubscribe.onComplete();
    }
}

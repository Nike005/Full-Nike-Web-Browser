package com.anthonycr.bonsai;

public interface ObservableSubscriber extends Subscription {
    void onError(Throwable th);

    void onStart();
}

package com.anthonycr.bonsai;

public interface StreamSubscriber<T> extends CompletableSubscriber {
    void onNext(T t);
}

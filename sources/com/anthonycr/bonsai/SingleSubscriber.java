package com.anthonycr.bonsai;

public interface SingleSubscriber<T> extends CompletableSubscriber {
    void onItem(T t);
}

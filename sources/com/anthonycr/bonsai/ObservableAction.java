package com.anthonycr.bonsai;

interface ObservableAction<T> {
    void onSubscribe(T t);
}

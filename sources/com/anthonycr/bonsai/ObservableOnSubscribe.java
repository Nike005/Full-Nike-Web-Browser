package com.anthonycr.bonsai;

public abstract class ObservableOnSubscribe {
    public void onStart() {
    }

    public void onError(Throwable th) {
        throw new RuntimeException("Exception thrown: override onError to handle it", th);
    }
}

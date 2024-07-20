package com.anthonycr.bonsai;

import com.anthonycr.bonsai.ObservableOnSubscribe;

class ObservableSubscriberWrapper<T extends ObservableOnSubscribe> implements ObservableSubscriber {
    private final Scheduler defaultThread;
    private final Scheduler observerThread;
    volatile boolean onErrorExecuted = false;
    private volatile boolean onStartExecuted = false;
    protected volatile T onSubscribe;

    public ObservableSubscriberWrapper(T t, Scheduler scheduler, Scheduler scheduler2) {
        this.onSubscribe = t;
        this.observerThread = scheduler;
        this.defaultThread = scheduler2;
    }

    /* access modifiers changed from: package-private */
    public void executeOnObserverThread(Runnable runnable) {
        Scheduler scheduler = this.observerThread;
        if (scheduler != null) {
            scheduler.execute(runnable);
        } else {
            this.defaultThread.execute(runnable);
        }
    }

    public void unsubscribe() {
        this.onSubscribe = null;
    }

    public boolean isUnsubscribed() {
        return this.onSubscribe == null;
    }

    public void onStart() {
        T t = this.onSubscribe;
        if (!this.onStartExecuted) {
            if (t != null) {
                executeOnObserverThread(new OnStartRunnable(t));
            }
            this.onStartExecuted = true;
            return;
        }
        throw new RuntimeException("onStart is called internally, do not call it yourself");
    }

    public void onError(Throwable th) {
        T t = this.onSubscribe;
        if (t != null) {
            executeOnObserverThread(new OnErrorRunnable(t, th));
        }
        this.onErrorExecuted = true;
        unsubscribe();
    }
}

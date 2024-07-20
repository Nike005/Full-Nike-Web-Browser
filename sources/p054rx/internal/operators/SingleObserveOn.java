package p054rx.internal.operators;

import p054rx.Scheduler;
import p054rx.Single;
import p054rx.SingleSubscriber;
import p054rx.functions.Action0;

/* renamed from: rx.internal.operators.SingleObserveOn */
public final class SingleObserveOn<T> implements Single.OnSubscribe<T> {
    final Scheduler scheduler;
    final Single.OnSubscribe<T> source;

    public SingleObserveOn(Single.OnSubscribe<T> onSubscribe, Scheduler scheduler2) {
        this.source = onSubscribe;
        this.scheduler = scheduler2;
    }

    public void call(SingleSubscriber<? super T> singleSubscriber) {
        Scheduler.Worker createWorker = this.scheduler.createWorker();
        ObserveOnSingleSubscriber observeOnSingleSubscriber = new ObserveOnSingleSubscriber(singleSubscriber, createWorker);
        singleSubscriber.add(createWorker);
        singleSubscriber.add(observeOnSingleSubscriber);
        this.source.call(observeOnSingleSubscriber);
    }

    /* renamed from: rx.internal.operators.SingleObserveOn$ObserveOnSingleSubscriber */
    static final class ObserveOnSingleSubscriber<T> extends SingleSubscriber<T> implements Action0 {
        final SingleSubscriber<? super T> actual;
        Throwable error;
        T value;

        /* renamed from: w */
        final Scheduler.Worker f3993w;

        public ObserveOnSingleSubscriber(SingleSubscriber<? super T> singleSubscriber, Scheduler.Worker worker) {
            this.actual = singleSubscriber;
            this.f3993w = worker;
        }

        public void onSuccess(T t) {
            this.value = t;
            this.f3993w.schedule(this);
        }

        public void onError(Throwable th) {
            this.error = th;
            this.f3993w.schedule(this);
        }

        public void call() {
            try {
                Throwable th = this.error;
                if (th != null) {
                    this.error = null;
                    this.actual.onError(th);
                } else {
                    T t = this.value;
                    this.value = null;
                    this.actual.onSuccess(t);
                }
            } finally {
                this.f3993w.unsubscribe();
            }
        }
    }
}

package p054rx.internal.operators;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import p054rx.Observable;
import p054rx.Observer;
import p054rx.Scheduler;
import p054rx.Subscriber;
import p054rx.exceptions.Exceptions;
import p054rx.functions.Action0;
import p054rx.observers.SerializedSubscriber;

/* renamed from: rx.internal.operators.OperatorSampleWithTime */
public final class OperatorSampleWithTime<T> implements Observable.Operator<T, T> {
    final Scheduler scheduler;
    final long time;
    final TimeUnit unit;

    public OperatorSampleWithTime(long j, TimeUnit timeUnit, Scheduler scheduler2) {
        this.time = j;
        this.unit = timeUnit;
        this.scheduler = scheduler2;
    }

    public Subscriber<? super T> call(Subscriber<? super T> subscriber) {
        SerializedSubscriber serializedSubscriber = new SerializedSubscriber(subscriber);
        Scheduler.Worker createWorker = this.scheduler.createWorker();
        subscriber.add(createWorker);
        SamplerSubscriber samplerSubscriber = new SamplerSubscriber(serializedSubscriber);
        subscriber.add(samplerSubscriber);
        long j = this.time;
        createWorker.schedulePeriodically(samplerSubscriber, j, j, this.unit);
        return samplerSubscriber;
    }

    /* renamed from: rx.internal.operators.OperatorSampleWithTime$SamplerSubscriber */
    static final class SamplerSubscriber<T> extends Subscriber<T> implements Action0 {
        private static final Object EMPTY_TOKEN = new Object();
        private final Subscriber<? super T> subscriber;
        final AtomicReference<Object> value = new AtomicReference<>(EMPTY_TOKEN);

        public SamplerSubscriber(Subscriber<? super T> subscriber2) {
            this.subscriber = subscriber2;
        }

        public void onStart() {
            request(Long.MAX_VALUE);
        }

        public void onNext(T t) {
            this.value.set(t);
        }

        public void onError(Throwable th) {
            this.subscriber.onError(th);
            unsubscribe();
        }

        public void onCompleted() {
            emitIfNonEmpty();
            this.subscriber.onCompleted();
            unsubscribe();
        }

        public void call() {
            emitIfNonEmpty();
        }

        private void emitIfNonEmpty() {
            Object andSet = this.value.getAndSet(EMPTY_TOKEN);
            if (andSet != EMPTY_TOKEN) {
                try {
                    this.subscriber.onNext(andSet);
                } catch (Throwable th) {
                    Exceptions.throwOrReport(th, (Observer<?>) this);
                }
            }
        }
    }
}

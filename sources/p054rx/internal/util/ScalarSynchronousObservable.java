package p054rx.internal.util;

import java.util.concurrent.atomic.AtomicBoolean;
import p054rx.Observable;
import p054rx.Observer;
import p054rx.Producer;
import p054rx.Scheduler;
import p054rx.Subscriber;
import p054rx.Subscription;
import p054rx.exceptions.Exceptions;
import p054rx.functions.Action0;
import p054rx.functions.Func1;
import p054rx.internal.producers.SingleProducer;
import p054rx.internal.schedulers.EventLoopsScheduler;
import p054rx.observers.Subscribers;
import p054rx.plugins.RxJavaHooks;

/* renamed from: rx.internal.util.ScalarSynchronousObservable */
public final class ScalarSynchronousObservable<T> extends Observable<T> {
    static final boolean STRONG_MODE = Boolean.valueOf(System.getProperty("rx.just.strong-mode", "false")).booleanValue();

    /* renamed from: t */
    final T f3998t;

    static <T> Producer createProducer(Subscriber<? super T> subscriber, T t) {
        if (STRONG_MODE) {
            return new SingleProducer(subscriber, t);
        }
        return new WeakSingleProducer(subscriber, t);
    }

    public static <T> ScalarSynchronousObservable<T> create(T t) {
        return new ScalarSynchronousObservable<>(t);
    }

    protected ScalarSynchronousObservable(T t) {
        super(RxJavaHooks.onCreate(new JustOnSubscribe(t)));
        this.f3998t = t;
    }

    public T get() {
        return this.f3998t;
    }

    public Observable<T> scalarScheduleOn(final Scheduler scheduler) {
        Func1 func1;
        if (scheduler instanceof EventLoopsScheduler) {
            final EventLoopsScheduler eventLoopsScheduler = (EventLoopsScheduler) scheduler;
            func1 = new Func1<Action0, Subscription>() {
                public Subscription call(Action0 action0) {
                    return eventLoopsScheduler.scheduleDirect(action0);
                }
            };
        } else {
            func1 = new Func1<Action0, Subscription>() {
                public Subscription call(final Action0 action0) {
                    final Scheduler.Worker createWorker = scheduler.createWorker();
                    createWorker.schedule(new Action0() {
                        public void call() {
                            try {
                                action0.call();
                            } finally {
                                createWorker.unsubscribe();
                            }
                        }
                    });
                    return createWorker;
                }
            };
        }
        return unsafeCreate(new ScalarAsyncOnSubscribe(this.f3998t, func1));
    }

    /* renamed from: rx.internal.util.ScalarSynchronousObservable$JustOnSubscribe */
    static final class JustOnSubscribe<T> implements Observable.OnSubscribe<T> {
        final T value;

        JustOnSubscribe(T t) {
            this.value = t;
        }

        public void call(Subscriber<? super T> subscriber) {
            subscriber.setProducer(ScalarSynchronousObservable.createProducer(subscriber, this.value));
        }
    }

    /* renamed from: rx.internal.util.ScalarSynchronousObservable$ScalarAsyncOnSubscribe */
    static final class ScalarAsyncOnSubscribe<T> implements Observable.OnSubscribe<T> {
        final Func1<Action0, Subscription> onSchedule;
        final T value;

        ScalarAsyncOnSubscribe(T t, Func1<Action0, Subscription> func1) {
            this.value = t;
            this.onSchedule = func1;
        }

        public void call(Subscriber<? super T> subscriber) {
            subscriber.setProducer(new ScalarAsyncProducer(subscriber, this.value, this.onSchedule));
        }
    }

    /* renamed from: rx.internal.util.ScalarSynchronousObservable$ScalarAsyncProducer */
    static final class ScalarAsyncProducer<T> extends AtomicBoolean implements Producer, Action0 {
        private static final long serialVersionUID = -2466317989629281651L;
        final Subscriber<? super T> actual;
        final Func1<Action0, Subscription> onSchedule;
        final T value;

        public ScalarAsyncProducer(Subscriber<? super T> subscriber, T t, Func1<Action0, Subscription> func1) {
            this.actual = subscriber;
            this.value = t;
            this.onSchedule = func1;
        }

        public void request(long j) {
            if (j < 0) {
                throw new IllegalArgumentException("n >= 0 required but it was " + j);
            } else if (j != 0 && compareAndSet(false, true)) {
                this.actual.add(this.onSchedule.call(this));
            }
        }

        public void call() {
            Subscriber<? super T> subscriber = this.actual;
            if (!subscriber.isUnsubscribed()) {
                T t = this.value;
                try {
                    subscriber.onNext(t);
                    if (!subscriber.isUnsubscribed()) {
                        subscriber.onCompleted();
                    }
                } catch (Throwable th) {
                    Exceptions.throwOrReport(th, (Observer<?>) subscriber, (Object) t);
                }
            }
        }

        public String toString() {
            return "ScalarAsyncProducer[" + this.value + ", " + get() + "]";
        }
    }

    public <R> Observable<R> scalarFlatMap(final Func1<? super T, ? extends Observable<? extends R>> func1) {
        return unsafeCreate(new Observable.OnSubscribe<R>() {
            public void call(Subscriber<? super R> subscriber) {
                Observable observable = (Observable) func1.call(ScalarSynchronousObservable.this.f3998t);
                if (observable instanceof ScalarSynchronousObservable) {
                    subscriber.setProducer(ScalarSynchronousObservable.createProducer(subscriber, ((ScalarSynchronousObservable) observable).f3998t));
                } else {
                    observable.unsafeSubscribe(Subscribers.wrap(subscriber));
                }
            }
        });
    }

    /* renamed from: rx.internal.util.ScalarSynchronousObservable$WeakSingleProducer */
    static final class WeakSingleProducer<T> implements Producer {
        final Subscriber<? super T> actual;
        boolean once;
        final T value;

        public WeakSingleProducer(Subscriber<? super T> subscriber, T t) {
            this.actual = subscriber;
            this.value = t;
        }

        public void request(long j) {
            if (!this.once) {
                if (j < 0) {
                    throw new IllegalStateException("n >= required but it was " + j);
                } else if (j != 0) {
                    this.once = true;
                    Subscriber<? super T> subscriber = this.actual;
                    if (!subscriber.isUnsubscribed()) {
                        T t = this.value;
                        try {
                            subscriber.onNext(t);
                            if (!subscriber.isUnsubscribed()) {
                                subscriber.onCompleted();
                            }
                        } catch (Throwable th) {
                            Exceptions.throwOrReport(th, (Observer<?>) subscriber, (Object) t);
                        }
                    }
                }
            }
        }
    }
}

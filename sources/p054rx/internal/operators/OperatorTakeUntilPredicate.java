package p054rx.internal.operators;

import p054rx.Observable;
import p054rx.Observer;
import p054rx.Producer;
import p054rx.Subscriber;
import p054rx.exceptions.Exceptions;
import p054rx.functions.Func1;

/* renamed from: rx.internal.operators.OperatorTakeUntilPredicate */
public final class OperatorTakeUntilPredicate<T> implements Observable.Operator<T, T> {
    final Func1<? super T, Boolean> stopPredicate;

    public OperatorTakeUntilPredicate(Func1<? super T, Boolean> func1) {
        this.stopPredicate = func1;
    }

    public Subscriber<? super T> call(Subscriber<? super T> subscriber) {
        final ParentSubscriber parentSubscriber = new ParentSubscriber(subscriber);
        subscriber.add(parentSubscriber);
        subscriber.setProducer(new Producer() {
            public void request(long j) {
                parentSubscriber.downstreamRequest(j);
            }
        });
        return parentSubscriber;
    }

    /* renamed from: rx.internal.operators.OperatorTakeUntilPredicate$ParentSubscriber */
    final class ParentSubscriber extends Subscriber<T> {
        private final Subscriber<? super T> child;
        private boolean done;

        ParentSubscriber(Subscriber<? super T> subscriber) {
            this.child = subscriber;
        }

        public void onNext(T t) {
            this.child.onNext(t);
            try {
                if (OperatorTakeUntilPredicate.this.stopPredicate.call(t).booleanValue()) {
                    this.done = true;
                    this.child.onCompleted();
                    unsubscribe();
                }
            } catch (Throwable th) {
                this.done = true;
                Exceptions.throwOrReport(th, (Observer<?>) this.child, (Object) t);
                unsubscribe();
            }
        }

        public void onCompleted() {
            if (!this.done) {
                this.child.onCompleted();
            }
        }

        public void onError(Throwable th) {
            if (!this.done) {
                this.child.onError(th);
            }
        }

        /* access modifiers changed from: package-private */
        public void downstreamRequest(long j) {
            request(j);
        }
    }
}

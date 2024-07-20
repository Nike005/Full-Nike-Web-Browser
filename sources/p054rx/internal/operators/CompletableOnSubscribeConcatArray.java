package p054rx.internal.operators;

import java.util.concurrent.atomic.AtomicInteger;
import p054rx.Completable;
import p054rx.CompletableSubscriber;
import p054rx.Subscription;
import p054rx.internal.subscriptions.SequentialSubscription;

/* renamed from: rx.internal.operators.CompletableOnSubscribeConcatArray */
public final class CompletableOnSubscribeConcatArray implements Completable.OnSubscribe {
    final Completable[] sources;

    public CompletableOnSubscribeConcatArray(Completable[] completableArr) {
        this.sources = completableArr;
    }

    public void call(CompletableSubscriber completableSubscriber) {
        ConcatInnerSubscriber concatInnerSubscriber = new ConcatInnerSubscriber(completableSubscriber, this.sources);
        completableSubscriber.onSubscribe(concatInnerSubscriber.f3975sd);
        concatInnerSubscriber.next();
    }

    /* renamed from: rx.internal.operators.CompletableOnSubscribeConcatArray$ConcatInnerSubscriber */
    static final class ConcatInnerSubscriber extends AtomicInteger implements CompletableSubscriber {
        private static final long serialVersionUID = -7965400327305809232L;
        final CompletableSubscriber actual;
        int index;

        /* renamed from: sd */
        final SequentialSubscription f3975sd = new SequentialSubscription();
        final Completable[] sources;

        public ConcatInnerSubscriber(CompletableSubscriber completableSubscriber, Completable[] completableArr) {
            this.actual = completableSubscriber;
            this.sources = completableArr;
        }

        public void onSubscribe(Subscription subscription) {
            this.f3975sd.replace(subscription);
        }

        public void onError(Throwable th) {
            this.actual.onError(th);
        }

        public void onCompleted() {
            next();
        }

        /* access modifiers changed from: package-private */
        public void next() {
            if (!this.f3975sd.isUnsubscribed() && getAndIncrement() == 0) {
                Completable[] completableArr = this.sources;
                while (!this.f3975sd.isUnsubscribed()) {
                    int i = this.index;
                    this.index = i + 1;
                    if (i == completableArr.length) {
                        this.actual.onCompleted();
                        return;
                    }
                    completableArr[i].unsafeSubscribe((CompletableSubscriber) this);
                    if (decrementAndGet() == 0) {
                        return;
                    }
                }
            }
        }
    }
}

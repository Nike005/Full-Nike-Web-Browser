package p054rx.internal.operators;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import p054rx.Observable;
import p054rx.Observer;
import p054rx.Subscriber;
import p054rx.exceptions.Exceptions;
import p054rx.internal.producers.SingleDelayedProducer;

/* renamed from: rx.internal.operators.OperatorToObservableList */
public final class OperatorToObservableList<T> implements Observable.Operator<List<T>, T> {

    /* renamed from: rx.internal.operators.OperatorToObservableList$Holder */
    static final class Holder {
        static final OperatorToObservableList<Object> INSTANCE = new OperatorToObservableList<>();

        Holder() {
        }
    }

    public static <T> OperatorToObservableList<T> instance() {
        return Holder.INSTANCE;
    }

    OperatorToObservableList() {
    }

    public Subscriber<? super T> call(final Subscriber<? super List<T>> subscriber) {
        final SingleDelayedProducer singleDelayedProducer = new SingleDelayedProducer(subscriber);
        C27801 r1 = new Subscriber<T>() {
            boolean completed;
            List<T> list = new LinkedList();

            public void onStart() {
                request(Long.MAX_VALUE);
            }

            public void onCompleted() {
                if (!this.completed) {
                    this.completed = true;
                    try {
                        ArrayList arrayList = new ArrayList(this.list);
                        this.list = null;
                        singleDelayedProducer.setValue(arrayList);
                    } catch (Throwable th) {
                        Exceptions.throwOrReport(th, (Observer<?>) this);
                    }
                }
            }

            public void onError(Throwable th) {
                subscriber.onError(th);
            }

            public void onNext(T t) {
                if (!this.completed) {
                    this.list.add(t);
                }
            }
        };
        subscriber.add(r1);
        subscriber.setProducer(singleDelayedProducer);
        return r1;
    }
}

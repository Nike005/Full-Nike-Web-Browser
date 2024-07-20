package p054rx.observers;

import p054rx.Observer;
import p054rx.Subscriber;

/* renamed from: rx.observers.SerializedSubscriber */
public class SerializedSubscriber<T> extends Subscriber<T> {

    /* renamed from: s */
    private final Observer<T> f4012s;

    public SerializedSubscriber(Subscriber<? super T> subscriber) {
        this(subscriber, true);
    }

    public SerializedSubscriber(Subscriber<? super T> subscriber, boolean z) {
        super(subscriber, z);
        this.f4012s = new SerializedObserver(subscriber);
    }

    public void onCompleted() {
        this.f4012s.onCompleted();
    }

    public void onError(Throwable th) {
        this.f4012s.onError(th);
    }

    public void onNext(T t) {
        this.f4012s.onNext(t);
    }
}

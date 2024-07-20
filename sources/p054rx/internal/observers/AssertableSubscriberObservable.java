package p054rx.internal.observers;

import java.util.List;
import java.util.concurrent.TimeUnit;
import p054rx.Producer;
import p054rx.Subscriber;
import p054rx.functions.Action0;
import p054rx.observers.AssertableSubscriber;
import p054rx.observers.TestSubscriber;

/* renamed from: rx.internal.observers.AssertableSubscriberObservable */
public class AssertableSubscriberObservable<T> extends Subscriber<T> implements AssertableSubscriber<T> {

    /* renamed from: ts */
    private final TestSubscriber<T> f3973ts;

    public AssertableSubscriberObservable(TestSubscriber<T> testSubscriber) {
        this.f3973ts = testSubscriber;
    }

    public static <T> AssertableSubscriberObservable<T> create(long j) {
        TestSubscriber testSubscriber = new TestSubscriber(j);
        AssertableSubscriberObservable<T> assertableSubscriberObservable = new AssertableSubscriberObservable<>(testSubscriber);
        assertableSubscriberObservable.add(testSubscriber);
        return assertableSubscriberObservable;
    }

    public void onStart() {
        this.f3973ts.onStart();
    }

    public void onCompleted() {
        this.f3973ts.onCompleted();
    }

    public void setProducer(Producer producer) {
        this.f3973ts.setProducer(producer);
    }

    public final int getCompletions() {
        return this.f3973ts.getCompletions();
    }

    public void onError(Throwable th) {
        this.f3973ts.onError(th);
    }

    public List<Throwable> getOnErrorEvents() {
        return this.f3973ts.getOnErrorEvents();
    }

    public void onNext(T t) {
        this.f3973ts.onNext(t);
    }

    public final int getValueCount() {
        return this.f3973ts.getValueCount();
    }

    public AssertableSubscriber<T> requestMore(long j) {
        this.f3973ts.requestMore(j);
        return this;
    }

    public List<T> getOnNextEvents() {
        return this.f3973ts.getOnNextEvents();
    }

    public AssertableSubscriber<T> assertReceivedOnNext(List<T> list) {
        this.f3973ts.assertReceivedOnNext(list);
        return this;
    }

    public final AssertableSubscriber<T> awaitValueCount(int i, long j, TimeUnit timeUnit) {
        if (this.f3973ts.awaitValueCount(i, j, timeUnit)) {
            return this;
        }
        throw new AssertionError("Did not receive enough values in time. Expected: " + i + ", Actual: " + this.f3973ts.getValueCount());
    }

    public AssertableSubscriber<T> assertTerminalEvent() {
        this.f3973ts.assertTerminalEvent();
        return this;
    }

    public AssertableSubscriber<T> assertUnsubscribed() {
        this.f3973ts.assertUnsubscribed();
        return this;
    }

    public AssertableSubscriber<T> assertNoErrors() {
        this.f3973ts.assertNoErrors();
        return this;
    }

    public AssertableSubscriber<T> awaitTerminalEvent() {
        this.f3973ts.awaitTerminalEvent();
        return this;
    }

    public AssertableSubscriber<T> awaitTerminalEvent(long j, TimeUnit timeUnit) {
        this.f3973ts.awaitTerminalEvent(j, timeUnit);
        return this;
    }

    public AssertableSubscriber<T> awaitTerminalEventAndUnsubscribeOnTimeout(long j, TimeUnit timeUnit) {
        this.f3973ts.awaitTerminalEventAndUnsubscribeOnTimeout(j, timeUnit);
        return this;
    }

    public Thread getLastSeenThread() {
        return this.f3973ts.getLastSeenThread();
    }

    public AssertableSubscriber<T> assertCompleted() {
        this.f3973ts.assertCompleted();
        return this;
    }

    public AssertableSubscriber<T> assertNotCompleted() {
        this.f3973ts.assertNotCompleted();
        return this;
    }

    public AssertableSubscriber<T> assertError(Class<? extends Throwable> cls) {
        this.f3973ts.assertError(cls);
        return this;
    }

    public AssertableSubscriber<T> assertError(Throwable th) {
        this.f3973ts.assertError(th);
        return this;
    }

    public AssertableSubscriber<T> assertNoTerminalEvent() {
        this.f3973ts.assertNoTerminalEvent();
        return this;
    }

    public AssertableSubscriber<T> assertNoValues() {
        this.f3973ts.assertNoValues();
        return this;
    }

    public AssertableSubscriber<T> assertValueCount(int i) {
        this.f3973ts.assertValueCount(i);
        return this;
    }

    public AssertableSubscriber<T> assertValues(T... tArr) {
        this.f3973ts.assertValues(tArr);
        return this;
    }

    public AssertableSubscriber<T> assertValue(T t) {
        this.f3973ts.assertValue(t);
        return this;
    }

    public final AssertableSubscriber<T> assertValuesAndClear(T t, T... tArr) {
        this.f3973ts.assertValuesAndClear(t, tArr);
        return this;
    }

    public final AssertableSubscriber<T> perform(Action0 action0) {
        action0.call();
        return this;
    }

    public String toString() {
        return this.f3973ts.toString();
    }

    public final AssertableSubscriber<T> assertResult(T... tArr) {
        this.f3973ts.assertValues(tArr);
        this.f3973ts.assertNoErrors();
        this.f3973ts.assertCompleted();
        return this;
    }

    public final AssertableSubscriber<T> assertFailure(Class<? extends Throwable> cls, T... tArr) {
        this.f3973ts.assertValues(tArr);
        this.f3973ts.assertError(cls);
        this.f3973ts.assertNotCompleted();
        return this;
    }

    public final AssertableSubscriber<T> assertFailureAndMessage(Class<? extends Throwable> cls, String str, T... tArr) {
        this.f3973ts.assertValues(tArr);
        this.f3973ts.assertError(cls);
        this.f3973ts.assertNotCompleted();
        String message = this.f3973ts.getOnErrorEvents().get(0).getMessage();
        if (message == str || (str != null && str.equals(message))) {
            return this;
        }
        throw new AssertionError("Error message differs. Expected: '" + str + "', Received: '" + message + "'");
    }
}

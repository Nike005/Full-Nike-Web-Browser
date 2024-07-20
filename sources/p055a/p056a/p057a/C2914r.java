package p055a.p056a.p057a;

import java.util.ListIterator;
import java.util.NoSuchElementException;

/* renamed from: a.a.a.r */
/* compiled from: StartAppSDK */
public final class C2914r implements ListIterator {

    /* renamed from: a */
    public static final C2914r f4015a = new C2914r();

    public /* synthetic */ void add(Object obj) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public boolean hasNext() {
        return false;
    }

    public boolean hasPrevious() {
        return false;
    }

    public int nextIndex() {
        return 0;
    }

    public int previousIndex() {
        return -1;
    }

    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public /* synthetic */ void set(Object obj) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    private C2914r() {
    }

    /* renamed from: a */
    public Void next() {
        throw new NoSuchElementException();
    }

    /* renamed from: b */
    public Void previous() {
        throw new NoSuchElementException();
    }
}

package p055a.p056a.p057a;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;
import org.apache.commons.lang3.ClassUtils;
import p055a.p056a.p058b.p060b.C2924d;
import p055a.p056a.p058b.p060b.C2928h;

/* renamed from: a.a.a.s */
/* compiled from: StartAppSDK */
public final class C2915s implements Serializable, List, RandomAccess {

    /* renamed from: a */
    public static final C2915s f4016a = new C2915s();
    private static final long serialVersionUID = -7390468764508069838L;

    /* renamed from: a */
    public int mo24932a() {
        return 0;
    }

    /* renamed from: a */
    public boolean mo24934a(Void voidR) {
        C2928h.m6157b(voidR, "element");
        return false;
    }

    public /* synthetic */ void add(int i, Object obj) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public /* synthetic */ boolean add(Object obj) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public boolean addAll(int i, Collection collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public boolean addAll(Collection collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    /* renamed from: b */
    public int mo24939b(Void voidR) {
        C2928h.m6157b(voidR, "element");
        return -1;
    }

    /* renamed from: c */
    public int mo24940c(Void voidR) {
        C2928h.m6157b(voidR, "element");
        return -1;
    }

    public void clear() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public int hashCode() {
        return 1;
    }

    public boolean isEmpty() {
        return true;
    }

    public /* synthetic */ Object remove(int i) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public boolean remove(Object obj) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public boolean removeAll(Collection collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public boolean retainAll(Collection collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public /* synthetic */ Object set(int i, Object obj) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public Object[] toArray() {
        return C2924d.m6148a(this);
    }

    public <T> T[] toArray(T[] tArr) {
        return C2924d.m6149a(this, tArr);
    }

    public String toString() {
        return "[]";
    }

    private C2915s() {
    }

    public final boolean contains(Object obj) {
        if (obj instanceof Void) {
            return mo24934a((Void) obj);
        }
        return false;
    }

    public final int indexOf(Object obj) {
        if (obj instanceof Void) {
            return mo24939b((Void) obj);
        }
        return -1;
    }

    public final int lastIndexOf(Object obj) {
        if (obj instanceof Void) {
            return mo24940c((Void) obj);
        }
        return -1;
    }

    public final int size() {
        return mo24932a();
    }

    public boolean equals(Object obj) {
        return (obj instanceof List) && ((List) obj).isEmpty();
    }

    public boolean containsAll(Collection collection) {
        C2928h.m6157b(collection, "elements");
        return collection.isEmpty();
    }

    /* renamed from: a */
    public Void get(int i) {
        throw new IndexOutOfBoundsException("Empty list doesn't contain element at index " + i + ClassUtils.PACKAGE_SEPARATOR_CHAR);
    }

    public Iterator iterator() {
        return C2914r.f4015a;
    }

    public ListIterator listIterator() {
        return C2914r.f4015a;
    }

    public ListIterator listIterator(int i) {
        if (i == 0) {
            return C2914r.f4015a;
        }
        throw new IndexOutOfBoundsException("Index: " + i);
    }

    public List subList(int i, int i2) {
        if (i == 0 && i2 == 0) {
            return this;
        }
        throw new IndexOutOfBoundsException("fromIndex: " + i + ", toIndex: " + i2);
    }

    private final Object readResolve() {
        return f4016a;
    }
}

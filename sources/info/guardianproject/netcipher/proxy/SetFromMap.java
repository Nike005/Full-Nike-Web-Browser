package info.guardianproject.netcipher.proxy;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.AbstractSet;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

class SetFromMap<E> extends AbstractSet<E> implements Serializable {
    private static final long serialVersionUID = 2454657854757543876L;
    private transient Set<E> backingSet;

    /* renamed from: m */
    private final Map<E, Boolean> f3930m;

    SetFromMap(Map<E, Boolean> map) {
        this.f3930m = map;
        this.backingSet = map.keySet();
    }

    public boolean equals(Object obj) {
        return this.backingSet.equals(obj);
    }

    public int hashCode() {
        return this.backingSet.hashCode();
    }

    public boolean add(E e) {
        return this.f3930m.put(e, Boolean.TRUE) == null;
    }

    public void clear() {
        this.f3930m.clear();
    }

    public String toString() {
        return this.backingSet.toString();
    }

    public boolean contains(Object obj) {
        return this.backingSet.contains(obj);
    }

    public boolean containsAll(Collection<?> collection) {
        return this.backingSet.containsAll(collection);
    }

    public boolean isEmpty() {
        return this.f3930m.isEmpty();
    }

    public boolean remove(Object obj) {
        return this.f3930m.remove(obj) != null;
    }

    public boolean retainAll(Collection<?> collection) {
        return this.backingSet.retainAll(collection);
    }

    public Object[] toArray() {
        return this.backingSet.toArray();
    }

    public <T> T[] toArray(T[] tArr) {
        return this.backingSet.toArray(tArr);
    }

    public Iterator<E> iterator() {
        return this.backingSet.iterator();
    }

    public int size() {
        return this.f3930m.size();
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        this.backingSet = this.f3930m.keySet();
    }
}

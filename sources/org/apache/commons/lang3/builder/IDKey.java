package org.apache.commons.lang3.builder;

final class IDKey {

    /* renamed from: id */
    private final int f3950id;
    private final Object value;

    public IDKey(Object obj) {
        this.f3950id = System.identityHashCode(obj);
        this.value = obj;
    }

    public int hashCode() {
        return this.f3950id;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof IDKey)) {
            return false;
        }
        IDKey iDKey = (IDKey) obj;
        if (this.f3950id == iDKey.f3950id && this.value == iDKey.value) {
            return true;
        }
        return false;
    }
}

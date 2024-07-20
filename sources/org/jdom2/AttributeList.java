package org.jdom2;

import java.util.AbstractList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.RandomAccess;
import org.jdom2.internal.ArrayCopy;

final class AttributeList extends AbstractList<Attribute> implements RandomAccess {
    private static final int INITIAL_ARRAY_SIZE = 4;
    /* access modifiers changed from: private */
    public Attribute[] attributeData;
    private final Element parent;
    /* access modifiers changed from: private */
    public int size;

    AttributeList(Element element) {
        this.parent = element;
    }

    /* access modifiers changed from: package-private */
    public final void uncheckedAddAttribute(Attribute attribute) {
        attribute.parent = this.parent;
        ensureCapacity(this.size + 1);
        Attribute[] attributeArr = this.attributeData;
        int i = this.size;
        this.size = i + 1;
        attributeArr[i] = attribute;
        this.modCount++;
    }

    public boolean add(Attribute attribute) {
        if (attribute.getParent() != null) {
            throw new IllegalAddException("The attribute already has an existing parent \"" + attribute.getParent().getQualifiedName() + "\"");
        } else if (Verifier.checkNamespaceCollision(attribute, this.parent) == null) {
            int indexOfDuplicate = indexOfDuplicate(attribute);
            if (indexOfDuplicate < 0) {
                attribute.setParent(this.parent);
                ensureCapacity(this.size + 1);
                Attribute[] attributeArr = this.attributeData;
                int i = this.size;
                this.size = i + 1;
                attributeArr[i] = attribute;
                this.modCount++;
            } else {
                this.attributeData[indexOfDuplicate].setParent((Element) null);
                this.attributeData[indexOfDuplicate] = attribute;
                attribute.setParent(this.parent);
            }
            return true;
        } else {
            Element element = this.parent;
            throw new IllegalAddException(element, attribute, Verifier.checkNamespaceCollision(attribute, element));
        }
    }

    public void add(int i, Attribute attribute) {
        if (i < 0 || i > this.size) {
            throw new IndexOutOfBoundsException("Index: " + i + " Size: " + size());
        } else if (attribute.getParent() != null) {
            throw new IllegalAddException("The attribute already has an existing parent \"" + attribute.getParent().getQualifiedName() + "\"");
        } else if (indexOfDuplicate(attribute) < 0) {
            String checkNamespaceCollision = Verifier.checkNamespaceCollision(attribute, this.parent);
            if (checkNamespaceCollision == null) {
                attribute.setParent(this.parent);
                ensureCapacity(this.size + 1);
                int i2 = this.size;
                if (i == i2) {
                    Attribute[] attributeArr = this.attributeData;
                    this.size = i2 + 1;
                    attributeArr[i2] = attribute;
                } else {
                    Attribute[] attributeArr2 = this.attributeData;
                    System.arraycopy(attributeArr2, i, attributeArr2, i + 1, i2 - i);
                    this.attributeData[i] = attribute;
                    this.size++;
                }
                this.modCount++;
                return;
            }
            throw new IllegalAddException(this.parent, attribute, checkNamespaceCollision);
        } else {
            throw new IllegalAddException("Cannot add duplicate attribute");
        }
    }

    public boolean addAll(Collection<? extends Attribute> collection) {
        return addAll(size(), collection);
    }

    public boolean addAll(int i, Collection<? extends Attribute> collection) {
        if (i < 0 || i > this.size) {
            throw new IndexOutOfBoundsException("Index: " + i + " Size: " + size());
        } else if (collection != null) {
            int size2 = collection.size();
            int i2 = 0;
            if (size2 == 0) {
                return false;
            }
            if (size2 == 1) {
                add(i, (Attribute) collection.iterator().next());
                return true;
            }
            ensureCapacity(size() + size2);
            int i3 = this.modCount;
            try {
                for (Attribute add : collection) {
                    add(i + i2, add);
                    i2++;
                }
                return true;
            } catch (Throwable th) {
                while (true) {
                    i2--;
                    if (i2 >= 0) {
                        remove(i + i2);
                    } else {
                        this.modCount = i3;
                        throw th;
                    }
                }
            }
        } else {
            throw new NullPointerException("Can not add a null Collection to AttributeList");
        }
    }

    public void clear() {
        if (this.attributeData != null) {
            while (true) {
                int i = this.size;
                if (i <= 0) {
                    break;
                }
                int i2 = i - 1;
                this.size = i2;
                this.attributeData[i2].setParent((Element) null);
                this.attributeData[this.size] = null;
            }
        }
        this.modCount++;
    }

    /* access modifiers changed from: package-private */
    public void clearAndSet(Collection<? extends Attribute> collection) {
        if (collection == null || collection.isEmpty()) {
            clear();
            return;
        }
        Attribute[] attributeArr = this.attributeData;
        int i = this.size;
        int i2 = this.modCount;
        while (true) {
            int i3 = this.size;
            if (i3 > 0) {
                int i4 = i3 - 1;
                this.size = i4;
                attributeArr[i4].setParent((Element) null);
            } else {
                this.size = 0;
                this.attributeData = null;
                try {
                    addAll(0, collection);
                    return;
                } catch (Throwable th) {
                    this.attributeData = attributeArr;
                    while (true) {
                        int i5 = this.size;
                        if (i5 < i) {
                            Attribute[] attributeArr2 = this.attributeData;
                            this.size = i5 + 1;
                            attributeArr2[i5].setParent(this.parent);
                        } else {
                            this.modCount = i2;
                            throw th;
                        }
                    }
                }
            }
        }
    }

    private void ensureCapacity(int i) {
        Attribute[] attributeArr = this.attributeData;
        if (attributeArr == null) {
            this.attributeData = new Attribute[Math.max(i, 4)];
        } else if (i >= attributeArr.length) {
            this.attributeData = (Attribute[]) ArrayCopy.copyOf((E[]) attributeArr, ((i + 4) >>> 1) << 1);
        }
    }

    public Attribute get(int i) {
        if (i >= 0 && i < this.size) {
            return this.attributeData[i];
        }
        throw new IndexOutOfBoundsException("Index: " + i + " Size: " + size());
    }

    /* access modifiers changed from: package-private */
    public Attribute get(String str, Namespace namespace) {
        int indexOf = indexOf(str, namespace);
        if (indexOf < 0) {
            return null;
        }
        return this.attributeData[indexOf];
    }

    /* access modifiers changed from: package-private */
    public int indexOf(String str, Namespace namespace) {
        if (this.attributeData == null) {
            return -1;
        }
        if (namespace == null) {
            return indexOf(str, Namespace.NO_NAMESPACE);
        }
        String uri = namespace.getURI();
        for (int i = 0; i < this.size; i++) {
            Attribute attribute = this.attributeData[i];
            if (uri.equals(attribute.getNamespaceURI()) && str.equals(attribute.getName())) {
                return i;
            }
        }
        return -1;
    }

    public Attribute remove(int i) {
        if (i < 0 || i >= this.size) {
            throw new IndexOutOfBoundsException("Index: " + i + " Size: " + size());
        }
        Attribute attribute = this.attributeData[i];
        attribute.setParent((Element) null);
        Attribute[] attributeArr = this.attributeData;
        System.arraycopy(attributeArr, i + 1, attributeArr, i, (this.size - i) - 1);
        Attribute[] attributeArr2 = this.attributeData;
        int i2 = this.size - 1;
        this.size = i2;
        attributeArr2[i2] = null;
        this.modCount++;
        return attribute;
    }

    /* access modifiers changed from: package-private */
    public boolean remove(String str, Namespace namespace) {
        int indexOf = indexOf(str, namespace);
        if (indexOf < 0) {
            return false;
        }
        remove(indexOf);
        return true;
    }

    public Attribute set(int i, Attribute attribute) {
        if (i < 0 || i >= this.size) {
            throw new IndexOutOfBoundsException("Index: " + i + " Size: " + size());
        } else if (attribute.getParent() == null) {
            int indexOfDuplicate = indexOfDuplicate(attribute);
            if (indexOfDuplicate < 0 || indexOfDuplicate == i) {
                String checkNamespaceCollision = Verifier.checkNamespaceCollision(attribute, this.parent, i);
                if (checkNamespaceCollision == null) {
                    Attribute attribute2 = this.attributeData[i];
                    attribute2.setParent((Element) null);
                    this.attributeData[i] = attribute;
                    attribute.setParent(this.parent);
                    return attribute2;
                }
                throw new IllegalAddException(this.parent, attribute, checkNamespaceCollision);
            }
            throw new IllegalAddException("Cannot set duplicate attribute");
        } else {
            throw new IllegalAddException("The attribute already has an existing parent \"" + attribute.getParent().getQualifiedName() + "\"");
        }
    }

    private int indexOfDuplicate(Attribute attribute) {
        return indexOf(attribute.getName(), attribute.getNamespace());
    }

    public Iterator<Attribute> iterator() {
        return new ALIterator();
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public String toString() {
        return super.toString();
    }

    private final int binarySearch(int[] iArr, int i, int i2, Comparator<? super Attribute> comparator) {
        int i3 = i - 1;
        Attribute attribute = this.attributeData[i2];
        int i4 = 0;
        while (i4 <= i3) {
            int i5 = (i4 + i3) >>> 1;
            int compare = comparator.compare(attribute, this.attributeData[iArr[i5]]);
            if (compare == 0) {
                while (compare == 0 && i5 < i3) {
                    int i6 = i5 + 1;
                    if (comparator.compare(attribute, this.attributeData[iArr[i6]]) != 0) {
                        break;
                    }
                    i5 = i6;
                }
                return i5 + 1;
            } else if (compare < 0) {
                i3 = i5 - 1;
            } else {
                i4 = i5 + 1;
            }
        }
        return i4;
    }

    private void sortInPlace(int[] iArr) {
        int[] copyOf = ArrayCopy.copyOf(iArr, iArr.length);
        Arrays.sort(copyOf);
        int length = copyOf.length;
        Attribute[] attributeArr = new Attribute[length];
        for (int i = 0; i < length; i++) {
            attributeArr[i] = this.attributeData[iArr[i]];
        }
        for (int i2 = 0; i2 < iArr.length; i2++) {
            this.attributeData[copyOf[i2]] = attributeArr[i2];
        }
    }

    /* access modifiers changed from: package-private */
    public void sort(Comparator<? super Attribute> comparator) {
        int i = this.size;
        int[] iArr = new int[i];
        for (int i2 = 0; i2 < i; i2++) {
            int binarySearch = binarySearch(iArr, i2, i2, comparator);
            if (binarySearch < i2) {
                System.arraycopy(iArr, binarySearch, iArr, binarySearch + 1, i2 - binarySearch);
            }
            iArr[binarySearch] = i2;
        }
        sortInPlace(iArr);
    }

    private final class ALIterator implements Iterator<Attribute> {
        private boolean canremove;
        private int cursor;
        private int expect;

        private ALIterator() {
            this.expect = -1;
            this.cursor = 0;
            this.canremove = false;
            this.expect = AttributeList.this.modCount;
        }

        public boolean hasNext() {
            return this.cursor < AttributeList.this.size;
        }

        public Attribute next() {
            if (AttributeList.this.modCount != this.expect) {
                throw new ConcurrentModificationException("ContentList was modified outside of this Iterator");
            } else if (this.cursor < AttributeList.this.size) {
                this.canremove = true;
                Attribute[] access$400 = AttributeList.this.attributeData;
                int i = this.cursor;
                this.cursor = i + 1;
                return access$400[i];
            } else {
                throw new NoSuchElementException("Iterated beyond the end of the ContentList.");
            }
        }

        public void remove() {
            if (AttributeList.this.modCount != this.expect) {
                throw new ConcurrentModificationException("ContentList was modified outside of this Iterator");
            } else if (this.canremove) {
                AttributeList attributeList = AttributeList.this;
                int i = this.cursor - 1;
                this.cursor = i;
                attributeList.remove(i);
                this.expect = AttributeList.this.modCount;
                this.canremove = false;
            } else {
                throw new IllegalStateException("Can only remove() content after a call to next()");
            }
        }
    }
}

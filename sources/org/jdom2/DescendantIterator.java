package org.jdom2;

import java.util.Iterator;
import org.jdom2.internal.ArrayCopy;
import org.jdom2.util.IteratorIterable;

final class DescendantIterator implements IteratorIterable<Content> {
    private Iterator<Content> ascending = null;
    private Iterator<Content> current = null;
    private Iterator<Content> descending = null;
    private boolean hasnext = true;
    private final Parent parent;
    private int ssize = 0;
    private Object[] stack = new Object[16];

    DescendantIterator(Parent parent2) {
        this.parent = parent2;
        Iterator<Content> it = parent2.getContent().iterator();
        this.current = it;
        this.hasnext = it.hasNext();
    }

    public DescendantIterator iterator() {
        return new DescendantIterator(this.parent);
    }

    public boolean hasNext() {
        return this.hasnext;
    }

    public Content next() {
        Iterator<Content> it;
        Iterator<Content> it2 = this.descending;
        if (it2 != null) {
            this.current = it2;
            this.descending = null;
        } else {
            Iterator<Content> it3 = this.ascending;
            if (it3 != null) {
                this.current = it3;
                this.ascending = null;
            }
        }
        Content next = this.current.next();
        if (next instanceof Element) {
            Element element = (Element) next;
            if (element.getContentSize() > 0) {
                this.descending = element.getContent().iterator();
                int i = this.ssize;
                Object[] objArr = this.stack;
                if (i >= objArr.length) {
                    this.stack = ArrayCopy.copyOf((E[]) objArr, i + 16);
                }
                Object[] objArr2 = this.stack;
                int i2 = this.ssize;
                this.ssize = i2 + 1;
                objArr2[i2] = this.current;
                return next;
            }
        }
        if (this.current.hasNext()) {
            return next;
        }
        do {
            int i3 = this.ssize;
            if (i3 > 0) {
                Object[] objArr3 = this.stack;
                int i4 = i3 - 1;
                this.ssize = i4;
                it = (Iterator) objArr3[i4];
                this.ascending = it;
                objArr3[i4] = null;
            } else {
                this.ascending = null;
                this.hasnext = false;
                return next;
            }
        } while (!it.hasNext());
        return next;
    }

    public void remove() {
        Iterator<Content> it;
        this.current.remove();
        this.descending = null;
        if (!this.current.hasNext() && this.ascending == null) {
            do {
                int i = this.ssize;
                if (i > 0) {
                    Object[] objArr = this.stack;
                    int i2 = i - 1;
                    this.ssize = i2;
                    it = (Iterator) objArr[i2];
                    objArr[i2] = null;
                    this.ascending = it;
                } else {
                    this.ascending = null;
                    this.hasnext = false;
                    return;
                }
            } while (!it.hasNext());
        }
    }
}

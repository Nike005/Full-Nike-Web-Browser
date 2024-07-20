package p055a.p056a.p057a;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;
import p055a.p056a.p058b.p060b.C2928h;

/* renamed from: a.a.a.q */
/* compiled from: StartAppSDK */
class C2913q extends C2912p {
    /* renamed from: c */
    public static final <T> T m6126c(List<? extends T> list) {
        C2928h.m6157b(list, "$receiver");
        if (!list.isEmpty()) {
            return list.get(0);
        }
        throw new NoSuchElementException("List is empty.");
    }

    /* renamed from: d */
    public static final <T> T m6127d(List<? extends T> list) {
        C2928h.m6157b(list, "$receiver");
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    /* renamed from: e */
    public static final <T> T m6128e(List<? extends T> list) {
        C2928h.m6157b(list, "$receiver");
        if (!list.isEmpty()) {
            return list.get(C2903g.m6117a(list));
        }
        throw new NoSuchElementException("List is empty.");
    }

    /* renamed from: a */
    public static final <T, C extends Collection<? super T>> C m6122a(Iterable<? extends T> iterable, C c) {
        C2928h.m6157b(iterable, "$receiver");
        C2928h.m6157b(c, "destination");
        for (Object add : iterable) {
            c.add(add);
        }
        return c;
    }

    /* renamed from: a */
    public static final <T> List<T> m6123a(Iterable<? extends T> iterable) {
        C2928h.m6157b(iterable, "$receiver");
        if (!(iterable instanceof Collection)) {
            return C2903g.m6120b(C2903g.m6125b(iterable));
        }
        Collection collection = (Collection) iterable;
        int size = collection.size();
        if (size == 0) {
            return C2903g.m6118a();
        }
        if (size != 1) {
            return C2903g.m6124a(collection);
        }
        return C2903g.m6116a(iterable instanceof List ? ((List) iterable).get(0) : iterable.iterator().next());
    }

    /* renamed from: b */
    public static final <T> List<T> m6125b(Iterable<? extends T> iterable) {
        C2928h.m6157b(iterable, "$receiver");
        if (iterable instanceof Collection) {
            return C2903g.m6124a((Collection) iterable);
        }
        return (List) C2903g.m6122a(iterable, new ArrayList());
    }

    /* renamed from: a */
    public static final <T> List<T> m6124a(Collection<? extends T> collection) {
        C2928h.m6157b(collection, "$receiver");
        return new ArrayList<>(collection);
    }
}

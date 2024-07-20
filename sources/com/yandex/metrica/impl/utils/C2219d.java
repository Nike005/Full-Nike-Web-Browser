package com.yandex.metrica.impl.utils;

import java.util.Collection;
import java.util.HashSet;

/* renamed from: com.yandex.metrica.impl.utils.d */
public class C2219d {
    /* renamed from: a */
    public static boolean m5933a(Collection<?> collection, Collection<?> collection2) {
        HashSet hashSet;
        if (collection == null && collection2 == null) {
            return true;
        }
        if (collection == null || collection2 == null || collection.size() != collection2.size()) {
            return false;
        }
        if (collection instanceof HashSet) {
            Collection<?> collection3 = collection2;
            hashSet = (HashSet) collection;
            collection = collection3;
        } else if (collection2 instanceof HashSet) {
            hashSet = (HashSet) collection2;
        } else {
            HashSet hashSet2 = new HashSet(collection);
            collection = collection2;
            hashSet = hashSet2;
        }
        for (Object contains : collection) {
            if (!hashSet.contains(contains)) {
                return false;
            }
        }
        return true;
    }
}

package com.yandex.metrica.impl.p050ob;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/* renamed from: com.yandex.metrica.impl.ob.es */
class C2130es implements C2133ev {

    /* renamed from: a */
    private Map<String, Set<String>> f3660a = new ConcurrentHashMap();

    /* renamed from: b */
    private volatile AtomicLong f3661b = new AtomicLong();

    /* renamed from: a */
    public void mo17718a(String str, String[] strArr) {
        if (!this.f3660a.keySet().contains(str)) {
            this.f3660a.put(str, new HashSet(Arrays.asList(strArr)));
            m5609d();
        }
    }

    /* renamed from: c */
    public Map<String, Set<String>> mo17724c() {
        HashMap hashMap = new HashMap();
        for (String next : this.f3660a.keySet()) {
            hashMap.put(next, mo17716a(next));
        }
        return hashMap;
    }

    /* renamed from: a */
    public void mo17723a(Map<String, Set<String>> map) {
        this.f3660a = new ConcurrentHashMap(map);
        m5609d();
    }

    /* renamed from: a */
    public Set<String> mo17716a(String str) {
        Set set = this.f3660a.get(str);
        if (set == null) {
            return null;
        }
        return new HashSet(set);
    }

    /* renamed from: a */
    public boolean mo17719a(String str, String str2) {
        Set set = this.f3660a.get(str);
        if (set == null) {
            set = new HashSet();
            this.f3660a.put(str, set);
        }
        m5609d();
        return set.add(str2);
    }

    /* renamed from: a */
    public void mo17717a(String str, Set<String> set) {
        this.f3660a.put(str, new HashSet(set));
        m5609d();
    }

    /* renamed from: a */
    public long mo17715a() {
        return this.f3661b.get();
    }

    /* renamed from: b */
    public void mo17720b() {
        m5609d();
    }

    /* renamed from: d */
    private void m5609d() {
        this.f3661b.set(System.currentTimeMillis());
    }
}

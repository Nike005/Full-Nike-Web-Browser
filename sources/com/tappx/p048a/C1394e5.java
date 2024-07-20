package com.tappx.p048a;

import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* renamed from: com.tappx.a.e5 */
public class C1394e5 {

    /* renamed from: a */
    private static final C1394e5 f1785a = new C1394e5();

    /* renamed from: b */
    private static final Map<Integer, C1395a> f1786b = Collections.synchronizedMap(new HashMap());

    /* renamed from: com.tappx.a.e5$a */
    public static class C1395a {

        /* renamed from: a */
        private final C1625t3 f1787a;

        /* renamed from: b */
        private final WeakReference<C1354c5> f1788b;

        public C1395a(C1625t3 t3Var, C1354c5 c5Var) {
            this.f1787a = t3Var;
            this.f1788b = new WeakReference<>(c5Var);
        }

        /* renamed from: a */
        public C1625t3 mo15745a() {
            return this.f1787a;
        }

        /* renamed from: b */
        public WeakReference<C1354c5> mo15746b() {
            return this.f1788b;
        }
    }

    /* renamed from: a */
    public static C1394e5 m2567a() {
        return f1785a;
    }

    /* renamed from: b */
    private synchronized void m2568b() {
        Iterator<Map.Entry<Integer, C1395a>> it = f1786b.entrySet().iterator();
        while (it.hasNext()) {
            if (((C1395a) it.next().getValue()).mo15746b().get() == null) {
                it.remove();
            }
        }
    }

    /* renamed from: a */
    public void mo15744a(int i, C1354c5 c5Var, C1625t3 t3Var) {
        m2568b();
        if (f1786b.size() <= 50) {
            f1786b.put(Integer.valueOf(i), new C1395a(t3Var, (C1354c5) null));
        }
    }

    /* renamed from: a */
    public C1395a mo15743a(int i) {
        return f1786b.remove(Integer.valueOf(i));
    }
}

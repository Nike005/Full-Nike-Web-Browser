package com.tappx.p048a;

import android.util.SparseArray;
import com.tappx.p048a.C1354c5;

/* renamed from: com.tappx.a.a5 */
public class C1317a5 {

    /* renamed from: a */
    private static final SparseArray<C1354c5.C1355a> f1607a = new SparseArray<>();

    /* renamed from: b */
    private static volatile int f1608b = 0;

    /* renamed from: a */
    public static int m2228a(C1354c5.C1355a aVar) {
        int a = m2227a();
        f1607a.append(a, aVar);
        return a;
    }

    /* renamed from: b */
    public static void m2230b(int i) {
        f1607a.remove(i);
    }

    /* renamed from: a */
    public static C1354c5.C1355a m2229a(int i) {
        return f1607a.get(i);
    }

    /* renamed from: a */
    private static synchronized int m2227a() {
        int i;
        synchronized (C1317a5.class) {
            i = f1608b;
            f1608b++;
        }
        return i;
    }
}

package com.yandex.metrica.impl;

import android.util.SparseArray;

/* renamed from: com.yandex.metrica.impl.bb */
public final class C1878bb {

    /* renamed from: a */
    static final C1879a f3080a = new C1879a("273", C1880bc.m4536a());

    /* renamed from: b */
    static final SparseArray<C1879a> f3081b;

    /* renamed from: com.yandex.metrica.impl.bb$a */
    static final class C1879a {

        /* renamed from: a */
        String f3082a;

        /* renamed from: b */
        String f3083b;

        public C1879a(String str, String str2) {
            this.f3082a = str;
            this.f3083b = str2;
        }
    }

    static {
        SparseArray<C1879a> sparseArray = new SparseArray<>();
        f3081b = sparseArray;
        sparseArray.put(1, new C1879a("100", "1.00"));
        f3081b.put(2, new C1879a("110", "1.10"));
        f3081b.put(3, new C1879a("111", "1.11"));
        f3081b.put(4, new C1879a("120", "1.20"));
        f3081b.put(5, new C1879a("121", "1.21"));
        f3081b.put(6, new C1879a("122", "1.22"));
        f3081b.put(7, new C1879a("123", "1.23"));
        f3081b.put(8, new C1879a("124", "1.24"));
        f3081b.put(9, new C1879a("126", "1.26"));
        f3081b.put(10, new C1879a("127", "1.27"));
        f3081b.put(11, new C1879a("140", "1.40"));
        f3081b.put(12, new C1879a("141", "1.41"));
        f3081b.put(13, new C1879a("142", "1.42"));
        f3081b.put(14, new C1879a("150", "1.50"));
        f3081b.put(15, new C1879a("151", "1.51"));
        f3081b.put(16, new C1879a("160", "1.60"));
        f3081b.put(17, new C1879a("161", "1.61"));
        f3081b.put(18, new C1879a("162", "1.62"));
        f3081b.put(19, new C1879a("163", "1.63"));
        f3081b.put(20, new C1879a("164", "1.64"));
        f3081b.put(21, new C1879a("165", "1.65"));
        f3081b.put(22, new C1879a("166", "1.66"));
        f3081b.put(23, new C1879a("167", "1.67"));
        f3081b.put(24, new C1879a("168", "1.68"));
        f3081b.put(25, new C1879a("169", "1.69"));
        f3081b.put(26, new C1879a("170", "1.70"));
        f3081b.put(27, new C1879a("171", "1.71"));
        f3081b.put(28, new C1879a("172", "1.72"));
        f3081b.put(29, new C1879a("180", "1.80"));
        f3081b.put(30, new C1879a("181", "1.81"));
        f3081b.put(31, new C1879a("182", "1.82"));
        f3081b.put(32, new C1879a("200", "2.00"));
        f3081b.put(33, new C1879a("210", "2.10"));
        f3081b.put(34, new C1879a("211", "2.11"));
        f3081b.put(35, new C1879a("220", "2.20"));
        f3081b.put(36, new C1879a("221", "2.21"));
        f3081b.put(37, new C1879a("222", "2.22"));
        f3081b.put(38, new C1879a("223", "2.23"));
        f3081b.put(39, new C1879a("230", "2.30"));
        f3081b.put(40, new C1879a("231", "2.31"));
        f3081b.put(41, new C1879a("232", "2.32"));
        f3081b.put(42, new C1879a("233", "2.33"));
        f3081b.put(43, new C1879a("240", "2.40"));
        f3081b.put(44, new C1879a("241", "2.41"));
        f3081b.put(45, new C1879a("242", "2.42"));
        f3081b.put(46, new C1879a("243", "2.43"));
        f3081b.put(47, new C1879a("250", "2.50"));
        f3081b.put(48, new C1879a("251", "2.51"));
        f3081b.put(49, new C1879a("252", "2.52"));
        f3081b.put(50, new C1879a("260", "2.60"));
        f3081b.put(51, new C1879a("261", "2.61"));
        f3081b.put(52, new C1879a("262", "2.62"));
        f3081b.put(53, new C1879a("263", "2.63"));
        f3081b.put(54, new C1879a("264", "2.64"));
        f3081b.put(55, new C1879a("270", "2.70"));
        f3081b.put(56, new C1879a("271", "2.71"));
        f3081b.put(57, new C1879a("272", "2.72"));
        f3081b.put(58, new C1879a("273", "2.73"));
    }

    /* renamed from: a */
    static C1879a m4535a(int i) {
        return f3081b.get(i);
    }
}

package com.tappx.p048a;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/* renamed from: com.tappx.a.q5 */
public class C1590q5 {

    /* renamed from: a */
    public final int f2228a;

    /* renamed from: b */
    public final byte[] f2229b;

    /* renamed from: c */
    public final Map<String, String> f2230c;

    /* renamed from: d */
    public final List<C1528m5> f2231d;

    /* renamed from: e */
    public final boolean f2232e;

    /* renamed from: f */
    public final long f2233f;

    @Deprecated
    public C1590q5(int i, byte[] bArr, Map<String, String> map, boolean z, long j) {
        this(i, bArr, map, m3291a(map), z, j);
    }

    /* renamed from: a */
    private static Map<String, String> m3292a(List<C1528m5> list) {
        if (list == null) {
            return null;
        }
        if (list.isEmpty()) {
            return Collections.emptyMap();
        }
        TreeMap treeMap = new TreeMap(String.CASE_INSENSITIVE_ORDER);
        for (C1528m5 next : list) {
            treeMap.put(next.mo15969a(), next.mo15970b());
        }
        return treeMap;
    }

    public C1590q5(int i, byte[] bArr, boolean z, long j, List<C1528m5> list) {
        this(i, bArr, m3292a(list), list, z, j);
    }

    @Deprecated
    public C1590q5(byte[] bArr, Map<String, String> map) {
        this(200, bArr, map, false, 0);
    }

    private C1590q5(int i, byte[] bArr, Map<String, String> map, List<C1528m5> list, boolean z, long j) {
        this.f2228a = i;
        this.f2229b = bArr;
        this.f2230c = map;
        if (list == null) {
            this.f2231d = null;
        } else {
            this.f2231d = Collections.unmodifiableList(list);
        }
        this.f2232e = z;
        this.f2233f = j;
    }

    /* renamed from: a */
    private static List<C1528m5> m3291a(Map<String, String> map) {
        if (map == null) {
            return null;
        }
        if (map.isEmpty()) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList(map.size());
        for (Map.Entry next : map.entrySet()) {
            arrayList.add(new C1528m5((String) next.getKey(), (String) next.getValue()));
        }
        return arrayList;
    }
}

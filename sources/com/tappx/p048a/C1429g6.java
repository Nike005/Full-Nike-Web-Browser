package com.tappx.p048a;

import java.io.InputStream;
import java.util.Collections;
import java.util.List;

/* renamed from: com.tappx.a.g6 */
public final class C1429g6 {

    /* renamed from: a */
    private final int f1883a;

    /* renamed from: b */
    private final List<C1528m5> f1884b;

    /* renamed from: c */
    private final int f1885c;

    /* renamed from: d */
    private final InputStream f1886d;

    public C1429g6(int i, List<C1528m5> list) {
        this(i, list, -1, (InputStream) null);
    }

    /* renamed from: a */
    public final InputStream mo15836a() {
        return this.f1886d;
    }

    /* renamed from: b */
    public final int mo15837b() {
        return this.f1885c;
    }

    /* renamed from: c */
    public final List<C1528m5> mo15838c() {
        return Collections.unmodifiableList(this.f1884b);
    }

    /* renamed from: d */
    public final int mo15839d() {
        return this.f1883a;
    }

    public C1429g6(int i, List<C1528m5> list, int i2, InputStream inputStream) {
        this.f1883a = i;
        this.f1884b = list;
        this.f1885c = i2;
        this.f1886d = inputStream;
    }
}

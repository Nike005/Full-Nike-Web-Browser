package com.tappx.p048a;

import java.util.Map;

/* renamed from: com.tappx.a.a0 */
public class C1309a0 extends Exception {

    /* renamed from: a */
    public final C1310a f1579a;

    /* renamed from: b */
    public final int f1580b;

    /* renamed from: com.tappx.a.a0$a */
    public enum C1310a {
        SERVER_ERROR,
        NETWORK_ERROR,
        PARSE_ERROR,
        NO_FILL
    }

    public C1309a0(C1310a aVar, Map<String, String> map, int i) {
        this.f1579a = aVar;
        this.f1580b = i;
    }

    /* renamed from: a */
    public C1310a mo15518a() {
        return this.f1579a;
    }

    public C1309a0(C1310a aVar) {
        this(aVar, (Map<String, String>) null, -1);
    }
}

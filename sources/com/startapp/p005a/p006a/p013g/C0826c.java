package com.startapp.p005a.p006a.p013g;

import com.startapp.p005a.p006a.p007a.C0800a;
import com.startapp.p005a.p006a.p010d.C0815e;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.startapp.a.a.g.c */
/* compiled from: StartAppSDK */
public class C0826c {

    /* renamed from: a */
    private final Map<C0823a, C0825b> f371a;

    public C0826c() {
        HashMap hashMap = new HashMap();
        this.f371a = hashMap;
        hashMap.put(C0823a.ZERO, new C0830g());
        this.f371a.put(C0823a.THREE, new C0829f());
        this.f371a.put(C0823a.FOUR, new C0828e());
        this.f371a.put(C0823a.FIVE, new C0827d());
    }

    /* renamed from: a */
    public C0800a mo13714a(C0823a aVar) {
        return this.f371a.get(aVar).mo13713b();
    }

    /* renamed from: b */
    public C0815e mo13715b(C0823a aVar) {
        return this.f371a.get(aVar).mo13712a();
    }
}

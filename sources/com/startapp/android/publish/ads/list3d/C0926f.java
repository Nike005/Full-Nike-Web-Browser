package com.startapp.android.publish.ads.list3d;

import com.startapp.common.p043a.C1270g;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* renamed from: com.startapp.android.publish.ads.list3d.f */
/* compiled from: StartAppSDK */
public class C0926f {

    /* renamed from: a */
    private static C0926f f601a = new C0926f();

    /* renamed from: b */
    private Map<String, C0925e> f602b = new ConcurrentHashMap();

    private C0926f() {
    }

    /* renamed from: a */
    public static C0926f m744a() {
        return f601a;
    }

    /* renamed from: a */
    public C0925e mo14097a(String str) {
        if (this.f602b.containsKey(str)) {
            return this.f602b.get(str);
        }
        C0925e eVar = new C0925e();
        this.f602b.put(str, eVar);
        C1270g.m2078a("ListModelManager", 3, "Created new model for uuid " + str + ", Size = " + this.f602b.size());
        return eVar;
    }

    /* renamed from: b */
    public void mo14098b(String str) {
        this.f602b.remove(str);
        C1270g.m2078a("ListModelManager", 3, "Model for " + str + " was removed, Size = " + this.f602b.size());
    }
}

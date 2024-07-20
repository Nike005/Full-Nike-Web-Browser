package com.yandex.metrica.impl;

import com.yandex.metrica.impl.p050ob.C2014bz;
import com.yandex.metrica.impl.p050ob.C2092dt;
import com.yandex.metrica.impl.utils.C2223g;
import com.yandex.metrica.impl.utils.C2227i;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;

/* renamed from: com.yandex.metrica.impl.g */
public class C1913g {

    /* renamed from: a */
    private final C2014bz f3163a;

    /* renamed from: b */
    private final Executor f3164b;
    /* access modifiers changed from: private */

    /* renamed from: c */
    public final C1905c f3165c;
    /* access modifiers changed from: private */

    /* renamed from: d */
    public volatile Map<String, Long> f3166d = null;

    public C1913g(C2014bz bzVar, C1905c cVar, Executor executor) {
        this.f3163a = bzVar;
        this.f3164b = executor;
        this.f3165c = cVar;
        m4726b();
        this.f3164b.execute(new Runnable() {
            public void run() {
                HashMap<String, String> a = C1913g.this.f3165c.mo17098a();
                HashMap hashMap = new HashMap();
                if (!C1897bk.m4653a((Map) a)) {
                    for (Map.Entry next : a.entrySet()) {
                        hashMap.put(next.getKey(), Long.valueOf(C2227i.m5956a((String) next.getValue(), 0)));
                    }
                }
                Map unused = C1913g.this.f3166d = hashMap;
            }
        });
    }

    /* renamed from: b */
    private void m4726b() {
        String l = this.f3163a.mo17347l((String) null);
        C2092dt dtVar = new C2092dt();
        HashMap<String, String> a = C2223g.m5949a(l);
        if (!C1897bk.m4653a((Map) a)) {
            for (Map.Entry next : a.entrySet()) {
                dtVar.mo17648a((String) next.getKey(), C2227i.m5955a((String) next.getValue(), Integer.MAX_VALUE));
            }
        }
        this.f3163a.mo17329b((String) null);
        this.f3163a.mo17327a((String) null);
        this.f3163a.mo17349n((String) null);
    }

    /* renamed from: a */
    public void mo17108a() {
        m4726b();
    }
}

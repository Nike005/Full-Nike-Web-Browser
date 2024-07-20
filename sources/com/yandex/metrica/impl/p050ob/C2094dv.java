package com.yandex.metrica.impl.p050ob;

import android.os.Bundle;
import android.text.TextUtils;
import com.yandex.metrica.IIdentifierCallback;
import com.yandex.metrica.impl.C1868ay;
import com.yandex.metrica.impl.C1897bk;
import com.yandex.metrica.impl.p050ob.C2097dx;
import com.yandex.metrica.impl.utils.C2227i;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

/* renamed from: com.yandex.metrica.impl.ob.dv */
public class C2094dv implements C2096dw {

    /* renamed from: a */
    static final Map<C2093du, IIdentifierCallback.Reason> f3585a = Collections.unmodifiableMap(new HashMap<C2093du, IIdentifierCallback.Reason>() {
        {
            put(C2093du.UNKNOWN, IIdentifierCallback.Reason.UNKNOWN);
            put(C2093du.NETWORK, IIdentifierCallback.Reason.NETWORK);
            put(C2093du.PARSE, IIdentifierCallback.Reason.INVALID_RESPONSE);
        }
    });

    /* renamed from: b */
    private final C1868ay f3586b;

    /* renamed from: c */
    private final C2097dx f3587c;

    /* renamed from: d */
    private final C2014bz f3588d;

    /* renamed from: e */
    private final Object f3589e = new Object();

    /* renamed from: f */
    private final Map<IIdentifierCallback, Object> f3590f = new WeakHashMap();

    /* renamed from: g */
    private final Map<IIdentifierCallback, Object> f3591g = new WeakHashMap();

    public C2094dv(C1868ay ayVar, String str, C2014bz bzVar) {
        this.f3586b = ayVar;
        this.f3588d = bzVar;
        this.f3587c = new C2097dx(bzVar, str);
        mo17661e();
    }

    /* renamed from: a */
    public String mo17651a() {
        return this.f3587c.mo17671c();
    }

    /* renamed from: b */
    public String mo17657b() {
        return this.f3588d.mo17326a();
    }

    /* renamed from: c */
    public String mo17659c() {
        return this.f3587c.mo17673d();
    }

    /* renamed from: a */
    public void mo17653a(IIdentifierCallback iIdentifierCallback) {
        synchronized (this.f3589e) {
            this.f3591g.put(iIdentifierCallback, (Object) null);
            if (!this.f3587c.mo17668a(C2097dx.C2098a.ALL)) {
                this.f3586b.mo16920c();
            }
        }
        mo17661e();
    }

    /* renamed from: a */
    public void mo17652a(Bundle bundle) {
        synchronized (this.f3589e) {
            this.f3587c.mo17663a(bundle);
            this.f3587c.mo17662a(System.currentTimeMillis() / 1000);
        }
        mo17661e();
    }

    /* renamed from: d */
    public void mo17660d() {
        if (!this.f3587c.mo17668a(C2097dx.C2098a.ALL) || this.f3587c.mo17667a()) {
            this.f3586b.mo16920c();
        }
    }

    /* renamed from: a */
    public void mo17655a(List<String> list) {
        List<String> b = this.f3587c.mo17669b();
        if (C1897bk.m4652a((Collection) list)) {
            if (!C1897bk.m4652a((Collection) b)) {
                this.f3587c.mo17665a((List<String>) null);
                this.f3586b.mo16914a((List<String>) null);
            }
        } else if (!C1897bk.m4651a((Object) list, (Object) b)) {
            this.f3587c.mo17665a(list);
            this.f3586b.mo16914a(list);
        } else {
            this.f3586b.mo16914a(b);
        }
    }

    /* renamed from: a */
    public void mo17654a(String str) {
        this.f3586b.mo16922c(str);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: e */
    public void mo17661e() {
        WeakHashMap weakHashMap = new WeakHashMap();
        HashMap hashMap = new HashMap();
        WeakHashMap weakHashMap2 = new WeakHashMap();
        HashMap hashMap2 = new HashMap();
        synchronized (this.f3589e) {
            if (this.f3587c.mo17668a(C2097dx.C2098a.IDENTIFIERS)) {
                weakHashMap.putAll(this.f3590f);
                this.f3590f.clear();
                this.f3587c.mo17670b((Map<String, String>) hashMap);
            }
            if (this.f3587c.mo17668a(C2097dx.C2098a.ALL)) {
                weakHashMap2.putAll(this.f3591g);
                this.f3591g.clear();
                this.f3587c.mo17666a((Map<String, String>) hashMap2);
            }
        }
        for (IIdentifierCallback onReceive : weakHashMap.keySet()) {
            onReceive.onReceive(new HashMap(hashMap));
        }
        for (IIdentifierCallback onReceive2 : weakHashMap2.keySet()) {
            onReceive2.onReceive(new HashMap(hashMap2));
        }
        weakHashMap.clear();
        hashMap.clear();
        weakHashMap2.clear();
        hashMap2.clear();
    }

    /* renamed from: b */
    public void mo17658b(Bundle bundle) {
        IIdentifierCallback.Reason reason = f3585a.get(C2093du.m5462b(bundle));
        WeakHashMap weakHashMap = new WeakHashMap();
        WeakHashMap weakHashMap2 = new WeakHashMap();
        synchronized (this.f3589e) {
            weakHashMap.putAll(this.f3590f);
            weakHashMap2.putAll(this.f3591g);
            this.f3590f.clear();
            this.f3591g.clear();
        }
        for (IIdentifierCallback onRequestError : weakHashMap.keySet()) {
            onRequestError.onRequestError(reason);
        }
        for (IIdentifierCallback onRequestError2 : weakHashMap2.keySet()) {
            onRequestError2.onRequestError(reason);
        }
        weakHashMap.clear();
        weakHashMap2.clear();
    }

    /* renamed from: a */
    public void mo17656a(Map<String, String> map) {
        HashMap hashMap = new HashMap();
        if (map != null) {
            for (Map.Entry next : map.entrySet()) {
                String str = (String) next.getKey();
                boolean z = true;
                if (!TextUtils.isEmpty(str) && !str.contains(":") && !str.contains(",") && !str.contains("&")) {
                    String str2 = (String) next.getValue();
                    if (TextUtils.isEmpty(str2) || C2227i.m5956a(str2, -1) == -1) {
                        z = false;
                    }
                    if (z) {
                        hashMap.put(next.getKey(), next.getValue());
                    }
                }
            }
        }
        this.f3586b.mo16915a((Map<String, String>) hashMap);
    }
}

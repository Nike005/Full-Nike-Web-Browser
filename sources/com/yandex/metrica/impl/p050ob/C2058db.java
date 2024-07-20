package com.yandex.metrica.impl.p050ob;

import android.text.TextUtils;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/* renamed from: com.yandex.metrica.impl.ob.db */
public class C2058db {

    /* renamed from: a */
    public static final Map<String, String> f3447a = Collections.unmodifiableMap(new HashMap<String, String>() {
        {
            put("20799a27-fa80-4b36-b2db-0f8141f24180", "13");
            put("01528cc0-dd34-494d-9218-24af1317e1ee", "17233");
            put("4e610cd2-753f-4bfc-9b05-772ce8905c5e", "21952");
            put("67bb016b-be40-4c08-a190-96a3f3b503d3", "22675");
            put("e4250327-8d3c-4d35-b9e8-3c1720a64b91", "22678");
            put("6c5f504e-8928-47b5-bfb5-73af8d8bf4b4", "30404");
            put("7d962ba4-a392-449a-a02d-6c5be5613928", "30407");
        }
    });

    /* renamed from: b */
    private C2060dc f3448b;

    public C2058db(C2060dc dcVar) {
        this.f3448b = dcVar;
    }

    /* renamed from: a */
    public void mo17523a() {
        if (mo17530f()) {
            mo17531g();
            mo17532h();
        }
    }

    /* renamed from: b */
    public void mo17525b() {
        String d = mo17528d();
        if (!TextUtils.isEmpty(d) && "DONE".equals(this.f3448b.mo17544g().get(C2060dc.m5310f(d)))) {
            mo17524a(d);
        }
    }

    /* renamed from: c */
    public void mo17527c() {
        mo17524a(mo17529e());
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo17524a(String str) {
        if (str != null) {
            mo17526b(str);
            mo17531g();
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: d */
    public String mo17528d() {
        return f3447a.get(this.f3448b.mo17550j());
    }

    /* access modifiers changed from: package-private */
    /* renamed from: e */
    public String mo17529e() {
        Map<String, ?> g = this.f3448b.mo17544g();
        for (String f : f3447a.values()) {
            g.remove(C2060dc.m5310f(f));
        }
        LinkedList linkedList = new LinkedList();
        for (String g2 : g.keySet()) {
            try {
                linkedList.add(Integer.valueOf(Integer.parseInt(C2060dc.m5311g(g2))));
            } catch (Throwable unused) {
            }
        }
        if (linkedList.size() == 1) {
            return ((Integer) linkedList.getFirst()).toString();
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: f */
    public boolean mo17530f() {
        return this.f3448b.mo17533a((String) null) != null;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: g */
    public void mo17531g() {
        this.f3448b.mo17534a();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public void mo17526b(String str) {
        this.f3448b.mo17540d(str);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: h */
    public void mo17532h() {
        this.f3448b.mo17536b();
    }
}

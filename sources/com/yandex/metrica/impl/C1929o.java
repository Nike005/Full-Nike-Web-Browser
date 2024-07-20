package com.yandex.metrica.impl;

import com.yandex.metrica.impl.utils.C2221f;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

/* renamed from: com.yandex.metrica.impl.o */
public class C1929o {

    /* renamed from: a */
    private Map<String, String> f3209a = new HashMap();

    /* renamed from: b */
    private C2221f f3210b = new C2221f();

    /* renamed from: c */
    private C2221f.C2222a f3211c;

    C1929o(C2221f.C2222a aVar) {
        this.f3211c = aVar;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo17172a(String str, String str2) {
        if (str2 == null) {
            this.f3209a.remove(str);
            return;
        }
        this.f3210b.mo17914a(this.f3209a, str, str2, this.f3211c, "Crash Environment");
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public String mo17171a() {
        if (this.f3209a.isEmpty()) {
            return null;
        }
        return new JSONObject(this.f3209a).toString();
    }
}

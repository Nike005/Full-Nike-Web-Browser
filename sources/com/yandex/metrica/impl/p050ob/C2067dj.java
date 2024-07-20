package com.yandex.metrica.impl.p050ob;

import android.content.Context;

/* renamed from: com.yandex.metrica.impl.ob.dj */
public class C2067dj extends C2061dd {

    /* renamed from: c */
    private C2068dk f3539c;

    /* access modifiers changed from: protected */
    /* renamed from: f */
    public String mo17543f() {
        return "_serviceproviderspreferences";
    }

    public C2067dj(Context context) {
        this(context, (String) null);
    }

    public C2067dj(Context context, String str) {
        super(context, str);
    }

    /* access modifiers changed from: protected */
    /* renamed from: h */
    public void mo17548h() {
        super.mo17548h();
        this.f3539c = new C2068dk("LOCATION_TRACKING_ENABLED");
    }

    /* renamed from: a */
    public boolean mo17602a() {
        return this.f3455b.getBoolean(this.f3539c.mo17606b(), false);
    }

    /* renamed from: b */
    public void mo17603b() {
        mo17547h(this.f3539c.mo17606b()).mo17551k();
    }
}

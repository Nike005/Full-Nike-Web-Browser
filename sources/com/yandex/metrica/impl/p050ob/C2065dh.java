package com.yandex.metrica.impl.p050ob;

import android.content.Context;

/* renamed from: com.yandex.metrica.impl.ob.dh */
public class C2065dh extends C2061dd {

    /* renamed from: c */
    private static final C2068dk f3515c = new C2068dk("PREF_KEY_OFFSET");

    /* renamed from: d */
    private C2068dk f3516d;

    /* access modifiers changed from: protected */
    /* renamed from: f */
    public String mo17543f() {
        return "_servertimeoffset";
    }

    public C2065dh(Context context, String str) {
        super(context, str);
    }

    /* access modifiers changed from: protected */
    /* renamed from: h */
    public void mo17548h() {
        super.mo17548h();
        this.f3516d = new C2068dk(f3515c.mo17604a(), (String) null);
    }

    /* renamed from: a */
    public long mo17588a(int i) {
        return this.f3455b.getLong(this.f3516d.mo17606b(), (long) i);
    }

    /* renamed from: a */
    public void mo17589a() {
        mo17547h(this.f3516d.mo17606b()).mo17551k();
    }
}

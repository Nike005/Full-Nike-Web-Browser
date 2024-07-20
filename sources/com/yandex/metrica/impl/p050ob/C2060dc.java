package com.yandex.metrica.impl.p050ob;

import android.content.Context;
import java.util.Map;

/* renamed from: com.yandex.metrica.impl.ob.dc */
public class C2060dc extends C2061dd {

    /* renamed from: c */
    private final C2068dk f3449c = new C2068dk("init_event_pref_key", mo17550j());

    /* renamed from: d */
    private final C2068dk f3450d = new C2068dk("init_event_pref_key");

    /* renamed from: e */
    private final C2068dk f3451e = new C2068dk("first_event_pref_key", mo17550j());

    /* renamed from: f */
    private final C2068dk f3452f = new C2068dk("fitst_event_description_key", mo17550j());

    /* access modifiers changed from: protected */
    /* renamed from: f */
    public String mo17543f() {
        return "_initpreferences";
    }

    public C2060dc(Context context, String str) {
        super(context, str);
    }

    /* renamed from: a */
    public void mo17534a() {
        mo17546a(this.f3449c.mo17606b(), "DONE").mo17551k();
    }

    /* renamed from: a */
    public String mo17533a(String str) {
        return this.f3455b.getString(this.f3450d.mo17606b(), str);
    }

    /* renamed from: b */
    public String mo17535b(String str) {
        return this.f3455b.getString(this.f3449c.mo17606b(), str);
    }

    /* renamed from: c */
    public String mo17537c(String str) {
        return this.f3455b.getString(this.f3451e.mo17606b(), str);
    }

    /* renamed from: b */
    public void mo17536b() {
        m5309a(this.f3450d);
    }

    /* renamed from: d */
    public void mo17540d(String str) {
        m5309a(new C2068dk("init_event_pref_key", str));
    }

    /* renamed from: c */
    public void mo17538c() {
        m5309a(this.f3449c);
    }

    /* renamed from: d */
    public void mo17539d() {
        m5309a(this.f3451e);
    }

    /* renamed from: e */
    public String mo17541e(String str) {
        return this.f3455b.getString(this.f3452f.mo17606b(), str);
    }

    /* renamed from: e */
    public void mo17542e() {
        m5309a(this.f3452f);
    }

    /* renamed from: a */
    private void m5309a(C2068dk dkVar) {
        this.f3455b.edit().remove(dkVar.mo17606b()).apply();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: g */
    public Map<String, ?> mo17544g() {
        return this.f3455b.getAll();
    }

    /* renamed from: f */
    static String m5310f(String str) {
        return new C2068dk("init_event_pref_key", str).mo17606b();
    }

    /* renamed from: g */
    static String m5311g(String str) {
        return str.replace("init_event_pref_key", "");
    }
}

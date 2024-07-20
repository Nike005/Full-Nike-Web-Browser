package com.yandex.metrica.impl.p050ob;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.yandex.metrica.impl.ob.dd */
public abstract class C2061dd {

    /* renamed from: c */
    private static final C2068dk f3453c = new C2068dk("UNDEFINED_");

    /* renamed from: a */
    protected final String f3454a;

    /* renamed from: b */
    protected final SharedPreferences f3455b;

    /* renamed from: d */
    private final Map<String, Object> f3456d = new HashMap();

    /* renamed from: e */
    private boolean f3457e = false;

    /* access modifiers changed from: protected */
    /* renamed from: f */
    public abstract String mo17543f();

    public C2061dd(Context context, String str) {
        this.f3454a = str;
        this.f3455b = mo17545a(context);
        mo17548h();
    }

    /* access modifiers changed from: protected */
    /* renamed from: h */
    public void mo17548h() {
        new C2068dk(f3453c.mo17604a(), this.f3454a);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public SharedPreferences mo17545a(Context context) {
        return C2069dl.m5401a(context, mo17543f());
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public <T extends C2061dd> T mo17546a(String str, Object obj) {
        synchronized (this) {
            if (obj != null) {
                this.f3456d.put(str, obj);
            }
        }
        return this;
    }

    /* access modifiers changed from: protected */
    /* renamed from: h */
    public <T extends C2061dd> T mo17547h(String str) {
        synchronized (this) {
            this.f3456d.put(str, this);
        }
        return this;
    }

    /* access modifiers changed from: protected */
    /* renamed from: i */
    public <T extends C2061dd> T mo17549i() {
        synchronized (this) {
            this.f3457e = true;
            this.f3456d.clear();
        }
        return this;
    }

    /* access modifiers changed from: protected */
    /* renamed from: j */
    public String mo17550j() {
        return this.f3454a;
    }

    /* renamed from: k */
    public void mo17551k() {
        synchronized (this) {
            SharedPreferences.Editor edit = this.f3455b.edit();
            if (this.f3457e) {
                edit.clear();
                edit.apply();
            } else {
                for (Map.Entry next : this.f3456d.entrySet()) {
                    String str = (String) next.getKey();
                    Object value = next.getValue();
                    if (value == this) {
                        edit.remove(str);
                    } else if (value instanceof String) {
                        edit.putString(str, (String) value);
                    } else if (value instanceof Long) {
                        edit.putLong(str, ((Long) value).longValue());
                    } else if (value instanceof Integer) {
                        edit.putInt(str, ((Integer) value).intValue());
                    } else if (value instanceof Boolean) {
                        edit.putBoolean(str, ((Boolean) value).booleanValue());
                    } else if (value != null) {
                        throw new UnsupportedOperationException();
                    }
                }
                edit.apply();
            }
            this.f3456d.clear();
            this.f3457e = false;
        }
    }
}

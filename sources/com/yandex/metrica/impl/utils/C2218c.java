package com.yandex.metrica.impl.utils;

import android.util.Log;

/* renamed from: com.yandex.metrica.impl.utils.c */
public abstract class C2218c {

    /* renamed from: a */
    private volatile boolean f3893a = false;

    /* renamed from: d */
    private static String m5918d(String str) {
        return str == null ? "" : str;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: c */
    public abstract String mo17908c(String str, Object[] objArr);

    /* access modifiers changed from: package-private */
    /* renamed from: d */
    public abstract String mo17910d();

    /* access modifiers changed from: package-private */
    /* renamed from: e */
    public abstract String mo17911e();

    /* renamed from: a */
    public void mo17899a() {
        this.f3893a = true;
    }

    /* renamed from: b */
    public boolean mo17906b() {
        return this.f3893a;
    }

    public C2218c(boolean z) {
        this.f3893a = z;
    }

    /* renamed from: a */
    public void mo17902a(String str) {
        mo17900a(4, str);
    }

    /* renamed from: b */
    public void mo17904b(String str) {
        mo17900a(5, str);
    }

    /* renamed from: c */
    public void mo17909c(String str) {
        mo17900a(6, str);
    }

    /* renamed from: a */
    public void mo17903a(String str, Object... objArr) {
        mo17901a(4, str, objArr);
    }

    /* renamed from: b */
    public void mo17905b(String str, Object... objArr) {
        mo17901a(5, str, objArr);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo17900a(int i, String str) {
        if (this.f3893a) {
            String d = mo17910d();
            Log.println(i, d, mo17911e() + m5918d(str));
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo17901a(int i, String str, Object... objArr) {
        if (this.f3893a) {
            Log.println(i, mo17910d(), m5919d(str, objArr));
        }
    }

    /* renamed from: d */
    private String m5919d(String str, Object[] objArr) {
        try {
            return mo17911e() + mo17908c(m5918d(str), objArr);
        } catch (Throwable unused) {
            return mo17907c();
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: c */
    public String mo17907c() {
        return mo17911e();
    }
}

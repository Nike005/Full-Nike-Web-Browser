package com.startapp.common;

/* renamed from: com.startapp.common.e */
/* compiled from: StartAppSDK */
public class C1301e extends Exception {

    /* renamed from: b */
    private boolean f1563b;

    /* renamed from: c */
    private int f1564c;

    public C1301e(String str, Throwable th, boolean z, int i) {
        super(str, th);
        this.f1563b = false;
        this.f1564c = 0;
        this.f1563b = z;
        this.f1564c = i;
    }

    public C1301e(String str, Throwable th, int i) {
        this(str, th, false, i);
    }

    /* renamed from: a */
    public int mo15512a() {
        return this.f1564c;
    }

    public C1301e() {
        this.f1563b = false;
        this.f1564c = 0;
    }

    public C1301e(String str, Throwable th) {
        this(str, th, false);
    }

    public C1301e(String str, Throwable th, boolean z) {
        this(str, th, z, 0);
    }

    /* renamed from: b */
    public boolean mo15513b() {
        return this.f1563b;
    }
}

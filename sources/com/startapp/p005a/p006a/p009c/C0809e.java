package com.startapp.p005a.p006a.p009c;

import java.io.Serializable;
import java.io.Writer;

/* renamed from: com.startapp.a.a.c.e */
/* compiled from: StartAppSDK */
public class C0809e extends Writer implements Serializable {

    /* renamed from: b */
    private final StringBuilder f343b;

    public void close() {
    }

    public void flush() {
    }

    public C0809e() {
        this.f343b = new StringBuilder();
    }

    public C0809e(int i) {
        this.f343b = new StringBuilder(i);
    }

    public Writer append(char c) {
        this.f343b.append(c);
        return this;
    }

    public Writer append(CharSequence charSequence) {
        this.f343b.append(charSequence);
        return this;
    }

    public Writer append(CharSequence charSequence, int i, int i2) {
        this.f343b.append(charSequence, i, i2);
        return this;
    }

    public void write(String str) {
        if (str != null) {
            this.f343b.append(str);
        }
    }

    public void write(char[] cArr, int i, int i2) {
        if (cArr != null) {
            this.f343b.append(cArr, i, i2);
        }
    }

    public String toString() {
        return this.f343b.toString();
    }
}

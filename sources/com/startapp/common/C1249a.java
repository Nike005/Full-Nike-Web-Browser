package com.startapp.common;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import com.startapp.common.C1303g;
import com.startapp.common.p043a.C1265d;

/* renamed from: com.startapp.common.a */
/* compiled from: StartAppSDK */
public class C1249a {

    /* renamed from: a */
    String f1470a;

    /* renamed from: b */
    C1252a f1471b;

    /* renamed from: c */
    int f1472c;

    /* renamed from: com.startapp.common.a$a */
    /* compiled from: StartAppSDK */
    public interface C1252a {
        /* renamed from: a */
        void mo13933a(Bitmap bitmap, int i);
    }

    public C1249a(String str, C1252a aVar, int i) {
        this.f1470a = str;
        this.f1471b = aVar;
        this.f1472c = i;
    }

    /* renamed from: a */
    public void mo15438a() {
        C1303g.m2206a(C1303g.C1307a.HIGH, (Runnable) new Runnable() {
            public void run() {
                final Bitmap b = C1265d.m2056b(C1249a.this.f1470a);
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    public void run() {
                        if (C1249a.this.f1471b != null) {
                            C1249a.this.f1471b.mo13933a(b, C1249a.this.f1472c);
                        }
                    }
                });
            }
        });
    }
}

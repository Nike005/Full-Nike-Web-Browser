package com.startapp.android.publish.adsCommon.p039h;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.startapp.android.publish.adsCommon.p033f.C1127b;
import com.startapp.common.C1298d;
import com.startapp.common.C1303g;

/* renamed from: com.startapp.android.publish.adsCommon.h.a */
/* compiled from: StartAppSDK */
public abstract class C1149a implements C1298d {

    /* renamed from: a */
    protected final Context f1210a;

    /* renamed from: b */
    protected final Runnable f1211b;

    /* renamed from: c */
    protected C1127b f1212c;

    /* renamed from: d */
    private Handler f1213d = new Handler(Looper.getMainLooper());

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public abstract void mo14941b();

    public C1149a(Context context, Runnable runnable, C1127b bVar) {
        this.f1210a = context;
        this.f1211b = runnable;
        this.f1212c = bVar;
    }

    /* renamed from: a */
    public void mo14938a() {
        C1303g.m2206a(C1303g.C1307a.DEFAULT, (Runnable) new Runnable() {
            public void run() {
                C1149a.this.mo14941b();
            }
        });
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo14940a(Runnable runnable, long j) {
        this.f1213d.postDelayed(runnable, j);
    }

    /* renamed from: a */
    public void mo14939a(Object obj) {
        Handler handler = this.f1213d;
        if (handler != null) {
            handler.removeCallbacksAndMessages((Object) null);
        }
        this.f1211b.run();
    }
}

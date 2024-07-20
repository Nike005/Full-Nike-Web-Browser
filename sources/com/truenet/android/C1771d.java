package com.truenet.android;

import java.lang.Thread;
import p055a.p056a.p058b.p059a.C2919b;
import p055a.p056a.p058b.p060b.C2928h;

/* renamed from: com.truenet.android.d */
/* compiled from: StartAppSDK */
final class C1771d implements Thread.UncaughtExceptionHandler {

    /* renamed from: a */
    private final /* synthetic */ C2919b f2715a;

    C1771d(C2919b bVar) {
        this.f2715a = bVar;
    }

    public final /* synthetic */ void uncaughtException(Thread thread, Throwable th) {
        C2928h.m6154a(this.f2715a.mo16477a(thread, th), "invoke(...)");
    }
}

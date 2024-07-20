package com.truenet.android;

import android.content.Context;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import p055a.p056a.C2971j;
import p055a.p056a.p058b.p059a.C2918a;
import p055a.p056a.p058b.p059a.C2919b;
import p055a.p056a.p058b.p060b.C2928h;
import p055a.p056a.p058b.p060b.C2929i;

/* renamed from: com.truenet.android.c */
/* compiled from: StartAppSDK */
public final class C1767c {

    /* renamed from: a */
    public static final C1768a f2702a = new C1768a((C2925e) null);

    /* renamed from: b */
    private final ExecutorService f2703b;

    /* renamed from: c */
    private C2918a<C2971j> f2704c = C1770c.f2714a;

    /* renamed from: d */
    private int f2705d;

    /* renamed from: e */
    private final Context f2706e;

    /* renamed from: f */
    private final List<String> f2707f;

    /* renamed from: g */
    private final long f2708g;

    /* renamed from: h */
    private final int f2709h;

    /* renamed from: com.truenet.android.c$c */
    /* compiled from: StartAppSDK */
    static final class C1770c extends C2929i implements C2918a<C2971j> {

        /* renamed from: a */
        public static final C1770c f2714a = new C1770c();

        C1770c() {
            super(0);
        }

        /* renamed from: b */
        public final void mo16550b() {
        }

        /* renamed from: a */
        public /* synthetic */ Object mo16475a() {
            mo16550b();
            return C2971j.f4034a;
        }
    }

    public C1767c(Context context, List<String> list, ThreadFactory threadFactory, long j, int i, int i2) {
        C2928h.m6157b(context, "context");
        C2928h.m6157b(list, "links");
        C2928h.m6157b(threadFactory, "threadFactory");
        this.f2706e = context;
        this.f2707f = list;
        this.f2708g = j;
        this.f2709h = i;
        this.f2703b = Executors.newFixedThreadPool(i2, threadFactory);
    }

    /* renamed from: com.truenet.android.c$a */
    /* compiled from: StartAppSDK */
    public static final class C1768a {
        private C1768a() {
        }

        public /* synthetic */ C1768a(C2925e eVar) {
            this();
        }
    }

    /* renamed from: a */
    public final void mo16547a(C2918a<C2971j> aVar) {
        C2928h.m6157b(aVar, "<set-?>");
        this.f2704c = aVar;
    }

    /* renamed from: a */
    private final int m3903a() {
        int i;
        synchronized (this) {
            i = this.f2705d + 1;
            this.f2705d = i;
        }
        return i;
    }

    /* access modifiers changed from: private */
    /* renamed from: b */
    public final void m3905b() {
        synchronized (this) {
            int i = this.f2705d - 1;
            this.f2705d = i;
            if (i <= 0) {
                this.f2704c.mo16475a();
            }
            C2971j jVar = C2971j.f4034a;
        }
    }

    /* renamed from: a */
    public final void mo16548a(C2919b<? super C1758b, ? super Integer, C2971j> bVar) {
        C2928h.m6157b(bVar, "block");
        int i = 0;
        for (String bVar2 : this.f2707f) {
            m3903a();
            this.f2703b.execute(new C1769b(new C1758b(this.f2706e, bVar2, this.f2709h, this.f2708g), i, this, bVar));
            i++;
        }
    }

    /* renamed from: com.truenet.android.c$b */
    /* compiled from: StartAppSDK */
    static final class C1769b implements Runnable {

        /* renamed from: a */
        final /* synthetic */ C1758b f2710a;

        /* renamed from: b */
        final /* synthetic */ int f2711b;

        /* renamed from: c */
        final /* synthetic */ C1767c f2712c;

        /* renamed from: d */
        final /* synthetic */ C2919b f2713d;

        C1769b(C1758b bVar, int i, C1767c cVar, C2919b bVar2) {
            this.f2710a = bVar;
            this.f2711b = i;
            this.f2712c = cVar;
            this.f2713d = bVar2;
        }

        public final void run() {
            this.f2710a.mo16528g();
            this.f2713d.mo16477a(this.f2710a, Integer.valueOf(this.f2711b));
            this.f2712c.m3905b();
        }
    }
}

package com.yandex.metrica.impl;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import com.yandex.metrica.C1780b;
import com.yandex.metrica.C1797e;
import com.yandex.metrica.DeferredDeeplinkParametersListener;
import com.yandex.metrica.IIdentifierCallback;
import com.yandex.metrica.YandexMetrica;
import com.yandex.metrica.impl.C1866ax;
import com.yandex.metrica.impl.C1917i;
import com.yandex.metrica.impl.C1921j;
import com.yandex.metrica.impl.GoogleAdvertisingIdGetter;
import com.yandex.metrica.impl.p050ob.C2003bp;
import com.yandex.metrica.impl.p050ob.C2014bz;
import com.yandex.metrica.impl.p050ob.C2094dv;
import com.yandex.metrica.impl.p050ob.C2096dw;
import com.yandex.metrica.impl.utils.C2225h;
import com.yandex.metrica.impl.utils.C2228j;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* renamed from: com.yandex.metrica.impl.bo */
public final class C1902bo implements C1921j.C1922a {

    /* renamed from: a */
    private static C1902bo f3135a;

    /* renamed from: b */
    private static C1928n f3136b = new C1928n();

    /* renamed from: c */
    private final Context f3137c;

    /* renamed from: d */
    private final C1866ax f3138d;
    /* access modifiers changed from: private */

    /* renamed from: e */
    public C2243z f3139e;

    /* renamed from: f */
    private C1829ah f3140f;

    /* renamed from: g */
    private final ExecutorService f3141g = Executors.newSingleThreadExecutor();

    /* renamed from: h */
    private final C2094dv f3142h;

    /* renamed from: i */
    private final C1853ar f3143i;

    /* renamed from: j */
    private C1913g f3144j;

    /* renamed from: k */
    private C1917i f3145k;

    private C1902bo(Context context, String str) {
        Log.i(C2228j.m5960f().mo17910d(), "Initializing of Metrica" + ", Release type" + ", Version 2.73" + ", API Level 58" + ", Dated 15.06.2017.");
        C2228j.m5959a(context);
        this.f3137c = context.getApplicationContext();
        GoogleAdvertisingIdGetter.C1803b.f2873a.mo16734a(this.f3137c);
        Handler handler = new Handler(Looper.getMainLooper());
        C1868ay ayVar = new C1868ay(this.f3141g, this.f3137c, handler);
        C2014bz bzVar = new C2014bz(C2003bp.m5024a(this.f3137c).mo17291e());
        new C1910f(bzVar).mo16801a(this.f3137c);
        C2094dv dvVar = new C2094dv(ayVar, str, bzVar);
        this.f3142h = dvVar;
        ayVar.mo16908a((C2096dw) dvVar);
        this.f3143i = new C1853ar(ayVar, bzVar);
        C1921j jVar = new C1921j(handler);
        jVar.mo17143a(this);
        ayVar.mo16907a(jVar);
        this.f3138d = new C1866ax.C1867a().mo16896a(this.f3137c).mo16900a((C2096dw) this.f3142h).mo16898a(ayVar).mo16897a(handler).mo16899a(jVar).mo16901a();
        if (C1880bc.m4538b()) {
            this.f3144j = new C1913g(bzVar, new C1905c(this.f3137c), this.f3141g);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo17089a() {
        C1829ah ahVar = new C1829ah(Thread.getDefaultUncaughtExceptionHandler());
        ahVar.mo16796a(new C1863av(this.f3138d.mo16894a("20799a27-fa80-4b36-b2db-0f8141f24180"), new C1917i.C1918a() {
            /* renamed from: a */
            public boolean mo17095a(Throwable th) {
                String a = C1897bk.m4641a(th);
                if (TextUtils.isEmpty(a)) {
                    return false;
                }
                StringBuilder sb = new StringBuilder("at ");
                sb.append("com.yandex.metrica");
                sb.append(".");
                return a.contains(sb.toString());
            }
        }));
        this.f3140f = ahVar;
        Thread.setDefaultUncaughtExceptionHandler(ahVar);
    }

    /* renamed from: a */
    public static synchronized void m4673a(Context context, C1797e eVar) {
        synchronized (C1902bo.class) {
            boolean i = f3136b.mo17170i();
            C1797e a = f3136b.mo17161a(eVar);
            m4679b(context, a);
            if (f3135a.f3139e == null) {
                if (Boolean.TRUE.equals(a.isLogEnabled())) {
                    C2228j.m5960f().mo17899a();
                }
                C1902bo boVar = f3135a;
                C2243z a2 = boVar.f3138d.mo16895a(a, i);
                boVar.f3139e = a2;
                m4676a(a2.mo16952d().mo16887b().mo16598k());
            } else {
                f3135a.f3139e.mo17949a(a, i);
            }
            ((C1780b) YandexMetrica.getReporter(context, "20799a27-fa80-4b36-b2db-0f8141f24180")).mo16674a(1);
        }
    }

    /* renamed from: a */
    public static synchronized void m4672a(Context context) {
        synchronized (C1902bo.class) {
            m4679b(context, (C1797e) null);
        }
    }

    /* renamed from: b */
    public static synchronized void m4679b(Context context, C1797e eVar) {
        synchronized (C1902bo.class) {
            C1897bk.m4646a((Object) context, "App Context");
            if (f3135a == null) {
                C1902bo boVar = new C1902bo(context.getApplicationContext(), eVar != null ? eVar.mo16699a() : null);
                f3135a = boVar;
                C2235v.m5976a(boVar.f3137c);
                if (eVar != null) {
                    boVar.f3142h.mo17655a(eVar.mo16701c());
                    boVar.f3142h.mo17656a(eVar.mo16704f());
                    boVar.f3142h.mo17654a(eVar.mo16705g());
                }
                boVar.f3142h.mo17660d();
                boVar.f3141g.execute(new C2225h.C2226a(boVar.f3137c));
                f3135a.mo17089a();
            }
        }
    }

    /* renamed from: b */
    public static synchronized C1902bo m4677b() {
        C1902bo boVar;
        synchronized (C1902bo.class) {
            if (f3135a != null) {
                boVar = f3135a;
            } else {
                throw C1898bl.f3127a;
            }
        }
        return boVar;
    }

    /* renamed from: b */
    public static C1902bo m4678b(Context context) {
        m4672a(context);
        return m4677b();
    }

    /* renamed from: c */
    public static synchronized C2243z m4681c() {
        C2243z zVar;
        synchronized (C1902bo.class) {
            C1902bo b = m4677b();
            if (b.f3139e != null) {
                zVar = b.f3139e;
            } else {
                throw C1898bl.f3127a;
            }
        }
        return zVar;
    }

    /* renamed from: d */
    static synchronized boolean m4685d() {
        boolean z;
        synchronized (C1902bo.class) {
            z = (f3135a == null || f3135a.f3139e == null) ? false : true;
        }
        return z;
    }

    /* renamed from: a */
    public C1780b mo17088a(String str) {
        return this.f3138d.mo16894a(str);
    }

    /* renamed from: b */
    public void mo17093b(String str) {
        this.f3143i.mo16862a(str);
    }

    /* renamed from: g */
    private static C1811ac m4687g() {
        return m4685d() ? m4677b().f3139e : f3136b;
    }

    /* renamed from: a */
    public static void m4671a(int i) {
        m4687g().setSessionTimeout(i);
    }

    /* renamed from: a */
    public static void m4676a(boolean z) {
        if (m4685d()) {
            C1902bo b = m4677b();
            if (z) {
                if (b.f3145k == null) {
                    b.f3145k = new C1863av(b.f3139e, new C1917i.C1918a() {
                        /* renamed from: a */
                        public boolean mo17095a(Throwable th) {
                            return C1902bo.this.f3139e.mo17955f();
                        }
                    });
                }
                b.f3140f.mo16796a(b.f3145k);
            } else {
                b.f3140f.mo16797b(b.f3145k);
            }
            b.f3139e.mo17952c(z);
            return;
        }
        f3136b.mo17165c(z);
    }

    /* renamed from: b */
    public static void m4680b(boolean z) {
        m4687g().mo16769d(z);
    }

    /* renamed from: a */
    public static void m4674a(Location location) {
        m4687g().mo16764a(location);
    }

    /* renamed from: c */
    public static void m4683c(boolean z) {
        m4687g().mo16768b(z);
    }

    /* renamed from: c */
    public static void m4682c(String str) {
        m4687g().mo16765a(str);
    }

    /* renamed from: d */
    public static void m4684d(boolean z) {
        m4687g().mo16767a(z);
    }

    /* renamed from: e */
    public static boolean m4686e() {
        return m4687g().mo16770h();
    }

    /* renamed from: a */
    public static void m4675a(String str, String str2) {
        m4687g().mo16766a(str, str2);
    }

    /* renamed from: f */
    public String mo17094f() {
        return this.f3142h.mo17651a();
    }

    /* renamed from: a */
    public void mo17092a(IIdentifierCallback iIdentifierCallback) {
        this.f3142h.mo17653a(iIdentifierCallback);
    }

    /* renamed from: a */
    public void mo17090a(int i, Bundle bundle) {
        if (i == 1) {
            this.f3142h.mo17652a(bundle);
            C1913g gVar = this.f3144j;
            if (gVar != null) {
                gVar.mo17108a();
            }
        } else if (i == 2) {
            this.f3142h.mo17658b(bundle);
        }
    }

    /* renamed from: a */
    public void mo17091a(DeferredDeeplinkParametersListener deferredDeeplinkParametersListener) {
        this.f3143i.mo16861a(deferredDeeplinkParametersListener);
    }
}

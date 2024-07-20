package com.yandex.metrica.impl;

import android.content.Context;
import android.os.Handler;
import android.os.RemoteException;
import com.yandex.metrica.IMetricaService;
import com.yandex.metrica.impl.C1870az;
import com.yandex.metrica.impl.C2208p;
import com.yandex.metrica.impl.p050ob.C2096dw;
import com.yandex.metrica.impl.utils.C2223g;
import com.yandex.metrica.impl.utils.C2228j;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

/* renamed from: com.yandex.metrica.impl.ay */
public class C1868ay implements C2213s {

    /* renamed from: a */
    private final Context f3015a;

    /* renamed from: b */
    private C1812ad f3016b;

    /* renamed from: c */
    private final NativeCrashesHelper f3017c;

    /* renamed from: d */
    private final ExecutorService f3018d;

    /* renamed from: e */
    private C2243z f3019e;

    /* renamed from: f */
    private C2215u f3020f;

    /* renamed from: g */
    private C2096dw f3021g;

    /* renamed from: h */
    private final C1870az f3022h = new C1870az(this);

    C1868ay(ExecutorService executorService, Context context, Handler handler) {
        this.f3016b = new C1812ad(context, handler);
        this.f3018d = executorService;
        this.f3015a = context;
        this.f3017c = new NativeCrashesHelper(context);
        this.f3020f = new C2215u(context);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo16909a(C2243z zVar) {
        this.f3019e = zVar;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo16908a(C2096dw dwVar) {
        this.f3021g = dwVar;
        this.f3020f.mo16888b(dwVar);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo16907a(C1921j jVar) {
        this.f3020f.mo16882a(jVar);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo16916a(boolean z, C1864aw awVar) {
        awVar.mo16887b().mo16574b(z);
        this.f3017c.mo16750a(z);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo16911a(String str, C1864aw awVar) {
        C2228j.m5960f().mo17902a("Error received: native");
        mo16906a(C2208p.m5868a(C2208p.C2209a.EVENT_TYPE_NATIVE_CRASH, str), awVar);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo16910a(String str) {
        mo16911a(str, this.f3019e.mo16952d());
    }

    /* access modifiers changed from: private */
    /* renamed from: c */
    public static C1915h m4387c(C1915h hVar, C1864aw awVar) {
        if (hVar.mo17118c() == C2208p.C2209a.EVENT_TYPE_EXCEPTION_USER.mo17884a()) {
            hVar.mo17124e(awVar.mo16892f());
        }
        return hVar;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo16906a(C1915h hVar, C1864aw awVar) {
        mo16903a(m4387c(hVar, awVar), awVar, (Map<String, Object>) null);
    }

    /* renamed from: a */
    public Future<Void> mo16903a(C1915h hVar, final C1864aw awVar, final Map<String, Object> map) {
        this.f3016b.mo16775c();
        C1870az.C1875d dVar = new C1870az.C1875d(hVar, awVar);
        if (!C1897bk.m4653a((Map) map)) {
            dVar.mo16933a((C1870az.C1874c) new C1870az.C1874c() {
                /* renamed from: a */
                public C1915h mo16925a(C1915h hVar) {
                    return C1868ay.m4387c(hVar.mo17107c(C2223g.m5948a(map)), awVar);
                }
            });
        }
        return m4385a(dVar);
    }

    /* renamed from: c */
    public void mo16920c() {
        mo16906a(C2208p.m5882d(C2208p.C2209a.EVENT_TYPE_STARTUP), (C1864aw) this.f3020f);
    }

    /* renamed from: b */
    public void mo16919b(String str) {
        mo16906a(C2208p.m5883d(str), (C1864aw) this.f3020f);
    }

    /* renamed from: a */
    public void mo16905a(C1864aw awVar) {
        mo16906a(C2208p.m5867a(awVar.mo16893g()), awVar);
    }

    /* renamed from: a */
    public void mo16914a(List<String> list) {
        this.f3020f.mo16887b().mo16568a(list);
    }

    /* renamed from: a */
    public void mo16915a(Map<String, String> map) {
        this.f3020f.mo16887b().mo16569a(map);
    }

    /* renamed from: c */
    public void mo16922c(String str) {
        this.f3020f.mo16887b().mo16594h(str);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo16913a(Throwable th, C1864aw awVar) {
        String str;
        if (awVar.mo16887b().mo16554C()) {
            C2228j.m5960f().mo17902a("Error received: uncaught");
        }
        this.f3016b.mo16775c();
        String a = C1897bk.m4640a((String) null, th);
        if (th == null) {
            str = "";
        } else {
            str = th.getClass().getName();
        }
        C1915h c = C2208p.m5879c(str, a);
        c.mo17124e(awVar.mo16892f());
        try {
            m4385a(new C1870az.C1875d(c, awVar).mo16934a(true)).get();
        } catch (InterruptedException | ExecutionException unused) {
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: d */
    public void mo16923d() {
        this.f3016b.mo16775c();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: e */
    public void mo16924e() {
        this.f3016b.mo16774b();
    }

    /* renamed from: a */
    public void mo16904a(IMetricaService iMetricaService, C1915h hVar, C1864aw awVar) throws RemoteException {
        mo16921c(awVar);
        if (awVar.mo16887b().mo16599l()) {
            this.f3017c.mo16749a(this, this.f3018d);
        }
        iMetricaService.reportData(hVar.mo17110a(awVar.mo16889c()));
        C2243z zVar = this.f3019e;
        if (zVar == null || zVar.mo16954e()) {
            this.f3016b.mo16774b();
        }
    }

    /* renamed from: a */
    public void mo16912a(String str, String str2, C1864aw awVar) {
        m4385a(new C1870az.C1875d(new C1915h().mo17111a(C2208p.C2209a.EVENT_TYPE_APP_ENVIRONMENT_UPDATED.mo17884a()).mo17114a(str, str2), awVar));
    }

    /* renamed from: b */
    public void mo16918b(C1864aw awVar) {
        m4385a(new C1870az.C1875d(new C1915h().mo17111a(C2208p.C2209a.EVENT_TYPE_APP_ENVIRONMENT_CLEARED.mo17884a()), awVar));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: c */
    public void mo16921c(C1864aw awVar) {
        if (awVar.mo16887b().mo16554C()) {
            awVar.mo16887b().mo16586e(C2228j.m5960f().mo17906b());
        }
    }

    /* renamed from: a */
    private Future<Void> m4385a(C1870az.C1875d dVar) {
        dVar.mo16932a().mo16883a(this.f3021g);
        return this.f3022h.mo16926a(dVar);
    }

    /* renamed from: a */
    public C1812ad mo16902a() {
        return this.f3016b;
    }

    /* renamed from: b */
    public Context mo16917b() {
        return this.f3015a;
    }
}

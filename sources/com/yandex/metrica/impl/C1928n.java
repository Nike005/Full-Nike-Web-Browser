package com.yandex.metrica.impl;

import android.location.Location;
import com.yandex.metrica.C1797e;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.yandex.metrica.impl.n */
public class C1928n implements C1811ac {

    /* renamed from: a */
    private Integer f3198a;

    /* renamed from: b */
    private Boolean f3199b;

    /* renamed from: c */
    private Boolean f3200c;

    /* renamed from: d */
    private Location f3201d;

    /* renamed from: e */
    private Boolean f3202e;

    /* renamed from: f */
    private String f3203f;

    /* renamed from: g */
    private Boolean f3204g;

    /* renamed from: h */
    private Map<String, String> f3205h = new HashMap();

    /* renamed from: i */
    private Map<String, String> f3206i = new HashMap();

    /* renamed from: j */
    private boolean f3207j;

    /* renamed from: k */
    private boolean f3208k;

    /* renamed from: a */
    public Integer mo17162a() {
        return this.f3198a;
    }

    /* renamed from: b */
    public Boolean mo17163b() {
        return this.f3199b;
    }

    /* renamed from: c */
    public Boolean mo17164c() {
        return this.f3200c;
    }

    /* renamed from: d */
    public Location mo17166d() {
        return this.f3201d;
    }

    /* renamed from: e */
    public Boolean mo17167e() {
        return this.f3202e;
    }

    /* renamed from: f */
    public String mo17168f() {
        return this.f3203f;
    }

    /* renamed from: g */
    public Boolean mo17169g() {
        return this.f3204g;
    }

    /* renamed from: h */
    public boolean mo16770h() {
        Boolean bool = this.f3204g;
        if (bool == null) {
            return false;
        }
        return bool.booleanValue();
    }

    /* renamed from: a */
    public void mo16767a(boolean z) {
        this.f3204g = Boolean.valueOf(z);
    }

    /* renamed from: a */
    public void mo16765a(String str) {
        this.f3203f = str;
    }

    /* renamed from: b */
    public void mo16768b(boolean z) {
        this.f3202e = Boolean.valueOf(z);
    }

    public void setSessionTimeout(int i) {
        this.f3198a = Integer.valueOf(i);
    }

    /* renamed from: c */
    public void mo17165c(boolean z) {
        this.f3199b = Boolean.valueOf(z);
    }

    /* renamed from: a */
    public void mo16764a(Location location) {
        this.f3201d = location;
    }

    /* renamed from: d */
    public void mo16769d(boolean z) {
        this.f3200c = Boolean.valueOf(z);
    }

    /* renamed from: i */
    public boolean mo17170i() {
        return this.f3207j;
    }

    /* renamed from: a */
    public void mo16766a(String str, String str2) {
        this.f3206i.put(str, str2);
    }

    /* renamed from: a */
    public C1797e mo17161a(C1797e eVar) {
        if (this.f3208k) {
            return eVar;
        }
        C1797e.C1798a a = C1797e.m4050a(eVar.getApiKey());
        a.mo16719a(eVar.mo16704f(), eVar.mo16709k());
        a.mo16715a(eVar.mo16703e());
        a.mo16714a(eVar.getPreloadInfo());
        a.mo16727c(eVar.mo16699a());
        a.mo16713a(eVar.getLocation());
        if (eVar.mo16701c() != null) {
            a.mo16718a(eVar.mo16701c());
        }
        if (eVar.getAppVersion() != null) {
            a.mo16716a(eVar.getAppVersion());
        }
        if (eVar.mo16707i() != null) {
            a.mo16729d(eVar.mo16707i().intValue());
        }
        if (eVar.mo16702d() != null) {
            a.mo16721b(eVar.mo16702d().intValue());
        }
        if (eVar.mo16706h() != null) {
            a.mo16726c(eVar.mo16706h().intValue());
        }
        if ((eVar.isLogEnabled() != null) && eVar.isLogEnabled().booleanValue()) {
            a.mo16711a();
        }
        if (eVar.getSessionTimeout() != null) {
            a.mo16712a(eVar.getSessionTimeout().intValue());
        }
        if (eVar.isReportCrashEnabled() != null) {
            a.mo16720a(eVar.isReportCrashEnabled().booleanValue());
        }
        if (eVar.isReportNativeCrashEnabled() != null) {
            a.mo16724b(eVar.isReportNativeCrashEnabled().booleanValue());
        }
        if (eVar.isTrackLocationEnabled() != null) {
            a.mo16728c(eVar.isTrackLocationEnabled().booleanValue());
        }
        if (eVar.isCollectInstalledApps() != null) {
            a.mo16731d(eVar.isCollectInstalledApps().booleanValue());
        }
        if (eVar.mo16705g() != null) {
            a.mo16722b(eVar.mo16705g());
        }
        if (eVar.isFirstActivationAsUpdate()) {
            a.mo16732e(true);
        }
        m4785a(eVar.mo16708j(), a);
        m4786b(eVar.getErrorEnvironment(), a);
        Integer a2 = mo17162a();
        if (eVar.getSessionTimeout() == null) {
            if (a2 != null) {
                a.mo16712a(a2.intValue());
            }
        }
        Boolean b = mo17163b();
        if (eVar.isReportCrashEnabled() == null) {
            if (b != null) {
                a.mo16720a(b.booleanValue());
            }
        }
        Boolean c = mo17164c();
        if (eVar.isReportNativeCrashEnabled() == null) {
            if (c != null) {
                a.mo16724b(c.booleanValue());
            }
        }
        Boolean e = mo17167e();
        if (eVar.isTrackLocationEnabled() == null) {
            if (e != null) {
                a.mo16728c(e.booleanValue());
            }
        }
        Location d = mo17166d();
        if (eVar.getLocation() == null) {
            if (d != null) {
                a.mo16713a(d);
            }
        }
        Boolean g = mo17169g();
        if (eVar.isCollectInstalledApps() == null) {
            if (g != null) {
                a.mo16731d(g.booleanValue());
            }
        }
        String f = mo17168f();
        if (eVar.getAppVersion() == null) {
            if (f != null) {
                a.mo16716a(f);
            }
        }
        m4785a(this.f3205h, a);
        m4786b(this.f3206i, a);
        this.f3208k = true;
        this.f3198a = null;
        this.f3199b = null;
        this.f3200c = null;
        this.f3201d = null;
        this.f3202e = null;
        this.f3203f = null;
        this.f3204g = null;
        this.f3205h.clear();
        this.f3206i.clear();
        this.f3207j = false;
        return a.mo16725b();
    }

    /* renamed from: a */
    private static void m4785a(Map<String, String> map, C1797e.C1798a aVar) {
        if (!C1897bk.m4653a((Map) map)) {
            for (Map.Entry next : map.entrySet()) {
                aVar.mo16723b((String) next.getKey(), (String) next.getValue());
            }
        }
    }

    /* renamed from: b */
    private static void m4786b(Map<String, String> map, C1797e.C1798a aVar) {
        if (!C1897bk.m4653a((Map) map)) {
            for (Map.Entry next : map.entrySet()) {
                aVar.mo16717a((String) next.getKey(), (String) next.getValue());
            }
        }
    }
}

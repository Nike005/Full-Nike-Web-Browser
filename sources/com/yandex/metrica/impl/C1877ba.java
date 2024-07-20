package com.yandex.metrica.impl;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageItemInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Build;
import android.text.TextUtils;
import com.integralads.avid.library.mopub.session.internal.InternalAvidAdSessionContext;
import com.yandex.metrica.C1779a;
import com.yandex.metrica.CounterConfiguration;
import com.yandex.metrica.YandexMetrica;
import com.yandex.metrica.impl.C1878bb;
import com.yandex.metrica.impl.C1890bg;
import com.yandex.metrica.impl.GoogleAdvertisingIdGetter;
import com.yandex.metrica.impl.interact.DeviceInfo;
import com.yandex.metrica.impl.p050ob.C2019cd;
import com.yandex.metrica.impl.p050ob.C2026ci;
import com.yandex.metrica.impl.p050ob.C2174g;
import com.yandex.metrica.impl.p050ob.C2180i;
import com.yandex.metrica.impl.p050ob.C2185m;
import com.yandex.metrica.impl.p050ob.C2186n;
import com.yandex.metrica.impl.p050ob.C2188p;
import com.yandex.metrica.impl.p050ob.C2198t;
import com.yandex.metrica.impl.utils.C2227i;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/* renamed from: com.yandex.metrica.impl.ba */
public class C1877ba {

    /* renamed from: A */
    private List<String> f3042A;

    /* renamed from: B */
    private String f3043B;

    /* renamed from: C */
    private String f3044C;

    /* renamed from: D */
    private String f3045D;

    /* renamed from: E */
    private String f3046E;

    /* renamed from: F */
    private boolean f3047F;

    /* renamed from: G */
    private boolean f3048G;

    /* renamed from: H */
    private boolean f3049H;

    /* renamed from: I */
    private boolean f3050I;

    /* renamed from: J */
    private String f3051J;

    /* renamed from: K */
    private String f3052K;

    /* renamed from: L */
    private String f3053L;

    /* renamed from: a */
    private String f3054a = Build.MANUFACTURER;

    /* renamed from: b */
    private String f3055b = Build.MODEL;

    /* renamed from: c */
    private String f3056c = Build.VERSION.RELEASE;

    /* renamed from: d */
    private int f3057d = Build.VERSION.SDK_INT;

    /* renamed from: e */
    private String f3058e = "273";

    /* renamed from: f */
    private String f3059f = C1880bc.m4536a();

    /* renamed from: g */
    private String f3060g = "7854";

    /* renamed from: h */
    private String f3061h;

    /* renamed from: i */
    private String f3062i;

    /* renamed from: j */
    private String f3063j;

    /* renamed from: k */
    private String f3064k;

    /* renamed from: l */
    private String f3065l;

    /* renamed from: m */
    private String f3066m;

    /* renamed from: n */
    private String f3067n;

    /* renamed from: o */
    private String f3068o;

    /* renamed from: p */
    private String f3069p;

    /* renamed from: q */
    private int f3070q;

    /* renamed from: r */
    private int f3071r;

    /* renamed from: s */
    private int f3072s;

    /* renamed from: t */
    private float f3073t;

    /* renamed from: u */
    private String f3074u;

    /* renamed from: v */
    private String f3075v;

    /* renamed from: w */
    private String f3076w;

    /* renamed from: x */
    private String f3077x;

    /* renamed from: y */
    private String f3078y;

    /* renamed from: z */
    private List<String> f3079z;

    public C1877ba() {
        this.f3061h = TextUtils.isEmpty("") ? "public" : "public_";
        this.f3062i = SystemMediaRouteProvider.PACKAGE_NAME;
        this.f3063j = InternalAvidAdSessionContext.AVID_API_LEVEL;
        this.f3074u = C1779a.PHONE.name().toLowerCase(Locale.US);
        this.f3046E = "0";
    }

    /* renamed from: a */
    public String mo16969a() {
        return this.f3068o;
    }

    /* renamed from: b */
    public synchronized String mo16981b() {
        return m4453a(this.f3064k, "");
    }

    /* renamed from: c */
    public synchronized String mo16986c() {
        return m4453a(this.f3065l, "");
    }

    /* renamed from: d */
    public String mo16991d() {
        return m4453a(this.f3069p, "");
    }

    /* renamed from: c */
    public void mo16988c(String str) {
        this.f3069p = str;
    }

    /* renamed from: d */
    public synchronized void mo16992d(String str) {
        this.f3066m = str;
    }

    /* renamed from: e */
    public synchronized String mo16994e() {
        return this.f3066m;
    }

    /* renamed from: f */
    public String mo16997f() {
        return this.f3063j;
    }

    /* renamed from: g */
    public String mo16999g() {
        return this.f3059f;
    }

    /* renamed from: e */
    public void mo16996e(String str) {
        this.f3058e = str;
    }

    /* renamed from: h */
    public String mo17001h() {
        return this.f3058e;
    }

    /* renamed from: f */
    public void mo16998f(String str) {
        this.f3076w = str;
    }

    /* renamed from: i */
    public String mo17003i() {
        return this.f3076w;
    }

    /* renamed from: j */
    public int mo17005j() {
        return C2227i.m5955a(this.f3076w, 0);
    }

    /* renamed from: k */
    public String mo17007k() {
        return this.f3060g;
    }

    /* renamed from: g */
    public void mo17000g(String str) {
        this.f3060g = str;
    }

    /* renamed from: l */
    public String mo17009l() {
        return this.f3061h;
    }

    /* renamed from: h */
    public void mo17002h(String str) {
        this.f3061h = str;
    }

    /* renamed from: m */
    public String mo17011m() {
        return this.f3062i;
    }

    /* renamed from: n */
    public String mo17013n() {
        return m4453a(this.f3067n, "");
    }

    /* renamed from: o */
    public String mo17015o() {
        return m4453a(this.f3054a, "");
    }

    /* renamed from: p */
    public String mo17017p() {
        return m4453a(this.f3055b, "");
    }

    /* renamed from: q */
    public String mo17019q() {
        return m4453a(this.f3056c, "");
    }

    /* renamed from: r */
    public int mo17021r() {
        return this.f3057d;
    }

    /* renamed from: i */
    public void mo17004i(String str) {
        this.f3056c = str;
    }

    /* renamed from: a */
    public void mo16972a(int i) {
        this.f3057d = i;
    }

    /* renamed from: s */
    public int mo17023s() {
        return this.f3070q;
    }

    /* renamed from: t */
    public int mo17025t() {
        return this.f3071r;
    }

    /* renamed from: u */
    public int mo17026u() {
        return this.f3072s;
    }

    /* renamed from: v */
    public float mo17027v() {
        return this.f3073t;
    }

    /* renamed from: w */
    public String mo17028w() {
        return m4453a(this.f3075v, "");
    }

    /* renamed from: j */
    public void mo17006j(String str) {
        this.f3075v = str;
    }

    /* renamed from: x */
    public String mo17029x() {
        return m4453a(this.f3077x, "");
    }

    /* renamed from: l */
    public void mo17010l(String str) {
        this.f3051J = str;
    }

    /* renamed from: y */
    public String mo17030y() {
        return m4453a(this.f3051J, "");
    }

    /* renamed from: z */
    public String mo17031z() {
        return m4453a(this.f3078y, "");
    }

    /* renamed from: a */
    public void mo16978a(List<String> list) {
        this.f3079z = list;
    }

    /* renamed from: n */
    public void mo17014n(String str) {
        this.f3043B = str;
    }

    /* renamed from: o */
    public void mo17016o(String str) {
        this.f3044C = str;
    }

    /* renamed from: A */
    public String mo16955A() {
        return m4453a(this.f3044C, "");
    }

    /* renamed from: B */
    public String mo16956B() {
        return m4453a(this.f3045D, "");
    }

    /* renamed from: p */
    public void mo17018p(String str) {
        this.f3045D = str;
    }

    /* renamed from: C */
    public String mo16957C() {
        return m4453a(this.f3043B, "");
    }

    /* renamed from: D */
    public List<String> mo16958D() {
        ArrayList arrayList = new ArrayList();
        if (!C1897bk.m4652a((Collection) this.f3079z)) {
            arrayList.addAll(this.f3079z);
        }
        if (!C1897bk.m4652a((Collection) this.f3042A)) {
            arrayList.addAll(this.f3042A);
        }
        arrayList.add("https://startup.mobile.yandex.net/");
        return arrayList;
    }

    /* renamed from: E */
    public List<String> mo16959E() {
        return this.f3079z;
    }

    /* renamed from: q */
    public void mo17020q(String str) {
        this.f3046E = str;
    }

    /* renamed from: F */
    public String mo16960F() {
        return m4453a(this.f3046E, "0");
    }

    /* renamed from: G */
    public String mo16961G() {
        return m4453a(this.f3074u, C1779a.PHONE.name().toLowerCase(Locale.US));
    }

    /* renamed from: H */
    public boolean mo16962H() {
        return this.f3047F;
    }

    /* renamed from: a */
    public void mo16979a(boolean z) {
        this.f3047F = z;
    }

    /* renamed from: I */
    public boolean mo16963I() {
        return this.f3048G;
    }

    /* renamed from: J */
    public boolean mo16964J() {
        return this.f3049H;
    }

    /* renamed from: K */
    public boolean mo16965K() {
        return this.f3050I;
    }

    /* renamed from: b */
    public void mo16985b(boolean z) {
        this.f3048G = z;
    }

    /* renamed from: c */
    public void mo16989c(boolean z) {
        this.f3049H = z;
    }

    /* renamed from: d */
    public void mo16993d(boolean z) {
        this.f3050I = z;
    }

    /* renamed from: L */
    public String mo16966L() {
        return m4453a(this.f3052K, "https://certificate.mobile.yandex.net/api/v1/pins");
    }

    /* renamed from: r */
    public void mo17022r(String str) {
        this.f3052K = str;
    }

    /* renamed from: M */
    public synchronized boolean mo16967M() {
        return !C1894bi.m4624a(mo16981b(), mo16986c(), mo16957C());
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0024, code lost:
        return false;
     */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized boolean mo16980a(long r7) {
        /*
            r6 = this;
            monitor-enter(r6)
            boolean r0 = r6.mo16967M()     // Catch:{ all -> 0x0025 }
            r1 = 0
            if (r0 != 0) goto L_0x000a
            monitor-exit(r6)
            return r1
        L_0x000a:
            long r2 = java.lang.System.currentTimeMillis()     // Catch:{ all -> 0x0025 }
            r4 = 1000(0x3e8, double:4.94E-321)
            long r2 = r2 / r4
            long r2 = r2 - r7
            r7 = 86400(0x15180, double:4.26873E-319)
            int r0 = (r2 > r7 ? 1 : (r2 == r7 ? 0 : -1))
            if (r0 > 0) goto L_0x0023
            r7 = 0
            int r0 = (r2 > r7 ? 1 : (r2 == r7 ? 0 : -1))
            if (r0 >= 0) goto L_0x0020
            goto L_0x0023
        L_0x0020:
            r7 = 1
            monitor-exit(r6)
            return r7
        L_0x0023:
            monitor-exit(r6)
            return r1
        L_0x0025:
            r7 = move-exception
            monitor-exit(r6)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.yandex.metrica.impl.C1877ba.mo16980a(long):boolean");
    }

    /* renamed from: a */
    public void mo16975a(C2198t tVar) {
        Context m = tVar.mo17866m();
        String b = tVar.mo17865l().mo17819b();
        CounterConfiguration j = tVar.mo17863j();
        DeviceInfo instance = DeviceInfo.getInstance(m);
        C2019cd E = tVar.mo17833E();
        this.f3068o = C1897bk.m4637a(m, j, b);
        this.f3074u = mo16971a(m, j);
        List<ResolveInfo> a = C1887be.m4553a(m, C1887be.m4552a(m).setPackage(b));
        C1878bb.C1879a a2 = !a.isEmpty() ? C1878bb.m4535a(C1887be.m4550a((PackageItemInfo) a.get(0).serviceInfo)) : null;
        if (a2 == null) {
            a2 = C1878bb.f3080a;
            HashMap hashMap = new HashMap();
            hashMap.put("package", b);
            YandexMetrica.getReporter(m, "20799a27-fa80-4b36-b2db-0f8141f24180").reportEvent("invalid_sdk_version", (Map<String, Object>) hashMap);
        }
        this.f3076w = a2.f3082a;
        m4454a(instance);
        mo16976a(tVar, E);
        m4456b(tVar, E);
        m4455b(E);
        String o = j.mo16602o();
        if (C1894bi.m4622a(o)) {
            o = mo17029x();
            if (C1894bi.m4622a(o)) {
                o = C1897bk.m4638a(m, b);
            }
        }
        mo17008k(o);
        String p = j.mo16603p();
        if (C1894bi.m4622a(p)) {
            p = mo17031z();
            if (C1894bi.m4622a(p)) {
                p = String.valueOf(C1897bk.m4655b(m, b));
            }
        }
        mo17012m(p);
        String packageName = tVar.mo17866m().getPackageName();
        try {
            this.f3053L = m4452a(mo16990d(tVar));
        } catch (PackageManager.NameNotFoundException unused) {
            if (TextUtils.equals(packageName, tVar.mo17865l().mo17819b())) {
                this.f3053L = m4452a(tVar.mo17866m().getApplicationInfo());
            } else {
                this.f3053L = "0";
            }
        }
        mo16995e(tVar);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public void mo16983b(C2198t tVar) {
        C2019cd E = tVar.mo17833E();
        m4456b(tVar, E);
        mo16976a(tVar, E);
        mo16995e(tVar);
    }

    /* renamed from: b */
    private void m4455b(C2019cd cdVar) {
        this.f3051J = cdVar.mo17426d((String) null);
    }

    /* renamed from: c */
    public void mo16987c(C2198t tVar) {
        m4454a(DeviceInfo.getInstance(tVar.mo17866m()));
        m4455b(tVar.mo17833E());
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public String mo16971a(Context context, CounterConfiguration counterConfiguration) {
        C1779a e = counterConfiguration.mo16584e();
        return e == null ? mo16982b(context) : e.mo16673a();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public String mo16982b(Context context) {
        return DeviceInfo.getInstance(context).deviceType;
    }

    /* renamed from: a */
    private void m4454a(DeviceInfo deviceInfo) {
        this.f3067n = deviceInfo.platformDeviceId;
        C2174g.m5753a().mo17806b((C2180i) new C2185m(this.f3067n));
        this.f3072s = deviceInfo.screenDpi;
        this.f3073t = deviceInfo.scaleFactor;
        int i = deviceInfo.screenWidth;
        int i2 = deviceInfo.screenHeight;
        this.f3070q = Math.max(i, i2);
        this.f3071r = Math.min(i, i2);
        this.f3075v = deviceInfo.getLocale();
        this.f3046E = deviceInfo.deviceRootStatus;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: d */
    public ApplicationInfo mo16990d(C2198t tVar) throws PackageManager.NameNotFoundException {
        return tVar.mo17866m().getPackageManager().getApplicationInfo(tVar.mo17865l().mo17819b(), 0);
    }

    /* renamed from: a */
    private static String m4452a(ApplicationInfo applicationInfo) {
        return (applicationInfo.flags & 2) != 0 ? "1" : "0";
    }

    /* renamed from: a */
    public synchronized void mo16973a(C1890bg.C1891a aVar) {
        mo16977a(aVar.mo17062i());
        mo16984b(aVar.mo17060h());
        mo17016o(aVar.mo17050d());
        mo16978a(aVar.mo17047c());
        mo17014n(aVar.mo17053e());
        mo17018p(aVar.mo17056f());
        mo17022r(aVar.mo17058g());
        mo17010l(aVar.mo17063j());
        mo16979a(aVar.mo17043a());
        mo16985b(aVar.mo17046b());
        mo16989c(aVar.mo17069p());
        mo16993d(aVar.mo17070q());
    }

    /* renamed from: e */
    public synchronized void mo16995e(C2198t tVar) {
        CounterConfiguration j = tVar.mo17863j();
        mo16977a(j.mo16590g());
        mo16992d(j.mo16596i());
        mo17008k(j.mo16602o());
        mo17012m(j.mo16603p());
        mo16988c(j.mo16556E());
        m4457f(tVar);
    }

    /* renamed from: f */
    private void m4457f(C2198t tVar) {
        CounterConfiguration j = tVar.mo17863j();
        if (tVar.mo17865l().mo17821d() || tVar.mo17865l().mo17820c()) {
            C2019cd E = tVar.mo17833E();
            List<String> n = j.mo16601n();
            if (C1897bk.m4652a((Collection) n) && !C1897bk.m4652a((Collection) this.f3042A)) {
                E.mo17418b((List<String>) null).mo17398h();
                this.f3042A = null;
            }
            if (!C1897bk.m4652a((Collection) n) && !C1897bk.m4651a((Object) n, (Object) this.f3042A)) {
                this.f3042A = n;
                E.mo17418b(n).mo17398h();
            }
        }
    }

    /* renamed from: N */
    public String mo16968N() {
        return this.f3053L;
    }

    /* renamed from: s */
    public void mo17024s(String str) {
        this.f3053L = str;
    }

    /* renamed from: a */
    public synchronized void mo16977a(String str) {
        if (!C1894bi.m4622a(str)) {
            this.f3064k = str;
        }
    }

    /* renamed from: b */
    public synchronized void mo16984b(String str) {
        if (!C1894bi.m4622a(str)) {
            this.f3065l = str;
        }
    }

    /* renamed from: a */
    public String mo16970a(Context context) {
        return m4453a(GoogleAdvertisingIdGetter.C1803b.f2873a.mo16736b(context), "");
    }

    /* renamed from: k */
    public void mo17008k(String str) {
        if (!C1894bi.m4622a(str)) {
            this.f3077x = str;
        }
    }

    /* renamed from: m */
    public void mo17012m(String str) {
        if (!C1894bi.m4622a(str)) {
            this.f3078y = str;
        }
    }

    /* renamed from: a */
    private static String m4453a(String str, String str2) {
        return !C1894bi.m4622a(str) ? str : str2;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo16976a(C2198t tVar, C2019cd cdVar) {
        this.f3044C = cdVar.mo17431f("");
        this.f3045D = cdVar.mo17432g("");
        cdVar.mo17424c("https://startup.mobile.yandex.net/");
        this.f3079z = cdVar.mo17416a();
        this.f3042A = cdVar.mo17421b();
        this.f3043B = cdVar.mo17429e("");
        m4457f(tVar);
        mo16974a(cdVar);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo16974a(C2019cd cdVar) {
        mo16979a(cdVar.mo17430e());
        mo16985b(cdVar.mo17435i());
        mo16989c(cdVar.mo17437j());
        mo16993d(cdVar.mo17439k());
    }

    /* renamed from: b */
    private synchronized void m4456b(C2198t tVar, C2019cd cdVar) {
        String c = mo16986c();
        if (C1894bi.m4622a(c)) {
            c = tVar.mo17863j().mo16593h();
            if (C1894bi.m4622a(c)) {
                c = C2026ci.m5230a().mo17473a(tVar.mo17866m());
            }
        }
        C2174g.m5753a().mo17806b((C2180i) new C2186n(c));
        mo16984b(c);
        String g = tVar.mo17863j().mo16590g();
        if (C1894bi.m4622a(g)) {
            g = mo16981b();
            if (C1894bi.m4622a(g)) {
                g = cdVar.mo17420b("");
            }
        }
        C2174g.m5753a().mo17806b((C2180i) new C2188p(g, tVar.mo17865l().mo17819b()));
        mo16977a(g);
    }
}

package com.yandex.metrica.impl.p050ob;

import com.yandex.metrica.impl.utils.C2223g;
import java.util.List;

/* renamed from: com.yandex.metrica.impl.ob.cd */
public class C2019cd extends C2017cb {

    /* renamed from: a */
    static final C2068dk f3355a = new C2068dk("PREF_KEY_UID_");

    /* renamed from: b */
    static final C2068dk f3356b = new C2068dk("PREF_KEY_DEVICE_ID_");

    /* renamed from: c */
    private static final C2068dk f3357c = new C2068dk("PREF_KEY_HOST_URL_");

    /* renamed from: d */
    private static final C2068dk f3358d = new C2068dk("PREF_KEY_HOST_URLS_FROM_STARTUP");

    /* renamed from: e */
    private static final C2068dk f3359e = new C2068dk("PREF_KEY_HOST_URLS_FROM_CLIENT");

    /* renamed from: f */
    private static final C2068dk f3360f = new C2068dk("PREF_KEY_REPORT_URL_");

    /* renamed from: g */
    private static final C2068dk f3361g = new C2068dk("PREF_KEY_GET_AD_URL");

    /* renamed from: h */
    private static final C2068dk f3362h = new C2068dk("PREF_KEY_REPORT_AD_URL");

    /* renamed from: i */
    private static final C2068dk f3363i = new C2068dk("PREF_KEY_STARTUP_OBTAIN_TIME_");

    /* renamed from: j */
    private static final C2068dk f3364j = new C2068dk("PREF_KEY_STARTUP_ENCODED_CLIDS_");

    /* renamed from: k */
    private static final C2068dk f3365k = new C2068dk("PREF_KEY_DISTRIBUTION_REFERRER_");

    /* renamed from: l */
    private static final C2068dk f3366l = new C2068dk("STARTUP_CLIDS_MATCH_WITH_APP_CLIDS_");

    /* renamed from: m */
    private static final C2068dk f3367m = new C2068dk("PREF_KEY_PINNING_UPDATE_URL");

    /* renamed from: n */
    private static final C2068dk f3368n = new C2068dk("PREF_KEY_EASY_COLLECTING_ENABLED_");

    /* renamed from: o */
    private static final C2068dk f3369o = new C2068dk("PREF_KEY_COLLECTING_PACKAGE_INFO_ENABLED_");

    /* renamed from: p */
    private static final C2068dk f3370p = new C2068dk("PREF_KEY_PERMISSIONS_COLLECTING_ENABLED_");

    /* renamed from: q */
    private static final C2068dk f3371q = new C2068dk("PREF_KEY_FEATURES_COLLECTING_ENABLED_");

    /* renamed from: r */
    private static final C2068dk f3372r = new C2068dk("SOCKET_CONFIG_");

    /* renamed from: s */
    private static final C2068dk f3373s = new C2068dk("LAST_STARTUP_REQUEST_CLIDS");

    /* renamed from: t */
    private static final C2068dk f3374t = new C2068dk("LAST_STARTUP_CLIDS_SAVE_TIME");

    /* renamed from: A */
    private C2068dk f3375A;

    /* renamed from: B */
    private C2068dk f3376B;

    /* renamed from: C */
    private C2068dk f3377C;

    /* renamed from: D */
    private C2068dk f3378D;

    /* renamed from: E */
    private C2068dk f3379E;

    /* renamed from: F */
    private C2068dk f3380F;

    /* renamed from: G */
    private C2068dk f3381G;

    /* renamed from: H */
    private C2068dk f3382H;

    /* renamed from: I */
    private C2068dk f3383I;

    /* renamed from: J */
    private C2068dk f3384J;

    /* renamed from: K */
    private C2068dk f3385K;

    /* renamed from: L */
    private C2068dk f3386L;

    /* renamed from: M */
    private C2068dk f3387M;

    /* renamed from: u */
    private C2068dk f3388u;

    /* renamed from: v */
    private C2068dk f3389v;

    /* renamed from: w */
    private C2068dk f3390w;

    /* renamed from: x */
    private C2068dk f3391x;

    /* renamed from: y */
    private C2068dk f3392y;

    /* renamed from: z */
    private C2068dk f3393z;

    public C2019cd(C2004bq bqVar, String str) {
        super(bqVar, str);
    }

    /* access modifiers changed from: protected */
    /* renamed from: f */
    public void mo17396f() {
        super.mo17396f();
        this.f3388u = new C2068dk(f3356b.mo17604a());
        this.f3389v = mo17399q(f3355a.mo17604a());
        this.f3390w = mo17399q(f3357c.mo17604a());
        this.f3391x = mo17399q(f3358d.mo17604a());
        this.f3392y = mo17399q(f3359e.mo17604a());
        this.f3393z = mo17399q(f3360f.mo17604a());
        this.f3375A = mo17399q(f3361g.mo17604a());
        this.f3376B = mo17399q(f3362h.mo17604a());
        this.f3377C = mo17399q(f3363i.mo17604a());
        this.f3378D = mo17399q(f3364j.mo17604a());
        this.f3379E = mo17399q(f3365k.mo17604a());
        this.f3380F = mo17399q(f3366l.mo17604a());
        this.f3381G = mo17399q(f3368n.mo17604a());
        this.f3382H = mo17399q(f3369o.mo17604a());
        this.f3383I = mo17399q(f3370p.mo17604a());
        this.f3384J = mo17399q(f3371q.mo17604a());
        this.f3386L = mo17399q(f3373s.mo17604a());
        this.f3387M = mo17399q(f3374t.mo17604a());
        this.f3385K = mo17399q(f3372r.mo17604a());
    }

    /* renamed from: a */
    public long mo17412a(long j) {
        return mo17393b(this.f3377C.mo17606b(), j);
    }

    /* renamed from: a */
    public String mo17415a(String str) {
        return mo17394b(this.f3388u.mo17606b(), str);
    }

    /* renamed from: b */
    public String mo17420b(String str) {
        return mo17394b(this.f3389v.mo17606b(), str);
    }

    /* renamed from: c */
    public String mo17424c(String str) {
        return mo17394b(this.f3390w.mo17606b(), str);
    }

    /* renamed from: a */
    public List<String> mo17416a() {
        return C2223g.m5951b(mo17394b(this.f3391x.mo17606b(), (String) null));
    }

    /* renamed from: b */
    public List<String> mo17421b() {
        return C2223g.m5951b(mo17394b(this.f3392y.mo17606b(), (String) null));
    }

    /* renamed from: d */
    public String mo17426d(String str) {
        return mo17394b(this.f3378D.mo17606b(), str);
    }

    /* renamed from: e */
    public String mo17429e(String str) {
        return mo17394b(this.f3393z.mo17606b(), str);
    }

    /* renamed from: f */
    public String mo17431f(String str) {
        return mo17394b(this.f3375A.mo17606b(), str);
    }

    /* renamed from: g */
    public String mo17432g(String str) {
        return mo17394b(this.f3376B.mo17606b(), str);
    }

    /* renamed from: c */
    public String mo17423c() {
        return mo17394b(this.f3379E.mo17606b(), (String) null);
    }

    /* renamed from: d */
    public boolean mo17427d() {
        return mo17395b(this.f3380F.mo17606b(), true);
    }

    /* renamed from: e */
    public boolean mo17430e() {
        return mo17395b(this.f3381G.mo17606b(), false);
    }

    /* renamed from: i */
    public boolean mo17435i() {
        return mo17395b(this.f3382H.mo17606b(), false);
    }

    /* renamed from: j */
    public boolean mo17437j() {
        return mo17395b(this.f3383I.mo17606b(), false);
    }

    /* renamed from: k */
    public boolean mo17439k() {
        return mo17395b(this.f3384J.mo17606b(), false);
    }

    /* renamed from: l */
    public String mo17441l() {
        return mo17394b(this.f3385K.mo17606b(), (String) null);
    }

    /* renamed from: h */
    public String mo17433h(String str) {
        return mo17394b(f3367m.mo17606b(), str);
    }

    /* renamed from: i */
    public C2019cd mo17434i(String str) {
        return (C2019cd) mo17390a(f3367m.mo17606b(), str);
    }

    /* renamed from: j */
    public C2019cd mo17436j(String str) {
        return (C2019cd) mo17390a(this.f3389v.mo17606b(), str);
    }

    /* renamed from: k */
    public C2019cd mo17438k(String str) {
        return (C2019cd) mo17390a(this.f3388u.mo17606b(), str);
    }

    /* renamed from: l */
    public C2019cd mo17440l(String str) {
        return (C2019cd) mo17390a(this.f3393z.mo17606b(), str);
    }

    /* renamed from: m */
    public C2019cd mo17442m(String str) {
        return (C2019cd) mo17390a(this.f3376B.mo17606b(), str);
    }

    /* renamed from: n */
    public C2019cd mo17445n(String str) {
        return (C2019cd) mo17390a(this.f3375A.mo17606b(), str);
    }

    /* renamed from: o */
    public C2019cd mo17446o(String str) {
        return (C2019cd) mo17390a(this.f3390w.mo17606b(), str);
    }

    /* renamed from: a */
    public C2019cd mo17413a(List<String> list) {
        return (C2019cd) mo17390a(this.f3391x.mo17606b(), C2223g.m5947a(list));
    }

    /* renamed from: b */
    public C2019cd mo17418b(List<String> list) {
        return (C2019cd) mo17390a(this.f3392y.mo17606b(), C2223g.m5947a(list));
    }

    /* renamed from: a */
    public C2019cd mo17414a(boolean z) {
        return (C2019cd) mo17391a(this.f3381G.mo17606b(), z);
    }

    /* renamed from: b */
    public C2019cd mo17419b(boolean z) {
        return (C2019cd) mo17391a(this.f3382H.mo17606b(), z);
    }

    /* renamed from: c */
    public C2019cd mo17422c(boolean z) {
        return (C2019cd) mo17391a(this.f3383I.mo17606b(), z);
    }

    /* renamed from: d */
    public C2019cd mo17425d(boolean z) {
        return (C2019cd) mo17391a(this.f3384J.mo17606b(), z);
    }

    /* renamed from: b */
    public C2019cd mo17417b(long j) {
        return (C2019cd) mo17389a(this.f3377C.mo17606b(), j);
    }

    /* renamed from: p */
    public C2019cd mo17447p(String str) {
        return (C2019cd) mo17390a(this.f3378D.mo17606b(), str);
    }

    /* renamed from: s */
    public C2019cd mo17448s(String str) {
        return (C2019cd) mo17390a(this.f3379E.mo17606b(), str);
    }

    /* renamed from: e */
    public C2019cd mo17428e(boolean z) {
        return (C2019cd) mo17391a(this.f3380F.mo17606b(), z);
    }

    /* renamed from: t */
    public C2019cd mo17449t(String str) {
        return (C2019cd) mo17390a(this.f3385K.mo17606b(), str);
    }

    /* renamed from: m */
    public String mo17443m() {
        return mo17394b(this.f3386L.mo17606b(), (String) null);
    }

    /* renamed from: n */
    public long mo17444n() {
        return mo17393b(this.f3387M.mo17606b(), -1);
    }

    /* renamed from: u */
    public C2019cd mo17450u(String str) {
        return (C2019cd) mo17390a(this.f3386L.mo17606b(), str).mo17389a(this.f3387M.mo17606b(), System.currentTimeMillis());
    }
}

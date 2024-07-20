package com.yandex.metrica.impl.p050ob;

import android.content.Context;
import android.content.SharedPreferences;
import com.yandex.metrica.CounterConfiguration;
import com.yandex.metrica.impl.C1807a;

/* renamed from: com.yandex.metrica.impl.ob.df */
public class C2063df extends C2061dd {

    /* renamed from: c */
    public static final C2068dk f3477c = new C2068dk("APP_ENVIRONMENT");

    /* renamed from: d */
    public static final C2068dk f3478d = new C2068dk("APP_ENVIRONMENT_REVISION");

    /* renamed from: e */
    private static final C2068dk f3479e = new C2068dk("SESSION_SLEEP_START_");

    /* renamed from: f */
    private static final C2068dk f3480f = new C2068dk("SESSION_ID_");

    /* renamed from: g */
    private static final C2068dk f3481g = new C2068dk("SESSION_COUNTER_ID_");

    /* renamed from: h */
    private static final C2068dk f3482h = new C2068dk("SESSION_INIT_TIME_");

    /* renamed from: i */
    private static final C2068dk f3483i = new C2068dk("SESSION_ALIVE_TIME_");

    /* renamed from: j */
    private static final C2068dk f3484j = new C2068dk("SESSION_IS_ALIVE_REPORT_NEEDED_");

    /* renamed from: k */
    private static final C2068dk f3485k = new C2068dk("BG_SESSION_ID_");

    /* renamed from: l */
    private static final C2068dk f3486l = new C2068dk("BG_SESSION_SLEEP_START_");

    /* renamed from: m */
    private static final C2068dk f3487m = new C2068dk("BG_SESSION_COUNTER_ID_");

    /* renamed from: n */
    private static final C2068dk f3488n = new C2068dk("BG_SESSION_INIT_TIME_");

    /* renamed from: o */
    private static final C2068dk f3489o = new C2068dk("COLLECT_INSTALLED_APPS_");

    /* renamed from: p */
    private static final C2068dk f3490p = new C2068dk("IDENTITY_SEND_TIME_");

    /* renamed from: q */
    private static final C2068dk f3491q = new C2068dk("USER_INFO_");

    /* renamed from: r */
    private static final C2068dk f3492r = new C2068dk("REFERRER_");

    /* renamed from: s */
    private static final C2068dk f3493s = new C2068dk("APP_ENVIRONMENT_");

    /* renamed from: t */
    private static final C2068dk f3494t = new C2068dk("APP_ENVIRONMENT_REVISION_");

    /* renamed from: A */
    private C2068dk f3495A;

    /* renamed from: B */
    private C2068dk f3496B;

    /* renamed from: C */
    private C2068dk f3497C;

    /* renamed from: D */
    private C2068dk f3498D;

    /* renamed from: E */
    private C2068dk f3499E;

    /* renamed from: F */
    private C2068dk f3500F;

    /* renamed from: G */
    private C2068dk f3501G;

    /* renamed from: H */
    private C2068dk f3502H;

    /* renamed from: I */
    private C2068dk f3503I;

    /* renamed from: J */
    private C2068dk f3504J;

    /* renamed from: u */
    private C2068dk f3505u;

    /* renamed from: v */
    private C2068dk f3506v;

    /* renamed from: w */
    private C2068dk f3507w;

    /* renamed from: x */
    private C2068dk f3508x;

    /* renamed from: y */
    private C2068dk f3509y;

    /* renamed from: z */
    private C2068dk f3510z;

    /* access modifiers changed from: protected */
    /* renamed from: f */
    public String mo17543f() {
        return "_boundentrypreferences";
    }

    public C2063df(Context context, String str) {
        super(context, str);
        mo17576d();
        mo17567a(-1);
        mo17571b(0);
        mo17574c(0);
    }

    /* access modifiers changed from: protected */
    /* renamed from: h */
    public void mo17548h() {
        super.mo17548h();
        this.f3505u = new C2068dk(f3479e.mo17604a(), mo17550j());
        this.f3506v = new C2068dk(f3480f.mo17604a(), mo17550j());
        this.f3507w = new C2068dk(f3481g.mo17604a(), mo17550j());
        this.f3508x = new C2068dk(f3482h.mo17604a(), mo17550j());
        this.f3509y = new C2068dk(f3483i.mo17604a(), mo17550j());
        this.f3510z = new C2068dk(f3484j.mo17604a(), mo17550j());
        this.f3495A = new C2068dk(f3485k.mo17604a(), mo17550j());
        this.f3496B = new C2068dk(f3486l.mo17604a(), mo17550j());
        this.f3497C = new C2068dk(f3487m.mo17604a(), mo17550j());
        this.f3498D = new C2068dk(f3488n.mo17604a(), mo17550j());
        this.f3499E = new C2068dk(f3490p.mo17604a(), mo17550j());
        this.f3500F = new C2068dk(f3489o.mo17604a(), mo17550j());
        this.f3501G = new C2068dk(f3491q.mo17604a(), mo17550j());
        this.f3502H = new C2068dk(f3492r.mo17604a(), mo17550j());
        this.f3503I = new C2068dk(f3493s.mo17604a(), mo17550j());
        this.f3504J = new C2068dk(f3494t.mo17604a(), mo17550j());
    }

    /* renamed from: a */
    public long mo17562a(long j) {
        return m5344a(this.f3508x.mo17606b(), j);
    }

    /* renamed from: b */
    public long mo17568b(long j) {
        return m5344a(this.f3498D.mo17606b(), j);
    }

    /* renamed from: c */
    public long mo17572c(long j) {
        return m5344a(this.f3499E.mo17606b(), j);
    }

    /* renamed from: d */
    public long mo17575d(long j) {
        return m5344a(this.f3506v.mo17606b(), j);
    }

    /* renamed from: e */
    public long mo17577e(long j) {
        return m5344a(this.f3495A.mo17606b(), j);
    }

    /* renamed from: f */
    public long mo17579f(long j) {
        return m5344a(this.f3507w.mo17606b(), j);
    }

    /* renamed from: a */
    private long m5344a(String str, long j) {
        return this.f3455b.getLong(str, j);
    }

    /* renamed from: g */
    public long mo17580g(long j) {
        return m5344a(this.f3497C.mo17606b(), j);
    }

    /* renamed from: a */
    public C1807a.C1808a mo17563a() {
        synchronized (this) {
            if (!this.f3455b.contains(this.f3503I.mo17606b()) || !this.f3455b.contains(this.f3504J.mo17606b())) {
                return null;
            }
            C1807a.C1808a aVar = new C1807a.C1808a(this.f3455b.getString(this.f3503I.mo17606b(), "{}"), this.f3455b.getLong(this.f3504J.mo17606b(), 0));
            return aVar;
        }
    }

    /* renamed from: h */
    public long mo17582h(long j) {
        return m5344a(this.f3505u.mo17606b(), j);
    }

    /* renamed from: i */
    public long mo17583i(long j) {
        return m5344a(this.f3496B.mo17606b(), j);
    }

    /* renamed from: a */
    public Boolean mo17565a(boolean z) {
        return Boolean.valueOf(this.f3455b.getBoolean(this.f3510z.mo17606b(), z));
    }

    /* renamed from: b */
    public CounterConfiguration.C1774a mo17569b() {
        return CounterConfiguration.C1774a.m3973a(this.f3455b.getInt(this.f3500F.mo17606b(), CounterConfiguration.C1774a.UNDEFINED.f2723d));
    }

    /* renamed from: a */
    public String mo17566a(String str) {
        return this.f3455b.getString(this.f3501G.mo17606b(), str);
    }

    /* renamed from: b */
    public String mo17570b(String str) {
        return this.f3455b.getString(this.f3502H.mo17606b(), str);
    }

    /* renamed from: a */
    public C2063df mo17564a(C1807a.C1808a aVar) {
        synchronized (this) {
            mo17546a(this.f3503I.mo17606b(), aVar.f2885a);
            mo17546a(this.f3504J.mo17606b(), Long.valueOf(aVar.f2886b));
        }
        return this;
    }

    /* renamed from: c */
    public C2063df mo17573c() {
        return (C2063df) mo17547h(this.f3502H.mo17606b());
    }

    /* renamed from: a */
    public void mo17567a(int i) {
        C2069dl.m5402a(this.f3455b, this.f3509y.mo17606b(), i);
    }

    /* renamed from: b */
    public void mo17571b(int i) {
        C2069dl.m5402a(this.f3455b, this.f3505u.mo17606b(), i);
    }

    /* renamed from: c */
    public void mo17574c(int i) {
        C2069dl.m5402a(this.f3455b, this.f3507w.mo17606b(), i);
    }

    /* renamed from: d */
    public void mo17576d() {
        SharedPreferences sharedPreferences = this.f3455b;
        String b = this.f3500F.mo17606b();
        if (sharedPreferences != null && sharedPreferences.contains(b)) {
            try {
                sharedPreferences.getBoolean(b, false);
                sharedPreferences.edit().remove(b).putInt(b, CounterConfiguration.C1774a.UNDEFINED.f2723d).apply();
            } catch (ClassCastException unused) {
            }
        }
    }

    /* renamed from: e */
    public C2063df mo17578e() {
        return (C2063df) mo17547h(this.f3500F.mo17606b());
    }

    /* renamed from: g */
    public boolean mo17581g() {
        return this.f3455b.contains(this.f3508x.mo17606b()) || this.f3455b.contains(this.f3509y.mo17606b()) || this.f3455b.contains(this.f3510z.mo17606b()) || this.f3455b.contains(this.f3505u.mo17606b()) || this.f3455b.contains(this.f3506v.mo17606b()) || this.f3455b.contains(this.f3507w.mo17606b()) || this.f3455b.contains(this.f3498D.mo17606b()) || this.f3455b.contains(this.f3496B.mo17606b()) || this.f3455b.contains(this.f3495A.mo17606b()) || this.f3455b.contains(this.f3497C.mo17606b()) || this.f3455b.contains(this.f3503I.mo17606b()) || this.f3455b.contains(this.f3500F.mo17606b()) || this.f3455b.contains(this.f3501G.mo17606b()) || this.f3455b.contains(this.f3502H.mo17606b()) || this.f3455b.contains(this.f3499E.mo17606b());
    }

    /* renamed from: l */
    public void mo17584l() {
        this.f3455b.edit().remove(this.f3498D.mo17606b()).remove(this.f3497C.mo17606b()).remove(this.f3495A.mo17606b()).remove(this.f3496B.mo17606b()).remove(this.f3508x.mo17606b()).remove(this.f3507w.mo17606b()).remove(this.f3506v.mo17606b()).remove(this.f3505u.mo17606b()).remove(this.f3510z.mo17606b()).remove(this.f3509y.mo17606b()).remove(this.f3501G.mo17606b()).remove(this.f3500F.mo17606b()).remove(this.f3503I.mo17606b()).remove(this.f3504J.mo17606b()).remove(this.f3502H.mo17606b()).remove(this.f3499E.mo17606b()).apply();
    }
}

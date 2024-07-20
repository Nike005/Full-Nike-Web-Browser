package com.tappx.p048a;

import android.content.SharedPreferences;

/* renamed from: com.tappx.a.q2 */
final class C1586q2 {

    /* renamed from: a */
    private final SharedPreferences f2226a;

    /* renamed from: com.tappx.a.q2$a */
    static /* synthetic */ class C1587a {

        /* renamed from: a */
        static final /* synthetic */ int[] f2227a;

        /* JADX WARNING: Can't wrap try/catch for region: R(12:0|1|2|3|4|5|6|7|8|9|10|12) */
        /* JADX WARNING: Code restructure failed: missing block: B:13:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                com.tappx.a.p2[] r0 = com.tappx.p048a.C1566p2.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                f2227a = r0
                com.tappx.a.p2 r1 = com.tappx.p048a.C1566p2.DENIED_DEVELOPER     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = f2227a     // Catch:{ NoSuchFieldError -> 0x001d }
                com.tappx.a.p2 r1 = com.tappx.p048a.C1566p2.DENIED_USER     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = f2227a     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.tappx.a.p2 r1 = com.tappx.p048a.C1566p2.GRANTED_DEVELOPER     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = f2227a     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.tappx.a.p2 r1 = com.tappx.p048a.C1566p2.GRANTED_USER     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = f2227a     // Catch:{ NoSuchFieldError -> 0x003e }
                com.tappx.a.p2 r1 = com.tappx.p048a.C1566p2.MISSING_ANSWER     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.tappx.p048a.C1586q2.C1587a.<clinit>():void");
        }
    }

    C1586q2(SharedPreferences sharedPreferences) {
        this.f2226a = sharedPreferences;
    }

    /* renamed from: c */
    private C1566p2 m3256c(int i) {
        if (i == -2) {
            return C1566p2.DENIED_DEVELOPER;
        }
        if (i == -1) {
            return C1566p2.DENIED_USER;
        }
        if (i == 1) {
            return C1566p2.GRANTED_USER;
        }
        if (i != 2) {
            return C1566p2.MISSING_ANSWER;
        }
        return C1566p2.GRANTED_DEVELOPER;
    }

    /* renamed from: a */
    public void mo16082a(Boolean bool, String str) {
        this.f2226a.edit().putInt("tappx_privacy_applies", m3254b(bool)).putString("tappx_privacy_consent_html", str).apply();
    }

    /* renamed from: d */
    public Boolean mo16091d() {
        return m3255b(this.f2226a.getInt("tappx_privacy_applies", 0));
    }

    /* renamed from: e */
    public long mo16092e() {
        return this.f2226a.getLong("tappx_consent_timestamp", -1);
    }

    /* renamed from: f */
    public String mo16093f() {
        return this.f2226a.getString("tappx_privacy_gdpr_consent", (String) null);
    }

    /* renamed from: g */
    public C1566p2 mo16094g() {
        return m3256c(this.f2226a.getInt("tappx_privacy_accepted", 0));
    }

    /* renamed from: h */
    public String mo16095h() {
        return this.f2226a.getString("tappx_mark_choice", (String) null);
    }

    /* renamed from: i */
    public String mo16096i() {
        return this.f2226a.getString("tappx_privacy_consent_html", (String) null);
    }

    /* renamed from: j */
    public int mo16097j() {
        return this.f2226a.getInt("tappx_privacy_version", 0);
    }

    /* renamed from: k */
    public String mo16098k() {
        return this.f2226a.getString("tappx_usprivacy_string", (String) null);
    }

    /* renamed from: l */
    public boolean mo16099l() {
        return this.f2226a.getBoolean("tappx_privacy_autoDisclaimer", false);
    }

    /* renamed from: m */
    public boolean mo16100m() {
        return this.f2226a.getBoolean("tappx_privacy_renew", false);
    }

    /* renamed from: b */
    private Boolean m3255b(int i) {
        return (i == -1 || i == 1) ? true : null;
    }

    /* renamed from: b */
    private int m3254b(Boolean bool) {
        if (bool == null) {
            return 0;
        }
        return bool.booleanValue() ? 1 : -1;
    }

    /* renamed from: a */
    public void mo16081a(Boolean bool) {
        this.f2226a.edit().putInt("tappx_privacy_applies", m3254b(bool)).apply();
    }

    /* renamed from: b */
    public void mo16085b() {
        this.f2226a.edit().remove("tappx_privacy_accepted").remove("tappx_sync_required").apply();
    }

    /* renamed from: c */
    public void mo16089c(boolean z) {
        if (z) {
            this.f2226a.edit().putBoolean("tappx_privacy_renew", true).apply();
        } else {
            this.f2226a.edit().remove("tappx_privacy_renew").apply();
        }
    }

    /* renamed from: b */
    private int m3253b(C1566p2 p2Var) {
        int i = C1587a.f2227a[p2Var.ordinal()];
        if (i == 1) {
            return -2;
        }
        if (i == 2) {
            return -1;
        }
        if (i != 3) {
            return i != 4 ? 0 : 1;
        }
        return 2;
    }

    /* renamed from: a */
    public void mo16080a(C1566p2 p2Var) {
        this.f2226a.edit().putInt("tappx_privacy_accepted", m3253b(p2Var)).apply();
    }

    /* renamed from: b */
    public void mo16086b(String str) {
        this.f2226a.edit().putString("tappx_privacy_gdpr_consent", str).apply();
    }

    /* renamed from: a */
    public void mo16084a(boolean z) {
        this.f2226a.edit().putBoolean("tappx_privacy_autoDisclaimer", z).apply();
    }

    /* renamed from: b */
    public void mo16087b(boolean z) {
        this.f2226a.edit().putBoolean("tappx_sync_required", z).apply();
    }

    /* renamed from: c */
    public void mo16088c(String str) {
        this.f2226a.edit().putString("tappx_usprivacy_string", str).apply();
    }

    /* renamed from: a */
    public void mo16077a() {
        this.f2226a.edit().remove("tappx_privacy_applies").remove("tappx_privacy_autoDisclaimer").remove("tappx_consent_timestamp").remove("tappx_privacy_renew").remove("tappx_privacy_consent_html").apply();
    }

    /* renamed from: c */
    public boolean mo16090c() {
        return this.f2226a.getBoolean("tappx_sync_required", false);
    }

    /* renamed from: a */
    public void mo16078a(int i) {
        this.f2226a.edit().putInt("tappx_privacy_version", i).apply();
    }

    /* renamed from: a */
    public void mo16079a(long j) {
        this.f2226a.edit().putLong("tappx_consent_timestamp", j).apply();
    }

    /* renamed from: a */
    public void mo16083a(String str) {
        this.f2226a.edit().putString("tappx_mark_choice", str).apply();
    }
}

package com.yandex.metrica.impl.p050ob;

import android.text.TextUtils;
import com.yandex.metrica.impl.utils.C2223g;
import java.util.List;

/* renamed from: com.yandex.metrica.impl.ob.bz */
public class C2014bz extends C2017cb {

    /* renamed from: a */
    static final C2068dk f3314a = new C2068dk("UUID");

    /* renamed from: b */
    static final C2068dk f3315b = new C2068dk("DEVICE_ID_POSSIBLE");

    /* renamed from: c */
    static final C2068dk f3316c = new C2068dk("DEVICE_ID");

    /* renamed from: d */
    static final C2068dk f3317d = new C2068dk("AD_URL_GET");

    /* renamed from: e */
    static final C2068dk f3318e = new C2068dk("AD_URL_REPORT");

    /* renamed from: f */
    static final C2068dk f3319f = new C2068dk("CUSTOM_HOSTS");

    /* renamed from: g */
    static final C2068dk f3320g = new C2068dk("SERVER_TIME_OFFSET");

    /* renamed from: h */
    static final C2068dk f3321h = new C2068dk("STARTUP_REQUEST_TIME");

    /* renamed from: i */
    static final C2068dk f3322i = new C2068dk("CLIDS");

    /* renamed from: j */
    static final C2068dk f3323j = new C2068dk("COOKIE_BROWSERS");

    /* renamed from: k */
    static final C2068dk f3324k = new C2068dk("BIND_ID_URL");

    /* renamed from: l */
    static final C2068dk f3325l = new C2068dk("REFERRER");

    /* renamed from: m */
    static final C2068dk f3326m = new C2068dk("DEFERRED_DEEP_LINK_WAS_CHECKED");

    /* renamed from: n */
    static final C2068dk f3327n = new C2068dk("API_LEVEL");

    public C2014bz(C2004bq bqVar) {
        super(bqVar);
    }

    /* renamed from: a */
    public String mo17327a(String str) {
        return mo17394b(f3314a.mo17606b(), str);
    }

    /* renamed from: b */
    public String mo17329b(String str) {
        return mo17394b(f3316c.mo17606b(), str);
    }

    /* renamed from: a */
    public String mo17326a() {
        return mo17394b(f3315b.mo17606b(), "");
    }

    /* renamed from: c */
    public String mo17333c(String str) {
        return mo17394b(f3317d.mo17606b(), str);
    }

    /* renamed from: d */
    public String mo17335d(String str) {
        return mo17394b(f3318e.mo17606b(), str);
    }

    /* renamed from: b */
    public List<String> mo17330b() {
        String b = mo17394b(f3319f.mo17606b(), (String) null);
        if (TextUtils.isEmpty(b)) {
            return null;
        }
        return C2223g.m5951b(b);
    }

    /* renamed from: a */
    public long mo17324a(long j) {
        return mo17393b(f3320g.mo17604a(), j);
    }

    /* renamed from: b */
    public long mo17328b(long j) {
        return mo17393b(f3321h.mo17606b(), j);
    }

    /* renamed from: e */
    public String mo17339e(String str) {
        return mo17394b(f3322i.mo17606b(), str);
    }

    /* renamed from: c */
    public long mo17331c(long j) {
        return mo17393b(f3327n.mo17606b(), j);
    }

    /* renamed from: c */
    public String mo17332c() {
        return mo17394b(f3325l.mo17606b(), (String) null);
    }

    /* renamed from: d */
    public boolean mo17336d() {
        return mo17395b(f3326m.mo17606b(), false);
    }

    /* renamed from: f */
    public C2014bz mo17341f(String str) {
        return (C2014bz) mo17390a(f3314a.mo17606b(), str);
    }

    /* renamed from: g */
    public C2014bz mo17342g(String str) {
        return (C2014bz) mo17390a(f3316c.mo17606b(), str);
    }

    /* renamed from: h */
    public C2014bz mo17343h(String str) {
        return (C2014bz) mo17390a(f3317d.mo17606b(), str);
    }

    /* renamed from: a */
    public C2014bz mo17325a(List<String> list) {
        return (C2014bz) mo17390a(f3319f.mo17606b(), C2223g.m5947a(list));
    }

    /* renamed from: i */
    public C2014bz mo17344i(String str) {
        return (C2014bz) mo17390a(f3318e.mo17606b(), str);
    }

    /* renamed from: d */
    public C2014bz mo17334d(long j) {
        return (C2014bz) mo17389a(f3320g.mo17606b(), j);
    }

    /* renamed from: e */
    public C2014bz mo17338e(long j) {
        return (C2014bz) mo17389a(f3321h.mo17606b(), j);
    }

    /* renamed from: j */
    public C2014bz mo17345j(String str) {
        return (C2014bz) mo17390a(f3322i.mo17606b(), str);
    }

    /* renamed from: f */
    public C2014bz mo17340f(long j) {
        return (C2014bz) mo17389a(f3327n.mo17606b(), j);
    }

    /* renamed from: k */
    public C2014bz mo17346k(String str) {
        return (C2014bz) mo17390a(f3315b.mo17606b(), str);
    }

    /* renamed from: l */
    public String mo17347l(String str) {
        return mo17394b(f3323j.mo17606b(), str);
    }

    /* renamed from: m */
    public C2014bz mo17348m(String str) {
        return (C2014bz) mo17390a(f3323j.mo17606b(), str);
    }

    /* renamed from: n */
    public String mo17349n(String str) {
        return mo17394b(f3324k.mo17606b(), str);
    }

    /* renamed from: o */
    public C2014bz mo17350o(String str) {
        return (C2014bz) mo17390a(f3324k.mo17606b(), str);
    }

    /* renamed from: p */
    public C2014bz mo17351p(String str) {
        return (C2014bz) mo17390a(f3325l.mo17606b(), str);
    }

    /* renamed from: e */
    public C2014bz mo17337e() {
        return (C2014bz) mo17391a(f3326m.mo17606b(), true);
    }
}

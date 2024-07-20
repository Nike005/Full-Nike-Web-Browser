package com.yandex.metrica.impl.p050ob;

import android.os.Bundle;
import android.text.TextUtils;
import com.yandex.metrica.IIdentifierCallback;
import com.yandex.metrica.impl.C1894bi;
import java.util.List;
import java.util.Map;

/* renamed from: com.yandex.metrica.impl.ob.dx */
public class C2097dx {

    /* renamed from: a */
    private String f3592a = this.f3601j.mo17327a((String) null);

    /* renamed from: b */
    private String f3593b;

    /* renamed from: c */
    private String f3594c = this.f3601j.mo17333c((String) null);

    /* renamed from: d */
    private String f3595d = this.f3601j.mo17335d((String) null);

    /* renamed from: e */
    private String f3596e = this.f3601j.mo17349n((String) null);

    /* renamed from: f */
    private List<String> f3597f = this.f3601j.mo17330b();

    /* renamed from: g */
    private long f3598g = this.f3601j.mo17324a(0);

    /* renamed from: h */
    private String f3599h = this.f3601j.mo17339e((String) null);

    /* renamed from: i */
    private String f3600i = this.f3601j.mo17347l((String) null);

    /* renamed from: j */
    private final C2014bz f3601j;

    /* renamed from: com.yandex.metrica.impl.ob.dx$a */
    public enum C2098a {
        IDENTIFIERS,
        URLS,
        ALL
    }

    public C2097dx(C2014bz bzVar, String str) {
        this.f3601j = bzVar;
        this.f3593b = bzVar.mo17329b((String) null);
        m5481b(str);
        m5485e();
    }

    /* renamed from: b */
    private void m5481b(String str) {
        if (C1894bi.m4622a(this.f3592a) && !C1894bi.m4622a(str)) {
            this.f3592a = str;
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public synchronized void mo17666a(Map<String, String> map) {
        mo17670b(map);
        mo17672c(map);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public synchronized boolean mo17668a(C2098a aVar) {
        if (C2098a.ALL == aVar) {
            return m5489h();
        } else if (C2098a.IDENTIFIERS == aVar) {
            return m5487f();
        } else if (C2098a.URLS != aVar) {
            return false;
        } else {
            return m5488g();
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public synchronized void mo17670b(Map<String, String> map) {
        if (!C1894bi.m4622a(this.f3592a)) {
            map.put(IIdentifierCallback.YANDEX_MOBILE_METRICA_UUID, this.f3592a);
        }
        if (!C1894bi.m4622a(this.f3593b)) {
            map.put(IIdentifierCallback.YANDEX_MOBILE_METRICA_DEVICE_ID, this.f3593b);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: c */
    public synchronized void mo17672c(Map<String, String> map) {
        if (!C1894bi.m4622a(this.f3594c)) {
            map.put(IIdentifierCallback.YANDEX_MOBILE_METRICA_GET_AD_URL, this.f3594c);
        }
        if (!C1894bi.m4622a(this.f3595d)) {
            map.put(IIdentifierCallback.YANDEX_MOBILE_METRICA_REPORT_AD_URL, this.f3595d);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public synchronized void mo17663a(Bundle bundle) {
        m5480b(bundle);
        m5482c(bundle);
        m5479b(bundle.getLong("ServerTimeOffset"));
        String string = bundle.getString("Clids");
        if (!C1894bi.m4622a(string)) {
            this.f3599h = string;
        }
        String string2 = bundle.getString("CookieBrowsers");
        if (!TextUtils.isEmpty(string2)) {
            this.f3600i = string2;
        }
        m5485e();
    }

    /* renamed from: e */
    private void m5485e() {
        this.f3601j.mo17341f(this.f3592a).mo17342g(this.f3593b).mo17343h(this.f3594c).mo17344i(this.f3595d).mo17334d(this.f3598g).mo17345j(this.f3599h).mo17348m(this.f3600i).mo17350o(this.f3596e).mo17398h();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo17662a(long j) {
        this.f3601j.mo17338e(j).mo17398h();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public List<String> mo17669b() {
        return this.f3597f;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo17665a(List<String> list) {
        this.f3597f = list;
        this.f3601j.mo17325a(list);
    }

    /* renamed from: f */
    private synchronized boolean m5487f() {
        return !C1894bi.m4624a(this.f3592a, this.f3593b);
    }

    /* renamed from: g */
    private synchronized boolean m5488g() {
        return !C1894bi.m4624a(this.f3594c);
    }

    /* renamed from: h */
    private synchronized boolean m5489h() {
        return m5487f() && m5488g();
    }

    /* renamed from: b */
    private synchronized void m5480b(Bundle bundle) {
        m5481b(bundle.getString("UuId"));
        String string = bundle.getString("DeviceId");
        if (!C1894bi.m4622a(string)) {
            mo17664a(string);
        }
    }

    /* renamed from: c */
    private synchronized void m5482c(Bundle bundle) {
        String string = bundle.getString("AdUrlGet");
        if (!TextUtils.isEmpty(string)) {
            m5483c(string);
        }
        String string2 = bundle.getString("AdUrlReport");
        if (!TextUtils.isEmpty(string2)) {
            m5484d(string2);
        }
        String string3 = bundle.getString("BindIdUrl");
        if (!TextUtils.isEmpty(string3)) {
            m5486e(string3);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public synchronized void mo17664a(String str) {
        this.f3593b = str;
    }

    /* renamed from: c */
    private synchronized void m5483c(String str) {
        this.f3594c = str;
    }

    /* renamed from: d */
    private synchronized void m5484d(String str) {
        this.f3595d = str;
    }

    /* renamed from: e */
    private synchronized void m5486e(String str) {
        this.f3596e = str;
    }

    /* renamed from: b */
    private synchronized void m5479b(long j) {
        this.f3598g = j;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: c */
    public String mo17671c() {
        return this.f3592a;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: d */
    public String mo17673d() {
        return this.f3593b;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public boolean mo17667a() {
        long currentTimeMillis = (System.currentTimeMillis() / 1000) - this.f3601j.mo17328b(0);
        return currentTimeMillis > 86400 || currentTimeMillis < 0;
    }
}

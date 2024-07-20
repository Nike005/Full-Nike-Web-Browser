package com.yandex.metrica.impl;

import android.content.Context;
import android.text.TextUtils;
import com.yandex.metrica.C1780b;
import com.yandex.metrica.impl.C2208p;
import com.yandex.metrica.impl.p050ob.C2096dw;
import com.yandex.metrica.impl.utils.C2221f;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/* renamed from: com.yandex.metrica.impl.b */
public abstract class C1876b implements C1780b {

    /* renamed from: a */
    public static final Collection<Integer> f3038a;

    /* renamed from: b */
    protected final C1864aw f3039b;

    /* renamed from: c */
    protected final C1868ay f3040c;

    /* renamed from: d */
    private C2239w f3041d;

    static {
        HashSet hashSet = new HashSet();
        hashSet.add(1);
        hashSet.add(2);
        hashSet.add(3);
        hashSet.add(4);
        hashSet.add(5);
        hashSet.add(6);
        hashSet.add(7);
        hashSet.add(8);
        hashSet.add(11);
        hashSet.add(12);
        hashSet.add(13);
        hashSet.add(16);
        hashSet.add(17);
        hashSet.add(18);
        hashSet.add(19);
        f3038a = Collections.unmodifiableCollection(hashSet);
    }

    C1876b(Context context, String str, C1868ay ayVar, C1864aw awVar) {
        context.getApplicationContext();
        this.f3040c = ayVar;
        this.f3039b = awVar;
        awVar.mo16887b().mo16567a(str);
        this.f3039b.mo16887b().mo16577c(context.getPackageName());
        this.f3039b.mo16884a(C2221f.C2222a.m5942d());
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo16938a() {
        this.f3040c.mo16905a(this.f3039b);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo16941a(C2096dw dwVar) {
        this.f3039b.mo16888b(dwVar);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo16940a(C1921j jVar) {
        this.f3039b.mo16882a(jVar);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo16942a(C2239w wVar) {
        this.f3041d = wVar;
    }

    /* renamed from: a */
    public void mo16943a(String str) {
        this.f3039b.mo16887b().mo16591g(str);
    }

    /* renamed from: a */
    public void mo16944a(String str, String str2) {
        if (!TextUtils.isEmpty(str)) {
            this.f3039b.mo16885a(str, str2);
        }
    }

    /* renamed from: a */
    public void mo16945a(Map<String, String> map) {
        if (!C1897bk.m4653a((Map) map)) {
            for (Map.Entry next : map.entrySet()) {
                mo16944a((String) next.getKey(), (String) next.getValue());
            }
        }
    }

    /* renamed from: b */
    public void mo16949b(Map<String, String> map) {
        if (!C1897bk.m4653a((Map) map)) {
            for (Map.Entry next : map.entrySet()) {
                mo16948b((String) next.getKey(), (String) next.getValue());
            }
        }
    }

    /* renamed from: b */
    public void mo16948b(String str, String str2) {
        if (!TextUtils.isEmpty(str)) {
            this.f3040c.mo16912a(str, str2, this.f3039b);
        }
    }

    /* renamed from: b */
    public void mo16946b() {
        this.f3040c.mo16918b(this.f3039b);
    }

    public void onResumeSession() {
        mo16947b((String) null);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public void mo16947b(String str) {
        this.f3040c.mo16923d();
        this.f3041d.mo17933b();
        this.f3040c.mo16906a(C2208p.m5874b(str), this.f3039b);
        if (this.f3039b.mo16891e()) {
            this.f3040c.mo16906a(C2208p.m5882d(C2208p.C2209a.EVENT_TYPE_PURGE_BUFFER), this.f3039b);
        }
    }

    public void onPauseSession() {
        mo16950c((String) null);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: c */
    public void mo16950c(String str) {
        if (!this.f3039b.mo16886a()) {
            this.f3040c.mo16924e();
            this.f3041d.mo17932a();
            this.f3040c.mo16906a(C2208p.m5878c(str), this.f3039b);
            this.f3039b.mo16890d();
        }
    }

    public void reportEvent(String str) {
        reportEvent(str, (String) null);
    }

    public void reportEvent(String str, String str2) {
        C1897bk.m4646a((Object) str, "Event Name");
        m4433a(C2208p.m5870a(str, str2));
    }

    public void reportEvent(String str, Map<String, Object> map) {
        C1897bk.m4646a((Object) str, "Event Name");
        this.f3040c.mo16903a(C2208p.m5869a(str), mo16952d(), (Map<String, Object>) C1897bk.m4653a((Map) map) ? null : new HashMap(map));
    }

    /* renamed from: a */
    public void mo16939a(int i, String str, String str2, Map<String, String> map) {
        HashMap hashMap;
        if (!f3038a.contains(Integer.valueOf(i))) {
            if (map == null) {
                hashMap = null;
            } else {
                hashMap = new HashMap(map);
            }
            m4433a(C2208p.m5865a(i, str, str2, hashMap));
        }
    }

    public void reportError(String str, Throwable th) {
        C1897bk.m4646a((Object) str, "Message");
        m4433a(C2208p.m5875b(str, C1897bk.m4640a((String) null, th)));
    }

    public void setSessionTimeout(int i) {
        this.f3039b.mo16887b().mo16576c(i);
    }

    public void reportUnhandledException(Throwable th) {
        C1897bk.m4646a((Object) th, "Exception");
        this.f3040c.mo16913a(th, this.f3039b);
    }

    /* renamed from: d */
    public void mo16953d(String str) {
        C1897bk.m4646a((Object) str, "Native Crash");
        this.f3040c.mo16911a(str, this.f3039b);
    }

    /* renamed from: a */
    public void mo16674a(int i) {
        this.f3039b.mo16887b().mo16572b(i);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: c */
    public boolean mo16951c() {
        boolean z = !mo16954e();
        if (z) {
            this.f3040c.mo16906a(C2208p.m5878c(C2208p.C2209a.EVENT_TYPE_ALIVE.mo17885b()), this.f3039b);
        }
        return z;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: d */
    public C1864aw mo16952d() {
        return this.f3039b;
    }

    /* renamed from: a */
    private void m4433a(C1915h hVar) {
        this.f3040c.mo16906a(hVar, this.f3039b);
    }

    /* renamed from: e */
    public boolean mo16954e() {
        return this.f3039b.mo16886a();
    }
}

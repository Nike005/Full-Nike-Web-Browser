package com.yandex.metrica.impl;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.location.Location;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import com.yandex.metrica.C1797e;
import com.yandex.metrica.impl.p050ob.C2096dw;
import com.yandex.metrica.impl.utils.C2228j;
import java.util.Map;

/* renamed from: com.yandex.metrica.impl.z */
public class C2243z extends C1876b implements C1811ac {
    C2243z(Context context, C1797e eVar, C1868ay ayVar) {
        super(context, eVar.getApiKey(), ayVar, new C1864aw());
        this.f3039b.mo16881a(new C1840an(eVar.getPreloadInfo()));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo16941a(C2096dw dwVar) {
        super.mo16941a(dwVar);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo16940a(C1921j jVar) {
        super.mo16940a(jVar);
    }

    public void reportEvent(String str) {
        super.reportEvent(str);
    }

    public void reportEvent(String str, String str2) {
        super.reportEvent(str, str2);
        m5997c(str, str2);
    }

    public void reportEvent(String str, Map<String, Object> map) {
        String str2;
        super.reportEvent(str, map);
        if (map == null) {
            str2 = null;
        } else {
            str2 = map.toString();
        }
        m5997c(str, str2);
    }

    /* renamed from: c */
    private static void m5997c(String str, String str2) {
        StringBuilder sb = new StringBuilder("Event received: ");
        sb.append(str);
        if (!TextUtils.isEmpty(str2)) {
            sb.append(". With value: ");
            sb.append(str2);
        }
        C2228j.m5960f().mo17902a(sb.toString());
    }

    public void reportError(String str, Throwable th) {
        super.reportError(str, th);
        C2228j.m5960f().mo17903a("Error received: %s", str);
    }

    /* renamed from: a */
    public void mo17947a(Activity activity) {
        Uri data;
        if (activity == null) {
            C2228j.m5960f().mo17904b("Null activity parameter for reportAppOpen(Activity)");
        } else if (activity.getIntent() != null && (data = activity.getIntent().getData()) != null) {
            this.f3040c.mo16906a(C2208p.m5866a(data), this.f3039b);
        }
    }

    /* renamed from: e */
    public void mo17954e(String str) {
        if (TextUtils.isEmpty(str)) {
            C2228j.m5960f().mo17904b("Null or empty deeplink value for reportAppOpen(String) was ignored.");
        } else {
            this.f3040c.mo16906a(C2208p.m5884e(str), this.f3039b);
        }
    }

    /* renamed from: a */
    public void mo17948a(Application application) {
        C1897bk.m4646a((Object) application, "Application");
        if (Build.VERSION.SDK_INT >= 14) {
            C2228j.m5960f().mo17902a("Enable activity auto tracking");
            application.registerActivityLifecycleCallbacks(new C1927m(this));
            return;
        }
        C2228j.m5960f().mo17904b("Could not enable activity auto tracking. API level should be more than 14 (ICE_CREAM_SANDWICH)");
    }

    /* renamed from: b */
    public void mo17950b(Activity activity) {
        mo16947b(mo17953d(activity));
    }

    /* renamed from: c */
    public void mo17951c(Activity activity) {
        mo16950c(mo17953d(activity));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: d */
    public String mo17953d(Activity activity) {
        if (activity != null) {
            return activity.getClass().getSimpleName();
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo17949a(C1797e eVar, boolean z) {
        this.f3039b.mo16887b().mo16566a(eVar);
        mo16769d(this.f3039b.mo16887b().mo16599l());
        if (z) {
            mo16946b();
        }
        mo16949b(eVar.mo16708j());
        mo16945a(eVar.getErrorEnvironment());
    }

    /* renamed from: c */
    public void mo17952c(boolean z) {
        this.f3039b.mo16887b().mo16570a(z);
    }

    /* renamed from: d */
    public void mo16769d(boolean z) {
        this.f3040c.mo16916a(z, this.f3039b);
    }

    /* renamed from: a */
    public void mo16764a(Location location) {
        this.f3039b.mo16887b().mo16560a(location);
    }

    /* renamed from: b */
    public void mo16768b(boolean z) {
        this.f3039b.mo16887b().mo16578c(z);
    }

    /* renamed from: b */
    public void mo16948b(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            C2228j.m5960f().mo17905b("Invalid App Environment (key,value) pair: (%s,%s).", str, str2);
            return;
        }
        super.mo16948b(str, str2);
    }

    /* renamed from: a */
    public void mo16944a(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            C2228j.m5960f().mo17905b("Invalid Error Environment (key,value) pair: (%s,%s).", str, str2);
            return;
        }
        super.mo16944a(str, str2);
    }

    /* renamed from: a */
    public void mo16767a(boolean z) {
        this.f3039b.mo16887b().mo16582d(z);
        this.f3040c.mo16906a(C2208p.m5864a(), this.f3039b);
    }

    /* renamed from: h */
    public boolean mo16770h() {
        return this.f3039b.mo16887b().mo16604q();
    }

    /* renamed from: f */
    public boolean mo17955f() {
        return this.f3039b.mo16887b().mo16598k();
    }
}

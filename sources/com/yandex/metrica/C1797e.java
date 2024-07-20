package com.yandex.metrica;

import android.location.Location;
import android.text.TextUtils;
import com.yandex.metrica.YandexMetricaConfig;
import com.yandex.metrica.impl.C1897bk;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/* renamed from: com.yandex.metrica.e */
public class C1797e extends YandexMetricaConfig {

    /* renamed from: a */
    private final C1779a f2840a;

    /* renamed from: b */
    private final Map<String, String> f2841b;

    /* renamed from: c */
    private final String f2842c;

    /* renamed from: d */
    private final String f2843d;

    /* renamed from: e */
    private final List<String> f2844e;

    /* renamed from: f */
    private final Integer f2845f;

    /* renamed from: g */
    private final Integer f2846g;

    /* renamed from: h */
    private final Integer f2847h;

    /* renamed from: i */
    private final Map<String, String> f2848i;

    /* renamed from: j */
    private final Boolean f2849j;

    /* renamed from: k */
    private final Boolean f2850k;

    /* synthetic */ C1797e(C1798a aVar, byte b) {
        this(aVar);
    }

    public C1797e(YandexMetricaConfig yandexMetricaConfig) {
        super(yandexMetricaConfig);
        this.f2840a = null;
        this.f2841b = null;
        this.f2843d = null;
        this.f2845f = null;
        this.f2846g = null;
        this.f2847h = null;
        this.f2842c = null;
        this.f2848i = null;
        this.f2849j = null;
        this.f2850k = null;
        this.f2844e = null;
    }

    /* renamed from: a */
    static C1797e m4051a(YandexMetricaConfig yandexMetricaConfig) {
        if (yandexMetricaConfig instanceof C1797e) {
            return (C1797e) yandexMetricaConfig;
        }
        return new C1797e(yandexMetricaConfig);
    }

    /* renamed from: a */
    public static C1798a m4050a(String str) {
        return new C1798a(str);
    }

    /* renamed from: b */
    static C1798a m4052b(YandexMetricaConfig yandexMetricaConfig) {
        C1798a a = m4050a(yandexMetricaConfig.getApiKey());
        boolean z = false;
        if (yandexMetricaConfig.getAppVersion() != null) {
            a.mo16716a(yandexMetricaConfig.getAppVersion());
        }
        if (yandexMetricaConfig.getSessionTimeout() != null) {
            a.mo16712a(yandexMetricaConfig.getSessionTimeout().intValue());
        }
        if (yandexMetricaConfig.isReportCrashEnabled() != null) {
            a.mo16720a(yandexMetricaConfig.isReportCrashEnabled().booleanValue());
        }
        if (yandexMetricaConfig.isReportNativeCrashEnabled() != null) {
            a.mo16724b(yandexMetricaConfig.isReportNativeCrashEnabled().booleanValue());
        }
        if (yandexMetricaConfig.getLocation() != null) {
            a.mo16713a(yandexMetricaConfig.getLocation());
        }
        if (yandexMetricaConfig.isTrackLocationEnabled() != null) {
            a.mo16728c(yandexMetricaConfig.isTrackLocationEnabled().booleanValue());
        }
        if (yandexMetricaConfig.isCollectInstalledApps() != null) {
            a.mo16731d(yandexMetricaConfig.isCollectInstalledApps().booleanValue());
        }
        if ((yandexMetricaConfig.isLogEnabled() != null) && yandexMetricaConfig.isLogEnabled().booleanValue()) {
            a.mo16711a();
        }
        if (yandexMetricaConfig.getPreloadInfo() != null) {
            a.mo16714a(yandexMetricaConfig.getPreloadInfo());
        }
        if (yandexMetricaConfig.getErrorEnvironment() != null) {
            for (Map.Entry next : yandexMetricaConfig.getErrorEnvironment().entrySet()) {
                a.mo16717a((String) next.getKey(), (String) next.getValue());
            }
        }
        if (yandexMetricaConfig.isFirstActivationAsUpdate()) {
            a.mo16732e(true);
        }
        if (yandexMetricaConfig instanceof C1797e) {
            C1797e eVar = (C1797e) yandexMetricaConfig;
            if (eVar.mo16700b() != null) {
                a.mo16730d(eVar.mo16700b());
            }
            if (eVar.mo16701c() != null) {
                z = true;
            }
            if (z) {
                a.mo16718a(eVar.mo16701c());
            }
        }
        return a;
    }

    /* renamed from: com.yandex.metrica.e$a */
    public static final class C1798a {

        /* renamed from: a */
        public String f2851a;

        /* renamed from: b */
        Boolean f2852b;
        /* access modifiers changed from: private */

        /* renamed from: c */
        public YandexMetricaConfig.Builder f2853c;
        /* access modifiers changed from: private */

        /* renamed from: d */
        public C1779a f2854d;
        /* access modifiers changed from: private */

        /* renamed from: e */
        public String f2855e;
        /* access modifiers changed from: private */

        /* renamed from: f */
        public String f2856f;
        /* access modifiers changed from: private */

        /* renamed from: g */
        public List<String> f2857g;
        /* access modifiers changed from: private */

        /* renamed from: h */
        public Integer f2858h;
        /* access modifiers changed from: private */

        /* renamed from: i */
        public Map<String, String> f2859i;
        /* access modifiers changed from: private */

        /* renamed from: j */
        public Integer f2860j;
        /* access modifiers changed from: private */

        /* renamed from: k */
        public Integer f2861k;
        /* access modifiers changed from: private */

        /* renamed from: l */
        public Map<String, String> f2862l = new HashMap();
        /* access modifiers changed from: private */

        /* renamed from: m */
        public Boolean f2863m;

        protected C1798a(String str) {
            this.f2853c = YandexMetricaConfig.newConfigBuilder(str);
        }

        /* renamed from: a */
        public C1798a mo16716a(String str) {
            this.f2853c.setAppVersion(str);
            return this;
        }

        /* renamed from: a */
        public C1798a mo16712a(int i) {
            this.f2853c.setSessionTimeout(i);
            return this;
        }

        /* renamed from: b */
        public C1798a mo16722b(String str) {
            this.f2851a = str;
            return this;
        }

        /* renamed from: a */
        public C1798a mo16720a(boolean z) {
            this.f2853c.setReportCrashesEnabled(z);
            return this;
        }

        /* renamed from: b */
        public C1798a mo16724b(boolean z) {
            this.f2853c.setReportNativeCrashesEnabled(z);
            return this;
        }

        /* renamed from: a */
        public C1798a mo16711a() {
            this.f2853c.setLogEnabled();
            return this;
        }

        /* renamed from: a */
        public C1798a mo16713a(Location location) {
            this.f2853c.setLocation(location);
            return this;
        }

        /* renamed from: c */
        public C1798a mo16728c(boolean z) {
            this.f2853c.setTrackLocationEnabled(z);
            return this;
        }

        /* renamed from: d */
        public C1798a mo16731d(boolean z) {
            this.f2853c.setCollectInstalledApps(z);
            return this;
        }

        /* renamed from: a */
        public C1798a mo16717a(String str, String str2) {
            this.f2853c.putErrorEnvironmentValue(str, str2);
            return this;
        }

        /* renamed from: a */
        public C1798a mo16715a(C1779a aVar) {
            this.f2854d = aVar;
            return this;
        }

        /* renamed from: c */
        public C1798a mo16727c(String str) {
            this.f2855e = str;
            return this;
        }

        /* renamed from: d */
        public C1798a mo16730d(String str) {
            C1897bk.m4648a(str, "Custom Host URL");
            this.f2856f = str;
            return this;
        }

        /* renamed from: a */
        public C1798a mo16718a(List<String> list) {
            this.f2857g = list;
            return this;
        }

        /* renamed from: b */
        public C1798a mo16721b(int i) {
            if (i >= 0) {
                this.f2858h = Integer.valueOf(i);
                return this;
            }
            throw new IllegalArgumentException(String.format(Locale.US, "Invalid %1$s. %1$s should be positive.", new Object[]{"App Build Number"}));
        }

        /* renamed from: a */
        public C1798a mo16719a(Map<String, String> map, Boolean bool) {
            this.f2863m = bool;
            this.f2859i = map;
            return this;
        }

        /* renamed from: c */
        public C1798a mo16726c(int i) {
            this.f2861k = Integer.valueOf(i);
            return this;
        }

        /* renamed from: d */
        public C1798a mo16729d(int i) {
            this.f2860j = Integer.valueOf(i);
            return this;
        }

        /* renamed from: a */
        public C1798a mo16714a(PreloadInfo preloadInfo) {
            this.f2853c.setPreloadInfo(preloadInfo);
            return this;
        }

        /* renamed from: e */
        public C1798a mo16732e(boolean z) {
            this.f2853c.handleFirstActivationAsUpdate(z);
            return this;
        }

        /* renamed from: b */
        public C1798a mo16723b(String str, String str2) {
            this.f2862l.put(str, str2);
            return this;
        }

        /* renamed from: b */
        public C1797e mo16725b() {
            return new C1797e(this, (byte) 0);
        }
    }

    private C1797e(C1798a aVar) {
        super(aVar.f2853c);
        List list;
        this.f2843d = aVar.f2855e;
        this.f2845f = aVar.f2858h;
        List<String> list2 = null;
        if (aVar.f2857g != null && !aVar.f2857g.isEmpty()) {
            list = aVar.f2857g;
        } else if (!TextUtils.isEmpty(aVar.f2856f)) {
            list = new ArrayList();
            list.add(aVar.f2856f);
        } else {
            list = null;
        }
        this.f2844e = list != null ? Collections.unmodifiableList(list) : list2;
        this.f2840a = aVar.f2854d;
        this.f2841b = aVar.f2859i;
        this.f2847h = aVar.f2861k;
        this.f2846g = aVar.f2860j;
        this.f2842c = aVar.f2851a;
        this.f2848i = aVar.f2862l;
        this.f2849j = aVar.f2863m;
        this.f2850k = aVar.f2852b;
    }

    /* renamed from: a */
    public String mo16699a() {
        return this.f2843d;
    }

    /* renamed from: b */
    public String mo16700b() {
        List<String> list = this.f2844e;
        if (list == null || list.isEmpty()) {
            return null;
        }
        return this.f2844e.iterator().next();
    }

    /* renamed from: c */
    public List<String> mo16701c() {
        return this.f2844e;
    }

    /* renamed from: d */
    public Integer mo16702d() {
        return this.f2845f;
    }

    /* renamed from: e */
    public C1779a mo16703e() {
        return this.f2840a;
    }

    /* renamed from: f */
    public Map<String, String> mo16704f() {
        return this.f2841b;
    }

    /* renamed from: g */
    public String mo16705g() {
        return this.f2842c;
    }

    /* renamed from: h */
    public Integer mo16706h() {
        return this.f2847h;
    }

    /* renamed from: i */
    public Integer mo16707i() {
        return this.f2846g;
    }

    /* renamed from: j */
    public Map<String, String> mo16708j() {
        return this.f2848i;
    }

    /* renamed from: k */
    public Boolean mo16709k() {
        return this.f2849j;
    }

    /* renamed from: l */
    public Boolean mo16710l() {
        return this.f2850k;
    }
}

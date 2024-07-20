package com.yandex.metrica;

import android.location.Location;
import com.yandex.metrica.impl.C1810ab;
import com.yandex.metrica.impl.C1897bk;
import java.util.HashMap;
import java.util.Map;

public class YandexMetricaConfig {

    /* renamed from: a */
    private final String f2735a;

    /* renamed from: b */
    private final String f2736b;

    /* renamed from: c */
    private final Integer f2737c;

    /* renamed from: d */
    private final Boolean f2738d;

    /* renamed from: e */
    private final Boolean f2739e;

    /* renamed from: f */
    private final Location f2740f;

    /* renamed from: g */
    private final Boolean f2741g;

    /* renamed from: h */
    private final Boolean f2742h;

    /* renamed from: i */
    private final Boolean f2743i;

    /* renamed from: j */
    private final PreloadInfo f2744j;

    /* renamed from: k */
    private final Map<String, String> f2745k;

    /* renamed from: l */
    private final boolean f2746l;

    public static Builder newConfigBuilder(String str) {
        return new Builder(str);
    }

    public static YandexMetricaConfig fromJson(String str) {
        return new C1810ab().mo16762a(str);
    }

    public static class Builder {
        /* access modifiers changed from: private */

        /* renamed from: a */
        public final String f2747a;
        /* access modifiers changed from: private */

        /* renamed from: b */
        public String f2748b;
        /* access modifiers changed from: private */

        /* renamed from: c */
        public Integer f2749c;
        /* access modifiers changed from: private */

        /* renamed from: d */
        public Boolean f2750d;
        /* access modifiers changed from: private */

        /* renamed from: e */
        public Boolean f2751e;
        /* access modifiers changed from: private */

        /* renamed from: f */
        public Location f2752f;
        /* access modifiers changed from: private */

        /* renamed from: g */
        public Boolean f2753g;
        /* access modifiers changed from: private */

        /* renamed from: h */
        public Boolean f2754h;
        /* access modifiers changed from: private */

        /* renamed from: i */
        public Boolean f2755i;
        /* access modifiers changed from: private */

        /* renamed from: j */
        public PreloadInfo f2756j;
        /* access modifiers changed from: private */

        /* renamed from: k */
        public Map<String, String> f2757k = new HashMap();
        /* access modifiers changed from: private */

        /* renamed from: l */
        public boolean f2758l;

        protected Builder(String str) {
            C1897bk.m4647a(str);
            this.f2747a = str;
        }

        public Builder setAppVersion(String str) {
            C1897bk.m4648a(str, "App Version");
            this.f2748b = str;
            return this;
        }

        public Builder setSessionTimeout(int i) {
            this.f2749c = Integer.valueOf(i);
            return this;
        }

        public Builder setReportCrashesEnabled(boolean z) {
            this.f2750d = Boolean.valueOf(z);
            return this;
        }

        public Builder setReportNativeCrashesEnabled(boolean z) {
            this.f2751e = Boolean.valueOf(z);
            return this;
        }

        public Builder setLogEnabled() {
            this.f2755i = true;
            return this;
        }

        public Builder setLocation(Location location) {
            this.f2752f = location;
            return this;
        }

        public Builder setTrackLocationEnabled(boolean z) {
            this.f2753g = Boolean.valueOf(z);
            return this;
        }

        public Builder setCollectInstalledApps(boolean z) {
            this.f2754h = Boolean.valueOf(z);
            return this;
        }

        public Builder setPreloadInfo(PreloadInfo preloadInfo) {
            this.f2756j = preloadInfo;
            return this;
        }

        public Builder putErrorEnvironmentValue(String str, String str2) {
            this.f2757k.put(str, str2);
            return this;
        }

        public Builder handleFirstActivationAsUpdate(boolean z) {
            this.f2758l = z;
            return this;
        }

        public YandexMetricaConfig build() {
            return new YandexMetricaConfig(this);
        }
    }

    protected YandexMetricaConfig(Builder builder) {
        this.f2735a = builder.f2747a;
        this.f2736b = builder.f2748b;
        this.f2737c = builder.f2749c;
        this.f2738d = builder.f2750d;
        this.f2739e = builder.f2751e;
        this.f2740f = builder.f2752f;
        this.f2741g = builder.f2753g;
        this.f2742h = builder.f2754h;
        this.f2743i = builder.f2755i;
        this.f2744j = builder.f2756j;
        this.f2745k = builder.f2757k;
        this.f2746l = builder.f2758l;
    }

    protected YandexMetricaConfig(YandexMetricaConfig yandexMetricaConfig) {
        this.f2735a = yandexMetricaConfig.f2735a;
        this.f2736b = yandexMetricaConfig.f2736b;
        this.f2737c = yandexMetricaConfig.f2737c;
        this.f2738d = yandexMetricaConfig.f2738d;
        this.f2739e = yandexMetricaConfig.f2739e;
        this.f2740f = yandexMetricaConfig.f2740f;
        this.f2741g = yandexMetricaConfig.f2741g;
        this.f2742h = yandexMetricaConfig.f2742h;
        this.f2743i = yandexMetricaConfig.f2743i;
        this.f2744j = yandexMetricaConfig.f2744j;
        this.f2745k = yandexMetricaConfig.f2745k;
        this.f2746l = yandexMetricaConfig.f2746l;
    }

    public String getApiKey() {
        return this.f2735a;
    }

    public String getAppVersion() {
        return this.f2736b;
    }

    public Integer getSessionTimeout() {
        return this.f2737c;
    }

    public Boolean isReportCrashEnabled() {
        return this.f2738d;
    }

    public Boolean isReportNativeCrashEnabled() {
        return this.f2739e;
    }

    public Location getLocation() {
        return this.f2740f;
    }

    public Boolean isTrackLocationEnabled() {
        return this.f2741g;
    }

    public Boolean isLogEnabled() {
        return this.f2743i;
    }

    public Boolean isCollectInstalledApps() {
        return this.f2742h;
    }

    public PreloadInfo getPreloadInfo() {
        return this.f2744j;
    }

    public Map<String, String> getErrorEnvironment() {
        return this.f2745k;
    }

    public boolean isFirstActivationAsUpdate() {
        return this.f2746l;
    }

    public String toJson() {
        return new C1810ab().mo16763a(this);
    }
}

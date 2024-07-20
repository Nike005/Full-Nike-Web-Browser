package com.yandex.metrica;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class PreloadInfo {

    /* renamed from: a */
    private String f2731a;

    /* renamed from: b */
    private Map<String, String> f2732b;

    /* synthetic */ PreloadInfo(Builder builder, byte b) {
        this(builder);
    }

    public static class Builder {
        /* access modifiers changed from: private */

        /* renamed from: a */
        public String f2733a;
        /* access modifiers changed from: private */

        /* renamed from: b */
        public Map<String, String> f2734b;

        /* synthetic */ Builder(String str, byte b) {
            this(str);
        }

        private Builder(String str) {
            this.f2733a = str;
            this.f2734b = new HashMap();
        }

        public Builder setAdditionalParams(String str, String str2) {
            if (!(str == null || str2 == null)) {
                this.f2734b.put(str, str2);
            }
            return this;
        }

        public PreloadInfo build() {
            return new PreloadInfo(this, (byte) 0);
        }
    }

    private PreloadInfo(Builder builder) {
        this.f2731a = builder.f2733a;
        this.f2732b = Collections.unmodifiableMap(builder.f2734b);
    }

    public static Builder newBuilder(String str) {
        return new Builder(str, (byte) 0);
    }

    public String getTrackingId() {
        return this.f2731a;
    }

    public Map<String, String> getAdditionalParams() {
        return this.f2732b;
    }
}

package com.yandex.metrica.impl.p050ob;

import android.content.pm.FeatureInfo;

/* renamed from: com.yandex.metrica.impl.ob.cl */
public abstract class C2036cl {
    /* access modifiers changed from: protected */
    /* renamed from: a */
    public abstract C2039cm mo17496a(FeatureInfo featureInfo);

    /* renamed from: b */
    public C2039cm mo17497b(FeatureInfo featureInfo) {
        if (featureInfo.name != null) {
            return mo17496a(featureInfo);
        }
        if (featureInfo.reqGlEsVersion == 0) {
            return mo17496a(featureInfo);
        }
        return new C2039cm("openGlFeature", featureInfo.reqGlEsVersion, mo17498c(featureInfo));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: c */
    public boolean mo17498c(FeatureInfo featureInfo) {
        return (featureInfo.flags & 1) != 0;
    }

    /* renamed from: com.yandex.metrica.impl.ob.cl$a */
    public static class C2037a extends C2036cl {
        /* renamed from: a */
        public C2039cm mo17496a(FeatureInfo featureInfo) {
            return new C2039cm(featureInfo.name, featureInfo.version, mo17498c(featureInfo));
        }
    }

    /* renamed from: com.yandex.metrica.impl.ob.cl$b */
    public static class C2038b extends C2036cl {
        /* renamed from: a */
        public C2039cm mo17496a(FeatureInfo featureInfo) {
            return new C2039cm(featureInfo.name, mo17498c(featureInfo));
        }
    }
}

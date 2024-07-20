package com.startapp.android.publish.adsCommon.p028a;

import java.io.Serializable;
import org.apache.commons.lang3.StringUtils;

/* renamed from: com.startapp.android.publish.adsCommon.a.f */
/* compiled from: StartAppSDK */
public class C1073f implements Serializable {
    private static final long serialVersionUID = 1;
    private String reason;
    private boolean shouldDisplayAd;

    public C1073f(boolean z, String str) {
        this.shouldDisplayAd = z;
        this.reason = str;
    }

    public C1073f(boolean z) {
        this(z, "");
    }

    /* renamed from: a */
    public boolean mo14664a() {
        return this.shouldDisplayAd;
    }

    /* renamed from: b */
    public String mo14665b() {
        return this.reason;
    }

    /* renamed from: c */
    public String mo14666c() {
        String str = this.reason;
        return str != null ? str.split(StringUtils.SPACE)[0] : "";
    }
}

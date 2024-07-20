package com.startapp.android.publish.adsCommon.p040i;

import android.os.Bundle;

/* renamed from: com.startapp.android.publish.adsCommon.i.b */
/* compiled from: StartAppSDK */
public class C1160b {

    /* renamed from: a */
    private final Bundle f1234a;

    public C1160b(Bundle bundle) {
        this.f1234a = bundle;
    }

    /* renamed from: a */
    public String mo14953a() {
        return this.f1234a.getString("install_referrer");
    }

    /* renamed from: b */
    public long mo14954b() {
        return this.f1234a.getLong("referrer_click_timestamp_seconds");
    }

    /* renamed from: c */
    public long mo14955c() {
        return this.f1234a.getLong("install_begin_timestamp_seconds");
    }
}

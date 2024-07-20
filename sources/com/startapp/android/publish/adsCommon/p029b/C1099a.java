package com.startapp.android.publish.adsCommon.p029b;

import java.io.Serializable;

/* renamed from: com.startapp.android.publish.adsCommon.b.a */
/* compiled from: StartAppSDK */
public class C1099a implements Serializable {
    private static final long serialVersionUID = 1;
    private int adAttempt = 0;
    private boolean appPresence = false;
    private boolean isShown = true;
    private int minAppVersion = 0;
    private String packageName;
    private String trackingUrl;

    public C1099a(String str, String str2, int i, int i2) {
        this.trackingUrl = str;
        this.packageName = str2;
        this.adAttempt = i;
        this.minAppVersion = i2;
    }

    /* renamed from: a */
    public String mo14787a() {
        return this.trackingUrl;
    }

    /* renamed from: a */
    public void mo14788a(String str) {
        this.trackingUrl = str;
    }

    /* renamed from: b */
    public String mo14790b() {
        return this.packageName;
    }

    /* renamed from: c */
    public boolean mo14792c() {
        return this.isShown;
    }

    /* renamed from: a */
    public void mo14789a(boolean z) {
        this.isShown = z;
    }

    /* renamed from: d */
    public boolean mo14793d() {
        return this.appPresence;
    }

    /* renamed from: b */
    public void mo14791b(boolean z) {
        this.appPresence = z;
    }

    /* renamed from: e */
    public int mo14794e() {
        return this.minAppVersion;
    }
}

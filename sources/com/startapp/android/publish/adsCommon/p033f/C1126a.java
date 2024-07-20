package com.startapp.android.publish.adsCommon.p033f;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

/* renamed from: com.startapp.android.publish.adsCommon.f.a */
/* compiled from: StartAppSDK */
public class C1126a implements Serializable {

    /* renamed from: a */
    private static final String f1154a = "https://imp.startappservice.com/tracking/infoEvent";
    private static final long serialVersionUID = 1;
    private boolean dns = false;
    public String hostPeriodic;
    public String hostSecured;
    private int retryNum = 3;
    private int retryTime = 10;
    private boolean sendHopsOnFirstSucceededSmartRedirect = false;
    private float succeededSmartRedirectInfoProbability = 0.01f;

    public C1126a() {
        String str = f1154a;
        this.hostSecured = str;
        this.hostPeriodic = str;
    }

    /* renamed from: a */
    public String mo14865a() {
        return this.hostSecured;
    }

    /* renamed from: b */
    public String mo14866b() {
        String str = this.hostPeriodic;
        return str != null ? str : f1154a;
    }

    /* renamed from: c */
    public boolean mo14867c() {
        return this.dns;
    }

    /* renamed from: d */
    public int mo14868d() {
        return this.retryNum;
    }

    /* renamed from: e */
    public long mo14869e() {
        return TimeUnit.SECONDS.toMillis((long) this.retryTime);
    }

    /* renamed from: f */
    public float mo14870f() {
        return this.succeededSmartRedirectInfoProbability;
    }

    /* renamed from: g */
    public boolean mo14871g() {
        return this.sendHopsOnFirstSucceededSmartRedirect;
    }
}

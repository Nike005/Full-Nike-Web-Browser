package com.startapp.android.publish.adsCommon;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Process;
import android.util.Pair;
import com.startapp.android.publish.adsCommon.C1040Ad;
import com.startapp.android.publish.adsCommon.adListeners.AdEventListener;
import com.startapp.android.publish.adsCommon.adListeners.C1080b;
import com.startapp.android.publish.adsCommon.p033f.C1130d;
import com.startapp.android.publish.adsCommon.p033f.C1132f;
import com.startapp.android.publish.common.model.AdPreferences;
import com.startapp.android.publish.common.model.GetAdRequest;
import com.startapp.common.C1303g;
import com.startapp.common.p043a.C1270g;

/* renamed from: com.startapp.android.publish.adsCommon.d */
/* compiled from: StartAppSDK */
public abstract class C1113d {

    /* renamed from: a */
    protected final Context f1133a;
    /* access modifiers changed from: protected */

    /* renamed from: b */
    public final C1040Ad f1134b;

    /* renamed from: c */
    protected final AdPreferences f1135c;
    /* access modifiers changed from: protected */

    /* renamed from: d */
    public final AdEventListener f1136d;

    /* renamed from: e */
    protected AdPreferences.Placement f1137e;

    /* renamed from: f */
    protected String f1138f = null;

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public abstract boolean mo13755a(Object obj);

    /* access modifiers changed from: protected */
    /* renamed from: e */
    public abstract Object mo13756e();

    public C1113d(Context context, C1040Ad ad, AdPreferences adPreferences, AdEventListener adEventListener, AdPreferences.Placement placement) {
        this.f1133a = context;
        this.f1134b = ad;
        this.f1135c = adPreferences;
        this.f1136d = new C1080b(adEventListener);
        this.f1137e = placement;
    }

    /* renamed from: c */
    public void mo14816c() {
        C1303g.m2206a(C1303g.C1307a.HIGH, (Runnable) new Runnable() {
            public void run() {
                Process.setThreadPriority(10);
                final Boolean d = C1113d.this.mo14817d();
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    public void run() {
                        C1113d.this.mo13754a(d);
                    }
                });
            }
        });
    }

    /* access modifiers changed from: protected */
    /* renamed from: d */
    public Boolean mo14817d() {
        return Boolean.valueOf(mo13755a(mo13756e()));
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo13754a(Boolean bool) {
        mo14261b(bool);
        if (!bool.booleanValue()) {
            this.f1134b.setErrorMessage(this.f1138f);
            this.f1136d.onFailedToReceiveAd(this.f1134b);
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public void mo14261b(Boolean bool) {
        this.f1134b.setState(bool.booleanValue() ? C1040Ad.AdState.READY : C1040Ad.AdState.UN_INITIALIZED);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public GetAdRequest mo13957a() {
        return mo14815b(new GetAdRequest());
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public GetAdRequest mo14815b(GetAdRequest getAdRequest) {
        Pair<String, String> d = C1168l.m1641d(this.f1133a);
        try {
            getAdRequest.fillAdPreferences(this.f1133a, this.f1135c, this.f1137e, d);
            if (!C1098b.m1303a().mo14750D()) {
                C1270g.m2076a(6, "forceExternal - check - request - metadata false disabletwoclicks");
                if (C1103c.m1389a(this.f1133a, this.f1137e)) {
                    getAdRequest.setDisableTwoClicks(true);
                }
            }
            try {
                getAdRequest.fillApplicationDetails(this.f1133a, this.f1135c, false);
            } catch (Exception e) {
                C1132f.m1527a(this.f1133a, C1130d.EXCEPTION, "BaseService.GetAdRequest - fillApplicationDetails failed", e.getMessage(), "");
            }
            return getAdRequest;
        } catch (Exception unused) {
            C1168l.m1633a(d);
            return null;
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: f */
    public AdPreferences.Placement mo14818f() {
        return this.f1137e;
    }
}

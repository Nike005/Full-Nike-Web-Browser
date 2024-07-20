package com.startapp.android.publish.adsCommon.adListeners;

import android.os.Handler;
import android.os.Looper;
import com.startapp.android.publish.adsCommon.C1040Ad;

/* renamed from: com.startapp.android.publish.adsCommon.adListeners.b */
/* compiled from: StartAppSDK */
public class C1080b implements AdEventListener {

    /* renamed from: a */
    AdEventListener f1033a;

    public C1080b(AdEventListener adEventListener) {
        this.f1033a = adEventListener;
    }

    public void onReceiveAd(final C1040Ad ad) {
        if (this.f1033a != null) {
            Handler a = mo14682a();
            if (a != null) {
                a.post(new Runnable() {
                    public void run() {
                        C1080b.this.f1033a.onReceiveAd(ad);
                    }
                });
            } else {
                this.f1033a.onReceiveAd(ad);
            }
        }
    }

    public void onFailedToReceiveAd(final C1040Ad ad) {
        if (this.f1033a != null) {
            Handler a = mo14682a();
            if (a != null) {
                a.post(new Runnable() {
                    public void run() {
                        C1080b.this.f1033a.onFailedToReceiveAd(ad);
                    }
                });
            } else {
                this.f1033a.onFailedToReceiveAd(ad);
            }
        }
    }

    /* renamed from: a */
    public Handler mo14682a() {
        return new Handler(Looper.getMainLooper());
    }
}

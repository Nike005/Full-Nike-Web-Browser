package com.startapp.android.publish.adsCommon.adListeners;

import android.os.Handler;
import android.os.Looper;
import com.startapp.android.publish.adsCommon.C1040Ad;

/* renamed from: com.startapp.android.publish.adsCommon.adListeners.a */
/* compiled from: StartAppSDK */
public class C1075a implements AdDisplayListener {

    /* renamed from: a */
    AdDisplayListener f1024a;

    public C1075a(AdDisplayListener adDisplayListener) {
        this.f1024a = adDisplayListener;
    }

    public void adHidden(final C1040Ad ad) {
        if (this.f1024a != null) {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                public void run() {
                    C1075a.this.f1024a.adHidden(ad);
                }
            });
        }
    }

    public void adDisplayed(final C1040Ad ad) {
        if (this.f1024a != null) {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                public void run() {
                    C1075a.this.f1024a.adDisplayed(ad);
                }
            });
        }
    }

    public void adClicked(final C1040Ad ad) {
        if (this.f1024a != null) {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                public void run() {
                    C1075a.this.f1024a.adClicked(ad);
                }
            });
        }
    }

    public void adNotDisplayed(final C1040Ad ad) {
        if (this.f1024a != null) {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                public void run() {
                    C1075a.this.f1024a.adNotDisplayed(ad);
                }
            });
        }
    }
}

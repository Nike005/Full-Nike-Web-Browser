package com.startapp.android.publish.adsCommon;

import android.app.Activity;
import java.io.Serializable;

/* renamed from: com.startapp.android.publish.adsCommon.a */
/* compiled from: StartAppSDK */
public class C1067a implements Serializable {
    private static final long serialVersionUID = 1;
    private boolean isActivityFullScreen;

    public C1067a(Activity activity) {
        m1212a(C1103c.m1387a(activity));
    }

    /* renamed from: a */
    public boolean mo14647a() {
        return this.isActivityFullScreen;
    }

    /* renamed from: a */
    private void m1212a(boolean z) {
        this.isActivityFullScreen = z;
    }
}

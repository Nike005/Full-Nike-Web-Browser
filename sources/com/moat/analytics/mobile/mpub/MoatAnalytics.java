package com.moat.analytics.mobile.mpub;

import android.app.Application;
import com.moat.analytics.mobile.mpub.C0345v;

public abstract class MoatAnalytics {

    /* renamed from: a */
    private static MoatAnalytics f50a;

    public static synchronized MoatAnalytics getInstance() {
        MoatAnalytics moatAnalytics;
        synchronized (MoatAnalytics.class) {
            if (f50a == null) {
                try {
                    f50a = new C0327k();
                } catch (Exception e) {
                    C0330n.m214a(e);
                    f50a = new C0345v.C0346a();
                }
            }
            moatAnalytics = f50a;
        }
        return moatAnalytics;
    }

    public abstract void prepareNativeDisplayTracking(String str);

    public abstract void start(Application application);

    public abstract void start(MoatOptions moatOptions, Application application);
}

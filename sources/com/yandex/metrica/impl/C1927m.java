package com.yandex.metrica.impl;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

/* renamed from: com.yandex.metrica.impl.m */
public class C1927m implements Application.ActivityLifecycleCallbacks {

    /* renamed from: a */
    private C2243z f3197a;

    public void onActivityCreated(Activity activity, Bundle bundle) {
    }

    public void onActivityDestroyed(Activity activity) {
    }

    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    public void onActivityStarted(Activity activity) {
    }

    public void onActivityStopped(Activity activity) {
    }

    public C1927m(C2243z zVar) {
        this.f3197a = zVar;
    }

    public void onActivityResumed(Activity activity) {
        this.f3197a.mo17950b(activity);
    }

    public void onActivityPaused(Activity activity) {
        this.f3197a.mo17951c(activity);
    }
}

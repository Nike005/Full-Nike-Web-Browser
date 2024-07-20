package com.moat.analytics.mobile.mpub;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import com.google.android.gms.ads.AdActivity;
import com.moat.analytics.mobile.mpub.C0351w;
import com.moat.analytics.mobile.mpub.p002a.p004b.C0301a;
import java.lang.ref.WeakReference;

/* renamed from: com.moat.analytics.mobile.mpub.f */
class C0309f {

    /* renamed from: a */
    private static WebAdTracker f84a;

    /* renamed from: b */
    private static WeakReference<Activity> f85b = new WeakReference<>((Object) null);

    C0309f() {
    }

    /* renamed from: a */
    private static void m119a() {
        if (f84a != null) {
            C0336p.m228a(3, "GMAInterstitialHelper", f85b.get(), "Stopping to track GMA interstitial");
            f84a.stopTracking();
            f84a = null;
        }
    }

    /* renamed from: a */
    static void m120a(Activity activity) {
        try {
            if (C0351w.m264a().f197a != C0351w.C0359d.OFF) {
                if (!m122b(activity)) {
                    m119a();
                    f85b = new WeakReference<>((Object) null);
                } else if (f85b.get() == null || f85b.get() != activity) {
                    View decorView = activity.getWindow().getDecorView();
                    if (decorView instanceof ViewGroup) {
                        C0301a<WebView> a = C0303ab.m87a((ViewGroup) decorView, true);
                        if (a.mo10376c()) {
                            f85b = new WeakReference<>(activity);
                            m121a(a.mo10374b());
                            return;
                        }
                        C0336p.m228a(3, "GMAInterstitialHelper", (Object) activity, "Sorry, no WebView in this activity");
                    }
                }
            }
        } catch (Exception e) {
            C0330n.m214a(e);
        }
    }

    /* renamed from: a */
    private static void m121a(WebView webView) {
        C0336p.m228a(3, "GMAInterstitialHelper", f85b.get(), "Starting to track GMA interstitial");
        WebAdTracker createWebAdTracker = MoatFactory.create().createWebAdTracker(webView);
        f84a = createWebAdTracker;
        createWebAdTracker.startTracking();
    }

    /* renamed from: b */
    private static boolean m122b(Activity activity) {
        String name = activity.getClass().getName();
        C0336p.m228a(3, "GMAInterstitialHelper", (Object) activity, "Activity name: " + name);
        return name.contains(AdActivity.CLASS_NAME);
    }
}

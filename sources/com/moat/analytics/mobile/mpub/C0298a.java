package com.moat.analytics.mobile.mpub;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import java.lang.ref.WeakReference;

/* renamed from: com.moat.analytics.mobile.mpub.a */
class C0298a {

    /* renamed from: a */
    static WeakReference<Activity> f53a = null;

    /* renamed from: b */
    private static boolean f54b = false;

    /* renamed from: c */
    private static Application f55c = null;
    /* access modifiers changed from: private */

    /* renamed from: d */
    public static int f56d = 0;
    /* access modifiers changed from: private */

    /* renamed from: e */
    public static boolean f57e = false;

    /* renamed from: com.moat.analytics.mobile.mpub.a$a */
    private static class C0299a implements Application.ActivityLifecycleCallbacks {
        C0299a() {
        }

        /* renamed from: a */
        private static void m78a(boolean z) {
            C0336p.m228a(3, "ActivityState", (Object) null, z ? "App became visible" : "App became invisible");
        }

        public void onActivityCreated(Activity activity, Bundle bundle) {
            int unused = C0298a.f56d = 1;
        }

        public void onActivityDestroyed(Activity activity) {
            try {
                if (!(C0298a.f56d == 3 || C0298a.f56d == 5)) {
                    if (C0298a.f57e) {
                        m78a(false);
                    }
                    boolean unused = C0298a.f57e = false;
                }
                int unused2 = C0298a.f56d = 6;
                C0336p.m228a(3, "ActivityState", (Object) this, "Activity destroyed: " + activity.getClass() + "@" + activity.hashCode());
                if (C0298a.m76b(activity)) {
                    C0298a.f53a = new WeakReference<>((Object) null);
                }
            } catch (Exception e) {
                C0330n.m214a(e);
            }
        }

        public void onActivityPaused(Activity activity) {
            try {
                int unused = C0298a.f56d = 4;
                if (C0298a.m76b(activity)) {
                    C0298a.f53a = new WeakReference<>((Object) null);
                }
                C0336p.m228a(3, "ActivityState", (Object) this, "Activity paused: " + activity.getClass() + "@" + activity.hashCode());
            } catch (Exception e) {
                C0330n.m214a(e);
            }
        }

        public void onActivityResumed(Activity activity) {
            try {
                C0298a.f53a = new WeakReference<>(activity);
                int unused = C0298a.f56d = 3;
                C0351w.m264a().mo10464b();
                C0336p.m228a(3, "ActivityState", (Object) this, "Activity resumed: " + activity.getClass() + "@" + activity.hashCode());
                if (((C0327k) MoatAnalytics.getInstance()).f149b) {
                    C0309f.m120a(activity);
                }
            } catch (Exception e) {
                C0330n.m214a(e);
            }
        }

        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        }

        public void onActivityStarted(Activity activity) {
            try {
                C0298a.f53a = new WeakReference<>(activity);
                int unused = C0298a.f56d = 2;
                if (!C0298a.f57e) {
                    m78a(true);
                }
                boolean unused2 = C0298a.f57e = true;
                C0336p.m228a(3, "ActivityState", (Object) this, "Activity started: " + activity.getClass() + "@" + activity.hashCode());
            } catch (Exception e) {
                C0330n.m214a(e);
            }
        }

        public void onActivityStopped(Activity activity) {
            try {
                if (C0298a.f56d != 3) {
                    boolean unused = C0298a.f57e = false;
                    m78a(false);
                }
                int unused2 = C0298a.f56d = 5;
                if (C0298a.m76b(activity)) {
                    C0298a.f53a = new WeakReference<>((Object) null);
                }
                C0336p.m228a(3, "ActivityState", (Object) this, "Activity stopped: " + activity.getClass() + "@" + activity.hashCode());
            } catch (Exception e) {
                C0330n.m214a(e);
            }
        }
    }

    C0298a() {
    }

    /* renamed from: a */
    static Application m71a() {
        return f55c;
    }

    /* renamed from: a */
    static void m72a(Application application) {
        f55c = application;
        if (!f54b) {
            f54b = true;
            application.registerActivityLifecycleCallbacks(new C0299a());
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: b */
    public static boolean m76b(Activity activity) {
        WeakReference<Activity> weakReference = f53a;
        return weakReference != null && weakReference.get() == activity;
    }
}

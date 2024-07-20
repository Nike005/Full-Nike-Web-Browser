package com.moat.analytics.mobile.mpub;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/* renamed from: com.moat.analytics.mobile.mpub.i */
class C0319i {

    /* renamed from: a */
    private static final C0319i f113a = new C0319i();

    /* renamed from: b */
    private Class<?> f114b = null;

    /* renamed from: c */
    private Object f115c = null;
    /* access modifiers changed from: private */

    /* renamed from: d */
    public final Map<C0322j, String> f116d;
    /* access modifiers changed from: private */

    /* renamed from: e */
    public final Map<C0304b, String> f117e;

    /* renamed from: f */
    private final ScheduledExecutorService f118f;
    /* access modifiers changed from: private */

    /* renamed from: g */
    public ScheduledFuture<?> f119g;
    /* access modifiers changed from: private */

    /* renamed from: h */
    public ScheduledFuture<?> f120h;

    private C0319i() {
        m150c();
        this.f118f = Executors.newScheduledThreadPool(1);
        this.f116d = new WeakHashMap();
        this.f117e = new WeakHashMap();
    }

    /* renamed from: a */
    static C0319i m142a() {
        return f113a;
    }

    /* renamed from: a */
    private void m144a(Context context) {
        try {
            if (this.f115c != null) {
                return;
            }
            if (this.f114b != null) {
                this.f115c = this.f114b.getMethod("getInstance", new Class[]{Context.class}).invoke((Object) null, new Object[]{context});
            }
        } catch (NoSuchMethodException e) {
            C0336p.m230a("JSUpdateLooper", (Object) C0339s.class, "NoSuchMethodException while getting LocalBroadcastManager instance", (Throwable) e);
        } catch (Exception e2) {
            C0330n.m214a(e2);
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public void m145a(Context context, Intent intent) {
        try {
            m144a(context);
            if (this.f114b == null) {
                return;
            }
            if (this.f115c != null) {
                this.f114b.getMethod("sendBroadcast", new Class[]{Intent.class}).invoke(this.f115c, new Object[]{intent});
            }
        } catch (NoSuchMethodException e) {
            C0336p.m230a("JSUpdateLooper", (Object) C0339s.class, "NoSuchMethodException calling LocalBroadcastManager sendBroadcast", (Throwable) e);
        } catch (Exception e2) {
            C0330n.m214a(e2);
        }
    }

    /* renamed from: b */
    private void m148b(final Context context) {
        ScheduledFuture<?> scheduledFuture = this.f120h;
        if (scheduledFuture == null || scheduledFuture.isDone()) {
            C0336p.m228a(3, "JSUpdateLooper", (Object) this, "Starting metadata reporting loop");
            this.f120h = this.f118f.scheduleWithFixedDelay(new Runnable() {
                public void run() {
                    try {
                        C0319i.this.m145a(context.getApplicationContext(), new Intent("UPDATE_METADATA"));
                        if (C0319i.this.f116d.isEmpty()) {
                            C0319i.this.f120h.cancel(true);
                        }
                    } catch (Exception e) {
                        C0330n.m214a(e);
                    }
                }
            }, 0, 50, TimeUnit.MILLISECONDS);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0020, code lost:
        com.moat.analytics.mobile.mpub.C0336p.m230a("JSUpdateLooper", (java.lang.Object) r4, "ClassNotFoundException while retrieving LocalBroadcastManager androidx class", (java.lang.Throwable) r0);
        com.moat.analytics.mobile.mpub.C0336p.m230a("JSUpdateLooper", (java.lang.Object) r4, "No LocalBroadcastManager class was found.", (java.lang.Throwable) r1);
        com.moat.analytics.mobile.mpub.C0336p.m231a("[ERROR] ", "No LocalBroadcastManager class was found.");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0009, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x001f, code lost:
        r0 = e;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:3:0x0009 A[ExcHandler: Exception (e java.lang.Exception), Splitter:B:1:0x0002] */
    /* renamed from: c */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void m150c() {
        /*
            r4 = this;
            java.lang.String r0 = "androidx.localbroadcastmanager.content.LocalBroadcastManager"
            java.lang.Class r1 = java.lang.Class.forName(r0)     // Catch:{ ClassNotFoundException -> 0x000b, Exception -> 0x0009 }
            r4.f114b = r1     // Catch:{ ClassNotFoundException -> 0x000b, Exception -> 0x0009 }
            goto L_0x0030
        L_0x0009:
            r0 = move-exception
            goto L_0x000d
        L_0x000b:
            r1 = move-exception
            goto L_0x0011
        L_0x000d:
            com.moat.analytics.mobile.mpub.C0330n.m214a(r0)
            goto L_0x0030
        L_0x0011:
            java.lang.String r2 = "JSUpdateLooper"
            java.lang.String r3 = "ClassNotFoundException while retrieving LocalBroadcastManager support class"
            com.moat.analytics.mobile.mpub.C0336p.m230a((java.lang.String) r2, (java.lang.Object) r4, (java.lang.String) r3, (java.lang.Throwable) r1)
            java.lang.Class r0 = java.lang.Class.forName(r0)     // Catch:{ ClassNotFoundException -> 0x001f, Exception -> 0x0009 }
            r4.f114b = r0     // Catch:{ ClassNotFoundException -> 0x001f, Exception -> 0x0009 }
            goto L_0x0030
        L_0x001f:
            r0 = move-exception
            java.lang.String r3 = "ClassNotFoundException while retrieving LocalBroadcastManager androidx class"
            com.moat.analytics.mobile.mpub.C0336p.m230a((java.lang.String) r2, (java.lang.Object) r4, (java.lang.String) r3, (java.lang.Throwable) r0)
            java.lang.String r3 = "No LocalBroadcastManager class was found."
            com.moat.analytics.mobile.mpub.C0336p.m230a((java.lang.String) r2, (java.lang.Object) r4, (java.lang.String) r3, (java.lang.Throwable) r1)
            java.lang.String r1 = "[ERROR] "
            com.moat.analytics.mobile.mpub.C0336p.m231a(r1, r3)
            goto L_0x000d
        L_0x0030:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.moat.analytics.mobile.mpub.C0319i.m150c():void");
    }

    /* renamed from: c */
    private void m151c(final Context context) {
        ScheduledFuture<?> scheduledFuture = this.f119g;
        if (scheduledFuture == null || scheduledFuture.isDone()) {
            C0336p.m228a(3, "JSUpdateLooper", (Object) this, "Starting view update loop");
            this.f119g = this.f118f.scheduleWithFixedDelay(new Runnable() {
                public void run() {
                    try {
                        C0319i.this.m145a(context.getApplicationContext(), new Intent("UPDATE_VIEW_INFO"));
                        if (C0319i.this.f117e.isEmpty()) {
                            C0336p.m228a(3, "JSUpdateLooper", (Object) C0319i.this, "No more active trackers");
                            C0319i.this.f119g.cancel(true);
                        }
                    } catch (Exception e) {
                        C0330n.m214a(e);
                    }
                }
            }, 0, (long) C0351w.m264a().f200d, TimeUnit.MILLISECONDS);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo10426a(Context context, BroadcastReceiver broadcastReceiver) {
        try {
            m144a(context);
            if (this.f114b == null) {
                return;
            }
            if (this.f115c != null) {
                this.f114b.getMethod("unregisterReceiver", new Class[]{BroadcastReceiver.class}).invoke(this.f115c, new Object[]{broadcastReceiver});
            }
        } catch (NoSuchMethodException e) {
            C0336p.m230a("JSUpdateLooper", (Object) C0339s.class, "NoSuchMethodException while calling LocalBroadcastManager unregisterReceiver", (Throwable) e);
        } catch (Exception e2) {
            C0330n.m214a(e2);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo10427a(Context context, BroadcastReceiver broadcastReceiver, IntentFilter intentFilter) {
        try {
            m144a(context);
            if (this.f114b == null) {
                return;
            }
            if (this.f115c != null) {
                this.f114b.getMethod("registerReceiver", new Class[]{BroadcastReceiver.class, IntentFilter.class}).invoke(this.f115c, new Object[]{broadcastReceiver, intentFilter});
            }
        } catch (NoSuchMethodException e) {
            C0336p.m230a("JSUpdateLooper", (Object) C0339s.class, "NoSuchMethodException while calling LocalBroadcastManager registerReceiver", (Throwable) e);
        } catch (Exception e2) {
            C0330n.m214a(e2);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo10428a(Context context, C0304b bVar) {
        if (bVar != null) {
            C0336p.m228a(3, "JSUpdateLooper", (Object) this, "addActiveTracker" + bVar.hashCode());
            Map<C0304b, String> map = this.f117e;
            if (map != null && !map.containsKey(bVar)) {
                this.f117e.put(bVar, "");
                m151c(context);
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo10429a(Context context, C0322j jVar) {
        Map<C0322j, String> map = this.f116d;
        if (map != null && jVar != null) {
            map.put(jVar, "");
            m148b(context);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo10430a(C0304b bVar) {
        if (bVar != null) {
            C0336p.m228a(3, "JSUpdateLooper", (Object) this, "removeActiveTracker" + bVar.hashCode());
            Map<C0304b, String> map = this.f117e;
            if (map != null) {
                map.remove(bVar);
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo10431a(C0322j jVar) {
        if (jVar != null) {
            C0336p.m228a(3, "JSUpdateLooper", (Object) this, "removeSetupNeededBridge" + jVar.hashCode());
            Map<C0322j, String> map = this.f116d;
            if (map != null) {
                map.remove(jVar);
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public boolean mo10432b() {
        return this.f114b != null;
    }
}

package com.yandex.metrica.impl;

import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import com.yandex.metrica.IMetricaService;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;

/* renamed from: com.yandex.metrica.impl.ad */
public class C1812ad {

    /* renamed from: a */
    public static final long f2887a = TimeUnit.SECONDS.toMillis(10);

    /* renamed from: b */
    private final Context f2888b;

    /* renamed from: c */
    private final Handler f2889c;

    /* renamed from: d */
    private final List<C1815a> f2890d = new CopyOnWriteArrayList();
    /* access modifiers changed from: private */

    /* renamed from: e */
    public volatile IMetricaService f2891e = null;

    /* renamed from: f */
    private final Runnable f2892f = new Runnable() {
        public void run() {
            C1812ad.this.m4146f();
        }
    };

    /* renamed from: g */
    private final ServiceConnection f2893g = new ServiceConnection() {
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            IMetricaService unused = C1812ad.this.f2891e = IMetricaService.Stub.asInterface(iBinder);
            C1812ad.m4144b(C1812ad.this);
        }

        public void onServiceDisconnected(ComponentName componentName) {
            IMetricaService unused = C1812ad.this.f2891e = null;
            C1812ad.this.m4147g();
        }
    };

    /* renamed from: com.yandex.metrica.impl.ad$a */
    interface C1815a {
        /* renamed from: a */
        void mo16781a();
    }

    public C1812ad(Context context, Handler handler) {
        this.f2888b = context.getApplicationContext();
        this.f2889c = handler;
    }

    /* renamed from: a */
    public synchronized void mo16772a() {
        if (this.f2891e == null) {
            try {
                this.f2888b.bindService(C1887be.m4556c(this.f2888b), this.f2893g, 1);
            } catch (Exception unused) {
            }
        }
    }

    /* renamed from: b */
    public void mo16774b() {
        this.f2889c.removeCallbacks(this.f2892f);
        this.f2889c.postDelayed(this.f2892f, f2887a);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: c */
    public void mo16775c() {
        this.f2889c.removeCallbacks(this.f2892f);
    }

    /* renamed from: d */
    public boolean mo16776d() {
        return this.f2891e != null;
    }

    /* renamed from: e */
    public IMetricaService mo16777e() {
        return this.f2891e;
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Can't wrap try/catch for region: R(6:1|2|(2:6|7)|8|9|10) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:8:0x0015 */
    /* renamed from: f */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void m4146f() {
        /*
            r3 = this;
            monitor-enter(r3)
            android.content.Context r0 = r3.f2888b     // Catch:{ all -> 0x001c }
            r1 = 0
            if (r0 == 0) goto L_0x0015
            boolean r0 = r3.mo16776d()     // Catch:{ all -> 0x001c }
            if (r0 == 0) goto L_0x0015
            android.content.Context r0 = r3.f2888b     // Catch:{ Exception -> 0x0015 }
            android.content.ServiceConnection r2 = r3.f2893g     // Catch:{ Exception -> 0x0015 }
            r0.unbindService(r2)     // Catch:{ Exception -> 0x0015 }
            r3.f2891e = r1     // Catch:{ Exception -> 0x0015 }
        L_0x0015:
            r3.f2891e = r1     // Catch:{ all -> 0x001c }
            r3.m4147g()     // Catch:{ all -> 0x001c }
            monitor-exit(r3)
            return
        L_0x001c:
            r0 = move-exception
            monitor-exit(r3)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.yandex.metrica.impl.C1812ad.m4146f():void");
    }

    /* access modifiers changed from: private */
    /* renamed from: g */
    public void m4147g() {
        Iterator<C1815a> it = this.f2890d.iterator();
        while (it.hasNext()) {
            it.next();
        }
    }

    /* renamed from: a */
    public void mo16773a(C1815a aVar) {
        this.f2890d.add(aVar);
    }

    /* renamed from: b */
    static /* synthetic */ void m4144b(C1812ad adVar) {
        for (C1815a a : adVar.f2890d) {
            a.mo16781a();
        }
    }
}

package com.yandex.metrica.impl;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.os.Bundle;
import android.os.RemoteException;
import android.text.TextUtils;
import com.yandex.metrica.CounterConfiguration;
import com.yandex.metrica.MetricaService;
import com.yandex.metrica.impl.C2208p;
import com.yandex.metrica.impl.GoogleAdvertisingIdGetter;
import com.yandex.metrica.impl.p050ob.C2003bp;
import com.yandex.metrica.impl.p050ob.C2019cd;
import com.yandex.metrica.impl.p050ob.C2026ci;
import com.yandex.metrica.impl.p050ob.C2041co;
import com.yandex.metrica.impl.p050ob.C2044cp;
import com.yandex.metrica.impl.p050ob.C2090dr;
import com.yandex.metrica.impl.p050ob.C2091ds;
import com.yandex.metrica.impl.p050ob.C2117ef;
import com.yandex.metrica.impl.p050ob.C2174g;
import com.yandex.metrica.impl.p050ob.C2180i;
import com.yandex.metrica.impl.p050ob.C2187o;
import com.yandex.metrica.impl.p050ob.C2189q;
import com.yandex.metrica.impl.p050ob.C2190r;
import com.yandex.metrica.impl.p050ob.C2198t;
import com.yandex.metrica.impl.utils.C2229k;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.json.JSONException;

/* renamed from: com.yandex.metrica.impl.ag */
public class C1827ag implements C1816ae {

    /* renamed from: c */
    private static final Executor f2921c = new C2044cp();

    /* renamed from: d */
    private static final ExecutorService f2922d = Executors.newSingleThreadExecutor();
    /* access modifiers changed from: private */

    /* renamed from: e */
    public static final Map<String, C2198t> f2923e = new HashMap();

    /* renamed from: f */
    private static final C2189q f2924f = new C2189q();

    /* renamed from: a */
    private Context f2925a;

    /* renamed from: b */
    private MetricaService.C1778b f2926b;

    /* renamed from: a */
    static /* synthetic */ boolean m4188a(CounterConfiguration counterConfiguration) {
        return counterConfiguration == null;
    }

    public C1827ag(Context context, MetricaService.C1778b bVar) {
        this.f2925a = context;
        this.f2926b = bVar;
    }

    /* renamed from: a */
    public void mo16782a() {
        new C1882bd(this.f2925a).mo16801a(this.f2925a);
        C2229k.m5967a().mo17929a(this.f2925a);
        GoogleAdvertisingIdGetter.C1803b.f2873a.mo16734a(this.f2925a);
        C2019cd cdVar = new C2019cd(C2003bp.m5024a(this.f2925a).mo17290d(), this.f2925a.getPackageName());
        C2041co.m5269a().mo17503a(this.f2925a, cdVar.mo17420b((String) null), cdVar.mo17433h((String) null));
        mo16794a(cdVar);
        C2026ci.m5230a().mo17473a(this.f2925a);
    }

    /* renamed from: a */
    public void mo16786a(Intent intent, int i) {
        m4191b(intent, i);
    }

    /* renamed from: a */
    public void mo16787a(Intent intent, int i, int i2) {
        m4191b(intent, i2);
    }

    /* renamed from: a */
    public void mo16785a(Intent intent) {
        C2090dr.m5452a(this.f2925a).mo17641a();
        C2241y.m5985a(this.f2925a).mo17937a((Object) this);
        C2117ef.m5566a(this.f2925a).mo17690a();
    }

    /* renamed from: b */
    public void mo16789b(Intent intent) {
        C2090dr.m5452a(this.f2925a).mo17641a();
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x0042  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x001a A[SYNTHETIC] */
    /* renamed from: c */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void mo16790c(android.content.Intent r6) {
        /*
            r5 = this;
            android.net.Uri r6 = r6.getData()
            java.lang.String r6 = r6.getEncodedAuthority()
            java.util.Map<java.lang.String, com.yandex.metrica.impl.ob.t> r0 = f2923e
            monitor-enter(r0)
            java.util.HashMap r1 = new java.util.HashMap     // Catch:{ all -> 0x0060 }
            java.util.Map<java.lang.String, com.yandex.metrica.impl.ob.t> r2 = f2923e     // Catch:{ all -> 0x0060 }
            r1.<init>(r2)     // Catch:{ all -> 0x0060 }
            java.util.Set r1 = r1.entrySet()     // Catch:{ all -> 0x0060 }
            java.util.Iterator r1 = r1.iterator()     // Catch:{ all -> 0x0060 }
        L_0x001a:
            boolean r2 = r1.hasNext()     // Catch:{ all -> 0x0060 }
            if (r2 == 0) goto L_0x004d
            java.lang.Object r2 = r1.next()     // Catch:{ all -> 0x0060 }
            java.util.Map$Entry r2 = (java.util.Map.Entry) r2     // Catch:{ all -> 0x0060 }
            java.lang.Object r3 = r2.getKey()     // Catch:{ all -> 0x0060 }
            java.lang.String r3 = (java.lang.String) r3     // Catch:{ all -> 0x0060 }
            java.lang.Object r2 = r2.getValue()     // Catch:{ all -> 0x0060 }
            com.yandex.metrica.impl.ob.t r2 = (com.yandex.metrica.impl.p050ob.C2198t) r2     // Catch:{ all -> 0x0060 }
            if (r3 == 0) goto L_0x003f
            if (r2 == 0) goto L_0x003f
            boolean r4 = r3.startsWith(r6)     // Catch:{ all -> 0x0060 }
            if (r4 == 0) goto L_0x003d
            goto L_0x003f
        L_0x003d:
            r4 = 0
            goto L_0x0040
        L_0x003f:
            r4 = 1
        L_0x0040:
            if (r4 == 0) goto L_0x001a
            java.util.Map<java.lang.String, com.yandex.metrica.impl.ob.t> r4 = f2923e     // Catch:{ all -> 0x0060 }
            r4.remove(r3)     // Catch:{ all -> 0x0060 }
            if (r2 == 0) goto L_0x001a
            r2.mo17850c()     // Catch:{ all -> 0x0060 }
            goto L_0x001a
        L_0x004d:
            monitor-exit(r0)     // Catch:{ all -> 0x0060 }
            java.util.Map<java.lang.String, com.yandex.metrica.impl.ob.t> r6 = f2923e
            boolean r6 = r6.isEmpty()
            if (r6 == 0) goto L_0x005f
            android.content.Context r6 = r5.f2925a
            com.yandex.metrica.impl.ob.dr r6 = com.yandex.metrica.impl.p050ob.C2090dr.m5452a(r6)
            r6.mo17642b()
        L_0x005f:
            return
        L_0x0060:
            r6 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0060 }
            goto L_0x0064
        L_0x0063:
            throw r6
        L_0x0064:
            goto L_0x0063
        */
        throw new UnsupportedOperationException("Method not decompiled: com.yandex.metrica.impl.C1827ag.mo16790c(android.content.Intent):void");
    }

    /* renamed from: a */
    public void mo16784a(int i, String str, int i2, String str2, Bundle bundle) throws RemoteException {
        bundle.setClassLoader(CounterConfiguration.class.getClassLoader());
        m4184a(i, new C1915h(str2, str, i2), bundle);
    }

    /* renamed from: a */
    public void mo16783a(int i, Bundle bundle) throws RemoteException {
        bundle.setClassLoader(CounterConfiguration.class.getClassLoader());
        m4184a(i, C1915h.m4731b(bundle), bundle);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo16794a(C2019cd cdVar) {
        String l = cdVar.mo17441l();
        if (TextUtils.isEmpty(l)) {
            C2174g.m5753a().mo17803a((Class<? extends C2180i>) C2187o.class);
            return;
        }
        try {
            C2174g.m5753a().mo17806b((C2180i) new C2187o(new C2091ds(l)));
        } catch (JSONException unused) {
        }
    }

    /* renamed from: b */
    private void m4191b(Intent intent, int i) {
        if (intent != null) {
            intent.getExtras().setClassLoader(CounterConfiguration.class.getClassLoader());
            boolean z = false;
            if (!(intent == null || intent.getData() == null)) {
                C1915h b = C1915h.m4731b(intent.getExtras());
                if (b.mo17135n()) {
                    b.mo17111a(intent.getIntExtra("EXTRA_KEY_KEY_START_TYPE", C2208p.C2209a.EVENT_TYPE_UNDEFINED.mo17884a())).mo17106b(intent.getStringExtra("EXTRA_KEY_KEY_START_EVENT")).mo17107c("");
                }
                if (!b.mo17134m() && !b.mo17135n()) {
                    Bundle bundleExtra = intent.getBundleExtra("EXTRA_KEY_LIB_CFG");
                    if (bundleExtra == null) {
                        bundleExtra = intent.getExtras();
                    }
                    CounterConfiguration b2 = CounterConfiguration.m3911b(bundleExtra);
                    if (b2 == null) {
                        z = true;
                    }
                    if (!z) {
                        String encodedAuthority = intent.getData().getEncodedAuthority();
                        m4193b(b2, encodedAuthority);
                        m4192b(b2);
                        C2241y.m5985a(this.f2925a).mo17936a(b.mo17123e());
                        try {
                            C2198t tVar = new C2198t(this.f2925a, f2921c, C2190r.m5776a(this.f2925a, b2, (Integer) null, encodedAuthority), b2, f2924f);
                            tVar.mo17841a(b);
                            tVar.mo17852d();
                        } catch (Exception unused) {
                        }
                    }
                }
            }
        }
        this.f2926b.mo16643a(i);
    }

    /* renamed from: com.yandex.metrica.impl.ag$a */
    private final class C1828a implements Runnable {

        /* renamed from: b */
        private final int f2928b;

        /* renamed from: c */
        private final C1915h f2929c;

        /* renamed from: d */
        private final Bundle f2930d;

        /* renamed from: e */
        private final Context f2931e;

        C1828a(Context context, C1915h hVar, Bundle bundle, int i) {
            this.f2931e = context.getApplicationContext();
            this.f2928b = i;
            this.f2929c = hVar;
            this.f2930d = bundle;
        }

        /* JADX WARNING: Code restructure failed: missing block: B:29:0x00c6, code lost:
            return;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void run() {
            /*
                r9 = this;
                android.os.Bundle r0 = r9.f2930d
                com.yandex.metrica.CounterConfiguration r0 = com.yandex.metrica.CounterConfiguration.m3911b((android.os.Bundle) r0)
                boolean r1 = com.yandex.metrica.impl.C1827ag.m4188a((com.yandex.metrica.CounterConfiguration) r0)
                if (r1 == 0) goto L_0x000d
                return
            L_0x000d:
                com.yandex.metrica.impl.ag r1 = com.yandex.metrica.impl.C1827ag.this
                com.yandex.metrica.impl.h r2 = r9.f2929c
                int r3 = r9.f2928b
                com.yandex.metrica.impl.ob.r r1 = r1.mo16793a((com.yandex.metrica.impl.C1915h) r2, (com.yandex.metrica.CounterConfiguration) r0, (int) r3)
                if (r1 != 0) goto L_0x001a
                return
            L_0x001a:
                java.lang.String r2 = r1.mo17819b()
                com.yandex.metrica.impl.C1827ag.m4193b((com.yandex.metrica.CounterConfiguration) r0, (java.lang.String) r2)
                java.util.Map r2 = com.yandex.metrica.impl.C1827ag.f2923e
                monitor-enter(r2)
                com.yandex.metrica.impl.ag r3 = com.yandex.metrica.impl.C1827ag.this     // Catch:{ all -> 0x00c7 }
                r3.m4192b((com.yandex.metrica.CounterConfiguration) r0)     // Catch:{ all -> 0x00c7 }
                com.yandex.metrica.impl.ag r3 = com.yandex.metrica.impl.C1827ag.this     // Catch:{ all -> 0x00c7 }
                android.content.Context r4 = r9.f2931e     // Catch:{ all -> 0x00c7 }
                java.lang.String r4 = r4.getPackageName()     // Catch:{ all -> 0x00c7 }
                java.lang.String r5 = r0.mo16587f()     // Catch:{ all -> 0x00c7 }
                boolean r4 = r4.equals(r5)     // Catch:{ all -> 0x00c7 }
                boolean r5 = r0.mo16600m()     // Catch:{ all -> 0x00c7 }
                com.yandex.metrica.impl.C2241y.m5985a(r3.f2925a).mo17938a(r3, r4, r5)     // Catch:{ all -> 0x00c7 }
                java.lang.String r3 = r0.mo16597j()     // Catch:{ all -> 0x00c7 }
                android.os.Bundle r4 = r9.f2930d     // Catch:{ all -> 0x00c7 }
                java.lang.String r5 = "COUNTER_MIGRATION_CFG_OBJ"
                boolean r4 = r4.containsKey(r5)     // Catch:{ all -> 0x00c7 }
                if (r4 == 0) goto L_0x008a
                android.os.Bundle r4 = r9.f2930d     // Catch:{ all -> 0x00c7 }
                com.yandex.metrica.CounterConfiguration r4 = m4207a(r4)     // Catch:{ all -> 0x00c7 }
                if (r4 == 0) goto L_0x008a
                boolean r5 = r4.mo16555D()     // Catch:{ all -> 0x00c7 }
                if (r5 == 0) goto L_0x008a
                android.content.Context r5 = r9.f2931e     // Catch:{ all -> 0x00c7 }
                int r6 = r9.f2928b     // Catch:{ all -> 0x00c7 }
                java.lang.Integer r6 = java.lang.Integer.valueOf(r6)     // Catch:{ all -> 0x00c7 }
                r7 = 0
                com.yandex.metrica.impl.ob.r r5 = com.yandex.metrica.impl.p050ob.C2190r.m5776a(r5, r4, r6, r7)     // Catch:{ all -> 0x00c7 }
                java.util.Map r6 = com.yandex.metrica.impl.C1827ag.f2923e     // Catch:{ all -> 0x00c7 }
                java.lang.String r8 = r5.toString()     // Catch:{ all -> 0x00c7 }
                boolean r6 = r6.containsKey(r8)     // Catch:{ all -> 0x00c7 }
                if (r6 != 0) goto L_0x008a
                com.yandex.metrica.CounterConfiguration r6 = new com.yandex.metrica.CounterConfiguration     // Catch:{ all -> 0x00c7 }
                r6.<init>((com.yandex.metrica.CounterConfiguration) r4)     // Catch:{ all -> 0x00c7 }
                r6.mo16567a((java.lang.String) r3)     // Catch:{ all -> 0x00c7 }
                com.yandex.metrica.impl.ag r3 = com.yandex.metrica.impl.C1827ag.this     // Catch:{ all -> 0x00c7 }
                com.yandex.metrica.impl.ob.t r3 = com.yandex.metrica.impl.C1827ag.m4183a(r3, r5, r6, r7)     // Catch:{ all -> 0x00c7 }
                r3.mo17856f()     // Catch:{ all -> 0x00c7 }
            L_0x008a:
                com.yandex.metrica.impl.ag r3 = com.yandex.metrica.impl.C1827ag.this     // Catch:{ all -> 0x00c7 }
                com.yandex.metrica.impl.h r4 = r9.f2929c     // Catch:{ all -> 0x00c7 }
                com.yandex.metrica.impl.ob.t r1 = com.yandex.metrica.impl.C1827ag.m4183a(r3, r1, r0, r4)     // Catch:{ all -> 0x00c7 }
                boolean r3 = com.yandex.metrica.impl.C1827ag.m4189a((com.yandex.metrica.impl.p050ob.C2198t) r1)     // Catch:{ all -> 0x00c7 }
                if (r3 == 0) goto L_0x009a
                monitor-exit(r2)     // Catch:{ all -> 0x00c7 }
                return
            L_0x009a:
                android.content.Context r3 = r9.f2931e     // Catch:{ all -> 0x00c7 }
                com.yandex.metrica.impl.y r3 = com.yandex.metrica.impl.C2241y.m5985a((android.content.Context) r3)     // Catch:{ all -> 0x00c7 }
                com.yandex.metrica.impl.h r4 = r9.f2929c     // Catch:{ all -> 0x00c7 }
                android.location.Location r4 = r4.mo17123e()     // Catch:{ all -> 0x00c7 }
                r3.mo17936a((android.location.Location) r4)     // Catch:{ all -> 0x00c7 }
                com.yandex.metrica.impl.h r3 = r9.f2929c     // Catch:{ all -> 0x00c7 }
                int r3 = r3.mo17118c()     // Catch:{ all -> 0x00c7 }
                boolean r3 = com.yandex.metrica.impl.C2208p.m5871a((int) r3)     // Catch:{ all -> 0x00c7 }
                if (r3 != 0) goto L_0x00b8
                r1.mo17840a((com.yandex.metrica.CounterConfiguration) r0)     // Catch:{ all -> 0x00c7 }
            L_0x00b8:
                com.yandex.metrica.impl.h r0 = r9.f2929c     // Catch:{ all -> 0x00c7 }
                boolean r0 = com.yandex.metrica.impl.C1827ag.m4190a((com.yandex.metrica.impl.p050ob.C2198t) r1, (com.yandex.metrica.impl.C1915h) r0)     // Catch:{ all -> 0x00c7 }
                if (r0 != 0) goto L_0x00c5
                com.yandex.metrica.impl.h r0 = r9.f2929c     // Catch:{ all -> 0x00c7 }
                r1.mo17841a((com.yandex.metrica.impl.C1915h) r0)     // Catch:{ all -> 0x00c7 }
            L_0x00c5:
                monitor-exit(r2)     // Catch:{ all -> 0x00c7 }
                return
            L_0x00c7:
                r0 = move-exception
                monitor-exit(r2)     // Catch:{ all -> 0x00c7 }
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.yandex.metrica.impl.C1827ag.C1828a.run():void");
        }

        /* renamed from: a */
        private static CounterConfiguration m4207a(Bundle bundle) {
            if (bundle == null) {
                return null;
            }
            try {
                return (CounterConfiguration) bundle.getParcelable("COUNTER_MIGRATION_CFG_OBJ");
            } catch (Throwable unused) {
                return null;
            }
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: b */
    public void m4192b(CounterConfiguration counterConfiguration) {
        if (TextUtils.isEmpty(counterConfiguration.mo16593h())) {
            m4195c(counterConfiguration);
            return;
        }
        String d = C2026ci.m5230a().mo17478d();
        if (!(TextUtils.isEmpty(d) || TextUtils.equals(counterConfiguration.mo16593h(), d))) {
            m4195c(counterConfiguration);
        }
    }

    /* renamed from: c */
    private void m4195c(CounterConfiguration counterConfiguration) {
        String g = C2026ci.m5232g(this.f2925a, counterConfiguration.mo16587f());
        if (!TextUtils.isEmpty(g)) {
            counterConfiguration.mo16585e(g);
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: b */
    public static void m4193b(CounterConfiguration counterConfiguration, String str) {
        if (TextUtils.isEmpty(counterConfiguration.mo16587f())) {
            counterConfiguration.mo16577c(str);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public C2190r mo16793a(C1915h hVar, CounterConfiguration counterConfiguration, int i) {
        if (!C2208p.m5872a(hVar)) {
            return C2190r.m5776a(this.f2925a, counterConfiguration, Integer.valueOf(i), (String) null);
        }
        String l = hVar.mo17133l();
        boolean z = false;
        Iterator<ApplicationInfo> it = this.f2925a.getPackageManager().getInstalledApplications(0).iterator();
        while (true) {
            if (it.hasNext()) {
                if (it.next().packageName.equals(l)) {
                    z = true;
                    break;
                }
            } else {
                break;
            }
        }
        if (z) {
            return C2190r.m5777a(l);
        }
        return null;
    }

    /* renamed from: a */
    private void m4184a(int i, C1915h hVar, Bundle bundle) {
        if (!hVar.mo17135n()) {
            f2922d.execute(new C1828a(this.f2925a, hVar, bundle, i));
        }
    }

    /* renamed from: b */
    public void mo16788b() {
        C2241y.m5985a(this.f2925a).mo17940b((Object) this);
        C2117ef.m5566a(this.f2925a).mo17691b();
    }

    /* renamed from: a */
    static /* synthetic */ C2198t m4183a(C1827ag agVar, C2190r rVar, CounterConfiguration counterConfiguration, C1915h hVar) {
        if (rVar == null) {
            return null;
        }
        C2198t tVar = f2923e.get(rVar.toString());
        if (tVar == null) {
            tVar = new C2198t(agVar.f2925a, f2921c, rVar, counterConfiguration, f2924f);
            if (hVar == null || !C2208p.m5872a(hVar)) {
                f2923e.put(rVar.toString(), tVar);
            }
        } else {
            tVar.mo17846b(counterConfiguration);
        }
        return tVar;
    }

    /* renamed from: a */
    static /* synthetic */ boolean m4189a(C2198t tVar) {
        return tVar == null || tVar.mo17868o();
    }

    /* renamed from: a */
    static /* synthetic */ boolean m4190a(C2198t tVar, C1915h hVar) {
        if (C2208p.C2209a.EVENT_TYPE_STARTUP.mo17884a() == hVar.mo17118c()) {
            tVar.mo17854e();
            return true;
        } else if (C2208p.C2209a.EVENT_TYPE_REFERRER_RECEIVED.mo17884a() != hVar.mo17118c()) {
            return false;
        } else {
            tVar.mo17847b(hVar);
            return true;
        }
    }
}

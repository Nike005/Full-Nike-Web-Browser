package com.yandex.metrica.impl.p050ob;

import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import com.mopub.network.ImpressionData;
import com.yandex.metrica.impl.p050ob.C2169fu;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.locks.ReentrantLock;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.yandex.metrica.impl.ob.fh */
class C2149fh {
    /* access modifiers changed from: private */

    /* renamed from: a */
    public static final String f3705a = C2149fh.class.getSimpleName();

    /* renamed from: b */
    private C2143fb f3706b;

    /* renamed from: c */
    private C2143fb f3707c;

    /* renamed from: d */
    private C2164fs f3708d;

    /* renamed from: e */
    private Map<String, String> f3709e;
    /* access modifiers changed from: private */

    /* renamed from: f */
    public C2152fi f3710f;

    /* renamed from: g */
    private String f3711g;

    /* renamed from: h */
    private C2153fj f3712h;

    /* renamed from: i */
    private long f3713i;

    /* renamed from: j */
    private final ReentrantLock f3714j = new ReentrantLock();

    C2149fh(C2146fe feVar, C2138ey eyVar, C2164fs fsVar, C2145fd fdVar) {
        this.f3706b = eyVar.mo17713c();
        this.f3707c = eyVar.mo17711a();
        this.f3708d = fsVar;
        this.f3711g = fdVar.mo17742b();
        HashMap hashMap = new HashMap();
        this.f3709e = hashMap;
        hashMap.put("app_id", feVar.mo17750c());
        Map<String, String> map = this.f3709e;
        map.put("app_platform", "android_" + Build.VERSION.RELEASE);
        this.f3709e.put("manufacturer", Build.MANUFACTURER);
        this.f3709e.put("model", Build.MODEL);
        this.f3709e.put(ImpressionData.APP_VERSION, feVar.mo17748a());
        this.f3713i = fdVar.mo17740a();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public ReentrantLock mo17755a() {
        return this.f3714j;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public synchronized boolean mo17757b() {
        if (m5690j()) {
            Log.i(f3705a, "starting pins update on error");
            JSONObject g = m5687g();
            if (g != null) {
                return m5685a(g);
            }
            m5688h();
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: c */
    public synchronized void mo17758c() {
        if (mo17759d() && m5690j()) {
            Log.i(f3705a, "starting pins update on schedule");
            C2152fi i = m5689i();
            this.f3710f = i;
            this.f3708d.mo17773a(i, new C2169fu.C2171b<JSONObject>() {
                /* renamed from: a */
                public void mo17761a(JSONObject jSONObject) {
                    boolean unused = C2149fh.this.m5685a(jSONObject);
                    C2152fi unused2 = C2149fh.this.f3710f = null;
                }
            }, new C2169fu.C2170a() {
                /* renamed from: a */
                public void mo17763a(C2162fr frVar) {
                    String f = C2149fh.f3705a;
                    Log.i(f, "can't update pins on schedule: " + frVar.getMessage());
                    C2149fh.this.m5688h();
                    C2152fi unused = C2149fh.this.f3710f = null;
                }
            });
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public synchronized void mo17756a(C2153fj fjVar) {
        this.f3712h = fjVar;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: d */
    public boolean mo17759d() {
        if (!mo17760e()) {
            return m5683a(this.f3706b, this.f3713i) || m5683a(this.f3707c, this.f3713i);
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: e */
    public boolean mo17760e() {
        return this.f3710f != null;
    }

    /* renamed from: g */
    private JSONObject m5687g() {
        try {
            C2172fv a = C2172fv.m5746a();
            this.f3708d.mo17773a(m5689i(), a, a);
            return (JSONObject) a.get(30000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            String str = f3705a;
            Log.i(str, "can't update pins on error: " + e.getMessage());
            return null;
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public boolean m5685a(JSONObject jSONObject) {
        try {
            m5682a(jSONObject.getJSONArray("pins-sha256"), this.f3706b);
            m5682a(jSONObject.getJSONArray("blacklist"), this.f3707c);
            Log.i(f3705a, "pins have been updated");
            return true;
        } catch (JSONException e) {
            String str = f3705a;
            Log.i(str, "can't update pins: " + e.getMessage());
            return false;
        }
    }

    /* renamed from: a */
    private static void m5682a(JSONArray jSONArray, C2143fb fbVar) throws JSONException {
        fbVar.mo17733a();
        for (int i = 0; i < jSONArray.length(); i++) {
            fbVar.mo17734a(jSONArray.getString(i));
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: h */
    public void m5688h() {
        this.f3706b.mo17737d();
        this.f3707c.mo17737d();
    }

    /* renamed from: a */
    private static boolean m5683a(C2143fb fbVar, long j) {
        long currentTimeMillis = System.currentTimeMillis();
        return currentTimeMillis - fbVar.mo17736c() >= j || currentTimeMillis < fbVar.mo17736c();
    }

    /* renamed from: i */
    private C2152fi m5689i() {
        String a = this.f3712h.mo17506a();
        if (TextUtils.isEmpty(a)) {
            this.f3709e.remove("uuid");
        } else {
            this.f3709e.put("uuid", a);
        }
        return new C2152fi(this.f3711g, this.f3709e);
    }

    /* renamed from: j */
    private boolean m5690j() {
        return this.f3708d != null;
    }
}

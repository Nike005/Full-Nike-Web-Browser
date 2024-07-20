package com.moat.analytics.mobile.mpub;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.webkit.ValueCallback;
import android.webkit.WebView;
import com.google.firebase.messaging.Constants;
import com.moat.analytics.mobile.mpub.C0339s;
import com.moat.analytics.mobile.mpub.C0351w;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import org.htmlcleaner.CleanerProperties;
import org.json.JSONObject;

/* renamed from: com.moat.analytics.mobile.mpub.j */
class C0322j {
    /* access modifiers changed from: private */

    /* renamed from: a */
    public int f125a = 0;

    /* renamed from: b */
    private boolean f126b = false;

    /* renamed from: c */
    private boolean f127c = false;

    /* renamed from: d */
    private final AtomicBoolean f128d = new AtomicBoolean(false);
    /* access modifiers changed from: private */

    /* renamed from: e */
    public boolean f129e = false;
    /* access modifiers changed from: private */

    /* renamed from: f */
    public boolean f130f = false;

    /* renamed from: g */
    private boolean f131g = false;

    /* renamed from: h */
    private final WeakReference<WebView> f132h;

    /* renamed from: i */
    private final Map<C0304b, String> f133i;

    /* renamed from: j */
    private final LinkedList<String> f134j;
    /* access modifiers changed from: private */

    /* renamed from: k */
    public final long f135k;

    /* renamed from: l */
    private final String f136l;

    /* renamed from: m */
    private final List<String> f137m;

    /* renamed from: n */
    private final C0326a f138n;

    /* renamed from: o */
    private final BroadcastReceiver f139o = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            try {
                C0322j.this.m168d();
            } catch (Exception e) {
                C0330n.m214a(e);
            }
            if (System.currentTimeMillis() - C0322j.this.f135k > 30000) {
                C0322j.this.m183i();
            }
        }
    };

    /* renamed from: p */
    private final BroadcastReceiver f140p = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            try {
                C0322j.this.m172e();
            } catch (Exception e) {
                C0330n.m214a(e);
            }
        }
    };

    /* renamed from: com.moat.analytics.mobile.mpub.j$a */
    enum C0326a {
        WEBVIEW,
        NATIVE_DISPLAY,
        NATIVE_VIDEO
    }

    C0322j(WebView webView, C0326a aVar) {
        this.f132h = new WeakReference<>(webView);
        this.f138n = aVar;
        this.f134j = new LinkedList<>();
        this.f137m = new ArrayList();
        this.f133i = new WeakHashMap();
        this.f135k = System.currentTimeMillis();
        this.f136l = String.format("javascript:(function(d,k){function l(){function d(a,b){var c=ipkn[b]||ipkn[kuea];if(c){var h=function(b){var c=b.b;c.ts=b.i;c.ticks=b.g;c.buffered=!0;a(c)};h(c.first);c.a.forEach(function(a){h(a)})}}function e(a){var b=a.a,c=a.c,h=a.b;a=a.f;var d=[];if(c)b[c]&&d.push(b[c].fn[0]);else for(key in b)if(b[key])for(var g=0,e=b[key].fn.length;g<e;g++)d.push(b[key].fn[g]);g=0;for(e=d.length;g<e;g++){var f=d[g];if('function'===typeof f)try{h?f(h):f()}catch(k){}a&&delete b[c]}}function f(a,b,c){'function'===typeof a&& (b===kuea&&c[b]?c[b].fn.push(a):c[b]={ts:+new Date,fn:[a]},c===yhgt&&d(a,b))}kuea=+new Date;iymv={};briz=!1;ewat=+new Date;bnkr=[];bjmk={};dptk={};uqaj={};ryup={};yhgt={};ipkn={};csif={};this.h=function(a){this.namespace=a.namespace;this.version=a.version;this.appName=a.appName;this.deviceOS=a.deviceOS;this.isNative=a.isNative;this.versionHash=a.versionHash;this.aqzx=a.aqzx;this.appId=a.appId;this.metadata=a};this.nvsj=function(a){briz||(f(a,ewat,iymv),briz=!0)};this.bpsy=function(a,b){var c=b||kuea; c!==kuea&&bjmk[c]||f(a,c,bjmk)};this.qmrv=function(a,b){var c=b||kuea;c!==kuea&&uqaj[c]||f(a,c,uqaj)};this.lgpr=function(a,b){f(a,b||kuea,yhgt)};this.hgen=function(a,b){f(a,b||kuea,csif)};this.xrnk=function(a){delete yhgt[a||kuea]};this.vgft=function(a){return dptk[a||kuea]||!1};this.lkpu=function(a){return ryup[a||kuea]||!1};this.crts=function(a){var b={a:iymv,b:a,c:ewat};briz?e(b):bnkr.push(a)};this.mqjh=function(a){var b=a||kuea;dptk[b]=!0;var c={a:bjmk,f:!0};b!==kuea&&(c.b=a,c.c=a);e(c)};this.egpw= function(a){var b=a||kuea;ryup[b]=!0;var c={a:uqaj,f:!0};b!==kuea&&(c.b=a,c.c=a);e(c)};this.sglu=function(a){var b=a.adKey||kuea,c={a:yhgt,b:a.event||a,g:1,i:+new Date,f:!1};b!==kuea&&(c.c=a.adKey);a=0<Object.keys(yhgt).length;if(!a||!this.isNative)if(ipkn[b]){var d=ipkn[b].a.slice(-1)[0]||ipkn[b].first;JSON.stringify(c.b)==JSON.stringify(d.b)?d.g+=1:(5<=ipkn[b].a.length&&ipkn[b].a.shift(),ipkn[b].a.push(c))}else ipkn[b]={first:c,a:[]};a&&e(c);return a};this.ucbx=function(a){e({c:a.adKey||kuea,a:csif, b:a.event,f:!1})}}'undefined'===typeof d.MoatMAK&&(d.MoatMAK=new l,d.MoatMAK.h(k),d.__zMoatInit__=!0)})(window,%s);", new Object[]{m181h()});
        if (m171d("Initialize")) {
            IntentFilter intentFilter = new IntentFilter("UPDATE_METADATA");
            IntentFilter intentFilter2 = new IntentFilter("UPDATE_VIEW_INFO");
            C0319i.m142a().mo10427a(C0339s.m239b(), this.f139o, intentFilter);
            C0319i.m142a().mo10427a(C0339s.m239b(), this.f140p, intentFilter2);
            m168d();
            C0319i.m142a().mo10429a(C0339s.m239b(), this);
            C0336p.m228a(3, "JavaScriptBridge", (Object) this, "bridge initialization succeeded");
        }
    }

    /* renamed from: a */
    private boolean m162a(WebView webView) {
        return webView.getSettings().getJavaScriptEnabled();
    }

    /* renamed from: b */
    static /* synthetic */ int m164b(C0322j jVar) {
        int i = jVar.f125a;
        jVar.f125a = i + 1;
        return i;
    }

    /* renamed from: c */
    private void m166c() {
        for (Map.Entry<C0304b, String> key : this.f133i.entrySet()) {
            C0304b bVar = (C0304b) key.getKey();
            if (bVar.mo10389e()) {
                m180g(String.format("javascript: if(typeof MoatMAK !== 'undefined'){MoatMAK.mqjh(\"%s\");}", new Object[]{bVar.f65e}));
            }
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: d */
    public void m168d() {
        try {
            if (C0351w.m264a().f197a != C0351w.C0359d.OFF) {
                if (!this.f127c) {
                    C0336p.m228a(3, "JavaScriptBridge", (Object) this, "Attempting to establish communication (setting environment variables).");
                    this.f127c = true;
                }
                m180g(this.f136l);
            }
        } catch (Exception e) {
            C0336p.m230a("JavaScriptBridge", (Object) this, "Attempt failed to establish communication (did not set environment variables).", (Throwable) e);
        }
    }

    /* renamed from: d */
    private void m169d(C0304b bVar) {
        C0336p.m228a(3, "JavaScriptBridge", (Object) this, "Stopping view update loop");
        if (bVar != null) {
            C0319i.m142a().mo10430a(bVar);
        }
    }

    /* renamed from: d */
    private boolean m171d(String str) {
        WebView g = m178g();
        if (g == null) {
            C0336p.m228a(6, "JavaScriptBridge", (Object) this, "WebView is null. Can't " + str);
            throw new C0330n("WebView is null");
        } else if (m162a(g)) {
            return true;
        } else {
            C0336p.m228a(6, "JavaScriptBridge", (Object) this, "JavaScript is not enabled in the given WebView. Can't " + str);
            throw new C0330n("JavaScript is not enabled in the WebView");
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: e */
    public void m172e() {
        try {
            if (C0351w.m264a().f197a != C0351w.C0359d.OFF) {
                if (this.f131g) {
                    C0336p.m228a(3, "JavaScriptBridge", (Object) this, "Can't send info, already cleaned up");
                    return;
                }
                if (m177f()) {
                    if (!this.f126b || m178g().getUrl() != null) {
                        if (m178g().getUrl() != null) {
                            this.f126b = true;
                        }
                        for (Map.Entry<C0304b, String> key : this.f133i.entrySet()) {
                            C0304b bVar = (C0304b) key.getKey();
                            if (bVar == null || bVar.mo10390f() == null) {
                                C0336p.m228a(3, "JavaScriptBridge", (Object) this, "Tracker has no subject");
                                if (bVar != null) {
                                    if (!bVar.f66f) {
                                    }
                                }
                                mo10442c(bVar);
                            }
                            if (bVar.mo10389e()) {
                                if (!this.f128d.get()) {
                                    m180g(String.format("javascript: if(typeof MoatMAK !== 'undefined'){MoatMAK.mqjh(\"%s\");}", new Object[]{bVar.f65e}));
                                }
                                String format = String.format("javascript: if(typeof MoatMAK !== 'undefined'){MoatMAK.sglu(%s);}", new Object[]{bVar.mo10392h()});
                                if (Build.VERSION.SDK_INT >= 19) {
                                    m178g().evaluateJavascript(format, new ValueCallback<String>() {
                                        /* renamed from: a */
                                        public void onReceiveValue(String str) {
                                            String str2 = "null";
                                            if (str == null || str.equalsIgnoreCase(str2) || str.equalsIgnoreCase("false")) {
                                                C0322j jVar = C0322j.this;
                                                StringBuilder sb = new StringBuilder();
                                                sb.append("Received value is:");
                                                if (str != null) {
                                                    str2 = "(String)" + str;
                                                }
                                                sb.append(str2);
                                                C0336p.m228a(3, "JavaScriptBridge", (Object) jVar, sb.toString());
                                                if (C0322j.this.f125a >= 150) {
                                                    C0336p.m228a(3, "JavaScriptBridge", (Object) C0322j.this, "Giving up on finding ad");
                                                    C0322j.this.mo10439b();
                                                }
                                                C0322j.m164b(C0322j.this);
                                                if (str != null && str.equalsIgnoreCase("false") && !C0322j.this.f129e) {
                                                    boolean unused = C0322j.this.f129e = true;
                                                    C0336p.m228a(3, "JavaScriptBridge", (Object) C0322j.this, "Bridge connection established");
                                                }
                                            } else if (str.equalsIgnoreCase(CleanerProperties.BOOL_ATT_TRUE)) {
                                                if (!C0322j.this.f130f) {
                                                    boolean unused2 = C0322j.this.f130f = true;
                                                    C0336p.m228a(3, "JavaScriptBridge", (Object) C0322j.this, "Javascript has found ad");
                                                    C0322j.this.mo10435a();
                                                }
                                                int unused3 = C0322j.this.f125a = 0;
                                            } else {
                                                C0336p.m228a(3, "JavaScriptBridge", (Object) C0322j.this, "Received unusual value from Javascript:" + str);
                                            }
                                        }
                                    });
                                } else {
                                    m178g().loadUrl(format);
                                }
                            }
                        }
                        return;
                    }
                }
                StringBuilder sb = new StringBuilder();
                sb.append("WebView became null");
                sb.append(m178g() == null ? "" : "based on null url");
                sb.append(", stopping tracking loop");
                C0336p.m228a(3, "JavaScriptBridge", (Object) this, sb.toString());
                mo10439b();
            }
        } catch (Exception e) {
            C0330n.m214a(e);
            mo10439b();
        }
    }

    /* renamed from: e */
    private void m174e(String str) {
        if (this.f137m.size() >= 50) {
            this.f137m.subList(0, 25).clear();
        }
        this.f137m.add(str);
    }

    /* renamed from: f */
    private void m176f(String str) {
        if (this.f128d.get()) {
            m180g(str);
        } else {
            m174e(str);
        }
    }

    /* renamed from: f */
    private boolean m177f() {
        return m178g() != null;
    }

    /* renamed from: g */
    private WebView m178g() {
        return (WebView) this.f132h.get();
    }

    /* renamed from: g */
    private void m180g(String str) {
        if (this.f131g) {
            C0336p.m228a(3, "JavaScriptBridge", (Object) this, "Can't send, already cleaned up");
        } else if (m177f()) {
            C0336p.m232b(2, "JavaScriptBridge", this, str);
            if (Build.VERSION.SDK_INT >= 19) {
                m178g().evaluateJavascript(str, (ValueCallback) null);
            } else {
                m178g().loadUrl(str);
            }
        }
    }

    /* renamed from: h */
    private String m181h() {
        try {
            C0339s.C0341a c = C0339s.m241c();
            C0339s.C0342b d = C0339s.m242d();
            HashMap hashMap = new HashMap();
            String a = c.mo10460a();
            String b = c.mo10461b();
            String c2 = c.mo10462c();
            String num = Integer.toString(Build.VERSION.SDK_INT);
            String a2 = C0339s.m236a();
            String str = "0";
            String str2 = this.f138n == C0326a.WEBVIEW ? str : "1";
            String str3 = d.f190e ? "1" : str;
            if (d.f189d) {
                str = "1";
            }
            hashMap.put("versionHash", "422d7e65812d34458dfd0c5f14e8141470b6e2ae");
            hashMap.put("appName", a);
            hashMap.put("namespace", "MPUB");
            hashMap.put("version", "2.6.6");
            hashMap.put("deviceOS", num);
            hashMap.put("isNative", str2);
            hashMap.put("appId", b);
            hashMap.put(Constants.ScionAnalytics.PARAM_SOURCE, c2);
            hashMap.put("carrier", d.f187b);
            hashMap.put("sim", d.f186a);
            hashMap.put("phone", String.valueOf(d.f188c));
            hashMap.put("buildFp", Build.FINGERPRINT);
            hashMap.put("buildModel", Build.MODEL);
            hashMap.put("buildMfg", Build.MANUFACTURER);
            hashMap.put("buildBrand", Build.BRAND);
            hashMap.put("buildProduct", Build.PRODUCT);
            hashMap.put("buildTags", Build.TAGS);
            hashMap.put("f1", str);
            hashMap.put("f2", str3);
            if (a2 != null) {
                hashMap.put("aqzx", a2);
            }
            return new JSONObject(hashMap).toString();
        } catch (Exception unused) {
            return "{}";
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: i */
    public void m183i() {
        C0336p.m228a(3, "JavaScriptBridge", (Object) this, "Stopping metadata reporting loop");
        C0319i.m142a().mo10431a(this);
        C0319i.m142a().mo10426a(C0339s.m239b(), this.f139o);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo10435a() {
        C0336p.m228a(3, "JavaScriptBridge", (Object) this, "webViewReady");
        if (this.f128d.compareAndSet(false, true)) {
            C0336p.m228a(3, "JavaScriptBridge", (Object) this, "webViewReady first time");
            m183i();
            for (String g : this.f137m) {
                m180g(g);
            }
            this.f137m.clear();
        }
        m166c();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo10436a(C0304b bVar) {
        if (bVar != null) {
            C0336p.m228a(3, "JavaScriptBridge", (Object) this, "adding tracker" + bVar.f65e);
            this.f133i.put(bVar, "");
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo10437a(String str) {
        m176f(String.format("javascript: if(typeof MoatMAK !== 'undefined'){MoatMAK.crts(%s);}", new Object[]{str}));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo10438a(String str, JSONObject jSONObject) {
        if (this.f131g) {
            C0336p.m228a(3, "JavaScriptBridge", (Object) this, "Can't dispatch, already cleaned up");
            return;
        }
        String jSONObject2 = jSONObject.toString();
        if (!this.f128d.get() || !m177f()) {
            this.f134j.add(jSONObject2);
            return;
        }
        m180g(String.format("javascript:%s.dispatchEvent(%s);", new Object[]{str, jSONObject2}));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public void mo10439b() {
        C0336p.m228a(3, "JavaScriptBridge", (Object) this, "Cleaning up");
        this.f131g = true;
        m183i();
        for (Map.Entry<C0304b, String> key : this.f133i.entrySet()) {
            m169d((C0304b) key.getKey());
        }
        this.f133i.clear();
        C0319i.m142a().mo10426a(C0339s.m239b(), this.f140p);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public void mo10440b(C0304b bVar) {
        if (m171d("startTracking")) {
            C0336p.m228a(3, "JavaScriptBridge", (Object) this, "Starting tracking on tracker" + bVar.f65e);
            m180g(String.format("javascript: if(typeof MoatMAK !== 'undefined'){MoatMAK.mqjh(\"%s\");}", new Object[]{bVar.f65e}));
            C0319i.m142a().mo10428a(C0339s.m239b(), bVar);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public void mo10441b(String str) {
        C0336p.m228a(3, "JavaScriptBridge", (Object) this, "markUserInteractionEvent:" + str);
        m176f(String.format("javascript: if(typeof MoatMAK !== 'undefined'){MoatMAK.ucbx(%s);}", new Object[]{str}));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: c */
    public void mo10442c(C0304b bVar) {
        C0330n nVar = null;
        if (!this.f131g) {
            try {
                if (m171d("stopTracking")) {
                    try {
                        C0336p.m228a(3, "JavaScriptBridge", (Object) this, "Ending tracking on tracker" + bVar.f65e);
                        m180g(String.format("javascript: if(typeof MoatMAK !== 'undefined'){MoatMAK.egpw(\"%s\");}", new Object[]{bVar.f65e}));
                    } catch (Exception e) {
                        C0336p.m230a("JavaScriptBridge", (Object) this, "Failed to end impression.", (Throwable) e);
                    }
                }
            } catch (C0330n e2) {
                nVar = e2;
            }
            if (this.f138n == C0326a.NATIVE_DISPLAY) {
                m169d(bVar);
            } else {
                mo10439b();
            }
            this.f133i.remove(bVar);
        }
        if (nVar != null) {
            throw nVar;
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: c */
    public void mo10443c(String str) {
        C0336p.m228a(3, "JavaScriptBridge", (Object) this, "flushDispatchQueue");
        if (this.f134j.size() >= 200) {
            LinkedList linkedList = new LinkedList();
            for (int i = 0; i < 10; i++) {
                linkedList.addFirst(this.f134j.removeFirst());
            }
            int min = Math.min(Math.min(this.f134j.size() / 200, 10) + 200, this.f134j.size());
            for (int i2 = 0; i2 < min; i2++) {
                this.f134j.removeFirst();
            }
            Iterator it = linkedList.iterator();
            while (it.hasNext()) {
                this.f134j.addFirst((String) it.next());
            }
        }
        if (!this.f134j.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            String str2 = "";
            int i3 = 1;
            while (!this.f134j.isEmpty() && i3 < 200) {
                i3++;
                String removeFirst = this.f134j.removeFirst();
                if (sb.length() + removeFirst.length() > 2000) {
                    break;
                }
                sb.append(str2);
                sb.append(removeFirst);
                str2 = ",";
            }
            m180g(String.format("javascript:%s.dispatchMany([%s])", new Object[]{str, sb.toString()}));
        }
        this.f134j.clear();
    }

    /* access modifiers changed from: protected */
    public void finalize() {
        try {
            super.finalize();
            C0336p.m228a(3, "JavaScriptBridge", (Object) this, "finalize");
            mo10439b();
        } catch (Exception e) {
            C0330n.m214a(e);
        }
    }
}

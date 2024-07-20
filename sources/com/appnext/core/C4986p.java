package com.appnext.core;

import android.content.Context;
import android.os.AsyncTask;
import java.io.IOException;
import java.net.HttpRetryException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.appnext.core.p */
public abstract class C4986p {

    /* renamed from: hD */
    protected static final String f4851hD = "https://cdn.appnext.com/tools/sdk/confign";

    /* renamed from: aR */
    protected HashMap<String, String> f4852aR = null;

    /* renamed from: hE */
    protected HashMap<String, Object> f4853hE = null;

    /* renamed from: hF */
    private ArrayList<C4988a> f4854hF;
    /* access modifiers changed from: private */
    public int state = 0;

    /* renamed from: com.appnext.core.p$a */
    public interface C4988a {
        /* renamed from: b */
        void mo40595b(HashMap<String, Object> hashMap);

        void error(String str);
    }

    /* access modifiers changed from: protected */
    public abstract String getUrl();

    /* access modifiers changed from: protected */
    /* renamed from: n */
    public abstract HashMap<String, String> mo40628n();

    /* access modifiers changed from: protected */
    /* renamed from: o */
    public abstract HashMap<String, String> mo40629o();

    /* renamed from: a */
    public final synchronized void mo41299a(C4988a aVar) {
        mo41298a((Context) null, aVar);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0061, code lost:
        return;
     */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized void mo41298a(android.content.Context r6, com.appnext.core.C4986p.C4988a r7) {
        /*
            r5 = this;
            monitor-enter(r5)
            java.util.ArrayList<com.appnext.core.p$a> r0 = r5.f4854hF     // Catch:{ all -> 0x0062 }
            if (r0 != 0) goto L_0x000c
            java.util.ArrayList r0 = new java.util.ArrayList     // Catch:{ all -> 0x0062 }
            r0.<init>()     // Catch:{ all -> 0x0062 }
            r5.f4854hF = r0     // Catch:{ all -> 0x0062 }
        L_0x000c:
            int r0 = r5.state     // Catch:{ all -> 0x0062 }
            r1 = 2
            if (r0 != r1) goto L_0x001a
            if (r7 == 0) goto L_0x0060
            java.util.HashMap<java.lang.String, java.lang.Object> r6 = r5.f4853hE     // Catch:{ all -> 0x0062 }
            r7.mo40595b(r6)     // Catch:{ all -> 0x0062 }
            monitor-exit(r5)
            return
        L_0x001a:
            int r0 = r5.state     // Catch:{ all -> 0x0062 }
            if (r0 != 0) goto L_0x0059
            r0 = 1
            r5.state = r0     // Catch:{ all -> 0x0062 }
            java.lang.String r2 = r5.getUrl()     // Catch:{ all -> 0x0062 }
            if (r6 == 0) goto L_0x0030
            java.lang.String r3 = "pck"
            java.lang.String r6 = r6.getPackageName()     // Catch:{ all -> 0x0062 }
            r5.mo41305q(r3, r6)     // Catch:{ all -> 0x0062 }
        L_0x0030:
            java.lang.String r6 = "vid"
            java.lang.String r3 = com.appnext.core.C4967f.m6838bi()     // Catch:{ all -> 0x0062 }
            r5.mo41305q(r6, r3)     // Catch:{ all -> 0x0062 }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ all -> 0x0062 }
            java.lang.String r3 = "start loading config from "
            r6.<init>(r3)     // Catch:{ all -> 0x0062 }
            r6.append(r2)     // Catch:{ all -> 0x0062 }
            com.appnext.core.p$b r6 = new com.appnext.core.p$b     // Catch:{ all -> 0x0062 }
            r3 = 0
            r6.<init>()     // Catch:{ all -> 0x0062 }
            java.util.concurrent.Executor r3 = android.os.AsyncTask.THREAD_POOL_EXECUTOR     // Catch:{ all -> 0x0062 }
            java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch:{ all -> 0x0062 }
            r4 = 0
            r1[r4] = r2     // Catch:{ all -> 0x0062 }
            java.util.HashMap r2 = r5.mo40628n()     // Catch:{ all -> 0x0062 }
            r1[r0] = r2     // Catch:{ all -> 0x0062 }
            r6.executeOnExecutor(r3, r1)     // Catch:{ all -> 0x0062 }
        L_0x0059:
            if (r7 == 0) goto L_0x0060
            java.util.ArrayList<com.appnext.core.p$a> r6 = r5.f4854hF     // Catch:{ all -> 0x0062 }
            r6.add(r7)     // Catch:{ all -> 0x0062 }
        L_0x0060:
            monitor-exit(r5)
            return
        L_0x0062:
            r6 = move-exception
            monitor-exit(r5)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appnext.core.C4986p.mo41298a(android.content.Context, com.appnext.core.p$a):void");
    }

    /* renamed from: ad */
    private String m6882ad(String str) {
        HashMap<String, String> hashMap = this.f4852aR;
        if (hashMap == null) {
            return str;
        }
        for (String next : hashMap.keySet()) {
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            String str2 = "?";
            if (str.contains(str2)) {
                str2 = "&";
            }
            sb.append(str2);
            sb.append(next);
            sb.append("=");
            sb.append(this.f4852aR.get(next));
            str = sb.toString();
        }
        return str;
    }

    /* renamed from: a */
    public void mo40626a(HashMap<String, String> hashMap) {
        this.f4852aR = hashMap;
    }

    /* renamed from: q */
    public final void mo41305q(String str, String str2) {
        if (this.f4852aR == null) {
            this.f4852aR = new HashMap<>();
        }
        this.f4852aR.put(str, str2);
    }

    /* renamed from: r */
    public final void mo41306r(String str, String str2) {
        if (this.f4852aR == null) {
            this.f4852aR = new HashMap<>();
        }
        if (!this.f4852aR.containsKey(str)) {
            this.f4852aR.put(str, str2);
        }
    }

    /* renamed from: bn */
    public final HashMap<String, Object> mo41300bn() {
        return this.f4853hE;
    }

    /* renamed from: s */
    public final void mo41307s(String str, String str2) {
        if (this.f4853hE == null) {
            this.f4853hE = new HashMap<>();
        }
        this.f4853hE.put(str, str2);
    }

    public final boolean isLoaded() {
        return this.state == 2;
    }

    public final String get(String str) {
        String value = getValue(str);
        if (value != null) {
            return value;
        }
        if (mo40629o().containsKey(str)) {
            return mo40629o().get(str);
        }
        return null;
    }

    public final String get(String str, String str2) {
        return getValue(str) == null ? str2 : getValue(str);
    }

    /* renamed from: com.appnext.core.p$b */
    private class C4989b extends AsyncTask<Object, Void, String> {
        private C4989b() {
        }

        /* access modifiers changed from: protected */
        public final /* synthetic */ Object doInBackground(Object[] objArr) {
            return m6896a(objArr);
        }

        /* access modifiers changed from: protected */
        public final /* synthetic */ void onPostExecute(Object obj) {
            String str = (String) obj;
            super.onPostExecute(str);
            if (str == null) {
                int unused = C4986p.this.state = 0;
                C4986p.m6880a(C4986p.this, "unknown error");
            } else if (str.startsWith("error:")) {
                int unused2 = C4986p.this.state = 0;
                C4986p.m6880a(C4986p.this, str.substring(7));
            } else {
                try {
                    HashMap<String, Object> ae = C4986p.m6883ae(str);
                    if (C4986p.this.f4853hE == null) {
                        C4986p.this.f4853hE = ae;
                    } else {
                        C4986p.this.f4853hE.putAll(ae);
                    }
                    int unused3 = C4986p.this.state = 2;
                    C4986p.m6881a(C4986p.this, (HashMap) C4986p.this.f4853hE);
                } catch (Throwable th) {
                    new StringBuilder("error ").append(th.getMessage());
                    int unused4 = C4986p.this.state = 0;
                    C4986p.m6880a(C4986p.this, "parsing error");
                }
            }
        }

        /* renamed from: a */
        protected static String m6896a(Object... objArr) {
            try {
                return C4967f.m6815a(objArr[0], (HashMap<String, String>) objArr[1]);
            } catch (HttpRetryException e) {
                return "error: " + e.getReason();
            } catch (IOException unused) {
                return "error: network problem";
            } catch (Throwable unused2) {
                return "error: Internal error";
            }
        }

        /* access modifiers changed from: protected */
        /* renamed from: ag */
        public final void mo41308ag(String str) {
            super.onPostExecute(str);
            if (str == null) {
                int unused = C4986p.this.state = 0;
                C4986p.m6880a(C4986p.this, "unknown error");
            } else if (str.startsWith("error:")) {
                int unused2 = C4986p.this.state = 0;
                C4986p.m6880a(C4986p.this, str.substring(7));
            } else {
                try {
                    HashMap<String, Object> ae = C4986p.m6883ae(str);
                    if (C4986p.this.f4853hE == null) {
                        C4986p.this.f4853hE = ae;
                    } else {
                        C4986p.this.f4853hE.putAll(ae);
                    }
                    int unused3 = C4986p.this.state = 2;
                    C4986p.m6881a(C4986p.this, (HashMap) C4986p.this.f4853hE);
                } catch (Throwable th) {
                    new StringBuilder("error ").append(th.getMessage());
                    int unused4 = C4986p.this.state = 0;
                    C4986p.m6880a(C4986p.this, "parsing error");
                }
            }
        }
    }

    /* renamed from: ae */
    protected static HashMap<String, Object> m6883ae(String str) throws JSONException {
        HashMap<String, Object> hashMap = new HashMap<>();
        JSONObject jSONObject = new JSONObject(str);
        Iterator<String> keys = jSONObject.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            String string = jSONObject.getString(next);
            try {
                JSONObject jSONObject2 = new JSONObject(string);
                Iterator<String> keys2 = jSONObject2.keys();
                while (keys2.hasNext()) {
                    String next2 = keys2.next();
                    String string2 = jSONObject2.getString(next2);
                    hashMap.put(next + "_" + next2, string2);
                }
            } catch (Throwable unused) {
                hashMap.put(next, string);
            }
        }
        return hashMap;
    }

    /* renamed from: af */
    private void m6884af(String str) {
        synchronized ("https://cdn.appnext.com/tools/sdk/confign") {
            Iterator it = new ArrayList(this.f4854hF).iterator();
            while (it.hasNext()) {
                C4988a aVar = (C4988a) it.next();
                if (aVar != null) {
                    aVar.error(str);
                }
            }
            this.f4854hF.clear();
        }
    }

    /* renamed from: e */
    private void m6885e(HashMap<String, Object> hashMap) {
        synchronized ("https://cdn.appnext.com/tools/sdk/confign") {
            Iterator it = new ArrayList(this.f4854hF).iterator();
            while (it.hasNext()) {
                ((C4988a) it.next()).mo40595b(hashMap);
            }
            this.f4854hF.clear();
        }
    }

    public final String getValue(String str) {
        HashMap<String, Object> hashMap = this.f4853hE;
        if (hashMap != null && hashMap.containsKey(str)) {
            return (String) this.f4853hE.get(str);
        }
        return null;
    }

    /* renamed from: a */
    static /* synthetic */ void m6880a(C4986p pVar, String str) {
        synchronized ("https://cdn.appnext.com/tools/sdk/confign") {
            Iterator it = new ArrayList(pVar.f4854hF).iterator();
            while (it.hasNext()) {
                C4988a aVar = (C4988a) it.next();
                if (aVar != null) {
                    aVar.error(str);
                }
            }
            pVar.f4854hF.clear();
        }
    }

    /* renamed from: a */
    static /* synthetic */ void m6881a(C4986p pVar, HashMap hashMap) {
        synchronized ("https://cdn.appnext.com/tools/sdk/confign") {
            Iterator it = new ArrayList(pVar.f4854hF).iterator();
            while (it.hasNext()) {
                ((C4988a) it.next()).mo40595b(hashMap);
            }
            pVar.f4854hF.clear();
        }
    }
}

package com.yandex.metrica.impl;

import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.yandex.metrica.DeferredDeeplinkParametersListener;
import com.yandex.metrica.impl.p050ob.C2014bz;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.yandex.metrica.impl.ar */
public class C1853ar {

    /* renamed from: a */
    private final boolean f2976a;

    /* renamed from: b */
    private final C1868ay f2977b;

    /* renamed from: c */
    private final C2014bz f2978c;

    /* renamed from: d */
    private String f2979d;

    /* renamed from: e */
    private Map<String, String> f2980e;

    /* renamed from: f */
    private DeferredDeeplinkParametersListener f2981f;

    /* renamed from: g */
    private Handler f2982g;

    public C1853ar(C1868ay ayVar, C2014bz bzVar) {
        this.f2977b = ayVar;
        this.f2978c = bzVar;
        this.f2979d = bzVar.mo17332c();
        boolean d = bzVar.mo17336d();
        this.f2976a = d;
        if (d) {
            this.f2978c.mo17351p((String) null);
            this.f2979d = null;
            return;
        }
        m4310d(mo16863b(this.f2979d));
    }

    /* renamed from: a */
    public void mo16862a(String str) {
        this.f2977b.mo16919b(str);
        if (!this.f2976a) {
            synchronized (this) {
                this.f2979d = str;
                this.f2978c.mo17351p(str);
                m4310d(mo16863b(str));
                if (this.f2982g == null) {
                    this.f2982g = new Handler(Looper.getMainLooper());
                }
                this.f2982g.post(new Runnable() {
                    public void run() {
                        C1853ar.this.m4307a();
                    }
                });
            }
        }
    }

    /* renamed from: d */
    private void m4310d(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.f2980e = mo16864c(str);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public String mo16863b(String str) {
        return m4311e(str).get("appmetrica_deep_link");
    }

    /* access modifiers changed from: package-private */
    /* renamed from: c */
    public Map<String, String> mo16864c(String str) {
        Map<String, String> e = m4311e(Uri.decode(str));
        HashMap hashMap = new HashMap(e.size());
        for (Map.Entry next : e.entrySet()) {
            hashMap.put(Uri.decode((String) next.getKey()), Uri.decode((String) next.getValue()));
        }
        return hashMap;
    }

    /* renamed from: e */
    private static Map<String, String> m4311e(String str) {
        HashMap hashMap = new HashMap();
        if (str != null) {
            int lastIndexOf = str.lastIndexOf(63);
            if (lastIndexOf >= 0) {
                str = str.substring(lastIndexOf + 1);
            }
            if (str.contains("=")) {
                for (String str2 : str.split("&")) {
                    int indexOf = str2.indexOf("=");
                    if (indexOf >= 0) {
                        hashMap.put(str2.substring(0, indexOf), str2.substring(indexOf + 1));
                    } else {
                        hashMap.put(str2, "");
                    }
                }
            }
        }
        return hashMap;
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public void m4307a() {
        if (!C1897bk.m4653a((Map) this.f2980e)) {
            DeferredDeeplinkParametersListener deferredDeeplinkParametersListener = this.f2981f;
            if (deferredDeeplinkParametersListener != null) {
                deferredDeeplinkParametersListener.onParametersLoaded(this.f2980e);
                this.f2981f = null;
            }
        } else if (this.f2979d != null) {
            m4308a(DeferredDeeplinkParametersListener.Error.PARSE_ERROR);
        }
    }

    /* renamed from: a */
    private void m4308a(DeferredDeeplinkParametersListener.Error error) {
        DeferredDeeplinkParametersListener deferredDeeplinkParametersListener = this.f2981f;
        if (deferredDeeplinkParametersListener != null) {
            deferredDeeplinkParametersListener.onError(error, this.f2979d);
            this.f2981f = null;
        }
    }

    /* renamed from: a */
    public synchronized void mo16861a(DeferredDeeplinkParametersListener deferredDeeplinkParametersListener) {
        try {
            this.f2981f = deferredDeeplinkParametersListener;
            if (this.f2976a) {
                m4308a(DeferredDeeplinkParametersListener.Error.NOT_A_FIRST_LAUNCH);
            } else {
                m4307a();
            }
            this.f2978c.mo17337e();
        } catch (Throwable th) {
            this.f2978c.mo17337e();
            throw th;
        }
    }
}

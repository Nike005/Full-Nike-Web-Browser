package com.yandex.metrica.impl.p050ob;

import android.content.Context;
import android.text.TextUtils;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import javax.net.ssl.SSLSocketFactory;

/* renamed from: com.yandex.metrica.impl.ob.co */
public class C2041co {

    /* renamed from: a */
    String f3432a;
    /* access modifiers changed from: private */

    /* renamed from: b */
    public String f3433b;

    /* renamed from: c */
    private SSLSocketFactory f3434c;

    /* renamed from: d */
    private C2153fj f3435d = new C2153fj() {
        /* renamed from: a */
        public String mo17506a() {
            return C2041co.this.f3433b;
        }
    };

    /* renamed from: com.yandex.metrica.impl.ob.co$a */
    private static class C2043a {

        /* renamed from: a */
        static final C2041co f3437a = new C2041co();
    }

    /* renamed from: a */
    public static C2041co m5269a() {
        return C2043a.f3437a;
    }

    C2041co() {
    }

    /* renamed from: b */
    public synchronized SSLSocketFactory mo17504b() {
        return this.f3434c;
    }

    /* renamed from: c */
    public synchronized boolean mo17505c() {
        return this.f3434c != null;
    }

    /* renamed from: d */
    private static X509Certificate m5271d() {
        try {
            String[] a = C1930a.m4806a();
            if (a == null || a.length <= 0) {
                return null;
            }
            return C2136ex.m5643a(a[0]);
        } catch (Exception unused) {
            return null;
        }
    }

    /* renamed from: a */
    public synchronized void mo17503a(Context context, String str, String str2) {
        String str3;
        if (TextUtils.isEmpty(str2)) {
            str3 = "https://certificate.mobile.yandex.net/api/v1/pins";
        } else {
            str3 = str2 + "/api/v1/pins";
        }
        boolean z = false;
        if (!TextUtils.isEmpty(str)) {
            if (!(mo17505c() && str3.equals(this.f3432a))) {
                z = true;
            }
        }
        if (z) {
            this.f3433b = str;
            this.f3432a = str3;
            C2145fd fdVar = new C2145fd(this.f3435d, true, true);
            X509Certificate d = m5271d();
            if (d != null) {
                ArrayList arrayList = new ArrayList();
                arrayList.add(d);
                fdVar.mo17741a(this.f3432a, arrayList);
                try {
                    this.f3434c = new C2139ez(new C2134ew(context, fdVar)).mo17732a().getSocketFactory();
                } catch (Exception unused) {
                }
            }
        }
    }
}

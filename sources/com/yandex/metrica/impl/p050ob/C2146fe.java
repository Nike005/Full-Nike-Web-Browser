package com.yandex.metrica.impl.p050ob;

import android.content.Context;
import android.content.pm.PackageManager;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.yandex.metrica.impl.ob.fe */
class C2146fe {

    /* renamed from: a */
    private String f3699a;

    /* renamed from: b */
    private String f3700b;

    /* renamed from: c */
    private String f3701c;

    C2146fe(Context context) {
        try {
            this.f3699a = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException unused) {
            this.f3699a = "0.0";
        }
        this.f3700b = context.getFilesDir().getAbsolutePath();
        this.f3701c = context.getPackageName();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public String mo17748a() {
        return this.f3699a;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public String mo17749b() {
        return this.f3700b;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: c */
    public String mo17750c() {
        return this.f3701c;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public C2164fs mo17747a(List<X509Certificate> list) throws GeneralSecurityException, IOException {
        return C2148fg.m5677a(list);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: d */
    public C2164fs mo17751d() throws GeneralSecurityException, IOException {
        ArrayList arrayList = new ArrayList();
        for (String a : C1930a.m4806a()) {
            arrayList.add(C2136ex.m5643a(a));
        }
        return C2148fg.m5677a((List<X509Certificate>) arrayList);
    }
}

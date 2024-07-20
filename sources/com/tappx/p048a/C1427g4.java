package com.tappx.p048a;

import android.content.Context;
import android.content.pm.PackageManager;
import android.webkit.WebView;

/* renamed from: com.tappx.a.g4 */
public class C1427g4 {

    /* renamed from: a */
    private static final String f1880a = (C1555o4.f2151b + C1461i4.f1946b);

    /* renamed from: b */
    private static int f1881b = 0;

    public C1427g4(Context context) {
        f1881b = m2745a(context);
    }

    /* renamed from: a */
    private int m2745a(Context context) {
        int i = f1881b;
        if (i != 0) {
            return i;
        }
        try {
            int i2 = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).applicationInfo.targetSdkVersion;
            f1881b = i2;
            return i2;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return 0;
        }
    }

    /* renamed from: b */
    private boolean m2747b() {
        return m2746a();
    }

    /* renamed from: a */
    public void mo15834a(WebView webView) {
        if (!m2747b()) {
            webView.loadUrl("javascript:" + C1461i4.f1945a);
        }
    }

    /* renamed from: a */
    private boolean m2746a() {
        return f1881b >= 24;
    }

    /* renamed from: a */
    public String mo15833a(String str) {
        return m2747b() ? str.replaceFirst(C1555o4.f2150a, f1880a) : str;
    }
}

package com.tappx.p048a;

import android.os.Build;
import android.webkit.WebView;
import com.tappx.p048a.C1613s4;

/* renamed from: com.tappx.a.x4 */
public class C1692x4 {
    /* renamed from: a */
    public static void m3610a(WebView webView) {
        if (Build.VERSION.SDK_INT >= 11) {
            webView.onResume();
            return;
        }
        try {
            new C1613s4.C1614a(webView, "onResume").mo16133b().mo16132a();
        } catch (Exception unused) {
        }
    }

    /* renamed from: a */
    public static void m3611a(WebView webView, boolean z) {
        if (z) {
            webView.stopLoading();
            webView.loadUrl("");
        }
        if (Build.VERSION.SDK_INT >= 11) {
            webView.onPause();
            return;
        }
        try {
            new C1613s4.C1614a(webView, "onPause").mo16133b().mo16132a();
        } catch (Exception unused) {
        }
    }
}

package com.p088b.p089a.p090a.p091a.p099g;

import android.webkit.WebView;

/* renamed from: com.b.a.a.a.g.b */
public class C5145b extends C5143a {
    public C5145b(WebView webView) {
        if (webView != null && !webView.getSettings().getJavaScriptEnabled()) {
            webView.getSettings().setJavaScriptEnabled(true);
        }
        mo41905a(webView);
    }
}

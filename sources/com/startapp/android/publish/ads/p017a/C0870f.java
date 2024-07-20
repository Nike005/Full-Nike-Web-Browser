package com.startapp.android.publish.ads.p017a;

import android.os.Handler;
import android.webkit.WebView;
import com.mopub.common.AdType;

/* renamed from: com.startapp.android.publish.ads.a.f */
/* compiled from: StartAppSDK */
public class C0870f extends C0855c {
    /* renamed from: a */
    public void mo13802a(WebView webView) {
        super.mo13802a(webView);
        if (mo13777g().equals(AdType.INTERSTITIAL)) {
            webView.setBackgroundColor(0);
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: c */
    public void mo13807c(final WebView webView) {
        new Handler().postDelayed(new Runnable() {
            public void run() {
                try {
                    webView.setBackgroundColor(0);
                } catch (Exception unused) {
                }
            }
        }, 1000);
    }
}

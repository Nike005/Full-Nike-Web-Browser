package com.tappx.p048a;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import com.tappx.p048a.C1568p3;
import com.tappx.sdk.android.PrivacyConsentActivity;
import java.util.concurrent.TimeUnit;

/* renamed from: com.tappx.a.l2 */
public final class C1506l2 {

    /* renamed from: h */
    private static final long f2038h = TimeUnit.SECONDS.toMillis(4);
    /* access modifiers changed from: private */

    /* renamed from: a */
    public final C1536n2 f2039a;
    /* access modifiers changed from: private */

    /* renamed from: b */
    public C1568p3 f2040b;
    /* access modifiers changed from: private */

    /* renamed from: c */
    public final PrivacyConsentActivity f2041c;

    /* renamed from: d */
    private WebView f2042d;

    /* renamed from: e */
    private final C1524m2 f2043e;

    /* renamed from: f */
    private C1568p3.C1574f f2044f = new C1508b();

    /* renamed from: g */
    private final WebViewClient f2045g = new C1509c();

    /* renamed from: com.tappx.a.l2$a */
    class C1507a implements Runnable {
        C1507a() {
        }

        public void run() {
            C1506l2.this.f2040b.setCloseEnabled(true);
        }
    }

    /* renamed from: com.tappx.a.l2$b */
    class C1508b implements C1568p3.C1574f {
        C1508b() {
        }

        /* renamed from: a */
        public void mo15700a() {
            C1506l2.this.m2976d();
        }
    }

    /* renamed from: com.tappx.a.l2$c */
    class C1509c extends WebViewClient {
        C1509c() {
        }

        public void onPageFinished(WebView webView, String str) {
            super.onPageFinished(webView, str);
        }

        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            super.onPageStarted(webView, str, bitmap);
        }

        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            if ("tappx://consent?yes".equals(str)) {
                C1506l2.this.f2039a.mo15992g();
                return true;
            } else if ("tappx://consent?no".equals(str)) {
                C1506l2.this.f2039a.mo15991f();
                return true;
            } else if ("tappx://close".equals(str)) {
                C1506l2.this.m2976d();
                return true;
            } else {
                if (!TextUtils.isEmpty(str)) {
                    C1506l2.this.f2041c.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
                }
                return super.shouldOverrideUrlLoading(webView, str);
            }
        }
    }

    public C1506l2(PrivacyConsentActivity privacyConsentActivity) {
        this.f2041c = privacyConsentActivity;
        C1552o2 a = C1552o2.m3165a(privacyConsentActivity);
        this.f2039a = a.mo16036c();
        this.f2043e = a.mo16035b();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0006, code lost:
        r0 = r0.getLanguage();
     */
    /* renamed from: e */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String m2977e() {
        /*
            r1 = this;
            java.util.Locale r0 = java.util.Locale.getDefault()
            if (r0 == 0) goto L_0x0011
            java.lang.String r0 = r0.getLanguage()
            if (r0 == 0) goto L_0x0011
            java.lang.String r0 = r0.toUpperCase()
            return r0
        L_0x0011:
            java.lang.String r0 = "EN"
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tappx.p048a.C1506l2.m2977e():java.lang.String");
    }

    /* renamed from: f */
    private String m2978f() {
        String stringExtra = this.f2041c.getIntent().getStringExtra("GR8QbFbIwPD6k5hAnMxS6Za9cNsNHXXZzG7GWfNC");
        if (stringExtra == null) {
            return null;
        }
        return Uri.parse(stringExtra).buildUpon().appendQueryParameter(C1400f.m2603b("Atea2vjkWMaKJqXPDr3CPg"), m2977e()).build().toString();
    }

    /* renamed from: g */
    private View m2979g() {
        this.f2040b = new C1568p3(this.f2041c);
        WebView c = m2973c();
        this.f2042d = c;
        this.f2040b.addView(c, 0, new FrameLayout.LayoutParams(-1, -1));
        return this.f2040b;
    }

    /* renamed from: h */
    private void m2980h() {
        this.f2040b.setCloseEnabled(false);
        this.f2040b.setCloseListener(this.f2044f);
        this.f2040b.postDelayed(new C1507a(), f2038h);
    }

    /* renamed from: i */
    private void m2981i() {
        this.f2041c.requestWindowFeature(1);
        this.f2041c.getWindow().addFlags(1024);
        this.f2041c.setContentView(m2979g());
        m2980h();
    }

    /* renamed from: j */
    private void m2982j() {
        String stringExtra = this.f2041c.getIntent().getStringExtra("kuutYDJOjEGYmzrvCGMIZqwyDXtIZYWxcXzXexLx");
        String f = m2978f();
        if (stringExtra != null && !stringExtra.isEmpty()) {
            this.f2042d.loadDataWithBaseURL("https://tappx.com/", stringExtra, "text/html", "UTF-8", (String) null);
        } else if (f != null) {
            this.f2042d.loadUrl(f);
        } else {
            this.f2041c.finish();
        }
    }

    /* renamed from: a */
    public static Intent m2970a(Context context, String str, String str2) {
        Intent intent = new Intent(context, PrivacyConsentActivity.class);
        intent.putExtra("GR8QbFbIwPD6k5hAnMxS6Za9cNsNHXXZzG7GWfNC", str);
        intent.putExtra("kuutYDJOjEGYmzrvCGMIZqwyDXtIZYWxcXzXexLx", str2);
        return intent;
    }

    /* renamed from: c */
    private WebView m2973c() {
        WebView webView = new WebView(this.f2041c);
        webView.setVerticalScrollBarEnabled(false);
        webView.setHorizontalScrollBarEnabled(false);
        WebSettings settings = webView.getSettings();
        settings.setSupportZoom(false);
        settings.setBuiltInZoomControls(false);
        settings.setLoadsImagesAutomatically(true);
        settings.setLoadWithOverviewMode(true);
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setAppCacheEnabled(true);
        settings.setAppCachePath(this.f2041c.getCacheDir().getAbsolutePath());
        settings.setAllowFileAccess(false);
        settings.setAllowContentAccess(false);
        if (Build.VERSION.SDK_INT >= 16) {
            settings.setAllowUniversalAccessFromFileURLs(false);
        }
        if (Build.VERSION.SDK_INT >= 17) {
            webView.setId(View.generateViewId());
        }
        webView.setWebViewClient(this.f2045g);
        return webView;
    }

    /* access modifiers changed from: private */
    /* renamed from: d */
    public void m2976d() {
        this.f2041c.finish();
    }

    /* renamed from: b */
    public void mo15934b() {
        this.f2043e.mo15966a();
    }

    /* renamed from: a */
    public void mo15932a(Bundle bundle) {
        m2981i();
        m2982j();
    }

    /* renamed from: a */
    public boolean mo15933a() {
        return !this.f2040b.mo16057b();
    }
}

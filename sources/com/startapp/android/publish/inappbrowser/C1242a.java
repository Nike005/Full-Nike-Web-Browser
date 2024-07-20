package com.startapp.android.publish.inappbrowser;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.os.Build;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import com.google.android.gms.cast.RemoteMediaPlayer;
import com.startapp.android.publish.ads.p017a.C0851b;
import com.startapp.android.publish.adsCommon.C1103c;
import com.startapp.android.publish.adsCommon.Utils.C1060h;
import com.startapp.android.publish.adsCommon.p033f.C1130d;
import com.startapp.android.publish.adsCommon.p033f.C1132f;
import com.startapp.common.p043a.C1261c;
import com.startapp.common.p043a.C1270g;

/* renamed from: com.startapp.android.publish.inappbrowser.a */
/* compiled from: StartAppSDK */
public class C1242a extends C0851b implements View.OnClickListener {

    /* renamed from: j */
    protected static boolean f1433j = false;

    /* renamed from: d */
    protected RelativeLayout f1434d;

    /* renamed from: e */
    protected C1245b f1435e;

    /* renamed from: f */
    protected WebView f1436f;

    /* renamed from: g */
    protected AnimatingProgressBar f1437g;

    /* renamed from: h */
    protected FrameLayout f1438h;

    /* renamed from: i */
    protected String f1439i;

    /* renamed from: s */
    public void mo13789s() {
    }

    /* renamed from: u */
    public void mo13791u() {
    }

    public C1242a(String str) {
        this.f1439i = str;
    }

    /* renamed from: a */
    public void mo13760a(Bundle bundle) {
        super.mo13760a(bundle);
        f1433j = false;
        this.f1434d = new RelativeLayout(mo13768b());
        m1947b(this.f1439i);
        if (bundle != null) {
            mo13773c(bundle);
        }
        mo13768b().setContentView(this.f1434d, new RelativeLayout.LayoutParams(-2, -2));
    }

    /* renamed from: b */
    private void m1947b(String str) {
        C1270g.m2078a("IABrowserMode", 3, "initUi");
        if (this.f1435e == null) {
            C1245b bVar = new C1245b(mo13768b());
            this.f1435e = bVar;
            bVar.mo15421a();
            this.f1435e.mo15423b();
            this.f1435e.setButtonsListener(this);
        }
        this.f1434d.addView(this.f1435e);
        this.f1437g = new AnimatingProgressBar(mo13768b(), (AttributeSet) null, 16842872);
        ShapeDrawable shapeDrawable = new ShapeDrawable(new RectShape());
        shapeDrawable.getPaint().setColor(Color.parseColor("#45d200"));
        this.f1437g.setProgressDrawable(new ClipDrawable(shapeDrawable, 3, 1));
        this.f1437g.setBackgroundColor(-1);
        this.f1437g.setId(2108);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, C1060h.m1162a((Context) mo13768b(), 4));
        layoutParams.addRule(3, RemoteMediaPlayer.STATUS_CANCELED);
        this.f1434d.addView(this.f1437g, layoutParams);
        this.f1438h = new FrameLayout(mo13768b());
        if (this.f1436f == null) {
            try {
                m1948y();
                this.f1436f.loadUrl(str);
            } catch (Exception e) {
                this.f1435e.mo15425c();
                C1103c.m1398c(mo13768b(), str);
                C1132f.m1527a(mo13768b(), C1130d.EXCEPTION, "IABrowserMode.initUi - Webvie  failed", e.getMessage(), "");
                mo13768b().finish();
            }
        }
        this.f1438h.addView(this.f1436f);
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-1, -1);
        layoutParams2.addRule(15);
        layoutParams2.addRule(3, 2108);
        this.f1434d.addView(this.f1438h, layoutParams2);
    }

    /* renamed from: b */
    public void mo13770b(Bundle bundle) {
        this.f1436f.saveState(bundle);
    }

    /* renamed from: c */
    public void mo13773c(Bundle bundle) {
        this.f1436f.restoreState(bundle);
    }

    /* renamed from: y */
    private void m1948y() {
        this.f1436f = new WebView(mo13768b());
        m1949z();
        this.f1436f.setWebViewClient(new C1244a(mo13768b(), this.f1435e, this.f1437g, this));
        this.f1436f.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView webView, int i) {
                C1242a.this.f1437g.setProgress(i);
            }

            public void onReceivedTitle(WebView webView, String str) {
                if (str != null && !str.equals("")) {
                    C1242a.this.f1435e.getTitleTxt().setText(str);
                }
            }
        });
    }

    /* renamed from: z */
    private void m1949z() {
        this.f1436f.getSettings().setJavaScriptEnabled(true);
        this.f1436f.getSettings().setUseWideViewPort(true);
        this.f1436f.getSettings().setLoadWithOverviewMode(true);
        this.f1436f.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        this.f1436f.getSettings().setBuiltInZoomControls(true);
        if (Build.VERSION.SDK_INT >= 11) {
            this.f1436f.getSettings().setDisplayZoomControls(false);
        }
    }

    /* renamed from: com.startapp.android.publish.inappbrowser.a$a */
    /* compiled from: StartAppSDK */
    private static class C1244a extends WebViewClient {

        /* renamed from: a */
        private Context f1441a;

        /* renamed from: b */
        private C1242a f1442b;

        /* renamed from: c */
        private C1245b f1443c;

        /* renamed from: d */
        private AnimatingProgressBar f1444d;

        /* renamed from: e */
        private int f1445e = 0;

        /* renamed from: f */
        private boolean f1446f = false;

        public C1244a(Context context, C1245b bVar, AnimatingProgressBar animatingProgressBar, C1242a aVar) {
            this.f1441a = context;
            this.f1444d = animatingProgressBar;
            this.f1443c = bVar;
            this.f1442b = aVar;
        }

        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            if (!C1242a.f1433j) {
                C1270g.m2078a("IABrowserMode", 3, "IABWebViewClient::onPageStarted - [" + str + "]" + "REDIRECTED  -> " + this.f1445e + " Can go back " + webView.canGoBack());
                if (this.f1446f) {
                    this.f1445e = 1;
                    this.f1444d.mo15408a();
                    this.f1443c.mo15422a(webView);
                } else {
                    this.f1445e = Math.max(this.f1445e, 1);
                }
                this.f1444d.setVisibility(0);
                this.f1443c.getUrlTxt().setText(str);
                this.f1443c.mo15422a(webView);
                super.onPageStarted(webView, str, bitmap);
            }
        }

        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            C1270g.m2078a("IABrowserMode", 3, "IABWebViewClient::shouldOverrideUrlLoading - [" + str + "]");
            if (!C1242a.f1433j) {
                if (!this.f1446f) {
                    this.f1446f = true;
                    this.f1444d.mo15408a();
                    this.f1445e = 0;
                }
                this.f1445e++;
                if (C1103c.m1403d(str) && !C1103c.m1397b(str)) {
                    return false;
                }
                this.f1445e = 1;
                C1103c.m1398c(this.f1441a, str);
                C1242a aVar = this.f1442b;
                if (aVar != null) {
                    aVar.mo15414x();
                }
            }
            return true;
        }

        public void onPageFinished(WebView webView, String str) {
            if (!C1242a.f1433j) {
                C1270g.m2078a("IABrowserMode", 3, "IABWebViewClient::onPageFinished - [" + str + "]");
                this.f1443c.mo15422a(webView);
                int i = this.f1445e + -1;
                this.f1445e = i;
                if (i == 0) {
                    this.f1446f = false;
                    this.f1444d.mo15408a();
                    if (this.f1444d.isShown()) {
                        this.f1444d.setVisibility(8);
                    }
                    this.f1443c.mo15422a(webView);
                }
                super.onPageFinished(webView, str);
            }
        }

        public void onReceivedError(WebView webView, int i, String str, String str2) {
            C1270g.m2078a("IABrowserMode", 3, "IABWebViewClient::onReceivedError - [" + str + "], [" + str2 + "]");
            this.f1444d.mo15408a();
            super.onReceivedError(webView, i, str, str2);
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case 2103:
                mo15414x();
                return;
            case 2104:
                if (this.f1436f != null) {
                    C1103c.m1398c(mo13768b(), this.f1436f.getUrl());
                    mo15414x();
                    return;
                }
                return;
            case 2105:
                WebView webView = this.f1436f;
                if (webView != null && webView.canGoBack()) {
                    this.f1437g.mo15408a();
                    this.f1436f.goBack();
                    return;
                }
                return;
            case 2106:
                WebView webView2 = this.f1436f;
                if (webView2 != null && webView2.canGoForward()) {
                    this.f1437g.mo15408a();
                    this.f1436f.goForward();
                    return;
                }
                return;
            default:
                return;
        }
    }

    /* renamed from: a */
    public boolean mo13767a(int i, KeyEvent keyEvent) {
        if (keyEvent.getAction() != 0 || i != 4) {
            return super.mo13767a(i, keyEvent);
        }
        WebView webView = this.f1436f;
        if (webView == null || !webView.canGoBack()) {
            C1270g.m2078a("IABrowserMode", 3, "IABWebViewClient::KEYCODE_BACK canT go back");
            mo15414x();
            return true;
        }
        C1270g.m2078a("IABrowserMode", 3, "IABWebViewClient::KEYCODE_BACK can go back");
        this.f1437g.mo15408a();
        this.f1436f.goBack();
        return true;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: x */
    public void mo15414x() {
        m1946A();
        this.f1435e.mo15425c();
        mo13768b().finish();
    }

    /* renamed from: A */
    private void m1946A() {
        try {
            f1433j = true;
            this.f1436f.stopLoading();
            this.f1436f.removeAllViews();
            this.f1436f.postInvalidate();
            C1261c.m2039b(this.f1436f);
            this.f1436f.destroy();
            this.f1436f = null;
        } catch (Exception e) {
            C1270g.m2078a("IABrowserMode", 6, "IABrowserMode::destroyWebview error " + e.getMessage());
        }
    }
}

package com.appnext.core.webview;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.appnext.core.C4967f;
import com.mopub.common.Constants;
import java.io.IOException;
import java.net.HttpRetryException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public final class AppnextWebView {

    /* renamed from: il */
    public static final int f4893il = 1;

    /* renamed from: im */
    public static final int f4894im = 2;
    /* access modifiers changed from: private */

    /* renamed from: in */
    public static AppnextWebView f4895in;
    /* access modifiers changed from: private */

    /* renamed from: bX */
    public WebAppInterface f4896bX;
    /* access modifiers changed from: private */

    /* renamed from: io */
    public final HashMap<String, C5016b> f4897io = new HashMap<>();

    /* renamed from: ip */
    private HashMap<String, WebView> f4898ip;

    /* renamed from: com.appnext.core.webview.AppnextWebView$c */
    public interface C5017c {
        void error(String str);

        /* renamed from: f */
        void mo40715f(String str);
    }

    /* renamed from: a */
    public final void mo41342a(WebAppInterface webAppInterface) {
        if (this.f4896bX == webAppInterface) {
            this.f4896bX = null;
        }
    }

    /* renamed from: com.appnext.core.webview.AppnextWebView$b */
    private class C5016b {

        /* renamed from: aQ */
        public String f4903aQ;

        /* renamed from: hF */
        public ArrayList<C5017c> f4904hF;

        /* renamed from: is */
        public String f4906is;
        public int state;

        private C5016b() {
            this.state = 0;
            this.f4906is = "";
            this.f4904hF = new ArrayList<>();
        }
    }

    /* renamed from: u */
    public static AppnextWebView m6957u(Context context) {
        if (f4895in == null) {
            AppnextWebView appnextWebView = new AppnextWebView();
            f4895in = appnextWebView;
            appnextWebView.f4898ip = new HashMap<>();
        }
        return f4895in;
    }

    private AppnextWebView() {
    }

    /* JADX WARNING: Code restructure failed: missing block: B:20:0x004b, code lost:
        return;
     */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized void mo41343a(java.lang.String r8, com.appnext.core.webview.AppnextWebView.C5017c r9) {
        /*
            r7 = this;
            monitor-enter(r7)
            java.util.HashMap<java.lang.String, com.appnext.core.webview.AppnextWebView$b> r0 = r7.f4897io     // Catch:{ all -> 0x004c }
            boolean r0 = r0.containsKey(r8)     // Catch:{ all -> 0x004c }
            r1 = 0
            if (r0 == 0) goto L_0x0013
            java.util.HashMap<java.lang.String, com.appnext.core.webview.AppnextWebView$b> r0 = r7.f4897io     // Catch:{ all -> 0x004c }
            java.lang.Object r0 = r0.get(r8)     // Catch:{ all -> 0x004c }
            com.appnext.core.webview.AppnextWebView$b r0 = (com.appnext.core.webview.AppnextWebView.C5016b) r0     // Catch:{ all -> 0x004c }
            goto L_0x001a
        L_0x0013:
            com.appnext.core.webview.AppnextWebView$b r0 = new com.appnext.core.webview.AppnextWebView$b     // Catch:{ all -> 0x004c }
            r0.<init>()     // Catch:{ all -> 0x004c }
            r0.f4903aQ = r8     // Catch:{ all -> 0x004c }
        L_0x001a:
            int r2 = r0.state     // Catch:{ all -> 0x004c }
            r3 = 2
            if (r2 != r3) goto L_0x0026
            if (r9 == 0) goto L_0x004a
            r9.mo40715f(r8)     // Catch:{ all -> 0x004c }
            monitor-exit(r7)
            return
        L_0x0026:
            int r2 = r0.state     // Catch:{ all -> 0x004c }
            if (r2 != 0) goto L_0x003e
            r2 = 1
            r0.state = r2     // Catch:{ all -> 0x004c }
            com.appnext.core.webview.AppnextWebView$a r4 = new com.appnext.core.webview.AppnextWebView$a     // Catch:{ all -> 0x004c }
            r4.<init>(r0)     // Catch:{ all -> 0x004c }
            java.util.concurrent.Executor r5 = android.os.AsyncTask.THREAD_POOL_EXECUTOR     // Catch:{ all -> 0x004c }
            java.lang.String[] r3 = new java.lang.String[r3]     // Catch:{ all -> 0x004c }
            r6 = 0
            r3[r6] = r8     // Catch:{ all -> 0x004c }
            r3[r2] = r1     // Catch:{ all -> 0x004c }
            r4.executeOnExecutor(r5, r3)     // Catch:{ all -> 0x004c }
        L_0x003e:
            if (r9 == 0) goto L_0x0045
            java.util.ArrayList<com.appnext.core.webview.AppnextWebView$c> r1 = r0.f4904hF     // Catch:{ all -> 0x004c }
            r1.add(r9)     // Catch:{ all -> 0x004c }
        L_0x0045:
            java.util.HashMap<java.lang.String, com.appnext.core.webview.AppnextWebView$b> r9 = r7.f4897io     // Catch:{ all -> 0x004c }
            r9.put(r8, r0)     // Catch:{ all -> 0x004c }
        L_0x004a:
            monitor-exit(r7)
            return
        L_0x004c:
            r8 = move-exception
            monitor-exit(r7)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appnext.core.webview.AppnextWebView.mo41343a(java.lang.String, com.appnext.core.webview.AppnextWebView$c):void");
    }

    /* renamed from: com.appnext.core.webview.AppnextWebView$a */
    private class C5015a extends AsyncTask<String, Void, String> {

        /* renamed from: ir */
        C5016b f4902ir;

        /* access modifiers changed from: protected */
        public final /* synthetic */ void onPostExecute(Object obj) {
            String str = (String) obj;
            super.onPostExecute(str);
            if (str.startsWith("error:")) {
                this.f4902ir.state = 0;
                AppnextWebView.this.f4897io.put(this.f4902ir.f4903aQ, this.f4902ir);
                AppnextWebView.m6951a(AppnextWebView.this, this.f4902ir, str.substring(7));
                return;
            }
            this.f4902ir.state = 2;
            AppnextWebView.this.f4897io.put(this.f4902ir.f4903aQ, this.f4902ir);
            AppnextWebView.m6954b(AppnextWebView.this, this.f4902ir, str);
        }

        public C5015a(C5016b bVar) {
            this.f4902ir = bVar;
        }

        /* access modifiers changed from: protected */
        /* renamed from: b */
        public final String doInBackground(String... strArr) {
            try {
                C5016b bVar = (C5016b) AppnextWebView.this.f4897io.get(strArr[0]);
                bVar.f4906is = C4967f.m6818a(strArr[0], (HashMap<String, String>) null, true, 10000);
                AppnextWebView.this.f4897io.put(strArr[0], bVar);
                return strArr[0];
            } catch (HttpRetryException e) {
                return "error: " + e.getReason();
            } catch (IOException unused) {
                return "error: network problem";
            } catch (Throwable unused2) {
                return "error: unknown error";
            }
        }

        /* access modifiers changed from: protected */
        /* renamed from: ag */
        public final void mo41348ag(String str) {
            super.onPostExecute(str);
            if (str.startsWith("error:")) {
                this.f4902ir.state = 0;
                AppnextWebView.this.f4897io.put(this.f4902ir.f4903aQ, this.f4902ir);
                AppnextWebView.m6951a(AppnextWebView.this, this.f4902ir, str.substring(7));
                return;
            }
            this.f4902ir.state = 2;
            AppnextWebView.this.f4897io.put(this.f4902ir.f4903aQ, this.f4902ir);
            AppnextWebView.m6954b(AppnextWebView.this, this.f4902ir, str);
        }
    }

    /* renamed from: v */
    private WebView m6958v(Context context) {
        WebView webView = new WebView(context.getApplicationContext());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setAppCacheEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setDatabaseEnabled(true);
        if (Build.VERSION.SDK_INT >= 21) {
            webView.getSettings().setMixedContentMode(0);
        }
        if (Build.VERSION.SDK_INT >= 17) {
            webView.getSettings().setMediaPlaybackRequiresUserGesture(false);
        }
        webView.setWebChromeClient(new WebChromeClient());
        webView.setWebViewClient(new WebViewClient() {
            public final boolean shouldOverrideUrlLoading(WebView webView, String str) {
                if (str == null) {
                    return false;
                }
                if (!str.startsWith(Constants.HTTP)) {
                    return super.shouldOverrideUrlLoading(webView, str);
                }
                webView.loadUrl(str);
                return true;
            }
        });
        return webView;
    }

    /* renamed from: a */
    public final WebView mo41341a(Context context, String str, WebAppInterface webAppInterface, C5018a aVar, String str2) {
        String str3;
        try {
            f4895in.f4896bX = webAppInterface;
            WebView webView = this.f4898ip.get(str2);
            if (!(webView == null || webView.getParent() == null)) {
                ((ViewGroup) webView.getParent()).removeView(webView);
            }
            URL url = new URL(str);
            String str4 = url.getProtocol() + "://" + url.getHost();
            WebView webView2 = new WebView(context.getApplicationContext());
            webView2.getSettings().setJavaScriptEnabled(true);
            webView2.getSettings().setAppCacheEnabled(true);
            webView2.getSettings().setDomStorageEnabled(true);
            webView2.getSettings().setDatabaseEnabled(true);
            if (Build.VERSION.SDK_INT >= 21) {
                webView2.getSettings().setMixedContentMode(0);
            }
            if (Build.VERSION.SDK_INT >= 17) {
                webView2.getSettings().setMediaPlaybackRequiresUserGesture(false);
            }
            webView2.setWebChromeClient(new WebChromeClient());
            webView2.setWebViewClient(new WebViewClient() {
                public final boolean shouldOverrideUrlLoading(WebView webView, String str) {
                    if (str == null) {
                        return false;
                    }
                    if (!str.startsWith(Constants.HTTP)) {
                        return super.shouldOverrideUrlLoading(webView, str);
                    }
                    webView.loadUrl(str);
                    return true;
                }
            });
            if (this.f4897io.containsKey(str)) {
                if (this.f4897io.get(str).state == 2 && !this.f4897io.get(str).f4906is.equals("")) {
                    str3 = "<script>" + this.f4897io.get(str).f4906is + "</script>";
                    webView2.loadDataWithBaseURL(str4, "<html><body>" + str3 + "</body></html>", (String) null, "UTF-8", (String) null);
                    this.f4898ip.put(str2, webView2);
                    webView2.addJavascriptInterface(new WebInterface(), "Appnext");
                    return webView2;
                }
            }
            if (aVar != null) {
                str3 = "<script>" + aVar.mo40781J() + "</script>";
            } else {
                str3 = "<script src='" + str + "'></script>";
            }
            webView2.loadDataWithBaseURL(str4, "<html><body>" + str3 + "</body></html>", (String) null, "UTF-8", (String) null);
            this.f4898ip.put(str2, webView2);
            webView2.addJavascriptInterface(new WebInterface(), "Appnext");
            return webView2;
        } catch (Throwable unused) {
            return null;
        }
    }

    /* renamed from: ah */
    public final boolean mo41344ah(String str) {
        return this.f4898ip.get(str) != null;
    }

    /* renamed from: ai */
    public final WebView mo41345ai(String str) {
        WebView webView = this.f4898ip.get(str);
        if (!(webView == null || webView.getParent() == null)) {
            ((ViewGroup) webView.getParent()).removeView(webView);
        }
        return webView;
    }

    /* renamed from: aj */
    public final String mo41346aj(String str) {
        C5016b bVar = this.f4897io.get(str);
        if (bVar == null || bVar.state != 2) {
            return null;
        }
        return bVar.f4906is;
    }

    /* renamed from: b */
    public static void m6955b(WebAppInterface webAppInterface) {
        f4895in.f4896bX = webAppInterface;
    }

    /* renamed from: a */
    private void m6950a(C5016b bVar, String str) {
        new StringBuilder("webview error: ").append(str);
        synchronized (this.f4897io) {
            Iterator<C5017c> it = bVar.f4904hF.iterator();
            while (it.hasNext()) {
                it.next().error(str);
            }
            bVar.f4904hF.clear();
            this.f4897io.remove(bVar.f4903aQ);
        }
    }

    /* renamed from: b */
    private void m6953b(C5016b bVar, String str) {
        new StringBuilder("downloaded ").append(str);
        synchronized (this.f4897io) {
            Iterator<C5017c> it = bVar.f4904hF.iterator();
            while (it.hasNext()) {
                it.next().mo40715f(str);
            }
            bVar.f4904hF.clear();
        }
    }

    private class WebInterface extends WebAppInterface {
        public WebInterface() {
        }

        @JavascriptInterface
        public void destroy(String str) {
            super.destroy(str);
            if (AppnextWebView.f4895in.f4896bX != null) {
                AppnextWebView.f4895in.f4896bX.destroy(str);
            }
        }

        @JavascriptInterface
        public void postView(String str) {
            super.postView(str);
            if (AppnextWebView.f4895in.f4896bX != null) {
                AppnextWebView.f4895in.f4896bX.postView(str);
            }
        }

        @JavascriptInterface
        public void openStore(String str) {
            super.openStore(str);
            if (AppnextWebView.f4895in.f4896bX != null) {
                AppnextWebView.f4895in.f4896bX.openStore(str);
            }
        }

        @JavascriptInterface
        public void play() {
            super.play();
            if (AppnextWebView.f4895in.f4896bX != null) {
                AppnextWebView.f4895in.f4896bX.play();
            }
        }

        @JavascriptInterface
        public String filterAds(String str) {
            super.filterAds(str);
            return AppnextWebView.f4895in.f4896bX != null ? AppnextWebView.f4895in.f4896bX.filterAds(str) : str;
        }

        @JavascriptInterface
        public String loadAds() {
            super.loadAds();
            return AppnextWebView.f4895in.f4896bX != null ? AppnextWebView.f4895in.f4896bX.loadAds() : "";
        }

        @JavascriptInterface
        public void openLink(String str) {
            super.openLink(str);
            if (AppnextWebView.f4895in.f4896bX != null) {
                AppnextWebView.f4895in.f4896bX.openLink(str);
            }
        }

        @JavascriptInterface
        public void gotoAppWall() {
            super.gotoAppWall();
            if (AppnextWebView.f4895in.f4896bX != null) {
                AppnextWebView.f4895in.f4896bX.gotoAppWall();
            }
        }

        @JavascriptInterface
        public void videoPlayed() {
            super.videoPlayed();
            if (AppnextWebView.f4895in.f4896bX != null) {
                AppnextWebView.f4895in.f4896bX.videoPlayed();
            }
        }

        @JavascriptInterface
        public void notifyImpression(String str) {
            super.notifyImpression(str);
            if (AppnextWebView.f4895in.f4896bX != null) {
                AppnextWebView.f4895in.f4896bX.notifyImpression(str);
            }
        }

        @JavascriptInterface
        public void jsError(String str) {
            super.jsError(str);
            if (AppnextWebView.f4895in.f4896bX != null) {
                AppnextWebView.f4895in.f4896bX.jsError(str);
            }
        }

        @JavascriptInterface
        public void openResultPage(String str) {
            super.openResultPage(str);
            if (AppnextWebView.f4895in.f4896bX != null) {
                AppnextWebView.f4895in.f4896bX.openResultPage(str);
            }
        }

        @JavascriptInterface
        public void logSTP(String str, String str2) {
            super.logSTP(str, str2);
            if (AppnextWebView.f4895in.f4896bX != null) {
                AppnextWebView.f4895in.f4896bX.logSTP(str, str2);
            }
        }
    }

    /* renamed from: a */
    static /* synthetic */ void m6951a(AppnextWebView appnextWebView, C5016b bVar, String str) {
        new StringBuilder("webview error: ").append(str);
        synchronized (appnextWebView.f4897io) {
            Iterator<C5017c> it = bVar.f4904hF.iterator();
            while (it.hasNext()) {
                it.next().error(str);
            }
            bVar.f4904hF.clear();
            appnextWebView.f4897io.remove(bVar.f4903aQ);
        }
    }

    /* renamed from: b */
    static /* synthetic */ void m6954b(AppnextWebView appnextWebView, C5016b bVar, String str) {
        new StringBuilder("downloaded ").append(str);
        synchronized (appnextWebView.f4897io) {
            Iterator<C5017c> it = bVar.f4904hF.iterator();
            while (it.hasNext()) {
                it.next().mo40715f(str);
            }
            bVar.f4904hF.clear();
        }
    }
}

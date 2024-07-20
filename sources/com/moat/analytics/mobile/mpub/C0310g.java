package com.moat.analytics.mobile.mpub;

import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.util.Base64;
import android.webkit.ValueCallback;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.moat.analytics.mobile.mpub.C0322j;
import java.util.Locale;
import java.util.Map;
import org.htmlcleaner.CleanerProperties;
import org.json.JSONObject;

/* renamed from: com.moat.analytics.mobile.mpub.g */
class C0310g {

    /* renamed from: a */
    WebView f86a;

    /* renamed from: b */
    C0322j f87b;

    /* renamed from: c */
    final String f88c = String.format(Locale.ROOT, "_moatTracker%d", new Object[]{Integer.valueOf((int) (Math.random() * 1.0E8d))});

    /* renamed from: d */
    private final C0315a f89d;
    /* access modifiers changed from: private */

    /* renamed from: e */
    public boolean f90e = false;
    /* access modifiers changed from: private */

    /* renamed from: f */
    public Handler f91f;
    /* access modifiers changed from: private */

    /* renamed from: g */
    public Runnable f92g;

    /* renamed from: com.moat.analytics.mobile.mpub.g$a */
    enum C0315a {
        DISPLAY,
        VIDEO
    }

    C0310g(Context context, C0315a aVar) {
        this.f89d = aVar;
        WebView webView = new WebView(context);
        this.f86a = webView;
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setAllowContentAccess(false);
        settings.setAllowFileAccess(false);
        settings.setDatabaseEnabled(false);
        settings.setDomStorageEnabled(false);
        settings.setGeolocationEnabled(false);
        settings.setJavaScriptCanOpenWindowsAutomatically(false);
        settings.setSaveFormData(false);
        if (Build.VERSION.SDK_INT >= 16) {
            settings.setAllowFileAccessFromFileURLs(false);
            settings.setAllowUniversalAccessFromFileURLs(false);
        }
        if (Build.VERSION.SDK_INT >= 21) {
            settings.setMixedContentMode(1);
        }
        try {
            this.f87b = new C0322j(this.f86a, aVar == C0315a.VIDEO ? C0322j.C0326a.NATIVE_VIDEO : C0322j.C0326a.NATIVE_DISPLAY);
        } catch (C0330n e) {
            C0330n.m214a(e);
        }
    }

    /* renamed from: a */
    private static String m123a(String str, String str2, Integer num, Integer num2, JSONObject jSONObject, Integer num3) {
        try {
            return Base64.encodeToString(String.format(Locale.ROOT, "<html><head></head><body><div id=\"%s\" style=\"width: %dpx; height: %dpx;\"></div><script>(function initMoatTracking(apiname, pcode, ids, duration) {var events = [];window[pcode + '_moatElToTrack'] = document.getElementById('%s');var moatapi = {'dropTime':%d,'adData': {'ids': ids, 'duration': duration, 'url': 'n/a'},'dispatchEvent': function(ev) {if (this.sendEvent) {if (events) { events.push(ev); ev = events; events = false; }this.sendEvent(ev);} else {events.push(ev);}},'dispatchMany': function(evs){for (var i=0, l=evs.length; i<l; i++) {this.dispatchEvent(evs[i]);}}};Object.defineProperty(window, apiname, {'value': moatapi});var s = document.createElement('script');s.src = 'https://z.moatads.com/' + pcode + '/moatvideo.js?' + apiname + '#' + apiname;document.body.appendChild(s);})('%s', '%s', %s, %s);</script></body></html>", new Object[]{"mianahwvc", num, num2, "mianahwvc", Long.valueOf(System.currentTimeMillis()), str, str2, jSONObject.toString(), num3}).getBytes(), 1);
        } catch (Exception e) {
            C0330n.m214a(e);
            return "";
        }
    }

    /* renamed from: b */
    private static String m127b(String str) {
        return "<!DOCTYPE html>\n<html>\n<head lang=\"en\">\n   <meta charset=\"UTF-8\">\n   <title></title>\n</head>\n<body style=\"margin:0;padding:0;\">\n    <script src=\"https://z.moatads.com/" + str + "/moatad.js\" type=\"text/javascript\"></script>\n</body>\n</html>";
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo10411a() {
        C0336p.m228a(3, "GlobalWebView", (Object) this, "Cleaning up");
        this.f87b.mo10439b();
        this.f87b = null;
        this.f86a.destroy();
        this.f86a = null;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo10412a(String str) {
        if (this.f89d == C0315a.DISPLAY) {
            this.f86a.setWebViewClient(new WebViewClient() {
                public void onPageFinished(WebView webView, String str) {
                    if (!C0310g.this.f90e) {
                        try {
                            boolean unused = C0310g.this.f90e = true;
                            C0310g.this.f87b.mo10435a();
                        } catch (Exception e) {
                            C0330n.m214a(e);
                        }
                    }
                }
            });
            this.f86a.loadData(m127b(str), "text/html", "utf-8");
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo10413a(String str, Map<String, String> map, Integer num, Integer num2, Integer num3) {
        if (this.f89d == C0315a.VIDEO) {
            if (Build.VERSION.SDK_INT >= 19) {
                C0336p.m228a(3, "GlobalWebView", (Object) this, "Starting off polling interval to check for Video API instance presence");
                this.f91f = new Handler();
                C03122 r0 = new Runnable() {
                    public void run() {
                        try {
                            if (C0310g.this.f86a != null && Build.VERSION.SDK_INT >= 19) {
                                WebView webView = C0310g.this.f86a;
                                webView.evaluateJavascript("typeof " + C0310g.this.f88c + " !== 'undefined'", new ValueCallback<String>() {
                                    /* renamed from: a */
                                    public void onReceiveValue(String str) {
                                        if (CleanerProperties.BOOL_ATT_TRUE.equals(str)) {
                                            C0336p.m228a(3, "GlobalWebView", (Object) this, String.format("Video API instance %s detected. Flushing event queue", new Object[]{C0310g.this.f88c}));
                                            try {
                                                boolean unused = C0310g.this.f90e = true;
                                                C0310g.this.f87b.mo10435a();
                                                C0310g.this.f87b.mo10443c(C0310g.this.f88c);
                                            } catch (Exception e) {
                                                C0330n.m214a(e);
                                            }
                                        } else {
                                            C0310g.this.f91f.postDelayed(C0310g.this.f92g, 200);
                                        }
                                    }
                                });
                            }
                        } catch (Exception e) {
                            C0330n.m214a(e);
                        }
                    }
                };
                this.f92g = r0;
                this.f91f.post(r0);
            } else {
                C0336p.m228a(3, "GlobalWebView", (Object) this, "Android API version is less than KitKat: " + Build.VERSION.SDK_INT);
                this.f86a.setWebViewClient(new WebViewClient() {
                    public void onPageFinished(WebView webView, String str) {
                        if (!C0310g.this.f90e) {
                            C0336p.m228a(3, "GlobalWebView", (Object) this, "onPageFinished is called for the first time. Flushing event queue");
                            try {
                                boolean unused = C0310g.this.f90e = true;
                                C0310g.this.f87b.mo10435a();
                                C0310g.this.f87b.mo10443c(C0310g.this.f88c);
                            } catch (Exception e) {
                                C0330n.m214a(e);
                            }
                        }
                    }
                });
            }
            this.f86a.loadData(m123a(this.f88c, str, num, num2, new JSONObject(map), num3), "text/html", "base64");
        }
    }
}

package com.p088b.p089a.p090a.p091a.p095c;

import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.webkit.WebView;
import com.p088b.p089a.p090a.p091a.p097e.C5137c;
import kotlin.text.Typography;
import org.json.JSONObject;

/* renamed from: com.b.a.a.a.c.d */
public class C5125d {

    /* renamed from: a */
    private static C5125d f4978a = new C5125d();

    private C5125d() {
    }

    /* renamed from: a */
    public static C5125d m7072a() {
        return f4978a;
    }

    /* renamed from: a */
    public void mo41880a(WebView webView) {
        mo41884a(webView, "finishSession", new Object[0]);
    }

    /* renamed from: a */
    public void mo41881a(WebView webView, float f) {
        mo41884a(webView, "setDeviceVolume", Float.valueOf(f));
    }

    /* renamed from: a */
    public void mo41882a(WebView webView, String str, JSONObject jSONObject) {
        if (jSONObject != null) {
            mo41884a(webView, "publishVideoEvent", str, jSONObject);
            return;
        }
        mo41884a(webView, "publishVideoEvent", str);
    }

    /* renamed from: a */
    public void mo41883a(WebView webView, String str, JSONObject jSONObject, JSONObject jSONObject2) {
        mo41884a(webView, "startSession", str, jSONObject, jSONObject2);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo41884a(WebView webView, String str, Object... objArr) {
        if (webView != null) {
            StringBuilder sb = new StringBuilder(128);
            sb.append("javascript: if(window.omidBridge!==undefined){omidBridge.");
            sb.append(str);
            sb.append("(");
            mo41887a(sb, objArr);
            sb.append(")}");
            mo41885a(webView, sb);
            return;
        }
        C5137c.m7130a("The WebView is null for " + str);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo41885a(final WebView webView, StringBuilder sb) {
        final String sb2 = sb.toString();
        Handler handler = webView.getHandler();
        if (handler == null || Looper.myLooper() == handler.getLooper()) {
            webView.loadUrl(sb2);
        } else {
            handler.post(new Runnable() {
                public void run() {
                    webView.loadUrl(sb2);
                }
            });
        }
    }

    /* renamed from: a */
    public void mo41886a(WebView webView, JSONObject jSONObject) {
        mo41884a(webView, "init", jSONObject);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo41887a(StringBuilder sb, Object[] objArr) {
        if (objArr != null && objArr.length > 0) {
            for (Object obj : objArr) {
                if (obj == null) {
                    sb.append(Typography.quote);
                } else {
                    if (obj instanceof String) {
                        String obj2 = obj.toString();
                        if (obj2.startsWith("{")) {
                            sb.append(obj2);
                        } else {
                            sb.append(Typography.quote);
                            sb.append(obj2);
                        }
                    } else {
                        sb.append(obj);
                    }
                    sb.append(",");
                }
                sb.append(Typography.quote);
                sb.append(",");
            }
            sb.setLength(sb.length() - 1);
        }
    }

    /* renamed from: a */
    public boolean mo41888a(WebView webView, String str) {
        if (webView == null || TextUtils.isEmpty(str)) {
            return false;
        }
        webView.loadUrl("javascript: " + str);
        return true;
    }

    /* renamed from: b */
    public void mo41889b(WebView webView) {
        mo41884a(webView, "publishImpressionEvent", new Object[0]);
    }

    /* renamed from: b */
    public void mo41890b(WebView webView, String str) {
        if (str != null) {
            mo41888a(webView, "var script=document.createElement('script');script.setAttribute(\"type\",\"text/javascript\");script.setAttribute(\"src\",\"%SCRIPT_SRC%\");document.body.appendChild(script);".replace("%SCRIPT_SRC%", str));
        }
    }

    /* renamed from: c */
    public void mo41891c(WebView webView, String str) {
        mo41884a(webView, "setNativeViewHierarchy", str);
    }

    /* renamed from: d */
    public void mo41892d(WebView webView, String str) {
        mo41884a(webView, "setState", str);
    }
}

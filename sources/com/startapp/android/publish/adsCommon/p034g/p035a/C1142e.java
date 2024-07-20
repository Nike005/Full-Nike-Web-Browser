package com.startapp.android.publish.adsCommon.p034g.p035a;

import android.net.Uri;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.startapp.android.publish.adsCommon.p034g.p038d.C1146a;
import com.startapp.android.publish.adsCommon.p034g.p038d.C1147b;
import com.startapp.common.p043a.C1270g;
import java.io.ByteArrayInputStream;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Locale;
import java.util.Map;
import org.apache.commons.lang3.concurrent.AbstractCircuitBreaker;

/* renamed from: com.startapp.android.publish.adsCommon.g.a.e */
/* compiled from: StartAppSDK */
public class C1142e extends WebViewClient {

    /* renamed from: a */
    private C1139b f1202a;

    /* renamed from: b */
    private boolean f1203b = false;

    public C1142e(C1139b bVar) {
        this.f1202a = bVar;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public boolean mo14923a(String str) {
        return str != null && str.startsWith("mraid://");
    }

    public boolean shouldOverrideUrlLoading(WebView webView, String str) {
        C1270g.m2078a("MraidWebViewClient", 3, "shouldOverrideUrlLoading: " + str);
        if (mo14923a(str)) {
            return mo14925c(str);
        }
        return this.f1202a.open(str);
    }

    public WebResourceResponse shouldInterceptRequest(WebView webView, String str) {
        C1270g.m2078a("MraidWebViewClient", 3, "shouldInterceptRequest: " + str);
        if (this.f1203b || !mo14924b(str)) {
            return super.shouldInterceptRequest(webView, str);
        }
        this.f1203b = true;
        return m1547a();
    }

    public void onReceivedError(WebView webView, int i, String str, String str2) {
        C1270g.m2078a("MraidWebViewClient", 6, "onReceivedError: " + str);
        super.onReceivedError(webView, i, str, str2);
    }

    /* renamed from: b */
    public boolean mo14924b(String str) {
        try {
            return "mraid.js".equals(Uri.parse(str.toLowerCase(Locale.US)).getLastPathSegment());
        } catch (Exception e) {
            C1270g.m2078a("MraidWebViewClient", 6, "matchesInjectionUrl Exception: " + e.getMessage());
            return false;
        }
    }

    /* renamed from: a */
    private WebResourceResponse m1547a() {
        return new WebResourceResponse("text/javascript", "UTF-8", new ByteArrayInputStream(("javascript:" + C1146a.m1560a()).getBytes()));
    }

    /* renamed from: c */
    public boolean mo14925c(String str) {
        C1270g.m2078a("MraidWebViewClient", 3, "invokeMraidMethod " + str);
        String[] strArr = {"close", "resize"};
        String[] strArr2 = {"createCalendarEvent", "expand", AbstractCircuitBreaker.PROPERTY_NAME, "playVideo", "storePicture", "useCustomClose"};
        String[] strArr3 = {"setOrientationProperties", "setResizeProperties"};
        try {
            Map<String, String> a = C1147b.m1565a(str);
            String str2 = a.get("command");
            if (Arrays.asList(strArr).contains(str2)) {
                C1139b.class.getDeclaredMethod(str2, new Class[0]).invoke(this.f1202a, new Object[0]);
            } else if (Arrays.asList(strArr2).contains(str2)) {
                Method declaredMethod = C1139b.class.getDeclaredMethod(str2, new Class[]{String.class});
                char c = 65535;
                int hashCode = str2.hashCode();
                String str3 = "useCustomClose";
                if (hashCode != -733616544) {
                    if (hashCode == 1614272768) {
                        if (str2.equals(str3)) {
                            c = 1;
                        }
                    }
                } else if (str2.equals("createCalendarEvent")) {
                    c = 0;
                }
                if (c == 0) {
                    str3 = "eventJSON";
                } else if (c != 1) {
                    str3 = "url";
                }
                declaredMethod.invoke(this.f1202a, new Object[]{a.get(str3)});
            } else if (Arrays.asList(strArr3).contains(str2)) {
                C1139b.class.getDeclaredMethod(str2, new Class[]{Map.class}).invoke(this.f1202a, new Object[]{a});
            }
            C1270g.m2078a("MraidWebViewClient", 3, "successfully invoked " + str);
            return true;
        } catch (Exception e) {
            C1270g.m2078a("MraidWebViewClient", 6, "failed to invoke " + str + ". " + e.getMessage());
            return false;
        }
    }
}

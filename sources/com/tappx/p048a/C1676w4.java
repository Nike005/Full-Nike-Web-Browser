package com.tappx.p048a;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

/* renamed from: com.tappx.a.w4 */
public class C1676w4 {

    /* renamed from: com.tappx.a.w4$a */
    static class C1677a extends WebChromeClient {
        C1677a() {
        }

        public boolean onJsAlert(WebView webView, String str, String str2, JsResult jsResult) {
            C1467j0.m2872c(str2, new Object[0]);
            jsResult.confirm();
            return true;
        }

        public boolean onJsBeforeUnload(WebView webView, String str, String str2, JsResult jsResult) {
            C1467j0.m2872c(str2, new Object[0]);
            jsResult.confirm();
            return true;
        }

        public boolean onJsConfirm(WebView webView, String str, String str2, JsResult jsResult) {
            C1467j0.m2872c(str2, new Object[0]);
            jsResult.confirm();
            return true;
        }

        public boolean onJsPrompt(WebView webView, String str, String str2, String str3, JsPromptResult jsPromptResult) {
            C1467j0.m2872c(str2, new Object[0]);
            jsPromptResult.confirm();
            return true;
        }
    }

    /* renamed from: a */
    public static void m3551a(WebView webView) {
        webView.setWebChromeClient(new C1677a());
    }

    /* renamed from: b */
    public static void m3552b(View view) {
        if (view != null && view.getParent() != null && (view.getParent() instanceof ViewGroup)) {
            ((ViewGroup) view.getParent()).removeView(view);
        }
    }

    /* renamed from: a */
    public static View m3549a(Context context, View view) {
        View a = m3548a(context);
        return a != null ? a : m3550a(view);
    }

    /* renamed from: a */
    private static View m3548a(Context context) {
        if (!(context instanceof Activity)) {
            return null;
        }
        return ((Activity) context).getWindow().getDecorView().findViewById(16908290);
    }

    /* renamed from: a */
    private static View m3550a(View view) {
        View rootView;
        if (view == null || (rootView = view.getRootView()) == null) {
            return null;
        }
        View findViewById = rootView.findViewById(16908290);
        return findViewById != null ? findViewById : rootView;
    }
}

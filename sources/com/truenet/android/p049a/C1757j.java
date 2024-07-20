package com.truenet.android.p049a;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.util.DisplayMetrics;
import android.webkit.WebView;
import p055a.p056a.p058b.p060b.C2928h;

/* renamed from: com.truenet.android.a.j */
/* compiled from: StartAppSDK */
public final class C1757j {
    /* renamed from: a */
    public static final Bitmap m3867a(WebView webView) {
        C2928h.m6157b(webView, "$receiver");
        if (Build.VERSION.SDK_INT >= 21) {
            WebView.enableSlowWholeDocumentDraw();
        }
        DisplayMetrics displayMetrics = new DisplayMetrics();
        Context context = webView.getContext();
        C2928h.m6154a((Object) context, "context");
        C1747c.m3850b(context).getDefaultDisplay().getMetrics(displayMetrics);
        int i = displayMetrics.widthPixels;
        int i2 = displayMetrics.heightPixels;
        webView.measure(i, i2);
        webView.layout(0, 0, i, i2);
        webView.setDrawingCacheEnabled(true);
        webView.buildDrawingCache(true);
        Thread.sleep(500);
        Bitmap createBitmap = Bitmap.createBitmap(webView.getDrawingCache());
        webView.setDrawingCacheEnabled(false);
        C2928h.m6154a((Object) createBitmap, "bitmap");
        return createBitmap;
    }
}

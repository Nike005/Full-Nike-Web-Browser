package com.tappx.p048a;

import android.content.Context;
import android.os.Build;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import com.google.android.gms.cast.CastStatusCodes;

/* renamed from: com.tappx.a.l3 */
public class C1510l3 extends WebView {

    /* renamed from: c */
    private static boolean f2049c = false;

    /* renamed from: a */
    private C1511a f2050a;

    /* renamed from: b */
    private boolean f2051b;

    /* renamed from: com.tappx.a.l3$a */
    public interface C1511a {
        /* renamed from: b */
        void mo15629b(boolean z);
    }

    public C1510l3(Context context) {
        super(context.getApplicationContext());
        boolean z = false;
        mo15940a(false);
        mo16264c();
        C1676w4.m3551a((WebView) this);
        if (!f2049c) {
            m2987a(getContext());
            f2049c = true;
        }
        this.f2051b = getVisibility() == 0 ? true : z;
    }

    /* renamed from: c */
    private void mo16264c() {
        getSettings().setAllowFileAccess(false);
        getSettings().setAllowContentAccess(false);
        if (Build.VERSION.SDK_INT >= 16) {
            getSettings().setAllowFileAccessFromFileURLs(false);
            getSettings().setAllowUniversalAccessFromFileURLs(false);
        }
    }

    /* renamed from: a */
    public void mo15939a() {
        if (Build.VERSION.SDK_INT >= 17) {
            getSettings().setMediaPlaybackRequiresUserGesture(false);
        }
    }

    /* renamed from: b */
    public boolean mo15941b() {
        return this.f2051b;
    }

    public void destroy() {
        C1676w4.m3552b(this);
        removeAllViews();
        super.destroy();
    }

    /* access modifiers changed from: protected */
    public void onVisibilityChanged(View view, int i) {
        super.onVisibilityChanged(view, i);
        boolean z = i == 0;
        if (z != this.f2051b) {
            this.f2051b = z;
            C1511a aVar = this.f2050a;
            if (aVar != null) {
                aVar.mo15629b(z);
            }
        }
    }

    public void setVisibilityChangedListener(C1511a aVar) {
        this.f2050a = aVar;
    }

    /* renamed from: a */
    public void mo15940a(boolean z) {
        if (Build.VERSION.SDK_INT < 18) {
            if (z) {
                getSettings().setPluginState(WebSettings.PluginState.ON);
            } else {
                getSettings().setPluginState(WebSettings.PluginState.OFF);
            }
        }
    }

    /* renamed from: a */
    private void m2987a(Context context) {
        if (Build.VERSION.SDK_INT == 19) {
            WebView webView = new WebView(context.getApplicationContext());
            webView.setBackgroundColor(0);
            webView.loadDataWithBaseURL((String) null, "", "text/html", "UTF-8", (String) null);
            WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
            layoutParams.width = 1;
            layoutParams.height = 1;
            layoutParams.type = CastStatusCodes.APPLICATION_NOT_RUNNING;
            layoutParams.flags = 16777240;
            layoutParams.format = -2;
            layoutParams.gravity = 8388659;
            try {
                ((WindowManager) context.getSystemService("window")).addView(webView, layoutParams);
            } catch (Exception unused) {
            }
        }
    }
}

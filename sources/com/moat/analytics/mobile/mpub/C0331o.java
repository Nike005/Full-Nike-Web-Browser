package com.moat.analytics.mobile.mpub;

import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import com.moat.analytics.mobile.mpub.C0345v;
import com.moat.analytics.mobile.mpub.C0361x;
import com.moat.analytics.mobile.mpub.p002a.p004b.C0301a;
import java.lang.ref.WeakReference;
import java.util.Map;

/* renamed from: com.moat.analytics.mobile.mpub.o */
class C0331o extends MoatFactory {
    C0331o() {
        if (!m221a()) {
            C0336p.m229a("[ERROR] ", 3, "Factory", this, "Failed to initialize MoatFactory" + ", SDK was not started");
            throw new C0330n("Failed to initialize MoatFactory");
        }
    }

    /* renamed from: a */
    private NativeDisplayTracker m216a(View view, final Map<String, String> map) {
        final WeakReference weakReference = new WeakReference(view);
        return (NativeDisplayTracker) C0361x.m285a(new C0361x.C0363a<NativeDisplayTracker>() {
            /* renamed from: a */
            public C0301a<NativeDisplayTracker> mo10357a() {
                View view = (View) weakReference.get();
                C0336p.m229a("[INFO] ", 3, "Factory", this, "Attempting to create NativeDisplayTracker for " + C0336p.m226a(view));
                return C0301a.m81a(new C0343t(view, map));
            }
        }, NativeDisplayTracker.class);
    }

    /* renamed from: a */
    private NativeVideoTracker m217a(final String str) {
        return (NativeVideoTracker) C0361x.m285a(new C0361x.C0363a<NativeVideoTracker>() {
            /* renamed from: a */
            public C0301a<NativeVideoTracker> mo10357a() {
                C0336p.m229a("[INFO] ", 3, "Factory", this, "Attempting to create NativeVideoTracker");
                return C0301a.m81a(new C0344u(str));
            }
        }, NativeVideoTracker.class);
    }

    /* renamed from: a */
    private WebAdTracker m218a(ViewGroup viewGroup) {
        final WeakReference weakReference = new WeakReference(viewGroup);
        return (WebAdTracker) C0361x.m285a(new C0361x.C0363a<WebAdTracker>() {
            /* renamed from: a */
            public C0301a<WebAdTracker> mo10357a() {
                ViewGroup viewGroup = (ViewGroup) weakReference.get();
                C0336p.m229a("[INFO] ", 3, "Factory", this, "Attempting to create WebAdTracker for adContainer " + C0336p.m226a((View) viewGroup));
                return C0301a.m81a(new C0302aa(viewGroup));
            }
        }, WebAdTracker.class);
    }

    /* renamed from: a */
    private WebAdTracker m219a(WebView webView) {
        final WeakReference weakReference = new WeakReference(webView);
        return (WebAdTracker) C0361x.m285a(new C0361x.C0363a<WebAdTracker>() {
            /* renamed from: a */
            public C0301a<WebAdTracker> mo10357a() {
                WebView webView = (WebView) weakReference.get();
                C0336p.m229a("[INFO] ", 3, "Factory", this, "Attempting to create WebAdTracker for " + C0336p.m226a((View) webView));
                return C0301a.m81a(new C0302aa(webView));
            }
        }, WebAdTracker.class);
    }

    /* renamed from: a */
    private <T> T m220a(MoatPlugin<T> moatPlugin) {
        return moatPlugin.mo10327a();
    }

    /* renamed from: a */
    private boolean m221a() {
        return ((C0327k) C0327k.getInstance()).mo10449a();
    }

    public <T> T createCustomTracker(MoatPlugin<T> moatPlugin) {
        try {
            return m220a(moatPlugin);
        } catch (Exception e) {
            C0330n.m214a(e);
            return moatPlugin.mo10328b();
        }
    }

    public NativeDisplayTracker createNativeDisplayTracker(View view, Map<String, String> map) {
        try {
            return m216a(view, map);
        } catch (Exception e) {
            C0330n.m214a(e);
            return new C0345v.C0348c();
        }
    }

    public NativeVideoTracker createNativeVideoTracker(String str) {
        try {
            return m217a(str);
        } catch (Exception e) {
            C0330n.m214a(e);
            return new C0345v.C0349d();
        }
    }

    public WebAdTracker createWebAdTracker(ViewGroup viewGroup) {
        try {
            return m218a(viewGroup);
        } catch (Exception e) {
            C0330n.m214a(e);
            return new C0345v.C0350e();
        }
    }

    public WebAdTracker createWebAdTracker(WebView webView) {
        try {
            return m219a(webView);
        } catch (Exception e) {
            C0330n.m214a(e);
            return new C0345v.C0350e();
        }
    }
}

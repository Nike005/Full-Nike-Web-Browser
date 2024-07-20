package com.moat.analytics.mobile.mpub;

import android.view.ViewGroup;
import android.webkit.WebView;

/* renamed from: com.moat.analytics.mobile.mpub.aa */
class C0302aa extends C0304b implements WebAdTracker {
    C0302aa(ViewGroup viewGroup) {
        this(C0303ab.m87a(viewGroup, false).mo10375c(null));
        if (viewGroup == null) {
            C0336p.m229a("[ERROR] ", 3, "WebAdTracker", this, "WebAdTracker initialization not successful, " + "Target ViewGroup is null");
            this.f61a = new C0330n("Target ViewGroup is null");
        }
        if (this.f62b == null) {
            C0336p.m229a("[ERROR] ", 3, "WebAdTracker", this, "WebAdTracker initialization not successful, " + "No WebView to track inside of ad container");
            this.f61a = new C0330n("No WebView to track inside of ad container");
        }
    }

    C0302aa(WebView webView) {
        super(webView, false, false);
        C0336p.m228a(3, "WebAdTracker", (Object) this, "Initializing.");
        if (webView == null) {
            C0336p.m229a("[ERROR] ", 3, "WebAdTracker", this, "WebAdTracker initialization not successful, " + "WebView is null");
            this.f61a = new C0330n("WebView is null");
            return;
        }
        try {
            super.mo10381a(webView);
            C0336p.m231a("[SUCCESS] ", mo10380a() + " created for " + mo10391g());
        } catch (C0330n e) {
            this.f61a = e;
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public String mo10380a() {
        return "WebAdTracker";
    }
}

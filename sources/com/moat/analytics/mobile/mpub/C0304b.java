package com.moat.analytics.mobile.mpub;

import android.app.Activity;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebView;
import com.moat.analytics.mobile.mpub.C0322j;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

/* renamed from: com.moat.analytics.mobile.mpub.b */
abstract class C0304b {

    /* renamed from: a */
    C0330n f61a = null;

    /* renamed from: b */
    WeakReference<WebView> f62b;

    /* renamed from: c */
    C0322j f63c;

    /* renamed from: d */
    TrackerListener f64d;

    /* renamed from: e */
    final String f65e;

    /* renamed from: f */
    final boolean f66f;

    /* renamed from: g */
    private WeakReference<View> f67g;

    /* renamed from: h */
    private final C0366z f68h;

    /* renamed from: i */
    private final boolean f69i;

    /* renamed from: j */
    private boolean f70j;

    /* renamed from: k */
    private boolean f71k;

    C0304b(View view, boolean z, boolean z2) {
        String str;
        C0336p.m228a(3, "BaseTracker", (Object) this, "Initializing.");
        if (z) {
            str = "m" + hashCode();
        } else {
            str = "";
        }
        this.f65e = str;
        this.f67g = new WeakReference<>(view);
        this.f69i = z;
        this.f66f = z2;
        this.f70j = false;
        this.f71k = false;
        this.f68h = new C0366z();
    }

    /* renamed from: i */
    private void mo10402i() {
        String str;
        C0336p.m228a(3, "BaseTracker", (Object) this, "Attempting bridge installation.");
        if (this.f62b.get() != null) {
            this.f63c = new C0322j((WebView) this.f62b.get(), C0322j.C0326a.WEBVIEW);
            str = "Bridge installed.";
        } else {
            this.f63c = null;
            str = "Bridge not installed, WebView is null.";
        }
        C0336p.m228a(3, "BaseTracker", (Object) this, str);
    }

    /* renamed from: j */
    private void mo10403j() {
        if (this.f70j) {
            throw new C0330n("Tracker already started");
        }
    }

    /* renamed from: k */
    private void mo10404k() {
        if (this.f71k) {
            throw new C0330n("Tracker already stopped");
        }
    }

    /* renamed from: l */
    private boolean mo10405l() {
        return this.f69i || this.f66f;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public abstract String mo10380a();

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo10381a(WebView webView) {
        if (webView != null) {
            this.f62b = new WeakReference<>(webView);
            if (this.f63c == null && !mo10405l()) {
                mo10402i();
            }
            C0322j jVar = this.f63c;
            if (jVar != null) {
                jVar.mo10436a(this);
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo10382a(C0322j jVar) {
        this.f63c = jVar;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo10383a(String str, Exception exc) {
        try {
            C0330n.m214a(exc);
            String a = C0330n.m212a(str, exc);
            if (this.f64d != null) {
                this.f64d.onTrackingFailedToStart(a);
            }
            C0336p.m228a(3, "BaseTracker", (Object) this, a);
            C0336p.m231a("[ERROR] ", mo10380a() + StringUtils.SPACE + a);
        } catch (Exception unused) {
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo10384a(List<String> list) {
        if (mo10390f() == null && !this.f66f) {
            list.add("Tracker's target view is null");
        }
        if (!list.isEmpty()) {
            throw new C0330n(TextUtils.join(" and ", list));
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public void mo10385b() {
        C0336p.m228a(3, "BaseTracker", (Object) this, "Attempting to start impression.");
        mo10386c();
        mo10388d();
        mo10384a((List<String>) new ArrayList());
        C0322j jVar = this.f63c;
        if (jVar != null) {
            jVar.mo10440b(this);
            this.f70j = true;
            C0336p.m228a(3, "BaseTracker", (Object) this, "Impression started.");
            return;
        }
        C0336p.m228a(3, "BaseTracker", (Object) this, "Bridge is null, won't start tracking");
        throw new C0330n("Bridge is null");
    }

    /* access modifiers changed from: package-private */
    /* renamed from: c */
    public void mo10386c() {
        if (this.f61a != null) {
            throw new C0330n("Tracker initialization failed: " + this.f61a.getMessage());
        }
    }

    public void changeTargetView(View view) {
        C0336p.m228a(3, "BaseTracker", (Object) this, "changing view to " + C0336p.m226a(view));
        this.f67g = new WeakReference<>(view);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: d */
    public void mo10388d() {
        mo10403j();
        mo10404k();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: e */
    public boolean mo10389e() {
        return this.f70j && !this.f71k;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: f */
    public View mo10390f() {
        return (View) this.f67g.get();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: g */
    public String mo10391g() {
        return C0336p.m226a(mo10390f());
    }

    /* access modifiers changed from: package-private */
    /* renamed from: h */
    public String mo10392h() {
        this.f68h.mo10471a(this.f65e, mo10390f());
        return this.f68h.f237a;
    }

    public void removeListener() {
        this.f64d = null;
    }

    @Deprecated
    public void setActivity(Activity activity) {
    }

    public void setListener(TrackerListener trackerListener) {
        this.f64d = trackerListener;
    }

    public void startTracking() {
        try {
            C0336p.m228a(3, "BaseTracker", (Object) this, "In startTracking method.");
            mo10385b();
            if (this.f64d != null) {
                this.f64d.onTrackingStarted("Tracking started on " + mo10391g());
            }
            String str = "startTracking succeeded for " + mo10391g();
            C0336p.m228a(3, "BaseTracker", (Object) this, str);
            C0336p.m231a("[SUCCESS] ", mo10380a() + StringUtils.SPACE + str);
        } catch (Exception e) {
            mo10383a("startTracking", e);
        }
    }

    public void stopTracking() {
        boolean z = false;
        try {
            C0336p.m228a(3, "BaseTracker", (Object) this, "In stopTracking method.");
            this.f71k = true;
            if (this.f63c != null) {
                this.f63c.mo10442c(this);
                z = true;
            }
        } catch (Exception e) {
            C0330n.m214a(e);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Attempt to stop tracking ad impression was ");
        sb.append(z ? "" : "un");
        sb.append("successful.");
        C0336p.m228a(3, "BaseTracker", (Object) this, sb.toString());
        String str = z ? "[SUCCESS] " : "[ERROR] ";
        StringBuilder sb2 = new StringBuilder();
        sb2.append(mo10380a());
        sb2.append(" stopTracking ");
        sb2.append(z ? "succeeded" : "failed");
        sb2.append(" for ");
        sb2.append(mo10391g());
        C0336p.m231a(str, sb2.toString());
        TrackerListener trackerListener = this.f64d;
        if (trackerListener != null) {
            trackerListener.onTrackingStopped("");
            this.f64d = null;
        }
    }
}

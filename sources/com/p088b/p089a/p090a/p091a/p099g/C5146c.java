package com.p088b.p089a.p090a.p091a.p099g;

import android.os.Handler;
import android.webkit.WebView;
import com.p088b.p089a.p090a.p091a.p093b.C5117h;
import com.p088b.p089a.p090a.p091a.p095c.C5124c;
import com.p088b.p089a.p090a.p091a.p095c.C5125d;
import java.util.List;

/* renamed from: com.b.a.a.a.g.c */
public class C5146c extends C5143a {
    /* access modifiers changed from: private */

    /* renamed from: a */
    public WebView f5012a;

    /* renamed from: b */
    private List<C5117h> f5013b;

    /* renamed from: c */
    private final String f5014c;

    public C5146c(List<C5117h> list, String str) {
        this.f5013b = list;
        this.f5014c = str;
    }

    /* renamed from: a */
    public void mo41903a() {
        super.mo41903a();
        mo41923j();
    }

    /* renamed from: b */
    public void mo41914b() {
        super.mo41914b();
        new Handler().postDelayed(new Runnable() {

            /* renamed from: b */
            private WebView f5016b = C5146c.this.f5012a;

            public void run() {
                this.f5016b.destroy();
            }
        }, 2000);
        this.f5012a = null;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: j */
    public void mo41923j() {
        WebView webView = new WebView(C5124c.m7069a().mo41879b());
        this.f5012a = webView;
        webView.getSettings().setJavaScriptEnabled(true);
        mo41905a(this.f5012a);
        C5125d.m7072a().mo41888a(this.f5012a, this.f5014c);
        for (C5117h b : this.f5013b) {
            C5125d.m7072a().mo41890b(this.f5012a, b.mo41846b().toExternalForm());
        }
    }
}

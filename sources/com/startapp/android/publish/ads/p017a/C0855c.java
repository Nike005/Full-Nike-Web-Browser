package com.startapp.android.publish.ads.p017a;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;
import com.p088b.p089a.p090a.p091a.p093b.C5109a;
import com.p088b.p089a.p090a.p091a.p093b.C5111b;
import com.startapp.android.publish.ads.p018b.C0874c;
import com.startapp.android.publish.ads.splash.C0939b;
import com.startapp.android.publish.adsCommon.AdsConstants;
import com.startapp.android.publish.adsCommon.C1040Ad;
import com.startapp.android.publish.adsCommon.C1098b;
import com.startapp.android.publish.adsCommon.C1103c;
import com.startapp.android.publish.adsCommon.C1155i;
import com.startapp.android.publish.adsCommon.C1174m;
import com.startapp.android.publish.adsCommon.Utils.C1061i;
import com.startapp.android.publish.adsCommon.p031d.C1116a;
import com.startapp.android.publish.adsCommon.p031d.C1117b;
import com.startapp.android.publish.adsCommon.p033f.C1130d;
import com.startapp.android.publish.adsCommon.p033f.C1132f;
import com.startapp.android.publish.common.metaData.MetaData;
import com.startapp.android.publish.html.JsInterface;
import com.startapp.android.publish.omsdk.C1247a;
import com.startapp.common.C1275b;
import com.startapp.common.p043a.C1261c;
import com.startapp.common.p043a.C1270g;
import java.util.concurrent.TimeUnit;
import org.htmlcleaner.CleanerProperties;

/* renamed from: com.startapp.android.publish.ads.a.c */
/* compiled from: StartAppSDK */
public class C0855c extends C0851b {

    /* renamed from: d */
    protected WebView f426d;

    /* renamed from: e */
    protected C5111b f427e;

    /* renamed from: f */
    protected RelativeLayout f428f;

    /* renamed from: g */
    protected Runnable f429g = new Runnable() {
        public void run() {
            C0855c.this.mo13797B();
            C0855c.this.mo13786p();
        }
    };

    /* renamed from: h */
    protected Runnable f430h = new Runnable() {
        public void run() {
            boolean unused = C0855c.this.f436n = true;
            C0855c cVar = C0855c.this;
            cVar.mo13805b(cVar.f426d);
        }
    };

    /* renamed from: i */
    private Long f431i;

    /* renamed from: j */
    private Long f432j;

    /* renamed from: k */
    private long f433k = 0;

    /* renamed from: l */
    private long f434l = 0;

    /* renamed from: m */
    private C1155i f435m;
    /* access modifiers changed from: private */

    /* renamed from: n */
    public boolean f436n = true;
    /* access modifiers changed from: private */

    /* renamed from: o */
    public boolean f437o = false;

    /* access modifiers changed from: protected */
    /* renamed from: c */
    public void mo13807c(WebView webView) {
    }

    /* renamed from: a */
    public void mo13760a(Bundle bundle) {
        super.mo13760a(bundle);
        if (bundle == null) {
            if (mo13758a().hasExtra("lastLoadTime")) {
                this.f431i = (Long) mo13758a().getSerializableExtra("lastLoadTime");
            }
            if (mo13758a().hasExtra("adCacheTtl")) {
                this.f432j = (Long) mo13758a().getSerializableExtra("adCacheTtl");
                return;
            }
            return;
        }
        if (bundle.containsKey("postrollHtml")) {
            mo13763a(bundle.getString("postrollHtml"));
        }
        if (bundle.containsKey("lastLoadTime")) {
            this.f431i = (Long) bundle.getSerializable("lastLoadTime");
        }
        if (bundle.containsKey("adCacheTtl")) {
            this.f432j = (Long) bundle.getSerializable("adCacheTtl");
        }
    }

    /* renamed from: b */
    public void mo13770b(Bundle bundle) {
        super.mo13770b(bundle);
        if (mo13776f() != null) {
            bundle.putString("postrollHtml", mo13776f());
        }
        Long l = this.f431i;
        if (l != null) {
            bundle.putLong("lastLoadTime", l.longValue());
        }
        Long l2 = this.f432j;
        if (l2 != null) {
            bundle.putLong("adCacheTtl", l2.longValue());
        }
    }

    /* renamed from: u */
    public void mo13791u() {
        if (mo14372G()) {
            C1270g.m2078a("InterstitialMode", 3, "Ad Cache TTL passed, finishing");
            mo13786p();
            return;
        }
        C1174m.m1649a().mo14978a(true);
        if (this.f435m == null) {
            this.f435m = new C1155i(mo13768b(), mo13778h(), mo13799D(), mo13801F());
        }
        WebView webView = this.f426d;
        if (webView == null) {
            RelativeLayout relativeLayout = new RelativeLayout(mo13768b());
            this.f428f = relativeLayout;
            relativeLayout.setContentDescription("StartApp Ad");
            this.f428f.setId(AdsConstants.STARTAPP_AD_MAIN_LAYOUT_ID);
            mo13768b().setContentView(this.f428f);
            try {
                WebView webView2 = new WebView(mo13768b().getApplicationContext());
                this.f426d = webView2;
                webView2.setBackgroundColor(-16777216);
                mo13768b().getWindow().getDecorView().findViewById(16908290).setBackgroundColor(7829367);
                this.f426d.setVerticalScrollBarEnabled(false);
                this.f426d.setHorizontalScrollBarEnabled(false);
                this.f426d.getSettings().setJavaScriptEnabled(true);
                C1261c.m2027a(this.f426d);
                if (this.f405c) {
                    C1261c.m2028a(this.f426d, (Paint) null);
                }
                this.f426d.setOnLongClickListener(new View.OnLongClickListener() {
                    public boolean onLongClick(View view) {
                        return true;
                    }
                });
                this.f426d.setLongClickable(false);
                this.f426d.addJavascriptInterface(mo13809y(), "startappwall");
                mo13810z();
                mo13802a(this.f426d);
                C1061i.m1183a((Context) mo13768b(), this.f426d, mo13776f());
                this.f437o = CleanerProperties.BOOL_ATT_TRUE.equals(C1061i.m1179a(mo13776f(), "@jsTag@", "@jsTag@"));
                mo13808x();
                this.f428f.addView(this.f426d, new RelativeLayout.LayoutParams(-1, -1));
                mo13761a(this.f428f);
            } catch (Exception e) {
                C1132f.m1527a(mo13768b(), C1130d.EXCEPTION, "InterstitialMode.onResume - WebView failed", e.getMessage(), "");
                mo13786p();
            }
        } else {
            C1261c.m2041c(webView);
            this.f435m.mo14945a();
        }
        this.f433k = System.currentTimeMillis();
    }

    /* renamed from: v */
    public void mo13792v() {
        super.mo13792v();
        C5111b bVar = this.f427e;
        if (bVar != null) {
            bVar.mo41830b();
            this.f427e = null;
        }
        C1061i.m1192a((Object) this.f426d, 1000);
    }

    /* access modifiers changed from: protected */
    /* renamed from: x */
    public void mo13808x() {
        this.f426d.setWebViewClient(new C0862a());
        this.f426d.setWebChromeClient(new WebChromeClient());
    }

    /* renamed from: G */
    private boolean mo14372G() {
        if (mo13793w() instanceof C0874c) {
            return ((C0874c) mo13793w()).hasAdCacheTtlPassed();
        }
        return false;
    }

    /* access modifiers changed from: protected */
    /* renamed from: y */
    public JsInterface mo13809y() {
        Activity b = mo13768b();
        Runnable runnable = this.f429g;
        return new JsInterface(b, runnable, runnable, this.f430h, mo13798C(), mo13766a(0));
    }

    /* access modifiers changed from: protected */
    /* renamed from: z */
    public void mo13810z() {
        this.f435m.mo14945a();
    }

    /* renamed from: a */
    public void mo13802a(WebView webView) {
        this.f436n = false;
        webView.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View view, MotionEvent motionEvent) {
                boolean unused = C0855c.this.f436n = true;
                if (motionEvent.getAction() == 2) {
                    return true;
                }
                return false;
            }
        });
    }

    /* renamed from: b */
    public void mo13805b(WebView webView) {
        if (webView != null) {
            webView.setOnTouchListener((View.OnTouchListener) null);
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: A */
    public void mo13796A() {
        View a;
        if (MetaData.getInstance().isOmsdkEnabled() && this.f427e == null) {
            C5111b a2 = C1247a.m1971a(this.f426d);
            this.f427e = a2;
            if (a2 != null && this.f426d != null) {
                if (!(this.f403a == null || (a = this.f403a.mo14712a()) == null)) {
                    this.f427e.mo41831b(a);
                }
                this.f427e.mo41829a(this.f426d);
                this.f427e.mo41828a();
                C5109a.m6983a(this.f427e).mo41817a();
            }
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo13803a(String str, Object... objArr) {
        C1061i.m1188a(this.f426d, str, objArr);
    }

    /* renamed from: com.startapp.android.publish.ads.a.c$a */
    /* compiled from: StartAppSDK */
    class C0862a extends WebViewClient {
        C0862a() {
        }

        public void onPageFinished(WebView webView, String str) {
            C0855c.this.mo13807c(webView);
            C0855c cVar = C0855c.this;
            cVar.mo13803a("gClientInterface.setMode", cVar.mo13777g());
            C0855c.this.mo13803a("enableScheme", "externalLinks");
            C0855c.this.mo13796A();
        }

        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            C1270g.m2076a(2, "MyWebViewClient::shouldOverrideUrlLoading - [" + str + "]");
            if (!C0855c.this.f437o || C0855c.this.f436n) {
                return C0855c.this.mo13804a(str, false);
            }
            return false;
        }
    }

    /* renamed from: b */
    private boolean m507b(C1040Ad ad) {
        return C1061i.m1194a(8) && (ad instanceof C0939b);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public boolean mo13804a(String str, boolean z) {
        this.f435m.mo14946a(true);
        boolean z2 = C1103c.m1389a(mo13768b().getApplicationContext(), this.f404b) && !m507b(mo13793w());
        if (mo13806b(str)) {
            try {
                int a = C1103c.m1364a(str);
                if (!mo13774d()[a] || z2) {
                    C1270g.m2076a(6, "forceExternal - interMode - redirect");
                    m505b(str, a, z);
                } else {
                    C1270g.m2076a(6, "forceExternal -interMode - smartredirect");
                    m502a(str, a, z);
                }
            } catch (Exception unused) {
                C1270g.m2076a(6, "Error while trying parsing index from url");
                return false;
            }
        } else if (!mo13774d()[0] || z2) {
            C1270g.m2076a(6, "forceExternal - interMode - smartredirect");
            m505b(str, 0, z);
        } else {
            C1270g.m2076a(6, "forceExternal - interMode - redirectr");
            m502a(str, 0, z);
        }
        return true;
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public boolean mo13806b(String str) {
        return !this.f437o && str.contains("index=");
    }

    /* access modifiers changed from: protected */
    /* renamed from: B */
    public void mo13797B() {
        String[] l = mo13782l();
        if (l != null && l.length > 0 && mo13782l()[0] != null) {
            C1103c.m1395b((Context) mo13768b(), mo13782l()[0], mo13798C());
        }
    }

    /* renamed from: a */
    private void m502a(String str, int i, boolean z) {
        int i2 = i;
        Activity b = mo13768b();
        String str2 = null;
        String str3 = i2 < mo13779i().length ? mo13779i()[i2] : null;
        if (i2 < mo13780j().length) {
            str2 = mo13780j()[i2];
        }
        C1103c.m1379a(b, str, str3, str2, mo13798C(), C1098b.m1303a().mo14747A(), C1098b.m1303a().mo14748B(), mo13766a(i2), mo13769b(i2), z, new Runnable() {
            public void run() {
                C0855c.this.mo13786p();
            }
        });
    }

    /* renamed from: b */
    private void m505b(String str, int i, boolean z) {
        C1275b.m2102a((Context) mo13768b()).mo15481a(new Intent("com.startapp.android.OnClickCallback"));
        C1103c.m1376a(mo13768b(), str, i < mo13779i().length ? mo13779i()[i] : null, mo13798C(), mo13766a(i) && !C1103c.m1389a(mo13768b().getApplicationContext(), this.f404b), z);
        mo13786p();
    }

    /* renamed from: p */
    public void mo13786p() {
        super.mo13786p();
        C1174m.m1649a().mo14978a(false);
        C1155i iVar = this.f435m;
        if (iVar != null) {
            iVar.mo14946a(false);
        }
        mo13768b().runOnUiThread(new Runnable() {
            public void run() {
                if (C0855c.this.f426d != null) {
                    C1261c.m2039b(C0855c.this.f426d);
                }
            }
        });
    }

    /* renamed from: s */
    public void mo13789s() {
        C1155i iVar = this.f435m;
        if (iVar != null) {
            iVar.mo14947b();
        }
        if (this.f403a != null && this.f403a.mo14715b()) {
            this.f403a.mo14717d();
        }
        WebView webView = this.f426d;
        if (webView != null) {
            C1261c.m2039b(webView);
        }
        if (mo13777g().equals("back")) {
            mo13786p();
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: C */
    public C1117b mo13798C() {
        return new C1116a(mo13800E(), mo13784n());
    }

    /* access modifiers changed from: protected */
    /* renamed from: D */
    public C1117b mo13799D() {
        return new C1117b(mo13784n());
    }

    /* access modifiers changed from: protected */
    /* renamed from: E */
    public String mo13800E() {
        long currentTimeMillis = System.currentTimeMillis();
        this.f434l = currentTimeMillis;
        double d = (double) (currentTimeMillis - this.f433k);
        Double.isNaN(d);
        return String.valueOf(d / 1000.0d);
    }

    /* renamed from: r */
    public boolean mo13788r() {
        mo13797B();
        C1174m.m1649a().mo14978a(false);
        this.f435m.mo14946a(false);
        return false;
    }

    /* access modifiers changed from: protected */
    /* renamed from: F */
    public long mo13801F() {
        if (mo13785o() != null) {
            return TimeUnit.SECONDS.toMillis(mo13785o().longValue());
        }
        return TimeUnit.SECONDS.toMillis(MetaData.getInstance().getIABDisplayImpressionDelayInSeconds());
    }
}

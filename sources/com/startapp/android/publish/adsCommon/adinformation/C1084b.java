package com.startapp.android.publish.adsCommon.adinformation;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Point;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;
import com.startapp.android.publish.adsCommon.C1103c;
import com.startapp.android.publish.adsCommon.C1166k;
import com.startapp.android.publish.adsCommon.C1168l;
import com.startapp.android.publish.adsCommon.Utils.C1060h;
import com.startapp.android.publish.adsCommon.Utils.C1061i;
import com.startapp.android.publish.adsCommon.adinformation.AdInformationConfig;
import com.startapp.android.publish.adsCommon.p033f.C1130d;
import com.startapp.android.publish.adsCommon.p033f.C1131e;
import com.startapp.android.publish.adsCommon.p033f.C1132f;
import com.startapp.android.publish.common.metaData.C1237h;
import com.startapp.android.publish.common.metaData.MetaData;
import com.startapp.android.publish.common.model.AdPreferences;

/* renamed from: com.startapp.android.publish.adsCommon.adinformation.b */
/* compiled from: StartAppSDK */
public class C1084b implements View.OnClickListener {

    /* renamed from: a */
    Context f1042a;

    /* renamed from: b */
    RelativeLayout f1043b;

    /* renamed from: c */
    RelativeLayout f1044c;

    /* renamed from: d */
    AdInformationConfig f1045d;

    /* renamed from: e */
    C1237h f1046e;

    /* renamed from: f */
    private C1094d f1047f;

    /* renamed from: g */
    private WebView f1048g;

    /* renamed from: h */
    private Dialog f1049h = null;

    /* renamed from: i */
    private AdPreferences.Placement f1050i;

    /* renamed from: j */
    private Handler f1051j = new Handler();

    /* renamed from: k */
    private C1091a f1052k = C1091a.REGULAR;

    /* renamed from: l */
    private boolean f1053l = false;

    /* renamed from: m */
    private C1093c f1054m;

    /* renamed from: n */
    private Runnable f1055n = new Runnable() {
        public void run() {
            try {
                C1084b.this.mo14717d();
                C1084b.this.f1046e.mo15262a(C1084b.this.f1042a, true);
                C1084b.this.f1045d.mo14687a(C1084b.this.f1042a, true);
                C1084b.this.mo14714a(false);
            } catch (Exception e) {
                C1132f.m1529a(C1084b.this.f1042a, new C1131e(C1130d.EXCEPTION, "AdInformationObject.acceptRunnable failed", e.getMessage()), "");
            }
        }
    };

    /* renamed from: o */
    private Runnable f1056o = new Runnable() {
        public void run() {
            try {
                C1084b.this.mo14717d();
                C1168l.m1635b();
                C1084b.this.f1046e.mo15262a(C1084b.this.f1042a, false);
                C1084b.this.f1045d.mo14687a(C1084b.this.f1042a, true);
                C1084b.this.mo14714a(false);
            } catch (Exception e) {
                C1132f.m1529a(C1084b.this.f1042a, new C1131e(C1130d.EXCEPTION, "AdInformationObject.declineRunnable failed", e.getMessage()), "");
            }
        }
    };

    /* renamed from: p */
    private Runnable f1057p = new Runnable() {
        public void run() {
            try {
                C1084b.this.mo14717d();
                C1084b.this.mo14716c();
                C1084b.this.mo14714a(false);
            } catch (Exception e) {
                C1132f.m1529a(C1084b.this.f1042a, new C1131e(C1130d.EXCEPTION, "AdInformationObject.fullPrivacyPolicy failed", e.getMessage()), "");
            }
        }
    };

    /* renamed from: com.startapp.android.publish.adsCommon.adinformation.b$a */
    /* compiled from: StartAppSDK */
    public enum C1091a {
        REGULAR,
        LAYOUT
    }

    /* renamed from: com.startapp.android.publish.adsCommon.adinformation.b$b */
    /* compiled from: StartAppSDK */
    public enum C1092b {
        SMALL(AdInformationConfig.ImageResourceType.INFO_S, AdInformationConfig.ImageResourceType.INFO_EX_S),
        LARGE(AdInformationConfig.ImageResourceType.INFO_L, AdInformationConfig.ImageResourceType.INFO_EX_L);
        
        private AdInformationConfig.ImageResourceType infoExtendedType;
        private AdInformationConfig.ImageResourceType infoType;

        private C1092b(AdInformationConfig.ImageResourceType imageResourceType, AdInformationConfig.ImageResourceType imageResourceType2) {
            this.infoType = imageResourceType;
            this.infoExtendedType = imageResourceType2;
        }

        /* renamed from: a */
        public AdInformationConfig.ImageResourceType mo14724a() {
            return this.infoType;
        }
    }

    public C1084b(Context context, C1092b bVar, AdPreferences.Placement placement, C1093c cVar) {
        this.f1042a = context;
        this.f1050i = placement;
        this.f1054m = cVar;
        this.f1045d = m1267e();
        this.f1046e = MetaData.getInstance().getSimpleTokenConfig();
        this.f1047f = new C1094d(context, bVar, placement, cVar, this);
    }

    /* renamed from: a */
    public void mo14713a(RelativeLayout relativeLayout) {
        boolean z;
        if (m1268f() == null || !m1268f().mo14730e()) {
            z = m1267e().mo14689a(this.f1042a);
        } else {
            z = m1268f().mo14727b();
        }
        if (z) {
            this.f1044c = relativeLayout;
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
            if (m1268f() == null || !m1268f().mo14729d()) {
                m1267e().mo14685a(this.f1050i).addRules(layoutParams);
            } else {
                m1268f().mo14728c().addRules(layoutParams);
            }
            this.f1044c.addView(this.f1047f, layoutParams);
        }
    }

    /* renamed from: a */
    public View mo14712a() {
        return this.f1047f;
    }

    /* renamed from: b */
    public boolean mo14715b() {
        return this.f1053l;
    }

    /* renamed from: a */
    public static AdInformationConfig m1262a(Context context) {
        return C1083a.m1256b().mo14709a();
    }

    /* renamed from: e */
    private AdInformationConfig m1267e() {
        return C1083a.m1256b().mo14709a();
    }

    /* renamed from: f */
    private C1093c m1268f() {
        return this.f1054m;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo14714a(boolean z) {
        if (!this.f1050i.isInterstitial()) {
            Context context = this.f1042a;
            if (context instanceof Activity) {
                C1061i.m1182a((Activity) context, z);
            }
        }
    }

    public void onClick(View view) {
        if (!this.f1046e.mo15265b(this.f1042a) || !(this.f1042a instanceof Activity)) {
            mo14716c();
            return;
        }
        mo14714a(true);
        this.f1043b = new RelativeLayout(this.f1042a);
        try {
            WebView webView = new WebView(this.f1042a);
            this.f1048g = webView;
            webView.setWebViewClient(new WebViewClient());
            this.f1048g.setWebChromeClient(new WebChromeClient());
            this.f1048g.getSettings().setJavaScriptEnabled(true);
            this.f1048g.setHorizontalScrollBarEnabled(false);
            this.f1048g.setVerticalScrollBarEnabled(false);
            this.f1048g.loadUrl(m1263a(this.f1045d.mo14694f()));
            this.f1048g.addJavascriptInterface(new AdInformationJsInterface(this.f1042a, this.f1055n, this.f1056o, this.f1057p), "startappwall");
            Point point = new Point(1, 1);
            try {
                C1060h.m1169a((WindowManager) this.f1042a.getSystemService("window"), point);
                RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
                layoutParams.addRule(13);
                this.f1048g.setPadding(0, 0, 0, 0);
                layoutParams.setMargins(0, 0, 0, 0);
                this.f1043b.addView(this.f1048g, layoutParams);
                m1269g();
                int i = C10906.f1065a[this.f1052k.ordinal()];
                if (i == 1) {
                    m1265b(this.f1043b, point);
                } else if (i == 2) {
                    m1264a(this.f1043b, point);
                }
            } catch (Exception e) {
                C1132f.m1527a(this.f1042a, C1130d.EXCEPTION, "AdInformationObject.onClick - system service failed", e.getMessage(), "");
                mo14714a(false);
            }
        } catch (Exception e2) {
            C1132f.m1527a(this.f1042a, C1130d.EXCEPTION, "AdInformationObject.onClick - webview instantiation failed", e2.getMessage(), "");
            mo14714a(false);
        }
    }

    /* renamed from: com.startapp.android.publish.adsCommon.adinformation.b$6 */
    /* compiled from: StartAppSDK */
    static /* synthetic */ class C10906 {

        /* renamed from: a */
        static final /* synthetic */ int[] f1065a;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        static {
            /*
                com.startapp.android.publish.adsCommon.adinformation.b$a[] r0 = com.startapp.android.publish.adsCommon.adinformation.C1084b.C1091a.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                f1065a = r0
                com.startapp.android.publish.adsCommon.adinformation.b$a r1 = com.startapp.android.publish.adsCommon.adinformation.C1084b.C1091a.LAYOUT     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = f1065a     // Catch:{ NoSuchFieldError -> 0x001d }
                com.startapp.android.publish.adsCommon.adinformation.b$a r1 = com.startapp.android.publish.adsCommon.adinformation.C1084b.C1091a.REGULAR     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.startapp.android.publish.adsCommon.adinformation.C1084b.C10906.<clinit>():void");
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: c */
    public void mo14716c() {
        if (!C1061i.m1194a(256) || !MetaData.getInstance().isInAppBrowser()) {
            C1103c.m1398c(this.f1042a, this.f1045d.mo14690b());
        } else {
            C1103c.m1396b(this.f1042a, this.f1045d.mo14690b(), "");
        }
    }

    /* renamed from: d */
    public void mo14717d() {
        this.f1053l = false;
        int i = C10906.f1065a[this.f1052k.ordinal()];
        if (i == 1) {
            this.f1051j.post(new Runnable() {
                public void run() {
                    C1084b.this.f1044c.removeView(C1084b.this.f1043b);
                }
            });
        } else if (i == 2) {
            this.f1049h.dismiss();
        }
    }

    /* renamed from: g */
    private void m1269g() {
        String a = C1103c.m1367a(this.f1042a, (String) null);
        if (a != null) {
            WebView webView = this.f1048g;
            webView.loadUrl("javascript:window.onload=function(){document.getElementById('titlePlacement').innerText='" + a + "';}");
        }
    }

    /* renamed from: a */
    private void m1264a(ViewGroup viewGroup, Point point) {
        this.f1053l = true;
        Dialog dialog = new Dialog(this.f1042a);
        this.f1049h = dialog;
        dialog.requestWindowFeature(1);
        this.f1049h.setContentView(viewGroup);
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.copyFrom(this.f1049h.getWindow().getAttributes());
        layoutParams.width = (int) (((float) point.x) * 0.9f);
        layoutParams.height = (int) (((float) point.y) * 0.85f);
        this.f1049h.show();
        this.f1049h.getWindow().setAttributes(layoutParams);
    }

    /* renamed from: b */
    private void m1265b(final ViewGroup viewGroup, Point point) {
        this.f1053l = true;
        final RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams((int) (((float) point.x) * 0.9f), (int) (((float) point.y) * 0.85f));
        layoutParams.addRule(13);
        this.f1051j.post(new Runnable() {
            public void run() {
                C1084b.this.f1044c.addView(viewGroup, layoutParams);
            }
        });
    }

    /* renamed from: a */
    private String m1263a(String str) {
        StringBuilder sb = new StringBuilder(str);
        if (m1266b(this.f1042a)) {
            sb.append("?le=true");
        }
        return sb.toString();
    }

    /* renamed from: b */
    public static boolean m1266b(Context context) {
        return C1166k.m1606a(context, "shared_prefs_using_location", (Boolean) false).booleanValue();
    }
}

package com.appnext.ads.interstitial;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.webkit.ConsoleMessage;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;
import androidx.core.view.ViewCompat;
import com.appnext.C4703R;
import com.appnext.ads.AdsError;
import com.appnext.ads.C4708a;
import com.appnext.ads.C4711b;
import com.appnext.core.AppnextActivity;
import com.appnext.core.AppnextAd;
import com.appnext.core.AppnextError;
import com.appnext.core.C4924Ad;
import com.appnext.core.C4956e;
import com.appnext.core.C4967f;
import com.appnext.core.C4977k;
import com.appnext.core.C4986p;
import com.appnext.core.C4990q;
import com.appnext.core.result.C5010a;
import com.appnext.core.result.C5012c;
import com.appnext.core.result.C5013d;
import com.appnext.core.result.ResultPageActivity;
import com.appnext.core.webview.AppnextWebView;
import com.appnext.core.webview.WebAppInterface;
import com.mopub.common.AdType;
import com.mopub.common.Constants;
import com.mopub.network.ImpressionData;
import java.util.ArrayList;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class InterstitialActivity extends AppnextActivity {
    /* access modifiers changed from: private */

    /* renamed from: aE */
    public AppnextAd f4342aE;

    /* renamed from: aF */
    private C4711b f4343aF;
    /* access modifiers changed from: private */
    public ArrayList<AppnextAd> ads;
    /* access modifiers changed from: private */
    public Boolean autoPlay;

    /* renamed from: bP */
    protected WebView f4344bP;

    /* renamed from: bQ */
    private boolean f4345bQ = false;
    /* access modifiers changed from: private */

    /* renamed from: bR */
    public Interstitial f4346bR;
    /* access modifiers changed from: private */

    /* renamed from: bS */
    public InterstitialAd f4347bS;

    /* renamed from: bT */
    private String f4348bT = "";

    /* renamed from: bU */
    private int f4349bU = 0;
    /* access modifiers changed from: private */

    /* renamed from: bV */
    public Handler f4350bV;

    /* renamed from: bW */
    private C4956e.C4965a f4351bW;

    /* renamed from: bX */
    private WebAppInterface f4352bX;

    /* renamed from: bY */
    private boolean f4353bY = false;

    /* renamed from: bZ */
    private boolean f4354bZ = false;
    /* access modifiers changed from: private */

    /* renamed from: ca */
    public String f4355ca;
    private Boolean canClose;
    /* access modifiers changed from: private */

    /* renamed from: cc */
    public String f4356cc = "";
    /* access modifiers changed from: private */

    /* renamed from: cd */
    public boolean f4357cd = false;
    /* access modifiers changed from: private */

    /* renamed from: ce */
    public Runnable f4358ce = new Runnable() {
        public final void run() {
            InterstitialActivity.m6366l(InterstitialActivity.this);
        }
    };
    private boolean closed = false;
    private Boolean mute;

    /* renamed from: a */
    protected static void m6349a(String str, String str2, String str3) {
    }

    /* renamed from: u */
    static /* synthetic */ int m6375u(InterstitialActivity interstitialActivity) {
        int i = interstitialActivity.f4349bU;
        interstitialActivity.f4349bU = i + 1;
        return i;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        setRequestedOrientation(7);
        super.onCreate(bundle);
        if (Interstitial.currentAd == null) {
            finish();
            return;
        }
        this.f4346bR = new Interstitial(Interstitial.currentAd);
        if (getRequestedOrientation() == 6) {
            report(C4708a.f4184ai);
        } else {
            report(C4708a.f4185aj);
        }
        this.f4693gl = new RelativeLayout(this);
        setContentView(this.f4693gl);
        this.f4693gl.getLayoutParams().width = -1;
        this.f4693gl.getLayoutParams().height = -1;
        this.f4693gl.setBackgroundColor(-1);
        try {
            this.placementID = getIntent().getExtras().getString("id");
            if (getIntent().hasExtra("auto_play")) {
                Boolean valueOf = Boolean.valueOf(getIntent().getBooleanExtra("auto_play", true));
                this.autoPlay = valueOf;
                if (valueOf.booleanValue()) {
                    report(C4708a.f4180ae);
                } else {
                    report(C4708a.f4181af);
                }
            }
            if (getIntent().hasExtra("can_close")) {
                this.canClose = Boolean.valueOf(getIntent().getBooleanExtra("can_close", false));
            }
            if (getIntent().hasExtra("mute")) {
                Boolean valueOf2 = Boolean.valueOf(getIntent().getBooleanExtra("mute", true));
                this.mute = valueOf2;
                if (valueOf2.booleanValue()) {
                    report(C4708a.f4182ag);
                } else {
                    report(C4708a.f4183ah);
                }
            }
            if (getIntent().hasExtra("pview")) {
                this.f4691gj = getIntent().getStringExtra("pview");
                this.banner = getIntent().getStringExtra("banner");
                this.guid = getIntent().getStringExtra("guid");
            }
            if (getIntent().getSerializableExtra("ads") != null) {
                this.ads = (ArrayList) getIntent().getSerializableExtra("ads");
            }
            this.f4350bV = new Handler();
            AppnextWebView.m6957u(this).mo41343a(this.f4346bR.getPageUrl(), (AppnextWebView.C5017c) new AppnextWebView.C5017c() {
                /* renamed from: f */
                public final void mo40715f(String str) {
                    InterstitialActivity.m6347a(InterstitialActivity.this);
                }

                public final void error(String str) {
                    InterstitialActivity.m6347a(InterstitialActivity.this);
                }
            });
            this.f4351bW = new C4956e.C4965a() {
                public final void onMarket(String str) {
                    new StringBuilder("marketUrl ").append(str);
                    if (InterstitialActivity.this.handler != null) {
                        InterstitialActivity.this.handler.removeCallbacks((Runnable) null);
                    }
                    InterstitialActivity.this.mo41115ba();
                }

                public final void error(String str) {
                    if (InterstitialActivity.this.handler != null) {
                        InterstitialActivity.this.handler.removeCallbacks((Runnable) null);
                    }
                    InterstitialActivity.this.mo41115ba();
                    String unused = InterstitialActivity.this.placementID;
                    StringBuilder sb = new StringBuilder();
                    sb.append(new InterstitialAd(InterstitialActivity.this.f4342aE).getAppURL());
                    sb.append(StringUtils.SPACE);
                    sb.append(str);
                }
            };
            this.userAction = new C4990q(this, new C4990q.C4999a() {
                public final void report(String str) {
                }

                /* renamed from: e */
                public final C4924Ad mo40517e() {
                    return InterstitialActivity.this.f4346bR;
                }

                /* renamed from: f */
                public final AppnextAd mo40518f() {
                    return InterstitialActivity.this.f4342aE;
                }

                /* renamed from: g */
                public final C4986p mo40519g() {
                    return InterstitialActivity.this.getConfig();
                }
            });
            new Thread(new Runnable() {
                public final void run() {
                    InterstitialActivity interstitialActivity = InterstitialActivity.this;
                    String unused = interstitialActivity.f4356cc = C4967f.m6827b((Context) interstitialActivity, true);
                }
            }).start();
        } catch (Throwable unused) {
            finish();
        }
    }

    /* renamed from: v */
    private void m6377v() {
        try {
            AppnextWebView u = AppnextWebView.m6957u(this);
            this.f4344bP = u.mo41345ai(this.ads != null ? "fullscreen" : AdType.INTERSTITIAL);
            WebView a = u.mo41341a(this, this.f4346bR.getPageUrl(), mo40711w(), this.f4346bR.getFallback(), this.ads != null ? "fullscreen" : AdType.INTERSTITIAL);
            this.f4344bP = a;
            a.setWebViewClient(new WebViewClient() {
                public final boolean shouldOverrideUrlLoading(WebView webView, String str) {
                    if (str == null) {
                        return false;
                    }
                    if (!str.startsWith(Constants.HTTP)) {
                        return super.shouldOverrideUrlLoading(webView, str);
                    }
                    webView.loadUrl(str);
                    return true;
                }

                public final void onPageFinished(WebView webView, String str) {
                    super.onPageFinished(webView, str);
                    InterstitialActivity.this.f4350bV.removeCallbacksAndMessages((Object) null);
                    InterstitialActivity.m6366l(InterstitialActivity.this);
                }
            });
            this.f4344bP.setWebChromeClient(new WebChromeClient() {
                public final boolean onConsoleMessage(ConsoleMessage consoleMessage) {
                    new StringBuilder("console ").append(consoleMessage.message());
                    if (consoleMessage.message().contains("pause")) {
                        return true;
                    }
                    if (!consoleMessage.message().contains("TypeError") && !consoleMessage.message().contains("has no method") && !consoleMessage.message().contains("is not a function")) {
                        return true;
                    }
                    InterstitialActivity.this.onError(AppnextError.INTERNAL_ERROR);
                    InterstitialActivity.this.finish();
                    return true;
                }
            });
        } catch (Throwable unused) {
            onError(AppnextError.INTERNAL_ERROR);
            finish();
        }
    }

    private void pageFinished() {
        Handler handler = this.f4350bV;
        if (handler != null) {
            handler.removeCallbacks(this.f4358ce);
        }
        this.f4345bQ = true;
        String string = getIntent().getExtras().getString("creative");
        this.f4348bT = string;
        if (string == null || string.equals(Interstitial.TYPE_MANAGED)) {
            this.f4348bT = m6355d("creative");
        }
        new Thread(new Runnable() {
            public final void run() {
                InterstitialActivity.this.mo40712x();
            }
        }).start();
        WebView webView = this.f4344bP;
        if (webView == null) {
            onError(AppnextError.INTERNAL_ERROR);
            finish();
            return;
        }
        if (webView.getParent() != null) {
            ((ViewGroup) this.f4344bP.getParent()).removeView(this.f4344bP);
        }
        this.f4693gl.addView(this.f4344bP);
        this.f4344bP.getLayoutParams().width = -1;
        this.f4344bP.getLayoutParams().height = -1;
    }

    /* access modifiers changed from: protected */
    public final C4986p getConfig() {
        return C4795c.m6424K();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        Boolean bool;
        super.onResume();
        if (this.f4357cd) {
            onClose();
            finish();
            return;
        }
        if (this.f4345bQ && (bool = this.autoPlay) != null && bool.booleanValue()) {
            loadJS("Appnext.Layout.Video.play();");
        }
        try {
            this.f4344bP.loadUrl("javascript:(function() { try{Appnext.countToClose();}catch(e){}})()");
        } catch (Throwable unused) {
        }
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        WebView webView;
        super.onPause();
        if (!this.closed && (webView = this.f4344bP) != null) {
            webView.loadUrl("javascript:(function() { Appnext.Layout.Video.pause();})()");
        }
    }

    public void onBackPressed() {
        Boolean bool = this.canClose;
        if (bool != null) {
            if (!bool.booleanValue()) {
                return;
            }
        } else if (!Boolean.parseBoolean(m6355d("can_close"))) {
            return;
        }
        loadJS("Appnext.Layout.destroy('internal');");
        this.closed = true;
        onClose();
        finish();
    }

    /* access modifiers changed from: protected */
    public final void onError(final String str) {
        runOnUiThread(new Runnable() {
            public final void run() {
                if (InterstitialActivity.this.f4346bR != null && InterstitialActivity.this.f4346bR.getOnAdErrorCallback() != null) {
                    InterstitialActivity.this.f4346bR.getOnAdErrorCallback().adError(str);
                }
            }
        });
    }

    /* renamed from: e */
    private void m6359e(String str) {
        C4793a.m6403G();
        AppnextAd appnextAd = (AppnextAd) C4793a.parseAd(str);
        if (appnextAd != null) {
            this.f4342aE = new InterstitialAd(appnextAd);
            Interstitial interstitial = this.f4346bR;
            if (!(interstitial == null || interstitial.getOnAdClickedCallback() == null)) {
                this.f4346bR.getOnAdClickedCallback().adClicked();
            }
            mo40484b(this.f4342aE, this.f4351bW);
            report(C4708a.f4172V);
            String bannerID = this.f4342aE.getBannerID();
            InterstitialAd interstitialAd = this.f4347bS;
            if (bannerID.equals(interstitialAd != null ? interstitialAd.getBannerID() : "")) {
                if (!this.f4353bY) {
                    this.f4353bY = true;
                    report(C4708a.f4187al);
                }
            } else if (!this.f4354bZ) {
                this.f4354bZ = true;
                report(C4708a.f4186ak);
            }
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public final void mo40484b(AppnextAd appnextAd, C4956e.C4965a aVar) {
        mo41112a((ViewGroup) this.f4693gl, getResources().getDrawable(C4703R.C4705drawable.apnxt_loader));
        super.mo40484b(appnextAd, aVar);
    }

    /* access modifiers changed from: protected */
    /* renamed from: w */
    public final WebAppInterface mo40711w() {
        if (this.f4352bX == null) {
            this.f4352bX = new WebInterface();
        }
        return this.f4352bX;
    }

    protected class WebInterface extends WebAppInterface {
        @JavascriptInterface
        public String filterAds(String str) {
            return str;
        }

        @JavascriptInterface
        public void gotoAppWall() {
        }

        @JavascriptInterface
        public String loadAds() {
            return "";
        }

        @JavascriptInterface
        public void videoPlayed() {
        }

        public WebInterface() {
            super(InterstitialActivity.this);
        }

        @JavascriptInterface
        public void destroy(String str) {
            if (str.equals("c_close")) {
                boolean unused = InterstitialActivity.this.f4357cd = true;
                InterstitialActivity.this.runOnUiThread(new Runnable() {
                    public final void run() {
                        try {
                            JSONObject jSONObject = new JSONObject(InterstitialActivity.this.f4347bS.getAdJSON());
                            jSONObject.put("urlApp", jSONObject.getString("urlApp") + "&tem_id=156");
                            InterstitialActivity.m6352b(InterstitialActivity.this, jSONObject.toString());
                        } catch (JSONException unused) {
                            InterstitialActivity.m6352b(InterstitialActivity.this, InterstitialActivity.this.f4347bS.getAdJSON());
                        }
                    }
                });
            } else if (str.equals("close")) {
                InterstitialActivity.this.runOnUiThread(new Runnable() {
                    public final void run() {
                        InterstitialActivity.this.onClose();
                        InterstitialActivity.this.finish();
                    }
                });
            } else {
                InterstitialActivity.this.runOnUiThread(new Runnable() {
                    public final void run() {
                        InterstitialActivity.this.onError(AdsError.AD_NOT_READY);
                        InterstitialActivity.this.finish();
                    }
                });
            }
        }

        @JavascriptInterface
        public void notifyImpression(String str) {
            super.notifyImpression(str);
            if (InterstitialActivity.this.f4347bS != null) {
                InterstitialActivity.this.f4347bS.setImpressionURL(str);
                InterstitialActivity.this.handler.postDelayed(new C4791a(str), Long.parseLong(InterstitialActivity.this.getConfig().get("postpone_impression_sec")) * 1000);
            }
            if (InterstitialActivity.this.autoPlay != null && InterstitialActivity.this.autoPlay.booleanValue()) {
                play();
            }
        }

        @JavascriptInterface
        public void postView(String str) {
            if (Boolean.parseBoolean(InterstitialActivity.this.ads != null ? "false" : InterstitialActivity.this.m6355d("pview"))) {
                InterstitialActivity.this.handler.postDelayed(new C4792b(str), Long.parseLong(InterstitialActivity.this.getConfig().get("postpone_vta_sec")) * 1000);
            }
        }

        @JavascriptInterface
        public void openStore(final String str) {
            InterstitialActivity.this.runOnUiThread(new Runnable() {
                public final void run() {
                    InterstitialActivity.m6352b(InterstitialActivity.this, str);
                }
            });
        }

        @JavascriptInterface
        public void play() {
            String unused = InterstitialActivity.this.placementID;
            InterstitialActivity.this.loadJS("Appnext.Layout.Video.play();");
        }

        @JavascriptInterface
        public void openLink(String str) {
            InterstitialActivity.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
        }

        @JavascriptInterface
        public void logSTP(String str, String str2) {
            C4967f.m6820a((C4924Ad) InterstitialActivity.this.f4346bR, (AppnextAd) InterstitialActivity.this.f4347bS, str, str2, InterstitialActivity.this.getConfig());
        }

        @JavascriptInterface
        public void jsError(String str) {
            if (!TextUtils.isEmpty(str) || (!str.contains("is not a function") && !str.contains("has no method"))) {
                new StringBuilder("jsError ").append(str);
                InterstitialActivity.this.onError(AppnextError.INTERNAL_ERROR);
                InterstitialActivity.this.finish();
            } else if (InterstitialActivity.m6375u(InterstitialActivity.this) < 5) {
                InterstitialActivity.this.f4350bV.postDelayed(InterstitialActivity.this.f4358ce, 500);
            } else {
                InterstitialActivity.this.onError(AppnextError.INTERNAL_ERROR);
                InterstitialActivity.this.finish();
            }
        }

        @JavascriptInterface
        public void openResultPage(String str) {
            C5013d.m6946br().mo41339a(new C5012c() {
                /* renamed from: z */
                public final String mo40750z() {
                    return "160";
                }

                public final JSONObject getConfigParams() throws JSONException {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("vid", "2.5.1.472");
                    String str = "";
                    jSONObject.put("tid", InterstitialActivity.this.f4346bR == null ? str : InterstitialActivity.this.f4346bR.getTID());
                    if (InterstitialActivity.this.f4346bR != null) {
                        str = InterstitialActivity.this.f4346bR.getAUID();
                    }
                    jSONObject.put("auid", str);
                    jSONObject.put("osid", "100");
                    jSONObject.put("tem_id", "1601");
                    jSONObject.put("id", getPlacementId());
                    jSONObject.put("cat", InterstitialActivity.this.f4347bS.getCategories());
                    jSONObject.put("pview", InterstitialActivity.this.getConfig().get("pview"));
                    jSONObject.put("devn", C4967f.m6834be());
                    jSONObject.put("dosv", Build.VERSION.SDK_INT);
                    jSONObject.put("dds", "0");
                    jSONObject.put("ads_type", "banner");
                    jSONObject.put(ImpressionData.COUNTRY, InterstitialActivity.this.f4347bS.getCountry());
                    jSONObject.put("gdpr", C4977k.m6867a((AppnextAd) InterstitialActivity.this.f4347bS, InterstitialActivity.this.getConfig()));
                    return jSONObject;
                }

                public final AppnextAd getSelectedAd() {
                    return InterstitialActivity.this.f4347bS;
                }

                public final String getPlacementId() {
                    return InterstitialActivity.this.placementID;
                }

                /* renamed from: A */
                public final String mo40742A() {
                    return C4793a.m6403G().mo41250l((C4924Ad) InterstitialActivity.this.f4346bR);
                }

                /* renamed from: B */
                public final String mo40743B() {
                    return InterstitialActivity.this.f4355ca;
                }

                /* renamed from: C */
                public final C4986p mo40744C() {
                    return InterstitialActivity.this.getConfig();
                }

                /* renamed from: D */
                public final C4924Ad mo40745D() {
                    return InterstitialActivity.this.f4346bR;
                }

                /* renamed from: E */
                public final C5010a mo40746E() {
                    return new C5010a() {
                        /* renamed from: F */
                        public final Object mo40751F() {
                            return null;
                        }

                        public final String getFallbackScript() {
                            return null;
                        }

                        public final String getJSurl() {
                            return "https://cdn.appnext.com/tools/sdk/interstitial/v75/result.min.js";
                        }

                        public final WebViewClient getWebViewClient() {
                            return null;
                        }
                    };
                }
            });
            Intent intent = new Intent(InterstitialActivity.this, ResultPageActivity.class);
            intent.putExtra("shouldClose", false);
            intent.setFlags(65536);
            InterstitialActivity.this.startActivity(intent);
        }
    }

    /* renamed from: com.appnext.ads.interstitial.InterstitialActivity$a */
    private class C4791a implements Runnable {

        /* renamed from: ch */
        AppnextAd f4381ch;

        C4791a(String str) {
            InterstitialAd interstitialAd = new InterstitialAd(InterstitialActivity.this.f4347bS);
            this.f4381ch = interstitialAd;
            interstitialAd.setImpressionURL(str);
        }

        public final void run() {
            try {
                if (InterstitialActivity.this.userAction != null) {
                    InterstitialActivity.this.userAction.mo41317e(this.f4381ch);
                    InterstitialActivity.this.report(C4708a.f4157G);
                }
            } catch (Throwable unused) {
            }
        }
    }

    /* renamed from: com.appnext.ads.interstitial.InterstitialActivity$b */
    private class C4792b implements Runnable {

        /* renamed from: ch */
        AppnextAd f4383ch;

        C4792b(String str) {
            try {
                C4793a.m6403G();
                this.f4383ch = (AppnextAd) C4793a.parseAd(str);
            } catch (Throwable unused) {
            }
        }

        public final void run() {
            InterstitialActivity.this.mo40483a(this.f4383ch, (C4956e.C4965a) null);
        }
    }

    private void play() {
        loadJS("Appnext.Layout.Video.play();");
    }

    private void stop() {
        WebView webView = this.f4344bP;
        if (webView != null) {
            webView.loadUrl("javascript:(function() { Appnext.Layout.Video.pause();})()");
        }
    }

    /* access modifiers changed from: private */
    public void onClose() {
        Interstitial interstitial = this.f4346bR;
        if (interstitial != null && interstitial.getOnAdClosedCallback() != null) {
            this.f4346bR.getOnAdClosedCallback().onAdClosed();
        }
    }

    /* access modifiers changed from: private */
    public void report(String str) {
        Interstitial interstitial = this.f4346bR;
        if (interstitial != null) {
            String tid = interstitial.getTID();
            String vid = this.f4346bR.getVID();
            String auid = this.f4346bR.getAUID();
            String placementID = this.f4346bR.getPlacementID();
            String sessionId = this.f4346bR.getSessionId();
            InterstitialAd interstitialAd = this.f4347bS;
            String bannerID = interstitialAd != null ? interstitialAd.getBannerID() : "";
            InterstitialAd interstitialAd2 = this.f4347bS;
            C4967f.m6822a(tid, vid, auid, placementID, sessionId, str, "current_interstitial", bannerID, interstitialAd2 != null ? interstitialAd2.getCampaignID() : "");
        }
    }

    /* access modifiers changed from: private */
    public void loadJS(final String str) {
        runOnUiThread(new Runnable() {
            public final void run() {
                if (InterstitialActivity.this.f4344bP != null) {
                    WebView webView = InterstitialActivity.this.f4344bP;
                    webView.loadUrl("javascript:(function() { try { " + str + "} catch(err){ Appnext.jsError(err.message); }})()");
                }
            }
        });
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0098 A[Catch:{ all -> 0x0142 }] */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x00b8 A[Catch:{ all -> 0x0142 }] */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x00d6 A[Catch:{ all -> 0x0142 }] */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x00ee A[Catch:{ all -> 0x0142 }] */
    /* renamed from: x */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized void mo40712x() {
        /*
            r8 = this;
            monitor-enter(r8)
            com.appnext.ads.interstitial.a r0 = com.appnext.ads.interstitial.C4793a.m6403G()     // Catch:{ all -> 0x0142 }
            com.appnext.ads.interstitial.Interstitial r1 = r8.f4346bR     // Catch:{ all -> 0x0142 }
            java.lang.String r2 = r8.f4348bT     // Catch:{ all -> 0x0142 }
            java.util.ArrayList r0 = r0.mo40778b(r8, r1, r2)     // Catch:{ all -> 0x0142 }
            if (r0 != 0) goto L_0x0019
            r8.finish()     // Catch:{ all -> 0x0142 }
            java.lang.String r0 = "No Ads"
            r8.onError(r0)     // Catch:{ all -> 0x0142 }
            monitor-exit(r8)
            return
        L_0x0019:
            com.appnext.ads.interstitial.a r1 = com.appnext.ads.interstitial.C4793a.m6403G()     // Catch:{ all -> 0x0142 }
            java.lang.String r1 = r1.mo40776a(r0)     // Catch:{ all -> 0x0142 }
            if (r1 != 0) goto L_0x002d
            r8.finish()     // Catch:{ all -> 0x0142 }
            java.lang.String r0 = "No Ads"
            r8.onError(r0)     // Catch:{ all -> 0x0142 }
            monitor-exit(r8)
            return
        L_0x002d:
            java.lang.String r2 = " "
            java.lang.String r3 = "\\u2028"
            java.lang.String r1 = r1.replace(r2, r3)     // Catch:{ all -> 0x0142 }
            java.lang.String r2 = " "
            java.lang.String r3 = "\\u2029"
            java.lang.String r1 = r1.replace(r2, r3)     // Catch:{ all -> 0x0142 }
            r8.f4355ca = r1     // Catch:{ all -> 0x0142 }
            com.appnext.ads.interstitial.InterstitialAd r2 = new com.appnext.ads.interstitial.InterstitialAd     // Catch:{ all -> 0x0142 }
            r3 = 0
            java.lang.Object r4 = r0.get(r3)     // Catch:{ all -> 0x0142 }
            com.appnext.core.AppnextAd r4 = (com.appnext.core.AppnextAd) r4     // Catch:{ all -> 0x0142 }
            r2.<init>(r4)     // Catch:{ all -> 0x0142 }
            r8.f4347bS = r2     // Catch:{ all -> 0x0142 }
            android.content.res.Resources r2 = r8.getResources()     // Catch:{ all -> 0x0142 }
            android.content.res.Configuration r2 = r2.getConfiguration()     // Catch:{ all -> 0x0142 }
            int r2 = r2.orientation     // Catch:{ all -> 0x0142 }
            org.json.JSONObject r2 = r8.mo40713y()     // Catch:{ all -> 0x0142 }
            java.lang.Object r4 = r0.get(r3)     // Catch:{ all -> 0x0142 }
            com.appnext.core.AppnextAd r4 = (com.appnext.core.AppnextAd) r4     // Catch:{ all -> 0x0142 }
            java.lang.String r5 = r4.getVideoUrl()     // Catch:{ all -> 0x0142 }
            java.lang.String r6 = ""
            boolean r5 = r5.equals(r6)     // Catch:{ all -> 0x0142 }
            r6 = 1
            if (r5 == 0) goto L_0x0095
            java.lang.String r5 = r4.getVideoUrlHigh()     // Catch:{ all -> 0x0142 }
            java.lang.String r7 = ""
            boolean r5 = r5.equals(r7)     // Catch:{ all -> 0x0142 }
            if (r5 == 0) goto L_0x0095
            java.lang.String r5 = r4.getVideoUrl30Sec()     // Catch:{ all -> 0x0142 }
            java.lang.String r7 = ""
            boolean r5 = r5.equals(r7)     // Catch:{ all -> 0x0142 }
            if (r5 == 0) goto L_0x0095
            java.lang.String r4 = r4.getVideoUrlHigh30Sec()     // Catch:{ all -> 0x0142 }
            java.lang.String r5 = ""
            boolean r4 = r4.equals(r5)     // Catch:{ all -> 0x0142 }
            if (r4 != 0) goto L_0x0093
            goto L_0x0095
        L_0x0093:
            r4 = 0
            goto L_0x0096
        L_0x0095:
            r4 = 1
        L_0x0096:
            if (r4 == 0) goto L_0x00b8
            java.lang.String r4 = "remote_auto_play"
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x0142 }
            r5.<init>()     // Catch:{ all -> 0x0142 }
            java.lang.Boolean r7 = r8.autoPlay     // Catch:{ all -> 0x0142 }
            if (r7 == 0) goto L_0x00ac
            java.lang.Boolean r7 = r8.autoPlay     // Catch:{ all -> 0x0142 }
            boolean r7 = r7.booleanValue()     // Catch:{ all -> 0x0142 }
            if (r7 == 0) goto L_0x00ac
            goto L_0x00ad
        L_0x00ac:
            r6 = 0
        L_0x00ad:
            r5.append(r6)     // Catch:{ all -> 0x0142 }
            java.lang.String r5 = r5.toString()     // Catch:{ all -> 0x0142 }
            r2.put(r4, r5)     // Catch:{ all -> 0x0142 }
            goto L_0x00bf
        L_0x00b8:
            java.lang.String r4 = "remote_auto_play"
            java.lang.String r5 = "false"
            r2.put(r4, r5)     // Catch:{ all -> 0x0142 }
        L_0x00bf:
            com.appnext.ads.interstitial.InterstitialAd r4 = new com.appnext.ads.interstitial.InterstitialAd     // Catch:{ all -> 0x0142 }
            java.lang.Object r0 = r0.get(r3)     // Catch:{ all -> 0x0142 }
            com.appnext.core.AppnextAd r0 = (com.appnext.core.AppnextAd) r0     // Catch:{ all -> 0x0142 }
            r4.<init>(r0)     // Catch:{ all -> 0x0142 }
            java.lang.String r0 = r4.getButtonText()     // Catch:{ all -> 0x0142 }
            java.lang.String r3 = ""
            boolean r0 = r0.equals(r3)     // Catch:{ all -> 0x0142 }
            if (r0 == 0) goto L_0x00ee
            java.lang.String r0 = r4.getAdPackage()     // Catch:{ all -> 0x0142 }
            boolean r0 = com.appnext.core.C4967f.m6842c(r8, r0)     // Catch:{ all -> 0x0142 }
            if (r0 == 0) goto L_0x00e7
            java.lang.String r0 = "existing_button_text"
            java.lang.String r0 = r8.m6355d((java.lang.String) r0)     // Catch:{ all -> 0x0142 }
            goto L_0x00f2
        L_0x00e7:
            java.lang.String r0 = "new_button_text"
            java.lang.String r0 = r8.m6355d((java.lang.String) r0)     // Catch:{ all -> 0x0142 }
            goto L_0x00f2
        L_0x00ee:
            java.lang.String r0 = r4.getButtonText()     // Catch:{ all -> 0x0142 }
        L_0x00f2:
            java.lang.String r3 = "b_title"
            r2.put(r3, r0)     // Catch:{ all -> 0x0142 }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x0142 }
            java.lang.String r3 = "Appnext.setParams("
            r0.<init>(r3)     // Catch:{ all -> 0x0142 }
            java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x0142 }
            r0.append(r2)     // Catch:{ all -> 0x0142 }
            java.lang.String r2 = ");"
            r0.append(r2)     // Catch:{ all -> 0x0142 }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x0142 }
            r8.loadJS(r0)     // Catch:{ all -> 0x0142 }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x0142 }
            java.lang.String r2 = "Appnext.loadInterstitial("
            r0.<init>(r2)     // Catch:{ all -> 0x0142 }
            r0.append(r1)     // Catch:{ all -> 0x0142 }
            java.lang.String r1 = ");"
            r0.append(r1)     // Catch:{ all -> 0x0142 }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x0142 }
            r8.loadJS(r0)     // Catch:{ all -> 0x0142 }
            com.appnext.ads.interstitial.a r0 = com.appnext.ads.interstitial.C4793a.m6403G()     // Catch:{ all -> 0x0142 }
            com.appnext.ads.interstitial.InterstitialAd r1 = r8.f4347bS     // Catch:{ all -> 0x0142 }
            java.lang.String r1 = r1.getBannerID()     // Catch:{ all -> 0x0142 }
            com.appnext.ads.interstitial.Interstitial r2 = com.appnext.ads.interstitial.Interstitial.currentAd     // Catch:{ all -> 0x0142 }
            r0.mo40616a((java.lang.String) r1, (com.appnext.core.C4924Ad) r2)     // Catch:{ all -> 0x0142 }
            android.os.Handler r0 = r8.handler     // Catch:{ all -> 0x0142 }
            com.appnext.ads.interstitial.InterstitialActivity$3 r1 = new com.appnext.ads.interstitial.InterstitialActivity$3     // Catch:{ all -> 0x0142 }
            r1.<init>()     // Catch:{ all -> 0x0142 }
            r0.post(r1)     // Catch:{ all -> 0x0142 }
            monitor-exit(r8)
            return
        L_0x0142:
            r0 = move-exception
            r8.finish()     // Catch:{ all -> 0x0150 }
            java.lang.String r1 = "Internal error"
            r8.onError(r1)     // Catch:{ all -> 0x0150 }
            com.appnext.core.C4967f.m6829b((java.lang.Throwable) r0)     // Catch:{ all -> 0x0150 }
            monitor-exit(r8)
            return
        L_0x0150:
            r0 = move-exception
            monitor-exit(r8)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appnext.ads.interstitial.InterstitialActivity.mo40712x():void");
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:42:?, code lost:
        r3.put("icon_color", "");
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:41:0x027d */
    /* renamed from: y */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final org.json.JSONObject mo40713y() throws org.json.JSONException {
        /*
            r11 = this;
            java.lang.String r0 = "icon_color"
            com.appnext.ads.interstitial.Interstitial r1 = r11.f4346bR
            java.lang.String r1 = r1.getButtonColor()
            java.lang.String r2 = ""
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L_0x0017
            java.lang.String r1 = "button_color"
            java.lang.String r1 = r11.m6355d((java.lang.String) r1)
            goto L_0x001d
        L_0x0017:
            com.appnext.ads.interstitial.Interstitial r1 = r11.f4346bR
            java.lang.String r1 = r1.getButtonColor()
        L_0x001d:
            java.lang.String r3 = "#"
            boolean r3 = r1.startsWith(r3)
            r4 = 1
            if (r3 == 0) goto L_0x002a
            java.lang.String r1 = r1.substring(r4)
        L_0x002a:
            java.lang.Boolean r3 = r11.autoPlay
            java.lang.String r5 = "auto_play"
            if (r3 != 0) goto L_0x003e
            java.lang.String r3 = r11.m6355d((java.lang.String) r5)
            boolean r3 = java.lang.Boolean.parseBoolean(r3)
            java.lang.Boolean r3 = java.lang.Boolean.valueOf(r3)
            r11.autoPlay = r3
        L_0x003e:
            java.lang.Boolean r3 = r11.mute
            java.lang.String r6 = "mute"
            if (r3 != 0) goto L_0x0052
            java.lang.String r3 = r11.m6355d((java.lang.String) r6)
            boolean r3 = java.lang.Boolean.parseBoolean(r3)
            java.lang.Boolean r3 = java.lang.Boolean.valueOf(r3)
            r11.mute = r3
        L_0x0052:
            org.json.JSONObject r3 = new org.json.JSONObject
            r3.<init>()
            java.lang.String r7 = r11.placementID
            java.lang.String r8 = "id"
            r3.put(r8, r7)
            com.appnext.ads.interstitial.Interstitial r7 = r11.f4346bR
            java.lang.String r7 = r7.getCategories()
            java.lang.String r8 = "cat"
            r3.put(r8, r7)
            com.appnext.ads.interstitial.Interstitial r7 = r11.f4346bR
            java.lang.String r7 = r7.getPostback()
            java.lang.String r8 = "pbk"
            r3.put(r8, r7)
            java.lang.String r7 = "b_color"
            r3.put(r7, r1)
            java.util.ArrayList<com.appnext.core.AppnextAd> r1 = r11.ads
            java.lang.String r7 = "show_desc"
            if (r1 != 0) goto L_0x010d
            com.appnext.ads.interstitial.Interstitial r1 = r11.f4346bR
            java.lang.String r1 = r1.getSkipText()
            boolean r1 = r1.equals(r2)
            java.lang.String r8 = "skip_title"
            if (r1 == 0) goto L_0x0092
            java.lang.String r1 = r11.m6355d((java.lang.String) r8)
            goto L_0x0098
        L_0x0092:
            com.appnext.ads.interstitial.Interstitial r1 = r11.f4346bR
            java.lang.String r1 = r1.getSkipText()
        L_0x0098:
            r3.put(r8, r1)
            java.util.ArrayList<com.appnext.core.AppnextAd> r1 = r11.ads
            java.lang.String r8 = "pview"
            if (r1 == 0) goto L_0x00a4
            java.lang.String r1 = "false"
            goto L_0x00a8
        L_0x00a4:
            java.lang.String r1 = r11.m6355d((java.lang.String) r8)
        L_0x00a8:
            r3.put(r8, r1)
            java.lang.String r1 = "video_length"
            java.lang.String r8 = r11.m6355d((java.lang.String) r1)
            r3.put(r1, r8)
            java.lang.String r1 = "min_internet_connection"
            java.lang.String r8 = r11.m6355d((java.lang.String) r1)
            r3.put(r1, r8)
            java.lang.String r1 = "min_internet_connection_video"
            java.lang.String r8 = r11.m6355d((java.lang.String) r1)
            r3.put(r1, r8)
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.Boolean r8 = r11.mute
            r1.append(r8)
            java.lang.String r1 = r1.toString()
            r3.put(r6, r1)
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.Boolean r6 = r11.autoPlay
            r1.append(r6)
            java.lang.String r1 = r1.toString()
            r3.put(r5, r1)
            java.lang.String r1 = "remove_poster_on_auto_play"
            java.lang.String r5 = r11.m6355d((java.lang.String) r1)
            r3.put(r1, r5)
            java.lang.String r1 = "show_rating"
            java.lang.String r5 = r11.m6355d((java.lang.String) r1)
            r3.put(r1, r5)
            java.lang.String r1 = r11.m6355d((java.lang.String) r7)
            r3.put(r7, r1)
            java.lang.String r1 = r11.f4348bT
            java.lang.String r5 = "creative"
            r3.put(r5, r1)
            java.lang.String r1 = "remote_auto_play"
            r3.put(r1, r4)
        L_0x010d:
            java.lang.String r1 = "stp_flag"
            java.lang.String r5 = r11.m6355d((java.lang.String) r1)
            r3.put(r1, r5)
            java.lang.String r1 = "ext"
            java.lang.String r5 = "t"
            r3.put(r1, r5)
            java.lang.String r1 = com.appnext.core.C4967f.m6849o((android.content.Context) r11)
            java.lang.String r5 = "dct"
            r3.put(r5, r1)
            java.lang.String r1 = r11.f4356cc
            java.lang.String r5 = "did"
            r3.put(r5, r1)
            java.lang.String r1 = com.appnext.core.C4967f.m6834be()
            java.lang.String r5 = "devn"
            r3.put(r5, r1)
            int r1 = android.os.Build.VERSION.SDK_INT
            java.lang.String r5 = "dosv"
            r3.put(r5, r1)
            java.lang.String r1 = "dds"
            java.lang.String r5 = "0"
            r3.put(r1, r5)
            java.lang.String r1 = "urlApp_protection"
            java.lang.String r5 = r11.m6355d((java.lang.String) r1)
            r3.put(r1, r5)
            com.appnext.ads.interstitial.Interstitial r1 = r11.f4346bR
            java.lang.String r1 = r1.getVID()
            java.lang.String r5 = "vid"
            r3.put(r5, r1)
            com.appnext.ads.interstitial.Interstitial r1 = r11.f4346bR
            java.lang.String r1 = r1.getTID()
            java.lang.String r5 = "tid"
            r3.put(r5, r1)
            com.appnext.ads.interstitial.Interstitial r1 = r11.f4346bR
            java.lang.String r1 = r1.getAUID()
            java.lang.String r5 = "auid"
            r3.put(r5, r1)
            java.lang.String r1 = "osid"
            java.lang.String r5 = "100"
            r3.put(r1, r5)
            java.lang.String r1 = "ads_type"
            java.lang.String r5 = "interstitial"
            r3.put(r1, r5)
            com.appnext.ads.interstitial.InterstitialAd r1 = r11.f4347bS
            java.lang.String r1 = r1.getCountry()
            java.lang.String r5 = "country"
            r3.put(r5, r1)
            com.appnext.ads.interstitial.InterstitialAd r1 = r11.f4347bS
            com.appnext.ads.interstitial.c r5 = com.appnext.ads.interstitial.C4795c.m6424K()
            boolean r1 = com.appnext.core.C4977k.m6867a((com.appnext.core.AppnextAd) r1, (com.appnext.core.C4986p) r5)
            java.lang.String r5 = "gdpr"
            r3.put(r5, r1)
            org.json.JSONObject r1 = new org.json.JSONObject
            com.appnext.core.a.b r5 = com.appnext.core.p086a.C4944b.m6738bp()
            java.lang.String r5 = r5.mo41226bq()
            r1.<init>(r5)
            java.lang.String r1 = r1.toString()
            java.lang.String r5 = "lang_settings"
            r3.put(r5, r1)
            com.appnext.ads.interstitial.Interstitial r1 = r11.f4346bR
            java.lang.String r1 = r1.getLanguage()
            if (r1 == 0) goto L_0x01ba
            boolean r5 = r1.equals(r2)
            if (r5 == 0) goto L_0x01c6
        L_0x01ba:
            java.util.Locale r1 = java.util.Locale.getDefault()
            java.lang.String r1 = r1.getLanguage()
            java.lang.String r1 = r1.toUpperCase()
        L_0x01c6:
            java.lang.String r5 = "lang"
            r3.put(r5, r1)
            org.json.JSONArray r1 = new org.json.JSONArray
            java.lang.String r5 = "S1"
            java.lang.String r5 = r11.m6355d((java.lang.String) r5)
            r1.<init>(r5)
            java.lang.String r1 = r1.toString()
            java.lang.String r5 = "tem"
            r3.put(r5, r1)
            java.lang.String r1 = "clickType_A"
            java.lang.String r1 = r11.m6355d((java.lang.String) r1)
            java.lang.String r5 = "click_x"
            r3.put(r5, r1)
            android.content.Intent r1 = r11.getIntent()
            if (r1 == 0) goto L_0x0205
            android.content.Intent r1 = r11.getIntent()
            boolean r1 = r1.hasExtra(r7)
            if (r1 == 0) goto L_0x0205
            android.content.Intent r1 = r11.getIntent()
            java.lang.String r1 = r1.getStringExtra(r7)
            r3.put(r7, r1)
        L_0x0205:
            com.appnext.ads.interstitial.InterstitialAd r1 = r11.f4347bS     // Catch:{ all -> 0x0285 }
            java.lang.String r1 = r1.getImageURL()     // Catch:{ all -> 0x0285 }
            android.graphics.Bitmap r1 = com.appnext.core.C4967f.m6809Y(r1)     // Catch:{ all -> 0x0285 }
            java.io.ByteArrayOutputStream r5 = new java.io.ByteArrayOutputStream     // Catch:{ all -> 0x0285 }
            r5.<init>()     // Catch:{ all -> 0x0285 }
            android.graphics.Bitmap$CompressFormat r6 = android.graphics.Bitmap.CompressFormat.PNG     // Catch:{ all -> 0x0285 }
            r7 = 100
            r1.compress(r6, r7, r5)     // Catch:{ all -> 0x0285 }
            byte[] r5 = r5.toByteArray()     // Catch:{ all -> 0x0285 }
            r6 = 0
            java.lang.String r5 = android.util.Base64.encodeToString(r5, r6)     // Catch:{ all -> 0x0285 }
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ all -> 0x0285 }
            java.lang.String r8 = "data:image/"
            r7.<init>(r8)     // Catch:{ all -> 0x0285 }
            com.appnext.ads.interstitial.InterstitialAd r8 = r11.f4347bS     // Catch:{ all -> 0x0285 }
            java.lang.String r8 = r8.getImageURL()     // Catch:{ all -> 0x0285 }
            com.appnext.ads.interstitial.InterstitialAd r9 = r11.f4347bS     // Catch:{ all -> 0x0285 }
            java.lang.String r9 = r9.getImageURL()     // Catch:{ all -> 0x0285 }
            r10 = 46
            int r9 = r9.lastIndexOf(r10)     // Catch:{ all -> 0x0285 }
            int r9 = r9 + r4
            java.lang.String r8 = r8.substring(r9)     // Catch:{ all -> 0x0285 }
            r7.append(r8)     // Catch:{ all -> 0x0285 }
            java.lang.String r8 = ";base64,"
            r7.append(r8)     // Catch:{ all -> 0x0285 }
            r7.append(r5)     // Catch:{ all -> 0x0285 }
            java.lang.String r5 = r7.toString()     // Catch:{ all -> 0x0285 }
            androidx.palette.graphics.Palette$Builder r1 = androidx.palette.graphics.Palette.from((android.graphics.Bitmap) r1)     // Catch:{ all -> 0x027d }
            androidx.palette.graphics.Palette r1 = r1.generate()     // Catch:{ all -> 0x027d }
            androidx.palette.graphics.Palette$Swatch r1 = r1.getVibrantSwatch()     // Catch:{ all -> 0x027d }
            if (r1 == 0) goto L_0x0279
            int r1 = r1.getRgb()     // Catch:{ all -> 0x027d }
            java.lang.String r7 = "#%06X"
            java.lang.Object[] r4 = new java.lang.Object[r4]     // Catch:{ all -> 0x027d }
            r8 = 16777215(0xffffff, float:2.3509886E-38)
            r1 = r1 & r8
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)     // Catch:{ all -> 0x027d }
            r4[r6] = r1     // Catch:{ all -> 0x027d }
            java.lang.String r1 = java.lang.String.format(r7, r4)     // Catch:{ all -> 0x027d }
            r3.put(r0, r1)     // Catch:{ all -> 0x027d }
            goto L_0x0280
        L_0x0279:
            r3.put(r0, r2)     // Catch:{ all -> 0x027d }
            goto L_0x0280
        L_0x027d:
            r3.put(r0, r2)     // Catch:{ all -> 0x0285 }
        L_0x0280:
            java.lang.String r0 = "icon_src"
            r3.put(r0, r5)     // Catch:{ all -> 0x0285 }
        L_0x0285:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appnext.ads.interstitial.InterstitialActivity.mo40713y():org.json.JSONObject");
    }

    /* renamed from: a */
    private static String m6345a(int i) {
        return String.format("#%06X", new Object[]{Integer.valueOf(i & ViewCompat.MEASURED_SIZE_MASK)});
    }

    private static boolean hasVideo(AppnextAd appnextAd) {
        return !appnextAd.getVideoUrl().equals("") || !appnextAd.getVideoUrlHigh().equals("") || !appnextAd.getVideoUrl30Sec().equals("") || !appnextAd.getVideoUrlHigh30Sec().equals("");
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        try {
            C4793a.m6403G().mo40780g(this.f4346bR);
            this.f4346bR.destroy();
            this.f4346bR = null;
            if (this.f4350bV != null) {
                this.f4350bV.removeCallbacksAndMessages((Object) null);
            }
            this.f4350bV = null;
            this.f4358ce = null;
            this.f4347bS = null;
            if (this.f4344bP != null) {
                this.f4344bP.stopLoading();
                if (this.f4344bP.getParent() != null) {
                    ((ViewGroup) this.f4344bP.getParent()).removeView(this.f4344bP);
                }
                this.f4344bP.setWebChromeClient((WebChromeClient) null);
                this.f4344bP.setWebViewClient((WebViewClient) null);
                this.f4344bP.destroy();
                this.f4344bP = null;
            }
            AppnextWebView.m6957u(this).mo41342a(mo40711w());
            this.f4352bX = null;
            this.f4351bW = null;
            if (this.f4343aF != null) {
                this.f4343aF.mo40469a((Context) this);
                this.f4343aF = null;
            }
        } catch (Throwable unused) {
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: d */
    public String m6355d(String str) {
        String str2 = C4795c.m6424K().get(str);
        return str2 == null ? "" : str2;
    }

    /* renamed from: a */
    static /* synthetic */ void m6347a(InterstitialActivity interstitialActivity) {
        try {
            AppnextWebView u = AppnextWebView.m6957u(interstitialActivity);
            interstitialActivity.f4344bP = u.mo41345ai(interstitialActivity.ads != null ? "fullscreen" : AdType.INTERSTITIAL);
            WebView a = u.mo41341a(interstitialActivity, interstitialActivity.f4346bR.getPageUrl(), interstitialActivity.mo40711w(), interstitialActivity.f4346bR.getFallback(), interstitialActivity.ads != null ? "fullscreen" : AdType.INTERSTITIAL);
            interstitialActivity.f4344bP = a;
            a.setWebViewClient(new WebViewClient() {
                public final boolean shouldOverrideUrlLoading(WebView webView, String str) {
                    if (str == null) {
                        return false;
                    }
                    if (!str.startsWith(Constants.HTTP)) {
                        return super.shouldOverrideUrlLoading(webView, str);
                    }
                    webView.loadUrl(str);
                    return true;
                }

                public final void onPageFinished(WebView webView, String str) {
                    super.onPageFinished(webView, str);
                    InterstitialActivity.this.f4350bV.removeCallbacksAndMessages((Object) null);
                    InterstitialActivity.m6366l(InterstitialActivity.this);
                }
            });
            interstitialActivity.f4344bP.setWebChromeClient(new WebChromeClient() {
                public final boolean onConsoleMessage(ConsoleMessage consoleMessage) {
                    new StringBuilder("console ").append(consoleMessage.message());
                    if (consoleMessage.message().contains("pause")) {
                        return true;
                    }
                    if (!consoleMessage.message().contains("TypeError") && !consoleMessage.message().contains("has no method") && !consoleMessage.message().contains("is not a function")) {
                        return true;
                    }
                    InterstitialActivity.this.onError(AppnextError.INTERNAL_ERROR);
                    InterstitialActivity.this.finish();
                    return true;
                }
            });
        } catch (Throwable unused) {
            interstitialActivity.onError(AppnextError.INTERNAL_ERROR);
            interstitialActivity.finish();
        }
    }

    /* renamed from: l */
    static /* synthetic */ void m6366l(InterstitialActivity interstitialActivity) {
        Handler handler = interstitialActivity.f4350bV;
        if (handler != null) {
            handler.removeCallbacks(interstitialActivity.f4358ce);
        }
        interstitialActivity.f4345bQ = true;
        String string = interstitialActivity.getIntent().getExtras().getString("creative");
        interstitialActivity.f4348bT = string;
        if (string == null || string.equals(Interstitial.TYPE_MANAGED)) {
            interstitialActivity.f4348bT = interstitialActivity.m6355d("creative");
        }
        new Thread(new Runnable() {
            public final void run() {
                InterstitialActivity.this.mo40712x();
            }
        }).start();
        WebView webView = interstitialActivity.f4344bP;
        if (webView == null) {
            interstitialActivity.onError(AppnextError.INTERNAL_ERROR);
            interstitialActivity.finish();
            return;
        }
        if (webView.getParent() != null) {
            ((ViewGroup) interstitialActivity.f4344bP.getParent()).removeView(interstitialActivity.f4344bP);
        }
        interstitialActivity.f4693gl.addView(interstitialActivity.f4344bP);
        interstitialActivity.f4344bP.getLayoutParams().width = -1;
        interstitialActivity.f4344bP.getLayoutParams().height = -1;
    }

    /* renamed from: b */
    static /* synthetic */ void m6352b(InterstitialActivity interstitialActivity, String str) {
        C4793a.m6403G();
        AppnextAd appnextAd = (AppnextAd) C4793a.parseAd(str);
        if (appnextAd != null) {
            interstitialActivity.f4342aE = new InterstitialAd(appnextAd);
            Interstitial interstitial = interstitialActivity.f4346bR;
            if (!(interstitial == null || interstitial.getOnAdClickedCallback() == null)) {
                interstitialActivity.f4346bR.getOnAdClickedCallback().adClicked();
            }
            interstitialActivity.mo40484b(interstitialActivity.f4342aE, interstitialActivity.f4351bW);
            interstitialActivity.report(C4708a.f4172V);
            String bannerID = interstitialActivity.f4342aE.getBannerID();
            InterstitialAd interstitialAd = interstitialActivity.f4347bS;
            if (bannerID.equals(interstitialAd != null ? interstitialAd.getBannerID() : "")) {
                if (!interstitialActivity.f4353bY) {
                    interstitialActivity.f4353bY = true;
                    interstitialActivity.report(C4708a.f4187al);
                }
            } else if (!interstitialActivity.f4354bZ) {
                interstitialActivity.f4354bZ = true;
                interstitialActivity.report(C4708a.f4186ak);
            }
        }
    }
}

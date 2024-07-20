package com.appnext.banners;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.appnext.banners.C4861g;
import com.appnext.core.AppnextAd;
import com.appnext.core.C4956e;
import com.appnext.core.C4967f;
import com.appnext.core.C4990q;
import com.appnext.core.result.C5011b;
import com.mopub.common.Constants;

public class BannerActivity extends Activity {

    /* renamed from: ap */
    String f4427ap;
    BannerAd bannerAd;

    /* renamed from: cF */
    BannerAdData f4428cF;

    /* renamed from: cG */
    String f4429cG;

    /* renamed from: cH */
    String f4430cH;

    /* renamed from: cI */
    String f4431cI;

    /* renamed from: cJ */
    String f4432cJ;

    /* renamed from: cK */
    C4859e f4433cK;

    /* renamed from: cL */
    Banner f4434cL;

    /* renamed from: cd */
    boolean f4435cd;
    BannerAdData selectedAd;
    C4990q userAction;

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x008a  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x00ac A[Catch:{ all -> 0x01a8 }] */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0100 A[Catch:{ all -> 0x01a8 }] */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x015f  */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x0177  */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x0180  */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x019c  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onCreate(android.os.Bundle r17) {
        /*
            r16 = this;
            r0 = r16
            r1 = 1
            r0.requestWindowFeature(r1)
            android.view.Window r2 = r16.getWindow()
            r3 = 1024(0x400, float:1.435E-42)
            r2.setFlags(r3, r3)
            super.onCreate(r17)
            com.appnext.banners.BannerActivity$a r2 = new com.appnext.banners.BannerActivity$a
            r3 = 0
            r2.<init>()
            r0.f4433cK = r2
            android.widget.LinearLayout r2 = new android.widget.LinearLayout
            r2.<init>(r0)
            android.widget.LinearLayout$LayoutParams r4 = new android.widget.LinearLayout$LayoutParams
            r5 = -1
            r4.<init>(r5, r5)
            r2.setLayoutParams(r4)
            r0.setContentView(r2)
            com.appnext.core.q r4 = new com.appnext.core.q
            com.appnext.banners.BannerActivity$1 r6 = new com.appnext.banners.BannerActivity$1
            r6.<init>()
            r4.<init>(r0, r6)
            r0.userAction = r4
            android.content.Intent r4 = r16.getIntent()     // Catch:{ all -> 0x01a8 }
            android.os.Bundle r4 = r4.getExtras()     // Catch:{ all -> 0x01a8 }
            java.lang.String r6 = "placement"
            java.lang.String r6 = r4.getString(r6)     // Catch:{ all -> 0x01a8 }
            r0.f4427ap = r6     // Catch:{ all -> 0x01a8 }
            java.lang.String r6 = "size"
            java.lang.String r6 = r4.getString(r6)     // Catch:{ all -> 0x01a8 }
            r0.f4431cI = r6     // Catch:{ all -> 0x01a8 }
            java.lang.String r7 = r0.f4427ap     // Catch:{ all -> 0x01a8 }
            int r8 = r6.hashCode()     // Catch:{ all -> 0x01a8 }
            java.lang.String r9 = "BANNER"
            java.lang.String r10 = "MEDIUM_RECTANGLE"
            java.lang.String r11 = "LARGE_BANNER"
            r12 = 1951953708(0x7458732c, float:6.859571E31)
            r13 = -96588539(0xfffffffffa3e2d05, float:-2.4686238E35)
            r14 = -1966536496(0xffffffff8ac908d0, float:-1.9358911E-32)
            r15 = 0
            r3 = 2
            if (r8 == r14) goto L_0x007d
            if (r8 == r13) goto L_0x0075
            if (r8 == r12) goto L_0x006d
            goto L_0x0085
        L_0x006d:
            boolean r8 = r6.equals(r9)     // Catch:{ all -> 0x01a8 }
            if (r8 == 0) goto L_0x0085
            r8 = 0
            goto L_0x0086
        L_0x0075:
            boolean r8 = r6.equals(r10)     // Catch:{ all -> 0x01a8 }
            if (r8 == 0) goto L_0x0085
            r8 = 2
            goto L_0x0086
        L_0x007d:
            boolean r8 = r6.equals(r11)     // Catch:{ all -> 0x01a8 }
            if (r8 == 0) goto L_0x0085
            r8 = 1
            goto L_0x0086
        L_0x0085:
            r8 = -1
        L_0x0086:
            java.lang.String r12 = "Wrong banner size "
            if (r8 == 0) goto L_0x00ac
            if (r8 == r1) goto L_0x00a6
            if (r8 != r3) goto L_0x0094
            com.appnext.banners.MediumRectangleAd r6 = new com.appnext.banners.MediumRectangleAd     // Catch:{ all -> 0x01a8 }
            r6.<init>(r0, r7)     // Catch:{ all -> 0x01a8 }
            goto L_0x00b1
        L_0x0094:
            java.lang.IllegalArgumentException r1 = new java.lang.IllegalArgumentException     // Catch:{ all -> 0x01a8 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x01a8 }
            r2.<init>(r12)     // Catch:{ all -> 0x01a8 }
            r2.append(r6)     // Catch:{ all -> 0x01a8 }
            java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x01a8 }
            r1.<init>(r2)     // Catch:{ all -> 0x01a8 }
            throw r1     // Catch:{ all -> 0x01a8 }
        L_0x00a6:
            com.appnext.banners.LargeBannerAd r6 = new com.appnext.banners.LargeBannerAd     // Catch:{ all -> 0x01a8 }
            r6.<init>(r0, r7)     // Catch:{ all -> 0x01a8 }
            goto L_0x00b1
        L_0x00ac:
            com.appnext.banners.SmallBannerAd r6 = new com.appnext.banners.SmallBannerAd     // Catch:{ all -> 0x01a8 }
            r6.<init>(r0, r7)     // Catch:{ all -> 0x01a8 }
        L_0x00b1:
            com.appnext.banners.BannerAd r6 = (com.appnext.banners.BannerAd) r6     // Catch:{ all -> 0x01a8 }
            r0.bannerAd = r6     // Catch:{ all -> 0x01a8 }
            java.lang.String r7 = "postback"
            java.lang.String r7 = r4.getString(r7)     // Catch:{ all -> 0x01a8 }
            r6.setPostback(r7)     // Catch:{ all -> 0x01a8 }
            com.appnext.banners.BannerAd r6 = r0.bannerAd     // Catch:{ all -> 0x01a8 }
            java.lang.String r7 = "category"
            java.lang.String r7 = r4.getString(r7)     // Catch:{ all -> 0x01a8 }
            r6.setCategories(r7)     // Catch:{ all -> 0x01a8 }
            java.lang.String r6 = "clicked"
            java.lang.String r6 = r4.getString(r6)     // Catch:{ all -> 0x01a8 }
            r0.f4430cH = r6     // Catch:{ all -> 0x01a8 }
            java.lang.String r6 = "shouldClose"
            boolean r6 = r4.getBoolean(r6, r15)     // Catch:{ all -> 0x01a8 }
            r0.f4435cd = r6     // Catch:{ all -> 0x01a8 }
            java.lang.String r6 = "selected"
            java.io.Serializable r4 = r4.getSerializable(r6)     // Catch:{ all -> 0x01a8 }
            com.appnext.banners.BannerAdData r4 = (com.appnext.banners.BannerAdData) r4     // Catch:{ all -> 0x01a8 }
            r0.selectedAd = r4     // Catch:{ all -> 0x01a8 }
            com.appnext.banners.b r4 = com.appnext.banners.C4856b.m6439R()     // Catch:{ all -> 0x01a8 }
            com.appnext.banners.BannerAd r6 = r0.bannerAd     // Catch:{ all -> 0x01a8 }
            java.lang.String r4 = r4.mo41250l((com.appnext.core.C4924Ad) r6)     // Catch:{ all -> 0x01a8 }
            r0.f4429cG = r4     // Catch:{ all -> 0x01a8 }
            com.appnext.banners.b r4 = com.appnext.banners.C4856b.m6439R()     // Catch:{ all -> 0x01a8 }
            com.appnext.banners.BannerAd r6 = r0.bannerAd     // Catch:{ all -> 0x01a8 }
            java.util.ArrayList r4 = r4.mo40906f(r6)     // Catch:{ all -> 0x01a8 }
            org.json.JSONArray r6 = new org.json.JSONArray     // Catch:{ all -> 0x01a8 }
            r6.<init>()     // Catch:{ all -> 0x01a8 }
            if (r4 == 0) goto L_0x0123
            java.util.Iterator r4 = r4.iterator()     // Catch:{ all -> 0x01a8 }
        L_0x0104:
            boolean r7 = r4.hasNext()     // Catch:{ all -> 0x01a8 }
            if (r7 == 0) goto L_0x0123
            java.lang.Object r7 = r4.next()     // Catch:{ all -> 0x01a8 }
            com.appnext.core.AppnextAd r7 = (com.appnext.core.AppnextAd) r7     // Catch:{ all -> 0x01a8 }
            org.json.JSONObject r8 = new org.json.JSONObject     // Catch:{ all -> 0x01a8 }
            com.appnext.banners.BannerAdData r15 = new com.appnext.banners.BannerAdData     // Catch:{ all -> 0x01a8 }
            r15.<init>(r7)     // Catch:{ all -> 0x01a8 }
            java.lang.String r7 = r15.getAdJSON()     // Catch:{ all -> 0x01a8 }
            r8.<init>(r7)     // Catch:{ all -> 0x01a8 }
            r6.put(r8)     // Catch:{ all -> 0x01a8 }
            r15 = 0
            goto L_0x0104
        L_0x0123:
            org.json.JSONObject r4 = new org.json.JSONObject     // Catch:{ all -> 0x01a8 }
            r4.<init>()     // Catch:{ all -> 0x01a8 }
            java.lang.String r7 = "apps"
            org.json.JSONObject r4 = r4.put(r7, r6)     // Catch:{ all -> 0x01a8 }
            java.lang.String r4 = r4.toString()     // Catch:{ all -> 0x01a8 }
            r0.f4432cJ = r4     // Catch:{ all -> 0x01a8 }
            com.appnext.banners.BannerActivity$Banner r4 = new com.appnext.banners.BannerActivity$Banner
            r4.<init>(r0)
            r0.f4434cL = r4
            r2.addView(r4)
            com.appnext.banners.BannerActivity$Banner r2 = r0.f4434cL
            android.view.ViewGroup$LayoutParams r2 = r2.getLayoutParams()
            r2.height = r5
            com.appnext.banners.BannerActivity$Banner r2 = r0.f4434cL
            android.view.ViewGroup$LayoutParams r2 = r2.getLayoutParams()
            r2.width = r5
            com.appnext.banners.BannerActivity$Banner r2 = r0.f4434cL
            java.lang.String r4 = r0.f4427ap
            r2.setPlacementId(r4)
            com.appnext.banners.BannerActivity$Banner r2 = r0.f4434cL
            java.lang.String r4 = r0.f4431cI
            int r6 = r4.hashCode()
            if (r6 == r14) goto L_0x0177
            if (r6 == r13) goto L_0x016f
            r7 = 1951953708(0x7458732c, float:6.859571E31)
            if (r6 == r7) goto L_0x0167
            goto L_0x017e
        L_0x0167:
            boolean r6 = r4.equals(r9)
            if (r6 == 0) goto L_0x017e
            r5 = 0
            goto L_0x017e
        L_0x016f:
            boolean r6 = r4.equals(r10)
            if (r6 == 0) goto L_0x017e
            r5 = 2
            goto L_0x017e
        L_0x0177:
            boolean r6 = r4.equals(r11)
            if (r6 == 0) goto L_0x017e
            r5 = 1
        L_0x017e:
            if (r5 == 0) goto L_0x019c
            if (r5 == r1) goto L_0x0199
            if (r5 != r3) goto L_0x0187
            com.appnext.banners.BannerSize r1 = com.appnext.banners.BannerSize.MEDIUM_RECTANGLE
            goto L_0x019e
        L_0x0187:
            java.lang.IllegalArgumentException r1 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>(r12)
            r2.append(r4)
            java.lang.String r2 = r2.toString()
            r1.<init>(r2)
            throw r1
        L_0x0199:
            com.appnext.banners.BannerSize r1 = com.appnext.banners.BannerSize.LARGE_BANNER
            goto L_0x019e
        L_0x019c:
            com.appnext.banners.BannerSize r1 = com.appnext.banners.BannerSize.BANNER
        L_0x019e:
            r2.setBannerSize(r1)
            com.appnext.banners.BannerActivity$Banner r1 = r0.f4434cL
            r2 = 0
            r1.loadAd(r2)
            return
        L_0x01a8:
            r16.finish()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appnext.banners.BannerActivity.onCreate(android.os.Bundle):void");
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|8) */
    /* JADX WARNING: Code restructure failed: missing block: B:9:?, code lost:
        return;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0008 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x000d */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onDestroy() {
        /*
            r1 = this;
            super.onDestroy()
            com.appnext.core.q r0 = r1.userAction     // Catch:{ all -> 0x0008 }
            r0.destroy()     // Catch:{ all -> 0x0008 }
        L_0x0008:
            com.appnext.banners.BannerAd r0 = r1.bannerAd     // Catch:{ all -> 0x000d }
            r0.destroy()     // Catch:{ all -> 0x000d }
        L_0x000d:
            com.appnext.banners.BannerActivity$Banner r0 = r1.f4434cL     // Catch:{ all -> 0x0012 }
            r0.destroy()     // Catch:{ all -> 0x0012 }
        L_0x0012:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appnext.banners.BannerActivity.onDestroy():void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0037  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0055  */
    /* renamed from: n */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static com.appnext.banners.BannerSize m6428n(java.lang.String r4) {
        /*
            int r0 = r4.hashCode()
            r1 = -1966536496(0xffffffff8ac908d0, float:-1.9358911E-32)
            r2 = 2
            r3 = 1
            if (r0 == r1) goto L_0x002a
            r1 = -96588539(0xfffffffffa3e2d05, float:-2.4686238E35)
            if (r0 == r1) goto L_0x0020
            r1 = 1951953708(0x7458732c, float:6.859571E31)
            if (r0 == r1) goto L_0x0016
            goto L_0x0034
        L_0x0016:
            java.lang.String r0 = "BANNER"
            boolean r0 = r4.equals(r0)
            if (r0 == 0) goto L_0x0034
            r0 = 0
            goto L_0x0035
        L_0x0020:
            java.lang.String r0 = "MEDIUM_RECTANGLE"
            boolean r0 = r4.equals(r0)
            if (r0 == 0) goto L_0x0034
            r0 = 2
            goto L_0x0035
        L_0x002a:
            java.lang.String r0 = "LARGE_BANNER"
            boolean r0 = r4.equals(r0)
            if (r0 == 0) goto L_0x0034
            r0 = 1
            goto L_0x0035
        L_0x0034:
            r0 = -1
        L_0x0035:
            if (r0 == 0) goto L_0x0055
            if (r0 == r3) goto L_0x0052
            if (r0 != r2) goto L_0x003e
            com.appnext.banners.BannerSize r4 = com.appnext.banners.BannerSize.MEDIUM_RECTANGLE
            return r4
        L_0x003e:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "Wrong banner size "
            r1.<init>(r2)
            r1.append(r4)
            java.lang.String r4 = r1.toString()
            r0.<init>(r4)
            throw r0
        L_0x0052:
            com.appnext.banners.BannerSize r4 = com.appnext.banners.BannerSize.LARGE_BANNER
            return r4
        L_0x0055:
            com.appnext.banners.BannerSize r4 = com.appnext.banners.BannerSize.BANNER
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appnext.banners.BannerActivity.m6428n(java.lang.String):com.appnext.banners.BannerSize");
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0037  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x005b  */
    /* renamed from: c */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.appnext.core.C4924Ad mo40783c(java.lang.String r5, java.lang.String r6) {
        /*
            r4 = this;
            int r0 = r6.hashCode()
            r1 = -1966536496(0xffffffff8ac908d0, float:-1.9358911E-32)
            r2 = 2
            r3 = 1
            if (r0 == r1) goto L_0x002a
            r1 = -96588539(0xfffffffffa3e2d05, float:-2.4686238E35)
            if (r0 == r1) goto L_0x0020
            r1 = 1951953708(0x7458732c, float:6.859571E31)
            if (r0 == r1) goto L_0x0016
            goto L_0x0034
        L_0x0016:
            java.lang.String r0 = "BANNER"
            boolean r0 = r6.equals(r0)
            if (r0 == 0) goto L_0x0034
            r0 = 0
            goto L_0x0035
        L_0x0020:
            java.lang.String r0 = "MEDIUM_RECTANGLE"
            boolean r0 = r6.equals(r0)
            if (r0 == 0) goto L_0x0034
            r0 = 2
            goto L_0x0035
        L_0x002a:
            java.lang.String r0 = "LARGE_BANNER"
            boolean r0 = r6.equals(r0)
            if (r0 == 0) goto L_0x0034
            r0 = 1
            goto L_0x0035
        L_0x0034:
            r0 = -1
        L_0x0035:
            if (r0 == 0) goto L_0x005b
            if (r0 == r3) goto L_0x0055
            if (r0 != r2) goto L_0x0041
            com.appnext.banners.MediumRectangleAd r6 = new com.appnext.banners.MediumRectangleAd
            r6.<init>(r4, r5)
            return r6
        L_0x0041:
            java.lang.IllegalArgumentException r5 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "Wrong banner size "
            r0.<init>(r1)
            r0.append(r6)
            java.lang.String r6 = r0.toString()
            r5.<init>(r6)
            throw r5
        L_0x0055:
            com.appnext.banners.LargeBannerAd r6 = new com.appnext.banners.LargeBannerAd
            r6.<init>(r4, r5)
            return r6
        L_0x005b:
            com.appnext.banners.SmallBannerAd r6 = new com.appnext.banners.SmallBannerAd
            r6.<init>(r4, r5)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appnext.banners.BannerActivity.mo40783c(java.lang.String, java.lang.String):com.appnext.core.Ad");
    }

    /* renamed from: com.appnext.banners.BannerActivity$a */
    private class C4814a extends C4861g {
        /* access modifiers changed from: protected */
        public final String getJSurl() {
            return "https://cdn.appnext.com/tools/sdk/banner/2.4.3/result.min.js";
        }

        private C4814a() {
        }

        /* access modifiers changed from: protected */
        public final String getFallbackScript() {
            return new C5011b().mo40781J();
        }

        /* access modifiers changed from: protected */
        public final int getLayout() {
            return C4822R.layout.apnxt_full_screen;
        }

        /* access modifiers changed from: protected */
        public final BannerAd getBannerAd() {
            return BannerActivity.this.bannerAd;
        }

        /* access modifiers changed from: protected */
        public final BannerAdData getSelectedAd() {
            return BannerActivity.this.selectedAd;
        }

        public final void loadAd(BannerAdRequest bannerAdRequest) {
            inflateView(0, (AppnextAd) null);
        }

        /* access modifiers changed from: protected */
        public final BannerAdRequest getAdRequest() {
            return new BannerAdRequest();
        }

        /* access modifiers changed from: protected */
        public final C4861g.C4871a getWebInterface() {
            return new C4818b();
        }

        /* access modifiers changed from: protected */
        public final WebViewClient getWebViewClient() {
            return new C4815a();
        }

        /* renamed from: com.appnext.banners.BannerActivity$a$b */
        public class C4818b extends C4861g.C4871a {
            public C4818b() {
                super();
            }

            @JavascriptInterface
            public final void adClicked(String str, int i) {
                BannerActivity bannerActivity = BannerActivity.this;
                C4856b.m6439R();
                bannerActivity.f4428cF = new BannerAdData((AppnextAd) C4856b.parseAd(str));
                C4990q qVar = BannerActivity.this.userAction;
                BannerAdData bannerAdData = BannerActivity.this.f4428cF;
                qVar.mo41312a(bannerAdData, BannerActivity.this.f4428cF.getAppURL() + "&tem_id=" + C4814a.this.getBannerAd().getTemId(C4814a.this.getSelectedAd()) + "1", C4814a.this.getPlacementId(), new C4956e.C4965a() {
                    public final void error(String str) {
                    }

                    public final void onMarket(String str) {
                        if (BannerActivity.this.f4435cd) {
                            BannerActivity.this.finish();
                        }
                    }
                });
            }

            @JavascriptInterface
            public final void impression(String str) {
                C4856b.m6439R();
                BannerActivity.this.userAction.mo41317e((AppnextAd) C4856b.parseAd(str));
            }

            @JavascriptInterface
            public final void postView(String str) {
                C4856b.m6439R();
                BannerAdData bannerAdData = new BannerAdData((AppnextAd) C4856b.parseAd(str));
                C4990q qVar = BannerActivity.this.userAction;
                qVar.mo41311a(bannerAdData, bannerAdData.getImpressionURL() + "&tem_id=" + C4814a.this.getBannerAd().getTemId(C4814a.this.getSelectedAd()) + "1", (C4956e.C4965a) null);
            }

            @JavascriptInterface
            public final void openLink(String str) {
                Intent intent = new Intent("android.intent.action.VIEW");
                intent.setData(Uri.parse(str));
                intent.addFlags(268435456);
                BannerActivity.this.startActivity(intent);
            }
        }

        /* renamed from: com.appnext.banners.BannerActivity$a$a */
        public class C4815a extends WebViewClient {
            public C4815a() {
            }

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
                new Thread(new Runnable() {
                    public final void run() {
                        final String b = C4967f.m6827b(C4814a.this.context, false);
                        new Handler(Looper.getMainLooper()).post(new Runnable() {
                            public final void run() {
                                try {
                                    C4814a aVar = C4814a.this;
                                    aVar.loadJS("javascript:(function() { try { Appnext.setParams(" + C4814a.this.getConfigParams().put("did", b).toString() + "); } catch(err){ Appnext.jsError(err.message); }})()");
                                } catch (Throwable unused) {
                                }
                                C4814a aVar2 = C4814a.this;
                                aVar2.loadJS("javascript:(function() { try { Appnext.load(" + C4814a.this.getSelectedAd().getAdJSON() + "," + BannerActivity.this.f4432cJ + "," + BannerActivity.this.f4429cG + "); } catch(err){ Appnext.jsError(err.message); }})()");
                            }
                        });
                    }
                }).start();
            }
        }
    }

    private class Banner extends BannerView {
        public Banner(Context context) {
            super(context);
        }

        /* access modifiers changed from: protected */
        public C4859e getBannerAdapter() {
            return BannerActivity.this.f4433cK;
        }
    }
}

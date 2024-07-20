package com.startapp.android.publish.ads.nativead;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import com.startapp.android.publish.ads.nativead.StartAppNativeAd;
import com.startapp.android.publish.adsCommon.C1098b;
import com.startapp.android.publish.adsCommon.C1103c;
import com.startapp.android.publish.adsCommon.p031d.C1117b;
import com.startapp.android.publish.common.model.AdDetails;
import com.startapp.android.publish.common.model.AdPreferences;
import com.startapp.common.C1249a;
import com.startapp.common.p043a.C1270g;

/* compiled from: StartAppSDK */
public class NativeAdDetails implements NativeAdInterface {

    /* renamed from: a */
    int f603a;

    /* renamed from: b */
    C0932a f604b;

    /* renamed from: c */
    private AdDetails f605c;

    /* renamed from: d */
    private Bitmap f606d;

    /* renamed from: e */
    private Bitmap f607e;

    /* renamed from: f */
    private boolean f608f = false;

    /* renamed from: g */
    private String f609g;

    /* renamed from: com.startapp.android.publish.ads.nativead.NativeAdDetails$a */
    /* compiled from: StartAppSDK */
    protected interface C0932a {
        void onNativeAdDetailsLoaded(int i);
    }

    public NativeAdDetails(AdDetails adDetails, NativeAdPreferences nativeAdPreferences, int i, C0932a aVar) {
        C1270g.m2078a("StartAppNativeAd", 3, "Initializiang SingleAd [" + i + "]");
        this.f605c = adDetails;
        this.f603a = i;
        this.f604b = aVar;
        if (nativeAdPreferences.isAutoBitmapDownload()) {
            new C1249a(getImageUrl(), new C1249a.C1252a() {
                /* renamed from: a */
                public void mo13933a(Bitmap bitmap, int i) {
                    NativeAdDetails.this.mo14100a(bitmap);
                    new C1249a(NativeAdDetails.this.getSecondaryImageUrl(), new C1249a.C1252a() {
                        /* renamed from: a */
                        public void mo13933a(Bitmap bitmap, int i) {
                            NativeAdDetails.this.mo14103b(bitmap);
                            NativeAdDetails.this.mo14099a();
                        }
                    }, i).mo15438a();
                }
            }, i).mo15438a();
        } else {
            mo14099a();
        }
    }

    public String toString() {
        return "         Title: [" + getTitle() + "]\n" + "         Description: [" + getDescription().substring(0, 30) + "]...\n" + "         Rating: [" + getRating() + "]\n" + "         Installs: [" + getInstalls() + "]\n" + "         Category: [" + getCategory() + "]\n" + "         PackageName: [" + getPackacgeName() + "]\n" + "         CampaginAction: [" + getCampaignAction() + "]\n";
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo14100a(Bitmap bitmap) {
        this.f606d = bitmap;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public void mo14103b(Bitmap bitmap) {
        this.f607e = bitmap;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo14099a() {
        new Handler().post(new Runnable() {
            public void run() {
                C1270g.m2078a("StartAppNativeAd", 3, "SingleAd [" + NativeAdDetails.this.f603a + "] Loaded");
                if (NativeAdDetails.this.f604b != null) {
                    NativeAdDetails.this.f604b.onNativeAdDetailsLoaded(NativeAdDetails.this.f603a);
                }
            }
        });
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo14101a(String str) {
        this.f609g = str;
    }

    public String getTitle() {
        AdDetails adDetails = this.f605c;
        return adDetails != null ? adDetails.getTitle() : "";
    }

    public String getDescription() {
        AdDetails adDetails = this.f605c;
        return adDetails != null ? adDetails.getDescription() : "";
    }

    public float getRating() {
        AdDetails adDetails = this.f605c;
        if (adDetails != null) {
            return adDetails.getRating();
        }
        return 5.0f;
    }

    public String getImageUrl() {
        AdDetails adDetails = this.f605c;
        return adDetails != null ? adDetails.getImageUrl() : "";
    }

    public String getSecondaryImageUrl() {
        AdDetails adDetails = this.f605c;
        return adDetails != null ? adDetails.getSecondaryImageUrl() : "";
    }

    public Bitmap getImageBitmap() {
        return this.f606d;
    }

    public Bitmap getSecondaryImageBitmap() {
        return this.f607e;
    }

    public String getInstalls() {
        AdDetails adDetails = this.f605c;
        return adDetails != null ? adDetails.getInstalls() : "";
    }

    public String getCategory() {
        AdDetails adDetails = this.f605c;
        return adDetails != null ? adDetails.getCategory() : "";
    }

    public String getPackacgeName() {
        AdDetails adDetails = this.f605c;
        return adDetails != null ? adDetails.getPackageName() : "";
    }

    public StartAppNativeAd.C0934b getCampaignAction() {
        StartAppNativeAd.C0934b bVar = StartAppNativeAd.C0934b.OPEN_MARKET;
        AdDetails adDetails = this.f605c;
        return (adDetails == null || !adDetails.isCPE()) ? bVar : StartAppNativeAd.C0934b.LAUNCH_APP;
    }

    public Boolean isApp() {
        AdDetails adDetails = this.f605c;
        if (adDetails != null) {
            return Boolean.valueOf(adDetails.isApp());
        }
        return true;
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public AdDetails mo14102b() {
        return this.f605c;
    }

    /* renamed from: com.startapp.android.publish.ads.nativead.NativeAdDetails$3 */
    /* compiled from: StartAppSDK */
    static /* synthetic */ class C09313 {

        /* renamed from: a */
        static final /* synthetic */ int[] f613a;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        static {
            /*
                com.startapp.android.publish.ads.nativead.StartAppNativeAd$b[] r0 = com.startapp.android.publish.ads.nativead.StartAppNativeAd.C0934b.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                f613a = r0
                com.startapp.android.publish.ads.nativead.StartAppNativeAd$b r1 = com.startapp.android.publish.ads.nativead.StartAppNativeAd.C0934b.OPEN_MARKET     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = f613a     // Catch:{ NoSuchFieldError -> 0x001d }
                com.startapp.android.publish.ads.nativead.StartAppNativeAd$b r1 = com.startapp.android.publish.ads.nativead.StartAppNativeAd.C0934b.LAUNCH_APP     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.startapp.android.publish.ads.nativead.NativeAdDetails.C09313.<clinit>():void");
        }
    }

    public void sendClick(Context context) {
        if (this.f605c != null) {
            int i = C09313.f613a[getCampaignAction().ordinal()];
            if (i == 1) {
                boolean a = C1103c.m1389a(context, AdPreferences.Placement.INAPP_NATIVE);
                if (!this.f605c.isSmartRedirect() || a) {
                    C1103c.m1376a(context, this.f605c.getClickUrl(), this.f605c.getTrackingClickUrl(), new C1117b(this.f609g), this.f605c.isStartappBrowserEnabled() && !a, false);
                } else {
                    C1103c.m1378a(context, this.f605c.getClickUrl(), this.f605c.getTrackingClickUrl(), this.f605c.getPackageName(), new C1117b(this.f609g), C1098b.m1303a().mo14747A(), C1098b.m1303a().mo14748B(), this.f605c.isStartappBrowserEnabled(), this.f605c.shouldSendRedirectHops(), false);
                }
            } else if (i == 2) {
                C1103c.m1385a(getPackacgeName(), this.f605c.getIntentDetails(), this.f605c.getClickUrl(), context, new C1117b(this.f609g));
            }
        }
    }

    public void sendImpression(Context context) {
        if (!this.f608f) {
            this.f608f = true;
            if (this.f605c != null) {
                C1270g.m2078a("StartAppNativeAd", 3, "Sending Impression for [" + this.f603a + "]");
                C1103c.m1372a(context, this.f605c.getTrackingUrl(), new C1117b(this.f609g));
                return;
            }
            return;
        }
        C1270g.m2078a("StartAppNativeAd", 3, "Already sent impression for [" + this.f603a + "]");
    }
}

package com.tappx.sdk.android;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import com.integralads.avid.library.mopub.BuildConfig;
import com.mopub.mobileads.CustomEventInterstitial;
import com.mopub.mobileads.MoPubErrorCode;
import java.util.Map;

public class MopubInterstitialAdapter extends CustomEventInterstitial {

    /* renamed from: a */
    private TappxInterstitial f2613a;

    /* renamed from: com.tappx.sdk.android.MopubInterstitialAdapter$1 */
    static /* synthetic */ class C17231 {

        /* renamed from: a */
        static final /* synthetic */ int[] f2614a;

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|(3:7|8|10)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        static {
            /*
                com.tappx.sdk.android.TappxAdError[] r0 = com.tappx.sdk.android.TappxAdError.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                f2614a = r0
                com.tappx.sdk.android.TappxAdError r1 = com.tappx.sdk.android.TappxAdError.DEVELOPER_ERROR     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = f2614a     // Catch:{ NoSuchFieldError -> 0x001d }
                com.tappx.sdk.android.TappxAdError r1 = com.tappx.sdk.android.TappxAdError.INTERNAL_ERROR     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = f2614a     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.tappx.sdk.android.TappxAdError r1 = com.tappx.sdk.android.TappxAdError.SERVER_ERROR     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = f2614a     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.tappx.sdk.android.TappxAdError r1 = com.tappx.sdk.android.TappxAdError.NO_FILL     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.tappx.sdk.android.MopubInterstitialAdapter.C17231.<clinit>():void");
        }
    }

    /* renamed from: com.tappx.sdk.android.MopubInterstitialAdapter$a */
    private static final class C1724a implements TappxInterstitialListener {

        /* renamed from: a */
        private final CustomEventInterstitial.CustomEventInterstitialListener f2615a;

        private C1724a(CustomEventInterstitial.CustomEventInterstitialListener customEventInterstitialListener) {
            this.f2615a = customEventInterstitialListener;
        }

        /* synthetic */ C1724a(CustomEventInterstitial.CustomEventInterstitialListener customEventInterstitialListener, C17231 r2) {
            this(customEventInterstitialListener);
        }

        public void onInterstitialClicked(TappxInterstitial tappxInterstitial) {
            this.f2615a.onInterstitialClicked();
        }

        public void onInterstitialDismissed(TappxInterstitial tappxInterstitial) {
            this.f2615a.onInterstitialDismissed();
        }

        public void onInterstitialLoadFailed(TappxInterstitial tappxInterstitial, TappxAdError tappxAdError) {
            Log.v("Tappx", "MoPub adapter: Interstitial load failed " + tappxAdError);
            this.f2615a.onInterstitialFailed(MopubInterstitialAdapter.m3794b(tappxAdError));
        }

        public void onInterstitialLoaded(TappxInterstitial tappxInterstitial) {
            Log.v("Tappx", "MoPub adapter: Interstitial loaded");
            this.f2615a.onInterstitialLoaded();
        }

        public void onInterstitialShown(TappxInterstitial tappxInterstitial) {
            this.f2615a.onInterstitialShown();
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: b */
    public static MoPubErrorCode m3794b(TappxAdError tappxAdError) {
        int i = C17231.f2614a[tappxAdError.ordinal()];
        return i != 1 ? i != 2 ? i != 3 ? MoPubErrorCode.NO_FILL : MoPubErrorCode.SERVER_ERROR : MoPubErrorCode.INTERNAL_ERROR : MoPubErrorCode.ADAPTER_CONFIGURATION_ERROR;
    }

    /* access modifiers changed from: protected */
    public void loadInterstitial(Context context, CustomEventInterstitial.CustomEventInterstitialListener customEventInterstitialListener, Map<String, Object> map, Map<String, String> map2) {
        String str = map2.get("appKey");
        boolean z = context instanceof Activity;
        if (str == null || str.isEmpty()) {
            Log.e("Tappx", "MoPub adapter: invalid app key as server parameter");
            customEventInterstitialListener.onInterstitialFailed(MoPubErrorCode.ADAPTER_CONFIGURATION_ERROR);
        } else if (!z) {
            customEventInterstitialListener.onInterstitialFailed(MoPubErrorCode.NO_FILL);
        } else {
            TappxInterstitial tappxInterstitial = new TappxInterstitial(context, str);
            this.f2613a = tappxInterstitial;
            tappxInterstitial.setListener(new C1724a(customEventInterstitialListener, (C17231) null));
            this.f2613a.loadAd(new AdRequest().mediator(BuildConfig.SDK_NAME));
            Log.v("Tappx", "Loading " + getClass().getSimpleName());
        }
    }

    /* access modifiers changed from: protected */
    public void onInvalidate() {
        TappxInterstitial tappxInterstitial = this.f2613a;
        if (tappxInterstitial != null) {
            tappxInterstitial.destroy();
            this.f2613a.setListener((TappxInterstitialListener) null);
            this.f2613a = null;
        }
    }

    /* access modifiers changed from: protected */
    public void showInterstitial() {
        TappxInterstitial tappxInterstitial = this.f2613a;
        if (tappxInterstitial != null) {
            tappxInterstitial.show();
        }
    }
}

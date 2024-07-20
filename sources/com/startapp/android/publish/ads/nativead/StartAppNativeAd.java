package com.startapp.android.publish.ads.nativead;

import android.content.Context;
import com.startapp.android.publish.ads.nativead.NativeAdDetails;
import com.startapp.android.publish.adsCommon.C1040Ad;
import com.startapp.android.publish.adsCommon.C1103c;
import com.startapp.android.publish.adsCommon.adListeners.AdEventListener;
import com.startapp.android.publish.adsCommon.adListeners.C1080b;
import com.startapp.android.publish.adsCommon.adinformation.C1083a;
import com.startapp.android.publish.adsCommon.p028a.C1068a;
import com.startapp.android.publish.adsCommon.p028a.C1069b;
import com.startapp.android.publish.adsCommon.p028a.C1073f;
import com.startapp.android.publish.adsCommon.p028a.C1074g;
import com.startapp.android.publish.common.model.AdDetails;
import com.startapp.android.publish.common.model.AdPreferences;
import com.startapp.common.Constants;
import com.startapp.common.p043a.C1270g;
import com.startapp.common.p043a.C1274i;
import java.util.ArrayList;
import java.util.List;

/* compiled from: StartAppSDK */
public class StartAppNativeAd extends C1040Ad implements NativeAdDetails.C0932a {
    private static final String TAG = "StartAppNativeAd";
    private static final long serialVersionUID = 1;
    private C0933a adEventDelegate;
    boolean isLoading = false;
    private List<NativeAdDetails> listNativeAds = new ArrayList();
    private C0936b nativeAd;
    private NativeAdPreferences preferences;
    private int totalObjectsLoaded = 0;

    /* renamed from: com.startapp.android.publish.ads.nativead.StartAppNativeAd$b */
    /* compiled from: StartAppSDK */
    public enum C0934b {
        LAUNCH_APP,
        OPEN_MARKET
    }

    /* access modifiers changed from: protected */
    public void loadAds(AdPreferences adPreferences, AdEventListener adEventListener) {
    }

    public StartAppNativeAd(Context context) {
        super(context, AdPreferences.Placement.INAPP_NATIVE);
    }

    private NativeAdPreferences getPreferences() {
        return this.preferences;
    }

    private void setPreferences(NativeAdPreferences nativeAdPreferences) {
        this.preferences = nativeAdPreferences;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("\n===== StartAppNativeAd =====\n");
        for (int i = 0; i < getNumberOfAds(); i++) {
            stringBuffer.append(this.listNativeAds.get(i));
        }
        stringBuffer.append("===== End StartAppNativeAd =====");
        return stringBuffer.toString();
    }

    /* access modifiers changed from: package-private */
    public void initNativeAdList() {
        this.totalObjectsLoaded = 0;
        if (this.listNativeAds == null) {
            this.listNativeAds = new ArrayList();
        }
        this.listNativeAds.clear();
        C0936b bVar = this.nativeAd;
        if (bVar != null && bVar.mo14937d() != null) {
            for (int i = 0; i < this.nativeAd.mo14937d().size(); i++) {
                this.listNativeAds.add(new NativeAdDetails(this.nativeAd.mo14937d().get(i), getPreferences(), i, this));
            }
        }
    }

    public void onNativeAdDetailsLoaded(int i) {
        this.totalObjectsLoaded++;
        if (this.nativeAd.mo14937d() != null && this.totalObjectsLoaded == this.nativeAd.mo14937d().size()) {
            onNativeAdLoaded();
        }
    }

    private void onNativeAdLoaded() {
        C1270g.m2078a(TAG, 3, "Ad Loaded successfully");
        this.isLoading = false;
        setErrorMessage((String) null);
        if (this.adEventDelegate != null) {
            C1270g.m2078a(TAG, 3, "Calling original RecienedAd callback");
            AdEventListener a = this.adEventDelegate.mo14148a();
            if (a != null) {
                a.onReceiveAd(this);
            }
        }
    }

    public int getNumberOfAds() {
        List<NativeAdDetails> list = this.listNativeAds;
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    public boolean isBelowMinCPM() {
        return this.nativeAd.isBelowMinCPM();
    }

    public boolean loadAd() {
        return loadAd(new NativeAdPreferences(), (AdEventListener) null);
    }

    public boolean loadAd(AdEventListener adEventListener) {
        return loadAd(new NativeAdPreferences(), adEventListener);
    }

    public boolean loadAd(NativeAdPreferences nativeAdPreferences) {
        return loadAd(nativeAdPreferences, (AdEventListener) null);
    }

    public boolean loadAd(NativeAdPreferences nativeAdPreferences, AdEventListener adEventListener) {
        C1270g.m2078a(TAG, 3, "Start loading StartAppNativeAd");
        this.adEventDelegate = new C0933a(adEventListener);
        setPreferences(nativeAdPreferences);
        if (this.isLoading) {
            setErrorMessage("Ad is currently being loaded");
            return false;
        }
        this.isLoading = true;
        C0936b bVar = new C0936b(this.context, getPreferences());
        this.nativeAd = bVar;
        return bVar.load(nativeAdPreferences, this.adEventDelegate);
    }

    public ArrayList<NativeAdDetails> getNativeAds() {
        return getNativeAds((String) null);
    }

    public ArrayList<NativeAdDetails> getNativeAds(String str) {
        ArrayList<NativeAdDetails> arrayList = new ArrayList<>();
        C1073f a = C1074g.m1233a().mo14667b().mo14661a(AdPreferences.Placement.INAPP_NATIVE, str);
        if (a.mo14664a()) {
            List<NativeAdDetails> list = this.listNativeAds;
            if (list != null) {
                for (NativeAdDetails next : list) {
                    next.mo14101a(str);
                    arrayList.add(next);
                }
                C1069b.m1217a().mo14655a(new C1068a(AdPreferences.Placement.INAPP_NATIVE, str));
            }
        } else {
            C1103c.m1383a(this.context, C1103c.m1391a(getAdDetailsList()), str, a.mo14666c());
            if (Constants.m1978a().booleanValue()) {
                C1274i.m2100a().mo15477a(this.context, a.mo14665b());
            }
        }
        return arrayList;
    }

    private List<AdDetails> getAdDetailsList() {
        ArrayList arrayList = new ArrayList();
        List<NativeAdDetails> list = this.listNativeAds;
        if (list != null) {
            for (NativeAdDetails b : list) {
                arrayList.add(b.mo14102b());
            }
        }
        return arrayList;
    }

    public static String getPrivacyURL() {
        if (C1083a.m1256b().mo14710c() == null) {
            return "";
        }
        String c = C1083a.m1256b().mo14710c();
        if (c.contains(acr.browser.lightning.constant.Constants.HTTP) || c.contains(acr.browser.lightning.constant.Constants.HTTPS)) {
            return C1083a.m1256b().mo14710c();
        }
        return acr.browser.lightning.constant.Constants.HTTPS + C1083a.m1256b().mo14710c();
    }

    public static String getPrivacyImageUrl() {
        return C1083a.m1256b().mo14711d();
    }

    /* renamed from: com.startapp.android.publish.ads.nativead.StartAppNativeAd$a */
    /* compiled from: StartAppSDK */
    private class C0933a implements AdEventListener {

        /* renamed from: b */
        private AdEventListener f615b = null;

        public C0933a(AdEventListener adEventListener) {
            this.f615b = new C1080b(adEventListener);
        }

        public void onReceiveAd(C1040Ad ad) {
            C1270g.m2078a(StartAppNativeAd.TAG, 3, "NativeAd Received");
            StartAppNativeAd.this.initNativeAdList();
        }

        public void onFailedToReceiveAd(C1040Ad ad) {
            C1270g.m2078a(StartAppNativeAd.TAG, 3, "NativeAd Failed to load");
            StartAppNativeAd.this.setErrorMessage(ad.getErrorMessage());
            AdEventListener adEventListener = this.f615b;
            if (adEventListener != null) {
                adEventListener.onFailedToReceiveAd(StartAppNativeAd.this);
                this.f615b = null;
            }
            StartAppNativeAd.this.isLoading = false;
            StartAppNativeAd.this.initNativeAdList();
        }

        /* renamed from: a */
        public AdEventListener mo14148a() {
            return this.f615b;
        }
    }
}

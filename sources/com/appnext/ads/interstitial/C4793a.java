package com.appnext.ads.interstitial;

import android.content.Context;
import android.util.Pair;
import com.appnext.core.AppnextAd;
import com.appnext.core.C4924Ad;
import com.appnext.core.C4940a;
import com.appnext.core.C4946b;
import com.appnext.core.C4948d;
import com.appnext.core.C4967f;
import com.appnext.core.C4972g;
import com.appnext.core.C4986p;
import com.appnext.core.p086a.C4944b;
import com.appnext.core.webview.AppnextWebView;
import java.util.ArrayList;
import java.util.Iterator;
import org.altbeacon.beacon.BeaconManager;
import org.json.JSONArray;
import org.json.JSONException;

/* renamed from: com.appnext.ads.interstitial.a */
public final class C4793a extends C4948d {

    /* renamed from: aM */
    private static final int f4385aM = 30;

    /* renamed from: cl */
    private static C4793a f4386cl;

    /* renamed from: bT */
    private String f4387bT;

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final <T> void mo40617a(String str, C4924Ad ad, T t) {
    }

    /* renamed from: G */
    public static synchronized C4793a m6403G() {
        C4793a aVar;
        synchronized (C4793a.class) {
            if (f4386cl == null) {
                f4386cl = new C4793a();
            }
            aVar = f4386cl;
        }
        return aVar;
    }

    private C4793a() {
    }

    /* renamed from: a */
    public final void mo40777a(Context context, C4924Ad ad, String str, C4948d.C4955a aVar, String str2) {
        this.f4387bT = str2;
        super.mo41238a(context, ad, str, aVar);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final String mo40613a(Context context, C4924Ad ad, String str, ArrayList<Pair<String, String>> arrayList) {
        Object obj;
        StringBuilder sb = new StringBuilder("&auid=");
        sb.append(ad != null ? ad.getAUID() : "600");
        sb.append("&vidmin=");
        String str2 = "";
        sb.append(ad == null ? str2 : Integer.valueOf(ad.getMinVideoLength()));
        sb.append("&vidmax=");
        if (ad == null) {
            obj = str2;
        } else {
            obj = Integer.valueOf(ad.getMaxVideoLength());
        }
        sb.append(obj);
        if (this.f4387bT.equals("static")) {
            str2 = "&creative=0";
        }
        sb.append(str2);
        return sb.toString();
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final void mo40614a(Context context, C4924Ad ad, C4940a aVar) throws Exception {
        AppnextWebView.m6957u(context).mo41343a(((Interstitial) ad).getPageUrl(), (AppnextWebView.C5017c) null);
        if (aVar != null && aVar.getAds() != null && aVar.getAds().size() > 0) {
            C4967f.m6809Y(((AppnextAd) aVar.getAds().get(0)).getImageURL());
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final int mo40610a(Context context, C4972g gVar) {
        int i;
        AppnextAd appnextAd = (AppnextAd) gVar;
        InterstitialAd interstitialAd = new InterstitialAd(appnextAd);
        if (!interstitialAd.getCampaignGoal().equals(C4944b.f4721hX) || !C4967f.m6842c(context, interstitialAd.getAdPackage())) {
            i = (!interstitialAd.getCampaignGoal().equals(C4944b.f4722hY) || C4967f.m6842c(context, interstitialAd.getAdPackage())) ? 0 : 2;
        } else {
            i = 1;
        }
        int b = m6407b(context, appnextAd);
        if (i == 0 && b == 0) {
            return 0;
        }
        return i != 0 ? i : b;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final void mo40615a(C4924Ad ad, String str, String str2) {
        new StringBuilder("error ").append(str);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final boolean mo40618a(Context context, C4924Ad ad, ArrayList<?> arrayList) {
        return mo40775a(context, (ArrayList<AppnextAd>) arrayList, ((Interstitial) ad).getCreativeType(), ad) != null;
    }

    /* access modifiers changed from: protected */
    /* renamed from: c */
    public final C4986p mo40622c(C4924Ad ad) {
        return C4795c.m6424K();
    }

    /* access modifiers changed from: protected */
    /* renamed from: d */
    public final boolean mo40779d(C4924Ad ad) {
        return mo41246h(ad) && mo41249k(ad).getAds() != null && mo41249k(ad).getAds().size() > 0 && mo41249k(ad).mo41212aU().longValue() + BeaconManager.DEFAULT_BACKGROUND_BETWEEN_SCAN_PERIOD > System.currentTimeMillis();
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public final ArrayList<AppnextAd> mo40778b(Context context, C4924Ad ad, String str) {
        ArrayList<?> ads;
        AppnextAd a;
        if (mo41249k(ad) == null || (ads = mo41249k(ad).getAds()) == null || (a = mo40775a(context, (ArrayList<AppnextAd>) ads, str, ad)) == null) {
            return null;
        }
        ads.remove(a);
        ads.add(0, a);
        return ads;
    }

    /* renamed from: a */
    private static ArrayList<AppnextAd> m6405a(ArrayList<AppnextAd> arrayList, AppnextAd appnextAd) {
        arrayList.remove(appnextAd);
        arrayList.add(0, appnextAd);
        return arrayList;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final String mo40776a(ArrayList<AppnextAd> arrayList) {
        return super.mo40776a(arrayList);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final void mo40616a(String str, C4924Ad ad) {
        super.mo40616a(str, ad);
    }

    private static boolean hasVideo(AppnextAd appnextAd) {
        return !appnextAd.getVideoUrl().equals("") || !appnextAd.getVideoUrlHigh().equals("") || !appnextAd.getVideoUrl30Sec().equals("") || !appnextAd.getVideoUrlHigh30Sec().equals("");
    }

    /* renamed from: c */
    private static boolean m6408c(AppnextAd appnextAd) {
        return !appnextAd.getWideImageURL().equals("");
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final AppnextAd mo40775a(Context context, ArrayList<AppnextAd> arrayList, String str, C4924Ad ad) {
        Iterator<AppnextAd> it = arrayList.iterator();
        while (it.hasNext()) {
            AppnextAd next = it.next();
            if (m6406a(next, str, ad)) {
                return next;
            }
        }
        return null;
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0038  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0067  */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean m6406a(com.appnext.core.AppnextAd r6, java.lang.String r7, com.appnext.core.C4924Ad r8) {
        /*
            r5 = this;
            int r0 = r7.hashCode()
            r1 = -892481938(0xffffffffcacdce6e, float:-6743863.0)
            r2 = 0
            r3 = 2
            r4 = 1
            if (r0 == r1) goto L_0x002b
            r1 = 112202875(0x6b0147b, float:6.6233935E-35)
            if (r0 == r1) goto L_0x0021
            r1 = 835260319(0x31c90f9f, float:5.851646E-9)
            if (r0 == r1) goto L_0x0017
            goto L_0x0035
        L_0x0017:
            java.lang.String r0 = "managed"
            boolean r7 = r7.equals(r0)
            if (r7 == 0) goto L_0x0035
            r7 = 0
            goto L_0x0036
        L_0x0021:
            java.lang.String r0 = "video"
            boolean r7 = r7.equals(r0)
            if (r7 == 0) goto L_0x0035
            r7 = 1
            goto L_0x0036
        L_0x002b:
            java.lang.String r0 = "static"
            boolean r7 = r7.equals(r0)
            if (r7 == 0) goto L_0x0035
            r7 = 2
            goto L_0x0036
        L_0x0035:
            r7 = -1
        L_0x0036:
            if (r7 == 0) goto L_0x0067
            if (r7 == r4) goto L_0x0052
            if (r7 == r3) goto L_0x003d
            goto L_0x0082
        L_0x003d:
            boolean r7 = m6408c((com.appnext.core.AppnextAd) r6)
            if (r7 == 0) goto L_0x0082
            java.lang.String r6 = r6.getBannerID()
            java.lang.String r7 = r8.getPlacementID()
            boolean r6 = m6747a((java.lang.String) r6, (java.lang.String) r7)
            if (r6 != 0) goto L_0x0082
            return r4
        L_0x0052:
            boolean r7 = hasVideo(r6)
            if (r7 == 0) goto L_0x0082
            java.lang.String r6 = r6.getBannerID()
            java.lang.String r7 = r8.getPlacementID()
            boolean r6 = m6747a((java.lang.String) r6, (java.lang.String) r7)
            if (r6 != 0) goto L_0x0082
            return r4
        L_0x0067:
            boolean r7 = m6408c((com.appnext.core.AppnextAd) r6)
            if (r7 != 0) goto L_0x0073
            boolean r7 = hasVideo(r6)
            if (r7 == 0) goto L_0x0082
        L_0x0073:
            java.lang.String r6 = r6.getBannerID()
            java.lang.String r7 = r8.getPlacementID()
            boolean r6 = m6747a((java.lang.String) r6, (java.lang.String) r7)
            if (r6 != 0) goto L_0x0082
            return r4
        L_0x0082:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appnext.ads.interstitial.C4793a.m6406a(com.appnext.core.AppnextAd, java.lang.String, com.appnext.core.Ad):boolean");
    }

    /* renamed from: a */
    private static int m6404a(Context context, AppnextAd appnextAd) {
        InterstitialAd interstitialAd = new InterstitialAd(appnextAd);
        if (!interstitialAd.getCampaignGoal().equals(C4944b.f4721hX) || !C4967f.m6842c(context, interstitialAd.getAdPackage())) {
            return (!interstitialAd.getCampaignGoal().equals(C4944b.f4722hY) || C4967f.m6842c(context, interstitialAd.getAdPackage())) ? 0 : 2;
        }
        return 1;
    }

    /* renamed from: b */
    private static int m6407b(Context context, AppnextAd appnextAd) {
        InterstitialAd interstitialAd = new InterstitialAd(appnextAd);
        if (!interstitialAd.getCptList().equals("") && !interstitialAd.getCptList().equals("[]")) {
            try {
                JSONArray jSONArray = new JSONArray(interstitialAd.getCptList());
                for (int i = 0; i < jSONArray.length(); i++) {
                    if (C4967f.m6842c(context, jSONArray.getString(i))) {
                        return 0;
                    }
                }
                return 3;
            } catch (JSONException unused) {
            }
        }
        return 0;
    }

    /* access modifiers changed from: protected */
    /* renamed from: g */
    public final void mo40780g(C4924Ad ad) {
        if (ad != null && mo41248j(ad) == 0) {
            mo41243aW().remove(new C4946b(ad));
        }
    }
}

package com.tappx.p048a;

import android.content.Context;
import android.view.View;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.tappx.p048a.C1517m0;

/* renamed from: com.tappx.a.s0 */
public class C1606s0 implements C1582q0 {

    /* renamed from: d */
    private static final AdSize[] f2252d = {AdSize.BANNER, AdSize.FULL_BANNER, AdSize.LARGE_BANNER, AdSize.LEADERBOARD, AdSize.MEDIUM_RECTANGLE, AdSize.WIDE_SKYSCRAPER};
    /* access modifiers changed from: private */

    /* renamed from: a */
    public final AdView f2253a;

    /* renamed from: b */
    private boolean f2254b = false;

    /* renamed from: c */
    private C1517m0.C1520c f2255c;

    /* renamed from: com.tappx.a.s0$a */
    class C1607a extends AdListener {

        /* renamed from: a */
        final /* synthetic */ C1517m0.C1520c f2256a;

        /* renamed from: b */
        final /* synthetic */ Runnable f2257b;

        C1607a(C1517m0.C1520c cVar, Runnable runnable) {
            this.f2256a = cVar;
            this.f2257b = runnable;
        }

        public void onAdClosed() {
        }

        public void onAdFailedToLoad(int i) {
            C1467j0.m2873d("1HPYA2lkbaNURYCXsP4iRrPA2bcLu2GoZBfTi2x2iws", new Object[0]);
            C1517m0.C1520c cVar = this.f2256a;
            if (cVar != null) {
                cVar.mo15960a(C1313a2.NO_FILL);
            }
        }

        public void onAdLeftApplication() {
            C1517m0.C1520c cVar = this.f2256a;
            if (cVar != null) {
                cVar.mo15958a();
            }
        }

        public void onAdLoaded() {
            C1467j0.m2873d("sQBMFfIvnZat9SH496KzHfKib626NzkhHKkXIfYGxxc", new Object[0]);
            this.f2257b.run();
            C1517m0.C1520c cVar = this.f2256a;
            if (cVar != null) {
                cVar.mo15959a((View) C1606s0.this.f2253a);
            }
        }

        public void onAdOpened() {
            C1517m0.C1520c cVar = this.f2256a;
            if (cVar != null) {
                cVar.mo15963d();
            }
        }
    }

    public C1606s0(Context context) {
        this.f2253a = new AdView(context);
    }

    public void destroy() {
        AdView adView = this.f2253a;
        if (adView != null) {
            adView.destroy();
        }
    }

    public void loadAd() {
        if (this.f2254b) {
            C1517m0.C1520c cVar = this.f2255c;
            if (cVar != null) {
                cVar.mo15960a(C1313a2.INTERNAL_ERROR);
                return;
            }
            return;
        }
        try {
            this.f2253a.loadAd(new AdRequest.Builder().build());
        } catch (Throwable unused) {
        }
    }

    /* renamed from: a */
    public void mo16070a(C1517m0.C1520c cVar, Runnable runnable) {
        this.f2255c = cVar;
        if (cVar == null) {
            try {
                this.f2253a.setAdListener((AdListener) null);
            } catch (Throwable unused) {
                this.f2254b = true;
            }
        } else {
            this.f2253a.setAdListener(new C1607a(cVar, runnable));
        }
    }

    /* renamed from: a */
    public void mo16071a(String str, int i, int i2) {
        try {
            this.f2253a.setAdSize(m3330a(i, i2));
            this.f2253a.setAdUnitId(str);
        } catch (Throwable unused) {
            this.f2254b = true;
        }
    }

    /* renamed from: a */
    private AdSize m3330a(int i, int i2) {
        for (AdSize adSize : f2252d) {
            if (adSize.getWidth() == i && adSize.getHeight() == i2) {
                return adSize;
            }
        }
        return AdSize.BANNER;
    }

    /* renamed from: a */
    public View mo16069a() {
        return this.f2253a;
    }
}

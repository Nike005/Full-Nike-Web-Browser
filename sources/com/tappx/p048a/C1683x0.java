package com.tappx.p048a;

import android.content.Context;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.tappx.p048a.C1361d1;

/* renamed from: com.tappx.a.x0 */
class C1683x0 implements C1652v0 {

    /* renamed from: a */
    private final InterstitialAd f2464a;

    /* renamed from: b */
    private boolean f2465b = false;

    /* renamed from: c */
    private C1361d1.C1363b f2466c;

    /* renamed from: com.tappx.a.x0$a */
    class C1684a extends AdListener {

        /* renamed from: a */
        final /* synthetic */ C1361d1.C1363b f2467a;

        /* renamed from: b */
        final /* synthetic */ Runnable f2468b;

        /* renamed from: c */
        final /* synthetic */ C1361d1 f2469c;

        C1684a(C1683x0 x0Var, C1361d1.C1363b bVar, Runnable runnable, C1361d1 d1Var) {
            this.f2467a = bVar;
            this.f2468b = runnable;
            this.f2469c = d1Var;
        }

        public void onAdClosed() {
            this.f2467a.mo15676d();
        }

        public void onAdFailedToLoad(int i) {
            C1467j0.m2873d("1HPYA2lkbaNURYCXsP4iRrPA2bcLu2GoZBfTi2x2iws", new Object[0]);
            this.f2467a.mo15672a(C1313a2.NO_FILL);
        }

        public void onAdLeftApplication() {
            this.f2467a.mo15671a();
            this.f2467a.mo15675c();
        }

        public void onAdLoaded() {
            this.f2468b.run();
            C1467j0.m2873d("sQBMFfIvnZat9SH496KzHfKib626NzkhHKkXIfYGxxc", new Object[0]);
            this.f2467a.mo15673a(this.f2469c);
        }

        public void onAdOpened() {
            this.f2467a.mo15674b();
        }
    }

    public C1683x0(Context context) {
        this.f2464a = new InterstitialAd(context);
    }

    /* renamed from: a */
    public void mo16224a(String str) {
        try {
            C1467j0.m2873d("XqPsOXkCkiOwfSDmQAngCTOElG/CkYie3dvHgxY0q1o", str);
            this.f2464a.setAdUnitId(str);
        } catch (Throwable unused) {
            this.f2465b = true;
        }
    }

    public void destroy() {
        try {
            if (this.f2464a != null) {
                this.f2464a.setAdListener((AdListener) null);
            }
        } catch (Throwable unused) {
        }
    }

    public void loadAd() {
        if (this.f2465b) {
            C1361d1.C1363b bVar = this.f2466c;
            if (bVar != null) {
                bVar.mo15672a(C1313a2.INTERNAL_ERROR);
                return;
            }
            return;
        }
        try {
            this.f2464a.loadAd(new AdRequest.Builder().build());
        } catch (Throwable unused) {
        }
    }

    public void show() {
        try {
            if (this.f2464a == null || !this.f2464a.isLoaded()) {
                C1467j0.m2873d("E7DpZ5iKZ4wFqPfA8T/0xoaEEF1mb1e+vYW2ILlIGMBhCfsQnXB9y+crvSN476OS+43wU0ucLzr4quLmL9S5+Q", new Object[0]);
            } else {
                this.f2464a.show();
            }
        } catch (Throwable unused) {
        }
    }

    /* renamed from: a */
    public void mo16223a(C1361d1.C1363b bVar, C1361d1 d1Var, Runnable runnable) {
        this.f2466c = bVar;
        if (bVar == null) {
            try {
                this.f2464a.setAdListener((AdListener) null);
            } catch (Throwable unused) {
                this.f2465b = true;
            }
        } else {
            this.f2464a.setAdListener(new C1684a(this, bVar, runnable, d1Var));
        }
    }
}

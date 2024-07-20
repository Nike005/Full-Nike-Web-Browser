package com.tappx.sdk.android;

import android.content.Context;
import com.tappx.p048a.C1433h2;

public final class TappxInterstitial implements ITappxInterstitial {

    /* renamed from: a */
    private final Context f2638a;

    /* renamed from: b */
    private C1433h2 f2639b;

    public TappxInterstitial(Context context, String str) {
        this.f2638a = context;
        C1433h2 h2Var = new C1433h2(this, context);
        this.f2639b = h2Var;
        h2Var.mo15780b(str);
    }

    public void destroy() {
        this.f2639b.mo15770a();
    }

    public Context getContext() {
        return this.f2638a;
    }

    public boolean isReady() {
        return this.f2639b.mo15844e();
    }

    public void loadAd() {
        loadAd((AdRequest) null);
    }

    public void setAutoShowWhenReady(boolean z) {
        this.f2639b.mo15843a(z);
    }

    public void setListener(TappxInterstitialListener tappxInterstitialListener) {
        this.f2639b.mo15842a(tappxInterstitialListener);
    }

    public void show() {
        this.f2639b.mo15845f();
    }

    public void loadAd(AdRequest adRequest) {
        this.f2639b.mo15773a(adRequest);
    }
}

package com.tappx.p048a;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.tappx.sdk.android.TappxPrivacyManager;

/* renamed from: com.tappx.a.r2 */
final class C1597r2 implements TappxPrivacyManager {

    /* renamed from: a */
    private final C1536n2 f2243a;

    C1597r2(C1536n2 n2Var) {
        new Handler(Looper.getMainLooper());
        this.f2243a = n2Var;
    }

    public void checkAndShowPrivacyDisclaimer(Activity activity) {
        checkAndShowPrivacyDisclaimer(activity, (Runnable) null);
    }

    public void denyPersonalInfoConsent() {
        this.f2243a.mo15993h();
    }

    public void grantPersonalInfoConsent() {
        this.f2243a.mo15994i();
    }

    public void renewPrivacyConsent(Activity activity) {
        this.f2243a.mo15980a((Context) activity);
    }

    public void setAutoPrivacyDisclaimerEnabled(boolean z) {
        this.f2243a.mo15984a(z);
    }

    public void setGDPRConsent(String str) {
        this.f2243a.mo15983a(str);
    }

    public void setUSPrivacy(String str) {
        this.f2243a.mo15987b(str);
    }

    public void checkAndShowPrivacyDisclaimer(Activity activity, Runnable runnable) {
        this.f2243a.mo15981a((Context) activity, runnable);
    }
}

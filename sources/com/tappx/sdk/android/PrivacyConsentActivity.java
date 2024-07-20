package com.tappx.sdk.android;

import android.app.Activity;
import android.os.Bundle;
import com.tappx.p048a.C1506l2;

public class PrivacyConsentActivity extends Activity {

    /* renamed from: a */
    private C1506l2 f2616a;

    public void onBackPressed() {
        if (!this.f2616a.mo15933a()) {
            super.onBackPressed();
        }
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        C1506l2 l2Var = new C1506l2(this);
        this.f2616a = l2Var;
        l2Var.mo15932a(bundle);
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        super.onStop();
        this.f2616a.mo15934b();
    }
}

package com.tappx.sdk.android;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import com.tappx.p048a.C1702y4;

public class BaseAdActivity extends Activity {

    /* renamed from: a */
    private C1702y4 f2609a;

    /* renamed from: a */
    private void m3788a() {
        if (Build.VERSION.SDK_INT >= 14) {
            getWindow().setFlags(16777216, 16777216);
        }
    }

    public void onBackPressed() {
        if (this.f2609a.mo16294d()) {
            super.onBackPressed();
        }
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        C1702y4 y4Var = new C1702y4(this);
        this.f2609a = y4Var;
        y4Var.mo16291a(bundle);
        m3788a();
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        this.f2609a.mo16290a();
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        this.f2609a.mo16292b();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        this.f2609a.mo16293c();
    }
}

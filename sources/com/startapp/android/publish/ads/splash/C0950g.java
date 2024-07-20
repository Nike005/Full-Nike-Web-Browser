package com.startapp.android.publish.ads.splash;

import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.Toast;
import com.startapp.android.publish.ads.p017a.C0851b;
import com.startapp.android.publish.common.model.AdPreferences;
import com.startapp.common.p043a.C1270g;
import java.io.Serializable;

/* renamed from: com.startapp.android.publish.ads.splash.g */
/* compiled from: StartAppSDK */
public class C0950g extends C0851b {

    /* renamed from: d */
    private SplashConfig f649d = null;

    /* renamed from: e */
    private C0951h f650e;

    /* renamed from: f */
    private boolean f651f = false;

    /* renamed from: g */
    private boolean f652g = false;

    /* renamed from: q */
    public void mo13787q() {
    }

    /* renamed from: a */
    public void mo13760a(Bundle bundle) {
        C1270g.m2078a("SplashMode", 3, "onCreate");
        this.f649d = (SplashConfig) mo13758a().getSerializableExtra("SplashConfig");
    }

    /* renamed from: a */
    public boolean mo13767a(int i, KeyEvent keyEvent) {
        C1270g.m2078a("SplashMode", 3, "onKeyDown");
        if (this.f651f) {
            if (i == 25) {
                if (!this.f652g) {
                    this.f652g = true;
                    this.f650e.mo14224g();
                    Toast.makeText(mo13768b(), "Test Mode", 0).show();
                    return true;
                }
            } else if (i == 24 && this.f652g) {
                mo13768b().finish();
                return true;
            }
        }
        return i == 4;
    }

    /* renamed from: s */
    public void mo13789s() {
        C1270g.m2078a("SplashMode", 3, "onPause");
        C0951h hVar = this.f650e;
        if (hVar != null) {
            hVar.mo14217a();
        }
    }

    /* renamed from: t */
    public void mo13790t() {
        C1270g.m2078a("SplashMode", 3, "onStop");
        C0951h hVar = this.f650e;
        if (hVar != null) {
            hVar.mo14219b();
        }
    }

    /* renamed from: u */
    public void mo13791u() {
        AdPreferences adPreferences;
        C1270g.m2078a("SplashMode", 3, "onResume");
        if (this.f649d != null) {
            Serializable serializableExtra = mo13758a().getSerializableExtra("AdPreference");
            if (serializableExtra != null) {
                adPreferences = (AdPreferences) serializableExtra;
            } else {
                adPreferences = new AdPreferences();
            }
            this.f651f = mo13758a().getBooleanExtra("testMode", false);
            C0951h hVar = new C0951h(mo13768b(), this.f649d, adPreferences);
            this.f650e = hVar;
            hVar.mo14218a((Bundle) null);
        }
    }

    /* renamed from: v */
    public void mo13792v() {
        C1270g.m2078a("SplashMode", 3, "onDestroy");
    }
}

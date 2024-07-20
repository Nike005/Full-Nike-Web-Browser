package com.startapp.android.publish.adsCommon.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.KeyEvent;
import com.startapp.android.publish.ads.p017a.C0851b;
import com.startapp.android.publish.adsCommon.C1103c;
import com.startapp.android.publish.adsCommon.Utils.C1061i;
import com.startapp.android.publish.common.model.AdPreferences;
import com.startapp.common.C1275b;
import com.startapp.common.p043a.C1261c;
import com.startapp.common.p043a.C1270g;

/* compiled from: StartAppSDK */
public class OverlayActivity extends Activity {

    /* renamed from: a */
    private C0851b f1017a;

    /* renamed from: b */
    private boolean f1018b;

    /* renamed from: c */
    private int f1019c;

    /* renamed from: d */
    private boolean f1020d;

    /* renamed from: e */
    private Bundle f1021e;

    /* renamed from: f */
    private boolean f1022f = false;

    /* renamed from: g */
    private int f1023g = -1;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        boolean z = false;
        overridePendingTransition(0, 0);
        super.onCreate(bundle);
        boolean booleanExtra = getIntent().getBooleanExtra("videoAd", false);
        requestWindowFeature(1);
        if (getIntent().getBooleanExtra("fullscreen", false) || booleanExtra) {
            getWindow().setFlags(1024, 1024);
        }
        C1270g.m2078a("AppWallActivity", 2, "AppWallActivity::onCreate");
        this.f1020d = getIntent().getBooleanExtra("activityShouldLockOrientation", true);
        if (bundle == null && !booleanExtra) {
            C1275b.m2102a((Context) this).mo15481a(new Intent("com.startapp.android.ShowDisplayBroadcastListener"));
        }
        if (bundle != null) {
            this.f1023g = bundle.getInt("activityLockedOrientation", -1);
            this.f1020d = bundle.getBoolean("activityShouldLockOrientation", true);
        }
        this.f1019c = getIntent().getIntExtra("orientation", getResources().getConfiguration().orientation);
        if (getResources().getConfiguration().orientation != this.f1019c) {
            z = true;
        }
        this.f1018b = z;
        if (!z) {
            m1235a();
            this.f1017a.mo13760a(bundle);
            return;
        }
        this.f1021e = bundle;
    }

    /* renamed from: a */
    private void m1235a() {
        C0851b a = C0851b.m452a(this, getIntent(), AdPreferences.Placement.getByIndex(getIntent().getIntExtra("placement", 0)));
        this.f1017a = a;
        if (a == null) {
            finish();
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (this.f1018b) {
            m1235a();
            this.f1017a.mo13760a(this.f1021e);
            this.f1017a.mo13791u();
            this.f1018b = false;
        }
        this.f1017a.mo13759a(configuration);
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        C0851b bVar = this.f1017a;
        if (bVar == null || bVar.mo13767a(i, keyEvent)) {
            return true;
        }
        return super.onKeyDown(i, keyEvent);
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        C1270g.m2078a("AppWallActivity", 2, "OverlayActivity::onPause");
        super.onPause();
        if (!this.f1018b) {
            this.f1017a.mo13789s();
            C1103c.m1370a((Context) this);
        }
        overridePendingTransition(0, 0);
    }

    /* access modifiers changed from: protected */
    public void onSaveInstanceState(Bundle bundle) {
        C1270g.m2078a("AppWallActivity", 2, "AppWallActivity::onSaveInstanceState");
        super.onSaveInstanceState(bundle);
        if (!this.f1018b) {
            this.f1017a.mo13770b(bundle);
            bundle.putInt("activityLockedOrientation", this.f1023g);
            bundle.putBoolean("activityShouldLockOrientation", this.f1020d);
        }
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        C1270g.m2078a("AppWallActivity", 2, "AppWallActivity::onResume");
        super.onResume();
        if (this.f1022f) {
            this.f1017a.mo13772c();
        }
        int i = this.f1023g;
        if (i == -1) {
            this.f1023g = C1061i.m1172a((Activity) this, this.f1019c, this.f1020d);
        } else {
            C1261c.m2021a((Activity) this, i);
        }
        if (!this.f1018b) {
            this.f1017a.mo13791u();
        }
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        C1270g.m2078a("AppWallActivity", 2, "AppWallActivity::onStop");
        super.onStop();
        if (!this.f1018b) {
            this.f1017a.mo13790t();
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        C1270g.m2078a("AppWallActivity", 2, "AppWallActivity::onDestroy");
        if (!this.f1018b) {
            this.f1017a.mo13792v();
            this.f1017a = null;
            C1061i.m1182a((Activity) this, false);
        }
        super.onDestroy();
    }

    public void onBackPressed() {
        if (!this.f1017a.mo13788r()) {
            super.onBackPressed();
        }
    }

    public void finish() {
        C0851b bVar = this.f1017a;
        if (bVar != null) {
            bVar.mo13787q();
        }
        super.finish();
    }
}

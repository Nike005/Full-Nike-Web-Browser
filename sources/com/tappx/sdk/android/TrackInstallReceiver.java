package com.tappx.sdk.android;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.tappx.p048a.C1314a3;
import com.tappx.p048a.C1467j0;

public class TrackInstallReceiver extends BroadcastReceiver {

    /* renamed from: a */
    private final C1314a3 f2640a = new C1314a3();

    public void onReceive(Context context, Intent intent) {
        if (intent.hasExtra("ilp")) {
            C1467j0.m2868a(intent.getStringExtra("ilp"));
        } else if ("com.android.vending.INSTALL_REFERRER".equalsIgnoreCase(intent.getAction())) {
            this.f2640a.mo15527a(context, intent);
        }
    }
}

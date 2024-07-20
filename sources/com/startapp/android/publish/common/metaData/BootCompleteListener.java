package com.startapp.android.publish.common.metaData;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import com.startapp.android.publish.adsCommon.Utils.C1051b;
import com.startapp.android.publish.adsCommon.p033f.C1130d;
import com.startapp.android.publish.adsCommon.p033f.C1132f;
import com.startapp.common.p043a.C1270g;

/* compiled from: StartAppSDK */
public class BootCompleteListener extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        try {
            C1270g.m2076a(3, "BootCompleteListener - onReceive");
            long elapsedRealtime = SystemClock.elapsedRealtime() + 60000;
            C1051b.m1136a(context);
            C1051b.m1139a(context, Long.valueOf(elapsedRealtime));
            C1051b.m1138a(context, elapsedRealtime);
        } catch (Exception e) {
            C1132f.m1527a(context, C1130d.EXCEPTION, "BootCompleteListener.onReceive - failed to start services", e.getMessage(), "");
        }
    }
}

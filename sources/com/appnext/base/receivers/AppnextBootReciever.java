package com.appnext.base.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.appnext.base.operations.imp.scdle;
import com.appnext.base.p078a.C4880a;
import com.appnext.base.p078a.p080b.C4887c;
import com.appnext.base.p082b.C4899d;
import com.appnext.base.p082b.C4901e;
import com.appnext.base.p082b.C4903g;
import com.appnext.base.services.p084b.C4922a;

public class AppnextBootReciever extends BroadcastReceiver {
    public void onReceive(final Context context, Intent intent) {
        try {
            C4901e.init(context);
            if ("android.intent.action.BOOT_COMPLETED".equals(intent.getAction())) {
                C4903g.m6575aN().mo41004b(new Runnable() {
                    public final void run() {
                        try {
                            String simpleName = scdle.class.getSimpleName();
                            C4887c cVar = new C4887c(C4899d.f4618fe, "", "", "1", C4899d.f4626fm, simpleName, simpleName + System.currentTimeMillis(), (String) null);
                            C4880a.m6472X().mo40942ab().mo40975a(cVar);
                            C4922a.m6693d(context).mo41070a(cVar, true);
                        } catch (Throwable unused) {
                        }
                    }
                });
            }
        } catch (Throwable unused) {
        }
    }
}

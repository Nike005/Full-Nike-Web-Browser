package com.startapp.android.publish.common.metaData;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import com.startapp.common.p044b.C1279a;
import com.startapp.common.p044b.p045a.C1285b;

/* compiled from: StartAppSDK */
public class InfoEventService extends Service {

    /* renamed from: a */
    private static final String f1378a = "InfoEventService";

    public IBinder onBind(Intent intent) {
        return null;
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        C1279a.m2109a((Context) this);
        boolean a = C1279a.m2121a(intent, (C1285b.C1287b) new C1285b.C1287b() {
            /* renamed from: a */
            public void mo15139a(C1285b.C1286a aVar) {
                InfoEventService.this.stopSelf();
            }
        });
        C1279a.m2114a(3, f1378a, "onHandleIntent: RunnerManager.runJob" + a, (Throwable) null);
        return super.onStartCommand(intent, i, i2);
    }
}

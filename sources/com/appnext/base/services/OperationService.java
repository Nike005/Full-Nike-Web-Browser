package com.appnext.base.services;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import com.appnext.base.operations.C4910a;
import com.appnext.base.p082b.C4899d;
import com.appnext.base.services.p083a.C4920c;

public class OperationService extends IntentService {
    public OperationService() {
        super(OperationService.class.getName());
    }

    /* access modifiers changed from: protected */
    public void onHandleIntent(Intent intent) {
        try {
            String stringExtra = intent.getStringExtra(C4899d.f4620fg);
            Bundle bundleExtra = intent.getBundleExtra(C4920c.f4674eH);
            new C4921b().mo41069a(getApplicationContext(), stringExtra, (String) null, bundleExtra, (Intent) intent.clone(), (C4910a.C4911a) null);
        } catch (Throwable unused) {
        }
    }
}

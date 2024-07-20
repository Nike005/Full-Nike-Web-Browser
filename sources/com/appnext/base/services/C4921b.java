package com.appnext.base.services;

import android.content.Context;
import android.os.Bundle;
import com.appnext.base.operations.C4910a;
import com.appnext.base.operations.C4912b;
import com.appnext.base.p078a.C4880a;
import com.appnext.base.p078a.p080b.C4887c;
import com.appnext.base.p082b.C4901e;
import com.appnext.base.p082b.C4905i;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;

/* renamed from: com.appnext.base.services.b */
public final class C4921b {
    /* renamed from: a */
    public final void mo41069a(Context context, String str, String str2, Bundle bundle, Object obj, C4910a.C4911a aVar) {
        try {
            C4905i.m6591aR().init(context.getApplicationContext());
            C4901e.init(context.getApplicationContext());
            AdvertisingIdClient.Info advertisingIdInfo = AdvertisingIdClient.getAdvertisingIdInfo(context.getApplicationContext());
            if (advertisingIdInfo != null && advertisingIdInfo.isLimitAdTrackingEnabled()) {
                C4905i.m6591aR().putBoolean(C4905i.f4638fC, true);
                m6691b(aVar);
                return;
            }
            C4887c t = C4880a.m6472X().mo40942ab().mo40981t(str);
            if (t == null) {
                m6691b(aVar);
            } else {
                C4912b.m6644aI().mo41052a(t.getKey(), t, bundle, obj, aVar);
            }
        } catch (Throwable unused) {
            m6691b(aVar);
        }
    }

    /* renamed from: b */
    private static void m6691b(C4910a.C4911a aVar) {
        if (aVar != null) {
            try {
                aVar.mo41049aH();
            } catch (Throwable unused) {
            }
        }
    }
}

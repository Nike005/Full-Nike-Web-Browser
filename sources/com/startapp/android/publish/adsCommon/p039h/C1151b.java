package com.startapp.android.publish.adsCommon.p039h;

import android.content.Context;
import com.startapp.android.publish.adsCommon.C1166k;
import com.startapp.android.publish.adsCommon.p030c.C1111b;
import com.startapp.android.publish.adsCommon.p033f.C1127b;
import com.startapp.android.publish.common.metaData.MetaData;
import java.util.concurrent.TimeUnit;

/* renamed from: com.startapp.android.publish.adsCommon.h.b */
/* compiled from: StartAppSDK */
public class C1151b extends C1149a {
    public C1151b(Context context, Runnable runnable, C1127b bVar) {
        super(context, runnable, bVar);
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public void mo14941b() {
        try {
            long millis = TimeUnit.SECONDS.toMillis((long) MetaData.getInstance().getBluetoothConfig().mo15242a());
            final C1111b bVar = new C1111b(this.f1210a, this);
            mo14940a(new Runnable() {
                public void run() {
                    bVar.mo14811a();
                    C1151b.this.mo14939a(bVar.mo14813b());
                }
            }, millis);
            bVar.mo14812a(m1576c());
        } catch (Exception unused) {
            mo14939a((Object) null);
        }
    }

    /* renamed from: c */
    private boolean m1576c() {
        long currentTimeMillis = System.currentTimeMillis();
        boolean z = currentTimeMillis - C1166k.m1609a(this.f1210a, "lastBtDiscoveringTime", (Long) 0L).longValue() >= ((long) MetaData.getInstance().getBluetoothConfig().mo15244c()) * 60000;
        if (z) {
            C1166k.m1616b(this.f1210a, "lastBtDiscoveringTime", Long.valueOf(currentTimeMillis));
        }
        return z;
    }

    /* renamed from: a */
    public void mo14939a(Object obj) {
        if (obj != null) {
            this.f1212c.mo14876b(obj.toString());
        }
        super.mo14939a(obj);
    }
}

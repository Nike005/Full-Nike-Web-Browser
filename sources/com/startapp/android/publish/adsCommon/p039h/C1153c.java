package com.startapp.android.publish.adsCommon.p039h;

import android.content.Context;
import com.startapp.android.publish.adsCommon.p033f.C1127b;
import com.startapp.android.publish.adsCommon.p041j.C1163b;
import com.startapp.android.publish.common.metaData.MetaData;
import java.util.concurrent.TimeUnit;

/* renamed from: com.startapp.android.publish.adsCommon.h.c */
/* compiled from: StartAppSDK */
public class C1153c extends C1149a {
    public C1153c(Context context, Runnable runnable, C1127b bVar) {
        super(context, runnable, bVar);
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public void mo14941b() {
        try {
            long millis = TimeUnit.SECONDS.toMillis((long) MetaData.getInstance().getSensorsConfig().mo15251a());
            final C1163b bVar = new C1163b(this.f1210a, this);
            mo14940a(new Runnable() {
                public void run() {
                    bVar.mo14959b();
                    C1153c.this.mo14939a(bVar.mo14960c());
                }
            }, millis);
            bVar.mo14958a();
        } catch (Exception unused) {
            mo14939a((Object) null);
        }
    }

    /* renamed from: a */
    public void mo14939a(Object obj) {
        if (obj != null) {
            this.f1212c.mo14873a(obj.toString());
        }
        super.mo14939a(obj);
    }
}

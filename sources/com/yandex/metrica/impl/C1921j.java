package com.yandex.metrica.impl;

import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import com.yandex.metrica.impl.C1890bg;
import com.yandex.metrica.impl.p050ob.C2093du;
import com.yandex.metrica.impl.utils.C2223g;
import com.yandex.metrica.impl.utils.C2231l;
import com.yandex.metrica.impl.utils.C2232m;
import java.util.Map;

/* renamed from: com.yandex.metrica.impl.j */
public class C1921j extends ResultReceiver {

    /* renamed from: a */
    private C1922a f3188a;

    /* renamed from: com.yandex.metrica.impl.j$a */
    interface C1922a {
        /* renamed from: a */
        void mo17090a(int i, Bundle bundle);
    }

    public C1921j(Handler handler) {
        super(handler);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo17143a(C1922a aVar) {
        this.f3188a = aVar;
    }

    /* access modifiers changed from: protected */
    public void onReceiveResult(int i, Bundle bundle) {
        C1922a aVar = this.f3188a;
        if (aVar != null) {
            aVar.mo17090a(i, bundle);
        }
    }

    /* renamed from: a */
    public static void m4769a(ResultReceiver resultReceiver, C1877ba baVar, C1890bg.C1891a aVar) {
        if (resultReceiver != null) {
            Bundle bundle = new Bundle();
            bundle.putString("UuId", baVar.mo16981b());
            bundle.putString("DeviceId", baVar.mo16986c());
            bundle.putString("AdUrlGet", baVar.mo16955A());
            bundle.putString("AdUrlReport", baVar.mo16956B());
            bundle.putLong("ServerTimeOffset", C2232m.m5973a());
            bundle.putString("Clids", C2223g.m5948a((Map) C2231l.m5972a(baVar.mo17030y())));
            bundle.putString("CookieBrowsers", aVar.mo17065l().mo17647a());
            bundle.putString("BindIdUrl", aVar.mo17066m());
            resultReceiver.send(1, bundle);
        }
    }

    /* renamed from: a */
    public static void m4770a(ResultReceiver resultReceiver, C2093du duVar) {
        if (resultReceiver != null) {
            resultReceiver.send(2, duVar.mo17650a(new Bundle()));
        }
    }
}

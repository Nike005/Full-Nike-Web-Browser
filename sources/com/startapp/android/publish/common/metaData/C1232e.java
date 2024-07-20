package com.startapp.android.publish.common.metaData;

import android.content.Context;
import com.startapp.android.publish.adsCommon.Utils.C1051b;
import com.startapp.android.publish.adsCommon.p033f.C1128c;
import com.startapp.android.publish.adsCommon.p033f.C1130d;
import com.startapp.android.publish.adsCommon.p033f.C1132f;
import com.startapp.common.C1298d;
import com.startapp.common.p043a.C1270g;
import com.startapp.common.p044b.p045a.C1285b;
import java.util.Map;

/* renamed from: com.startapp.android.publish.common.metaData.e */
/* compiled from: StartAppSDK */
public class C1232e implements C1285b {
    /* renamed from: a */
    public void mo14857a(final Context context, int i, Map<String, String> map, final C1285b.C1287b bVar) {
        C1270g.m2078a("PeriodicInfoEvent", 3, "PeriodicInfoEvent execute");
        try {
            MetaData.init(context);
            MetaData.getInstance().setReady(true);
            if (MetaData.getInstance().isPeriodicInfoEventEnabled()) {
                new C1128c(context, true, new C1298d() {
                    /* renamed from: a */
                    public void mo14939a(Object obj) {
                        if (bVar != null) {
                            C1051b.m1142d(context);
                            bVar.mo15139a(C1285b.C1286a.SUCCESS);
                        }
                    }
                }).mo14880a();
            } else if (bVar != null) {
                C1051b.m1142d(context);
                bVar.mo15139a(C1285b.C1286a.SUCCESS);
            }
        } catch (Exception e) {
            C1132f.m1527a(context, C1130d.EXCEPTION, "PeriodicInfoEvent.execute", e.getMessage(), "");
        }
    }
}

package com.startapp.android.publish.common.metaData;

import android.content.Context;
import com.startapp.android.publish.adsCommon.AdsConstants;
import com.startapp.android.publish.adsCommon.C1166k;
import com.startapp.android.publish.adsCommon.C1168l;
import com.startapp.android.publish.adsCommon.Utils.C1051b;
import com.startapp.android.publish.adsCommon.Utils.C1061i;
import com.startapp.android.publish.adsCommon.p033f.C1130d;
import com.startapp.android.publish.adsCommon.p033f.C1132f;
import com.startapp.android.publish.adsCommon.p042k.C1167a;
import com.startapp.android.publish.common.metaData.MetaDataRequest;
import com.startapp.android.publish.common.model.AdPreferences;
import com.startapp.common.p043a.C1270g;
import com.startapp.common.p044b.p045a.C1285b;
import java.util.Map;

/* renamed from: com.startapp.android.publish.common.metaData.f */
/* compiled from: StartAppSDK */
public class C1234f implements C1285b {
    /* renamed from: a */
    public void mo14857a(Context context, int i, Map<String, String> map, C1285b.C1287b bVar) {
        try {
            MetaData.init(context);
            if (MetaData.getInstance().isPeriodicMetaDataEnabled()) {
                m1909a(context, bVar);
            }
        } catch (Exception e) {
            C1132f.m1527a(context, C1130d.EXCEPTION, "PeriodicMetaData.execute", e.getMessage(), "");
        }
    }

    /* renamed from: a */
    private static void m1909a(Context context, C1285b.C1287b bVar) {
        final AdPreferences adPreferences = new AdPreferences(C1166k.m1610a(context, "shared_prefs_devId", (String) null), C1166k.m1610a(context, "shared_prefs_appId", ""));
        final Context context2 = context;
        final C1285b.C1287b bVar2 = bVar;
        new C1228c(context, adPreferences, MetaDataRequest.C1224a.PERIODIC) {

            /* renamed from: d */
            private MetaData f1416d = null;

            /* access modifiers changed from: protected */
            /* renamed from: c */
            public Boolean mo15248c() {
                C1270g.m2076a(3, "Loading MetaData");
                try {
                    C1168l.m1636b(context2);
                    MetaDataRequest metaDataRequest = new MetaDataRequest(context2, MetaDataRequest.C1224a.PERIODIC);
                    metaDataRequest.fillApplicationDetails(context2, adPreferences, false);
                    metaDataRequest.fillLocationDetails(adPreferences, context2);
                    this.f1416d = (MetaData) C1061i.m1176a(C1167a.m1619a(context2, AdsConstants.m1127a(AdsConstants.ServiceApiType.METADATA), metaDataRequest, (Map<String, String>) null).mo15466a(), MetaData.class);
                    return Boolean.TRUE;
                } catch (Exception e) {
                    C1270g.m2077a(6, "Unable to handle GetMetaData command!!!!", (Throwable) e);
                    return Boolean.FALSE;
                }
            }

            /* access modifiers changed from: protected */
            /* renamed from: a */
            public void mo15246a(Boolean bool) {
                try {
                    if (!(!bool.booleanValue() || this.f1416d == null || context2 == null)) {
                        MetaData.update(context2, this.f1416d);
                    }
                    C1051b.m1141c(context2);
                    if (bVar2 != null) {
                        bVar2.mo15139a(C1285b.C1286a.SUCCESS);
                    }
                } catch (Exception e) {
                    C1132f.m1527a(context2, C1130d.EXCEPTION, "PeriodicMetaData.onPostExecute", e.getMessage(), "");
                }
            }
        }.mo15245a();
    }
}

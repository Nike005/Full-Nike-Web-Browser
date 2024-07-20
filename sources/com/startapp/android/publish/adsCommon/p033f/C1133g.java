package com.startapp.android.publish.adsCommon.p033f;

import android.content.Context;
import com.startapp.android.publish.adsCommon.C1168l;
import com.startapp.android.publish.adsCommon.Utils.C1061i;
import com.startapp.android.publish.adsCommon.p042k.C1167a;
import com.startapp.android.publish.common.metaData.MetaData;
import com.startapp.android.publish.common.model.AdPreferences;
import com.startapp.common.C1301e;
import com.startapp.common.C1303g;
import com.startapp.common.p043a.C1270g;
import java.util.Map;

/* renamed from: com.startapp.android.publish.adsCommon.f.g */
/* compiled from: StartAppSDK */
public class C1133g {

    /* renamed from: a */
    private final Context f1191a;

    /* renamed from: b */
    private final AdPreferences f1192b;

    /* renamed from: c */
    private final C1131e f1193c;
    /* access modifiers changed from: private */

    /* renamed from: d */
    public final C1135a f1194d;

    /* renamed from: com.startapp.android.publish.adsCommon.f.g$a */
    /* compiled from: StartAppSDK */
    public interface C1135a {
        /* renamed from: a */
        void mo14858a(boolean z);
    }

    public C1133g(Context context, AdPreferences adPreferences, C1131e eVar, C1135a aVar) {
        this.f1191a = context;
        this.f1192b = adPreferences;
        this.f1193c = eVar;
        this.f1194d = aVar;
    }

    /* renamed from: a */
    public void mo14904a() {
        C1303g.m2206a(C1303g.C1307a.DEFAULT, (Runnable) new Runnable() {
            public void run() {
                boolean b = C1133g.this.mo14905b();
                if (C1133g.this.f1194d != null) {
                    C1133g.this.f1194d.mo14858a(b);
                }
            }
        });
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public boolean mo14905b() {
        C1270g.m2076a(3, "Sending InfoEvent " + this.f1193c);
        try {
            C1061i.m1184a(this.f1191a, this.f1192b);
            try {
                C1168l.m1636b(this.f1191a);
                this.f1193c.fillLocationDetails(this.f1192b, this.f1191a);
                this.f1193c.fillApplicationDetails(this.f1191a, this.f1192b);
            } catch (Exception unused) {
            }
            try {
                C1270g.m2076a(3, "Networking InfoEvent");
                String a = MetaData.getInstance().getAnalyticsConfig().mo14865a();
                if (C1130d.PERIODIC.equals(this.f1193c.mo14888e())) {
                    a = MetaData.getInstance().getAnalyticsConfig().mo14866b();
                }
                C1167a.m1621a(this.f1191a, a, this.f1193c, (Map<String, String>) null, MetaData.getInstance().getAnalyticsConfig().mo14868d(), MetaData.getInstance().getAnalyticsConfig().mo14869e());
                return true;
            } catch (C1301e e) {
                C1270g.m2077a(6, "Unable to send InfoEvent command!!!!", (Throwable) e);
                return false;
            }
        } catch (Exception e2) {
            C1270g.m2077a(6, "Unable to fill AdPreferences ", (Throwable) e2);
            return false;
        }
    }
}

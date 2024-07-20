package com.startapp.android.publish.adsCommon.Utils;

import android.content.Context;
import com.startapp.android.publish.adsCommon.p028a.C1069b;
import com.startapp.android.publish.common.metaData.C1231d;
import com.startapp.android.publish.common.metaData.MetaData;
import com.startapp.android.publish.common.metaData.MetaDataRequest;
import com.startapp.android.publish.common.model.AdPreferences;
import com.startapp.common.p043a.C1270g;
import java.util.UUID;

/* renamed from: com.startapp.android.publish.adsCommon.Utils.g */
/* compiled from: StartAppSDK */
public class C1059g {

    /* renamed from: a */
    private static C1059g f987a = new C1059g();

    /* renamed from: b */
    private String f988b = "";

    /* renamed from: c */
    private long f989c = 0;

    /* renamed from: d */
    private MetaDataRequest.C1224a f990d = MetaDataRequest.C1224a.LAUNCH;

    /* renamed from: a */
    public String mo14634a() {
        return this.f988b;
    }

    /* renamed from: b */
    public long mo14636b() {
        return this.f989c;
    }

    /* renamed from: c */
    public MetaDataRequest.C1224a mo14637c() {
        return this.f990d;
    }

    /* renamed from: a */
    public synchronized void mo14635a(Context context, MetaDataRequest.C1224a aVar) {
        this.f988b = UUID.randomUUID().toString();
        this.f989c = System.currentTimeMillis();
        this.f990d = aVar;
        C1270g.m2078a("SessionManager", 3, "Starting new session: reason=" + aVar + " sessionId=" + this.f988b);
        if (!C1061i.m1193a()) {
            C1069b.m1217a().mo14656b();
        }
        AdPreferences adPreferences = new AdPreferences();
        C1061i.m1184a(context, adPreferences);
        MetaData.getInstance().loadFromServer(context, adPreferences, aVar, false, (C1231d) null, true);
    }

    /* renamed from: d */
    public static C1059g m1157d() {
        return f987a;
    }
}

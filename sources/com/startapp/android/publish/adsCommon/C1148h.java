package com.startapp.android.publish.adsCommon;

import android.content.Context;
import com.startapp.android.publish.common.model.AdDetails;
import com.startapp.android.publish.common.model.AdPreferences;
import java.util.List;
import java.util.concurrent.TimeUnit;

/* renamed from: com.startapp.android.publish.adsCommon.h */
/* compiled from: StartAppSDK */
public abstract class C1148h extends C1040Ad {
    private static final long serialVersionUID = 1;
    private List<AdDetails> adsDetails = null;

    public C1148h(Context context, AdPreferences.Placement placement) {
        super(context, placement);
    }

    /* renamed from: a */
    public void mo14936a(List<AdDetails> list) {
        this.adsDetails = list;
        mo13948b();
        mo13946a();
    }

    /* renamed from: a */
    private void mo13946a() {
        boolean z = true;
        for (AdDetails isBelowMinCPM : this.adsDetails) {
            if (!isBelowMinCPM.getIsBelowMinCPM()) {
                z = false;
            }
        }
        this.belowMinCPM = z;
    }

    /* renamed from: d */
    public List<AdDetails> mo14937d() {
        return this.adsDetails;
    }

    /* renamed from: b */
    private void mo13948b() {
        List<AdDetails> list = this.adsDetails;
        Long l = null;
        if (list != null) {
            for (AdDetails next : list) {
                if (!(next == null || next.getTtl() == null)) {
                    if (l == null || next.getTtl().longValue() < l.longValue()) {
                        l = next.getTtl();
                    }
                }
            }
        }
        if (l != null) {
            this.adCacheTtl = Long.valueOf(TimeUnit.SECONDS.toMillis(l.longValue()));
        }
    }
}

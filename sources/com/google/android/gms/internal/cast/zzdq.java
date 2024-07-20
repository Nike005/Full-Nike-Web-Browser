package com.google.android.gms.internal.cast;

import android.hardware.display.VirtualDisplay;
import com.google.android.gms.cast.CastRemoteDisplay;
import com.google.android.gms.cast.CastRemoteDisplayApi;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;

@Deprecated
public final class zzdq implements CastRemoteDisplayApi {
    /* access modifiers changed from: private */
    public static final zzdg zzbd = new zzdg("CastRemoteDisplayApiImpl");
    /* access modifiers changed from: private */
    public VirtualDisplay zzbe;
    /* access modifiers changed from: private */
    public Api<?> zzxs;
    /* access modifiers changed from: private */
    public final zzeg zzxt = new zzdr(this);

    public zzdq(Api api) {
        this.zzxs = api;
    }

    /* access modifiers changed from: private */
    /* renamed from: a_ */
    public final void m51a_() {
        VirtualDisplay virtualDisplay = this.zzbe;
        if (virtualDisplay != null) {
            if (virtualDisplay.getDisplay() != null) {
                zzdg zzdg = zzbd;
                int displayId = this.zzbe.getDisplay().getDisplayId();
                StringBuilder sb = new StringBuilder(38);
                sb.append("releasing virtual display: ");
                sb.append(displayId);
                zzdg.mo6870d(sb.toString(), new Object[0]);
            }
            this.zzbe.release();
            this.zzbe = null;
        }
    }

    public final PendingResult<CastRemoteDisplay.CastRemoteDisplaySessionResult> startRemoteDisplay(GoogleApiClient googleApiClient, String str) {
        zzbd.mo6870d("startRemoteDisplay", new Object[0]);
        return googleApiClient.execute(new zzds(this, googleApiClient, str));
    }

    public final PendingResult<CastRemoteDisplay.CastRemoteDisplaySessionResult> stopRemoteDisplay(GoogleApiClient googleApiClient) {
        zzbd.mo6870d("stopRemoteDisplay", new Object[0]);
        return googleApiClient.execute(new zzdt(this, googleApiClient));
    }
}

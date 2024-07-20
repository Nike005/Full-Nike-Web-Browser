package com.google.android.gms.internal.cast;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;

final class zzds extends zzdv {
    private final /* synthetic */ zzdq zzxu;
    private final /* synthetic */ String zzxv;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzds(zzdq zzdq, GoogleApiClient googleApiClient, String str) {
        super(zzdq, googleApiClient);
        this.zzxu = zzdq;
        this.zzxv = str;
    }

    public final /* synthetic */ void doExecute(Api.AnyClient anyClient) throws RemoteException {
        doExecute((zzea) anyClient);
    }

    public final void zza(zzea zzea) throws RemoteException {
        zzea.zza(new zzdw(this, zzea), this.zzxu.zzxt, this.zzxv);
    }
}

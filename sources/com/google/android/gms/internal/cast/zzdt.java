package com.google.android.gms.internal.cast;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;

final class zzdt extends zzdv {
    zzdt(zzdq zzdq, GoogleApiClient googleApiClient) {
        super(zzdq, googleApiClient);
    }

    public final /* synthetic */ void doExecute(Api.AnyClient anyClient) throws RemoteException {
        doExecute((zzea) anyClient);
    }

    public final void zza(zzea zzea) throws RemoteException {
        zzea.zza(new zzdx(this));
    }
}

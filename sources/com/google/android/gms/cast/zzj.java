package com.google.android.gms.cast;

import android.os.RemoteException;
import com.google.android.gms.cast.Cast;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.internal.cast.zzcn;
import com.google.android.gms.internal.cast.zzcy;

final class zzj extends zzcy {
    zzj(Cast.CastApi.zza zza, GoogleApiClient googleApiClient) {
        super(googleApiClient);
    }

    public final /* synthetic */ void doExecute(Api.AnyClient anyClient) throws RemoteException {
        doExecute((zzcn) anyClient);
    }

    public final void zza(zzcn zzcn) throws RemoteException {
        try {
            zzcn.zzb(this);
        } catch (IllegalStateException unused) {
            zzk(2001);
        }
    }
}
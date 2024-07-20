package com.google.android.gms.cast;

import android.os.RemoteException;
import com.google.android.gms.cast.Cast;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.internal.cast.zzcn;
import com.google.android.gms.internal.cast.zzcy;

final class zzf extends zzcy {
    private final /* synthetic */ String zzad;
    private final /* synthetic */ String zzae;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzf(Cast.CastApi.zza zza, GoogleApiClient googleApiClient, String str, String str2) {
        super(googleApiClient);
        this.zzad = str;
        this.zzae = str2;
    }

    public final /* synthetic */ void doExecute(Api.AnyClient anyClient) throws RemoteException {
        doExecute((zzcn) anyClient);
    }

    public final void zza(zzcn zzcn) throws RemoteException {
        try {
            zzcn.zza(this.zzad, this.zzae, (BaseImplementation.ResultHolder<Status>) this);
        } catch (IllegalArgumentException | IllegalStateException unused) {
            zzk(2001);
        }
    }
}

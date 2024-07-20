package com.google.android.gms.cast;

import android.os.RemoteException;
import com.google.android.gms.cast.Cast;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.internal.cast.zzcn;

final class zzh extends Cast.zza {
    private final /* synthetic */ String zzaf;
    private final /* synthetic */ LaunchOptions zzag;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzh(Cast.CastApi.zza zza, GoogleApiClient googleApiClient, String str, LaunchOptions launchOptions) {
        super(googleApiClient);
        this.zzaf = str;
        this.zzag = launchOptions;
    }

    public final /* synthetic */ void doExecute(Api.AnyClient anyClient) throws RemoteException {
        doExecute((zzcn) anyClient);
    }

    public final void zza(zzcn zzcn) throws RemoteException {
        try {
            zzcn.zza(this.zzaf, this.zzag, (BaseImplementation.ResultHolder<Cast.ApplicationConnectionResult>) this);
        } catch (IllegalStateException unused) {
            zzk(2001);
        }
    }
}

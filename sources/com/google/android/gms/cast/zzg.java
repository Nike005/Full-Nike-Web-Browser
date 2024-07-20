package com.google.android.gms.cast;

import android.os.RemoteException;
import com.google.android.gms.cast.Cast;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.internal.cast.zzcn;

final class zzg extends Cast.zza {
    private final /* synthetic */ String zzaf;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzg(Cast.CastApi.zza zza, GoogleApiClient googleApiClient, String str) {
        super(googleApiClient);
        this.zzaf = str;
    }

    public final /* synthetic */ void doExecute(Api.AnyClient anyClient) throws RemoteException {
        doExecute((zzcn) anyClient);
    }

    public final void zza(zzcn zzcn) throws RemoteException {
        try {
            String str = this.zzaf;
            LaunchOptions launchOptions = new LaunchOptions();
            launchOptions.setRelaunchIfRunning(false);
            zzcn.zza(str, launchOptions, (BaseImplementation.ResultHolder<Cast.ApplicationConnectionResult>) this);
        } catch (IllegalStateException unused) {
            zzk(2001);
        }
    }
}

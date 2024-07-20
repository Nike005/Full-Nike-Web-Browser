package com.google.android.gms.cast;

import android.os.RemoteException;
import com.google.android.gms.cast.Cast;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.internal.cast.zzcn;

final class zzi extends Cast.zza {
    private final /* synthetic */ String val$sessionId;
    private final /* synthetic */ String zzaf;
    private final /* synthetic */ zzaf zzah = null;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzi(Cast.CastApi.zza zza, GoogleApiClient googleApiClient, String str, String str2, zzaf zzaf2) {
        super(googleApiClient);
        this.zzaf = str;
        this.val$sessionId = str2;
    }

    public final /* synthetic */ void doExecute(Api.AnyClient anyClient) throws RemoteException {
        doExecute((zzcn) anyClient);
    }

    public final void zza(zzcn zzcn) throws RemoteException {
        try {
            zzcn.zza(this.zzaf, this.val$sessionId, this.zzah, this);
        } catch (IllegalStateException unused) {
            zzk(2001);
        }
    }
}

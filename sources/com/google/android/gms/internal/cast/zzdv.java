package com.google.android.gms.internal.cast;

import android.os.RemoteException;
import com.google.android.gms.cast.CastRemoteDisplay;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation;

class zzdv extends BaseImplementation.ApiMethodImpl<CastRemoteDisplay.CastRemoteDisplaySessionResult, zzea> {
    final /* synthetic */ zzdq zzxu;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public zzdv(zzdq zzdq, GoogleApiClient googleApiClient) {
        super((Api<?>) zzdq.zzxs, googleApiClient);
        this.zzxu = zzdq;
    }

    /* access modifiers changed from: protected */
    public /* synthetic */ Result createFailedResult(Status status) {
        return new zzdy(status);
    }

    /* renamed from: zza */
    public void doExecute(zzea zzea) throws RemoteException {
    }
}

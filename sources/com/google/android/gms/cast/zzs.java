package com.google.android.gms.cast;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.internal.cast.zzdz;
import com.google.android.gms.internal.cast.zzee;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzs extends TaskApiCall<zzdz, Void> {
    final /* synthetic */ CastRemoteDisplayClient zzbi;

    zzs(CastRemoteDisplayClient castRemoteDisplayClient) {
        this.zzbi = castRemoteDisplayClient;
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ void doExecute(Api.AnyClient anyClient, TaskCompletionSource taskCompletionSource) throws RemoteException {
        ((zzee) ((zzdz) anyClient).getService()).zza(new zzt(this, taskCompletionSource));
    }
}

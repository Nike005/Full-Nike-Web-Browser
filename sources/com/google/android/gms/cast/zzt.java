package com.google.android.gms.cast;

import android.os.RemoteException;
import com.google.android.gms.cast.CastRemoteDisplayClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.TaskUtil;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzt extends CastRemoteDisplayClient.zza {
    private final /* synthetic */ TaskCompletionSource zzbj;
    private final /* synthetic */ zzs zzbm;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzt(zzs zzs, TaskCompletionSource taskCompletionSource) {
        super((zzp) null);
        this.zzbm = zzs;
        this.zzbj = taskCompletionSource;
    }

    public final void onDisconnected() throws RemoteException {
        this.zzbm.zzbi.zzbd.mo6870d("onDisconnected", new Object[0]);
        this.zzbm.zzbi.m19a_();
        TaskUtil.setResultOrApiException(Status.RESULT_SUCCESS, this.zzbj);
    }

    public final void onError(int i) throws RemoteException {
        this.zzbm.zzbi.zzbd.mo6870d("onError: %d", Integer.valueOf(i));
        this.zzbm.zzbi.m19a_();
        TaskUtil.setResultOrApiException(Status.RESULT_INTERNAL_ERROR, this.zzbj);
    }
}

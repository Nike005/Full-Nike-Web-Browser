package com.google.android.gms.internal.cast;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.List;

final class zzcj extends TaskApiCall<zzcl, Void> {
    private final /* synthetic */ List zzny;
    private final /* synthetic */ String[] zzut;
    private final /* synthetic */ String zzuu;

    zzcj(zzch zzch, String[] strArr, String str, List list) {
        this.zzut = strArr;
        this.zzuu = str;
        this.zzny = list;
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ void doExecute(Api.AnyClient anyClient, TaskCompletionSource taskCompletionSource) throws RemoteException {
        ((zzdd) ((zzcl) anyClient).getService()).zza(new zzck(this, taskCompletionSource), this.zzut, this.zzuu, this.zzny);
    }
}

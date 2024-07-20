package com.google.android.gms.cast.framework.media;

import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

final class zzau implements ResultCallback<Status> {
    private final long zzga;
    private final /* synthetic */ RemoteMediaClient.zza zzob;

    zzau(RemoteMediaClient.zza zza, long j) {
        this.zzob = zza;
        this.zzga = j;
    }

    public final /* synthetic */ void onResult(Result result) {
        Status status = (Status) result;
        if (!status.isSuccess()) {
            RemoteMediaClient.this.zzeu.zza(this.zzga, status.getStatusCode());
        }
    }
}

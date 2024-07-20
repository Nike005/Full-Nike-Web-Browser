package com.google.android.gms.cast;

import com.google.android.gms.cast.RemoteMediaPlayer;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

final class zzbm implements ResultCallback<Status> {
    private final long zzga;
    private final /* synthetic */ RemoteMediaPlayer.zza zzgb;

    zzbm(RemoteMediaPlayer.zza zza, long j) {
        this.zzgb = zza;
        this.zzga = j;
    }

    public final /* synthetic */ void onResult(Result result) {
        Status status = (Status) result;
        if (!status.isSuccess()) {
            RemoteMediaPlayer.this.zzeu.zza(this.zzga, status.getStatusCode());
        }
    }
}

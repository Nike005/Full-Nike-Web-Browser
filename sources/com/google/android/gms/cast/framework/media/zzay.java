package com.google.android.gms.cast.framework.media;

import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import java.util.Set;
import java.util.TimerTask;

final class zzay extends TimerTask {
    private final /* synthetic */ RemoteMediaClient zzod;
    private final /* synthetic */ RemoteMediaClient.zze zzoj;

    zzay(RemoteMediaClient.zze zze, RemoteMediaClient remoteMediaClient) {
        this.zzoj = zze;
        this.zzod = remoteMediaClient;
    }

    public final void run() {
        RemoteMediaClient.this.zza((Set<RemoteMediaClient.ProgressListener>) this.zzoj.zzof);
        RemoteMediaClient.this.handler.postDelayed(this, this.zzoj.zzog);
    }
}

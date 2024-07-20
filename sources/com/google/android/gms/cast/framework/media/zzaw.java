package com.google.android.gms.cast.framework.media;

import android.util.Log;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.cast.zzdm;
import org.json.JSONObject;

final class zzaw implements zzdm {
    private final /* synthetic */ RemoteMediaClient zzod;
    private final /* synthetic */ RemoteMediaClient.zzc zzoe;

    zzaw(RemoteMediaClient.zzc zzc, RemoteMediaClient remoteMediaClient) {
        this.zzoe = zzc;
        this.zzod = remoteMediaClient;
    }

    public final void zza(long j, int i, Object obj) {
        try {
            this.zzoe.setResult(new RemoteMediaClient.zzd(new Status(i), obj instanceof JSONObject ? (JSONObject) obj : null));
        } catch (IllegalStateException e) {
            Log.e("RemoteMediaClient", "Result already set when calling onRequestCompleted", e);
        }
    }

    public final void zzb(long j) {
        try {
            this.zzoe.setResult((RemoteMediaClient.MediaChannelResult) this.zzoe.createFailedResult(new Status(2103)));
        } catch (IllegalStateException e) {
            Log.e("RemoteMediaClient", "Result already set when calling onRequestReplaced", e);
        }
    }
}

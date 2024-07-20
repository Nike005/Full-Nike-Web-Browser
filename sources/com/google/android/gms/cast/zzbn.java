package com.google.android.gms.cast;

import android.util.Log;
import com.google.android.gms.cast.RemoteMediaPlayer;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.cast.zzdm;
import org.json.JSONObject;

final class zzbn implements zzdm {
    private final /* synthetic */ RemoteMediaPlayer.zzb zzgd;

    zzbn(RemoteMediaPlayer.zzb zzb) {
        this.zzgd = zzb;
    }

    public final void zza(long j, int i, Object obj) {
        try {
            this.zzgd.setResult(new RemoteMediaPlayer.zzc(new Status(i), obj instanceof JSONObject ? (JSONObject) obj : null));
        } catch (IllegalStateException e) {
            Log.e("RemoteMediaPlayer", "Result already set when calling onRequestCompleted", e);
        }
    }

    public final void zzb(long j) {
        try {
            this.zzgd.setResult((RemoteMediaPlayer.MediaChannelResult) this.zzgd.createFailedResult(new Status(2103)));
        } catch (IllegalStateException e) {
            Log.e("RemoteMediaPlayer", "Result already set when calling onRequestReplaced", e);
        }
    }
}

package com.google.android.gms.cast;

import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.cast.MediaLoadOptions;
import com.google.android.gms.cast.RemoteMediaPlayer;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.cast.zzcn;
import org.json.JSONObject;

final class zzba extends RemoteMediaPlayer.zzb {
    private final /* synthetic */ RemoteMediaPlayer zzfa;
    private final /* synthetic */ GoogleApiClient zzfb;
    private final /* synthetic */ long zzfh;
    private final /* synthetic */ JSONObject zzfi;
    private final /* synthetic */ boolean zzfp;
    private final /* synthetic */ long[] zzfq;
    private final /* synthetic */ MediaInfo zzfr;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzba(RemoteMediaPlayer remoteMediaPlayer, GoogleApiClient googleApiClient, GoogleApiClient googleApiClient2, boolean z, long j, long[] jArr, JSONObject jSONObject, MediaInfo mediaInfo) {
        super(googleApiClient);
        this.zzfa = remoteMediaPlayer;
        this.zzfb = googleApiClient2;
        this.zzfp = z;
        this.zzfh = j;
        this.zzfq = jArr;
        this.zzfi = jSONObject;
        this.zzfr = mediaInfo;
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ void doExecute(Api.AnyClient anyClient) throws RemoteException {
        doExecute((zzcn) anyClient);
    }

    /* access modifiers changed from: protected */
    public final void zza(zzcn zzcn) {
        RemoteMediaPlayer.zza zzf;
        synchronized (this.zzfa.lock) {
            this.zzfa.zzev.zza(this.zzfb);
            try {
                this.zzfa.zzeu.zza(this.zzgc, this.zzfr, new MediaLoadOptions.Builder().setAutoplay(this.zzfp).setPlayPosition(this.zzfh).setActiveTrackIds(this.zzfq).setCustomData(this.zzfi).build());
                zzf = this.zzfa.zzev;
            } catch (IllegalStateException e) {
                try {
                    Log.e("RemoteMediaPlayer", "load - channel error", e);
                    setResult((RemoteMediaPlayer.MediaChannelResult) createFailedResult(new Status(2100)));
                    zzf = this.zzfa.zzev;
                } catch (Throwable th) {
                    this.zzfa.zzev.zza((GoogleApiClient) null);
                    throw th;
                }
            }
            zzf.zza((GoogleApiClient) null);
        }
    }
}

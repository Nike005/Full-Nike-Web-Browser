package com.google.android.gms.cast;

import android.os.RemoteException;
import com.google.android.gms.cast.RemoteMediaPlayer;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.internal.cast.zzcn;

final class zzbl extends RemoteMediaPlayer.zzb {
    private final /* synthetic */ RemoteMediaPlayer zzfa;
    private final /* synthetic */ GoogleApiClient zzfb;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzbl(RemoteMediaPlayer remoteMediaPlayer, GoogleApiClient googleApiClient, GoogleApiClient googleApiClient2) {
        super(googleApiClient);
        this.zzfa = remoteMediaPlayer;
        this.zzfb = googleApiClient2;
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ void doExecute(Api.AnyClient anyClient) throws RemoteException {
        doExecute((zzcn) anyClient);
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Can't wrap try/catch for region: R(4:11|12|13|14) */
    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0028, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:?, code lost:
        setResult((com.google.android.gms.cast.RemoteMediaPlayer.MediaChannelResult) createFailedResult(new com.google.android.gms.common.api.Status(2100)));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:?, code lost:
        r1 = r3.zzfa.zzev;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0043, code lost:
        r3.zzfa.zzev.zza((com.google.android.gms.common.api.GoogleApiClient) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x004c, code lost:
        throw r1;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x002a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zza(com.google.android.gms.internal.cast.zzcn r4) {
        /*
            r3 = this;
            com.google.android.gms.cast.RemoteMediaPlayer r4 = r3.zzfa
            java.lang.Object r4 = r4.lock
            monitor-enter(r4)
            com.google.android.gms.cast.RemoteMediaPlayer r0 = r3.zzfa     // Catch:{ all -> 0x004d }
            com.google.android.gms.cast.RemoteMediaPlayer$zza r0 = r0.zzev     // Catch:{ all -> 0x004d }
            com.google.android.gms.common.api.GoogleApiClient r1 = r3.zzfb     // Catch:{ all -> 0x004d }
            r0.zza(r1)     // Catch:{ all -> 0x004d }
            r0 = 0
            com.google.android.gms.cast.RemoteMediaPlayer r1 = r3.zzfa     // Catch:{ IllegalStateException -> 0x002a }
            com.google.android.gms.internal.cast.zzdh r1 = r1.zzeu     // Catch:{ IllegalStateException -> 0x002a }
            com.google.android.gms.internal.cast.zzdm r2 = r3.zzgc     // Catch:{ IllegalStateException -> 0x002a }
            r1.zzb((com.google.android.gms.internal.cast.zzdm) r2)     // Catch:{ IllegalStateException -> 0x002a }
            com.google.android.gms.cast.RemoteMediaPlayer r1 = r3.zzfa     // Catch:{ all -> 0x004d }
            com.google.android.gms.cast.RemoteMediaPlayer$zza r1 = r1.zzev     // Catch:{ all -> 0x004d }
        L_0x0024:
            r1.zza(r0)     // Catch:{ all -> 0x004d }
            goto L_0x0041
        L_0x0028:
            r1 = move-exception
            goto L_0x0043
        L_0x002a:
            com.google.android.gms.common.api.Status r1 = new com.google.android.gms.common.api.Status     // Catch:{ all -> 0x0028 }
            r2 = 2100(0x834, float:2.943E-42)
            r1.<init>(r2)     // Catch:{ all -> 0x0028 }
            com.google.android.gms.common.api.Result r1 = r3.createFailedResult(r1)     // Catch:{ all -> 0x0028 }
            com.google.android.gms.cast.RemoteMediaPlayer$MediaChannelResult r1 = (com.google.android.gms.cast.RemoteMediaPlayer.MediaChannelResult) r1     // Catch:{ all -> 0x0028 }
            r3.setResult(r1)     // Catch:{ all -> 0x0028 }
            com.google.android.gms.cast.RemoteMediaPlayer r1 = r3.zzfa     // Catch:{ all -> 0x004d }
            com.google.android.gms.cast.RemoteMediaPlayer$zza r1 = r1.zzev     // Catch:{ all -> 0x004d }
            goto L_0x0024
        L_0x0041:
            monitor-exit(r4)     // Catch:{ all -> 0x004d }
            return
        L_0x0043:
            com.google.android.gms.cast.RemoteMediaPlayer r2 = r3.zzfa     // Catch:{ all -> 0x004d }
            com.google.android.gms.cast.RemoteMediaPlayer$zza r2 = r2.zzev     // Catch:{ all -> 0x004d }
            r2.zza(r0)     // Catch:{ all -> 0x004d }
            throw r1     // Catch:{ all -> 0x004d }
        L_0x004d:
            r0 = move-exception
            monitor-exit(r4)     // Catch:{ all -> 0x004d }
            goto L_0x0051
        L_0x0050:
            throw r0
        L_0x0051:
            goto L_0x0050
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.cast.zzbl.zza(com.google.android.gms.internal.cast.zzcn):void");
    }
}

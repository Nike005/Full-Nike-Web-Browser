package com.google.android.gms.cast.framework.media;

import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.common.api.GoogleApiClient;

final class zzt extends RemoteMediaClient.zzc {
    private final /* synthetic */ RemoteMediaClient zzns;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzt(RemoteMediaClient remoteMediaClient, GoogleApiClient googleApiClient) {
        super(remoteMediaClient, googleApiClient);
        this.zzns = remoteMediaClient;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x0015 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzb(com.google.android.gms.internal.cast.zzcn r3) {
        /*
            r2 = this;
            com.google.android.gms.cast.framework.media.RemoteMediaClient r3 = r2.zzns
            java.lang.Object r3 = r3.lock
            monitor-enter(r3)
            com.google.android.gms.cast.framework.media.RemoteMediaClient r0 = r2.zzns     // Catch:{ IllegalStateException -> 0x0015 }
            com.google.android.gms.internal.cast.zzdh r0 = r0.zzeu     // Catch:{ IllegalStateException -> 0x0015 }
            com.google.android.gms.internal.cast.zzdm r1 = r2.zzgc     // Catch:{ IllegalStateException -> 0x0015 }
            r0.zzb((com.google.android.gms.internal.cast.zzdm) r1)     // Catch:{ IllegalStateException -> 0x0015 }
            goto L_0x0025
        L_0x0013:
            r0 = move-exception
            goto L_0x0027
        L_0x0015:
            com.google.android.gms.common.api.Status r0 = new com.google.android.gms.common.api.Status     // Catch:{ all -> 0x0013 }
            r1 = 2100(0x834, float:2.943E-42)
            r0.<init>(r1)     // Catch:{ all -> 0x0013 }
            com.google.android.gms.common.api.Result r0 = r2.createFailedResult(r0)     // Catch:{ all -> 0x0013 }
            com.google.android.gms.cast.framework.media.RemoteMediaClient$MediaChannelResult r0 = (com.google.android.gms.cast.framework.media.RemoteMediaClient.MediaChannelResult) r0     // Catch:{ all -> 0x0013 }
            r2.setResult(r0)     // Catch:{ all -> 0x0013 }
        L_0x0025:
            monitor-exit(r3)     // Catch:{ all -> 0x0013 }
            return
        L_0x0027:
            monitor-exit(r3)     // Catch:{ all -> 0x0013 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.cast.framework.media.zzt.zzb(com.google.android.gms.internal.cast.zzcn):void");
    }
}

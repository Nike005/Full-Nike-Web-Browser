package com.google.android.gms.cast.framework.media;

import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.common.api.GoogleApiClient;

final class zzak extends RemoteMediaClient.zzc {
    private final /* synthetic */ RemoteMediaClient zzns;
    private final /* synthetic */ int[] zznu;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzak(RemoteMediaClient remoteMediaClient, GoogleApiClient googleApiClient, boolean z, int[] iArr) {
        super(googleApiClient, true);
        this.zzns = remoteMediaClient;
        this.zznu = iArr;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x0017 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzb(com.google.android.gms.internal.cast.zzcn r4) {
        /*
            r3 = this;
            com.google.android.gms.cast.framework.media.RemoteMediaClient r4 = r3.zzns
            java.lang.Object r4 = r4.lock
            monitor-enter(r4)
            com.google.android.gms.cast.framework.media.RemoteMediaClient r0 = r3.zzns     // Catch:{ zzdk | IllegalStateException -> 0x0017 }
            com.google.android.gms.internal.cast.zzdh r0 = r0.zzeu     // Catch:{ zzdk | IllegalStateException -> 0x0017 }
            com.google.android.gms.internal.cast.zzdm r1 = r3.zzgc     // Catch:{ zzdk | IllegalStateException -> 0x0017 }
            int[] r2 = r3.zznu     // Catch:{ zzdk | IllegalStateException -> 0x0017 }
            r0.zza((com.google.android.gms.internal.cast.zzdm) r1, (int[]) r2)     // Catch:{ zzdk | IllegalStateException -> 0x0017 }
            goto L_0x0027
        L_0x0015:
            r0 = move-exception
            goto L_0x0029
        L_0x0017:
            com.google.android.gms.common.api.Status r0 = new com.google.android.gms.common.api.Status     // Catch:{ all -> 0x0015 }
            r1 = 2100(0x834, float:2.943E-42)
            r0.<init>(r1)     // Catch:{ all -> 0x0015 }
            com.google.android.gms.common.api.Result r0 = r3.createFailedResult(r0)     // Catch:{ all -> 0x0015 }
            com.google.android.gms.cast.framework.media.RemoteMediaClient$MediaChannelResult r0 = (com.google.android.gms.cast.framework.media.RemoteMediaClient.MediaChannelResult) r0     // Catch:{ all -> 0x0015 }
            r3.setResult(r0)     // Catch:{ all -> 0x0015 }
        L_0x0027:
            monitor-exit(r4)     // Catch:{ all -> 0x0015 }
            return
        L_0x0029:
            monitor-exit(r4)     // Catch:{ all -> 0x0015 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.cast.framework.media.zzak.zzb(com.google.android.gms.internal.cast.zzcn):void");
    }
}

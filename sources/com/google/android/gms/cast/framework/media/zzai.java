package com.google.android.gms.cast.framework.media;

import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.common.api.GoogleApiClient;
import org.json.JSONObject;

final class zzai extends RemoteMediaClient.zzc {
    private final /* synthetic */ JSONObject zzfi;
    private final /* synthetic */ int zzfs;
    private final /* synthetic */ int zzft;
    private final /* synthetic */ RemoteMediaClient zzns;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzai(RemoteMediaClient remoteMediaClient, GoogleApiClient googleApiClient, int i, int i2, JSONObject jSONObject) {
        super(remoteMediaClient, googleApiClient);
        this.zzns = remoteMediaClient;
        this.zzfs = i;
        this.zzft = i2;
        this.zzfi = jSONObject;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Can't wrap try/catch for region: R(8:18|(1:20)(1:21)|22|(1:24)(1:25)|26|27|28|29) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:28:0x0090 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzb(com.google.android.gms.internal.cast.zzcn r8) {
        /*
            r7 = this;
            com.google.android.gms.cast.framework.media.RemoteMediaClient r8 = r7.zzns
            java.lang.Object r8 = r8.lock
            monitor-enter(r8)
            com.google.android.gms.cast.framework.media.RemoteMediaClient r0 = r7.zzns     // Catch:{ all -> 0x00a2 }
            int r1 = r7.zzfs     // Catch:{ all -> 0x00a2 }
            int r0 = r0.zzc((int) r1)     // Catch:{ all -> 0x00a2 }
            r1 = -1
            r2 = 0
            if (r0 != r1) goto L_0x0023
            com.google.android.gms.common.api.Status r0 = new com.google.android.gms.common.api.Status     // Catch:{ all -> 0x00a2 }
            r0.<init>(r2)     // Catch:{ all -> 0x00a2 }
            com.google.android.gms.common.api.Result r0 = r7.createFailedResult(r0)     // Catch:{ all -> 0x00a2 }
            com.google.android.gms.cast.framework.media.RemoteMediaClient$MediaChannelResult r0 = (com.google.android.gms.cast.framework.media.RemoteMediaClient.MediaChannelResult) r0     // Catch:{ all -> 0x00a2 }
            r7.setResult(r0)     // Catch:{ all -> 0x00a2 }
            monitor-exit(r8)     // Catch:{ all -> 0x00a2 }
            return
        L_0x0023:
            int r1 = r7.zzft     // Catch:{ all -> 0x00a2 }
            r3 = 1
            if (r1 >= 0) goto L_0x004c
            com.google.android.gms.common.api.Status r0 = new com.google.android.gms.common.api.Status     // Catch:{ all -> 0x00a2 }
            r1 = 2001(0x7d1, float:2.804E-42)
            java.util.Locale r4 = java.util.Locale.ROOT     // Catch:{ all -> 0x00a2 }
            java.lang.String r5 = "Invalid request: Invalid newIndex %d."
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch:{ all -> 0x00a2 }
            int r6 = r7.zzft     // Catch:{ all -> 0x00a2 }
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)     // Catch:{ all -> 0x00a2 }
            r3[r2] = r6     // Catch:{ all -> 0x00a2 }
            java.lang.String r2 = java.lang.String.format(r4, r5, r3)     // Catch:{ all -> 0x00a2 }
            r0.<init>(r1, r2)     // Catch:{ all -> 0x00a2 }
            com.google.android.gms.common.api.Result r0 = r7.createFailedResult(r0)     // Catch:{ all -> 0x00a2 }
            com.google.android.gms.cast.framework.media.RemoteMediaClient$MediaChannelResult r0 = (com.google.android.gms.cast.framework.media.RemoteMediaClient.MediaChannelResult) r0     // Catch:{ all -> 0x00a2 }
            r7.setResult(r0)     // Catch:{ all -> 0x00a2 }
            monitor-exit(r8)     // Catch:{ all -> 0x00a2 }
            return
        L_0x004c:
            int r1 = r7.zzft     // Catch:{ all -> 0x00a2 }
            if (r0 != r1) goto L_0x0060
            com.google.android.gms.common.api.Status r0 = new com.google.android.gms.common.api.Status     // Catch:{ all -> 0x00a2 }
            r0.<init>(r2)     // Catch:{ all -> 0x00a2 }
            com.google.android.gms.common.api.Result r0 = r7.createFailedResult(r0)     // Catch:{ all -> 0x00a2 }
            com.google.android.gms.cast.framework.media.RemoteMediaClient$MediaChannelResult r0 = (com.google.android.gms.cast.framework.media.RemoteMediaClient.MediaChannelResult) r0     // Catch:{ all -> 0x00a2 }
            r7.setResult(r0)     // Catch:{ all -> 0x00a2 }
            monitor-exit(r8)     // Catch:{ all -> 0x00a2 }
            return
        L_0x0060:
            int r1 = r7.zzft     // Catch:{ all -> 0x00a2 }
            if (r1 <= r0) goto L_0x0068
            int r0 = r7.zzft     // Catch:{ all -> 0x00a2 }
            int r0 = r0 + r3
            goto L_0x006a
        L_0x0068:
            int r0 = r7.zzft     // Catch:{ all -> 0x00a2 }
        L_0x006a:
            com.google.android.gms.cast.framework.media.RemoteMediaClient r1 = r7.zzns     // Catch:{ all -> 0x00a2 }
            com.google.android.gms.cast.MediaStatus r1 = r1.getMediaStatus()     // Catch:{ all -> 0x00a2 }
            com.google.android.gms.cast.MediaQueueItem r0 = r1.getQueueItem(r0)     // Catch:{ all -> 0x00a2 }
            if (r0 == 0) goto L_0x007b
            int r0 = r0.getItemId()     // Catch:{ all -> 0x00a2 }
            goto L_0x007c
        L_0x007b:
            r0 = 0
        L_0x007c:
            com.google.android.gms.cast.framework.media.RemoteMediaClient r1 = r7.zzns     // Catch:{ zzdk | IllegalStateException -> 0x0090 }
            com.google.android.gms.internal.cast.zzdh r1 = r1.zzeu     // Catch:{ zzdk | IllegalStateException -> 0x0090 }
            com.google.android.gms.internal.cast.zzdm r4 = r7.zzgc     // Catch:{ zzdk | IllegalStateException -> 0x0090 }
            int[] r3 = new int[r3]     // Catch:{ zzdk | IllegalStateException -> 0x0090 }
            int r5 = r7.zzfs     // Catch:{ zzdk | IllegalStateException -> 0x0090 }
            r3[r2] = r5     // Catch:{ zzdk | IllegalStateException -> 0x0090 }
            org.json.JSONObject r2 = r7.zzfi     // Catch:{ zzdk | IllegalStateException -> 0x0090 }
            r1.zza((com.google.android.gms.internal.cast.zzdm) r4, (int[]) r3, (int) r0, (org.json.JSONObject) r2)     // Catch:{ zzdk | IllegalStateException -> 0x0090 }
            goto L_0x00a0
        L_0x0090:
            com.google.android.gms.common.api.Status r0 = new com.google.android.gms.common.api.Status     // Catch:{ all -> 0x00a2 }
            r1 = 2100(0x834, float:2.943E-42)
            r0.<init>(r1)     // Catch:{ all -> 0x00a2 }
            com.google.android.gms.common.api.Result r0 = r7.createFailedResult(r0)     // Catch:{ all -> 0x00a2 }
            com.google.android.gms.cast.framework.media.RemoteMediaClient$MediaChannelResult r0 = (com.google.android.gms.cast.framework.media.RemoteMediaClient.MediaChannelResult) r0     // Catch:{ all -> 0x00a2 }
            r7.setResult(r0)     // Catch:{ all -> 0x00a2 }
        L_0x00a0:
            monitor-exit(r8)     // Catch:{ all -> 0x00a2 }
            return
        L_0x00a2:
            r0 = move-exception
            monitor-exit(r8)     // Catch:{ all -> 0x00a2 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.cast.framework.media.zzai.zzb(com.google.android.gms.internal.cast.zzcn):void");
    }
}

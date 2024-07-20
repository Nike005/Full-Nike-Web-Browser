package com.google.android.gms.cast.framework.media;

import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.common.api.GoogleApiClient;
import org.json.JSONObject;

final class zzag extends RemoteMediaClient.zzc {
    private final /* synthetic */ JSONObject zzfi;
    private final /* synthetic */ int zzfs;
    private final /* synthetic */ RemoteMediaClient zzns;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzag(RemoteMediaClient remoteMediaClient, GoogleApiClient googleApiClient, int i, JSONObject jSONObject) {
        super(remoteMediaClient, googleApiClient);
        this.zzns = remoteMediaClient;
        this.zzfs = i;
        this.zzfi = jSONObject;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Can't wrap try/catch for region: R(4:8|9|10|11) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:10:0x0038 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzb(com.google.android.gms.internal.cast.zzcn r6) {
        /*
            r5 = this;
            com.google.android.gms.cast.framework.media.RemoteMediaClient r6 = r5.zzns
            java.lang.Object r6 = r6.lock
            monitor-enter(r6)
            com.google.android.gms.cast.framework.media.RemoteMediaClient r0 = r5.zzns     // Catch:{ all -> 0x004a }
            int r1 = r5.zzfs     // Catch:{ all -> 0x004a }
            int r0 = r0.zzc((int) r1)     // Catch:{ all -> 0x004a }
            r1 = -1
            r2 = 0
            if (r0 != r1) goto L_0x0023
            com.google.android.gms.common.api.Status r0 = new com.google.android.gms.common.api.Status     // Catch:{ all -> 0x004a }
            r0.<init>(r2)     // Catch:{ all -> 0x004a }
            com.google.android.gms.common.api.Result r0 = r5.createFailedResult(r0)     // Catch:{ all -> 0x004a }
            com.google.android.gms.cast.framework.media.RemoteMediaClient$MediaChannelResult r0 = (com.google.android.gms.cast.framework.media.RemoteMediaClient.MediaChannelResult) r0     // Catch:{ all -> 0x004a }
            r5.setResult(r0)     // Catch:{ all -> 0x004a }
            monitor-exit(r6)     // Catch:{ all -> 0x004a }
            return
        L_0x0023:
            com.google.android.gms.cast.framework.media.RemoteMediaClient r0 = r5.zzns     // Catch:{ zzdk | IllegalStateException -> 0x0038 }
            com.google.android.gms.internal.cast.zzdh r0 = r0.zzeu     // Catch:{ zzdk | IllegalStateException -> 0x0038 }
            com.google.android.gms.internal.cast.zzdm r1 = r5.zzgc     // Catch:{ zzdk | IllegalStateException -> 0x0038 }
            r3 = 1
            int[] r3 = new int[r3]     // Catch:{ zzdk | IllegalStateException -> 0x0038 }
            int r4 = r5.zzfs     // Catch:{ zzdk | IllegalStateException -> 0x0038 }
            r3[r2] = r4     // Catch:{ zzdk | IllegalStateException -> 0x0038 }
            org.json.JSONObject r2 = r5.zzfi     // Catch:{ zzdk | IllegalStateException -> 0x0038 }
            r0.zza((com.google.android.gms.internal.cast.zzdm) r1, (int[]) r3, (org.json.JSONObject) r2)     // Catch:{ zzdk | IllegalStateException -> 0x0038 }
            goto L_0x0048
        L_0x0038:
            com.google.android.gms.common.api.Status r0 = new com.google.android.gms.common.api.Status     // Catch:{ all -> 0x004a }
            r1 = 2100(0x834, float:2.943E-42)
            r0.<init>(r1)     // Catch:{ all -> 0x004a }
            com.google.android.gms.common.api.Result r0 = r5.createFailedResult(r0)     // Catch:{ all -> 0x004a }
            com.google.android.gms.cast.framework.media.RemoteMediaClient$MediaChannelResult r0 = (com.google.android.gms.cast.framework.media.RemoteMediaClient.MediaChannelResult) r0     // Catch:{ all -> 0x004a }
            r5.setResult(r0)     // Catch:{ all -> 0x004a }
        L_0x0048:
            monitor-exit(r6)     // Catch:{ all -> 0x004a }
            return
        L_0x004a:
            r0 = move-exception
            monitor-exit(r6)     // Catch:{ all -> 0x004a }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.cast.framework.media.zzag.zzb(com.google.android.gms.internal.cast.zzcn):void");
    }
}

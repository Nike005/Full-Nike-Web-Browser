package com.google.android.gms.cast.framework.media;

import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.common.api.GoogleApiClient;
import org.json.JSONObject;

final class zzaq extends RemoteMediaClient.zzc {
    private final /* synthetic */ JSONObject zzfi;
    private final /* synthetic */ long zzfu;
    private final /* synthetic */ int zzfv;
    private final /* synthetic */ RemoteMediaClient zzns;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzaq(RemoteMediaClient remoteMediaClient, GoogleApiClient googleApiClient, long j, int i, JSONObject jSONObject) {
        super(remoteMediaClient, googleApiClient);
        this.zzns = remoteMediaClient;
        this.zzfu = j;
        this.zzfv = i;
        this.zzfi = jSONObject;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001b */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzb(com.google.android.gms.internal.cast.zzcn r8) {
        /*
            r7 = this;
            com.google.android.gms.cast.framework.media.RemoteMediaClient r8 = r7.zzns
            java.lang.Object r8 = r8.lock
            monitor-enter(r8)
            com.google.android.gms.cast.framework.media.RemoteMediaClient r0 = r7.zzns     // Catch:{ zzdk | IllegalStateException -> 0x001b }
            com.google.android.gms.internal.cast.zzdh r1 = r0.zzeu     // Catch:{ zzdk | IllegalStateException -> 0x001b }
            com.google.android.gms.internal.cast.zzdm r2 = r7.zzgc     // Catch:{ zzdk | IllegalStateException -> 0x001b }
            long r3 = r7.zzfu     // Catch:{ zzdk | IllegalStateException -> 0x001b }
            int r5 = r7.zzfv     // Catch:{ zzdk | IllegalStateException -> 0x001b }
            org.json.JSONObject r6 = r7.zzfi     // Catch:{ zzdk | IllegalStateException -> 0x001b }
            r1.zza((com.google.android.gms.internal.cast.zzdm) r2, (long) r3, (int) r5, (org.json.JSONObject) r6)     // Catch:{ zzdk | IllegalStateException -> 0x001b }
            goto L_0x002b
        L_0x0019:
            r0 = move-exception
            goto L_0x002d
        L_0x001b:
            com.google.android.gms.common.api.Status r0 = new com.google.android.gms.common.api.Status     // Catch:{ all -> 0x0019 }
            r1 = 2100(0x834, float:2.943E-42)
            r0.<init>(r1)     // Catch:{ all -> 0x0019 }
            com.google.android.gms.common.api.Result r0 = r7.createFailedResult(r0)     // Catch:{ all -> 0x0019 }
            com.google.android.gms.cast.framework.media.RemoteMediaClient$MediaChannelResult r0 = (com.google.android.gms.cast.framework.media.RemoteMediaClient.MediaChannelResult) r0     // Catch:{ all -> 0x0019 }
            r7.setResult(r0)     // Catch:{ all -> 0x0019 }
        L_0x002b:
            monitor-exit(r8)     // Catch:{ all -> 0x0019 }
            return
        L_0x002d:
            monitor-exit(r8)     // Catch:{ all -> 0x0019 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.cast.framework.media.zzaq.zzb(com.google.android.gms.internal.cast.zzcn):void");
    }
}

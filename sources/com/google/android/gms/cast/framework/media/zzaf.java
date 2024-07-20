package com.google.android.gms.cast.framework.media;

import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.common.api.GoogleApiClient;
import org.json.JSONObject;

final class zzaf extends RemoteMediaClient.zzc {
    private final /* synthetic */ int zzfg;
    private final /* synthetic */ JSONObject zzfi;
    private final /* synthetic */ RemoteMediaClient zzns;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzaf(RemoteMediaClient remoteMediaClient, GoogleApiClient googleApiClient, int i, JSONObject jSONObject) {
        super(remoteMediaClient, googleApiClient);
        this.zzns = remoteMediaClient;
        this.zzfg = i;
        this.zzfi = jSONObject;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x0022 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzb(com.google.android.gms.internal.cast.zzcn r11) {
        /*
            r10 = this;
            com.google.android.gms.cast.framework.media.RemoteMediaClient r11 = r10.zzns
            java.lang.Object r11 = r11.lock
            monitor-enter(r11)
            com.google.android.gms.cast.framework.media.RemoteMediaClient r0 = r10.zzns     // Catch:{ zzdk | IllegalStateException -> 0x0022 }
            com.google.android.gms.internal.cast.zzdh r1 = r0.zzeu     // Catch:{ zzdk | IllegalStateException -> 0x0022 }
            com.google.android.gms.internal.cast.zzdm r2 = r10.zzgc     // Catch:{ zzdk | IllegalStateException -> 0x0022 }
            r3 = 0
            r4 = -1
            r6 = 0
            r7 = 0
            int r0 = r10.zzfg     // Catch:{ zzdk | IllegalStateException -> 0x0022 }
            java.lang.Integer r8 = java.lang.Integer.valueOf(r0)     // Catch:{ zzdk | IllegalStateException -> 0x0022 }
            org.json.JSONObject r9 = r10.zzfi     // Catch:{ zzdk | IllegalStateException -> 0x0022 }
            r1.zza((com.google.android.gms.internal.cast.zzdm) r2, (int) r3, (long) r4, (com.google.android.gms.cast.MediaQueueItem[]) r6, (int) r7, (java.lang.Integer) r8, (org.json.JSONObject) r9)     // Catch:{ zzdk | IllegalStateException -> 0x0022 }
            goto L_0x0032
        L_0x0020:
            r0 = move-exception
            goto L_0x0034
        L_0x0022:
            com.google.android.gms.common.api.Status r0 = new com.google.android.gms.common.api.Status     // Catch:{ all -> 0x0020 }
            r1 = 2100(0x834, float:2.943E-42)
            r0.<init>(r1)     // Catch:{ all -> 0x0020 }
            com.google.android.gms.common.api.Result r0 = r10.createFailedResult(r0)     // Catch:{ all -> 0x0020 }
            com.google.android.gms.cast.framework.media.RemoteMediaClient$MediaChannelResult r0 = (com.google.android.gms.cast.framework.media.RemoteMediaClient.MediaChannelResult) r0     // Catch:{ all -> 0x0020 }
            r10.setResult(r0)     // Catch:{ all -> 0x0020 }
        L_0x0032:
            monitor-exit(r11)     // Catch:{ all -> 0x0020 }
            return
        L_0x0034:
            monitor-exit(r11)     // Catch:{ all -> 0x0020 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.cast.framework.media.zzaf.zzb(com.google.android.gms.internal.cast.zzcn):void");
    }
}

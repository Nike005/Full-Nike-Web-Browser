package com.google.android.gms.cast.framework.media;

import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.common.api.GoogleApiClient;
import org.json.JSONObject;

final class zzab extends RemoteMediaClient.zzc {
    private final /* synthetic */ JSONObject zzfi;
    private final /* synthetic */ int zzfk;
    private final /* synthetic */ int[] zzfo;
    private final /* synthetic */ RemoteMediaClient zzns;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzab(RemoteMediaClient remoteMediaClient, GoogleApiClient googleApiClient, int[] iArr, int i, JSONObject jSONObject) {
        super(remoteMediaClient, googleApiClient);
        this.zzns = remoteMediaClient;
        this.zzfo = iArr;
        this.zzfk = i;
        this.zzfi = jSONObject;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001b */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzb(com.google.android.gms.internal.cast.zzcn r6) {
        /*
            r5 = this;
            com.google.android.gms.cast.framework.media.RemoteMediaClient r6 = r5.zzns
            java.lang.Object r6 = r6.lock
            monitor-enter(r6)
            com.google.android.gms.cast.framework.media.RemoteMediaClient r0 = r5.zzns     // Catch:{ zzdk | IllegalStateException -> 0x001b }
            com.google.android.gms.internal.cast.zzdh r0 = r0.zzeu     // Catch:{ zzdk | IllegalStateException -> 0x001b }
            com.google.android.gms.internal.cast.zzdm r1 = r5.zzgc     // Catch:{ zzdk | IllegalStateException -> 0x001b }
            int[] r2 = r5.zzfo     // Catch:{ zzdk | IllegalStateException -> 0x001b }
            int r3 = r5.zzfk     // Catch:{ zzdk | IllegalStateException -> 0x001b }
            org.json.JSONObject r4 = r5.zzfi     // Catch:{ zzdk | IllegalStateException -> 0x001b }
            r0.zza((com.google.android.gms.internal.cast.zzdm) r1, (int[]) r2, (int) r3, (org.json.JSONObject) r4)     // Catch:{ zzdk | IllegalStateException -> 0x001b }
            goto L_0x002b
        L_0x0019:
            r0 = move-exception
            goto L_0x002d
        L_0x001b:
            com.google.android.gms.common.api.Status r0 = new com.google.android.gms.common.api.Status     // Catch:{ all -> 0x0019 }
            r1 = 2100(0x834, float:2.943E-42)
            r0.<init>(r1)     // Catch:{ all -> 0x0019 }
            com.google.android.gms.common.api.Result r0 = r5.createFailedResult(r0)     // Catch:{ all -> 0x0019 }
            com.google.android.gms.cast.framework.media.RemoteMediaClient$MediaChannelResult r0 = (com.google.android.gms.cast.framework.media.RemoteMediaClient.MediaChannelResult) r0     // Catch:{ all -> 0x0019 }
            r5.setResult(r0)     // Catch:{ all -> 0x0019 }
        L_0x002b:
            monitor-exit(r6)     // Catch:{ all -> 0x0019 }
            return
        L_0x002d:
            monitor-exit(r6)     // Catch:{ all -> 0x0019 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.cast.framework.media.zzab.zzb(com.google.android.gms.internal.cast.zzcn):void");
    }
}

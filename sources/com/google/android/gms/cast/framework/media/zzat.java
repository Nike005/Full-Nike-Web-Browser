package com.google.android.gms.cast.framework.media;

import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.common.api.GoogleApiClient;
import org.json.JSONObject;

final class zzat extends RemoteMediaClient.zzc {
    private final /* synthetic */ JSONObject zzfi;
    private final /* synthetic */ RemoteMediaClient zzns;
    private final /* synthetic */ double zzoa;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzat(RemoteMediaClient remoteMediaClient, GoogleApiClient googleApiClient, double d, JSONObject jSONObject) {
        super(remoteMediaClient, googleApiClient);
        this.zzns = remoteMediaClient;
        this.zzoa = d;
        this.zzfi = jSONObject;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x0019 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzb(com.google.android.gms.internal.cast.zzcn r6) {
        /*
            r5 = this;
            com.google.android.gms.cast.framework.media.RemoteMediaClient r6 = r5.zzns
            java.lang.Object r6 = r6.lock
            monitor-enter(r6)
            com.google.android.gms.cast.framework.media.RemoteMediaClient r0 = r5.zzns     // Catch:{ zzdk | IllegalStateException -> 0x0019 }
            com.google.android.gms.internal.cast.zzdh r0 = r0.zzeu     // Catch:{ zzdk | IllegalStateException -> 0x0019 }
            com.google.android.gms.internal.cast.zzdm r1 = r5.zzgc     // Catch:{ zzdk | IllegalStateException -> 0x0019 }
            double r2 = r5.zzoa     // Catch:{ zzdk | IllegalStateException -> 0x0019 }
            org.json.JSONObject r4 = r5.zzfi     // Catch:{ zzdk | IllegalStateException -> 0x0019 }
            r0.zzb(r1, r2, r4)     // Catch:{ zzdk | IllegalStateException -> 0x0019 }
            goto L_0x0029
        L_0x0017:
            r0 = move-exception
            goto L_0x002b
        L_0x0019:
            com.google.android.gms.common.api.Status r0 = new com.google.android.gms.common.api.Status     // Catch:{ all -> 0x0017 }
            r1 = 2100(0x834, float:2.943E-42)
            r0.<init>(r1)     // Catch:{ all -> 0x0017 }
            com.google.android.gms.common.api.Result r0 = r5.createFailedResult(r0)     // Catch:{ all -> 0x0017 }
            com.google.android.gms.cast.framework.media.RemoteMediaClient$MediaChannelResult r0 = (com.google.android.gms.cast.framework.media.RemoteMediaClient.MediaChannelResult) r0     // Catch:{ all -> 0x0017 }
            r5.setResult(r0)     // Catch:{ all -> 0x0017 }
        L_0x0029:
            monitor-exit(r6)     // Catch:{ all -> 0x0017 }
            return
        L_0x002b:
            monitor-exit(r6)     // Catch:{ all -> 0x0017 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.cast.framework.media.zzat.zzb(com.google.android.gms.internal.cast.zzcn):void");
    }
}

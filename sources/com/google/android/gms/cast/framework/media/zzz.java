package com.google.android.gms.cast.framework.media;

import com.google.android.gms.cast.MediaQueueItem;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.common.api.GoogleApiClient;
import org.json.JSONObject;

final class zzz extends RemoteMediaClient.zzc {
    private final /* synthetic */ JSONObject zzfi;
    private final /* synthetic */ MediaQueueItem[] zzfm;
    private final /* synthetic */ RemoteMediaClient zzns;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzz(RemoteMediaClient remoteMediaClient, GoogleApiClient googleApiClient, MediaQueueItem[] mediaQueueItemArr, JSONObject jSONObject) {
        super(remoteMediaClient, googleApiClient);
        this.zzns = remoteMediaClient;
        this.zzfm = mediaQueueItemArr;
        this.zzfi = jSONObject;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001e */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzb(com.google.android.gms.internal.cast.zzcn r11) {
        /*
            r10 = this;
            com.google.android.gms.cast.framework.media.RemoteMediaClient r11 = r10.zzns
            java.lang.Object r11 = r11.lock
            monitor-enter(r11)
            com.google.android.gms.cast.framework.media.RemoteMediaClient r0 = r10.zzns     // Catch:{ zzdk | IllegalStateException -> 0x001e }
            com.google.android.gms.internal.cast.zzdh r1 = r0.zzeu     // Catch:{ zzdk | IllegalStateException -> 0x001e }
            com.google.android.gms.internal.cast.zzdm r2 = r10.zzgc     // Catch:{ zzdk | IllegalStateException -> 0x001e }
            r3 = 0
            r4 = -1
            com.google.android.gms.cast.MediaQueueItem[] r6 = r10.zzfm     // Catch:{ zzdk | IllegalStateException -> 0x001e }
            r7 = 0
            r8 = 0
            org.json.JSONObject r9 = r10.zzfi     // Catch:{ zzdk | IllegalStateException -> 0x001e }
            r1.zza((com.google.android.gms.internal.cast.zzdm) r2, (int) r3, (long) r4, (com.google.android.gms.cast.MediaQueueItem[]) r6, (int) r7, (java.lang.Integer) r8, (org.json.JSONObject) r9)     // Catch:{ zzdk | IllegalStateException -> 0x001e }
            goto L_0x002e
        L_0x001c:
            r0 = move-exception
            goto L_0x0030
        L_0x001e:
            com.google.android.gms.common.api.Status r0 = new com.google.android.gms.common.api.Status     // Catch:{ all -> 0x001c }
            r1 = 2100(0x834, float:2.943E-42)
            r0.<init>(r1)     // Catch:{ all -> 0x001c }
            com.google.android.gms.common.api.Result r0 = r10.createFailedResult(r0)     // Catch:{ all -> 0x001c }
            com.google.android.gms.cast.framework.media.RemoteMediaClient$MediaChannelResult r0 = (com.google.android.gms.cast.framework.media.RemoteMediaClient.MediaChannelResult) r0     // Catch:{ all -> 0x001c }
            r10.setResult(r0)     // Catch:{ all -> 0x001c }
        L_0x002e:
            monitor-exit(r11)     // Catch:{ all -> 0x001c }
            return
        L_0x0030:
            monitor-exit(r11)     // Catch:{ all -> 0x001c }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.cast.framework.media.zzz.zzb(com.google.android.gms.internal.cast.zzcn):void");
    }
}
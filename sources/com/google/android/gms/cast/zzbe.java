package com.google.android.gms.cast;

import android.os.RemoteException;
import com.google.android.gms.cast.RemoteMediaPlayer;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.internal.cast.zzcn;
import org.json.JSONObject;

final class zzbe extends RemoteMediaPlayer.zzb {
    private final /* synthetic */ RemoteMediaPlayer zzfa;
    private final /* synthetic */ GoogleApiClient zzfb;
    private final /* synthetic */ JSONObject zzfi;
    private final /* synthetic */ int zzfs;
    private final /* synthetic */ int zzft;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzbe(RemoteMediaPlayer remoteMediaPlayer, GoogleApiClient googleApiClient, int i, int i2, GoogleApiClient googleApiClient2, JSONObject jSONObject) {
        super(googleApiClient);
        this.zzfa = remoteMediaPlayer;
        this.zzfs = i;
        this.zzft = i2;
        this.zzfb = googleApiClient2;
        this.zzfi = jSONObject;
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ void doExecute(Api.AnyClient anyClient) throws RemoteException {
        doExecute((zzcn) anyClient);
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Can't wrap try/catch for region: R(4:34|35|36|37) */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x00a5, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:?, code lost:
        setResult((com.google.android.gms.cast.RemoteMediaPlayer.MediaChannelResult) createFailedResult(new com.google.android.gms.common.api.Status(2100)));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:?, code lost:
        r0 = r7.zzfa.zzev;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x00c0, code lost:
        r7.zzfa.zzev.zza((com.google.android.gms.common.api.GoogleApiClient) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x00c9, code lost:
        throw r0;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:34:0x00a7 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zza(com.google.android.gms.internal.cast.zzcn r8) {
        /*
            r7 = this;
            com.google.android.gms.cast.RemoteMediaPlayer r8 = r7.zzfa
            java.lang.Object r8 = r8.lock
            monitor-enter(r8)
            com.google.android.gms.cast.RemoteMediaPlayer r0 = r7.zzfa     // Catch:{ all -> 0x00ca }
            int r1 = r7.zzfs     // Catch:{ all -> 0x00ca }
            int r0 = r0.zzc((int) r1)     // Catch:{ all -> 0x00ca }
            r1 = -1
            r2 = 0
            if (r0 != r1) goto L_0x0023
            com.google.android.gms.common.api.Status r0 = new com.google.android.gms.common.api.Status     // Catch:{ all -> 0x00ca }
            r0.<init>(r2)     // Catch:{ all -> 0x00ca }
            com.google.android.gms.common.api.Result r0 = r7.createFailedResult(r0)     // Catch:{ all -> 0x00ca }
            com.google.android.gms.cast.RemoteMediaPlayer$MediaChannelResult r0 = (com.google.android.gms.cast.RemoteMediaPlayer.MediaChannelResult) r0     // Catch:{ all -> 0x00ca }
            r7.setResult(r0)     // Catch:{ all -> 0x00ca }
            monitor-exit(r8)     // Catch:{ all -> 0x00ca }
            return
        L_0x0023:
            int r1 = r7.zzft     // Catch:{ all -> 0x00ca }
            r3 = 1
            if (r1 >= 0) goto L_0x004c
            com.google.android.gms.common.api.Status r0 = new com.google.android.gms.common.api.Status     // Catch:{ all -> 0x00ca }
            r1 = 2001(0x7d1, float:2.804E-42)
            java.util.Locale r4 = java.util.Locale.ROOT     // Catch:{ all -> 0x00ca }
            java.lang.String r5 = "Invalid request: Invalid newIndex %d."
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch:{ all -> 0x00ca }
            int r6 = r7.zzft     // Catch:{ all -> 0x00ca }
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)     // Catch:{ all -> 0x00ca }
            r3[r2] = r6     // Catch:{ all -> 0x00ca }
            java.lang.String r2 = java.lang.String.format(r4, r5, r3)     // Catch:{ all -> 0x00ca }
            r0.<init>(r1, r2)     // Catch:{ all -> 0x00ca }
            com.google.android.gms.common.api.Result r0 = r7.createFailedResult(r0)     // Catch:{ all -> 0x00ca }
            com.google.android.gms.cast.RemoteMediaPlayer$MediaChannelResult r0 = (com.google.android.gms.cast.RemoteMediaPlayer.MediaChannelResult) r0     // Catch:{ all -> 0x00ca }
            r7.setResult(r0)     // Catch:{ all -> 0x00ca }
            monitor-exit(r8)     // Catch:{ all -> 0x00ca }
            return
        L_0x004c:
            int r1 = r7.zzft     // Catch:{ all -> 0x00ca }
            if (r0 != r1) goto L_0x0060
            com.google.android.gms.common.api.Status r0 = new com.google.android.gms.common.api.Status     // Catch:{ all -> 0x00ca }
            r0.<init>(r2)     // Catch:{ all -> 0x00ca }
            com.google.android.gms.common.api.Result r0 = r7.createFailedResult(r0)     // Catch:{ all -> 0x00ca }
            com.google.android.gms.cast.RemoteMediaPlayer$MediaChannelResult r0 = (com.google.android.gms.cast.RemoteMediaPlayer.MediaChannelResult) r0     // Catch:{ all -> 0x00ca }
            r7.setResult(r0)     // Catch:{ all -> 0x00ca }
            monitor-exit(r8)     // Catch:{ all -> 0x00ca }
            return
        L_0x0060:
            int r1 = r7.zzft     // Catch:{ all -> 0x00ca }
            if (r1 <= r0) goto L_0x0068
            int r0 = r7.zzft     // Catch:{ all -> 0x00ca }
            int r0 = r0 + r3
            goto L_0x006a
        L_0x0068:
            int r0 = r7.zzft     // Catch:{ all -> 0x00ca }
        L_0x006a:
            com.google.android.gms.cast.RemoteMediaPlayer r1 = r7.zzfa     // Catch:{ all -> 0x00ca }
            com.google.android.gms.cast.MediaStatus r1 = r1.getMediaStatus()     // Catch:{ all -> 0x00ca }
            com.google.android.gms.cast.MediaQueueItem r0 = r1.getQueueItem(r0)     // Catch:{ all -> 0x00ca }
            if (r0 == 0) goto L_0x007b
            int r0 = r0.getItemId()     // Catch:{ all -> 0x00ca }
            goto L_0x007c
        L_0x007b:
            r0 = 0
        L_0x007c:
            com.google.android.gms.cast.RemoteMediaPlayer r1 = r7.zzfa     // Catch:{ all -> 0x00ca }
            com.google.android.gms.cast.RemoteMediaPlayer$zza r1 = r1.zzev     // Catch:{ all -> 0x00ca }
            com.google.android.gms.common.api.GoogleApiClient r4 = r7.zzfb     // Catch:{ all -> 0x00ca }
            r1.zza(r4)     // Catch:{ all -> 0x00ca }
            r1 = 0
            com.google.android.gms.cast.RemoteMediaPlayer r4 = r7.zzfa     // Catch:{ zzdk | IllegalStateException -> 0x00a7 }
            com.google.android.gms.internal.cast.zzdh r4 = r4.zzeu     // Catch:{ zzdk | IllegalStateException -> 0x00a7 }
            com.google.android.gms.internal.cast.zzdm r5 = r7.zzgc     // Catch:{ zzdk | IllegalStateException -> 0x00a7 }
            int[] r3 = new int[r3]     // Catch:{ zzdk | IllegalStateException -> 0x00a7 }
            int r6 = r7.zzfs     // Catch:{ zzdk | IllegalStateException -> 0x00a7 }
            r3[r2] = r6     // Catch:{ zzdk | IllegalStateException -> 0x00a7 }
            org.json.JSONObject r2 = r7.zzfi     // Catch:{ zzdk | IllegalStateException -> 0x00a7 }
            r4.zza((com.google.android.gms.internal.cast.zzdm) r5, (int[]) r3, (int) r0, (org.json.JSONObject) r2)     // Catch:{ zzdk | IllegalStateException -> 0x00a7 }
            com.google.android.gms.cast.RemoteMediaPlayer r0 = r7.zzfa     // Catch:{ all -> 0x00ca }
            com.google.android.gms.cast.RemoteMediaPlayer$zza r0 = r0.zzev     // Catch:{ all -> 0x00ca }
        L_0x00a1:
            r0.zza(r1)     // Catch:{ all -> 0x00ca }
            goto L_0x00be
        L_0x00a5:
            r0 = move-exception
            goto L_0x00c0
        L_0x00a7:
            com.google.android.gms.common.api.Status r0 = new com.google.android.gms.common.api.Status     // Catch:{ all -> 0x00a5 }
            r2 = 2100(0x834, float:2.943E-42)
            r0.<init>(r2)     // Catch:{ all -> 0x00a5 }
            com.google.android.gms.common.api.Result r0 = r7.createFailedResult(r0)     // Catch:{ all -> 0x00a5 }
            com.google.android.gms.cast.RemoteMediaPlayer$MediaChannelResult r0 = (com.google.android.gms.cast.RemoteMediaPlayer.MediaChannelResult) r0     // Catch:{ all -> 0x00a5 }
            r7.setResult(r0)     // Catch:{ all -> 0x00a5 }
            com.google.android.gms.cast.RemoteMediaPlayer r0 = r7.zzfa     // Catch:{ all -> 0x00ca }
            com.google.android.gms.cast.RemoteMediaPlayer$zza r0 = r0.zzev     // Catch:{ all -> 0x00ca }
            goto L_0x00a1
        L_0x00be:
            monitor-exit(r8)     // Catch:{ all -> 0x00ca }
            return
        L_0x00c0:
            com.google.android.gms.cast.RemoteMediaPlayer r2 = r7.zzfa     // Catch:{ all -> 0x00ca }
            com.google.android.gms.cast.RemoteMediaPlayer$zza r2 = r2.zzev     // Catch:{ all -> 0x00ca }
            r2.zza(r1)     // Catch:{ all -> 0x00ca }
            throw r0     // Catch:{ all -> 0x00ca }
        L_0x00ca:
            r0 = move-exception
            monitor-exit(r8)     // Catch:{ all -> 0x00ca }
            goto L_0x00ce
        L_0x00cd:
            throw r0
        L_0x00ce:
            goto L_0x00cd
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.cast.zzbe.zza(com.google.android.gms.internal.cast.zzcn):void");
    }
}

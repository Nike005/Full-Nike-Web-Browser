package com.google.android.gms.cast;

import android.os.RemoteException;
import com.google.android.gms.cast.RemoteMediaPlayer;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.internal.cast.zzcn;
import org.json.JSONObject;

final class zzat extends RemoteMediaPlayer.zzb {
    private final /* synthetic */ RemoteMediaPlayer zzfa;
    private final /* synthetic */ GoogleApiClient zzfb;
    private final /* synthetic */ JSONObject zzfi;
    private final /* synthetic */ MediaQueueItem[] zzfj;
    private final /* synthetic */ int zzfk;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzat(RemoteMediaPlayer remoteMediaPlayer, GoogleApiClient googleApiClient, GoogleApiClient googleApiClient2, MediaQueueItem[] mediaQueueItemArr, int i, JSONObject jSONObject) {
        super(googleApiClient);
        this.zzfa = remoteMediaPlayer;
        this.zzfb = googleApiClient2;
        this.zzfj = mediaQueueItemArr;
        this.zzfk = i;
        this.zzfi = jSONObject;
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ void doExecute(Api.AnyClient anyClient) throws RemoteException {
        doExecute((zzcn) anyClient);
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0032, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:?, code lost:
        setResult((com.google.android.gms.cast.RemoteMediaPlayer.MediaChannelResult) createFailedResult(new com.google.android.gms.common.api.Status(2100)));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:?, code lost:
        r1 = r11.zzfa.zzev;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x004d, code lost:
        r11.zzfa.zzev.zza((com.google.android.gms.common.api.GoogleApiClient) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0056, code lost:
        throw r1;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0034 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zza(com.google.android.gms.internal.cast.zzcn r12) {
        /*
            r11 = this;
            com.google.android.gms.cast.RemoteMediaPlayer r12 = r11.zzfa
            java.lang.Object r12 = r12.lock
            monitor-enter(r12)
            com.google.android.gms.cast.RemoteMediaPlayer r0 = r11.zzfa     // Catch:{ all -> 0x0057 }
            com.google.android.gms.cast.RemoteMediaPlayer$zza r0 = r0.zzev     // Catch:{ all -> 0x0057 }
            com.google.android.gms.common.api.GoogleApiClient r1 = r11.zzfb     // Catch:{ all -> 0x0057 }
            r0.zza(r1)     // Catch:{ all -> 0x0057 }
            r0 = 0
            com.google.android.gms.cast.RemoteMediaPlayer r1 = r11.zzfa     // Catch:{ zzdk | IllegalStateException -> 0x0034 }
            com.google.android.gms.internal.cast.zzdh r2 = r1.zzeu     // Catch:{ zzdk | IllegalStateException -> 0x0034 }
            com.google.android.gms.internal.cast.zzdm r3 = r11.zzgc     // Catch:{ zzdk | IllegalStateException -> 0x0034 }
            com.google.android.gms.cast.MediaQueueItem[] r4 = r11.zzfj     // Catch:{ zzdk | IllegalStateException -> 0x0034 }
            int r5 = r11.zzfk     // Catch:{ zzdk | IllegalStateException -> 0x0034 }
            r6 = 0
            r7 = -1
            r8 = -1
            org.json.JSONObject r10 = r11.zzfi     // Catch:{ zzdk | IllegalStateException -> 0x0034 }
            r2.zza((com.google.android.gms.internal.cast.zzdm) r3, (com.google.android.gms.cast.MediaQueueItem[]) r4, (int) r5, (int) r6, (int) r7, (long) r8, (org.json.JSONObject) r10)     // Catch:{ zzdk | IllegalStateException -> 0x0034 }
            com.google.android.gms.cast.RemoteMediaPlayer r1 = r11.zzfa     // Catch:{ all -> 0x0057 }
            com.google.android.gms.cast.RemoteMediaPlayer$zza r1 = r1.zzev     // Catch:{ all -> 0x0057 }
        L_0x002e:
            r1.zza(r0)     // Catch:{ all -> 0x0057 }
            goto L_0x004b
        L_0x0032:
            r1 = move-exception
            goto L_0x004d
        L_0x0034:
            com.google.android.gms.common.api.Status r1 = new com.google.android.gms.common.api.Status     // Catch:{ all -> 0x0032 }
            r2 = 2100(0x834, float:2.943E-42)
            r1.<init>(r2)     // Catch:{ all -> 0x0032 }
            com.google.android.gms.common.api.Result r1 = r11.createFailedResult(r1)     // Catch:{ all -> 0x0032 }
            com.google.android.gms.cast.RemoteMediaPlayer$MediaChannelResult r1 = (com.google.android.gms.cast.RemoteMediaPlayer.MediaChannelResult) r1     // Catch:{ all -> 0x0032 }
            r11.setResult(r1)     // Catch:{ all -> 0x0032 }
            com.google.android.gms.cast.RemoteMediaPlayer r1 = r11.zzfa     // Catch:{ all -> 0x0057 }
            com.google.android.gms.cast.RemoteMediaPlayer$zza r1 = r1.zzev     // Catch:{ all -> 0x0057 }
            goto L_0x002e
        L_0x004b:
            monitor-exit(r12)     // Catch:{ all -> 0x0057 }
            return
        L_0x004d:
            com.google.android.gms.cast.RemoteMediaPlayer r2 = r11.zzfa     // Catch:{ all -> 0x0057 }
            com.google.android.gms.cast.RemoteMediaPlayer$zza r2 = r2.zzev     // Catch:{ all -> 0x0057 }
            r2.zza(r0)     // Catch:{ all -> 0x0057 }
            throw r1     // Catch:{ all -> 0x0057 }
        L_0x0057:
            r0 = move-exception
            monitor-exit(r12)     // Catch:{ all -> 0x0057 }
            goto L_0x005b
        L_0x005a:
            throw r0
        L_0x005b:
            goto L_0x005a
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.cast.zzat.zza(com.google.android.gms.internal.cast.zzcn):void");
    }
}

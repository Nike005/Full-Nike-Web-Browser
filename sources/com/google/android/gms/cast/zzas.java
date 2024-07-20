package com.google.android.gms.cast;

import android.os.RemoteException;
import com.google.android.gms.cast.RemoteMediaPlayer;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.internal.cast.zzcn;
import org.json.JSONObject;

final class zzas extends RemoteMediaPlayer.zzb {
    private final /* synthetic */ RemoteMediaPlayer zzfa;
    private final /* synthetic */ GoogleApiClient zzfb;
    private final /* synthetic */ MediaQueueItem[] zzfe;
    private final /* synthetic */ int zzff;
    private final /* synthetic */ int zzfg;
    private final /* synthetic */ long zzfh;
    private final /* synthetic */ JSONObject zzfi;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzas(RemoteMediaPlayer remoteMediaPlayer, GoogleApiClient googleApiClient, GoogleApiClient googleApiClient2, MediaQueueItem[] mediaQueueItemArr, int i, int i2, long j, JSONObject jSONObject) {
        super(googleApiClient);
        this.zzfa = remoteMediaPlayer;
        this.zzfb = googleApiClient2;
        this.zzfe = mediaQueueItemArr;
        this.zzff = i;
        this.zzfg = i2;
        this.zzfh = j;
        this.zzfi = jSONObject;
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ void doExecute(Api.AnyClient anyClient) throws RemoteException {
        doExecute((zzcn) anyClient);
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Can't wrap try/catch for region: R(4:11|12|13|14) */
    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0032, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:?, code lost:
        setResult((com.google.android.gms.cast.RemoteMediaPlayer.MediaChannelResult) createFailedResult(new com.google.android.gms.common.api.Status(2100)));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:?, code lost:
        r1 = r10.zzfa.zzev;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x004d, code lost:
        r10.zzfa.zzev.zza((com.google.android.gms.common.api.GoogleApiClient) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0056, code lost:
        throw r1;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0034 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zza(com.google.android.gms.internal.cast.zzcn r11) {
        /*
            r10 = this;
            com.google.android.gms.cast.RemoteMediaPlayer r11 = r10.zzfa
            java.lang.Object r11 = r11.lock
            monitor-enter(r11)
            com.google.android.gms.cast.RemoteMediaPlayer r0 = r10.zzfa     // Catch:{ all -> 0x0057 }
            com.google.android.gms.cast.RemoteMediaPlayer$zza r0 = r0.zzev     // Catch:{ all -> 0x0057 }
            com.google.android.gms.common.api.GoogleApiClient r1 = r10.zzfb     // Catch:{ all -> 0x0057 }
            r0.zza(r1)     // Catch:{ all -> 0x0057 }
            r0 = 0
            com.google.android.gms.cast.RemoteMediaPlayer r1 = r10.zzfa     // Catch:{ IllegalStateException -> 0x0034 }
            com.google.android.gms.internal.cast.zzdh r2 = r1.zzeu     // Catch:{ IllegalStateException -> 0x0034 }
            com.google.android.gms.internal.cast.zzdm r3 = r10.zzgc     // Catch:{ IllegalStateException -> 0x0034 }
            com.google.android.gms.cast.MediaQueueItem[] r4 = r10.zzfe     // Catch:{ IllegalStateException -> 0x0034 }
            int r5 = r10.zzff     // Catch:{ IllegalStateException -> 0x0034 }
            int r6 = r10.zzfg     // Catch:{ IllegalStateException -> 0x0034 }
            long r7 = r10.zzfh     // Catch:{ IllegalStateException -> 0x0034 }
            org.json.JSONObject r9 = r10.zzfi     // Catch:{ IllegalStateException -> 0x0034 }
            r2.zza(r3, r4, r5, r6, r7, r9)     // Catch:{ IllegalStateException -> 0x0034 }
            com.google.android.gms.cast.RemoteMediaPlayer r1 = r10.zzfa     // Catch:{ all -> 0x0057 }
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
            com.google.android.gms.common.api.Result r1 = r10.createFailedResult(r1)     // Catch:{ all -> 0x0032 }
            com.google.android.gms.cast.RemoteMediaPlayer$MediaChannelResult r1 = (com.google.android.gms.cast.RemoteMediaPlayer.MediaChannelResult) r1     // Catch:{ all -> 0x0032 }
            r10.setResult(r1)     // Catch:{ all -> 0x0032 }
            com.google.android.gms.cast.RemoteMediaPlayer r1 = r10.zzfa     // Catch:{ all -> 0x0057 }
            com.google.android.gms.cast.RemoteMediaPlayer$zza r1 = r1.zzev     // Catch:{ all -> 0x0057 }
            goto L_0x002e
        L_0x004b:
            monitor-exit(r11)     // Catch:{ all -> 0x0057 }
            return
        L_0x004d:
            com.google.android.gms.cast.RemoteMediaPlayer r2 = r10.zzfa     // Catch:{ all -> 0x0057 }
            com.google.android.gms.cast.RemoteMediaPlayer$zza r2 = r2.zzev     // Catch:{ all -> 0x0057 }
            r2.zza(r0)     // Catch:{ all -> 0x0057 }
            throw r1     // Catch:{ all -> 0x0057 }
        L_0x0057:
            r0 = move-exception
            monitor-exit(r11)     // Catch:{ all -> 0x0057 }
            goto L_0x005b
        L_0x005a:
            throw r0
        L_0x005b:
            goto L_0x005a
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.cast.zzas.zza(com.google.android.gms.internal.cast.zzcn):void");
    }
}

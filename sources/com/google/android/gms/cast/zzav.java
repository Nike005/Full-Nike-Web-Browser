package com.google.android.gms.cast;

import android.os.RemoteException;
import com.google.android.gms.cast.RemoteMediaPlayer;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.internal.cast.zzcn;
import org.json.JSONObject;

final class zzav extends RemoteMediaPlayer.zzb {
    private final /* synthetic */ RemoteMediaPlayer zzfa;
    private final /* synthetic */ GoogleApiClient zzfb;
    private final /* synthetic */ JSONObject zzfi;
    private final /* synthetic */ MediaQueueItem[] zzfm;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzav(RemoteMediaPlayer remoteMediaPlayer, GoogleApiClient googleApiClient, GoogleApiClient googleApiClient2, MediaQueueItem[] mediaQueueItemArr, JSONObject jSONObject) {
        super(googleApiClient);
        this.zzfa = remoteMediaPlayer;
        this.zzfb = googleApiClient2;
        this.zzfm = mediaQueueItemArr;
        this.zzfi = jSONObject;
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ void doExecute(Api.AnyClient anyClient) throws RemoteException {
        doExecute((zzcn) anyClient);
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Can't wrap try/catch for region: R(4:11|12|13|14) */
    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0031, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:?, code lost:
        setResult((com.google.android.gms.cast.RemoteMediaPlayer.MediaChannelResult) createFailedResult(new com.google.android.gms.common.api.Status(2100)));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:?, code lost:
        r1 = r11.zzfa.zzev;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x004c, code lost:
        r11.zzfa.zzev.zza((com.google.android.gms.common.api.GoogleApiClient) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0055, code lost:
        throw r1;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0033 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zza(com.google.android.gms.internal.cast.zzcn r12) {
        /*
            r11 = this;
            com.google.android.gms.cast.RemoteMediaPlayer r12 = r11.zzfa
            java.lang.Object r12 = r12.lock
            monitor-enter(r12)
            com.google.android.gms.cast.RemoteMediaPlayer r0 = r11.zzfa     // Catch:{ all -> 0x0056 }
            com.google.android.gms.cast.RemoteMediaPlayer$zza r0 = r0.zzev     // Catch:{ all -> 0x0056 }
            com.google.android.gms.common.api.GoogleApiClient r1 = r11.zzfb     // Catch:{ all -> 0x0056 }
            r0.zza(r1)     // Catch:{ all -> 0x0056 }
            r0 = 0
            com.google.android.gms.cast.RemoteMediaPlayer r1 = r11.zzfa     // Catch:{ zzdk | IllegalStateException -> 0x0033 }
            com.google.android.gms.internal.cast.zzdh r2 = r1.zzeu     // Catch:{ zzdk | IllegalStateException -> 0x0033 }
            com.google.android.gms.internal.cast.zzdm r3 = r11.zzgc     // Catch:{ zzdk | IllegalStateException -> 0x0033 }
            r4 = 0
            r5 = -1
            com.google.android.gms.cast.MediaQueueItem[] r7 = r11.zzfm     // Catch:{ zzdk | IllegalStateException -> 0x0033 }
            r8 = 0
            r9 = 0
            org.json.JSONObject r10 = r11.zzfi     // Catch:{ zzdk | IllegalStateException -> 0x0033 }
            r2.zza((com.google.android.gms.internal.cast.zzdm) r3, (int) r4, (long) r5, (com.google.android.gms.cast.MediaQueueItem[]) r7, (int) r8, (java.lang.Integer) r9, (org.json.JSONObject) r10)     // Catch:{ zzdk | IllegalStateException -> 0x0033 }
            com.google.android.gms.cast.RemoteMediaPlayer r1 = r11.zzfa     // Catch:{ all -> 0x0056 }
            com.google.android.gms.cast.RemoteMediaPlayer$zza r1 = r1.zzev     // Catch:{ all -> 0x0056 }
        L_0x002d:
            r1.zza(r0)     // Catch:{ all -> 0x0056 }
            goto L_0x004a
        L_0x0031:
            r1 = move-exception
            goto L_0x004c
        L_0x0033:
            com.google.android.gms.common.api.Status r1 = new com.google.android.gms.common.api.Status     // Catch:{ all -> 0x0031 }
            r2 = 2100(0x834, float:2.943E-42)
            r1.<init>(r2)     // Catch:{ all -> 0x0031 }
            com.google.android.gms.common.api.Result r1 = r11.createFailedResult(r1)     // Catch:{ all -> 0x0031 }
            com.google.android.gms.cast.RemoteMediaPlayer$MediaChannelResult r1 = (com.google.android.gms.cast.RemoteMediaPlayer.MediaChannelResult) r1     // Catch:{ all -> 0x0031 }
            r11.setResult(r1)     // Catch:{ all -> 0x0031 }
            com.google.android.gms.cast.RemoteMediaPlayer r1 = r11.zzfa     // Catch:{ all -> 0x0056 }
            com.google.android.gms.cast.RemoteMediaPlayer$zza r1 = r1.zzev     // Catch:{ all -> 0x0056 }
            goto L_0x002d
        L_0x004a:
            monitor-exit(r12)     // Catch:{ all -> 0x0056 }
            return
        L_0x004c:
            com.google.android.gms.cast.RemoteMediaPlayer r2 = r11.zzfa     // Catch:{ all -> 0x0056 }
            com.google.android.gms.cast.RemoteMediaPlayer$zza r2 = r2.zzev     // Catch:{ all -> 0x0056 }
            r2.zza(r0)     // Catch:{ all -> 0x0056 }
            throw r1     // Catch:{ all -> 0x0056 }
        L_0x0056:
            r0 = move-exception
            monitor-exit(r12)     // Catch:{ all -> 0x0056 }
            goto L_0x005a
        L_0x0059:
            throw r0
        L_0x005a:
            goto L_0x0059
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.cast.zzav.zza(com.google.android.gms.internal.cast.zzcn):void");
    }
}
